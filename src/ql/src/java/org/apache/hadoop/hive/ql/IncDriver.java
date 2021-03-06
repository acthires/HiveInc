package org.apache.hadoop.hive.ql;

import java.io.DataInput;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.MetaStoreUtils;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.Schema;
import org.apache.hadoop.hive.ql.exec.ConditionalTask;
import org.apache.hadoop.hive.ql.exec.FetchTask;
import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.exec.ReduceSinkOperator;
import org.apache.hadoop.hive.ql.exec.Task;
import org.apache.hadoop.hive.ql.exec.TaskFactory;
import org.apache.hadoop.hive.ql.exec.TaskResult;
import org.apache.hadoop.hive.ql.exec.TaskRunner;
import org.apache.hadoop.hive.ql.exec.Utilities;
import org.apache.hadoop.hive.ql.history.HiveHistory.Keys;
import org.apache.hadoop.hive.ql.hooks.ExecuteWithHookContext;
import org.apache.hadoop.hive.ql.hooks.Hook;
import org.apache.hadoop.hive.ql.hooks.HookContext;
import org.apache.hadoop.hive.ql.hooks.HookUtils;
import org.apache.hadoop.hive.ql.hooks.PostExecute;
import org.apache.hadoop.hive.ql.hooks.PreExecute;
import org.apache.hadoop.hive.ql.hooks.WriteEntity;
import org.apache.hadoop.hive.ql.lockmgr.HiveLock;
import org.apache.hadoop.hive.ql.lockmgr.HiveLockManager;
import org.apache.hadoop.hive.ql.lockmgr.LockException;
import org.apache.hadoop.hive.ql.log.PerfLogger;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.metadata.formatting.JsonMetaDataFormatter;
import org.apache.hadoop.hive.ql.metadata.formatting.MetaDataFormatUtils;
import org.apache.hadoop.hive.ql.metadata.formatting.MetaDataFormatter;
import org.apache.hadoop.hive.ql.parse.ASTNode;
import org.apache.hadoop.hive.ql.parse.ASTNodeUtils;
import org.apache.hadoop.hive.ql.parse.BaseSemanticAnalyzer;
import org.apache.hadoop.hive.ql.parse.ExtractTmpSemanticAnalyzer;
import org.apache.hadoop.hive.ql.parse.HiveSemanticAnalyzerHook;
import org.apache.hadoop.hive.ql.parse.HiveSemanticAnalyzerHookContext;
import org.apache.hadoop.hive.ql.parse.HiveSemanticAnalyzerHookContextImpl;
import org.apache.hadoop.hive.ql.parse.IncQueryParser;
import org.apache.hadoop.hive.ql.parse.IncSplitSemanticAnalyzer;
import org.apache.hadoop.hive.ql.parse.IncSplitSemanticAnalyzer.SimpleQB;
import org.apache.hadoop.hive.ql.parse.ParseDriver;
import org.apache.hadoop.hive.ql.parse.ParseUtils;
import org.apache.hadoop.hive.ql.parse.QB;
import org.apache.hadoop.hive.ql.parse.SemanticAnalyzer;
import org.apache.hadoop.hive.ql.parse.SemanticAnalyzerFactory;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.parse.VariableSubstitution;
import org.apache.hadoop.hive.ql.plan.HiveOperation;
import org.apache.hadoop.hive.ql.plan.MapWork;
import org.apache.hadoop.hive.ql.plan.MapredWork;
import org.apache.hadoop.hive.ql.plan.PartitionDesc;
import org.apache.hadoop.hive.ql.plan.TableDesc;
import org.apache.hadoop.hive.ql.processors.CommandProcessor;
import org.apache.hadoop.hive.ql.processors.CommandProcessorFactory;
import org.apache.hadoop.hive.ql.processors.CommandProcessorResponse;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.hive.ql.session.SessionState.LogHelper;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.shims.ShimLoader;

public class IncDriver implements CommandProcessor {

  static final private Log LOG = LogFactory.getLog(IncDriver.class.getName());
  static final private LogHelper console = new LogHelper(LOG);

  private static final Object compileMonitor = new Object();

  private int maxRows = 100;
  ByteStream.Output bos = new ByteStream.Output();

  private HiveConf conf;
  private DataInput resStream;
  private Context ctx;
  private QueryPlan tmpTablePlan;
  //create a qblist to store the qb of the two Phases
  //private ArrayList<QB> qblist ;
  private ArrayList<QB> gbyCacheQBlist = new ArrayList<QB>();
  private ArrayList<QB> joinCacheQBlist = new ArrayList<QB>();
  private QB finalQB = null;
  private IncSplitSemanticAnalyzer semcache;
  private SemanticAnalyzer sem;
  private ArrayList<QueryPlan> cacheTablePlan = null;
  private QueryPlan FinalPlan;
  private QueryPlan OriginalPlan;
  private ASTNode originalTree;
  private incCtx  incCtx;
  private Schema schema;
  private HiveLockManager hiveLockMgr;

  private String errorMessage;
  private String SQLState;
  private Throwable downstreamError;

  // A limit on the number of threads that can be launched
  private int maxthreads;
  private static final int SLEEP_TIME = 2000;
  protected int tryCount = Integer.MAX_VALUE;

  private final Timer timer = new Timer();
  private static SessionState ss ;
  // statistic execution times of incremental query
  private final AtomicInteger counter = new AtomicInteger(0);
  private volatile boolean run = true;
  private boolean isINC = false;
  private final HashSet<String> toRemovePath = new HashSet<String>();

  //inc table name mapped to Table object of currently inc table
  //private HashMap< String , Table > tabNameToTab;
  private static long start = System.currentTimeMillis();
  private static long lastEnd = 0;//last end time is begin time of current inc run
  private final ArrayList<String> incList = new ArrayList<String>();

  public void init() {
    Operator.resetId();
  }


  public boolean isINC() {
    return isINC;
  }


  public Schema getSchema() {
    return schema;
  }

  /**
   * Get a Schema with fields represented with native Hive types
   */
  public static Schema getSchema(BaseSemanticAnalyzer sem, HiveConf conf) {
    Schema schema = null;

    // If we have a plan, prefer its logical result schema if it's
    // available; otherwise, try digging out a fetch task; failing that,
    // give up.
    if (sem == null) {
      // can't get any info without a plan
    } else if (sem.getResultSchema() != null) {
      List<FieldSchema> lst = sem.getResultSchema();
      schema = new Schema(lst, null);
    } else if (sem.getFetchTask() != null) {
      FetchTask ft = sem.getFetchTask();
      TableDesc td = ft.getTblDesc();
      // partitioned tables don't have tableDesc set on the FetchTask. Instead
      // they have a list of PartitionDesc objects, each with a table desc.
      // Let's
      // try to fetch the desc for the first partition and use it's
      // deserializer.
      if (td == null && ft.getWork() != null && ft.getWork().getPartDesc() != null) {
        if (ft.getWork().getPartDesc().size() > 0) {
          td = ft.getWork().getPartDesc().get(0).getTableDesc();
        }
      }

      if (td == null) {
        LOG.info("No returning schema.");
      } else {
        String tableName = "result";
        List<FieldSchema> lst = null;
        try {
          lst = MetaStoreUtils.getFieldsFromDeserializer(tableName, td.getDeserializer());
        } catch (Exception e) {
          LOG.warn("Error getting schema: "
              + org.apache.hadoop.util.StringUtils.stringifyException(e));
        }
        if (lst != null) {
          schema = new Schema(lst, null);
        }
      }
    }
    if (schema == null) {
      schema = new Schema();
    }
    LOG.info("Returning Hive schema: " + schema);
    return schema;
  }

