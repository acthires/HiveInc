/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hive.ql.optimizer.correlation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.ContentSummary;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.common.Pair;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.MultiDriver;
import org.apache.hadoop.hive.ql.exec.FakeOperator;
import org.apache.hadoop.hive.ql.exec.FileSinkOperator;
import org.apache.hadoop.hive.ql.exec.GroupByOperator;
import org.apache.hadoop.hive.ql.exec.JoinOperator;
import org.apache.hadoop.hive.ql.exec.MapJoinOperator;
import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.exec.PTFOperator;
import org.apache.hadoop.hive.ql.exec.ReduceSinkOperator;
import org.apache.hadoop.hive.ql.exec.TableScanOperator;
import org.apache.hadoop.hive.ql.exec.UnionOperator;
import org.apache.hadoop.hive.ql.lib.DefaultGraphWalker;
import org.apache.hadoop.hive.ql.lib.DefaultRuleDispatcher;
import org.apache.hadoop.hive.ql.lib.Dispatcher;
import org.apache.hadoop.hive.ql.lib.GraphWalker;
import org.apache.hadoop.hive.ql.lib.Node;
import org.apache.hadoop.hive.ql.lib.NodeProcessor;
import org.apache.hadoop.hive.ql.lib.NodeProcessorCtx;
import org.apache.hadoop.hive.ql.lib.Rule;
import org.apache.hadoop.hive.ql.lib.RuleRegExp;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.optimizer.MapJoinProcessor;
import org.apache.hadoop.hive.ql.optimizer.Transform;
import org.apache.hadoop.hive.ql.optimizer.physical.CommonJoinTaskDispatcher;
import org.apache.hadoop.hive.ql.parse.InterQueryFlowCtx;
import org.apache.hadoop.hive.ql.parse.MultiParseContext;
import org.apache.hadoop.hive.ql.parse.ParseContext;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.plan.ExprNodeColumnDesc;
import org.apache.hadoop.hive.ql.plan.ExprNodeDesc;
import org.apache.hadoop.hive.ql.plan.ExprNodeDescUtils;
import org.apache.hadoop.hive.ql.plan.JoinDesc;
import org.apache.hadoop.hive.ql.plan.OperatorDesc;

/**
 * Implementation of  Correlation Optimizer. This optimizer is based on
 * the paper "YSmart: Yet Another SQL-to-MapReduce Translator"
 * (Rubao Lee, Tian Luo, Yin Huai, Fusheng Wang, Yongqiang He, and Xiaodong Zhang)
 * (http://www.cse.ohio-state.edu/hpcs/WWW/HTML/publications/papers/TR-11-7.pdf).
 * Correlation Optimizer detects if ReduceSinkOperators share same keys.
 * Then, it will transform the query plan tree (operator tree) by exploiting
 * detected correlations. For details, see the original paper of YSmart.
 *
 * Test queries associated with this optimizer are correlationoptimizer1.q to
 * correlationoptimizer14.q
 */
public class RelaxCorrelationOptimizer implements Transform {

  private static final Log LOG = LogFactory.getLog(RelaxCorrelationOptimizer.class.getName());

  private boolean abort; // if correlation optimizer will not try to optimize this query

  private ParseContext pCtx;

  //Join operators which may be converted by CommonJoinResolver;
  private final Set<Operator<? extends OperatorDesc>> skipedJoinOperators;

  public RelaxCorrelationOptimizer() {
    super();
    pCtx = null;
    skipedJoinOperators = new HashSet<Operator<? extends OperatorDesc>>();
    abort = false;
  }

  private void findPossibleAutoConvertedJoinOperators() throws SemanticException {
    // Guess if CommonJoinResolver will work. If CommonJoinResolver may
    // convert a join operation, correlation optimizer will not merge that join.
    // TODO: If hive.auto.convert.join.noconditionaltask=true, for a JoinOperator
    // that has both intermediate tables and query input tables as input tables,
    // we should be able to guess if this JoinOperator will be converted to a MapJoin
    // based on hive.auto.convert.join.noconditionaltask.size.
    for (JoinOperator joinOp: pCtx.getJoinContext().keySet()) {
      boolean isAbleToGuess = true;
      boolean mayConvert = false;
      // Get total size and individual alias's size
      long aliasTotalKnownInputSize = 0;
      Map<String, Long> aliasToSize = new HashMap<String, Long>();
      Map<Integer, String> posToAlias = new HashMap<Integer, String>();
      for (Operator<? extends OperatorDesc> op: joinOp.getParentOperators()) {
        TableScanOperator tsop = CorrelationUtilities.findTableScanOperator(op);
        if (tsop == null) {
          isAbleToGuess = false;
          break;
        }

        Table table = pCtx.getTopToTable().get(tsop);
        String alias = tsop.getConf().getAlias();
        posToAlias.put(joinOp.getParentOperators().indexOf(op), alias);
        if (table == null) {
          // table should not be null.
          throw new SemanticException("The table of " +
              tsop.getName() + " " + tsop.getIdentifier() +
              " is null, which is not expected.");
        }

        Path p = table.getPath();
        FileSystem fs = null;
        ContentSummary resultCs = null;
        try {
          fs = table.getPath().getFileSystem(pCtx.getConf());
          resultCs = fs.getContentSummary(p);
        } catch (IOException e) {
          LOG.warn("Encounter a error while querying content summary of table " +
              table.getCompleteName() + " from FileSystem. " +
              "Cannot guess if CommonJoinOperator will optimize " +
              joinOp.getName() + " " + joinOp.getIdentifier());
        }
        if (resultCs == null) {
          isAbleToGuess = false;
          break;
        }

        long size = resultCs.getLength();
        aliasTotalKnownInputSize += size;
        Long es = aliasToSize.get(alias);
        if(es == null) {
          es = new Long(0);
        }
        es += size;
        aliasToSize.put(alias, es);
      }

      if (!isAbleToGuess) {
        LOG.info("Cannot guess if CommonJoinOperator will optimize " +
            joinOp.getName() + " " + joinOp.getIdentifier());
        continue;
      }

      JoinDesc joinDesc = joinOp.getConf();
      Byte[] order = joinDesc.getTagOrder();
      int numAliases = order.length;
      Set<Integer> bigTableCandidates =
          MapJoinProcessor.getBigTableCandidates(joinDesc.getConds());
      if (bigTableCandidates == null) {
        continue;
      }

      String bigTableAlias = null;
      long ThresholdOfSmallTblSizeSum = HiveConf.getLongVar(pCtx.getConf(),
          HiveConf.ConfVars.HIVESMALLTABLESFILESIZE);
      for (int i = 0; i < numAliases; i++) {
        // this table cannot be big table
        if (!bigTableCandidates.contains(i)) {
          continue;
        }
        bigTableAlias = posToAlias.get(i);
        if (!CommonJoinTaskDispatcher.cannotConvert(bigTableAlias, aliasToSize,
            aliasTotalKnownInputSize, ThresholdOfSmallTblSizeSum)) {
          mayConvert = true;
        }
      }

      if (mayConvert) {
        LOG.info(joinOp.getName() + " " + joinOp.getIdentifier() +
            " may be converted to MapJoin by CommonJoinResolver");
        skipedJoinOperators.add(joinOp);
      }
    }
  }

  /**
   * Detect correlations and transform the query tree.
   *
   * @param pactx
   *          current parse context
   * @throws SemanticException
   */
  public ParseContext transform(ParseContext pctx) throws SemanticException {

    pCtx = pctx;

    if (HiveConf.getBoolVar(pCtx.getConf(),HiveConf.ConfVars.HIVECONVERTJOIN)) {
      findPossibleAutoConvertedJoinOperators();
    }

    LOG.info("TC_PRE: trace back column names and version ID in RSs");
    OrigColumnNodeProcCtx origCorrCtx = new OrigColumnNodeProcCtx(pCtx);
    Map<Rule, NodeProcessor> origOpRules = new LinkedHashMap<Rule, NodeProcessor>();
    origOpRules.put(new RuleRegExp("R1", ReduceSinkOperator.getOperatorName() + "%"),
	new OrigColumnNodeProc());

    Dispatcher disp = new DefaultRuleDispatcher(getDefaultOrigProc(), origOpRules, origCorrCtx);
    GraphWalker ogw = new TopoGraphWalker(disp);

    // start walking from TS lists
    List<Node> topNodes = new ArrayList<Node>();
    topNodes.addAll(pCtx.getTopOps().values());
    ogw.startWalking(topNodes, null);

    origCorrCtx.removeWalkedAll();
    abort = origCorrCtx.isAbort();
    if (abort) {
      LOG.info("Abort. Reasons are ...");
      for (String reason : origCorrCtx.getAbortReasons()) {
        LOG.info("-- " + reason);
      }
      return pCtx;
    }
    LOG.info("WL:TC :Flow Analysis version ID dump ");
    //dumpversioninfoofALLRS(origCorrCtx);
    dumpversioninfoofALLRSInfo(origCorrCtx);


    // start detecting TC
    // TODO: change the order of TC detection, take advantage of flow analysis results
    LOG.info("TC: start detection");
    Map<Rule, NodeProcessor> opRules_TC = new LinkedHashMap<Rule, NodeProcessor>();
    opRules_TC.put(new RuleRegExp("R1", JoinOperator.getOperatorName() + "%"),new TCNodeProc());
    opRules_TC.put(new RuleRegExp("R2", FakeOperator.getOperatorName() + "%"),new TCNodeProc());
    //find RS and use the methods of class TCNodeProc.
    disp = new DefaultRuleDispatcher(getDefaultOrigProc(), opRules_TC, origCorrCtx);
    ogw = new DefaultGraphWalker(disp);
    topNodes = new ArrayList<Node>();
    HashSet<Node> tempTopNodes = new HashSet<Node>();
    HashSet<Integer> shouldremoveQIDlist = new HashSet<Integer>();
    boolean inlineOptimizer=true;
    if(inlineOptimizer){
      //remove the topop with dependence flow version
      InterQueryFlowCtx Ictx= ((MultiParseContext)pCtx).getQueryFlowCtx();
     // getmultipctx
      HashMap<Integer, ParseContext>  multipctx=((MultiParseContext)pCtx).getmultipctx();
      for(int gidi=0;gidi<Ictx.getTabVersion().size();gidi++){
        FileSinkOperator fs=Ictx.getWrOp(gidi);
        List<TableScanOperator> tslist=new ArrayList<TableScanOperator>();
        if(Ictx.getRdOpList(gidi)==null){
          continue;
        }
        tslist.addAll(Ictx.getRdOpList(gidi));

        if(fs!=null){
         // getListoftopop();
          tslist.add((TableScanOperator)(MultiDriver.getOneTSbyFS(fs)));
          for(TableScanOperator ts:tslist){
            for(int i=0;i<multipctx.size();i++){
              if(multipctx.get(i).getTopOps().values().contains(ts)){
                tempTopNodes.addAll(multipctx.get(i).getTopOps().values());
                shouldremoveQIDlist.add(i);
              }
            }
          }


        }

      }

      for(Node node:pCtx.getTopOps().values()){
        if(!tempTopNodes.contains(node)){
          topNodes.add(node);
        }
      }

    }else{
      topNodes.addAll(pCtx.getTopOps().values());


    }
    MultiDriver.addbottomfakeoperator((MultiParseContext)pCtx,shouldremoveQIDlist);
    ogw.startWalking(topNodes, null);

    deletefakeoperator(((MultiParseContext)pCtx).getFakeOperator());
    // We have finished tree walking (correlation detection).
    // We will first see if we need to abort (the operator tree has not been changed).
    // If not, we will start to transform the operator tree.
    abort = origCorrCtx.isAbort();
    if (abort) {
      LOG.info("Abort. Reasons are ...");
      for (String reason : origCorrCtx.getAbortReasons()) {
        LOG.info("-- " + reason);
      }
    } else {
      // transform the operator tree
      LOG.info("Begain query plan transformation based on intra-query correlations. " +
          origCorrCtx.getCorrelations().size() + " correlation(s) to be applied");
      long start = System.currentTimeMillis();
      /*//////////////////////////////////////////
      //common sub tree test
      LOG.info("======Print correlation(s)' common sub tree =======");
      List<Operator<?>> lastOp =  new ArrayList<Operator<?>>();
      for (IntraQueryCorrelation correlation : origCorrCtx.getCorrelations()){
        Iterator<ReduceSinkOperator> it=correlation.getTcReduceSinkOperators().iterator();
        //  ReduceSinkOperator lastrs;
          while(it.hasNext()){
            lastOp.add(it.next());
          }
          if(lastOp.size() >= 2 ){
            for( int i = 0 ; i < lastOp.size() ; i++){
              for(int j=i+1;j<lastOp.size();j++){

                LOG.info("The correlate RS of two optree is : "+ lastOp.get(i).toString() + " "+  lastOp.get(j).toString() );
                CommonSubtreeDetect cstd = new CommonSubtreeDetect(lastOp.get(i) , lastOp.get(j));
                LOG.info(cstd.toString() );
              }
            }
          }
        lastOp.clear();
      }

      long end =  System.currentTimeMillis();
      double timeTaken = (end - start) / 1000.0;
      LOG.info("Run Common subtree detect time taken: " + timeTaken + " seconds" );
      //CommonSubtree Detect
      */
      /////////////////////////////////////*/

      int count=1;
      for (IntraQueryCorrelation correlation : origCorrCtx.getCorrelations()) {
        LOG.info(count+"th correlation is applied");
        QueryPlanTreeTransformation_TC.applyCorrelation(pCtx, origCorrCtx, correlation);
       // LOG.info(correlation.getTcReduceSinkOperators());
        count++;
      }
    }
    return pCtx;
  }


