package org.apache.hadoop.hive.ql.parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.CommonToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hive.ql.lib.Node;
import org.apache.hadoop.hive.ql.parse.IncSplitSemanticAnalyzer.CacheColumn;
import org.apache.hadoop.hive.ql.parse.IncSplitSemanticAnalyzer.ExprFunction;

public class ASTNodeUtils {

  static final private Log LOG = LogFactory.getLog(ASTNodeUtils.class.getName());

  /*Create a ASTNode for TOK_SELECT's child : TOK_SELEXPR
   * TOK_SELEXPR's child  should not be a  TOK_FUNCTION,we have the method  newSelExpr1withFunc to do it
   * It's used in Phase 1(generate the insert cache table QL)
   * */
  public static ASTNode newSelExpr1withoutFunc(ASTNode origintokselexpr,CacheColumn col) throws SemanticException{
    ASTNode tableselexpr = new ASTNode(new CommonToken(HiveParser.TOK_SELEXPR, "TOK_SELEXPR"));
    ASTNode selexprChild = null;
    if(origintokselexpr.getToken().getType() == HiveParser.TOK_SELEXPR){
      ASTNode child = (ASTNode)origintokselexpr.getChild(0);
      int type = child.getToken().getType();
      if(type == HiveParser.TOK_TABLE_OR_COL){
        selexprChild = child;
      }else if (type == HiveParser.TOK_FUNCTION){
        throw new SemanticException("ERROR: TOK_SELEXPR's child should not be a  TOK_FUNCTION,we have the method  newSelExpr1withFunc to do it");
      }else if(type == HiveParser.DOT){
        selexprChild = child;
      }

    }else{
      throw new SemanticException("ERROR: ASTNode is not a  TOK_SELEXPR");
    }
    if(selexprChild != null){
      tableselexpr.addChild(selexprChild);
    }else{
      throw new SemanticException("ERROR: can't generate TOK_SELEXPR's Child!");
    }
    return tableselexpr;
  }

  /*Create a ASTNode for TOK_SELECT's child : TOK_SELEXPR
   * TOK_SELEXPR's child  should not be a  TOK_FUNCTION,we have the method  newSelExpr1withFunc to do it
   * It's used in Phase 2(generate the select from cache table QL)
   * */
  public static ASTNode newSelExpr2withoutFunc(ASTNode ast,CacheColumn col, ASTNode expralias) throws SemanticException{

    ASTNode tableselexpr = new ASTNode(new CommonToken(HiveParser.TOK_SELEXPR, "TOK_SELEXPR"));
    ASTNode selexprChild = null;
    if(ast.getToken().getType() == HiveParser.TOK_SELEXPR){
      ASTNode child = (ASTNode)ast.getChild(0);
      int type = child.getToken().getType();
      if(type == HiveParser.TOK_TABLE_OR_COL){
        String colname=col.columnname;
        selexprChild = newtoktableorcol(colname);

      }else if (type == HiveParser.TOK_FUNCTION){
        throw new SemanticException("ERROR: TOK_SELEXPR's child should not be a  TOK_FUNCTION," +
        		"we have the method  newSelExpr1withFunc to do it");
      }else if(type == HiveParser.DOT){
        String colname = col.columnname;
        selexprChild = newtoktableorcol(colname);
      }

    }else{
      throw new SemanticException("ERROR: ASTNode is not a  TOK_SELEXPR");
    }
    if(selexprChild != null){
      tableselexpr.addChild(selexprChild);
      selexprChild.setParent(tableselexpr);
      if(expralias!=null){
        tableselexpr.addChild(expralias);
        expralias.setParent(tableselexpr);
      }

    }else{
      throw new SemanticException("ERROR: can't generate TOK_SELEXPR's Child!");
    }

    return tableselexpr;
  }

