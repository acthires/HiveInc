package org.apache.hadoop.hive.ql.parse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hive.ql.ErrorMsg;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.metadata.Table;

public class IncQueryParser {
  private static final Log LOG = LogFactory.getLog("hive.ql.parse.IncQueryParser");

  private Date startDate;
  private Date StopDate;
  private int constantDate;
  private String constantUnit;
  private int intervalTime;
  private String intervalUnit;
  //private String incTableName;
  //private String incTableAlias;
  private final ASTNode  originalTree;
  //private ASTNode  incToken;
  private boolean isConstantParam = false;
  private boolean isAfterParam = true;

  /**
   * TOK_TABREF
   *  TOK_TABLENAME(tablename)
   *  TOK_INCRE
   *  aliasname
   */
  private final ArrayList<String> incAliases = new ArrayList<String>();
  private final HashMap<String,String> incAliasToTabName = new HashMap<String,String>();
  private final HashMap<String,Table> incAliasToTable = new HashMap<String,Table>();
  private final ArrayList<ASTNode> incTabRef = new ArrayList<ASTNode>();
  private ArrayList<String> scanAllIncAliases = new ArrayList<String>();
  private ArrayList<String> scanNewFileIncAliases = null;
  private final ArrayList<String> aliasNoOuter = new ArrayList<String>();
  private final HashSet<String> incTabName = new HashSet<String>();

  private static HashMap<String , Integer> dateToId = new HashMap<String , Integer>();

  static{
    dateToId.put("year", 1);
    dateToId.put("month", 2);
    dateToId.put("day", 3);
    dateToId.put("hour", 4);
    dateToId.put("minute", 5);
    dateToId.put("second", 6);
  }

  //default the interval time unit and constant date unit are seconds
  private final static String INTERVAL_UNIT = "second";
  private final static String CONSTANT_UNIT = "second";
  private final static int INTERVAL_TIMES = 10;

  public IncQueryParser( ASTNode tree ) throws SemanticException{
    while ((tree.getToken() == null) && (tree.getChildCount() > 0)) {
      tree = (ASTNode) tree.getChild(0);
    }
    this.originalTree = tree;
    intervalUnit = INTERVAL_UNIT;
    constantUnit = CONSTANT_UNIT;
    startDate = null ;
    StopDate = null;
  }

  /**
   * The rang  of year, month , day ,hour , minute , second etc had
   * been checked in generating date instance, so we do not need to
   * check again!
   * @param ast TOK_STARTTIME or TOK_STOPTTIME
   */
  private void setDateTime( ASTNode ast ){
    int year = 0 , month = 0 , day = 0 , hour = 0,  minute = 0 , second = 0;
    ASTNode date  = (ASTNode) ast.getChild(0).getChild(0);
    if( date.getChildCount() > 3){
      year = Integer.parseInt(date.getChild(0).getText());
      month = Integer.parseInt(date.getChild(2).getText());
      day = Integer.parseInt(date.getChild(4).getText());
    }else{
      // set year from system time
      Calendar cal = Calendar.getInstance();
      year = cal.get(Calendar.YEAR);
      month = Integer.parseInt(date.getChild(0).getText() );
      day = Integer.parseInt(date.getChild(2).getText());
    }
    //get hour:minute:second, if it can not get from ql, setting
    //current time to 0:0:0
    if(((ASTNode)ast.getChild(0)).getChildCount() > 1){
      ASTNode time  = (ASTNode) ast.getChild(0).getChild(1);
      hour = Integer.parseInt(time.getChild(0).getText());
      minute = Integer.parseInt(time.getChild(1).getText());
      if( time.getChildCount() > 2){
        second = Integer.parseInt(time.getChild(2).getText());
      }
    }
    Calendar cal = Calendar.getInstance();
    cal.setLenient(false);
    cal.set(year, month-1, day, hour, minute, second);
    Date dateTime = cal.getTime();
    switch( ast.getType() ){
      case HiveParser.TOK_STARTTIME:
        setStartDate(dateTime);
        break;
      case HiveParser.TOK_STOPTIME:
        setStopDate(dateTime);
        break;
       default:
        // nothing
    }
  }

  public  boolean checkIncTokenParams( ) throws SemanticException{
    int constant = 0 ,interval = INTERVAL_TIMES;
    if(incTabRef .size() == 0){
      return false;
    }
    ASTNode incToken = (ASTNode) incTabRef.get(0).getChild(1);
    int childCount = incToken.getChildCount();
    if(childCount < 1){
      throw new SemanticException("Inc-query parameters less than 1 ");
    }
    for( int i = 0 ; i < childCount  ;i ++){
       ASTNode incChild = (ASTNode) incToken.getChild(i);
       if( incChild.getType() == HiveParser.TOK_STARTTIME ){
         setDateTime(incChild);
       }
       if(incChild.getType() == HiveParser.TOK_STOPTIME){
         isAfterParam = false;
         setDateTime(incChild);
       }
       if(incChild.getType() == HiveParser.TOK_INTERVAL ){
         interval = Integer.parseInt(incChild.getChild(0).getText());
         if(incChild.getChildCount() > 1 ){
            intervalUnit = incChild.getChild(1).getText();
            if(!dateUnitCheck(intervalUnit, incChild.getType())){
              throw new SemanticException("Unit error of interval parameter in inc-query parameters  ");
            }
         }
       }
       if(incChild.getType() == HiveParser.TOK_CONSTANT){
         isConstantParam = true;
         isAfterParam = false;
         constant = Integer.parseInt(incChild.getChild(0).getText());
         if(incChild.getChildCount() > 1 ){
           constantUnit = incChild.getChild(1).getText();
            if(!dateUnitCheck(constantUnit , incChild.getType())){
              throw new SemanticException("Unit error of constant parameter in inc-query parameters  ");
            }
         }
       }
    }
    setIntervalTime(interval);
    setConstantDate(constant);
    return true;
  }

