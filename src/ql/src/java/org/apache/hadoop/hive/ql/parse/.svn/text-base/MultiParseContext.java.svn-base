package org.apache.hadoop.hive.ql.parse;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.Context;
import org.apache.hadoop.hive.ql.QueryProperties;
import org.apache.hadoop.hive.ql.exec.AbstractMapJoinOperator;
import org.apache.hadoop.hive.ql.exec.FetchTask;
import org.apache.hadoop.hive.ql.exec.FileSinkOperator;
import org.apache.hadoop.hive.ql.exec.GroupByOperator;
import org.apache.hadoop.hive.ql.exec.JoinOperator;
import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.exec.ReduceSinkOperator;
import org.apache.hadoop.hive.ql.exec.SMBMapJoinOperator;
import org.apache.hadoop.hive.ql.exec.TableScanOperator;
import org.apache.hadoop.hive.ql.exec.Task;
import org.apache.hadoop.hive.ql.hooks.ReadEntity;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.optimizer.unionproc.UnionProcContext;
import org.apache.hadoop.hive.ql.plan.ExprNodeDesc;
import org.apache.hadoop.hive.ql.plan.FilterDesc.sampleDesc;
import org.apache.hadoop.hive.ql.plan.LoadFileDesc;
import org.apache.hadoop.hive.ql.plan.LoadTableDesc;
import org.apache.hadoop.hive.ql.plan.MapJoinDesc;
import org.apache.hadoop.hive.ql.plan.OperatorDesc;

public class MultiParseContext extends ParseContext {
  private HashMap<Integer, ParseContext>  multipctx;

  private HashMap<Integer,FetchTask> FetchTaskList;
  private Operator<? extends OperatorDesc> FakeOperator;
  private HashMap<String,Task<? extends Serializable>> querymaptasklist;

  private InterQueryFlowCtx queryFlowCtx;


  public MultiParseContext(){
    super();
    FetchTaskList=new HashMap<Integer, FetchTask>();
    querymaptasklist=new HashMap<String,Task<? extends Serializable>>();
  }

  public MultiParseContext(
      HiveConf conf,
      QB qb,
      ASTNode ast,
      HashMap<TableScanOperator, ExprNodeDesc> opToPartPruner,
      HashMap<TableScanOperator, PrunedPartitionList> opToPartList,
      HashMap<String, Operator<? extends OperatorDesc>> topOps,
      HashMap<String, Operator<? extends OperatorDesc>> topSelOps,
      LinkedHashMap<Operator<? extends OperatorDesc>, OpParseContext> opParseCtx,
      Map<JoinOperator, QBJoinTree> joinContext,
      Map<SMBMapJoinOperator, QBJoinTree> smbMapJoinContext,
      HashMap<TableScanOperator, Table> topToTable,
      HashMap<TableScanOperator, Map<String, String>> topToProps,
      Map<FileSinkOperator, Table> fsopToTable,
      List<LoadTableDesc> loadTableWork, List<LoadFileDesc> loadFileWork,
      Context ctx, HashMap<String, String> idToTableNameMap, int destTableId,
      UnionProcContext uCtx, List<AbstractMapJoinOperator<? extends MapJoinDesc>> listMapJoinOpsNoReducer,
      Map<GroupByOperator, Set<String>> groupOpToInputTables,
      Map<String, PrunedPartitionList> prunedPartitions,
      HashMap<TableScanOperator, sampleDesc> opToSamplePruner,
      GlobalLimitCtx globalLimitCtx,
      HashMap<String, SplitSample> nameToSplitSample,
      HashSet<ReadEntity> semanticInputs, List<Task<? extends Serializable>> rootTasks,
      Map<TableScanOperator, Map<String, ExprNodeDesc>> opToPartToSkewedPruner,
      Map<String, ReadEntity> viewAliasToInput,
      List<ReduceSinkOperator> reduceSinkOperatorsAddedByEnforceBucketingSorting,
      QueryProperties queryProperties,
      HashMap<Integer, ParseContext> multipctx){
    super(conf, qb, ast,opToPartPruner, opToPartList,topOps, topSelOps, opParseCtx, joinContext,smbMapJoinContext,
        topToTable,topToProps, fsopToTable,loadTableWork,loadFileWork, ctx,idToTableNameMap, destTableId,
        uCtx, listMapJoinOpsNoReducer, groupOpToInputTables, prunedPartitions,opToSamplePruner,  globalLimitCtx,nameToSplitSample,
        semanticInputs, rootTasks,opToPartToSkewedPruner,viewAliasToInput,reduceSinkOperatorsAddedByEnforceBucketingSorting,queryProperties);
    FetchTaskList=new HashMap<Integer, FetchTask>();
    querymaptasklist=new HashMap<String,Task<? extends Serializable>>();
    this.multipctx=multipctx;

  }


  public  HashMap<Integer, ParseContext> getmultipctx(){
    return multipctx;
  }

  public void setmultipctx( HashMap<Integer, ParseContext> multipctx){
    this.multipctx=multipctx;
  }

  public HashMap<String,Task<? extends Serializable>>   getquerymaptask(){
    return querymaptasklist;
  }

  public void setquerymaptask(HashMap<String,Task<? extends Serializable>> querymaptasklist){
    this.querymaptasklist=querymaptasklist;

  }

  public HashMap <Integer, FetchTask> getFetchTaskList(){
    return FetchTaskList;
  }

  public void setFetchTaskList(HashMap<Integer, FetchTask> FetchTasklist){
    this.FetchTaskList=FetchTasklist;

  }
  public Operator<? extends OperatorDesc> getFakeOperator(){
    return this.FakeOperator;
  }

  public void setFakeOperator(Operator<? extends OperatorDesc>  FakeOperator){
    this.FakeOperator=FakeOperator;

  }

  public void setQueryFlowCtx(InterQueryFlowCtx queryFlowCtx) {
    this.queryFlowCtx = queryFlowCtx;
  }

  public InterQueryFlowCtx getQueryFlowCtx() {
    return queryFlowCtx;
  }


}