  /*Create a ASTNode for TOK_SELECT's child : TOK_SELEXPR
   * TOK_SELEXPR's child  should be a  TOK_FUNCTION
   * It's used in Phase 1(generate the insert cache table QL)
   * */
  public static void newSelExpr1withFunc(ASTNode origintokselexpr, Map<ASTNode,ArrayList<ExprFunction>> funcmap,ArrayList<ASTNode> selexprlist ) throws SemanticException{

    if(origintokselexpr.getToken().getType() == HiveParser.TOK_SELEXPR){
      ASTNode child = (ASTNode)origintokselexpr.getChild(0);
      int type = child.getToken().getType();
     if (type == HiveParser.TOK_FUNCTION
         || type == HiveParser.TOK_FUNCTIONDI/*kangyanli added*/){
        ArrayList<ExprFunction> exprlist = funcmap.get(child);
        ExprFunction phase1exprfunc = exprlist.get(0);
        ASTNode selexprChild = null;
        ASTNode tableselexpr = null;
        if(phase1exprfunc == null){
          throw new SemanticException("ERROR:  no phase1exprfunc");
        }

        while(phase1exprfunc != null){
          tableselexpr = new ASTNode(new CommonToken(HiveParser.TOK_SELEXPR, "TOK_SELEXPR"));
          selexprChild = phase1exprfunc.FuncAST;
          tableselexpr.addChild(selexprChild);
          selexprChild.setParent(tableselexpr);
          selexprlist.add(tableselexpr);
          phase1exprfunc = phase1exprfunc.next;
        }

      }else{
        throw new SemanticException("ERROR: ASTNode is not a  TOK_FUNCTION");
      }
    }else{
      throw new SemanticException("ERROR: ASTNode is not a  TOK_SELEXPR");
    }
  }
  /*Create a ASTNode for TOK_SELECT's child : TOK_SELEXPR
   * TOK_SELEXPR's child  should be a  TOK_FUNCTION
   * It's used in Phase 2(generate the select from cache table QL)
   * */
  public static void newSelExpr2withFunc(ASTNode origintokselexpr, Map<ASTNode,ArrayList<ExprFunction>> funcmap,
      ArrayList<ASTNode> selexprlist, ASTNode expralias) throws SemanticException{

    if(origintokselexpr.getToken().getType() == HiveParser.TOK_SELEXPR){
      ASTNode child = (ASTNode)origintokselexpr.getChild(0);
      int type = child.getToken().getType();
     if (type == HiveParser.TOK_FUNCTION
         || type == HiveParser.TOK_FUNCTIONDI/*kangyanli added*/){
        ArrayList<ExprFunction> exprlist = funcmap.get(child);
        ExprFunction phase1exprfunc = exprlist.get(1);
        ASTNode selexprChild = null;
        ASTNode tableselexpr = null;
        if(phase1exprfunc == null){
          throw new SemanticException("ERROR:  no phase1exprfunc");
        }

        while(phase1exprfunc != null){
          tableselexpr = new ASTNode(new CommonToken(HiveParser.TOK_SELEXPR, "TOK_SELEXPR"));
          selexprChild = phase1exprfunc.FuncAST;
          tableselexpr.addChild(selexprChild);
          selexprChild.setParent(tableselexpr);
          if(expralias != null){
            tableselexpr.addChild(expralias);
            expralias.setParent(tableselexpr);
          }
          selexprlist.add(tableselexpr);
          phase1exprfunc = phase1exprfunc.next;
        }

      }else{
        throw new SemanticException("ERROR: ASTNode is not a  TOK_FUNCTION");
      }
    }else{
      throw new SemanticException("ERROR: ASTNode is not a  TOK_SELEXPR");
    }
  }

  /*Create a ASTNode for TOK_SELECT's child : TOK_SELEXPR
   * TOK_SELEXPR's child  should be a  TOK_FUNCTION
   * It's used in Phase 2(generate the select from cache table QL)
   * */
  public static ASTNode newSelExpr2withFunc(ASTNode origintokselexpr, Map<ASTNode,ArrayList<ExprFunction>> funcmap) throws SemanticException{
    int type = origintokselexpr.getToken().getType();
   if (type == HiveParser.TOK_FUNCTION){
      ArrayList<ExprFunction> exprlist = funcmap.get(origintokselexpr);
      ExprFunction phase1exprfunc = exprlist.get(1);

      if(phase1exprfunc == null){
        throw new SemanticException("ERROR:  no phase1exprfunc");
      }
      if(phase1exprfunc != null){
        return phase1exprfunc.FuncAST;
        //phase1exprfunc = phase1exprfunc.next;
      }
      return null;

    }else{
      throw new SemanticException("ERROR: ASTNode is not a  TOK_FUNCTION");
    }


  }

