package org.apache.hadoop.hive.ql.parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hive.ql.exec.FileSinkOperator;
import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.parse.CommonSubtreeDetect.SubOpTree;
import org.apache.hadoop.hive.ql.parse.InterQueryFlowCtx.Tuple;

public class CommonSubtreeReuse {
  static final private Log LOG = LogFactory.getLog(CommonSubtreeReuse.class.getName());
  private final HashMap<Integer, ParseContext> pctxs;
  private final MultiParseContext multiPctx;
  private final HashMap<String ,  List<SubOpTree>> qIDToCommonSubtrees;
  // Last node of each Optree
  private final HashMap<Integer , Operator<?>> rootNodes;

  private HashMap<Integer, List<Integer>> sameQueryMap;
  private HashMap<Integer, List<Integer>> transitiveQueryMap;
  private Set<Tuple<Integer,Integer>> partSameQuerySet;

  public CommonSubtreeReuse( HashMap<Integer, ParseContext> pctxs, MultiParseContext multiPctx){
    this.rootNodes = new HashMap<Integer, Operator<?>> ();
    this.qIDToCommonSubtrees = new HashMap<String, List<SubOpTree>>();
    this.pctxs = pctxs;
    this.multiPctx = multiPctx;
  }

  public void subtreeReuse(){
    InterQueryFlowCtx queryFlowCtx = multiPctx.getQueryFlowCtx();
    sameQueryMap = queryFlowCtx.getSameQueryMap();
    transitiveQueryMap = queryFlowCtx.getTransitiveQueryMap();
    partSameQuerySet = queryFlowCtx.getContainSameQuerySet();
    CSTreeOptimization();
  }

  private boolean updateFlowCtx() {
    if (qIDToCommonSubtrees.isEmpty()) {
      return false;
    }

    boolean update = false;
    InterQueryFlowCtx flowCtx = multiPctx.getQueryFlowCtx();
    HashMap<Integer, Integer> verMap = flowCtx.getCsqSameVerMap();

    LOG.info("Renew inter-query flow dependencies after CSQ OPT");
    for(String key : qIDToCommonSubtrees.keySet()) {
      for (SubOpTree cst : qIDToCommonSubtrees.get(key)) {
        // child operator List of the CST root node should never be null
        boolean up = true;
        for (Operator<?> op : cst.getRootNode().getChildOperators()) {
          if (! (op instanceof FileSinkOperator)) {
            up = false;
            break;
          }
        }

        if (up) {
          // CST OPT make all child operators of the cst root shared the same data
          // (Although the data might be stored under different directories)
          List<Operator<?>> shareList = cst.getRootNode().getChildOperators();
          FileSinkOperator keyOp = (FileSinkOperator)(shareList.get(0));

          if (flowCtx.getOpToTabWr().get(keyOp) != null) {
            // fsOps of select queries can not be found in opToTabWr map
            Integer ver = flowCtx.getOpToTabWr().get(keyOp).getKey();
            for (Operator<?> op : cst.getRootNode().getChildOperators()) {
              if (!op.equals(keyOp)) {
                Integer shareVer = flowCtx.getOpToTabWr().get(op).getKey();
                if (verMap.get(shareVer) == null) {
                  // maintain map from keyOp to shallowOp
                   LOG.info("data sharing introduced by recent CSQ OPT: " + shareVer
                       + " --> " + ver);
                   verMap.put(shareVer, ver);
                   update = true;
                }
              }
            }
          }
        }
      }
    }

    return update;
  }

  public void sameQueryOptimization(){
    // Try to merge each query in the sameQueryMap to its "key" query
    Iterator<Integer> iter = sameQueryMap.keySet().iterator();
    while (iter.hasNext()) {
      int keyId = iter.next();

      ArrayList<Integer> qIDs = new  ArrayList<Integer>();
      qIDs.add(keyId);
      qIDs.addAll(sameQueryMap.get(keyId));
      for(int queryId : qIDs){
        System.out.println(">>> Deal with candidates for CSQ: query_" + queryId + ", root "+
            Operator.getLastOp(pctxs.get(queryId).getTopOps().values()).toString());
        rootNodes.put(queryId , Operator.getLastOp(pctxs.get(queryId).getTopOps().values()));
      }
      String queryIds = createQueryID(qIDs);

      for( int i = 1; i < qIDs.size(); i++){
        CommonSubtreeDetect cstd = new CommonSubtreeDetect(rootNodes.get(qIDs.get(0)) ,
            rootNodes.get(qIDs.get(i)),multiPctx);
        List<SubOpTree> cstList  = cstd.genCSTree(cstd.getCSTree());
        if (cstList != null && qIDToCommonSubtrees.get(queryIds) != null) {
          qIDToCommonSubtrees.get(queryIds).addAll(cstList);
        } else {
          qIDToCommonSubtrees.put(queryIds , cstList);
        }
        // TODO: handle conflicts brought to the "key" query by CSTree reusing
        if (!cstd.reuseCSTree(cstList)) {
          System.out.println("CSQ Failed. Processing Abort !");
          System.exit(1);
        }
      }
    }

    // At this moment, all CSTrees stored in the qIDToCommonSubtrees field are merged
    // Dump them first
    for(String key : qIDToCommonSubtrees.keySet()){
      System.out.println("***************qIDToCommonSubtrees " + key  );
      for( SubOpTree opt : qIDToCommonSubtrees.get(key)){
        System.out.println("\t\t******* " + opt.toString() );
      }
    }
  }

