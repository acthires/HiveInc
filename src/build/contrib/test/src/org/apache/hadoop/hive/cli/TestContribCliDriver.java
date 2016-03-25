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
package org.apache.hadoop.hive.cli;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.*;
import java.util.*;

import org.apache.hadoop.hive.ql.QTestUtil;
import org.apache.hadoop.hive.ql.session.SessionState;

public class TestContribCliDriver extends TestCase {

  private static QTestUtil qt;

  static {
    try {
      boolean miniMR = false;
      String hadoopVer;
      if ("".equals("miniMR"))
        miniMR = true;
      hadoopVer = "0.20.2";
      qt = new QTestUtil("/home/kangyanli/hive-0.12.0/src/contrib/src/test/results/clientpositive", "/home/kangyanli/hive-0.12.0/src/build/contrib/test/logs/contribclientpositive", miniMR, hadoopVer);

      // do a one time initialization
      qt.cleanUp();
      qt.createSources();

    } catch (Exception e) {
      System.out.println("Exception: " + e.getMessage());
      e.printStackTrace();
      System.out.flush();
      fail("Unexpected exception in static initialization");
    }
  }

  public TestContribCliDriver(String name) {
    super(name);
  }

  @Override
  protected void setUp() {
    try {
      qt.clearTestSideEffects();
    } catch (Exception e) {
      System.out.println("Exception: " + e.getMessage());
      e.printStackTrace();
      System.out.flush();
      fail("Unexpected exception in setup");
    }
  }

  /**
   * Dummy last test. This is only meant to shutdown qt
   */
  public void testCliDriver_shutdown() {
    System.out.println ("Cleaning up " + "TestContribCliDriver");
  }