  private void dumpversioninfoofALLRS(OrigColumnNodeProcCtx origCorrCtx) {
    // TODO Auto-generated method stub
    HashMap<Node, OrigColumnwithVersionDesc> info =origCorrCtx.getRsToOrigNameswithversion();

    Iterator iter=info.entrySet().iterator();
    while(iter.hasNext()){
      Map.Entry<Operator<? extends OperatorDesc>,OrigColumnwithVersionDesc> entry = (Map.Entry<Operator<? extends OperatorDesc>,OrigColumnwithVersionDesc>)iter.next();
      Operator<? extends OperatorDesc> op=entry.getKey();
      OrigColumnwithVersionDesc value=entry.getValue();
      List<Integer> keyID =value.getKeyID();
      List<Integer> valueID =value.getValID();
      List<Integer>  partitionID =value.getPID();
      List<ExprNodeDesc> keyCols =value.keyCols();
      List<ExprNodeDesc> valueCols =value.valCols();
      List<ExprNodeDesc>  partitionCols =value.partitionCols();
      List<ExprNodeDesc> orgkeyCols =((ReduceSinkOperator)op).getConf().getKeyCols();
      List<ExprNodeDesc> orgvalueCols =((ReduceSinkOperator)op).getConf().getValueCols();
      List<ExprNodeDesc>  orgpartitionCols =((ReduceSinkOperator)op).getConf().getPartitionCols();

      LOG.debug("==========================================================");
      LOG.debug("WL:The current RS is"+op.getName() + " " + op.getIdentifier());
      LOG.debug("WL:Now dump the version ID and the value of each column!");
      LOG.debug("WL:Key Columns for " + op.getName() + " " + op.getIdentifier());

      for ( int i=0;i< keyCols.size();i++) {
         if (keyCols.get(i) instanceof ExprNodeColumnDesc) {
          LOG.debug("WL:---keyCols ID   : " + keyID.get(i) );
          LOG.debug("WL:---keyCols Value: " + keyCols.get(i));
          LOG.debug("WL:---original keyCols Value: " + orgkeyCols.get(i));
        } else {
          LOG.debug("-- <non-col>");
        }
      }
      LOG.debug("Wl:value Columns for " + op.getName() + " " + op.getIdentifier());
      for ( int i=0;i< valueCols.size();i++) {
        if (valueCols.get(i) instanceof ExprNodeColumnDesc) {
         LOG.debug("WL:---valueCols ID   : " + valueID.get(i));
         LOG.debug("WL:---valueCols Value: " + valueCols.get(i));
         LOG.debug("WL:---original valueCols Value: " + orgvalueCols.get(i));
       } else {
         LOG.debug("-- <non-col>");
       }
     }
      LOG.debug("Wl:Partition Columns for " + op.getName() + " " + op.getIdentifier());
      for ( int i=0;i< partitionCols.size();i++) {
        if (partitionCols.get(i) instanceof ExprNodeColumnDesc) {
         LOG.debug("WL:---partitionCols ID   : " +partitionID.get(i)) ;
         LOG.debug("WL:---partitionCols Value: " + partitionCols.get(i));
         LOG.debug("WL:---original partitionCols Value: " + orgpartitionCols.get(i));
       } else {
         LOG.debug("-- <non-col>");
       }
     }

    }
  }

  private void dumpversioninfoofALLRSInfo(OrigColumnNodeProcCtx origCorrCtx) {
    // TODO Auto-generated method stub
    HashMap<Node, OrigColumnwithVersionDesc> info =origCorrCtx.getRsToOrigNameswithversion();

    Iterator iter=info.entrySet().iterator();
    while(iter.hasNext()){
      Map.Entry<Operator<? extends OperatorDesc>,OrigColumnwithVersionDesc> entry = (Map.Entry<Operator<? extends OperatorDesc>,OrigColumnwithVersionDesc>)iter.next();
      Operator<? extends OperatorDesc> op=entry.getKey();
      OrigColumnwithVersionDesc value=entry.getValue();
      List<Integer> keyID =value.getKeyID();
      List<Integer> valueID =value.getValID();
      List<Integer>  partitionID =value.getPID();
      List<ExprNodeDesc> keyCols =value.keyCols();
      List<ExprNodeDesc> valueCols =value.valCols();
      List<ExprNodeDesc>  partitionCols =value.partitionCols();
      List<ExprNodeDesc> orgkeyCols =((ReduceSinkOperator)op).getConf().getKeyCols();
      List<ExprNodeDesc> orgvalueCols =((ReduceSinkOperator)op).getConf().getValueCols();
      List<ExprNodeDesc>  orgpartitionCols =((ReduceSinkOperator)op).getConf().getPartitionCols();

      LOG.info("==========================================================");
      LOG.info("WL:The current RS is"+op.getName() + " " + op.getIdentifier());
      LOG.info("WL:Now dump the version ID and the value of each column!");
      LOG.info("WL:Key Columns for " + op.getName() + " " + op.getIdentifier());

      for ( int i=0;i< keyCols.size();i++) {
         if (keyCols.get(i) instanceof ExprNodeColumnDesc) {
          LOG.info("WL:---keyCols ID   : " + keyID.get(i) );
          LOG.info("WL:---keyCols Value: " + keyCols.get(i));
          LOG.info("WL:---original keyCols Value: " + orgkeyCols.get(i));
        } else {
          LOG.info("-- <non-col>");
        }
      }
      LOG.debug("Wl:value Columns for " + op.getName() + " " + op.getIdentifier());
      for ( int i=0;i< valueCols.size();i++) {
        if (valueCols.get(i) instanceof ExprNodeColumnDesc) {
         LOG.info("WL:---valueCols ID   : " + valueID.get(i));
         LOG.info("WL:---valueCols Value: " + valueCols.get(i));
         LOG.info("WL:---original valueCols Value: " + orgvalueCols.get(i));
       } else {
         LOG.info("-- <non-col>");
       }
     }
      LOG.debug("Wl:Partition Columns for " + op.getName() + " " + op.getIdentifier());
      for ( int i=0;i< partitionCols.size();i++) {
        if (partitionCols.get(i) instanceof ExprNodeColumnDesc) {
         LOG.info("WL:---partitionCols ID   : " +partitionID.get(i)) ;
         LOG.info("WL:---partitionCols Value: " + partitionCols.get(i));
         LOG.info("WL:---original partitionCols Value: " + orgpartitionCols.get(i));
       } else {
         LOG.info("-- <non-col>");
       }
     }

    }
  }

  public  void deletefakeoperator(Operator<? extends OperatorDesc> fakeOperator) {
    if(fakeOperator==null){
      return;
    }
    if(fakeOperator.getParentOperators()!=null){
      for(Operator<? extends OperatorDesc> p:fakeOperator.getParentOperators()){
        p.getChildOperators().remove(fakeOperator);
      }
      fakeOperator.getParentOperators().clear();
    }

  }
  private class TCNodeProc implements NodeProcessor {

