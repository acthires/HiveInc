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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hive.ql.exec.ExtractOperator;
import org.apache.hadoop.hive.ql.exec.FileSinkOperator;
import org.apache.hadoop.hive.ql.exec.FilterOperator;
import org.apache.hadoop.hive.ql.exec.GroupByOperator;
import org.apache.hadoop.hive.ql.exec.JoinOperator;
import org.apache.hadoop.hive.ql.exec.LimitOperator;
import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.exec.ReduceSinkOperator;
import org.apache.hadoop.hive.ql.exec.SelectOperator;
import org.apache.hadoop.hive.ql.exec.TableScanOperator;
import org.apache.hadoop.hive.ql.exec.UnionOperator;
import org.apache.hadoop.hive.ql.plan.AggregationDesc;
import org.apache.hadoop.hive.ql.plan.ExprNodeDesc;
import org.apache.hadoop.hive.ql.plan.JoinCondDesc;
import org.apache.hadoop.hive.ql.plan.OperatorDesc;

/**
 * Detecting the common sub tree of two Optree
 *
 */
public class CommonSubtreeDetect {

  private static final Log LOG = LogFactory.getLog(CommonSubtreeDetect.class.getName());

  private   List< Object > opTree1 ;
  private   List< Object > opTree2 ;

  private   int [][] opCmpResTab;

  private   HashMap<Operator<?> , Operator<?>> sameOpToOp;

  private ParseContext pCtx;

  public  CommonSubtreeDetect( ){

  }

  public CommonSubtreeDetect(Operator<? extends OperatorDesc> rootOp1,
      Operator<? extends OperatorDesc> rootOp2 ,ParseContext pCtx){
    this.pCtx = pCtx;
    sameOpToOp =  new HashMap<Operator<?> , Operator<?>>();
    opTree1 = new  ArrayList<Object>() ;
    opTree2 = new  ArrayList<Object>() ;
    opTree1.addAll(genOptree(rootOp1));
    opTree2.addAll(genOptree(rootOp2));
    //a two-dimensional table entry established
    int rowLen = opTree1.size();
    int colLen = opTree2.size();
    this.opCmpResTab = new int[rowLen][colLen];
    buildResTab();
  }

  private void buildResTab(){
    for( int i = 0 ; i < opCmpResTab.length ; i++){
      for( int j = 0 ; j < opCmpResTab[i].length ; j++){
        if( opTree1.get(i) instanceof Operator && opTree2.get(j) instanceof Operator){
          Operator<?> op1 = (Operator<?>)opTree1.get(i);
          Operator<?> op2 = (Operator<?>)opTree2.get(j);
          if ( op1.getName().equals(op2.getName()) &&
              (opCmp(op1, op2) || transitiveOpCmp(op1, op2))){
            if( (i-1) >= 0  && (j-1 ) >= 0){
              opCmpResTab[i][j] =  opCmpResTab[i-1][j-1] + 1;
            }else{
              opCmpResTab[i][j] = 1;
            }
          }else{
            opCmpResTab[i][j] = 0;
          }
        }else if( opTree1.get(i) instanceof String && opTree2.get(j) instanceof String){
          String op1 = (String)opTree1.get(i);
          String op2 = (String)opTree2.get(j);
          if(op1.toString().equals(op2.toString())){
            if( (i-1) >= 0  && (j-1 ) >= 0){
              opCmpResTab[i][j] =  opCmpResTab[i-1][j-1] + 1;
            }else{
              opCmpResTab[i][j] = 1;
            }
          }
        }else {
          opCmpResTab[i][j] = 0;
        }
      }
    }
  }

  public  List<Operator<?>> getTsOps( Operator<?> rootOp ){
    List<Operator<?>> visited = new ArrayList<Operator<?>>();
    visited.clear();
    getTsOps( visited, rootOp);
    return visited;
  }

  //recursive get all top operators of currently optree
  private  void getTsOps( List<Operator<?>> visited, Operator<?> op ){
    if( op.getNumParent() == 0 && op.getNumChild() != 0) {
      visited.add(op );
    }
    if(op.getNumParent() > 0){
      List<Operator<?>> parent = op.getParentOperators();
      for (int i = 0; i < parent.size(); i++) {
        getTsOps( visited, parent.get(i));
      }
    }
  }


  /**
   * according to the last operator of Optree , generating the link
   * of operator.for example , as the following Optree
   *
   *       FS[13]
   *         |
   *      SEL[12]
   *         |
   *      JOIN[11]
   *       /   \
   *      /     \
   *     /       \
   *   RS[10]  RS[9]
   *     |       |
   *   TS[7]   TS[8]
   *
   * it will be converted the following link of operator:
   * TS[7]-RS[10]-JOIN[11]-SEL[12]-FS[13]-X-X-X-X-X-TS[8]-RS[9]-X-X
   * @param rootOp
   * @return list of Operators
   */