  @Override
  protected void tearDown() {
    try {
      qt.clearPostTestEffects();
      if (getName().equals("testCliDriver_shutdown"))
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
    suite.addTest(new TestContribCliDriver("testCliDriver_dboutput"));
    suite.addTest(new TestContribCliDriver("testCliDriver_fileformat_base64"));
    suite.addTest(new TestContribCliDriver("testCliDriver_java_mr_example"));
    suite.addTest(new TestContribCliDriver("testCliDriver_lateral_view_explode2"));
    suite.addTest(new TestContribCliDriver("testCliDriver_serde_regex"));
    suite.addTest(new TestContribCliDriver("testCliDriver_serde_s3"));
    suite.addTest(new TestContribCliDriver("testCliDriver_serde_typedbytes"));
    suite.addTest(new TestContribCliDriver("testCliDriver_serde_typedbytes2"));
    suite.addTest(new TestContribCliDriver("testCliDriver_serde_typedbytes3"));
    suite.addTest(new TestContribCliDriver("testCliDriver_serde_typedbytes4"));
    suite.addTest(new TestContribCliDriver("testCliDriver_serde_typedbytes5"));
    suite.addTest(new TestContribCliDriver("testCliDriver_serde_typedbytes6"));
    suite.addTest(new TestContribCliDriver("testCliDriver_serde_typedbytes_null"));
    suite.addTest(new TestContribCliDriver("testCliDriver_udaf_example_avg"));
    suite.addTest(new TestContribCliDriver("testCliDriver_udaf_example_group_concat"));
    suite.addTest(new TestContribCliDriver("testCliDriver_udaf_example_max"));
    suite.addTest(new TestContribCliDriver("testCliDriver_udaf_example_max_n"));
    suite.addTest(new TestContribCliDriver("testCliDriver_udaf_example_min"));
    suite.addTest(new TestContribCliDriver("testCliDriver_udaf_example_min_n"));
    suite.addTest(new TestContribCliDriver("testCliDriver_udf_example_add"));
    suite.addTest(new TestContribCliDriver("testCliDriver_udf_example_arraymapstruct"));
    suite.addTest(new TestContribCliDriver("testCliDriver_udf_example_format"));
    suite.addTest(new TestContribCliDriver("testCliDriver_udf_row_sequence"));
    suite.addTest(new TestContribCliDriver("testCliDriver_udtf_explode2"));
    suite.addTest(new TestContribCliDriver("testCliDriver_udtf_output_on_close"));
    suite.addTest(new TestContribCliDriver("testCliDriver_shutdown"));
    return suite;
  }

  static String debugHint = "\nSee build/ql/tmp/hive.log, "
     + "or try \"ant test ... -Dtest.silent=false\" to get more logs.";

  public void testCliDriver_dboutput() throws Exception {
    runTest("dboutput", "dboutput.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/dboutput.q");
  }

  public void testCliDriver_fileformat_base64() throws Exception {
    runTest("fileformat_base64", "fileformat_base64.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/fileformat_base64.q");
  }

  public void testCliDriver_java_mr_example() throws Exception {
    runTest("java_mr_example", "java_mr_example.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/java_mr_example.q");
  }

  public void testCliDriver_lateral_view_explode2() throws Exception {
    runTest("lateral_view_explode2", "lateral_view_explode2.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/lateral_view_explode2.q");
  }

  public void testCliDriver_serde_regex() throws Exception {
    runTest("serde_regex", "serde_regex.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/serde_regex.q");
  }

  public void testCliDriver_serde_s3() throws Exception {
    runTest("serde_s3", "serde_s3.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/serde_s3.q");
  }

  public void testCliDriver_serde_typedbytes() throws Exception {
    runTest("serde_typedbytes", "serde_typedbytes.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/serde_typedbytes.q");
  }

  public void testCliDriver_serde_typedbytes2() throws Exception {
    runTest("serde_typedbytes2", "serde_typedbytes2.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/serde_typedbytes2.q");
  }

  public void testCliDriver_serde_typedbytes3() throws Exception {
    runTest("serde_typedbytes3", "serde_typedbytes3.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/serde_typedbytes3.q");
  }

  public void testCliDriver_serde_typedbytes4() throws Exception {
    runTest("serde_typedbytes4", "serde_typedbytes4.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/serde_typedbytes4.q");
  }

  public void testCliDriver_serde_typedbytes5() throws Exception {
    runTest("serde_typedbytes5", "serde_typedbytes5.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/serde_typedbytes5.q");
  }

  public void testCliDriver_serde_typedbytes6() throws Exception {
    runTest("serde_typedbytes6", "serde_typedbytes6.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/serde_typedbytes6.q");
  }

  public void testCliDriver_serde_typedbytes_null() throws Exception {
    runTest("serde_typedbytes_null", "serde_typedbytes_null.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/serde_typedbytes_null.q");
  }

  public void testCliDriver_udaf_example_avg() throws Exception {
    runTest("udaf_example_avg", "udaf_example_avg.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/udaf_example_avg.q");
  }

  public void testCliDriver_udaf_example_group_concat() throws Exception {
    runTest("udaf_example_group_concat", "udaf_example_group_concat.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/udaf_example_group_concat.q");
  }

  public void testCliDriver_udaf_example_max() throws Exception {
    runTest("udaf_example_max", "udaf_example_max.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/udaf_example_max.q");
  }

  public void testCliDriver_udaf_example_max_n() throws Exception {
    runTest("udaf_example_max_n", "udaf_example_max_n.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/udaf_example_max_n.q");
  }

  public void testCliDriver_udaf_example_min() throws Exception {
    runTest("udaf_example_min", "udaf_example_min.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/udaf_example_min.q");
  }

  public void testCliDriver_udaf_example_min_n() throws Exception {
    runTest("udaf_example_min_n", "udaf_example_min_n.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/udaf_example_min_n.q");
  }

  public void testCliDriver_udf_example_add() throws Exception {
    runTest("udf_example_add", "udf_example_add.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/udf_example_add.q");
  }

  public void testCliDriver_udf_example_arraymapstruct() throws Exception {
    runTest("udf_example_arraymapstruct", "udf_example_arraymapstruct.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/udf_example_arraymapstruct.q");
  }

  public void testCliDriver_udf_example_format() throws Exception {
    runTest("udf_example_format", "udf_example_format.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/udf_example_format.q");
  }

  public void testCliDriver_udf_row_sequence() throws Exception {
    runTest("udf_row_sequence", "udf_row_sequence.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/udf_row_sequence.q");
  }

  public void testCliDriver_udtf_explode2() throws Exception {
    runTest("udtf_explode2", "udtf_explode2.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/udtf_explode2.q");
  }

  public void testCliDriver_udtf_output_on_close() throws Exception {
    runTest("udtf_output_on_close", "udtf_output_on_close.q", "/home/kangyanli/hive-0.12.0/src/contrib/src/test/queries/clientpositive/udtf_output_on_close.q");
  }


  private void runTest(String tname, String fname, String fpath) throws Exception {
    long startTime = System.currentTimeMillis();
    try {
      System.out.println("Begin query: " + fname);

      qt.addFile(fpath);

      if (qt.shouldBeSkipped(fname)) {
        return;
      }

      qt.cliInit(fname, false);
      int ecode = qt.executeClient(fname);
      if (ecode != 0) {
        fail("Client Execution failed with error code = " + ecode + debugHint);
      }
      ecode = qt.checkCliDriverResults(fname);
      if (ecode != 0) {
        fail("Client execution results failed with error code = " + ecode
            + debugHint);
      }
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
