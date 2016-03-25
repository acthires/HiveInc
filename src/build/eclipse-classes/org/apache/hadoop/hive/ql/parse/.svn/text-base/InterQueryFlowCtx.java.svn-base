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
package org.apache.hadoop.hive.ql.parse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.ql.Driver;
import org.apache.hadoop.hive.ql.exec.FileSinkOperator;
import org.apache.hadoop.hive.ql.exec.GroupByOperator;
import org.apache.hadoop.hive.ql.exec.JoinOperator;
import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.exec.TableScanOperator;
import org.apache.hadoop.hive.ql.plan.OperatorDesc;



/**
 * Inter-query flow analysis
 * Build SSA Structure for usage and definitions of dataSet among queries
 */
public class InterQueryFlowCtx {

  static final private Log LOG = LogFactory.getLog(Driver.class.getName());

  private final HiveConf hiveConf;
  private final HashMap<Integer, ParseContext> multiPctx;
  /**
   * GlobalVersionID, i.e. GID: integer --> TabID, LocalVersionId: Pair<String, Integer>
   */
  private final LinkedHashMap<Integer ,Tuple<String ,Integer>> tabVersion ;
  /**
   * GID: integer --> Operator which define the version: FileSinkOperator
   * TO BE EXTENDED: the partitions or columns defined by that operator
   */
  private final HashMap<Integer, FileSinkOperator> tabVerWrDesc;
  /**
   * GID: integer -->
   *        Operators which use the version: List<TableScanOperator>
   * TO BE EXTENDED: the partitions or columns uses by each of the operators
   */
  private final HashMap<Integer, List<TableScanOperator>> tabVerRdDesc;
  /**
   *  operator: TableScanOperator --> GID and TableID used by it: Pair<integer, integer>
   */
  private final HashMap<TableScanOperator, Tuple<Integer, String>> opToTabRd;
  /**
   *   operator: FileSinkOperator --> GID and TableID used by it: Pair<integer, integer>
   */
  private final HashMap<FileSinkOperator, Tuple<Integer, String>> opToTabWr;
  /**
   *   Tab ID (Name): String --> historical GIDs: List<integer>
   */
  private final HashMap<String, List<Integer>> tabIdToVer;
  // TO BE EXTENDED
  private final HashMap<String, List<Table>> tabMap;

  /*---- following fields are declared for CommonSubQuery Optimization -----*/
  // statistics info for each query
  private final LinkedHashMap<Integer, Set<Integer>> queryVerSetMap;
  private final HashMap<Integer , Integer> queryJoinMap;
  private final HashMap<Integer , Integer> queryAggrMap;

  // candidates for CSQ OPT
  private final HashMap<Integer, List<Integer>> sameQueryMap;
  // transitive candidates when CSQ OPT is performed on sameQuerySet
  private final HashMap<Integer, List<Integer>> transitiveQueryMap;
  // TO BE USED
  private final Set<Tuple< Integer ,Integer>> containSameQuerySet;
  // queries which are not processed by CSQ OPT
  private final Set<Integer> normalQuerySet;
  // versions which share the same dataSet after CSQ OPT
  private HashMap<Integer, Integer> csqSameVerMap;

  public InterQueryFlowCtx(HiveConf conf, HashMap<Integer, ParseContext> ctx){
    hiveConf = conf;
    multiPctx = ctx;

    tabVersion = new LinkedHashMap<Integer ,Tuple<String, Integer>>();
    opToTabRd = new HashMap<TableScanOperator, Tuple<Integer, String>>();
    opToTabWr= new HashMap<FileSinkOperator, Tuple<Integer, String>>();
    tabVerWrDesc = new HashMap<Integer, FileSinkOperator>();
    tabVerRdDesc = new HashMap<Integer, List<TableScanOperator>>();
    tabIdToVer = new HashMap<String, List<Integer>>();
    tabMap = new HashMap<String, List<Table>>();

    queryVerSetMap = new LinkedHashMap<Integer, Set<Integer>>();
    queryJoinMap = new  HashMap<Integer , Integer>();
    queryAggrMap = new  HashMap<Integer , Integer>();
    sameQueryMap = new HashMap<Integer, List<Integer>>();
    transitiveQueryMap = new  HashMap<Integer, List<Integer>>();
    containSameQuerySet = new  HashSet<Tuple< Integer ,Integer>>();
    normalQuerySet = new  HashSet<Integer>();
    csqSameVerMap = new HashMap<Integer, Integer>();
  }