  private  List<?> genOptree( Operator<?> rootOp){
    List<Operator<?>> topOps =  getTsOps( rootOp );
    List<Object> visited = new ArrayList<Object>();
    List<Operator<?> >walked = new ArrayList<Operator<?>>();
    for(Operator<?> topOp : topOps){
      genOptree( walked, topOp,rootOp);
      int vaildLen = 0;
      while( !walked.isEmpty()){
        Operator<?> curOp = walked.remove(0);
        if( !visited.contains( curOp)) {
          visited.add(curOp);
          vaildLen++;
        }
      }

      while( vaildLen-- > 0 ){
        String str = new String("X");
        visited.add(str);
      }
      walked.clear();
    }
    return visited;
  }


  private void genOptree( List<Operator<?> > walked , Operator<?> op ,  Operator<?> rootOp){
    walked.add(op);
    if( op.equals(rootOp)){
      return ;
    }
   if (op.getNumChild() > 0) {
     List<Operator<?>> children = op.getChildOperators();
     for (int i = 0; i < children.size(); i++) {
       genOptree( walked, children.get(i), rootOp);
     }
   }

  }

  public HashSet<List<Object>> getCSTree( ){
    /*
     *  Extracting string version CSTree
     */
    HashSet<List<Object>> res = new HashSet<List<Object>>();

    boolean[] valid = new boolean[opCmpResTab.length];
    for ( int i = 0; i < opCmpResTab.length; i++ ) {
      valid[i] = true;
    }

    while (true) {
      List<Object> opList = getLargestCSTree(valid);
      if (opList == null || opList.isEmpty()) {
        break;
      }

      Collections.reverse( opList);
      res.add(opList);
    }

    validate(res);
    return res;
  }

  private boolean transitiveOpCmp(Operator<?> op1, Operator<?> op2) {
    // precondition: op1.getName() == op2.getName()
    if (!(op1 instanceof TableScanOperator)) {
      return false;
    }

    MultiParseContext multiPctx = (MultiParseContext)pCtx;
    InterQueryFlowCtx flowCtx = multiPctx.getQueryFlowCtx();
    if (flowCtx == null || flowCtx.getCsqSameVerMap().isEmpty()) {
      return false;
    }

    HashMap<Integer, Integer> verMap = flowCtx.getCsqSameVerMap();
    int ver1 = flowCtx.getOpToTabRd().get((TableScanOperator)op1).getKey();
    int ver2 = flowCtx.getOpToTabRd().get((TableScanOperator)op2).getKey();

    if (ver1 == ver2 ||
        verMap.get(ver1) != null && verMap.get(ver1) == ver2 ||
        verMap.get(ver2) != null && verMap.get(ver2) == ver1) {
      LOG.info("Adjust comparing result of " + op1 + " and " + op2);
      return true;
    }

    return false;
  }// including common sub-strings which contain no operator

  private List<Object> getLargestCSTree( boolean[] valid ){
    int threshold = 2;  // The smallest CSTree should be TS-RS
    int r_idx = -1, c_idx = -1;
    List<Object> opList = new ArrayList<Object>();

    /* Scan the right-upper triangle to find the longest sub string ( which has
     * not been recorded before).
     * If there are more than one candidates, record the last one we meet each time.
     * Other candidates would be recorded in further iterations.
     * There is no need to scan the the left part of the triangle as it is symmetrical.
     */
    for( int r = opCmpResTab.length-1, c = opCmpResTab[0].length-1; r >= 0; r-- ){
      for(int or = r , oc = c ; or >= 0 && oc >=0 ; or--, oc-- ){
        if(opCmpResTab[or][oc]  >= threshold && valid[or] == true ){
          threshold = opCmpResTab[or][oc];
          r_idx = or;
          c_idx = oc;
        }
      }
    }

    // Chances are one of the subString is longer, so further search is needed
    for( int r = opCmpResTab.length-1, c = opCmpResTab[0].length-2; c >=opCmpResTab.length-1; c-- ){
      for(int or = r , oc = c ; or >= 0 && oc >=0 ; or--, oc-- ){
        if(opCmpResTab[or][oc]  >= threshold && valid[or] == true ){
          threshold = opCmpResTab[or][oc];
          r_idx = or;
          c_idx = oc;
        }
      }
    }

    if (r_idx == -1 || c_idx == -1) {
      return opList;
    }
    LOG.info("Length of the longest common substring: " + threshold);

    /* Update the valid set so that none of the largest CSTree we've
     * found could share any common operator.
     */
    for (int i = r_idx; i > r_idx-threshold; i--) {
       valid[i] = false;
    }

    /* At this point, we've got the longest common substring.
     * What we have to do next is to find and record the corresponding
     * common sub-OPtree (CSTree).
     *
     * POTENTIAL THREAT:
     * Chances are the corresponding CSTree might not exist.
     */
    for(  ;  r_idx >= 0 && c_idx >=0 && opCmpResTab[r_idx][c_idx] > 0 ; r_idx-- , c_idx-- ){
      Object r_obj = getOpTree1().get(r_idx);
      opList.add(r_obj);

      if(r_obj instanceof  Operator<?>){
        Operator<?> r_op = (Operator<?>)r_obj;
        Operator<?> l_op = (Operator<?>)getOpTree2().get(c_idx);
        if (sameOpToOp.get(r_op) == null) {
          sameOpToOp.put(r_op,l_op);
        } else if(!sameOpToOp.get(r_op).equals(l_op)) {
          // This branch should never been taken
          LOG.info("Potential Threat: reset csq mapping for operator "
                    + r_op + " from " + sameOpToOp.get(r_op) + " to " + l_op);
          sameOpToOp.put(r_op,l_op);
        }
      }
    }
    LOG.info(opList.toString());
    return opList;
  }

