package org.apache.hadoop.hive.ql.optimizer.correlation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.exec.OperatorFactory;
import org.apache.hadoop.hive.ql.exec.ReduceSinkOperator;
import org.apache.hadoop.hive.ql.exec.Utilities;
import org.apache.hadoop.hive.ql.optimizer.correlation.RelaxCorrelationOptimizer.OrigColumnNodeProcCtx;
import org.apache.hadoop.hive.ql.parse.ParseContext;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.plan.DemuxDesc;
import org.apache.hadoop.hive.ql.plan.OperatorDesc;
import org.apache.hadoop.hive.ql.plan.TableDesc;
/**
 * QueryPlanTreeTransformation contains static methods used to transform
 * the query plan tree (operator tree) based on the transmit correlation we have
 * detected by Correlation Optimizer.
 */
@SuppressWarnings("unused")
public class QueryPlanTreeTransformation_TC {
  private static final Log LOG = LogFactory.getLog(QueryPlanTreeTransformation_TC.class.getName());
  private static void setNewTag(IntraQueryCorrelation correlation,
      List<Operator<? extends OperatorDesc>> childrenOfDemux,
      ReduceSinkOperator rsop, Map<ReduceSinkOperator, Integer> tcRSToNewTag)
          throws SemanticException {
    int newTag = tcRSToNewTag.get(rsop);
    int oldTag = rsop.getConf().getTag();
    if (oldTag == -1) {
      // if this child of DemuxOperator does not use tag, we just set the oldTag to 0.
      oldTag = 0;
    }
    Operator<? extends OperatorDesc> child = CorrelationUtilities.getSingleChild(rsop, true);
    if (!childrenOfDemux.contains(child)) {
      childrenOfDemux.add(child);
    }
    int childIndex = childrenOfDemux.indexOf(child);
    correlation.setNewTag(newTag, oldTag, childIndex);
    rsop.getConf().setTag(newTag);
  }
  protected static void applyCorrelation(
      ParseContext pCtx,
      OrigColumnNodeProcCtx origCorrCtx,
      IntraQueryCorrelation correlation)
      throws SemanticException {
    final List<ReduceSinkOperator> tcReduceSinkOperators =
      correlation.getTcReduceSinkOperators();
    LOG.info(tcReduceSinkOperators);
    final int numReducers = correlation.getNumReducers();
    List<Operator<? extends OperatorDesc>> childrenOfDemux =
        new ArrayList<Operator<? extends OperatorDesc>>();
    List<Operator<? extends OperatorDesc>> parentRSsOfDemux =
        new ArrayList<Operator<? extends OperatorDesc>>();
    Map<Integer, Integer> childIndexToOriginalNumParents =
        new HashMap<Integer, Integer>();
    List<TableDesc> keysSerializeInfos = new ArrayList<TableDesc>();
    List<TableDesc> valuessSerializeInfos = new ArrayList<TableDesc>();
    Map<ReduceSinkOperator, Integer> tcRSToNewTag =
        new HashMap<ReduceSinkOperator, Integer>();
    int newTag = 0;
    for (ReduceSinkOperator rsop: tcReduceSinkOperators) {
      rsop.getConf().setNumReducers(numReducers);
      tcRSToNewTag.put(rsop, newTag);
      parentRSsOfDemux.add(rsop);
      keysSerializeInfos.add(rsop.getConf().getKeySerializeInfo());
      valuessSerializeInfos.add(rsop.getConf().getValueSerializeInfo());
      Operator<? extends OperatorDesc> child = CorrelationUtilities.getSingleChild(rsop, true);
      if (!childrenOfDemux.contains(child)) {
        childrenOfDemux.add(child);
        int childIndex = childrenOfDemux.size() - 1;
        childIndexToOriginalNumParents.put(childIndex, child.getNumParent());
      }
      newTag++;
    }

    for (ReduceSinkOperator rsop: tcReduceSinkOperators) {
      setNewTag(correlation, childrenOfDemux, rsop, tcRSToNewTag);
    }

    // Create the DemuxOperaotr
    DemuxDesc demuxDesc =
        new DemuxDesc(
            correlation.getNewTagToOldTag(),
            correlation.getNewTagToChildIndex(),
            childIndexToOriginalNumParents,
            keysSerializeInfos,
            valuessSerializeInfos);
    Operator<? extends OperatorDesc> demuxOp = OperatorFactory.get(demuxDesc);
    demuxOp.setChildOperators(childrenOfDemux);
    demuxOp.setParentOperators(parentRSsOfDemux);
    for (Operator<? extends OperatorDesc> child: childrenOfDemux) {
      List<Operator<? extends OperatorDesc>> parentsWithMultipleDemux =
          new ArrayList<Operator<? extends OperatorDesc>>();
      boolean hasTcReduceSinkOperators = false;
      boolean hasNonTcReduceSinkOperators = false;
      for (int i = 0; i < child.getParentOperators().size(); i++) {
        Operator<? extends OperatorDesc> p = child.getParentOperators().get(i);
        assert p instanceof ReduceSinkOperator;
        ReduceSinkOperator rsop = (ReduceSinkOperator)p;
        if (tcReduceSinkOperators.contains(rsop)) {
          hasTcReduceSinkOperators = true;
          parentsWithMultipleDemux.add(demuxOp);
        } else {
          hasNonTcReduceSinkOperators = true;
          parentsWithMultipleDemux.add(rsop);
        }
      }
      if (hasTcReduceSinkOperators && hasNonTcReduceSinkOperators) {
        child.setParentOperators(parentsWithMultipleDemux);
      } else {
        child.setParentOperators(Utilities.makeList(demuxOp));
      }
    }
    for (Operator<? extends OperatorDesc> parent: parentRSsOfDemux) {
      parent.setChildOperators(Utilities.makeList(demuxOp));
    }

  }
}
