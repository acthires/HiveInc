/**
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.hadoop.hive.ql.parse;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.ContentSummary;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.Warehouse;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.ql.Context;
import org.apache.hadoop.hive.ql.ErrorMsg;
import org.apache.hadoop.hive.ql.exec.ColumnStatsTask;
import org.apache.hadoop.hive.ql.exec.ConditionalTask;
import org.apache.hadoop.hive.ql.exec.FetchTask;
import org.apache.hadoop.hive.ql.exec.FileSinkOperator;
import org.apache.hadoop.hive.ql.exec.MapJoinOperator;
import org.apache.hadoop.hive.ql.exec.MoveTask;
import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.exec.ReduceSinkOperator;
import org.apache.hadoop.hive.ql.exec.StatsTask;
import org.apache.hadoop.hive.ql.exec.TableScanOperator;
import org.apache.hadoop.hive.ql.exec.Task;
import org.apache.hadoop.hive.ql.exec.TaskFactory;
import org.apache.hadoop.hive.ql.exec.UnionOperator;
import org.apache.hadoop.hive.ql.exec.Utilities;
import org.apache.hadoop.hive.ql.exec.mr.ExecDriver;
import org.apache.hadoop.hive.ql.exec.mr.MapRedTask;
import org.apache.hadoop.hive.ql.hooks.ReadEntity;
import org.apache.hadoop.hive.ql.hooks.WriteEntity;
import org.apache.hadoop.hive.ql.lib.DefaultRuleDispatcher;
import org.apache.hadoop.hive.ql.lib.Dispatcher;
import org.apache.hadoop.hive.ql.lib.GraphWalker;
import org.apache.hadoop.hive.ql.lib.Node;
import org.apache.hadoop.hive.ql.lib.NodeProcessor;
import org.apache.hadoop.hive.ql.lib.Rule;
import org.apache.hadoop.hive.ql.lib.RuleRegExp;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.optimizer.GenMRFileSink1;
import org.apache.hadoop.hive.ql.optimizer.GenMROperator;
import org.apache.hadoop.hive.ql.optimizer.GenMRProcContext;
import org.apache.hadoop.hive.ql.optimizer.GenMRProcContext.GenMapRedCtx;
import org.apache.hadoop.hive.ql.optimizer.GenMRRedSink1;
import org.apache.hadoop.hive.ql.optimizer.GenMRRedSink2;
import org.apache.hadoop.hive.ql.optimizer.GenMRRedSink3;
import org.apache.hadoop.hive.ql.optimizer.GenMRTableScan1;
import org.apache.hadoop.hive.ql.optimizer.GenMRUnion1;
import org.apache.hadoop.hive.ql.optimizer.GenMapRedUtils;
import org.apache.hadoop.hive.ql.optimizer.MapJoinFactory;
import org.apache.hadoop.hive.ql.optimizer.physical.PhysicalContext;
import org.apache.hadoop.hive.ql.optimizer.physical.PhysicalOptimizer;
import org.apache.hadoop.hive.ql.plan.ColumnStatsDesc;
import org.apache.hadoop.hive.ql.plan.ColumnStatsWork;
import org.apache.hadoop.hive.ql.plan.CopyFileDesc;
import org.apache.hadoop.hive.ql.plan.CreateTableDesc;
import org.apache.hadoop.hive.ql.plan.DDLWork;
import org.apache.hadoop.hive.ql.plan.FetchWork;
import org.apache.hadoop.hive.ql.plan.LoadFileDesc;
import org.apache.hadoop.hive.ql.plan.LoadTableDesc;
import org.apache.hadoop.hive.ql.plan.MapWork;
import org.apache.hadoop.hive.ql.plan.MapredWork;
import org.apache.hadoop.hive.ql.plan.MoveWork;
import org.apache.hadoop.hive.ql.plan.OperatorDesc;
import org.apache.hadoop.hive.ql.plan.PlanUtils;
import org.apache.hadoop.hive.ql.plan.TableDesc;
import org.apache.hadoop.hive.ql.session.SessionState.LogHelper;
import org.apache.hadoop.hive.shims.ShimLoader;

public class MapReduceCompiler {

  protected final static Log LOG = LogFactory.getLog(MapReduceCompiler.class);
  private Hive db;
  protected LogHelper console;
  private static HiveConf conf;


  public MapReduceCompiler() {
  }

  public void init(HiveConf conf, LogHelper console, Hive db) {
    this.conf = conf;
    this.db = db;
    this.console = console;
  }

  @SuppressWarnings({"nls", "unchecked"})
  public void compile(final ParseContext pCtx, final List<Task<? extends Serializable>> rootTasks,
      final HashSet<ReadEntity> inputs, final HashSet<WriteEntity> outputs) throws SemanticException {

    Context ctx = pCtx.getContext();
    GlobalLimitCtx globalLimitCtx = pCtx.getGlobalLimitCtx();
    QB qb = pCtx.getQB();
    List<Task<MoveWork>> mvTask = new ArrayList<Task<MoveWork>>();

    List<LoadTableDesc> loadTableWork = pCtx.getLoadTableWork();
    List<LoadFileDesc> loadFileWork = pCtx.getLoadFileWork();

    boolean isCStats = qb.isAnalyzeRewrite();

    if (pCtx.getFetchTask() != null) {
      return;
    }

    /*
     * In case of a select, use a fetch task instead of a move task.
     * If the select is from analyze table column rewrite, don't create a fetch task. Instead create
     * a column stats task later.
     */
    if (pCtx.getQB().getIsQuery() && !isCStats) {
      if ((!loadTableWork.isEmpty()) || (loadFileWork.size() != 1)) {
        throw new SemanticException(ErrorMsg.GENERIC_ERROR.getMsg());
      }

      LoadFileDesc loadFileDesc = loadFileWork.get(0);

      String cols = loadFileDesc.getColumns();
      String colTypes = loadFileDesc.getColumnTypes();

      TableDesc resultTab = pCtx.getFetchTabledesc();
      if (resultTab == null) {
        String resFileFormat = HiveConf.getVar(conf, HiveConf.ConfVars.HIVEQUERYRESULTFILEFORMAT);
        resultTab = PlanUtils.getDefaultQueryOutputTableDesc(cols, colTypes, resFileFormat);
      }

      FetchWork fetch = new FetchWork(new Path(loadFileDesc.getSourceDir()).toString(),
          resultTab, qb.getParseInfo().getOuterQueryLimit());
      fetch.setSource(pCtx.getFetchSource());
      fetch.setSink(pCtx.getFetchSink());

      pCtx.setFetchTask((FetchTask) TaskFactory.get(fetch, conf));

      // For the FetchTask, the limit optimization requires we fetch all the rows
      // in memory and count how many rows we get. It's not practical if the
      // limit factor is too big
      int fetchLimit = HiveConf.getIntVar(conf, HiveConf.ConfVars.HIVELIMITOPTMAXFETCH);
      if (globalLimitCtx.isEnable() && globalLimitCtx.getGlobalLimit() > fetchLimit) {
        LOG.info("For FetchTask, LIMIT " + globalLimitCtx.getGlobalLimit() + " > " + fetchLimit
            + ". Doesn't qualify limit optimiztion.");
        globalLimitCtx.disableOpt();
      }
    } else if (!isCStats) {
      for (LoadTableDesc ltd : loadTableWork) {
        Task<MoveWork> tsk = TaskFactory.get(new MoveWork(null, null, ltd, null, false), conf);
        mvTask.add(tsk);
        // Check to see if we are stale'ing any indexes and auto-update them if we want
        if (HiveConf.getBoolVar(conf, HiveConf.ConfVars.HIVEINDEXAUTOUPDATE)) {
          IndexUpdater indexUpdater = new IndexUpdater(loadTableWork, inputs, conf);
          try {
            List<Task<? extends Serializable>> indexUpdateTasks = indexUpdater
                .generateUpdateTasks();
            for (Task<? extends Serializable> updateTask : indexUpdateTasks) {
              tsk.addDependentTask(updateTask);
            }
          } catch (HiveException e) {
            console
                .printInfo("WARNING: could not auto-update stale indexes, which are not in sync");
          }
        }
      }

      boolean oneLoadFile = true;
      for (LoadFileDesc lfd : loadFileWork) {
        if (qb.isCTAS()) {
          assert (oneLoadFile); // should not have more than 1 load file for
          // CTAS
          // make the movetask's destination directory the table's destination.
          String location = qb.getTableDesc().getLocation();
          if (location == null) {
            // get the table's default location
            Table dumpTable;
            Path targetPath;
            try {
              dumpTable = db.newTable(qb.getTableDesc().getTableName());
              if (!db.databaseExists(dumpTable.getDbName())) {
                throw new SemanticException("ERROR: The database " + dumpTable.getDbName()
                    + " does not exist.");
              }
              Warehouse wh = new Warehouse(conf);
              targetPath = wh.getTablePath(db.getDatabase(dumpTable.getDbName()), dumpTable
                  .getTableName());
            } catch (HiveException e) {
              throw new SemanticException(e);
            } catch (MetaException e) {
              throw new SemanticException(e);
            }

            location = targetPath.toString();
          }
          lfd.setTargetDir(location);

          oneLoadFile = false;
        }
        mvTask.add(TaskFactory.get(new MoveWork(null, null, null, lfd, false), conf));
      }
    }

    // generate map reduce plans
    ParseContext tempParseContext = getParseContext(pCtx, rootTasks);
    GenMRProcContext procCtx = new GenMRProcContext(
        conf,
        new HashMap<Operator<? extends OperatorDesc>, Task<? extends Serializable>>(),
        tempParseContext, mvTask, rootTasks,
        new LinkedHashMap<Operator<? extends OperatorDesc>, GenMapRedCtx>(),
        inputs, outputs);

    // create a walker which walks the tree in a DFS manner while maintaining
    // the operator stack.
    // The dispatcher generates the plan from the operator tree
    Map<Rule, NodeProcessor> opRules = new LinkedHashMap<Rule, NodeProcessor>();
    opRules.put(new RuleRegExp(new String("R1"),
        TableScanOperator.getOperatorName() + "%"),
        new GenMRTableScan1());
    opRules.put(new RuleRegExp(new String("R2"),
        TableScanOperator.getOperatorName() + "%.*" + ReduceSinkOperator.getOperatorName() + "%"),
        new GenMRRedSink1());
    opRules.put(new RuleRegExp(new String("R3"),
        ReduceSinkOperator.getOperatorName() + "%.*" + ReduceSinkOperator.getOperatorName() + "%"),
        new GenMRRedSink2());
    opRules.put(new RuleRegExp(new String("R4"),
        FileSinkOperator.getOperatorName() + "%"),
        new GenMRFileSink1());
    opRules.put(new RuleRegExp(new String("R5"),
        UnionOperator.getOperatorName() + "%"),
        new GenMRUnion1());
    opRules.put(new RuleRegExp(new String("R6"),
        UnionOperator.getOperatorName() + "%.*" + ReduceSinkOperator.getOperatorName() + "%"),
        new GenMRRedSink3());
    opRules.put(new RuleRegExp(new String("R7"),
        MapJoinOperator.getOperatorName() + "%"),
        MapJoinFactory.getTableScanMapJoin());

    // The dispatcher fires the processor corresponding to the closest matching
    // rule and passes the context along
    Dispatcher disp = new DefaultRuleDispatcher(new GenMROperator(), opRules,
        procCtx);

    GraphWalker ogw = new GenMapRedWalker(disp);
    ArrayList<Node> topNodes = new ArrayList<Node>();
    topNodes.addAll(pCtx.getTopOps().values());
    ogw.startWalking(topNodes, null);

    /*
     * If the query was the result of analyze table column compute statistics rewrite, create
     * a column stats task instead of a fetch task to persist stats to the metastore.
     */
    if (isCStats) {
      genColumnStatsTask(qb, loadTableWork, loadFileWork, rootTasks);
    }

    // reduce sink does not have any kids - since the plan by now has been
    // broken up into multiple
    // tasks, iterate over all tasks.
    // For each task, go over all operators recursively
    for (Task<? extends Serializable> rootTask : rootTasks) {
      breakTaskTree(rootTask);
    }

    // For each task, set the key descriptor for the reducer
    for (Task<? extends Serializable> rootTask : rootTasks) {
      GenMapRedUtils.setKeyAndValueDescForTaskTree(rootTask);
    }

    // If a task contains an operator which instructs bucketizedhiveinputformat
    // to be used, please do so
    for (Task<? extends Serializable> rootTask : rootTasks) {
      setInputFormat(rootTask);
    }



    PhysicalContext physicalContext = new PhysicalContext(conf,
        getParseContext(pCtx, rootTasks), ctx, rootTasks, pCtx.getFetchTask());
    PhysicalOptimizer physicalOptimizer = new PhysicalOptimizer(
        physicalContext, conf);
    physicalOptimizer.optimize();

    // For each operator, generate the counters if needed
    if (HiveConf.getBoolVar(conf, HiveConf.ConfVars.HIVEJOBPROGRESS)) {
      for (Task<? extends Serializable> rootTask : rootTasks) {
        generateCountersTask(rootTask);
      }
    }

    decideExecMode(rootTasks, ctx, globalLimitCtx);

    if (qb.isCTAS()) {
      // generate a DDL task and make it a dependent task of the leaf
      CreateTableDesc crtTblDesc = qb.getTableDesc();

      crtTblDesc.validate();

      // Clear the output for CTAS since we don't need the output from the
      // mapredWork, the
      // DDLWork at the tail of the chain will have the output
      outputs.clear();

      Task<? extends Serializable> crtTblTask = TaskFactory.get(new DDLWork(
          inputs, outputs, crtTblDesc), conf);

      // find all leaf tasks and make the DDLTask as a dependent task of all of
      // them
      HashSet<Task<? extends Serializable>> leaves = new HashSet<Task<? extends Serializable>>();
      getLeafTasks(rootTasks, leaves);
      assert (leaves.size() > 0);
      for (Task<? extends Serializable> task : leaves) {
        if (task instanceof StatsTask) {
          // StatsTask require table to already exist
          for (Task<? extends Serializable> parentOfStatsTask : task.getParentTasks()) {
            parentOfStatsTask.addDependentTask(crtTblTask);
          }
          for (Task<? extends Serializable> parentOfCrtTblTask : crtTblTask.getParentTasks()) {
            parentOfCrtTblTask.removeDependentTask(task);
          }
          crtTblTask.addDependentTask(task);
        } else {
          task.addDependentTask(crtTblTask);
        }
      }
    }

    if (globalLimitCtx.isEnable() && pCtx.getFetchTask() != null) {
      LOG.info("set least row check for FetchTask: " + globalLimitCtx.getGlobalLimit());
      pCtx.getFetchTask().getWork().setLeastNumRows(globalLimitCtx.getGlobalLimit());
    }

    if (globalLimitCtx.isEnable() && globalLimitCtx.getLastReduceLimitDesc() != null) {
      LOG.info("set least row check for LimitDesc: " + globalLimitCtx.getGlobalLimit());
      globalLimitCtx.getLastReduceLimitDesc().setLeastRows(globalLimitCtx.getGlobalLimit());
      List<ExecDriver> mrTasks = Utilities.getMRTasks(rootTasks);
      for (ExecDriver tsk : mrTasks) {
        tsk.setRetryCmdWhenFail(true);
      }
    }

    for(Map.Entry<Task<? extends Serializable>,String> entry:procCtx.getStoreTaskTODir().entrySet()){
      try {
        addMoveTask(pCtx,entry.getKey(),entry.getValue());
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

  }

  public static void addMoveTask(ParseContext parseCtx, Task<? extends Serializable> tsk,String dirName) throws IOException {
    // generate the temporary file
    // it must be on the same file system as the current destination

    Context baseCtx = parseCtx.getContext();
    LOG.info("Add moveTask after "+ tsk.getId() + " for storing result of it.");
    /*java.net.URI uri = (new Path("inc_"+ tsk.getId())).toUri();
    System.out.println(uri.getScheme()+'*'+uri.getAuthority());
    String targetDir = baseCtx.getExternalTmpFileURI(uri);*/
    String targetDir = baseCtx.getIncPath(dirName);
    String sourceDir = null;
    if(tsk instanceof MapRedTask){
      MapRedTask cur = (MapRedTask)tsk;
      sourceDir = Utilities.getDir(cur.getWork().getReduceWork());
    }
    LOG.info("   sourceDir is " + sourceDir);
    LOG.info("   targetDir is " + targetDir);
    MoveWork mvWork = new MoveWork(null, null, null,
        null, false);
    mvWork.setCopyFileWork(new CopyFileDesc(sourceDir,targetDir,true));
    Task<? extends Serializable> mvTask = TaskFactory.get(mvWork,conf);

    List<Task<? extends Serializable>> parentTasks = new ArrayList<Task<? extends Serializable>>();
    parentTasks.add(tsk);
    mvTask.setParentTasks(parentTasks);

    if(tsk.getChildTasks()!=null){
      mvTask.setChildTasks(tsk.getChildTasks());
      List<Task<? extends Serializable>> queue = new ArrayList<Task<? extends Serializable>>();
      for(Task<? extends Serializable> child:tsk.getChildTasks()){
        //change the ts-op of child's sourceDir to targetDir, look for GenMapRedUtils.splitTasks, setTaskPlan
        child.getParentTasks().set(child.getParentTasks().indexOf(tsk),mvTask);
        if(child instanceof MapRedTask){
          queue.add(child);
        }else if(child instanceof ConditionalTask){
          for(Task<? extends Serializable> cur: ((ConditionalTask) child).getListTasks()){
            if(cur instanceof MapRedTask){
              queue.add(cur);
            }
          }
        }
        //change the child task's source path
        for(Task<? extends Serializable> cur:queue){
          MapredWork cplan = (MapredWork) cur.getWork();
          MapWork mplan = cplan.getMapWork();
          List<String> paths = mplan.getPaths();
          if (paths.contains(sourceDir)) {
            mplan.getPathToAliases().put(targetDir, mplan.getPathToAliases().get(sourceDir));
            mplan.getPathToPartitionInfo().put(targetDir, mplan.getPathToPartitionInfo().get(sourceDir));
            mplan.getPathToAliases().remove(sourceDir);
            mplan.getPathToPartitionInfo().remove(sourceDir);
          }
        }
      }
    }

    List<Task<? extends Serializable>> childs= new ArrayList<Task<? extends Serializable>>();
    childs.add(mvTask);
    tsk.setChildTasks(childs);
  }

  private void setInputFormat(MapWork work, Operator<? extends OperatorDesc> op) {
    if (op.isUseBucketizedHiveInputFormat()) {
      work.setUseBucketizedHiveInputFormat(true);
      return;
    }

    if (op.getChildOperators() != null) {
      for (Operator<? extends OperatorDesc> childOp : op.getChildOperators()) {
        setInputFormat(work, childOp);
      }
    }
  }

  // loop over all the tasks recursively
  private void setInputFormat(Task<? extends Serializable> task) {
    if (task instanceof ExecDriver) {
      MapWork work = ((MapredWork) task.getWork()).getMapWork();
      HashMap<String, Operator<? extends OperatorDesc>> opMap = work.getAliasToWork();
      if (!opMap.isEmpty()) {
        for (Operator<? extends OperatorDesc> op : opMap.values()) {
          setInputFormat(work, op);
        }
      }
    } else if (task instanceof ConditionalTask) {
      List<Task<? extends Serializable>> listTasks
        = ((ConditionalTask) task).getListTasks();
      for (Task<? extends Serializable> tsk : listTasks) {
        setInputFormat(tsk);
      }
    }

    if (task.getChildTasks() != null) {
      for (Task<? extends Serializable> childTask : task.getChildTasks()) {
        setInputFormat(childTask);
      }
    }
  }

  // loop over all the tasks recursively
  private void generateCountersTask(Task<? extends Serializable> task) {
    if (task instanceof ExecDriver) {
      HashMap<String, Operator<? extends OperatorDesc>> opMap = ((MapredWork) task
          .getWork()).getMapWork().getAliasToWork();
      if (!opMap.isEmpty()) {
        for (Operator<? extends OperatorDesc> op : opMap.values()) {
          generateCountersOperator(op);
        }
      }

      if (((MapredWork)task.getWork()).getReduceWork() != null) {
        Operator<? extends OperatorDesc> reducer = ((MapredWork) task.getWork()).getReduceWork()
            .getReducer();
        LOG.info("Generating counters for operator " + reducer);
        generateCountersOperator(reducer);
      }
    } else if (task instanceof ConditionalTask) {
      List<Task<? extends Serializable>> listTasks = ((ConditionalTask) task)
          .getListTasks();
      for (Task<? extends Serializable> tsk : listTasks) {
        generateCountersTask(tsk);
      }
    }

    // Start the counters from scratch - a hack for hadoop 17.
    Operator.resetLastEnumUsed();

    if (task.getChildTasks() == null) {
      return;
    }

    for (Task<? extends Serializable> childTask : task.getChildTasks()) {
      generateCountersTask(childTask);
    }
  }

  private void generateCountersOperator(Operator<? extends OperatorDesc> op) {
    op.assignCounterNameToEnum();

    if (op.getChildOperators() == null) {
      return;
    }

    for (Operator<? extends OperatorDesc> child : op.getChildOperators()) {
      generateCountersOperator(child);
    }
  }

  public ParseContext getParseContext(ParseContext pCtx, List<Task<? extends Serializable>> rootTasks) {
    return new ParseContext(conf, pCtx.getQB(), pCtx.getParseTree(),
        pCtx.getOpToPartPruner(), pCtx.getOpToPartList(), pCtx.getTopOps(),
        pCtx.getTopSelOps(), pCtx.getOpParseCtx(), pCtx.getJoinContext(),
        pCtx.getSmbMapJoinContext(), pCtx.getTopToTable(), pCtx.getTopToProps(),
        pCtx.getFsopToTable(),
        pCtx.getLoadTableWork(), pCtx.getLoadFileWork(), pCtx.getContext(),
        pCtx.getIdToTableNameMap(), pCtx.getDestTableId(), pCtx.getUCtx(),
        pCtx.getListMapJoinOpsNoReducer(), pCtx.getGroupOpToInputTables(),
        pCtx.getPrunedPartitions(), pCtx.getOpToSamplePruner(), pCtx.getGlobalLimitCtx(),
        pCtx.getNameToSplitSample(), pCtx.getSemanticInputs(), rootTasks,
        pCtx.getOpToPartToSkewedPruner(), pCtx.getViewAliasToInput(),
        pCtx.getReduceSinkOperatorsAddedByEnforceBucketingSorting(),
        pCtx.getQueryProperties());
  }

  public MultiParseContext getParseContext(MultiParseContext pCtx, List<Task<? extends Serializable>> rootTasks,HashMap<Integer, ParseContext> multipctx) {
    return new MultiParseContext(conf, pCtx.getQB(), pCtx.getParseTree(),
        pCtx.getOpToPartPruner(), pCtx.getOpToPartList(), pCtx.getTopOps(),
        pCtx.getTopSelOps(), pCtx.getOpParseCtx(), pCtx.getJoinContext(),
        pCtx.getSmbMapJoinContext(), pCtx.getTopToTable(), pCtx.getTopToProps(),
        pCtx.getFsopToTable(),
        pCtx.getLoadTableWork(), pCtx.getLoadFileWork(), pCtx.getContext(),
        pCtx.getIdToTableNameMap(), pCtx.getDestTableId(), pCtx.getUCtx(),
        pCtx.getListMapJoinOpsNoReducer(), pCtx.getGroupOpToInputTables(),
        pCtx.getPrunedPartitions(), pCtx.getOpToSamplePruner(), pCtx.getGlobalLimitCtx(),
        pCtx.getNameToSplitSample(), pCtx.getSemanticInputs(), rootTasks,
        pCtx.getOpToPartToSkewedPruner(), pCtx.getViewAliasToInput(),
        pCtx.getReduceSinkOperatorsAddedByEnforceBucketingSorting(),
        pCtx.getQueryProperties(),multipctx);
  }

  // loop over all the tasks recursively
  private void breakTaskTree(Task<? extends Serializable> task) {

    if (task instanceof ExecDriver) {
      HashMap<String, Operator<? extends OperatorDesc>> opMap = ((MapredWork) task
          .getWork()).getMapWork().getAliasToWork();
      if (!opMap.isEmpty()) {
        for (Operator<? extends OperatorDesc> op : opMap.values()) {
          breakOperatorTree(op);
        }
      }
    } else if (task instanceof ConditionalTask) {
      List<Task<? extends Serializable>> listTasks = ((ConditionalTask) task)
          .getListTasks();
      for (Task<? extends Serializable> tsk : listTasks) {
        breakTaskTree(tsk);
      }
    }

    if (task.getChildTasks() == null) {
      return;
    }

    for (Task<? extends Serializable> childTask : task.getChildTasks()) {
      breakTaskTree(childTask);
    }
  }

  // loop over all the operators recursively
  private void breakOperatorTree(Operator<? extends OperatorDesc> topOp) {
    if (topOp instanceof ReduceSinkOperator) {
      topOp.setChildOperators(null);
    }

    if (topOp.getChildOperators() == null) {
      return;
    }

    for (Operator<? extends OperatorDesc> op : topOp.getChildOperators()) {
      breakOperatorTree(op);
    }
  }

  /**
   * A helper function to generate a column stats task on top of map-red task. The column stats
   * task fetches from the output of the map-red task, constructs the column stats object and
   * persists it to the metastore.
   *
   * This method generates a plan with a column stats task on top of map-red task and sets up the
   * appropriate metadata to be used during execution.
   *
   * @param qb
   */
  @SuppressWarnings("unchecked")
  private void genColumnStatsTask(QB qb, List<LoadTableDesc> loadTableWork,
      List<LoadFileDesc> loadFileWork, List<Task<? extends Serializable>> rootTasks) {
    QBParseInfo qbParseInfo = qb.getParseInfo();
    ColumnStatsTask cStatsTask = null;
    ColumnStatsWork cStatsWork = null;
    FetchWork fetch = null;
    String tableName = qbParseInfo.getTableName();
    String partName = qbParseInfo.getPartName();
    List<String> colName = qbParseInfo.getColName();
    List<String> colType = qbParseInfo.getColType();
    boolean isTblLevel = qbParseInfo.isTblLvl();

    String cols = loadFileWork.get(0).getColumns();
    String colTypes = loadFileWork.get(0).getColumnTypes();

    String resFileFormat = HiveConf.getVar(conf, HiveConf.ConfVars.HIVEQUERYRESULTFILEFORMAT);
    TableDesc resultTab = PlanUtils.getDefaultQueryOutputTableDesc(cols, colTypes, resFileFormat);

    fetch = new FetchWork(new Path(loadFileWork.get(0).getSourceDir()).toString(),
        resultTab, qb.getParseInfo().getOuterQueryLimit());

    ColumnStatsDesc cStatsDesc = new ColumnStatsDesc(tableName, partName,
        colName, colType, isTblLevel);
    cStatsWork = new ColumnStatsWork(fetch, cStatsDesc);
    cStatsTask = (ColumnStatsTask) TaskFactory.get(cStatsWork, conf);
    rootTasks.add(cStatsTask);
  }

  /**
   * Find all leaf tasks of the list of root tasks.
   */
  private void getLeafTasks(List<Task<? extends Serializable>> rootTasks,
      HashSet<Task<? extends Serializable>> leaves) {

    for (Task<? extends Serializable> root : rootTasks) {
      getLeafTasks(root, leaves);
    }
  }

  private void getLeafTasks(Task<? extends Serializable> task,
      HashSet<Task<? extends Serializable>> leaves) {
    if (task.getDependentTasks() == null) {
      if (!leaves.contains(task)) {
        leaves.add(task);
      }
    } else {
      getLeafTasks(task.getDependentTasks(), leaves);
    }
  }

  /**
   * Make a best guess at trying to find the number of reducers
   */
  private static int getNumberOfReducers(MapredWork mrwork, HiveConf conf) {
    if (mrwork.getReduceWork() == null) {
      return 0;
    }

    if (mrwork.getReduceWork().getNumReduceTasks() >= 0) {
      return mrwork.getReduceWork().getNumReduceTasks();
    }

    return conf.getIntVar(HiveConf.ConfVars.HADOOPNUMREDUCERS);
  }

  private void decideExecMode(List<Task<? extends Serializable>> rootTasks, Context ctx,
      GlobalLimitCtx globalLimitCtx)
      throws SemanticException {

    // bypass for explain queries for now
    if (ctx.getExplain()) {
      return;
    }

    // user has told us to run in local mode or doesn't want auto-local mode
    if (ctx.isLocalOnlyExecutionMode() ||
        !conf.getBoolVar(HiveConf.ConfVars.LOCALMODEAUTO)) {
      return;
    }

    final Context lCtx = ctx;
    PathFilter p = new PathFilter() {
      public boolean accept(Path file) {
        return !lCtx.isMRTmpFileURI(file.toUri().getPath());
      }
    };
    List<ExecDriver> mrtasks = Utilities.getMRTasks(rootTasks);

    // map-reduce jobs will be run locally based on data size
    // first find out if any of the jobs needs to run non-locally
    boolean hasNonLocalJob = false;
    for (ExecDriver mrtask : mrtasks) {
      try {
        ContentSummary inputSummary = Utilities.getInputSummary
            (ctx, ((MapredWork) mrtask.getWork()).getMapWork(), p);
        int numReducers = getNumberOfReducers(mrtask.getWork(), conf);

        long estimatedInput;

        if (globalLimitCtx != null && globalLimitCtx.isEnable()) {
          // If the global limit optimization is triggered, we will
          // estimate input data actually needed based on limit rows.
          // estimated Input = (num_limit * max_size_per_row) * (estimated_map + 2)
          //
          long sizePerRow = HiveConf.getLongVar(conf,
              HiveConf.ConfVars.HIVELIMITMAXROWSIZE);
          estimatedInput = globalLimitCtx.getGlobalLimit() * sizePerRow;
          long minSplitSize = HiveConf.getLongVar(conf,
              HiveConf.ConfVars.MAPREDMINSPLITSIZE);
          long estimatedNumMap = inputSummary.getLength() / minSplitSize + 1;
          estimatedInput = estimatedInput * (estimatedNumMap + 1);
        } else {
          estimatedInput = inputSummary.getLength();
        }

        if (LOG.isDebugEnabled()) {
          LOG.debug("Task: " + mrtask.getId() + ", Summary: " +
              inputSummary.getLength() + "," + inputSummary.getFileCount() + ","
              + numReducers + ", estimated Input: " + estimatedInput);
        }

        if (MapRedTask.isEligibleForLocalMode(conf, numReducers,
            estimatedInput, inputSummary.getFileCount()) != null) {
          hasNonLocalJob = true;
          break;
        } else {
          mrtask.setLocalMode(true);
        }
      } catch (IOException e) {
        throw new SemanticException(e);
      }
    }

    if (!hasNonLocalJob) {
      // Entire query can be run locally.
      // Save the current tracker value and restore it when done.
      ctx.setOriginalTracker(ShimLoader.getHadoopShims().getJobLauncherRpcAddress(conf));
      ShimLoader.getHadoopShims().setJobLauncherRpcAddress(conf, "local");
      console.printInfo("Automatically selecting local only mode for query");
    }
  }

  public void compile(final MultiParseContext pCtx, final List<Task<? extends Serializable>> rootTasks,
      final HashSet<ReadEntity> inputs, final HashSet<WriteEntity> outputs) throws SemanticException {


    Context ctx = pCtx.getContext();

    QB qb = pCtx.getQB();
    List<Task<MoveWork>> mvTask = new ArrayList<Task<MoveWork>>();


    //multiquery analysis
    HashMap<Integer, ParseContext> multipCtx =((MultiParseContext) pCtx).getmultipctx();
    int size=multipCtx.size();
    for(int i=0;i<size;i++){
      ParseContext pCtxi=multipCtx.get(i);

      QB qbi=pCtxi.getQB();
      GlobalLimitCtx globalLimitCtx = pCtxi.getGlobalLimitCtx();

      List<LoadTableDesc> loadTableWorki = pCtxi.getLoadTableWork();
      List<LoadFileDesc> loadFileWorki = pCtxi.getLoadFileWork();
      boolean isCStatsi = qbi.isAnalyzeRewrite();

      if (pCtxi.getQB().getIsQuery() && !isCStatsi) {
        if ((!loadTableWorki.isEmpty()) || (loadFileWorki.size() != 1)) {
          throw new SemanticException(ErrorMsg.GENERIC_ERROR.getMsg());
        }

        LoadFileDesc loadFileDesc = loadFileWorki.get(0);

        String cols = loadFileDesc.getColumns();
        String colTypes = loadFileDesc.getColumnTypes();

        TableDesc resultTab = pCtxi.getFetchTabledesc();
        if (resultTab == null) {
          String resFileFormat = HiveConf.getVar(conf, HiveConf.ConfVars.HIVEQUERYRESULTFILEFORMAT);
          resultTab = PlanUtils.getDefaultQueryOutputTableDesc(cols, colTypes, resFileFormat);
        }

        FetchWork fetch = new FetchWork(new Path(loadFileDesc.getSourceDir()).toString(),
            resultTab, qbi.getParseInfo().getOuterQueryLimit());
        fetch.setSource(pCtxi.getFetchSource());
        fetch.setSink(pCtxi.getFetchSink());

      //  pCtx.setFetchTask((FetchTask) TaskFactory.get(fetch, conf));
        ((MultiParseContext) pCtx).getFetchTaskList().put(i, (FetchTask) TaskFactory.get(fetch, conf));



        // For the FetchTask, the limit optimization requires we fetch all the rows
        // in memory and count how many rows we get. It's not practical if the
        // limit factor is too big
        int fetchLimit = HiveConf.getIntVar(conf, HiveConf.ConfVars.HIVELIMITOPTMAXFETCH);
        if (globalLimitCtx.isEnable() && globalLimitCtx.getGlobalLimit() > fetchLimit) {
          LOG.info("For FetchTask, LIMIT " + globalLimitCtx.getGlobalLimit() + " > " + fetchLimit
              + ". Doesn't qualify limit optimiztion.");
          globalLimitCtx.disableOpt();
        }

      }else if (!isCStatsi) {
          for (LoadTableDesc ltd : loadTableWorki) {
            Task<MoveWork> tsk = TaskFactory.get(new MoveWork(null, null, ltd, null, false), conf);
            mvTask.add(tsk);
            // Check to see if we are stale'ing any indexes and auto-update them if we want
            if (HiveConf.getBoolVar(conf, HiveConf.ConfVars.HIVEINDEXAUTOUPDATE)) {
              IndexUpdater indexUpdater = new IndexUpdater(loadTableWorki, inputs, conf);
              try {
                List<Task<? extends Serializable>> indexUpdateTasks = indexUpdater
                    .generateUpdateTasks();
                for (Task<? extends Serializable> updateTask : indexUpdateTasks) {
                  tsk.addDependentTask(updateTask);
                }
              } catch (HiveException e) {
                console
                    .printInfo("WARNING: could not auto-update stale indexes, which are not in sync");
              }
            }
          }

          boolean oneLoadFile = true;
          for (LoadFileDesc lfd : loadFileWorki) {
            /*
            if (qb.isCTAS()) {
              assert (oneLoadFile); // should not have more than 1 load file for
              // CTAS
              // make the movetask's destination directory the table's destination.
              String location = qbi.getTableDesc().getLocation();
              if (location == null) {
                // get the table's default location
                Table dumpTable;
                Path targetPath;
                try {
                  dumpTable = db.newTable(qbi.getTableDesc().getTableName());
                  if (!db.databaseExists(dumpTable.getDbName())) {
                    throw new SemanticException("ERROR: The database " + dumpTable.getDbName()
                        + " does not exist.");
                  }
                  Warehouse wh = new Warehouse(conf);
                  targetPath = wh.getTablePath(db.getDatabase(dumpTable.getDbName()), dumpTable
                      .getTableName());
                } catch (HiveException e) {
                  throw new SemanticException(e);
                } catch (MetaException e) {
                  throw new SemanticException(e);
                }

                location = targetPath.toString();
              }
              lfd.setTargetDir(location);

              oneLoadFile = false;
            }*/
            mvTask.add(TaskFactory.get(new MoveWork(null, null, null, lfd, false), conf));
          }
        }

      }


    // generate map reduce plans
    //ParseContext tempParseContext = getParseContext(pCtx, rootTasks);
    MultiParseContext tempParseContext = getParseContext((MultiParseContext)pCtx, rootTasks,pCtx.getmultipctx());
    GenMRProcContext procCtx = new GenMRProcContext(
        conf,
        new HashMap<Operator<? extends OperatorDesc>, Task<? extends Serializable>>(),
        tempParseContext, mvTask, rootTasks,
        new LinkedHashMap<Operator<? extends OperatorDesc>, GenMapRedCtx>(),
        inputs, outputs);

    // create a walker which walks the tree in a DFS manner while maintaining
    // the operator stack.
    // The dispatcher generates the plan from the operator tree
    Map<Rule, NodeProcessor> opRules = new LinkedHashMap<Rule, NodeProcessor>();
    opRules.put(new RuleRegExp(new String("R1"),
        TableScanOperator.getOperatorName() + "%"),
        new GenMRTableScan1());
    opRules.put(new RuleRegExp(new String("R2"),
        TableScanOperator.getOperatorName() + "%.*" + ReduceSinkOperator.getOperatorName() + "%"),
        new GenMRRedSink1());
    opRules.put(new RuleRegExp(new String("R3"),
        ReduceSinkOperator.getOperatorName() + "%.*" + ReduceSinkOperator.getOperatorName() + "%"),
        new GenMRRedSink2());
    opRules.put(new RuleRegExp(new String("R4"),
        FileSinkOperator.getOperatorName() + "%"),
        new GenMRFileSink1());
    opRules.put(new RuleRegExp(new String("R5"),
        UnionOperator.getOperatorName() + "%"),
        new GenMRUnion1());
    opRules.put(new RuleRegExp(new String("R6"),
        UnionOperator.getOperatorName() + "%.*" + ReduceSinkOperator.getOperatorName() + "%"),
        new GenMRRedSink3());
    opRules.put(new RuleRegExp(new String("R7"),
        MapJoinOperator.getOperatorName() + "%"),
        MapJoinFactory.getTableScanMapJoin());

    // The dispatcher fires the processor corresponding to the closest matching
    // rule and passes the context along
    Dispatcher disp = new DefaultRuleDispatcher(new GenMROperator(), opRules,
        procCtx);

    GraphWalker ogw = new GenMapRedWalker(disp);
    ArrayList<Node> topNodes = new ArrayList<Node>();
    topNodes.addAll(pCtx.getTopOps().values());

    // Bubble sort
    LOG.debug("Before Bubble Sort: "+ topNodes.toString());
    Node tmp;
    for (int iter = topNodes.size()-1; iter >= 0; iter--) {
      for (int i = 0; i < iter; i++) {
        Operator<?> op1 = (Operator<?>)(topNodes.get(i));
        Operator<?> op2 = (Operator<?>)(topNodes.get(i+1));
        if (Integer.parseInt(op1.getIdentifier()) >
            Integer.parseInt(op2.getIdentifier()) ) {
          tmp = topNodes.get(i);
          topNodes.set(i, topNodes.get(i+1));
          topNodes.set(i+1, tmp);
        }
      }
    }
    LOG.debug("After Bubble Sort: "+ topNodes.toString());

    ogw.startWalking(topNodes, null);

    InterQueryFlowCtx Ictx=pCtx.getQueryFlowCtx();
    //for(table is same version dizeng (table1name ,tablename2))
    //   task.getname(tablename) setdependent(task.getname(tablename2))


    // add inter-query flow dependence edges
    int gidi=0;
    for(gidi=0;gidi<Ictx.getTabVersion().size();gidi++){
      FileSinkOperator fsop=Ictx.getWrOp(gidi);
      List<TableScanOperator> tsList=Ictx.getRdOpList(gidi);
      if(fsop != null){
        LOG.info("fsOp: : "+fsop.getName()+" "+fsop.getIdentifier());
      }
      if(tsList != null && tsList.size() > 0){
        LOG.info("TSlist: "+tsList.toString());
      }

      if(fsop != null && tsList != null && tsList.size()>0){
        ArrayList<Task<? extends Serializable>> fsTaskList=getTaskListwithOP(fsop,procCtx );
        for(Task<? extends Serializable> fsTask:fsTaskList){
          if (fsTask != null) {
            LOG.info("fsTask: "+fsTask.getName()+" "+fsTask.getId());
          } else {
            LOG.info("Task for FS "+fsop.getIdentifier()+" has been optimized.");
          }
        }

        for(TableScanOperator tsop : tsList){
          Task<? extends Serializable> tsTask = getTaskwithOP(tsop, procCtx);
          if(tsTask != null){
            LOG.info("tsTask: " + tsTask.getName() + " " + tsTask.getId());
            for(Task<? extends Serializable> fstask : fsTaskList) {
               fstask.addDependentTask(tsTask);
            }
            if(rootTasks.contains(tsTask)){
              rootTasks.remove(tsTask);
            }
          } else {
             LOG.info("Task for TS "+tsop.getIdentifier()+" has been optimized.");
          }
        }
      }

    }

    LOG.info("Before break Tree:\t " + Operator.toString(pCtx.getTopOps().values()));

    /*
     * If the query was the result of analyze table column compute statistics rewrite, create
     * a column stats task instead of a fetch task to persist stats to the metastore.

    if (isCStats) {
      genColumnStatsTask(qb, loadTableWork, loadFileWork, rootTasks);
    }
     */

    // reduce sink does not have any kids - since the plan by now has been
    // broken up into multiple
    // tasks, iterate over all tasks.
    // For each task, go over all operators recursively
    for (Task<? extends Serializable> rootTask : rootTasks) {
      breakTaskTree(rootTask);
    }

    // For each task, set the key descriptor for the reducer
    for (Task<? extends Serializable> rootTask : rootTasks) {
      GenMapRedUtils.setKeyAndValueDescForTaskTree(rootTask);
    }

    // If a task contains an operator which instructs bucketizedhiveinputformat
    // to be used, please do so
    for (Task<? extends Serializable> rootTask : rootTasks) {
      setInputFormat(rootTask);
    }

    // task reordering
    /*for (Task<? extends Serializable> rootTask : rootTasks) {
      List<Task<? extends Serializable>> deps = rootTask.getDependentTasks();

      if (deps != null) {
         for (Task<? extends Serializable> dep : deps) {
            for (Task<? extends Serializable> otherRootTask : rootTasks) {
               otherRootTask.addDependentTask(dep);
               LOG.info("Let Task " + dep.getId() + " execute after Task "
        		 + otherRootTask.getId());
            }
         }
      }
    }*/

    //for multiple query analysis pCtx.getFetchTask()==null
    // should be replaced by pCtx.getFetchTaskList() but pCtx.getFetchTask is useless
    //in this optimizer.please take care when using it.
    PhysicalContext physicalContext = new PhysicalContext(conf,
        getParseContext((MultiParseContext)pCtx, rootTasks), ctx, rootTasks, pCtx.getFetchTask());
    PhysicalOptimizer physicalOptimizer = new PhysicalOptimizer(
        physicalContext, conf);
    physicalOptimizer.optimize();

    // For each operator, generate the counters if needed
    if (HiveConf.getBoolVar(conf, HiveConf.ConfVars.HIVEJOBPROGRESS)) {
      for (Task<? extends Serializable> rootTask : rootTasks) {
        generateCountersTask(rootTask);
      }
    }

    //decideExecMode(rootTasks, ctx, globalLimitCtx);
    decideExecMode(rootTasks,ctx,null);

    if (qb.isCTAS()) {
      // generate a DDL task and make it a dependent task of the leaf
      CreateTableDesc crtTblDesc = qb.getTableDesc();

      crtTblDesc.validate();

      // Clear the output for CTAS since we don't need the output from the
      // mapredWork, the
      // DDLWork at the tail of the chain will have the output
      outputs.clear();

      Task<? extends Serializable> crtTblTask = TaskFactory.get(new DDLWork(
          inputs, outputs, crtTblDesc), conf);

      // find all leaf tasks and make the DDLTask as a dependent task of all of
      // them
      HashSet<Task<? extends Serializable>> leaves = new HashSet<Task<? extends Serializable>>();
      getLeafTasks(rootTasks, leaves);
      assert (leaves.size() > 0);
      for (Task<? extends Serializable> task : leaves) {
        if (task instanceof StatsTask) {
          // StatsTask require table to already exist
          for (Task<? extends Serializable> parentOfStatsTask : task.getParentTasks()) {
            parentOfStatsTask.addDependentTask(crtTblTask);
          }
          for (Task<? extends Serializable> parentOfCrtTblTask : crtTblTask.getParentTasks()) {
            parentOfCrtTblTask.removeDependentTask(task);
          }
          crtTblTask.addDependentTask(task);
        } else {
          task.addDependentTask(crtTblTask);
        }
      }
    }

    for(int i=0;i<pCtx.getmultipctx().size();i++){

      GlobalLimitCtx globalLimitCtx =pCtx.getmultipctx().get(i).getGlobalLimitCtx();
      if (globalLimitCtx.isEnable() && pCtx.getFetchTaskList().get(i) != null) {
        LOG.info("Query ID "+i+":  set least row check for FetchTask: " + globalLimitCtx.getGlobalLimit());
        pCtx.getFetchTaskList().get(i).getWork().setLeastNumRows(globalLimitCtx.getGlobalLimit());
      }

      if (globalLimitCtx.isEnable() && globalLimitCtx.getLastReduceLimitDesc() != null) {
        LOG.info("set least row check for LimitDesc: " + globalLimitCtx.getGlobalLimit());
        globalLimitCtx.getLastReduceLimitDesc().setLeastRows(globalLimitCtx.getGlobalLimit());
        List<ExecDriver> mrTasks = Utilities.getMRTasks(rootTasks);
        for (ExecDriver tsk : mrTasks) {
          tsk.setRetryCmdWhenFail(true);
        }
      }
    }
  }

  private ArrayList<Task<? extends Serializable>> getTaskListwithOP(Operator<?> op, GenMRProcContext procCtx){
    ArrayList<Task<? extends Serializable>> tasklist =new  ArrayList<Task<? extends Serializable>>();
    if(op instanceof FileSinkOperator){
      if(op.getParentOperators()!=null){
        Task<? extends Serializable> task=procCtx.getMapCurrCtx().get(op.getParentOperators().get(0)).getCurrTask();
        if(task.getChildTasks().get(0) instanceof ConditionalTask){

          ConditionalTask ctask = (ConditionalTask)(task.getChildTasks().get(0));
          for(Task<? extends Serializable> t: ctask.getListTasks())
          {
            addLastMovetask(tasklist,t);
          }

        }else{
          addLastMovetask(tasklist,task);
        }
      }
    }
    return tasklist;
  }

  private void addLastMovetask(ArrayList<Task<? extends Serializable>> tasklist,
      Task<? extends Serializable> t) {
    // TODO Auto-generated method stub
    if (t instanceof MoveTask ){
      tasklist.add(t);
    }else{
      if(t.getChildTasks()!=null){
        for(Task<? extends Serializable> task:t.getChildTasks()){
          addLastMovetask(tasklist,task);
        }
      }
    }

  }

  private Task<? extends Serializable> getTaskwithOP(Operator<?> op, GenMRProcContext procCtx) {
    // TODO Auto-generated method stub

    Iterator iter=procCtx.getTaskToSeenOps().entrySet().iterator();
    while(iter.hasNext()){
      Map.Entry<Task<? extends Serializable>, List<Operator<? extends OperatorDesc>>> entry = (Map.Entry<Task<? extends Serializable>, List<Operator<? extends OperatorDesc>>> )iter.next();
      Task<? extends Serializable>  key=entry.getKey();
      List<Operator<? extends OperatorDesc>> value=entry.getValue();
      if(value.contains(op)){
        return key;
      }
    }

    return null;
  }
}