  private void  validate( HashSet<List<Object>>  candidates){
    /*
     * Check validation:
     * A sub-tree should be considered as a common sub-tree if and
     * only if all of its sub-trees are common sub-trees
     */
    boolean changed = true;
    int totalValidOps = 0, iterId = 0;

    while(changed) {
      changed = false;
      totalValidOps = 0;
      Iterator<List<Object>> iter = candidates.iterator();
      LOG.debug("# of iteration: " + iterId++);

      while( iter.hasNext() ){
        List<Object> obj = iter.next();
        List<Object> invalidList = new ArrayList<Object>();
        boolean last_valid = true;
        LOG.debug("current sub-string: " + obj.toString());

        for(int i = 0 ; i < obj.size() ; i++){
          if( obj.get(i)  instanceof Operator<?>){
            /* If last op is invalid which means the subtree lead by it is not
             * a common sub-tree, then the current op could never be able to
             * lead a common sub-tree either. Otherwise, the current op might
             * be able to lead a common sub-tree if all of its ancestors have
             * already be considered to be in some common sub-trees.
             * If the current op is not an instance of logical operator, reset
             * the last invalid flag.
             */
            if (!last_valid) {
              invalidList.add(obj.get(i));
              sameOpToOp.remove((Operator<?>)(obj.get(i)));
            } else { /* last_valid is true */
              Operator<?> op = (Operator<?>)(obj.get(i));
              List<Operator<?>> parents = op.getParentOperators();

              if (parents != null) {
                for (Operator<?> parent : parents) {
                  if (sameOpToOp.get(parent) == null) {
                     last_valid = false;
                     break;
                  }
                }
              }
              if (!last_valid) {
                invalidList.add(op);
                sameOpToOp.remove(op);
              } else if (op instanceof JoinOperator ||
                  op instanceof GroupByOperator){
                totalValidOps++;
              }
            }
          }else {
            last_valid = true;
          }
        }

        // remove common sub-strings which contain no valid operator
        if (invalidList.size() > 0) {
          changed = true;
          if (invalidList.size() == obj.size()) {
            LOG.debug("remove a whole string: " + obj.toString());
            iter.remove();
            continue;
          }
        }
        // including common sub-strings which contain no operator
        int validOps = 0;
        for(int i = 0 ; i < obj.size() ; i++){
          if( obj.get(i)  instanceof Operator<?> &&
              !invalidList.contains(obj.get(i))){
            validOps++;
          }
        }
        if (validOps <= 0) {
          LOG.debug("remove a whole string: " + obj.toString());
          iter.remove();
        } else if (invalidList.size() > 0) {
          LOG.debug("remove a sub string: " + invalidList.toString());
          obj.removeAll(invalidList);
        }
      }
      // Too strict
      /*if (totalValidOps <= 0) {
        LOG.debug("remove all candidate strings");
        candidates.clear();
        break;
      }*/
    }
    LOG.info("After validation: ");
    for (List<Object> candidate : candidates) {
      //LOG.info(candidate.toString());
      System.out.println(candidate.toString());
    }
  }

