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


import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.hive.ql.exec.ColumnInfo;
import org.apache.hadoop.hive.ql.exec.ExtractOperator;
import org.apache.hadoop.hive.ql.exec.FilterOperator;
import org.apache.hadoop.hive.ql.exec.ForwardOperator;
import org.apache.hadoop.hive.ql.exec.GroupByOperator;
import org.apache.hadoop.hive.ql.exec.JoinOperator;
import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.exec.OperatorFactory;
import org.apache.hadoop.hive.ql.exec.ReduceSinkOperator;
import org.apache.hadoop.hive.ql.exec.RowSchema;
import org.apache.hadoop.hive.ql.exec.ScriptOperator;
import org.apache.hadoop.hive.ql.exec.SelectOperator;
import org.apache.hadoop.hive.ql.exec.TableScanOperator;
import org.apache.hadoop.hive.ql.exec.Utilities;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.parse.OpParseContext;
import org.apache.hadoop.hive.ql.parse.ParseContext;
import org.apache.hadoop.hive.ql.parse.RowResolver;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.plan.AggregationDesc;
import org.apache.hadoop.hive.ql.plan.ExprNodeDesc;
import org.apache.hadoop.hive.ql.plan.ExprNodeDescUtils;
import org.apache.hadoop.hive.ql.plan.GroupByDesc;
import org.apache.hadoop.hive.ql.plan.JoinCondDesc;
import org.apache.hadoop.hive.ql.plan.JoinDesc;
import org.apache.hadoop.hive.ql.plan.OperatorDesc;
import org.apache.hadoop.hive.ql.plan.SelectDesc;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;
import org.mortbay.log.Log;

/**
 * Utilities for both CorrelationOptimizer and ReduceSinkDeDuplication.
 *
 */
public final class CorrelationUtilities {

  protected static boolean isExisted(ExprNodeDesc expr, List<ExprNodeDesc> columns) {
    for (ExprNodeDesc thisExpr : columns) {
      if (thisExpr != null && thisExpr.isSame(expr)) {
        return true;
      }
    }
    return false;
  }

  protected static String getColumnName(
      Map<String, ExprNodeDesc> opColumnExprMap, ExprNodeDesc expr) {
    for (Entry<String, ExprNodeDesc> entry : opColumnExprMap.entrySet()) {
      ExprNodeDesc thisExpr = entry.getValue();
      if (thisExpr != null && thisExpr.isSame(expr)) {
        return entry.getKey();
      }
    }
    return null;
  }

  protected static boolean hasGroupingSet(ReduceSinkOperator cRS) throws SemanticException {
    GroupByOperator cGBYm = getSingleParent(cRS, GroupByOperator.class);
    if (cGBYm != null && cGBYm.getConf().isGroupingSetsPresent()) {
      return true;
    }
    return false;
  }

  /**
   * @param operator the input operator
   * @param throwException if throw a exception when the input operator has multiple parents
   * @return the single parent or null when the input operator has multiple parents and
   *         throwException is false;
   * @throws HiveException
   */
  protected static Operator<?> getSingleParent(Operator<?> operator,
      boolean throwException) throws SemanticException {
    List<Operator<?>> parents = operator.getParentOperators();
    if (parents != null && parents.size() == 1) {
      return parents.get(0);
    }
    if (throwException) {
      if (parents == null) {
        throw new SemanticException("Operator " + operator.getName() + " (ID: " +
            operator.getIdentifier() + ") does not have any parent, but we expect 1 parent.");
      } else if (parents.size() > 1) {
        throw new SemanticException("Operator " + operator.getName() + " (ID: " +
            operator.getIdentifier() + ") has " + parents.size() +
            " parents, but we expect 1 parent.");
      }
    }
    return null;
  }

  protected static Operator<?> getSingleParent(Operator<?> operator) throws SemanticException {
    return getSingleParent(operator, false);
  }

