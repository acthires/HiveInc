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

public class TestParseNegative extends TestCase {

  private static QTestUtil qt;
  
  static {
    try {
      boolean miniMR = false;
      if ("".equals("miniMR"))
        miniMR = true;
      String hadoopVer = "0.20.2";
      qt = new QTestUtil("/home/kangyanli/hive-0.12.0/src/ql/src/test/results/compiler/errors", "/home/kangyanli/hive-0.12.0/src/build/ql/test/logs/negative", miniMR, hadoopVer);
    } catch (Exception e) {
      System.out.println("Exception: " + e.getMessage());
      e.printStackTrace();
      System.out.flush();
      fail("Unexpected exception in static initialization");
    }
  }

  public TestParseNegative(String name) {
    super(name);
  }

  @Override
  protected void tearDown() {
    try {
      qt.clearPostTestEffects();
      if (getName().equals("testParseNegative_shutdown"))
        qt.shutdown();
    }
    catch (Exception e) {
      System.out.println("Exception: " + e.getMessage());
      e.printStackTrace();
      System.out.flush();
      fail("Unexpected exception in tearDown");
    }
  }

  /**
   * Dummy last test. This is only meant to shutdown qt
   */
  public void testParseNegative_shutdown() {
    System.out.println ("Cleaning up " + "TestParseNegative");
  }

  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTest(new TestParseNegative("testParseNegative_ambiguous_join_col"));
    suite.addTest(new TestParseNegative("testParseNegative_duplicate_alias"));
    suite.addTest(new TestParseNegative("testParseNegative_garbage"));
    suite.addTest(new TestParseNegative("testParseNegative_insert_wrong_number_columns"));
    suite.addTest(new TestParseNegative("testParseNegative_invalid_create_table"));
    suite.addTest(new TestParseNegative("testParseNegative_invalid_dot"));
    suite.addTest(new TestParseNegative("testParseNegative_invalid_function_param2"));
    suite.addTest(new TestParseNegative("testParseNegative_invalid_index"));
    suite.addTest(new TestParseNegative("testParseNegative_invalid_list_index"));
    suite.addTest(new TestParseNegative("testParseNegative_invalid_list_index2"));
    suite.addTest(new TestParseNegative("testParseNegative_invalid_map_index"));
    suite.addTest(new TestParseNegative("testParseNegative_invalid_map_index2"));
    suite.addTest(new TestParseNegative("testParseNegative_invalid_select"));
    suite.addTest(new TestParseNegative("testParseNegative_macro_reserved_word"));
    suite.addTest(new TestParseNegative("testParseNegative_missing_overwrite"));
    suite.addTest(new TestParseNegative("testParseNegative_nonkey_groupby"));
    suite.addTest(new TestParseNegative("testParseNegative_quoted_string"));
    suite.addTest(new TestParseNegative("testParseNegative_unknown_column1"));
    suite.addTest(new TestParseNegative("testParseNegative_unknown_column2"));
    suite.addTest(new TestParseNegative("testParseNegative_unknown_column3"));
    suite.addTest(new TestParseNegative("testParseNegative_unknown_column4"));
    suite.addTest(new TestParseNegative("testParseNegative_unknown_column5"));
    suite.addTest(new TestParseNegative("testParseNegative_unknown_column6"));
    suite.addTest(new TestParseNegative("testParseNegative_unknown_function1"));
    suite.addTest(new TestParseNegative("testParseNegative_unknown_function2"));
    suite.addTest(new TestParseNegative("testParseNegative_unknown_function3"));
    suite.addTest(new TestParseNegative("testParseNegative_unknown_function4"));
    suite.addTest(new TestParseNegative("testParseNegative_unknown_table1"));
    suite.addTest(new TestParseNegative("testParseNegative_unknown_table2"));
    suite.addTest(new TestParseNegative("testParseNegative_wrong_distinct1"));
    suite.addTest(new TestParseNegative("testParseNegative_wrong_distinct2"));
    suite.addTest(new TestParseNegative("testParseNegative_shutdown"));
    return suite;
  }

  static String debugHint = "\nSee build/ql/tmp/hive.log, "
     + "or try \"ant test ... -Dtest.silent=false\" to get more logs.";

  public void testParseNegative_ambiguous_join_col() throws Exception {
    runTest("ambiguous_join_col", "ambiguous_join_col.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/ambiguous_join_col.q");
  }

  public void testParseNegative_duplicate_alias() throws Exception {
    runTest("duplicate_alias", "duplicate_alias.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/duplicate_alias.q");
  }

  public void testParseNegative_garbage() throws Exception {
    runTest("garbage", "garbage.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/garbage.q");
  }

  public void testParseNegative_insert_wrong_number_columns() throws Exception {
    runTest("insert_wrong_number_columns", "insert_wrong_number_columns.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/insert_wrong_number_columns.q");
  }

  public void testParseNegative_invalid_create_table() throws Exception {
    runTest("invalid_create_table", "invalid_create_table.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/invalid_create_table.q");
  }

  public void testParseNegative_invalid_dot() throws Exception {
    runTest("invalid_dot", "invalid_dot.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/invalid_dot.q");
  }

  public void testParseNegative_invalid_function_param2() throws Exception {
    runTest("invalid_function_param2", "invalid_function_param2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/invalid_function_param2.q");
  }

  public void testParseNegative_invalid_index() throws Exception {
    runTest("invalid_index", "invalid_index.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/invalid_index.q");
  }

  public void testParseNegative_invalid_list_index() throws Exception {
    runTest("invalid_list_index", "invalid_list_index.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/invalid_list_index.q");
  }

  public void testParseNegative_invalid_list_index2() throws Exception {
    runTest("invalid_list_index2", "invalid_list_index2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/invalid_list_index2.q");
  }

  public void testParseNegative_invalid_map_index() throws Exception {
    runTest("invalid_map_index", "invalid_map_index.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/invalid_map_index.q");
  }

  public void testParseNegative_invalid_map_index2() throws Exception {
    runTest("invalid_map_index2", "invalid_map_index2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/invalid_map_index2.q");
  }

  public void testParseNegative_invalid_select() throws Exception {
    runTest("invalid_select", "invalid_select.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/invalid_select.q");
  }

  public void testParseNegative_macro_reserved_word() throws Exception {
    runTest("macro_reserved_word", "macro_reserved_word.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/macro_reserved_word.q");
  }

  public void testParseNegative_missing_overwrite() throws Exception {
    runTest("missing_overwrite", "missing_overwrite.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/missing_overwrite.q");
  }

  public void testParseNegative_nonkey_groupby() throws Exception {
    runTest("nonkey_groupby", "nonkey_groupby.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/nonkey_groupby.q");
  }

  public void testParseNegative_quoted_string() throws Exception {
    runTest("quoted_string", "quoted_string.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/quoted_string.q");
  }

  public void testParseNegative_unknown_column1() throws Exception {
    runTest("unknown_column1", "unknown_column1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/unknown_column1.q");
  }

  public void testParseNegative_unknown_column2() throws Exception {
    runTest("unknown_column2", "unknown_column2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/unknown_column2.q");
  }

  public void testParseNegative_unknown_column3() throws Exception {
    runTest("unknown_column3", "unknown_column3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/unknown_column3.q");
  }

  public void testParseNegative_unknown_column4() throws Exception {
    runTest("unknown_column4", "unknown_column4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/unknown_column4.q");
  }

  public void testParseNegative_unknown_column5() throws Exception {
    runTest("unknown_column5", "unknown_column5.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/unknown_column5.q");
  }

  public void testParseNegative_unknown_column6() throws Exception {
    runTest("unknown_column6", "unknown_column6.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/unknown_column6.q");
  }

  public void testParseNegative_unknown_function1() throws Exception {
    runTest("unknown_function1", "unknown_function1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/unknown_function1.q");
  }

  public void testParseNegative_unknown_function2() throws Exception {
    runTest("unknown_function2", "unknown_function2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/unknown_function2.q");
  }

  public void testParseNegative_unknown_function3() throws Exception {
    runTest("unknown_function3", "unknown_function3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/unknown_function3.q");
  }

  public void testParseNegative_unknown_function4() throws Exception {
    runTest("unknown_function4", "unknown_function4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/unknown_function4.q");
  }

  public void testParseNegative_unknown_table1() throws Exception {
    runTest("unknown_table1", "unknown_table1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/unknown_table1.q");
  }

  public void testParseNegative_unknown_table2() throws Exception {
    runTest("unknown_table2", "unknown_table2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/unknown_table2.q");
  }

  public void testParseNegative_wrong_distinct1() throws Exception {
    runTest("wrong_distinct1", "wrong_distinct1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/wrong_distinct1.q");
  }

  public void testParseNegative_wrong_distinct2() throws Exception {
    runTest("wrong_distinct2", "wrong_distinct2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/negative/wrong_distinct2.q");
  }


  private void runTest(String tname, String fname, String fpath) throws Exception {
    long startTime = System.currentTimeMillis();
    try {
      System.out.println("Begin query: " + fname);

      qt.addFile(fpath);

      qt.init(fname);
      ASTNode tree = qt.parseQuery(fname);
      List<Task<? extends Serializable>> tasks = qt.analyzeAST(tree);
      fail("Unexpected success for query: " + fname + debugHint);
    }
    catch (ParseException pe) {
      int ecode = qt.checkNegativeResults(fname, pe);
      if (ecode != 0) {
        fail("failed with error code = " + ecode + debugHint);
      }
    }
    catch (SemanticException se) {
      int ecode = qt.checkNegativeResults(fname, se);
      if (ecode != 0) {
        fail("failed with error code = " + ecode + debugHint);
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