  public List<SubOpTree> genCSTree( HashSet<List<Object>> res ){
    /*
     * Transform string version CSTree into query/tree version.
     * Note that, different common substrings may belong to the
     * same common sub-query/tree.
     */
    HashMap<Operator<?>, List<Object>> cstCandidates =
      new HashMap<Operator<?>, List<Object>>();
    HashMap<Operator<?>, Operator<?>> rootMap =
      new HashMap<Operator<?>, Operator<?>>();
    List<SubOpTree> cstList =  new ArrayList<SubOpTree>();

    /* Step 1. identify root node candidates
     * Nodes whose child operators can not be found in the current
     * common substring are considered as candidates.
     */
    for(List<Object> opList : res){
      for(int i = 0; i < opList.size(); i++){
        if(opList.get(i) instanceof  Operator<?> &&
          (i+1 == opList.size() || !(opList.get(i+1) instanceof Operator<?>)))
        { // A potential common sub-tree is found
          Operator<?> op = (Operator<?>)(opList.get(i));
          List<Object> cstCandidate = new ArrayList<Object>();
          for (int j = i; j >= 0; j--) {
            if (!(opList.get(j) instanceof Operator<?>)) {
              break;
            }
            cstCandidate.add(opList.get(j));
          }
          cstCandidates.put((Operator<?>)op, cstCandidate);
          LOG.debug("cst candidate: " + cstCandidate.toString());
        }
      }
    }

    /* Step2. Identify real root nodes and create the initial CSTree for
     * each of them.
     * If all of the children of some root candidate could not be found
     * in any other substring, the candidate is considered to be a real
     * root node of a common sub-tree. Candidates whose children exist
     * in other substrings should be merged into the subtrees that their
     * children belong to.
     *
     * 2.1 identify real root nodes
     */
    Set<Operator<?>> rootCandidates = cstCandidates.keySet();
    Set<Map.Entry<Operator<?>, List<Object>>> entries = cstCandidates.entrySet();
    for (Operator<?> rootCandidate : rootCandidates) {
      List<Operator<?>> children = rootCandidate.getChildOperators();
      if (children != null) {
        for (Operator<?> child : children) {
          for(Map.Entry<Operator<?>, List<Object>> e : entries) {
            if (!e.getKey().equals(rootCandidate) && e.getValue().contains(child)) {
              rootMap.put(rootCandidate, e.getKey());
              LOG.debug("subTree lead by " + rootCandidate + " should be merged" +
              		" into the one lead by " + e.getKey());
              break;
            }
          }
        }
      }
    }
    // 2.2 create the initial CSTree for each root node
    for (Operator<?> rootCandidate : rootCandidates) {
      if (rootMap.get(rootCandidate) == null) {
        // a real root is found
        LOG.info("generate initial CSTree for root " + rootCandidate);
        SubOpTree cst = mergeIntoCSTree(rootCandidate,
            cstCandidates.get(rootCandidate), null);
        cstList.add(cst);
      }
    }

    // Step 3. merge left substrings into recent generated CSTrees
    rootCandidates = cstCandidates.keySet();
    for (Operator<?> rootCandidate : rootCandidates) {
      Operator<?> mapOp = rootMap.get(rootCandidate);
      if ( mapOp == null) {
        continue;
      }
      while (rootMap.get(mapOp) != null) {
        mapOp = rootMap.get(mapOp);
      }
      for (SubOpTree cst:cstList) {
        if (mapOp.equals(cst.getRootNode())) {
          LOG.info("merge the subTree lead by " + rootCandidate +
              " into the one lead by " + cst.getRootNode());
          mergeIntoCSTree(rootCandidate,
              cstCandidates.get(rootCandidate), cst);
          break;
        }
      }
    }

    // Step 4. validation
    for (int i = cstList.size()-1; i >= 0; i--) {
      SubOpTree cst = cstList.get(i);
      if (cst.getMaxDepth() < 2 &&
          cst.getJoinNodes().size() <= 0 &&
          cst.getAggrNodes().size() < 2) {
        List<Operator<?>> childOps = cst.getRootNode().getChildOperators();
        if (childOps != null && childOps.size() >= 1 &&
            (childOps.get(0) instanceof FileSinkOperator) ||
            (childOps.get(0) instanceof ExtractOperator) ||
            (childOps.get(0) instanceof LimitOperator)) {
          continue;
        }
        LOG.info("remove invalid CSTree lead by " + cst.getRootNode());
        cstList.remove(i);
      }
    }
    return cstList;
  }

  private SubOpTree mergeIntoCSTree(Operator<?> root,
      List<Object> opList, SubOpTree cst) {
    if (opList == null) {
      return cst;
    }
    if (cst == null) {
      cst = new SubOpTree(root);
    }

    int depth = 0;
    for(int i = 0 ; i < opList.size() ; i++){
      Object obj = opList.get(i);
      if( obj instanceof  Operator<?>){
        Operator<?> op = (Operator<?>)obj;
        cst.getCommonNodes().add(op);
        if(op instanceof JoinOperator){
          cst.getJoinNodes().add(op);
        }
        if(op instanceof GroupByOperator){
          cst.getAggrNodes().add(op);
        }
        if(op instanceof ReduceSinkOperator ){
          depth++;
        }
        if(op instanceof TableScanOperator){
          cst.getLeafNodes().add(op);
        }
      }
    }
    if (depth > cst.getMaxDepth()) {
      cst.setMaxDepth(depth);
    }
    return cst;
  }