    /*private void analyzeReduceSinkOperatorsOfJoinOperator(JoinCondDesc[] joinConds,
        List<Operator<? extends OperatorDesc>> rsOps, Operator<? extends OperatorDesc> curentRsOp,
        Set<ReduceSinkOperator> correlatedRsOps) {
      if (correlatedRsOps.contains((ReduceSinkOperator) curentRsOp)) {
        return;
      }
      correlatedRsOps.add((ReduceSinkOperator) curentRsOp);

      int pos = rsOps.indexOf(curentRsOp);//?
      for (int i = 0; i < joinConds.length; i++) {
        JoinCondDesc joinCond = joinConds[i];
        int type = joinCond.getType();
        if (pos == joinCond.getLeft()) {
          if (type == JoinDesc.INNER_JOIN ||
              type == JoinDesc.LEFT_OUTER_JOIN ||
              type == JoinDesc.LEFT_SEMI_JOIN) {
            Operator<? extends OperatorDesc> newCurrentRsOps = rsOps.get(joinCond.getRight());
            analyzeReduceSinkOperatorsOfJoinOperator(joinConds, rsOps, newCurrentRsOps,
                correlatedRsOps);
          }
        } else if (pos == joinCond.getRight()) {
          if (type == JoinDesc.INNER_JOIN || type == JoinDesc.RIGHT_OUTER_JOIN) {
            Operator<? extends OperatorDesc> newCurrentRsOps = rsOps.get(joinCond.getLeft());
            analyzeReduceSinkOperatorsOfJoinOperator(joinConds, rsOps, newCurrentRsOps,
                correlatedRsOps);
          }
        }
      }
    }*/

    private boolean sameKeys(List<ExprNodeDesc> k1, List<ExprNodeDesc> k2) {
      if (k1.size() != k2.size()) {
        return false;
      }
      for (int i = 0; i < k1.size(); i++) {
        ExprNodeDesc expr1 = k1.get(i);
        ExprNodeDesc expr2 = k2.get(i);
        if (expr1 == null) {
          if (expr2 == null) {
            continue;
          } else {
            return false;
          }
        } else {
          if (!expr1.isSame(expr2)) {
            return false;
          }
        }
      }
      return true;
    }

    private boolean sameKeyswithID(List<ExprNodeDesc> k1, List<ExprNodeDesc> k2,
        List<Integer> ListID1,List<Integer> ListID2) {
      if (k1.size() != k2.size()) {
        return false;
      }
      for (int i = 0; i < k1.size(); i++) {
        ExprNodeDesc expr1 = k1.get(i);
        ExprNodeDesc expr2 = k2.get(i);
        int ID1 = ListID1.get(i);
        int ID2 = ListID2.get(i);
        if(ID1!=ID2){
          return false;
        }
        if (expr1 == null) {
          if (expr2 == null) {
            continue;
          } else {
            return false;
          }
        } else {
          if (!expr1.isSame(expr2)) {
            return false;
          }
        }
      }
      return true;
    }
    private boolean sameversionID(List<Integer> k1, List<Integer> k2) {
      if (k1.size() != k2.size()) {
        return false;
      }
      for (int i = 0; i < k1.size(); i++) {
        int ID1 = k1.get(i);
        int ID2 = k2.get(i);
        if(ID1!=ID2){
          return false;
        }
      }
      return true;
    }


    private boolean samevalueversionID(List<Integer> k1, List<Integer> k2) {

      for (int i = 0; i < k1.size(); i++) {
        int ID1 = k1.get(i);
        int ID2 = k2.get(i);
        if(ID1!=ID2){
          return false;
        }
      }
      return true;
    }




    private boolean sameOrder(String order1, String order2) {
      if (order1 == null || order1.trim().equals("")) {
        if (order2 == null || order2.trim().equals("")) {
          return true;
        }
        return false;
      }
      if (order2 == null || order2.trim().equals("")) {
        return false;
      }
      order1 = order1.trim();
      order2 = order2.trim();
      if (!order1.equals(order2)) {
        return false;
      }
      return true;
    }

    private boolean overlapKeys(List<ExprNodeDesc> k1, List<ExprNodeDesc> k2) {
      if (k1.size() == 0 || k2.size() == 0) {
        return false;
      }
      for (int i = 0; i < k1.size(); i++) {
        ExprNodeDesc expr1 = k1.get(i);
        if (expr1 == null) {
          continue;
        }
        for (int j = 0; j < k2.size(); j++) {
           ExprNodeDesc expr2 = k2.get(j);
           if (expr2 == null) {
            continue;
          }
           if (expr1.isSame(expr2)) {
            return true;
          }
        }
      }
      return false;
    }
    private boolean overlapKeyswithID(List<ExprNodeDesc> k1, List<ExprNodeDesc> k2,
        List<Integer> ListID1,List<Integer> ListID2) {
      if (k1.size() == 0 || k2.size() == 0) {
        return false;
      }
      for (int i = 0; i < k1.size(); i++) {
        ExprNodeDesc expr1 = k1.get(i);

        if (expr1 == null) {
          continue;
        }
        for (int j = 0; j < k2.size(); j++) {
           ExprNodeDesc expr2 = k2.get(j);
           if (expr2 == null) {
            continue;
          }
           if (expr1.isSame(expr2)) {

             int ID1=ListID1.get(i);
             int ID2=ListID2.get(j);
             if(ID1!=ID2){
               return false;
             }

            return true;
          }
        }
      }
      return false;
    }

    private List<ReduceSinkOperator> sameRsLevelTc(ReduceSinkOperator rs,
		LinkedHashSet<ReduceSinkOperator> corrRSs, OrigColumnNodeProcCtx ctx) {
       List<ReduceSinkOperator> tc = new ArrayList<ReduceSinkOperator>();

       int rsLevel = ctx.rsColwithIDDesc(rs).getRsLevel();
       for (ReduceSinkOperator rsop : corrRSs) {
          if (rsLevel == ctx.rsColwithIDDesc(rsop).getRsLevel()) {
             tc.add(rsop);
          } else {
             LOG.info("===sameRsLevel IS MET: " + rsop.getName() + " " + rsop.getIdentifier() +
                      " is excluded from " + rs.getName() + " " +
                      rs.getIdentifier() + "'s TC set");
          }
       }

       return tc;
    }

    private LinkedHashSet<ReduceSinkOperator> detectCrossJoin(ReduceSinkOperator rs,
    LinkedHashSet<ReduceSinkOperator> corrRSs) throws SemanticException {
      LinkedHashSet<ReduceSinkOperator> tc= new LinkedHashSet<ReduceSinkOperator>();
      boolean flag=false;
      if(rs.getChildOperators().size()>1
          ||CorrelationUtilities.findSiblingReduceSinkOperators((ReduceSinkOperator)rs).size()>1){
        if(!rs.getChildOperators().contains(((MultiParseContext)pCtx).getFakeOperator())){
          LOG.info("TC: detect crossjoin!");
          flag=true;
        }

      }


       for (ReduceSinkOperator rsop : corrRSs) {
         if(rsop.getChildOperators().contains(((MultiParseContext)pCtx).getFakeOperator())){
           tc.add(rsop);
         }else if (rsop.getChildOperators().size()==1&&CorrelationUtilities.findSiblingReduceSinkOperators((ReduceSinkOperator)rsop).size()==1) {
             tc.add(rsop);
          } else if(flag==false){

            tc.add(rsop);
            flag=true;
          }else {
             LOG.info("TC:==CROSSJOIN IS MET: " + rsop.getName() + " " + rsop.getIdentifier() +
                      " is excluded from " + rs.getName() + " " +
                      rs.getIdentifier() + "'s TC set");
          }
       }

       return tc;
    }

