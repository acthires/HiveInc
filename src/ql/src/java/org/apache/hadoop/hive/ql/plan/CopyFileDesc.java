package org.apache.hadoop.hive.ql.plan;

import java.io.Serializable;

public class CopyFileDesc implements Serializable{
  /**
   *
   */
  private static final long serialVersionUID = 1L;
  String sourceDir;
  String targetDir;
  boolean isDfsDir;
  public CopyFileDesc(String source,String target,boolean isDfs){
    this.sourceDir= source;
    this.targetDir = target;
    this.isDfsDir = isDfs;
  }

  public String getTargetDir(){
    return targetDir;
  }

  public String getSourceDir(){
    return sourceDir;
  }

  public boolean getIsDfDir(){
    return isDfsDir;
  }
}