  /*Create a ASTNode like this:
   * TOK_FUNCTION
   *   Identifier:funcname
   *   TOK_TABLE_OR_COL
   *     Identifier :colname
   * */
  public static ASTNode newfunctinAST(String funcname,String colname ) throws SemanticException{

    ASTNode tokfun = new ASTNode(new CommonToken(HiveParser.TOK_FUNCTION, "TOK_FUNCTION"));
    ASTNode tokid = new ASTNode(new CommonToken(HiveParser.Identifier, funcname));
    ASTNode toktableorcol = newtoktableorcol(colname);
    tokfun.addChild(tokid);
    tokfun.addChild(toktableorcol);
    tokid.setParent(tokfun);
    toktableorcol.setParent(tokfun);

    return tokfun;

  }
  /*Create a ASTNode like this:
   * TOK_FUNCTION
   *   Identifier:funcname
   *   childASTNode
   * */
  public static ASTNode newfunctinAST(String funcname,ASTNode childASTNode ) throws SemanticException{

    ASTNode tokfun = new ASTNode(new CommonToken(HiveParser.TOK_FUNCTION, "TOK_FUNCTION"));
    ASTNode tokid = new ASTNode(new CommonToken(HiveParser.Identifier, funcname));
    tokfun.addChild(tokid);
    tokfun.addChild(childASTNode);
    tokid.setParent(tokfun);
    return tokfun;

  }
  /*Create a ASTNode like this:
   * DIVIDE
   *   leftASTNode
   *   rightASTNode
   * */
  public static ASTNode newDividAST(ASTNode leftASTNode ,ASTNode rightASTNode ){
    ASTNode tokdivide = new ASTNode(new CommonToken(HiveParser.DIVIDE, "/"));
    tokdivide.addChild(leftASTNode);
    tokdivide.addChild(rightASTNode);
    leftASTNode.setParent(tokdivide);
    rightASTNode.setParent(tokdivide);
    return tokdivide;

  }

  /*Create a ASTNode like this:
   * Star
   *   leftASTNode
   *   rightASTNode
   * */
  public static ASTNode newStarAST(ASTNode leftASTNode ,ASTNode rightASTNode ){
    ASTNode tokdivide = new ASTNode(new CommonToken(HiveParser.STAR, "*"));
    tokdivide.addChild(leftASTNode);
    tokdivide.addChild(rightASTNode);
    leftASTNode.setParent(tokdivide);
    rightASTNode.setParent(tokdivide);
    return tokdivide;

  }

  /*Create a ASTNode like this:
   * MINUS
   *   leftASTNode
   *   rightASTNode
   * */
  public static ASTNode newMinusAST(ASTNode leftASTNode ,ASTNode rightASTNode ){
    ASTNode tokdivide = new ASTNode(new CommonToken(HiveParser.MINUS, "-"));
    tokdivide.addChild(leftASTNode);
    tokdivide.addChild(rightASTNode);
    leftASTNode.setParent(tokdivide);
    rightASTNode.setParent(tokdivide);
    return tokdivide;

  }

  /*Create a ASTNode like this:
   * PLUS
   *   leftASTNode
   *   rightASTNode
   * */
  public static ASTNode newPlusAST(ASTNode leftASTNode ,ASTNode rightASTNode ){
    ASTNode tokdivide = new ASTNode(new CommonToken(HiveParser.PLUS, "+"));
    tokdivide.addChild(leftASTNode);
    tokdivide.addChild(rightASTNode);
    leftASTNode.setParent(tokdivide);
    rightASTNode.setParent(tokdivide);
    return tokdivide;

  }

  //useless
  public static ASTNode newSelExprChild(ASTNode ast,CacheColumn col ) throws SemanticException{
    ASTNode selexprChild = null;
    int type = ast.getToken().getType();
    if(type == HiveParser.TOK_TABLE_OR_COL){
      String str1="col1";

      selexprChild = newtoktableorcol(str1);

    }else if (type == HiveParser.TOK_FUNCTION){

    }else if(type == HiveParser.DOT){

    }

    return selexprChild;
  }
  /*Create a ASTNode like this:
   * TOK_TABLE_OR_COL
   *   Identifier:tableorcol
   * */
  public static ASTNode newtoktableorcol(String tableorcol) {
    // TODO Auto-generated method stub
    ASTNode toktableorcol = new ASTNode(new CommonToken(HiveParser.TOK_TABLE_OR_COL, "TOK_TABLE_OR_COL"));
    ASTNode tokid = new ASTNode(new CommonToken(HiveParser.Identifier, tableorcol));
    tokid.setParent(toktableorcol);
    toktableorcol.addChild(tokid);
    return toktableorcol;

  }

