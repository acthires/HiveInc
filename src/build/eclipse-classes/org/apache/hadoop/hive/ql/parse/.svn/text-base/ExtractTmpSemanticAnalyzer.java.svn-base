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
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.antlr.runtime.CommonToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.TableType;
import org.apache.hadoop.hive.metastore.Warehouse;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.ql.CommandNeedRetryException;
import org.apache.hadoop.hive.ql.Context;
import org.apache.hadoop.hive.ql.Driver;
import org.apache.hadoop.hive.ql.ErrorMsg;
import org.apache.hadoop.hive.ql.IncDriver.incCtx;
import org.apache.hadoop.hive.ql.exec.ColumnInfo;
import org.apache.hadoop.hive.ql.exec.FileSinkOperator;
import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.exec.OperatorFactory;
import org.apache.hadoop.hive.ql.exec.ReduceSinkOperator;
import org.apache.hadoop.hive.ql.exec.RowSchema;
import org.apache.hadoop.hive.ql.exec.Utilities;
import org.apache.hadoop.hive.ql.hooks.WriteEntity;
import org.apache.hadoop.hive.ql.io.HiveOutputFormat;
import org.apache.hadoop.hive.ql.metadata.DummyPartition;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.metadata.Partition;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.optimizer.Optimizer;
import org.apache.hadoop.hive.ql.parse.BaseSemanticAnalyzer.tableSpec.SpecType;
import org.apache.hadoop.hive.ql.plan.DynamicPartitionCtx;
import org.apache.hadoop.hive.ql.plan.ExprNodeColumnDesc;
import org.apache.hadoop.hive.ql.plan.ExprNodeDesc;
import org.apache.hadoop.hive.ql.plan.FileSinkDesc;
import org.apache.hadoop.hive.ql.plan.HiveOperation;
import org.apache.hadoop.hive.ql.plan.ListBucketingCtx;
import org.apache.hadoop.hive.ql.plan.LoadTableDesc;
import org.apache.hadoop.hive.ql.plan.OperatorDesc;
import org.apache.hadoop.hive.ql.plan.PlanUtils;
import org.apache.hadoop.hive.ql.plan.TableDesc;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;

/**
 * IncQuerySemanticAnalyzer.
 *
 */
public class ExtractTmpSemanticAnalyzer extends SemanticAnalyzer {
  private static final Log LOG = LogFactory
      .getLog(ExtractTmpSemanticAnalyzer.class);

  private incCtx incCtx = null;
  private final List<ASTNode> addedSubQuery = new ArrayList<ASTNode>();
  //private boolean isCreateTmpPhase = false;
  private ArrayList<String> incTable = null;
  private ArrayList<ASTNode>  incToken = null;
  private boolean isInsertSubQuery = false;
  private final HashMap<String, String> aliasToTabs = new HashMap<String,String>();
  private final HashMap<String,ASTNode> subQueryaliasToSel = new HashMap<String,ASTNode>();

  //map from [tabName,colName] to newColName in  new added subQuery, avoid same columnName of different table
  private final HashMap<String[],String> colNameMap = new HashMap<String[],String>();

  private ParseContext pCtxInc = null;
  private final List<String> tmpTableCols = new ArrayList<String>();
  private boolean canExtractTmp = false;

  //following is to record multi tmp tables information
  private final HashMap<String,ASTNode> subQNameToAST = new HashMap<String,ASTNode>();
  private final HashMap<String,ReduceSinkOperator> subQNameToRS = new HashMap<String,ReduceSinkOperator>();
  private final HashMap<ReduceSinkOperator,String> RSToTmpName = new HashMap<ReduceSinkOperator,String>();
  private final HashMap<String,String[]> subQNameToAlias = new HashMap<String,String[]>();
  private final HashMap<String,String[]> tmpNameToAlias = new HashMap<String,String[]>();
  private final HashMap<String,ASTNode> subQNameToCurrRoot = new HashMap<String,ASTNode>();

  public ExtractTmpSemanticAnalyzer(HiveConf conf) throws SemanticException {
    super(conf);
  }

  public boolean canExtractTmp(){
    return canExtractTmp;
  }

  public ExtractTmpSemanticAnalyzer(HiveConf conf, ASTNode tree, incCtx ctx) throws SemanticException {
     super(conf);
     this.ast = tree;
     incCtx = ctx;
     incTable = incCtx.getParserRes().getAliasNoOuter();
     incToken = incCtx.getParserRes().getIncTabRef();
  }

  @Override
  public void analyze(ASTNode ast, Context origCtx) throws SemanticException {
    QB qb;
    QBParseInfo qbp;

    // initialize QB
    init();
  }

  @Override
  protected Operator genJoinReduceSinkChild(QB qb, QBJoinTree joinTree,
      Operator child, String srcName, int pos) throws SemanticException {
    RowResolver inputRS = opParseCtx.get(child).getRowResolver();
    RowResolver outputRS = new RowResolver();
    ArrayList<String> outputColumns = new ArrayList<String>();
    ArrayList<ExprNodeDesc> reduceKeys = new ArrayList<ExprNodeDesc>();

    // Compute join keys and store in reduceKeys
    ArrayList<ASTNode> exprs = joinTree.getExpressions().get(pos);
    for (int i = 0; i < exprs.size(); i++) {
      ASTNode expr = exprs.get(i);
      reduceKeys.add(genExprNodeDesc(expr, inputRS));
    }

    // Walk over the input row resolver and copy in the output
    ArrayList<ExprNodeDesc> reduceValues = new ArrayList<ExprNodeDesc>();
    Iterator<String> tblNamesIter = inputRS.getTableNames().iterator();
    Map<String, ExprNodeDesc> colExprMap = new HashMap<String, ExprNodeDesc>();
    while (tblNamesIter.hasNext()) {
      String src = tblNamesIter.next();
      HashMap<String, ColumnInfo> fMap = inputRS.getFieldMap(src);
      for (Map.Entry<String, ColumnInfo> entry : fMap.entrySet()) {
        String field = entry.getKey();
        ColumnInfo valueInfo = entry.getValue();
        ExprNodeColumnDesc inputExpr = new ExprNodeColumnDesc(valueInfo
            .getType(), valueInfo.getInternalName(), valueInfo.getTabAlias(),
            valueInfo.getIsVirtualCol());
        reduceValues.add(inputExpr);
        if (outputRS.get(src, field) == null) {
          String col = getColumnInternalName(reduceValues.size() - 1);
          outputColumns.add(col);
          ColumnInfo newColInfo = new ColumnInfo(Utilities.ReduceField.VALUE
              .toString()
              + "." + col, valueInfo.getType(), src, valueInfo
              .getIsVirtualCol(), valueInfo.isHiddenVirtualCol());

          colExprMap.put(newColInfo.getInternalName(), inputExpr);
          outputRS.put(src, field, newColInfo);
        }
      }
    }

    int numReds = -1;

    // Use only 1 reducer in case of cartesian product
    if (reduceKeys.size() == 0) {
      numReds = 1;

      // Cartesian product is not supported in strict mode
      if (conf.getVar(HiveConf.ConfVars.HIVEMAPREDMODE).equalsIgnoreCase(
          "strict")) {
        throw new SemanticException(ErrorMsg.NO_CARTESIAN_PRODUCT.getMsg());
      }
    }

    ReduceSinkOperator rsOp = (ReduceSinkOperator) putOpInsertMap(
        OperatorFactory.getAndMakeChild(PlanUtils.getReduceSinkDesc(reduceKeys,
            reduceValues, outputColumns, false, joinTree.getNextTag(),
            reduceKeys.size(), numReds), new RowSchema(outputRS
            .getColumnInfos()), child), outputRS);
    rsOp.setColumnExprMap(colExprMap);
    rsOp.setInputAlias(srcName);

    /**
     * Record tmpTable' RS, which will be used to get tmpTable's columns later.
     */
      if(pos == 1){//all tmpTables are right child of join
        for(String subQ:subQNameToAlias.keySet()){
          if(srcName.equals(subQ.toLowerCase())){
            subQNameToRS.put(subQ, rsOp);
          }
        }
      }

    return rsOp;
  }

