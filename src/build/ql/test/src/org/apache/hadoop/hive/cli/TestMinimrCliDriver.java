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

public class TestMinimrCliDriver extends TestCase {

  private static QTestUtil qt;

  static {
    try {
      boolean miniMR = false;
      String hadoopVer;
      if ("miniMR".equals("miniMR"))
        miniMR = true;
      hadoopVer = "0.20.2";
      qt = new QTestUtil("/home/kangyanli/hive-0.12.0/src/ql/src/test/results/clientpositive", "/home/kangyanli/hive-0.12.0/src/build/ql/test/logs/clientpositive", miniMR, hadoopVer);

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

  public TestMinimrCliDriver(String name) {
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
    System.out.println ("Cleaning up " + "TestMinimrCliDriver");
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
    suite.addTest(new TestMinimrCliDriver("testCliDriver_bucket4"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_bucket5"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_bucket_num_reducers"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_bucket_num_reducers2"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_bucketizedhiveinputformat"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_bucketmapjoin6"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_bucketmapjoin7"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_disable_merge_for_bucketing"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_groupby2"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_infer_bucket_sort_bucketed_table"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_infer_bucket_sort_dyn_part"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_infer_bucket_sort_map_operators"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_infer_bucket_sort_merge"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_infer_bucket_sort_num_buckets"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_infer_bucket_sort_reducers_power_two"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_input16_cc"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_join1"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_leftsemijoin_mr"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_list_bucket_dml_10"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_load_fs2"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_load_hdfs_file_with_space_in_the_name"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_optrstat_groupby"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_parallel_orderby"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_reduce_deduplicate"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_remote_script"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_schemeAuthority"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_schemeAuthority2"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_scriptfile1"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_smb_mapjoin_8"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_truncate_column_buckets"));
    suite.addTest(new TestMinimrCliDriver("testCliDriver_shutdown"));
    return suite;
  }

  static String debugHint = "\nSee build/ql/tmp/hive.log, "
     + "or try \"ant test ... -Dtest.silent=false\" to get more logs.";

  public void testCliDriver_bucket4() throws Exception {
    runTest("bucket4", "bucket4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/bucket4.q");
  }

  public void testCliDriver_bucket5() throws Exception {
    runTest("bucket5", "bucket5.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/bucket5.q");
  }

  public void testCliDriver_bucket_num_reducers() throws Exception {
    runTest("bucket_num_reducers", "bucket_num_reducers.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/bucket_num_reducers.q");
  }

  public void testCliDriver_bucket_num_reducers2() throws Exception {
    runTest("bucket_num_reducers2", "bucket_num_reducers2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/bucket_num_reducers2.q");
  }

  public void testCliDriver_bucketizedhiveinputformat() throws Exception {
    runTest("bucketizedhiveinputformat", "bucketizedhiveinputformat.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/bucketizedhiveinputformat.q");
  }

  public void testCliDriver_bucketmapjoin6() throws Exception {
    runTest("bucketmapjoin6", "bucketmapjoin6.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/bucketmapjoin6.q");
  }

  public void testCliDriver_bucketmapjoin7() throws Exception {
    runTest("bucketmapjoin7", "bucketmapjoin7.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/bucketmapjoin7.q");
  }

  public void testCliDriver_disable_merge_for_bucketing() throws Exception {
    runTest("disable_merge_for_bucketing", "disable_merge_for_bucketing.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/disable_merge_for_bucketing.q");
  }

  public void testCliDriver_groupby2() throws Exception {
    runTest("groupby2", "groupby2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/groupby2.q");
  }

  public void testCliDriver_infer_bucket_sort_bucketed_table() throws Exception {
    runTest("infer_bucket_sort_bucketed_table", "infer_bucket_sort_bucketed_table.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/infer_bucket_sort_bucketed_table.q");
  }

  public void testCliDriver_infer_bucket_sort_dyn_part() throws Exception {
    runTest("infer_bucket_sort_dyn_part", "infer_bucket_sort_dyn_part.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/infer_bucket_sort_dyn_part.q");
  }

  public void testCliDriver_infer_bucket_sort_map_operators() throws Exception {
    runTest("infer_bucket_sort_map_operators", "infer_bucket_sort_map_operators.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/infer_bucket_sort_map_operators.q");
  }

  public void testCliDriver_infer_bucket_sort_merge() throws Exception {
    runTest("infer_bucket_sort_merge", "infer_bucket_sort_merge.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/infer_bucket_sort_merge.q");
  }

  public void testCliDriver_infer_bucket_sort_num_buckets() throws Exception {
    runTest("infer_bucket_sort_num_buckets", "infer_bucket_sort_num_buckets.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/infer_bucket_sort_num_buckets.q");
  }

  public void testCliDriver_infer_bucket_sort_reducers_power_two() throws Exception {
    runTest("infer_bucket_sort_reducers_power_two", "infer_bucket_sort_reducers_power_two.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/infer_bucket_sort_reducers_power_two.q");
  }

  public void testCliDriver_input16_cc() throws Exception {
    runTest("input16_cc", "input16_cc.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/input16_cc.q");
  }

  public void testCliDriver_join1() throws Exception {
    runTest("join1", "join1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/join1.q");
  }

  public void testCliDriver_leftsemijoin_mr() throws Exception {
    runTest("leftsemijoin_mr", "leftsemijoin_mr.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/leftsemijoin_mr.q");
  }

  public void testCliDriver_list_bucket_dml_10() throws Exception {
    runTest("list_bucket_dml_10", "list_bucket_dml_10.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/list_bucket_dml_10.q");
  }

  public void testCliDriver_load_fs2() throws Exception {
    runTest("load_fs2", "load_fs2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/load_fs2.q");
  }

  public void testCliDriver_load_hdfs_file_with_space_in_the_name() throws Exception {
    runTest("load_hdfs_file_with_space_in_the_name", "load_hdfs_file_with_space_in_the_name.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/load_hdfs_file_with_space_in_the_name.q");
  }

  public void testCliDriver_optrstat_groupby() throws Exception {
    runTest("optrstat_groupby", "optrstat_groupby.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/optrstat_groupby.q");
  }

  public void testCliDriver_parallel_orderby() throws Exception {
    runTest("parallel_orderby", "parallel_orderby.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/parallel_orderby.q");
  }

  public void testCliDriver_reduce_deduplicate() throws Exception {
    runTest("reduce_deduplicate", "reduce_deduplicate.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/reduce_deduplicate.q");
  }

  public void testCliDriver_remote_script() throws Exception {
    runTest("remote_script", "remote_script.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/remote_script.q");
  }

  public void testCliDriver_schemeAuthority() throws Exception {
    runTest("schemeAuthority", "schemeAuthority.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/schemeAuthority.q");
  }

  public void testCliDriver_schemeAuthority2() throws Exception {
    runTest("schemeAuthority2", "schemeAuthority2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/schemeAuthority2.q");
  }

  public void testCliDriver_scriptfile1() throws Exception {
    runTest("scriptfile1", "scriptfile1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/scriptfile1.q");
  }

  public void testCliDriver_smb_mapjoin_8() throws Exception {
    runTest("smb_mapjoin_8", "smb_mapjoin_8.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/smb_mapjoin_8.q");
  }

  public void testCliDriver_truncate_column_buckets() throws Exception {
    runTest("truncate_column_buckets", "truncate_column_buckets.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientpositive/truncate_column_buckets.q");
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