  /*Create a ASTNode like this:
   * TOK_TAB
   *   TOK_TABNAME:tabname
   * */
  public static ASTNode newtokTable(String tabname){
    ASTNode toktab = new ASTNode(new CommonToken(HiveParser.TOK_TAB, "TOK_TAB"));
    ASTNode toktabname = new ASTNode(new CommonToken(HiveParser.TOK_TABNAME, "TOK_TABNAME"));
    ASTNode tokid = new ASTNode(new CommonToken(HiveParser.Identifier, tabname));
    toktab.addChild(toktabname);
    toktabname.setParent(toktab);
    toktabname.addChild(tokid);
    tokid.setParent(toktabname);
    return toktab;
  }

  /*Create a ASTNode like this:
   * TOK_DIR
   *   TOK_TMP_FILE
   * */
  public static ASTNode newtoktmpfile() {

    ASTNode tokdir = new ASTNode(new CommonToken(HiveParser.TOK_DIR, "TOK_DIR"));
    ASTNode toktmpfile = new ASTNode(new CommonToken(HiveParser.TOK_TMP_FILE, "TOK_TMP_FILE"));
    tokdir.addChild(toktmpfile);
    toktmpfile.setParent(tokdir);
    return tokdir;
  }

  /*Create a ASTNode like this:
   * TOK_TABREF
   *   TOK_TABNAME
   *     Identifier:tabName
   * */
  public static ASTNode newtoktabref(String tabName) {

    ASTNode tokid = new ASTNode(new CommonToken(HiveParser.Identifier, tabName));
    ASTNode tablename = new ASTNode(new CommonToken(HiveParser.TOK_TABNAME, "TOK_TABNAME"));
    ASTNode toktabref = new ASTNode(new CommonToken(HiveParser.TOK_TABREF, "TOK_TABREF"));
    tokid.setParent(tablename);
    tablename.addChild(tokid);
    tablename.setParent(toktabref);
    toktabref.addChild(tablename);
    return toktabref;
  }

  /*Create a ASTNode like this:
   *     Identifier:alias
   * */

  public static ASTNode newalias(ASTNode originalias) {
    String alias = originalias.getToken().getText();
    ASTNode tokid = new ASTNode(new CommonToken(HiveParser.Identifier, alias));

    return tokid;
  }




  /*Judge whether the two ASTNodes are same ASTNode
   *
   *Same ASTNode : they have same text,type and children ,but they are different object
   * */
  public static boolean isSameAST(ASTNode ast1 ,ASTNode ast2){
    if(isSameASTdump(ast1,ast2)){
      return true;
    }else{

      if(ast1.getToken().getType()==HiveParser.TOK_TABLE_OR_COL){
        return isSameASTdump((ASTNode)ast1.getChild(0),ast2);
      }
      if(ast2.getToken().getType()==HiveParser.TOK_TABLE_OR_COL){
        return isSameASTdump(ast1,(ASTNode)ast2.getChild(0));
      }
      //ToDo :
      //

      return false;
    }

  }

  /*Use ast.dump() as String to distinguish the two ASTNode
   *ToDO: maybe,it's approximately correct
   * */
  public static boolean isSameASTdump(ASTNode ast1 ,ASTNode ast2){
    String dump1 =ast1.dump();
    String dump2 =ast2.dump();
    if(dump1.equals(dump2)){
      return true;
    }else{
      return false;
    }
  }


  public static ASTNode clone(ASTNode ast){
    //ToDO
    ASTNode newAST = new ASTNode(ast);
    if(ast.getChildren() != null){
      for(Node child: ast.getChildren()){
        ASTNode newChild = clone((ASTNode)child);
        newAST.addChild(newChild);
        newChild.setParent(newAST);
      }
    }

    return newAST;
  }


  /*Use LOG to dump the ASTNode
   * */
  public static void dump(ASTNode ast){
    //ToDO
    LOG.info(ast.dump());

  }

  /**
   * change "colName" to "tabName.colName"
   * @param Cond
   * @param colNameToTabName
   */
  public static void addTabName(ASTNode tokTableOrCol,HashMap<String, String> colNameToTabName){
    ASTNode parent = (ASTNode) tokTableOrCol.getParent();
    int pos = tokTableOrCol.getChildIndex();
    ASTNode colNameAST = (ASTNode) tokTableOrCol.getChild(0);
    String colName = colNameAST.getText().toLowerCase();
    String tabName =colNameToTabName.get(colName);
    if(tabName!= null){
      ASTNode dot = new ASTNode(new CommonToken(HiveParser.DOT,"."));
      ASTNode tabNameAST = new ASTNode(new CommonToken(HiveParser.Identifier,tabName));
      tokTableOrCol.setChild(0, tabNameAST);
      tabNameAST.setParent(tokTableOrCol);
      dot.addChild(tokTableOrCol);
      tokTableOrCol.setParent(dot);
      dot.addChild(colNameAST);
      colNameAST.setParent(dot);
      parent.setChild(pos,dot);
      dot.setParent(parent);
      LOG.info("change " + colName + " to " + tabName + "." + colName);
    }
  }