  public boolean reuseCSTree(List<SubOpTree> cstList) {
    for (SubOpTree cst : cstList) {
      LOG.info("data reusing optimization for Common SubTree:"
          + cst.toString());
      Operator<?> root = cst.getRootNode();
      /* Preprocessing: for CSQs lead by rsops, set the direct ancestor
       * of each rsop as the root for each CSQ respectively in order to
       * avoid violations in JFC or TC detection.
       */
      if (root instanceof ReduceSinkOperator) {
        root = root.getParentOperators().get(0);
      }
      Operator<?> shadow = sameOpToOp.get(root);
      try {
        // Step 1: iteratively delete ancestors of shadowOp
        List<Operator<?>> parentOps = root.getParentOperators();
        for (Operator<?> parent : parentOps) {
           removeShadow(parent, cst);
        }
        // Step 2. merge shadowOp with rootOp
        List<Operator<?>> childOfShadow = shadow.getChildOperators();
        if (childOfShadow != null) { // Will it be null ?
          for (Operator<?> child : childOfShadow ) {
            LOG.info("replace parentOp of " + child + " from shadowOp "
                + shadow + " to " + root);
            child.replaceParent(shadow, root);
            root.getChildOperators().add(child);
          }
          shadow.getChildOperators().removeAll(childOfShadow);
        }
        // Step 3. remove shadowOp itself
        LOG.info("remove the shadowOp itself");
        pCtx.getOpParseCtx().remove(shadow);
        if (shadow instanceof JoinOperator) {
          pCtx.getJoinContext().remove(shadow);
        }
      } catch (SemanticException e) {
        return false;
      }
    }
    return true;
  }

  private void removeShadow(Operator<?> root, SubOpTree cst)
          throws SemanticException{
    if (!cst.getCommonNodes().contains(root)) {
      throw new SemanticException(root +
          " to be detected could not be found in CSTree" );
    }

    Operator<?> shadow = sameOpToOp.get(root);
    List<Operator<?>> parentOps = root.getParentOperators();
    if (parentOps != null && parentOps.size() > 0) {
      for (Operator<?> parent : parentOps) {
        removeShadow(parent, cst);
      }
    }
    LOG.info("remove ancestors of the shadowOp: " + shadow);
    if (shadow instanceof TableScanOperator) {
      String key = "";
      for( String str :  pCtx.getTopOps().keySet()){
        if( pCtx.getTopOps().get(str).equals(shadow )){
          key = str;
          break;
        }
      }
      pCtx.getTopOps().remove(key);
      pCtx.getTopToTable().remove(shadow);
    }
    if (shadow instanceof JoinOperator) {
      pCtx.getJoinContext().remove(shadow);
    }
    pCtx.getOpParseCtx().remove(shadow);
    for (Operator<?> child : shadow.getChildOperators()) {
      child.removeParent(shadow);
    }
  }

  //to eliminate sub tree that have common operator
  private void  removeCommonPart( HashSet<List<Object>>  commonList ){
    HashSet<List<Object>> commonl = new HashSet<List<Object>>();
    Iterator<List<Object>> listIter = commonList.iterator();
    while( listIter.hasNext() ){
      List<Object> l = listIter.next();
      Iterator<List<Object>> tmpIter = commonList.iterator();
      while( tmpIter.hasNext()){
        List<Object> tmpl = tmpIter.next();
        if(!l.equals(tmpl) && l.containsAll(tmpl)){
          commonl.add(tmpl);
        }
      }
      //remove list that don't include operator
      boolean visitedOp = false;
      for(int i = 0 ; i < l.size() ; i++){
          if( l.get(i)  instanceof Operator<?>){
            visitedOp = true;
            break;
          }
      }
      if( visitedOp == false){
        listIter.remove();
        continue;
      }
    }

    //merge common sub optree
   for( List<Object> list : commonl){
     commonList.remove(list);
   }
  }

  public class SubOpTree  {
    private final  Operator<? extends OperatorDesc> rootNode;
    // the value that the longest branch of optree
    private int maxDepth;
    // the value that the shortest branch.
    private int minDepth;
    private  List<Operator<?>> leafNodes;
    private  List<Operator<?>> joinNodes;
    private  List<Operator<?>> aggrNodes;
    private  Set<Operator<?>> commonNodes;

    public SubOpTree(Operator<?> rootNode){
      this.rootNode = rootNode;
      this.maxDepth = 0;
      this.minDepth = 0;
      this.leafNodes = new ArrayList<Operator<?>>();
      this.joinNodes = new ArrayList<Operator<?>>();
      this.aggrNodes = new ArrayList<Operator<?>>();
      this.commonNodes = new HashSet<Operator<?>>();
    }

