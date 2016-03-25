package org.apache.hadoop.hive.ql.exec;

import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.plan.FakeDesc;
import org.apache.hadoop.hive.ql.plan.api.OperatorType;

public class FakeOperator extends Operator<FakeDesc> {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @Override
  public void processOp(Object row, int tag) throws HiveException {
    // TODO Auto-generated method stub

  }

  @Override
  public String getName() {
    return getOperatorName();
  }

  static public String getOperatorName() {
    return "FAKE";
  }

  @Override
  public OperatorType getType() {
    return OperatorType.FAKE;
  }

}