  public Object process(Node nd, Stack<Node> stack, NodeProcessorCtx ctx,
         Object... nodeOutputs) throws SemanticException {
    OrigColumnNodeProcCtx corrCtx = (OrigColumnNodeProcCtx) ctx;
    Operator<? extends OperatorDesc> op= (Operator<? extends OperatorDesc>)nd;
    // Check if we have visited this operator
    /*if (corrCtx.isWalked(op)) {
      return null;
    }*/
    LOG.debug("***********************JOIN/FAKE IS MET******************************");
    LOG.info("TC: now in "+op.getIdentifier()+" "+op.getName());
    List<Operator<? extends OperatorDesc>> RSs = new ArrayList<Operator<? extends OperatorDesc>>();
    /* 1) Use parents list as a worklist which stores a pre-order depth-first traversing order.
     * 2) Only exploit TC between current RS with each its right-hand-side RS siblings and their ancestors
     *    as its left-hand-side TC exploiting has already been done in previous iterations.
     */
    List<Operator<? extends OperatorDesc>> parents = new ArrayList<Operator<? extends OperatorDesc>>();
    RSs.addAll(op.getParentOperators());// parents RSs of Join
    String RSparents="";
    for(int i=0;i<RSs.size();i++){
      RSparents+=" ["+RSs.get(i)+"]";
    }
    LOG.info("TC: join/fake's parents: "+RSparents);
    for(int i=0;i<RSs.size();i++){
      if(!(RSs.get(i) instanceof ReduceSinkOperator)){//MuxOperator
        continue;
      }
      ReduceSinkOperator rs = (ReduceSinkOperator)RSs.get(i);
      parents.add(rs);
      int j = 0;
      while (j < parents.size()) {
          Operator<? extends OperatorDesc> current = parents.get(j);

          j++;
          boolean RSalreadyinCorlist=false;
          for (IntraQueryCorrelation correlation : corrCtx.getCorrelations()) {
            if(correlation.getTcReduceSinkOperators().contains(current)){
              RSalreadyinCorlist=true;
              break;
            }
          }
          if(RSalreadyinCorlist==true){
            LOG.info("TC: Stop to detect the operator " + current.getIdentifier() + " " + current.getName()+
                " because it is aleady in the correaltion list!");
            continue;
          }


          if (current.getParentOperators() != null)
           {
            parents.addAll(current.getParentOperators()); // add parentops into worklist
          }
          LOG.info("TC: Walk to operator " + current.getIdentifier() + " " + current.getName());
          if(current instanceof ReduceSinkOperator && !corrCtx.isWalked((ReduceSinkOperator)current)){//find a rs operator

            IntraQueryCorrelation correlation = new IntraQueryCorrelation(corrCtx.minReducer());// initialize a datastruct to record TC
            LinkedHashSet<ReduceSinkOperator> correlatedReduceSinkOperators = new LinkedHashSet<ReduceSinkOperator>();

            LinkedHashSet<ReduceSinkOperator> newcorrelatedReduceSinkOperators =
              exploitTransmitCorrelation((ReduceSinkOperator)current, (ReduceSinkOperator)RSs.get(i),corrCtx, correlation);
            correlatedReduceSinkOperators.addAll(newcorrelatedReduceSinkOperators);


            if(correlatedReduceSinkOperators.size()!=0){
              LOG.info("TC: ******has Transmit Correlation!********");
              /* Right now, we only apply TC optimization on RS on the same level
               * TODO: have to be relaxed later
               */
              /*
              LinkedHashSet<ReduceSinkOperator>  temptc=detectCrossJoin((ReduceSinkOperator)current, correlatedReduceSinkOperators);
              correlatedReduceSinkOperators=temptc;
              List<ReduceSinkOperator> tcrs = new ArrayList<ReduceSinkOperator>();
              tcrs.addAll(correlatedReduceSinkOperators);
              tcrs.add((ReduceSinkOperator)current);
              correlation.setTransmitCorrelation(true, tcrs);*/

                LinkedHashSet<ReduceSinkOperator>  temptc=detectCrossJoin((ReduceSinkOperator)current, correlatedReduceSinkOperators);
                correlatedReduceSinkOperators=temptc;

              List<ReduceSinkOperator> tcrs = sameRsLevelTc(
			(ReduceSinkOperator)current, correlatedReduceSinkOperators, corrCtx);
              if (!tcrs.isEmpty()) {
                 tcrs.add((ReduceSinkOperator)current);
                 correlation.setTransmitCorrelation(true, tcrs);
              }
              /* Don't add current tcrs into walked list so that we can keep searching
               * searching right level TC in its ancestors
               */
              //corrCtx.addWalkedAll(tcrs);
            }
            if (correlation.hasTransmitCorrelation()) {
              LOG.info("correlation add to corrCtx");
              corrCtx.addCorrelation(correlation);
            }
            /* Here we don't need to remove Rsops which has already been walked in
             * exploitTC but with no TC found. In fact, we don't even need to care
             * about the left-hand-side siblings of the underconsidering RS as we have
             * already exploited TC between the RS and each of its left-hand-side
             * siblings in previous iterations.
             */
            corrCtx.removeWalkedAll(correlation.getAllReduceSinkOperators());

          }
      }
      corrCtx.addWalked((ReduceSinkOperator)RSs.get(i));
      parents.clear();

    }

    return null;
  }
  /*
   * bootRsop is one of parents of join and also a sibling of currentRs
   */
  private LinkedHashSet<ReduceSinkOperator> exploitTransmitCorrelation(
      ReduceSinkOperator currentRs, ReduceSinkOperator bootRsop,
        OrigColumnNodeProcCtx correlationCtx, IntraQueryCorrelation correlation)
        throws SemanticException {
    if(correlationCtx.isWalked(currentRs)){
      LOG.info("TC: Operator " + currentRs.getIdentifier() + "has already been walked");
      return null;
    }
    //find tc for rsop start from sibling RS of rsop
    correlationCtx.addWalked(currentRs);
    correlation.addToAllReduceSinkOperators(currentRs);
    //correlation.addToAllReduceSinkOperators(rsop);
    boolean shouldDetect = true;
    List<ExprNodeDesc> keyCols = currentRs.getConf().getKeyCols();
    List<ExprNodeDesc> partitionCols = currentRs.getConf().getPartitionCols();
    LinkedHashSet<ReduceSinkOperator> newReduceSinkOperators =
      new LinkedHashSet<ReduceSinkOperator>();
    for (ExprNodeDesc key : keyCols) {
      if (!(key instanceof ExprNodeColumnDesc)) {
        shouldDetect = false;
      }
    }
    for (ExprNodeDesc key : partitionCols) {
      if (!(key instanceof ExprNodeColumnDesc)) {
        shouldDetect = false;
      }
    }
    /* 1) Instead of exploiting TC from parentOps of each RS, set each RS itself as a start point.
     * 2) Only exploit TC in left-hand-side siblings (i.e. ops which are marked as unwalked) of the current RS.
     */
    if (shouldDetect) {
       String sortOrder = currentRs.getConf().getOrder();
       List<ReduceSinkOperator> siblingRss = CorrelationUtilities.findSiblingReduceSinkOperators(bootRsop,((MultiParseContext)pCtx).getFakeOperator());
       for (ReduceSinkOperator rs : siblingRss) {
         if(rs != bootRsop && !correlationCtx.isWalked(rs)){
            // only find correlated RS in different & unwalked branches from bootRsop of join
            LOG.info("TC: Operator " + rs.getIdentifier()
                 + ": start detecting correlation from this operator");
             //find the correlated rs from sibling of rsop
             LinkedHashSet<ReduceSinkOperator> correlatedReduceSinkOperators =
                  findCorrelatedReduceSinkOperators(currentRs, rs, correlationCtx, correlation);
             newReduceSinkOperators.addAll(correlatedReduceSinkOperators);
          }
       }
    } else {
      LOG.info("TC: Operator " + currentRs.getIdentifier() + "should not be detected");
    }

    return newReduceSinkOperators;
   }

   private LinkedHashSet<ReduceSinkOperator> findCorrelatedReduceSinkOperators(
        Operator<? extends OperatorDesc> child,
        Operator<? extends OperatorDesc> current,
        OrigColumnNodeProcCtx ctx,
        IntraQueryCorrelation correlation) throws SemanticException {
      LOG.info("TC: now detecting operators (" + child.getIdentifier() + " " + child.getName() + "," +
        	current.getIdentifier() + " " + current.getName() + ")");

      LinkedHashSet<ReduceSinkOperator> correlatedReduceSinkOperators =
          new LinkedHashSet<ReduceSinkOperator>();
      if (skipedJoinOperators.contains(current)) {
        LOG.info(current.getName() + " " + current.getIdentifier() +
            " may be converted to MapJoin by " +
            "CommonJoinResolver. Correlation optimizer will not detect correlations" +
            "involved in this operator");
        return correlatedReduceSinkOperators;
      }
      if (current.getParentOperators() == null) {
        return correlatedReduceSinkOperators;
      }
      if (current instanceof PTFOperator) {
        // Currently, we do not support PTF operator.
        LOG.info("Currently, correlation optimizer does not support PTF operator.");
        return correlatedReduceSinkOperators;
      }
      if (current instanceof UnionOperator) {
        // If we get a UnionOperator, right now, we only handle it when
        // we can find correlated ReduceSinkOperators from all inputs.
        LinkedHashSet<ReduceSinkOperator> corrRSs = new LinkedHashSet<ReduceSinkOperator>();
        for (Operator<? extends OperatorDesc> parent : current.getParentOperators()) {
          LinkedHashSet<ReduceSinkOperator> tmp =
              findCorrelatedReduceSinkOperators(child, parent, ctx, correlation);
          if (tmp != null && tmp.size() > 0) {
            corrRSs.addAll(tmp);
          } else {
            return correlatedReduceSinkOperators;
          }
        }
        correlatedReduceSinkOperators.addAll(corrRSs);
        UnionOperator union = (UnionOperator)current;
     //   union.getConf().setAllInputsInSameReducer(true);
      } else if (!(current instanceof ReduceSinkOperator)) {
        for (Operator<? extends OperatorDesc> parent : current.getParentOperators()) {
          correlatedReduceSinkOperators.addAll(
              findCorrelatedReduceSinkOperators(child, parent, ctx, correlation));
        }
      } else if (current.getColumnExprMap() != null && current instanceof ReduceSinkOperator) {
        ReduceSinkOperator rsop = (ReduceSinkOperator) current;
        OrigColumnwithVersionDesc rsOrig = ctx.rsColwithIDDesc((ReduceSinkOperator)rsop);
        OrigColumnwithVersionDesc childOrig = ctx.rsColwithIDDesc((ReduceSinkOperator)child);
        if(rsOrig == null || childOrig == null) {
           LOG.info("Column description is not found");
           return correlatedReduceSinkOperators;
        }

        List<Integer> childKeyID = childOrig.getKeyID();
        List<Integer> childValueID = childOrig.getValID();
        List<Integer> childPID = childOrig.getPID();
        List<ExprNodeDesc> childKeyCols = childOrig.keyCols();
        List<ExprNodeDesc> childValueCols = childOrig.valCols();
        List<ExprNodeDesc> childPartitionCols = childOrig.partitionCols();
        List<Integer> rsKeyID = rsOrig.getKeyID();
        List<Integer> rsValueID = rsOrig.getValID();
        List<Integer> rsPID = rsOrig.getPID();
        List<ExprNodeDesc> rsKeyCols = rsOrig.keyCols();
        List<ExprNodeDesc> rsPartitionCols = rsOrig.partitionCols();
        List<ExprNodeDesc> rsValueCols = rsOrig.valCols();
        String childRSOrder = ((ReduceSinkOperator)child).getConf().getOrder();

        // Two ReduceSinkOperators are correlated means that
        // they have same sorting columns (key columns), same partitioning columns,
        // same sorting orders, and no conflict on the numbers of reducers.
        // TODO: we should relax this condition
        // TODO: we need to handle aggregation functions with distinct keyword. In this case,
        // distinct columns will be added to the key columns.
        // exploit overlapped data
        LOG.info("TC: exploit data overlapped between" + child.getIdentifier() +
        " and " + current.getIdentifier());
        boolean isOverlapped = overlapKeyswithID(childKeyCols, rsKeyCols,childKeyID,rsKeyID) ||
                               overlapKeyswithID(childValueCols,rsValueCols,childValueID,rsValueID) ||
                               overlapKeyswithID(childPartitionCols,rsPartitionCols,childPID,rsPID); // Too relax?
        if (isOverlapped) {
            // TODO: add further handler
            LOG.info("TC: has data overlapped !");
        }
        boolean isCorrelatedpre = sameKeyswithID(childKeyCols, rsKeyCols,childKeyID,rsKeyID) &&
            sameOrder(rsop.getConf().getOrder(), childRSOrder) &&
            sameKeyswithID(childPartitionCols, rsPartitionCols,childPID,rsPID);
        //   && sameversionID(childValueID,rsValueID);
          //  &&
          //  correlation.adjustNumReducers(rsop.getConf().getNumReducers());
        boolean isinclude =false;
        boolean isCorrelated=false;
        if (isCorrelatedpre) {

            // further check if they have same value columns
            // TODO: consider table version
            LOG.info("TC: exploit strict TC between " + child.getIdentifier() +
                   " and " + current.getIdentifier());
            isinclude = inccludekeyswithID(childValueCols,rsValueCols,childValueID,rsValueID);



            boolean isSame = sameKeyswithID(childValueCols,rsValueCols,childValueID,rsValueID);
            if (isSame) {
               // TODO: add further handler
               LOG.info("TC: has same data");
            }else{}

              isCorrelated=isinclude||isSame;

        } else if (/*sameOrder(rsop.getConf().getOrder(), childRSOrder) &&*/
            correlation.adjustNumReducers(rsop.getConf().getNumReducers())) // Too strict?
        {        }
        isCorrelated=isCorrelatedpre;

        GroupByOperator cGBY =
            CorrelationUtilities.getSingleChild(rsop, GroupByOperator.class);
        if (cGBY != null) {
          if (CorrelationUtilities.hasGroupingSet(rsop) ||
              cGBY.getConf().isGroupingSetsPresent()) {
            // Do not support grouping set right now
            isCorrelated = false;
          }
        }

        if (isCorrelated) {
          LOG.info("Operator " + current.getIdentifier() + " " +
              current.getName() + " is correlated");

          correlatedReduceSinkOperators.add(rsop);
        } else {
          LOG.info("Operator " + current.getIdentifier() + " " +
              current.getName() + " is not correlated");

          for (Operator<? extends OperatorDesc> parent : current.getParentOperators()) {
             correlatedReduceSinkOperators.addAll(
                  findCorrelatedReduceSinkOperators(child, parent, ctx, correlation));
          }
        }
      } else {
        LOG.error("ReduceSinkOperator " + current.getIdentifier() + " does not have ColumnExprMap");
        throw new SemanticException("RelaxCorrelationOptimizer cannot optimize this plan. " +
            "ReduceSinkOperator " + current.getIdentifier()
            + " does not have ColumnExprMap");
      }
      return correlatedReduceSinkOperators;
   }