  /**
   * @param operator the input operator
   * @param throwException if throw a exception when the input operator has multiple children
   * @return the single child or null when the input operator has multiple children and
   *         throwException is false;
   * @throws HiveException
   */
  protected static Operator<?> getSingleChild(Operator<?> operator,
      boolean throwException) throws SemanticException {
    List<Operator<?>> children = operator.getChildOperators();
    if (children != null && children.size() == 1) {
      return children.get(0);
    }
    if (throwException) {
      if (children == null) {
        throw new SemanticException("Operator " + operator.getName() + " (ID: " +
            operator.getIdentifier() + ") does not have any child, but we expect 1 child.");
      } else if (children.size() > 1) {
        throw new SemanticException("Operator " + operator.getName() + " (ID: " +
            operator.getIdentifier() + ") has " + children.size() +
            " children, but we expect 1 child.");
      }
    }
    return null;
  }

  protected static Operator<?> getSingleChild(Operator<?> operator) throws SemanticException {
    return getSingleChild(operator, false);
  }

  protected static <T> T getSingleChild(Operator<?> operator, Class<T> type)
      throws SemanticException {
    Operator<?> parent = getSingleChild(operator);
    return type.isInstance(parent) ? (T)parent : null;
  }

  protected static <T> T getSingleParent(Operator<?> operator, Class<T> type)
      throws SemanticException {
    Operator<?> parent = getSingleParent(operator);
    return type.isInstance(parent) ? (T)parent : null;
  }

  protected static Operator<?> getStartForGroupBy(ReduceSinkOperator cRS)
      throws SemanticException {
    Operator<? extends Serializable> parent = getSingleParent(cRS);
    return parent instanceof GroupByOperator ? parent : cRS;  // skip map-aggr GBY
  }


  protected static boolean[] getSortedTags(JoinOperator joinOp) {
    boolean[] result = new boolean[joinOp.getParentOperators().size()];
    for (int tag = 0; tag < result.length; tag++) {
      result[tag] = isSortedTag(joinOp, tag);
    }
    return result;
  }

  // for left outer joins, left alias is sorted but right alias might be not
  // (nulls, etc.). vice versa.
  protected static boolean isSortedTag(JoinOperator joinOp, int tag) {
    for (JoinCondDesc cond : joinOp.getConf().getConds()) {
      switch (cond.getType()) {
        case JoinDesc.LEFT_OUTER_JOIN:
          if (cond.getRight() == tag) {
            return false;
          }
          continue;
        case JoinDesc.RIGHT_OUTER_JOIN:
          if (cond.getLeft() == tag) {
            return false;
          }
          continue;
        case JoinDesc.FULL_OUTER_JOIN:
          if (cond.getLeft() == tag || cond.getRight() == tag) {
            return false;
          }
      }
    }
    return true;
  }

  protected static int indexOf(ExprNodeDesc cexpr, ExprNodeDesc[] pexprs, Operator child,
      Operator[] parents, boolean[] sorted) throws SemanticException {
    for (int tag = 0; tag < parents.length; tag++) {
      if (sorted[tag] &&
          pexprs[tag].isSame(ExprNodeDescUtils.backtrack(cexpr, child, parents[tag]))) {
        return tag;
      }
    }
    return -1;
  }

  protected static <T extends Operator<?>> T findPossibleParent(Operator<?> start, Class<T> target,
      boolean trustScript) throws SemanticException {
    T[] parents = findPossibleParents(start, target, trustScript);
    return parents != null && parents.length == 1 ? parents[0] : null;
  }

