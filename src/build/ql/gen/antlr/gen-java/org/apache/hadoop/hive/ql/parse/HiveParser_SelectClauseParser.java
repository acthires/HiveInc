// $ANTLR 3.4 SelectClauseParser.g 2015-12-26 22:51:51

package org.apache.hadoop.hive.ql.parse;

import java.util.Collection;
import java.util.HashMap;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


/**
   Licensed to the Apache Software Foundation (ASF) under one or more 
   contributor license agreements.  See the NOTICE file distributed with 
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with 
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
@SuppressWarnings({"all", "warnings", "unchecked"})
public class HiveParser_SelectClauseParser extends Parser {
    public static final int EOF=-1;
    public static final int AMPERSAND=4;
    public static final int BITWISEOR=5;
    public static final int BITWISEXOR=6;
    public static final int BigintLiteral=7;
    public static final int ByteLengthLiteral=8;
    public static final int COLON=9;
    public static final int COMMA=10;
    public static final int COMMENT=11;
    public static final int CharSetLiteral=12;
    public static final int CharSetName=13;
    public static final int DIV=14;
    public static final int DIVIDE=15;
    public static final int DOLLAR=16;
    public static final int DOT=17;
    public static final int DecimalLiteral=18;
    public static final int Digit=19;
    public static final int EQUAL=20;
    public static final int EQUAL_NS=21;
    public static final int Exponent=22;
    public static final int GREATERTHAN=23;
    public static final int GREATERTHANOREQUALTO=24;
    public static final int HexDigit=25;
    public static final int Identifier=26;
    public static final int KW_ADD=27;
    public static final int KW_AFTER=28;
    public static final int KW_ALL=29;
    public static final int KW_ALTER=30;
    public static final int KW_ANALYZE=31;
    public static final int KW_AND=32;
    public static final int KW_ARCHIVE=33;
    public static final int KW_ARRAY=34;
    public static final int KW_AS=35;
    public static final int KW_ASC=36;
    public static final int KW_BEFORE=37;
    public static final int KW_BETWEEN=38;
    public static final int KW_BIGINT=39;
    public static final int KW_BINARY=40;
    public static final int KW_BOOLEAN=41;
    public static final int KW_BOTH=42;
    public static final int KW_BUCKET=43;
    public static final int KW_BUCKETS=44;
    public static final int KW_BY=45;
    public static final int KW_CASCADE=46;
    public static final int KW_CASE=47;
    public static final int KW_CAST=48;
    public static final int KW_CHANGE=49;
    public static final int KW_CLUSTER=50;
    public static final int KW_CLUSTERED=51;
    public static final int KW_CLUSTERSTATUS=52;
    public static final int KW_COLLECTION=53;
    public static final int KW_COLUMN=54;
    public static final int KW_COLUMNS=55;
    public static final int KW_COMMENT=56;
    public static final int KW_COMPUTE=57;
    public static final int KW_CONCATENATE=58;
    public static final int KW_CONSTANT=59;
    public static final int KW_CONTINUE=60;
    public static final int KW_CREATE=61;
    public static final int KW_CROSS=62;
    public static final int KW_CUBE=63;
    public static final int KW_CURRENT=64;
    public static final int KW_CURSOR=65;
    public static final int KW_DATA=66;
    public static final int KW_DATABASE=67;
    public static final int KW_DATABASES=68;
    public static final int KW_DATE=69;
    public static final int KW_DATETIME=70;
    public static final int KW_DBPROPERTIES=71;
    public static final int KW_DECIMAL=72;
    public static final int KW_DEFERRED=73;
    public static final int KW_DELETE=74;
    public static final int KW_DELIMITED=75;
    public static final int KW_DEPENDENCY=76;
    public static final int KW_DESC=77;
    public static final int KW_DESCRIBE=78;
    public static final int KW_DIRECTORIES=79;
    public static final int KW_DIRECTORY=80;
    public static final int KW_DISABLE=81;
    public static final int KW_DISTINCT=82;
    public static final int KW_DISTRIBUTE=83;
    public static final int KW_DOUBLE=84;
    public static final int KW_DROP=85;
    public static final int KW_ELEM_TYPE=86;
    public static final int KW_ELSE=87;
    public static final int KW_ENABLE=88;
    public static final int KW_END=89;
    public static final int KW_ESCAPED=90;
    public static final int KW_EXCHANGE=91;
    public static final int KW_EXCLUSIVE=92;
    public static final int KW_EXISTS=93;
    public static final int KW_EXPLAIN=94;
    public static final int KW_EXPORT=95;
    public static final int KW_EXTENDED=96;
    public static final int KW_EXTERNAL=97;
    public static final int KW_FALSE=98;
    public static final int KW_FETCH=99;
    public static final int KW_FIELDS=100;
    public static final int KW_FILEFORMAT=101;
    public static final int KW_FIRST=102;
    public static final int KW_FLOAT=103;
    public static final int KW_FOLLOWING=104;
    public static final int KW_FOR=105;
    public static final int KW_FORMAT=106;
    public static final int KW_FORMATTED=107;
    public static final int KW_FROM=108;
    public static final int KW_FULL=109;
    public static final int KW_FUNCTION=110;
    public static final int KW_FUNCTIONS=111;
    public static final int KW_GRANT=112;
    public static final int KW_GROUP=113;
    public static final int KW_GROUPING=114;
    public static final int KW_HAVING=115;
    public static final int KW_HOLD_DDLTIME=116;
    public static final int KW_IDXPROPERTIES=117;
    public static final int KW_IF=118;
    public static final int KW_IGNORE=119;
    public static final int KW_IMPORT=120;
    public static final int KW_IN=121;
    public static final int KW_INCRE=122;
    public static final int KW_INDEX=123;
    public static final int KW_INDEXES=124;
    public static final int KW_INNER=125;
    public static final int KW_INPATH=126;
    public static final int KW_INPUTDRIVER=127;
    public static final int KW_INPUTFORMAT=128;
    public static final int KW_INSERT=129;
    public static final int KW_INT=130;
    public static final int KW_INTERSECT=131;
    public static final int KW_INTERVAL=132;
    public static final int KW_INTO=133;
    public static final int KW_IS=134;
    public static final int KW_ITEMS=135;
    public static final int KW_JOIN=136;
    public static final int KW_KEYS=137;
    public static final int KW_KEY_TYPE=138;
    public static final int KW_LATERAL=139;
    public static final int KW_LEFT=140;
    public static final int KW_LESS=141;
    public static final int KW_LIKE=142;
    public static final int KW_LIMIT=143;
    public static final int KW_LINES=144;
    public static final int KW_LOAD=145;
    public static final int KW_LOCAL=146;
    public static final int KW_LOCATION=147;
    public static final int KW_LOCK=148;
    public static final int KW_LOCKS=149;
    public static final int KW_LOGICAL=150;
    public static final int KW_LONG=151;
    public static final int KW_MACRO=152;
    public static final int KW_MAP=153;
    public static final int KW_MAPJOIN=154;
    public static final int KW_MATERIALIZED=155;
    public static final int KW_MINUS=156;
    public static final int KW_MORE=157;
    public static final int KW_MSCK=158;
    public static final int KW_NOSCAN=159;
    public static final int KW_NOT=160;
    public static final int KW_NO_DROP=161;
    public static final int KW_NULL=162;
    public static final int KW_OF=163;
    public static final int KW_OFFLINE=164;
    public static final int KW_ON=165;
    public static final int KW_OPTION=166;
    public static final int KW_OR=167;
    public static final int KW_ORCFILE=168;
    public static final int KW_ORDER=169;
    public static final int KW_OUT=170;
    public static final int KW_OUTER=171;
    public static final int KW_OUTPUTDRIVER=172;
    public static final int KW_OUTPUTFORMAT=173;
    public static final int KW_OVER=174;
    public static final int KW_OVERWRITE=175;
    public static final int KW_PARTIALSCAN=176;
    public static final int KW_PARTITION=177;
    public static final int KW_PARTITIONED=178;
    public static final int KW_PARTITIONS=179;
    public static final int KW_PERCENT=180;
    public static final int KW_PLUS=181;
    public static final int KW_PRECEDING=182;
    public static final int KW_PRESERVE=183;
    public static final int KW_PRETTY=184;
    public static final int KW_PROCEDURE=185;
    public static final int KW_PROTECTION=186;
    public static final int KW_PURGE=187;
    public static final int KW_RANGE=188;
    public static final int KW_RCFILE=189;
    public static final int KW_READ=190;
    public static final int KW_READONLY=191;
    public static final int KW_READS=192;
    public static final int KW_REBUILD=193;
    public static final int KW_RECORDREADER=194;
    public static final int KW_RECORDWRITER=195;
    public static final int KW_REDUCE=196;
    public static final int KW_REGEXP=197;
    public static final int KW_RENAME=198;
    public static final int KW_REPAIR=199;
    public static final int KW_REPLACE=200;
    public static final int KW_RESTRICT=201;
    public static final int KW_REVOKE=202;
    public static final int KW_RIGHT=203;
    public static final int KW_RLIKE=204;
    public static final int KW_ROLE=205;
    public static final int KW_ROLLUP=206;
    public static final int KW_ROW=207;
    public static final int KW_ROWS=208;
    public static final int KW_SCHEMA=209;
    public static final int KW_SCHEMAS=210;
    public static final int KW_SELECT=211;
    public static final int KW_SEMI=212;
    public static final int KW_SEQUENCEFILE=213;
    public static final int KW_SERDE=214;
    public static final int KW_SERDEPROPERTIES=215;
    public static final int KW_SET=216;
    public static final int KW_SETS=217;
    public static final int KW_SHARED=218;
    public static final int KW_SHOW=219;
    public static final int KW_SHOW_DATABASE=220;
    public static final int KW_SKEWED=221;
    public static final int KW_SMALLINT=222;
    public static final int KW_SORT=223;
    public static final int KW_SORTED=224;
    public static final int KW_SSL=225;
    public static final int KW_STATISTICS=226;
    public static final int KW_STORED=227;
    public static final int KW_STREAMTABLE=228;
    public static final int KW_STRING=229;
    public static final int KW_STRUCT=230;
    public static final int KW_TABLE=231;
    public static final int KW_TABLES=232;
    public static final int KW_TABLESAMPLE=233;
    public static final int KW_TBLPROPERTIES=234;
    public static final int KW_TEMPORARY=235;
    public static final int KW_TERMINATED=236;
    public static final int KW_TEXTFILE=237;
    public static final int KW_THEN=238;
    public static final int KW_TIMESTAMP=239;
    public static final int KW_TINYINT=240;
    public static final int KW_TO=241;
    public static final int KW_TOUCH=242;
    public static final int KW_TRANSFORM=243;
    public static final int KW_TRIGGER=244;
    public static final int KW_TRUE=245;
    public static final int KW_TRUNCATE=246;
    public static final int KW_UNARCHIVE=247;
    public static final int KW_UNBOUNDED=248;
    public static final int KW_UNDO=249;
    public static final int KW_UNION=250;
    public static final int KW_UNIONTYPE=251;
    public static final int KW_UNIQUEJOIN=252;
    public static final int KW_UNLOCK=253;
    public static final int KW_UNSET=254;
    public static final int KW_UNSIGNED=255;
    public static final int KW_UPDATE=256;
    public static final int KW_USE=257;
    public static final int KW_USER=258;
    public static final int KW_USING=259;
    public static final int KW_UTC=260;
    public static final int KW_UTCTIMESTAMP=261;
    public static final int KW_VALUE_TYPE=262;
    public static final int KW_VARCHAR=263;
    public static final int KW_VIEW=264;
    public static final int KW_WHEN=265;
    public static final int KW_WHERE=266;
    public static final int KW_WHILE=267;
    public static final int KW_WINDOW=268;
    public static final int KW_WITH=269;
    public static final int LCURLY=270;
    public static final int LESSTHAN=271;
    public static final int LESSTHANOREQUALTO=272;
    public static final int LPAREN=273;
    public static final int LSQUARE=274;
    public static final int Letter=275;
    public static final int MINUS=276;
    public static final int MOD=277;
    public static final int NOTEQUAL=278;
    public static final int Number=279;
    public static final int PLUS=280;
    public static final int QUESTION=281;
    public static final int RCURLY=282;
    public static final int RPAREN=283;
    public static final int RSQUARE=284;
    public static final int RegexComponent=285;
    public static final int SEMICOLON=286;
    public static final int STAR=287;
    public static final int SmallintLiteral=288;
    public static final int StringLiteral=289;
    public static final int TILDE=290;
    public static final int TimeUnit=291;
    public static final int TinyintLiteral=292;
    public static final int WS=293;
    public static final int INT=311;
    public static final int KW_=313;
    public static final int KW_DAY=356;
    public static final int KW_HOUR=400;
    public static final int KW_MINUTE=439;
    public static final int KW_MONTH=440;
    public static final int KW_WEEK=546;
    public static final int SECOND=563;
    public static final int TOK_ALIASLIST=568;
    public static final int TOK_ALLCOLREF=569;
    public static final int TOK_ALTERDATABASE_PROPERTIES=570;
    public static final int TOK_ALTERINDEX_PROPERTIES=571;
    public static final int TOK_ALTERINDEX_REBUILD=572;
    public static final int TOK_ALTERTABLE_ADDCOLS=573;
    public static final int TOK_ALTERTABLE_ADDPARTS=574;
    public static final int TOK_ALTERTABLE_ALTERPARTS=575;
    public static final int TOK_ALTERTABLE_ALTERPARTS_MERGEFILES=576;
    public static final int TOK_ALTERTABLE_ALTERPARTS_PROTECTMODE=577;
    public static final int TOK_ALTERTABLE_ARCHIVE=578;
    public static final int TOK_ALTERTABLE_CHANGECOL_AFTER_POSITION=579;
    public static final int TOK_ALTERTABLE_CLUSTER_SORT=580;
    public static final int TOK_ALTERTABLE_DROPPARTS=581;
    public static final int TOK_ALTERTABLE_FILEFORMAT=582;
    public static final int TOK_ALTERTABLE_LOCATION=583;
    public static final int TOK_ALTERTABLE_PARTITION=584;
    public static final int TOK_ALTERTABLE_PROPERTIES=585;
    public static final int TOK_ALTERTABLE_RENAME=586;
    public static final int TOK_ALTERTABLE_RENAMECOL=587;
    public static final int TOK_ALTERTABLE_RENAMEPART=588;
    public static final int TOK_ALTERTABLE_REPLACECOLS=589;
    public static final int TOK_ALTERTABLE_SERDEPROPERTIES=590;
    public static final int TOK_ALTERTABLE_SERIALIZER=591;
    public static final int TOK_ALTERTABLE_SKEWED=592;
    public static final int TOK_ALTERTABLE_TOUCH=593;
    public static final int TOK_ALTERTABLE_UNARCHIVE=594;
    public static final int TOK_ALTERTBLPART_SKEWED_LOCATION=595;
    public static final int TOK_ALTERVIEW_ADDPARTS=596;
    public static final int TOK_ALTERVIEW_AS=597;
    public static final int TOK_ALTERVIEW_DROPPARTS=598;
    public static final int TOK_ALTERVIEW_PROPERTIES=599;
    public static final int TOK_ALTERVIEW_RENAME=600;
    public static final int TOK_ANALYZE=601;
    public static final int TOK_BIGINT=602;
    public static final int TOK_BINARY=603;
    public static final int TOK_BOOLEAN=604;
    public static final int TOK_CASCADE=605;
    public static final int TOK_CHARSETLITERAL=606;
    public static final int TOK_CLUSTERBY=607;
    public static final int TOK_COLTYPELIST=608;
    public static final int TOK_CONSTANT=609;
    public static final int TOK_CREATEDATABASE=610;
    public static final int TOK_CREATEFUNCTION=611;
    public static final int TOK_CREATEINDEX=612;
    public static final int TOK_CREATEINDEX_INDEXTBLNAME=613;
    public static final int TOK_CREATEMACRO=614;
    public static final int TOK_CREATEROLE=615;
    public static final int TOK_CREATETABLE=616;
    public static final int TOK_CREATEVIEW=617;
    public static final int TOK_CROSSJOIN=618;
    public static final int TOK_CUBE_GROUPBY=619;
    public static final int TOK_DATABASECOMMENT=620;
    public static final int TOK_DATABASELOCATION=621;
    public static final int TOK_DATABASEPROPERTIES=622;
    public static final int TOK_DATE=623;
    public static final int TOK_DATELITERAL=624;
    public static final int TOK_DATETIME=625;
    public static final int TOK_DBPROPLIST=626;
    public static final int TOK_DECIMAL=627;
    public static final int TOK_DEFERRED_REBUILDINDEX=628;
    public static final int TOK_DESCDATABASE=629;
    public static final int TOK_DESCFUNCTION=630;
    public static final int TOK_DESCTABLE=631;
    public static final int TOK_DESTINATION=632;
    public static final int TOK_DIR=633;
    public static final int TOK_DISABLE=634;
    public static final int TOK_DISTRIBUTEBY=635;
    public static final int TOK_DOUBLE=636;
    public static final int TOK_DROPDATABASE=637;
    public static final int TOK_DROPFUNCTION=638;
    public static final int TOK_DROPINDEX=639;
    public static final int TOK_DROPMACRO=640;
    public static final int TOK_DROPROLE=641;
    public static final int TOK_DROPTABLE=642;
    public static final int TOK_DROPTABLE_PROPERTIES=643;
    public static final int TOK_DROPVIEW=644;
    public static final int TOK_DROPVIEW_PROPERTIES=645;
    public static final int TOK_ENABLE=646;
    public static final int TOK_EXCHANGEPARTITION=647;
    public static final int TOK_EXPLAIN=648;
    public static final int TOK_EXPLIST=649;
    public static final int TOK_EXPORT=650;
    public static final int TOK_FALSE=651;
    public static final int TOK_FILEFORMAT_GENERIC=652;
    public static final int TOK_FLOAT=653;
    public static final int TOK_FROM=654;
    public static final int TOK_FULLOUTERJOIN=655;
    public static final int TOK_FUNCTION=656;
    public static final int TOK_FUNCTIONDI=657;
    public static final int TOK_FUNCTIONSTAR=658;
    public static final int TOK_GRANT=659;
    public static final int TOK_GRANT_ROLE=660;
    public static final int TOK_GRANT_WITH_OPTION=661;
    public static final int TOK_GROUP=662;
    public static final int TOK_GROUPBY=663;
    public static final int TOK_GROUPING_SETS=664;
    public static final int TOK_GROUPING_SETS_EXPRESSION=665;
    public static final int TOK_HAVING=666;
    public static final int TOK_HINT=667;
    public static final int TOK_HINTARGLIST=668;
    public static final int TOK_HINTLIST=669;
    public static final int TOK_HOLD_DDLTIME=670;
    public static final int TOK_IFEXISTS=671;
    public static final int TOK_IFNOTEXISTS=672;
    public static final int TOK_IGNOREPROTECTION=673;
    public static final int TOK_IMPORT=674;
    public static final int TOK_INCRE=675;
    public static final int TOK_INDEXCOMMENT=676;
    public static final int TOK_INDEXPROPERTIES=677;
    public static final int TOK_INDEXPROPLIST=678;
    public static final int TOK_INSERT=679;
    public static final int TOK_INSERT_INTO=680;
    public static final int TOK_INT=681;
    public static final int TOK_INTERVAL=682;
    public static final int TOK_ISNOTNULL=683;
    public static final int TOK_ISNULL=684;
    public static final int TOK_JOIN=685;
    public static final int TOK_LATERAL_VIEW=686;
    public static final int TOK_LATERAL_VIEW_OUTER=687;
    public static final int TOK_LEFTOUTERJOIN=688;
    public static final int TOK_LEFTSEMIJOIN=689;
    public static final int TOK_LENGTH=690;
    public static final int TOK_LIKETABLE=691;
    public static final int TOK_LIMIT=692;
    public static final int TOK_LIST=693;
    public static final int TOK_LOAD=694;
    public static final int TOK_LOCAL_DIR=695;
    public static final int TOK_LOCKTABLE=696;
    public static final int TOK_MAP=697;
    public static final int TOK_MAPJOIN=698;
    public static final int TOK_MSCK=699;
    public static final int TOK_NOT_CLUSTERED=700;
    public static final int TOK_NOT_SORTED=701;
    public static final int TOK_NO_DROP=702;
    public static final int TOK_NULL=703;
    public static final int TOK_OFFLINE=704;
    public static final int TOK_OP_ADD=705;
    public static final int TOK_OP_AND=706;
    public static final int TOK_OP_BITAND=707;
    public static final int TOK_OP_BITNOT=708;
    public static final int TOK_OP_BITOR=709;
    public static final int TOK_OP_BITXOR=710;
    public static final int TOK_OP_DIV=711;
    public static final int TOK_OP_EQ=712;
    public static final int TOK_OP_GE=713;
    public static final int TOK_OP_GT=714;
    public static final int TOK_OP_LE=715;
    public static final int TOK_OP_LIKE=716;
    public static final int TOK_OP_LT=717;
    public static final int TOK_OP_MOD=718;
    public static final int TOK_OP_MUL=719;
    public static final int TOK_OP_NE=720;
    public static final int TOK_OP_NOT=721;
    public static final int TOK_OP_OR=722;
    public static final int TOK_OP_SUB=723;
    public static final int TOK_ORDERBY=724;
    public static final int TOK_ORREPLACE=725;
    public static final int TOK_PARTITIONINGSPEC=726;
    public static final int TOK_PARTITIONLOCATION=727;
    public static final int TOK_PARTSPEC=728;
    public static final int TOK_PARTVAL=729;
    public static final int TOK_PERCENT=730;
    public static final int TOK_PRINCIPAL_NAME=731;
    public static final int TOK_PRIVILEGE=732;
    public static final int TOK_PRIVILEGE_LIST=733;
    public static final int TOK_PRIV_ALL=734;
    public static final int TOK_PRIV_ALTER_DATA=735;
    public static final int TOK_PRIV_ALTER_METADATA=736;
    public static final int TOK_PRIV_CREATE=737;
    public static final int TOK_PRIV_DROP=738;
    public static final int TOK_PRIV_INDEX=739;
    public static final int TOK_PRIV_LOCK=740;
    public static final int TOK_PRIV_OBJECT=741;
    public static final int TOK_PRIV_OBJECT_COL=742;
    public static final int TOK_PRIV_SELECT=743;
    public static final int TOK_PRIV_SHOW_DATABASE=744;
    public static final int TOK_PTBLFUNCTION=745;
    public static final int TOK_QUERY=746;
    public static final int TOK_READONLY=747;
    public static final int TOK_RECORDREADER=748;
    public static final int TOK_RECORDWRITER=749;
    public static final int TOK_RESTRICT=750;
    public static final int TOK_REVOKE=751;
    public static final int TOK_REVOKE_ROLE=752;
    public static final int TOK_RIGHTOUTERJOIN=753;
    public static final int TOK_ROLE=754;
    public static final int TOK_ROLLUP_GROUPBY=755;
    public static final int TOK_ROWCOUNT=756;
    public static final int TOK_SELECT=757;
    public static final int TOK_SELECTDI=758;
    public static final int TOK_SELEXPR=759;
    public static final int TOK_SERDE=760;
    public static final int TOK_SERDENAME=761;
    public static final int TOK_SERDEPROPS=762;
    public static final int TOK_SHOWCOLUMNS=763;
    public static final int TOK_SHOWDATABASES=764;
    public static final int TOK_SHOWFUNCTIONS=765;
    public static final int TOK_SHOWINDEXES=766;
    public static final int TOK_SHOWLOCKS=767;
    public static final int TOK_SHOWPARTITIONS=768;
    public static final int TOK_SHOWTABLES=769;
    public static final int TOK_SHOW_CREATETABLE=770;
    public static final int TOK_SHOW_GRANT=771;
    public static final int TOK_SHOW_ROLE_GRANT=772;
    public static final int TOK_SHOW_TABLESTATUS=773;
    public static final int TOK_SHOW_TBLPROPERTIES=774;
    public static final int TOK_SKEWED_LOCATIONS=775;
    public static final int TOK_SKEWED_LOCATION_LIST=776;
    public static final int TOK_SKEWED_LOCATION_MAP=777;
    public static final int TOK_SMALLINT=778;
    public static final int TOK_SORTBY=779;
    public static final int TOK_STARTTIME=780;
    public static final int TOK_STOPTIME=781;
    public static final int TOK_STORAGEHANDLER=782;
    public static final int TOK_STOREDASDIRS=783;
    public static final int TOK_STREAMTABLE=784;
    public static final int TOK_STRING=785;
    public static final int TOK_STRINGLITERALSEQUENCE=786;
    public static final int TOK_STRUCT=787;
    public static final int TOK_SUBQUERY=788;
    public static final int TOK_SWITCHDATABASE=789;
    public static final int TOK_TAB=790;
    public static final int TOK_TABALIAS=791;
    public static final int TOK_TABCOL=792;
    public static final int TOK_TABCOLLIST=793;
    public static final int TOK_TABCOLNAME=794;
    public static final int TOK_TABCOLVALUE=795;
    public static final int TOK_TABCOLVALUES=796;
    public static final int TOK_TABCOLVALUE_PAIR=797;
    public static final int TOK_TABLEBUCKETS=798;
    public static final int TOK_TABLEBUCKETSAMPLE=799;
    public static final int TOK_TABLECOMMENT=800;
    public static final int TOK_TABLEFILEFORMAT=801;
    public static final int TOK_TABLELOCATION=802;
    public static final int TOK_TABLEPARTCOLS=803;
    public static final int TOK_TABLEPROPERTIES=804;
    public static final int TOK_TABLEPROPERTY=805;
    public static final int TOK_TABLEPROPLIST=806;
    public static final int TOK_TABLEROWFORMAT=807;
    public static final int TOK_TABLEROWFORMATCOLLITEMS=808;
    public static final int TOK_TABLEROWFORMATFIELD=809;
    public static final int TOK_TABLEROWFORMATLINES=810;
    public static final int TOK_TABLEROWFORMATMAPKEYS=811;
    public static final int TOK_TABLESERIALIZER=812;
    public static final int TOK_TABLESKEWED=813;
    public static final int TOK_TABLESPLITSAMPLE=814;
    public static final int TOK_TABLE_OR_COL=815;
    public static final int TOK_TABLE_PARTITION=816;
    public static final int TOK_TABNAME=817;
    public static final int TOK_TABREF=818;
    public static final int TOK_TABSORTCOLNAMEASC=819;
    public static final int TOK_TABSORTCOLNAMEDESC=820;
    public static final int TOK_TABSRC=821;
    public static final int TOK_TABTYPE=822;
    public static final int TOK_TBLORCFILE=823;
    public static final int TOK_TBLRCFILE=824;
    public static final int TOK_TBLSEQUENCEFILE=825;
    public static final int TOK_TBLTEXTFILE=826;
    public static final int TOK_TIME=827;
    public static final int TOK_TIMESTAMP=828;
    public static final int TOK_TINYINT=829;
    public static final int TOK_TMP_FILE=830;
    public static final int TOK_TRANSFORM=831;
    public static final int TOK_TRUE=832;
    public static final int TOK_TRUNCATETABLE=833;
    public static final int TOK_UNION=834;
    public static final int TOK_UNIONTYPE=835;
    public static final int TOK_UNIQUEJOIN=836;
    public static final int TOK_UNLOCKTABLE=837;
    public static final int TOK_USER=838;
    public static final int TOK_USERSCRIPTCOLNAMES=839;
    public static final int TOK_USERSCRIPTCOLSCHEMA=840;
    public static final int TOK_VARCHAR=841;
    public static final int TOK_VIEWPARTCOLS=842;
    public static final int TOK_WHERE=843;
    public static final int TOK_WINDOWDEF=844;
    public static final int TOK_WINDOWRANGE=845;
    public static final int TOK_WINDOWSPEC=846;
    public static final int TOK_WINDOWVALUES=847;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators
    public HiveParser gHiveParser;
    public HiveParser gParent;


    public HiveParser_SelectClauseParser(TokenStream input, HiveParser gHiveParser) {
        this(input, new RecognizerSharedState(), gHiveParser);
    }
    public HiveParser_SelectClauseParser(TokenStream input, RecognizerSharedState state, HiveParser gHiveParser) {
        super(input, state);
        this.gHiveParser = gHiveParser;
        gParent = gHiveParser;
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return HiveParser.tokenNames; }
    public String getGrammarFileName() { return "SelectClauseParser.g"; }


      @Override
      public Object recoverFromMismatchedSet(IntStream input,
          RecognitionException re, BitSet follow) throws RecognitionException {
        throw re;
      }
      @Override
      public void displayRecognitionError(String[] tokenNames,
          RecognitionException e) {
        gParent.errors.add(new ParseError(gParent, e, tokenNames));
      }


    public static class selectClause_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "selectClause"
    // SelectClauseParser.g:52:1: selectClause : ( KW_SELECT ( hintClause )? ( ( ( KW_ALL |dist= KW_DISTINCT )? selectList ) | (transform= KW_TRANSFORM selectTrfmClause ) ) -> {$transform == null && $dist == null}? ^( TOK_SELECT ( hintClause )? selectList ) -> {$transform == null && $dist != null}? ^( TOK_SELECTDI ( hintClause )? selectList ) -> ^( TOK_SELECT ( hintClause )? ^( TOK_SELEXPR selectTrfmClause ) ) | trfmClause -> ^( TOK_SELECT ^( TOK_SELEXPR trfmClause ) ) );
    public final HiveParser_SelectClauseParser.selectClause_return selectClause() throws RecognitionException {
        HiveParser_SelectClauseParser.selectClause_return retval = new HiveParser_SelectClauseParser.selectClause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token dist=null;
        Token transform=null;
        Token KW_SELECT1=null;
        Token KW_ALL3=null;
        HiveParser_SelectClauseParser.hintClause_return hintClause2 =null;

        HiveParser_SelectClauseParser.selectList_return selectList4 =null;

        HiveParser_SelectClauseParser.selectTrfmClause_return selectTrfmClause5 =null;

        HiveParser_SelectClauseParser.trfmClause_return trfmClause6 =null;


        CommonTree dist_tree=null;
        CommonTree transform_tree=null;
        CommonTree KW_SELECT1_tree=null;
        CommonTree KW_ALL3_tree=null;
        RewriteRuleTokenStream stream_KW_ALL=new RewriteRuleTokenStream(adaptor,"token KW_ALL");
        RewriteRuleTokenStream stream_KW_SELECT=new RewriteRuleTokenStream(adaptor,"token KW_SELECT");
        RewriteRuleTokenStream stream_KW_TRANSFORM=new RewriteRuleTokenStream(adaptor,"token KW_TRANSFORM");
        RewriteRuleTokenStream stream_KW_DISTINCT=new RewriteRuleTokenStream(adaptor,"token KW_DISTINCT");
        RewriteRuleSubtreeStream stream_selectList=new RewriteRuleSubtreeStream(adaptor,"rule selectList");
        RewriteRuleSubtreeStream stream_hintClause=new RewriteRuleSubtreeStream(adaptor,"rule hintClause");
        RewriteRuleSubtreeStream stream_trfmClause=new RewriteRuleSubtreeStream(adaptor,"rule trfmClause");
        RewriteRuleSubtreeStream stream_selectTrfmClause=new RewriteRuleSubtreeStream(adaptor,"rule selectTrfmClause");
         gParent.msgs.push("select clause"); 
        try {
            // SelectClauseParser.g:55:5: ( KW_SELECT ( hintClause )? ( ( ( KW_ALL |dist= KW_DISTINCT )? selectList ) | (transform= KW_TRANSFORM selectTrfmClause ) ) -> {$transform == null && $dist == null}? ^( TOK_SELECT ( hintClause )? selectList ) -> {$transform == null && $dist != null}? ^( TOK_SELECTDI ( hintClause )? selectList ) -> ^( TOK_SELECT ( hintClause )? ^( TOK_SELEXPR selectTrfmClause ) ) | trfmClause -> ^( TOK_SELECT ^( TOK_SELEXPR trfmClause ) ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==KW_SELECT) ) {
                alt4=1;
            }
            else if ( (LA4_0==KW_MAP||LA4_0==KW_REDUCE) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }
            switch (alt4) {
                case 1 :
                    // SelectClauseParser.g:56:5: KW_SELECT ( hintClause )? ( ( ( KW_ALL |dist= KW_DISTINCT )? selectList ) | (transform= KW_TRANSFORM selectTrfmClause ) )
                    {
                    KW_SELECT1=(Token)match(input,KW_SELECT,FOLLOW_KW_SELECT_in_selectClause79);  
                    stream_KW_SELECT.add(KW_SELECT1);


                    // SelectClauseParser.g:56:15: ( hintClause )?
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==DIVIDE) ) {
                        alt1=1;
                    }
                    switch (alt1) {
                        case 1 :
                            // SelectClauseParser.g:56:15: hintClause
                            {
                            pushFollow(FOLLOW_hintClause_in_selectClause81);
                            hintClause2=hintClause();

                            state._fsp--;

                            stream_hintClause.add(hintClause2.getTree());

                            }
                            break;

                    }


                    // SelectClauseParser.g:56:27: ( ( ( KW_ALL |dist= KW_DISTINCT )? selectList ) | (transform= KW_TRANSFORM selectTrfmClause ) )
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==BigintLiteral||LA3_0==CharSetName||LA3_0==DecimalLiteral||(LA3_0 >= Identifier && LA3_0 <= KW_ANALYZE)||(LA3_0 >= KW_ARCHIVE && LA3_0 <= KW_COLLECTION)||(LA3_0 >= KW_COLUMNS && LA3_0 <= KW_CONCATENATE)||(LA3_0 >= KW_CONTINUE && LA3_0 <= KW_CREATE)||LA3_0==KW_CUBE||(LA3_0 >= KW_CURSOR && LA3_0 <= KW_DATA)||(LA3_0 >= KW_DATABASES && LA3_0 <= KW_ELEM_TYPE)||LA3_0==KW_ENABLE||LA3_0==KW_ESCAPED||(LA3_0 >= KW_EXCLUSIVE && LA3_0 <= KW_EXPORT)||(LA3_0 >= KW_EXTERNAL && LA3_0 <= KW_FLOAT)||(LA3_0 >= KW_FOR && LA3_0 <= KW_FORMATTED)||LA3_0==KW_FULL||(LA3_0 >= KW_FUNCTIONS && LA3_0 <= KW_GROUPING)||(LA3_0 >= KW_HOLD_DDLTIME && LA3_0 <= KW_INTERSECT)||(LA3_0 >= KW_INTO && LA3_0 <= KW_ITEMS)||(LA3_0 >= KW_KEYS && LA3_0 <= KW_LEFT)||(LA3_0 >= KW_LIKE && LA3_0 <= KW_LONG)||(LA3_0 >= KW_MAP && LA3_0 <= KW_MINUS)||(LA3_0 >= KW_MSCK && LA3_0 <= KW_OFFLINE)||LA3_0==KW_OPTION||(LA3_0 >= KW_ORCFILE && LA3_0 <= KW_OUTPUTFORMAT)||LA3_0==KW_OVERWRITE||(LA3_0 >= KW_PARTITION && LA3_0 <= KW_PLUS)||(LA3_0 >= KW_PRETTY && LA3_0 <= KW_RECORDWRITER)||(LA3_0 >= KW_REGEXP && LA3_0 <= KW_SCHEMAS)||(LA3_0 >= KW_SEMI && LA3_0 <= KW_TABLES)||(LA3_0 >= KW_TBLPROPERTIES && LA3_0 <= KW_TEXTFILE)||(LA3_0 >= KW_TIMESTAMP && LA3_0 <= KW_TOUCH)||(LA3_0 >= KW_TRIGGER && LA3_0 <= KW_UNARCHIVE)||(LA3_0 >= KW_UNDO && LA3_0 <= KW_UNIONTYPE)||(LA3_0 >= KW_UNLOCK && LA3_0 <= KW_VALUE_TYPE)||LA3_0==KW_VIEW||LA3_0==KW_WHILE||LA3_0==KW_WITH||LA3_0==LPAREN||LA3_0==MINUS||(LA3_0 >= Number && LA3_0 <= PLUS)||(LA3_0 >= STAR && LA3_0 <= TILDE)||LA3_0==TinyintLiteral) ) {
                        alt3=1;
                    }
                    else if ( (LA3_0==KW_TRANSFORM) ) {
                        alt3=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 0, input);

                        throw nvae;

                    }
                    switch (alt3) {
                        case 1 :
                            // SelectClauseParser.g:56:28: ( ( KW_ALL |dist= KW_DISTINCT )? selectList )
                            {
                            // SelectClauseParser.g:56:28: ( ( KW_ALL |dist= KW_DISTINCT )? selectList )
                            // SelectClauseParser.g:56:29: ( KW_ALL |dist= KW_DISTINCT )? selectList
                            {
                            // SelectClauseParser.g:56:29: ( KW_ALL |dist= KW_DISTINCT )?
                            int alt2=3;
                            int LA2_0 = input.LA(1);

                            if ( (LA2_0==KW_ALL) ) {
                                alt2=1;
                            }
                            else if ( (LA2_0==KW_DISTINCT) ) {
                                alt2=2;
                            }
                            switch (alt2) {
                                case 1 :
                                    // SelectClauseParser.g:56:30: KW_ALL
                                    {
                                    KW_ALL3=(Token)match(input,KW_ALL,FOLLOW_KW_ALL_in_selectClause87);  
                                    stream_KW_ALL.add(KW_ALL3);


                                    }
                                    break;
                                case 2 :
                                    // SelectClauseParser.g:56:39: dist= KW_DISTINCT
                                    {
                                    dist=(Token)match(input,KW_DISTINCT,FOLLOW_KW_DISTINCT_in_selectClause93);  
                                    stream_KW_DISTINCT.add(dist);


                                    }
                                    break;

                            }


                            pushFollow(FOLLOW_selectList_in_selectClause97);
                            selectList4=selectList();

                            state._fsp--;

                            stream_selectList.add(selectList4.getTree());

                            }


                            }
                            break;
                        case 2 :
                            // SelectClauseParser.g:57:29: (transform= KW_TRANSFORM selectTrfmClause )
                            {
                            // SelectClauseParser.g:57:29: (transform= KW_TRANSFORM selectTrfmClause )
                            // SelectClauseParser.g:57:30: transform= KW_TRANSFORM selectTrfmClause
                            {
                            transform=(Token)match(input,KW_TRANSFORM,FOLLOW_KW_TRANSFORM_in_selectClause131);  
                            stream_KW_TRANSFORM.add(transform);


                            pushFollow(FOLLOW_selectTrfmClause_in_selectClause133);
                            selectTrfmClause5=selectTrfmClause();

                            state._fsp--;

                            stream_selectTrfmClause.add(selectTrfmClause5.getTree());

                            }


                            }
                            break;

                    }


                    // AST REWRITE
                    // elements: selectTrfmClause, selectList, hintClause, hintClause, hintClause, selectList
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 58:6: -> {$transform == null && $dist == null}? ^( TOK_SELECT ( hintClause )? selectList )
                    if (transform == null && dist == null) {
                        // SelectClauseParser.g:58:48: ^( TOK_SELECT ( hintClause )? selectList )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(TOK_SELECT, "TOK_SELECT")
                        , root_1);

                        // SelectClauseParser.g:58:61: ( hintClause )?
                        if ( stream_hintClause.hasNext() ) {
                            adaptor.addChild(root_1, stream_hintClause.nextTree());

                        }
                        stream_hintClause.reset();

                        adaptor.addChild(root_1, stream_selectList.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    else // 59:6: -> {$transform == null && $dist != null}? ^( TOK_SELECTDI ( hintClause )? selectList )
                    if (transform == null && dist != null) {
                        // SelectClauseParser.g:59:48: ^( TOK_SELECTDI ( hintClause )? selectList )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(TOK_SELECTDI, "TOK_SELECTDI")
                        , root_1);

                        // SelectClauseParser.g:59:63: ( hintClause )?
                        if ( stream_hintClause.hasNext() ) {
                            adaptor.addChild(root_1, stream_hintClause.nextTree());

                        }
                        stream_hintClause.reset();

                        adaptor.addChild(root_1, stream_selectList.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    else // 60:6: -> ^( TOK_SELECT ( hintClause )? ^( TOK_SELEXPR selectTrfmClause ) )
                    {
                        // SelectClauseParser.g:60:9: ^( TOK_SELECT ( hintClause )? ^( TOK_SELEXPR selectTrfmClause ) )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(TOK_SELECT, "TOK_SELECT")
                        , root_1);

                        // SelectClauseParser.g:60:22: ( hintClause )?
                        if ( stream_hintClause.hasNext() ) {
                            adaptor.addChild(root_1, stream_hintClause.nextTree());

                        }
                        stream_hintClause.reset();

                        // SelectClauseParser.g:60:34: ^( TOK_SELEXPR selectTrfmClause )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(TOK_SELEXPR, "TOK_SELEXPR")
                        , root_2);

                        adaptor.addChild(root_2, stream_selectTrfmClause.nextTree());

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // SelectClauseParser.g:62:5: trfmClause
                    {
                    pushFollow(FOLLOW_trfmClause_in_selectClause204);
                    trfmClause6=trfmClause();

                    state._fsp--;

                    stream_trfmClause.add(trfmClause6.getTree());

                    // AST REWRITE
                    // elements: trfmClause
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 62:17: -> ^( TOK_SELECT ^( TOK_SELEXPR trfmClause ) )
                    {
                        // SelectClauseParser.g:62:19: ^( TOK_SELECT ^( TOK_SELEXPR trfmClause ) )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(TOK_SELECT, "TOK_SELECT")
                        , root_1);

                        // SelectClauseParser.g:62:32: ^( TOK_SELEXPR trfmClause )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(TOK_SELEXPR, "TOK_SELEXPR")
                        , root_2);

                        adaptor.addChild(root_2, stream_trfmClause.nextTree());

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "selectClause"


    public static class selectList_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "selectList"
    // SelectClauseParser.g:65:1: selectList : selectItem ( COMMA selectItem )* -> ( selectItem )+ ;
    public final HiveParser_SelectClauseParser.selectList_return selectList() throws RecognitionException {
        HiveParser_SelectClauseParser.selectList_return retval = new HiveParser_SelectClauseParser.selectList_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token COMMA8=null;
        HiveParser_SelectClauseParser.selectItem_return selectItem7 =null;

        HiveParser_SelectClauseParser.selectItem_return selectItem9 =null;


        CommonTree COMMA8_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_selectItem=new RewriteRuleSubtreeStream(adaptor,"rule selectItem");
         gParent.msgs.push("select list"); 
        try {
            // SelectClauseParser.g:68:5: ( selectItem ( COMMA selectItem )* -> ( selectItem )+ )
            // SelectClauseParser.g:69:5: selectItem ( COMMA selectItem )*
            {
            pushFollow(FOLLOW_selectItem_in_selectList247);
            selectItem7=selectItem();

            state._fsp--;

            stream_selectItem.add(selectItem7.getTree());

            // SelectClauseParser.g:69:16: ( COMMA selectItem )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==COMMA) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // SelectClauseParser.g:69:18: COMMA selectItem
            	    {
            	    COMMA8=(Token)match(input,COMMA,FOLLOW_COMMA_in_selectList251);  
            	    stream_COMMA.add(COMMA8);


            	    pushFollow(FOLLOW_selectItem_in_selectList254);
            	    selectItem9=selectItem();

            	    state._fsp--;

            	    stream_selectItem.add(selectItem9.getTree());

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            // AST REWRITE
            // elements: selectItem
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 69:39: -> ( selectItem )+
            {
                if ( !(stream_selectItem.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_selectItem.hasNext() ) {
                    adaptor.addChild(root_0, stream_selectItem.nextTree());

                }
                stream_selectItem.reset();

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "selectList"


    public static class selectTrfmClause_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "selectTrfmClause"
    // SelectClauseParser.g:72:1: selectTrfmClause : LPAREN selectExpressionList RPAREN inSerde= rowFormat inRec= recordWriter KW_USING StringLiteral ( KW_AS ( ( LPAREN ( aliasList | columnNameTypeList ) RPAREN ) | ( aliasList | columnNameTypeList ) ) )? outSerde= rowFormat outRec= recordReader -> ^( TOK_TRANSFORM selectExpressionList $inSerde $inRec StringLiteral $outSerde $outRec ( aliasList )? ( columnNameTypeList )? ) ;
    public final HiveParser_SelectClauseParser.selectTrfmClause_return selectTrfmClause() throws RecognitionException {
        HiveParser_SelectClauseParser.selectTrfmClause_return retval = new HiveParser_SelectClauseParser.selectTrfmClause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token LPAREN10=null;
        Token RPAREN12=null;
        Token KW_USING13=null;
        Token StringLiteral14=null;
        Token KW_AS15=null;
        Token LPAREN16=null;
        Token RPAREN19=null;
        HiveParser.rowFormat_return inSerde =null;

        HiveParser.recordWriter_return inRec =null;

        HiveParser.rowFormat_return outSerde =null;

        HiveParser.recordReader_return outRec =null;

        HiveParser_SelectClauseParser.selectExpressionList_return selectExpressionList11 =null;

        HiveParser_FromClauseParser.aliasList_return aliasList17 =null;

        HiveParser.columnNameTypeList_return columnNameTypeList18 =null;

        HiveParser_FromClauseParser.aliasList_return aliasList20 =null;

        HiveParser.columnNameTypeList_return columnNameTypeList21 =null;


        CommonTree LPAREN10_tree=null;
        CommonTree RPAREN12_tree=null;
        CommonTree KW_USING13_tree=null;
        CommonTree StringLiteral14_tree=null;
        CommonTree KW_AS15_tree=null;
        CommonTree LPAREN16_tree=null;
        CommonTree RPAREN19_tree=null;
        RewriteRuleTokenStream stream_StringLiteral=new RewriteRuleTokenStream(adaptor,"token StringLiteral");
        RewriteRuleTokenStream stream_KW_AS=new RewriteRuleTokenStream(adaptor,"token KW_AS");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_KW_USING=new RewriteRuleTokenStream(adaptor,"token KW_USING");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_rowFormat=new RewriteRuleSubtreeStream(adaptor,"rule rowFormat");
        RewriteRuleSubtreeStream stream_columnNameTypeList=new RewriteRuleSubtreeStream(adaptor,"rule columnNameTypeList");
        RewriteRuleSubtreeStream stream_recordReader=new RewriteRuleSubtreeStream(adaptor,"rule recordReader");
        RewriteRuleSubtreeStream stream_aliasList=new RewriteRuleSubtreeStream(adaptor,"rule aliasList");
        RewriteRuleSubtreeStream stream_selectExpressionList=new RewriteRuleSubtreeStream(adaptor,"rule selectExpressionList");
        RewriteRuleSubtreeStream stream_recordWriter=new RewriteRuleSubtreeStream(adaptor,"rule recordWriter");
         gParent.msgs.push("transform clause"); 
        try {
            // SelectClauseParser.g:75:5: ( LPAREN selectExpressionList RPAREN inSerde= rowFormat inRec= recordWriter KW_USING StringLiteral ( KW_AS ( ( LPAREN ( aliasList | columnNameTypeList ) RPAREN ) | ( aliasList | columnNameTypeList ) ) )? outSerde= rowFormat outRec= recordReader -> ^( TOK_TRANSFORM selectExpressionList $inSerde $inRec StringLiteral $outSerde $outRec ( aliasList )? ( columnNameTypeList )? ) )
            // SelectClauseParser.g:76:5: LPAREN selectExpressionList RPAREN inSerde= rowFormat inRec= recordWriter KW_USING StringLiteral ( KW_AS ( ( LPAREN ( aliasList | columnNameTypeList ) RPAREN ) | ( aliasList | columnNameTypeList ) ) )? outSerde= rowFormat outRec= recordReader
            {
            LPAREN10=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_selectTrfmClause293);  
            stream_LPAREN.add(LPAREN10);


            pushFollow(FOLLOW_selectExpressionList_in_selectTrfmClause295);
            selectExpressionList11=selectExpressionList();

            state._fsp--;

            stream_selectExpressionList.add(selectExpressionList11.getTree());

            RPAREN12=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_selectTrfmClause297);  
            stream_RPAREN.add(RPAREN12);


            pushFollow(FOLLOW_rowFormat_in_selectTrfmClause305);
            inSerde=gHiveParser.rowFormat();

            state._fsp--;

            stream_rowFormat.add(inSerde.getTree());

            pushFollow(FOLLOW_recordWriter_in_selectTrfmClause309);
            inRec=gHiveParser.recordWriter();

            state._fsp--;

            stream_recordWriter.add(inRec.getTree());

            KW_USING13=(Token)match(input,KW_USING,FOLLOW_KW_USING_in_selectTrfmClause315);  
            stream_KW_USING.add(KW_USING13);


            StringLiteral14=(Token)match(input,StringLiteral,FOLLOW_StringLiteral_in_selectTrfmClause317);  
            stream_StringLiteral.add(StringLiteral14);


            // SelectClauseParser.g:79:5: ( KW_AS ( ( LPAREN ( aliasList | columnNameTypeList ) RPAREN ) | ( aliasList | columnNameTypeList ) ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==KW_AS) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // SelectClauseParser.g:79:7: KW_AS ( ( LPAREN ( aliasList | columnNameTypeList ) RPAREN ) | ( aliasList | columnNameTypeList ) )
                    {
                    KW_AS15=(Token)match(input,KW_AS,FOLLOW_KW_AS_in_selectTrfmClause325);  
                    stream_KW_AS.add(KW_AS15);


                    // SelectClauseParser.g:79:13: ( ( LPAREN ( aliasList | columnNameTypeList ) RPAREN ) | ( aliasList | columnNameTypeList ) )
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==LPAREN) ) {
                        alt8=1;
                    }
                    else if ( ((LA8_0 >= Identifier && LA8_0 <= KW_AFTER)||(LA8_0 >= KW_ALTER && LA8_0 <= KW_ANALYZE)||(LA8_0 >= KW_ARCHIVE && LA8_0 <= KW_CASCADE)||(LA8_0 >= KW_CHANGE && LA8_0 <= KW_COLLECTION)||(LA8_0 >= KW_COLUMNS && LA8_0 <= KW_CONCATENATE)||(LA8_0 >= KW_CONTINUE && LA8_0 <= KW_CREATE)||LA8_0==KW_CUBE||(LA8_0 >= KW_CURSOR && LA8_0 <= KW_DATA)||(LA8_0 >= KW_DATABASES && LA8_0 <= KW_DISABLE)||(LA8_0 >= KW_DISTRIBUTE && LA8_0 <= KW_ELEM_TYPE)||LA8_0==KW_ENABLE||LA8_0==KW_ESCAPED||(LA8_0 >= KW_EXCLUSIVE && LA8_0 <= KW_EXPORT)||(LA8_0 >= KW_EXTERNAL && LA8_0 <= KW_FLOAT)||(LA8_0 >= KW_FOR && LA8_0 <= KW_FORMATTED)||LA8_0==KW_FULL||(LA8_0 >= KW_FUNCTIONS && LA8_0 <= KW_GROUPING)||(LA8_0 >= KW_HOLD_DDLTIME && LA8_0 <= KW_IDXPROPERTIES)||(LA8_0 >= KW_IGNORE && LA8_0 <= KW_INTERSECT)||(LA8_0 >= KW_INTO && LA8_0 <= KW_ITEMS)||(LA8_0 >= KW_KEYS && LA8_0 <= KW_LEFT)||(LA8_0 >= KW_LIKE && LA8_0 <= KW_LONG)||(LA8_0 >= KW_MAPJOIN && LA8_0 <= KW_MINUS)||(LA8_0 >= KW_MSCK && LA8_0 <= KW_NOSCAN)||(LA8_0 >= KW_NO_DROP && LA8_0 <= KW_OFFLINE)||LA8_0==KW_OPTION||(LA8_0 >= KW_ORCFILE && LA8_0 <= KW_OUTPUTFORMAT)||LA8_0==KW_OVERWRITE||(LA8_0 >= KW_PARTITION && LA8_0 <= KW_PLUS)||(LA8_0 >= KW_PRETTY && LA8_0 <= KW_RECORDWRITER)||(LA8_0 >= KW_REGEXP && LA8_0 <= KW_SCHEMAS)||(LA8_0 >= KW_SEMI && LA8_0 <= KW_TABLES)||(LA8_0 >= KW_TBLPROPERTIES && LA8_0 <= KW_TEXTFILE)||(LA8_0 >= KW_TIMESTAMP && LA8_0 <= KW_TOUCH)||(LA8_0 >= KW_TRIGGER && LA8_0 <= KW_UNARCHIVE)||(LA8_0 >= KW_UNDO && LA8_0 <= KW_UNIONTYPE)||(LA8_0 >= KW_UNLOCK && LA8_0 <= KW_VALUE_TYPE)||LA8_0==KW_VIEW||LA8_0==KW_WHILE||LA8_0==KW_WITH) ) {
                        alt8=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 8, 0, input);

                        throw nvae;

                    }
                    switch (alt8) {
                        case 1 :
                            // SelectClauseParser.g:79:14: ( LPAREN ( aliasList | columnNameTypeList ) RPAREN )
                            {
                            // SelectClauseParser.g:79:14: ( LPAREN ( aliasList | columnNameTypeList ) RPAREN )
                            // SelectClauseParser.g:79:15: LPAREN ( aliasList | columnNameTypeList ) RPAREN
                            {
                            LPAREN16=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_selectTrfmClause329);  
                            stream_LPAREN.add(LPAREN16);


                            // SelectClauseParser.g:79:22: ( aliasList | columnNameTypeList )
                            int alt6=2;
                            int LA6_0 = input.LA(1);

                            if ( (LA6_0==Identifier) ) {
                                int LA6_1 = input.LA(2);

                                if ( (LA6_1==COMMA||LA6_1==RPAREN) ) {
                                    alt6=1;
                                }
                                else if ( (LA6_1==KW_ARRAY||(LA6_1 >= KW_BIGINT && LA6_1 <= KW_BOOLEAN)||(LA6_1 >= KW_DATE && LA6_1 <= KW_DATETIME)||LA6_1==KW_DECIMAL||LA6_1==KW_DOUBLE||LA6_1==KW_FLOAT||LA6_1==KW_INT||LA6_1==KW_MAP||LA6_1==KW_SMALLINT||(LA6_1 >= KW_STRING && LA6_1 <= KW_STRUCT)||(LA6_1 >= KW_TIMESTAMP && LA6_1 <= KW_TINYINT)||LA6_1==KW_UNIONTYPE||LA6_1==KW_VARCHAR) ) {
                                    alt6=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 6, 1, input);

                                    throw nvae;

                                }
                            }
                            else if ( ((LA6_0 >= KW_ADD && LA6_0 <= KW_AFTER)||(LA6_0 >= KW_ALTER && LA6_0 <= KW_ANALYZE)||(LA6_0 >= KW_ARCHIVE && LA6_0 <= KW_CASCADE)||(LA6_0 >= KW_CHANGE && LA6_0 <= KW_COLLECTION)||(LA6_0 >= KW_COLUMNS && LA6_0 <= KW_CONCATENATE)||(LA6_0 >= KW_CONTINUE && LA6_0 <= KW_CREATE)||LA6_0==KW_CUBE||(LA6_0 >= KW_CURSOR && LA6_0 <= KW_DATA)||(LA6_0 >= KW_DATABASES && LA6_0 <= KW_DISABLE)||(LA6_0 >= KW_DISTRIBUTE && LA6_0 <= KW_ELEM_TYPE)||LA6_0==KW_ENABLE||LA6_0==KW_ESCAPED||(LA6_0 >= KW_EXCLUSIVE && LA6_0 <= KW_EXPORT)||(LA6_0 >= KW_EXTERNAL && LA6_0 <= KW_FLOAT)||(LA6_0 >= KW_FOR && LA6_0 <= KW_FORMATTED)||LA6_0==KW_FULL||(LA6_0 >= KW_FUNCTIONS && LA6_0 <= KW_GROUPING)||(LA6_0 >= KW_HOLD_DDLTIME && LA6_0 <= KW_IDXPROPERTIES)||(LA6_0 >= KW_IGNORE && LA6_0 <= KW_INTERSECT)||(LA6_0 >= KW_INTO && LA6_0 <= KW_ITEMS)||(LA6_0 >= KW_KEYS && LA6_0 <= KW_LEFT)||(LA6_0 >= KW_LIKE && LA6_0 <= KW_LONG)||(LA6_0 >= KW_MAPJOIN && LA6_0 <= KW_MINUS)||(LA6_0 >= KW_MSCK && LA6_0 <= KW_NOSCAN)||(LA6_0 >= KW_NO_DROP && LA6_0 <= KW_OFFLINE)||LA6_0==KW_OPTION||(LA6_0 >= KW_ORCFILE && LA6_0 <= KW_OUTPUTFORMAT)||LA6_0==KW_OVERWRITE||(LA6_0 >= KW_PARTITION && LA6_0 <= KW_PLUS)||(LA6_0 >= KW_PRETTY && LA6_0 <= KW_RECORDWRITER)||(LA6_0 >= KW_REGEXP && LA6_0 <= KW_SCHEMAS)||(LA6_0 >= KW_SEMI && LA6_0 <= KW_TABLES)||(LA6_0 >= KW_TBLPROPERTIES && LA6_0 <= KW_TEXTFILE)||(LA6_0 >= KW_TIMESTAMP && LA6_0 <= KW_TOUCH)||(LA6_0 >= KW_TRIGGER && LA6_0 <= KW_UNARCHIVE)||(LA6_0 >= KW_UNDO && LA6_0 <= KW_UNIONTYPE)||(LA6_0 >= KW_UNLOCK && LA6_0 <= KW_VALUE_TYPE)||LA6_0==KW_VIEW||LA6_0==KW_WHILE||LA6_0==KW_WITH) ) {
                                int LA6_2 = input.LA(2);

                                if ( (LA6_2==COMMA||LA6_2==RPAREN) ) {
                                    alt6=1;
                                }
                                else if ( (LA6_2==KW_ARRAY||(LA6_2 >= KW_BIGINT && LA6_2 <= KW_BOOLEAN)||(LA6_2 >= KW_DATE && LA6_2 <= KW_DATETIME)||LA6_2==KW_DECIMAL||LA6_2==KW_DOUBLE||LA6_2==KW_FLOAT||LA6_2==KW_INT||LA6_2==KW_MAP||LA6_2==KW_SMALLINT||(LA6_2 >= KW_STRING && LA6_2 <= KW_STRUCT)||(LA6_2 >= KW_TIMESTAMP && LA6_2 <= KW_TINYINT)||LA6_2==KW_UNIONTYPE||LA6_2==KW_VARCHAR) ) {
                                    alt6=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 6, 2, input);

                                    throw nvae;

                                }
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 6, 0, input);

                                throw nvae;

                            }
                            switch (alt6) {
                                case 1 :
                                    // SelectClauseParser.g:79:23: aliasList
                                    {
                                    pushFollow(FOLLOW_aliasList_in_selectTrfmClause332);
                                    aliasList17=gHiveParser.aliasList();

                                    state._fsp--;

                                    stream_aliasList.add(aliasList17.getTree());

                                    }
                                    break;
                                case 2 :
                                    // SelectClauseParser.g:79:35: columnNameTypeList
                                    {
                                    pushFollow(FOLLOW_columnNameTypeList_in_selectTrfmClause336);
                                    columnNameTypeList18=gHiveParser.columnNameTypeList();

                                    state._fsp--;

                                    stream_columnNameTypeList.add(columnNameTypeList18.getTree());

                                    }
                                    break;

                            }


                            RPAREN19=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_selectTrfmClause339);  
                            stream_RPAREN.add(RPAREN19);


                            }


                            }
                            break;
                        case 2 :
                            // SelectClauseParser.g:79:65: ( aliasList | columnNameTypeList )
                            {
                            // SelectClauseParser.g:79:65: ( aliasList | columnNameTypeList )
                            int alt7=2;
                            alt7 = dfa7.predict(input);
                            switch (alt7) {
                                case 1 :
                                    // SelectClauseParser.g:79:66: aliasList
                                    {
                                    pushFollow(FOLLOW_aliasList_in_selectTrfmClause345);
                                    aliasList20=gHiveParser.aliasList();

                                    state._fsp--;

                                    stream_aliasList.add(aliasList20.getTree());

                                    }
                                    break;
                                case 2 :
                                    // SelectClauseParser.g:79:78: columnNameTypeList
                                    {
                                    pushFollow(FOLLOW_columnNameTypeList_in_selectTrfmClause349);
                                    columnNameTypeList21=gHiveParser.columnNameTypeList();

                                    state._fsp--;

                                    stream_columnNameTypeList.add(columnNameTypeList21.getTree());

                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    }
                    break;

            }


            pushFollow(FOLLOW_rowFormat_in_selectTrfmClause361);
            outSerde=gHiveParser.rowFormat();

            state._fsp--;

            stream_rowFormat.add(outSerde.getTree());

            pushFollow(FOLLOW_recordReader_in_selectTrfmClause365);
            outRec=gHiveParser.recordReader();

            state._fsp--;

            stream_recordReader.add(outRec.getTree());

            // AST REWRITE
            // elements: selectExpressionList, outRec, inRec, columnNameTypeList, StringLiteral, outSerde, inSerde, aliasList
            // token labels: 
            // rule labels: retval, inSerde, inRec, outRec, outSerde
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_inSerde=new RewriteRuleSubtreeStream(adaptor,"rule inSerde",inSerde!=null?inSerde.tree:null);
            RewriteRuleSubtreeStream stream_inRec=new RewriteRuleSubtreeStream(adaptor,"rule inRec",inRec!=null?inRec.tree:null);
            RewriteRuleSubtreeStream stream_outRec=new RewriteRuleSubtreeStream(adaptor,"rule outRec",outRec!=null?outRec.tree:null);
            RewriteRuleSubtreeStream stream_outSerde=new RewriteRuleSubtreeStream(adaptor,"rule outSerde",outSerde!=null?outSerde.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 81:5: -> ^( TOK_TRANSFORM selectExpressionList $inSerde $inRec StringLiteral $outSerde $outRec ( aliasList )? ( columnNameTypeList )? )
            {
                // SelectClauseParser.g:81:8: ^( TOK_TRANSFORM selectExpressionList $inSerde $inRec StringLiteral $outSerde $outRec ( aliasList )? ( columnNameTypeList )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(TOK_TRANSFORM, "TOK_TRANSFORM")
                , root_1);

                adaptor.addChild(root_1, stream_selectExpressionList.nextTree());

                adaptor.addChild(root_1, stream_inSerde.nextTree());

                adaptor.addChild(root_1, stream_inRec.nextTree());

                adaptor.addChild(root_1, 
                stream_StringLiteral.nextNode()
                );

                adaptor.addChild(root_1, stream_outSerde.nextTree());

                adaptor.addChild(root_1, stream_outRec.nextTree());

                // SelectClauseParser.g:81:93: ( aliasList )?
                if ( stream_aliasList.hasNext() ) {
                    adaptor.addChild(root_1, stream_aliasList.nextTree());

                }
                stream_aliasList.reset();

                // SelectClauseParser.g:81:104: ( columnNameTypeList )?
                if ( stream_columnNameTypeList.hasNext() ) {
                    adaptor.addChild(root_1, stream_columnNameTypeList.nextTree());

                }
                stream_columnNameTypeList.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "selectTrfmClause"


    public static class hintClause_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "hintClause"
    // SelectClauseParser.g:84:1: hintClause : DIVIDE STAR PLUS hintList STAR DIVIDE -> ^( TOK_HINTLIST hintList ) ;
    public final HiveParser_SelectClauseParser.hintClause_return hintClause() throws RecognitionException {
        HiveParser_SelectClauseParser.hintClause_return retval = new HiveParser_SelectClauseParser.hintClause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token DIVIDE22=null;
        Token STAR23=null;
        Token PLUS24=null;
        Token STAR26=null;
        Token DIVIDE27=null;
        HiveParser_SelectClauseParser.hintList_return hintList25 =null;


        CommonTree DIVIDE22_tree=null;
        CommonTree STAR23_tree=null;
        CommonTree PLUS24_tree=null;
        CommonTree STAR26_tree=null;
        CommonTree DIVIDE27_tree=null;
        RewriteRuleTokenStream stream_PLUS=new RewriteRuleTokenStream(adaptor,"token PLUS");
        RewriteRuleTokenStream stream_STAR=new RewriteRuleTokenStream(adaptor,"token STAR");
        RewriteRuleTokenStream stream_DIVIDE=new RewriteRuleTokenStream(adaptor,"token DIVIDE");
        RewriteRuleSubtreeStream stream_hintList=new RewriteRuleSubtreeStream(adaptor,"rule hintList");
         gParent.msgs.push("hint clause"); 
        try {
            // SelectClauseParser.g:87:5: ( DIVIDE STAR PLUS hintList STAR DIVIDE -> ^( TOK_HINTLIST hintList ) )
            // SelectClauseParser.g:88:5: DIVIDE STAR PLUS hintList STAR DIVIDE
            {
            DIVIDE22=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_hintClause428);  
            stream_DIVIDE.add(DIVIDE22);


            STAR23=(Token)match(input,STAR,FOLLOW_STAR_in_hintClause430);  
            stream_STAR.add(STAR23);


            PLUS24=(Token)match(input,PLUS,FOLLOW_PLUS_in_hintClause432);  
            stream_PLUS.add(PLUS24);


            pushFollow(FOLLOW_hintList_in_hintClause434);
            hintList25=hintList();

            state._fsp--;

            stream_hintList.add(hintList25.getTree());

            STAR26=(Token)match(input,STAR,FOLLOW_STAR_in_hintClause436);  
            stream_STAR.add(STAR26);


            DIVIDE27=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_hintClause438);  
            stream_DIVIDE.add(DIVIDE27);


            // AST REWRITE
            // elements: hintList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 88:43: -> ^( TOK_HINTLIST hintList )
            {
                // SelectClauseParser.g:88:46: ^( TOK_HINTLIST hintList )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(TOK_HINTLIST, "TOK_HINTLIST")
                , root_1);

                adaptor.addChild(root_1, stream_hintList.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "hintClause"


    public static class hintList_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "hintList"
    // SelectClauseParser.g:91:1: hintList : hintItem ( COMMA hintItem )* -> ( hintItem )+ ;
    public final HiveParser_SelectClauseParser.hintList_return hintList() throws RecognitionException {
        HiveParser_SelectClauseParser.hintList_return retval = new HiveParser_SelectClauseParser.hintList_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token COMMA29=null;
        HiveParser_SelectClauseParser.hintItem_return hintItem28 =null;

        HiveParser_SelectClauseParser.hintItem_return hintItem30 =null;


        CommonTree COMMA29_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_hintItem=new RewriteRuleSubtreeStream(adaptor,"rule hintItem");
         gParent.msgs.push("hint list"); 
        try {
            // SelectClauseParser.g:94:5: ( hintItem ( COMMA hintItem )* -> ( hintItem )+ )
            // SelectClauseParser.g:95:5: hintItem ( COMMA hintItem )*
            {
            pushFollow(FOLLOW_hintItem_in_hintList477);
            hintItem28=hintItem();

            state._fsp--;

            stream_hintItem.add(hintItem28.getTree());

            // SelectClauseParser.g:95:14: ( COMMA hintItem )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==COMMA) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // SelectClauseParser.g:95:15: COMMA hintItem
            	    {
            	    COMMA29=(Token)match(input,COMMA,FOLLOW_COMMA_in_hintList480);  
            	    stream_COMMA.add(COMMA29);


            	    pushFollow(FOLLOW_hintItem_in_hintList482);
            	    hintItem30=hintItem();

            	    state._fsp--;

            	    stream_hintItem.add(hintItem30.getTree());

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            // AST REWRITE
            // elements: hintItem
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 95:32: -> ( hintItem )+
            {
                if ( !(stream_hintItem.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_hintItem.hasNext() ) {
                    adaptor.addChild(root_0, stream_hintItem.nextTree());

                }
                stream_hintItem.reset();

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "hintList"


    public static class hintItem_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "hintItem"
    // SelectClauseParser.g:98:1: hintItem : hintName ( LPAREN hintArgs RPAREN )? -> ^( TOK_HINT hintName ( hintArgs )? ) ;
    public final HiveParser_SelectClauseParser.hintItem_return hintItem() throws RecognitionException {
        HiveParser_SelectClauseParser.hintItem_return retval = new HiveParser_SelectClauseParser.hintItem_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token LPAREN32=null;
        Token RPAREN34=null;
        HiveParser_SelectClauseParser.hintName_return hintName31 =null;

        HiveParser_SelectClauseParser.hintArgs_return hintArgs33 =null;


        CommonTree LPAREN32_tree=null;
        CommonTree RPAREN34_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_hintName=new RewriteRuleSubtreeStream(adaptor,"rule hintName");
        RewriteRuleSubtreeStream stream_hintArgs=new RewriteRuleSubtreeStream(adaptor,"rule hintArgs");
         gParent.msgs.push("hint item"); 
        try {
            // SelectClauseParser.g:101:5: ( hintName ( LPAREN hintArgs RPAREN )? -> ^( TOK_HINT hintName ( hintArgs )? ) )
            // SelectClauseParser.g:102:5: hintName ( LPAREN hintArgs RPAREN )?
            {
            pushFollow(FOLLOW_hintName_in_hintItem520);
            hintName31=hintName();

            state._fsp--;

            stream_hintName.add(hintName31.getTree());

            // SelectClauseParser.g:102:14: ( LPAREN hintArgs RPAREN )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==LPAREN) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // SelectClauseParser.g:102:15: LPAREN hintArgs RPAREN
                    {
                    LPAREN32=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_hintItem523);  
                    stream_LPAREN.add(LPAREN32);


                    pushFollow(FOLLOW_hintArgs_in_hintItem525);
                    hintArgs33=hintArgs();

                    state._fsp--;

                    stream_hintArgs.add(hintArgs33.getTree());

                    RPAREN34=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_hintItem527);  
                    stream_RPAREN.add(RPAREN34);


                    }
                    break;

            }


            // AST REWRITE
            // elements: hintArgs, hintName
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 102:40: -> ^( TOK_HINT hintName ( hintArgs )? )
            {
                // SelectClauseParser.g:102:43: ^( TOK_HINT hintName ( hintArgs )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(TOK_HINT, "TOK_HINT")
                , root_1);

                adaptor.addChild(root_1, stream_hintName.nextTree());

                // SelectClauseParser.g:102:63: ( hintArgs )?
                if ( stream_hintArgs.hasNext() ) {
                    adaptor.addChild(root_1, stream_hintArgs.nextTree());

                }
                stream_hintArgs.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "hintItem"


    public static class hintName_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "hintName"
    // SelectClauseParser.g:105:1: hintName : ( KW_MAPJOIN -> TOK_MAPJOIN | KW_INCRE -> TOK_INCRE | KW_STREAMTABLE -> TOK_STREAMTABLE | KW_HOLD_DDLTIME -> TOK_HOLD_DDLTIME );
    public final HiveParser_SelectClauseParser.hintName_return hintName() throws RecognitionException {
        HiveParser_SelectClauseParser.hintName_return retval = new HiveParser_SelectClauseParser.hintName_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token KW_MAPJOIN35=null;
        Token KW_INCRE36=null;
        Token KW_STREAMTABLE37=null;
        Token KW_HOLD_DDLTIME38=null;

        CommonTree KW_MAPJOIN35_tree=null;
        CommonTree KW_INCRE36_tree=null;
        CommonTree KW_STREAMTABLE37_tree=null;
        CommonTree KW_HOLD_DDLTIME38_tree=null;
        RewriteRuleTokenStream stream_KW_INCRE=new RewriteRuleTokenStream(adaptor,"token KW_INCRE");
        RewriteRuleTokenStream stream_KW_HOLD_DDLTIME=new RewriteRuleTokenStream(adaptor,"token KW_HOLD_DDLTIME");
        RewriteRuleTokenStream stream_KW_MAPJOIN=new RewriteRuleTokenStream(adaptor,"token KW_MAPJOIN");
        RewriteRuleTokenStream stream_KW_STREAMTABLE=new RewriteRuleTokenStream(adaptor,"token KW_STREAMTABLE");

         gParent.msgs.push("hint name"); 
        try {
            // SelectClauseParser.g:108:5: ( KW_MAPJOIN -> TOK_MAPJOIN | KW_INCRE -> TOK_INCRE | KW_STREAMTABLE -> TOK_STREAMTABLE | KW_HOLD_DDLTIME -> TOK_HOLD_DDLTIME )
            int alt12=4;
            switch ( input.LA(1) ) {
            case KW_MAPJOIN:
                {
                alt12=1;
                }
                break;
            case KW_INCRE:
                {
                alt12=2;
                }
                break;
            case KW_STREAMTABLE:
                {
                alt12=3;
                }
                break;
            case KW_HOLD_DDLTIME:
                {
                alt12=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;

            }

            switch (alt12) {
                case 1 :
                    // SelectClauseParser.g:109:5: KW_MAPJOIN
                    {
                    KW_MAPJOIN35=(Token)match(input,KW_MAPJOIN,FOLLOW_KW_MAPJOIN_in_hintName571);  
                    stream_KW_MAPJOIN.add(KW_MAPJOIN35);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 109:16: -> TOK_MAPJOIN
                    {
                        adaptor.addChild(root_0, 
                        (CommonTree)adaptor.create(TOK_MAPJOIN, "TOK_MAPJOIN")
                        );

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // SelectClauseParser.g:110:7: KW_INCRE
                    {
                    KW_INCRE36=(Token)match(input,KW_INCRE,FOLLOW_KW_INCRE_in_hintName583);  
                    stream_KW_INCRE.add(KW_INCRE36);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 110:16: -> TOK_INCRE
                    {
                        adaptor.addChild(root_0, 
                        (CommonTree)adaptor.create(TOK_INCRE, "TOK_INCRE")
                        );

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // SelectClauseParser.g:111:7: KW_STREAMTABLE
                    {
                    KW_STREAMTABLE37=(Token)match(input,KW_STREAMTABLE,FOLLOW_KW_STREAMTABLE_in_hintName595);  
                    stream_KW_STREAMTABLE.add(KW_STREAMTABLE37);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 111:22: -> TOK_STREAMTABLE
                    {
                        adaptor.addChild(root_0, 
                        (CommonTree)adaptor.create(TOK_STREAMTABLE, "TOK_STREAMTABLE")
                        );

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 4 :
                    // SelectClauseParser.g:112:7: KW_HOLD_DDLTIME
                    {
                    KW_HOLD_DDLTIME38=(Token)match(input,KW_HOLD_DDLTIME,FOLLOW_KW_HOLD_DDLTIME_in_hintName607);  
                    stream_KW_HOLD_DDLTIME.add(KW_HOLD_DDLTIME38);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 112:23: -> TOK_HOLD_DDLTIME
                    {
                        adaptor.addChild(root_0, 
                        (CommonTree)adaptor.create(TOK_HOLD_DDLTIME, "TOK_HOLD_DDLTIME")
                        );

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "hintName"


    public static class hintArgs_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "hintArgs"
    // SelectClauseParser.g:115:1: hintArgs : hintArgName ( COMMA hintArgName )* -> ^( TOK_HINTARGLIST ( hintArgName )+ ) ;
    public final HiveParser_SelectClauseParser.hintArgs_return hintArgs() throws RecognitionException {
        HiveParser_SelectClauseParser.hintArgs_return retval = new HiveParser_SelectClauseParser.hintArgs_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token COMMA40=null;
        HiveParser_SelectClauseParser.hintArgName_return hintArgName39 =null;

        HiveParser_SelectClauseParser.hintArgName_return hintArgName41 =null;


        CommonTree COMMA40_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_hintArgName=new RewriteRuleSubtreeStream(adaptor,"rule hintArgName");
         gParent.msgs.push("hint arguments"); 
        try {
            // SelectClauseParser.g:118:5: ( hintArgName ( COMMA hintArgName )* -> ^( TOK_HINTARGLIST ( hintArgName )+ ) )
            // SelectClauseParser.g:119:5: hintArgName ( COMMA hintArgName )*
            {
            pushFollow(FOLLOW_hintArgName_in_hintArgs642);
            hintArgName39=hintArgName();

            state._fsp--;

            stream_hintArgName.add(hintArgName39.getTree());

            // SelectClauseParser.g:119:17: ( COMMA hintArgName )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==COMMA) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // SelectClauseParser.g:119:18: COMMA hintArgName
            	    {
            	    COMMA40=(Token)match(input,COMMA,FOLLOW_COMMA_in_hintArgs645);  
            	    stream_COMMA.add(COMMA40);


            	    pushFollow(FOLLOW_hintArgName_in_hintArgs647);
            	    hintArgName41=hintArgName();

            	    state._fsp--;

            	    stream_hintArgName.add(hintArgName41.getTree());

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            // AST REWRITE
            // elements: hintArgName
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 119:38: -> ^( TOK_HINTARGLIST ( hintArgName )+ )
            {
                // SelectClauseParser.g:119:41: ^( TOK_HINTARGLIST ( hintArgName )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(TOK_HINTARGLIST, "TOK_HINTARGLIST")
                , root_1);

                if ( !(stream_hintArgName.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_hintArgName.hasNext() ) {
                    adaptor.addChild(root_1, stream_hintArgName.nextTree());

                }
                stream_hintArgName.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "hintArgs"


    public static class hintArgName_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "hintArgName"
    // SelectClauseParser.g:122:1: hintArgName : identifier ;
    public final HiveParser_SelectClauseParser.hintArgName_return hintArgName() throws RecognitionException {
        HiveParser_SelectClauseParser.hintArgName_return retval = new HiveParser_SelectClauseParser.hintArgName_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        HiveParser_IdentifiersParser.identifier_return identifier42 =null;



         gParent.msgs.push("hint argument name"); 
        try {
            // SelectClauseParser.g:125:5: ( identifier )
            // SelectClauseParser.g:126:5: identifier
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_identifier_in_hintArgName689);
            identifier42=gHiveParser.identifier();

            state._fsp--;

            adaptor.addChild(root_0, identifier42.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "hintArgName"


    public static class selectItem_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "selectItem"
    // SelectClauseParser.g:129:1: selectItem : ( selectExpression ( ( ( KW_AS )? identifier ) | ( KW_AS LPAREN identifier ( COMMA identifier )* RPAREN ) )? ) -> ^( TOK_SELEXPR selectExpression ( identifier )* ) ;
    public final HiveParser_SelectClauseParser.selectItem_return selectItem() throws RecognitionException {
        HiveParser_SelectClauseParser.selectItem_return retval = new HiveParser_SelectClauseParser.selectItem_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token KW_AS44=null;
        Token KW_AS46=null;
        Token LPAREN47=null;
        Token COMMA49=null;
        Token RPAREN51=null;
        HiveParser_SelectClauseParser.selectExpression_return selectExpression43 =null;

        HiveParser_IdentifiersParser.identifier_return identifier45 =null;

        HiveParser_IdentifiersParser.identifier_return identifier48 =null;

        HiveParser_IdentifiersParser.identifier_return identifier50 =null;


        CommonTree KW_AS44_tree=null;
        CommonTree KW_AS46_tree=null;
        CommonTree LPAREN47_tree=null;
        CommonTree COMMA49_tree=null;
        CommonTree RPAREN51_tree=null;
        RewriteRuleTokenStream stream_KW_AS=new RewriteRuleTokenStream(adaptor,"token KW_AS");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_selectExpression=new RewriteRuleSubtreeStream(adaptor,"rule selectExpression");
        RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");
         gParent.msgs.push("selection target"); 
        try {
            // SelectClauseParser.g:132:5: ( ( selectExpression ( ( ( KW_AS )? identifier ) | ( KW_AS LPAREN identifier ( COMMA identifier )* RPAREN ) )? ) -> ^( TOK_SELEXPR selectExpression ( identifier )* ) )
            // SelectClauseParser.g:133:5: ( selectExpression ( ( ( KW_AS )? identifier ) | ( KW_AS LPAREN identifier ( COMMA identifier )* RPAREN ) )? )
            {
            // SelectClauseParser.g:133:5: ( selectExpression ( ( ( KW_AS )? identifier ) | ( KW_AS LPAREN identifier ( COMMA identifier )* RPAREN ) )? )
            // SelectClauseParser.g:133:7: selectExpression ( ( ( KW_AS )? identifier ) | ( KW_AS LPAREN identifier ( COMMA identifier )* RPAREN ) )?
            {
            pushFollow(FOLLOW_selectExpression_in_selectItem722);
            selectExpression43=selectExpression();

            state._fsp--;

            stream_selectExpression.add(selectExpression43.getTree());

            // SelectClauseParser.g:134:7: ( ( ( KW_AS )? identifier ) | ( KW_AS LPAREN identifier ( COMMA identifier )* RPAREN ) )?
            int alt16=3;
            alt16 = dfa16.predict(input);
            switch (alt16) {
                case 1 :
                    // SelectClauseParser.g:134:8: ( ( KW_AS )? identifier )
                    {
                    // SelectClauseParser.g:134:8: ( ( KW_AS )? identifier )
                    // SelectClauseParser.g:134:9: ( KW_AS )? identifier
                    {
                    // SelectClauseParser.g:134:9: ( KW_AS )?
                    int alt14=2;
                    alt14 = dfa14.predict(input);
                    switch (alt14) {
                        case 1 :
                            // SelectClauseParser.g:134:9: KW_AS
                            {
                            KW_AS44=(Token)match(input,KW_AS,FOLLOW_KW_AS_in_selectItem732);  
                            stream_KW_AS.add(KW_AS44);


                            }
                            break;

                    }


                    pushFollow(FOLLOW_identifier_in_selectItem735);
                    identifier45=gHiveParser.identifier();

                    state._fsp--;

                    stream_identifier.add(identifier45.getTree());

                    }


                    }
                    break;
                case 2 :
                    // SelectClauseParser.g:134:30: ( KW_AS LPAREN identifier ( COMMA identifier )* RPAREN )
                    {
                    // SelectClauseParser.g:134:30: ( KW_AS LPAREN identifier ( COMMA identifier )* RPAREN )
                    // SelectClauseParser.g:134:31: KW_AS LPAREN identifier ( COMMA identifier )* RPAREN
                    {
                    KW_AS46=(Token)match(input,KW_AS,FOLLOW_KW_AS_in_selectItem741);  
                    stream_KW_AS.add(KW_AS46);


                    LPAREN47=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_selectItem743);  
                    stream_LPAREN.add(LPAREN47);


                    pushFollow(FOLLOW_identifier_in_selectItem745);
                    identifier48=gHiveParser.identifier();

                    state._fsp--;

                    stream_identifier.add(identifier48.getTree());

                    // SelectClauseParser.g:134:55: ( COMMA identifier )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==COMMA) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // SelectClauseParser.g:134:56: COMMA identifier
                    	    {
                    	    COMMA49=(Token)match(input,COMMA,FOLLOW_COMMA_in_selectItem748);  
                    	    stream_COMMA.add(COMMA49);


                    	    pushFollow(FOLLOW_identifier_in_selectItem750);
                    	    identifier50=gHiveParser.identifier();

                    	    state._fsp--;

                    	    stream_identifier.add(identifier50.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);


                    RPAREN51=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_selectItem754);  
                    stream_RPAREN.add(RPAREN51);


                    }


                    }
                    break;

            }


            }


            // AST REWRITE
            // elements: selectExpression, identifier
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 135:7: -> ^( TOK_SELEXPR selectExpression ( identifier )* )
            {
                // SelectClauseParser.g:135:10: ^( TOK_SELEXPR selectExpression ( identifier )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(TOK_SELEXPR, "TOK_SELEXPR")
                , root_1);

                adaptor.addChild(root_1, stream_selectExpression.nextTree());

                // SelectClauseParser.g:135:41: ( identifier )*
                while ( stream_identifier.hasNext() ) {
                    adaptor.addChild(root_1, stream_identifier.nextTree());

                }
                stream_identifier.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "selectItem"


    public static class trfmClause_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "trfmClause"
    // SelectClauseParser.g:138:1: trfmClause : ( KW_MAP selectExpressionList | KW_REDUCE selectExpressionList ) inSerde= rowFormat inRec= recordWriter KW_USING StringLiteral ( KW_AS ( ( LPAREN ( aliasList | columnNameTypeList ) RPAREN ) | ( aliasList | columnNameTypeList ) ) )? outSerde= rowFormat outRec= recordReader -> ^( TOK_TRANSFORM selectExpressionList $inSerde $inRec StringLiteral $outSerde $outRec ( aliasList )? ( columnNameTypeList )? ) ;
    public final HiveParser_SelectClauseParser.trfmClause_return trfmClause() throws RecognitionException {
        HiveParser_SelectClauseParser.trfmClause_return retval = new HiveParser_SelectClauseParser.trfmClause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token KW_MAP52=null;
        Token KW_REDUCE54=null;
        Token KW_USING56=null;
        Token StringLiteral57=null;
        Token KW_AS58=null;
        Token LPAREN59=null;
        Token RPAREN62=null;
        HiveParser.rowFormat_return inSerde =null;

        HiveParser.recordWriter_return inRec =null;

        HiveParser.rowFormat_return outSerde =null;

        HiveParser.recordReader_return outRec =null;

        HiveParser_SelectClauseParser.selectExpressionList_return selectExpressionList53 =null;

        HiveParser_SelectClauseParser.selectExpressionList_return selectExpressionList55 =null;

        HiveParser_FromClauseParser.aliasList_return aliasList60 =null;

        HiveParser.columnNameTypeList_return columnNameTypeList61 =null;

        HiveParser_FromClauseParser.aliasList_return aliasList63 =null;

        HiveParser.columnNameTypeList_return columnNameTypeList64 =null;


        CommonTree KW_MAP52_tree=null;
        CommonTree KW_REDUCE54_tree=null;
        CommonTree KW_USING56_tree=null;
        CommonTree StringLiteral57_tree=null;
        CommonTree KW_AS58_tree=null;
        CommonTree LPAREN59_tree=null;
        CommonTree RPAREN62_tree=null;
        RewriteRuleTokenStream stream_StringLiteral=new RewriteRuleTokenStream(adaptor,"token StringLiteral");
        RewriteRuleTokenStream stream_KW_AS=new RewriteRuleTokenStream(adaptor,"token KW_AS");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_KW_REDUCE=new RewriteRuleTokenStream(adaptor,"token KW_REDUCE");
        RewriteRuleTokenStream stream_KW_USING=new RewriteRuleTokenStream(adaptor,"token KW_USING");
        RewriteRuleTokenStream stream_KW_MAP=new RewriteRuleTokenStream(adaptor,"token KW_MAP");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_rowFormat=new RewriteRuleSubtreeStream(adaptor,"rule rowFormat");
        RewriteRuleSubtreeStream stream_columnNameTypeList=new RewriteRuleSubtreeStream(adaptor,"rule columnNameTypeList");
        RewriteRuleSubtreeStream stream_recordReader=new RewriteRuleSubtreeStream(adaptor,"rule recordReader");
        RewriteRuleSubtreeStream stream_aliasList=new RewriteRuleSubtreeStream(adaptor,"rule aliasList");
        RewriteRuleSubtreeStream stream_selectExpressionList=new RewriteRuleSubtreeStream(adaptor,"rule selectExpressionList");
        RewriteRuleSubtreeStream stream_recordWriter=new RewriteRuleSubtreeStream(adaptor,"rule recordWriter");
         gParent.msgs.push("transform clause"); 
        try {
            // SelectClauseParser.g:141:5: ( ( KW_MAP selectExpressionList | KW_REDUCE selectExpressionList ) inSerde= rowFormat inRec= recordWriter KW_USING StringLiteral ( KW_AS ( ( LPAREN ( aliasList | columnNameTypeList ) RPAREN ) | ( aliasList | columnNameTypeList ) ) )? outSerde= rowFormat outRec= recordReader -> ^( TOK_TRANSFORM selectExpressionList $inSerde $inRec StringLiteral $outSerde $outRec ( aliasList )? ( columnNameTypeList )? ) )
            // SelectClauseParser.g:142:5: ( KW_MAP selectExpressionList | KW_REDUCE selectExpressionList ) inSerde= rowFormat inRec= recordWriter KW_USING StringLiteral ( KW_AS ( ( LPAREN ( aliasList | columnNameTypeList ) RPAREN ) | ( aliasList | columnNameTypeList ) ) )? outSerde= rowFormat outRec= recordReader
            {
            // SelectClauseParser.g:142:5: ( KW_MAP selectExpressionList | KW_REDUCE selectExpressionList )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==KW_MAP) ) {
                alt17=1;
            }
            else if ( (LA17_0==KW_REDUCE) ) {
                alt17=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }
            switch (alt17) {
                case 1 :
                    // SelectClauseParser.g:142:9: KW_MAP selectExpressionList
                    {
                    KW_MAP52=(Token)match(input,KW_MAP,FOLLOW_KW_MAP_in_trfmClause809);  
                    stream_KW_MAP.add(KW_MAP52);


                    pushFollow(FOLLOW_selectExpressionList_in_trfmClause814);
                    selectExpressionList53=selectExpressionList();

                    state._fsp--;

                    stream_selectExpressionList.add(selectExpressionList53.getTree());

                    }
                    break;
                case 2 :
                    // SelectClauseParser.g:143:9: KW_REDUCE selectExpressionList
                    {
                    KW_REDUCE54=(Token)match(input,KW_REDUCE,FOLLOW_KW_REDUCE_in_trfmClause824);  
                    stream_KW_REDUCE.add(KW_REDUCE54);


                    pushFollow(FOLLOW_selectExpressionList_in_trfmClause826);
                    selectExpressionList55=selectExpressionList();

                    state._fsp--;

                    stream_selectExpressionList.add(selectExpressionList55.getTree());

                    }
                    break;

            }


            pushFollow(FOLLOW_rowFormat_in_trfmClause836);
            inSerde=gHiveParser.rowFormat();

            state._fsp--;

            stream_rowFormat.add(inSerde.getTree());

            pushFollow(FOLLOW_recordWriter_in_trfmClause840);
            inRec=gHiveParser.recordWriter();

            state._fsp--;

            stream_recordWriter.add(inRec.getTree());

            KW_USING56=(Token)match(input,KW_USING,FOLLOW_KW_USING_in_trfmClause846);  
            stream_KW_USING.add(KW_USING56);


            StringLiteral57=(Token)match(input,StringLiteral,FOLLOW_StringLiteral_in_trfmClause848);  
            stream_StringLiteral.add(StringLiteral57);


            // SelectClauseParser.g:146:5: ( KW_AS ( ( LPAREN ( aliasList | columnNameTypeList ) RPAREN ) | ( aliasList | columnNameTypeList ) ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==KW_AS) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // SelectClauseParser.g:146:7: KW_AS ( ( LPAREN ( aliasList | columnNameTypeList ) RPAREN ) | ( aliasList | columnNameTypeList ) )
                    {
                    KW_AS58=(Token)match(input,KW_AS,FOLLOW_KW_AS_in_trfmClause856);  
                    stream_KW_AS.add(KW_AS58);


                    // SelectClauseParser.g:146:13: ( ( LPAREN ( aliasList | columnNameTypeList ) RPAREN ) | ( aliasList | columnNameTypeList ) )
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==LPAREN) ) {
                        alt20=1;
                    }
                    else if ( ((LA20_0 >= Identifier && LA20_0 <= KW_AFTER)||(LA20_0 >= KW_ALTER && LA20_0 <= KW_ANALYZE)||(LA20_0 >= KW_ARCHIVE && LA20_0 <= KW_CASCADE)||(LA20_0 >= KW_CHANGE && LA20_0 <= KW_COLLECTION)||(LA20_0 >= KW_COLUMNS && LA20_0 <= KW_CONCATENATE)||(LA20_0 >= KW_CONTINUE && LA20_0 <= KW_CREATE)||LA20_0==KW_CUBE||(LA20_0 >= KW_CURSOR && LA20_0 <= KW_DATA)||(LA20_0 >= KW_DATABASES && LA20_0 <= KW_DISABLE)||(LA20_0 >= KW_DISTRIBUTE && LA20_0 <= KW_ELEM_TYPE)||LA20_0==KW_ENABLE||LA20_0==KW_ESCAPED||(LA20_0 >= KW_EXCLUSIVE && LA20_0 <= KW_EXPORT)||(LA20_0 >= KW_EXTERNAL && LA20_0 <= KW_FLOAT)||(LA20_0 >= KW_FOR && LA20_0 <= KW_FORMATTED)||LA20_0==KW_FULL||(LA20_0 >= KW_FUNCTIONS && LA20_0 <= KW_GROUPING)||(LA20_0 >= KW_HOLD_DDLTIME && LA20_0 <= KW_IDXPROPERTIES)||(LA20_0 >= KW_IGNORE && LA20_0 <= KW_INTERSECT)||(LA20_0 >= KW_INTO && LA20_0 <= KW_ITEMS)||(LA20_0 >= KW_KEYS && LA20_0 <= KW_LEFT)||(LA20_0 >= KW_LIKE && LA20_0 <= KW_LONG)||(LA20_0 >= KW_MAPJOIN && LA20_0 <= KW_MINUS)||(LA20_0 >= KW_MSCK && LA20_0 <= KW_NOSCAN)||(LA20_0 >= KW_NO_DROP && LA20_0 <= KW_OFFLINE)||LA20_0==KW_OPTION||(LA20_0 >= KW_ORCFILE && LA20_0 <= KW_OUTPUTFORMAT)||LA20_0==KW_OVERWRITE||(LA20_0 >= KW_PARTITION && LA20_0 <= KW_PLUS)||(LA20_0 >= KW_PRETTY && LA20_0 <= KW_RECORDWRITER)||(LA20_0 >= KW_REGEXP && LA20_0 <= KW_SCHEMAS)||(LA20_0 >= KW_SEMI && LA20_0 <= KW_TABLES)||(LA20_0 >= KW_TBLPROPERTIES && LA20_0 <= KW_TEXTFILE)||(LA20_0 >= KW_TIMESTAMP && LA20_0 <= KW_TOUCH)||(LA20_0 >= KW_TRIGGER && LA20_0 <= KW_UNARCHIVE)||(LA20_0 >= KW_UNDO && LA20_0 <= KW_UNIONTYPE)||(LA20_0 >= KW_UNLOCK && LA20_0 <= KW_VALUE_TYPE)||LA20_0==KW_VIEW||LA20_0==KW_WHILE||LA20_0==KW_WITH) ) {
                        alt20=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 20, 0, input);

                        throw nvae;

                    }
                    switch (alt20) {
                        case 1 :
                            // SelectClauseParser.g:146:14: ( LPAREN ( aliasList | columnNameTypeList ) RPAREN )
                            {
                            // SelectClauseParser.g:146:14: ( LPAREN ( aliasList | columnNameTypeList ) RPAREN )
                            // SelectClauseParser.g:146:15: LPAREN ( aliasList | columnNameTypeList ) RPAREN
                            {
                            LPAREN59=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_trfmClause860);  
                            stream_LPAREN.add(LPAREN59);


                            // SelectClauseParser.g:146:22: ( aliasList | columnNameTypeList )
                            int alt18=2;
                            int LA18_0 = input.LA(1);

                            if ( (LA18_0==Identifier) ) {
                                int LA18_1 = input.LA(2);

                                if ( (LA18_1==COMMA||LA18_1==RPAREN) ) {
                                    alt18=1;
                                }
                                else if ( (LA18_1==KW_ARRAY||(LA18_1 >= KW_BIGINT && LA18_1 <= KW_BOOLEAN)||(LA18_1 >= KW_DATE && LA18_1 <= KW_DATETIME)||LA18_1==KW_DECIMAL||LA18_1==KW_DOUBLE||LA18_1==KW_FLOAT||LA18_1==KW_INT||LA18_1==KW_MAP||LA18_1==KW_SMALLINT||(LA18_1 >= KW_STRING && LA18_1 <= KW_STRUCT)||(LA18_1 >= KW_TIMESTAMP && LA18_1 <= KW_TINYINT)||LA18_1==KW_UNIONTYPE||LA18_1==KW_VARCHAR) ) {
                                    alt18=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 18, 1, input);

                                    throw nvae;

                                }
                            }
                            else if ( ((LA18_0 >= KW_ADD && LA18_0 <= KW_AFTER)||(LA18_0 >= KW_ALTER && LA18_0 <= KW_ANALYZE)||(LA18_0 >= KW_ARCHIVE && LA18_0 <= KW_CASCADE)||(LA18_0 >= KW_CHANGE && LA18_0 <= KW_COLLECTION)||(LA18_0 >= KW_COLUMNS && LA18_0 <= KW_CONCATENATE)||(LA18_0 >= KW_CONTINUE && LA18_0 <= KW_CREATE)||LA18_0==KW_CUBE||(LA18_0 >= KW_CURSOR && LA18_0 <= KW_DATA)||(LA18_0 >= KW_DATABASES && LA18_0 <= KW_DISABLE)||(LA18_0 >= KW_DISTRIBUTE && LA18_0 <= KW_ELEM_TYPE)||LA18_0==KW_ENABLE||LA18_0==KW_ESCAPED||(LA18_0 >= KW_EXCLUSIVE && LA18_0 <= KW_EXPORT)||(LA18_0 >= KW_EXTERNAL && LA18_0 <= KW_FLOAT)||(LA18_0 >= KW_FOR && LA18_0 <= KW_FORMATTED)||LA18_0==KW_FULL||(LA18_0 >= KW_FUNCTIONS && LA18_0 <= KW_GROUPING)||(LA18_0 >= KW_HOLD_DDLTIME && LA18_0 <= KW_IDXPROPERTIES)||(LA18_0 >= KW_IGNORE && LA18_0 <= KW_INTERSECT)||(LA18_0 >= KW_INTO && LA18_0 <= KW_ITEMS)||(LA18_0 >= KW_KEYS && LA18_0 <= KW_LEFT)||(LA18_0 >= KW_LIKE && LA18_0 <= KW_LONG)||(LA18_0 >= KW_MAPJOIN && LA18_0 <= KW_MINUS)||(LA18_0 >= KW_MSCK && LA18_0 <= KW_NOSCAN)||(LA18_0 >= KW_NO_DROP && LA18_0 <= KW_OFFLINE)||LA18_0==KW_OPTION||(LA18_0 >= KW_ORCFILE && LA18_0 <= KW_OUTPUTFORMAT)||LA18_0==KW_OVERWRITE||(LA18_0 >= KW_PARTITION && LA18_0 <= KW_PLUS)||(LA18_0 >= KW_PRETTY && LA18_0 <= KW_RECORDWRITER)||(LA18_0 >= KW_REGEXP && LA18_0 <= KW_SCHEMAS)||(LA18_0 >= KW_SEMI && LA18_0 <= KW_TABLES)||(LA18_0 >= KW_TBLPROPERTIES && LA18_0 <= KW_TEXTFILE)||(LA18_0 >= KW_TIMESTAMP && LA18_0 <= KW_TOUCH)||(LA18_0 >= KW_TRIGGER && LA18_0 <= KW_UNARCHIVE)||(LA18_0 >= KW_UNDO && LA18_0 <= KW_UNIONTYPE)||(LA18_0 >= KW_UNLOCK && LA18_0 <= KW_VALUE_TYPE)||LA18_0==KW_VIEW||LA18_0==KW_WHILE||LA18_0==KW_WITH) ) {
                                int LA18_2 = input.LA(2);

                                if ( (LA18_2==COMMA||LA18_2==RPAREN) ) {
                                    alt18=1;
                                }
                                else if ( (LA18_2==KW_ARRAY||(LA18_2 >= KW_BIGINT && LA18_2 <= KW_BOOLEAN)||(LA18_2 >= KW_DATE && LA18_2 <= KW_DATETIME)||LA18_2==KW_DECIMAL||LA18_2==KW_DOUBLE||LA18_2==KW_FLOAT||LA18_2==KW_INT||LA18_2==KW_MAP||LA18_2==KW_SMALLINT||(LA18_2 >= KW_STRING && LA18_2 <= KW_STRUCT)||(LA18_2 >= KW_TIMESTAMP && LA18_2 <= KW_TINYINT)||LA18_2==KW_UNIONTYPE||LA18_2==KW_VARCHAR) ) {
                                    alt18=2;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 18, 2, input);

                                    throw nvae;

                                }
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 18, 0, input);

                                throw nvae;

                            }
                            switch (alt18) {
                                case 1 :
                                    // SelectClauseParser.g:146:23: aliasList
                                    {
                                    pushFollow(FOLLOW_aliasList_in_trfmClause863);
                                    aliasList60=gHiveParser.aliasList();

                                    state._fsp--;

                                    stream_aliasList.add(aliasList60.getTree());

                                    }
                                    break;
                                case 2 :
                                    // SelectClauseParser.g:146:35: columnNameTypeList
                                    {
                                    pushFollow(FOLLOW_columnNameTypeList_in_trfmClause867);
                                    columnNameTypeList61=gHiveParser.columnNameTypeList();

                                    state._fsp--;

                                    stream_columnNameTypeList.add(columnNameTypeList61.getTree());

                                    }
                                    break;

                            }


                            RPAREN62=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_trfmClause870);  
                            stream_RPAREN.add(RPAREN62);


                            }


                            }
                            break;
                        case 2 :
                            // SelectClauseParser.g:146:65: ( aliasList | columnNameTypeList )
                            {
                            // SelectClauseParser.g:146:65: ( aliasList | columnNameTypeList )
                            int alt19=2;
                            alt19 = dfa19.predict(input);
                            switch (alt19) {
                                case 1 :
                                    // SelectClauseParser.g:146:66: aliasList
                                    {
                                    pushFollow(FOLLOW_aliasList_in_trfmClause876);
                                    aliasList63=gHiveParser.aliasList();

                                    state._fsp--;

                                    stream_aliasList.add(aliasList63.getTree());

                                    }
                                    break;
                                case 2 :
                                    // SelectClauseParser.g:146:78: columnNameTypeList
                                    {
                                    pushFollow(FOLLOW_columnNameTypeList_in_trfmClause880);
                                    columnNameTypeList64=gHiveParser.columnNameTypeList();

                                    state._fsp--;

                                    stream_columnNameTypeList.add(columnNameTypeList64.getTree());

                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    }
                    break;

            }


            pushFollow(FOLLOW_rowFormat_in_trfmClause892);
            outSerde=gHiveParser.rowFormat();

            state._fsp--;

            stream_rowFormat.add(outSerde.getTree());

            pushFollow(FOLLOW_recordReader_in_trfmClause896);
            outRec=gHiveParser.recordReader();

            state._fsp--;

            stream_recordReader.add(outRec.getTree());

            // AST REWRITE
            // elements: aliasList, outRec, StringLiteral, selectExpressionList, outSerde, inSerde, inRec, columnNameTypeList
            // token labels: 
            // rule labels: retval, inRec, inSerde, outRec, outSerde
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_inRec=new RewriteRuleSubtreeStream(adaptor,"rule inRec",inRec!=null?inRec.tree:null);
            RewriteRuleSubtreeStream stream_inSerde=new RewriteRuleSubtreeStream(adaptor,"rule inSerde",inSerde!=null?inSerde.tree:null);
            RewriteRuleSubtreeStream stream_outRec=new RewriteRuleSubtreeStream(adaptor,"rule outRec",outRec!=null?outRec.tree:null);
            RewriteRuleSubtreeStream stream_outSerde=new RewriteRuleSubtreeStream(adaptor,"rule outSerde",outSerde!=null?outSerde.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 148:5: -> ^( TOK_TRANSFORM selectExpressionList $inSerde $inRec StringLiteral $outSerde $outRec ( aliasList )? ( columnNameTypeList )? )
            {
                // SelectClauseParser.g:148:8: ^( TOK_TRANSFORM selectExpressionList $inSerde $inRec StringLiteral $outSerde $outRec ( aliasList )? ( columnNameTypeList )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(TOK_TRANSFORM, "TOK_TRANSFORM")
                , root_1);

                adaptor.addChild(root_1, stream_selectExpressionList.nextTree());

                adaptor.addChild(root_1, stream_inSerde.nextTree());

                adaptor.addChild(root_1, stream_inRec.nextTree());

                adaptor.addChild(root_1, 
                stream_StringLiteral.nextNode()
                );

                adaptor.addChild(root_1, stream_outSerde.nextTree());

                adaptor.addChild(root_1, stream_outRec.nextTree());

                // SelectClauseParser.g:148:93: ( aliasList )?
                if ( stream_aliasList.hasNext() ) {
                    adaptor.addChild(root_1, stream_aliasList.nextTree());

                }
                stream_aliasList.reset();

                // SelectClauseParser.g:148:104: ( columnNameTypeList )?
                if ( stream_columnNameTypeList.hasNext() ) {
                    adaptor.addChild(root_1, stream_columnNameTypeList.nextTree());

                }
                stream_columnNameTypeList.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "trfmClause"


    public static class selectExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "selectExpression"
    // SelectClauseParser.g:151:1: selectExpression : ( expression | tableAllColumns );
    public final HiveParser_SelectClauseParser.selectExpression_return selectExpression() throws RecognitionException {
        HiveParser_SelectClauseParser.selectExpression_return retval = new HiveParser_SelectClauseParser.selectExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        HiveParser_IdentifiersParser.expression_return expression65 =null;

        HiveParser_FromClauseParser.tableAllColumns_return tableAllColumns66 =null;



         gParent.msgs.push("select expression"); 
        try {
            // SelectClauseParser.g:154:5: ( expression | tableAllColumns )
            int alt22=2;
            alt22 = dfa22.predict(input);
            switch (alt22) {
                case 1 :
                    // SelectClauseParser.g:155:5: expression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_expression_in_selectExpression959);
                    expression65=gHiveParser.expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression65.getTree());

                    }
                    break;
                case 2 :
                    // SelectClauseParser.g:155:18: tableAllColumns
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_tableAllColumns_in_selectExpression963);
                    tableAllColumns66=gHiveParser.tableAllColumns();

                    state._fsp--;

                    adaptor.addChild(root_0, tableAllColumns66.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "selectExpression"


    public static class selectExpressionList_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "selectExpressionList"
    // SelectClauseParser.g:158:1: selectExpressionList : selectExpression ( COMMA selectExpression )* -> ^( TOK_EXPLIST ( selectExpression )+ ) ;
    public final HiveParser_SelectClauseParser.selectExpressionList_return selectExpressionList() throws RecognitionException {
        HiveParser_SelectClauseParser.selectExpressionList_return retval = new HiveParser_SelectClauseParser.selectExpressionList_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token COMMA68=null;
        HiveParser_SelectClauseParser.selectExpression_return selectExpression67 =null;

        HiveParser_SelectClauseParser.selectExpression_return selectExpression69 =null;


        CommonTree COMMA68_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_selectExpression=new RewriteRuleSubtreeStream(adaptor,"rule selectExpression");
         gParent.msgs.push("select expression list"); 
        try {
            // SelectClauseParser.g:161:5: ( selectExpression ( COMMA selectExpression )* -> ^( TOK_EXPLIST ( selectExpression )+ ) )
            // SelectClauseParser.g:162:5: selectExpression ( COMMA selectExpression )*
            {
            pushFollow(FOLLOW_selectExpression_in_selectExpressionList994);
            selectExpression67=selectExpression();

            state._fsp--;

            stream_selectExpression.add(selectExpression67.getTree());

            // SelectClauseParser.g:162:22: ( COMMA selectExpression )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==COMMA) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // SelectClauseParser.g:162:23: COMMA selectExpression
            	    {
            	    COMMA68=(Token)match(input,COMMA,FOLLOW_COMMA_in_selectExpressionList997);  
            	    stream_COMMA.add(COMMA68);


            	    pushFollow(FOLLOW_selectExpression_in_selectExpressionList999);
            	    selectExpression69=selectExpression();

            	    state._fsp--;

            	    stream_selectExpression.add(selectExpression69.getTree());

            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);


            // AST REWRITE
            // elements: selectExpression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 162:48: -> ^( TOK_EXPLIST ( selectExpression )+ )
            {
                // SelectClauseParser.g:162:51: ^( TOK_EXPLIST ( selectExpression )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(TOK_EXPLIST, "TOK_EXPLIST")
                , root_1);

                if ( !(stream_selectExpression.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_selectExpression.hasNext() ) {
                    adaptor.addChild(root_1, stream_selectExpression.nextTree());

                }
                stream_selectExpression.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "selectExpressionList"


    public static class incrementalClause_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "incrementalClause"
    // SelectClauseParser.g:168:1: incrementalClause : DIVIDE STAR PLUS KW_INCRE LPAREN incrementalArgs RPAREN STAR DIVIDE -> ^( TOK_INCRE ( incrementalArgs )? ) ;
    public final HiveParser_SelectClauseParser.incrementalClause_return incrementalClause() throws RecognitionException {
        HiveParser_SelectClauseParser.incrementalClause_return retval = new HiveParser_SelectClauseParser.incrementalClause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token DIVIDE70=null;
        Token STAR71=null;
        Token PLUS72=null;
        Token KW_INCRE73=null;
        Token LPAREN74=null;
        Token RPAREN76=null;
        Token STAR77=null;
        Token DIVIDE78=null;
        HiveParser_SelectClauseParser.incrementalArgs_return incrementalArgs75 =null;


        CommonTree DIVIDE70_tree=null;
        CommonTree STAR71_tree=null;
        CommonTree PLUS72_tree=null;
        CommonTree KW_INCRE73_tree=null;
        CommonTree LPAREN74_tree=null;
        CommonTree RPAREN76_tree=null;
        CommonTree STAR77_tree=null;
        CommonTree DIVIDE78_tree=null;
        RewriteRuleTokenStream stream_KW_INCRE=new RewriteRuleTokenStream(adaptor,"token KW_INCRE");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_PLUS=new RewriteRuleTokenStream(adaptor,"token PLUS");
        RewriteRuleTokenStream stream_STAR=new RewriteRuleTokenStream(adaptor,"token STAR");
        RewriteRuleTokenStream stream_DIVIDE=new RewriteRuleTokenStream(adaptor,"token DIVIDE");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_incrementalArgs=new RewriteRuleSubtreeStream(adaptor,"rule incrementalArgs");
         gParent.msgs.push("incremental clause"); 
        try {
            // SelectClauseParser.g:171:5: ( DIVIDE STAR PLUS KW_INCRE LPAREN incrementalArgs RPAREN STAR DIVIDE -> ^( TOK_INCRE ( incrementalArgs )? ) )
            // SelectClauseParser.g:172:5: DIVIDE STAR PLUS KW_INCRE LPAREN incrementalArgs RPAREN STAR DIVIDE
            {
            DIVIDE70=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_incrementalClause1046);  
            stream_DIVIDE.add(DIVIDE70);


            STAR71=(Token)match(input,STAR,FOLLOW_STAR_in_incrementalClause1048);  
            stream_STAR.add(STAR71);


            PLUS72=(Token)match(input,PLUS,FOLLOW_PLUS_in_incrementalClause1050);  
            stream_PLUS.add(PLUS72);


            KW_INCRE73=(Token)match(input,KW_INCRE,FOLLOW_KW_INCRE_in_incrementalClause1052);  
            stream_KW_INCRE.add(KW_INCRE73);


            LPAREN74=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_incrementalClause1054);  
            stream_LPAREN.add(LPAREN74);


            pushFollow(FOLLOW_incrementalArgs_in_incrementalClause1056);
            incrementalArgs75=incrementalArgs();

            state._fsp--;

            stream_incrementalArgs.add(incrementalArgs75.getTree());

            RPAREN76=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_incrementalClause1058);  
            stream_RPAREN.add(RPAREN76);


            STAR77=(Token)match(input,STAR,FOLLOW_STAR_in_incrementalClause1060);  
            stream_STAR.add(STAR77);


            DIVIDE78=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_incrementalClause1062);  
            stream_DIVIDE.add(DIVIDE78);


            // AST REWRITE
            // elements: incrementalArgs
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 173:6: -> ^( TOK_INCRE ( incrementalArgs )? )
            {
                // SelectClauseParser.g:173:10: ^( TOK_INCRE ( incrementalArgs )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(TOK_INCRE, "TOK_INCRE")
                , root_1);

                // SelectClauseParser.g:173:22: ( incrementalArgs )?
                if ( stream_incrementalArgs.hasNext() ) {
                    adaptor.addChild(root_1, stream_incrementalArgs.nextTree());

                }
                stream_incrementalArgs.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "incrementalClause"


    public static class incrementalArgs_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "incrementalArgs"
    // SelectClauseParser.g:176:1: incrementalArgs : (bdate= betweenDate -> $bdate| KW_CONSTANT time= Number unit0= identifier (interval= KW_INTERVAL numerator= Number DIVIDE unit1= identifier )? -> {interval != null}? ^( TOK_CONSTANT $time $unit0) ^( TOK_INTERVAL $numerator $unit1) -> ^( TOK_CONSTANT $time $unit0) | KW_AFTER sdate= startDate (interval= KW_INTERVAL numerator= Number DIVIDE unit2= identifier )? -> {interval != null}? $sdate ^( TOK_INTERVAL $numerator $unit2) -> $sdate);
    public final HiveParser_SelectClauseParser.incrementalArgs_return incrementalArgs() throws RecognitionException {
        HiveParser_SelectClauseParser.incrementalArgs_return retval = new HiveParser_SelectClauseParser.incrementalArgs_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token time=null;
        Token interval=null;
        Token numerator=null;
        Token KW_CONSTANT79=null;
        Token DIVIDE80=null;
        Token KW_AFTER81=null;
        Token DIVIDE82=null;
        HiveParser_SelectClauseParser.betweenDate_return bdate =null;

        HiveParser_IdentifiersParser.identifier_return unit0 =null;

        HiveParser_IdentifiersParser.identifier_return unit1 =null;

        HiveParser_SelectClauseParser.startDate_return sdate =null;

        HiveParser_IdentifiersParser.identifier_return unit2 =null;


        CommonTree time_tree=null;
        CommonTree interval_tree=null;
        CommonTree numerator_tree=null;
        CommonTree KW_CONSTANT79_tree=null;
        CommonTree DIVIDE80_tree=null;
        CommonTree KW_AFTER81_tree=null;
        CommonTree DIVIDE82_tree=null;
        RewriteRuleTokenStream stream_Number=new RewriteRuleTokenStream(adaptor,"token Number");
        RewriteRuleTokenStream stream_KW_AFTER=new RewriteRuleTokenStream(adaptor,"token KW_AFTER");
        RewriteRuleTokenStream stream_DIVIDE=new RewriteRuleTokenStream(adaptor,"token DIVIDE");
        RewriteRuleTokenStream stream_KW_CONSTANT=new RewriteRuleTokenStream(adaptor,"token KW_CONSTANT");
        RewriteRuleTokenStream stream_KW_INTERVAL=new RewriteRuleTokenStream(adaptor,"token KW_INTERVAL");
        RewriteRuleSubtreeStream stream_startDate=new RewriteRuleSubtreeStream(adaptor,"rule startDate");
        RewriteRuleSubtreeStream stream_betweenDate=new RewriteRuleSubtreeStream(adaptor,"rule betweenDate");
        RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");
         gParent.msgs.push("incremental arguments"); 
        try {
            // SelectClauseParser.g:179:5: (bdate= betweenDate -> $bdate| KW_CONSTANT time= Number unit0= identifier (interval= KW_INTERVAL numerator= Number DIVIDE unit1= identifier )? -> {interval != null}? ^( TOK_CONSTANT $time $unit0) ^( TOK_INTERVAL $numerator $unit1) -> ^( TOK_CONSTANT $time $unit0) | KW_AFTER sdate= startDate (interval= KW_INTERVAL numerator= Number DIVIDE unit2= identifier )? -> {interval != null}? $sdate ^( TOK_INTERVAL $numerator $unit2) -> $sdate)
            int alt26=3;
            switch ( input.LA(1) ) {
            case Number:
                {
                alt26=1;
                }
                break;
            case KW_CONSTANT:
                {
                alt26=2;
                }
                break;
            case KW_AFTER:
                {
                alt26=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;

            }

            switch (alt26) {
                case 1 :
                    // SelectClauseParser.g:180:6: bdate= betweenDate
                    {
                    pushFollow(FOLLOW_betweenDate_in_incrementalArgs1112);
                    bdate=betweenDate();

                    state._fsp--;

                    stream_betweenDate.add(bdate.getTree());

                    // AST REWRITE
                    // elements: bdate
                    // token labels: 
                    // rule labels: retval, bdate
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_bdate=new RewriteRuleSubtreeStream(adaptor,"rule bdate",bdate!=null?bdate.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 181:5: -> $bdate
                    {
                        adaptor.addChild(root_0, stream_bdate.nextTree());

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // SelectClauseParser.g:182:7: KW_CONSTANT time= Number unit0= identifier (interval= KW_INTERVAL numerator= Number DIVIDE unit1= identifier )?
                    {
                    KW_CONSTANT79=(Token)match(input,KW_CONSTANT,FOLLOW_KW_CONSTANT_in_incrementalArgs1131);  
                    stream_KW_CONSTANT.add(KW_CONSTANT79);


                    time=(Token)match(input,Number,FOLLOW_Number_in_incrementalArgs1135);  
                    stream_Number.add(time);


                    pushFollow(FOLLOW_identifier_in_incrementalArgs1139);
                    unit0=gHiveParser.identifier();

                    state._fsp--;

                    stream_identifier.add(unit0.getTree());

                    // SelectClauseParser.g:182:48: (interval= KW_INTERVAL numerator= Number DIVIDE unit1= identifier )?
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==KW_INTERVAL) ) {
                        alt24=1;
                    }
                    switch (alt24) {
                        case 1 :
                            // SelectClauseParser.g:182:49: interval= KW_INTERVAL numerator= Number DIVIDE unit1= identifier
                            {
                            interval=(Token)match(input,KW_INTERVAL,FOLLOW_KW_INTERVAL_in_incrementalArgs1144);  
                            stream_KW_INTERVAL.add(interval);


                            numerator=(Token)match(input,Number,FOLLOW_Number_in_incrementalArgs1148);  
                            stream_Number.add(numerator);


                            DIVIDE80=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_incrementalArgs1150);  
                            stream_DIVIDE.add(DIVIDE80);


                            pushFollow(FOLLOW_identifier_in_incrementalArgs1154);
                            unit1=gHiveParser.identifier();

                            state._fsp--;

                            stream_identifier.add(unit1.getTree());

                            }
                            break;

                    }


                    // AST REWRITE
                    // elements: time, numerator, unit0, unit0, unit1, time
                    // token labels: time, numerator
                    // rule labels: retval, unit1, unit0
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_time=new RewriteRuleTokenStream(adaptor,"token time",time);
                    RewriteRuleTokenStream stream_numerator=new RewriteRuleTokenStream(adaptor,"token numerator",numerator);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_unit1=new RewriteRuleSubtreeStream(adaptor,"rule unit1",unit1!=null?unit1.tree:null);
                    RewriteRuleSubtreeStream stream_unit0=new RewriteRuleSubtreeStream(adaptor,"rule unit0",unit0!=null?unit0.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 183:6: -> {interval != null}? ^( TOK_CONSTANT $time $unit0) ^( TOK_INTERVAL $numerator $unit1)
                    if (interval != null) {
                        // SelectClauseParser.g:183:27: ^( TOK_CONSTANT $time $unit0)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(TOK_CONSTANT, "TOK_CONSTANT")
                        , root_1);

                        adaptor.addChild(root_1, stream_time.nextNode());

                        adaptor.addChild(root_1, stream_unit0.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                        // SelectClauseParser.g:183:58: ^( TOK_INTERVAL $numerator $unit1)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(TOK_INTERVAL, "TOK_INTERVAL")
                        , root_1);

                        adaptor.addChild(root_1, stream_numerator.nextNode());

                        adaptor.addChild(root_1, stream_unit1.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    else // 184:6: -> ^( TOK_CONSTANT $time $unit0)
                    {
                        // SelectClauseParser.g:184:8: ^( TOK_CONSTANT $time $unit0)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(TOK_CONSTANT, "TOK_CONSTANT")
                        , root_1);

                        adaptor.addChild(root_1, stream_time.nextNode());

                        adaptor.addChild(root_1, stream_unit0.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // SelectClauseParser.g:185:7: KW_AFTER sdate= startDate (interval= KW_INTERVAL numerator= Number DIVIDE unit2= identifier )?
                    {
                    KW_AFTER81=(Token)match(input,KW_AFTER,FOLLOW_KW_AFTER_in_incrementalArgs1213);  
                    stream_KW_AFTER.add(KW_AFTER81);


                    pushFollow(FOLLOW_startDate_in_incrementalArgs1217);
                    sdate=startDate();

                    state._fsp--;

                    stream_startDate.add(sdate.getTree());

                    // SelectClauseParser.g:185:32: (interval= KW_INTERVAL numerator= Number DIVIDE unit2= identifier )?
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( (LA25_0==KW_INTERVAL) ) {
                        alt25=1;
                    }
                    switch (alt25) {
                        case 1 :
                            // SelectClauseParser.g:185:33: interval= KW_INTERVAL numerator= Number DIVIDE unit2= identifier
                            {
                            interval=(Token)match(input,KW_INTERVAL,FOLLOW_KW_INTERVAL_in_incrementalArgs1222);  
                            stream_KW_INTERVAL.add(interval);


                            numerator=(Token)match(input,Number,FOLLOW_Number_in_incrementalArgs1226);  
                            stream_Number.add(numerator);


                            DIVIDE82=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_incrementalArgs1228);  
                            stream_DIVIDE.add(DIVIDE82);


                            pushFollow(FOLLOW_identifier_in_incrementalArgs1232);
                            unit2=gHiveParser.identifier();

                            state._fsp--;

                            stream_identifier.add(unit2.getTree());

                            }
                            break;

                    }


                    // AST REWRITE
                    // elements: sdate, numerator, unit2, sdate
                    // token labels: numerator
                    // rule labels: retval, unit2, sdate
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_numerator=new RewriteRuleTokenStream(adaptor,"token numerator",numerator);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_unit2=new RewriteRuleSubtreeStream(adaptor,"rule unit2",unit2!=null?unit2.tree:null);
                    RewriteRuleSubtreeStream stream_sdate=new RewriteRuleSubtreeStream(adaptor,"rule sdate",sdate!=null?sdate.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 186:6: -> {interval != null}? $sdate ^( TOK_INTERVAL $numerator $unit2)
                    if (interval != null) {
                        adaptor.addChild(root_0, stream_sdate.nextTree());

                        // SelectClauseParser.g:186:35: ^( TOK_INTERVAL $numerator $unit2)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(TOK_INTERVAL, "TOK_INTERVAL")
                        , root_1);

                        adaptor.addChild(root_1, stream_numerator.nextNode());

                        adaptor.addChild(root_1, stream_unit2.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    else // 187:6: -> $sdate
                    {
                        adaptor.addChild(root_0, stream_sdate.nextTree());

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "incrementalArgs"


    public static class intervalUnit_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "intervalUnit"
    // SelectClauseParser.g:190:1: intervalUnit : ( KW_HOUR | KW_ SECOND | KW_DAY | KW_WEEK | KW_MONTH | KW_MINUTE );
    public final HiveParser_SelectClauseParser.intervalUnit_return intervalUnit() throws RecognitionException {
        HiveParser_SelectClauseParser.intervalUnit_return retval = new HiveParser_SelectClauseParser.intervalUnit_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token KW_HOUR83=null;
        Token KW_84=null;
        Token SECOND85=null;
        Token KW_DAY86=null;
        Token KW_WEEK87=null;
        Token KW_MONTH88=null;
        Token KW_MINUTE89=null;

        CommonTree KW_HOUR83_tree=null;
        CommonTree KW_84_tree=null;
        CommonTree SECOND85_tree=null;
        CommonTree KW_DAY86_tree=null;
        CommonTree KW_WEEK87_tree=null;
        CommonTree KW_MONTH88_tree=null;
        CommonTree KW_MINUTE89_tree=null;

         gParent.msgs.push("interval Unit"); 
        try {
            // SelectClauseParser.g:193:5: ( KW_HOUR | KW_ SECOND | KW_DAY | KW_WEEK | KW_MONTH | KW_MINUTE )
            int alt27=6;
            switch ( input.LA(1) ) {
            case KW_HOUR:
                {
                alt27=1;
                }
                break;
            case KW_:
                {
                alt27=2;
                }
                break;
            case KW_DAY:
                {
                alt27=3;
                }
                break;
            case KW_WEEK:
                {
                alt27=4;
                }
                break;
            case KW_MONTH:
                {
                alt27=5;
                }
                break;
            case KW_MINUTE:
                {
                alt27=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;

            }

            switch (alt27) {
                case 1 :
                    // SelectClauseParser.g:194:5: KW_HOUR
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    KW_HOUR83=(Token)match(input,KW_HOUR,FOLLOW_KW_HOUR_in_intervalUnit1296); 
                    KW_HOUR83_tree = 
                    (CommonTree)adaptor.create(KW_HOUR83)
                    ;
                    adaptor.addChild(root_0, KW_HOUR83_tree);


                    }
                    break;
                case 2 :
                    // SelectClauseParser.g:194:15: KW_ SECOND
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    KW_84=(Token)match(input,KW_,FOLLOW_KW__in_intervalUnit1300); 
                    KW_84_tree = 
                    (CommonTree)adaptor.create(KW_84)
                    ;
                    adaptor.addChild(root_0, KW_84_tree);


                    SECOND85=(Token)match(input,SECOND,FOLLOW_SECOND_in_intervalUnit1302); 
                    SECOND85_tree = 
                    (CommonTree)adaptor.create(SECOND85)
                    ;
                    adaptor.addChild(root_0, SECOND85_tree);


                    }
                    break;
                case 3 :
                    // SelectClauseParser.g:194:28: KW_DAY
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    KW_DAY86=(Token)match(input,KW_DAY,FOLLOW_KW_DAY_in_intervalUnit1306); 
                    KW_DAY86_tree = 
                    (CommonTree)adaptor.create(KW_DAY86)
                    ;
                    adaptor.addChild(root_0, KW_DAY86_tree);


                    }
                    break;
                case 4 :
                    // SelectClauseParser.g:194:38: KW_WEEK
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    KW_WEEK87=(Token)match(input,KW_WEEK,FOLLOW_KW_WEEK_in_intervalUnit1311); 
                    KW_WEEK87_tree = 
                    (CommonTree)adaptor.create(KW_WEEK87)
                    ;
                    adaptor.addChild(root_0, KW_WEEK87_tree);


                    }
                    break;
                case 5 :
                    // SelectClauseParser.g:194:48: KW_MONTH
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    KW_MONTH88=(Token)match(input,KW_MONTH,FOLLOW_KW_MONTH_in_intervalUnit1315); 
                    KW_MONTH88_tree = 
                    (CommonTree)adaptor.create(KW_MONTH88)
                    ;
                    adaptor.addChild(root_0, KW_MONTH88_tree);


                    }
                    break;
                case 6 :
                    // SelectClauseParser.g:194:59: KW_MINUTE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    KW_MINUTE89=(Token)match(input,KW_MINUTE,FOLLOW_KW_MINUTE_in_intervalUnit1319); 
                    KW_MINUTE89_tree = 
                    (CommonTree)adaptor.create(KW_MINUTE89)
                    ;
                    adaptor.addChild(root_0, KW_MINUTE89_tree);


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "intervalUnit"


    public static class betweenDate_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "betweenDate"
    // SelectClauseParser.g:197:1: betweenDate : stime= dateArgs MINUS etime= dateArgs -> ^( TOK_STARTTIME $stime) ^( TOK_STOPTIME $etime) ;
    public final HiveParser_SelectClauseParser.betweenDate_return betweenDate() throws RecognitionException {
        HiveParser_SelectClauseParser.betweenDate_return retval = new HiveParser_SelectClauseParser.betweenDate_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token MINUS90=null;
        HiveParser_SelectClauseParser.dateArgs_return stime =null;

        HiveParser_SelectClauseParser.dateArgs_return etime =null;


        CommonTree MINUS90_tree=null;
        RewriteRuleTokenStream stream_MINUS=new RewriteRuleTokenStream(adaptor,"token MINUS");
        RewriteRuleSubtreeStream stream_dateArgs=new RewriteRuleSubtreeStream(adaptor,"rule dateArgs");
         gParent.msgs.push("between Date "); 
        try {
            // SelectClauseParser.g:200:5: (stime= dateArgs MINUS etime= dateArgs -> ^( TOK_STARTTIME $stime) ^( TOK_STOPTIME $etime) )
            // SelectClauseParser.g:201:5: stime= dateArgs MINUS etime= dateArgs
            {
            pushFollow(FOLLOW_dateArgs_in_betweenDate1357);
            stime=dateArgs();

            state._fsp--;

            stream_dateArgs.add(stime.getTree());

            MINUS90=(Token)match(input,MINUS,FOLLOW_MINUS_in_betweenDate1359);  
            stream_MINUS.add(MINUS90);


            pushFollow(FOLLOW_dateArgs_in_betweenDate1364);
            etime=dateArgs();

            state._fsp--;

            stream_dateArgs.add(etime.getTree());

            // AST REWRITE
            // elements: etime, stime
            // token labels: 
            // rule labels: retval, stime, etime
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_stime=new RewriteRuleSubtreeStream(adaptor,"rule stime",stime!=null?stime.tree:null);
            RewriteRuleSubtreeStream stream_etime=new RewriteRuleSubtreeStream(adaptor,"rule etime",etime!=null?etime.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 202:5: -> ^( TOK_STARTTIME $stime) ^( TOK_STOPTIME $etime)
            {
                // SelectClauseParser.g:202:7: ^( TOK_STARTTIME $stime)
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(TOK_STARTTIME, "TOK_STARTTIME")
                , root_1);

                adaptor.addChild(root_1, stream_stime.nextTree());

                adaptor.addChild(root_0, root_1);
                }

                // SelectClauseParser.g:202:32: ^( TOK_STOPTIME $etime)
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(TOK_STOPTIME, "TOK_STOPTIME")
                , root_1);

                adaptor.addChild(root_1, stream_etime.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "betweenDate"


    public static class startDate_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "startDate"
    // SelectClauseParser.g:205:1: startDate : stime= dateArgs -> ^( TOK_STARTTIME $stime) ;
    public final HiveParser_SelectClauseParser.startDate_return startDate() throws RecognitionException {
        HiveParser_SelectClauseParser.startDate_return retval = new HiveParser_SelectClauseParser.startDate_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        HiveParser_SelectClauseParser.dateArgs_return stime =null;


        RewriteRuleSubtreeStream stream_dateArgs=new RewriteRuleSubtreeStream(adaptor,"rule dateArgs");
         gParent.msgs.push("startDate "); 
        try {
            // SelectClauseParser.g:208:5: (stime= dateArgs -> ^( TOK_STARTTIME $stime) )
            // SelectClauseParser.g:209:6: stime= dateArgs
            {
            pushFollow(FOLLOW_dateArgs_in_startDate1418);
            stime=dateArgs();

            state._fsp--;

            stream_dateArgs.add(stime.getTree());

            // AST REWRITE
            // elements: stime
            // token labels: 
            // rule labels: retval, stime
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_stime=new RewriteRuleSubtreeStream(adaptor,"rule stime",stime!=null?stime.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 210:5: -> ^( TOK_STARTTIME $stime)
            {
                // SelectClauseParser.g:210:7: ^( TOK_STARTTIME $stime)
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(TOK_STARTTIME, "TOK_STARTTIME")
                , root_1);

                adaptor.addChild(root_1, stream_stime.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "startDate"


    public static class dateArgs_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "dateArgs"
    // SelectClauseParser.g:213:1: dateArgs : it= indefinite_date (c= COMMA et= explicit_time )? -> {c != null}? ^( TOK_DATETIME $it $et) -> ^( TOK_DATETIME $it) ;
    public final HiveParser_SelectClauseParser.dateArgs_return dateArgs() throws RecognitionException {
        HiveParser_SelectClauseParser.dateArgs_return retval = new HiveParser_SelectClauseParser.dateArgs_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token c=null;
        HiveParser_SelectClauseParser.indefinite_date_return it =null;

        HiveParser_SelectClauseParser.explicit_time_return et =null;


        CommonTree c_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_indefinite_date=new RewriteRuleSubtreeStream(adaptor,"rule indefinite_date");
        RewriteRuleSubtreeStream stream_explicit_time=new RewriteRuleSubtreeStream(adaptor,"rule explicit_time");
         gParent.msgs.push("incremental arguments"); 
        try {
            // SelectClauseParser.g:216:5: (it= indefinite_date (c= COMMA et= explicit_time )? -> {c != null}? ^( TOK_DATETIME $it $et) -> ^( TOK_DATETIME $it) )
            // SelectClauseParser.g:217:6: it= indefinite_date (c= COMMA et= explicit_time )?
            {
            pushFollow(FOLLOW_indefinite_date_in_dateArgs1465);
            it=indefinite_date();

            state._fsp--;

            stream_indefinite_date.add(it.getTree());

            // SelectClauseParser.g:217:25: (c= COMMA et= explicit_time )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==COMMA) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // SelectClauseParser.g:217:26: c= COMMA et= explicit_time
                    {
                    c=(Token)match(input,COMMA,FOLLOW_COMMA_in_dateArgs1470);  
                    stream_COMMA.add(c);


                    pushFollow(FOLLOW_explicit_time_in_dateArgs1474);
                    et=explicit_time();

                    state._fsp--;

                    stream_explicit_time.add(et.getTree());

                    }
                    break;

            }


            // AST REWRITE
            // elements: et, it, it
            // token labels: 
            // rule labels: retval, it, et
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_it=new RewriteRuleSubtreeStream(adaptor,"rule it",it!=null?it.tree:null);
            RewriteRuleSubtreeStream stream_et=new RewriteRuleSubtreeStream(adaptor,"rule et",et!=null?et.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 218:6: -> {c != null}? ^( TOK_DATETIME $it $et)
            if (c != null) {
                // SelectClauseParser.g:218:20: ^( TOK_DATETIME $it $et)
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(TOK_DATETIME, "TOK_DATETIME")
                , root_1);

                adaptor.addChild(root_1, stream_it.nextTree());

                adaptor.addChild(root_1, stream_et.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            else // 219:6: -> ^( TOK_DATETIME $it)
            {
                // SelectClauseParser.g:219:8: ^( TOK_DATETIME $it)
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(TOK_DATETIME, "TOK_DATETIME")
                , root_1);

                adaptor.addChild(root_1, stream_it.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "dateArgs"


    public static class indefinite_date_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "indefinite_date"
    // SelectClauseParser.g:222:1: indefinite_date : (expr+= year_or_month )* day= Number -> ^( TOK_DATE ( $expr)* $day) ;
    public final HiveParser_SelectClauseParser.indefinite_date_return indefinite_date() throws RecognitionException {
        HiveParser_SelectClauseParser.indefinite_date_return retval = new HiveParser_SelectClauseParser.indefinite_date_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token day=null;
        List list_expr=null;
        RuleReturnScope expr = null;
        CommonTree day_tree=null;
        RewriteRuleTokenStream stream_Number=new RewriteRuleTokenStream(adaptor,"token Number");
        RewriteRuleSubtreeStream stream_year_or_month=new RewriteRuleSubtreeStream(adaptor,"rule year_or_month");
         gParent.msgs.push("indefinite_time"); 
        try {
            // SelectClauseParser.g:225:5: ( (expr+= year_or_month )* day= Number -> ^( TOK_DATE ( $expr)* $day) )
            // SelectClauseParser.g:226:4: (expr+= year_or_month )* day= Number
            {
            // SelectClauseParser.g:226:4: (expr+= year_or_month )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==Number) ) {
                    int LA29_1 = input.LA(2);

                    if ( (LA29_1==DIVIDE) ) {
                        alt29=1;
                    }


                }


                switch (alt29) {
            	case 1 :
            	    // SelectClauseParser.g:226:5: expr+= year_or_month
            	    {
            	    pushFollow(FOLLOW_year_or_month_in_indefinite_date1544);
            	    expr=year_or_month();

            	    state._fsp--;

            	    stream_year_or_month.add(expr.getTree());
            	    if (list_expr==null) list_expr=new ArrayList();
            	    list_expr.add(expr.getTree());


            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);


            day=(Token)match(input,Number,FOLLOW_Number_in_indefinite_date1550);  
            stream_Number.add(day);


            // AST REWRITE
            // elements: day, expr
            // token labels: day
            // rule labels: retval
            // token list labels: 
            // rule list labels: expr
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_day=new RewriteRuleTokenStream(adaptor,"token day",day);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"token expr",list_expr);
            root_0 = (CommonTree)adaptor.nil();
            // 227:6: -> ^( TOK_DATE ( $expr)* $day)
            {
                // SelectClauseParser.g:227:8: ^( TOK_DATE ( $expr)* $day)
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(TOK_DATE, "TOK_DATE")
                , root_1);

                // SelectClauseParser.g:227:21: ( $expr)*
                while ( stream_expr.hasNext() ) {
                    adaptor.addChild(root_1, stream_expr.nextTree());

                }
                stream_expr.reset();

                adaptor.addChild(root_1, stream_day.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "indefinite_date"


    public static class year_or_month_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "year_or_month"
    // SelectClauseParser.g:230:1: year_or_month : Number DIVIDE ;
    public final HiveParser_SelectClauseParser.year_or_month_return year_or_month() throws RecognitionException {
        HiveParser_SelectClauseParser.year_or_month_return retval = new HiveParser_SelectClauseParser.year_or_month_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token Number91=null;
        Token DIVIDE92=null;

        CommonTree Number91_tree=null;
        CommonTree DIVIDE92_tree=null;

         gParent.msgs.push("year_month"); 
        try {
            // SelectClauseParser.g:233:6: ( Number DIVIDE )
            // SelectClauseParser.g:234:7: Number DIVIDE
            {
            root_0 = (CommonTree)adaptor.nil();


            Number91=(Token)match(input,Number,FOLLOW_Number_in_year_or_month1604); 
            Number91_tree = 
            (CommonTree)adaptor.create(Number91)
            ;
            adaptor.addChild(root_0, Number91_tree);


            DIVIDE92=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_year_or_month1606); 
            DIVIDE92_tree = 
            (CommonTree)adaptor.create(DIVIDE92)
            ;
            adaptor.addChild(root_0, DIVIDE92_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "year_or_month"


    public static class explicit_time_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "explicit_time"
    // SelectClauseParser.g:237:1: explicit_time : hour= Number COLON minute= Number ( COLON second= Number )? -> ^( TOK_TIME $hour $minute ( $second)? ) ;
    public final HiveParser_SelectClauseParser.explicit_time_return explicit_time() throws RecognitionException {
        HiveParser_SelectClauseParser.explicit_time_return retval = new HiveParser_SelectClauseParser.explicit_time_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token hour=null;
        Token minute=null;
        Token second=null;
        Token COLON93=null;
        Token COLON94=null;

        CommonTree hour_tree=null;
        CommonTree minute_tree=null;
        CommonTree second_tree=null;
        CommonTree COLON93_tree=null;
        CommonTree COLON94_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_Number=new RewriteRuleTokenStream(adaptor,"token Number");

         gParent.msgs.push("explicit_time"); 
        try {
            // SelectClauseParser.g:240:2: (hour= Number COLON minute= Number ( COLON second= Number )? -> ^( TOK_TIME $hour $minute ( $second)? ) )
            // SelectClauseParser.g:240:4: hour= Number COLON minute= Number ( COLON second= Number )?
            {
            hour=(Token)match(input,Number,FOLLOW_Number_in_explicit_time1639);  
            stream_Number.add(hour);


            COLON93=(Token)match(input,COLON,FOLLOW_COLON_in_explicit_time1641);  
            stream_COLON.add(COLON93);


            minute=(Token)match(input,Number,FOLLOW_Number_in_explicit_time1645);  
            stream_Number.add(minute);


            // SelectClauseParser.g:240:36: ( COLON second= Number )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==COLON) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // SelectClauseParser.g:240:37: COLON second= Number
                    {
                    COLON94=(Token)match(input,COLON,FOLLOW_COLON_in_explicit_time1648);  
                    stream_COLON.add(COLON94);


                    second=(Token)match(input,Number,FOLLOW_Number_in_explicit_time1652);  
                    stream_Number.add(second);


                    }
                    break;

            }


            // AST REWRITE
            // elements: minute, second, hour
            // token labels: minute, second, hour
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_minute=new RewriteRuleTokenStream(adaptor,"token minute",minute);
            RewriteRuleTokenStream stream_second=new RewriteRuleTokenStream(adaptor,"token second",second);
            RewriteRuleTokenStream stream_hour=new RewriteRuleTokenStream(adaptor,"token hour",hour);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 241:7: -> ^( TOK_TIME $hour $minute ( $second)? )
            {
                // SelectClauseParser.g:241:10: ^( TOK_TIME $hour $minute ( $second)? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(TOK_TIME, "TOK_TIME")
                , root_1);

                adaptor.addChild(root_1, stream_hour.nextNode());

                adaptor.addChild(root_1, stream_minute.nextNode());

                // SelectClauseParser.g:241:36: ( $second)?
                if ( stream_second.hasNext() ) {
                    adaptor.addChild(root_1, stream_second.nextNode());

                }
                stream_second.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "explicit_time"


    public static class window_clause_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "window_clause"
    // SelectClauseParser.g:246:1: window_clause : KW_WINDOW window_defn ( COMMA window_defn )* -> ^( KW_WINDOW ( window_defn )+ ) ;
    public final HiveParser_SelectClauseParser.window_clause_return window_clause() throws RecognitionException {
        HiveParser_SelectClauseParser.window_clause_return retval = new HiveParser_SelectClauseParser.window_clause_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token KW_WINDOW95=null;
        Token COMMA97=null;
        HiveParser_SelectClauseParser.window_defn_return window_defn96 =null;

        HiveParser_SelectClauseParser.window_defn_return window_defn98 =null;


        CommonTree KW_WINDOW95_tree=null;
        CommonTree COMMA97_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_KW_WINDOW=new RewriteRuleTokenStream(adaptor,"token KW_WINDOW");
        RewriteRuleSubtreeStream stream_window_defn=new RewriteRuleSubtreeStream(adaptor,"rule window_defn");
         gParent.msgs.push("window_clause"); 
        try {
            // SelectClauseParser.g:249:3: ( KW_WINDOW window_defn ( COMMA window_defn )* -> ^( KW_WINDOW ( window_defn )+ ) )
            // SelectClauseParser.g:250:3: KW_WINDOW window_defn ( COMMA window_defn )*
            {
            KW_WINDOW95=(Token)match(input,KW_WINDOW,FOLLOW_KW_WINDOW_in_window_clause1705);  
            stream_KW_WINDOW.add(KW_WINDOW95);


            pushFollow(FOLLOW_window_defn_in_window_clause1707);
            window_defn96=window_defn();

            state._fsp--;

            stream_window_defn.add(window_defn96.getTree());

            // SelectClauseParser.g:250:25: ( COMMA window_defn )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==COMMA) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // SelectClauseParser.g:250:26: COMMA window_defn
            	    {
            	    COMMA97=(Token)match(input,COMMA,FOLLOW_COMMA_in_window_clause1710);  
            	    stream_COMMA.add(COMMA97);


            	    pushFollow(FOLLOW_window_defn_in_window_clause1712);
            	    window_defn98=window_defn();

            	    state._fsp--;

            	    stream_window_defn.add(window_defn98.getTree());

            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);


            // AST REWRITE
            // elements: window_defn, KW_WINDOW
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 250:46: -> ^( KW_WINDOW ( window_defn )+ )
            {
                // SelectClauseParser.g:250:49: ^( KW_WINDOW ( window_defn )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                stream_KW_WINDOW.nextNode()
                , root_1);

                if ( !(stream_window_defn.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_window_defn.hasNext() ) {
                    adaptor.addChild(root_1, stream_window_defn.nextTree());

                }
                stream_window_defn.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "window_clause"


    public static class window_defn_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "window_defn"
    // SelectClauseParser.g:253:1: window_defn : Identifier KW_AS window_specification -> ^( TOK_WINDOWDEF Identifier window_specification ) ;
    public final HiveParser_SelectClauseParser.window_defn_return window_defn() throws RecognitionException {
        HiveParser_SelectClauseParser.window_defn_return retval = new HiveParser_SelectClauseParser.window_defn_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token Identifier99=null;
        Token KW_AS100=null;
        HiveParser_SelectClauseParser.window_specification_return window_specification101 =null;


        CommonTree Identifier99_tree=null;
        CommonTree KW_AS100_tree=null;
        RewriteRuleTokenStream stream_KW_AS=new RewriteRuleTokenStream(adaptor,"token KW_AS");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleSubtreeStream stream_window_specification=new RewriteRuleSubtreeStream(adaptor,"rule window_specification");
         gParent.msgs.push("window_defn"); 
        try {
            // SelectClauseParser.g:256:3: ( Identifier KW_AS window_specification -> ^( TOK_WINDOWDEF Identifier window_specification ) )
            // SelectClauseParser.g:257:3: Identifier KW_AS window_specification
            {
            Identifier99=(Token)match(input,Identifier,FOLLOW_Identifier_in_window_defn1748);  
            stream_Identifier.add(Identifier99);


            KW_AS100=(Token)match(input,KW_AS,FOLLOW_KW_AS_in_window_defn1750);  
            stream_KW_AS.add(KW_AS100);


            pushFollow(FOLLOW_window_specification_in_window_defn1752);
            window_specification101=window_specification();

            state._fsp--;

            stream_window_specification.add(window_specification101.getTree());

            // AST REWRITE
            // elements: Identifier, window_specification
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 257:41: -> ^( TOK_WINDOWDEF Identifier window_specification )
            {
                // SelectClauseParser.g:257:44: ^( TOK_WINDOWDEF Identifier window_specification )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(TOK_WINDOWDEF, "TOK_WINDOWDEF")
                , root_1);

                adaptor.addChild(root_1, 
                stream_Identifier.nextNode()
                );

                adaptor.addChild(root_1, stream_window_specification.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "window_defn"


    public static class window_specification_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "window_specification"
    // SelectClauseParser.g:260:1: window_specification : ( Identifier | ( LPAREN ( Identifier )? ( partitioningSpec )? ( window_frame )? RPAREN ) ) -> ^( TOK_WINDOWSPEC ( Identifier )? ( partitioningSpec )? ( window_frame )? ) ;
    public final HiveParser_SelectClauseParser.window_specification_return window_specification() throws RecognitionException {
        HiveParser_SelectClauseParser.window_specification_return retval = new HiveParser_SelectClauseParser.window_specification_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token Identifier102=null;
        Token LPAREN103=null;
        Token Identifier104=null;
        Token RPAREN107=null;
        HiveParser_FromClauseParser.partitioningSpec_return partitioningSpec105 =null;

        HiveParser_SelectClauseParser.window_frame_return window_frame106 =null;


        CommonTree Identifier102_tree=null;
        CommonTree LPAREN103_tree=null;
        CommonTree Identifier104_tree=null;
        CommonTree RPAREN107_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_window_frame=new RewriteRuleSubtreeStream(adaptor,"rule window_frame");
        RewriteRuleSubtreeStream stream_partitioningSpec=new RewriteRuleSubtreeStream(adaptor,"rule partitioningSpec");
         gParent.msgs.push("window_specification"); 
        try {
            // SelectClauseParser.g:263:3: ( ( Identifier | ( LPAREN ( Identifier )? ( partitioningSpec )? ( window_frame )? RPAREN ) ) -> ^( TOK_WINDOWSPEC ( Identifier )? ( partitioningSpec )? ( window_frame )? ) )
            // SelectClauseParser.g:264:3: ( Identifier | ( LPAREN ( Identifier )? ( partitioningSpec )? ( window_frame )? RPAREN ) )
            {
            // SelectClauseParser.g:264:3: ( Identifier | ( LPAREN ( Identifier )? ( partitioningSpec )? ( window_frame )? RPAREN ) )
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==Identifier) ) {
                alt35=1;
            }
            else if ( (LA35_0==LPAREN) ) {
                alt35=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;

            }
            switch (alt35) {
                case 1 :
                    // SelectClauseParser.g:264:4: Identifier
                    {
                    Identifier102=(Token)match(input,Identifier,FOLLOW_Identifier_in_window_specification1788);  
                    stream_Identifier.add(Identifier102);


                    }
                    break;
                case 2 :
                    // SelectClauseParser.g:264:17: ( LPAREN ( Identifier )? ( partitioningSpec )? ( window_frame )? RPAREN )
                    {
                    // SelectClauseParser.g:264:17: ( LPAREN ( Identifier )? ( partitioningSpec )? ( window_frame )? RPAREN )
                    // SelectClauseParser.g:264:19: LPAREN ( Identifier )? ( partitioningSpec )? ( window_frame )? RPAREN
                    {
                    LPAREN103=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_window_specification1794);  
                    stream_LPAREN.add(LPAREN103);


                    // SelectClauseParser.g:264:26: ( Identifier )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0==Identifier) ) {
                        alt32=1;
                    }
                    switch (alt32) {
                        case 1 :
                            // SelectClauseParser.g:264:26: Identifier
                            {
                            Identifier104=(Token)match(input,Identifier,FOLLOW_Identifier_in_window_specification1796);  
                            stream_Identifier.add(Identifier104);


                            }
                            break;

                    }


                    // SelectClauseParser.g:264:38: ( partitioningSpec )?
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==KW_CLUSTER||LA33_0==KW_DISTRIBUTE||LA33_0==KW_ORDER||LA33_0==KW_PARTITION||LA33_0==KW_SORT) ) {
                        alt33=1;
                    }
                    switch (alt33) {
                        case 1 :
                            // SelectClauseParser.g:264:38: partitioningSpec
                            {
                            pushFollow(FOLLOW_partitioningSpec_in_window_specification1799);
                            partitioningSpec105=gHiveParser.partitioningSpec();

                            state._fsp--;

                            stream_partitioningSpec.add(partitioningSpec105.getTree());

                            }
                            break;

                    }


                    // SelectClauseParser.g:264:56: ( window_frame )?
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==KW_RANGE||LA34_0==KW_ROWS) ) {
                        alt34=1;
                    }
                    switch (alt34) {
                        case 1 :
                            // SelectClauseParser.g:264:56: window_frame
                            {
                            pushFollow(FOLLOW_window_frame_in_window_specification1802);
                            window_frame106=window_frame();

                            state._fsp--;

                            stream_window_frame.add(window_frame106.getTree());

                            }
                            break;

                    }


                    RPAREN107=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_window_specification1805);  
                    stream_RPAREN.add(RPAREN107);


                    }


                    }
                    break;

            }


            // AST REWRITE
            // elements: Identifier, partitioningSpec, window_frame
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 264:79: -> ^( TOK_WINDOWSPEC ( Identifier )? ( partitioningSpec )? ( window_frame )? )
            {
                // SelectClauseParser.g:264:82: ^( TOK_WINDOWSPEC ( Identifier )? ( partitioningSpec )? ( window_frame )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(TOK_WINDOWSPEC, "TOK_WINDOWSPEC")
                , root_1);

                // SelectClauseParser.g:264:99: ( Identifier )?
                if ( stream_Identifier.hasNext() ) {
                    adaptor.addChild(root_1, 
                    stream_Identifier.nextNode()
                    );

                }
                stream_Identifier.reset();

                // SelectClauseParser.g:264:111: ( partitioningSpec )?
                if ( stream_partitioningSpec.hasNext() ) {
                    adaptor.addChild(root_1, stream_partitioningSpec.nextTree());

                }
                stream_partitioningSpec.reset();

                // SelectClauseParser.g:264:129: ( window_frame )?
                if ( stream_window_frame.hasNext() ) {
                    adaptor.addChild(root_1, stream_window_frame.nextTree());

                }
                stream_window_frame.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "window_specification"


    public static class window_frame_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "window_frame"
    // SelectClauseParser.g:267:1: window_frame : ( window_range_expression | window_value_expression );
    public final HiveParser_SelectClauseParser.window_frame_return window_frame() throws RecognitionException {
        HiveParser_SelectClauseParser.window_frame_return retval = new HiveParser_SelectClauseParser.window_frame_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        HiveParser_SelectClauseParser.window_range_expression_return window_range_expression108 =null;

        HiveParser_SelectClauseParser.window_value_expression_return window_value_expression109 =null;



        try {
            // SelectClauseParser.g:267:14: ( window_range_expression | window_value_expression )
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==KW_ROWS) ) {
                alt36=1;
            }
            else if ( (LA36_0==KW_RANGE) ) {
                alt36=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;

            }
            switch (alt36) {
                case 1 :
                    // SelectClauseParser.g:268:2: window_range_expression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_window_range_expression_in_window_frame1832);
                    window_range_expression108=window_range_expression();

                    state._fsp--;

                    adaptor.addChild(root_0, window_range_expression108.getTree());

                    }
                    break;
                case 2 :
                    // SelectClauseParser.g:269:2: window_value_expression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_window_value_expression_in_window_frame1837);
                    window_value_expression109=window_value_expression();

                    state._fsp--;

                    adaptor.addChild(root_0, window_value_expression109.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "window_frame"


    public static class window_range_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "window_range_expression"
    // SelectClauseParser.g:272:1: window_range_expression : ( KW_ROWS sb= window_frame_start_boundary -> ^( TOK_WINDOWRANGE $sb) | KW_ROWS KW_BETWEEN s= window_frame_boundary KW_AND end= window_frame_boundary -> ^( TOK_WINDOWRANGE $s $end) );
    public final HiveParser_SelectClauseParser.window_range_expression_return window_range_expression() throws RecognitionException {
        HiveParser_SelectClauseParser.window_range_expression_return retval = new HiveParser_SelectClauseParser.window_range_expression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token KW_ROWS110=null;
        Token KW_ROWS111=null;
        Token KW_BETWEEN112=null;
        Token KW_AND113=null;
        HiveParser_SelectClauseParser.window_frame_start_boundary_return sb =null;

        HiveParser_SelectClauseParser.window_frame_boundary_return s =null;

        HiveParser_SelectClauseParser.window_frame_boundary_return end =null;


        CommonTree KW_ROWS110_tree=null;
        CommonTree KW_ROWS111_tree=null;
        CommonTree KW_BETWEEN112_tree=null;
        CommonTree KW_AND113_tree=null;
        RewriteRuleTokenStream stream_KW_BETWEEN=new RewriteRuleTokenStream(adaptor,"token KW_BETWEEN");
        RewriteRuleTokenStream stream_KW_ROWS=new RewriteRuleTokenStream(adaptor,"token KW_ROWS");
        RewriteRuleTokenStream stream_KW_AND=new RewriteRuleTokenStream(adaptor,"token KW_AND");
        RewriteRuleSubtreeStream stream_window_frame_boundary=new RewriteRuleSubtreeStream(adaptor,"rule window_frame_boundary");
        RewriteRuleSubtreeStream stream_window_frame_start_boundary=new RewriteRuleSubtreeStream(adaptor,"rule window_frame_start_boundary");
         gParent.msgs.push("window_range_expression"); 
        try {
            // SelectClauseParser.g:275:2: ( KW_ROWS sb= window_frame_start_boundary -> ^( TOK_WINDOWRANGE $sb) | KW_ROWS KW_BETWEEN s= window_frame_boundary KW_AND end= window_frame_boundary -> ^( TOK_WINDOWRANGE $s $end) )
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==KW_ROWS) ) {
                int LA37_1 = input.LA(2);

                if ( (LA37_1==KW_BETWEEN) ) {
                    alt37=2;
                }
                else if ( (LA37_1==KW_CURRENT||LA37_1==KW_UNBOUNDED||LA37_1==Number) ) {
                    alt37=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 37, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;

            }
            switch (alt37) {
                case 1 :
                    // SelectClauseParser.g:276:2: KW_ROWS sb= window_frame_start_boundary
                    {
                    KW_ROWS110=(Token)match(input,KW_ROWS,FOLLOW_KW_ROWS_in_window_range_expression1859);  
                    stream_KW_ROWS.add(KW_ROWS110);


                    pushFollow(FOLLOW_window_frame_start_boundary_in_window_range_expression1863);
                    sb=window_frame_start_boundary();

                    state._fsp--;

                    stream_window_frame_start_boundary.add(sb.getTree());

                    // AST REWRITE
                    // elements: sb
                    // token labels: 
                    // rule labels: retval, sb
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_sb=new RewriteRuleSubtreeStream(adaptor,"rule sb",sb!=null?sb.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 276:41: -> ^( TOK_WINDOWRANGE $sb)
                    {
                        // SelectClauseParser.g:276:44: ^( TOK_WINDOWRANGE $sb)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(TOK_WINDOWRANGE, "TOK_WINDOWRANGE")
                        , root_1);

                        adaptor.addChild(root_1, stream_sb.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // SelectClauseParser.g:277:2: KW_ROWS KW_BETWEEN s= window_frame_boundary KW_AND end= window_frame_boundary
                    {
                    KW_ROWS111=(Token)match(input,KW_ROWS,FOLLOW_KW_ROWS_in_window_range_expression1877);  
                    stream_KW_ROWS.add(KW_ROWS111);


                    KW_BETWEEN112=(Token)match(input,KW_BETWEEN,FOLLOW_KW_BETWEEN_in_window_range_expression1879);  
                    stream_KW_BETWEEN.add(KW_BETWEEN112);


                    pushFollow(FOLLOW_window_frame_boundary_in_window_range_expression1883);
                    s=window_frame_boundary();

                    state._fsp--;

                    stream_window_frame_boundary.add(s.getTree());

                    KW_AND113=(Token)match(input,KW_AND,FOLLOW_KW_AND_in_window_range_expression1885);  
                    stream_KW_AND.add(KW_AND113);


                    pushFollow(FOLLOW_window_frame_boundary_in_window_range_expression1889);
                    end=window_frame_boundary();

                    state._fsp--;

                    stream_window_frame_boundary.add(end.getTree());

                    // AST REWRITE
                    // elements: s, end
                    // token labels: 
                    // rule labels: retval, s, end
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"rule s",s!=null?s.tree:null);
                    RewriteRuleSubtreeStream stream_end=new RewriteRuleSubtreeStream(adaptor,"rule end",end!=null?end.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 277:78: -> ^( TOK_WINDOWRANGE $s $end)
                    {
                        // SelectClauseParser.g:277:81: ^( TOK_WINDOWRANGE $s $end)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(TOK_WINDOWRANGE, "TOK_WINDOWRANGE")
                        , root_1);

                        adaptor.addChild(root_1, stream_s.nextTree());

                        adaptor.addChild(root_1, stream_end.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "window_range_expression"


    public static class window_value_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "window_value_expression"
    // SelectClauseParser.g:280:1: window_value_expression : ( KW_RANGE sb= window_frame_start_boundary -> ^( TOK_WINDOWVALUES $sb) | KW_RANGE KW_BETWEEN s= window_frame_boundary KW_AND end= window_frame_boundary -> ^( TOK_WINDOWVALUES $s $end) );
    public final HiveParser_SelectClauseParser.window_value_expression_return window_value_expression() throws RecognitionException {
        HiveParser_SelectClauseParser.window_value_expression_return retval = new HiveParser_SelectClauseParser.window_value_expression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token KW_RANGE114=null;
        Token KW_RANGE115=null;
        Token KW_BETWEEN116=null;
        Token KW_AND117=null;
        HiveParser_SelectClauseParser.window_frame_start_boundary_return sb =null;

        HiveParser_SelectClauseParser.window_frame_boundary_return s =null;

        HiveParser_SelectClauseParser.window_frame_boundary_return end =null;


        CommonTree KW_RANGE114_tree=null;
        CommonTree KW_RANGE115_tree=null;
        CommonTree KW_BETWEEN116_tree=null;
        CommonTree KW_AND117_tree=null;
        RewriteRuleTokenStream stream_KW_BETWEEN=new RewriteRuleTokenStream(adaptor,"token KW_BETWEEN");
        RewriteRuleTokenStream stream_KW_AND=new RewriteRuleTokenStream(adaptor,"token KW_AND");
        RewriteRuleTokenStream stream_KW_RANGE=new RewriteRuleTokenStream(adaptor,"token KW_RANGE");
        RewriteRuleSubtreeStream stream_window_frame_boundary=new RewriteRuleSubtreeStream(adaptor,"rule window_frame_boundary");
        RewriteRuleSubtreeStream stream_window_frame_start_boundary=new RewriteRuleSubtreeStream(adaptor,"rule window_frame_start_boundary");
         gParent.msgs.push("window_value_expression"); 
        try {
            // SelectClauseParser.g:283:2: ( KW_RANGE sb= window_frame_start_boundary -> ^( TOK_WINDOWVALUES $sb) | KW_RANGE KW_BETWEEN s= window_frame_boundary KW_AND end= window_frame_boundary -> ^( TOK_WINDOWVALUES $s $end) )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==KW_RANGE) ) {
                int LA38_1 = input.LA(2);

                if ( (LA38_1==KW_BETWEEN) ) {
                    alt38=2;
                }
                else if ( (LA38_1==KW_CURRENT||LA38_1==KW_UNBOUNDED||LA38_1==Number) ) {
                    alt38=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 38, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;

            }
            switch (alt38) {
                case 1 :
                    // SelectClauseParser.g:284:2: KW_RANGE sb= window_frame_start_boundary
                    {
                    KW_RANGE114=(Token)match(input,KW_RANGE,FOLLOW_KW_RANGE_in_window_value_expression1923);  
                    stream_KW_RANGE.add(KW_RANGE114);


                    pushFollow(FOLLOW_window_frame_start_boundary_in_window_value_expression1927);
                    sb=window_frame_start_boundary();

                    state._fsp--;

                    stream_window_frame_start_boundary.add(sb.getTree());

                    // AST REWRITE
                    // elements: sb
                    // token labels: 
                    // rule labels: retval, sb
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_sb=new RewriteRuleSubtreeStream(adaptor,"rule sb",sb!=null?sb.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 284:42: -> ^( TOK_WINDOWVALUES $sb)
                    {
                        // SelectClauseParser.g:284:45: ^( TOK_WINDOWVALUES $sb)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(TOK_WINDOWVALUES, "TOK_WINDOWVALUES")
                        , root_1);

                        adaptor.addChild(root_1, stream_sb.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // SelectClauseParser.g:285:2: KW_RANGE KW_BETWEEN s= window_frame_boundary KW_AND end= window_frame_boundary
                    {
                    KW_RANGE115=(Token)match(input,KW_RANGE,FOLLOW_KW_RANGE_in_window_value_expression1941);  
                    stream_KW_RANGE.add(KW_RANGE115);


                    KW_BETWEEN116=(Token)match(input,KW_BETWEEN,FOLLOW_KW_BETWEEN_in_window_value_expression1943);  
                    stream_KW_BETWEEN.add(KW_BETWEEN116);


                    pushFollow(FOLLOW_window_frame_boundary_in_window_value_expression1947);
                    s=window_frame_boundary();

                    state._fsp--;

                    stream_window_frame_boundary.add(s.getTree());

                    KW_AND117=(Token)match(input,KW_AND,FOLLOW_KW_AND_in_window_value_expression1949);  
                    stream_KW_AND.add(KW_AND117);


                    pushFollow(FOLLOW_window_frame_boundary_in_window_value_expression1953);
                    end=window_frame_boundary();

                    state._fsp--;

                    stream_window_frame_boundary.add(end.getTree());

                    // AST REWRITE
                    // elements: s, end
                    // token labels: 
                    // rule labels: retval, s, end
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"rule s",s!=null?s.tree:null);
                    RewriteRuleSubtreeStream stream_end=new RewriteRuleSubtreeStream(adaptor,"rule end",end!=null?end.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 285:79: -> ^( TOK_WINDOWVALUES $s $end)
                    {
                        // SelectClauseParser.g:285:82: ^( TOK_WINDOWVALUES $s $end)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(TOK_WINDOWVALUES, "TOK_WINDOWVALUES")
                        , root_1);

                        adaptor.addChild(root_1, stream_s.nextTree());

                        adaptor.addChild(root_1, stream_end.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "window_value_expression"


    public static class window_frame_start_boundary_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "window_frame_start_boundary"
    // SelectClauseParser.g:288:1: window_frame_start_boundary : ( KW_UNBOUNDED KW_PRECEDING -> ^( KW_PRECEDING KW_UNBOUNDED ) | KW_CURRENT KW_ROW -> ^( KW_CURRENT ) | Number KW_PRECEDING -> ^( KW_PRECEDING Number ) );
    public final HiveParser_SelectClauseParser.window_frame_start_boundary_return window_frame_start_boundary() throws RecognitionException {
        HiveParser_SelectClauseParser.window_frame_start_boundary_return retval = new HiveParser_SelectClauseParser.window_frame_start_boundary_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token KW_UNBOUNDED118=null;
        Token KW_PRECEDING119=null;
        Token KW_CURRENT120=null;
        Token KW_ROW121=null;
        Token Number122=null;
        Token KW_PRECEDING123=null;

        CommonTree KW_UNBOUNDED118_tree=null;
        CommonTree KW_PRECEDING119_tree=null;
        CommonTree KW_CURRENT120_tree=null;
        CommonTree KW_ROW121_tree=null;
        CommonTree Number122_tree=null;
        CommonTree KW_PRECEDING123_tree=null;
        RewriteRuleTokenStream stream_KW_PRECEDING=new RewriteRuleTokenStream(adaptor,"token KW_PRECEDING");
        RewriteRuleTokenStream stream_Number=new RewriteRuleTokenStream(adaptor,"token Number");
        RewriteRuleTokenStream stream_KW_CURRENT=new RewriteRuleTokenStream(adaptor,"token KW_CURRENT");
        RewriteRuleTokenStream stream_KW_ROW=new RewriteRuleTokenStream(adaptor,"token KW_ROW");
        RewriteRuleTokenStream stream_KW_UNBOUNDED=new RewriteRuleTokenStream(adaptor,"token KW_UNBOUNDED");

         gParent.msgs.push("windowframestartboundary"); 
        try {
            // SelectClauseParser.g:291:3: ( KW_UNBOUNDED KW_PRECEDING -> ^( KW_PRECEDING KW_UNBOUNDED ) | KW_CURRENT KW_ROW -> ^( KW_CURRENT ) | Number KW_PRECEDING -> ^( KW_PRECEDING Number ) )
            int alt39=3;
            switch ( input.LA(1) ) {
            case KW_UNBOUNDED:
                {
                alt39=1;
                }
                break;
            case KW_CURRENT:
                {
                alt39=2;
                }
                break;
            case Number:
                {
                alt39=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;

            }

            switch (alt39) {
                case 1 :
                    // SelectClauseParser.g:292:3: KW_UNBOUNDED KW_PRECEDING
                    {
                    KW_UNBOUNDED118=(Token)match(input,KW_UNBOUNDED,FOLLOW_KW_UNBOUNDED_in_window_frame_start_boundary1988);  
                    stream_KW_UNBOUNDED.add(KW_UNBOUNDED118);


                    KW_PRECEDING119=(Token)match(input,KW_PRECEDING,FOLLOW_KW_PRECEDING_in_window_frame_start_boundary1990);  
                    stream_KW_PRECEDING.add(KW_PRECEDING119);


                    // AST REWRITE
                    // elements: KW_UNBOUNDED, KW_PRECEDING
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 292:30: -> ^( KW_PRECEDING KW_UNBOUNDED )
                    {
                        // SelectClauseParser.g:292:33: ^( KW_PRECEDING KW_UNBOUNDED )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        stream_KW_PRECEDING.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_KW_UNBOUNDED.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // SelectClauseParser.g:293:3: KW_CURRENT KW_ROW
                    {
                    KW_CURRENT120=(Token)match(input,KW_CURRENT,FOLLOW_KW_CURRENT_in_window_frame_start_boundary2006);  
                    stream_KW_CURRENT.add(KW_CURRENT120);


                    KW_ROW121=(Token)match(input,KW_ROW,FOLLOW_KW_ROW_in_window_frame_start_boundary2008);  
                    stream_KW_ROW.add(KW_ROW121);


                    // AST REWRITE
                    // elements: KW_CURRENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 293:22: -> ^( KW_CURRENT )
                    {
                        // SelectClauseParser.g:293:25: ^( KW_CURRENT )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        stream_KW_CURRENT.nextNode()
                        , root_1);

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // SelectClauseParser.g:294:3: Number KW_PRECEDING
                    {
                    Number122=(Token)match(input,Number,FOLLOW_Number_in_window_frame_start_boundary2021);  
                    stream_Number.add(Number122);


                    KW_PRECEDING123=(Token)match(input,KW_PRECEDING,FOLLOW_KW_PRECEDING_in_window_frame_start_boundary2023);  
                    stream_KW_PRECEDING.add(KW_PRECEDING123);


                    // AST REWRITE
                    // elements: KW_PRECEDING, Number
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 294:23: -> ^( KW_PRECEDING Number )
                    {
                        // SelectClauseParser.g:294:26: ^( KW_PRECEDING Number )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        stream_KW_PRECEDING.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_Number.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "window_frame_start_boundary"


    public static class window_frame_boundary_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "window_frame_boundary"
    // SelectClauseParser.g:297:1: window_frame_boundary : ( KW_UNBOUNDED (r= KW_PRECEDING |r= KW_FOLLOWING ) -> ^( $r KW_UNBOUNDED ) | KW_CURRENT KW_ROW -> ^( KW_CURRENT ) | Number (d= KW_PRECEDING |d= KW_FOLLOWING ) -> ^( $d Number ) );
    public final HiveParser_SelectClauseParser.window_frame_boundary_return window_frame_boundary() throws RecognitionException {
        HiveParser_SelectClauseParser.window_frame_boundary_return retval = new HiveParser_SelectClauseParser.window_frame_boundary_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token r=null;
        Token d=null;
        Token KW_UNBOUNDED124=null;
        Token KW_CURRENT125=null;
        Token KW_ROW126=null;
        Token Number127=null;

        CommonTree r_tree=null;
        CommonTree d_tree=null;
        CommonTree KW_UNBOUNDED124_tree=null;
        CommonTree KW_CURRENT125_tree=null;
        CommonTree KW_ROW126_tree=null;
        CommonTree Number127_tree=null;
        RewriteRuleTokenStream stream_KW_PRECEDING=new RewriteRuleTokenStream(adaptor,"token KW_PRECEDING");
        RewriteRuleTokenStream stream_Number=new RewriteRuleTokenStream(adaptor,"token Number");
        RewriteRuleTokenStream stream_KW_FOLLOWING=new RewriteRuleTokenStream(adaptor,"token KW_FOLLOWING");
        RewriteRuleTokenStream stream_KW_CURRENT=new RewriteRuleTokenStream(adaptor,"token KW_CURRENT");
        RewriteRuleTokenStream stream_KW_ROW=new RewriteRuleTokenStream(adaptor,"token KW_ROW");
        RewriteRuleTokenStream stream_KW_UNBOUNDED=new RewriteRuleTokenStream(adaptor,"token KW_UNBOUNDED");

         gParent.msgs.push("windowframeboundary"); 
        try {
            // SelectClauseParser.g:300:3: ( KW_UNBOUNDED (r= KW_PRECEDING |r= KW_FOLLOWING ) -> ^( $r KW_UNBOUNDED ) | KW_CURRENT KW_ROW -> ^( KW_CURRENT ) | Number (d= KW_PRECEDING |d= KW_FOLLOWING ) -> ^( $d Number ) )
            int alt42=3;
            switch ( input.LA(1) ) {
            case KW_UNBOUNDED:
                {
                alt42=1;
                }
                break;
            case KW_CURRENT:
                {
                alt42=2;
                }
                break;
            case Number:
                {
                alt42=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;

            }

            switch (alt42) {
                case 1 :
                    // SelectClauseParser.g:301:3: KW_UNBOUNDED (r= KW_PRECEDING |r= KW_FOLLOWING )
                    {
                    KW_UNBOUNDED124=(Token)match(input,KW_UNBOUNDED,FOLLOW_KW_UNBOUNDED_in_window_frame_boundary2054);  
                    stream_KW_UNBOUNDED.add(KW_UNBOUNDED124);


                    // SelectClauseParser.g:301:16: (r= KW_PRECEDING |r= KW_FOLLOWING )
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==KW_PRECEDING) ) {
                        alt40=1;
                    }
                    else if ( (LA40_0==KW_FOLLOWING) ) {
                        alt40=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 40, 0, input);

                        throw nvae;

                    }
                    switch (alt40) {
                        case 1 :
                            // SelectClauseParser.g:301:17: r= KW_PRECEDING
                            {
                            r=(Token)match(input,KW_PRECEDING,FOLLOW_KW_PRECEDING_in_window_frame_boundary2059);  
                            stream_KW_PRECEDING.add(r);


                            }
                            break;
                        case 2 :
                            // SelectClauseParser.g:301:32: r= KW_FOLLOWING
                            {
                            r=(Token)match(input,KW_FOLLOWING,FOLLOW_KW_FOLLOWING_in_window_frame_boundary2063);  
                            stream_KW_FOLLOWING.add(r);


                            }
                            break;

                    }


                    // AST REWRITE
                    // elements: r, KW_UNBOUNDED
                    // token labels: r
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_r=new RewriteRuleTokenStream(adaptor,"token r",r);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 301:49: -> ^( $r KW_UNBOUNDED )
                    {
                        // SelectClauseParser.g:301:52: ^( $r KW_UNBOUNDED )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_r.nextNode(), root_1);

                        adaptor.addChild(root_1, 
                        stream_KW_UNBOUNDED.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // SelectClauseParser.g:302:3: KW_CURRENT KW_ROW
                    {
                    KW_CURRENT125=(Token)match(input,KW_CURRENT,FOLLOW_KW_CURRENT_in_window_frame_boundary2081);  
                    stream_KW_CURRENT.add(KW_CURRENT125);


                    KW_ROW126=(Token)match(input,KW_ROW,FOLLOW_KW_ROW_in_window_frame_boundary2083);  
                    stream_KW_ROW.add(KW_ROW126);


                    // AST REWRITE
                    // elements: KW_CURRENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 302:22: -> ^( KW_CURRENT )
                    {
                        // SelectClauseParser.g:302:25: ^( KW_CURRENT )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        stream_KW_CURRENT.nextNode()
                        , root_1);

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // SelectClauseParser.g:303:3: Number (d= KW_PRECEDING |d= KW_FOLLOWING )
                    {
                    Number127=(Token)match(input,Number,FOLLOW_Number_in_window_frame_boundary2096);  
                    stream_Number.add(Number127);


                    // SelectClauseParser.g:303:10: (d= KW_PRECEDING |d= KW_FOLLOWING )
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==KW_PRECEDING) ) {
                        alt41=1;
                    }
                    else if ( (LA41_0==KW_FOLLOWING) ) {
                        alt41=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 41, 0, input);

                        throw nvae;

                    }
                    switch (alt41) {
                        case 1 :
                            // SelectClauseParser.g:303:11: d= KW_PRECEDING
                            {
                            d=(Token)match(input,KW_PRECEDING,FOLLOW_KW_PRECEDING_in_window_frame_boundary2101);  
                            stream_KW_PRECEDING.add(d);


                            }
                            break;
                        case 2 :
                            // SelectClauseParser.g:303:28: d= KW_FOLLOWING
                            {
                            d=(Token)match(input,KW_FOLLOWING,FOLLOW_KW_FOLLOWING_in_window_frame_boundary2107);  
                            stream_KW_FOLLOWING.add(d);


                            }
                            break;

                    }


                    // AST REWRITE
                    // elements: Number, d
                    // token labels: d
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_d=new RewriteRuleTokenStream(adaptor,"token d",d);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 303:45: -> ^( $d Number )
                    {
                        // SelectClauseParser.g:303:48: ^( $d Number )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_d.nextNode(), root_1);

                        adaptor.addChild(root_1, 
                        stream_Number.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

             gParent.msgs.pop(); 
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "window_frame_boundary"

    // Delegated rules


    protected DFA7 dfa7 = new DFA7(this);
    protected DFA16 dfa16 = new DFA16(this);
    protected DFA14 dfa14 = new DFA14(this);
    protected DFA19 dfa19 = new DFA19(this);
    protected DFA22 dfa22 = new DFA22(this);
    static final String DFA7_eotS =
        "\u0081\uffff";
    static final String DFA7_eofS =
        "\1\uffff\2\3\176\uffff";
    static final String DFA7_minS =
        "\1\32\2\12\23\uffff\1\7\45\uffff\1\7\104\uffff";
    static final String DFA7_maxS =
        "\1\u010d\2\u011b\23\uffff\1\u0124\45\uffff\1\u0124\104\uffff";
    static final String DFA7_acceptS =
        "\3\uffff\1\1\24\uffff\1\2\150\uffff";
    static final String DFA7_specialS =
        "\u0081\uffff}>";
    static final String[] DFA7_transitionS = {
            "\1\1\2\2\1\uffff\2\2\1\uffff\16\2\2\uffff\5\2\1\uffff\4\2\1"+
            "\uffff\2\2\1\uffff\1\2\1\uffff\2\2\1\uffff\16\2\1\uffff\4\2"+
            "\1\uffff\1\2\1\uffff\1\2\1\uffff\4\2\1\uffff\7\2\1\uffff\3\2"+
            "\1\uffff\1\2\1\uffff\4\2\1\uffff\2\2\1\uffff\15\2\1\uffff\3"+
            "\2\1\uffff\4\2\1\uffff\12\2\2\uffff\3\2\1\uffff\2\2\1\uffff"+
            "\4\2\1\uffff\1\2\1\uffff\6\2\1\uffff\1\2\1\uffff\5\2\2\uffff"+
            "\14\2\1\uffff\16\2\1\uffff\25\2\1\uffff\4\2\1\uffff\4\2\1\uffff"+
            "\4\2\1\uffff\3\2\1\uffff\12\2\1\uffff\1\2\2\uffff\1\2\1\uffff"+
            "\1\2",
            "\1\3\27\uffff\1\30\4\uffff\3\30\10\uffff\1\3\22\uffff\2\30"+
            "\1\uffff\1\30\12\uffff\1\3\1\30\22\uffff\1\30\4\uffff\1\3\4"+
            "\uffff\1\3\1\uffff\1\3\15\uffff\1\3\1\30\10\uffff\1\3\3\uffff"+
            "\1\3\11\uffff\1\26\17\uffff\1\3\30\uffff\1\3\1\uffff\1\3\12"+
            "\uffff\1\3\3\uffff\1\3\12\uffff\1\30\1\3\5\uffff\2\30\10\uffff"+
            "\2\30\11\uffff\1\3\1\30\13\uffff\1\30\2\uffff\1\3\1\uffff\1"+
            "\3\16\uffff\1\3",
            "\1\3\27\uffff\1\30\4\uffff\3\30\10\uffff\1\3\22\uffff\2\30"+
            "\1\uffff\1\30\12\uffff\1\3\1\30\22\uffff\1\30\4\uffff\1\3\4"+
            "\uffff\1\3\1\uffff\1\3\15\uffff\1\3\1\30\10\uffff\1\3\3\uffff"+
            "\1\3\11\uffff\1\74\17\uffff\1\3\30\uffff\1\3\1\uffff\1\3\12"+
            "\uffff\1\3\3\uffff\1\3\12\uffff\1\30\1\3\5\uffff\2\30\10\uffff"+
            "\2\30\11\uffff\1\3\1\30\13\uffff\1\30\2\uffff\1\3\1\uffff\1"+
            "\3\16\uffff\1\3",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\3\5\uffff\1\3\4\uffff\1\3\7\uffff\3\3\1\uffff\2\3\1\uffff"+
            "\25\3\1\uffff\4\3\1\uffff\2\3\1\uffff\1\3\1\uffff\2\3\1\uffff"+
            "\16\3\1\uffff\4\3\1\uffff\1\3\1\uffff\1\3\1\uffff\4\3\1\uffff"+
            "\7\3\1\uffff\3\3\1\uffff\1\3\1\uffff\4\3\1\uffff\20\3\1\uffff"+
            "\3\3\1\uffff\4\3\1\uffff\12\3\1\uffff\4\3\1\uffff\7\3\1\uffff"+
            "\1\3\1\uffff\6\3\1\uffff\1\3\1\uffff\5\3\2\uffff\14\3\1\uffff"+
            "\16\3\1\uffff\25\3\1\uffff\4\3\1\uffff\4\3\1\uffff\4\3\1\uffff"+
            "\3\3\1\uffff\12\3\1\uffff\1\3\2\uffff\1\3\1\uffff\1\3\1\uffff"+
            "\1\30\1\uffff\1\3\2\uffff\1\3\2\uffff\2\3\6\uffff\4\3\1\uffff"+
            "\1\3",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\3\5\uffff\1\3\4\uffff\1\3\7\uffff\3\3\1\uffff\2\3\1\uffff"+
            "\25\3\1\uffff\4\3\1\uffff\2\3\1\uffff\1\3\1\uffff\2\3\1\uffff"+
            "\16\3\1\uffff\4\3\1\uffff\1\3\1\uffff\1\3\1\uffff\4\3\1\uffff"+
            "\7\3\1\uffff\3\3\1\uffff\1\3\1\uffff\4\3\1\uffff\20\3\1\uffff"+
            "\3\3\1\uffff\4\3\1\uffff\12\3\1\uffff\4\3\1\uffff\7\3\1\uffff"+
            "\1\3\1\uffff\6\3\1\uffff\1\3\1\uffff\5\3\2\uffff\14\3\1\uffff"+
            "\16\3\1\uffff\25\3\1\uffff\4\3\1\uffff\4\3\1\uffff\4\3\1\uffff"+
            "\3\3\1\uffff\12\3\1\uffff\1\3\2\uffff\1\3\1\uffff\1\3\1\uffff"+
            "\1\30\1\uffff\1\3\2\uffff\1\3\2\uffff\2\3\6\uffff\4\3\1\uffff"+
            "\1\3",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }
        public String getDescription() {
            return "79:65: ( aliasList | columnNameTypeList )";
        }
    }
    static final String DFA16_eotS =
        "\u00e2\uffff";
    static final String DFA16_eofS =
        "\1\4\1\2\1\uffff\1\2\3\uffff\1\2\1\uffff\1\2\1\uffff\4\2\1\uffff"+
        "\2\2\u00d0\uffff";
    static final String DFA16_minS =
        "\2\12\1\uffff\1\12\3\uffff\1\12\1\uffff\1\12\1\uffff\4\12\1\uffff"+
        "\2\12\u00d0\uffff";
    static final String DFA16_maxS =
        "\2\u011b\1\uffff\1\u011b\3\uffff\1\u011b\1\uffff\1\u011b\1\uffff"+
        "\4\u011b\1\uffff\2\u011b\u00d0\uffff";
    static final String DFA16_acceptS =
        "\2\uffff\1\1\1\uffff\1\3\22\uffff\1\2\u00ca\uffff";
    static final String DFA16_specialS =
        "\u00e2\uffff}>";
    static final String[] DFA16_transitionS = {
            "\1\4\17\uffff\3\2\1\uffff\2\2\1\uffff\2\2\1\1\13\2\2\uffff\1"+
            "\2\1\13\3\2\1\uffff\4\2\1\uffff\2\2\1\uffff\1\2\1\uffff\2\2"+
            "\1\uffff\16\2\1\uffff\1\14\3\2\1\uffff\1\2\1\uffff\1\2\1\uffff"+
            "\4\2\1\uffff\7\2\1\uffff\3\2\1\4\1\2\1\uffff\2\2\1\7\1\2\1\4"+
            "\2\2\1\uffff\12\2\1\21\2\2\1\uffff\3\2\1\uffff\2\2\1\3\1\2\1"+
            "\uffff\1\2\1\16\10\2\1\uffff\1\4\3\2\1\uffff\2\2\1\uffff\4\2"+
            "\1\uffff\1\2\1\uffff\1\2\1\11\4\2\1\uffff\1\2\1\uffff\5\2\2"+
            "\uffff\14\2\1\4\16\2\1\4\13\2\1\15\11\2\1\uffff\4\2\1\uffff"+
            "\4\2\1\uffff\4\2\1\uffff\1\2\1\20\1\2\1\uffff\12\2\1\uffff\1"+
            "\2\1\uffff\1\4\1\2\1\4\1\2\15\uffff\1\4",
            "\1\2\17\uffff\3\2\1\uffff\2\2\1\uffff\16\2\2\uffff\5\2\1\uffff"+
            "\4\2\1\uffff\2\2\1\uffff\1\2\1\uffff\2\2\1\uffff\16\2\1\uffff"+
            "\4\2\1\uffff\1\2\1\uffff\1\2\1\uffff\4\2\1\uffff\7\2\1\uffff"+
            "\5\2\1\uffff\7\2\1\uffff\15\2\1\uffff\3\2\1\uffff\4\2\1\uffff"+
            "\12\2\1\uffff\4\2\1\uffff\2\2\1\uffff\4\2\1\uffff\1\2\1\uffff"+
            "\6\2\1\uffff\1\2\1\uffff\5\2\2\uffff\61\2\1\uffff\4\2\1\uffff"+
            "\4\2\1\uffff\4\2\1\uffff\3\2\1\uffff\12\2\1\uffff\1\2\1\uffff"+
            "\4\2\3\uffff\1\27\11\uffff\1\2",
            "",
            "\1\2\47\uffff\1\2\40\uffff\1\2\30\uffff\1\2\4\uffff\1\2\1\uffff"+
            "\1\2\15\uffff\1\2\11\uffff\1\2\3\uffff\1\2\11\uffff\1\2\17\uffff"+
            "\1\2\32\uffff\1\2\16\uffff\1\2\13\uffff\1\2\32\uffff\1\2\15"+
            "\uffff\1\4\1\uffff\1\2\1\uffff\1\2\16\uffff\1\2",
            "",
            "",
            "",
            "\1\2\42\uffff\1\4\4\uffff\1\2\40\uffff\1\2\30\uffff\1\2\4\uffff"+
            "\1\2\1\uffff\1\2\15\uffff\1\2\11\uffff\1\2\3\uffff\1\2\11\uffff"+
            "\1\2\17\uffff\1\2\32\uffff\1\2\16\uffff\1\2\13\uffff\1\2\32"+
            "\uffff\1\2\17\uffff\1\2\1\uffff\1\2\16\uffff\1\2",
            "",
            "\1\2\42\uffff\1\4\4\uffff\1\2\40\uffff\1\2\30\uffff\1\2\4\uffff"+
            "\1\2\1\uffff\1\2\15\uffff\1\2\11\uffff\1\2\3\uffff\1\2\11\uffff"+
            "\1\2\17\uffff\1\2\32\uffff\1\2\16\uffff\1\2\13\uffff\1\2\32"+
            "\uffff\1\2\17\uffff\1\2\1\uffff\1\2\16\uffff\1\2",
            "",
            "\1\2\42\uffff\1\4\4\uffff\1\2\40\uffff\1\2\30\uffff\1\2\4\uffff"+
            "\1\2\1\uffff\1\2\15\uffff\1\2\11\uffff\1\2\3\uffff\1\2\11\uffff"+
            "\1\2\17\uffff\1\2\32\uffff\1\2\16\uffff\1\2\13\uffff\1\2\32"+
            "\uffff\1\2\17\uffff\1\2\1\uffff\1\2\16\uffff\1\2",
            "\1\2\42\uffff\1\4\4\uffff\1\2\40\uffff\1\2\30\uffff\1\2\4\uffff"+
            "\1\2\1\uffff\1\2\15\uffff\1\2\11\uffff\1\2\3\uffff\1\2\11\uffff"+
            "\1\2\17\uffff\1\2\32\uffff\1\2\16\uffff\1\2\13\uffff\1\2\32"+
            "\uffff\1\2\17\uffff\1\2\1\uffff\1\2\16\uffff\1\2",
            "\1\2\42\uffff\1\4\4\uffff\1\2\40\uffff\1\2\30\uffff\1\2\4\uffff"+
            "\1\2\1\uffff\1\2\15\uffff\1\2\11\uffff\1\2\3\uffff\1\2\11\uffff"+
            "\1\2\17\uffff\1\2\32\uffff\1\2\16\uffff\1\2\13\uffff\1\2\32"+
            "\uffff\1\2\17\uffff\1\2\1\uffff\1\2\16\uffff\1\2",
            "\1\2\47\uffff\1\2\40\uffff\1\2\30\uffff\1\2\4\uffff\1\2\1\uffff"+
            "\1\2\15\uffff\1\2\11\uffff\1\2\3\uffff\1\2\11\uffff\1\2\17\uffff"+
            "\1\2\32\uffff\1\2\16\uffff\1\2\13\uffff\1\2\32\uffff\1\2\17"+
            "\uffff\1\2\1\uffff\1\2\12\uffff\1\4\3\uffff\1\2",
            "",
            "\1\2\22\uffff\1\4\24\uffff\1\2\40\uffff\1\2\30\uffff\1\2\4"+
            "\uffff\1\2\1\uffff\1\2\15\uffff\1\2\11\uffff\1\2\3\uffff\1\2"+
            "\11\uffff\1\2\17\uffff\1\2\32\uffff\1\2\16\uffff\1\2\13\uffff"+
            "\1\2\32\uffff\1\2\17\uffff\1\2\1\uffff\1\2\16\uffff\1\2",
            "\1\2\47\uffff\1\2\40\uffff\1\2\30\uffff\1\2\4\uffff\1\2\1\uffff"+
            "\1\2\15\uffff\1\2\3\uffff\1\4\5\uffff\1\2\3\uffff\1\2\11\uffff"+
            "\1\2\17\uffff\1\2\5\uffff\1\4\24\uffff\1\2\16\uffff\1\2\13\uffff"+
            "\1\2\32\uffff\1\2\17\uffff\1\2\1\uffff\1\2\16\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA16_eot = DFA.unpackEncodedString(DFA16_eotS);
    static final short[] DFA16_eof = DFA.unpackEncodedString(DFA16_eofS);
    static final char[] DFA16_min = DFA.unpackEncodedStringToUnsignedChars(DFA16_minS);
    static final char[] DFA16_max = DFA.unpackEncodedStringToUnsignedChars(DFA16_maxS);
    static final short[] DFA16_accept = DFA.unpackEncodedString(DFA16_acceptS);
    static final short[] DFA16_special = DFA.unpackEncodedString(DFA16_specialS);
    static final short[][] DFA16_transition;

    static {
        int numStates = DFA16_transitionS.length;
        DFA16_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA16_transition[i] = DFA.unpackEncodedString(DFA16_transitionS[i]);
        }
    }

    class DFA16 extends DFA {

        public DFA16(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 16;
            this.eot = DFA16_eot;
            this.eof = DFA16_eof;
            this.min = DFA16_min;
            this.max = DFA16_max;
            this.accept = DFA16_accept;
            this.special = DFA16_special;
            this.transition = DFA16_transition;
        }
        public String getDescription() {
            return "134:7: ( ( ( KW_AS )? identifier ) | ( KW_AS LPAREN identifier ( COMMA identifier )* RPAREN ) )?";
        }
    }
    static final String DFA14_eotS =
        "\u00ce\uffff";
    static final String DFA14_eofS =
        "\1\uffff\1\2\3\uffff\1\4\3\uffff\1\4\1\uffff\1\4\1\uffff\4\4\1\uffff"+
        "\2\4\u00ba\uffff";
    static final String DFA14_minS =
        "\1\32\1\12\3\uffff\1\12\3\uffff\1\12\1\uffff\1\12\1\uffff\4\12\1"+
        "\uffff\2\12\u00ba\uffff";
    static final String DFA14_maxS =
        "\1\u010d\1\u011b\3\uffff\1\u011b\3\uffff\1\u011b\1\uffff\1\u011b"+
        "\1\uffff\4\u011b\1\uffff\2\u011b\u00ba\uffff";
    static final String DFA14_acceptS =
        "\2\uffff\1\2\1\uffff\1\1\u00c9\uffff";
    static final String DFA14_specialS =
        "\u00ce\uffff}>";
    static final String[] DFA14_transitionS = {
            "\3\2\1\uffff\2\2\1\uffff\2\2\1\1\13\2\2\uffff\5\2\1\uffff\4"+
            "\2\1\uffff\2\2\1\uffff\1\2\1\uffff\2\2\1\uffff\16\2\1\uffff"+
            "\4\2\1\uffff\1\2\1\uffff\1\2\1\uffff\4\2\1\uffff\7\2\1\uffff"+
            "\3\2\1\uffff\1\2\1\uffff\4\2\1\uffff\2\2\1\uffff\15\2\1\uffff"+
            "\3\2\1\uffff\4\2\1\uffff\12\2\2\uffff\3\2\1\uffff\2\2\1\uffff"+
            "\4\2\1\uffff\1\2\1\uffff\6\2\1\uffff\1\2\1\uffff\5\2\2\uffff"+
            "\14\2\1\uffff\16\2\1\uffff\25\2\1\uffff\4\2\1\uffff\4\2\1\uffff"+
            "\4\2\1\uffff\3\2\1\uffff\12\2\1\uffff\1\2\2\uffff\1\2\1\uffff"+
            "\1\2",
            "\1\2\17\uffff\3\4\1\uffff\2\4\1\uffff\16\4\2\uffff\1\4\1\15"+
            "\3\4\1\uffff\4\4\1\uffff\2\4\1\uffff\1\4\1\uffff\2\4\1\uffff"+
            "\16\4\1\uffff\1\16\3\4\1\uffff\1\4\1\uffff\1\4\1\uffff\4\4\1"+
            "\uffff\7\4\1\uffff\3\4\1\2\1\4\1\uffff\2\4\1\11\1\4\1\2\2\4"+
            "\1\uffff\12\4\1\23\2\4\1\uffff\3\4\1\uffff\2\4\1\5\1\4\1\uffff"+
            "\1\4\1\20\10\4\1\uffff\1\2\3\4\1\uffff\2\4\1\uffff\4\4\1\uffff"+
            "\1\4\1\uffff\1\4\1\13\4\4\1\uffff\1\4\1\uffff\5\4\2\uffff\14"+
            "\4\1\2\16\4\1\2\13\4\1\17\11\4\1\uffff\4\4\1\uffff\4\4\1\uffff"+
            "\4\4\1\uffff\1\4\1\22\1\4\1\uffff\12\4\1\uffff\1\4\1\uffff\1"+
            "\2\1\4\1\2\1\4\15\uffff\1\2",
            "",
            "",
            "",
            "\1\4\47\uffff\1\4\40\uffff\1\4\30\uffff\1\4\4\uffff\1\4\1\uffff"+
            "\1\4\15\uffff\1\4\11\uffff\1\4\3\uffff\1\4\11\uffff\1\4\17\uffff"+
            "\1\4\32\uffff\1\4\16\uffff\1\4\13\uffff\1\4\32\uffff\1\4\15"+
            "\uffff\1\2\1\uffff\1\4\1\uffff\1\4\16\uffff\1\4",
            "",
            "",
            "",
            "\1\4\42\uffff\1\2\4\uffff\1\4\40\uffff\1\4\30\uffff\1\4\4\uffff"+
            "\1\4\1\uffff\1\4\15\uffff\1\4\11\uffff\1\4\3\uffff\1\4\11\uffff"+
            "\1\4\17\uffff\1\4\32\uffff\1\4\16\uffff\1\4\13\uffff\1\4\32"+
            "\uffff\1\4\17\uffff\1\4\1\uffff\1\4\16\uffff\1\4",
            "",
            "\1\4\42\uffff\1\2\4\uffff\1\4\40\uffff\1\4\30\uffff\1\4\4\uffff"+
            "\1\4\1\uffff\1\4\15\uffff\1\4\11\uffff\1\4\3\uffff\1\4\11\uffff"+
            "\1\4\17\uffff\1\4\32\uffff\1\4\16\uffff\1\4\13\uffff\1\4\32"+
            "\uffff\1\4\17\uffff\1\4\1\uffff\1\4\16\uffff\1\4",
            "",
            "\1\4\42\uffff\1\2\4\uffff\1\4\40\uffff\1\4\30\uffff\1\4\4\uffff"+
            "\1\4\1\uffff\1\4\15\uffff\1\4\11\uffff\1\4\3\uffff\1\4\11\uffff"+
            "\1\4\17\uffff\1\4\32\uffff\1\4\16\uffff\1\4\13\uffff\1\4\32"+
            "\uffff\1\4\17\uffff\1\4\1\uffff\1\4\16\uffff\1\4",
            "\1\4\42\uffff\1\2\4\uffff\1\4\40\uffff\1\4\30\uffff\1\4\4\uffff"+
            "\1\4\1\uffff\1\4\15\uffff\1\4\11\uffff\1\4\3\uffff\1\4\11\uffff"+
            "\1\4\17\uffff\1\4\32\uffff\1\4\16\uffff\1\4\13\uffff\1\4\32"+
            "\uffff\1\4\17\uffff\1\4\1\uffff\1\4\16\uffff\1\4",
            "\1\4\42\uffff\1\2\4\uffff\1\4\40\uffff\1\4\30\uffff\1\4\4\uffff"+
            "\1\4\1\uffff\1\4\15\uffff\1\4\11\uffff\1\4\3\uffff\1\4\11\uffff"+
            "\1\4\17\uffff\1\4\32\uffff\1\4\16\uffff\1\4\13\uffff\1\4\32"+
            "\uffff\1\4\17\uffff\1\4\1\uffff\1\4\16\uffff\1\4",
            "\1\4\47\uffff\1\4\40\uffff\1\4\30\uffff\1\4\4\uffff\1\4\1\uffff"+
            "\1\4\15\uffff\1\4\11\uffff\1\4\3\uffff\1\4\11\uffff\1\4\17\uffff"+
            "\1\4\32\uffff\1\4\16\uffff\1\4\13\uffff\1\4\32\uffff\1\4\17"+
            "\uffff\1\4\1\uffff\1\4\12\uffff\1\2\3\uffff\1\4",
            "",
            "\1\4\22\uffff\1\2\24\uffff\1\4\40\uffff\1\4\30\uffff\1\4\4"+
            "\uffff\1\4\1\uffff\1\4\15\uffff\1\4\11\uffff\1\4\3\uffff\1\4"+
            "\11\uffff\1\4\17\uffff\1\4\32\uffff\1\4\16\uffff\1\4\13\uffff"+
            "\1\4\32\uffff\1\4\17\uffff\1\4\1\uffff\1\4\16\uffff\1\4",
            "\1\4\47\uffff\1\4\40\uffff\1\4\30\uffff\1\4\4\uffff\1\4\1\uffff"+
            "\1\4\15\uffff\1\4\3\uffff\1\2\5\uffff\1\4\3\uffff\1\4\11\uffff"+
            "\1\4\17\uffff\1\4\5\uffff\1\2\24\uffff\1\4\16\uffff\1\4\13\uffff"+
            "\1\4\32\uffff\1\4\17\uffff\1\4\1\uffff\1\4\16\uffff\1\4",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA14_eot = DFA.unpackEncodedString(DFA14_eotS);
    static final short[] DFA14_eof = DFA.unpackEncodedString(DFA14_eofS);
    static final char[] DFA14_min = DFA.unpackEncodedStringToUnsignedChars(DFA14_minS);
    static final char[] DFA14_max = DFA.unpackEncodedStringToUnsignedChars(DFA14_maxS);
    static final short[] DFA14_accept = DFA.unpackEncodedString(DFA14_acceptS);
    static final short[] DFA14_special = DFA.unpackEncodedString(DFA14_specialS);
    static final short[][] DFA14_transition;

    static {
        int numStates = DFA14_transitionS.length;
        DFA14_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA14_transition[i] = DFA.unpackEncodedString(DFA14_transitionS[i]);
        }
    }

    class DFA14 extends DFA {

        public DFA14(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 14;
            this.eot = DFA14_eot;
            this.eof = DFA14_eof;
            this.min = DFA14_min;
            this.max = DFA14_max;
            this.accept = DFA14_accept;
            this.special = DFA14_special;
            this.transition = DFA14_transition;
        }
        public String getDescription() {
            return "134:9: ( KW_AS )?";
        }
    }
    static final String DFA19_eotS =
        "\u0081\uffff";
    static final String DFA19_eofS =
        "\1\uffff\2\3\176\uffff";
    static final String DFA19_minS =
        "\1\32\2\12\23\uffff\1\7\45\uffff\1\7\104\uffff";
    static final String DFA19_maxS =
        "\1\u010d\2\u011b\23\uffff\1\u0124\45\uffff\1\u0124\104\uffff";
    static final String DFA19_acceptS =
        "\3\uffff\1\1\24\uffff\1\2\150\uffff";
    static final String DFA19_specialS =
        "\u0081\uffff}>";
    static final String[] DFA19_transitionS = {
            "\1\1\2\2\1\uffff\2\2\1\uffff\16\2\2\uffff\5\2\1\uffff\4\2\1"+
            "\uffff\2\2\1\uffff\1\2\1\uffff\2\2\1\uffff\16\2\1\uffff\4\2"+
            "\1\uffff\1\2\1\uffff\1\2\1\uffff\4\2\1\uffff\7\2\1\uffff\3\2"+
            "\1\uffff\1\2\1\uffff\4\2\1\uffff\2\2\1\uffff\15\2\1\uffff\3"+
            "\2\1\uffff\4\2\1\uffff\12\2\2\uffff\3\2\1\uffff\2\2\1\uffff"+
            "\4\2\1\uffff\1\2\1\uffff\6\2\1\uffff\1\2\1\uffff\5\2\2\uffff"+
            "\14\2\1\uffff\16\2\1\uffff\25\2\1\uffff\4\2\1\uffff\4\2\1\uffff"+
            "\4\2\1\uffff\3\2\1\uffff\12\2\1\uffff\1\2\2\uffff\1\2\1\uffff"+
            "\1\2",
            "\1\3\27\uffff\1\30\4\uffff\3\30\10\uffff\1\3\22\uffff\2\30"+
            "\1\uffff\1\30\12\uffff\1\3\1\30\22\uffff\1\30\4\uffff\1\3\4"+
            "\uffff\1\3\1\uffff\1\3\15\uffff\1\3\1\30\10\uffff\1\3\3\uffff"+
            "\1\3\11\uffff\1\26\17\uffff\1\3\30\uffff\1\3\1\uffff\1\3\12"+
            "\uffff\1\3\3\uffff\1\3\12\uffff\1\30\1\3\5\uffff\2\30\10\uffff"+
            "\2\30\11\uffff\1\3\1\30\13\uffff\1\30\2\uffff\1\3\1\uffff\1"+
            "\3\16\uffff\1\3",
            "\1\3\27\uffff\1\30\4\uffff\3\30\10\uffff\1\3\22\uffff\2\30"+
            "\1\uffff\1\30\12\uffff\1\3\1\30\22\uffff\1\30\4\uffff\1\3\4"+
            "\uffff\1\3\1\uffff\1\3\15\uffff\1\3\1\30\10\uffff\1\3\3\uffff"+
            "\1\3\11\uffff\1\74\17\uffff\1\3\30\uffff\1\3\1\uffff\1\3\12"+
            "\uffff\1\3\3\uffff\1\3\12\uffff\1\30\1\3\5\uffff\2\30\10\uffff"+
            "\2\30\11\uffff\1\3\1\30\13\uffff\1\30\2\uffff\1\3\1\uffff\1"+
            "\3\16\uffff\1\3",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\3\5\uffff\1\3\4\uffff\1\3\7\uffff\3\3\1\uffff\2\3\1\uffff"+
            "\25\3\1\uffff\4\3\1\uffff\2\3\1\uffff\1\3\1\uffff\2\3\1\uffff"+
            "\16\3\1\uffff\4\3\1\uffff\1\3\1\uffff\1\3\1\uffff\4\3\1\uffff"+
            "\7\3\1\uffff\3\3\1\uffff\1\3\1\uffff\4\3\1\uffff\20\3\1\uffff"+
            "\3\3\1\uffff\4\3\1\uffff\12\3\1\uffff\4\3\1\uffff\7\3\1\uffff"+
            "\1\3\1\uffff\6\3\1\uffff\1\3\1\uffff\5\3\2\uffff\14\3\1\uffff"+
            "\16\3\1\uffff\25\3\1\uffff\4\3\1\uffff\4\3\1\uffff\4\3\1\uffff"+
            "\3\3\1\uffff\12\3\1\uffff\1\3\2\uffff\1\3\1\uffff\1\3\1\uffff"+
            "\1\30\1\uffff\1\3\2\uffff\1\3\2\uffff\2\3\6\uffff\4\3\1\uffff"+
            "\1\3",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\3\5\uffff\1\3\4\uffff\1\3\7\uffff\3\3\1\uffff\2\3\1\uffff"+
            "\25\3\1\uffff\4\3\1\uffff\2\3\1\uffff\1\3\1\uffff\2\3\1\uffff"+
            "\16\3\1\uffff\4\3\1\uffff\1\3\1\uffff\1\3\1\uffff\4\3\1\uffff"+
            "\7\3\1\uffff\3\3\1\uffff\1\3\1\uffff\4\3\1\uffff\20\3\1\uffff"+
            "\3\3\1\uffff\4\3\1\uffff\12\3\1\uffff\4\3\1\uffff\7\3\1\uffff"+
            "\1\3\1\uffff\6\3\1\uffff\1\3\1\uffff\5\3\2\uffff\14\3\1\uffff"+
            "\16\3\1\uffff\25\3\1\uffff\4\3\1\uffff\4\3\1\uffff\4\3\1\uffff"+
            "\3\3\1\uffff\12\3\1\uffff\1\3\2\uffff\1\3\1\uffff\1\3\1\uffff"+
            "\1\30\1\uffff\1\3\2\uffff\1\3\2\uffff\2\3\6\uffff\4\3\1\uffff"+
            "\1\3",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA19_eot = DFA.unpackEncodedString(DFA19_eotS);
    static final short[] DFA19_eof = DFA.unpackEncodedString(DFA19_eofS);
    static final char[] DFA19_min = DFA.unpackEncodedStringToUnsignedChars(DFA19_minS);
    static final char[] DFA19_max = DFA.unpackEncodedStringToUnsignedChars(DFA19_maxS);
    static final short[] DFA19_accept = DFA.unpackEncodedString(DFA19_acceptS);
    static final short[] DFA19_special = DFA.unpackEncodedString(DFA19_specialS);
    static final short[][] DFA19_transition;

    static {
        int numStates = DFA19_transitionS.length;
        DFA19_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA19_transition[i] = DFA.unpackEncodedString(DFA19_transitionS[i]);
        }
    }

    class DFA19 extends DFA {

        public DFA19(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 19;
            this.eot = DFA19_eot;
            this.eof = DFA19_eof;
            this.min = DFA19_min;
            this.max = DFA19_max;
            this.accept = DFA19_accept;
            this.special = DFA19_special;
            this.transition = DFA19_transition;
        }
        public String getDescription() {
            return "146:65: ( aliasList | columnNameTypeList )";
        }
    }
    static final String DFA22_eotS =
        "\u01dc\uffff";
    static final String DFA22_eofS =
        "\3\uffff\2\1\7\uffff\2\1\1\uffff\1\1\1\uffff\4\1\u01c7\uffff";
    static final String DFA22_minS =
        "\1\7\2\uffff\2\4\7\uffff\2\4\1\uffff\1\4\1\uffff\4\4\5\uffff\1\32"+
        "\60\uffff\1\32\55\uffff\1\32\56\uffff\1\32\57\uffff\1\32\56\uffff"+
        "\1\32\56\uffff\1\32\56\uffff\1\32\56\uffff\1\32\107\uffff";
    static final String DFA22_maxS =
        "\1\u0124\2\uffff\1\u011f\1\u0121\7\uffff\2\u011f\1\uffff\1\u011f"+
        "\1\uffff\4\u011f\5\uffff\1\u011f\60\uffff\1\u011f\55\uffff\1\u011f"+
        "\56\uffff\1\u011f\57\uffff\1\u011f\56\uffff\1\u011f\56\uffff\1\u011f"+
        "\56\uffff\1\u011f\56\uffff\1\u011f\107\uffff";
    static final String DFA22_acceptS =
        "\1\uffff\1\1\26\uffff\1\2\u01a9\uffff\2\1\1\uffff\2\1\1\uffff\2"+
        "\1\1\uffff\2\1\1\uffff\2\1\1\uffff\2\1\1\uffff\2\1\1\uffff\2\1\1"+
        "\uffff\2\1";
    static final String DFA22_specialS =
        "\u01dc\uffff}>";
    static final String[] DFA22_transitionS = {
            "\1\1\5\uffff\1\1\4\uffff\1\1\7\uffff\1\23\2\24\1\uffff\2\24"+
            "\1\uffff\1\24\1\17\14\24\2\1\5\24\1\uffff\4\24\1\uffff\2\24"+
            "\1\uffff\1\24\1\uffff\2\24\1\uffff\1\24\1\4\14\24\1\uffff\4"+
            "\24\1\uffff\1\24\1\uffff\1\24\1\uffff\4\24\1\uffff\1\24\1\15"+
            "\5\24\1\uffff\3\24\1\uffff\1\24\1\uffff\4\24\1\uffff\2\24\1"+
            "\1\15\24\1\uffff\3\24\1\uffff\4\24\1\uffff\12\24\1\uffff\1\1"+
            "\3\24\1\uffff\2\24\1\1\1\24\1\3\2\24\1\uffff\1\24\1\uffff\6"+
            "\24\1\uffff\1\24\1\uffff\5\24\2\uffff\14\24\1\uffff\16\24\1"+
            "\uffff\22\24\1\21\2\24\1\uffff\4\24\1\uffff\4\24\1\uffff\1\24"+
            "\1\14\2\24\1\uffff\2\24\1\22\1\uffff\12\24\1\uffff\1\24\2\uffff"+
            "\1\24\1\uffff\1\24\3\uffff\1\1\2\uffff\1\1\2\uffff\2\1\6\uffff"+
            "\1\30\3\1\1\uffff\1\1",
            "",
            "",
            "\3\1\3\uffff\1\1\3\uffff\2\1\1\uffff\1\32\2\uffff\2\1\1\uffff"+
            "\2\1\1\uffff\3\1\1\uffff\21\1\2\uffff\5\1\1\uffff\4\1\1\uffff"+
            "\2\1\1\uffff\1\1\1\uffff\2\1\1\uffff\16\1\1\uffff\4\1\1\uffff"+
            "\1\1\1\uffff\1\1\1\uffff\4\1\1\uffff\7\1\1\uffff\5\1\1\uffff"+
            "\7\1\1\uffff\15\1\1\uffff\3\1\1\uffff\4\1\1\uffff\12\1\1\uffff"+
            "\4\1\1\uffff\7\1\1\uffff\10\1\1\uffff\1\1\1\uffff\5\1\2\uffff"+
            "\61\1\1\uffff\4\1\1\uffff\4\1\1\uffff\4\1\1\uffff\3\1\1\uffff"+
            "\12\1\1\uffff\1\1\1\uffff\4\1\1\uffff\4\1\1\uffff\3\1\1\uffff"+
            "\1\1\2\uffff\1\1\3\uffff\1\1",
            "\3\1\3\uffff\1\1\3\uffff\2\1\1\uffff\1\113\2\uffff\2\1\1\uffff"+
            "\2\1\1\uffff\3\1\1\uffff\21\1\2\uffff\5\1\1\uffff\4\1\1\uffff"+
            "\2\1\1\uffff\1\1\1\uffff\2\1\1\uffff\16\1\1\uffff\4\1\1\uffff"+
            "\1\1\1\uffff\1\1\1\uffff\4\1\1\uffff\7\1\1\uffff\5\1\1\uffff"+
            "\7\1\1\uffff\15\1\1\uffff\3\1\1\uffff\4\1\1\uffff\12\1\1\uffff"+
            "\4\1\1\uffff\7\1\1\uffff\10\1\1\uffff\1\1\1\uffff\5\1\2\uffff"+
            "\61\1\1\uffff\4\1\1\uffff\4\1\1\uffff\4\1\1\uffff\3\1\1\uffff"+
            "\12\1\1\uffff\1\1\1\uffff\4\1\1\uffff\4\1\1\uffff\3\1\1\uffff"+
            "\1\1\2\uffff\1\1\3\uffff\1\1\1\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\3\1\3\uffff\1\1\3\uffff\2\1\1\uffff\1\171\2\uffff\2\1\1\uffff"+
            "\2\1\1\uffff\3\1\1\uffff\21\1\2\uffff\5\1\1\uffff\4\1\1\uffff"+
            "\2\1\1\uffff\1\1\1\uffff\2\1\1\uffff\16\1\1\uffff\4\1\1\uffff"+
            "\1\1\1\uffff\1\1\1\uffff\4\1\1\uffff\7\1\1\uffff\5\1\1\uffff"+
            "\7\1\1\uffff\15\1\1\uffff\3\1\1\uffff\4\1\1\uffff\12\1\1\uffff"+
            "\4\1\1\uffff\7\1\1\uffff\10\1\1\uffff\1\1\1\uffff\5\1\2\uffff"+
            "\61\1\1\uffff\4\1\1\uffff\4\1\1\uffff\4\1\1\uffff\3\1\1\uffff"+
            "\12\1\1\uffff\1\1\1\uffff\4\1\1\uffff\4\1\1\uffff\3\1\1\uffff"+
            "\1\1\2\uffff\1\1\3\uffff\1\1",
            "\3\1\3\uffff\1\1\3\uffff\2\1\1\uffff\1\u00a8\2\uffff\2\1\1"+
            "\uffff\2\1\1\uffff\3\1\1\uffff\21\1\2\uffff\5\1\1\uffff\4\1"+
            "\1\uffff\2\1\1\uffff\1\1\1\uffff\2\1\1\uffff\16\1\1\uffff\4"+
            "\1\1\uffff\1\1\1\uffff\1\1\1\uffff\4\1\1\uffff\7\1\1\uffff\5"+
            "\1\1\uffff\7\1\1\uffff\15\1\1\uffff\3\1\1\uffff\4\1\1\uffff"+
            "\12\1\1\uffff\4\1\1\uffff\7\1\1\uffff\10\1\1\uffff\1\1\1\uffff"+
            "\5\1\2\uffff\61\1\1\uffff\4\1\1\uffff\4\1\1\uffff\4\1\1\uffff"+
            "\3\1\1\uffff\12\1\1\uffff\1\1\1\uffff\4\1\1\uffff\4\1\1\uffff"+
            "\3\1\1\uffff\1\1\2\uffff\1\1\3\uffff\1\1",
            "",
            "\3\1\3\uffff\1\1\3\uffff\2\1\1\uffff\1\u00d8\2\uffff\2\1\1"+
            "\uffff\2\1\1\uffff\3\1\1\uffff\21\1\2\uffff\5\1\1\uffff\4\1"+
            "\1\uffff\2\1\1\uffff\1\1\1\uffff\2\1\1\uffff\16\1\1\uffff\4"+
            "\1\1\uffff\1\1\1\uffff\1\1\1\uffff\4\1\1\uffff\7\1\1\uffff\5"+
            "\1\1\uffff\7\1\1\uffff\15\1\1\uffff\3\1\1\uffff\4\1\1\uffff"+
            "\12\1\1\uffff\4\1\1\uffff\7\1\1\uffff\10\1\1\uffff\1\1\1\uffff"+
            "\5\1\2\uffff\61\1\1\uffff\4\1\1\uffff\4\1\1\uffff\4\1\1\uffff"+
            "\3\1\1\uffff\12\1\1\uffff\1\1\1\uffff\4\1\1\uffff\4\1\1\uffff"+
            "\3\1\1\uffff\1\1\2\uffff\1\1\3\uffff\1\1",
            "",
            "\3\1\3\uffff\1\1\3\uffff\2\1\1\uffff\1\u0107\2\uffff\2\1\1"+
            "\uffff\2\1\1\uffff\3\1\1\uffff\21\1\2\uffff\5\1\1\uffff\4\1"+
            "\1\uffff\2\1\1\uffff\1\1\1\uffff\2\1\1\uffff\16\1\1\uffff\4"+
            "\1\1\uffff\1\1\1\uffff\1\1\1\uffff\4\1\1\uffff\7\1\1\uffff\5"+
            "\1\1\uffff\7\1\1\uffff\15\1\1\uffff\3\1\1\uffff\4\1\1\uffff"+
            "\12\1\1\uffff\4\1\1\uffff\7\1\1\uffff\10\1\1\uffff\1\1\1\uffff"+
            "\5\1\2\uffff\61\1\1\uffff\4\1\1\uffff\4\1\1\uffff\4\1\1\uffff"+
            "\3\1\1\uffff\12\1\1\uffff\1\1\1\uffff\4\1\1\uffff\4\1\1\uffff"+
            "\3\1\1\uffff\1\1\2\uffff\1\1\3\uffff\1\1",
            "\3\1\3\uffff\1\1\3\uffff\2\1\1\uffff\1\u0136\2\uffff\2\1\1"+
            "\uffff\2\1\1\uffff\3\1\1\uffff\21\1\2\uffff\5\1\1\uffff\4\1"+
            "\1\uffff\2\1\1\uffff\1\1\1\uffff\2\1\1\uffff\16\1\1\uffff\4"+
            "\1\1\uffff\1\1\1\uffff\1\1\1\uffff\4\1\1\uffff\7\1\1\uffff\5"+
            "\1\1\uffff\7\1\1\uffff\15\1\1\uffff\3\1\1\uffff\4\1\1\uffff"+
            "\12\1\1\uffff\4\1\1\uffff\7\1\1\uffff\10\1\1\uffff\1\1\1\uffff"+
            "\5\1\2\uffff\61\1\1\uffff\4\1\1\uffff\4\1\1\uffff\4\1\1\uffff"+
            "\3\1\1\uffff\12\1\1\uffff\1\1\1\uffff\4\1\1\uffff\4\1\1\uffff"+
            "\3\1\1\uffff\1\1\2\uffff\1\1\3\uffff\1\1",
            "\3\1\3\uffff\1\1\3\uffff\2\1\1\uffff\1\u0165\2\uffff\2\1\1"+
            "\uffff\2\1\1\uffff\3\1\1\uffff\21\1\2\uffff\5\1\1\uffff\4\1"+
            "\1\uffff\2\1\1\uffff\1\1\1\uffff\2\1\1\uffff\16\1\1\uffff\4"+
            "\1\1\uffff\1\1\1\uffff\1\1\1\uffff\4\1\1\uffff\7\1\1\uffff\5"+
            "\1\1\uffff\7\1\1\uffff\15\1\1\uffff\3\1\1\uffff\4\1\1\uffff"+
            "\12\1\1\uffff\4\1\1\uffff\7\1\1\uffff\10\1\1\uffff\1\1\1\uffff"+
            "\5\1\2\uffff\61\1\1\uffff\4\1\1\uffff\4\1\1\uffff\4\1\1\uffff"+
            "\3\1\1\uffff\12\1\1\uffff\1\1\1\uffff\4\1\1\uffff\4\1\1\uffff"+
            "\3\1\1\uffff\1\1\2\uffff\1\1\3\uffff\1\1",
            "\3\1\3\uffff\1\1\3\uffff\2\1\1\uffff\1\u0194\2\uffff\2\1\1"+
            "\uffff\2\1\1\uffff\3\1\1\uffff\21\1\2\uffff\5\1\1\uffff\4\1"+
            "\1\uffff\2\1\1\uffff\1\1\1\uffff\2\1\1\uffff\16\1\1\uffff\4"+
            "\1\1\uffff\1\1\1\uffff\1\1\1\uffff\4\1\1\uffff\7\1\1\uffff\5"+
            "\1\1\uffff\7\1\1\uffff\15\1\1\uffff\3\1\1\uffff\4\1\1\uffff"+
            "\12\1\1\uffff\4\1\1\uffff\7\1\1\uffff\10\1\1\uffff\1\1\1\uffff"+
            "\5\1\2\uffff\61\1\1\uffff\4\1\1\uffff\4\1\1\uffff\4\1\1\uffff"+
            "\3\1\1\uffff\12\1\1\uffff\1\1\1\uffff\4\1\1\uffff\4\1\1\uffff"+
            "\3\1\1\uffff\1\1\2\uffff\1\1\3\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "\1\u01c2\2\u01c3\1\uffff\2\u01c3\1\uffff\16\u01c3\2\uffff\5"+
            "\u01c3\1\uffff\4\u01c3\1\uffff\2\u01c3\1\uffff\1\u01c3\1\uffff"+
            "\2\u01c3\1\uffff\16\u01c3\1\uffff\4\u01c3\1\uffff\1\u01c3\1"+
            "\uffff\1\u01c3\1\uffff\4\u01c3\1\uffff\7\u01c3\1\uffff\3\u01c3"+
            "\1\uffff\1\u01c3\1\uffff\4\u01c3\1\uffff\2\u01c3\1\uffff\15"+
            "\u01c3\1\uffff\3\u01c3\1\uffff\4\u01c3\1\uffff\12\u01c3\2\uffff"+
            "\3\u01c3\1\uffff\2\u01c3\1\uffff\4\u01c3\1\uffff\1\u01c3\1\uffff"+
            "\6\u01c3\1\uffff\1\u01c3\1\uffff\5\u01c3\2\uffff\14\u01c3\1"+
            "\uffff\16\u01c3\1\uffff\25\u01c3\1\uffff\4\u01c3\1\uffff\4\u01c3"+
            "\1\uffff\4\u01c3\1\uffff\3\u01c3\1\uffff\12\u01c3\1\uffff\1"+
            "\u01c3\2\uffff\1\u01c3\1\uffff\1\u01c3\21\uffff\1\30",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u01c5\2\u01c6\1\uffff\2\u01c6\1\uffff\16\u01c6\2\uffff\5"+
            "\u01c6\1\uffff\4\u01c6\1\uffff\2\u01c6\1\uffff\1\u01c6\1\uffff"+
            "\2\u01c6\1\uffff\16\u01c6\1\uffff\4\u01c6\1\uffff\1\u01c6\1"+
            "\uffff\1\u01c6\1\uffff\4\u01c6\1\uffff\7\u01c6\1\uffff\3\u01c6"+
            "\1\uffff\1\u01c6\1\uffff\4\u01c6\1\uffff\2\u01c6\1\uffff\15"+
            "\u01c6\1\uffff\3\u01c6\1\uffff\4\u01c6\1\uffff\12\u01c6\2\uffff"+
            "\3\u01c6\1\uffff\2\u01c6\1\uffff\4\u01c6\1\uffff\1\u01c6\1\uffff"+
            "\6\u01c6\1\uffff\1\u01c6\1\uffff\5\u01c6\2\uffff\14\u01c6\1"+
            "\uffff\16\u01c6\1\uffff\25\u01c6\1\uffff\4\u01c6\1\uffff\4\u01c6"+
            "\1\uffff\4\u01c6\1\uffff\3\u01c6\1\uffff\12\u01c6\1\uffff\1"+
            "\u01c6\2\uffff\1\u01c6\1\uffff\1\u01c6\21\uffff\1\30",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u01c8\2\u01c9\1\uffff\2\u01c9\1\uffff\16\u01c9\2\uffff\5"+
            "\u01c9\1\uffff\4\u01c9\1\uffff\2\u01c9\1\uffff\1\u01c9\1\uffff"+
            "\2\u01c9\1\uffff\16\u01c9\1\uffff\4\u01c9\1\uffff\1\u01c9\1"+
            "\uffff\1\u01c9\1\uffff\4\u01c9\1\uffff\7\u01c9\1\uffff\3\u01c9"+
            "\1\uffff\1\u01c9\1\uffff\4\u01c9\1\uffff\2\u01c9\1\uffff\15"+
            "\u01c9\1\uffff\3\u01c9\1\uffff\4\u01c9\1\uffff\12\u01c9\2\uffff"+
            "\3\u01c9\1\uffff\2\u01c9\1\uffff\4\u01c9\1\uffff\1\u01c9\1\uffff"+
            "\6\u01c9\1\uffff\1\u01c9\1\uffff\5\u01c9\2\uffff\14\u01c9\1"+
            "\uffff\16\u01c9\1\uffff\25\u01c9\1\uffff\4\u01c9\1\uffff\4\u01c9"+
            "\1\uffff\4\u01c9\1\uffff\3\u01c9\1\uffff\12\u01c9\1\uffff\1"+
            "\u01c9\2\uffff\1\u01c9\1\uffff\1\u01c9\21\uffff\1\30",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u01cb\2\u01cc\1\uffff\2\u01cc\1\uffff\16\u01cc\2\uffff\5"+
            "\u01cc\1\uffff\4\u01cc\1\uffff\2\u01cc\1\uffff\1\u01cc\1\uffff"+
            "\2\u01cc\1\uffff\16\u01cc\1\uffff\4\u01cc\1\uffff\1\u01cc\1"+
            "\uffff\1\u01cc\1\uffff\4\u01cc\1\uffff\7\u01cc\1\uffff\3\u01cc"+
            "\1\uffff\1\u01cc\1\uffff\4\u01cc\1\uffff\2\u01cc\1\uffff\15"+
            "\u01cc\1\uffff\3\u01cc\1\uffff\4\u01cc\1\uffff\12\u01cc\2\uffff"+
            "\3\u01cc\1\uffff\2\u01cc\1\uffff\4\u01cc\1\uffff\1\u01cc\1\uffff"+
            "\6\u01cc\1\uffff\1\u01cc\1\uffff\5\u01cc\2\uffff\14\u01cc\1"+
            "\uffff\16\u01cc\1\uffff\25\u01cc\1\uffff\4\u01cc\1\uffff\4\u01cc"+
            "\1\uffff\4\u01cc\1\uffff\3\u01cc\1\uffff\12\u01cc\1\uffff\1"+
            "\u01cc\2\uffff\1\u01cc\1\uffff\1\u01cc\21\uffff\1\30",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u01ce\2\u01cf\1\uffff\2\u01cf\1\uffff\16\u01cf\2\uffff\5"+
            "\u01cf\1\uffff\4\u01cf\1\uffff\2\u01cf\1\uffff\1\u01cf\1\uffff"+
            "\2\u01cf\1\uffff\16\u01cf\1\uffff\4\u01cf\1\uffff\1\u01cf\1"+
            "\uffff\1\u01cf\1\uffff\4\u01cf\1\uffff\7\u01cf\1\uffff\3\u01cf"+
            "\1\uffff\1\u01cf\1\uffff\4\u01cf\1\uffff\2\u01cf\1\uffff\15"+
            "\u01cf\1\uffff\3\u01cf\1\uffff\4\u01cf\1\uffff\12\u01cf\2\uffff"+
            "\3\u01cf\1\uffff\2\u01cf\1\uffff\4\u01cf\1\uffff\1\u01cf\1\uffff"+
            "\6\u01cf\1\uffff\1\u01cf\1\uffff\5\u01cf\2\uffff\14\u01cf\1"+
            "\uffff\16\u01cf\1\uffff\25\u01cf\1\uffff\4\u01cf\1\uffff\4\u01cf"+
            "\1\uffff\4\u01cf\1\uffff\3\u01cf\1\uffff\12\u01cf\1\uffff\1"+
            "\u01cf\2\uffff\1\u01cf\1\uffff\1\u01cf\21\uffff\1\30",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u01d1\2\u01d2\1\uffff\2\u01d2\1\uffff\16\u01d2\2\uffff\5"+
            "\u01d2\1\uffff\4\u01d2\1\uffff\2\u01d2\1\uffff\1\u01d2\1\uffff"+
            "\2\u01d2\1\uffff\16\u01d2\1\uffff\4\u01d2\1\uffff\1\u01d2\1"+
            "\uffff\1\u01d2\1\uffff\4\u01d2\1\uffff\7\u01d2\1\uffff\3\u01d2"+
            "\1\uffff\1\u01d2\1\uffff\4\u01d2\1\uffff\2\u01d2\1\uffff\15"+
            "\u01d2\1\uffff\3\u01d2\1\uffff\4\u01d2\1\uffff\12\u01d2\2\uffff"+
            "\3\u01d2\1\uffff\2\u01d2\1\uffff\4\u01d2\1\uffff\1\u01d2\1\uffff"+
            "\6\u01d2\1\uffff\1\u01d2\1\uffff\5\u01d2\2\uffff\14\u01d2\1"+
            "\uffff\16\u01d2\1\uffff\25\u01d2\1\uffff\4\u01d2\1\uffff\4\u01d2"+
            "\1\uffff\4\u01d2\1\uffff\3\u01d2\1\uffff\12\u01d2\1\uffff\1"+
            "\u01d2\2\uffff\1\u01d2\1\uffff\1\u01d2\21\uffff\1\30",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u01d4\2\u01d5\1\uffff\2\u01d5\1\uffff\16\u01d5\2\uffff\5"+
            "\u01d5\1\uffff\4\u01d5\1\uffff\2\u01d5\1\uffff\1\u01d5\1\uffff"+
            "\2\u01d5\1\uffff\16\u01d5\1\uffff\4\u01d5\1\uffff\1\u01d5\1"+
            "\uffff\1\u01d5\1\uffff\4\u01d5\1\uffff\7\u01d5\1\uffff\3\u01d5"+
            "\1\uffff\1\u01d5\1\uffff\4\u01d5\1\uffff\2\u01d5\1\uffff\15"+
            "\u01d5\1\uffff\3\u01d5\1\uffff\4\u01d5\1\uffff\12\u01d5\2\uffff"+
            "\3\u01d5\1\uffff\2\u01d5\1\uffff\4\u01d5\1\uffff\1\u01d5\1\uffff"+
            "\6\u01d5\1\uffff\1\u01d5\1\uffff\5\u01d5\2\uffff\14\u01d5\1"+
            "\uffff\16\u01d5\1\uffff\25\u01d5\1\uffff\4\u01d5\1\uffff\4\u01d5"+
            "\1\uffff\4\u01d5\1\uffff\3\u01d5\1\uffff\12\u01d5\1\uffff\1"+
            "\u01d5\2\uffff\1\u01d5\1\uffff\1\u01d5\21\uffff\1\30",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u01d7\2\u01d8\1\uffff\2\u01d8\1\uffff\16\u01d8\2\uffff\5"+
            "\u01d8\1\uffff\4\u01d8\1\uffff\2\u01d8\1\uffff\1\u01d8\1\uffff"+
            "\2\u01d8\1\uffff\16\u01d8\1\uffff\4\u01d8\1\uffff\1\u01d8\1"+
            "\uffff\1\u01d8\1\uffff\4\u01d8\1\uffff\7\u01d8\1\uffff\3\u01d8"+
            "\1\uffff\1\u01d8\1\uffff\4\u01d8\1\uffff\2\u01d8\1\uffff\15"+
            "\u01d8\1\uffff\3\u01d8\1\uffff\4\u01d8\1\uffff\12\u01d8\2\uffff"+
            "\3\u01d8\1\uffff\2\u01d8\1\uffff\4\u01d8\1\uffff\1\u01d8\1\uffff"+
            "\6\u01d8\1\uffff\1\u01d8\1\uffff\5\u01d8\2\uffff\14\u01d8\1"+
            "\uffff\16\u01d8\1\uffff\25\u01d8\1\uffff\4\u01d8\1\uffff\4\u01d8"+
            "\1\uffff\4\u01d8\1\uffff\3\u01d8\1\uffff\12\u01d8\1\uffff\1"+
            "\u01d8\2\uffff\1\u01d8\1\uffff\1\u01d8\21\uffff\1\30",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u01da\2\u01db\1\uffff\2\u01db\1\uffff\16\u01db\2\uffff\5"+
            "\u01db\1\uffff\4\u01db\1\uffff\2\u01db\1\uffff\1\u01db\1\uffff"+
            "\2\u01db\1\uffff\16\u01db\1\uffff\4\u01db\1\uffff\1\u01db\1"+
            "\uffff\1\u01db\1\uffff\4\u01db\1\uffff\7\u01db\1\uffff\3\u01db"+
            "\1\uffff\1\u01db\1\uffff\4\u01db\1\uffff\2\u01db\1\uffff\15"+
            "\u01db\1\uffff\3\u01db\1\uffff\4\u01db\1\uffff\12\u01db\2\uffff"+
            "\3\u01db\1\uffff\2\u01db\1\uffff\4\u01db\1\uffff\1\u01db\1\uffff"+
            "\6\u01db\1\uffff\1\u01db\1\uffff\5\u01db\2\uffff\14\u01db\1"+
            "\uffff\16\u01db\1\uffff\25\u01db\1\uffff\4\u01db\1\uffff\4\u01db"+
            "\1\uffff\4\u01db\1\uffff\3\u01db\1\uffff\12\u01db\1\uffff\1"+
            "\u01db\2\uffff\1\u01db\1\uffff\1\u01db\21\uffff\1\30",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA22_eot = DFA.unpackEncodedString(DFA22_eotS);
    static final short[] DFA22_eof = DFA.unpackEncodedString(DFA22_eofS);
    static final char[] DFA22_min = DFA.unpackEncodedStringToUnsignedChars(DFA22_minS);
    static final char[] DFA22_max = DFA.unpackEncodedStringToUnsignedChars(DFA22_maxS);
    static final short[] DFA22_accept = DFA.unpackEncodedString(DFA22_acceptS);
    static final short[] DFA22_special = DFA.unpackEncodedString(DFA22_specialS);
    static final short[][] DFA22_transition;

    static {
        int numStates = DFA22_transitionS.length;
        DFA22_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA22_transition[i] = DFA.unpackEncodedString(DFA22_transitionS[i]);
        }
    }

    class DFA22 extends DFA {

        public DFA22(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 22;
            this.eot = DFA22_eot;
            this.eof = DFA22_eof;
            this.min = DFA22_min;
            this.max = DFA22_max;
            this.accept = DFA22_accept;
            this.special = DFA22_special;
            this.transition = DFA22_transition;
        }
        public String getDescription() {
            return "151:1: selectExpression : ( expression | tableAllColumns );";
        }
    }
 

    public static final BitSet FOLLOW_KW_SELECT_in_selectClause79 = new BitSet(new long[]{0xB7BFFFFEFC04A080L,0xFFF7AEFEF57FFFF6L,0xFF3EBF5FDEFFDEEFL,0xEEFFBDFFFFF7FFEFL,0x000000178192297FL});
    public static final BitSet FOLLOW_hintClause_in_selectClause81 = new BitSet(new long[]{0xB7BFFFFEFC042080L,0xFFF7AEFEF57FFFF6L,0xFF3EBF5FDEFFDEEFL,0xEEFFBDFFFFF7FFEFL,0x000000178192297FL});
    public static final BitSet FOLLOW_KW_ALL_in_selectClause87 = new BitSet(new long[]{0xB7BFFFFEDC042080L,0xFFF7AEFEF57BFFF6L,0xFF3EBF5FDEFFDEEFL,0xEEF7BDFFFFF7FFEFL,0x000000178192297FL});
    public static final BitSet FOLLOW_KW_DISTINCT_in_selectClause93 = new BitSet(new long[]{0xB7BFFFFEDC042080L,0xFFF7AEFEF57BFFF6L,0xFF3EBF5FDEFFDEEFL,0xEEF7BDFFFFF7FFEFL,0x000000178192297FL});
    public static final BitSet FOLLOW_selectList_in_selectClause97 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_TRANSFORM_in_selectClause131 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_selectTrfmClause_in_selectClause133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_trfmClause_in_selectClause204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selectItem_in_selectList247 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_COMMA_in_selectList251 = new BitSet(new long[]{0xB7BFFFFEDC042080L,0xFFF7AEFEF57BFFF6L,0xFF3EBF5FDEFFDEEFL,0xEEF7BDFFFFF7FFEFL,0x000000178192297FL});
    public static final BitSet FOLLOW_selectItem_in_selectList254 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_LPAREN_in_selectTrfmClause293 = new BitSet(new long[]{0xB7BFFFFEDC042080L,0xFFF7AEFEF57BFFF6L,0xFF3EBF5FDEFFDEEFL,0xEEF7BDFFFFF7FFEFL,0x000000178192297FL});
    public static final BitSet FOLLOW_selectExpressionList_in_selectTrfmClause295 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RPAREN_in_selectTrfmClause297 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000008008L,0x0000000000000008L});
    public static final BitSet FOLLOW_rowFormat_in_selectTrfmClause305 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L,0x0000000000000008L});
    public static final BitSet FOLLOW_recordWriter_in_selectTrfmClause309 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_KW_USING_in_selectTrfmClause315 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_StringLiteral_in_selectTrfmClause317 = new BitSet(new long[]{0x0000000800000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000008004L});
    public static final BitSet FOLLOW_KW_AS_in_selectTrfmClause325 = new BitSet(new long[]{0xB7BE7FFEDC000000L,0xFFB7AEFEF57BFFF6L,0xFF3EBF5EDCFFDEEFL,0xEEF7BDFFFFF7FFEFL,0x000000000002297FL});
    public static final BitSet FOLLOW_LPAREN_in_selectTrfmClause329 = new BitSet(new long[]{0xB7BE7FFEDC000000L,0xFFB7AEFEF57BFFF6L,0xFF3EBF5EDCFFDEEFL,0xEEF7BDFFFFF7FFEFL,0x000000000000297FL});
    public static final BitSet FOLLOW_aliasList_in_selectTrfmClause332 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_columnNameTypeList_in_selectTrfmClause336 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RPAREN_in_selectTrfmClause339 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000008004L});
    public static final BitSet FOLLOW_aliasList_in_selectTrfmClause345 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000008004L});
    public static final BitSet FOLLOW_columnNameTypeList_in_selectTrfmClause349 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000008004L});
    public static final BitSet FOLLOW_rowFormat_in_selectTrfmClause361 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_recordReader_in_selectTrfmClause365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIVIDE_in_hintClause428 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_STAR_in_hintClause430 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_PLUS_in_hintClause432 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000000L,0x0000000004000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_hintList_in_hintClause434 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_STAR_in_hintClause436 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_DIVIDE_in_hintClause438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_hintItem_in_hintList477 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_COMMA_in_hintList480 = new BitSet(new long[]{0x0000000000000000L,0x0410000000000000L,0x0000000004000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_hintItem_in_hintList482 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_hintName_in_hintItem520 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_LPAREN_in_hintItem523 = new BitSet(new long[]{0xB7BE7FFEDC000000L,0xFFB7AEFEF57BFFF6L,0xFF3EBF5EDCFFDEEFL,0xEEF7BDFFFFF7FFEFL,0x000000000000297FL});
    public static final BitSet FOLLOW_hintArgs_in_hintItem525 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RPAREN_in_hintItem527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_MAPJOIN_in_hintName571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_INCRE_in_hintName583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_STREAMTABLE_in_hintName595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_HOLD_DDLTIME_in_hintName607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_hintArgName_in_hintArgs642 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_COMMA_in_hintArgs645 = new BitSet(new long[]{0xB7BE7FFEDC000000L,0xFFB7AEFEF57BFFF6L,0xFF3EBF5EDCFFDEEFL,0xEEF7BDFFFFF7FFEFL,0x000000000000297FL});
    public static final BitSet FOLLOW_hintArgName_in_hintArgs647 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_identifier_in_hintArgName689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selectExpression_in_selectItem722 = new BitSet(new long[]{0xB7BE7FFEDC000002L,0xFFB7AEFEF57BFFF6L,0xFF3EBF5EDCFFDEEFL,0xEEF7BDFFFFF7FFEFL,0x000000000000297FL});
    public static final BitSet FOLLOW_KW_AS_in_selectItem732 = new BitSet(new long[]{0xB7BE7FFEDC000000L,0xFFB7AEFEF57BFFF6L,0xFF3EBF5EDCFFDEEFL,0xEEF7BDFFFFF7FFEFL,0x000000000000297FL});
    public static final BitSet FOLLOW_identifier_in_selectItem735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_AS_in_selectItem741 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_LPAREN_in_selectItem743 = new BitSet(new long[]{0xB7BE7FFEDC000000L,0xFFB7AEFEF57BFFF6L,0xFF3EBF5EDCFFDEEFL,0xEEF7BDFFFFF7FFEFL,0x000000000000297FL});
    public static final BitSet FOLLOW_identifier_in_selectItem745 = new BitSet(new long[]{0x0000000000000400L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_COMMA_in_selectItem748 = new BitSet(new long[]{0xB7BE7FFEDC000000L,0xFFB7AEFEF57BFFF6L,0xFF3EBF5EDCFFDEEFL,0xEEF7BDFFFFF7FFEFL,0x000000000000297FL});
    public static final BitSet FOLLOW_identifier_in_selectItem750 = new BitSet(new long[]{0x0000000000000400L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RPAREN_in_selectItem754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_MAP_in_trfmClause809 = new BitSet(new long[]{0xB7BFFFFEDC042080L,0xFFF7AEFEF57BFFF6L,0xFF3EBF5FDEFFDEEFL,0xEEF7BDFFFFF7FFEFL,0x000000178192297FL});
    public static final BitSet FOLLOW_selectExpressionList_in_trfmClause814 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000008008L,0x0000000000000008L});
    public static final BitSet FOLLOW_KW_REDUCE_in_trfmClause824 = new BitSet(new long[]{0xB7BFFFFEDC042080L,0xFFF7AEFEF57BFFF6L,0xFF3EBF5FDEFFDEEFL,0xEEF7BDFFFFF7FFEFL,0x000000178192297FL});
    public static final BitSet FOLLOW_selectExpressionList_in_trfmClause826 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000008008L,0x0000000000000008L});
    public static final BitSet FOLLOW_rowFormat_in_trfmClause836 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L,0x0000000000000008L});
    public static final BitSet FOLLOW_recordWriter_in_trfmClause840 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_KW_USING_in_trfmClause846 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_StringLiteral_in_trfmClause848 = new BitSet(new long[]{0x0000000800000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000008004L});
    public static final BitSet FOLLOW_KW_AS_in_trfmClause856 = new BitSet(new long[]{0xB7BE7FFEDC000000L,0xFFB7AEFEF57BFFF6L,0xFF3EBF5EDCFFDEEFL,0xEEF7BDFFFFF7FFEFL,0x000000000002297FL});
    public static final BitSet FOLLOW_LPAREN_in_trfmClause860 = new BitSet(new long[]{0xB7BE7FFEDC000000L,0xFFB7AEFEF57BFFF6L,0xFF3EBF5EDCFFDEEFL,0xEEF7BDFFFFF7FFEFL,0x000000000000297FL});
    public static final BitSet FOLLOW_aliasList_in_trfmClause863 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_columnNameTypeList_in_trfmClause867 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RPAREN_in_trfmClause870 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000008004L});
    public static final BitSet FOLLOW_aliasList_in_trfmClause876 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000008004L});
    public static final BitSet FOLLOW_columnNameTypeList_in_trfmClause880 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000008004L});
    public static final BitSet FOLLOW_rowFormat_in_trfmClause892 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_recordReader_in_trfmClause896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_selectExpression959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tableAllColumns_in_selectExpression963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selectExpression_in_selectExpressionList994 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_COMMA_in_selectExpressionList997 = new BitSet(new long[]{0xB7BFFFFEDC042080L,0xFFF7AEFEF57BFFF6L,0xFF3EBF5FDEFFDEEFL,0xEEF7BDFFFFF7FFEFL,0x000000178192297FL});
    public static final BitSet FOLLOW_selectExpression_in_selectExpressionList999 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_DIVIDE_in_incrementalClause1046 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_STAR_in_incrementalClause1048 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_PLUS_in_incrementalClause1050 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_KW_INCRE_in_incrementalClause1052 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_LPAREN_in_incrementalClause1054 = new BitSet(new long[]{0x0800000010000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_incrementalArgs_in_incrementalClause1056 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RPAREN_in_incrementalClause1058 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_STAR_in_incrementalClause1060 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_DIVIDE_in_incrementalClause1062 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_betweenDate_in_incrementalArgs1112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_CONSTANT_in_incrementalArgs1131 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_Number_in_incrementalArgs1135 = new BitSet(new long[]{0xB7BE7FFEDC000000L,0xFFB7AEFEF57BFFF6L,0xFF3EBF5EDCFFDEEFL,0xEEF7BDFFFFF7FFEFL,0x000000000000297FL});
    public static final BitSet FOLLOW_identifier_in_incrementalArgs1139 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_KW_INTERVAL_in_incrementalArgs1144 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_Number_in_incrementalArgs1148 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_DIVIDE_in_incrementalArgs1150 = new BitSet(new long[]{0xB7BE7FFEDC000000L,0xFFB7AEFEF57BFFF6L,0xFF3EBF5EDCFFDEEFL,0xEEF7BDFFFFF7FFEFL,0x000000000000297FL});
    public static final BitSet FOLLOW_identifier_in_incrementalArgs1154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_AFTER_in_incrementalArgs1213 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_startDate_in_incrementalArgs1217 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_KW_INTERVAL_in_incrementalArgs1222 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_Number_in_incrementalArgs1226 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_DIVIDE_in_incrementalArgs1228 = new BitSet(new long[]{0xB7BE7FFEDC000000L,0xFFB7AEFEF57BFFF6L,0xFF3EBF5EDCFFDEEFL,0xEEF7BDFFFFF7FFEFL,0x000000000000297FL});
    public static final BitSet FOLLOW_identifier_in_incrementalArgs1232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_HOUR_in_intervalUnit1296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW__in_intervalUnit1300 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_SECOND_in_intervalUnit1302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_DAY_in_intervalUnit1306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_WEEK_in_intervalUnit1311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_MONTH_in_intervalUnit1315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_MINUTE_in_intervalUnit1319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dateArgs_in_betweenDate1357 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_MINUS_in_betweenDate1359 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_dateArgs_in_betweenDate1364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dateArgs_in_startDate1418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_indefinite_date_in_dateArgs1465 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_COMMA_in_dateArgs1470 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_explicit_time_in_dateArgs1474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_year_or_month_in_indefinite_date1544 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_Number_in_indefinite_date1550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Number_in_year_or_month1604 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_DIVIDE_in_year_or_month1606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Number_in_explicit_time1639 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_COLON_in_explicit_time1641 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_Number_in_explicit_time1645 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_COLON_in_explicit_time1648 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_Number_in_explicit_time1652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_WINDOW_in_window_clause1705 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_window_defn_in_window_clause1707 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_COMMA_in_window_clause1710 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_window_defn_in_window_clause1712 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_Identifier_in_window_defn1748 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_KW_AS_in_window_defn1750 = new BitSet(new long[]{0x0000000004000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_window_specification_in_window_defn1752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_window_specification1788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_window_specification1794 = new BitSet(new long[]{0x0004000004000000L,0x0000000000080000L,0x1002020000000000L,0x0000000080010000L,0x0000000008000000L});
    public static final BitSet FOLLOW_Identifier_in_window_specification1796 = new BitSet(new long[]{0x0004000000000000L,0x0000000000080000L,0x1002020000000000L,0x0000000080010000L,0x0000000008000000L});
    public static final BitSet FOLLOW_partitioningSpec_in_window_specification1799 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x1000000000000000L,0x0000000000010000L,0x0000000008000000L});
    public static final BitSet FOLLOW_window_frame_in_window_specification1802 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_RPAREN_in_window_specification1805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_window_range_expression_in_window_frame1832 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_window_value_expression_in_window_frame1837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_ROWS_in_window_range_expression1859 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L,0x0000000000000000L,0x0100000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_window_frame_start_boundary_in_window_range_expression1863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_ROWS_in_window_range_expression1877 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_KW_BETWEEN_in_window_range_expression1879 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L,0x0000000000000000L,0x0100000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_window_frame_boundary_in_window_range_expression1883 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_KW_AND_in_window_range_expression1885 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L,0x0000000000000000L,0x0100000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_window_frame_boundary_in_window_range_expression1889 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_RANGE_in_window_value_expression1923 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L,0x0000000000000000L,0x0100000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_window_frame_start_boundary_in_window_value_expression1927 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_RANGE_in_window_value_expression1941 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_KW_BETWEEN_in_window_value_expression1943 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L,0x0000000000000000L,0x0100000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_window_frame_boundary_in_window_value_expression1947 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_KW_AND_in_window_value_expression1949 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L,0x0000000000000000L,0x0100000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_window_frame_boundary_in_window_value_expression1953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_UNBOUNDED_in_window_frame_start_boundary1988 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_KW_PRECEDING_in_window_frame_start_boundary1990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_CURRENT_in_window_frame_start_boundary2006 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_KW_ROW_in_window_frame_start_boundary2008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Number_in_window_frame_start_boundary2021 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_KW_PRECEDING_in_window_frame_start_boundary2023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_UNBOUNDED_in_window_frame_boundary2054 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_KW_PRECEDING_in_window_frame_boundary2059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_FOLLOWING_in_window_frame_boundary2063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_CURRENT_in_window_frame_boundary2081 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_KW_ROW_in_window_frame_boundary2083 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Number_in_window_frame_boundary2096 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_KW_PRECEDING_in_window_frame_boundary2101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KW_FOLLOWING_in_window_frame_boundary2107 = new BitSet(new long[]{0x0000000000000002L});

}