   private boolean inccludekeyswithID(List<ExprNodeDesc> k1,
      List<ExprNodeDesc> k2, List<Integer> ListID1, List<Integer> ListID2) {
     if(k1.size()==0||k2.size()==0){
       return true;
     }
     List<ExprNodeDesc> tempk1 =new ArrayList<ExprNodeDesc>();
     List<ExprNodeDesc> tempk2=new ArrayList<ExprNodeDesc>();
     List<Integer> tempid1=new  ArrayList<Integer>();
     List<Integer> tempid2=new  ArrayList<Integer>();
     tempk1.addAll(k1);
     tempk2.addAll(k2);
     tempid1.addAll(ListID1);
     tempid2.addAll(ListID2);
     if(k1.size()>k2.size()){
       tempk1.clear();
       tempk2.clear();
       tempid1.clear();
       tempid2.clear();
       tempk1.addAll(k2);
       tempk2.addAll(k1);
       tempid1.addAll(ListID2);
       tempid2.addAll(ListID1);
     }

     for (int i = 0; i < tempk1.size(); i++) {
       ExprNodeDesc expr1 = tempk1.get(i);

       int ID1 = tempid1.get(i);
       if(expr1==null){
         continue;
       }

       boolean flag=false;
       for(int j=0;j<tempk2.size();j++){
         ExprNodeDesc expr2 = tempk2.get(j);
         int ID2 = tempid2.get(j);
         if (expr1.isSame(expr2)) {
           flag=true;
      //     if(ID1!=ID2){
      //       return false;
      //     }
         }
       }
       if(flag==false){
         return false;
       }
     }
     return true;
  }

  @Deprecated
   private LinkedHashSet<ReduceSinkOperator> findCorrelatedReduceSinkOperators(
        Operator<? extends OperatorDesc> child,
        List<ExprNodeDesc> childKeyCols, List<ExprNodeDesc> childPartitionCols,
        String childRSOrder,
        Operator<? extends OperatorDesc> current,
        IntraQueryCorrelation correlation) throws SemanticException {
      LOG.info("TC: now detecting operators (" + child.getIdentifier() + " " + child.getName() + "," +
		current.getIdentifier() + " " + current.getName() + ")");

      LinkedHashSet<ReduceSinkOperator> correlatedReduceSinkOperators =
          new LinkedHashSet<ReduceSinkOperator>();
      if (skipedJoinOperators.contains(current)) {
        LOG.info(current.getName() + " " + current.getIdentifier() +
            " may be converted to MapJoin by " +
            "CommonJoinResolver. Correlation optimizer will not detect correlations" +
            "involved in this operator");
        return correlatedReduceSinkOperators;
      }
      if (current.getParentOperators() == null) {
        return correlatedReduceSinkOperators;
      }
      if (current instanceof PTFOperator) {
        // Currently, we do not support PTF operator.
        LOG.info("Currently, correlation optimizer does not support PTF operator.");
        return correlatedReduceSinkOperators;
      }
      /* bug fixing 20140308
       * 1) The first parameter of findCorrelatedReduceSinkOperators should be "child"
       *    or its ancestors, not "current". Otherwise, we may exploit TC between "current"
       *    and its ancestors and results in the wrong TC set we earlier found in Q18b.
       *
       * 2) Unlike JFCexploit, here "child" is not actually a child op of "current", but
       *    a sibling of "current" or an ancestor of the sibling. Therefore, column names
       *    used in "child" can not be convert to names used in "current" with "backtrack"
       *    method. The method can only back-tracking column names in one op to one of its
       *    ancestor op, such as TS.
       */
      if (current instanceof UnionOperator) {
        // If we get a UnionOperator, right now, we only handle it when
        // we can find correlated ReduceSinkOperators from all inputs.
        LinkedHashSet<ReduceSinkOperator> corrRSs = new LinkedHashSet<ReduceSinkOperator>();
        for (Operator<? extends OperatorDesc> parent : current.getParentOperators()) {
          // bug fixing 20140308 step 1)
          LinkedHashSet<ReduceSinkOperator> tmp =
              findCorrelatedReduceSinkOperators(
                  child/*current*/, childKeyCols, childPartitionCols, childRSOrder, parent, correlation);
          if (tmp != null && tmp.size() > 0) {
            corrRSs.addAll(tmp);
          } else {
            return correlatedReduceSinkOperators;
          }
        }
        correlatedReduceSinkOperators.addAll(corrRSs);
        UnionOperator union = (UnionOperator)current;
        union.getConf().setAllInputsInSameReducer(true);
      } else if (current.getColumnExprMap() == null && !(current instanceof ReduceSinkOperator)) {
        for (Operator<? extends OperatorDesc> parent : current.getParentOperators()) {
          // bug fixing 20140308 step 1)
          correlatedReduceSinkOperators.addAll(
              findCorrelatedReduceSinkOperators(
                  child/*current*/, childKeyCols, childPartitionCols, childRSOrder, parent, correlation));
        }
      } else if (current.getColumnExprMap() != null && !(current instanceof ReduceSinkOperator)) {
        /* bug fixing 20140308 step 2)
         * Converse the column names used in "current" to those used in its parentOps.
         *
         * TODO: Note that it is not the optimal way to fix this problem. The ultimate solution is
         * to alter the traveling order, from top-down to bottom-up.
         */
        /*List<ExprNodeDesc> backtrackedKeyCols =
            ExprNodeDescUtils.backtrack(childKeyCols, child, current);
        List<ExprNodeDesc> backtrackedPartitionCols =
            ExprNodeDescUtils.backtrack(childPartitionCols, child, current);*/

        for (ExprNodeDesc expr: childKeyCols) {
          if (!(expr instanceof ExprNodeColumnDesc)) {
            return correlatedReduceSinkOperators;
          }
        }
        for (Operator<? extends OperatorDesc> parent : current.getParentOperators()) {
            List<ExprNodeDesc> backtrackedKeyCols =
                ExprNodeDescUtils.backtrack(childKeyCols, current, parent);
            List<ExprNodeDesc> backtrackedPartitionCols =
                ExprNodeDescUtils.backtrack(childPartitionCols, current, parent);

            correlatedReduceSinkOperators.addAll(findCorrelatedReduceSinkOperators(
                child/*current*/, backtrackedKeyCols, backtrackedPartitionCols, childRSOrder,
                parent, correlation));
        }
        // end of bug fixing 20140308 step 2)
      } else if (current.getColumnExprMap() != null && current instanceof ReduceSinkOperator) {
        ReduceSinkOperator rsop = (ReduceSinkOperator) current;
        /* bug fixing 20140308 step 2)
         * Unlike the last branch, here we have to backtrack column names of both "current"
         * and "child" to topops, so that we can compare them in their original appearance.
         * In order to do that, we have to look up for their topops set first, and backtrack
         * each of them to their ternimal ops, respectively.
         *
         * TODO: Note that it is not the optimal way to fix this problem. The ultimate solution is
         * to alter the traveling order, from top-down to bottom-up.
         */
        /*List<ExprNodeDesc> backtrackedKeyCols =
            ExprNodeDescUtils.backtrack(childKeyCols, child, current);
        List<ExprNodeDesc> backtrackedPartitionCols =
            ExprNodeDescUtils.backtrack(childPartitionCols, child, current);*/
        List<ExprNodeDesc> rsKeyCols = rsop.getConf().getKeyCols();
        List<ExprNodeDesc> rsPartitionCols = rsop.getConf().getPartitionCols();

        // Two ReduceSinkOperators are correlated means that
        // they have same sorting columns (key columns), same partitioning columns,
        // same sorting orders, and no conflict on the numbers of reducers.
        // TODO: we should relax this condition
        // TODO: we need to handle aggregation functions with distinct keyword. In this case,
        // distinct columns will be added to the key columns.
        boolean isCorrelated = sameKeys(rsKeyCols, childKeyCols/*backtrackedKeyCols*/) &&
            sameOrder(rsop.getConf().getOrder(), childRSOrder) &&
            sameKeys(childPartitionCols/*backtrackedPartitionCols*/, rsPartitionCols) &&
            correlation.adjustNumReducers(rsop.getConf().getNumReducers());

        if (!isCorrelated &&
            //sameOrder(rsop.getConf().getOrder(), childRSOrder) &&
            correlation.adjustNumReducers(rsop.getConf().getNumReducers())) // Too strict?
        {
            // for simplicity, we only considering RS whose single parent is TS
            // TODO: More specific exploting will be added after renewing the whole traveling order
            LOG.info("TC: backtracking to TS if it is possible");
            Operator<?> old_child = child;
            Operator<?> old_current = current;
            List<Operator<?>> child_tops = child.getParentOperators();
			//ExprNodeDescUtils.getTerminalParents(child);  // new method
            List<ExprNodeDesc> backtrackedKeyCols = new ArrayList<ExprNodeDesc>();
            List<ExprNodeDesc> backtrackedPartitionCols =  new ArrayList<ExprNodeDesc>();
            backtrackedKeyCols.addAll(childKeyCols);
            backtrackedPartitionCols.addAll(childPartitionCols);
            while (child_tops != null && child_tops.size() == 1) {
               Operator<?> tsop = child_tops.get(0);
               if (tsop instanceof ReduceSinkOperator) {
                break;
              }
               if (tsop.getColumnExprMap() != null) {
                  backtrackedKeyCols = ExprNodeDescUtils.backtrack
						(backtrackedKeyCols, child, tsop);
                  backtrackedPartitionCols = ExprNodeDescUtils.backtrack
						(backtrackedPartitionCols, child, tsop);
               }
               child = tsop;
               child_tops = tsop.getParentOperators();
            }

            List<Operator<?>> current_tops = current.getParentOperators();
			//ExprNodeDescUtils.getTerminalParents(current);
	    List<ExprNodeDesc> backtrackedKeyCols_1 = new ArrayList<ExprNodeDesc>();
            List<ExprNodeDesc> backtrackedPartitionCols_1 =  new ArrayList<ExprNodeDesc>();
            backtrackedKeyCols_1.addAll(rsKeyCols);
            backtrackedPartitionCols_1.addAll(rsPartitionCols);
            while (current_tops != null && current_tops.size() == 1) {
               Operator<?> tsop = current_tops.get(0);
               if (tsop instanceof ReduceSinkOperator) {
                break;
              }
               if (tsop.getColumnExprMap() != null) {
                  backtrackedKeyCols_1 = ExprNodeDescUtils.backtrack
                                                (backtrackedKeyCols_1, current, tsop);
                  backtrackedPartitionCols_1 = ExprNodeDescUtils.backtrack
                                                (backtrackedPartitionCols_1, current, tsop);
               }
               current = tsop;
               current_tops = tsop.getParentOperators();
            }

            if (!(child instanceof TableScanOperator) ||
                !(current instanceof TableScanOperator) ||
                backtrackedKeyCols.isEmpty() || backtrackedKeyCols.isEmpty()) {
               // TODO
            } else {
               if (backtrackedKeyCols.size() == backtrackedKeyCols_1.size() &&
                  backtrackedPartitionCols.size() == backtrackedPartitionCols_1.size()) {
                  LOG.info("TC: exploit strict TC between " + child.getIdentifier() +
			 " and " + current.getIdentifier());
                  isCorrelated = sameKeys(backtrackedKeyCols, backtrackedKeyCols_1) &&
			sameOrder(rsop.getConf().getOrder(), childRSOrder) &&
                	sameKeys(backtrackedPartitionCols, backtrackedPartitionCols_1);
               } else {
                  // exploit overlapped data
                  LOG.info("TC: exploit overlapped TC between" + child.getIdentifier() +
				" and " + current.getIdentifier());
                  boolean isOverlapped = overlapKeys(backtrackedKeyCols, backtrackedKeyCols_1) &&
                		overlapKeys(backtrackedPartitionCols, backtrackedPartitionCols_1);
                  if (isOverlapped) {
                    LOG.info("TC: has data overlapped !");
                  }
               }
            }
            child = old_child;
            current = old_current;
        }
        // end of bug fixing 20140308 step 2)
        GroupByOperator cGBY =
            CorrelationUtilities.getSingleChild(rsop, GroupByOperator.class);
        if (cGBY != null) {
          if (CorrelationUtilities.hasGroupingSet(rsop) ||
              cGBY.getConf().isGroupingSetsPresent()) {
            // Do not support grouping set right now
            isCorrelated = false;
          }
        }

        if (isCorrelated) {
          LOG.info("Operator " + current.getIdentifier() + " " +
              current.getName() + " is correlated");

            correlatedReduceSinkOperators.add(rsop);

        } else {
          LOG.info("Operator " + current.getIdentifier() + " " +
              current.getName() + " is not correlated");
          /* bug fixing 20140307-3
           * Unlike JFC, here we have to keeep chasing parentOps of the current RS
           * in order to exploit further TC
           */
          //correlatedReduceSinkOperators.clear();
          for (Operator<? extends OperatorDesc> parent : current.getParentOperators()) {
            correlatedReduceSinkOperators.addAll(
                findCorrelatedReduceSinkOperators(
                    child, childKeyCols, childPartitionCols, childRSOrder, parent, correlation));
          }
          // end of bug fixing 20140307-3
        }
      } else {
        LOG.error("ReduceSinkOperator " + current.getIdentifier() + " does not have ColumnExprMap");
        throw new SemanticException("RelaxCorrelationOptimizer cannot optimize this plan. " +
            "ReduceSinkOperator " + current.getIdentifier()
            + " does not have ColumnExprMap");
      }
      return correlatedReduceSinkOperators;
   }
 }