  public void transitiveQueryOptimization(){
    InterQueryFlowCtx flowCtx = multiPctx.getQueryFlowCtx();
    HashMap<Integer, Integer> verMap = flowCtx.getCsqSameVerMap();

    Iterator<Integer> iter = transitiveQueryMap.keySet().iterator();
    while (iter.hasNext()) {
      int keyId = iter.next();
      if (rootNodes.get(keyId) != null) {
        // The set of queries has already been processed
        continue;
      }

      /* Check if there is transitive equality exists among "key"
       * query and each of its "map" queries
       * TODO: look for equality among "map" queries
       */
      ArrayList<Integer> qIDs = new  ArrayList<Integer>();
      Set<Integer> keyVerSet = flowCtx.getQueryVerSetMap(keyId);
      qIDs.add(keyId);
      for (int mapId : transitiveQueryMap.get(keyId)) {
        // Check verSet of the query
        Set<Integer> mapVerSet = new HashSet<Integer>();
        for (int ver : flowCtx.getQueryVerSetMap(mapId)) {
          if (verMap.get(ver) == null) {
            mapVerSet.add(ver);
          } else {
            mapVerSet.add(verMap.get(ver));
          }
        }
        if (mapVerSet.equals(keyVerSet)) {
          qIDs.add(mapId);
        }
      }
      if (qIDs.size() <= 1) {
        continue;
      }

      for(int queryId : qIDs){
        System.out.println(">>> Deal with TRANSITIVE candidates for CSQ: query_" +
            queryId + ", root "+
            Operator.getLastOp(pctxs.get(queryId).getTopOps().values()).toString());
        rootNodes.put(queryId , Operator.getLastOp(pctxs.get(queryId).getTopOps().values()));
      }
      String queryIds = createQueryID(qIDs);

      for( int i = 1; i < qIDs.size(); i++){
        CommonSubtreeDetect cstd = new CommonSubtreeDetect(rootNodes.get(qIDs.get(0)) ,
            rootNodes.get(qIDs.get(i)),multiPctx);
        /* No extra work is needed here as the getCSTree method will automatically
         * adjust the result of operator comparison in order to make sure TS operators
         * which share the same dataSet are considered as sameOps
         */
        List<SubOpTree> cstList  = cstd.genCSTree(cstd.getCSTree());
        if (cstList != null && qIDToCommonSubtrees.get(queryIds) != null) {
          qIDToCommonSubtrees.get(queryIds).addAll(cstList);
        } else {
          qIDToCommonSubtrees.put(queryIds , cstList);
        }
        // TODO: handle conflicts brought to the "key" query by CSTree reusing
        if (!cstd.reuseCSTree(cstList)) {
          System.out.println("CSQ Failed. Processing Abort !");
          System.exit(1);
        }
      }
    }
  }

  public void CSTreeOptimization() {
    // handle queries from the sameQueryMap first
    sameQueryOptimization();

    // update flow dependencies after processing "same" queries
    while(updateFlowCtx()) {
      // clear all CSQs found in previous iteration so that only
      // newly found CSQs will be handled per iter
      qIDToCommonSubtrees.clear();

      // TODO: process queries from the transitiveQueryMap recursively
      // Note that here we should never clear the rootNodes field to
      // prevent handling the same query sets repeatedly
      transitiveQueryOptimization();

      for(String key : qIDToCommonSubtrees.keySet()){
        System.out.println("***************qIDToTranstiveCommonSubtrees " + key  );
        for( SubOpTree opt : qIDToCommonSubtrees.get(key)){
          System.out.println(opt.toString() );
        }
      }
    }
  }


  private String createQueryID( int... IDs){
    StringBuilder queryid = new StringBuilder();
    queryid.append("queryid");
    for(int ID : IDs){
      queryid.append("_"+ID);
    }
    return  queryid.toString();
  }

  private String createQueryID( ArrayList<Integer> IDs ){
    StringBuilder queryid = new StringBuilder();
    queryid.append("queryid");
    for(int ID : IDs){
      queryid.append("_"+ID);
    }
    return  queryid.toString();
  }


  private String combineQueryID( String queryId , int... IDs){
    StringBuilder queryid = new StringBuilder();
    for(int ID : IDs){
      queryid.append("_"+ID);
    }
    return  queryid.toString();
  }

  private ArrayList<Integer> sameGroupQueryID( String queryID ){
    ArrayList<Integer> queryIds = new  ArrayList<Integer>( );
     String[] qids = queryID.split("_");
     for(int i = 0 ; i < qids.length ; i++){
       if( i > 0 ){
         queryIds.add( Integer.parseInt(qids[i]));
       }
     }
    return queryIds;
  }

}
