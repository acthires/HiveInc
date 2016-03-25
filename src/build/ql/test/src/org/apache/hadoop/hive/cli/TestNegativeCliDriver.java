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
import org.apache.hadoop.hive.ql.exec.Task;

public class TestNegativeCliDriver extends TestCase {

  private static QTestUtil qt;

  static {
    try {
      boolean miniMR = false;
      String hadoopVer;
      if ("".equals("miniMR"))
        miniMR = true;
      hadoopVer = "0.20.2";
      qt = new QTestUtil("/home/kangyanli/hive-0.12.0/src/ql/src/test/results/clientnegative", "/home/kangyanli/hive-0.12.0/src/build/ql/test/logs/clientnegative", miniMR, hadoopVer);
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

  public TestNegativeCliDriver(String name) {
    super(name);
  }

  @Override
  protected void setUp() {
    try {
      qt.clearTestSideEffects();
    }
    catch (Throwable e) {
      e.printStackTrace();
      System.out.flush();
      fail("Unexpected exception in setup");
    }
  }

  @Override
  protected void tearDown() {
    try {
      qt.clearPostTestEffects();
      if (getName().equals("testNegativeCliDriver_shutdown"))
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
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_add_partition_with_whitelist"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_addpart1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_concatenate_indexed_table"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_non_native"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_partition_coltype_2columns"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_partition_coltype_invalidcolname"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_partition_coltype_invalidtype"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_partition_invalidspec"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_partition_nodrop"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_partition_nodrop_table"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_partition_offline"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_partition_with_whitelist"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_rename_partition_failure"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_rename_partition_failure2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_rename_partition_failure3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_table_add_partition"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_table_wrong_regex"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_view_as_select_not_exist"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_view_as_select_with_partition"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_view_failure"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_view_failure2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_view_failure3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_view_failure4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_view_failure5"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_view_failure6"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_view_failure7"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_view_failure8"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_alter_view_failure9"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_altern1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_ambiguous_col"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_ambiguous_col0"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_ambiguous_col1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_ambiguous_col2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_analyze"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_analyze1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_analyze_view"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_archive1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_archive2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_archive3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_archive4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_archive5"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_archive_corrupt"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_archive_insert1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_archive_insert2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_archive_insert3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_archive_insert4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_archive_multi1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_archive_multi2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_archive_multi3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_archive_multi4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_archive_multi5"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_archive_multi6"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_archive_multi7"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_archive_partspec1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_archive_partspec2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_archive_partspec3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_archive_partspec4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_archive_partspec5"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_authorization_fail_1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_authorization_fail_2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_authorization_fail_3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_authorization_fail_4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_authorization_fail_5"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_authorization_fail_6"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_authorization_fail_7"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_authorization_part"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_autolocal1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_bad_exec_hooks"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_bad_indextype"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_bad_sample_clause"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_bucket_mapjoin_mismatch1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_bucket_mapjoin_wrong_table_metadata_1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_bucket_mapjoin_wrong_table_metadata_2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_cachingprintstream"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_clusterbydistributeby"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_clusterbyorderby"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_clusterbysortby"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_clustern1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_clustern2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_clustern3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_clustern4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_column_change_skewedcol_type1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_column_rename1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_column_rename2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_column_rename3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_column_rename4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_column_rename5"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_columnstats_partlvl_dp"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_columnstats_partlvl_incorrect_num_keys"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_columnstats_partlvl_invalid_values"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_columnstats_partlvl_multiple_part_clause"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_columnstats_tbllvl"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_columnstats_tbllvl_complex_type"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_columnstats_tbllvl_incorrect_column"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_compare_double_bigint"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_compare_string_bigint"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_insert_outputformat"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_or_replace_view1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_or_replace_view2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_or_replace_view3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_or_replace_view4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_or_replace_view5"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_or_replace_view6"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_or_replace_view7"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_or_replace_view8"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_skewed_table_col_name_value_no_mismatch"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_skewed_table_dup_col_name"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_skewed_table_failure_invalid_col_name"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_table_failure1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_table_failure2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_table_failure3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_table_failure4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_table_wrong_regex"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_udaf_failure"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_unknown_genericudf"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_unknown_udf_udaf"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_view_failure1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_view_failure2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_view_failure3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_view_failure4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_view_failure5"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_view_failure6"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_view_failure7"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_view_failure8"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_create_view_failure9"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_ctas"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_database_create_already_exists"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_database_create_invalid_name"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_database_drop_does_not_exist"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_database_drop_not_empty"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_database_drop_not_empty_restrict"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_database_switch_does_not_exist"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_date_literal1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_date_literal2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_date_literal3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_ddltime"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_decimal_precision"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_decimal_precision_1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_default_partition_name"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_deletejar"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_desc_failure1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_desc_failure2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_desc_failure3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_describe_xpath1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_describe_xpath2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_describe_xpath3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_describe_xpath4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_disallow_incompatible_type_change_on1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_disallow_incompatible_type_change_on2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_drop_function_failure"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_drop_index_failure"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_drop_native_udf"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_drop_partition_failure"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_drop_partition_filter_failure"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_drop_partition_filter_failure2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_drop_table_failure1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_drop_table_failure2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_drop_table_failure3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_drop_view_failure1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_drop_view_failure2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_duplicate_alias_in_transform"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_duplicate_alias_in_transform_schema"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_duplicate_insert1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_duplicate_insert2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_duplicate_insert3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_dyn_part1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_dyn_part2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_dyn_part3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_dyn_part4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_dyn_part_max"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_dyn_part_max_per_node"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_dynamic_partitions_with_whitelist"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exchange_partition_neg_incomplete_partition"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exchange_partition_neg_partition_exists"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exchange_partition_neg_partition_exists2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exchange_partition_neg_partition_exists3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exchange_partition_neg_partition_missing"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exchange_partition_neg_table_missing"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exchange_partition_neg_table_missing2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exchange_partition_neg_test"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_00_unsupported_schema"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_01_nonpart_over_loaded"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_02_all_part_over_overlap"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_03_nonpart_noncompat_colschema"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_04_nonpart_noncompat_colnumber"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_05_nonpart_noncompat_coltype"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_06_nonpart_noncompat_storage"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_07_nonpart_noncompat_ifof"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_08_nonpart_noncompat_serde"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_09_nonpart_noncompat_serdeparam"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_10_nonpart_noncompat_bucketing"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_11_nonpart_noncompat_sorting"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_12_nonnative_export"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_13_nonnative_import"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_14_nonpart_part"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_15_part_nonpart"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_16_part_noncompat_schema"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_17_part_spec_underspec"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_18_part_spec_missing"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_19_external_over_existing"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_20_managed_location_over_existing"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_21_part_managed_external"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_22_export_authfail"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_23_import_exist_authfail"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_24_import_part_authfail"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_exim_25_import_nonexist_authfail"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_external1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_external2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_fetchtask_ioexception"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_fileformat_bad_class"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_fileformat_void_input"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_fileformat_void_output"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_fs_default_name1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_fs_default_name2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_genericFileFormat"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_groupby2_map_skew_multi_distinct"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_groupby2_multi_distinct"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_groupby3_map_skew_multi_distinct"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_groupby3_multi_distinct"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_groupby_cube1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_groupby_cube2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_groupby_grouping_id1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_groupby_grouping_sets1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_groupby_grouping_sets2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_groupby_grouping_sets3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_groupby_grouping_sets4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_groupby_grouping_sets5"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_groupby_grouping_sets6"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_groupby_grouping_sets7"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_groupby_invalid_position"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_groupby_key"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_groupby_rollup1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_groupby_rollup2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_having1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_illegal_partition_type"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_illegal_partition_type2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_index_bitmap_no_map_aggr"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_index_compact_entry_limit"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_index_compact_size_limit"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_input1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_input2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_input4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_input41"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_input_part0_neg"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_insert_into1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_insert_into2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_insert_into3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_insert_into4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_insert_view_failure"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_insertexternal1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_insertover_dynapart_ifnotexists"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_arithmetic_type"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_avg_syntax"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_cast_from_binary_1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_cast_from_binary_2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_cast_from_binary_3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_cast_from_binary_4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_cast_from_binary_5"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_cast_from_binary_6"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_cast_to_binary_1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_cast_to_binary_2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_cast_to_binary_3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_cast_to_binary_4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_cast_to_binary_5"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_cast_to_binary_6"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_columns"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_config1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_config2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_create_tbl1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_create_tbl2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_mapjoin1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_max_syntax"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_min_syntax"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_select_column"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_select_column_with_subquery"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_select_column_with_tablename"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_select_expression"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_std_syntax"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_stddev_samp_syntax"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_sum_syntax"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_t_alter1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_t_alter2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_t_create2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_t_transform"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_tbl_name"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_var_samp_syntax"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_varchar_length_1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_varchar_length_2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_varchar_length_3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalid_variance_syntax"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_invalidate_view1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_join2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_join28"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_join29"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_join32"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_join35"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_join_nonexistent_part"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_joinneg"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_lateral_view_alias"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_lateral_view_join"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_line_terminator"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_load_exist_part_authfail"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_load_non_native"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_load_nonpart_authfail"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_load_part_authfail"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_load_part_nospec"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_load_stored_as_dirs"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_load_view_failure"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_load_wrong_fileformat"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_load_wrong_fileformat_rc_seq"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_load_wrong_fileformat_txt_seq"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_load_wrong_noof_part"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_local_mapred_error_cache"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_lockneg1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_lockneg2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_lockneg3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_lockneg4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_lockneg5"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_macro_unused_parameter"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_merge_negative_1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_merge_negative_2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_merge_negative_3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_nested_complex_neg"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_no_matching_udf"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_nonkey_groupby"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_nopart_insert"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_nopart_load"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_notable_alias3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_notable_alias4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_orderby_invalid_position"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_orderby_position_unsupported"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_orderbysortby"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_part_col_complex_type"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_protectmode_part"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_protectmode_part1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_protectmode_part2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_protectmode_part_no_drop"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_protectmode_tbl1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_protectmode_tbl2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_protectmode_tbl3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_protectmode_tbl4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_protectmode_tbl5"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_protectmode_tbl6"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_protectmode_tbl7"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_protectmode_tbl8"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_protectmode_tbl_no_drop"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_ptf_negative_AggrFuncsWithNoGBYNoPartDef"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_ptf_negative_AmbiguousWindowDefn"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_ptf_negative_DistributeByOrderBy"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_ptf_negative_DuplicateWindowAlias"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_ptf_negative_HavingLeadWithNoGBYNoWindowing"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_ptf_negative_HavingLeadWithPTF"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_ptf_negative_InvalidValueBoundary"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_ptf_negative_JoinWithAmbigousAlias"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_ptf_negative_PartitionBySortBy"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_ptf_negative_WhereWithRankCond"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_ptf_window_boundaries"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_ptf_window_boundaries2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_recursive_view"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_regex_col_1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_regex_col_2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_regex_col_groupby"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_sa_fail_hook3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_sample"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_script_broken_pipe1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_script_broken_pipe2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_script_broken_pipe3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_script_error"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_select_charliteral"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_select_udtf_alias"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_semijoin1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_semijoin2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_semijoin3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_semijoin4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_serde_regex"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_serde_regex2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_serde_regex3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_set_hiveconf_validation0"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_set_hiveconf_validation1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_set_table_property"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_show_columns1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_show_columns2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_show_columns3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_show_create_table_does_not_exist"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_show_create_table_index"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_show_partitions1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_show_tableproperties1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_show_tables_bad1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_show_tables_bad2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_show_tables_bad_db1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_show_tables_bad_db2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_show_tablestatus"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_show_tablestatus_not_existing_part"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_smb_bucketmapjoin"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_smb_mapjoin_14"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_sortmerge_mapjoin_mismatch_1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_split_sample_out_of_range"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_split_sample_wrong_format"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_split_sample_wrong_format2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_stats_aggregator_error_1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_stats_aggregator_error_2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_stats_noscan_non_native"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_stats_partialscan_autogether"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_stats_partialscan_non_external"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_stats_partialscan_non_native"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_stats_partscan_norcfile"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_stats_publisher_error_1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_stats_publisher_error_2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_strict_join"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_strict_orderby"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_strict_pruning"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_subq_insert"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_touch1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_touch2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_truncate_bucketed_column"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_truncate_column_indexed_table"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_truncate_column_list_bucketing"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_truncate_column_seqfile"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_truncate_nonexistant_column"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_truncate_partition_column"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_truncate_partition_column2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_truncate_table_failure1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_truncate_table_failure2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_truncate_table_failure3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_truncate_table_failure4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udaf_invalid_place"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_array_contains_wrong1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_array_contains_wrong2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_assert_true"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_assert_true2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_case_type_wrong"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_case_type_wrong2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_case_type_wrong3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_coalesce"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_concat_ws_wrong1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_concat_ws_wrong2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_concat_ws_wrong3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_elt_wrong_args_len"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_elt_wrong_type"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_field_wrong_args_len"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_field_wrong_type"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_format_number_wrong1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_format_number_wrong2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_format_number_wrong3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_format_number_wrong4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_format_number_wrong5"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_format_number_wrong6"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_format_number_wrong7"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_function_does_not_implement_udf"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_if_not_bool"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_if_wrong_args_len"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_in"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_instr_wrong_args_len"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_instr_wrong_type"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_locate_wrong_args_len"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_locate_wrong_type"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_map_keys_arg_num"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_map_keys_arg_type"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_map_values_arg_num"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_map_values_arg_type"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_max"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_min"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_printf_wrong1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_printf_wrong2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_printf_wrong3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_printf_wrong4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_reflect_neg"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_size_wrong_args_len"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_size_wrong_type"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_sort_array_wrong1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_sort_array_wrong2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_sort_array_wrong3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_test_error"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_test_error_reduce"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_when_type_wrong"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_when_type_wrong2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udf_when_type_wrong3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udfnull"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udtf_explode_not_supported1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udtf_explode_not_supported2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udtf_explode_not_supported3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udtf_explode_not_supported4"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udtf_invalid_place"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udtf_not_supported1"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_udtf_not_supported3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_union"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_union2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_union22"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_union3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_uniquejoin"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_uniquejoin2"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_uniquejoin3"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_unset_table_property"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_unset_view_property"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_windowing_leadlag_in_udaf"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_windowing_ll_no_over"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_wrong_column_type"));
    suite.addTest(new TestNegativeCliDriver("testNegativeCliDriver_shutdown"));
    return suite;
  }

  /**
   * Dummy last test. This is only meant to shutdown qt
   */
  public void testNegativeCliDriver_shutdown() {
    System.out.println ("Cleaning up " + "TestNegativeCliDriver");
  }

  static String debugHint = "\nSee build/ql/tmp/hive.log, "
     + "or try \"ant test ... -Dtest.silent=false\" to get more logs.";

  public void testNegativeCliDriver_add_partition_with_whitelist() throws Exception {
    runTest("add_partition_with_whitelist", "add_partition_with_whitelist.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/add_partition_with_whitelist.q");
  }

  public void testNegativeCliDriver_addpart1() throws Exception {
    runTest("addpart1", "addpart1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/addpart1.q");
  }

  public void testNegativeCliDriver_alter_concatenate_indexed_table() throws Exception {
    runTest("alter_concatenate_indexed_table", "alter_concatenate_indexed_table.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_concatenate_indexed_table.q");
  }

  public void testNegativeCliDriver_alter_non_native() throws Exception {
    runTest("alter_non_native", "alter_non_native.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_non_native.q");
  }

  public void testNegativeCliDriver_alter_partition_coltype_2columns() throws Exception {
    runTest("alter_partition_coltype_2columns", "alter_partition_coltype_2columns.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_partition_coltype_2columns.q");
  }

  public void testNegativeCliDriver_alter_partition_coltype_invalidcolname() throws Exception {
    runTest("alter_partition_coltype_invalidcolname", "alter_partition_coltype_invalidcolname.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_partition_coltype_invalidcolname.q");
  }

  public void testNegativeCliDriver_alter_partition_coltype_invalidtype() throws Exception {
    runTest("alter_partition_coltype_invalidtype", "alter_partition_coltype_invalidtype.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_partition_coltype_invalidtype.q");
  }

  public void testNegativeCliDriver_alter_partition_invalidspec() throws Exception {
    runTest("alter_partition_invalidspec", "alter_partition_invalidspec.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_partition_invalidspec.q");
  }

  public void testNegativeCliDriver_alter_partition_nodrop() throws Exception {
    runTest("alter_partition_nodrop", "alter_partition_nodrop.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_partition_nodrop.q");
  }

  public void testNegativeCliDriver_alter_partition_nodrop_table() throws Exception {
    runTest("alter_partition_nodrop_table", "alter_partition_nodrop_table.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_partition_nodrop_table.q");
  }

  public void testNegativeCliDriver_alter_partition_offline() throws Exception {
    runTest("alter_partition_offline", "alter_partition_offline.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_partition_offline.q");
  }

  public void testNegativeCliDriver_alter_partition_with_whitelist() throws Exception {
    runTest("alter_partition_with_whitelist", "alter_partition_with_whitelist.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_partition_with_whitelist.q");
  }

  public void testNegativeCliDriver_alter_rename_partition_failure() throws Exception {
    runTest("alter_rename_partition_failure", "alter_rename_partition_failure.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_rename_partition_failure.q");
  }

  public void testNegativeCliDriver_alter_rename_partition_failure2() throws Exception {
    runTest("alter_rename_partition_failure2", "alter_rename_partition_failure2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_rename_partition_failure2.q");
  }

  public void testNegativeCliDriver_alter_rename_partition_failure3() throws Exception {
    runTest("alter_rename_partition_failure3", "alter_rename_partition_failure3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_rename_partition_failure3.q");
  }

  public void testNegativeCliDriver_alter_table_add_partition() throws Exception {
    runTest("alter_table_add_partition", "alter_table_add_partition.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_table_add_partition.q");
  }

  public void testNegativeCliDriver_alter_table_wrong_regex() throws Exception {
    runTest("alter_table_wrong_regex", "alter_table_wrong_regex.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_table_wrong_regex.q");
  }

  public void testNegativeCliDriver_alter_view_as_select_not_exist() throws Exception {
    runTest("alter_view_as_select_not_exist", "alter_view_as_select_not_exist.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_view_as_select_not_exist.q");
  }

  public void testNegativeCliDriver_alter_view_as_select_with_partition() throws Exception {
    runTest("alter_view_as_select_with_partition", "alter_view_as_select_with_partition.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_view_as_select_with_partition.q");
  }

  public void testNegativeCliDriver_alter_view_failure() throws Exception {
    runTest("alter_view_failure", "alter_view_failure.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_view_failure.q");
  }

  public void testNegativeCliDriver_alter_view_failure2() throws Exception {
    runTest("alter_view_failure2", "alter_view_failure2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_view_failure2.q");
  }

  public void testNegativeCliDriver_alter_view_failure3() throws Exception {
    runTest("alter_view_failure3", "alter_view_failure3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_view_failure3.q");
  }

  public void testNegativeCliDriver_alter_view_failure4() throws Exception {
    runTest("alter_view_failure4", "alter_view_failure4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_view_failure4.q");
  }

  public void testNegativeCliDriver_alter_view_failure5() throws Exception {
    runTest("alter_view_failure5", "alter_view_failure5.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_view_failure5.q");
  }

  public void testNegativeCliDriver_alter_view_failure6() throws Exception {
    runTest("alter_view_failure6", "alter_view_failure6.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_view_failure6.q");
  }

  public void testNegativeCliDriver_alter_view_failure7() throws Exception {
    runTest("alter_view_failure7", "alter_view_failure7.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_view_failure7.q");
  }

  public void testNegativeCliDriver_alter_view_failure8() throws Exception {
    runTest("alter_view_failure8", "alter_view_failure8.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_view_failure8.q");
  }

  public void testNegativeCliDriver_alter_view_failure9() throws Exception {
    runTest("alter_view_failure9", "alter_view_failure9.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/alter_view_failure9.q");
  }

  public void testNegativeCliDriver_altern1() throws Exception {
    runTest("altern1", "altern1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/altern1.q");
  }

  public void testNegativeCliDriver_ambiguous_col() throws Exception {
    runTest("ambiguous_col", "ambiguous_col.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/ambiguous_col.q");
  }

  public void testNegativeCliDriver_ambiguous_col0() throws Exception {
    runTest("ambiguous_col0", "ambiguous_col0.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/ambiguous_col0.q");
  }

  public void testNegativeCliDriver_ambiguous_col1() throws Exception {
    runTest("ambiguous_col1", "ambiguous_col1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/ambiguous_col1.q");
  }

  public void testNegativeCliDriver_ambiguous_col2() throws Exception {
    runTest("ambiguous_col2", "ambiguous_col2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/ambiguous_col2.q");
  }

  public void testNegativeCliDriver_analyze() throws Exception {
    runTest("analyze", "analyze.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/analyze.q");
  }

  public void testNegativeCliDriver_analyze1() throws Exception {
    runTest("analyze1", "analyze1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/analyze1.q");
  }

  public void testNegativeCliDriver_analyze_view() throws Exception {
    runTest("analyze_view", "analyze_view.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/analyze_view.q");
  }

  public void testNegativeCliDriver_archive1() throws Exception {
    runTest("archive1", "archive1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/archive1.q");
  }

  public void testNegativeCliDriver_archive2() throws Exception {
    runTest("archive2", "archive2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/archive2.q");
  }

  public void testNegativeCliDriver_archive3() throws Exception {
    runTest("archive3", "archive3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/archive3.q");
  }

  public void testNegativeCliDriver_archive4() throws Exception {
    runTest("archive4", "archive4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/archive4.q");
  }

  public void testNegativeCliDriver_archive5() throws Exception {
    runTest("archive5", "archive5.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/archive5.q");
  }

  public void testNegativeCliDriver_archive_corrupt() throws Exception {
    runTest("archive_corrupt", "archive_corrupt.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/archive_corrupt.q");
  }

  public void testNegativeCliDriver_archive_insert1() throws Exception {
    runTest("archive_insert1", "archive_insert1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/archive_insert1.q");
  }

  public void testNegativeCliDriver_archive_insert2() throws Exception {
    runTest("archive_insert2", "archive_insert2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/archive_insert2.q");
  }

  public void testNegativeCliDriver_archive_insert3() throws Exception {
    runTest("archive_insert3", "archive_insert3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/archive_insert3.q");
  }

  public void testNegativeCliDriver_archive_insert4() throws Exception {
    runTest("archive_insert4", "archive_insert4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/archive_insert4.q");
  }

  public void testNegativeCliDriver_archive_multi1() throws Exception {
    runTest("archive_multi1", "archive_multi1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/archive_multi1.q");
  }

  public void testNegativeCliDriver_archive_multi2() throws Exception {
    runTest("archive_multi2", "archive_multi2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/archive_multi2.q");
  }

  public void testNegativeCliDriver_archive_multi3() throws Exception {
    runTest("archive_multi3", "archive_multi3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/archive_multi3.q");
  }

  public void testNegativeCliDriver_archive_multi4() throws Exception {
    runTest("archive_multi4", "archive_multi4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/archive_multi4.q");
  }

  public void testNegativeCliDriver_archive_multi5() throws Exception {
    runTest("archive_multi5", "archive_multi5.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/archive_multi5.q");
  }

  public void testNegativeCliDriver_archive_multi6() throws Exception {
    runTest("archive_multi6", "archive_multi6.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/archive_multi6.q");
  }

  public void testNegativeCliDriver_archive_multi7() throws Exception {
    runTest("archive_multi7", "archive_multi7.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/archive_multi7.q");
  }

  public void testNegativeCliDriver_archive_partspec1() throws Exception {
    runTest("archive_partspec1", "archive_partspec1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/archive_partspec1.q");
  }

  public void testNegativeCliDriver_archive_partspec2() throws Exception {
    runTest("archive_partspec2", "archive_partspec2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/archive_partspec2.q");
  }

  public void testNegativeCliDriver_archive_partspec3() throws Exception {
    runTest("archive_partspec3", "archive_partspec3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/archive_partspec3.q");
  }

  public void testNegativeCliDriver_archive_partspec4() throws Exception {
    runTest("archive_partspec4", "archive_partspec4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/archive_partspec4.q");
  }

  public void testNegativeCliDriver_archive_partspec5() throws Exception {
    runTest("archive_partspec5", "archive_partspec5.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/archive_partspec5.q");
  }

  public void testNegativeCliDriver_authorization_fail_1() throws Exception {
    runTest("authorization_fail_1", "authorization_fail_1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/authorization_fail_1.q");
  }

  public void testNegativeCliDriver_authorization_fail_2() throws Exception {
    runTest("authorization_fail_2", "authorization_fail_2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/authorization_fail_2.q");
  }

  public void testNegativeCliDriver_authorization_fail_3() throws Exception {
    runTest("authorization_fail_3", "authorization_fail_3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/authorization_fail_3.q");
  }

  public void testNegativeCliDriver_authorization_fail_4() throws Exception {
    runTest("authorization_fail_4", "authorization_fail_4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/authorization_fail_4.q");
  }

  public void testNegativeCliDriver_authorization_fail_5() throws Exception {
    runTest("authorization_fail_5", "authorization_fail_5.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/authorization_fail_5.q");
  }

  public void testNegativeCliDriver_authorization_fail_6() throws Exception {
    runTest("authorization_fail_6", "authorization_fail_6.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/authorization_fail_6.q");
  }

  public void testNegativeCliDriver_authorization_fail_7() throws Exception {
    runTest("authorization_fail_7", "authorization_fail_7.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/authorization_fail_7.q");
  }

  public void testNegativeCliDriver_authorization_part() throws Exception {
    runTest("authorization_part", "authorization_part.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/authorization_part.q");
  }

  public void testNegativeCliDriver_autolocal1() throws Exception {
    runTest("autolocal1", "autolocal1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/autolocal1.q");
  }

  public void testNegativeCliDriver_bad_exec_hooks() throws Exception {
    runTest("bad_exec_hooks", "bad_exec_hooks.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/bad_exec_hooks.q");
  }

  public void testNegativeCliDriver_bad_indextype() throws Exception {
    runTest("bad_indextype", "bad_indextype.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/bad_indextype.q");
  }

  public void testNegativeCliDriver_bad_sample_clause() throws Exception {
    runTest("bad_sample_clause", "bad_sample_clause.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/bad_sample_clause.q");
  }

  public void testNegativeCliDriver_bucket_mapjoin_mismatch1() throws Exception {
    runTest("bucket_mapjoin_mismatch1", "bucket_mapjoin_mismatch1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/bucket_mapjoin_mismatch1.q");
  }

  public void testNegativeCliDriver_bucket_mapjoin_wrong_table_metadata_1() throws Exception {
    runTest("bucket_mapjoin_wrong_table_metadata_1", "bucket_mapjoin_wrong_table_metadata_1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/bucket_mapjoin_wrong_table_metadata_1.q");
  }

  public void testNegativeCliDriver_bucket_mapjoin_wrong_table_metadata_2() throws Exception {
    runTest("bucket_mapjoin_wrong_table_metadata_2", "bucket_mapjoin_wrong_table_metadata_2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/bucket_mapjoin_wrong_table_metadata_2.q");
  }

  public void testNegativeCliDriver_cachingprintstream() throws Exception {
    runTest("cachingprintstream", "cachingprintstream.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/cachingprintstream.q");
  }

  public void testNegativeCliDriver_clusterbydistributeby() throws Exception {
    runTest("clusterbydistributeby", "clusterbydistributeby.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/clusterbydistributeby.q");
  }

  public void testNegativeCliDriver_clusterbyorderby() throws Exception {
    runTest("clusterbyorderby", "clusterbyorderby.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/clusterbyorderby.q");
  }

  public void testNegativeCliDriver_clusterbysortby() throws Exception {
    runTest("clusterbysortby", "clusterbysortby.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/clusterbysortby.q");
  }

  public void testNegativeCliDriver_clustern1() throws Exception {
    runTest("clustern1", "clustern1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/clustern1.q");
  }

  public void testNegativeCliDriver_clustern2() throws Exception {
    runTest("clustern2", "clustern2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/clustern2.q");
  }

  public void testNegativeCliDriver_clustern3() throws Exception {
    runTest("clustern3", "clustern3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/clustern3.q");
  }

  public void testNegativeCliDriver_clustern4() throws Exception {
    runTest("clustern4", "clustern4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/clustern4.q");
  }

  public void testNegativeCliDriver_column_change_skewedcol_type1() throws Exception {
    runTest("column_change_skewedcol_type1", "column_change_skewedcol_type1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/column_change_skewedcol_type1.q");
  }

  public void testNegativeCliDriver_column_rename1() throws Exception {
    runTest("column_rename1", "column_rename1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/column_rename1.q");
  }

  public void testNegativeCliDriver_column_rename2() throws Exception {
    runTest("column_rename2", "column_rename2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/column_rename2.q");
  }

  public void testNegativeCliDriver_column_rename3() throws Exception {
    runTest("column_rename3", "column_rename3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/column_rename3.q");
  }

  public void testNegativeCliDriver_column_rename4() throws Exception {
    runTest("column_rename4", "column_rename4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/column_rename4.q");
  }

  public void testNegativeCliDriver_column_rename5() throws Exception {
    runTest("column_rename5", "column_rename5.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/column_rename5.q");
  }

  public void testNegativeCliDriver_columnstats_partlvl_dp() throws Exception {
    runTest("columnstats_partlvl_dp", "columnstats_partlvl_dp.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/columnstats_partlvl_dp.q");
  }

  public void testNegativeCliDriver_columnstats_partlvl_incorrect_num_keys() throws Exception {
    runTest("columnstats_partlvl_incorrect_num_keys", "columnstats_partlvl_incorrect_num_keys.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/columnstats_partlvl_incorrect_num_keys.q");
  }

  public void testNegativeCliDriver_columnstats_partlvl_invalid_values() throws Exception {
    runTest("columnstats_partlvl_invalid_values", "columnstats_partlvl_invalid_values.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/columnstats_partlvl_invalid_values.q");
  }

  public void testNegativeCliDriver_columnstats_partlvl_multiple_part_clause() throws Exception {
    runTest("columnstats_partlvl_multiple_part_clause", "columnstats_partlvl_multiple_part_clause.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/columnstats_partlvl_multiple_part_clause.q");
  }

  public void testNegativeCliDriver_columnstats_tbllvl() throws Exception {
    runTest("columnstats_tbllvl", "columnstats_tbllvl.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/columnstats_tbllvl.q");
  }

  public void testNegativeCliDriver_columnstats_tbllvl_complex_type() throws Exception {
    runTest("columnstats_tbllvl_complex_type", "columnstats_tbllvl_complex_type.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/columnstats_tbllvl_complex_type.q");
  }

  public void testNegativeCliDriver_columnstats_tbllvl_incorrect_column() throws Exception {
    runTest("columnstats_tbllvl_incorrect_column", "columnstats_tbllvl_incorrect_column.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/columnstats_tbllvl_incorrect_column.q");
  }

  public void testNegativeCliDriver_compare_double_bigint() throws Exception {
    runTest("compare_double_bigint", "compare_double_bigint.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/compare_double_bigint.q");
  }

  public void testNegativeCliDriver_compare_string_bigint() throws Exception {
    runTest("compare_string_bigint", "compare_string_bigint.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/compare_string_bigint.q");
  }

  public void testNegativeCliDriver_create_insert_outputformat() throws Exception {
    runTest("create_insert_outputformat", "create_insert_outputformat.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_insert_outputformat.q");
  }

  public void testNegativeCliDriver_create_or_replace_view1() throws Exception {
    runTest("create_or_replace_view1", "create_or_replace_view1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_or_replace_view1.q");
  }

  public void testNegativeCliDriver_create_or_replace_view2() throws Exception {
    runTest("create_or_replace_view2", "create_or_replace_view2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_or_replace_view2.q");
  }

  public void testNegativeCliDriver_create_or_replace_view3() throws Exception {
    runTest("create_or_replace_view3", "create_or_replace_view3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_or_replace_view3.q");
  }

  public void testNegativeCliDriver_create_or_replace_view4() throws Exception {
    runTest("create_or_replace_view4", "create_or_replace_view4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_or_replace_view4.q");
  }

  public void testNegativeCliDriver_create_or_replace_view5() throws Exception {
    runTest("create_or_replace_view5", "create_or_replace_view5.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_or_replace_view5.q");
  }

  public void testNegativeCliDriver_create_or_replace_view6() throws Exception {
    runTest("create_or_replace_view6", "create_or_replace_view6.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_or_replace_view6.q");
  }

  public void testNegativeCliDriver_create_or_replace_view7() throws Exception {
    runTest("create_or_replace_view7", "create_or_replace_view7.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_or_replace_view7.q");
  }

  public void testNegativeCliDriver_create_or_replace_view8() throws Exception {
    runTest("create_or_replace_view8", "create_or_replace_view8.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_or_replace_view8.q");
  }

  public void testNegativeCliDriver_create_skewed_table_col_name_value_no_mismatch() throws Exception {
    runTest("create_skewed_table_col_name_value_no_mismatch", "create_skewed_table_col_name_value_no_mismatch.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_skewed_table_col_name_value_no_mismatch.q");
  }

  public void testNegativeCliDriver_create_skewed_table_dup_col_name() throws Exception {
    runTest("create_skewed_table_dup_col_name", "create_skewed_table_dup_col_name.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_skewed_table_dup_col_name.q");
  }

  public void testNegativeCliDriver_create_skewed_table_failure_invalid_col_name() throws Exception {
    runTest("create_skewed_table_failure_invalid_col_name", "create_skewed_table_failure_invalid_col_name.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_skewed_table_failure_invalid_col_name.q");
  }

  public void testNegativeCliDriver_create_table_failure1() throws Exception {
    runTest("create_table_failure1", "create_table_failure1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_table_failure1.q");
  }

  public void testNegativeCliDriver_create_table_failure2() throws Exception {
    runTest("create_table_failure2", "create_table_failure2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_table_failure2.q");
  }

  public void testNegativeCliDriver_create_table_failure3() throws Exception {
    runTest("create_table_failure3", "create_table_failure3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_table_failure3.q");
  }

  public void testNegativeCliDriver_create_table_failure4() throws Exception {
    runTest("create_table_failure4", "create_table_failure4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_table_failure4.q");
  }

  public void testNegativeCliDriver_create_table_wrong_regex() throws Exception {
    runTest("create_table_wrong_regex", "create_table_wrong_regex.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_table_wrong_regex.q");
  }

  public void testNegativeCliDriver_create_udaf_failure() throws Exception {
    runTest("create_udaf_failure", "create_udaf_failure.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_udaf_failure.q");
  }

  public void testNegativeCliDriver_create_unknown_genericudf() throws Exception {
    runTest("create_unknown_genericudf", "create_unknown_genericudf.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_unknown_genericudf.q");
  }

  public void testNegativeCliDriver_create_unknown_udf_udaf() throws Exception {
    runTest("create_unknown_udf_udaf", "create_unknown_udf_udaf.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_unknown_udf_udaf.q");
  }

  public void testNegativeCliDriver_create_view_failure1() throws Exception {
    runTest("create_view_failure1", "create_view_failure1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_view_failure1.q");
  }

  public void testNegativeCliDriver_create_view_failure2() throws Exception {
    runTest("create_view_failure2", "create_view_failure2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_view_failure2.q");
  }

  public void testNegativeCliDriver_create_view_failure3() throws Exception {
    runTest("create_view_failure3", "create_view_failure3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_view_failure3.q");
  }

  public void testNegativeCliDriver_create_view_failure4() throws Exception {
    runTest("create_view_failure4", "create_view_failure4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_view_failure4.q");
  }

  public void testNegativeCliDriver_create_view_failure5() throws Exception {
    runTest("create_view_failure5", "create_view_failure5.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_view_failure5.q");
  }

  public void testNegativeCliDriver_create_view_failure6() throws Exception {
    runTest("create_view_failure6", "create_view_failure6.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_view_failure6.q");
  }

  public void testNegativeCliDriver_create_view_failure7() throws Exception {
    runTest("create_view_failure7", "create_view_failure7.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_view_failure7.q");
  }

  public void testNegativeCliDriver_create_view_failure8() throws Exception {
    runTest("create_view_failure8", "create_view_failure8.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_view_failure8.q");
  }

  public void testNegativeCliDriver_create_view_failure9() throws Exception {
    runTest("create_view_failure9", "create_view_failure9.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/create_view_failure9.q");
  }

  public void testNegativeCliDriver_ctas() throws Exception {
    runTest("ctas", "ctas.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/ctas.q");
  }

  public void testNegativeCliDriver_database_create_already_exists() throws Exception {
    runTest("database_create_already_exists", "database_create_already_exists.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/database_create_already_exists.q");
  }

  public void testNegativeCliDriver_database_create_invalid_name() throws Exception {
    runTest("database_create_invalid_name", "database_create_invalid_name.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/database_create_invalid_name.q");
  }

  public void testNegativeCliDriver_database_drop_does_not_exist() throws Exception {
    runTest("database_drop_does_not_exist", "database_drop_does_not_exist.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/database_drop_does_not_exist.q");
  }

  public void testNegativeCliDriver_database_drop_not_empty() throws Exception {
    runTest("database_drop_not_empty", "database_drop_not_empty.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/database_drop_not_empty.q");
  }

  public void testNegativeCliDriver_database_drop_not_empty_restrict() throws Exception {
    runTest("database_drop_not_empty_restrict", "database_drop_not_empty_restrict.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/database_drop_not_empty_restrict.q");
  }

  public void testNegativeCliDriver_database_switch_does_not_exist() throws Exception {
    runTest("database_switch_does_not_exist", "database_switch_does_not_exist.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/database_switch_does_not_exist.q");
  }

  public void testNegativeCliDriver_date_literal1() throws Exception {
    runTest("date_literal1", "date_literal1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/date_literal1.q");
  }

  public void testNegativeCliDriver_date_literal2() throws Exception {
    runTest("date_literal2", "date_literal2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/date_literal2.q");
  }

  public void testNegativeCliDriver_date_literal3() throws Exception {
    runTest("date_literal3", "date_literal3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/date_literal3.q");
  }

  public void testNegativeCliDriver_ddltime() throws Exception {
    runTest("ddltime", "ddltime.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/ddltime.q");
  }

  public void testNegativeCliDriver_decimal_precision() throws Exception {
    runTest("decimal_precision", "decimal_precision.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/decimal_precision.q");
  }

  public void testNegativeCliDriver_decimal_precision_1() throws Exception {
    runTest("decimal_precision_1", "decimal_precision_1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/decimal_precision_1.q");
  }

  public void testNegativeCliDriver_default_partition_name() throws Exception {
    runTest("default_partition_name", "default_partition_name.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/default_partition_name.q");
  }

  public void testNegativeCliDriver_deletejar() throws Exception {
    runTest("deletejar", "deletejar.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/deletejar.q");
  }

  public void testNegativeCliDriver_desc_failure1() throws Exception {
    runTest("desc_failure1", "desc_failure1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/desc_failure1.q");
  }

  public void testNegativeCliDriver_desc_failure2() throws Exception {
    runTest("desc_failure2", "desc_failure2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/desc_failure2.q");
  }

  public void testNegativeCliDriver_desc_failure3() throws Exception {
    runTest("desc_failure3", "desc_failure3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/desc_failure3.q");
  }

  public void testNegativeCliDriver_describe_xpath1() throws Exception {
    runTest("describe_xpath1", "describe_xpath1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/describe_xpath1.q");
  }

  public void testNegativeCliDriver_describe_xpath2() throws Exception {
    runTest("describe_xpath2", "describe_xpath2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/describe_xpath2.q");
  }

  public void testNegativeCliDriver_describe_xpath3() throws Exception {
    runTest("describe_xpath3", "describe_xpath3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/describe_xpath3.q");
  }

  public void testNegativeCliDriver_describe_xpath4() throws Exception {
    runTest("describe_xpath4", "describe_xpath4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/describe_xpath4.q");
  }

  public void testNegativeCliDriver_disallow_incompatible_type_change_on1() throws Exception {
    runTest("disallow_incompatible_type_change_on1", "disallow_incompatible_type_change_on1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/disallow_incompatible_type_change_on1.q");
  }

  public void testNegativeCliDriver_disallow_incompatible_type_change_on2() throws Exception {
    runTest("disallow_incompatible_type_change_on2", "disallow_incompatible_type_change_on2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/disallow_incompatible_type_change_on2.q");
  }

  public void testNegativeCliDriver_drop_function_failure() throws Exception {
    runTest("drop_function_failure", "drop_function_failure.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/drop_function_failure.q");
  }

  public void testNegativeCliDriver_drop_index_failure() throws Exception {
    runTest("drop_index_failure", "drop_index_failure.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/drop_index_failure.q");
  }

  public void testNegativeCliDriver_drop_native_udf() throws Exception {
    runTest("drop_native_udf", "drop_native_udf.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/drop_native_udf.q");
  }

  public void testNegativeCliDriver_drop_partition_failure() throws Exception {
    runTest("drop_partition_failure", "drop_partition_failure.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/drop_partition_failure.q");
  }

  public void testNegativeCliDriver_drop_partition_filter_failure() throws Exception {
    runTest("drop_partition_filter_failure", "drop_partition_filter_failure.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/drop_partition_filter_failure.q");
  }

  public void testNegativeCliDriver_drop_partition_filter_failure2() throws Exception {
    runTest("drop_partition_filter_failure2", "drop_partition_filter_failure2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/drop_partition_filter_failure2.q");
  }

  public void testNegativeCliDriver_drop_table_failure1() throws Exception {
    runTest("drop_table_failure1", "drop_table_failure1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/drop_table_failure1.q");
  }

  public void testNegativeCliDriver_drop_table_failure2() throws Exception {
    runTest("drop_table_failure2", "drop_table_failure2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/drop_table_failure2.q");
  }

  public void testNegativeCliDriver_drop_table_failure3() throws Exception {
    runTest("drop_table_failure3", "drop_table_failure3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/drop_table_failure3.q");
  }

  public void testNegativeCliDriver_drop_view_failure1() throws Exception {
    runTest("drop_view_failure1", "drop_view_failure1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/drop_view_failure1.q");
  }

  public void testNegativeCliDriver_drop_view_failure2() throws Exception {
    runTest("drop_view_failure2", "drop_view_failure2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/drop_view_failure2.q");
  }

  public void testNegativeCliDriver_duplicate_alias_in_transform() throws Exception {
    runTest("duplicate_alias_in_transform", "duplicate_alias_in_transform.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/duplicate_alias_in_transform.q");
  }

  public void testNegativeCliDriver_duplicate_alias_in_transform_schema() throws Exception {
    runTest("duplicate_alias_in_transform_schema", "duplicate_alias_in_transform_schema.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/duplicate_alias_in_transform_schema.q");
  }

  public void testNegativeCliDriver_duplicate_insert1() throws Exception {
    runTest("duplicate_insert1", "duplicate_insert1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/duplicate_insert1.q");
  }

  public void testNegativeCliDriver_duplicate_insert2() throws Exception {
    runTest("duplicate_insert2", "duplicate_insert2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/duplicate_insert2.q");
  }

  public void testNegativeCliDriver_duplicate_insert3() throws Exception {
    runTest("duplicate_insert3", "duplicate_insert3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/duplicate_insert3.q");
  }

  public void testNegativeCliDriver_dyn_part1() throws Exception {
    runTest("dyn_part1", "dyn_part1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/dyn_part1.q");
  }

  public void testNegativeCliDriver_dyn_part2() throws Exception {
    runTest("dyn_part2", "dyn_part2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/dyn_part2.q");
  }

  public void testNegativeCliDriver_dyn_part3() throws Exception {
    runTest("dyn_part3", "dyn_part3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/dyn_part3.q");
  }

  public void testNegativeCliDriver_dyn_part4() throws Exception {
    runTest("dyn_part4", "dyn_part4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/dyn_part4.q");
  }

  public void testNegativeCliDriver_dyn_part_max() throws Exception {
    runTest("dyn_part_max", "dyn_part_max.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/dyn_part_max.q");
  }

  public void testNegativeCliDriver_dyn_part_max_per_node() throws Exception {
    runTest("dyn_part_max_per_node", "dyn_part_max_per_node.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/dyn_part_max_per_node.q");
  }

  public void testNegativeCliDriver_dynamic_partitions_with_whitelist() throws Exception {
    runTest("dynamic_partitions_with_whitelist", "dynamic_partitions_with_whitelist.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/dynamic_partitions_with_whitelist.q");
  }

  public void testNegativeCliDriver_exchange_partition_neg_incomplete_partition() throws Exception {
    runTest("exchange_partition_neg_incomplete_partition", "exchange_partition_neg_incomplete_partition.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exchange_partition_neg_incomplete_partition.q");
  }

  public void testNegativeCliDriver_exchange_partition_neg_partition_exists() throws Exception {
    runTest("exchange_partition_neg_partition_exists", "exchange_partition_neg_partition_exists.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exchange_partition_neg_partition_exists.q");
  }

  public void testNegativeCliDriver_exchange_partition_neg_partition_exists2() throws Exception {
    runTest("exchange_partition_neg_partition_exists2", "exchange_partition_neg_partition_exists2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exchange_partition_neg_partition_exists2.q");
  }

  public void testNegativeCliDriver_exchange_partition_neg_partition_exists3() throws Exception {
    runTest("exchange_partition_neg_partition_exists3", "exchange_partition_neg_partition_exists3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exchange_partition_neg_partition_exists3.q");
  }

  public void testNegativeCliDriver_exchange_partition_neg_partition_missing() throws Exception {
    runTest("exchange_partition_neg_partition_missing", "exchange_partition_neg_partition_missing.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exchange_partition_neg_partition_missing.q");
  }

  public void testNegativeCliDriver_exchange_partition_neg_table_missing() throws Exception {
    runTest("exchange_partition_neg_table_missing", "exchange_partition_neg_table_missing.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exchange_partition_neg_table_missing.q");
  }

  public void testNegativeCliDriver_exchange_partition_neg_table_missing2() throws Exception {
    runTest("exchange_partition_neg_table_missing2", "exchange_partition_neg_table_missing2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exchange_partition_neg_table_missing2.q");
  }

  public void testNegativeCliDriver_exchange_partition_neg_test() throws Exception {
    runTest("exchange_partition_neg_test", "exchange_partition_neg_test.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exchange_partition_neg_test.q");
  }

  public void testNegativeCliDriver_exim_00_unsupported_schema() throws Exception {
    runTest("exim_00_unsupported_schema", "exim_00_unsupported_schema.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_00_unsupported_schema.q");
  }

  public void testNegativeCliDriver_exim_01_nonpart_over_loaded() throws Exception {
    runTest("exim_01_nonpart_over_loaded", "exim_01_nonpart_over_loaded.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_01_nonpart_over_loaded.q");
  }

  public void testNegativeCliDriver_exim_02_all_part_over_overlap() throws Exception {
    runTest("exim_02_all_part_over_overlap", "exim_02_all_part_over_overlap.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_02_all_part_over_overlap.q");
  }

  public void testNegativeCliDriver_exim_03_nonpart_noncompat_colschema() throws Exception {
    runTest("exim_03_nonpart_noncompat_colschema", "exim_03_nonpart_noncompat_colschema.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_03_nonpart_noncompat_colschema.q");
  }

  public void testNegativeCliDriver_exim_04_nonpart_noncompat_colnumber() throws Exception {
    runTest("exim_04_nonpart_noncompat_colnumber", "exim_04_nonpart_noncompat_colnumber.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_04_nonpart_noncompat_colnumber.q");
  }

  public void testNegativeCliDriver_exim_05_nonpart_noncompat_coltype() throws Exception {
    runTest("exim_05_nonpart_noncompat_coltype", "exim_05_nonpart_noncompat_coltype.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_05_nonpart_noncompat_coltype.q");
  }

  public void testNegativeCliDriver_exim_06_nonpart_noncompat_storage() throws Exception {
    runTest("exim_06_nonpart_noncompat_storage", "exim_06_nonpart_noncompat_storage.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_06_nonpart_noncompat_storage.q");
  }

  public void testNegativeCliDriver_exim_07_nonpart_noncompat_ifof() throws Exception {
    runTest("exim_07_nonpart_noncompat_ifof", "exim_07_nonpart_noncompat_ifof.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_07_nonpart_noncompat_ifof.q");
  }

  public void testNegativeCliDriver_exim_08_nonpart_noncompat_serde() throws Exception {
    runTest("exim_08_nonpart_noncompat_serde", "exim_08_nonpart_noncompat_serde.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_08_nonpart_noncompat_serde.q");
  }

  public void testNegativeCliDriver_exim_09_nonpart_noncompat_serdeparam() throws Exception {
    runTest("exim_09_nonpart_noncompat_serdeparam", "exim_09_nonpart_noncompat_serdeparam.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_09_nonpart_noncompat_serdeparam.q");
  }

  public void testNegativeCliDriver_exim_10_nonpart_noncompat_bucketing() throws Exception {
    runTest("exim_10_nonpart_noncompat_bucketing", "exim_10_nonpart_noncompat_bucketing.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_10_nonpart_noncompat_bucketing.q");
  }

  public void testNegativeCliDriver_exim_11_nonpart_noncompat_sorting() throws Exception {
    runTest("exim_11_nonpart_noncompat_sorting", "exim_11_nonpart_noncompat_sorting.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_11_nonpart_noncompat_sorting.q");
  }

  public void testNegativeCliDriver_exim_12_nonnative_export() throws Exception {
    runTest("exim_12_nonnative_export", "exim_12_nonnative_export.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_12_nonnative_export.q");
  }

  public void testNegativeCliDriver_exim_13_nonnative_import() throws Exception {
    runTest("exim_13_nonnative_import", "exim_13_nonnative_import.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_13_nonnative_import.q");
  }

  public void testNegativeCliDriver_exim_14_nonpart_part() throws Exception {
    runTest("exim_14_nonpart_part", "exim_14_nonpart_part.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_14_nonpart_part.q");
  }

  public void testNegativeCliDriver_exim_15_part_nonpart() throws Exception {
    runTest("exim_15_part_nonpart", "exim_15_part_nonpart.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_15_part_nonpart.q");
  }

  public void testNegativeCliDriver_exim_16_part_noncompat_schema() throws Exception {
    runTest("exim_16_part_noncompat_schema", "exim_16_part_noncompat_schema.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_16_part_noncompat_schema.q");
  }

  public void testNegativeCliDriver_exim_17_part_spec_underspec() throws Exception {
    runTest("exim_17_part_spec_underspec", "exim_17_part_spec_underspec.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_17_part_spec_underspec.q");
  }

  public void testNegativeCliDriver_exim_18_part_spec_missing() throws Exception {
    runTest("exim_18_part_spec_missing", "exim_18_part_spec_missing.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_18_part_spec_missing.q");
  }

  public void testNegativeCliDriver_exim_19_external_over_existing() throws Exception {
    runTest("exim_19_external_over_existing", "exim_19_external_over_existing.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_19_external_over_existing.q");
  }

  public void testNegativeCliDriver_exim_20_managed_location_over_existing() throws Exception {
    runTest("exim_20_managed_location_over_existing", "exim_20_managed_location_over_existing.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_20_managed_location_over_existing.q");
  }

  public void testNegativeCliDriver_exim_21_part_managed_external() throws Exception {
    runTest("exim_21_part_managed_external", "exim_21_part_managed_external.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_21_part_managed_external.q");
  }

  public void testNegativeCliDriver_exim_22_export_authfail() throws Exception {
    runTest("exim_22_export_authfail", "exim_22_export_authfail.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_22_export_authfail.q");
  }

  public void testNegativeCliDriver_exim_23_import_exist_authfail() throws Exception {
    runTest("exim_23_import_exist_authfail", "exim_23_import_exist_authfail.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_23_import_exist_authfail.q");
  }

  public void testNegativeCliDriver_exim_24_import_part_authfail() throws Exception {
    runTest("exim_24_import_part_authfail", "exim_24_import_part_authfail.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_24_import_part_authfail.q");
  }

  public void testNegativeCliDriver_exim_25_import_nonexist_authfail() throws Exception {
    runTest("exim_25_import_nonexist_authfail", "exim_25_import_nonexist_authfail.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/exim_25_import_nonexist_authfail.q");
  }

  public void testNegativeCliDriver_external1() throws Exception {
    runTest("external1", "external1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/external1.q");
  }

  public void testNegativeCliDriver_external2() throws Exception {
    runTest("external2", "external2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/external2.q");
  }

  public void testNegativeCliDriver_fetchtask_ioexception() throws Exception {
    runTest("fetchtask_ioexception", "fetchtask_ioexception.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/fetchtask_ioexception.q");
  }

  public void testNegativeCliDriver_fileformat_bad_class() throws Exception {
    runTest("fileformat_bad_class", "fileformat_bad_class.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/fileformat_bad_class.q");
  }

  public void testNegativeCliDriver_fileformat_void_input() throws Exception {
    runTest("fileformat_void_input", "fileformat_void_input.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/fileformat_void_input.q");
  }

  public void testNegativeCliDriver_fileformat_void_output() throws Exception {
    runTest("fileformat_void_output", "fileformat_void_output.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/fileformat_void_output.q");
  }

  public void testNegativeCliDriver_fs_default_name1() throws Exception {
    runTest("fs_default_name1", "fs_default_name1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/fs_default_name1.q");
  }

  public void testNegativeCliDriver_fs_default_name2() throws Exception {
    runTest("fs_default_name2", "fs_default_name2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/fs_default_name2.q");
  }

  public void testNegativeCliDriver_genericFileFormat() throws Exception {
    runTest("genericFileFormat", "genericFileFormat.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/genericFileFormat.q");
  }

  public void testNegativeCliDriver_groupby2_map_skew_multi_distinct() throws Exception {
    runTest("groupby2_map_skew_multi_distinct", "groupby2_map_skew_multi_distinct.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/groupby2_map_skew_multi_distinct.q");
  }

  public void testNegativeCliDriver_groupby2_multi_distinct() throws Exception {
    runTest("groupby2_multi_distinct", "groupby2_multi_distinct.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/groupby2_multi_distinct.q");
  }

  public void testNegativeCliDriver_groupby3_map_skew_multi_distinct() throws Exception {
    runTest("groupby3_map_skew_multi_distinct", "groupby3_map_skew_multi_distinct.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/groupby3_map_skew_multi_distinct.q");
  }

  public void testNegativeCliDriver_groupby3_multi_distinct() throws Exception {
    runTest("groupby3_multi_distinct", "groupby3_multi_distinct.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/groupby3_multi_distinct.q");
  }

  public void testNegativeCliDriver_groupby_cube1() throws Exception {
    runTest("groupby_cube1", "groupby_cube1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/groupby_cube1.q");
  }

  public void testNegativeCliDriver_groupby_cube2() throws Exception {
    runTest("groupby_cube2", "groupby_cube2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/groupby_cube2.q");
  }

  public void testNegativeCliDriver_groupby_grouping_id1() throws Exception {
    runTest("groupby_grouping_id1", "groupby_grouping_id1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/groupby_grouping_id1.q");
  }

  public void testNegativeCliDriver_groupby_grouping_sets1() throws Exception {
    runTest("groupby_grouping_sets1", "groupby_grouping_sets1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/groupby_grouping_sets1.q");
  }

  public void testNegativeCliDriver_groupby_grouping_sets2() throws Exception {
    runTest("groupby_grouping_sets2", "groupby_grouping_sets2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/groupby_grouping_sets2.q");
  }

  public void testNegativeCliDriver_groupby_grouping_sets3() throws Exception {
    runTest("groupby_grouping_sets3", "groupby_grouping_sets3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/groupby_grouping_sets3.q");
  }

  public void testNegativeCliDriver_groupby_grouping_sets4() throws Exception {
    runTest("groupby_grouping_sets4", "groupby_grouping_sets4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/groupby_grouping_sets4.q");
  }

  public void testNegativeCliDriver_groupby_grouping_sets5() throws Exception {
    runTest("groupby_grouping_sets5", "groupby_grouping_sets5.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/groupby_grouping_sets5.q");
  }

  public void testNegativeCliDriver_groupby_grouping_sets6() throws Exception {
    runTest("groupby_grouping_sets6", "groupby_grouping_sets6.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/groupby_grouping_sets6.q");
  }

  public void testNegativeCliDriver_groupby_grouping_sets7() throws Exception {
    runTest("groupby_grouping_sets7", "groupby_grouping_sets7.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/groupby_grouping_sets7.q");
  }

  public void testNegativeCliDriver_groupby_invalid_position() throws Exception {
    runTest("groupby_invalid_position", "groupby_invalid_position.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/groupby_invalid_position.q");
  }

  public void testNegativeCliDriver_groupby_key() throws Exception {
    runTest("groupby_key", "groupby_key.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/groupby_key.q");
  }

  public void testNegativeCliDriver_groupby_rollup1() throws Exception {
    runTest("groupby_rollup1", "groupby_rollup1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/groupby_rollup1.q");
  }

  public void testNegativeCliDriver_groupby_rollup2() throws Exception {
    runTest("groupby_rollup2", "groupby_rollup2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/groupby_rollup2.q");
  }

  public void testNegativeCliDriver_having1() throws Exception {
    runTest("having1", "having1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/having1.q");
  }

  public void testNegativeCliDriver_illegal_partition_type() throws Exception {
    runTest("illegal_partition_type", "illegal_partition_type.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/illegal_partition_type.q");
  }

  public void testNegativeCliDriver_illegal_partition_type2() throws Exception {
    runTest("illegal_partition_type2", "illegal_partition_type2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/illegal_partition_type2.q");
  }

  public void testNegativeCliDriver_index_bitmap_no_map_aggr() throws Exception {
    runTest("index_bitmap_no_map_aggr", "index_bitmap_no_map_aggr.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/index_bitmap_no_map_aggr.q");
  }

  public void testNegativeCliDriver_index_compact_entry_limit() throws Exception {
    runTest("index_compact_entry_limit", "index_compact_entry_limit.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/index_compact_entry_limit.q");
  }

  public void testNegativeCliDriver_index_compact_size_limit() throws Exception {
    runTest("index_compact_size_limit", "index_compact_size_limit.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/index_compact_size_limit.q");
  }

  public void testNegativeCliDriver_input1() throws Exception {
    runTest("input1", "input1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/input1.q");
  }

  public void testNegativeCliDriver_input2() throws Exception {
    runTest("input2", "input2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/input2.q");
  }

  public void testNegativeCliDriver_input4() throws Exception {
    runTest("input4", "input4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/input4.q");
  }

  public void testNegativeCliDriver_input41() throws Exception {
    runTest("input41", "input41.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/input41.q");
  }

  public void testNegativeCliDriver_input_part0_neg() throws Exception {
    runTest("input_part0_neg", "input_part0_neg.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/input_part0_neg.q");
  }

  public void testNegativeCliDriver_insert_into1() throws Exception {
    runTest("insert_into1", "insert_into1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/insert_into1.q");
  }

  public void testNegativeCliDriver_insert_into2() throws Exception {
    runTest("insert_into2", "insert_into2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/insert_into2.q");
  }

  public void testNegativeCliDriver_insert_into3() throws Exception {
    runTest("insert_into3", "insert_into3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/insert_into3.q");
  }

  public void testNegativeCliDriver_insert_into4() throws Exception {
    runTest("insert_into4", "insert_into4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/insert_into4.q");
  }

  public void testNegativeCliDriver_insert_view_failure() throws Exception {
    runTest("insert_view_failure", "insert_view_failure.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/insert_view_failure.q");
  }

  public void testNegativeCliDriver_insertexternal1() throws Exception {
    runTest("insertexternal1", "insertexternal1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/insertexternal1.q");
  }

  public void testNegativeCliDriver_insertover_dynapart_ifnotexists() throws Exception {
    runTest("insertover_dynapart_ifnotexists", "insertover_dynapart_ifnotexists.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/insertover_dynapart_ifnotexists.q");
  }

  public void testNegativeCliDriver_invalid_arithmetic_type() throws Exception {
    runTest("invalid_arithmetic_type", "invalid_arithmetic_type.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_arithmetic_type.q");
  }

  public void testNegativeCliDriver_invalid_avg_syntax() throws Exception {
    runTest("invalid_avg_syntax", "invalid_avg_syntax.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_avg_syntax.q");
  }

  public void testNegativeCliDriver_invalid_cast_from_binary_1() throws Exception {
    runTest("invalid_cast_from_binary_1", "invalid_cast_from_binary_1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_cast_from_binary_1.q");
  }

  public void testNegativeCliDriver_invalid_cast_from_binary_2() throws Exception {
    runTest("invalid_cast_from_binary_2", "invalid_cast_from_binary_2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_cast_from_binary_2.q");
  }

  public void testNegativeCliDriver_invalid_cast_from_binary_3() throws Exception {
    runTest("invalid_cast_from_binary_3", "invalid_cast_from_binary_3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_cast_from_binary_3.q");
  }

  public void testNegativeCliDriver_invalid_cast_from_binary_4() throws Exception {
    runTest("invalid_cast_from_binary_4", "invalid_cast_from_binary_4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_cast_from_binary_4.q");
  }

  public void testNegativeCliDriver_invalid_cast_from_binary_5() throws Exception {
    runTest("invalid_cast_from_binary_5", "invalid_cast_from_binary_5.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_cast_from_binary_5.q");
  }

  public void testNegativeCliDriver_invalid_cast_from_binary_6() throws Exception {
    runTest("invalid_cast_from_binary_6", "invalid_cast_from_binary_6.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_cast_from_binary_6.q");
  }

  public void testNegativeCliDriver_invalid_cast_to_binary_1() throws Exception {
    runTest("invalid_cast_to_binary_1", "invalid_cast_to_binary_1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_cast_to_binary_1.q");
  }

  public void testNegativeCliDriver_invalid_cast_to_binary_2() throws Exception {
    runTest("invalid_cast_to_binary_2", "invalid_cast_to_binary_2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_cast_to_binary_2.q");
  }

  public void testNegativeCliDriver_invalid_cast_to_binary_3() throws Exception {
    runTest("invalid_cast_to_binary_3", "invalid_cast_to_binary_3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_cast_to_binary_3.q");
  }

  public void testNegativeCliDriver_invalid_cast_to_binary_4() throws Exception {
    runTest("invalid_cast_to_binary_4", "invalid_cast_to_binary_4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_cast_to_binary_4.q");
  }

  public void testNegativeCliDriver_invalid_cast_to_binary_5() throws Exception {
    runTest("invalid_cast_to_binary_5", "invalid_cast_to_binary_5.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_cast_to_binary_5.q");
  }

  public void testNegativeCliDriver_invalid_cast_to_binary_6() throws Exception {
    runTest("invalid_cast_to_binary_6", "invalid_cast_to_binary_6.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_cast_to_binary_6.q");
  }

  public void testNegativeCliDriver_invalid_columns() throws Exception {
    runTest("invalid_columns", "invalid_columns.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_columns.q");
  }

  public void testNegativeCliDriver_invalid_config1() throws Exception {
    runTest("invalid_config1", "invalid_config1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_config1.q");
  }

  public void testNegativeCliDriver_invalid_config2() throws Exception {
    runTest("invalid_config2", "invalid_config2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_config2.q");
  }

  public void testNegativeCliDriver_invalid_create_tbl1() throws Exception {
    runTest("invalid_create_tbl1", "invalid_create_tbl1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_create_tbl1.q");
  }

  public void testNegativeCliDriver_invalid_create_tbl2() throws Exception {
    runTest("invalid_create_tbl2", "invalid_create_tbl2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_create_tbl2.q");
  }

  public void testNegativeCliDriver_invalid_mapjoin1() throws Exception {
    runTest("invalid_mapjoin1", "invalid_mapjoin1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_mapjoin1.q");
  }

  public void testNegativeCliDriver_invalid_max_syntax() throws Exception {
    runTest("invalid_max_syntax", "invalid_max_syntax.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_max_syntax.q");
  }

  public void testNegativeCliDriver_invalid_min_syntax() throws Exception {
    runTest("invalid_min_syntax", "invalid_min_syntax.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_min_syntax.q");
  }

  public void testNegativeCliDriver_invalid_select_column() throws Exception {
    runTest("invalid_select_column", "invalid_select_column.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_select_column.q");
  }

  public void testNegativeCliDriver_invalid_select_column_with_subquery() throws Exception {
    runTest("invalid_select_column_with_subquery", "invalid_select_column_with_subquery.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_select_column_with_subquery.q");
  }

  public void testNegativeCliDriver_invalid_select_column_with_tablename() throws Exception {
    runTest("invalid_select_column_with_tablename", "invalid_select_column_with_tablename.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_select_column_with_tablename.q");
  }

  public void testNegativeCliDriver_invalid_select_expression() throws Exception {
    runTest("invalid_select_expression", "invalid_select_expression.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_select_expression.q");
  }

  public void testNegativeCliDriver_invalid_std_syntax() throws Exception {
    runTest("invalid_std_syntax", "invalid_std_syntax.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_std_syntax.q");
  }

  public void testNegativeCliDriver_invalid_stddev_samp_syntax() throws Exception {
    runTest("invalid_stddev_samp_syntax", "invalid_stddev_samp_syntax.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_stddev_samp_syntax.q");
  }

  public void testNegativeCliDriver_invalid_sum_syntax() throws Exception {
    runTest("invalid_sum_syntax", "invalid_sum_syntax.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_sum_syntax.q");
  }

  public void testNegativeCliDriver_invalid_t_alter1() throws Exception {
    runTest("invalid_t_alter1", "invalid_t_alter1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_t_alter1.q");
  }

  public void testNegativeCliDriver_invalid_t_alter2() throws Exception {
    runTest("invalid_t_alter2", "invalid_t_alter2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_t_alter2.q");
  }

  public void testNegativeCliDriver_invalid_t_create2() throws Exception {
    runTest("invalid_t_create2", "invalid_t_create2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_t_create2.q");
  }

  public void testNegativeCliDriver_invalid_t_transform() throws Exception {
    runTest("invalid_t_transform", "invalid_t_transform.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_t_transform.q");
  }

  public void testNegativeCliDriver_invalid_tbl_name() throws Exception {
    runTest("invalid_tbl_name", "invalid_tbl_name.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_tbl_name.q");
  }

  public void testNegativeCliDriver_invalid_var_samp_syntax() throws Exception {
    runTest("invalid_var_samp_syntax", "invalid_var_samp_syntax.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_var_samp_syntax.q");
  }

  public void testNegativeCliDriver_invalid_varchar_length_1() throws Exception {
    runTest("invalid_varchar_length_1", "invalid_varchar_length_1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_varchar_length_1.q");
  }

  public void testNegativeCliDriver_invalid_varchar_length_2() throws Exception {
    runTest("invalid_varchar_length_2", "invalid_varchar_length_2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_varchar_length_2.q");
  }

  public void testNegativeCliDriver_invalid_varchar_length_3() throws Exception {
    runTest("invalid_varchar_length_3", "invalid_varchar_length_3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_varchar_length_3.q");
  }

  public void testNegativeCliDriver_invalid_variance_syntax() throws Exception {
    runTest("invalid_variance_syntax", "invalid_variance_syntax.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalid_variance_syntax.q");
  }

  public void testNegativeCliDriver_invalidate_view1() throws Exception {
    runTest("invalidate_view1", "invalidate_view1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/invalidate_view1.q");
  }

  public void testNegativeCliDriver_join2() throws Exception {
    runTest("join2", "join2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/join2.q");
  }

  public void testNegativeCliDriver_join28() throws Exception {
    runTest("join28", "join28.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/join28.q");
  }

  public void testNegativeCliDriver_join29() throws Exception {
    runTest("join29", "join29.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/join29.q");
  }

  public void testNegativeCliDriver_join32() throws Exception {
    runTest("join32", "join32.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/join32.q");
  }

  public void testNegativeCliDriver_join35() throws Exception {
    runTest("join35", "join35.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/join35.q");
  }

  public void testNegativeCliDriver_join_nonexistent_part() throws Exception {
    runTest("join_nonexistent_part", "join_nonexistent_part.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/join_nonexistent_part.q");
  }

  public void testNegativeCliDriver_joinneg() throws Exception {
    runTest("joinneg", "joinneg.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/joinneg.q");
  }

  public void testNegativeCliDriver_lateral_view_alias() throws Exception {
    runTest("lateral_view_alias", "lateral_view_alias.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/lateral_view_alias.q");
  }

  public void testNegativeCliDriver_lateral_view_join() throws Exception {
    runTest("lateral_view_join", "lateral_view_join.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/lateral_view_join.q");
  }

  public void testNegativeCliDriver_line_terminator() throws Exception {
    runTest("line_terminator", "line_terminator.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/line_terminator.q");
  }

  public void testNegativeCliDriver_load_exist_part_authfail() throws Exception {
    runTest("load_exist_part_authfail", "load_exist_part_authfail.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/load_exist_part_authfail.q");
  }

  public void testNegativeCliDriver_load_non_native() throws Exception {
    runTest("load_non_native", "load_non_native.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/load_non_native.q");
  }

  public void testNegativeCliDriver_load_nonpart_authfail() throws Exception {
    runTest("load_nonpart_authfail", "load_nonpart_authfail.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/load_nonpart_authfail.q");
  }

  public void testNegativeCliDriver_load_part_authfail() throws Exception {
    runTest("load_part_authfail", "load_part_authfail.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/load_part_authfail.q");
  }

  public void testNegativeCliDriver_load_part_nospec() throws Exception {
    runTest("load_part_nospec", "load_part_nospec.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/load_part_nospec.q");
  }

  public void testNegativeCliDriver_load_stored_as_dirs() throws Exception {
    runTest("load_stored_as_dirs", "load_stored_as_dirs.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/load_stored_as_dirs.q");
  }

  public void testNegativeCliDriver_load_view_failure() throws Exception {
    runTest("load_view_failure", "load_view_failure.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/load_view_failure.q");
  }

  public void testNegativeCliDriver_load_wrong_fileformat() throws Exception {
    runTest("load_wrong_fileformat", "load_wrong_fileformat.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/load_wrong_fileformat.q");
  }

  public void testNegativeCliDriver_load_wrong_fileformat_rc_seq() throws Exception {
    runTest("load_wrong_fileformat_rc_seq", "load_wrong_fileformat_rc_seq.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/load_wrong_fileformat_rc_seq.q");
  }

  public void testNegativeCliDriver_load_wrong_fileformat_txt_seq() throws Exception {
    runTest("load_wrong_fileformat_txt_seq", "load_wrong_fileformat_txt_seq.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/load_wrong_fileformat_txt_seq.q");
  }

  public void testNegativeCliDriver_load_wrong_noof_part() throws Exception {
    runTest("load_wrong_noof_part", "load_wrong_noof_part.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/load_wrong_noof_part.q");
  }

  public void testNegativeCliDriver_local_mapred_error_cache() throws Exception {
    runTest("local_mapred_error_cache", "local_mapred_error_cache.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/local_mapred_error_cache.q");
  }

  public void testNegativeCliDriver_lockneg1() throws Exception {
    runTest("lockneg1", "lockneg1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/lockneg1.q");
  }

  public void testNegativeCliDriver_lockneg2() throws Exception {
    runTest("lockneg2", "lockneg2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/lockneg2.q");
  }

  public void testNegativeCliDriver_lockneg3() throws Exception {
    runTest("lockneg3", "lockneg3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/lockneg3.q");
  }

  public void testNegativeCliDriver_lockneg4() throws Exception {
    runTest("lockneg4", "lockneg4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/lockneg4.q");
  }

  public void testNegativeCliDriver_lockneg5() throws Exception {
    runTest("lockneg5", "lockneg5.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/lockneg5.q");
  }

  public void testNegativeCliDriver_macro_unused_parameter() throws Exception {
    runTest("macro_unused_parameter", "macro_unused_parameter.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/macro_unused_parameter.q");
  }

  public void testNegativeCliDriver_merge_negative_1() throws Exception {
    runTest("merge_negative_1", "merge_negative_1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/merge_negative_1.q");
  }

  public void testNegativeCliDriver_merge_negative_2() throws Exception {
    runTest("merge_negative_2", "merge_negative_2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/merge_negative_2.q");
  }

  public void testNegativeCliDriver_merge_negative_3() throws Exception {
    runTest("merge_negative_3", "merge_negative_3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/merge_negative_3.q");
  }

  public void testNegativeCliDriver_nested_complex_neg() throws Exception {
    runTest("nested_complex_neg", "nested_complex_neg.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/nested_complex_neg.q");
  }

  public void testNegativeCliDriver_no_matching_udf() throws Exception {
    runTest("no_matching_udf", "no_matching_udf.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/no_matching_udf.q");
  }

  public void testNegativeCliDriver_nonkey_groupby() throws Exception {
    runTest("nonkey_groupby", "nonkey_groupby.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/nonkey_groupby.q");
  }

  public void testNegativeCliDriver_nopart_insert() throws Exception {
    runTest("nopart_insert", "nopart_insert.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/nopart_insert.q");
  }

  public void testNegativeCliDriver_nopart_load() throws Exception {
    runTest("nopart_load", "nopart_load.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/nopart_load.q");
  }

  public void testNegativeCliDriver_notable_alias3() throws Exception {
    runTest("notable_alias3", "notable_alias3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/notable_alias3.q");
  }

  public void testNegativeCliDriver_notable_alias4() throws Exception {
    runTest("notable_alias4", "notable_alias4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/notable_alias4.q");
  }

  public void testNegativeCliDriver_orderby_invalid_position() throws Exception {
    runTest("orderby_invalid_position", "orderby_invalid_position.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/orderby_invalid_position.q");
  }

  public void testNegativeCliDriver_orderby_position_unsupported() throws Exception {
    runTest("orderby_position_unsupported", "orderby_position_unsupported.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/orderby_position_unsupported.q");
  }

  public void testNegativeCliDriver_orderbysortby() throws Exception {
    runTest("orderbysortby", "orderbysortby.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/orderbysortby.q");
  }

  public void testNegativeCliDriver_part_col_complex_type() throws Exception {
    runTest("part_col_complex_type", "part_col_complex_type.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/part_col_complex_type.q");
  }

  public void testNegativeCliDriver_protectmode_part() throws Exception {
    runTest("protectmode_part", "protectmode_part.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/protectmode_part.q");
  }

  public void testNegativeCliDriver_protectmode_part1() throws Exception {
    runTest("protectmode_part1", "protectmode_part1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/protectmode_part1.q");
  }

  public void testNegativeCliDriver_protectmode_part2() throws Exception {
    runTest("protectmode_part2", "protectmode_part2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/protectmode_part2.q");
  }

  public void testNegativeCliDriver_protectmode_part_no_drop() throws Exception {
    runTest("protectmode_part_no_drop", "protectmode_part_no_drop.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/protectmode_part_no_drop.q");
  }

  public void testNegativeCliDriver_protectmode_tbl1() throws Exception {
    runTest("protectmode_tbl1", "protectmode_tbl1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/protectmode_tbl1.q");
  }

  public void testNegativeCliDriver_protectmode_tbl2() throws Exception {
    runTest("protectmode_tbl2", "protectmode_tbl2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/protectmode_tbl2.q");
  }

  public void testNegativeCliDriver_protectmode_tbl3() throws Exception {
    runTest("protectmode_tbl3", "protectmode_tbl3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/protectmode_tbl3.q");
  }

  public void testNegativeCliDriver_protectmode_tbl4() throws Exception {
    runTest("protectmode_tbl4", "protectmode_tbl4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/protectmode_tbl4.q");
  }

  public void testNegativeCliDriver_protectmode_tbl5() throws Exception {
    runTest("protectmode_tbl5", "protectmode_tbl5.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/protectmode_tbl5.q");
  }

  public void testNegativeCliDriver_protectmode_tbl6() throws Exception {
    runTest("protectmode_tbl6", "protectmode_tbl6.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/protectmode_tbl6.q");
  }

  public void testNegativeCliDriver_protectmode_tbl7() throws Exception {
    runTest("protectmode_tbl7", "protectmode_tbl7.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/protectmode_tbl7.q");
  }

  public void testNegativeCliDriver_protectmode_tbl8() throws Exception {
    runTest("protectmode_tbl8", "protectmode_tbl8.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/protectmode_tbl8.q");
  }

  public void testNegativeCliDriver_protectmode_tbl_no_drop() throws Exception {
    runTest("protectmode_tbl_no_drop", "protectmode_tbl_no_drop.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/protectmode_tbl_no_drop.q");
  }

  public void testNegativeCliDriver_ptf_negative_AggrFuncsWithNoGBYNoPartDef() throws Exception {
    runTest("ptf_negative_AggrFuncsWithNoGBYNoPartDef", "ptf_negative_AggrFuncsWithNoGBYNoPartDef.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/ptf_negative_AggrFuncsWithNoGBYNoPartDef.q");
  }

  public void testNegativeCliDriver_ptf_negative_AmbiguousWindowDefn() throws Exception {
    runTest("ptf_negative_AmbiguousWindowDefn", "ptf_negative_AmbiguousWindowDefn.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/ptf_negative_AmbiguousWindowDefn.q");
  }

  public void testNegativeCliDriver_ptf_negative_DistributeByOrderBy() throws Exception {
    runTest("ptf_negative_DistributeByOrderBy", "ptf_negative_DistributeByOrderBy.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/ptf_negative_DistributeByOrderBy.q");
  }

  public void testNegativeCliDriver_ptf_negative_DuplicateWindowAlias() throws Exception {
    runTest("ptf_negative_DuplicateWindowAlias", "ptf_negative_DuplicateWindowAlias.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/ptf_negative_DuplicateWindowAlias.q");
  }

  public void testNegativeCliDriver_ptf_negative_HavingLeadWithNoGBYNoWindowing() throws Exception {
    runTest("ptf_negative_HavingLeadWithNoGBYNoWindowing", "ptf_negative_HavingLeadWithNoGBYNoWindowing.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/ptf_negative_HavingLeadWithNoGBYNoWindowing.q");
  }

  public void testNegativeCliDriver_ptf_negative_HavingLeadWithPTF() throws Exception {
    runTest("ptf_negative_HavingLeadWithPTF", "ptf_negative_HavingLeadWithPTF.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/ptf_negative_HavingLeadWithPTF.q");
  }

  public void testNegativeCliDriver_ptf_negative_InvalidValueBoundary() throws Exception {
    runTest("ptf_negative_InvalidValueBoundary", "ptf_negative_InvalidValueBoundary.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/ptf_negative_InvalidValueBoundary.q");
  }

  public void testNegativeCliDriver_ptf_negative_JoinWithAmbigousAlias() throws Exception {
    runTest("ptf_negative_JoinWithAmbigousAlias", "ptf_negative_JoinWithAmbigousAlias.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/ptf_negative_JoinWithAmbigousAlias.q");
  }

  public void testNegativeCliDriver_ptf_negative_PartitionBySortBy() throws Exception {
    runTest("ptf_negative_PartitionBySortBy", "ptf_negative_PartitionBySortBy.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/ptf_negative_PartitionBySortBy.q");
  }

  public void testNegativeCliDriver_ptf_negative_WhereWithRankCond() throws Exception {
    runTest("ptf_negative_WhereWithRankCond", "ptf_negative_WhereWithRankCond.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/ptf_negative_WhereWithRankCond.q");
  }

  public void testNegativeCliDriver_ptf_window_boundaries() throws Exception {
    runTest("ptf_window_boundaries", "ptf_window_boundaries.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/ptf_window_boundaries.q");
  }

  public void testNegativeCliDriver_ptf_window_boundaries2() throws Exception {
    runTest("ptf_window_boundaries2", "ptf_window_boundaries2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/ptf_window_boundaries2.q");
  }

  public void testNegativeCliDriver_recursive_view() throws Exception {
    runTest("recursive_view", "recursive_view.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/recursive_view.q");
  }

  public void testNegativeCliDriver_regex_col_1() throws Exception {
    runTest("regex_col_1", "regex_col_1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/regex_col_1.q");
  }

  public void testNegativeCliDriver_regex_col_2() throws Exception {
    runTest("regex_col_2", "regex_col_2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/regex_col_2.q");
  }

  public void testNegativeCliDriver_regex_col_groupby() throws Exception {
    runTest("regex_col_groupby", "regex_col_groupby.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/regex_col_groupby.q");
  }

  public void testNegativeCliDriver_sa_fail_hook3() throws Exception {
    runTest("sa_fail_hook3", "sa_fail_hook3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/sa_fail_hook3.q");
  }

  public void testNegativeCliDriver_sample() throws Exception {
    runTest("sample", "sample.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/sample.q");
  }

  public void testNegativeCliDriver_script_broken_pipe1() throws Exception {
    runTest("script_broken_pipe1", "script_broken_pipe1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/script_broken_pipe1.q");
  }

  public void testNegativeCliDriver_script_broken_pipe2() throws Exception {
    runTest("script_broken_pipe2", "script_broken_pipe2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/script_broken_pipe2.q");
  }

  public void testNegativeCliDriver_script_broken_pipe3() throws Exception {
    runTest("script_broken_pipe3", "script_broken_pipe3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/script_broken_pipe3.q");
  }

  public void testNegativeCliDriver_script_error() throws Exception {
    runTest("script_error", "script_error.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/script_error.q");
  }

  public void testNegativeCliDriver_select_charliteral() throws Exception {
    runTest("select_charliteral", "select_charliteral.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/select_charliteral.q");
  }

  public void testNegativeCliDriver_select_udtf_alias() throws Exception {
    runTest("select_udtf_alias", "select_udtf_alias.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/select_udtf_alias.q");
  }

  public void testNegativeCliDriver_semijoin1() throws Exception {
    runTest("semijoin1", "semijoin1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/semijoin1.q");
  }

  public void testNegativeCliDriver_semijoin2() throws Exception {
    runTest("semijoin2", "semijoin2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/semijoin2.q");
  }

  public void testNegativeCliDriver_semijoin3() throws Exception {
    runTest("semijoin3", "semijoin3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/semijoin3.q");
  }

  public void testNegativeCliDriver_semijoin4() throws Exception {
    runTest("semijoin4", "semijoin4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/semijoin4.q");
  }

  public void testNegativeCliDriver_serde_regex() throws Exception {
    runTest("serde_regex", "serde_regex.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/serde_regex.q");
  }

  public void testNegativeCliDriver_serde_regex2() throws Exception {
    runTest("serde_regex2", "serde_regex2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/serde_regex2.q");
  }

  public void testNegativeCliDriver_serde_regex3() throws Exception {
    runTest("serde_regex3", "serde_regex3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/serde_regex3.q");
  }

  public void testNegativeCliDriver_set_hiveconf_validation0() throws Exception {
    runTest("set_hiveconf_validation0", "set_hiveconf_validation0.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/set_hiveconf_validation0.q");
  }

  public void testNegativeCliDriver_set_hiveconf_validation1() throws Exception {
    runTest("set_hiveconf_validation1", "set_hiveconf_validation1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/set_hiveconf_validation1.q");
  }

  public void testNegativeCliDriver_set_table_property() throws Exception {
    runTest("set_table_property", "set_table_property.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/set_table_property.q");
  }

  public void testNegativeCliDriver_show_columns1() throws Exception {
    runTest("show_columns1", "show_columns1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/show_columns1.q");
  }

  public void testNegativeCliDriver_show_columns2() throws Exception {
    runTest("show_columns2", "show_columns2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/show_columns2.q");
  }

  public void testNegativeCliDriver_show_columns3() throws Exception {
    runTest("show_columns3", "show_columns3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/show_columns3.q");
  }

  public void testNegativeCliDriver_show_create_table_does_not_exist() throws Exception {
    runTest("show_create_table_does_not_exist", "show_create_table_does_not_exist.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/show_create_table_does_not_exist.q");
  }

  public void testNegativeCliDriver_show_create_table_index() throws Exception {
    runTest("show_create_table_index", "show_create_table_index.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/show_create_table_index.q");
  }

  public void testNegativeCliDriver_show_partitions1() throws Exception {
    runTest("show_partitions1", "show_partitions1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/show_partitions1.q");
  }

  public void testNegativeCliDriver_show_tableproperties1() throws Exception {
    runTest("show_tableproperties1", "show_tableproperties1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/show_tableproperties1.q");
  }

  public void testNegativeCliDriver_show_tables_bad1() throws Exception {
    runTest("show_tables_bad1", "show_tables_bad1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/show_tables_bad1.q");
  }

  public void testNegativeCliDriver_show_tables_bad2() throws Exception {
    runTest("show_tables_bad2", "show_tables_bad2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/show_tables_bad2.q");
  }

  public void testNegativeCliDriver_show_tables_bad_db1() throws Exception {
    runTest("show_tables_bad_db1", "show_tables_bad_db1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/show_tables_bad_db1.q");
  }

  public void testNegativeCliDriver_show_tables_bad_db2() throws Exception {
    runTest("show_tables_bad_db2", "show_tables_bad_db2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/show_tables_bad_db2.q");
  }

  public void testNegativeCliDriver_show_tablestatus() throws Exception {
    runTest("show_tablestatus", "show_tablestatus.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/show_tablestatus.q");
  }

  public void testNegativeCliDriver_show_tablestatus_not_existing_part() throws Exception {
    runTest("show_tablestatus_not_existing_part", "show_tablestatus_not_existing_part.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/show_tablestatus_not_existing_part.q");
  }

  public void testNegativeCliDriver_smb_bucketmapjoin() throws Exception {
    runTest("smb_bucketmapjoin", "smb_bucketmapjoin.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/smb_bucketmapjoin.q");
  }

  public void testNegativeCliDriver_smb_mapjoin_14() throws Exception {
    runTest("smb_mapjoin_14", "smb_mapjoin_14.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/smb_mapjoin_14.q");
  }

  public void testNegativeCliDriver_sortmerge_mapjoin_mismatch_1() throws Exception {
    runTest("sortmerge_mapjoin_mismatch_1", "sortmerge_mapjoin_mismatch_1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/sortmerge_mapjoin_mismatch_1.q");
  }

  public void testNegativeCliDriver_split_sample_out_of_range() throws Exception {
    runTest("split_sample_out_of_range", "split_sample_out_of_range.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/split_sample_out_of_range.q");
  }

  public void testNegativeCliDriver_split_sample_wrong_format() throws Exception {
    runTest("split_sample_wrong_format", "split_sample_wrong_format.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/split_sample_wrong_format.q");
  }

  public void testNegativeCliDriver_split_sample_wrong_format2() throws Exception {
    runTest("split_sample_wrong_format2", "split_sample_wrong_format2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/split_sample_wrong_format2.q");
  }

  public void testNegativeCliDriver_stats_aggregator_error_1() throws Exception {
    runTest("stats_aggregator_error_1", "stats_aggregator_error_1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/stats_aggregator_error_1.q");
  }

  public void testNegativeCliDriver_stats_aggregator_error_2() throws Exception {
    runTest("stats_aggregator_error_2", "stats_aggregator_error_2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/stats_aggregator_error_2.q");
  }

  public void testNegativeCliDriver_stats_noscan_non_native() throws Exception {
    runTest("stats_noscan_non_native", "stats_noscan_non_native.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/stats_noscan_non_native.q");
  }

  public void testNegativeCliDriver_stats_partialscan_autogether() throws Exception {
    runTest("stats_partialscan_autogether", "stats_partialscan_autogether.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/stats_partialscan_autogether.q");
  }

  public void testNegativeCliDriver_stats_partialscan_non_external() throws Exception {
    runTest("stats_partialscan_non_external", "stats_partialscan_non_external.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/stats_partialscan_non_external.q");
  }

  public void testNegativeCliDriver_stats_partialscan_non_native() throws Exception {
    runTest("stats_partialscan_non_native", "stats_partialscan_non_native.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/stats_partialscan_non_native.q");
  }

  public void testNegativeCliDriver_stats_partscan_norcfile() throws Exception {
    runTest("stats_partscan_norcfile", "stats_partscan_norcfile.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/stats_partscan_norcfile.q");
  }

  public void testNegativeCliDriver_stats_publisher_error_1() throws Exception {
    runTest("stats_publisher_error_1", "stats_publisher_error_1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/stats_publisher_error_1.q");
  }

  public void testNegativeCliDriver_stats_publisher_error_2() throws Exception {
    runTest("stats_publisher_error_2", "stats_publisher_error_2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/stats_publisher_error_2.q");
  }

  public void testNegativeCliDriver_strict_join() throws Exception {
    runTest("strict_join", "strict_join.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/strict_join.q");
  }

  public void testNegativeCliDriver_strict_orderby() throws Exception {
    runTest("strict_orderby", "strict_orderby.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/strict_orderby.q");
  }

  public void testNegativeCliDriver_strict_pruning() throws Exception {
    runTest("strict_pruning", "strict_pruning.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/strict_pruning.q");
  }

  public void testNegativeCliDriver_subq_insert() throws Exception {
    runTest("subq_insert", "subq_insert.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/subq_insert.q");
  }

  public void testNegativeCliDriver_touch1() throws Exception {
    runTest("touch1", "touch1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/touch1.q");
  }

  public void testNegativeCliDriver_touch2() throws Exception {
    runTest("touch2", "touch2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/touch2.q");
  }

  public void testNegativeCliDriver_truncate_bucketed_column() throws Exception {
    runTest("truncate_bucketed_column", "truncate_bucketed_column.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/truncate_bucketed_column.q");
  }

  public void testNegativeCliDriver_truncate_column_indexed_table() throws Exception {
    runTest("truncate_column_indexed_table", "truncate_column_indexed_table.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/truncate_column_indexed_table.q");
  }

  public void testNegativeCliDriver_truncate_column_list_bucketing() throws Exception {
    runTest("truncate_column_list_bucketing", "truncate_column_list_bucketing.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/truncate_column_list_bucketing.q");
  }

  public void testNegativeCliDriver_truncate_column_seqfile() throws Exception {
    runTest("truncate_column_seqfile", "truncate_column_seqfile.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/truncate_column_seqfile.q");
  }

  public void testNegativeCliDriver_truncate_nonexistant_column() throws Exception {
    runTest("truncate_nonexistant_column", "truncate_nonexistant_column.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/truncate_nonexistant_column.q");
  }

  public void testNegativeCliDriver_truncate_partition_column() throws Exception {
    runTest("truncate_partition_column", "truncate_partition_column.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/truncate_partition_column.q");
  }

  public void testNegativeCliDriver_truncate_partition_column2() throws Exception {
    runTest("truncate_partition_column2", "truncate_partition_column2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/truncate_partition_column2.q");
  }

  public void testNegativeCliDriver_truncate_table_failure1() throws Exception {
    runTest("truncate_table_failure1", "truncate_table_failure1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/truncate_table_failure1.q");
  }

  public void testNegativeCliDriver_truncate_table_failure2() throws Exception {
    runTest("truncate_table_failure2", "truncate_table_failure2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/truncate_table_failure2.q");
  }

  public void testNegativeCliDriver_truncate_table_failure3() throws Exception {
    runTest("truncate_table_failure3", "truncate_table_failure3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/truncate_table_failure3.q");
  }

  public void testNegativeCliDriver_truncate_table_failure4() throws Exception {
    runTest("truncate_table_failure4", "truncate_table_failure4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/truncate_table_failure4.q");
  }

  public void testNegativeCliDriver_udaf_invalid_place() throws Exception {
    runTest("udaf_invalid_place", "udaf_invalid_place.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udaf_invalid_place.q");
  }

  public void testNegativeCliDriver_udf_array_contains_wrong1() throws Exception {
    runTest("udf_array_contains_wrong1", "udf_array_contains_wrong1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_array_contains_wrong1.q");
  }

  public void testNegativeCliDriver_udf_array_contains_wrong2() throws Exception {
    runTest("udf_array_contains_wrong2", "udf_array_contains_wrong2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_array_contains_wrong2.q");
  }

  public void testNegativeCliDriver_udf_assert_true() throws Exception {
    runTest("udf_assert_true", "udf_assert_true.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_assert_true.q");
  }

  public void testNegativeCliDriver_udf_assert_true2() throws Exception {
    runTest("udf_assert_true2", "udf_assert_true2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_assert_true2.q");
  }

  public void testNegativeCliDriver_udf_case_type_wrong() throws Exception {
    runTest("udf_case_type_wrong", "udf_case_type_wrong.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_case_type_wrong.q");
  }

  public void testNegativeCliDriver_udf_case_type_wrong2() throws Exception {
    runTest("udf_case_type_wrong2", "udf_case_type_wrong2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_case_type_wrong2.q");
  }

  public void testNegativeCliDriver_udf_case_type_wrong3() throws Exception {
    runTest("udf_case_type_wrong3", "udf_case_type_wrong3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_case_type_wrong3.q");
  }

  public void testNegativeCliDriver_udf_coalesce() throws Exception {
    runTest("udf_coalesce", "udf_coalesce.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_coalesce.q");
  }

  public void testNegativeCliDriver_udf_concat_ws_wrong1() throws Exception {
    runTest("udf_concat_ws_wrong1", "udf_concat_ws_wrong1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_concat_ws_wrong1.q");
  }

  public void testNegativeCliDriver_udf_concat_ws_wrong2() throws Exception {
    runTest("udf_concat_ws_wrong2", "udf_concat_ws_wrong2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_concat_ws_wrong2.q");
  }

  public void testNegativeCliDriver_udf_concat_ws_wrong3() throws Exception {
    runTest("udf_concat_ws_wrong3", "udf_concat_ws_wrong3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_concat_ws_wrong3.q");
  }

  public void testNegativeCliDriver_udf_elt_wrong_args_len() throws Exception {
    runTest("udf_elt_wrong_args_len", "udf_elt_wrong_args_len.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_elt_wrong_args_len.q");
  }

  public void testNegativeCliDriver_udf_elt_wrong_type() throws Exception {
    runTest("udf_elt_wrong_type", "udf_elt_wrong_type.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_elt_wrong_type.q");
  }

  public void testNegativeCliDriver_udf_field_wrong_args_len() throws Exception {
    runTest("udf_field_wrong_args_len", "udf_field_wrong_args_len.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_field_wrong_args_len.q");
  }

  public void testNegativeCliDriver_udf_field_wrong_type() throws Exception {
    runTest("udf_field_wrong_type", "udf_field_wrong_type.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_field_wrong_type.q");
  }

  public void testNegativeCliDriver_udf_format_number_wrong1() throws Exception {
    runTest("udf_format_number_wrong1", "udf_format_number_wrong1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_format_number_wrong1.q");
  }

  public void testNegativeCliDriver_udf_format_number_wrong2() throws Exception {
    runTest("udf_format_number_wrong2", "udf_format_number_wrong2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_format_number_wrong2.q");
  }

  public void testNegativeCliDriver_udf_format_number_wrong3() throws Exception {
    runTest("udf_format_number_wrong3", "udf_format_number_wrong3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_format_number_wrong3.q");
  }

  public void testNegativeCliDriver_udf_format_number_wrong4() throws Exception {
    runTest("udf_format_number_wrong4", "udf_format_number_wrong4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_format_number_wrong4.q");
  }

  public void testNegativeCliDriver_udf_format_number_wrong5() throws Exception {
    runTest("udf_format_number_wrong5", "udf_format_number_wrong5.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_format_number_wrong5.q");
  }

  public void testNegativeCliDriver_udf_format_number_wrong6() throws Exception {
    runTest("udf_format_number_wrong6", "udf_format_number_wrong6.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_format_number_wrong6.q");
  }

  public void testNegativeCliDriver_udf_format_number_wrong7() throws Exception {
    runTest("udf_format_number_wrong7", "udf_format_number_wrong7.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_format_number_wrong7.q");
  }

  public void testNegativeCliDriver_udf_function_does_not_implement_udf() throws Exception {
    runTest("udf_function_does_not_implement_udf", "udf_function_does_not_implement_udf.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_function_does_not_implement_udf.q");
  }

  public void testNegativeCliDriver_udf_if_not_bool() throws Exception {
    runTest("udf_if_not_bool", "udf_if_not_bool.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_if_not_bool.q");
  }

  public void testNegativeCliDriver_udf_if_wrong_args_len() throws Exception {
    runTest("udf_if_wrong_args_len", "udf_if_wrong_args_len.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_if_wrong_args_len.q");
  }

  public void testNegativeCliDriver_udf_in() throws Exception {
    runTest("udf_in", "udf_in.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_in.q");
  }

  public void testNegativeCliDriver_udf_instr_wrong_args_len() throws Exception {
    runTest("udf_instr_wrong_args_len", "udf_instr_wrong_args_len.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_instr_wrong_args_len.q");
  }

  public void testNegativeCliDriver_udf_instr_wrong_type() throws Exception {
    runTest("udf_instr_wrong_type", "udf_instr_wrong_type.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_instr_wrong_type.q");
  }

  public void testNegativeCliDriver_udf_locate_wrong_args_len() throws Exception {
    runTest("udf_locate_wrong_args_len", "udf_locate_wrong_args_len.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_locate_wrong_args_len.q");
  }

  public void testNegativeCliDriver_udf_locate_wrong_type() throws Exception {
    runTest("udf_locate_wrong_type", "udf_locate_wrong_type.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_locate_wrong_type.q");
  }

  public void testNegativeCliDriver_udf_map_keys_arg_num() throws Exception {
    runTest("udf_map_keys_arg_num", "udf_map_keys_arg_num.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_map_keys_arg_num.q");
  }

  public void testNegativeCliDriver_udf_map_keys_arg_type() throws Exception {
    runTest("udf_map_keys_arg_type", "udf_map_keys_arg_type.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_map_keys_arg_type.q");
  }

  public void testNegativeCliDriver_udf_map_values_arg_num() throws Exception {
    runTest("udf_map_values_arg_num", "udf_map_values_arg_num.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_map_values_arg_num.q");
  }

  public void testNegativeCliDriver_udf_map_values_arg_type() throws Exception {
    runTest("udf_map_values_arg_type", "udf_map_values_arg_type.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_map_values_arg_type.q");
  }

  public void testNegativeCliDriver_udf_max() throws Exception {
    runTest("udf_max", "udf_max.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_max.q");
  }

  public void testNegativeCliDriver_udf_min() throws Exception {
    runTest("udf_min", "udf_min.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_min.q");
  }

  public void testNegativeCliDriver_udf_printf_wrong1() throws Exception {
    runTest("udf_printf_wrong1", "udf_printf_wrong1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_printf_wrong1.q");
  }

  public void testNegativeCliDriver_udf_printf_wrong2() throws Exception {
    runTest("udf_printf_wrong2", "udf_printf_wrong2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_printf_wrong2.q");
  }

  public void testNegativeCliDriver_udf_printf_wrong3() throws Exception {
    runTest("udf_printf_wrong3", "udf_printf_wrong3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_printf_wrong3.q");
  }

  public void testNegativeCliDriver_udf_printf_wrong4() throws Exception {
    runTest("udf_printf_wrong4", "udf_printf_wrong4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_printf_wrong4.q");
  }

  public void testNegativeCliDriver_udf_reflect_neg() throws Exception {
    runTest("udf_reflect_neg", "udf_reflect_neg.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_reflect_neg.q");
  }

  public void testNegativeCliDriver_udf_size_wrong_args_len() throws Exception {
    runTest("udf_size_wrong_args_len", "udf_size_wrong_args_len.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_size_wrong_args_len.q");
  }

  public void testNegativeCliDriver_udf_size_wrong_type() throws Exception {
    runTest("udf_size_wrong_type", "udf_size_wrong_type.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_size_wrong_type.q");
  }

  public void testNegativeCliDriver_udf_sort_array_wrong1() throws Exception {
    runTest("udf_sort_array_wrong1", "udf_sort_array_wrong1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_sort_array_wrong1.q");
  }

  public void testNegativeCliDriver_udf_sort_array_wrong2() throws Exception {
    runTest("udf_sort_array_wrong2", "udf_sort_array_wrong2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_sort_array_wrong2.q");
  }

  public void testNegativeCliDriver_udf_sort_array_wrong3() throws Exception {
    runTest("udf_sort_array_wrong3", "udf_sort_array_wrong3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_sort_array_wrong3.q");
  }

  public void testNegativeCliDriver_udf_test_error() throws Exception {
    runTest("udf_test_error", "udf_test_error.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_test_error.q");
  }

  public void testNegativeCliDriver_udf_test_error_reduce() throws Exception {
    runTest("udf_test_error_reduce", "udf_test_error_reduce.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_test_error_reduce.q");
  }

  public void testNegativeCliDriver_udf_when_type_wrong() throws Exception {
    runTest("udf_when_type_wrong", "udf_when_type_wrong.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_when_type_wrong.q");
  }

  public void testNegativeCliDriver_udf_when_type_wrong2() throws Exception {
    runTest("udf_when_type_wrong2", "udf_when_type_wrong2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_when_type_wrong2.q");
  }

  public void testNegativeCliDriver_udf_when_type_wrong3() throws Exception {
    runTest("udf_when_type_wrong3", "udf_when_type_wrong3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udf_when_type_wrong3.q");
  }

  public void testNegativeCliDriver_udfnull() throws Exception {
    runTest("udfnull", "udfnull.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udfnull.q");
  }

  public void testNegativeCliDriver_udtf_explode_not_supported1() throws Exception {
    runTest("udtf_explode_not_supported1", "udtf_explode_not_supported1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udtf_explode_not_supported1.q");
  }

  public void testNegativeCliDriver_udtf_explode_not_supported2() throws Exception {
    runTest("udtf_explode_not_supported2", "udtf_explode_not_supported2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udtf_explode_not_supported2.q");
  }

  public void testNegativeCliDriver_udtf_explode_not_supported3() throws Exception {
    runTest("udtf_explode_not_supported3", "udtf_explode_not_supported3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udtf_explode_not_supported3.q");
  }

  public void testNegativeCliDriver_udtf_explode_not_supported4() throws Exception {
    runTest("udtf_explode_not_supported4", "udtf_explode_not_supported4.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udtf_explode_not_supported4.q");
  }

  public void testNegativeCliDriver_udtf_invalid_place() throws Exception {
    runTest("udtf_invalid_place", "udtf_invalid_place.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udtf_invalid_place.q");
  }

  public void testNegativeCliDriver_udtf_not_supported1() throws Exception {
    runTest("udtf_not_supported1", "udtf_not_supported1.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udtf_not_supported1.q");
  }

  public void testNegativeCliDriver_udtf_not_supported3() throws Exception {
    runTest("udtf_not_supported3", "udtf_not_supported3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/udtf_not_supported3.q");
  }

  public void testNegativeCliDriver_union() throws Exception {
    runTest("union", "union.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/union.q");
  }

  public void testNegativeCliDriver_union2() throws Exception {
    runTest("union2", "union2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/union2.q");
  }

  public void testNegativeCliDriver_union22() throws Exception {
    runTest("union22", "union22.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/union22.q");
  }

  public void testNegativeCliDriver_union3() throws Exception {
    runTest("union3", "union3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/union3.q");
  }

  public void testNegativeCliDriver_uniquejoin() throws Exception {
    runTest("uniquejoin", "uniquejoin.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/uniquejoin.q");
  }

  public void testNegativeCliDriver_uniquejoin2() throws Exception {
    runTest("uniquejoin2", "uniquejoin2.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/uniquejoin2.q");
  }

  public void testNegativeCliDriver_uniquejoin3() throws Exception {
    runTest("uniquejoin3", "uniquejoin3.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/uniquejoin3.q");
  }

  public void testNegativeCliDriver_unset_table_property() throws Exception {
    runTest("unset_table_property", "unset_table_property.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/unset_table_property.q");
  }

  public void testNegativeCliDriver_unset_view_property() throws Exception {
    runTest("unset_view_property", "unset_view_property.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/unset_view_property.q");
  }

  public void testNegativeCliDriver_windowing_leadlag_in_udaf() throws Exception {
    runTest("windowing_leadlag_in_udaf", "windowing_leadlag_in_udaf.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/windowing_leadlag_in_udaf.q");
  }

  public void testNegativeCliDriver_windowing_ll_no_over() throws Exception {
    runTest("windowing_ll_no_over", "windowing_ll_no_over.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/windowing_ll_no_over.q");
  }

  public void testNegativeCliDriver_wrong_column_type() throws Exception {
    runTest("wrong_column_type", "wrong_column_type.q", "/home/kangyanli/hive-0.12.0/src/ql/src/test/queries/clientnegative/wrong_column_type.q");
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

      qt.cliInit(fname, false);
      int ecode = qt.executeClient(fname);
      if (ecode == 0) {
        fail("Client Execution was expected to fail, but succeeded with error code 0 "
            + debugHint);
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