    public SubOpTree(Operator<?> rootNode , int maxDepth , int  minDepth,
        List<Operator<?>> leafNodes , List<Operator<?>> joinNodes){
      this.rootNode = rootNode;
      this.maxDepth = maxDepth;
      this.minDepth = minDepth;
      this.leafNodes = leafNodes;
      this.joinNodes = joinNodes;
      this.aggrNodes = new ArrayList<Operator<?>>();
    }

    public Operator<? extends OperatorDesc> getRootNode() {
      return rootNode;
    }

    public int getMaxDepth() {
      return maxDepth;
    }

    public int getMinDepth() {
      return minDepth;
    }

    public void setMaxDepth(int max) {
      maxDepth = max;
    }

    public void setMinDepth(int min) {
      minDepth = min;
    }

    public List<Operator<?>>  getLeafNodes() {
      return leafNodes;
    }

    public void setLeafNodes( List<Operator<?>>  leafNodes){
      this.leafNodes  = leafNodes;
    }

    public List<Operator<?>> getJoinNodes() {
      return joinNodes;
    }

    public void setJoinNodes(List<Operator<?>> joinNodes) {
      this.joinNodes = joinNodes;
    }

    public List<Operator<?>> getAggrNodes() {
      return aggrNodes;
    }

    public void setAggrNodes(List<Operator<?>> aggrNodes) {
      this.aggrNodes = aggrNodes;
    }

    public Set<Operator<?>> getCommonNodes() {
      return commonNodes;
    }

    public void setCommonNodes(Set<Operator<?>> commonNodes) {
      this.commonNodes = commonNodes;
    }

    public boolean check(Set<Operator<?>>allCommonNodes){

      boolean res[] = {true};
      for( Operator<?> leafNode : leafNodes){
        checkNode(allCommonNodes , leafNode ,res) ;
        if(res[0] == false){
          break;
        }else{
        }
      }

      return res[0];
    }

    private  void checkNode(Set<Operator<?>> allCommonNodes ,Operator<?> op , boolean res[] ){
      if( op.equals(rootNode) ){
          return ;
      }
      if( op.getNumChild() > 0){
        List<Operator<?>> children = op.getChildOperators();
        for (int i = 0; i < children.size(); i++) {
          if( ! allCommonNodes.contains(children.get(i))){
            return;
          }
          List<Operator<? extends OperatorDesc>> parentOps = children.get(i).getParentOperators();
          for(Operator<? extends OperatorDesc> parentOp : parentOps){
//            if( ! allCommonNodes.contains(parentOp ) && !(parentOp instanceof FilterOperator)){
            if( ! allCommonNodes.contains(parentOp ) ){
              res[0] = false;
              return;
            }
          }
          checkNode(allCommonNodes , children.get(i) ,res);
        }
      }
    }


    public String walker(){
      StringBuilder builder = new StringBuilder();
      Set<String> visited = new HashSet<String>();
      for( Operator<?> leafNode : leafNodes){
        walker(  builder ,  visited,  leafNode , 0);
      }
      return builder.toString();
    }


    private void walker(  StringBuilder builder , Set<String> visited, Operator<?> op , int start){
      if( op.equals(rootNode)){
        return;
      }
      String name = op.toString();
      boolean added = visited.add(name);
      if (start > 0) {
        builder.append("-");
        start++;
      }
      builder.append(name);
      start += name.length();
      if (added) {
        if (op.getNumChild() > 0) {
          List<Operator<?>> children = op.getChildOperators();
          for (int i = 0; i < children.size(); i++) {
            if (i > 0) {
              builder.append('\n');
              for (int j = 0; j < start; j++) {
                builder.append(' ');
              }
            }
            walker(builder, visited, children.get(i), start);
          }
        }
      }
    }

    @Override
    public String toString(){
      StringBuilder builder = new StringBuilder();
      builder.append("{root: "+ getRootNode()  + " maxDepth: " + getMaxDepth()
          + " minDepth: " + getMinDepth() + " leaves: " + getLeafNodes() + "}");
      return builder.toString();
    }
  }

  public void setPctx( ParseContext pCtx){
    this.pCtx = pCtx;
  }

  public ParseContext getPctx(  ){
    return this.pCtx;
  }

  public HashMap<Operator<?>, Operator<?>> getSameOpToOp() {
    return sameOpToOp;
  }

  public Operator<?> getSameOpToOp(Operator<?> op) {
    return sameOpToOp.get(op);
  }

