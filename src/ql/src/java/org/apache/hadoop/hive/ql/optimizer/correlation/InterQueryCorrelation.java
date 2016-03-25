package org.apache.hadoop.hive.ql.optimizer.correlation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.hive.ql.exec.ReduceSinkOperator;

public class InterQueryCorrelation {

  private boolean isTransmitCorrelation;
  // The bottom layer ReduceSinkOperators. These ReduceSinkOperators are used
  // to record the boundary of this sub-tree which can be evaluated in a single MR
  // job.
  //overlap RS
  private List<ReduceSinkOperator> TCoverlapReduceSinkOperators;
  public List<ReduceSinkOperator> getTCoverlapReduceSinkOperators() {
    return TCoverlapReduceSinkOperators;
  }

  public void setTCoverlapReduceSinkOperators(List<ReduceSinkOperator> tCoverlapReduceSinkOperators) {
    TCoverlapReduceSinkOperators = tCoverlapReduceSinkOperators;
  }

  // correlation RS
  private List<ReduceSinkOperator> tcReduceSinkOperators;
  //same RS
  private List<ReduceSinkOperator> TCsameReduceSinkOperators;
  public List<ReduceSinkOperator> getTCsameReduceSinkOperators() {
    return TCsameReduceSinkOperators;
  }

  public void setTCsameReduceSinkOperators(List<ReduceSinkOperator> tCsameReduceSinkOperators) {
    TCsameReduceSinkOperators = tCsameReduceSinkOperators;
  }

  // The number of reducer(s) should be used for those bottom layer ReduceSinkOperators
  private int numReducers;
  // This is the min number of reducer(s) for the bottom layer ReduceSinkOperators to avoid query
  // executed on too small number of reducers.
  private final int minReducers;

  // All ReduceSinkOperators in this sub-tree. This set is used when we start to remove unnecessary
  // ReduceSinkOperators.
  private final Set<ReduceSinkOperator> allReduceSinkOperators;

  // Since we merge multiple operation paths, we assign new tags to bottom layer
  // ReduceSinkOperatos. This mapping is used to map new tags to original tags associated
  // to these bottom layer ReduceSinkOperators.
  private final Map<Integer, Integer> newTagToOldTag;

  // A map from new tags to indices of children of DemuxOperator (the first Operator at the
  // Reduce side of optimized plan)
  private final Map<Integer, Integer> newTagToChildIndex;

  public InterQueryCorrelation(int minReducers) {
    this.numReducers = -1;
    this.minReducers = minReducers;
    this.allReduceSinkOperators = new HashSet<ReduceSinkOperator>();
    this.newTagToOldTag = new HashMap<Integer, Integer>();
    this.newTagToChildIndex = new HashMap<Integer, Integer>();
    this.isTransmitCorrelation = false;
    //this.tcReduceSinkOperators = new List<ReduceSinkOperator>();
  }

  public Map<Integer, Integer> getNewTagToOldTag() {
    return newTagToOldTag;
  }

  public Map<Integer, Integer> getNewTagToChildIndex() {
    return newTagToChildIndex;
  }

  public void setNewTag(Integer newTag, Integer oldTag, Integer childIndex) {
    newTagToOldTag.put(newTag, oldTag);
    newTagToChildIndex.put(newTag, childIndex);
  }
  public void addToAllReduceSinkOperators(ReduceSinkOperator rsop) {
    allReduceSinkOperators.add(rsop);
  }

  public Set<ReduceSinkOperator> getAllReduceSinkOperators() {
    return allReduceSinkOperators;
  }


  public void setTransmitCorrelation(boolean transmitCorrelation,
      List<ReduceSinkOperator> tcReduceSinkOperators)
  {
    this.isTransmitCorrelation=transmitCorrelation;
    this.tcReduceSinkOperators = tcReduceSinkOperators;
  }
  public boolean hasTransmitCorrelation(){
    return isTransmitCorrelation;
  }
  public List<ReduceSinkOperator> getTcReduceSinkOperators(){
    return tcReduceSinkOperators;
  }

  public int getNumReducers() {
    return numReducers;
  }

  public boolean adjustNumReducers(int newNumReducers) {
    assert newNumReducers != 0;
    if (newNumReducers > 0) {
      // If the new numReducer is less than minReducer, we will not consider
      // ReduceSinkOperator with this newNumReducer as a correlated ReduceSinkOperator
      if (newNumReducers < minReducers) {
        return false;
      }
      if (numReducers > 0) {
        if (newNumReducers != numReducers) {
          // If (numReducers > 0 && newNumReducers > 0 && newNumReducers != numReducers),
          // we will not consider ReduceSinkOperator with this newNumReducer as a correlated
          // ReduceSinkOperator
          return false;
        }
      } else {
        // if numReducers < 0 and newNumReducers > 0
        numReducers = newNumReducers;
      }
    }

    return true;
  }

}