  /**
   * Get a Schema with fields represented with Thrift DDL types
   */
  public Schema getThriftSchema() throws Exception {
    Schema schema;
    try {
      schema = getSchema();
      if (schema != null) {
        List<FieldSchema> lst = schema.getFieldSchemas();
        // Go over the schema and convert type to thrift type
        if (lst != null) {
          for (FieldSchema f : lst) {
            f.setType(MetaStoreUtils.typeToThriftType(f.getType()));
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
    LOG.info("Returning Thrift schema: " + schema);
    return schema;
  }

  /**
   * Return the maximum number of rows returned by getResults
   */
  public int getMaxRows() {
    return maxRows;
  }

  /**
   * Set the maximum number of rows returned by getResults
   */
  public void setMaxRows(int maxRows) {
    this.maxRows = maxRows;
  }

  public boolean hasReduceTasks(List<Task<? extends Serializable>> tasks) {
    if (tasks == null) {
      return false;
    }

    boolean hasReduce = false;
    for (Task<? extends Serializable> task : tasks) {
      if (task.hasReduce()) {
        return true;
      }

      hasReduce = (hasReduce || hasReduceTasks(task.getChildTasks()));
    }
    return hasReduce;
  }

  /**
   * for backwards compatibility with current tests
   */
  public IncDriver(HiveConf conf) {
    this.conf = conf;
  }

  public IncDriver() {
    if (SessionState.get() != null) {
      ss = SessionState.get();
      conf = SessionState.get().getConf();
    }else{
    }
  }

  /**
   * Compile a new query. Any currently-planned query associated with this Driver is discarded.
   *
   * @param command
   *          The SQL query to compile.
   */
  public int compile(String command) {
    return compile(command, true);
  }

  /**
   * Hold state variables specific to each query being executed, that may not
   * be consistent in the overall SessionState
   */
  private static class QueryState {
    private HiveOperation op;
    private String cmd;
    private boolean init = false;

    /**
     * Initialize the queryState with the query state variables
     */
    public void init(HiveOperation op, String cmd) {
      this.op = op;
      this.cmd = cmd;
      this.init = true;
    }

    public boolean isInitialized() {
      return this.init;
    }

    public HiveOperation getOp() {
      return this.op;
    }

    public String getCmd() {
      return this.cmd;
    }
  }

  public void saveSession(QueryState qs) {
    SessionState oldss = SessionState.get();
    if (oldss != null && oldss.getHiveOperation() != null) {
      qs.init(oldss.getHiveOperation(), oldss.getCmd());
    }
  }

  public void restoreSession(QueryState qs) {
    SessionState ss = SessionState.get();
    if (ss != null && qs != null && qs.isInitialized()) {
      ss.setCmd(qs.getCmd());
      ss.setCommandType(qs.getOp());
    }
  }

  public static class  incCtx{
     // inc-query context
    private IncQueryParser parserRes;
    ArrayList<QB> toSplitQB = new ArrayList<QB>();
    Map<QB,ReduceSinkOperator> QBToGBYRS = new HashMap<QB,ReduceSinkOperator>();//toSplitQB to GBYRS
    Map<QB,ArrayList<Long>> inOutSize = new HashMap<QB,ArrayList<Long>>();//toSplitQB to insize outsize
    Map<String,String> taskIdtoJobId = new HashMap<String,String>();//taskId to JobId
    Map<QB,SimpleQB> qbToSimpleQB= new HashMap<QB,SimpleQB>(); // use simpleQB's info to split qb
    Map<QB,ArrayList<String>> splitQBToScanAllAliases = new HashMap<QB,ArrayList<String>>();
    public IncQueryParser getParserRes() {
      return parserRes;
    }

    public ArrayList<QB> getToSplitQB(){
      return toSplitQB;
    }

    public Map<QB,ReduceSinkOperator> getQBToGBYRS(){
      return QBToGBYRS;
    }

    public Map<QB,SimpleQB> getQBToSimpleQB(){
      return qbToSimpleQB;
    }

    public Map<QB,ArrayList<String>> getSplitQBToScanAllAliases(){
      return this.splitQBToScanAllAliases;
    }

  }

  /**
   * Compile a new query, but potentially reset taskID counter.  Not resetting task counter
   * is useful for generating re-entrant QL queries.
   * @param command  The HiveQL query to compile
   * @param resetTaskIds Resets taskID counter if true.
   * @return 0 for ok
   */
  public int compile(String command, boolean resetTaskIds) {
    PerfLogger perfLogger = PerfLogger.getPerfLogger();
    perfLogger.PerfLogBegin(LOG, PerfLogger.COMPILE);

    //holder for parent command type/string when executing reentrant queries
    QueryState queryState = new QueryState();

    if (tmpTablePlan != null||cacheTablePlan != null ||FinalPlan != null ||OriginalPlan != null) {
      close();
      tmpTablePlan = null;
      cacheTablePlan = null ;
      FinalPlan = null ;
      OriginalPlan = null;
    }

    if (resetTaskIds) {
      TaskFactory.resetId();
    }
    saveSession(queryState);

    try {
      command = new VariableSubstitution().substitute(conf,command);
      ctx = new Context(conf);
      ctx.setTryCount(getTryCount());
      ctx.setCmd(command);
      ctx.setHDFSCleanup(true);

      perfLogger.PerfLogBegin(LOG, PerfLogger.PARSE);
      ParseDriver pd = new ParseDriver();
      ASTNode tree = pd.parse(command, ctx);
      tree = ParseUtils.findRootNonNullToken(tree);
      perfLogger.PerfLogEnd(LOG, PerfLogger.PARSE);
      //parser inc-query :1,Integrity Check .2,extract parameters
      IncQueryParser incParser = new IncQueryParser(tree);
      perfLogger.PerfLogBegin(LOG, PerfLogger.ANALYZE);
      incParser.analyzeIncAlias();
      if(incParser.getIncTableAlias().size() != 0){
        incParser.checkIncTokenParams();
        isINC = true;
      }else{
        //on not a incremental query, do generating OriginalQueryPlan
        genOriginalQueryPlan(tree,command,perfLogger);
        return 0;
      }
      originalTree = ASTNodeUtils.clone(tree);
      //Initial incCtx, store store of inc-query parser
      incCtx = new incCtx();
      incCtx.parserRes = incParser;

      //semantic analyze stage of  tmp table, generate the query plan of tmp table
      genExtractTmpQueryPlan(tree,command,perfLogger);
      //semantic analyze stage of Cache Table, generate the query plan of cache table
      //    IncPhase1:insert into cache table ;
      //    create a qblist to store the qb of the two Phases,use getQBList in semantic analyze class to get it
      //    qblist.get(0) is the IncPhase1's QB ,It's used in genExtractCacheQueryPlan
      //    QB will be used in semantic analyze class's method analyzeQB(QB) to generate the MR tasks
      genExtractCacheQueryPlan(tree,command,perfLogger);
      //LOG.info("IncDriver qblist.size = " + qblist.size());
      //Final semantic analyze stage , generate the final query plan
      //    IncPhase2:select the final result from cache table;
      //    qblist.get(1) is the IncPhase2's QB ,It's used in genFinalQueryPlan as the parameter.
      //    QB will be used in semantic analyze class's method analyzeQB(QB) to generate the MR tasks

      /*for(int i=0;i<qblist.size()-1;i++){
        cacheTablePlan.add(genQueryPlan(tree,command,qblist.get(i),perfLogger));
      }
      FinalPlan = genQueryPlan(tree,command,qblist.get(qblist.size()-1),perfLogger);*/

      return 0;
    } catch (Exception e) {
      ErrorMsg error = ErrorMsg.getErrorMsg(e.getMessage());
      errorMessage = "FAILED: " + e.getClass().getSimpleName();
      if (error != ErrorMsg.GENERIC_ERROR) {
        errorMessage += " [Error "  + error.getErrorCode()  + "]:";
      }

      // HIVE-4889
      if ((e instanceof IllegalArgumentException) && e.getMessage() == null && e.getCause() != null) {
        errorMessage += " " + e.getCause().getMessage();
      } else {
        errorMessage += " " + e.getMessage();
      }

      SQLState = error.getSQLState();
      downstreamError = e;
      console.printError(errorMessage, "\n"
          + org.apache.hadoop.util.StringUtils.stringifyException(e));
      return error.getErrorCode();
    } finally {
      perfLogger.PerfLogEnd(LOG, PerfLogger.COMPILE);
      restoreSession(queryState);
    }
  }


  private void genOriginalQueryPlan(ASTNode tree, String command, PerfLogger perfLogger ) throws Exception {

    LOG.info("genOriginalQueryPlan for: " + tree.dump());
    //when TOK_INC is nonexistent, step into normally compile flow
    BaseSemanticAnalyzer sem = SemanticAnalyzerFactory.get(conf, tree);
    List<HiveSemanticAnalyzerHook> saHooks =
        getHooks(HiveConf.ConfVars.SEMANTIC_ANALYZER_HOOK,
            HiveSemanticAnalyzerHook.class);
    if (saHooks != null) {
      HiveSemanticAnalyzerHookContext hookCtx = new HiveSemanticAnalyzerHookContextImpl();
      hookCtx.setConf(conf);
      for (HiveSemanticAnalyzerHook hook : saHooks) {
        tree = hook.preAnalyze(hookCtx, tree);
      }
      sem.analyze(tree, ctx);
      hookCtx.update(sem);
      for (HiveSemanticAnalyzerHook hook : saHooks) {
        hook.postAnalyze(hookCtx, sem.getRootTasks());
      }
    } else {
      sem.analyze(tree, ctx);
    }
    LOG.info("Semantic Analysis Completed");
    sem.validate();
    perfLogger.PerfLogEnd(LOG, PerfLogger.ANALYZE);
    OriginalPlan = new QueryPlan(command, sem, perfLogger.getStartTime(PerfLogger.DRIVER_RUN));

    // initialize FetchTask right here
    if (OriginalPlan.getFetchTask() != null) {
      OriginalPlan.getFetchTask().initialize(conf, OriginalPlan, null);
    }
    // get the output schema
    schema = getSchema(sem, conf);

  }


  private void genExtractTmpQueryPlan(ASTNode tree, String command, PerfLogger perfLogger) throws Exception {
    boolean needCrtTmpTable = HiveConf.getBoolVar(conf, HiveConf.ConfVars.HIVEINCEXTRACTTMP);
    if( needCrtTmpTable ){
      LOG.info("Begin Extract Tmp table, AST tree is: \n" + tree.dump());
      ExtractTmpSemanticAnalyzer semTmp =new ExtractTmpSemanticAnalyzer(conf,tree ,incCtx);
      semTmp.initSem(ctx);
      //generate ql of creating tmp table ,and running it
      semTmp.genAndrunCrtTmpQuery(tree);
      //analyze optrees of tmp table , change optrees
      if(semTmp.canExtractTmp()){
        semTmp.doExtractTmpCompile();
        LOG.info("End Extract Tmp table, AST tree is: \n" + tree.dump());
        semTmp.validate();
        tmpTablePlan = new QueryPlan(command, semTmp, perfLogger.getStartTime(PerfLogger.DRIVER_RUN));
        // initialize FetchTask right here
        if (tmpTablePlan.getFetchTask() != null) {
          tmpTablePlan.getFetchTask().initialize(conf, tmpTablePlan, null);
        }
      }
    }

  }


  private QueryPlan genQueryPlan(ASTNode tree, String command, QB qb, PerfLogger perfLogger) throws Exception {
    QueryPlan ret = null;
    sem =new IncSplitSemanticAnalyzer(conf,tree,incCtx);
    sem.initSem(ctx);
    List<HiveSemanticAnalyzerHook> saHooks =
        getHooks(HiveConf.ConfVars.SEMANTIC_ANALYZER_HOOK,
            HiveSemanticAnalyzerHook.class);
    // Do semantic analysis and plan generation
    if (saHooks != null) {
      HiveSemanticAnalyzerHookContext hookCtx = new HiveSemanticAnalyzerHookContextImpl();
      hookCtx.setConf(conf);
      for (HiveSemanticAnalyzerHook hook : saHooks) {
        tree = hook.preAnalyze(hookCtx, tree);
      }

      sem.analyzeQB(tree,qb);
      sem.mapReudceCompiler();
      hookCtx.update(sem);
      for (HiveSemanticAnalyzerHook hook : saHooks) {
        hook.postAnalyze(hookCtx, sem.getRootTasks());
      }
    } else {
      sem.analyzeQB(tree,qb);
      sem.mapReudceCompiler();
    }
    LOG.info("Semantic Analysis Completed");
    // validate the plan
    sem.validate();
    perfLogger.PerfLogEnd(LOG, PerfLogger.ANALYZE);

    ret = new QueryPlan(command, sem, perfLogger.getStartTime(PerfLogger.DRIVER_RUN));

    // initialize FetchTask right here
    if (ret.getFetchTask() != null) {
      ret.getFetchTask().initialize(conf, FinalPlan, null);
    }

    // get the output schema
    schema = getSchema(sem, conf);

    return ret;

  }
  /**
   * Generates IncPhase1's Query Plan
   * IncPhase1:insert into cache table
   * create a IncSplitSemanticAnalyzer object to do incremental table Semantic Analysis
   * @param tree
   *          The root of  AST tree
   * @param command
   *          query's command
   * @param perfLogger
   *          perfLogger
   * @return ArrayList<QB> :qblist.get(0) is the IncPhase1's QB ,It's used in genExtractCacheQueryPlan,
   *           qblist.get(1) is the IncPhase2's QB ,It's used in genFinalQueryPlan as the parameter.
   * @throws SemanticException IncSplitSemanticAnalyzer Compile
   */

  private void genExtractCacheQueryPlan(ASTNode tree, String command, PerfLogger perfLogger) throws Exception {

    //SemanticAnalyzer to do incremental optimization
    //split the original query into two queries ,one for insert cache table ,one for get the final result
    semcache =new IncSplitSemanticAnalyzer(conf,tree,incCtx);
    semcache.initSem(ctx);
    List<HiveSemanticAnalyzerHook> saHooks =
        getHooks(HiveConf.ConfVars.SEMANTIC_ANALYZER_HOOK,
            HiveSemanticAnalyzerHook.class);
    // Do semantic analysis and plan generation
    if (saHooks != null) {
      HiveSemanticAnalyzerHookContext hookCtx = new HiveSemanticAnalyzerHookContextImpl();
      hookCtx.setConf(conf);
      for (HiveSemanticAnalyzerHook hook : saHooks) {
        tree = hook.preAnalyze(hookCtx, tree);
      }
      //generate QL of creating cache table ,and run it by  a driver
      semcache.genAndrunCrtCacheQuery();
      //Spiting origin query to generate insert ql and query ql,then generate the MR tasks
      semcache.doIncSplitCompile();
      /*semcache.genLogicalPlan();
      semcache.mapReudceCompiler();
      hookCtx.update(semcache);
      for (HiveSemanticAnalyzerHook hook : saHooks) {
        hook.postAnalyze(hookCtx, semcache.getRootTasks());
      }*/
    } else {
      semcache.genAndrunCrtCacheQuery();
      semcache.doIncSplitCompile();

      /*semcache.genLogicalPlan();
      semcache.mapReudceCompiler();*/
    }
    this.gbyCacheQBlist = semcache.getGbyCacheQBList();
    this.joinCacheQBlist = semcache.getJoinCacheQBList();
    this.finalQB = semcache.getFinalQB();

    incCtx.getParserRes().setIncAliasToTable(semcache.getDb());
    incCtx.getParserRes().setScanAllIncAliases(semcache.getScanAllIncAliases());

    //tabNameToTab = semcache.getTabNameToTab();
    LOG.info("Semantic Analysis Completed");

    /*// validate the plan
    semcache.validate();
    perfLogger.PerfLogEnd(LOG, PerfLogger.ANALYZE);

    cacheTablePlan = new QueryPlan(command, semcache, perfLogger.getStartTime(PerfLogger.DRIVER_RUN));

    // initialize FetchTask right here
    if (cacheTablePlan.getFetchTask() != null) {
      cacheTablePlan.getFetchTask().initialize(conf, cacheTablePlan, null);
    }
    //qblist.get(0) is the IncPhase1's QB ,It's used in doIncSplitCompile,
    //qblist.get(1) is the IncPhase2's QB ,It's used in genFinalQueryPlan as the parameter.
    ArrayList<QB> qblist = semcache.getQBList();



    //insert into cache table ,schema is useless
    //schema = getSchema(semcache, conf);*/
    /*if(qblist.size() < 2){
      throw new SemanticException("ERROR:In IncSplitSemanticAnalyzer generating qblist get error! ");
    }*/
    //return qblist;
  }


  /*public QueryPlan getCacheTablePlan() {
    return cacheTablePlan;
  }*/
  public QueryPlan getFinalPlan() {
    return FinalPlan;
  }

  public QueryPlan getOriginalPlan() {
    return OriginalPlan;
  }



  /**
   * @param hiveLocks
   *          list of hive locks to be released Release all the locks specified. If some of the
   *          locks have already been released, ignore them
   **/
  private void releaseLocks(List<HiveLock> hiveLocks) {
    PerfLogger perfLogger = PerfLogger.getPerfLogger();
    perfLogger.PerfLogBegin(LOG, PerfLogger.RELEASE_LOCKS);

    if (hiveLocks != null) {
      ctx.getHiveLockMgr().releaseLocks(hiveLocks);
    }
    ctx.setHiveLocks(null);

    perfLogger.PerfLogEnd(LOG, PerfLogger.RELEASE_LOCKS);
  }

  public CommandProcessorResponse run(String command) throws CommandNeedRetryException {
	start = System.currentTimeMillis();
    CommandProcessorResponse cpr = runInternal(command);
    if(cpr.getResponseCode() == 0) {
      return cpr;
    }
    SessionState ss = SessionState.get();
    if(ss == null) {
      return cpr;
    }
    MetaDataFormatter mdf = MetaDataFormatUtils.getFormatter(ss.getConf());
    if(!(mdf instanceof JsonMetaDataFormatter)) {
      return cpr;
    }
    /*Here we want to encode the error in machine readable way (e.g. JSON)
     * Ideally, errorCode would always be set to a canonical error defined in ErrorMsg.
     * In practice that is rarely the case, so the messy logic below tries to tease
     * out canonical error code if it can.  Exclude stack trace from output when
     * the error is a specific/expected one.
     * It's written to stdout for backward compatibility (WebHCat consumes it).*/
    try {
      if(downstreamError == null) {
        mdf.error(ss.out, errorMessage, cpr.getResponseCode(), SQLState);
        return cpr;
      }
      ErrorMsg canonicalErr = ErrorMsg.getErrorMsg(cpr.getResponseCode());
      if(canonicalErr != null && canonicalErr != ErrorMsg.GENERIC_ERROR) {
        /*Some HiveExceptions (e.g. SemanticException) don't set
          canonical ErrorMsg explicitly, but there is logic
          (e.g. #compile()) to find an appropriate canonical error and
          return its code as error code. In this case we want to
          preserve it for downstream code to interpret*/
        mdf.error(ss.out, errorMessage, cpr.getResponseCode(), SQLState, null);
        return cpr;
      }
      if(downstreamError instanceof HiveException) {
        HiveException rc = (HiveException) downstreamError;
        mdf.error(ss.out, errorMessage,
                rc.getCanonicalErrorMsg().getErrorCode(), SQLState,
                rc.getCanonicalErrorMsg() == ErrorMsg.GENERIC_ERROR ?
                        org.apache.hadoop.util.StringUtils.stringifyException(rc)
                        : null);
      }
      else {
        ErrorMsg canonicalMsg =
                ErrorMsg.getErrorMsg(downstreamError.getMessage());
        mdf.error(ss.out, errorMessage, canonicalMsg.getErrorCode(),
                SQLState, org.apache.hadoop.util.StringUtils.
                stringifyException(downstreamError));
      }
    }
    catch(HiveException ex) {
      console.printError("Unable to JSON-encode the error",
              org.apache.hadoop.util.StringUtils.stringifyException(ex));
    }
    return cpr;
  }
  private CommandProcessorResponse runInternal(String command) throws CommandNeedRetryException {
    errorMessage = null;
    SQLState = null;
    downstreamError = null;

    if (!validateConfVariables()) {
      return new CommandProcessorResponse(12, errorMessage, SQLState);
    }

    HiveDriverRunHookContext hookContext = new HiveDriverRunHookContextImpl(conf, command);
    // Get all the driver run hooks and pre-execute them.
    List<HiveDriverRunHook> driverRunHooks;
    try {
      driverRunHooks = getHooks(HiveConf.ConfVars.HIVE_DRIVER_RUN_HOOKS,
          HiveDriverRunHook.class);
      for (HiveDriverRunHook driverRunHook : driverRunHooks) {
          driverRunHook.preDriverRun(hookContext);
      }
    } catch (Exception e) {
      errorMessage = "FAILED: Hive Internal Error: " + Utilities.getNameMessage(e);
      SQLState = ErrorMsg.findSQLState(e.getMessage());
      downstreamError = e;
      console.printError(errorMessage + "\n"
          + org.apache.hadoop.util.StringUtils.stringifyException(e));
      return new CommandProcessorResponse(12, errorMessage, SQLState);
    }

    // Reset the perf logger
    PerfLogger perfLogger = PerfLogger.getPerfLogger(true);
    perfLogger.PerfLogBegin(LOG, PerfLogger.DRIVER_RUN);
    perfLogger.PerfLogBegin(LOG, PerfLogger.TIME_TO_SUBMIT);

    int ret;
    synchronized (compileMonitor) {
      ret = compile(command);
    }
    if (ret != 0) {
      releaseLocks(ctx.getHiveLocks());
      return new CommandProcessorResponse(ret, errorMessage, SQLState);
    }



    ret = execute(command);
    if (ret != 0) {
      //if needRequireLock is false, the release here will do nothing because there is no lock
      releaseLocks(ctx.getHiveLocks());
      return new CommandProcessorResponse(ret, errorMessage, SQLState);
    }

    //if needRequireLock is false, the release here will do nothing because there is no lock
    releaseLocks(ctx.getHiveLocks());

    perfLogger.PerfLogEnd(LOG, PerfLogger.DRIVER_RUN);

    // Take all the driver run hooks and post-execute them.
    try {
      for (HiveDriverRunHook driverRunHook : driverRunHooks) {
          driverRunHook.postDriverRun(hookContext);
      }
    } catch (Exception e) {
      errorMessage = "FAILED: Hive Internal Error: " + Utilities.getNameMessage(e);
      SQLState = ErrorMsg.findSQLState(e.getMessage());
      downstreamError = e;
      console.printError(errorMessage + "\n"
          + org.apache.hadoop.util.StringUtils.stringifyException(e));
      return new CommandProcessorResponse(12, errorMessage, SQLState);
    }

    return new CommandProcessorResponse(ret);
  }

  /**
   * Validate configuration variables.
   *
   * @return
   */
  private boolean validateConfVariables() {
    boolean valid = true;
    if ((!conf.getBoolVar(HiveConf.ConfVars.HIVE_HADOOP_SUPPORTS_SUBDIRECTORIES))
        && ((conf.getBoolVar(HiveConf.ConfVars.HADOOPMAPREDINPUTDIRRECURSIVE)) || (conf
              .getBoolVar(HiveConf.ConfVars.HIVEOPTLISTBUCKETING)) || ((conf
                  .getBoolVar(HiveConf.ConfVars.HIVE_OPTIMIZE_UNION_REMOVE))))) {
      errorMessage = "FAILED: Hive Internal Error: "
          + ErrorMsg.SUPPORT_DIR_MUST_TRUE_FOR_LIST_BUCKETING.getMsg();
      SQLState = ErrorMsg.SUPPORT_DIR_MUST_TRUE_FOR_LIST_BUCKETING.getSQLState();
      console.printError(errorMessage + "\n");
      valid = false;
    }
    return valid;
  }

  /**
   * Returns a set of hooks specified in a configuration variable.
   * See getHooks(HiveConf.ConfVars hookConfVar, Class<T> clazz)
   */
  private List<Hook> getHooks(HiveConf.ConfVars hookConfVar) throws Exception {
    return getHooks(hookConfVar, Hook.class);
  }

  /**
   * Returns the hooks specified in a configuration variable.
   *
   * @param hookConfVar The configuration variable specifying a comma separated list of the hook
   *                    class names.
   * @param clazz       The super type of the hooks.
   * @return            A list of the hooks cast as the type specified in clazz, in the order
   *                    they are listed in the value of hookConfVar
   * @throws Exception
   */
  private <T extends Hook> List<T> getHooks(ConfVars hookConfVar,
      Class<T> clazz) throws Exception {
    try {
      return HookUtils.getHooks(conf, hookConfVar, clazz);
    } catch (ClassNotFoundException e) {
      console.printError(hookConfVar.varname + " Class not found:" + e.getMessage());
      throw e;
    }
  }

  public int execute(HiveConf conf , QueryPlan plan) throws CommandNeedRetryException {
    PerfLogger perfLogger = PerfLogger.getPerfLogger();
    perfLogger.PerfLogBegin(LOG, PerfLogger.DRIVER_EXECUTE);

    boolean noName = StringUtils.isEmpty(conf.getVar(HiveConf.ConfVars.HADOOPJOBNAME));
    int maxlen = conf.getIntVar(HiveConf.ConfVars.HIVEJOBNAMELENGTH);

    String queryId = plan.getQueryId();
    String queryStr = plan.getQueryStr();

    conf.setVar(HiveConf.ConfVars.HIVEQUERYID, queryId);
    conf.setVar(HiveConf.ConfVars.HIVEQUERYSTRING, queryStr);

    conf.set("mapreduce.workflow.id", "hive_"+queryId);
    conf.set("mapreduce.workflow.name", queryStr);

    maxthreads = HiveConf.getIntVar(conf, HiveConf.ConfVars.EXECPARALLETHREADNUMBER);

    try {
      LOG.info("Starting command: " + queryStr);

      plan.setStarted();

      if (SessionState.get() != null) {
        SessionState.get().getHiveHistory().startQuery(queryStr,
            conf.getVar(HiveConf.ConfVars.HIVEQUERYID));
        SessionState.get().getHiveHistory().logPlanProgress(plan);
      }
      resStream = null;

      HookContext hookContext = new HookContext(plan, conf, ctx.getPathToCS());
      hookContext.setHookType(HookContext.HookType.PRE_EXEC_HOOK);

      for (Hook peh : getHooks(HiveConf.ConfVars.PREEXECHOOKS)) {
        if (peh instanceof ExecuteWithHookContext) {
          perfLogger.PerfLogBegin(LOG, PerfLogger.PRE_HOOK + peh.getClass().getName());

          ((ExecuteWithHookContext) peh).run(hookContext);

          perfLogger.PerfLogEnd(LOG, PerfLogger.PRE_HOOK + peh.getClass().getName());
        } else if (peh instanceof PreExecute) {
          perfLogger.PerfLogBegin(LOG, PerfLogger.PRE_HOOK + peh.getClass().getName());

          ((PreExecute) peh).run(SessionState.get(), plan.getInputs(), plan.getOutputs(),
              ShimLoader.getHadoopShims().getUGIForConf(conf));

          perfLogger.PerfLogEnd(LOG, PerfLogger.PRE_HOOK + peh.getClass().getName());
        }
      }


      int jobs = Utilities.getMRTasks(plan.getRootTasks()).size();
      if (jobs > 0) {
        console.printInfo("Total MapReduce jobs = " + jobs);
      }
      if (SessionState.get() != null) {
        SessionState.get().getHiveHistory().setQueryProperty(queryId, Keys.QUERY_NUM_TASKS,
            String.valueOf(jobs));
        SessionState.get().getHiveHistory().setIdToTableMap(plan.getIdToTableNameMap());
      }
      String jobname = Utilities.abbreviate(queryStr, maxlen - 6);

      // A runtime that launches runnable tasks as separate Threads through
      // TaskRunners
      // As soon as a task isRunnable, it is put in a queue
      // At any time, at most maxthreads tasks can be running
      // The main thread polls the TaskRunners to check if they have finished.

      Queue<Task<? extends Serializable>> runnable = new ConcurrentLinkedQueue<Task<? extends Serializable>>();
      Map<TaskResult, TaskRunner> running = new HashMap<TaskResult, TaskRunner>();

      DriverContext driverCxt = new DriverContext(runnable, ctx);
      ctx.setHDFSCleanup(true);

      SessionState.get().setLastMapRedStatsList(new ArrayList<MapRedStats>());
      SessionState.get().setStackTraces(new HashMap<String, List<List<String>>>());
      SessionState.get().setLocalMapRedErrors(new HashMap<String, List<String>>());

      // Add root Tasks to runnable
      for (Task<? extends Serializable> tsk : plan.getRootTasks()) {
        // This should never happen, if it does, it's a bug with the potential to produce
        // incorrect results.
        assert tsk.getParentTasks() == null || tsk.getParentTasks().isEmpty();
        driverCxt.addToRunnable(tsk);
      }

      perfLogger.PerfLogEnd(LOG, PerfLogger.TIME_TO_SUBMIT);
      perfLogger.PerfLogBegin(LOG, PerfLogger.RUN_TASKS);
      // Loop while you either have tasks running, or tasks queued up
      while (running.size() != 0 || runnable.peek() != null) {
        // Launch upto maxthreads tasks
        while (runnable.peek() != null && running.size() < maxthreads) {
          Task<? extends Serializable> tsk = runnable.remove();
          perfLogger.PerfLogBegin(LOG, PerfLogger.TASK + tsk.getName() + "." + tsk.getId());
          launchTask(tsk, queryId, noName, running, jobname, jobs, driverCxt , plan);
        }

        // poll the Tasks to see which one completed
        TaskResult tskRes = pollTasks(running.keySet());
        TaskRunner tskRun = running.remove(tskRes);
        Task<? extends Serializable> tsk = tskRun.getTask();
        perfLogger.PerfLogEnd(LOG, PerfLogger.TASK + tsk.getName() + "." + tsk.getId());
        if(incCtx!=null)
         {
          incCtx.taskIdtoJobId.put(tsk.getId(), tsk.getJobID());//for gby split cost model
        }
        LOG.info("Set TaskId " + tsk.getId() + " 's jobId " + tsk.getJobID());
        hookContext.addCompleteTask(tskRun);

        int exitVal = tskRes.getExitVal();
        if (exitVal != 0) {
          if (tsk.ifRetryCmdWhenFail()) {
            if (!running.isEmpty()) {
              taskCleanup(running);
            }
            // in case we decided to run everything in local mode, restore the
            // the jobtracker setting to its initial value
            ctx.restoreOriginalTracker();
            throw new CommandNeedRetryException();
          }
          Task<? extends Serializable> backupTask = tsk.getAndInitBackupTask();
          if (backupTask != null) {
            setErrorMsgAndDetail(exitVal, tskRes.getTaskError(), tsk);
            console.printError(errorMessage);
            errorMessage = "ATTEMPT: Execute BackupTask: " + backupTask.getClass().getName();
            console.printError(errorMessage);

            // add backup task to runnable
            if (DriverContext.isLaunchable(backupTask)) {
              driverCxt.addToRunnable(backupTask);
            }
            continue;

          } else {
            hookContext.setHookType(HookContext.HookType.ON_FAILURE_HOOK);
            // Get all the failure execution hooks and execute them.
            for (Hook ofh : getHooks(HiveConf.ConfVars.ONFAILUREHOOKS)) {
              perfLogger.PerfLogBegin(LOG, PerfLogger.FAILURE_HOOK + ofh.getClass().getName());

              ((ExecuteWithHookContext) ofh).run(hookContext);

              perfLogger.PerfLogEnd(LOG, PerfLogger.FAILURE_HOOK + ofh.getClass().getName());
            }
            setErrorMsgAndDetail(exitVal, tskRes.getTaskError(), tsk);
            SQLState = "08S01";
            console.printError(errorMessage);
            if (!running.isEmpty()) {
              taskCleanup(running);
            }
            // in case we decided to run everything in local mode, restore the
            // the jobtracker setting to its initial value
            ctx.restoreOriginalTracker();
            return exitVal;
          }
        }

        if (SessionState.get() != null) {
          SessionState.get().getHiveHistory().setTaskProperty(queryId, tsk.getId(),
              Keys.TASK_RET_CODE, String.valueOf(exitVal));
          SessionState.get().getHiveHistory().endTask(queryId, tsk);
        }

        if (tsk.getChildTasks() != null) {
          for (Task<? extends Serializable> child : tsk.getChildTasks()) {
            if (DriverContext.isLaunchable(child)) {
              driverCxt.addToRunnable(child);
            }
          }
        }
      }
      perfLogger.PerfLogEnd(LOG, PerfLogger.RUN_TASKS);

      // in case we decided to run everything in local mode, restore the
      // the jobtracker setting to its initial value
      ctx.restoreOriginalTracker();

      // remove incomplete outputs.
      // Some incomplete outputs may be added at the beginning, for eg: for dynamic partitions.
      // remove them
      HashSet<WriteEntity> remOutputs = new HashSet<WriteEntity>();
      for (WriteEntity output : plan.getOutputs()) {
        if (!output.isComplete()) {
          remOutputs.add(output);
        }
      }

      for (WriteEntity output : remOutputs) {
        plan.getOutputs().remove(output);
      }

      hookContext.setHookType(HookContext.HookType.POST_EXEC_HOOK);
      // Get all the post execution hooks and execute them.
      for (Hook peh : getHooks(HiveConf.ConfVars.POSTEXECHOOKS)) {
        if (peh instanceof ExecuteWithHookContext) {
          perfLogger.PerfLogBegin(LOG, PerfLogger.POST_HOOK + peh.getClass().getName());

          ((ExecuteWithHookContext) peh).run(hookContext);

          perfLogger.PerfLogEnd(LOG, PerfLogger.POST_HOOK + peh.getClass().getName());
        } else if (peh instanceof PostExecute) {
          perfLogger.PerfLogBegin(LOG, PerfLogger.POST_HOOK + peh.getClass().getName());

          ((PostExecute) peh).run(SessionState.get(), plan.getInputs(), plan.getOutputs(),
              (SessionState.get() != null ? SessionState.get().getLineageState().getLineageInfo()
                  : null), ShimLoader.getHadoopShims().getUGIForConf(conf));

          perfLogger.PerfLogEnd(LOG, PerfLogger.POST_HOOK + peh.getClass().getName());
        }
      }


      if (SessionState.get() != null) {
        SessionState.get().getHiveHistory().setQueryProperty(queryId, Keys.QUERY_RET_CODE,
            String.valueOf(0));
        SessionState.get().getHiveHistory().printRowCount(queryId);
      }
    } catch (CommandNeedRetryException e) {
      throw e;
    } catch (Exception e) {
      ctx.restoreOriginalTracker();
      if (SessionState.get() != null) {
        SessionState.get().getHiveHistory().setQueryProperty(queryId, Keys.QUERY_RET_CODE,
            String.valueOf(12));
      }
      // TODO: do better with handling types of Exception here
      errorMessage = "FAILED: Hive Internal Error: " + Utilities.getNameMessage(e);
      SQLState = "08S01";
      downstreamError = e;
      console.printError(errorMessage + "\n"
          + org.apache.hadoop.util.StringUtils.stringifyException(e));
      return (12);
    } finally {
      if (SessionState.get() != null) {
        SessionState.get().getHiveHistory().endQuery(queryId);
      }
      if (noName) {
        conf.setVar(HiveConf.ConfVars.HADOOPJOBNAME, "");
      }
      perfLogger.PerfLogEnd(LOG, PerfLogger.DRIVER_EXECUTE);

      if (SessionState.get().getLastMapRedStatsList() != null
          && SessionState.get().getLastMapRedStatsList().size() > 0) {
        long totalCpu = 0;
        console.printInfo("MapReduce Jobs Launched: ");
        for (int i = 0; i < SessionState.get().getLastMapRedStatsList().size(); i++) {
          console.printInfo("Job " + i + ": " + SessionState.get().getLastMapRedStatsList().get(i));
          totalCpu += SessionState.get().getLastMapRedStatsList().get(i).getCpuMSec();
          if(incCtx!=null){// get info for gby cost model
            for(QB qb: incCtx.getToSplitQB()){
              ReduceSinkOperator rs = incCtx.getQBToGBYRS().get(qb);
              String taskId = rs.getTaskId();
              LOG.info("ToSplit TaskId is: " + taskId + "TaskId's jobId is: " + incCtx.taskIdtoJobId.get(taskId) + "; current JobId is " +
                  SessionState.get().getLastMapRedStatsList().get(i).getJobId());
              if(incCtx.taskIdtoJobId.get(taskId)!= null &&
  		incCtx.taskIdtoJobId.get(taskId).equals(SessionState.get().getLastMapRedStatsList().get(i).getJobId())){
                ArrayList<Long> inOutSize = new ArrayList<Long>();
                inOutSize.add((Long)SessionState.get().getLastMapRedStatsList().get(i).getHDFSRead());
                inOutSize.add((Long)SessionState.get().getLastMapRedStatsList().get(i).getHDFSWrite());
                LOG.info("Get in out size are : " + inOutSize.get(0) + " " + inOutSize.get(1));
                incCtx.inOutSize.put(qb, inOutSize);
              }
            }
          }
        }
        console.printInfo("Total MapReduce CPU Time Spent: " + Utilities.formatMsecToStr(totalCpu));
      }
    }
    plan.setDone();

    if (SessionState.get() != null) {
      try {
        SessionState.get().getHiveHistory().logPlanProgress(plan);
      } catch (Exception e) {
      }
    }
    console.printInfo("OK");

    return (0);
  }
  private void setErrorMsgAndDetail(int exitVal, Throwable downstreamError, Task tsk) {
    this.downstreamError = downstreamError;
    errorMessage = "FAILED: Execution Error, return code " + exitVal + " from " + tsk.getClass().getName();
    if(downstreamError != null) {
      //here we assume that upstream code may have parametrized the msg from ErrorMsg
      //so we want to keep it
      errorMessage += ". " + downstreamError.getMessage();
    }
    else {
      ErrorMsg em = ErrorMsg.getErrorMsg(exitVal);
      if (em != null) {
        errorMessage += ". " +  em.getMsg();
      }
    }
  }
  /**
   * Launches a new task
   *
   * @param tsk
   *          task being launched
   * @param queryId
   *          Id of the query containing the task
   * @param noName
   *          whether the task has a name set
   * @param running
   *          map from taskresults to taskrunners
   * @param jobname
   *          name of the task, if it is a map-reduce job
   * @param jobs
   *          number of map-reduce jobs
   * @param cxt
   *          the driver context
   */

  public void launchTask(Task<? extends Serializable> tsk, String queryId, boolean noName,
      Map<TaskResult, TaskRunner> running, String jobname, int jobs, DriverContext cxt , QueryPlan plan) {
    if (SessionState.get() != null) {
      SessionState.get().getHiveHistory().startTask(queryId, tsk, tsk.getClass().getName());
    }
    if (tsk.isMapRedTask() && !(tsk instanceof ConditionalTask)) {
      if (noName) {
        conf.setVar(HiveConf.ConfVars.HADOOPJOBNAME, jobname + "(" + tsk.getId() + ")");
      }
      conf.set("mapreduce.workflow.node.name", tsk.getId());
      Utilities.setWorkflowAdjacencies(conf, plan);
      cxt.incCurJobNo(1);
      console.printInfo("Launching Job " + cxt.getCurJobNo() + " out of " + jobs);
    }
    tsk.initialize(conf, plan, cxt);
    TaskResult tskRes = new TaskResult();
    TaskRunner tskRun = new TaskRunner(tsk, tskRes);

    // Launch Task
    if (HiveConf.getBoolVar(conf, HiveConf.ConfVars.EXECPARALLEL) && tsk.isMapRedTask()) {
      // Launch it in the parallel mode, as a separate thread only for MR tasks
      tskRun.start();
    } else {
      tskRun.runSequential();
    }
    running.put(tskRes, tskRun);
    return;
  }

  /**
   * Cleans up remaining tasks in case of failure
   */
  public void taskCleanup(Map<TaskResult, TaskRunner> running) {
    for (Map.Entry<TaskResult, TaskRunner> entry : running.entrySet()) {
      if (entry.getKey().isRunning()) {
        Task<?> task = entry.getValue().getTask();
        try {
          task.shutdown();
        } catch (Exception e) {
          console.printError("Exception on shutting down task " + task.getId() + ": " + e);
        }
      }
    }
    running.clear();
  }

  /**
   * Polls running tasks to see if a task has ended.
   *
   * @param results
   *          Set of result objects for running tasks
   * @return The result object for any completed/failed task
   */

  public TaskResult pollTasks(Set<TaskResult> results) {
    Iterator<TaskResult> resultIterator = results.iterator();
    while (true) {
      while (resultIterator.hasNext()) {
        TaskResult tskRes = resultIterator.next();
        if (!tskRes.isRunning()) {
          return tskRes;
        }
      }

      // In this loop, nothing was found
      // Sleep 10 seconds and restart
      try {
        Thread.sleep(SLEEP_TIME);
      } catch (InterruptedException ie) {
        // Do Nothing
        ;
      }
      resultIterator = results.iterator();
    }
  }

  public boolean getResults(QueryPlan plan , ArrayList<String> res) throws IOException, CommandNeedRetryException {
    if (plan != null && plan.getFetchTask() != null) {
      FetchTask ft = plan.getFetchTask();
      ft.setMaxRows(maxRows);
      ft.resetTotalRows();
      return ft.fetch(res);
    }

    if (resStream == null) {
      resStream = ctx.getStream();
    }
    if (resStream == null) {
      return false;
    }

    int numRows = 0;
    String row = null;

    while (numRows < maxRows) {
      if (resStream == null) {
        if (numRows > 0) {
          return true;
        } else {
          return false;
        }
      }

      bos.reset();
      Utilities.StreamStatus ss;
      try {
        ss = Utilities.readColumn(resStream, bos);
        if (bos.getCount() > 0) {
          row = new String(bos.getData(), 0, bos.getCount(), "UTF-8");
        } else if (ss == Utilities.StreamStatus.TERMINATED) {
          row = new String();
        }

        if (row != null) {
          numRows++;
          res.add(row);
        }
      } catch (IOException e) {
        console.printError("FAILED: Unexpected IO exception : " + e.getMessage());
        res = null;
        return false;
      }

      if (ss == Utilities.StreamStatus.EOF) {
        resStream = ctx.getStream();
      }
    }
    return true;
  }

  public int getTryCount() {
    return tryCount;
  }

  public void setTryCount(int tryCount) {
    this.tryCount = tryCount;
  }


  public int close( ) {
    try {
      ArrayList<QueryPlan> plans = new ArrayList<QueryPlan>();//tmpTablePlan , cacheTablePlan.toArray(),FinalPlan,OriginalPlan };
      plans.add(tmpTablePlan);
      plans.add(FinalPlan);
      plans.add(OriginalPlan);
      if(cacheTablePlan != null){
        for(QueryPlan plan:cacheTablePlan){
          plans.add(plan);
        }
      }

      for( int i = 0 ; i < plans.size() ; i++){
        QueryPlan plan = plans.get(i);
        if (plan != null) {
          FetchTask fetchTask = plan.getFetchTask();
          if (null != fetchTask) {
            try {
              fetchTask.clearFetch();
            } catch (Exception e) {
              LOG.debug(" Exception while clearing the Fetch task ", e);
            }
          }
        }
      }
      if (ctx != null) {
        ctx.clear();
      }
      if (null != resStream) {
        try {
          ((FSDataInputStream) resStream).close();
        } catch (Exception e) {
          LOG.debug(" Exception while closing the resStream ", e);
        }
      }
    } catch (Exception e) {
      console.printError("FAILED: Hive Internal Error: " + Utilities.getNameMessage(e) + "\n"
          + org.apache.hadoop.util.StringUtils.stringifyException(e));
      return 13;
    }

    return 0;
  }

  public void destroy() {
    if (ctx != null) {
      releaseLocks(ctx.getHiveLocks());
    }

    if (hiveLockMgr != null) {
      try {
        hiveLockMgr.close();
      } catch(LockException e) {
        LOG.warn("Exception in closing hive lock manager. "
            + org.apache.hadoop.util.StringUtils.stringifyException(e));
      }
    }
  }

  /**
   * to traverse task tree , to find map work of inc-table
   * @param tasks  root tasks
   * @param mapTasks mapwork
   */
  private  void incMapTasks(List<Task<? extends Serializable>> tasks, List<MapWork> mapTasks) {
    ArrayList<String> aliasSet = incCtx.parserRes.getIncTableAlias();
    for (Task<? extends Serializable> task : tasks) {
       Serializable work = task.getWork();
      if (work instanceof MapredWork && !mapTasks.contains(((MapredWork) work).getMapWork())) {
        MapWork mapTask = ((MapredWork) work).getMapWork();
        for(String alias_id:aliasSet){
          if(mapTask.getAliases().contains(alias_id)){
            mapTasks.add(mapTask);
          }
        }

      }
      if (task.getDependentTasks() != null) {
        incMapTasks(task.getDependentTasks(), mapTasks);
      }
    }
  }


  /**
   * According to start date and end date, retrieves incremental data files
   * @param tab : table Object of inc table
   * @return all paths of eligible data files
   */
  private ArrayList<Path> incFiles(Table tab,long curStart ){
    Calendar startDate = Calendar.getInstance();
    Calendar endDate = Calendar.getInstance();
    // stationary size window of time
    if(incCtx.parserRes.isConstantParam()){
      long constantMs = incCtx.parserRes.getConstantSec()*1000;
      startDate.setTimeInMillis(System.currentTimeMillis() - constantMs);
      endDate.setTimeInMillis(System.currentTimeMillis());
    }else if(incCtx.parserRes.isAfterParam()){
      // start time is immutable which from parameters of sql
      //end time is real-time from system time currently
      startDate.setTime(incCtx.parserRes.getStartDate());
      endDate.setTimeInMillis(System.currentTimeMillis());
    }else{
      startDate.setTime(incCtx.parserRes.getStartDate());
      endDate.setTime(incCtx.parserRes.getStopDate());
    }
    startDate.setTime(new Date(curStart));//kangyanli modifted for test

    return tab.getIncFilePaths( startDate , endDate);
  }

  private void changeInputPath(long curStart,QueryPlan curPlan ){
    ArrayList<Task<? extends Serializable>> tasks = curPlan.getRootTasks();
    List<MapWork> mapTasks = new  ArrayList<MapWork>();
    incMapTasks( tasks , mapTasks);
    ArrayList<String> scanNewFileAlias = incCtx.parserRes.getScanNewFileIncAliases();
    int i=0;
    for(MapWork mt:  mapTasks){
      LOG.info("Change input path for " + i++ + "th map task");
       LinkedHashMap<String, ArrayList<String>> pathToAlias = mt.getPathToAliases();
       LinkedHashMap<String, PartitionDesc> pathToPartitionInfo = mt.getPathToPartitionInfo();
       for(String path: pathToAlias.keySet()){
         LOG.info("Before change, input path : " +path + " alias :: "+ pathToAlias.get(path));
       }

       Iterator<Map.Entry<String,ArrayList<String>>> it = pathToAlias.entrySet().iterator();
       ArrayList<String> removedAlias = new ArrayList<String>();
       while(it.hasNext()){
         Map.Entry<String,ArrayList<String>> cur = it.next();
         String path = cur.getKey();
         ArrayList<String> aliases = cur.getValue();
         Iterator<String> itlist = aliases.iterator();
         while(itlist.hasNext()){
           String curAlias = itlist.next();
           if(scanNewFileAlias.contains(curAlias)){
             removedAlias.add(curAlias);
             itlist.remove();
           }
         }
         if(aliases.isEmpty()){
           it.remove();
           pathToPartitionInfo.remove(path);
         }
       }
       for(String incAlias:removedAlias){
         Table tab = incCtx.getParserRes().getTableForAlias(incAlias);
         ArrayList<Path> newPaths = incFiles(tab,curStart);
         for( Path newPath : newPaths){
           String newPathName = newPath.toString();
           if( ! pathToAlias.containsKey(newPathName)){
             pathToAlias.put(newPathName, new ArrayList<String>());
           }
           pathToAlias.get(newPathName).add(incAlias);
           pathToPartitionInfo.put(newPathName, mt.getAliasToPartnInfo().get(incAlias));
         }
       }

       for(String path: pathToAlias.keySet()){
         LOG.info("After change, input path : " +path + " alias :: "+ pathToAlias.get(path));
       }
//       for( String curAlias : scanNewFileAlias){
//         LOG.info("Begin find new added file for alias " + curAlias + " after time " + curStart);
//         Table tab = incCtx.getParserRes().getTableForAlias(curAlias);
//         LOG.info("Begin Find new added file in table " + tab.getTableName() + " after time " + curStart);
//         ArrayList<Path> paths = incFiles(tab,curStart);
//         if(paths.isEmpty()){
//           LOG.info("Do not find incremental data file in HDFS!");
//         }
//
//         ArrayList<String> newPaths  = new ArrayList<String>();
//         for(Path path : paths ){
//           newPaths.add(path.toString());
//         }
//
//         ArrayList<String> deletePath  = new ArrayList<String>();
//         for(String path: pathToAlias.keySet()){
//           ArrayList<String> pathAlias = pathToAlias.get(path);
//           if(pathAlias.contains(curAlias)){
//             pathAlias.remove(curAlias);
//           }
//           if(pathAlias.isEmpty()){
//             deletePath.add(path);
//           }
//         }
//
//         for( String delPath : deletePath){
//           pathToAlias.remove( delPath);
//         }
//         //add new path
//         for( String path : newPaths){
//           LOG.info("Added input path : " +path);
//           if( ! pathToAlias.containsKey(path)){
//             pathToAlias.put(path, new ArrayList<String>());
//           }
//           pathToAlias.get(path).add(curAlias);
//           mt.getPathToPartitionInfo().put(path, mt.getAliasToPartnInfo().get(curAlias));
//         }
//
//         for(String path: pathToAlias.keySet()){
//           LOG.info("After change, input path : " +path + " alias :: "+ pathToAlias.get(path));
//         }
//
//       }
    }
  }


  public int  execute( String command) throws CommandNeedRetryException{
    int ret = 0;
//    start = System.currentTimeMillis();
//    if(tmpTablePlan != null){
//      ret = execute(conf , tmpTablePlan);
//
//    }

    if(!isINC ){
      return runOriginalPlan();
    }

    long period = 1000;
    if( incCtx.parserRes.getIncTableAlias().size() != 0){
      period = incCtx.parserRes.getIntervalSec()*1000;
    }
    incList.add("10G");
    incList.add("15G");
    incList.add("20G");
    incList.add("25G");
    incList.add("10G");
    incList.add("30G");
    incList.add("20G");
    incList.add("20G");
    incList.add("30G");
    timer.schedule(new IncrementalExec(command),
      0,  //initial delay
      period); //subsequent rate
    while( true ){
      if( !run ){
        return ret;
      }
    }

  }

  private int runOriginalPlan() throws CommandNeedRetryException{

    start = System.currentTimeMillis();
    int ret = execute(conf ,  OriginalPlan);
    try {
      int resultcounter = outputRes(OriginalPlan) ;
      long end = System.currentTimeMillis();
      double timeTaken = (end  - start) / 1000.0;
      console.printInfo("Time taken: " + timeTaken + " seconds" +
          (resultcounter == 0 ? "" : ", Fetched: " + resultcounter + " row(s)"));
    } catch (IOException e) {
      console.printError("Failed with exception " + e.getClass().getName() + ":"
          + e.getMessage(), "\n"
          + org.apache.hadoop.util.StringUtils.stringifyException(e));
    }
    return ret;

  }

  private int  outputRes(QueryPlan queryplan) throws IOException, CommandNeedRetryException{

    SessionState ss = SessionState.get();
    PrintStream out = ss.out;
    ArrayList<String> res = new ArrayList<String>();
    int counter = 0;
    System.out.println("Output the result of incremental query :");
    while (getResults(queryplan ,  res)) {
      for (String r : res) {
        out.println(r);
      }
      counter += res.size();
      res.clear();
      if (out.checkError()) {
        break;
      }
    }

    return counter;
  }

  private class IncrementalExec extends TimerTask {
    private final IncDriver incDrv;
    private final String command;

    public IncrementalExec(String command  ){
      this.command = command;
      incDrv = CommandProcessorFactory.getIncDriver(conf);
    }
    @Override
    public void run() {
      try {
        SessionState.start(ss);

        if(counter.get() >= 1 && HiveConf.getBoolVar(conf, HiveConf.ConfVars.HIVEINCPUTNEWFILES)){
          for(String tabName: incCtx.getParserRes().getIncTabNames()){

            try {
              String sinkFile = HiveConf.getVar(conf, ConfVars.HIVEINCFILESINKDIR)+tabName + "/"+tabName + "Inc"+counter.get()+".tbl";
              String sourceFile = HiveConf.getVar(conf, ConfVars.HIVEINCFILESOURCEDIR)+incList.get(counter.get()-1) + "/"+ tabName +".tbl";
              toRemovePath.add(sinkFile);
              String putTableCommand = "hdfs dfs -put " + sourceFile + " " + sinkFile;
              console.printInfo(putTableCommand);
              Process process = Runtime.getRuntime().exec(putTableCommand);
              int exitValue = process.waitFor();
              if (0 != exitValue) {
                LOG.error("call shell failed. error code is :" + exitValue);
              }
            } catch (Throwable e) {
              LOG.error("call shell failed. " + e);
            }
          }

        }


          LOG.info("=====Begin "+ counter.get() + " incremental run"+ "=====");

          PerfLogger perfLogger = PerfLogger.getPerfLogger();
          perfLogger.PerfLogBegin(LOG, PerfLogger.ANALYZE);
          if(HiveConf.getBoolVar(conf, HiveConf.ConfVars.HIVEINCRUNORIGINAL)){
            LOG.info("=====Begin test origianl plan======");
            genOriginalQueryPlan(originalTree,command,perfLogger);
            runOriginalPlan();
          }
          if(HiveConf.getBoolVar(conf, HiveConf.ConfVars.HIVEINCRUNINC)){
            /*begin cost model for gby split*/
            LOG.info("ToSplitQB's size is " + incCtx.toSplitQB.size() + "; They are:");
            ArrayList<QB> toRemoveQB = new ArrayList<QB>( );
            for(QB qb:incCtx.getToSplitQB()){//for every qb in splitlist, test cost model
              LOG.info(qb.getId());
              if(incCtx.getQBToGBYRS().get(qb) != null){// after first run, get gbyRS, taskId, jobId, inoutSize for every toSplitQB
                LOG.info("GBYRS is " + incCtx.getQBToGBYRS().get(qb).toString()
                    + "; GBYTask is :" + incCtx.getQBToGBYRS().get(qb).getTaskId()
                    + "; JobId is: "  + incCtx.taskIdtoJobId.get(incCtx.getQBToGBYRS().get(qb).getTaskId()));
                ArrayList<Long> incOldSize = new ArrayList<Long>( );
                incOldSize.add(new Long(0)); incOldSize.add(new Long(0));
                getIncOldSize(incCtx.getParserRes().getIncTabNames(),incOldSize);
                if(gbycostModel(incCtx.inOutSize.get(qb).get(0),incCtx.inOutSize.get(qb).get(1),incOldSize.get(0),incOldSize.get(1))){//HiveConf.getBoolVar(conf, HiveConf.ConfVars.HIVEINCTESTCOSTMODELSPLIT)
                  LOG.info("Remove QB " + qb.getId() + " from toSplitQBs; Replace mergePos in finalQB to mergeQB; " +
                  		"Add splitQB to gbyCacheQBList;");
                  console.printInfo("Cost Model return true; Begin split GBY in QB " + qb.getId() + "Last inputSize is " + incCtx.inOutSize.get(qb).get(0));
                  toRemoveQB.add(qb) ;
                  SimpleQB sqb = incCtx.getQBToSimpleQB().get(qb);
                  if(sqb.getMergePosInFinalQB() != null) {
                    sqb.getMergePosInFinalQB().setQB(sqb.getMergeQB());
                  } else {
                    finalQB = sqb.getMergeQB();
                  }
                  gbyCacheQBlist.add(sqb.getSplitQB());
                  sqb.getSplitQB().setIsToDelete(true);//delete cache dir until after first time
                }
              }
            }
            for(QB qb: toRemoveQB){
              incCtx.getToSplitQB().remove(qb);// remove qb from splitlist
            }
            /*end cost model for gby split*/

            ArrayList<String> toRemoveScanAllAlias = new ArrayList<String>();
            cacheTablePlan = new ArrayList<QueryPlan>();

            for(int i=0;i<gbyCacheQBlist.size();i++){
              QB curCacheQB = gbyCacheQBlist.get(i);
              LOG.info("=====Begin generate " + i + " cacheTablePlan=====");
              cacheTablePlan.add(genQueryPlan(null,command,curCacheQB,perfLogger));

              if(curCacheQB.getIsToDelete()){
                curCacheQB.setIsToDelete(false);
                LOG.info("set curCacheQB " + curCacheQB.getId() + "'s incInputDirName null" );
                curCacheQB.getParseInfo().setStoreIncInputDirName(null);
                if(incCtx.getSplitQBToScanAllAliases().get(curCacheQB)!=null){
                  for(String alias: incCtx.getSplitQBToScanAllAliases().get(curCacheQB)){
                    toRemoveScanAllAlias.add(alias); //delete until this round end
                  }
                }
              }
            }

            LOG.info("=====Begin generate FinalPlan=====");
            FinalPlan = genQueryPlan(null,command,finalQB,perfLogger);
            toRemovePath.addAll(ctx.getIncInputCacheDir());

            // initialize FetchTask right here
            for(int i=0;i<cacheTablePlan.size();i++){
              QueryPlan curCachePlan = cacheTablePlan.get(i);
              if (curCachePlan.getFetchTask() != null) {
                curCachePlan.getFetchTask().initialize(conf, curCachePlan, null);
              }
            }

            if (FinalPlan.getFetchTask() != null) {
              FinalPlan.getFetchTask().initialize(conf, FinalPlan, null);
            }

            LOG.info("Incremental Aliases are: " + incCtx.getParserRes().getIncTableAlias().toString());
            LOG.info("Scan All incremental Aliases are: " + incCtx.getParserRes().getScanAllIncAliases().toString());
            LOG.info("Scan new file incremental Aliases are: " + incCtx.getParserRes().getScanNewFileIncAliases().toString());
            if(counter.get() >= 1) {
              start = System.currentTimeMillis();
            }else{
              start = System.currentTimeMillis();
              if(tmpTablePlan != null){
                execute(conf , tmpTablePlan);
              }
            }
            for(int i=0;i<cacheTablePlan.size();i++){
              QueryPlan curCachePlan = cacheTablePlan.get(i);
              LOG.info("==========Begin execute " + i + " cacheTablePlan==========");
              if(HiveConf.getBoolVar(conf, HiveConf.ConfVars.HIVEINCFINDNEWFILES)){
                changeInputPath(lastEnd,curCachePlan);
              }
              incDrv.execute(conf,curCachePlan);
            }
            LOG.info("==========Begin execute FinalPlan==========");
            if(HiveConf.getBoolVar(conf, HiveConf.ConfVars.HIVEINCFINDNEWFILES)){
              changeInputPath(lastEnd,FinalPlan);
            }
            incDrv.execute(conf,FinalPlan);

            int resultcounter = outputRes(FinalPlan) ;
            long end = System.currentTimeMillis();
            double timeTaken = (end  - start) / 1000.0;
            lastEnd = end;
            if(timeTaken > incCtx.parserRes.getIntervalSec()){
              console.printInfo("The invertal parameter less than execution time!");
            }
            console.printInfo("Time taken: " + timeTaken + " seconds" +
                (resultcounter == 0 ? "" : ", Fetched: " + resultcounter + " row(s)"));

            for(String alias:toRemoveScanAllAlias){
              LOG.info("Remove " + alias   + " from scanAllAliases;" );
              incCtx.getParserRes().getScanAllIncAliases().remove(alias);
            }
          }


      }catch ( Exception e) {
        console.printError("Failed with exception " + e.getClass().getName() + ":"
            + e.getMessage(), "\n"
            + org.apache.hadoop.util.StringUtils.stringifyException(e));
      }

      counter.incrementAndGet();
      // if open debug mode , run just once. or default execution 3 times
      int incTimes = conf.getIntVar(HiveConf.ConfVars.HIVEINCTIMES);
      if( LOG.isDebugEnabled() || counter.get() >= incTimes ){

        LOG.info("toRemovedPaths are: " + toRemovePath.toString());
        for(String path:toRemovePath){
          try {
            String deleteTableCommand = "hdfs dfs -rm -r "+path;
            Process process = Runtime.getRuntime().exec(deleteTableCommand);
            LOG.info(deleteTableCommand);
            int exitValue = process.waitFor();
            if (0 != exitValue) {
                LOG.error("call shell failed. error code is :" + exitValue);
            }
          } catch (Throwable e) {
             LOG.error("call shell failed. " + e);
          }

        }
        run = false;
        lastEnd = 0;
        timer.cancel();


      }
    }

    private boolean gbycostModel(Long gbyinSize, Long gbyoutSize, Long incSize, Long oldSize) {
      double gbyinSizeD = (double)gbyinSize.longValue(),gbyoutSizeD = (double) gbyoutSize.longValue(),incSizeD = (double)incSize.longValue(),oldSizeD=(double)oldSize.longValue();
      LOG.info("GBY inSize outsize is "+ gbyinSizeD + " " + gbyoutSizeD + "; incTable's incSize oldSize is " + incSizeD + " " + oldSizeD);
      double incToOld = incSizeD/oldSizeD;
      double splitSize = (double)conf.getLongVar(HiveConf.ConfVars.MAPREDMAXSPLITSIZE);//64MB,134217728(128MB), 536870912(512MB) mapred.min.split.size=?  mapred.max.split.size=?
      double maxDataSize = splitSize*4*2;
      LOG.info("alpha is " + incToOld + "; MaxDataSize is " + maxDataSize);
      double ans = Math.ceil((1+incToOld)*gbyinSizeD/maxDataSize) - Math.ceil(incToOld*gbyinSizeD/maxDataSize)-Math.ceil(2*gbyoutSizeD/maxDataSize);
      LOG.info("Tree part is " + Math.ceil((1+incToOld)*gbyinSizeD/maxDataSize) + " " + Math.ceil(incToOld*gbyinSizeD/maxDataSize) + " " + Math.ceil(2*gbyoutSizeD/maxDataSize));
      LOG.info("Get cost model ans is " + ans);
      return ans > 0;
    }

    private void getIncOldSize(HashSet<String> incTabNames, ArrayList<Long> incOldSize) throws HiveException {
      for(String tabName:incTabNames){
        LOG.info("find incSize and oldSize for table " + tabName);
        Table table = semcache.getDb().getTable(tabName);
        table.get(lastEnd,incOldSize);
        LOG.info("incSize is " + incOldSize.get(0).longValue() + "; oldSize is " + incOldSize.get(1).longValue() );
      }
    }
  }
}