  //comparison of two operator,if they are equal,return true,or return false
  private boolean opCmp(Operator<?> op1, Operator<?> op2 ){

    if( op1.getName().equals(op2.getName())){
      if( op1 instanceof TableScanOperator ){
        TableScanOperator t1 = (TableScanOperator)op1;
        TableScanOperator t2 = (TableScanOperator)op2;

        // the TableScanOperator are same means that
        // they have the same table name
        String tableName1 = "null";
        String tableName2 = "NULL";

        if( pCtx.getTopToTable().containsKey(t1) ){
          tableName1 = pCtx.getTopToTable().get(t1).getTableName();
        }
        if( pCtx.getTopToTable().containsKey(t2) ){
          tableName2 = pCtx.getTopToTable().get(t2).getTableName();
        }
        if(tableName1.equals(tableName2)){
          return true;
        }

      }

      if( op1 instanceof SelectOperator ){
        SelectOperator so1 = (SelectOperator)op1;
        SelectOperator so2 = (SelectOperator)op2;
        if( sameKeys(so1.getConf().getColList() ,  so2.getConf().getColList() ) &&
            so1.getConf().getOutputColumnNames().equals( so2.getConf().getOutputColumnNames())){
          LOG.debug("Two SEL operator have same column List " + exprToString( so1.getConf().getColList() ) +
                  " the same output column name " + so1.getConf().getOutputColumnNames());
          return true;
        }

      }

      if( op1 instanceof ReduceSinkOperator ){
        // Two ReduceSinkOperators are same means that
        // they have same key columns, same partitioning columns,
        // same sorting orders.
        ReduceSinkOperator rsOp1 = (ReduceSinkOperator)op1;
        ReduceSinkOperator rsOp2 = (ReduceSinkOperator)op2;
        ArrayList<ExprNodeDesc> keyCols1 = rsOp1.getConf().getKeyCols();
        ArrayList<ExprNodeDesc> keyCols2 = rsOp2.getConf().getKeyCols();
        ArrayList<ExprNodeDesc> valCols1 = rsOp1.getConf().getValueCols();
        ArrayList<ExprNodeDesc> valCols2 = rsOp2.getConf().getValueCols();
        ArrayList<ExprNodeDesc> partitionCols1 = rsOp1.getConf().getPartitionCols();
        ArrayList<ExprNodeDesc> partitionCols2 = rsOp2.getConf().getPartitionCols();
        String order1 = rsOp1.getConf().getOrder();
        String order2 = rsOp2.getConf().getOrder();
        boolean isSame = sameKeys( keyCols1 , keyCols2) &&
                         sameKeys( valCols1 , valCols2)  &&
                         sameKeys( partitionCols1 , partitionCols2) &&
                         sameOrder( order1 , order2 );
        if(isSame){
          LOG.debug("Two RS have same key columns " + exprToString(keyCols1) +
                   " the same values columns " + exprToString(valCols1) +
                   " the same partition key " + exprToString(partitionCols1) +
                   " the same order " + order1);
          return true;
        }
      }

      if( op1 instanceof FileSinkOperator ){
        // when two FileSinkOperator have the same
        FileSinkOperator fsOp1 = (FileSinkOperator)op1;
        FileSinkOperator fsOp2 = (FileSinkOperator)op2;

        boolean isSame = fsOp1.getConf().getTableInfo().equals( fsOp2.getConf().getTableInfo() )&&
                         fsOp1.getConf().getDestTableId() == fsOp2.getConf().getDestTableId() &&
                         fsOp1.getConf().getFinalDirName().equals( fsOp2.getConf().getFinalDirName());

        if( isSame ){
          LOG.debug("Two fileSinkOperator have  same  tableInfo , destTableID");
          return true;
        }

      }
      if( op1 instanceof JoinOperator ){
        JoinOperator joinOp1 = (JoinOperator)op1;
        JoinOperator joinOp2 = (JoinOperator)op2;

        boolean isSame = sameMap(joinOp1.getConf().getExprs() , joinOp2.getConf().getExprs()) &&
                         joinOp1.getConf().getOutputColumnNames().equals(joinOp2.getConf().getOutputColumnNames()) &&
                         sameMap(joinOp1.getConf().getFilters() , joinOp2.getConf().getFilters()) &&
                         sameCondList(joinOp1.getConf().getCondsList() , joinOp2.getConf().getCondsList() ) &&
                         joinOp1.getConf().getHandleSkewJoin() ==  joinOp2.getConf().getHandleSkewJoin() ;

        if( isSame ){
          LOG.debug("Two JoinOperator is same ");
          return true;
        }
      }

      if( op1 instanceof GroupByOperator ){
        GroupByOperator groupByOp1 = (GroupByOperator)op1;
        GroupByOperator groupByOp2 = (GroupByOperator)op2;
        boolean isSame = groupByOp1.getConf().getMode().equals(groupByOp2.getConf().getMode()) &&
                         sameKeys(groupByOp1.getConf().getKeys(),groupByOp2.getConf().getKeys()) &&
                         sameAggrList(groupByOp1.getConf().getAggregators(),  groupByOp2.getConf().getAggregators()) &&
                         groupByOp1.getConf().getBucketGroup() ==  groupByOp2.getConf().getBucketGroup() &&
                         groupByOp1.getConf().getOutputColumnNames().equals( groupByOp2.getConf().getOutputColumnNames());


        if( isSame ){
          LOG.debug("Two GroupByOperator is same ");
          return true;
        }

      }

      if( op1 instanceof UnionOperator ){
        UnionOperator unionOp1 = (UnionOperator)op1;
        UnionOperator unionOp2 = (UnionOperator)op2;

        boolean isSame = unionOp1.getConf().getNumInputs() ==  unionOp2.getConf().getNumInputs();
        if( isSame ){
          LOG.debug("Two UnionOperator is same ");
          return true;
        }
      }

     if( op1 instanceof LimitOperator ){
        LimitOperator limitOp1 = (LimitOperator)op1;
        LimitOperator limitOp2 = (LimitOperator)op2;
        boolean isSame = limitOp1.getConf().getLimit() ==  limitOp2.getConf().getLimit() &&
                         limitOp1.getConf().getLeastRows() ==  limitOp2.getConf().getLeastRows();

        if( isSame ){
          LOG.debug("Two LimitOperator is same ");
          return true;
        }
     }

     if( op1 instanceof ExtractOperator){
       ExtractOperator extractOp1 = (ExtractOperator)op1;
       ExtractOperator extractOp2 = (ExtractOperator)op2;

       boolean isSame = extractOp1.getConf().getCol().isSame( extractOp2.getConf().getCol() );

       if( isSame ){
         LOG.debug("Two ExtractOperator is same ");
         return true;
       }

     }

     if( op1 instanceof FilterOperator){
       FilterOperator filterOp1 = (FilterOperator)op1;
       FilterOperator filterOp2 = (FilterOperator)op2;

       boolean isSame = filterOp1.getConf().getPredicate().isSame( filterOp2.getConf().getPredicate());

       if( isSame ){
         LOG.debug("Two FilterOperator is same ");
         return true;
       }else{
         LOG.debug("Two FilterOperator is different : " +
       filterOp1.toString() + " [ "+filterOp2.toString() +" ]");
       }

     }

   }

    return false;
  }