  public void genAndrunCrtTmpQuery(ASTNode ast) throws HiveException, SerDeException{

    ASTNode child = ast;
    this.ast = ast;
    viewsExpanded = new ArrayList<String>();

    // analyze and process the position alias
    processPositionAlias(ast);

    // analyze create table command
    if (ast.getToken().getType() == HiveParser.TOK_CREATETABLE) {
      // if it is not CTAS, we don't need to go further and just return
      if ((child = analyzeCreateTable(ast, qb)) == null) {
        return;
      }
    } else {
      SessionState.get().setCommandType(HiveOperation.QUERY);
    }

    // analyze create view command
    if (ast.getToken().getType() == HiveParser.TOK_CREATEVIEW ||
        ast.getToken().getType() == HiveParser.TOK_ALTERVIEW_AS) {
      child = analyzeCreateView(ast, qb);
      SessionState.get().setCommandType(HiveOperation.CREATEVIEW);
      if (child == null) {
        return;
      }
      viewSelect = child;
      // prevent view from referencing itself
      viewsExpanded.add(SessionState.get().getCurrentDatabase() + "." + createVwDesc.getViewName());
    }

    LOG.info("Gen and run creat tmp query Begin!");
    //isCreateTmpPhase = true;

    //1. flatten joins that in different subQ into same subQ
    if(HiveConf.getBoolVar(conf, HiveConf.ConfVars.HIVEINCFLATTENSUBQS)){
      flattensubQs(child);
    }

    //2.1 joinReorder to move the incTable outest
    //2.2 extract the pos of tmp table
    HashMap<ASTNode,Boolean> includeIncToken = new HashMap<ASTNode,Boolean>();//List of TOK_TAB,TOK_SUBQ which include incTable
    joinReordeAndRecordPosOfTmp(child,child,includeIncToken);
    if(isInsertSubQuery){
      LOG.info("After joinReorder, whole ast is :" + child.dump());
    }else{
      LOG.info("No tmp table to extract!");
      return;
    }

    Phase1Ctx ctx_1 = initPhase1Ctx();
    if (!doPhase1(child, qb, ctx_1)) {
      // if phase1Result false return
      return;
    }

    getMetaData(qb);

    genPlan(qb);

    pCtxInc = new ParseContext(conf, qb, child, opToPartPruner,
        opToPartList, topOps, topSelOps, opParseCtx, joinContext, smbMapJoinContext,
        topToTable, topToTableProps, fsopToTable,
        loadTableWork, loadFileWork, ctx, idToTableNameMap, destTableId, uCtx,
        listMapJoinOpsNoReducer, groupOpToInputTables, prunedPartitions,
        opToSamplePruner, globalLimitCtx, nameToSplitSample, inputs, rootTasks,
        opToPartToSkewedPruner, viewAliasToInput,
        reduceSinkOperatorsAddedByEnforceBucketingSorting, queryProperties);

    // Generate table access stats if required
    if (HiveConf.getBoolVar(this.conf, HiveConf.ConfVars.HIVE_STATS_COLLECT_TABLEKEYS) == true) {
      TableAccessAnalyzer tableAccessAnalyzer = new TableAccessAnalyzer(pCtxInc);
      setTableAccessInfo(tableAccessAnalyzer.analyzeTableAccess());
    }

    if (LOG.isDebugEnabled()) {
      LOG.debug("Before logical optimization\n" + Operator.toString(pCtxInc.getTopOps().values()));
    }

    Optimizer optm = new Optimizer();
    optm.setPctx(pCtxInc);
    optm.initialize(conf);
    pCtxInc = optm.optimize();

    if (LOG.isDebugEnabled()) {
      LOG.debug("After logical optimization\n" + Operator.toString(pCtxInc.getTopOps().values()));
    }

    // Generate column access stats if required - wait until column pruning takes place
    // during optimization
    if (HiveConf.getBoolVar(this.conf, HiveConf.ConfVars.HIVE_STATS_COLLECT_SCANCOLS) == true) {
      ColumnAccessAnalyzer columnAccessAnalyzer = new ColumnAccessAnalyzer(pCtxInc);
      setColumnAccessInfo(columnAccessAnalyzer.analyzeColumnAccess());
    }


    for(String subQname:subQNameToRS.keySet()){
      ReduceSinkOperator tmpRSOp = subQNameToRS.get(subQname);

      String name = getTmpTableName();
      String crtTmpQL = genCrtTmpQL(tmpRSOp,name);
      RSToTmpName.put(tmpRSOp, name);

      String[] aliases = new String[subQNameToAlias.get(subQname).length+1];
      for(int i=0; i< subQNameToAlias.get(subQname).length;i++){
        aliases[i] = subQNameToAlias.get(subQname)[i];
      }
      aliases[subQNameToAlias.get(subQname).length] = subQname;
      tmpNameToAlias.put(name, aliases);

      try {
        (new Driver(conf)).run(crtTmpQL);
      } catch (CommandNeedRetryException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      canExtractTmp = true;
    }

  }

  private void flattensubQs(ASTNode root) throws HiveException, SerDeException {
    //Record colNames of tables or subQ we have encountered to avoid get colNames of them repeatedly
    HashMap<ASTNode,List<String>> TabASTToColNames = new HashMap<ASTNode,List<String>>();
    do{
      List<ASTNode> subQToDel = new ArrayList<ASTNode>();
      findSubQToDel(root,subQToDel);
      if(subQToDel.size() == 0){
        LOG.info("No subQ to flatten!");
        break;
      }else{
        for(int i= subQToDel.size()-1; i>= 0; i--){
            flattenCurSubQ(subQToDel.get(i),TabASTToColNames);
        }
        LOG.info("After flatten subQs, whole ast is:" + root.dump());
      }
    }while(true);
  }

/**
 *
 * @param curSubQ
 * @param tabASTToColNames: Record  colNames of tabs or subQs that have been encountered so for for later use;
 * @throws HiveException
 * @throws SerDeException
 */
  private void flattenCurSubQ(ASTNode curSubQ, HashMap<ASTNode,List<String>> tabASTToColNames) throws HiveException, SerDeException {
    LOG.info("Flatten subQuery: " + curSubQ.getChild(1).getText());
    ASTNode parentJoin = (ASTNode) curSubQ.getParent();
    ASTNode insert = (ASTNode) curSubQ.getChild(0).getChild(1);
    ASTNode from = (ASTNode) curSubQ.getChild(0).getChild(0);
    ASTNode directOuterQuery = parentJoin;
    while(directOuterQuery.getToken().getType()!= HiveParser.TOK_QUERY){
      directOuterQuery = (ASTNode) directOuterQuery.getParent();
    }
    ASTNode directOuterInsert = (ASTNode) directOuterQuery.getChild(1);

    //1.Change currSubQ's direct outer query:
    //For select where, groupBy,orderby: change "colName" to "tabName.colName".
    LOG.info("Begin add tabName to " + curSubQ.getChild(1).getText() + "'s direct outer Query:");
    ASTNode directOuterFrom = (ASTNode) directOuterQuery.getChild(0);
    HashMap<String,String> outerColNameToTabName = new HashMap<String,String>();
    findColSource(directOuterFrom,outerColNameToTabName,tabASTToColNames);
    addTabNameInsert(directOuterInsert,outerColNameToTabName);

    //2. move currSubQ's from's child to parentJoin's left child.
    int subQPos = curSubQ.getChildIndex();
    if(subQPos == 1){
      if(from.getChild(0).getType() == HiveParser.TOK_TABREF ||
          from.getChild(0).getType() == HiveParser.TOK_SUBQUERY ){
        LOG.info("move " + curSubQ.getChild(1).getText() + "'s from child to parentJoin's right child");
        parentJoin.setChild(subQPos, from.getChild(0));
        from.getChild(0).setParent(parentJoin);
      }
      else if((parentJoin.getChild(0).getType() == HiveParser.TOK_TABREF ||
          parentJoin.getChild(0).getType() == HiveParser.TOK_SUBQUERY)
          && isJoinToken((ASTNode) from.getChild(0))){
        LOG.info("move parentJoin's left child to parentJoin's right child" );
        parentJoin.setChild(1, parentJoin.getChild(0));
        LOG.info("move " + curSubQ.getChild(1).getText() + "'s from child to parentJoin's left child");
        parentJoin.setChild(0, from.getChild(0));

        ASTNodeUtils.changeJoinType(parentJoin);
      }
    }else if(subQPos == 0){
      LOG.info("move " + curSubQ.getChild(1).getText() + "'s from child to parentJoin's left child");
      parentJoin.setChild(subQPos, from.getChild(0));
      from.getChild(0).setParent(parentJoin);
    }

    //3 AliasAnalysis
    String subQAlias = unescapeIdentifier(curSubQ.getChild(1).getText().toLowerCase());
    ASTNode suQselect = (ASTNode) insert.getChild(1);
    HashMap<String,String> currSubQColNameToTabName = new HashMap<String,String>();
    findColSource(from,currSubQColNameToTabName,tabASTToColNames);
    //3.1 build map from <subQName,newColName> to <oldTabName,oldColName> for columns in currSubQ's select clause
    HashMap<String[],String[]> newTabColToOld = new HashMap<String[],String[]>();
    buildMapFromNewToOld(suQselect,currSubQColNameToTabName,subQAlias,newTabColToOld);

    //3.2 change reference to subQName of path curSubQ-->stop
    LOG.info("Begin analyze alias after flatten " + curSubQ.getChild(1).getText());
    analyzeAliasBottomUp((ASTNode) directOuterQuery.getParent(),curSubQ,newTabColToOld);

    //4. move currSubQ's where condition to direct Outer Query's where condition.
    if(insert.getChildCount()>=3){
      ASTNode thirdChild = (ASTNode) insert.getChild(2);
      if(thirdChild.getToken().getType() == HiveParser.TOK_WHERE){
        LOG.info("Move where condition of " + curSubQ.getChild(1).getText()
            + " to direct Outer Query's where condition");
        ASTNode whereCond = (ASTNode) thirdChild.getChild(0);
        addTabNameCond(whereCond,currSubQColNameToTabName);
        ASTNodeUtils.moveWhereOuter(whereCond,directOuterInsert);
      }
    }

  }

/**
 * Get map from renamed colName in subQ to source tabName and colName in source table.
 * After remove a TOK_SUBQUERY, we use this map to change outer's cite of this subQ name and renamed colName
 * to corresponding from table name and colName.
 * @param suQselect :select of subQ to remove
 * @param colNameToTabName : Map of all possible colName to it's tableName in the subQ to remove
 * @param subQAlias
 * @param newTabColToOld
 */
  private void buildMapFromNewToOld(ASTNode suQselect, HashMap<String,String> colNameToTabName,
      String subQAlias, HashMap<String[], String[]> newTabColToOld) {
    for(int j=0; j<suQselect.getChildCount(); j++){
      ASTNode selExpr = (ASTNode) suQselect.getChild(j);
      ASTNode child0 = (ASTNode) selExpr.getChild(0);
      String[] oldTabCol = new String[2];
      String[] newTabCol = new String[2];
      if(child0.getToken().getType() == HiveParser.DOT){
        String oldTabName = child0.getChild(0).getChild(0).getText().toLowerCase();
        String oldColName = child0.getChild(1).getText().toLowerCase();
        oldTabCol[0] = oldTabName;
        oldTabCol[1] = oldColName;
      }else if(child0.getToken().getType() == HiveParser.TOK_TABLE_OR_COL){
        String oldColName = child0.getChild(0).getText().toLowerCase();
        String oldTabName = colNameToTabName.get(oldColName);
        oldTabCol[0] = oldTabName;
        oldTabCol[1] = oldColName;
      }
      String newColName = selExpr.getChildCount() == 1 ? oldTabCol[1]
                                                                   : selExpr.getChild(1).getText().toLowerCase();
      newTabCol[0] = subQAlias;
      newTabCol[1] = newColName;
      LOG.info("Build map from " + newTabCol[0] + "." + newTabCol[1] + " to " + oldTabCol[0] + "." + oldTabCol[1]);
      newTabColToOld.put(newTabCol, oldTabCol);
    }
}

  private void addTabNameInsert(ASTNode curr,
      HashMap<String, String> colNameToTabName) {
    switch (curr.getToken().getType()) {
    case HiveParser.TOK_INSERT:
      for (int i = 0; i < curr.getChildCount(); ++i) {
        addTabNameInsert((ASTNode)curr.getChild(i),colNameToTabName);
      }
      break;
    case HiveParser.TOK_SELECT:
    case HiveParser.TOK_SELECTDI:
      for (int i = 0; i < curr.getChildCount(); ++i) {
        ASTNode selExpr = (ASTNode) curr.getChild(i);
        if (selExpr.getToken().getType() == HiveParser.TOK_SELEXPR) {
          ASTNode tmp = (ASTNode)selExpr.getChild(0);
          if(tmp.getToken().getType() == HiveParser.TOK_TABLE_OR_COL){
            ASTNodeUtils.addTabName(tmp,colNameToTabName);
          }else if(tmp.getToken().getType() != HiveParser.DOT) {
            addTabNameCond(tmp,colNameToTabName);
          }
        }
      }
      break;
    case HiveParser.TOK_WHERE:
      ASTNode condn = (ASTNode) curr.getChild(0);
      addTabNameCond(condn,colNameToTabName);
      break;
    case HiveParser.TOK_GROUPBY:
      for (int i = 0; i < curr.getChildCount(); ++i) {
        ASTNode tmp = (ASTNode)curr.getChild(i);
        if(tmp.getToken().getType() == HiveParser.TOK_TABLE_OR_COL){
          ASTNodeUtils.addTabName(tmp,colNameToTabName);
        }else if(tmp.getToken().getType() != HiveParser.DOT) {
          addTabNameCond(tmp,colNameToTabName);
        }
      }
      break;
    case HiveParser.TOK_ORDERBY:
      for (int i = 0; i < curr.getChildCount(); ++i) {
        ASTNode orderExpr = (ASTNode) curr.getChild(i);
        if (orderExpr.getToken().getType() == HiveParser.TOK_TABSORTCOLNAMEASC
            ||orderExpr.getToken().getType() == HiveParser.TOK_TABSORTCOLNAMEDESC) {
          ASTNode tmp = (ASTNode)orderExpr.getChild(0);
          if(tmp.getToken().getType() == HiveParser.TOK_TABLE_OR_COL){
            ASTNodeUtils.addTabName(tmp,colNameToTabName);
          }else if(tmp.getToken().getType() != HiveParser.DOT) {
            addTabNameCond(tmp,colNameToTabName);
          }
        }
      }
      break;
    default:
    }
  }

  /**
   * change "colName" to "tabName.colName"
   * @param colNameToTabName
   * @param whereCond
   */
  private void addTabNameCond(ASTNode Cond, HashMap<String, String> colNameToTabName) {

    if (Cond == null) {
      return ;
    }

    switch (Cond.getToken().getType()) {

    case HiveParser.KW_OR:
    case HiveParser.KW_AND:
      addTabNameCond((ASTNode) Cond.getChild(0),colNameToTabName);
      addTabNameCond((ASTNode) Cond.getChild(1),colNameToTabName);

      break;
    case HiveParser.TOK_FUNCTION:
    case HiveParser.TOK_FUNCTIONDI:
    case HiveParser.KW_NOT:
      for(int i=0;i<Cond.getChildCount();i++){
        ASTNode tmp = (ASTNode) Cond.getChild(i);
        if(tmp.getToken().getType() == HiveParser.TOK_TABLE_OR_COL){
          ASTNodeUtils.addTabName(tmp,colNameToTabName);
        }else if(tmp.getToken().getType() != HiveParser.DOT){
          addTabNameCond(tmp,colNameToTabName);
        }
      }
      break;
    case HiveParser.EQUAL_NS:
    case HiveParser.EQUAL:
    case HiveParser.KW_LIKE:
    case HiveParser.GREATERTHAN:
    case HiveParser.GREATERTHANOREQUALTO:
    case HiveParser.LESSTHAN:
    case HiveParser.LESSTHANOREQUALTO:
    case HiveParser.NOTEQUAL:
    case HiveParser.STAR:
    case HiveParser.MINUS:
    case HiveParser.PLUS:
    case HiveParser.DIV:
    case HiveParser.DIVIDE:
      ASTNode leftAST = (ASTNode) Cond.getChild(0);
      if(leftAST.getToken().getType() == HiveParser.TOK_TABLE_OR_COL){
        ASTNodeUtils.addTabName(leftAST,colNameToTabName);
      }else if(leftAST.getToken().getType() != HiveParser.DOT){
        addTabNameCond(leftAST,colNameToTabName);
      }

      ASTNode rightAST = (ASTNode) Cond.getChild(1);
      if(rightAST.getToken().getType() == HiveParser.TOK_TABLE_OR_COL){
        ASTNodeUtils.addTabName(rightAST,colNameToTabName);
      }else if(rightAST.getToken().getType() != HiveParser.DOT){
        addTabNameCond(rightAST,colNameToTabName);
      }
      break;
    case HiveParser.TOK_TABLE_OR_COL:
      ASTNodeUtils.addTabName(Cond,colNameToTabName);
      break;
      default:

    }

  }

  private void analyzeAliasBottomUp(ASTNode stop, ASTNode curr,
      HashMap<String[], String[]> sourceToDest) throws HiveException, SerDeException {
    if(curr == stop){
      return;
    }
    switch(curr.getToken().getType()){
    case HiveParser.TOK_QUERY:
      ASTNode insert = (ASTNode) curr.getChild(1);//TOK_INSERT
      analyzeAliasTopDown(insert,sourceToDest);
      break;

    case HiveParser.TOK_JOIN:
    case HiveParser.TOK_CROSSJOIN:
    case HiveParser.TOK_LEFTOUTERJOIN:
    case HiveParser.TOK_RIGHTOUTERJOIN:
    case HiveParser.TOK_FULLOUTERJOIN:
    case HiveParser.TOK_LEFTSEMIJOIN:
    case HiveParser.TOK_UNIQUEJOIN:
      ASTNode condn = (ASTNode) curr.getChild(2);//join's condition
      changeConditionAlias(condn,sourceToDest);
      break;
    default:

    }

    ASTNode next = (ASTNode) curr.getParent();
    analyzeAliasBottomUp(stop,next,sourceToDest);
  }

  private void analyzeAliasTopDown(ASTNode curr, HashMap<String[], String[]> sourceToDest) {
    if(curr == null){
      return;
    }
    switch(curr.getToken().getType()){
    case HiveParser.TOK_INSERT:
      int child_count = curr.getChildCount();
      for (int child_pos = 0; child_pos < child_count; ++child_pos) {
        ASTNode next = (ASTNode) curr.getChild(child_pos);
        analyzeAliasTopDown(next,sourceToDest);
      }
      break;
    case HiveParser.TOK_SELECT:
    case HiveParser.TOK_SELECTDI:
      for (int i = 0; i < curr.getChildCount(); ++i) {
        ASTNode selExpr = (ASTNode) curr.getChild(i);
        if (selExpr.getToken().getType() == HiveParser.TOK_SELEXPR) {
          ASTNode child0 = (ASTNode)selExpr.getChild(0);
          // rename the colName to original colName;
          if(selExpr.getChildCount() == 1 ){
            if(child0.getToken().getType() == HiveParser.DOT && child0.getChildCount() == 2){
              String newColName = child0.getChild(1).getText().toLowerCase();
              ASTNode newColNameAST = new ASTNode(new CommonToken(HiveParser.Identifier,newColName));
              selExpr.addChild(newColNameAST);
            }else if(child0.getToken().getType() == HiveParser.TOK_TABLE_OR_COL){
              String newColName = child0.getChild(0).getText().toLowerCase();
              ASTNode newColNameAST = new ASTNode(new CommonToken(HiveParser.Identifier,newColName));
              selExpr.addChild(newColNameAST);
            }
          }
          // change the tabName.colName to DestTabName.DestColName;
          if(child0.getToken().getType() == HiveParser.DOT && child0.getChildCount() == 2){
            changTabColwithDot(child0,sourceToDest);
          }else if(child0.getToken().getType() == HiveParser.TOK_TABLE_OR_COL){
            changColName(child0,sourceToDest);
          }else{
            changeConditionAlias(child0,sourceToDest);
          }
        }
      }
      break;
    case HiveParser.TOK_WHERE:
      ASTNode condn = (ASTNode) curr.getChild(0);
      changeConditionAlias(condn,sourceToDest);
      break;
    case HiveParser.TOK_GROUPBY:
      for (int i = 0; i < curr.getChildCount(); ++i) {
        ASTNode tmp = (ASTNode)curr.getChild(i);
        if(tmp.getToken().getType() == HiveParser.DOT && tmp.getChildCount() == 2){
          changTabColwithDot(tmp,sourceToDest);
        }else if(tmp.getToken().getType() == HiveParser.TOK_TABLE_OR_COL){
          changColName(tmp,sourceToDest);
        }
      }
      break;

      //Attentation: don's change TOK_ORDERBY's references!!!
      //Because we have renamed columns to original colName in TOK_SELECT

 /*   case HiveParser.TOK_ORDERBY:
      for (int i = 0; i < curr.getChildCount(); ++i) {
        ASTNode orderExpr = (ASTNode) curr.getChild(i);
        if (orderExpr.getToken().getType() == HiveParser.TOK_TABSORTCOLNAMEASC
            ||orderExpr.getToken().getType() == HiveParser.TOK_TABSORTCOLNAMEDESC) {
          ASTNode tmp = (ASTNode)orderExpr.getChild(0);
          if(tmp.getToken().getType() == HiveParser.DOT && tmp.getChildCount() == 2){
            changTabColwithDot(tmp,sourceToDest);
          }else if(tmp.getToken().getType() == HiveParser.TOK_TABLE_OR_COL){
            changColName(tmp,sourceToDest);
          }
        }
      }
      break;*/
    default:

    }

  }

  private void changeConditionAlias(ASTNode Cond, HashMap<String[], String[]> sourceToDest) {
    if (Cond == null) {
      return ;
    }

    switch (Cond.getToken().getType()) {

    case HiveParser.KW_OR:
    case HiveParser.KW_AND:
      changeConditionAlias((ASTNode) Cond.getChild(0), sourceToDest);
      changeConditionAlias((ASTNode) Cond.getChild(1), sourceToDest);

      break;
    case HiveParser.TOK_FUNCTION:
    case HiveParser.TOK_FUNCTIONDI:
    case HiveParser.KW_NOT:
      for(int i=0;i<Cond.getChildCount();i++){
        ASTNode tmp = (ASTNode) Cond.getChild(i);
        if(tmp.getToken().getType() == HiveParser.DOT){
          changTabColwithDot(tmp,sourceToDest);
        }else if(tmp.getToken().getType() == HiveParser.TOK_TABLE_OR_COL){
          changColName(tmp,sourceToDest);
        }else{
          changeConditionAlias(tmp, sourceToDest);
        }
      }
      break;
    case HiveParser.EQUAL_NS:
    case HiveParser.EQUAL:
    case HiveParser.KW_LIKE:
    case HiveParser.GREATERTHAN:
    case HiveParser.GREATERTHANOREQUALTO:
    case HiveParser.LESSTHAN:
    case HiveParser.LESSTHANOREQUALTO:
    case HiveParser.NOTEQUAL:
    case HiveParser.STAR:
    case HiveParser.MINUS:
    case HiveParser.PLUS:
    case HiveParser.DIV:
    case HiveParser.DIVIDE:
      ASTNode leftAST = (ASTNode) Cond.getChild(0);
      if(leftAST.getToken().getType() == HiveParser.DOT){
       changTabColwithDot(leftAST,sourceToDest);
      }else if(leftAST.getToken().getType() == HiveParser.TOK_TABLE_OR_COL){
        changColName(leftAST,sourceToDest);
      }else{
        changeConditionAlias(leftAST, sourceToDest);
      }

      ASTNode rightAST = (ASTNode) Cond.getChild(1);
      if(rightAST.getToken().getType() == HiveParser.DOT){
        changTabColwithDot(rightAST,sourceToDest);
      }else if(rightAST.getToken().getType() == HiveParser.TOK_TABLE_OR_COL){
        changColName(rightAST,sourceToDest);
      }else{
        changeConditionAlias(rightAST, sourceToDest);
      }

      break;
      default:

    }

  }

  private void changColName(ASTNode toktableorcol, HashMap<String[], String[]> sourceToDest) {
    String colName = toktableorcol.getChild(0).getText().toLowerCase();
    Iterator<Entry<String[], String[]>> iter = sourceToDest.entrySet().iterator();
    while(iter.hasNext()){
      Entry<String[], String[]> entry = iter.next();
      String[] source = entry.getKey();
      String[] dest = entry.getValue();
      if(colName.equals(source[1])){
        ASTNode newCol = new ASTNode(new CommonToken(HiveParser.Identifier,dest[1]));
        newCol.setParent(toktableorcol);
        toktableorcol.setChild(0,newCol);
        LOG.info("change " + colName + " to "+ dest[1] );
      }
    }
  }

  private void changTabColwithDot(ASTNode dot, HashMap<String[], String[]> sourceToDest) {
    String tabName = dot.getChild(0).getChild(0).getText().toLowerCase();
    String colName = dot.getChild(1).getText().toLowerCase();
    Iterator<Entry<String[], String[]>> iter = sourceToDest.entrySet().iterator();
    while(iter.hasNext()){
      Entry<String[], String[]> entry = iter.next();
      String[] source = entry.getKey();
      String[] dest = entry.getValue();
      if(colName.equals(source[1]) && tabName.equals(source[0])){
        ASTNode newTab = new ASTNode(new CommonToken(HiveParser.Identifier,dest[0]));
        ASTNode newCol = new ASTNode(new CommonToken(HiveParser.Identifier,dest[1]));
        newTab.setParent(dot.getChild(0));
        newCol.setParent(dot.getChild(1));
        dot.getChild(0).setChild(0,newTab);
        dot.setChild(1,newCol);
        LOG.info("change " + tabName+ "." + colName + " to "+ dest[0] + "." + dest[1] );
      }
    }
  }

/**
 * Input: curr, tabASTToColNames
 * output: colNameToTabName
 * If tabASTToColsNames contain curr, according tabASTToColsNames to fill colNameToTabName;
 * Else according metastore or "analyze select" to fill colNameToTabName;
 * @param curr
 * @param colNameToTabName
 * @param tabASTToColNames
 * @throws HiveException
 * @throws SerDeException
 */
  private void findColSource(ASTNode curr, HashMap<String, String> colNameToTabName, HashMap<ASTNode,List<String>> tabASTToColNames) throws HiveException, SerDeException {
    switch(curr.getToken().getType()){
    case HiveParser.TOK_FROM:
      findColSource((ASTNode) curr.getChild(0),colNameToTabName,tabASTToColNames);
      break;

    case HiveParser.TOK_JOIN:
    case HiveParser.TOK_CROSSJOIN:
    case HiveParser.TOK_LEFTOUTERJOIN:
    case HiveParser.TOK_RIGHTOUTERJOIN:
    case HiveParser.TOK_FULLOUTERJOIN:
    case HiveParser.TOK_LEFTSEMIJOIN:
    case HiveParser.TOK_UNIQUEJOIN:
      for(int i=0; i< curr.getChildCount();i++){
        findColSource((ASTNode) curr.getChild(i),colNameToTabName,tabASTToColNames);
      }
      break;

    case HiveParser.TOK_TABREF:
      String tableName = getUnescapedUnqualifiedTableName((ASTNode) curr.getChild(0))
      .toLowerCase();
      String tableAlias = curr.getChildCount() == 1 ? tableName
      : unescapeIdentifier(curr.getChild(curr.getChildCount() - 1)
      .getText().toLowerCase());
      if(tabASTToColNames.containsKey(curr)){
        List<String> cols = tabASTToColNames.get(curr);
        for (int j = 0; j < cols.size(); j++) {
          colNameToTabName.put(cols.get(j), tableAlias);
        }
      }else{
        Table tab = db.getTable(tableName);
        StructObjectInspector rowObjectInspector = (StructObjectInspector) tab
        .getDeserializer().getObjectInspector();
        List<? extends StructField> fields = rowObjectInspector
        .getAllStructFieldRefs();
        List<String> cols = new ArrayList<String>();
        for (int j = 0; j < fields.size(); j++) {
          String column = fields.get(j).getFieldName();
          colNameToTabName.put(column, tableAlias);
          cols.add(column);
        }
        tabASTToColNames.put(curr,cols);
      }
      break;

    case HiveParser.TOK_SUBQUERY:
      ASTNode select = (ASTNode) curr.getChild(0).getChild(1).getChild(1);
      String subQAlias = unescapeIdentifier(curr.getChild(1).getText().toLowerCase());
      if(tabASTToColNames.containsKey(curr)){
        List<String> cols = tabASTToColNames.get(curr);
        for (int j = 0; j < cols.size(); j++) {
          colNameToTabName.put(cols.get(j), subQAlias);
        }
      }else{
        List<String> cols = new ArrayList<String>();
        for(int i=0; i< select.getChildCount();i++){
          ASTNode selExpr = (ASTNode) select.getChild(i);
          ASTNode child0 = (ASTNode) selExpr.getChild(0);
          String colName = null;
          if(child0.getToken().getType() == HiveParser.DOT){
            colName = child0.getChild(1).getText().toLowerCase();
          }else if(child0.getToken().getType() == HiveParser.TOK_TABLE_OR_COL){
            colName = child0.getChild(0).getText().toLowerCase();
          }
          String rename = selExpr.getChildCount() == 1 ? colName
              : selExpr.getChild(1).getText().toLowerCase();
          colNameToTabName.put(rename, subQAlias);
          cols.add(rename);
        }
        tabASTToColNames.put(curr,cols);
      }
      break;
    }
  }

  /**
   * Traverse the tree to add subQs that can be flattened to List "subQToDel"
   * @param curr
   * @param subQToDel
   */
  private void findSubQToDel(ASTNode curr, List<ASTNode> subQToDel) {
    if(curr == null){
      return;
    }
    switch(curr.getToken().getType()){
    case HiveParser.TOK_SUBQUERY:
      if(canFlatten(curr)){
        LOG.info("find one TOK_SUBQUERY to flatten, it's alias is: " + curr.getChild(1).getText());
        subQToDel.add(curr);
      }
      break;
    }
    if(curr.getToken().getType() == HiveParser.TOK_TABREF
        || curr.getToken().getType() == HiveParser.TOK_INSERT){
      return;
    }
    for(int i=0; i< curr.getChildCount();i++){
      ASTNode next = (ASTNode) curr.getChild(i);
      findSubQToDel(next,subQToDel);
    }
  }

  private boolean canFlatten(ASTNode curSubQ) {
    //Condition1: parent of curSubQ should be join
    if(!isJoinToken((ASTNode) curSubQ.getParent())) {
      return false;
    }

    //Condition2: subQ's from child and subQ's sibling can't be both TOK_JOIN
    boolean canFlatten = false;
    ASTNode parentJoin = (ASTNode) curSubQ.getParent();
    ASTNode insert = (ASTNode) curSubQ.getChild(0).getChild(1);
    ASTNode from = (ASTNode) curSubQ.getChild(0).getChild(0);
    int subQPos = curSubQ.getChildIndex();
    if(subQPos == 1){
      if(from.getChild(0).getType() == HiveParser.TOK_TABREF ||
          from.getChild(0).getType() == HiveParser.TOK_SUBQUERY ){
        canFlatten = true;
      }
      else if((parentJoin.getChild(0).getType() == HiveParser.TOK_TABREF ||
          parentJoin.getChild(0).getType() == HiveParser.TOK_SUBQUERY)
          && isJoinToken((ASTNode) from.getChild(0))){
        canFlatten = true;
      }
    }else if(subQPos == 0){
      canFlatten = true;
    }
    if(!canFlatten){
      return false;
    }

    //Condition3: don't select expression or function, all select should be direct column fetch.
    ASTNode select = (ASTNode) insert.getChild(1);
    if(select.getToken().getType() == HiveParser.TOK_SELECT){
      for(int i=0;i<select.getChildCount();i++){
        ASTNode selExpr = (ASTNode) select.getChild(i);
        if(selExpr.getChild(0).getType() != HiveParser.DOT &&
            selExpr.getChild(0).getType() != HiveParser.TOK_TABLE_OR_COL &&
            selExpr.getChild(0).getType() != HiveParser.TOK_ALLCOLREF){
          return false;
        }
      }
    }
    if(select.getToken().getType() == HiveParser.TOK_SELECTDI){
      return false;
    }

    //Condition4: no TOK_GROUPBY and no TOK_ORDERBY in curSubQ.
    for(int i=2; i<insert.getChildCount();i++){
      //insert's first child is surely TOK_DESTINATION,second child is surely TOK_SELECT
      ASTNode tmpAST = (ASTNode) insert.getChild(i);
      if(tmpAST.getToken().getType() == HiveParser.TOK_GROUPBY){
        return false;
      }
      if(tmpAST.getToken().getType() == HiveParser.TOK_ORDERBY){
        return false;
      }
    }
    return true;
  }

  private String getTmpTableName() {
    /*String name = "tmp"+tmpCount;
    tmpCount++;*/
    /*UUID uuid  =  UUID.randomUUID();
    String suuid = UUID.randomUUID().toString();*/
    Calendar c = Calendar.getInstance();
    long time = c.getTimeInMillis();
    String name = "tmp"+ time;
    return name;
  }

  private String genCrtTmpQL(Operator tmpRSOp, String name) throws SemanticException{
    String crtTmpQL = null;
    List<String> cols = new ArrayList<String>();
    assert tmpRSOp.getParentOperators().size() == 0;
    Operator select = (Operator) tmpRSOp.getParentOperators().get(0);
    ArrayList<ColumnInfo> colInfos= select.getSchema().getSignature();
    for(int i = 0; i< colInfos.size(); i++ ){
      String currCol = colInfos.get(i).getInternalName();

      Map<Operator<? extends OperatorDesc>, OpParseContext> ctxMap = pCtxInc.getOpParseCtx();
      RowResolver inputRR = ctxMap.get(select).getRowResolver();
      HashMap<String, LinkedHashMap<String, ColumnInfo>> rslvMap =
        inputRR.getRslvMap();
      for (Map.Entry<String, LinkedHashMap<String, ColumnInfo>> e : rslvMap.entrySet()) {
        LinkedHashMap<String, ColumnInfo> f_map = e.getValue();
        for (Map.Entry<String, ColumnInfo> entry : f_map.entrySet()) {
          ColumnInfo colInfo = entry.getValue();
          String internalName = colInfo.getInternalName();
          if(internalName.equals(currCol)){
            String oldName = entry.getKey();
            cols.add(oldName+ " " +colInfo.getType().getTypeName());
            tmpTableCols.add(oldName);
            break;
          }
        }
      }
    }
    crtTmpQL = "create table " + name + " (";
    for(int i = 0 ;i <cols.size(); i++){
      if(i < cols.size() -1 ){
        crtTmpQL= crtTmpQL + cols.get(i) + " , ";
      }else{
        crtTmpQL= crtTmpQL + cols.get(i);
      }
    }
    crtTmpQL = crtTmpQL + " )";
    return crtTmpQL;
  }

  /**
   * Traverse query's ASTtree to do joinReorder and record positions of tmpTables
   * @param includeIncToken
   * @param curr: the ASTNode being processed
   * @param root: Root of subQquery that is being processed
   * @throws SerDeException
   * @throws HiveException
   */
  private void joinReordeAndRecordPosOfTmp(ASTNode curr,ASTNode root, HashMap<ASTNode,Boolean> includeIncToken) throws HiveException, SerDeException{

    if(curr.getToken().getType() == HiveParser.TOK_FROM){
      ASTNode frm = (ASTNode) curr.getChild(0);
      if(isJoinToken(frm)){

        List<ASTNode> ASTJoins = new ArrayList<ASTNode>();
        for(ASTNode AST = frm; isJoinToken(AST) ; AST = (ASTNode )AST.getChild(0)){
          ASTJoins.add(AST);
        }
        if(ASTJoins.size() == 1){
          return;
        }
        /**
         * 1. getIncPoses
         * 2. getIncAliases
         */
        Set<String> incAliases = new HashSet<String>();
        List<Integer> incPoses = getIncPos(ASTJoins,incAliases,includeIncToken);

        if(incPoses.size() == 0){
          LOG.info("No inc Table in this from clause!");

        }else{
          LOG.info("Before joinReorder, Tmp table pos are: " + incPoses.toString());

          /**
           * 1. genJoinTree
           *
           */
          QBJoinTree joinTree = genJoinTreeInc(frm);
          // make array with QBJoinTree : outer most(0) --> inner most(n)
          List<QBJoinTree> QBJoinTrees = new ArrayList<QBJoinTree>();
          for (;joinTree != null; joinTree = joinTree.getJoinSrc()) {
            QBJoinTrees.add(joinTree);
          }

          joinReorderAST(QBJoinTrees, ASTJoins,incAliases,incPoses);
          LOG.info("After joinReorder, Tmp table pos are: " + incPoses.toString());

          /**
           * After Join Reorder, except the innest join(which incSource maybe leftchild),
           * all other join's incSource can only be rightchild, because inner incSource exchange with outer's rightchild.
           * But innest join can't extract tmpTable, so all tmpTable are join's leftchild;
           * Here,
           * 1. Exchange left and right child of incJoin; After that:
           * 2. let incJoin's rightChild as tmpTable and surround a TOK_SUQBQUERY to it.
           * 3. let incJoin's leftChild is TOK_JOIN which include incTable;
           * */
          for(int i=0 ;i< incPoses.size();i++){
            int currIncPos = incPoses.get(i);
            ASTNode incJoin = ASTJoins.get(currIncPos);

            if(isJoinToken( (ASTNode) incJoin.getChild(0))){
              LOG.info("Exchange left and right child of join " + currIncPos);
              ASTNode tmp = (ASTNode) incJoin.getChild(0);
              incJoin.setChild(0,incJoin.getChild(1));
              incJoin.setChild(1,tmp);
              ASTNodeUtils.changeJoinType(incJoin);

              String[] subQueryAliases = QBJoinTrees.get(currIncPos).getLeftAliases();// now is rightChild
              String subQName =addSubQuery(ASTJoins.get(currIncPos),subQueryAliases);

              LOG.info("Added subQuery " + subQName + " to new right child: " + Arrays.asList(subQueryAliases).toString());

              subQNameToAlias.put(subQName, subQueryAliases);
              /*
               *  analyzeAlias dont't handle join here because only need change join conditions out of incPos,
               *   we had done it in addSubQuery function;
               */
              incJoin = ASTJoins.get(currIncPos);

              //LOG.info("Begin analyze alias after surroud add subQ " + subQName);
              analyzeAliasBottomUp((ASTNode) root.getParent(),incJoin,subQName,subQueryAliases,false);

              subQNameToAST.put(subQName,(ASTNode) incJoin.getChild(1));
              subQNameToCurrRoot.put(subQName, root);
              isInsertSubQuery = true;
            }
          }
        }

      }
    }

    if(curr.getToken().getType() == HiveParser.TOK_TABREF ||
        curr.getToken().getType() == HiveParser.TOK_INSERT){// no need to deep-in
      return;
    }

    int child_count = curr.getChildCount();
    for (int child_pos = 0; child_pos < child_count; ++child_pos) {
      ASTNode next = (ASTNode) curr.getChild(child_pos);
      if(addedSubQuery.contains(next)){
        continue;
      }
      ASTNode nextRoot = null;
      if(next.getToken().getType() == HiveParser.TOK_QUERY){
        nextRoot = next;
      }else{
        nextRoot = root;
      }
      joinReordeAndRecordPosOfTmp(next,nextRoot,includeIncToken);
    }

  }
  /**
   * 1. add TOK_SUBQUERY to right child of incJoin in ASTtrees
   * 2. change outer's aliases in subQueryAliases to subQuery's name
   * @param currIncPos
   */
 private String addSubQuery(ASTNode incParent, String[] subQueryAliases){

    /*  String name = "subQ"+addedsubQueryCount;
    addedsubQueryCount ++;*/
   /* UUID uuid  =  UUID.randomUUID();
    String suuid = UUID.randomUUID().toString();*/
    Calendar c = Calendar.getInstance();
    long time = c.getTimeInMillis();
    String subQueryName = "subQ"+ time;

    //add subQuery as incParent's rightChild;
    LOG.info("add TOK_SUBQUERY to " + incParent.toString() + "'s right child!");
    ASTNode subQuery = insertTokSubQuery(incParent,1,subQueryAliases,subQueryName);

    addedSubQuery.add(subQuery);
    return subQueryName;

  }

 /**
  *  if pos is 0 ,insert TOK_SUBQUERY as Parent's left Child
  * if pos is 1,insert TOK_SUBQUERY as Parent's right Child
  * construct like follows:
  *
  * paret
  *  (TOK_SUBQUERY
  *    (TOK_QUERY
  *      (TOK_FROM child)
  *      (TOK_INSERT
  *        TOK_DESTINATION (TOK_DIR TOK_TMP_FILE)
  *        TOK_SELECT (TOK_SELEXPR TOK_ALLCOLREF)
  *      )
  *    )
  *    name
  *  )
  * @param subQueryName
  *      */
private ASTNode insertTokSubQuery(ASTNode parent,int pos,String[] subQueryAliases, String subQueryName){
   ASTNode child = (ASTNode) parent.getChild(pos);

   /*paret(TOK_SUBQUERY)*/
   ASTNode subQuery = new ASTNode(new CommonToken(HiveParser.TOK_SUBQUERY,"TOK_SUBQUERY"));
   parent.setChild(pos, subQuery);
   subQuery.setParent(parent);

   /*TOK_SUBQUERY(TOK_QUERY)*/
   ASTNode query = new ASTNode(new CommonToken(HiveParser.TOK_QUERY,"TOK_QUERY"));
   subQuery.addChild(query);
   query.setParent(subQuery);

   /*TOK_SUBQUERY(tmp)*/
   ASTNode subQueryAlias = new ASTNode(new CommonToken(HiveParser.Identifier,subQueryName));
   subQuery.addChild(subQueryAlias);
   subQueryAlias.setParent(subQuery);

   /*TOK_QUERY(TOK_FROM) TOK_FROM(child)*/
   ASTNode from = new ASTNode(new CommonToken(HiveParser.TOK_FROM,"TOK_FROM"));
   query.addChild(from);
   from.setParent(query);
   from.addChild(child);
   child.setParent(from);

   /*TOK_QUERY(TOK_INSERT)*/
   ASTNode insert = new ASTNode(new CommonToken(HiveParser.TOK_INSERT,"TOK_INSERT"));
   query.addChild(insert);
   insert.setParent(query);

   /*TOK_INSERT(TOK_DESTINATION (TOK_DIR TOK_TMP_FILE))*/
   ASTNode destination = new ASTNode(new CommonToken(HiveParser.TOK_DESTINATION,"TOK_DESTINATION"));
   ASTNode dir = new ASTNode(new CommonToken(HiveParser.TOK_DIR,"TOK_DIR"));
   ASTNode tmpFile = new ASTNode(new CommonToken(HiveParser.TOK_TMP_FILE,"TOK_TMP_FILE"));
   insert.addChild(destination);
   destination.addChild(dir);
   dir.addChild(tmpFile);
   tmpFile.setParent(dir);
   dir.setParent(destination);
   destination.setParent(insert);

   /*TOK_INSERT(TOK_SELECT (TOK_SELEXPR TOK_ALLCOLREF))*/
   ASTNode select = new ASTNode(new CommonToken(HiveParser.TOK_SELECT,"TOK_SELECT"));
   buildSelExprs(select,subQueryAliases);
   insert.addChild(select);
   select.setParent(insert);

   return subQuery;

 }

/**
 * Build Select expression of added SubQuery,to
 * Rename every column of table in subQueryAliases as "tabName+colName"
 * @param select
 * @param subQueryAliases
 */
private void buildSelExprs(ASTNode select, String[] subQueryAliases) {
  for(int i=0;i<subQueryAliases.length;i++){
    String tabAlias = subQueryAliases[i];
    String tabName = aliasToTabs.get(tabAlias);
    try {
      if(tabName != null){
        Table tab = db.getTable(tabName);
        StructObjectInspector rowObjectInspector = (StructObjectInspector) tab
        .getDeserializer().getObjectInspector();
        List<? extends StructField> fields = rowObjectInspector
        .getAllStructFieldRefs();
        for (int j = 0; j < fields.size(); j++) {
          String oldColumn = fields.get(j).getFieldName();
          buildSelExpr(tabAlias,oldColumn,select);
        }
      }else{
        ASTNode suQselect = subQueryaliasToSel.get(tabAlias);
        for(int j=0; j<suQselect.getChildCount(); j++){
          ASTNode selExpr = (ASTNode) suQselect.getChild(j);
          ASTNode child0 = (ASTNode) selExpr.getChild(0);
          String colName = null;
          if(child0.getToken().getType() == HiveParser.DOT){
            String tab = child0.getChild(0).getChild(0).getText();
            String col = child0.getChild(1).getText();
            colName = tab + "." + col;
          }else if(child0.getToken().getType() == HiveParser.TOK_TABLE_OR_COL){
            colName = child0.getChild(0).getText().toLowerCase();
          }
          String oldColumn = selExpr.getChildCount() == 1 ? colName
              : selExpr.getChild(1).getText();
          buildSelExpr(tabAlias,oldColumn,select);
        }
      }
    } catch (HiveException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SerDeException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}

/**
 * Rename: select tabAlias.oldColumn as newColName
 * @param tabAlias
 * @param oldColumn
 * @param select
 */
private void buildSelExpr(String tabAlias, String oldColumn, ASTNode select) {
  String[] tabCol = new String[2];
  tabCol[0] = tabAlias;
  tabCol[1] = oldColumn;
  String newColName = tabCol[0] + "_"+ tabCol[1];
  colNameMap.put(tabCol, newColName);
  ASTNode selExpr = new ASTNode(new CommonToken(HiveParser.TOK_SELEXPR,"TOK_SELEXPR"));
  ASTNode dot = new ASTNode(new CommonToken(HiveParser.DOT,"."));
  ASTNode taborcol = new ASTNode(new CommonToken(HiveParser.TOK_TABLE_OR_COL,"TOK_TABLE_OR_COL"));
  ASTNode oldTabName = new ASTNode(new CommonToken(HiveParser.Identifier,tabAlias));
  ASTNode oldColName = new ASTNode(new CommonToken(HiveParser.Identifier,tabCol[1]));
  ASTNode newCol = new ASTNode(new CommonToken(HiveParser.Identifier,newColName));
  select.addChild(selExpr);
  selExpr.setParent(select);
  selExpr.addChild(dot);
  dot.setParent(selExpr);
  dot.addChild(taborcol);
  taborcol.setParent(dot);
  dot.addChild(oldColName);
  oldColName.setParent(dot);
  taborcol.addChild(oldTabName);
  oldTabName.setParent(taborcol);

  selExpr.addChild(newCol);
  newCol.setParent(selExpr);
}
  private QBJoinTree genJoinTreeInc(ASTNode joinParseTree)
      throws SemanticException{

    QBJoinTree joinTree = new QBJoinTree();
    JoinCond[] condn = new JoinCond[1];

    switch (joinParseTree.getToken().getType()) {
    case HiveParser.TOK_LEFTOUTERJOIN:
      joinTree.setNoOuterJoin(false);
      condn[0] = new JoinCond(0, 1, JoinType.LEFTOUTER);
      break;
    case HiveParser.TOK_RIGHTOUTERJOIN:
      joinTree.setNoOuterJoin(false);
      condn[0] = new JoinCond(0, 1, JoinType.RIGHTOUTER);
      break;
    case HiveParser.TOK_FULLOUTERJOIN:
      joinTree.setNoOuterJoin(false);
      condn[0] = new JoinCond(0, 1, JoinType.FULLOUTER);
      break;
    case HiveParser.TOK_LEFTSEMIJOIN:
      joinTree.setNoSemiJoin(false);
      condn[0] = new JoinCond(0, 1, JoinType.LEFTSEMI);
      break;
    default:
      condn[0] = new JoinCond(0, 1, JoinType.INNER);
      joinTree.setNoOuterJoin(true);
      break;
    }

    joinTree.setJoinCond(condn);

    ASTNode left = (ASTNode) joinParseTree.getChild(0);
    ASTNode right = (ASTNode) joinParseTree.getChild(1);

    if ((left.getToken().getType() == HiveParser.TOK_TABREF)
        || (left.getToken().getType() == HiveParser.TOK_SUBQUERY)
        || (left.getToken().getType() == HiveParser.TOK_PTBLFUNCTION)) {
      String tableName = getUnescapedUnqualifiedTableName((ASTNode) left.getChild(0))
          .toLowerCase();
      String alias = left.getChildCount() == 1 ? tableName
          : unescapeIdentifier(left.getChild(left.getChildCount() - 1)
          .getText().toLowerCase());
      // ptf node form is: ^(TOK_PTBLFUNCTION $name $alias? partitionTableFunctionSource partitioningSpec? expression*)
      // guranteed to have an lias here: check done in processJoin
      alias = (left.getToken().getType() == HiveParser.TOK_PTBLFUNCTION) ?
          unescapeIdentifier(left.getChild(1).getText().toLowerCase()) :
            alias;

      if(left.getToken().getType() == HiveParser.TOK_SUBQUERY){
        ASTNode select = (ASTNode) left.getChild(0).getChild(1).getChild(1);
        subQueryaliasToSel.put(alias, select);
      }else{
        aliasToTabs.put(alias, tableName);
      }

      joinTree.setLeftAlias(alias);
      String[] leftAliases = new String[1];
      leftAliases[0] = alias;
      joinTree.setLeftAliases(leftAliases);
      String[] children = new String[2];
      children[0] = alias;
      joinTree.setBaseSrc(children);
      joinTree.setId(qb.getId());
      /*joinTree.getAliasToOpInfo().put(
          getModifiedAlias(qb, alias), aliasToOpInfo.get(alias));*/
    } else if (isJoinToken(left)) {
      QBJoinTree leftTree = genJoinTreeInc(left);
      joinTree.setJoinSrc(leftTree);
      String[] leftChildAliases = leftTree.getLeftAliases();
      String leftAliases[] = new String[leftChildAliases.length + 1];
      for (int i = 0; i < leftChildAliases.length; i++) {
        leftAliases[i] = leftChildAliases[i];
      }
      leftAliases[leftChildAliases.length] = leftTree.getRightAliases()[0];
      joinTree.setLeftAliases(leftAliases);
    } else {
      assert (false);
    }

    if ((right.getToken().getType() == HiveParser.TOK_TABREF)
        || (right.getToken().getType() == HiveParser.TOK_SUBQUERY)
        || (right.getToken().getType() == HiveParser.TOK_PTBLFUNCTION)) {
      String tableName = getUnescapedUnqualifiedTableName((ASTNode) right.getChild(0))
          .toLowerCase();
      String alias = right.getChildCount() == 1 ? tableName
          : unescapeIdentifier(right.getChild(right.getChildCount() - 1)
          .getText().toLowerCase());
      // ptf node form is: ^(TOK_PTBLFUNCTION $name $alias? partitionTableFunctionSource partitioningSpec? expression*)
      // guranteed to have an lias here: check done in processJoin
      alias = (right.getToken().getType() == HiveParser.TOK_PTBLFUNCTION) ?
          unescapeIdentifier(right.getChild(1).getText().toLowerCase()) :
            alias;
      if(right.getToken().getType() == HiveParser.TOK_SUBQUERY){
        ASTNode select = (ASTNode) right.getChild(0).getChild(1).getChild(1);
        subQueryaliasToSel.put(alias, select);
      }else{
        aliasToTabs.put(alias, tableName);
      }

      String[] rightAliases = new String[1];
      rightAliases[0] = alias;
      joinTree.setRightAliases(rightAliases);
      String[] children = joinTree.getBaseSrc();
      if (children == null) {
        children = new String[2];
      }
      children[1] = alias;
      joinTree.setBaseSrc(children);
      joinTree.setId(qb.getId());
      /*joinTree.getAliasToOpInfo().put(
          getModifiedAlias(qb, alias), aliasToOpInfo.get(alias));*/
      // remember rhs table for semijoin
      if (joinTree.getNoSemiJoin() == false) {
        joinTree.addRHSSemijoin(alias);
      }
    } else {
      assert false;
    }

    ArrayList<ArrayList<ASTNode>> expressions = new ArrayList<ArrayList<ASTNode>>();
    expressions.add(new ArrayList<ASTNode>());
    expressions.add(new ArrayList<ASTNode>());
    joinTree.setExpressions(expressions);

    ArrayList<Boolean> nullsafes = new ArrayList<Boolean>();
    joinTree.setNullSafes(nullsafes);

    ArrayList<ArrayList<ASTNode>> filters = new ArrayList<ArrayList<ASTNode>>();
    filters.add(new ArrayList<ASTNode>());
    filters.add(new ArrayList<ASTNode>());
    joinTree.setFilters(filters);
    joinTree.setFilterMap(new int[2][]);

    ArrayList<ArrayList<ASTNode>> filtersForPushing =
        new ArrayList<ArrayList<ASTNode>>();
    filtersForPushing.add(new ArrayList<ASTNode>());
    filtersForPushing.add(new ArrayList<ASTNode>());
    joinTree.setFiltersForPushing(filtersForPushing);

    ASTNode joinCond = (ASTNode) joinParseTree.getChild(2);
    ArrayList<String> leftSrc = new ArrayList<String>();
    parseJoinCondition(joinTree, joinCond, leftSrc);
    joinTree.setLeftSrc(leftSrc);
    if (leftSrc.size() == 1) {
      joinTree.setLeftAlias(leftSrc.get(0));
    }

    return joinTree;

}

  private ArrayList<Integer> getIncPos(List<ASTNode> ASTJoins, Set<String> incAliases, HashMap<ASTNode, Boolean> includeIncToken) {
    ArrayList<Integer> ans = new ArrayList<Integer>();
    for(int i= ASTJoins.size()-1; i>=0; i--){

      ASTNode left = (ASTNode) ASTJoins.get(i).getChild(0);

      if ((left.getToken().getType() == HiveParser.TOK_TABREF)
          || (left.getToken().getType() == HiveParser.TOK_SUBQUERY)) {
        String tableName = getUnescapedUnqualifiedTableName((ASTNode) left.getChild(0))
        .toLowerCase();
        String alias = left.getChildCount() == 1 ? tableName
            : unescapeIdentifier(left.getChild(left.getChildCount() - 1)
            .getText().toLowerCase());

        boolean isIncludeIncTable = isIncludeIncTable(left,includeIncToken);
        if(isIncludeIncTable){
          if(ans.size()==0) {// only the innest incJoin can extract tmp
            ans.add(i);
          }
          incAliases.add(alias);
        }

      }

      ASTNode right = (ASTNode) ASTJoins.get(i).getChild(1);

      if ((right.getToken().getType() == HiveParser.TOK_TABREF)
          || (right.getToken().getType() == HiveParser.TOK_SUBQUERY)) {
        String tableName = getUnescapedUnqualifiedTableName((ASTNode) right.getChild(0))
        .toLowerCase();
        String alias = right.getChildCount() == 1 ? tableName
            : unescapeIdentifier(right.getChild(right.getChildCount() - 1)
            .getText().toLowerCase());
        boolean isIncludeIncTable = isIncludeIncTable(right,includeIncToken);
        if(isIncludeIncTable){
          if(ans.size()==0) {// only the innest incJoin can extract tmp
            ans.add(i);
          }
          incAliases.add(alias);
        }

      }
    }
    return ans;
  }

  private boolean isIncludeIncTable(ASTNode curr, HashMap<ASTNode, Boolean> includeIncToken) {
    if(includeIncToken.get(curr) != null){
      if(includeIncToken.get(curr) == true){
        return true;
      }else if(includeIncToken.get(curr) == false){
        return false;
      }
    }
    switch(curr.getToken().getType()){
    case HiveParser.TOK_TABREF:
      if(incToken.contains(curr)){
        includeIncToken.put(curr,true);
        return true;
      }else{
        includeIncToken.put(curr,false);
        return false;
      }
    case HiveParser.TOK_SUBQUERY:
      ASTNode tokFrom = (ASTNode) curr.getChild(0).getChild(0);
      if(tokFrom.getToken().getType() == HiveParser.TOK_FROM){
        ASTNode frm = (ASTNode) tokFrom.getChild(0);
        if(isJoinToken(frm)){
          ASTNode AST = frm;
          for(; isJoinToken(AST) ; AST = (ASTNode )AST.getChild(0)){
            if(isIncludeIncTable((ASTNode) AST.getChild(1),includeIncToken)){
              includeIncToken.put(curr,true);
              return true;
            }
          }
          if(isIncludeIncTable((ASTNode) AST,includeIncToken)){
            includeIncToken.put(curr,true);
            return true;
          }
        }else if(frm.getToken().getType() == HiveParser.TOK_SUBQUERY
            || frm.getToken().getType() == HiveParser.TOK_TABREF){
          if(isIncludeIncTable(frm,includeIncToken)){
            includeIncToken.put(curr,true);
            return true;
          }
        }

      }
      break;
    default:

    }
    includeIncToken.put(curr,false);
    return false;
  }

  /**
   * 1. change ASTtrees according joinTree and ctx_1;
   * 2. after joinReorder, ASTtree's incJoin's right child include the incTable;
   * 3. return the leftAliases of incJoin
   * because left child of incJoin need to surrounded by TOK_SUBQUERY;
   * @param incPoses
   * @return
   */
 private void joinReorderAST(List<QBJoinTree> QBJoinTrees,List<ASTNode> ASTtrees, Set<String> incAliases, List<Integer> incPoses){
    if (QBJoinTrees.size() == 1) {
      return ;
    }

    assert QBJoinTrees.size() == ASTtrees.size();

    LOG.info("incAliases are " + incAliases.toString());
    int lastIncPos = incPoses.get(0);
    /*traverse the trees list to move the incTable outer*/
    for (int inner= lastIncPos,outer=inner-1; outer >= 0; inner--,outer--) {
      QBJoinTree innerQBJoinTree = QBJoinTrees.get(inner);
      QBJoinTree outerQBJoinTree = QBJoinTrees.get(outer);
      String[] leftAliases = innerQBJoinTree.getLeftAliases();
      String[] rightAliases = innerQBJoinTree.getRightAliases();
      List<String> leftsrc = outerQBJoinTree.getLeftSrc();
      String[] outerRightAliases = outerQBJoinTree.getRightAliases();
      boolean isinnerLeftInc = false, isinnerRightInc = false;
      boolean isFromLeft = false, isFromRight =false;
      boolean isouterRightInc = false;
      for(String leftAlias:leftAliases){
        if(incAliases.contains(leftAlias)){
          isinnerLeftInc = true;
        }
        if(leftsrc.contains(leftAlias)){
          isFromLeft=true;
        }
      }
      for(String rightAlias:rightAliases){
        if(incAliases.contains(rightAlias)){
          isinnerRightInc = true;
        }
        if(leftsrc.contains(rightAlias)){
          isFromRight=true;
        }
      }
      for(String outerRightAlias:outerRightAliases){
        if(incAliases.contains(outerRightAlias)){
          isouterRightInc = true;
        }
      }

      if(isouterRightInc || (isinnerLeftInc && isinnerRightInc) || (isFromLeft && isFromRight)){
        LOG.info("Can't move " + Arrays.asList(outerRightAliases).toString() + " inner.");
        continue;
      }else if(isinnerLeftInc && isFromRight){
        exchangeInc(ASTtrees, innerQBJoinTree, outerQBJoinTree, 0,outer,lastIncPos,incPoses);
        lastIncPos = outer;
      }else if(isinnerRightInc && isFromLeft){
        exchangeInc(ASTtrees, innerQBJoinTree, outerQBJoinTree, 1,outer,lastIncPos,incPoses);
        lastIncPos = outer;
      }else{
        LOG.info("Can't move " + Arrays.asList(outerRightAliases).toString() + " inner.");
      }

//      boolean isExchange = false;
//      if(canExchange(incAliases,outerQBJoinTree.getLeftSrc(),ASTtrees,outer)){
//        /*exchange the incTable and currAST's right table in AST tree*/
//        if(java.util.Arrays.asList(leftAliases).containsAll(incAliases)){//incTable in left
//          exchangeInc(ASTtrees, innerQBJoinTree, outerQBJoinTree, 0,outer,lastIncPos,incPoses);
//          isExchange = true;
//          lastIncPos = outer;
//        }else if(java.util.Arrays.asList(rightAliases).containsAll(incAliases)){// incTable in right
//          exchangeInc(ASTtrees, innerQBJoinTree, outerQBJoinTree, 1,outer,lastIncPos,incPoses);
//          isExchange =true;
//          lastIncPos = outer;
//        }else{
//          isExchange = false;
//        }
//      }
//      if(!isExchange){
//        // add all incQBJoinTree's aliases to incAliases
//        CollectionUtils.addAll(incAliases,leftAliases);
//        CollectionUtils.addAll(incAliases,rightAliases);
//      }
    }
  }

 /**
  * Outer join's join condition don't contain incAlias, such as:( b can exchange with c, b is incAlias)
  * a join b
  *   on a.col1 = b.col1
  * join c
  *   on a.col2 = c.col2
  * @param incAliases
  * @param leftSrc: the join condition source table of left
  * @param ASTtrees
  * @param currPos
  * @return
  */
  private boolean canExchange(Set<String> incAliases, List<String> leftSrc, List<ASTNode> ASTtrees, int currPos) {
    for(String leftAlias:leftSrc){
      if(incAliases.contains(leftAlias)){
        return false;
      }
    }
    if(ASTtrees.get(currPos).getToken().getType() != HiveParser.TOK_JOIN ||
        ASTtrees.get(currPos+1).getToken().getType() != HiveParser.TOK_JOIN){
      return false;
    }
    return true;
  }

  /**
   *  incAST is left child of currAST
   * this function exchange the incAST's incAliasPos child and currAST's right child
   * @param currPos
   * @param incPoses
   * @param lastIncPos
   */
 private void exchangeInc(List<ASTNode> ASTtrees, QBJoinTree preQBJoinTree,QBJoinTree currQBJoinTree,
     int incAliasPos, int outer, int lastIncPos, List<Integer> incPoses){
    ASTNode currAST = ASTtrees.get(outer);
    ASTNode preAST = ASTtrees.get( outer + 1 );

    ASTNode tmpTable = (ASTNode) preAST.getChild(incAliasPos);
    preAST.setChild(incAliasPos, currAST.getChild(1));
    currAST.setChild(1, tmpTable);//right child of currAST

    if(preAST.getChildCount() == 3 && currAST.getChildCount() == 3){
      ASTNode tmpCondn = (ASTNode) preAST.getChild(2);
      preAST.setChild(2, currAST.getChild(2));
      currAST.setChild(2, tmpCondn);
    }else if(preAST.getChildCount() == 2 && currAST.getChildCount() == 3){
      preAST.addChild(currAST.getChild(2));
      currAST.deleteChild(2);
    }else if(preAST.getChildCount() == 3 && currAST.getChildCount() == 2){
      currAST.addChild(preAST.getChild(2));
      preAST.deleteChild(2);
    }

    // set preQBJoinTree's leftAliases or rightAliases
    String[] tmpAliases = null;
    if(incAliasPos == 0){
      tmpAliases = preQBJoinTree.getLeftAliases();
      preQBJoinTree.setLeftAliases(currQBJoinTree.getRightAliases());
    }else if(incAliasPos == 1){
      tmpAliases = preQBJoinTree.getRightAliases();
      preQBJoinTree.setRightAliases(currQBJoinTree.getRightAliases());
    }

    LOG.info("Join Reorder: Exchange inner " + Arrays.asList(tmpAliases).toString() + " with outer "
        + Arrays.asList(currQBJoinTree.getRightAliases()).toString());

    // set currQBJoin's rightAliases
    currQBJoinTree.setRightAliases(tmpAliases);

    // set currQBJoin's leftAliases
    String[] leftChildAliases = preQBJoinTree.getLeftAliases();
    String[] rightChildAliases = preQBJoinTree.getRightAliases();
    String leftAliases[] = new String[leftChildAliases.length + rightChildAliases.length];
    for (int j = 0; j < leftChildAliases.length; j++) {
      leftAliases[j] = leftChildAliases[j];
    }
    for (int j = 0; j < rightChildAliases.length; j++) {
      leftAliases[leftChildAliases.length + j] = rightChildAliases[j];
    }
    currQBJoinTree.setLeftAliases(leftAliases);

    if(lastIncPos - outer > 1){
      /**
       *                   [b4]join_outer                  join_outer
       *                     /       \                    /          \
       *                  [i]join    base5    ->       join           join
       *                   /    \                      /   \          /   \
       *               [i]join  base4                 b5   b4  join_last  base3
       *                /     \                                 /   \
       *         join_last    base3                           join   inc
       *         /  \                                         /  \
       *      join  inc                                    base1 base2
       *      / \
       *  base1 base2
       */
      incPoses.add(outer);
    }else{
      /**
       *        join_outer           join_outer
       *         /     \              /      \
       *   join_last  base2  ->   join_last  inc1
       *    /    \                 /   \
       *   inc1 base1           base2 base1
       */
      incPoses.set(incPoses.size()-1, outer);
    }

  }

  private void analyzeAliasBottomUp(ASTNode stop, ASTNode curr, String destName,
      String[] aliasToChange, boolean isFinalAnalyze) throws HiveException, SerDeException {
    //insert,joinCondition
    if(curr == stop){
      return;
    }
    switch(curr.getToken().getType()){
    case HiveParser.TOK_QUERY:
      ASTNode insert = (ASTNode) curr.getChild(1);//TOK_INSERT
      analyzeAliasTopDown(insert,destName,aliasToChange,isFinalAnalyze);
      break;

    case HiveParser.TOK_JOIN:
    case HiveParser.TOK_CROSSJOIN:
    case HiveParser.TOK_LEFTOUTERJOIN:
    case HiveParser.TOK_RIGHTOUTERJOIN:
    case HiveParser.TOK_FULLOUTERJOIN:
    case HiveParser.TOK_LEFTSEMIJOIN:
    case HiveParser.TOK_UNIQUEJOIN:
      ASTNode condn = (ASTNode) curr.getChild(2);//join's condition
      if(isFinalAnalyze){
        changeConditionAlias(condn,destName,aliasToChange,true);
      }else{
        changeConditionAlias(condn,destName,aliasToChange,false);
      }
      break;
    default:

    }

    ASTNode next = (ASTNode) curr.getParent();
    analyzeAliasBottomUp(stop,next,destName,aliasToChange,isFinalAnalyze);

  }

/**
 * analyzeInsert's child: select,where,groupby,orderby
 * @param curr
 * @param destName
 * @param aliasToChanged
 * @param isFinalAnalyze
 */
  private void analyzeAliasTopDown(ASTNode curr, String destName, String[] aliasToChange,
      boolean isFinalAnalyze) {
    if(curr == null){
      return;
    }
    switch(curr.getToken().getType()){
    case HiveParser.TOK_INSERT:
      int child_count = curr.getChildCount();
      for (int child_pos = 0; child_pos < child_count; ++child_pos) {
        ASTNode next = (ASTNode) curr.getChild(child_pos);
        analyzeAliasTopDown(next,destName,aliasToChange,isFinalAnalyze);
      }
      break;
    case HiveParser.TOK_SELECT:
    case HiveParser.TOK_SELECTDI:
      for (int i = 0; i < curr.getChildCount(); ++i) {
        ASTNode selExpr = (ASTNode) curr.getChild(i);
        if (selExpr.getToken().getType() == HiveParser.TOK_SELEXPR) {
          String newColName = null;
          ASTNode child0 = (ASTNode)selExpr.getChild(0);
          // Add rename filed: rename colName to original colName;
          if(selExpr.getChildCount() == 1 ){
            ASTNode newColNameAST = null;
            if(child0.getToken().getType() == HiveParser.DOT && child0.getChildCount() == 2){
              newColName = child0.getChild(1).getText().toLowerCase();
              newColNameAST = new ASTNode(new CommonToken(HiveParser.Identifier,newColName));
            }else if(child0.getToken().getType() == HiveParser.TOK_TABLE_OR_COL){
              newColName = child0.getChild(0).getText().toLowerCase();
              newColNameAST = new ASTNode(new CommonToken(HiveParser.Identifier,newColName));
            }
            selExpr.addChild(newColNameAST);
          }
          // Change the tabName.colName or colName to DestTabName.DestColName;
          ASTNode tmp = (ASTNode)selExpr.getChild(0);
          if(tmp.getToken().getType() == HiveParser.DOT && tmp.getChildCount() == 2){
            changTabColwithDot(tmp,destName,aliasToChange,false);
          }else if(tmp.getToken().getType() == HiveParser.TOK_TABLE_OR_COL){
            changColName(tmp,false);
          }else{
            changeConditionAlias(tmp,destName,aliasToChange,false);
          }
        }
      }
      break;
    case HiveParser.TOK_WHERE:
      ASTNode condn = (ASTNode) curr.getChild(0);
      boolean isDelete = changeConditionAlias(condn,destName,aliasToChange,isFinalAnalyze);
      if(isDelete){
        int pos = curr.getChildIndex();
        curr.getParent().deleteChild(pos);
        LOG.info("delete TOK_WHRER");
      }
      break;
    case HiveParser.TOK_GROUPBY:
      for (int i = 0; i < curr.getChildCount(); ++i) {
        ASTNode tmp = (ASTNode)curr.getChild(i);
        if(tmp.getToken().getType() == HiveParser.DOT && tmp.getChildCount() == 2){
          changTabColwithDot(tmp,destName,aliasToChange,false);
        }else if(tmp.getToken().getType() == HiveParser.TOK_TABLE_OR_COL){
          changColName(tmp,false);
        }
      }
      break;

      //Attentation: don's change TOK_ORDERBY's references!!!
      //Because we have renamed columns to original colName in TOK_SELECT

      /*case HiveParser.TOK_ORDERBY:
      for (int i = 0; i < curr.getChildCount(); ++i) {
        ASTNode orderExpr = (ASTNode) curr.getChild(i);
        if (orderExpr.getToken().getType() == HiveParser.TOK_TABSORTCOLNAMEASC
            ||orderExpr.getToken().getType() == HiveParser.TOK_TABSORTCOLNAMEDESC) {
          ASTNode tmp = (ASTNode)orderExpr.getChild(0);
          if(tmp.getToken().getType() == HiveParser.DOT && tmp.getChildCount() == 2){
            changTabColwithDot(tmp,destName,aliasToChange,false);
          }else if(tmp.getToken().getType() == HiveParser.TOK_TABLE_OR_COL){
            changColName(tmp,false);
            ASTNodeUtils.addTabName(tmp, colNameToTabName);
          }
        }
      }
      break;*/
    default:

    }
  }

/**
 * change aliases of condtion in TOK_WHERE, TOK_JOIN
 * the condition can be connect with "and", "or"
 * @param Cond
 * @param destName
 * @param changedAliases
 */
  private boolean changeConditionAlias(ASTNode Cond, String destName,String[] changedAliases, boolean isDelCondn) {
    //TODO:between
    if (Cond == null) {
      return false;
    }

    boolean isDel = false;
    switch (Cond.getToken().getType()) {

    case HiveParser.KW_OR:
    case HiveParser.KW_AND:
      boolean isDelect0 = changeConditionAlias((ASTNode) Cond.getChild(0), destName, changedAliases,isDelCondn);
      boolean isDelect1 = changeConditionAlias((ASTNode) Cond.getChild(1), destName, changedAliases,isDelCondn);
      if(isDelect0 && isDelect1){
        isDel = true;
      }else if(isDelect0 && !isDelect1){
        int pos = Cond.getChildIndex();
        Cond.getParent().setChild(pos, (ASTNode) Cond.getChild(1));
        LOG.info("Delete " + Cond.getChild(0).getText());
      }else if(!isDelect0 && isDelect1){
        int pos = Cond.getChildIndex();
        Cond.getParent().setChild(pos, (ASTNode) Cond.getChild(0));
        LOG.info("Delete " + Cond.getChild(1).getText());
      }

      break;
    case HiveParser.TOK_FUNCTION:
    case HiveParser.TOK_FUNCTIONDI:
    case HiveParser.KW_NOT:
      for(int i=0;i<Cond.getChildCount();i++){
        ASTNode tmp = (ASTNode) Cond.getChild(i);
        boolean isDelete = false;
        if(tmp.getToken().getType() == HiveParser.DOT){
          isDelete = changTabColwithDot(tmp,destName,changedAliases,isDelCondn);
        }else if(tmp.getToken().getType() == HiveParser.TOK_TABLE_OR_COL){
          isDelete = changColName(tmp,isDelCondn);
        }else{
          isDelete = changeConditionAlias(tmp, destName, changedAliases,isDelCondn);
        }
        if(isDelete){
          isDel = true;
        }
      }
      break;
    case HiveParser.EQUAL_NS:
    case HiveParser.EQUAL:
    case HiveParser.KW_LIKE:
    case HiveParser.GREATERTHAN:
    case HiveParser.GREATERTHANOREQUALTO:
    case HiveParser.LESSTHAN:
    case HiveParser.LESSTHANOREQUALTO:
    case HiveParser.NOTEQUAL:
    case HiveParser.STAR:
    case HiveParser.MINUS:
    case HiveParser.PLUS:
    case HiveParser.DIV:
    case HiveParser.DIVIDE:
      boolean isDelete = false;
      ASTNode leftAST = (ASTNode) Cond.getChild(0);
      if(leftAST.getToken().getType() == HiveParser.DOT){
        isDelete = changTabColwithDot(leftAST,destName,changedAliases,isDelCondn);
      }else if(leftAST.getToken().getType() == HiveParser.TOK_TABLE_OR_COL){
        isDelete = changColName(leftAST,isDelCondn);
      }else if(leftAST.getToken().getType() == HiveParser.StringLiteral
          || leftAST.getToken().getType() == HiveParser.Number){
        isDelete = false;
      }else{
        isDelete = changeConditionAlias(leftAST, destName, changedAliases,isDelCondn);
      }
      if(isDelete){
        isDel = true;
      }

      ASTNode rightAST = (ASTNode) Cond.getChild(1);
      if(rightAST.getToken().getType() == HiveParser.DOT){
        isDelete = changTabColwithDot(rightAST,destName,changedAliases,isDelCondn);
      }else if(rightAST.getToken().getType() == HiveParser.TOK_TABLE_OR_COL){
        isDelete = changColName(rightAST,isDelCondn);
      }else if(rightAST.getToken().getType() == HiveParser.StringLiteral
          || rightAST.getToken().getType() == HiveParser.Number){
        isDelete = false;
      }else{
        isDelete = changeConditionAlias(rightAST, destName, changedAliases,isDelCondn);
      }
      if(isDelete){
        isDel = true;
      }

      break;
      default:
        isDel = false;

    }
    return isDel;
  }

  private boolean changColName(ASTNode toktableorcol,boolean isDelCondn) {
    String colName = toktableorcol.getChild(0).getText().toLowerCase();
    Iterator<Entry<String[], String>> iterator = colNameMap.entrySet().iterator();
    while(iterator.hasNext()){// change colName
      Entry<String[], String> entry = iterator.next();
      String[] oldtabAndCol = entry.getKey();
      String newcol = entry.getValue();
      if((oldtabAndCol[1].equals(colName) || newcol.equals(colName)) && !tmpTableCols.contains(newcol) && isDelCondn){
        return true;
      }
      if(oldtabAndCol[1].equals(colName)){
        ASTNode tmpAlias = new ASTNode(new CommonToken(HiveParser.Identifier,newcol));
        tmpAlias.setParent(toktableorcol);
        toktableorcol.setChild(0,tmpAlias);
        LOG.info("change " + oldtabAndCol[1] + " to "+ entry.getValue() );
      }
    }
    return false;
  }

  private boolean changTabColwithDot(ASTNode dot, String destName, String[] changedAliases,boolean isDelCondn) {
    ASTNode left = (ASTNode) dot.getChild(0);
    ASTNode right = (ASTNode) dot.getChild(1);
    String tab = unescapeIdentifier(left.getChild(0).getText()
        .toLowerCase());
    String colName = right.getText().toLowerCase();
    for(int j=0;j < changedAliases.length; j++){ // change tabName
      if(changedAliases[j].toLowerCase().equals(tab)){
        ASTNode tmpAlias = new ASTNode(new CommonToken(HiveParser.Identifier,destName));
        tmpAlias.setParent(left);
        left.setChild(0,tmpAlias);
        LOG.info("change " + tab + " to "+ destName);
      }
    }
    Iterator<Entry<String[], String>> iterator = colNameMap.entrySet().iterator();
    while(iterator.hasNext()){// change colName
      Entry<String[], String> entry = iterator.next();
      String[] tabAndCol = entry.getKey();
      String newcol = entry.getValue();
      if((tabAndCol[1].equals(colName) || newcol.equals(colName)) && !tmpTableCols.contains(newcol) && isDelCondn){
        return true;
      }
      if(tabAndCol[0].equals(tab) && tabAndCol[1].equals(colName)){
        ASTNode tmpAlias = new ASTNode(new CommonToken(HiveParser.Identifier,newcol));
        tmpAlias.setParent(dot);
        dot.setChild(1,tmpAlias);
        LOG.info("change " + tabAndCol[1] + " to "+ entry.getValue() );
      }
    }
    return false;
  }

  public void doExtractTmpCompile() throws HiveException, SerDeException{
    LOG.info("Begin insert FileSinkOperator.");
    for(ReduceSinkOperator tmpRSOp:subQNameToRS.values()){
      assert tmpRSOp.getParentOperators().size() == 1;
      FileSinkOperator fs = (FileSinkOperator) createFileSinkOperator(qb,tmpRSOp.getParentOperators().get(0));
      fs.setParentOperators(tmpRSOp.getParentOperators());
      for(Operator parent :tmpRSOp.getParentOperators()){
        parent.setChildOperators(Utilities.makeList(fs));
      }
    }

      changPCtx(pCtxInc);
      LOG.info("After insert FileSinkOperator, the extract tmp tree is: \n" + Operator.toString(pCtxInc.getTopOps().values()));

      // Generate column access stats if required - wait until column pruning takes place
      // during optimization
      if (HiveConf.getBoolVar(this.conf, HiveConf.ConfVars.HIVE_STATS_COLLECT_SCANCOLS) == true) {
        ColumnAccessAnalyzer columnAccessAnalyzer = new ColumnAccessAnalyzer(pCtxInc);
        setColumnAccessInfo(columnAccessAnalyzer.analyzeColumnAccess());
      }

      if (!ctx.getExplainLogical()) {
        // At this point we have the complete operator tree
        // from which we want to create the map-reduce plan
        MapReduceCompiler compiler = new MapReduceCompiler();
        compiler.init(conf, console, db);
        compiler.compile(pCtxInc, rootTasks, inputs, outputs);
        fetchTask = pCtxInc.getFetchTask();
      }

      LOG.info("Completed plan generation");

      updateAST();//build the AST which include tabref to created tmp tables

      for(String subQName:subQNameToAST.keySet()){//analyzeAlias in ast
        ASTNode currAST = subQNameToAST.get(subQName);
        ASTNode directParent = (ASTNode) currAST.getParent();
        ReduceSinkOperator rs = subQNameToRS.get(subQName);
        String tmpName = RSToTmpName.get(rs);
        String[] tmpAliases = tmpNameToAlias.get(tmpName);
        ASTNode currRoot = subQNameToCurrRoot.get(subQName);
        analyzeAliasBottomUp((ASTNode) currRoot.getParent(),directParent,tmpName,tmpAliases,true);
      }

  }

  /*Create a new FileSink Operator to write data to tmp table*/
  private Operator createFileSinkOperator(QB qb, Operator<? extends OperatorDesc> input) throws SemanticException{

    String tmpName = RSToTmpName.get(input.getChildOperators().get(0));
    String dest = "insclause-"+tmpName;

    ASTNode tab = new ASTNode(new CommonToken(HiveParser.TOK_TAB,"TOK_TAB"));
    ASTNode tabname = new ASTNode(new CommonToken(HiveParser.TOK_TABNAME,"TOK_TABNAME"));
    ASTNode crtTmpTab = new ASTNode(new CommonToken(HiveParser.Identifier,tmpName));
    tab.addChild(tabname);
    tabname.addChild(crtTmpTab);
    crtTmpTab.setParent(tabname);
    tabname.setParent(tab);
    tableSpec ts = new tableSpec(db, conf, tab);

    RowResolver inputRR = opParseCtx.get(input).getRowResolver();
    QBMetaData qbm = qb.getMetaData();

    if (ts.tableHandle.isView()) {
      throw new SemanticException(ErrorMsg.DML_AGAINST_VIEW.getMsg());
    }

    Class<?> outputFormatClass = ts.tableHandle.getOutputFormatClass();
    if (!HiveOutputFormat.class.isAssignableFrom(outputFormatClass)) {
      throw new SemanticException(ErrorMsg.INVALID_OUTPUT_FORMAT_TYPE
          .getMsg(ast, "The class is " + outputFormatClass.toString()));
    }

    // tableSpec ts is got from the query (user specified),
    // which means the user didn't specify partitions in their query,
    // but whether the table itself is partitioned is not know.
    if (ts.specType != SpecType.STATIC_PARTITION) {
      // This is a table or dynamic partition
      qb.getMetaData().setDestForAlias(dest, ts.tableHandle);
      // has dynamic as well as static partitions
      if (ts.partSpec != null && ts.partSpec.size() > 0) {
        qb.getMetaData().setPartSpecForAlias(dest, ts.partSpec);
      }
    } else {
      // This is a partition
      qb.getMetaData().setDestForAlias(dest, ts.partHandle);
    }
    if (HiveConf.getBoolVar(conf, HiveConf.ConfVars.HIVESTATSAUTOGATHER)) {
      // Set that variable to automatically collect stats during the MapReduce job
      qb.getParseInfo().setIsInsertToTable(true);
      // Add the table spec for the destination table.
      qb.getParseInfo().addTableSpec(ts.tableName.toLowerCase(), ts);
    }

    Table dest_tab = null; // destination table if any
    Partition dest_part = null;// destination partition if any
    String queryTmpdir = null; // the intermediate destination directory
    Path dest_path = null; // the final destination directory
    TableDesc table_desc = null;
    int currentTableId = 0;
    boolean isLocal = false;
    SortBucketRSCtx rsCtx = new SortBucketRSCtx();
    DynamicPartitionCtx dpCtx = null;
    LoadTableDesc ltd = null;
    boolean holdDDLTime = checkHoldDDLTime(qb);
    ListBucketingCtx lbCtx = null;

    dest_tab = ts.tableHandle;
    // Is the user trying to insert into a external tables
    if ((!conf.getBoolVar(HiveConf.ConfVars.HIVE_INSERT_INTO_EXTERNAL_TABLES)) &&
        (dest_tab.getTableType().equals(TableType.EXTERNAL_TABLE))) {
      throw new SemanticException(
          ErrorMsg.INSERT_EXTERNAL_TABLE.getMsg(dest_tab.getTableName()));
    }

    Map<String, String> partSpec = ts.partSpec;
    dest_path = dest_tab.getPath();

    // check for partition
    List<FieldSchema> parts = dest_tab.getPartitionKeys();
    if (parts != null && parts.size() > 0) { // table is partitioned
      if (partSpec == null || partSpec.size() == 0) { // user did NOT specify partition
        throw new SemanticException(generateErrorMessage(
            qb.getParseInfo().getDestForClause(dest),
            ErrorMsg.NEED_PARTITION_ERROR.getMsg()));
      }
      // the HOLD_DDLTIIME hint should not be used with dynamic partition since the
      // newly generated partitions should always update their DDLTIME
      if (holdDDLTime) {
        throw new SemanticException(generateErrorMessage(
            qb.getParseInfo().getDestForClause(dest),
            ErrorMsg.HOLD_DDLTIME_ON_NONEXIST_PARTITIONS.getMsg()));
      }
      dpCtx = qbm.getDPCtx(dest);
      if (dpCtx == null) {
        Utilities.validatePartSpec(dest_tab, partSpec);
        dpCtx = new DynamicPartitionCtx(dest_tab, partSpec,
            conf.getVar(HiveConf.ConfVars.DEFAULTPARTITIONNAME),
            conf.getIntVar(HiveConf.ConfVars.DYNAMICPARTITIONMAXPARTSPERNODE));
        qbm.setDPCtx(dest, dpCtx);
      }

      if (HiveConf.getBoolVar(conf, HiveConf.ConfVars.DYNAMICPARTITIONING)) { // allow DP
        // turn on hive.task.progress to update # of partitions created to the JT
        HiveConf.setBoolVar(conf, HiveConf.ConfVars.HIVEJOBPROGRESS, true);

      } else { // QBMetaData.DEST_PARTITION capture the all-SP case
        throw new SemanticException(generateErrorMessage(
            qb.getParseInfo().getDestForClause(dest),
            ErrorMsg.DYNAMIC_PARTITION_DISABLED.getMsg()));
      }
      if (dpCtx.getSPPath() != null) {
        dest_path = new Path(dest_tab.getPath(), dpCtx.getSPPath());
      }
      if ((dest_tab.getNumBuckets() > 0) &&
          (conf.getBoolVar(HiveConf.ConfVars.HIVEENFORCEBUCKETING))) {
        dpCtx.setNumBuckets(dest_tab.getNumBuckets());
      }
    }

    boolean isNonNativeTable = dest_tab.isNonNative();
    //boolean isNonNativeTable = false;
    if (isNonNativeTable) {
      queryTmpdir = dest_path.toUri().getPath();
    } else {
      queryTmpdir = ctx.getExternalTmpFileURI(dest_path.toUri());
    }
    if (dpCtx != null) {
      // set the root of the temporay path where dynamic partition columns will populate
      dpCtx.setRootPath(queryTmpdir);
    }
    // this table_desc does not contain the partitioning columns
    table_desc = Utilities.getTableDesc(dest_tab);

    // Add sorting/bucketing if needed
    input = genBucketingSortingDest(dest, input, qb, table_desc, dest_tab, rsCtx);

    idToTableNameMap.put(String.valueOf(destTableId), dest_tab.getTableName());
    currentTableId = destTableId;
    destTableId++;

    lbCtx = constructListBucketingCtx(dest_tab.getSkewedColNames(),
        dest_tab.getSkewedColValues(), dest_tab.getSkewedColValueLocationMaps(),
        dest_tab.isStoredAsSubDirectories(), conf);

    // Create the work for moving the table
    // NOTE: specify Dynamic partitions in dest_tab for WriteEntity
    if (!isNonNativeTable) {
      ltd = new LoadTableDesc(queryTmpdir, ctx.getExternalTmpFileURI(dest_path.toUri()),
          table_desc, dpCtx);
      ltd.setReplace(!qb.getParseInfo().isInsertIntoTable(dest_tab.getDbName(),
          dest_tab.getTableName()));
      ltd.setLbCtx(lbCtx);

      if (holdDDLTime) {
        LOG.info("this query will not update transient_lastDdlTime!");
        ltd.setHoldDDLTime(true);
      }
      loadTableWork.add(ltd);
    }

    WriteEntity output = null;

    // Here only register the whole table for post-exec hook if no DP present
    // in the case of DP, we will register WriteEntity in MoveTask when the
    // list of dynamically created partitions are known.
    if ((dpCtx == null || dpCtx.getNumDPCols() == 0)) {
      output = new WriteEntity(dest_tab);
      if (!outputs.add(output)) {
        throw new SemanticException(ErrorMsg.OUTPUT_SPECIFIED_MULTIPLE_TIMES
            .getMsg(dest_tab.getTableName()));
      }
    }
    if ((dpCtx != null) && (dpCtx.getNumDPCols() >= 0)) {
      // No static partition specified
      if (dpCtx.getNumSPCols() == 0) {
        output = new WriteEntity(dest_tab, false);
        outputs.add(output);
      }
      // part of the partition specified
      // Create a DummyPartition in this case. Since, the metastore does not store partial
      // partitions currently, we need to store dummy partitions
      else {
        try {
          String ppath = dpCtx.getSPPath();
          ppath = ppath.substring(0, ppath.length() - 1);
          DummyPartition p =
              new DummyPartition(dest_tab, dest_tab.getDbName()
                  + "@" + dest_tab.getTableName() + "@" + ppath,
                  partSpec);
          output = new WriteEntity(p, false);
          outputs.add(output);
        } catch (HiveException e) {
          throw new SemanticException(e.getMessage(), e);
        }
      }
    }

    ctx.getLoadTableOutputMap().put(ltd, output);


    input = genConversionSelectOperator(dest, qb, input, table_desc, dpCtx);
    inputRR = opParseCtx.get(input).getRowResolver();

    ArrayList<ColumnInfo> vecCol = new ArrayList<ColumnInfo>();

    try {
      StructObjectInspector rowObjectInspector = (StructObjectInspector) table_desc
          .getDeserializer().getObjectInspector();
      List<? extends StructField> fields = rowObjectInspector
          .getAllStructFieldRefs();
      for (int i = 0; i < fields.size(); i++) {
        vecCol.add(new ColumnInfo(fields.get(i).getFieldName(), TypeInfoUtils
            .getTypeInfoFromObjectInspector(fields.get(i)
            .getFieldObjectInspector()), "", false));
      }
    } catch (Exception e) {
      throw new SemanticException(e.getMessage(), e);
    }

    RowSchema fsRS = new RowSchema(vecCol);

    // The output files of a FileSink can be merged if they are either not being written to a table
    // or are being written to a table which is either not bucketed or enforce bucketing is not set
    // and table the table is either not sorted or enforce sorting is not set
    boolean canBeMerged = (dest_tab == null || !((dest_tab.getNumBuckets() > 0 &&
        conf.getBoolVar(HiveConf.ConfVars.HIVEENFORCEBUCKETING)) ||
        (dest_tab.getSortCols() != null && dest_tab.getSortCols().size() > 0 &&
        conf.getBoolVar(HiveConf.ConfVars.HIVEENFORCESORTING))));

    FileSinkDesc fileSinkDesc = new FileSinkDesc(
      queryTmpdir,
      table_desc,
      conf.getBoolVar(HiveConf.ConfVars.COMPRESSRESULT),
      currentTableId,
      rsCtx.isMultiFileSpray(),
      canBeMerged,
      rsCtx.getNumFiles(),
      rsCtx.getTotalFiles(),
      rsCtx.getPartnCols(),
      dpCtx);

    /* Set List Bucketing context. */
    if (lbCtx != null) {
      lbCtx.processRowSkewedIndex(fsRS);
      lbCtx.calculateSkewedValueSubDirList();
    }
    fileSinkDesc.setLbCtx(lbCtx);

    // set it in plan instead of runtime in FileSinkOperator
    fileSinkDesc.setStatsCollectRawDataSize(HiveConf.getBoolVar(conf,
        HiveConf.ConfVars.HIVE_STATS_COLLECT_RAWDATASIZE));

    // set the stats publishing/aggregating key prefix
    // the same as directory name. The directory name
    // can be changed in the optimizer but the key should not be changed
    // it should be the same as the MoveWork's sourceDir.
    fileSinkDesc.setStatsAggPrefix(fileSinkDesc.getDirName());

    if (dest_part != null) {
      try {
        String staticSpec = Warehouse.makePartPath(dest_part.getSpec());
        fileSinkDesc.setStaticSpec(staticSpec);
      } catch (MetaException e) {
        throw new SemanticException(e);
      }
    } else if (dpCtx != null) {
      fileSinkDesc.setStaticSpec(dpCtx.getSPPath());
    }

    Operator ret = putOpInsertMap(OperatorFactory.getAndMakeChild(fileSinkDesc,
        fsRS, input), inputRR);

    if (ltd != null && SessionState.get() != null) {
      SessionState.get().getLineageState()
          .mapDirToFop(ltd.getSourceDir(), (FileSinkOperator) ret);
    }

    if (LOG.isDebugEnabled()) {
      LOG.debug("Created FileSink Plan for clause: " + dest + "dest_path: "
          + dest_path + " row schema: " + inputRR.toString());
    }

    fsopToTable.put((FileSinkOperator) ret, dest_tab);
    return ret;

  }

  private void changPCtx(ParseContext pCtx){

    /*remove the topOps that are not involved in tmp tables*/
    HashMap<String, Operator<? extends OperatorDesc>> topOps = pCtx.getTopOps();
    Iterator<Entry<String, Operator<? extends OperatorDesc>>> iter = topOps.entrySet().iterator();
    while(iter.hasNext()){
      String key = iter.next().getKey();
      boolean isFind = false;
      Iterator<Entry<String,String[]>> iter2 = subQNameToAlias.entrySet().iterator();
      while(iter2.hasNext()){
        Entry<String,String[]> curr = iter2.next();
        for(int i=0;i<curr.getValue().length;i++){
          String newName = curr.getKey() + ":" + curr.getValue()[i];
          if(key.length() >= newName.length()){
            if(key.indexOf(newName.toLowerCase()) >= 0){
              isFind = true;
            }
          }
        }
      }
      if(!isFind){
        iter.remove();
      }
    }

    /*following code is to clear the information about origianl FileSinkOperator
     * if don't clear, MapReduceCompile and insert information to table after jobs done will be wrong*/
    pCtx.getQB().setIsQuery(false);
    //pCtx.getLoadFileWork().clear();
    ctx.setResDir(null);
    ctx.setResFile(null);
  }

  /**
   * change the AST's tmpPos's TOK_JOIN to TOK_TABREF
   */
  private void updateAST(){
    for(String subQName:subQNameToAST.keySet()){
      ASTNode tmpAST = subQNameToAST.get(subQName);
      ASTNode parent = (ASTNode) tmpAST.getParent();
      ReduceSinkOperator rs = subQNameToRS.get(subQName);
      String tmpName = RSToTmpName.get(rs);
      ASTNode tabref = new ASTNode(new CommonToken(HiveParser.TOK_TABREF,"TOK_TABREF"));
      ASTNode tabname = new ASTNode(new CommonToken(HiveParser.TOK_TABNAME,"TOK_TABNAME"));
      ASTNode crtTmpTab = new ASTNode(new CommonToken(HiveParser.Identifier,tmpName));
      ASTNode tmpAlias = new ASTNode(new CommonToken(HiveParser.Identifier,tmpName));

      int tmpPos = tmpAST.getChildIndex();

      parent.setChild(tmpPos, tabref);
      tabref.addChild(tabname);
      tabref.addChild(tmpAlias);
      tabname.addChild(crtTmpTab);
      crtTmpTab.setParent(tabname);
      tabname.setParent(tabref);
      tmpAlias.setParent(tabref);
      tabref.setParent(parent);
    }
  }

}