  public Date getStartDate() {
    return startDate;
  }

  public Date getStopDate() {
    return StopDate;
  }

  public int getConstantDate() {
    return constantDate;
  }

  public String getConstantUnit() {
    return constantUnit;
  }

  public int getIntervalTime() {
    return intervalTime;
  }

  public String getIntervalUnit() {
    return intervalUnit;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public void setStopDate(Date stopDate) {
    StopDate = stopDate;
  }

  public void setConstantDate(int constantDate) {
    this.constantDate = constantDate;
  }

  public void setConstantUnit(String constantUnit) {
    this.constantUnit = constantUnit;
  }

  public void setIntervalTime(int intervalTime) {
    this.intervalTime = intervalTime;
  }

  public void setIntervalUnit(String intervalUnit) {
    this.intervalUnit = intervalUnit;
  }

  //convert currently interval time to milliseconds and return
  public long getIntervalSec() {
    int dateType = dateToId.get(intervalUnit);
    long sec = unitToSec(dateType) * intervalTime;
    return sec;
  }

  public long getConstantSec(){
    int dateType = dateToId.get(constantUnit);
    long sec = unitToSec(dateType) * constantDate;
    return sec;
  }

  private long unitToSec( int dateType){
    long sec = 0;
    switch(dateType){
      case 1:
        sec = 365 * 24 * 3600 ; break;
      case 2:
        sec = 30 * 24 * 3600 ; break;
      case 3:
        sec = 24 * 3600 ; break;
      case 4:
        sec = 3600 ; break;
      case 5:
        sec = 60 ; break;
      case 6:
        sec = 1; break;
    }
    return sec;
  }

  public void analyzeIncAlias() throws SemanticException{
    analyzeIncAlias(originalTree , null);
    LOG.info("Find inc Aliases: " + incAliases.toString());
  }

  private void analyzeIncAlias(ASTNode ast, String outerAlias) throws SemanticException {
    if (ast.getToken() != null) {
      switch(ast.getToken().getType()){
      case HiveParser.TOK_SUBQUERY:
        if (ast.getChildCount() != 2) {
          throw new SemanticException(ErrorMsg.NO_SUBQUERY_ALIAS.getMsg(ast));
        }
        String subQalias = BaseSemanticAnalyzer.unescapeIdentifier(ast.getChild(1).getText());
        outerAlias = QB.getAppendedAliasFromId(outerAlias,subQalias);
        analyzeIncAlias((ASTNode) ast.getChild(0),outerAlias);
        break;
      case HiveParser.TOK_TABREF:

        if(ASTNodeUtils.isIncTabRef(ast)){
          String tabalias = ASTNodeUtils.getAliasId(ast);
          String tabName = ASTNodeUtils.getTabeName(ast);
          String longAlias = QB.getAppendedAliasFromId(outerAlias,tabalias);
          incAliases.add(longAlias);
          incAliasToTabName.put(longAlias, tabName);
          incTabRef.add(ast);
          aliasNoOuter.add(tabalias);
          incTabName.add(tabName);
        }
        break;
      case HiveParser.TOK_INSERT:
      case HiveParser.TOK_INSERT_INTO:
        break;
      default:
        for(int i=0;i<ast.getChildCount();i++){
          analyzeIncAlias((ASTNode) ast.getChild(i),outerAlias);
        }
      }
    }
  }

  public ArrayList<ASTNode> getIncTabRef(){
    return incTabRef;
  }

  // convert date object to String , eg :2014-03-04 11:05:00
  private String dateToString ( Date date ){
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return format.format(date);
  }

  public ArrayList<String> getIncTableAlias( ){
    return incAliases;
  }

  public void setIncAliasToTable(Hive db) throws HiveException{
    for(Map.Entry<String,String> cur :incAliasToTabName.entrySet()){
      String alias = cur.getKey();
      String tableName = cur.getValue();
      Table table = db.getTable(tableName);
      incAliasToTable.put(alias, table);
    }
  }

  public Table getTableForAlias(String alias){
    return incAliasToTable.get(alias);
  }

  private boolean dateUnitCheck(String date , int type ){
    String pattern = "^(year|month|day|hour|minute|second|week)s?$";
    Pattern r = Pattern.compile(pattern , Pattern.CASE_INSENSITIVE);
    Matcher matcher = r.matcher(date);
    boolean patternRes = matcher.matches();
    String res = matcher.group().split("s$")[0];
    if( type == HiveParser.TOK_INTERVAL){
      setIntervalUnit(res);
    }
    if( type == HiveParser.TOK_CONSTANT){
      setConstantUnit(res);
    }
    return patternRes;
  }

  public ASTNode getOriginalTree() {
    return originalTree;
  }

  public boolean isConstantParam() {
    return isConstantParam;
  }

  public boolean isAfterParam() {
    return isAfterParam;
  }

  public void setScanAllIncAliases(ArrayList<String> scanAllInc) {
    this.scanAllIncAliases = scanAllInc;
  }

  public ArrayList<String> getScanAllIncAliases(){
    return scanAllIncAliases;
  }

  public ArrayList<String> getScanNewFileIncAliases(){
    scanNewFileIncAliases = new ArrayList<String>();
    for(String alias:incAliases){
      if(!scanAllIncAliases.contains(alias)){
        scanNewFileIncAliases.add(alias);
      }
    }
    return scanNewFileIncAliases;
  }

  public ArrayList<String> getAliasNoOuter() {
   return aliasNoOuter;
  }

  public HashSet<String> getIncTabNames(){
    return incTabName;
  }
}