  protected class OrigColumnDesc {
     private final List<ExprNodeDesc> keyCols;
     private final List<ExprNodeDesc> valCols;

     private final List<ExprNodeDesc> partitionCols; // Do we really need this field?
     private int rsLevel;

     public OrigColumnDesc(List<ExprNodeDesc> keyCols, List<ExprNodeDesc> valCols,
				List<ExprNodeDesc> partitionCols) {
        this.keyCols = new ArrayList<ExprNodeDesc>();
        this.valCols = new ArrayList<ExprNodeDesc>();
        this.partitionCols = new ArrayList<ExprNodeDesc>();

        this.keyCols.addAll(keyCols);
        this.valCols.addAll(valCols);
        this.partitionCols.addAll(partitionCols);
        this.rsLevel = 0;
     }

     public List<ExprNodeDesc> keyCols() { return keyCols; }
     public List<ExprNodeDesc> valCols() { return valCols; }
     public List<ExprNodeDesc> partitionCols() { return partitionCols; }

     public void setRsLevel(int level) { rsLevel = level; }
     public int getRsLevel() { return rsLevel; }
  }

  protected class OrigColumnwithVersionDesc {
    private final List<Integer> keyID;

    private final List<Integer> valID;
    private final List<Integer> PID;
    public List<Integer> getKeyID() {
      return keyID;
    }

    public List<Integer> getValID() {
      return valID;
    }

    public List<Integer> getPID() {
      return PID;
    }

    private final List<ExprNodeDesc> keyCols;
    private final List<ExprNodeDesc> valCols;

    private final List<ExprNodeDesc> partitionCols; // Do we really need this field?
    private int rsLevel;

    public OrigColumnwithVersionDesc(List<Integer> keyID,
        List<Integer> valID,
        List<Integer> PID,
        List<ExprNodeDesc> keyCols,
        List<ExprNodeDesc> valCols,
        List<ExprNodeDesc> partitionCols) {

       this.keyID=new ArrayList<Integer>();
       this.valID=new ArrayList<Integer>();
       this.PID=new ArrayList<Integer>();
       this.keyID.addAll(keyID);
       this.valID.addAll(valID);
       this.PID.addAll(PID);


       this.keyCols = new ArrayList<ExprNodeDesc>();
       this.valCols = new ArrayList<ExprNodeDesc>();
       this.partitionCols = new ArrayList<ExprNodeDesc>();

       this.keyCols.addAll(keyCols);
       this.valCols.addAll(valCols);
       this.partitionCols.addAll(partitionCols);

       this.rsLevel = 0;
    }

    public List<ExprNodeDesc> keyCols() { return keyCols; }
    public List<ExprNodeDesc> valCols() { return valCols; }
    public List<ExprNodeDesc> partitionCols() { return partitionCols; }

    public void setRsLevel(int level) { rsLevel = level; }
    public int getRsLevel() { return rsLevel; }
 }


  // node processor used to trace forward column names to specific nodes
  private class OrigColumnNodeProc implements NodeProcessor {
     private void trackOrigColumnNames(ReduceSinkOperator rsop, Operator<? extends OperatorDesc> op,
			OrigColumnNodeProcCtx ctx, List<ExprNodeDesc> keyCols,
			List<ExprNodeDesc> valueCols, List<ExprNodeDesc> partitionCols,
			List<Operator<? extends OperatorDesc>> terminals)
		throws SemanticException {
        LOG.debug("TC_PRE: Searching original column names in "
             			+ op.getIdentifier() + " " + op.getName());

        List<Operator<?>> parents = new ArrayList<Operator<?>>();
        parents.addAll(op.getParentOperators());

        List<Operator<?>> validParents = new ArrayList<Operator<?>>();
        for (Operator<? extends OperatorDesc> parent : parents) {
           if (parent instanceof ReduceSinkOperator) {
            terminals.add(parent);
          }
           if (parent.getColumnExprMap() != null) {
            validParents.add(parent);
          }
        }

        if (!validParents.isEmpty()) {
           LOG.debug("=== Before back tracking:");
           dumpOrigNames(rsop, keyCols, valueCols, partitionCols);

           keyCols = ExprNodeDescUtils.backtrack(keyCols, op, validParents);
           valueCols = ExprNodeDescUtils.backtrack(valueCols, op, validParents);
           partitionCols = ExprNodeDescUtils.backtrack(partitionCols, op, validParents);

           LOG.debug("=== After back tracking:");
           dumpOrigNames(rsop, keyCols, valueCols, partitionCols);
        }
        validParents.clear();

        if (!parents.isEmpty()) {
           if (!terminals.isEmpty()) {
            parents.removeAll(terminals);
          }

           /* Is it possible that some of the acenstors are RSs while others are TSs ?
            */
           if (parents.isEmpty()) {
              LOG.debug("TC_PRE: RS is met. Do in-place conversion and return.");

              int rsLevel = 0;
              for (Operator<? extends OperatorDesc> rs : terminals) {
                 if (!(rs instanceof ReduceSinkOperator)) {
                  continue;
                }

                 LOG.debug("=== Before in-place conversion:");
                 dumpOrigNames(rsop, keyCols, valueCols, partitionCols);

                 ctx.trackOrigColumns((ReduceSinkOperator)rs, keyCols);
                 ctx.trackOrigColumns((ReduceSinkOperator)rs, valueCols);
                 ctx.trackOrigColumns((ReduceSinkOperator)rs, partitionCols);

                 if (ctx.rsColDesc((ReduceSinkOperator)rs).getRsLevel() > rsLevel) {
                  rsLevel = ctx.rsColDesc((ReduceSinkOperator)rs).getRsLevel();
                }

                 LOG.debug("=== After in-place conversion:");
                 dumpOrigNames(rsop, keyCols, valueCols, partitionCols);
              }

              ctx.addRsToOrigColumns(rsop, keyCols, valueCols, partitionCols);

              OrigColumnDesc orig = ctx.rsColDesc(rsop);
              orig.setRsLevel(rsLevel+1);

              LOG.debug("*** Store original column names for "
				+ rsop.getName() + " " + rsop.getIdentifier()
                                + " (RSLevel: " + orig.getRsLevel() + ")");
              dumpOrigNames(rsop, orig.keyCols(), orig.valCols(), orig.partitionCols());
              return;
           } else {
              for (Operator<? extends OperatorDesc> parent : parents) {
                 if (parent instanceof TableScanOperator)
                 {
                  validParents.add(parent); // A more reasoable var name is needed here
                }
              }
              parents.removeAll(validParents);

              if (parents.isEmpty()) {
                 LOG.debug("TC_PRE: TS is met. Stop tracking and return.");


                 ctx.addRsToOrigColumns(rsop, keyCols, valueCols, partitionCols);
                 //ctx.addRsToOrigColumnswithVersion(op, keyCols, valueCols, partitionCols, versionID)

                 LOG.debug("*** Store original column names for "
                        	+ op.getName() + " " + op.getIdentifier());
                 OrigColumnDesc orig = ctx.rsColDesc(rsop);
                 dumpOrigNames(rsop, orig.keyCols(), orig.valCols(), orig.partitionCols());
                 return;
              } else {
                 for (Operator<? extends OperatorDesc> parent : parents) {
                  trackOrigColumnNames(rsop, parent, ctx,
keyCols, valueCols, partitionCols, terminals);
                }
              }
           }
        } else {
           LOG.info("TC_PRE: we should never reach here!");
        }
     }