  private boolean sameAggrList(ArrayList<AggregationDesc> l1 ,ArrayList<AggregationDesc> l2  ){
    if (l1.size() != l2.size()) {
      return false;
    }

    if (l1.size() == 0 &&  l2.size() == 0) {
      System.out.println("AggregationDesc is null");
      return true;
    }

    for(int i = 0 ; i < l1.size() ; i++){
       AggregationDesc l1Aggr = l1.get(i);
       AggregationDesc l2Aggr = l2.get(i);
       System.out.println("AggregationDesc -> getExprString getGenericUDAFName " +
           l1Aggr.getExprString()+ l2Aggr.getExprString() +"        "+
           l1Aggr.getGenericUDAFName()+l2Aggr.getGenericUDAFName());
       if( !l1Aggr.getMode().equals(l2Aggr.getMode()) ||
           !sameKeys(l1Aggr.getParameters() , l2Aggr.getParameters() )){
           return false;
       }
       if(!l1Aggr.getExprString().equals(l2Aggr.getExprString())){
           return false;
       }
    }
    return true;
  }


  private boolean sameCondList(List<JoinCondDesc> l1 , List<JoinCondDesc> l2){
    if (l1.size() != l2.size()) {
      return false;
    }
    for(int i = 0 ; i < l1.size() ; i++){
      JoinCondDesc jd1 = l1.get(i);
      JoinCondDesc jd2 = l2.get(i);
      if(!jd1.getJoinCondString().equals(jd2.getJoinCondString())){
        return false;
      }else{
        continue;
      }
    }
    return true;
  }


  private boolean sameMap(Map<?,List<ExprNodeDesc>> map1 , Map<?,List<ExprNodeDesc>> map2  ){
    if(map1.size() != map2.size()){
      return false;
    }
    if(map1.keySet().containsAll(map2.keySet()) &&
        map2.keySet().containsAll(map1.keySet())){
      for(  Object key : map1.keySet()){
        if(!sameKeys(map1.get(key) , map2.get(key))){
          return false;
        }else{
          continue;
        }
      }
    }
    return true;
  }


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

  private String exprToString( List<ExprNodeDesc> exprs ){
    String exprNames = "";
    for(ExprNodeDesc expr : exprs ){
      exprNames += "\t" + expr.getName();
    }
    return exprNames;
  }


  public int[][] getOpCmpResTab() {
    return opCmpResTab;
  }


  public List<Object> getOpTree1() {
    return opTree1;
  }


  public List<Object> getOpTree2() {
    return opTree2;
  }

}
