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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.*;
import java.util.*;

import org.apache.hadoop.hive.ql.QTestUtil;
import org.apache.hadoop.hive.ql.exec.Task;

public class TestParse extends TestCase {

  private static QTestUtil qt;

  static {
    try {
      boolean miniMR = false;
      if ("".equals("miniMR"))
        miniMR = true;
      String hadoopVer = "0.20.2";
      qt = new QTestUtil("/home/kangyanli/hive-0.12.0/src/ql/src/test/results/compiler", "/home/kangyanli/hive-0.12.0/src/build/ql/test/logs/positive", miniMR, hadoopVer);
      qt.init(null);
    } catch (Exception e) {
      System.out.println("Exception: " + e.getMessage());
      e.printStackTrace();
      System.out.flush();
      fail("Unexpected exception in static initialization");
    }
  }


  public TestParse(String name) {
    super(name);
  }

  @Override
  protected void tearDown() {
    try {
      qt.clearPostTestEffects();
      if (getName().equals("testParse_shutdown"))
        qt.shutdown();
    }
    catch (Exception e) {
      System.out.println("Exception: " + e.getMessage());
      e.printStackTrace();
      System.out.flush();
      fail("Unexpected exception in tearDown");
    }
  }

  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTest(new TestParse("testParse_case_sensitivity"));
    suite.addTest(new TestParse("testParse_cast1"));
    suite.addTest(new TestParse("testParse_groupby1"));
    suite.addTest(new TestParse("testParse_groupby2"));
    suite.addTest(new TestParse("testParse_groupby3"));
    suite.addTest(new TestParse("testParse_groupby4"));
    suite.addTest(new TestParse("testParse_groupby5"));
    suite.addTest(new TestParse("testParse_groupby6"));
    suite.addTest(new TestParse("testParse_input1"));
    suite.addTest(new TestParse("testParse_input2"));
    suite.addTest(new TestParse("testParse_input20"));
    suite.addTest(new TestParse("testParse_input3"));
    suite.addTest(new TestParse("testParse_input4"));
    suite.addTest(new TestParse("testParse_input5"));
    suite.addTest(new TestParse("testParse_input6"));
    suite.addTest(new TestParse("testParse_input7"));
    suite.addTest(new TestParse("testParse_input8"));
    suite.addTest(new TestParse("testParse_input9"));
    suite.addTest(new TestParse("testParse_input_part1"));
    suite.addTest(new TestParse("testParse_input_testsequencefile"));
    suite.addTest(new TestParse("testParse_input_testxpath"));
    suite.addTest(new TestParse("testParse_input_testxpath2"));
    suite.addTest(new TestParse("testParse_join1"));
    suite.addTest(new TestParse("testParse_join2"));
    suite.addTest(new TestParse("testParse_join3"));
    suite.addTest(new TestParse("testParse_join4"));
    suite.addTest(new TestParse("testParse_join5"));
    suite.addTest(new TestParse("testParse_join6"));
    suite.addTest(new TestParse("testParse_join7"));
    suite.addTest(new TestParse("testParse_join8"));
    suite.addTest(new TestParse("testParse_sample1"));
    suite.addTest(new TestParse("testParse_sample2"));
    suite.addTest(new TestParse("testParse_sample3"));
    suite.addTest(new TestParse("testParse_sample4"));
    suite.addTest(new TestParse("testParse_sample5"));
    suite.addTest(new TestParse("testParse_sample6"));
    suite.addTest(new TestParse("testParse_sample7"));
    suite.addTest(new TestParse("testParse_subq"));
    suite.addTest(new TestParse("testParse_udf1"));
    suite.addTest(new TestParse("testParse_udf4"));
    suite.addTest(new TestParse("testParse_udf6"));
    suite.addTest(new TestParse("testParse_udf_case"));
    suite.addTest(new TestParse("testParse_udf_when"));
    suite.addTest(new TestParse("testParse_union"));
    suite.addTest(new TestParse("testParse_shutdown"));
    return suite;
  }

  /**
   * Dummy last test. This is only meant to shutdown qt
   */
  public void testParse_shutdown() {
    System.out.println ("Cleaning up " + "TestParse");
  }

  static String debugHint = "\nSee build/ql/tmp/hive.log, "
     + "or try \"ant test ... -Dtest.silent=false\" to get more logs.";

  public void testParse_case_sensitivity() throws Exception {
    runTest("case_sensitivity", "case_sensitivity.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/case_sensitivity.q");
  }

  public void testParse_cast1() throws Exception {
    runTest("cast1", "cast1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/cast1.q");
  }

  public void testParse_groupby1() throws Exception {
    runTest("groupby1", "groupby1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/groupby1.q");
  }

  public void testParse_groupby2() throws Exception {
    runTest("groupby2", "groupby2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/groupby2.q");
  }

  public void testParse_groupby3() throws Exception {
    runTest("groupby3", "groupby3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/groupby3.q");
  }

  public void testParse_groupby4() throws Exception {
    runTest("groupby4", "groupby4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/groupby4.q");
  }

  public void testParse_groupby5() throws Exception {
    runTest("groupby5", "groupby5.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/groupby5.q");
  }

  public void testParse_groupby6() throws Exception {
    runTest("groupby6", "groupby6.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/groupby6.q");
  }

  public void testParse_input1() throws Exception {
    runTest("input1", "input1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/input1.q");
  }

  public void testParse_input2() throws Exception {
    runTest("input2", "input2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/input2.q");
  }

  public void testParse_input20() throws Exception {
    runTest("input20", "input20.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/input20.q");
  }

  public void testParse_input3() throws Exception {
    runTest("input3", "input3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/input3.q");
  }

  public void testParse_input4() throws Exception {
    runTest("input4", "input4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/input4.q");
  }

  public void testParse_input5() throws Exception {
    runTest("input5", "input5.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/input5.q");
  }

  public void testParse_input6() throws Exception {
    runTest("input6", "input6.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/input6.q");
  }

  public void testParse_input7() throws Exception {
    runTest("input7", "input7.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/input7.q");
  }

  public void testParse_input8() throws Exception {
    runTest("input8", "input8.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/input8.q");
  }

  public void testParse_input9() throws Exception {
    runTest("input9", "input9.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/input9.q");
  }

  public void testParse_input_part1() throws Exception {
    runTest("input_part1", "input_part1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/input_part1.q");
  }

  public void testParse_input_testsequencefile() throws Exception {
    runTest("input_testsequencefile", "input_testsequencefile.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/input_testsequencefile.q");
  }

  public void testParse_input_testxpath() throws Exception {
    runTest("input_testxpath", "input_testxpath.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/input_testxpath.q");
  }

  public void testParse_input_testxpath2() throws Exception {
    runTest("input_testxpath2", "input_testxpath2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/input_testxpath2.q");
  }

  public void testParse_join1() throws Exception {
    runTest("join1", "join1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/join1.q");
  }

  public void testParse_join2() throws Exception {
    runTest("join2", "join2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/join2.q");
  }

  public void testParse_join3() throws Exception {
    runTest("join3", "join3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/join3.q");
  }

  public void testParse_join4() throws Exception {
    runTest("join4", "join4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/join4.q");
  }

  public void testParse_join5() throws Exception {
    runTest("join5", "join5.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/join5.q");
  }

  public void testParse_join6() throws Exception {
    runTest("join6", "join6.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/join6.q");
  }

  public void testParse_join7() throws Exception {
    runTest("join7", "join7.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/join7.q");
  }

  public void testParse_join8() throws Exception {
    runTest("join8", "join8.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/join8.q");
  }

  public void testParse_sample1() throws Exception {
    runTest("sample1", "sample1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/sample1.q");
  }

  public void testParse_sample2() throws Exception {
    runTest("sample2", "sample2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/sample2.q");
  }

  public void testParse_sample3() throws Exception {
    runTest("sample3", "sample3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/sample3.q");
  }

  public void testParse_sample4() throws Exception {
    runTest("sample4", "sample4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/sample4.q");
  }

  public void testParse_sample5() throws Exception {
    runTest("sample5", "sample5.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/sample5.q");
  }

  public void testParse_sample6() throws Exception {
    runTest("sample6", "sample6.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/sample6.q");
  }

  public void testParse_sample7() throws Exception {
    runTest("sample7", "sample7.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/sample7.q");
  }

  public void testParse_subq() throws Exception {
    runTest("subq", "subq.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/subq.q");
  }

  public void testParse_udf1() throws Exception {
    runTest("udf1", "udf1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/udf1.q");
  }

  public void testParse_udf4() throws Exception {
    runTest("udf4", "udf4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/udf4.q");
  }

  public void testParse_udf6() throws Exception {
    runTest("udf6", "udf6.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/udf6.q");
  }

  public void testParse_udf_case() throws Exception {
    runTest("udf_case", "udf_case.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/udf_case.q");
  }

  public void testParse_udf_when() throws Exception {
    runTest("udf_when", "udf_when.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/udf_when.q");
  }

  public void testParse_union() throws Exception {
    runTest("union", "union.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/positive/union.q");
  }


  private void runTest(String tname, String fname, String fpath) throws Exception {
    long startTime = System.currentTimeMillis();
    try {
      System.out.println("Begin query: " + fname);

      qt.addFile(fpath);
      org.apache.hadoop.hive.ql.exec.Operator.resetId();
      org.apache.hadoop.hive.ql.exec.TaskFactory.resetId();

      ASTNode tree = qt.parseQuery(fname);
      int ecode = qt.checkParseResults(fname, tree);
      if (ecode != 0) {
        fail("Parse has unexpected out with error code = " + ecode + debugHint);
      }
      List<Task<? extends Serializable>> tasks = qt.analyzeAST(tree);
      ecode = qt.checkPlan(fname, tasks);
      if (ecode != 0) {
        fail("Semantic Analysis has unexpected output with error code = " + ecode
            + debugHint);
      }
      System.out.println("Done query: " + fname);
      qt.getQMap().clear();
    }
    catch (Throwable e) {
      System.out.println("Exception: " + e.getMessage());
      e.printStackTrace();
      System.out.println("Failed query: " + fname);
      System.out.flush();
      fail("Unexpected exception" + debugHint);
    }

    long elapsedTime = System.currentTimeMillis() - startTime;
    System.out.println("Done query: " + fname + " elapsedTime=" + elapsedTime/1000 + "s");
    assertTrue("Test passed", true);
  }
}