     private void dumpOrigNames(Operator<? extends OperatorDesc> op, List<ExprNodeDesc> keyCols,
		List<ExprNodeDesc> valueCols, List<ExprNodeDesc> partitionCols) {
        LOG.debug("Key Columns for " + op.getName() + " " + op.getIdentifier());
        for (ExprNodeDesc expr : keyCols) {
           if (expr instanceof ExprNodeColumnDesc) {
            LOG.debug("-- " + ((ExprNodeColumnDesc)expr).getColumn());
          } else {
            LOG.debug("-- <non-col>");
          }
        }
        LOG.debug("Partition Columns for " + op.getName() + " " + op.getIdentifier());
        for (ExprNodeDesc expr : partitionCols) {
           if (expr instanceof ExprNodeColumnDesc) {
            LOG.debug("-- " + ((ExprNodeColumnDesc)expr).getColumn());
          } else {
            LOG.debug("-- <non-col>");
          }
        }
        LOG.debug("Value Columns for " + op.getName() + " " + op.getIdentifier());
        for (ExprNodeDesc expr : valueCols) {
           if (expr instanceof ExprNodeColumnDesc) {
            LOG.debug("-- " + ((ExprNodeColumnDesc)expr).getColumn());
          } else {
            LOG.debug("-- <non-col>");
          }
        }
     }

     @Override
     public Object process(Node nd, Stack<Node> stack, NodeProcessorCtx ctx,
				Object... nodeOutputs) throws SemanticException {
       OrigColumnNodeProcCtx corrCtx = (OrigColumnNodeProcCtx) ctx;
       Operator<? extends OperatorDesc> op = (Operator<? extends OperatorDesc>) nd;

       if(!(op instanceof ReduceSinkOperator)) {
        throw new SemanticException("OPerator " + op.getIdentifier() + " is not a RS");
      }

       ReduceSinkOperator rsop = (ReduceSinkOperator)op;
       if (corrCtx.isWalked(rsop)) {
        throw new SemanticException("OPerator " + op.getIdentifier() + " has already been visited");
      }

       LOG.debug("TC_PRE: Walk to operator " + op.getIdentifier() + " " + op.getName());
       corrCtx.addWalked(rsop);

       List<ExprNodeDesc> keyCols = new ArrayList<ExprNodeDesc>();
       List<ExprNodeDesc> valueCols = new ArrayList<ExprNodeDesc>();
       List<ExprNodeDesc> partitionCols = new ArrayList<ExprNodeDesc>();

       keyCols.addAll(rsop.getConf().getKeyCols());
       valueCols.addAll(rsop.getConf().getValueCols());
       partitionCols.addAll(rsop.getConf().getPartitionCols());


    //  List<Operator<? extends OperatorDesc>> terminals =
		//		new ArrayList<Operator<? extends OperatorDesc>>();
       trackOrigColumnNameswithVersion(rsop, rsop, corrCtx,keyCols,
           valueCols, partitionCols);

       return rsop;
     }
    private void trackOrigColumnNameswithVersion(ReduceSinkOperator rsop, Operator<? extends OperatorDesc> op,
        OrigColumnNodeProcCtx ctx, List<ExprNodeDesc> keyCols,
        List<ExprNodeDesc> valueCols,
        List<ExprNodeDesc> partitionCols)
       // List<Operator<? extends OperatorDesc>> terminals)
        throws SemanticException {
            LOG.debug("TC_PRE: Searching the vsersion ID of each column names in "
                      + op.getIdentifier() + " " + op.getName());

            List<Operator<?>> parents = new ArrayList<Operator<?>>();
            if(op instanceof UnionOperator){
              assert op.getParentOperators().size() > 1;
              parents.add(op.getParentOperators().get(0));
            }else{
              parents.addAll(op.getParentOperators());
            }


        //    boolean ISRSParent=false;
        //    int countrs=0;
            List<Operator<? extends OperatorDesc>> terminals =
                new ArrayList<Operator<? extends OperatorDesc>>();


            List<Operator<?>> validParents = new ArrayList<Operator<?>>();
            for (Operator<? extends OperatorDesc> parent : parents) {
               if (parent instanceof ReduceSinkOperator) {
                terminals.add(parent);
              }
               if (parent.getColumnExprMap() != null) {
                validParents.add(parent);
              }
            }

        //    if(countrs>=2 &&countrs==parents.size()){
        //      ISRSParent=true;
       //     }

            MultiParseContext multipctx=(MultiParseContext)ctx.getPctx();
            InterQueryFlowCtx IQctx= multipctx.getQueryFlowCtx();
            List<ExprNodeDesc> listexprrs;


            List<Integer> kIDlist=new ArrayList<Integer>();
            List<Integer> vIDlist=new ArrayList<Integer>();
            List<Integer> pIDlist=new ArrayList<Integer>();

            if (!validParents.isEmpty()) {

                LOG.debug("=== Before back tracking:");
                dumpOrigNames(rsop, keyCols, valueCols, partitionCols);
                keyCols = ExprNodeDescUtils.backtrack(keyCols, op, validParents);
                valueCols = ExprNodeDescUtils.backtrack(valueCols, op, validParents);
                partitionCols= ExprNodeDescUtils.backtrack(partitionCols, op, validParents);
                LOG.debug("=== After back tracking:");
                dumpOrigNames(rsop, keyCols, valueCols, partitionCols);
            }
            validParents.clear();


            if (!parents.isEmpty()) {
               if (!terminals.isEmpty()) {
                parents.removeAll(terminals);
              }

               /* Is it possible that some of the acenstors are RSs while others are TSs ?
                */
               if (parents.isEmpty()) {
                  LOG.debug("TC_PRE: RS is met. Do in-place conversion and return.");

                  int rsLevel = 0;

                  for (Operator<? extends OperatorDesc> rs : terminals) {
                     if (!(rs instanceof ReduceSinkOperator)) {
                      continue;
                    }

                     LOG.debug("=== Before in-place conversion:");
                     dumpOrigNames(rsop, keyCols, valueCols, partitionCols);

                   //  ctx.trackOrigColumns(op, cols,listrs,ID);
                     for(int j=0;j<keyCols.size();j++){
                       kIDlist.add(-1);
                     }
                     for(int j=0;j<valueCols.size();j++){
                       vIDlist.add(-1);
                     }
                     for(int j=0;j<partitionCols.size();j++){
                       pIDlist.add(-1);
                     }


                     ctx.trackOrigColumnswithversion((ReduceSinkOperator)rs, keyCols,kIDlist);
                     ctx.trackOrigColumnswithversion((ReduceSinkOperator)rs, valueCols,vIDlist);
                     ctx.trackOrigColumnswithversion((ReduceSinkOperator)rs, partitionCols,pIDlist);


                     if (ctx.rsColwithIDDesc((ReduceSinkOperator)rs).getRsLevel() > rsLevel) {
                      rsLevel = ctx.rsColwithIDDesc((ReduceSinkOperator)rs).getRsLevel();
                    }

                     LOG.debug("=== After in-place conversion:");
                     dumpOrigNames(rsop, keyCols, valueCols, partitionCols);
                  }

                  ctx.addRsToOrigColumnswithVersion(rsop, keyCols, valueCols, partitionCols,kIDlist,vIDlist,pIDlist);

                  //OrigColumnwithVersionDesc
                  OrigColumnwithVersionDesc orig = ctx.rsColwithIDDesc(rsop);
                  orig.setRsLevel(rsLevel+1);

                  LOG.debug("*** Store original column names and globe version ID for "
            + rsop.getName() + " " + rsop.getIdentifier()
                                    + " (RSLevel: " + orig.getRsLevel() + ")");
                  dumpOrigNames(rsop, orig.keyCols(), orig.valCols(), orig.partitionCols());
                  return;
               } else {
                  for (Operator<? extends OperatorDesc> parent : parents) {
                     if (parent instanceof TableScanOperator)
                     {
                      validParents.add(parent); // A more reasoable var name is needed here
                    }
                  }
                  parents.removeAll(validParents);

                  if (parents.isEmpty()) {
                     LOG.debug("TC_PRE: TS is met. Stop tracking and return.");

                     for (Operator<? extends OperatorDesc> parent : validParents) {
                       assert (parent instanceof TableScanOperator);
                       int ID= IQctx.getTStoGID((TableScanOperator)parent);


                       for(int j=0;j<keyCols.size();j++){
                         kIDlist.add(ID);
                       }
                       for(int j=0;j<valueCols.size();j++){
                         vIDlist.add(ID);
                       }
                       for(int j=0;j<partitionCols.size();j++){
                         pIDlist.add(ID);
                       }
                       ctx.addRsToOrigColumnswithVersion(rsop, keyCols, valueCols, partitionCols,kIDlist,vIDlist,pIDlist);

                       LOG.debug("*** Store original column names and globe version ID for "
                                + parent.getName() + " " + parent.getIdentifier());
                       //OrigColumnwithVersionDesc
                       OrigColumnwithVersionDesc orig = ctx.rsColwithIDDesc(rsop);
                       dumpOrigNames(rsop, orig.keyCols(), orig.valCols(), orig.partitionCols());
                       return;

                    }


                  } else {
                     for (Operator<? extends OperatorDesc> parent : parents) {
                       trackOrigColumnNameswithVersion(rsop, parent, ctx,
    keyCols, valueCols, partitionCols);
                    }
                  }
               }
            } else {
               LOG.debug("TC_PRE: we should never reach here!");
            }

  }
 }
  private NodeProcessor getDefaultOrigProc() {
    return new NodeProcessor() {
      @Override
      public Object process(Node nd, Stack<Node> stack,
          NodeProcessorCtx ctx, Object... nodeOutputs) throws SemanticException {
        Operator<? extends OperatorDesc> op = (Operator<? extends OperatorDesc>) nd;
        LOG.debug("Walk to operator " + op.getIdentifier() + " "
            + op.getName() + ". No actual work to do");
        OrigColumnNodeProcCtx correlationCtx = (OrigColumnNodeProcCtx) ctx;
        if (op.getName().equals(MapJoinOperator.getOperatorName())) {
          correlationCtx.setAbort(true);
          correlationCtx.getAbortReasons().add("Found MAPJOIN");
        }
        return null;
      }
    };
  }