  public static void changeJoinType(ASTNode oldJoin) {
    ASTNode newJoin = null;
    if(oldJoin.getToken().getType() == HiveParser.TOK_LEFTOUTERJOIN){
      newJoin = new ASTNode(new CommonToken(HiveParser.TOK_RIGHTOUTERJOIN,"rightouterjoin"));
    }else if(oldJoin.getToken().getType() == HiveParser.TOK_RIGHTOUTERJOIN){
      newJoin = new ASTNode(new CommonToken(HiveParser.TOK_LEFTOUTERJOIN,"leftouterjoin"));
    }
    if(newJoin != null){
      int pos= oldJoin.getChildIndex();
      oldJoin.getParent().setChild(pos, newJoin);
      newJoin.setParent(oldJoin.getParent());
      for(int j = 0 ; j < oldJoin.getChildCount() ; j++){
        newJoin.addChild(oldJoin.getChild(j));
        oldJoin.getChild(j).setParent(newJoin);
      }
    }
  }

  public static void moveWhereOuter(ASTNode whereCond, ASTNode directOuterInsert) {
    ASTNode parent = (ASTNode) whereCond.getParent();
    if(directOuterInsert.getChildCount() == 2){
      directOuterInsert.addChild(parent);
      parent.setParent(directOuterInsert);
    }else if(directOuterInsert.getChildCount() >=3){
      ASTNode third = (ASTNode) directOuterInsert.getChild(2);
      if(third.getToken().getType() == HiveParser.TOK_WHERE){
        ASTNode oldCondn = (ASTNode) third.getChild(0);
        ASTNode and = new ASTNode(new CommonToken(HiveParser.KW_AND,"and"));
        and.setParent(third);
        third.setChild(0, and);

        and.addChild(whereCond);
        and.addChild(oldCondn);
        whereCond.setParent(and);
        oldCondn.setParent(and);
      }else{
        ASTNode lastChild = (ASTNode) directOuterInsert.getChild(directOuterInsert.getChildCount()-1);
        directOuterInsert.addChild(lastChild);
        for(int i=2;i<directOuterInsert.getChildCount()-2;i++){
          directOuterInsert.setChild(i+1, directOuterInsert.getChild(i));
        }
        directOuterInsert.setChild(2, parent);
        parent.setParent(directOuterInsert);
      }
    }
  }
  public static boolean isIncTabRef(ASTNode tabref){
    boolean isInc = false;
    for (int index = 1; index < tabref.getChildCount(); index++) {
      ASTNode ct = (ASTNode) tabref.getChild(index);
      if(ct.getToken().getType() == HiveParser.TOK_INCRE){
        isInc = true;
        break;
      }
    }
    return isInc;
  }

  public static String getAliasId(ASTNode tabref){
    int aliasIndex = 0;
    String tabalias = null;
    for (int index = 1; index < tabref.getChildCount(); index++) {
      ASTNode ct = (ASTNode) tabref.getChild(index);
      if (ct.getToken().getType() != HiveParser.TOK_TABLEBUCKETSAMPLE &&
          ct.getToken().getType() != HiveParser.TOK_TABLESPLITSAMPLE &&
          ct.getToken().getType() != HiveParser.TOK_TABLEPROPERTIES &&
          ct.getToken().getType() != HiveParser.TOK_INCRE ){
        aliasIndex = index;
      }
    }
    String tableName = BaseSemanticAnalyzer.getUnescapedUnqualifiedTableName((ASTNode) (tabref.getChild(0)));
    if (aliasIndex != 0) {
      tabalias = BaseSemanticAnalyzer.unescapeIdentifier(tabref.getChild(aliasIndex).getText());
    }
    else {
      tabalias = tableName;
    }
    return tabalias;
  }

  public static String getTabeName(ASTNode tabref) {
    return BaseSemanticAnalyzer.getUnescapedUnqualifiedTableName((ASTNode) (tabref.getChild(0)));
  }

}