  @SuppressWarnings("unchecked")
  protected static <T extends Operator<?>> T[] findPossibleParents(
      Operator<?> start, Class<T> target,
      boolean trustScript) throws SemanticException {
    Operator<?> cursor = getSingleParent(start);
    for (; cursor != null; cursor = getSingleParent(cursor)) {
      if (target.isAssignableFrom(cursor.getClass())) {
        T[] array = (T[]) Array.newInstance(target, 1);
        array[0] = (T) cursor;
        return array;
      }
      if (cursor instanceof JoinOperator) {
        return findParents((JoinOperator) cursor, target);
      }
      if (cursor instanceof ScriptOperator && !trustScript) {
        return null;
      }
      if (!(cursor instanceof SelectOperator
          || cursor instanceof FilterOperator
          || cursor instanceof ExtractOperator
          || cursor instanceof ForwardOperator
          || cursor instanceof ScriptOperator
          || cursor instanceof ReduceSinkOperator)) {
        return null;
      }
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  protected static <T extends Operator<?>> T[] findParents(JoinOperator join, Class<T> target)
      throws SemanticException {
    List<Operator<?>> parents = join.getParentOperators();
    T[] result = (T[]) Array.newInstance(target, parents.size());
    for (int tag = 0; tag < result.length; tag++) {
      Operator<?> cursor = parents.get(tag);
      for (; cursor != null; cursor = getSingleParent(cursor)) {
        if (target.isAssignableFrom(cursor.getClass())) {
          result[tag] = (T) cursor;
          break;
        }
      }
      if (result[tag] == null) {
        throw new IllegalStateException("failed to find " + target.getSimpleName()
            + " from " + join + " on tag " + tag);
      }
    }
    return result;
  }

  /**
   * Search the query plan tree from startPoint to the bottom. If there is no ReduceSinkOperator
   * between startPoint and the corresponding TableScanOperator, return the corresponding
   * TableScanOperator. Otherwise, return null.
   * @param startPoint the operator which the search will start at
   * @return the TableScanOperator traced from startPoint. Null, if the search encounters any
   * ReduceSinkOperator.
   */
  protected static TableScanOperator findTableScanOperator(
      Operator<? extends OperatorDesc> startPoint) {
    Operator<? extends OperatorDesc> thisOp = startPoint.getParentOperators().get(0);
    while (true) {
      if (thisOp.getName().equals(ReduceSinkOperator.getOperatorName())) {
        return null;
      } else if (thisOp.getName().equals(TableScanOperator.getOperatorName())) {
        return (TableScanOperator) thisOp;
      } else {
        if (thisOp.getParentOperators() != null) {
          thisOp = thisOp.getParentOperators().get(0);
        } else {
          break;
        }
      }
    }
    return null;
  }

  /**
   * Find all sibling ReduceSinkOperators (which have the same child operator of op) of op (op
   * included).
   * @throws SemanticException
   */
  public static List<ReduceSinkOperator> findSiblingReduceSinkOperators(ReduceSinkOperator op)
      throws SemanticException {
    List<ReduceSinkOperator> siblingRSs = new ArrayList<ReduceSinkOperator>();
    Operator<? extends OperatorDesc> child = getSingleChild(op, true);
    for (Operator<? extends OperatorDesc> parent: child.getParentOperators()) {
      if (parent instanceof ReduceSinkOperator) {
        siblingRSs.add((ReduceSinkOperator)parent);
      } else {
        throw new SemanticException("An sibling of a ReduceSinkOperatpr is not a" +
            "ReduceSinkOperatpr.");
      }
    }
    return siblingRSs;
  }
  /**
   * Find all sibling ReduceSinkOperators (which have the same child operator of op) of op (op
   * included).
   * @throws SemanticException
   */
  public static List<ReduceSinkOperator> findSiblingReduceSinkOperators(ReduceSinkOperator op,Operator<? extends OperatorDesc> chop)
      throws SemanticException {
    List<ReduceSinkOperator> siblingRSs = new ArrayList<ReduceSinkOperator>();
    Operator<? extends OperatorDesc> child=null;

    for(Operator<? extends OperatorDesc> current :op.getChildOperators()){
      if(op.getChildOperators().size()==1){
        child=current;
      }else{
        if(current.equals(chop)){
          child=current;
        }
      }
    }
    if(child==null){
      Log.info("this RS op doesnot have a child fake operator,mabe it's a union query ");
      return siblingRSs;
      //throw new SemanticException("there is no Operator:"+chop.getName());
    }
   // Operator<? extends OperatorDesc> child = getSingleChild(op, true);
    for (Operator<? extends OperatorDesc> parent: child.getParentOperators()) {
      if (parent instanceof ReduceSinkOperator) {
        siblingRSs.add((ReduceSinkOperator)parent);
      } else {
        throw new SemanticException("An sibling of a ReduceSinkOperatpr is not a" +
            "ReduceSinkOperatpr.");
      }
    }
    return siblingRSs;
  }


  /**
   * Find all sibling operators (which have the same child operator of op) of op (op
   * included).
   * @throws SemanticException
   */
  public static List<Operator<? extends OperatorDesc>> findSiblingOperators(
      Operator<? extends OperatorDesc> op)
      throws SemanticException {
    Operator<? extends OperatorDesc> child = getSingleChild(op, true);
    return child.getParentOperators();
  }

  protected static SelectOperator replaceReduceSinkWithSelectOperator(ReduceSinkOperator childRS,
      ParseContext context, AbstractCorrelationProcCtx procCtx) throws SemanticException {
    SelectOperator select = replaceOperatorWithSelect(childRS, context, procCtx);
    select.getConf().setOutputColumnNames(childRS.getConf().getOutputValueColumnNames());
    select.getConf().setColList(childRS.getConf().getValueCols());
    return select;
  }

  // replace the cRS to SEL operator
  // If child if cRS is EXT, EXT also should be removed
  protected static SelectOperator replaceOperatorWithSelect(Operator<?> operator,
      ParseContext context, AbstractCorrelationProcCtx procCtx)
      throws SemanticException {
    RowResolver inputRR = context.getOpParseCtx().get(operator).getRowResolver();
    SelectDesc select = new SelectDesc(null, null);

    Operator<?> parent = getSingleParent(operator);
    Operator<?> child = getSingleChild(operator);

    parent.getChildOperators().clear();

    SelectOperator sel = (SelectOperator) putOpInsertMap(
        OperatorFactory.getAndMakeChild(select, new RowSchema(inputRR
            .getColumnInfos()), parent), inputRR, context);

    sel.setColumnExprMap(operator.getColumnExprMap());

    sel.setChildOperators(operator.getChildOperators());
    for (Operator<? extends Serializable> ch : operator.getChildOperators()) {
      ch.replaceParent(operator, sel);
    }
    if (child instanceof ExtractOperator) {
      removeOperator(child, getSingleChild(child), sel, context);
      procCtx.addRemovedOperator(child);
    }
    operator.setChildOperators(null);
    operator.setParentOperators(null);
    procCtx.addRemovedOperator(operator);
    return sel;
  }

  protected static void removeReduceSinkForGroupBy(ReduceSinkOperator cRS, GroupByOperator cGBYr,
      ParseContext context, AbstractCorrelationProcCtx procCtx) throws SemanticException {

    Operator<?> parent = getSingleParent(cRS);

    if (parent instanceof GroupByOperator) {
      // pRS-cGBYm-cRS-cGBYr (map aggregation) --> pRS-cGBYr(COMPLETE)
      // copies desc of cGBYm to cGBYr and remove cGBYm and cRS
      GroupByOperator cGBYm = (GroupByOperator) parent;

      cGBYr.getConf().setKeys(ExprNodeDescUtils.backtrack(ExprNodeDescUtils.backtrack(cGBYr
              .getConf().getKeys(), cGBYr, cRS), cRS, cGBYm));
      cGBYr.getConf().setAggregators(cGBYm.getConf().getAggregators());
      for (AggregationDesc aggr : cGBYm.getConf().getAggregators()) {
        aggr.setMode(GenericUDAFEvaluator.Mode.COMPLETE);
      }
      cGBYr.setColumnExprMap(cGBYm.getColumnExprMap());
      cGBYr.setSchema(cGBYm.getSchema());
      RowResolver resolver = context.getOpParseCtx().get(cGBYm).getRowResolver();
      context.getOpParseCtx().get(cGBYr).setRowResolver(resolver);
    } else {
      // pRS-cRS-cGBYr (no map aggregation) --> pRS-cGBYr(COMPLETE)
      // revert expressions of cGBYr to that of cRS
      cGBYr.getConf().setKeys(ExprNodeDescUtils.backtrack(cGBYr.getConf().getKeys(), cGBYr, cRS));
      for (AggregationDesc aggr : cGBYr.getConf().getAggregators()) {
        aggr.setParameters(ExprNodeDescUtils.backtrack(aggr.getParameters(), cGBYr, cRS));
      }

      Map<String, ExprNodeDesc> oldMap = cGBYr.getColumnExprMap();
      RowResolver oldRR = context.getOpParseCtx().get(cGBYr).getRowResolver();

      Map<String, ExprNodeDesc> newMap = new HashMap<String, ExprNodeDesc>();
      RowResolver newRR = new RowResolver();

      List<String> outputCols = cGBYr.getConf().getOutputColumnNames();
      for (int i = 0; i < outputCols.size(); i++) {
        String colName = outputCols.get(i);
        String[] nm = oldRR.reverseLookup(colName);
        ColumnInfo colInfo = oldRR.get(nm[0], nm[1]);
        newRR.put(nm[0], nm[1], colInfo);
        ExprNodeDesc colExpr = ExprNodeDescUtils.backtrack(oldMap.get(colName), cGBYr, cRS);
        if (colExpr != null) {
          newMap.put(colInfo.getInternalName(), colExpr);
        }
      }
      cGBYr.setColumnExprMap(newMap);
      cGBYr.setSchema(new RowSchema(newRR.getColumnInfos()));
      context.getOpParseCtx().get(cGBYr).setRowResolver(newRR);
    }
    cGBYr.getConf().setMode(GroupByDesc.Mode.COMPLETE);

    removeOperator(cRS, cGBYr, parent, context);
    procCtx.addRemovedOperator(cRS);

    if (parent instanceof GroupByOperator) {
      removeOperator(parent, cGBYr, getSingleParent(parent), context);
      procCtx.addRemovedOperator(cGBYr);
    }
  }

  /** throw a exception if the input operator is null
   * @param operator
   * @throws HiveException
   */
  protected static void isNullOperator(Operator<?> operator) throws SemanticException {
    if (operator == null) {
      throw new SemanticException("Operator " + operator.getName() + " (ID: " +
          operator.getIdentifier() + ") is null.");
    }
  }

  /**
   * @param newOperator the operator will be inserted between child and parent
   * @param child
   * @param parent
   * @param context
   * @throws HiveException
   */
  protected static void insertOperatorBetween(
      Operator<?> newOperator, Operator<?> parent, Operator<?> child)
          throws SemanticException {
    isNullOperator(newOperator);
    isNullOperator(parent);
    isNullOperator(child);

    if (parent != getSingleParent(child)) {
      throw new SemanticException("Operator " + parent.getName() + " (ID: " +
          parent.getIdentifier() + ") is not the only parent of Operator " +
          child.getName() + " (ID: " + child.getIdentifier() + ")");
    }
    if (child != getSingleChild(parent)) {
      throw new SemanticException("Operator " + child.getName() + " (ID: " +
          child.getIdentifier() + ") is not the only child of Operator " +
          parent.getName() + " (ID: " + parent.getIdentifier() + ")");
    }

    newOperator.setParentOperators(Utilities.makeList(parent));
    newOperator.setChildOperators(Utilities.makeList(child));

    child.setParentOperators(Utilities.makeList(newOperator));
    parent.setChildOperators(Utilities.makeList(newOperator));
  }

  protected static void removeOperator(Operator<?> target, Operator<?> child, Operator<?> parent,
      ParseContext context) {
    for (Operator<?> aparent : target.getParentOperators()) {
      aparent.replaceChild(target, child);
    }
    for (Operator<?> achild : target.getChildOperators()) {
      achild.replaceParent(target, parent);
    }
    target.setChildOperators(null);
    target.setParentOperators(null);
    context.getOpParseCtx().remove(target);
  }

  protected static Operator<? extends Serializable> putOpInsertMap(Operator<?> op, RowResolver rr,
      ParseContext context) {
    OpParseContext ctx = new OpParseContext(rr);
    context.getOpParseCtx().put(op, ctx);
    return op;
  }
}