  protected class OrigColumnNodeProcCtx extends AbstractCorrelationProcCtx {

    private boolean abort;
    private final List<String> abortReasons;

    private final Set<ReduceSinkOperator> walked;

    private final List<IntraQueryCorrelation> correlations;

    private int fileSinkOperatorCount;

    private final HashMap<Node, OrigColumnDesc> rsToOrigNames;
    private final HashMap<Node, OrigColumnwithVersionDesc> rsToOrigNameswithversion;

    public HashMap<Node, OrigColumnwithVersionDesc> getRsToOrigNameswithversion() {
      return rsToOrigNameswithversion;
    }

    public OrigColumnNodeProcCtx(ParseContext pctx) {
      super(pctx);
      walked = new HashSet<ReduceSinkOperator>();
      correlations = new ArrayList<IntraQueryCorrelation>();
      rsToOrigNames = new HashMap<Node, OrigColumnDesc>();
      rsToOrigNameswithversion= new HashMap<Node, OrigColumnwithVersionDesc>();
      abort = false;
      abortReasons = new ArrayList<String>();
      fileSinkOperatorCount = 0;
    }

    public void setAbort(boolean abort) {
      this.abort = abort;
    }

    public boolean isAbort() {
      return abort;
    }

    public List<String> getAbortReasons() {
      return abortReasons;
    }

    public void addCorrelation(IntraQueryCorrelation correlation) {
      correlations.add(correlation);
    }

    public List<IntraQueryCorrelation> getCorrelations() {
      return correlations;
    }

    public boolean isWalked(ReduceSinkOperator op) {
      return walked.contains(op);
    }

    public void addWalked(ReduceSinkOperator op) {
      walked.add(op);
    }

    public void addWalkedAll(Collection<ReduceSinkOperator> c) {
      walked.addAll(c);
    }

    public void removeWalked(ReduceSinkOperator op) {
      walked.remove(op);
    }

    public void removeWalkedAll(Collection<ReduceSinkOperator> c) {
      walked.removeAll(c);
    }

    public void removeWalkedAll() {
      walked.clear();
    }

    public void incrementFileSinkOperatorCount() {
      fileSinkOperatorCount++;
      if (fileSinkOperatorCount == 2) {
        abort = true;
        abortReasons.add(
            "-- Currently, a query with multiple FileSinkOperators are not supported.");
      }
    }

    public void addRsToOrigColumns(ReduceSinkOperator op, List<ExprNodeDesc> keyCols,
			List<ExprNodeDesc> valCols, List<ExprNodeDesc> partitionCols) {
       if(rsToOrigNames.get(op) != null) {
          abort = true;
          abortReasons.add("-- Operator " + op.getIdentifier() + "already exists in Corr_TC.");
          return;
       }
       rsToOrigNames.put(op, new OrigColumnDesc(keyCols, valCols, partitionCols));
    }


    public void addRsToOrigColumnswithVersion(ReduceSinkOperator op,
        List<ExprNodeDesc> keyCols,
        List<ExprNodeDesc> valCols,
        List<ExprNodeDesc> partitionCols,
        List<Integer> keyColsID,
        List<Integer>  valueColsID,
        List<Integer> partitionColsID
        ){

      if(rsToOrigNameswithversion.get(op) != null) {
        /*
        if(op.getParentOperators().size()==1){
          if(op.getParentOperators().get(0) instanceof UnionOperator){
            return;
          }
        }*/
        abort = true;
        abortReasons.add("-- Operator " + op.getIdentifier() + "already exists in Corr_TC.");
        return;
     }

      rsToOrigNameswithversion.put(op,
          new OrigColumnwithVersionDesc(
              keyColsID, valueColsID,
              partitionColsID,keyCols,
              valCols,partitionCols));

    }
    /*
    public void addRsToOrigColumnswithVersion(ReduceSinkOperator op,
        List<ExprNodeDesc> keyCols,
        List<ExprNodeDesc> valCols,
        List<ExprNodeDesc> partitionCols,
        List<Operator<? extends OperatorDesc>> keyColsRS,
        List<Operator<? extends OperatorDesc>> valueColsRS,
        List<Operator<? extends OperatorDesc>> partitionColsRS
        ){
      List<Pair<Integer, ExprNodeDesc>>newkeyColsRS=new ArrayList<Pair<Integer, ExprNodeDesc>>();
      List<Pair<Integer, ExprNodeDesc>>newvalueColsRS=new ArrayList<Pair<Integer, ExprNodeDesc>>();
      List<Pair<Integer, ExprNodeDesc>>newpartitionColsRS=new ArrayList<Pair<Integer, ExprNodeDesc>>();

      assert keyCols.size()!=keyColsRS.size();
      assert valCols.size()!=valueColsRS.size();
      assert partitionCols.size()!=partitionColsRS.size();
      int i=0;
      for(Operator<? extends OperatorDesc> oprs: keyColsRS){
        newkeyColsRS.add(
            new Pair(rsToOrigNameswithversion.get(oprs).getkeyColswithID().get(i).getKey(),
                keyCols.get(i)));
        i++;
      }
      i=0;
      for(Operator<? extends OperatorDesc> oprs: valueColsRS){
        newvalueColsRS.add(
            new Pair(rsToOrigNameswithversion.get(oprs).getvalColswithID().get(i).getKey(),
                valCols.get(i)));
        i++;
      }
      i=0;
      for(Operator<? extends OperatorDesc> oprs: partitionColsRS){
        newpartitionColsRS.add(
            new Pair(rsToOrigNameswithversion.get(oprs).getpartitionColswithID().get(i).getKey(),
                partitionCols.get(i)));
        i++;
      }


      rsToOrigNameswithversion.put(op,
          new OrigColumnwithVersionDesc(
              newkeyColsRS, newvalueColsRS,
              newpartitionColsRS,keyCols,
              valCols,partitionCols));


    }*/
    public void initialID(List<Pair<Integer, ExprNodeDesc>> keyColswithID, List<ExprNodeDesc> keyCols,
        int ID){
      for(ExprNodeDesc key:keyCols){
        keyColswithID.add(new Pair(ID,key));
      }
    }


    public List<ExprNodeDesc> trackOrigColumns(ReduceSinkOperator op, List<ExprNodeDesc> cols) {
       OrigColumnDesc orig = rsToOrigNames.get(op);

       if (orig == null) {
          abort = true;
          abortReasons.add("-- Operator " + op.getIdentifier() + "doesn't exist in Corr_TC.");
          return cols;
       }

       List<ExprNodeDesc> origKeyCols = orig.keyCols();
       List<ExprNodeDesc> origValueCols = orig.valCols();
       List<ExprNodeDesc> rsKeyCols = op.getConf().getKeyCols();
       List<ExprNodeDesc> rsValueCols = op.getConf().getValueCols();

       int index = -1;
       for (ExprNodeDesc expr : cols) {
          index++;
          ExprNodeDesc origExpr = ExprNodeDescUtils.replace(expr, rsKeyCols, origKeyCols);
          if (origExpr == null || origExpr.isSame(expr)) {
            origExpr = ExprNodeDescUtils.replace(expr, rsValueCols, origValueCols);
          }
          if (origExpr != null && !origExpr.isSame(expr)) {
            cols.set(index, origExpr);
          }
       }

       return cols;
    }

    public List<ExprNodeDesc> trackOrigColumnswithversion(ReduceSinkOperator op, List<ExprNodeDesc> cols,
        List<Integer> IDlist
       ) {
      OrigColumnwithVersionDesc origver=rsToOrigNameswithversion.get(op);

      if (origver == null) {
         abort = true;
         abortReasons.add("-- Operator " + op.getIdentifier() + "doesn't exist in Corr_TC.");
         return cols;
      }

      List<ExprNodeDesc> origKeyCols = origver.keyCols();
      List<ExprNodeDesc> origValueCols = origver.valCols();
      List<Integer> keyID =origver.getKeyID();
      List<Integer> ValueID=origver.getValID();
      List<ExprNodeDesc> rsKeyCols = op.getConf().getKeyCols();
      List<ExprNodeDesc> rsValueCols = op.getConf().getValueCols();

      int index = -1;
      for (ExprNodeDesc expr : cols) {
         index++;
         ExprNodeDesc origExpr = ExprNodeDescUtils.replace(expr, rsKeyCols, origKeyCols);
         if(origExpr!=null){
           int j=0;
           for(ExprNodeDesc e :origKeyCols){
             if(origExpr.isSame(e)){
               IDlist.set(index, keyID.get(j));
             }
             j++;
           }
         }
         if (origExpr == null || origExpr.isSame(expr)) {
           origExpr = ExprNodeDescUtils.replace(expr, rsValueCols, origValueCols);

           if(origExpr!=null){
             int j=0;
             for(ExprNodeDesc e :origValueCols){
               if(origExpr.isSame(e)){
                 IDlist.set(index, ValueID.get(j));
               }
               j++;
             }
           }

         }
         if (origExpr != null && !origExpr.isSame(expr)) {
           cols.set(index, origExpr);
         }

      }

      return cols;
   }



    OrigColumnDesc rsColDesc(ReduceSinkOperator op) {
       return rsToOrigNames.get(op);
    }

    OrigColumnwithVersionDesc rsColwithIDDesc(ReduceSinkOperator op) {
      return   rsToOrigNameswithversion.get(op);
   }



  }
  public void initialID(List<Pair<Integer, ExprNodeDesc>> keyColswithID, Integer versionID) {
    // TODO Auto-generated method stub

  }

}