  /**
   * Tuple. implement data structure to store one pair value
   *
   * @param <T>
   * @param <V>
   */
  public class Tuple<T,V>{
    private  T key;
    private  V value;

    public Tuple(T key ,V value){
      this.key = key;
      this.value = value;
    }

    public T getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }

    public void setKey(T key) {
      this.key = key;
    }

    public void setValue(V value) {
      this.value = value;
    }
  }


  private class Table{
    private final String tableName;  // tabID
    private List<FieldSchema> columns;
    private List<FieldSchema> partitionKeys;

    public Table(String tableName){
      this.tableName = tableName;
    }

    public Table(String tableName, List<FieldSchema> columns,
        List<FieldSchema> partitionKeys) {
      this.tableName = tableName;
      this.columns = columns;
      this.partitionKeys = partitionKeys;
    }

    public String getTableName() {
      return tableName;
    }

    public List<FieldSchema> getColumns() {
      return columns;
    }

    public void setColumns(List<FieldSchema> columns) {
      this.columns = columns;
    }

    public List<FieldSchema> getPartitionKeys() {
      return partitionKeys;
    }

    public void setPartitionKeys(List<FieldSchema> partitionKeys) {
      this.partitionKeys = partitionKeys;
    }
  }


  private Integer newTabVer(Operator<?> op, String tabID) {
    int ver = tabVersion.size();

    if (op instanceof TableScanOperator) {
      // the dataSet is used for the first time and the localID should be 0
      tabVersion.put(ver, new Tuple<String, Integer>(tabID, 0));
      TableScanOperator tsop = (TableScanOperator)op;
      opToTabRd.put(tsop, new Tuple<Integer, String>(ver, tabID));

      List<TableScanOperator> tsList = new ArrayList<TableScanOperator>();
      tsList.add(tsop);
      tabVerRdDesc.put(ver, tsList);

      // create empty historical GID map for the table
      List<Integer> verList = new ArrayList<Integer>();
      verList.add(ver);
      tabIdToVer.put(tabID, verList);
    } else if (op instanceof FileSinkOperator) {
      int local = 0;
      if (tabIdToVer.get(tabID) != null) {
        // it is not first time this table is updated
        int len = tabIdToVer.get(tabID).size();
        int lastGid = tabIdToVer.get(tabID).get(len-1);
        local = 1 + tabVersion.get(lastGid).getValue();
         // update historical GID for the table
        tabIdToVer.get(tabID).add(ver);
      } else {
        // create historical GID map for the table
        List<Integer> verList = new ArrayList<Integer>();
        verList.add(ver);
        tabIdToVer.put(tabID, verList);
      }

      tabVersion.put(ver, new Tuple<String, Integer>(tabID, local));
      FileSinkOperator fsop = (FileSinkOperator)op;
      opToTabWr.put(fsop, new Tuple<Integer, String>(ver, tabID));
      tabVerWrDesc.put(ver, fsop);
    } else {
      return -1;
    }
    // return the recent created global version ID
    return ver;
  }

  /**
   * Main entry of inter-query flow analysis
   */
  public void multiQueryFlowAnalysis() {
    try {
      // processing each query in order
      for(int i = 0 ; i <  multiPctx.size() ; i++){
        Set<Integer> verSet = new HashSet<Integer>();
        ParseContext pCtx = multiPctx.get(i);

        // step 1. handling data usages
        Collection<Operator<? extends OperatorDesc>> topOps = pCtx.getTopOps().values();
        for (Operator<? extends OperatorDesc> topOp : topOps) {
            int ver = -1;
            String tableName =  pCtx.getTopToTable().get(topOp).getTableName();
            /* Create a new entry for it in the SSA structure if no corresponding GID
             * is found for the current dataSet. Otherwise, add current topOp into the
             * using list of the corresponding GID entry.
             */
            if (tabIdToVer.get(tableName) != null) {
              // Deal with the "otherwise" situation first :-)
              int len = tabIdToVer.get(tableName).size();
              ver = tabIdToVer.get(tableName).get(len-1);
              opToTabRd.put((TableScanOperator)topOp,
                  new Tuple<Integer, String>(ver, tableName));
              if (tabVerRdDesc.get(ver) != null) {
                 tabVerRdDesc.get(ver).add((TableScanOperator)topOp);
              } else {
                List<TableScanOperator> tsList = new ArrayList<TableScanOperator>();
                tsList.add((TableScanOperator)topOp);
                tabVerRdDesc.put(ver, tsList);
              }
            } else {
              // now
              // create a new GID entry
              ver = newTabVer(topOp, tableName);
              /* Actually, what we need here is to extract columns and partitions used
               * by current query, not just columns and partitions each table has.
               * TO BE EXTENDED
               */
              List<FieldSchema> columns = pCtx.getTopToTable().get(topOp).getCols();
              List<FieldSchema> partKeys = pCtx.getTopToTable().get(topOp).getPartitionKeys();
              Table table = new Table(tableName, columns, partKeys);
              if (tabMap.get(tableName) != null) {
                tabMap.get(tableName).add(table);
              } else {
                List<Table> tabList = new ArrayList<Table>();
                tabList.add(table);
                tabMap.put(tableName, tabList);
              }
            }

            // validation
            if (ver == -1) {  // Will it really happen ?
              throw new SemanticException("Fail to create a new global" +
              		" version id (GID) for " + topOp);
            }

            // update the GID set of the under considering query for both cases
            // Note that it is not a necessary action of flow analysis
            verSet.add(ver);
       }

       // Step 2. Deal with data definition
        Iterator<FileSinkOperator>  fsOpsIter = pCtx.getFsopToTable().keySet().iterator();
        while( fsOpsIter.hasNext()){
          FileSinkOperator fsOp = fsOpsIter.next();
          String tableName = fsOp.getConf().getTableInfo().getTableName();
          if (tableName == null) {
            // A select query is met
            continue;
          }
          // An insert query is met
          String[] tbName = tableName.split("\\.");
          tableName = tbName[1].trim();
          int ver = newTabVer(fsOp, tableName);
          // TO BE EXTENDED
          List<FieldSchema> columns =  pCtx.getFsopToTable().get(fsOp).getCols();
          List<FieldSchema> partitionKeys = pCtx.getFsopToTable().get(fsOp).getPartitionKeys();;
          Table table = new Table(tableName, columns, partitionKeys);
          if (tabMap.get(tableName) != null) {
            tabMap.get(tableName).add(table);
          } else {
            List<Table> tabList = new ArrayList<Table>();
            tabList.add(table);
            tabMap.put(tableName, tabList);
          }

          // validation
          if (ver == -1) {
            throw new SemanticException("Fail to create a new global" +
                " version id (GID) for " + fsOp);
          }
        }

        // record GID set for current query (will be used in extra step)
        queryVerSetMap.put(i, verSet);
      }
    } catch (SemanticException e) {
      System.out.println("Inter-query Flow Analysis Failed!");
      System.exit(1);
    }

    // TODO: need guard
    dump();

    // Extra Step. do statistics on query characteristics
    // Note that it is no a necessary step of flow analysis
    if (HiveConf.getBoolVar(hiveConf, HiveConf.ConfVars.HIVEOPTCSQ)) {
       queryComparison();
    }
   }

  private void queryComparison() {
    for(int i = 0 ; i <  multiPctx.size() ; i++){
      ParseContext pCtx = multiPctx.get(i);
      Collection<Operator<? extends OperatorDesc>> topOps = pCtx.getTopOps().values();

      int joinOpNums = 0  , groupbyOpnums = 0;
      int args[]  = new int[]{joinOpNums, groupbyOpnums};
      Set<String> visited = new HashSet<String>();
      for( Operator<?> topOp : topOps){
        countOpNums( topOp , visited, args);
      }
      queryJoinMap.put(i, args[0]);
      queryAggrMap.put(i, args[1]);
      LOG.info("queryID: " + i + "  verSet: " + queryVerSetMap.get(i).toString() +
          " #JOIN: " + args[0] + " #GBY: " + args[1]);
    }

    queryCompare( );
  }


  //count joinOperator and GroupbyOperator number
  private void countOpNums( Operator<?> op , Set<String> visited, int args[]){
    String name = op.toString();
    if(!visited.contains(name)){
      if(op instanceof JoinOperator){
        args[0]++;
      }
      if(op instanceof GroupByOperator){
        args[1]++;
      }
    }
    visited.add(name);
    if (op.getNumChild() > 0) {
      List<Operator<?>> children = op.getChildOperators();
      for (int i = 0; i < children.size(); i++) {
        countOpNums( children.get(i),visited,args);
      }
    }
  }


  public void queryCompare( ){
    // Use greedy strategy instead of exhaustive strategy
    if (multiPctx.size() <= 1) {
      return;
    }

    int[] marked = new int[multiPctx.size()];
    for (int i = 0; i < multiPctx.size(); i++)
     {
      marked[i] = -1;  // -1: normal, 0: transitive, 1 same
    }

    for (int i = 0; i < multiPctx.size(); i++){
      if (marked[i] >= 1) {
        continue;
      }

      for (int j = i + 1; j < multiPctx.size(); j++){
        if (marked[j] >= 1) {
          continue;
        }

        // Test same structure
        if (queryJoinMap.get(i) == queryJoinMap.get(j) &&
            queryAggrMap.get(i) == queryAggrMap.get(j)) {
          // Test same dataSet
          if (queryVerSetMap.get(i).equals(queryVerSetMap.get(j))) {
            // An exactly the same pair of queries is found
            if (sameQueryMap.get(i) != null) {
              sameQueryMap.get(i).add(j);
            } else {
              List<Integer> queryList = new ArrayList<Integer>();
              queryList.add(j);
              sameQueryMap.put(i, queryList);
              /* Note that it's OK for a query to be marked as a
               * transitive query and a same query at the same time,
               * as further step would deal with the conflicts.
               */
              marked[i] = 1;
            }
            marked[j] = 1;
          } else if (queryVerSetMap.get(i).size() == queryVerSetMap.get(j).size()) {
            /* An transitive pair of queries is found
             * it might become an exactly same pair after CSQ OPT is
             * performed on queries from the sameQueryMap
             */
            if (transitiveQueryMap.get(i) != null) {
              transitiveQueryMap.get(i).add(j);
            } else {
              List<Integer> queryList = new ArrayList<Integer>();
              queryList.add(j);
              transitiveQueryMap.put(i, queryList);
              marked[i] = 0;
            }
            marked[j] = 0;
          }
        } else {
          if( queryVerSetMap.get(i).containsAll( queryVerSetMap.get(j)) ||
              queryVerSetMap.get(j).containsAll( queryVerSetMap.get(i))) {
            /* The outcome of the queries might be an intermediate result
             * of the other query
             */
            Tuple<Integer , Integer>  queryIDs = new  Tuple<Integer , Integer>( i , j);
            containSameQuerySet.add(queryIDs);
          }
        }
      }
    }

    // Deal with conflicts
    Iterator<Integer> iter = transitiveQueryMap.keySet().iterator();
    Set<Integer> rmSet = new HashSet<Integer>();
    Set<Integer> upSet = new HashSet<Integer>();
    while(iter.hasNext()) {
      int id = iter.next();
      for (int i = transitiveQueryMap.get(id).size()-1; i >= 0; i--) {
        int mapId = transitiveQueryMap.get(id).get(i);
        if (marked[mapId] >= 1) {
          transitiveQueryMap.get(id).remove((Object)mapId);
        }
      }
      if (transitiveQueryMap.get(id).size() == 0) {
        // Query "id" now has no company in the transitiveMap.
        // Should be removed
        rmSet.add(id);
      } else if (marked[id] >= 1) {
        if (transitiveQueryMap.get(id).size() <= 1) {
          // Query "mapId" has no company in the transitiveMap.
          // Should be removed as well
          rmSet.add(id);
        } else {
          // Query "id" itself should be removed, but not its companies
          upSet.add(id);
        }
      }
    }
    for (int id : upSet) {
      int newId = transitiveQueryMap.get(id).get(0);
      List<Integer> newMap = transitiveQueryMap.get(id);
      newMap.remove((Object)newId);

      if (transitiveQueryMap.get(newId) != null) {
        for (int mapId : newMap) {
          if (!transitiveQueryMap.get(newId).contains(mapId)) {
            /* Note that, here we use ArrayList structure instead of HashSet
             * in order to maintain the order of CSQ detection. The disadvantage
             * is that we have to check duplicated element all by ourselves.
             */
            transitiveQueryMap.get(newId).add(mapId);
          }
        }
      } else {
        transitiveQueryMap.put(newId, newMap);
      }
    }
    for (int id : rmSet) {
      transitiveQueryMap.remove(id);
    }

    // maintain normalQuerySet
    for (int i = 0; i < multiPctx.size(); i++) {
      if (marked[i] < 0) {
        normalQuerySet.add(i);
      }
    }

    // dump
    LOG.info("Same query set: ");
    iter = sameQueryMap.keySet().iterator();
    while (iter.hasNext()) {
      int id = iter.next();
      LOG.info(id + ": " + transitiveQueryMap.get(id));
    }
  }

  /**
   * the latest  table version is the biggest value of all
   * @param tabID
   * @return the latest local table version of corresponding table ID
   */
  public Integer getLocalTbVer(Integer GID ){
    return  tabVersion.get(GID).getValue();
  }

  public LinkedHashMap<Integer, Tuple<String, Integer>> getTabVersion() {
    return tabVersion;
  }


  public HashMap<Integer, List<TableScanOperator>> getTabVerRdDesc() {
    return tabVerRdDesc;
  }


  public HashMap<Integer, FileSinkOperator> getTabVerWrDesc() {
    return tabVerWrDesc;
  }


  public HashMap<TableScanOperator, Tuple<Integer, String>> getOpToTabRd() {
    return opToTabRd;
  }


  public HashMap<FileSinkOperator,Tuple<Integer, String>> getOpToTabWr() {
    return opToTabWr;
  }


  public HashMap<String, List<Table>> getTabMap() {
    return tabMap;
  }

  public LinkedHashMap<Integer, Set<Integer>> getQueryVerSetMap() {
    return queryVerSetMap;
  }

  public Set<Integer> getQueryVerSetMap(int id) {
    return queryVerSetMap.get(id);
  }

  public HashMap<Integer, Integer> getQueryJoinMap() {
    return queryJoinMap;
  }

  public HashMap<Integer, Integer> getQueryAggrMap() {
    return queryAggrMap;
  }

  public HashMap<Integer, List<Integer>> getSameQueryMap() {
    return sameQueryMap;
  }

  public HashMap<Integer, List<Integer>> getTransitiveQueryMap() {
    return transitiveQueryMap;
  }

  public Set<Tuple<Integer,Integer>> getContainSameQuerySet() {
    return containSameQuerySet;
  }

  public Set<Integer> getNotExactSameQuerySet() {
    return normalQuerySet;
  }

  public HashMap<Integer, Integer> getCsqSameVerMap() {
    return csqSameVerMap;
  }

  public void setCsqSameVerMap (HashMap<Integer, Integer> csqMap){
    csqSameVerMap = csqMap;
  }

  public List<TableScanOperator> getFStoTSlist (FileSinkOperator fsop){
    if (opToTabWr.get(fsop) == null) {
      return null;
    }

    int fsVer = opToTabWr.get(fsop).getKey();
    return tabVerRdDesc.get(fsVer);
  }

  public int getTStoGID(TableScanOperator tsop){
    if (opToTabRd.get(tsop) == null) {
      return -1;
    }

    return opToTabRd.get(tsop).getKey();
  }

  public void dump(){
    // dump in the order of queries
    LOG.info("Dump flow dependencies in the order of queries: ");
    for(Integer i = 0 ; i <  multiPctx.size() ; i++){
      Set<Integer> verList = queryVerSetMap.get(i);
      if (verList == null) {
        continue;
      }

      String str = "queryID_" + i;
      String def = "   Wr:";
      for (Integer ver : verList) {
        if (tabVerWrDesc.get(ver) != null) {
          // an insert query is met
          def += " " + tabVerWrDesc.get(ver) + "(ver: "+ ver +")";
        }
      }

      String use = "   Rd:";
      ParseContext pCtx = multiPctx.get(i);
      Collection<Operator<? extends OperatorDesc>> topOps = pCtx.getTopOps().values();
      for (Operator<? extends OperatorDesc> topOp : topOps) {
        TableScanOperator tsop = (TableScanOperator)topOp;
        use += " " + topOp + "(ver: " + opToTabRd.get(tsop).getKey() + ")";
      }
      LOG.info(str + def + use);
    }

    LOG.info("Dump flow dependencies in SSA form");
    Iterator<Integer> iter = tabVersion.keySet().iterator();
    while(iter.hasNext()) {
      Integer ver = iter.next();
      String str = "verID: " + ver;
      String def = "   DEF: ";
      if (tabVerWrDesc.get(ver) != null) {
        def += tabVerWrDesc.get(ver).toString();
      }
      String use = "   USE: ";
      if (tabVerRdDesc.get(ver) != null) {
        use += tabVerRdDesc.get(ver).toString();
        use += " (freq: " + tabVerRdDesc.get(ver).size() + ")";
      }
      LOG.info(str + def + use);
    }

    LOG.info("Most frequently used version: " + getMostFreqVer().toString());
  }

  public void clear(){
    tabVersion.clear();
    opToTabRd.clear();
    opToTabWr.clear();
    tabMap.clear();
  }

  public int getGid(Operator<?> op) {
    if (op instanceof TableScanOperator) {
      TableScanOperator tsop = (TableScanOperator)op;
      if (opToTabRd.get(tsop) != null) {
        return opToTabRd.get(tsop).getKey();
      }
    } else if (op instanceof FileSinkOperator) {
      FileSinkOperator fsop = (FileSinkOperator)op;
      if (opToTabWr.get(fsop) != null) {
        return opToTabWr.get(fsop).getKey();
      }
    }
    return -1;
  }

  public FileSinkOperator getWrOp(Integer ver) {
    return (tabVerWrDesc.get(ver) != null)? tabVerWrDesc.get(ver):null;
  }

  public List<TableScanOperator> getRdOpList(Integer ver) {
    return (tabVerRdDesc.get(ver) != null)? tabVerRdDesc.get(ver):null;
  }

  public Set<Integer> getMostFreqVer() {
    int freq = 0;
    Iterator<Integer> iter = tabVersion.keySet().iterator();
    while(iter.hasNext()) {
      Integer ver = iter.next();
      if (tabVerRdDesc.get(ver) != null && tabVerRdDesc.get(ver).size() > freq) {
        freq = tabVerRdDesc.get(ver).size();
      }
    }

    if (freq > 0) {
      Set<Integer> verSet = new HashSet<Integer>();
      iter = tabVersion.keySet().iterator();
      while(iter.hasNext()) {
        Integer ver = iter.next();
        if (tabVerRdDesc.get(ver) != null && tabVerRdDesc.get(ver).size() == freq) {
          verSet.add(ver);
        }
      }
      return verSet;
    } else {
      return null;
    }
  }

  public Set<Integer> getFreqVerList(int threshold) {
    Set<Integer> verSet = new HashSet<Integer>();
    Iterator<Integer> iter = tabVersion.keySet().iterator();
    while(iter.hasNext()) {
      Integer ver = iter.next();
      if (tabVerRdDesc.get(ver) != null &&
          tabVerRdDesc.get(ver).size() == threshold) {
        verSet.add(ver);
      }
    }
    return verSet;
  }
}
