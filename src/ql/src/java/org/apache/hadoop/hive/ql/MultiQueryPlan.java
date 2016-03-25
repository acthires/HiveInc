package org.apache.hadoop.hive.ql;

import java.util.HashMap;

import org.apache.hadoop.hive.ql.exec.FetchTask;
import org.apache.hadoop.hive.ql.parse.SemanticAnalyzer;

public class MultiQueryPlan extends QueryPlan{
  private HashMap<Integer,FetchTask> FetchTaskList;

  public MultiQueryPlan(String queryString, SemanticAnalyzer sem, Long startTime) {
    super(queryString, sem,startTime);
    this.FetchTaskList=sem.getfetchtasklist();
  }


  public void Setfetchtasklist(HashMap<Integer,FetchTask> fetchtasklist){
    this.FetchTaskList=fetchtasklist;
  }
  public HashMap<Integer,FetchTask> getfetchtasklist(){
    return this.FetchTaskList;
  }


}
