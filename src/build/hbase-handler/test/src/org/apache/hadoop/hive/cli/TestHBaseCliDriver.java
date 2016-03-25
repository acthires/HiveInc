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

import org.apache.hadoop.hive.hbase.HBaseQTestUtil;
import org.apache.hadoop.hive.hbase.HBaseTestSetup;
import org.apache.hadoop.hive.ql.session.SessionState;

public class TestHBaseCliDriver extends TestCase {

  private HBaseQTestUtil qt;
  private HBaseTestSetup setup;

  public TestHBaseCliDriver(String name, HBaseTestSetup setup) {
    super(name);
    qt = null;
    this.setup = setup;
  }

  @Override
  protected void setUp() {
    try {
      boolean miniMR = false;
      if ("".equals("miniMR")) {
        miniMR = true;
      }
      qt = new HBaseQTestUtil("/home/kangyanli/hive-0.12.0/src/hbase-handler/src/test/results/positive", "/home/kangyanli/hive-0.12.0/src/build/hbase-handler/test/logs/hbase-handler", miniMR, setup);

    } catch (Exception e) {
      System.out.println("Exception: " + e.getMessage());
      e.printStackTrace();
      System.out.flush();
      fail("Unexpected exception in setup");
    }
  }

  @Override
  protected void tearDown() {
    try {
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
    HBaseTestSetup setup = new HBaseTestSetup(suite);
    suite.addTest(new TestHBaseCliDriver("testCliDriver_external_table_ppd", setup));
    suite.addTest(new TestHBaseCliDriver("testCliDriver_hbase_binary_external_table_queries", setup));
    suite.addTest(new TestHBaseCliDriver("testCliDriver_hbase_binary_map_queries", setup));
    suite.addTest(new TestHBaseCliDriver("testCliDriver_hbase_binary_map_queries_prefix", setup));
    suite.addTest(new TestHBaseCliDriver("testCliDriver_hbase_binary_storage_queries", setup));
    suite.addTest(new TestHBaseCliDriver("testCliDriver_hbase_joins", setup));
    suite.addTest(new TestHBaseCliDriver("testCliDriver_hbase_ppd_key_range", setup));
    suite.addTest(new TestHBaseCliDriver("testCliDriver_hbase_pushdown", setup));
    suite.addTest(new TestHBaseCliDriver("testCliDriver_hbase_queries", setup));
    suite.addTest(new TestHBaseCliDriver("testCliDriver_hbase_scan_params", setup));
    suite.addTest(new TestHBaseCliDriver("testCliDriver_hbase_single_sourced_multi_insert", setup));
    suite.addTest(new TestHBaseCliDriver("testCliDriver_hbase_stats", setup));
    suite.addTest(new TestHBaseCliDriver("testCliDriver_hbase_stats2", setup));
    suite.addTest(new TestHBaseCliDriver("testCliDriver_hbase_stats3", setup));
    suite.addTest(new TestHBaseCliDriver("testCliDriver_hbase_stats_empty_partition", setup));
    suite.addTest(new TestHBaseCliDriver("testCliDriver_ppd_key_ranges", setup));
    return setup;
  }

  public void testCliDriver_external_table_ppd() throws Exception {
    runTest("external_table_ppd", "external_table_ppd.q", "/home/kangyanli/hive-0.12.0/src/hbase-handler/src/test/queries/positive/external_table_ppd.q");
  }

  public void testCliDriver_hbase_binary_external_table_queries() throws Exception {
    runTest("hbase_binary_external_table_queries", "hbase_binary_external_table_queries.q", "/home/kangyanli/hive-0.12.0/src/hbase-handler/src/test/queries/positive/hbase_binary_external_table_queries.q");
  }

  public void testCliDriver_hbase_binary_map_queries() throws Exception {
    runTest("hbase_binary_map_queries", "hbase_binary_map_queries.q", "/home/kangyanli/hive-0.12.0/src/hbase-handler/src/test/queries/positive/hbase_binary_map_queries.q");
  }

  public void testCliDriver_hbase_binary_map_queries_prefix() throws Exception {
    runTest("hbase_binary_map_queries_prefix", "hbase_binary_map_queries_prefix.q", "/home/kangyanli/hive-0.12.0/src/hbase-handler/src/test/queries/positive/hbase_binary_map_queries_prefix.q");
  }

  public void testCliDriver_hbase_binary_storage_queries() throws Exception {
    runTest("hbase_binary_storage_queries", "hbase_binary_storage_queries.q", "/home/kangyanli/hive-0.12.0/src/hbase-handler/src/test/queries/positive/hbase_binary_storage_queries.q");
  }

  public void testCliDriver_hbase_joins() throws Exception {
    runTest("hbase_joins", "hbase_joins.q", "/home/kangyanli/hive-0.12.0/src/hbase-handler/src/test/queries/positive/hbase_joins.q");
  }

  public void testCliDriver_hbase_ppd_key_range() throws Exception {
    runTest("hbase_ppd_key_range", "hbase_ppd_key_range.q", "/home/kangyanli/hive-0.12.0/src/hbase-handler/src/test/queries/positive/hbase_ppd_key_range.q");
  }

  public void testCliDriver_hbase_pushdown() throws Exception {
    runTest("hbase_pushdown", "hbase_pushdown.q", "/home/kangyanli/hive-0.12.0/src/hbase-handler/src/test/queries/positive/hbase_pushdown.q");
  }

  public void testCliDriver_hbase_queries() throws Exception {
    runTest("hbase_queries", "hbase_queries.q", "/home/kangyanli/hive-0.12.0/src/hbase-handler/src/test/queries/positive/hbase_queries.q");
  }

  public void testCliDriver_hbase_scan_params() throws Exception {
    runTest("hbase_scan_params", "hbase_scan_params.q", "/home/kangyanli/hive-0.12.0/src/hbase-handler/src/test/queries/positive/hbase_scan_params.q");
  }

  public void testCliDriver_hbase_single_sourced_multi_insert() throws Exception {
    runTest("hbase_single_sourced_multi_insert", "hbase_single_sourced_multi_insert.q", "/home/kangyanli/hive-0.12.0/src/hbase-handler/src/test/queries/positive/hbase_single_sourced_multi_insert.q");
  }

  public void testCliDriver_hbase_stats() throws Exception {
    runTest("hbase_stats", "hbase_stats.q", "/home/kangyanli/hive-0.12.0/src/hbase-handler/src/test/queries/positive/hbase_stats.q");
  }

  public void testCliDriver_hbase_stats2() throws Exception {
    runTest("hbase_stats2", "hbase_stats2.q", "/home/kangyanli/hive-0.12.0/src/hbase-handler/src/test/queries/positive/hbase_stats2.q");
  }

  public void testCliDriver_hbase_stats3() throws Exception {
    runTest("hbase_stats3", "hbase_stats3.q", "/home/kangyanli/hive-0.12.0/src/hbase-handler/src/test/queries/positive/hbase_stats3.q");
  }

  public void testCliDriver_hbase_stats_empty_partition() throws Exception {
    runTest("hbase_stats_empty_partition", "hbase_stats_empty_partition.q", "/home/kangyanli/hive-0.12.0/src/hbase-handler/src/test/queries/positive/hbase_stats_empty_partition.q");
  }

  public void testCliDriver_ppd_key_ranges() throws Exception {
    runTest("ppd_key_ranges", "ppd_key_ranges.q", "/home/kangyanli/hive-0.12.0/src/hbase-handler/src/test/queries/positive/ppd_key_ranges.q");
  }


  private void runTest(String tname, String fname, String fpath) throws Exception {
    long startTime = System.currentTimeMillis();
    try {
      System.out.println("Begin query: " + fname);

      qt.addFile(fpath);

      if (qt.shouldBeSkipped(fname)) {
        System.out.println("Test " + fname + " skipped");
        return;
      }

      qt.cliInit(fname);
      qt.clearTestSideEffects();
      int ecode = qt.executeClient(fname);
      if (ecode != 0) {
        fail("Client Execution failed with error code = " + ecode);
      }

      ecode = qt.checkCliDriverResults(fname);
      if (ecode != 0) {
        fail("Client execution results failed with error code = " + ecode);
      }
      qt.clearPostTestEffects();

    } catch (Throwable e) {
      System.out.println("Exception: " + e.getMessage());
      e.printStackTrace();
      System.out.println("Failed query: " + fname);
      System.out.flush();
      fail("Unexpected exception");
    }

    long elapsedTime = System.currentTimeMillis() - startTime;
    System.out.println("Done query: " + fname + " elapsedTime=" + elapsedTime/1000 + "s");
    assertTrue("Test passed", true);
  }
}

