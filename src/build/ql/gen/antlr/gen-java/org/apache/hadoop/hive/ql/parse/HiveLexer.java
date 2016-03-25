// $ANTLR 3.4 /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g 2015-12-26 22:51:47
package org.apache.hadoop.hive.ql.parse;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

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
public class HiveLexer extends Lexer {
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

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public HiveLexer() {} 
    public HiveLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public HiveLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "/home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g"; }

    // $ANTLR start "KW_TRUE"
    public final void mKW_TRUE() throws RecognitionException {
        try {
            int _type = KW_TRUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:23:9: ( 'TRUE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:23:11: 'TRUE'
            {
            match("TRUE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_TRUE"

    // $ANTLR start "KW_FALSE"
    public final void mKW_FALSE() throws RecognitionException {
        try {
            int _type = KW_FALSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:24:10: ( 'FALSE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:24:12: 'FALSE'
            {
            match("FALSE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_FALSE"

    // $ANTLR start "KW_ALL"
    public final void mKW_ALL() throws RecognitionException {
        try {
            int _type = KW_ALL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:25:8: ( 'ALL' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:25:10: 'ALL'
            {
            match("ALL"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_ALL"

    // $ANTLR start "KW_AND"
    public final void mKW_AND() throws RecognitionException {
        try {
            int _type = KW_AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:26:8: ( 'AND' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:26:10: 'AND'
            {
            match("AND"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_AND"

    // $ANTLR start "KW_OR"
    public final void mKW_OR() throws RecognitionException {
        try {
            int _type = KW_OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:27:7: ( 'OR' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:27:9: 'OR'
            {
            match("OR"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_OR"

    // $ANTLR start "KW_NOT"
    public final void mKW_NOT() throws RecognitionException {
        try {
            int _type = KW_NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:28:8: ( 'NOT' | '!' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='N') ) {
                alt1=1;
            }
            else if ( (LA1_0=='!') ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }
            switch (alt1) {
                case 1 :
                    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:28:10: 'NOT'
                    {
                    match("NOT"); 



                    }
                    break;
                case 2 :
                    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:28:18: '!'
                    {
                    match('!'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_NOT"

    // $ANTLR start "KW_LIKE"
    public final void mKW_LIKE() throws RecognitionException {
        try {
            int _type = KW_LIKE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:29:9: ( 'LIKE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:29:11: 'LIKE'
            {
            match("LIKE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_LIKE"

    // $ANTLR start "KW_IF"
    public final void mKW_IF() throws RecognitionException {
        try {
            int _type = KW_IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:31:7: ( 'IF' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:31:9: 'IF'
            {
            match("IF"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_IF"

    // $ANTLR start "KW_EXISTS"
    public final void mKW_EXISTS() throws RecognitionException {
        try {
            int _type = KW_EXISTS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:32:11: ( 'EXISTS' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:32:13: 'EXISTS'
            {
            match("EXISTS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_EXISTS"

    // $ANTLR start "KW_CONSTANT"
    public final void mKW_CONSTANT() throws RecognitionException {
        try {
            int _type = KW_CONSTANT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:34:13: ( 'CONSTANT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:34:15: 'CONSTANT'
            {
            match("CONSTANT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_CONSTANT"

    // $ANTLR start "KW_INTERVAL"
    public final void mKW_INTERVAL() throws RecognitionException {
        try {
            int _type = KW_INTERVAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:35:13: ( 'INTERVAL' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:35:15: 'INTERVAL'
            {
            match("INTERVAL"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_INTERVAL"

    // $ANTLR start "KW_INCRE"
    public final void mKW_INCRE() throws RecognitionException {
        try {
            int _type = KW_INCRE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:36:10: ( 'INCREMENTAL' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:36:12: 'INCREMENTAL'
            {
            match("INCREMENTAL"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_INCRE"

    // $ANTLR start "KW_ASC"
    public final void mKW_ASC() throws RecognitionException {
        try {
            int _type = KW_ASC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:46:8: ( 'ASC' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:46:10: 'ASC'
            {
            match("ASC"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_ASC"

    // $ANTLR start "KW_DESC"
    public final void mKW_DESC() throws RecognitionException {
        try {
            int _type = KW_DESC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:47:9: ( 'DESC' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:47:11: 'DESC'
            {
            match("DESC"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_DESC"

    // $ANTLR start "KW_ORDER"
    public final void mKW_ORDER() throws RecognitionException {
        try {
            int _type = KW_ORDER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:48:10: ( 'ORDER' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:48:12: 'ORDER'
            {
            match("ORDER"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_ORDER"

    // $ANTLR start "KW_GROUP"
    public final void mKW_GROUP() throws RecognitionException {
        try {
            int _type = KW_GROUP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:49:10: ( 'GROUP' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:49:12: 'GROUP'
            {
            match("GROUP"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_GROUP"

    // $ANTLR start "KW_BY"
    public final void mKW_BY() throws RecognitionException {
        try {
            int _type = KW_BY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:50:7: ( 'BY' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:50:9: 'BY'
            {
            match("BY"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_BY"

    // $ANTLR start "KW_HAVING"
    public final void mKW_HAVING() throws RecognitionException {
        try {
            int _type = KW_HAVING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:51:11: ( 'HAVING' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:51:13: 'HAVING'
            {
            match("HAVING"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_HAVING"

    // $ANTLR start "KW_WHERE"
    public final void mKW_WHERE() throws RecognitionException {
        try {
            int _type = KW_WHERE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:52:10: ( 'WHERE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:52:12: 'WHERE'
            {
            match("WHERE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_WHERE"

    // $ANTLR start "KW_FROM"
    public final void mKW_FROM() throws RecognitionException {
        try {
            int _type = KW_FROM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:53:9: ( 'FROM' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:53:11: 'FROM'
            {
            match("FROM"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_FROM"

    // $ANTLR start "KW_AS"
    public final void mKW_AS() throws RecognitionException {
        try {
            int _type = KW_AS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:54:7: ( 'AS' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:54:9: 'AS'
            {
            match("AS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_AS"

    // $ANTLR start "KW_SELECT"
    public final void mKW_SELECT() throws RecognitionException {
        try {
            int _type = KW_SELECT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:55:11: ( 'SELECT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:55:13: 'SELECT'
            {
            match("SELECT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_SELECT"

    // $ANTLR start "KW_DISTINCT"
    public final void mKW_DISTINCT() throws RecognitionException {
        try {
            int _type = KW_DISTINCT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:56:13: ( 'DISTINCT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:56:15: 'DISTINCT'
            {
            match("DISTINCT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_DISTINCT"

    // $ANTLR start "KW_INSERT"
    public final void mKW_INSERT() throws RecognitionException {
        try {
            int _type = KW_INSERT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:57:11: ( 'INSERT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:57:13: 'INSERT'
            {
            match("INSERT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_INSERT"

    // $ANTLR start "KW_OVERWRITE"
    public final void mKW_OVERWRITE() throws RecognitionException {
        try {
            int _type = KW_OVERWRITE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:58:14: ( 'OVERWRITE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:58:16: 'OVERWRITE'
            {
            match("OVERWRITE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_OVERWRITE"

    // $ANTLR start "KW_OUTER"
    public final void mKW_OUTER() throws RecognitionException {
        try {
            int _type = KW_OUTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:59:10: ( 'OUTER' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:59:12: 'OUTER'
            {
            match("OUTER"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_OUTER"

    // $ANTLR start "KW_UNIQUEJOIN"
    public final void mKW_UNIQUEJOIN() throws RecognitionException {
        try {
            int _type = KW_UNIQUEJOIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:60:15: ( 'UNIQUEJOIN' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:60:17: 'UNIQUEJOIN'
            {
            match("UNIQUEJOIN"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_UNIQUEJOIN"

    // $ANTLR start "KW_PRESERVE"
    public final void mKW_PRESERVE() throws RecognitionException {
        try {
            int _type = KW_PRESERVE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:61:13: ( 'PRESERVE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:61:15: 'PRESERVE'
            {
            match("PRESERVE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_PRESERVE"

    // $ANTLR start "KW_JOIN"
    public final void mKW_JOIN() throws RecognitionException {
        try {
            int _type = KW_JOIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:62:9: ( 'JOIN' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:62:11: 'JOIN'
            {
            match("JOIN"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_JOIN"

    // $ANTLR start "KW_LEFT"
    public final void mKW_LEFT() throws RecognitionException {
        try {
            int _type = KW_LEFT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:63:9: ( 'LEFT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:63:11: 'LEFT'
            {
            match("LEFT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_LEFT"

    // $ANTLR start "KW_RIGHT"
    public final void mKW_RIGHT() throws RecognitionException {
        try {
            int _type = KW_RIGHT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:64:10: ( 'RIGHT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:64:12: 'RIGHT'
            {
            match("RIGHT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_RIGHT"

    // $ANTLR start "KW_FULL"
    public final void mKW_FULL() throws RecognitionException {
        try {
            int _type = KW_FULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:65:9: ( 'FULL' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:65:11: 'FULL'
            {
            match("FULL"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_FULL"

    // $ANTLR start "KW_ON"
    public final void mKW_ON() throws RecognitionException {
        try {
            int _type = KW_ON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:66:7: ( 'ON' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:66:9: 'ON'
            {
            match("ON"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_ON"

    // $ANTLR start "KW_PARTITION"
    public final void mKW_PARTITION() throws RecognitionException {
        try {
            int _type = KW_PARTITION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:67:14: ( 'PARTITION' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:67:16: 'PARTITION'
            {
            match("PARTITION"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_PARTITION"

    // $ANTLR start "KW_PARTITIONS"
    public final void mKW_PARTITIONS() throws RecognitionException {
        try {
            int _type = KW_PARTITIONS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:68:15: ( 'PARTITIONS' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:68:17: 'PARTITIONS'
            {
            match("PARTITIONS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_PARTITIONS"

    // $ANTLR start "KW_TABLE"
    public final void mKW_TABLE() throws RecognitionException {
        try {
            int _type = KW_TABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:69:9: ( 'TABLE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:69:11: 'TABLE'
            {
            match("TABLE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_TABLE"

    // $ANTLR start "KW_TABLES"
    public final void mKW_TABLES() throws RecognitionException {
        try {
            int _type = KW_TABLES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:70:10: ( 'TABLES' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:70:12: 'TABLES'
            {
            match("TABLES"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_TABLES"

    // $ANTLR start "KW_COLUMNS"
    public final void mKW_COLUMNS() throws RecognitionException {
        try {
            int _type = KW_COLUMNS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:71:11: ( 'COLUMNS' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:71:13: 'COLUMNS'
            {
            match("COLUMNS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_COLUMNS"

    // $ANTLR start "KW_INDEX"
    public final void mKW_INDEX() throws RecognitionException {
        try {
            int _type = KW_INDEX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:72:9: ( 'INDEX' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:72:11: 'INDEX'
            {
            match("INDEX"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_INDEX"

    // $ANTLR start "KW_INDEXES"
    public final void mKW_INDEXES() throws RecognitionException {
        try {
            int _type = KW_INDEXES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:73:11: ( 'INDEXES' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:73:13: 'INDEXES'
            {
            match("INDEXES"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_INDEXES"

    // $ANTLR start "KW_REBUILD"
    public final void mKW_REBUILD() throws RecognitionException {
        try {
            int _type = KW_REBUILD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:74:11: ( 'REBUILD' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:74:13: 'REBUILD'
            {
            match("REBUILD"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_REBUILD"

    // $ANTLR start "KW_FUNCTIONS"
    public final void mKW_FUNCTIONS() throws RecognitionException {
        try {
            int _type = KW_FUNCTIONS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:75:13: ( 'FUNCTIONS' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:75:15: 'FUNCTIONS'
            {
            match("FUNCTIONS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_FUNCTIONS"

    // $ANTLR start "KW_SHOW"
    public final void mKW_SHOW() throws RecognitionException {
        try {
            int _type = KW_SHOW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:76:8: ( 'SHOW' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:76:10: 'SHOW'
            {
            match("SHOW"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_SHOW"

    // $ANTLR start "KW_MSCK"
    public final void mKW_MSCK() throws RecognitionException {
        try {
            int _type = KW_MSCK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:77:8: ( 'MSCK' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:77:10: 'MSCK'
            {
            match("MSCK"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_MSCK"

    // $ANTLR start "KW_REPAIR"
    public final void mKW_REPAIR() throws RecognitionException {
        try {
            int _type = KW_REPAIR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:78:10: ( 'REPAIR' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:78:12: 'REPAIR'
            {
            match("REPAIR"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_REPAIR"

    // $ANTLR start "KW_DIRECTORY"
    public final void mKW_DIRECTORY() throws RecognitionException {
        try {
            int _type = KW_DIRECTORY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:79:13: ( 'DIRECTORY' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:79:15: 'DIRECTORY'
            {
            match("DIRECTORY"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_DIRECTORY"

    // $ANTLR start "KW_LOCAL"
    public final void mKW_LOCAL() throws RecognitionException {
        try {
            int _type = KW_LOCAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:80:9: ( 'LOCAL' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:80:11: 'LOCAL'
            {
            match("LOCAL"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_LOCAL"

    // $ANTLR start "KW_TRANSFORM"
    public final void mKW_TRANSFORM() throws RecognitionException {
        try {
            int _type = KW_TRANSFORM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:81:14: ( 'TRANSFORM' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:81:16: 'TRANSFORM'
            {
            match("TRANSFORM"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_TRANSFORM"

    // $ANTLR start "KW_USING"
    public final void mKW_USING() throws RecognitionException {
        try {
            int _type = KW_USING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:82:9: ( 'USING' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:82:11: 'USING'
            {
            match("USING"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_USING"

    // $ANTLR start "KW_CLUSTER"
    public final void mKW_CLUSTER() throws RecognitionException {
        try {
            int _type = KW_CLUSTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:83:11: ( 'CLUSTER' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:83:13: 'CLUSTER'
            {
            match("CLUSTER"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_CLUSTER"

    // $ANTLR start "KW_DISTRIBUTE"
    public final void mKW_DISTRIBUTE() throws RecognitionException {
        try {
            int _type = KW_DISTRIBUTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:84:14: ( 'DISTRIBUTE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:84:16: 'DISTRIBUTE'
            {
            match("DISTRIBUTE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_DISTRIBUTE"

    // $ANTLR start "KW_SORT"
    public final void mKW_SORT() throws RecognitionException {
        try {
            int _type = KW_SORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:85:8: ( 'SORT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:85:10: 'SORT'
            {
            match("SORT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_SORT"

    // $ANTLR start "KW_UNION"
    public final void mKW_UNION() throws RecognitionException {
        try {
            int _type = KW_UNION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:86:9: ( 'UNION' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:86:11: 'UNION'
            {
            match("UNION"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_UNION"

    // $ANTLR start "KW_LOAD"
    public final void mKW_LOAD() throws RecognitionException {
        try {
            int _type = KW_LOAD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:87:8: ( 'LOAD' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:87:10: 'LOAD'
            {
            match("LOAD"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_LOAD"

    // $ANTLR start "KW_EXPORT"
    public final void mKW_EXPORT() throws RecognitionException {
        try {
            int _type = KW_EXPORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:88:10: ( 'EXPORT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:88:12: 'EXPORT'
            {
            match("EXPORT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_EXPORT"

    // $ANTLR start "KW_IMPORT"
    public final void mKW_IMPORT() throws RecognitionException {
        try {
            int _type = KW_IMPORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:89:10: ( 'IMPORT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:89:12: 'IMPORT'
            {
            match("IMPORT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_IMPORT"

    // $ANTLR start "KW_DATA"
    public final void mKW_DATA() throws RecognitionException {
        try {
            int _type = KW_DATA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:90:8: ( 'DATA' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:90:10: 'DATA'
            {
            match("DATA"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_DATA"

    // $ANTLR start "KW_INPATH"
    public final void mKW_INPATH() throws RecognitionException {
        try {
            int _type = KW_INPATH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:91:10: ( 'INPATH' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:91:12: 'INPATH'
            {
            match("INPATH"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_INPATH"

    // $ANTLR start "KW_IS"
    public final void mKW_IS() throws RecognitionException {
        try {
            int _type = KW_IS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:92:6: ( 'IS' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:92:8: 'IS'
            {
            match("IS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_IS"

    // $ANTLR start "KW_NULL"
    public final void mKW_NULL() throws RecognitionException {
        try {
            int _type = KW_NULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:93:8: ( 'NULL' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:93:10: 'NULL'
            {
            match("NULL"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_NULL"

    // $ANTLR start "KW_CREATE"
    public final void mKW_CREATE() throws RecognitionException {
        try {
            int _type = KW_CREATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:94:10: ( 'CREATE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:94:12: 'CREATE'
            {
            match("CREATE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_CREATE"

    // $ANTLR start "KW_EXTERNAL"
    public final void mKW_EXTERNAL() throws RecognitionException {
        try {
            int _type = KW_EXTERNAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:95:12: ( 'EXTERNAL' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:95:14: 'EXTERNAL'
            {
            match("EXTERNAL"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_EXTERNAL"

    // $ANTLR start "KW_ALTER"
    public final void mKW_ALTER() throws RecognitionException {
        try {
            int _type = KW_ALTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:96:9: ( 'ALTER' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:96:11: 'ALTER'
            {
            match("ALTER"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_ALTER"

    // $ANTLR start "KW_CHANGE"
    public final void mKW_CHANGE() throws RecognitionException {
        try {
            int _type = KW_CHANGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:97:10: ( 'CHANGE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:97:12: 'CHANGE'
            {
            match("CHANGE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_CHANGE"

    // $ANTLR start "KW_COLUMN"
    public final void mKW_COLUMN() throws RecognitionException {
        try {
            int _type = KW_COLUMN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:98:10: ( 'COLUMN' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:98:12: 'COLUMN'
            {
            match("COLUMN"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_COLUMN"

    // $ANTLR start "KW_FIRST"
    public final void mKW_FIRST() throws RecognitionException {
        try {
            int _type = KW_FIRST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:99:9: ( 'FIRST' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:99:11: 'FIRST'
            {
            match("FIRST"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_FIRST"

    // $ANTLR start "KW_AFTER"
    public final void mKW_AFTER() throws RecognitionException {
        try {
            int _type = KW_AFTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:100:9: ( 'AFTER' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:100:11: 'AFTER'
            {
            match("AFTER"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_AFTER"

    // $ANTLR start "KW_DESCRIBE"
    public final void mKW_DESCRIBE() throws RecognitionException {
        try {
            int _type = KW_DESCRIBE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:101:12: ( 'DESCRIBE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:101:14: 'DESCRIBE'
            {
            match("DESCRIBE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_DESCRIBE"

    // $ANTLR start "KW_DROP"
    public final void mKW_DROP() throws RecognitionException {
        try {
            int _type = KW_DROP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:102:8: ( 'DROP' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:102:10: 'DROP'
            {
            match("DROP"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_DROP"

    // $ANTLR start "KW_RENAME"
    public final void mKW_RENAME() throws RecognitionException {
        try {
            int _type = KW_RENAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:103:10: ( 'RENAME' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:103:12: 'RENAME'
            {
            match("RENAME"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_RENAME"

    // $ANTLR start "KW_IGNORE"
    public final void mKW_IGNORE() throws RecognitionException {
        try {
            int _type = KW_IGNORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:104:10: ( 'IGNORE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:104:12: 'IGNORE'
            {
            match("IGNORE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_IGNORE"

    // $ANTLR start "KW_PROTECTION"
    public final void mKW_PROTECTION() throws RecognitionException {
        try {
            int _type = KW_PROTECTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:105:14: ( 'PROTECTION' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:105:16: 'PROTECTION'
            {
            match("PROTECTION"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_PROTECTION"

    // $ANTLR start "KW_TO"
    public final void mKW_TO() throws RecognitionException {
        try {
            int _type = KW_TO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:106:6: ( 'TO' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:106:8: 'TO'
            {
            match("TO"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_TO"

    // $ANTLR start "KW_COMMENT"
    public final void mKW_COMMENT() throws RecognitionException {
        try {
            int _type = KW_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:107:11: ( 'COMMENT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:107:13: 'COMMENT'
            {
            match("COMMENT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_COMMENT"

    // $ANTLR start "KW_BOOLEAN"
    public final void mKW_BOOLEAN() throws RecognitionException {
        try {
            int _type = KW_BOOLEAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:108:11: ( 'BOOLEAN' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:108:13: 'BOOLEAN'
            {
            match("BOOLEAN"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_BOOLEAN"

    // $ANTLR start "KW_TINYINT"
    public final void mKW_TINYINT() throws RecognitionException {
        try {
            int _type = KW_TINYINT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:109:11: ( 'TINYINT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:109:13: 'TINYINT'
            {
            match("TINYINT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_TINYINT"

    // $ANTLR start "KW_SMALLINT"
    public final void mKW_SMALLINT() throws RecognitionException {
        try {
            int _type = KW_SMALLINT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:110:12: ( 'SMALLINT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:110:14: 'SMALLINT'
            {
            match("SMALLINT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_SMALLINT"

    // $ANTLR start "KW_INT"
    public final void mKW_INT() throws RecognitionException {
        try {
            int _type = KW_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:111:7: ( 'INT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:111:9: 'INT'
            {
            match("INT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_INT"

    // $ANTLR start "KW_BIGINT"
    public final void mKW_BIGINT() throws RecognitionException {
        try {
            int _type = KW_BIGINT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:112:10: ( 'BIGINT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:112:12: 'BIGINT'
            {
            match("BIGINT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_BIGINT"

    // $ANTLR start "KW_FLOAT"
    public final void mKW_FLOAT() throws RecognitionException {
        try {
            int _type = KW_FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:113:9: ( 'FLOAT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:113:11: 'FLOAT'
            {
            match("FLOAT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_FLOAT"

    // $ANTLR start "KW_DOUBLE"
    public final void mKW_DOUBLE() throws RecognitionException {
        try {
            int _type = KW_DOUBLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:114:10: ( 'DOUBLE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:114:12: 'DOUBLE'
            {
            match("DOUBLE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_DOUBLE"

    // $ANTLR start "KW_DATE"
    public final void mKW_DATE() throws RecognitionException {
        try {
            int _type = KW_DATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:115:8: ( 'DATE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:115:10: 'DATE'
            {
            match("DATE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_DATE"

    // $ANTLR start "KW_DATETIME"
    public final void mKW_DATETIME() throws RecognitionException {
        try {
            int _type = KW_DATETIME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:116:12: ( 'DATETIME' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:116:14: 'DATETIME'
            {
            match("DATETIME"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_DATETIME"

    // $ANTLR start "KW_TIMESTAMP"
    public final void mKW_TIMESTAMP() throws RecognitionException {
        try {
            int _type = KW_TIMESTAMP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:117:13: ( 'TIMESTAMP' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:117:15: 'TIMESTAMP'
            {
            match("TIMESTAMP"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_TIMESTAMP"

    // $ANTLR start "KW_DECIMAL"
    public final void mKW_DECIMAL() throws RecognitionException {
        try {
            int _type = KW_DECIMAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:118:11: ( 'DECIMAL' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:118:13: 'DECIMAL'
            {
            match("DECIMAL"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_DECIMAL"

    // $ANTLR start "KW_STRING"
    public final void mKW_STRING() throws RecognitionException {
        try {
            int _type = KW_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:119:10: ( 'STRING' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:119:12: 'STRING'
            {
            match("STRING"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_STRING"

    // $ANTLR start "KW_VARCHAR"
    public final void mKW_VARCHAR() throws RecognitionException {
        try {
            int _type = KW_VARCHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:120:11: ( 'VARCHAR' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:120:13: 'VARCHAR'
            {
            match("VARCHAR"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_VARCHAR"

    // $ANTLR start "KW_ARRAY"
    public final void mKW_ARRAY() throws RecognitionException {
        try {
            int _type = KW_ARRAY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:121:9: ( 'ARRAY' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:121:11: 'ARRAY'
            {
            match("ARRAY"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_ARRAY"

    // $ANTLR start "KW_STRUCT"
    public final void mKW_STRUCT() throws RecognitionException {
        try {
            int _type = KW_STRUCT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:122:10: ( 'STRUCT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:122:12: 'STRUCT'
            {
            match("STRUCT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_STRUCT"

    // $ANTLR start "KW_MAP"
    public final void mKW_MAP() throws RecognitionException {
        try {
            int _type = KW_MAP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:123:7: ( 'MAP' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:123:9: 'MAP'
            {
            match("MAP"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_MAP"

    // $ANTLR start "KW_UNIONTYPE"
    public final void mKW_UNIONTYPE() throws RecognitionException {
        try {
            int _type = KW_UNIONTYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:124:13: ( 'UNIONTYPE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:124:15: 'UNIONTYPE'
            {
            match("UNIONTYPE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_UNIONTYPE"

    // $ANTLR start "KW_REDUCE"
    public final void mKW_REDUCE() throws RecognitionException {
        try {
            int _type = KW_REDUCE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:125:10: ( 'REDUCE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:125:12: 'REDUCE'
            {
            match("REDUCE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_REDUCE"

    // $ANTLR start "KW_PARTITIONED"
    public final void mKW_PARTITIONED() throws RecognitionException {
        try {
            int _type = KW_PARTITIONED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:126:15: ( 'PARTITIONED' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:126:17: 'PARTITIONED'
            {
            match("PARTITIONED"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_PARTITIONED"

    // $ANTLR start "KW_CLUSTERED"
    public final void mKW_CLUSTERED() throws RecognitionException {
        try {
            int _type = KW_CLUSTERED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:127:13: ( 'CLUSTERED' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:127:15: 'CLUSTERED'
            {
            match("CLUSTERED"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_CLUSTERED"

    // $ANTLR start "KW_SORTED"
    public final void mKW_SORTED() throws RecognitionException {
        try {
            int _type = KW_SORTED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:128:10: ( 'SORTED' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:128:12: 'SORTED'
            {
            match("SORTED"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_SORTED"

    // $ANTLR start "KW_INTO"
    public final void mKW_INTO() throws RecognitionException {
        try {
            int _type = KW_INTO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:129:8: ( 'INTO' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:129:10: 'INTO'
            {
            match("INTO"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_INTO"

    // $ANTLR start "KW_BUCKETS"
    public final void mKW_BUCKETS() throws RecognitionException {
        try {
            int _type = KW_BUCKETS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:130:11: ( 'BUCKETS' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:130:13: 'BUCKETS'
            {
            match("BUCKETS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_BUCKETS"

    // $ANTLR start "KW_ROW"
    public final void mKW_ROW() throws RecognitionException {
        try {
            int _type = KW_ROW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:131:7: ( 'ROW' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:131:9: 'ROW'
            {
            match("ROW"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_ROW"

    // $ANTLR start "KW_ROWS"
    public final void mKW_ROWS() throws RecognitionException {
        try {
            int _type = KW_ROWS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:132:8: ( 'ROWS' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:132:10: 'ROWS'
            {
            match("ROWS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_ROWS"

    // $ANTLR start "KW_FORMAT"
    public final void mKW_FORMAT() throws RecognitionException {
        try {
            int _type = KW_FORMAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:133:10: ( 'FORMAT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:133:12: 'FORMAT'
            {
            match("FORMAT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_FORMAT"

    // $ANTLR start "KW_DELIMITED"
    public final void mKW_DELIMITED() throws RecognitionException {
        try {
            int _type = KW_DELIMITED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:134:13: ( 'DELIMITED' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:134:15: 'DELIMITED'
            {
            match("DELIMITED"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_DELIMITED"

    // $ANTLR start "KW_FIELDS"
    public final void mKW_FIELDS() throws RecognitionException {
        try {
            int _type = KW_FIELDS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:135:10: ( 'FIELDS' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:135:12: 'FIELDS'
            {
            match("FIELDS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_FIELDS"

    // $ANTLR start "KW_TERMINATED"
    public final void mKW_TERMINATED() throws RecognitionException {
        try {
            int _type = KW_TERMINATED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:136:14: ( 'TERMINATED' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:136:16: 'TERMINATED'
            {
            match("TERMINATED"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_TERMINATED"

    // $ANTLR start "KW_ESCAPED"
    public final void mKW_ESCAPED() throws RecognitionException {
        try {
            int _type = KW_ESCAPED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:137:11: ( 'ESCAPED' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:137:13: 'ESCAPED'
            {
            match("ESCAPED"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_ESCAPED"

    // $ANTLR start "KW_COLLECTION"
    public final void mKW_COLLECTION() throws RecognitionException {
        try {
            int _type = KW_COLLECTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:138:14: ( 'COLLECTION' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:138:16: 'COLLECTION'
            {
            match("COLLECTION"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_COLLECTION"

    // $ANTLR start "KW_ITEMS"
    public final void mKW_ITEMS() throws RecognitionException {
        try {
            int _type = KW_ITEMS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:139:9: ( 'ITEMS' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:139:11: 'ITEMS'
            {
            match("ITEMS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_ITEMS"

    // $ANTLR start "KW_KEYS"
    public final void mKW_KEYS() throws RecognitionException {
        try {
            int _type = KW_KEYS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:140:8: ( 'KEYS' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:140:10: 'KEYS'
            {
            match("KEYS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_KEYS"

    // $ANTLR start "KW_KEY_TYPE"
    public final void mKW_KEY_TYPE() throws RecognitionException {
        try {
            int _type = KW_KEY_TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:141:12: ( '$KEY$' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:141:14: '$KEY$'
            {
            match("$KEY$"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_KEY_TYPE"

    // $ANTLR start "KW_LINES"
    public final void mKW_LINES() throws RecognitionException {
        try {
            int _type = KW_LINES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:142:9: ( 'LINES' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:142:11: 'LINES'
            {
            match("LINES"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_LINES"

    // $ANTLR start "KW_STORED"
    public final void mKW_STORED() throws RecognitionException {
        try {
            int _type = KW_STORED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:143:10: ( 'STORED' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:143:12: 'STORED'
            {
            match("STORED"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_STORED"

    // $ANTLR start "KW_FILEFORMAT"
    public final void mKW_FILEFORMAT() throws RecognitionException {
        try {
            int _type = KW_FILEFORMAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:144:14: ( 'FILEFORMAT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:144:16: 'FILEFORMAT'
            {
            match("FILEFORMAT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_FILEFORMAT"

    // $ANTLR start "KW_SEQUENCEFILE"
    public final void mKW_SEQUENCEFILE() throws RecognitionException {
        try {
            int _type = KW_SEQUENCEFILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:145:16: ( 'SEQUENCEFILE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:145:18: 'SEQUENCEFILE'
            {
            match("SEQUENCEFILE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_SEQUENCEFILE"

    // $ANTLR start "KW_TEXTFILE"
    public final void mKW_TEXTFILE() throws RecognitionException {
        try {
            int _type = KW_TEXTFILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:146:12: ( 'TEXTFILE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:146:14: 'TEXTFILE'
            {
            match("TEXTFILE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_TEXTFILE"

    // $ANTLR start "KW_RCFILE"
    public final void mKW_RCFILE() throws RecognitionException {
        try {
            int _type = KW_RCFILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:147:10: ( 'RCFILE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:147:12: 'RCFILE'
            {
            match("RCFILE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_RCFILE"

    // $ANTLR start "KW_ORCFILE"
    public final void mKW_ORCFILE() throws RecognitionException {
        try {
            int _type = KW_ORCFILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:148:11: ( 'ORC' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:148:13: 'ORC'
            {
            match("ORC"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_ORCFILE"

    // $ANTLR start "KW_INPUTFORMAT"
    public final void mKW_INPUTFORMAT() throws RecognitionException {
        try {
            int _type = KW_INPUTFORMAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:149:15: ( 'INPUTFORMAT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:149:17: 'INPUTFORMAT'
            {
            match("INPUTFORMAT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_INPUTFORMAT"

    // $ANTLR start "KW_OUTPUTFORMAT"
    public final void mKW_OUTPUTFORMAT() throws RecognitionException {
        try {
            int _type = KW_OUTPUTFORMAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:150:16: ( 'OUTPUTFORMAT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:150:18: 'OUTPUTFORMAT'
            {
            match("OUTPUTFORMAT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_OUTPUTFORMAT"

    // $ANTLR start "KW_INPUTDRIVER"
    public final void mKW_INPUTDRIVER() throws RecognitionException {
        try {
            int _type = KW_INPUTDRIVER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:151:15: ( 'INPUTDRIVER' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:151:17: 'INPUTDRIVER'
            {
            match("INPUTDRIVER"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_INPUTDRIVER"

    // $ANTLR start "KW_OUTPUTDRIVER"
    public final void mKW_OUTPUTDRIVER() throws RecognitionException {
        try {
            int _type = KW_OUTPUTDRIVER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:152:16: ( 'OUTPUTDRIVER' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:152:18: 'OUTPUTDRIVER'
            {
            match("OUTPUTDRIVER"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_OUTPUTDRIVER"

    // $ANTLR start "KW_OFFLINE"
    public final void mKW_OFFLINE() throws RecognitionException {
        try {
            int _type = KW_OFFLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:153:11: ( 'OFFLINE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:153:13: 'OFFLINE'
            {
            match("OFFLINE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_OFFLINE"

    // $ANTLR start "KW_ENABLE"
    public final void mKW_ENABLE() throws RecognitionException {
        try {
            int _type = KW_ENABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:154:10: ( 'ENABLE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:154:12: 'ENABLE'
            {
            match("ENABLE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_ENABLE"

    // $ANTLR start "KW_DISABLE"
    public final void mKW_DISABLE() throws RecognitionException {
        try {
            int _type = KW_DISABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:155:11: ( 'DISABLE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:155:13: 'DISABLE'
            {
            match("DISABLE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_DISABLE"

    // $ANTLR start "KW_READONLY"
    public final void mKW_READONLY() throws RecognitionException {
        try {
            int _type = KW_READONLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:156:12: ( 'READONLY' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:156:14: 'READONLY'
            {
            match("READONLY"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_READONLY"

    // $ANTLR start "KW_NO_DROP"
    public final void mKW_NO_DROP() throws RecognitionException {
        try {
            int _type = KW_NO_DROP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:157:11: ( 'NO_DROP' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:157:13: 'NO_DROP'
            {
            match("NO_DROP"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_NO_DROP"

    // $ANTLR start "KW_LOCATION"
    public final void mKW_LOCATION() throws RecognitionException {
        try {
            int _type = KW_LOCATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:158:12: ( 'LOCATION' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:158:14: 'LOCATION'
            {
            match("LOCATION"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_LOCATION"

    // $ANTLR start "KW_TABLESAMPLE"
    public final void mKW_TABLESAMPLE() throws RecognitionException {
        try {
            int _type = KW_TABLESAMPLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:159:15: ( 'TABLESAMPLE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:159:17: 'TABLESAMPLE'
            {
            match("TABLESAMPLE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_TABLESAMPLE"

    // $ANTLR start "KW_BUCKET"
    public final void mKW_BUCKET() throws RecognitionException {
        try {
            int _type = KW_BUCKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:160:10: ( 'BUCKET' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:160:12: 'BUCKET'
            {
            match("BUCKET"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_BUCKET"

    // $ANTLR start "KW_OUT"
    public final void mKW_OUT() throws RecognitionException {
        try {
            int _type = KW_OUT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:161:7: ( 'OUT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:161:9: 'OUT'
            {
            match("OUT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_OUT"

    // $ANTLR start "KW_OF"
    public final void mKW_OF() throws RecognitionException {
        try {
            int _type = KW_OF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:162:6: ( 'OF' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:162:8: 'OF'
            {
            match("OF"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_OF"

    // $ANTLR start "KW_PERCENT"
    public final void mKW_PERCENT() throws RecognitionException {
        try {
            int _type = KW_PERCENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:163:11: ( 'PERCENT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:163:13: 'PERCENT'
            {
            match("PERCENT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_PERCENT"

    // $ANTLR start "KW_CAST"
    public final void mKW_CAST() throws RecognitionException {
        try {
            int _type = KW_CAST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:164:8: ( 'CAST' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:164:10: 'CAST'
            {
            match("CAST"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_CAST"

    // $ANTLR start "KW_ADD"
    public final void mKW_ADD() throws RecognitionException {
        try {
            int _type = KW_ADD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:165:7: ( 'ADD' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:165:9: 'ADD'
            {
            match("ADD"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_ADD"

    // $ANTLR start "KW_REPLACE"
    public final void mKW_REPLACE() throws RecognitionException {
        try {
            int _type = KW_REPLACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:166:11: ( 'REPLACE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:166:13: 'REPLACE'
            {
            match("REPLACE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_REPLACE"

    // $ANTLR start "KW_RLIKE"
    public final void mKW_RLIKE() throws RecognitionException {
        try {
            int _type = KW_RLIKE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:167:9: ( 'RLIKE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:167:11: 'RLIKE'
            {
            match("RLIKE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_RLIKE"

    // $ANTLR start "KW_REGEXP"
    public final void mKW_REGEXP() throws RecognitionException {
        try {
            int _type = KW_REGEXP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:168:10: ( 'REGEXP' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:168:12: 'REGEXP'
            {
            match("REGEXP"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_REGEXP"

    // $ANTLR start "KW_TEMPORARY"
    public final void mKW_TEMPORARY() throws RecognitionException {
        try {
            int _type = KW_TEMPORARY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:169:13: ( 'TEMPORARY' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:169:15: 'TEMPORARY'
            {
            match("TEMPORARY"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_TEMPORARY"

    // $ANTLR start "KW_FUNCTION"
    public final void mKW_FUNCTION() throws RecognitionException {
        try {
            int _type = KW_FUNCTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:170:12: ( 'FUNCTION' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:170:14: 'FUNCTION'
            {
            match("FUNCTION"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_FUNCTION"

    // $ANTLR start "KW_MACRO"
    public final void mKW_MACRO() throws RecognitionException {
        try {
            int _type = KW_MACRO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:171:9: ( 'MACRO' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:171:11: 'MACRO'
            {
            match("MACRO"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_MACRO"

    // $ANTLR start "KW_EXPLAIN"
    public final void mKW_EXPLAIN() throws RecognitionException {
        try {
            int _type = KW_EXPLAIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:172:11: ( 'EXPLAIN' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:172:13: 'EXPLAIN'
            {
            match("EXPLAIN"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_EXPLAIN"

    // $ANTLR start "KW_EXTENDED"
    public final void mKW_EXTENDED() throws RecognitionException {
        try {
            int _type = KW_EXTENDED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:173:12: ( 'EXTENDED' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:173:14: 'EXTENDED'
            {
            match("EXTENDED"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_EXTENDED"

    // $ANTLR start "KW_FORMATTED"
    public final void mKW_FORMATTED() throws RecognitionException {
        try {
            int _type = KW_FORMATTED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:174:13: ( 'FORMATTED' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:174:15: 'FORMATTED'
            {
            match("FORMATTED"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_FORMATTED"

    // $ANTLR start "KW_PRETTY"
    public final void mKW_PRETTY() throws RecognitionException {
        try {
            int _type = KW_PRETTY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:175:10: ( 'PRETTY' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:175:12: 'PRETTY'
            {
            match("PRETTY"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_PRETTY"

    // $ANTLR start "KW_DEPENDENCY"
    public final void mKW_DEPENDENCY() throws RecognitionException {
        try {
            int _type = KW_DEPENDENCY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:176:14: ( 'DEPENDENCY' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:176:16: 'DEPENDENCY'
            {
            match("DEPENDENCY"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_DEPENDENCY"

    // $ANTLR start "KW_LOGICAL"
    public final void mKW_LOGICAL() throws RecognitionException {
        try {
            int _type = KW_LOGICAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:177:11: ( 'LOGICAL' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:177:13: 'LOGICAL'
            {
            match("LOGICAL"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_LOGICAL"

    // $ANTLR start "KW_SERDE"
    public final void mKW_SERDE() throws RecognitionException {
        try {
            int _type = KW_SERDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:178:9: ( 'SERDE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:178:11: 'SERDE'
            {
            match("SERDE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_SERDE"

    // $ANTLR start "KW_WITH"
    public final void mKW_WITH() throws RecognitionException {
        try {
            int _type = KW_WITH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:179:8: ( 'WITH' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:179:10: 'WITH'
            {
            match("WITH"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_WITH"

    // $ANTLR start "KW_DEFERRED"
    public final void mKW_DEFERRED() throws RecognitionException {
        try {
            int _type = KW_DEFERRED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:180:12: ( 'DEFERRED' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:180:14: 'DEFERRED'
            {
            match("DEFERRED"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_DEFERRED"

    // $ANTLR start "KW_SERDEPROPERTIES"
    public final void mKW_SERDEPROPERTIES() throws RecognitionException {
        try {
            int _type = KW_SERDEPROPERTIES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:181:19: ( 'SERDEPROPERTIES' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:181:21: 'SERDEPROPERTIES'
            {
            match("SERDEPROPERTIES"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_SERDEPROPERTIES"

    // $ANTLR start "KW_DBPROPERTIES"
    public final void mKW_DBPROPERTIES() throws RecognitionException {
        try {
            int _type = KW_DBPROPERTIES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:182:16: ( 'DBPROPERTIES' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:182:18: 'DBPROPERTIES'
            {
            match("DBPROPERTIES"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_DBPROPERTIES"

    // $ANTLR start "KW_LIMIT"
    public final void mKW_LIMIT() throws RecognitionException {
        try {
            int _type = KW_LIMIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:183:9: ( 'LIMIT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:183:11: 'LIMIT'
            {
            match("LIMIT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_LIMIT"

    // $ANTLR start "KW_SET"
    public final void mKW_SET() throws RecognitionException {
        try {
            int _type = KW_SET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:184:7: ( 'SET' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:184:9: 'SET'
            {
            match("SET"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_SET"

    // $ANTLR start "KW_UNSET"
    public final void mKW_UNSET() throws RecognitionException {
        try {
            int _type = KW_UNSET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:185:9: ( 'UNSET' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:185:11: 'UNSET'
            {
            match("UNSET"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_UNSET"

    // $ANTLR start "KW_TBLPROPERTIES"
    public final void mKW_TBLPROPERTIES() throws RecognitionException {
        try {
            int _type = KW_TBLPROPERTIES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:186:17: ( 'TBLPROPERTIES' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:186:19: 'TBLPROPERTIES'
            {
            match("TBLPROPERTIES"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_TBLPROPERTIES"

    // $ANTLR start "KW_IDXPROPERTIES"
    public final void mKW_IDXPROPERTIES() throws RecognitionException {
        try {
            int _type = KW_IDXPROPERTIES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:187:17: ( 'IDXPROPERTIES' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:187:19: 'IDXPROPERTIES'
            {
            match("IDXPROPERTIES"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_IDXPROPERTIES"

    // $ANTLR start "KW_VALUE_TYPE"
    public final void mKW_VALUE_TYPE() throws RecognitionException {
        try {
            int _type = KW_VALUE_TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:188:14: ( '$VALUE$' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:188:16: '$VALUE$'
            {
            match("$VALUE$"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_VALUE_TYPE"

    // $ANTLR start "KW_ELEM_TYPE"
    public final void mKW_ELEM_TYPE() throws RecognitionException {
        try {
            int _type = KW_ELEM_TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:189:13: ( '$ELEM$' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:189:15: '$ELEM$'
            {
            match("$ELEM$"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_ELEM_TYPE"

    // $ANTLR start "KW_CASE"
    public final void mKW_CASE() throws RecognitionException {
        try {
            int _type = KW_CASE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:190:8: ( 'CASE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:190:10: 'CASE'
            {
            match("CASE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_CASE"

    // $ANTLR start "KW_WHEN"
    public final void mKW_WHEN() throws RecognitionException {
        try {
            int _type = KW_WHEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:191:8: ( 'WHEN' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:191:10: 'WHEN'
            {
            match("WHEN"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_WHEN"

    // $ANTLR start "KW_THEN"
    public final void mKW_THEN() throws RecognitionException {
        try {
            int _type = KW_THEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:192:8: ( 'THEN' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:192:10: 'THEN'
            {
            match("THEN"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_THEN"

    // $ANTLR start "KW_ELSE"
    public final void mKW_ELSE() throws RecognitionException {
        try {
            int _type = KW_ELSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:193:8: ( 'ELSE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:193:10: 'ELSE'
            {
            match("ELSE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_ELSE"

    // $ANTLR start "KW_END"
    public final void mKW_END() throws RecognitionException {
        try {
            int _type = KW_END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:194:7: ( 'END' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:194:9: 'END'
            {
            match("END"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_END"

    // $ANTLR start "KW_MAPJOIN"
    public final void mKW_MAPJOIN() throws RecognitionException {
        try {
            int _type = KW_MAPJOIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:195:11: ( 'MAPJOIN' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:195:13: 'MAPJOIN'
            {
            match("MAPJOIN"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_MAPJOIN"

    // $ANTLR start "KW_STREAMTABLE"
    public final void mKW_STREAMTABLE() throws RecognitionException {
        try {
            int _type = KW_STREAMTABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:196:15: ( 'STREAMTABLE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:196:17: 'STREAMTABLE'
            {
            match("STREAMTABLE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_STREAMTABLE"

    // $ANTLR start "KW_HOLD_DDLTIME"
    public final void mKW_HOLD_DDLTIME() throws RecognitionException {
        try {
            int _type = KW_HOLD_DDLTIME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:197:16: ( 'HOLD_DDLTIME' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:197:18: 'HOLD_DDLTIME'
            {
            match("HOLD_DDLTIME"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_HOLD_DDLTIME"

    // $ANTLR start "KW_CLUSTERSTATUS"
    public final void mKW_CLUSTERSTATUS() throws RecognitionException {
        try {
            int _type = KW_CLUSTERSTATUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:198:17: ( 'CLUSTERSTATUS' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:198:19: 'CLUSTERSTATUS'
            {
            match("CLUSTERSTATUS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_CLUSTERSTATUS"

    // $ANTLR start "KW_UTC"
    public final void mKW_UTC() throws RecognitionException {
        try {
            int _type = KW_UTC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:199:7: ( 'UTC' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:199:9: 'UTC'
            {
            match("UTC"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_UTC"

    // $ANTLR start "KW_UTCTIMESTAMP"
    public final void mKW_UTCTIMESTAMP() throws RecognitionException {
        try {
            int _type = KW_UTCTIMESTAMP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:200:16: ( 'UTC_TMESTAMP' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:200:18: 'UTC_TMESTAMP'
            {
            match("UTC_TMESTAMP"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_UTCTIMESTAMP"

    // $ANTLR start "KW_LONG"
    public final void mKW_LONG() throws RecognitionException {
        try {
            int _type = KW_LONG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:201:8: ( 'LONG' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:201:10: 'LONG'
            {
            match("LONG"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_LONG"

    // $ANTLR start "KW_DELETE"
    public final void mKW_DELETE() throws RecognitionException {
        try {
            int _type = KW_DELETE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:202:10: ( 'DELETE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:202:12: 'DELETE'
            {
            match("DELETE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_DELETE"

    // $ANTLR start "KW_PLUS"
    public final void mKW_PLUS() throws RecognitionException {
        try {
            int _type = KW_PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:203:8: ( 'PLUS' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:203:10: 'PLUS'
            {
            match("PLUS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_PLUS"

    // $ANTLR start "KW_MINUS"
    public final void mKW_MINUS() throws RecognitionException {
        try {
            int _type = KW_MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:204:9: ( 'MINUS' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:204:11: 'MINUS'
            {
            match("MINUS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_MINUS"

    // $ANTLR start "KW_FETCH"
    public final void mKW_FETCH() throws RecognitionException {
        try {
            int _type = KW_FETCH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:205:9: ( 'FETCH' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:205:11: 'FETCH'
            {
            match("FETCH"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_FETCH"

    // $ANTLR start "KW_INTERSECT"
    public final void mKW_INTERSECT() throws RecognitionException {
        try {
            int _type = KW_INTERSECT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:206:13: ( 'INTERSECT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:206:15: 'INTERSECT'
            {
            match("INTERSECT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_INTERSECT"

    // $ANTLR start "KW_VIEW"
    public final void mKW_VIEW() throws RecognitionException {
        try {
            int _type = KW_VIEW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:207:8: ( 'VIEW' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:207:10: 'VIEW'
            {
            match("VIEW"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_VIEW"

    // $ANTLR start "KW_IN"
    public final void mKW_IN() throws RecognitionException {
        try {
            int _type = KW_IN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:208:6: ( 'IN' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:208:8: 'IN'
            {
            match("IN"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_IN"

    // $ANTLR start "KW_DATABASE"
    public final void mKW_DATABASE() throws RecognitionException {
        try {
            int _type = KW_DATABASE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:209:12: ( 'DATABASE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:209:14: 'DATABASE'
            {
            match("DATABASE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_DATABASE"

    // $ANTLR start "KW_DATABASES"
    public final void mKW_DATABASES() throws RecognitionException {
        try {
            int _type = KW_DATABASES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:210:13: ( 'DATABASES' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:210:15: 'DATABASES'
            {
            match("DATABASES"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_DATABASES"

    // $ANTLR start "KW_MATERIALIZED"
    public final void mKW_MATERIALIZED() throws RecognitionException {
        try {
            int _type = KW_MATERIALIZED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:211:16: ( 'MATERIALIZED' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:211:18: 'MATERIALIZED'
            {
            match("MATERIALIZED"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_MATERIALIZED"

    // $ANTLR start "KW_SCHEMA"
    public final void mKW_SCHEMA() throws RecognitionException {
        try {
            int _type = KW_SCHEMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:212:10: ( 'SCHEMA' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:212:12: 'SCHEMA'
            {
            match("SCHEMA"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_SCHEMA"

    // $ANTLR start "KW_SCHEMAS"
    public final void mKW_SCHEMAS() throws RecognitionException {
        try {
            int _type = KW_SCHEMAS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:213:11: ( 'SCHEMAS' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:213:13: 'SCHEMAS'
            {
            match("SCHEMAS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_SCHEMAS"

    // $ANTLR start "KW_GRANT"
    public final void mKW_GRANT() throws RecognitionException {
        try {
            int _type = KW_GRANT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:214:9: ( 'GRANT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:214:11: 'GRANT'
            {
            match("GRANT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_GRANT"

    // $ANTLR start "KW_REVOKE"
    public final void mKW_REVOKE() throws RecognitionException {
        try {
            int _type = KW_REVOKE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:215:10: ( 'REVOKE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:215:12: 'REVOKE'
            {
            match("REVOKE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_REVOKE"

    // $ANTLR start "KW_SSL"
    public final void mKW_SSL() throws RecognitionException {
        try {
            int _type = KW_SSL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:216:7: ( 'SSL' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:216:9: 'SSL'
            {
            match("SSL"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_SSL"

    // $ANTLR start "KW_UNDO"
    public final void mKW_UNDO() throws RecognitionException {
        try {
            int _type = KW_UNDO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:217:8: ( 'UNDO' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:217:10: 'UNDO'
            {
            match("UNDO"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_UNDO"

    // $ANTLR start "KW_LOCK"
    public final void mKW_LOCK() throws RecognitionException {
        try {
            int _type = KW_LOCK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:218:8: ( 'LOCK' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:218:10: 'LOCK'
            {
            match("LOCK"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_LOCK"

    // $ANTLR start "KW_LOCKS"
    public final void mKW_LOCKS() throws RecognitionException {
        try {
            int _type = KW_LOCKS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:219:9: ( 'LOCKS' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:219:11: 'LOCKS'
            {
            match("LOCKS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_LOCKS"

    // $ANTLR start "KW_UNLOCK"
    public final void mKW_UNLOCK() throws RecognitionException {
        try {
            int _type = KW_UNLOCK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:220:10: ( 'UNLOCK' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:220:12: 'UNLOCK'
            {
            match("UNLOCK"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_UNLOCK"

    // $ANTLR start "KW_SHARED"
    public final void mKW_SHARED() throws RecognitionException {
        try {
            int _type = KW_SHARED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:221:10: ( 'SHARED' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:221:12: 'SHARED'
            {
            match("SHARED"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_SHARED"

    // $ANTLR start "KW_EXCLUSIVE"
    public final void mKW_EXCLUSIVE() throws RecognitionException {
        try {
            int _type = KW_EXCLUSIVE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:222:13: ( 'EXCLUSIVE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:222:15: 'EXCLUSIVE'
            {
            match("EXCLUSIVE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_EXCLUSIVE"

    // $ANTLR start "KW_PROCEDURE"
    public final void mKW_PROCEDURE() throws RecognitionException {
        try {
            int _type = KW_PROCEDURE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:223:13: ( 'PROCEDURE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:223:15: 'PROCEDURE'
            {
            match("PROCEDURE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_PROCEDURE"

    // $ANTLR start "KW_UNSIGNED"
    public final void mKW_UNSIGNED() throws RecognitionException {
        try {
            int _type = KW_UNSIGNED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:224:12: ( 'UNSIGNED' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:224:14: 'UNSIGNED'
            {
            match("UNSIGNED"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_UNSIGNED"

    // $ANTLR start "KW_WHILE"
    public final void mKW_WHILE() throws RecognitionException {
        try {
            int _type = KW_WHILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:225:9: ( 'WHILE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:225:11: 'WHILE'
            {
            match("WHILE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_WHILE"

    // $ANTLR start "KW_READ"
    public final void mKW_READ() throws RecognitionException {
        try {
            int _type = KW_READ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:226:8: ( 'READ' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:226:10: 'READ'
            {
            match("READ"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_READ"

    // $ANTLR start "KW_READS"
    public final void mKW_READS() throws RecognitionException {
        try {
            int _type = KW_READS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:227:9: ( 'READS' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:227:11: 'READS'
            {
            match("READS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_READS"

    // $ANTLR start "KW_PURGE"
    public final void mKW_PURGE() throws RecognitionException {
        try {
            int _type = KW_PURGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:228:9: ( 'PURGE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:228:11: 'PURGE'
            {
            match("PURGE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_PURGE"

    // $ANTLR start "KW_RANGE"
    public final void mKW_RANGE() throws RecognitionException {
        try {
            int _type = KW_RANGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:229:9: ( 'RANGE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:229:11: 'RANGE'
            {
            match("RANGE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_RANGE"

    // $ANTLR start "KW_ANALYZE"
    public final void mKW_ANALYZE() throws RecognitionException {
        try {
            int _type = KW_ANALYZE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:230:11: ( 'ANALYZE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:230:13: 'ANALYZE'
            {
            match("ANALYZE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_ANALYZE"

    // $ANTLR start "KW_BEFORE"
    public final void mKW_BEFORE() throws RecognitionException {
        try {
            int _type = KW_BEFORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:231:10: ( 'BEFORE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:231:12: 'BEFORE'
            {
            match("BEFORE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_BEFORE"

    // $ANTLR start "KW_BETWEEN"
    public final void mKW_BETWEEN() throws RecognitionException {
        try {
            int _type = KW_BETWEEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:232:11: ( 'BETWEEN' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:232:13: 'BETWEEN'
            {
            match("BETWEEN"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_BETWEEN"

    // $ANTLR start "KW_BOTH"
    public final void mKW_BOTH() throws RecognitionException {
        try {
            int _type = KW_BOTH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:233:8: ( 'BOTH' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:233:10: 'BOTH'
            {
            match("BOTH"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_BOTH"

    // $ANTLR start "KW_BINARY"
    public final void mKW_BINARY() throws RecognitionException {
        try {
            int _type = KW_BINARY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:234:10: ( 'BINARY' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:234:12: 'BINARY'
            {
            match("BINARY"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_BINARY"

    // $ANTLR start "KW_CROSS"
    public final void mKW_CROSS() throws RecognitionException {
        try {
            int _type = KW_CROSS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:235:9: ( 'CROSS' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:235:11: 'CROSS'
            {
            match("CROSS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_CROSS"

    // $ANTLR start "KW_CONTINUE"
    public final void mKW_CONTINUE() throws RecognitionException {
        try {
            int _type = KW_CONTINUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:236:12: ( 'CONTINUE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:236:14: 'CONTINUE'
            {
            match("CONTINUE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_CONTINUE"

    // $ANTLR start "KW_CURSOR"
    public final void mKW_CURSOR() throws RecognitionException {
        try {
            int _type = KW_CURSOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:237:10: ( 'CURSOR' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:237:12: 'CURSOR'
            {
            match("CURSOR"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_CURSOR"

    // $ANTLR start "KW_TRIGGER"
    public final void mKW_TRIGGER() throws RecognitionException {
        try {
            int _type = KW_TRIGGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:238:11: ( 'TRIGGER' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:238:13: 'TRIGGER'
            {
            match("TRIGGER"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_TRIGGER"

    // $ANTLR start "KW_RECORDREADER"
    public final void mKW_RECORDREADER() throws RecognitionException {
        try {
            int _type = KW_RECORDREADER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:239:16: ( 'RECORDREADER' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:239:18: 'RECORDREADER'
            {
            match("RECORDREADER"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_RECORDREADER"

    // $ANTLR start "KW_RECORDWRITER"
    public final void mKW_RECORDWRITER() throws RecognitionException {
        try {
            int _type = KW_RECORDWRITER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:240:16: ( 'RECORDWRITER' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:240:18: 'RECORDWRITER'
            {
            match("RECORDWRITER"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_RECORDWRITER"

    // $ANTLR start "KW_SEMI"
    public final void mKW_SEMI() throws RecognitionException {
        try {
            int _type = KW_SEMI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:241:8: ( 'SEMI' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:241:10: 'SEMI'
            {
            match("SEMI"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_SEMI"

    // $ANTLR start "KW_LATERAL"
    public final void mKW_LATERAL() throws RecognitionException {
        try {
            int _type = KW_LATERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:242:11: ( 'LATERAL' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:242:13: 'LATERAL'
            {
            match("LATERAL"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_LATERAL"

    // $ANTLR start "KW_TOUCH"
    public final void mKW_TOUCH() throws RecognitionException {
        try {
            int _type = KW_TOUCH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:243:9: ( 'TOUCH' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:243:11: 'TOUCH'
            {
            match("TOUCH"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_TOUCH"

    // $ANTLR start "KW_ARCHIVE"
    public final void mKW_ARCHIVE() throws RecognitionException {
        try {
            int _type = KW_ARCHIVE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:244:11: ( 'ARCHIVE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:244:13: 'ARCHIVE'
            {
            match("ARCHIVE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_ARCHIVE"

    // $ANTLR start "KW_UNARCHIVE"
    public final void mKW_UNARCHIVE() throws RecognitionException {
        try {
            int _type = KW_UNARCHIVE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:245:13: ( 'UNARCHIVE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:245:15: 'UNARCHIVE'
            {
            match("UNARCHIVE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_UNARCHIVE"

    // $ANTLR start "KW_COMPUTE"
    public final void mKW_COMPUTE() throws RecognitionException {
        try {
            int _type = KW_COMPUTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:246:11: ( 'COMPUTE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:246:13: 'COMPUTE'
            {
            match("COMPUTE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_COMPUTE"

    // $ANTLR start "KW_STATISTICS"
    public final void mKW_STATISTICS() throws RecognitionException {
        try {
            int _type = KW_STATISTICS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:247:14: ( 'STATISTICS' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:247:16: 'STATISTICS'
            {
            match("STATISTICS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_STATISTICS"

    // $ANTLR start "KW_USE"
    public final void mKW_USE() throws RecognitionException {
        try {
            int _type = KW_USE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:248:7: ( 'USE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:248:9: 'USE'
            {
            match("USE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_USE"

    // $ANTLR start "KW_OPTION"
    public final void mKW_OPTION() throws RecognitionException {
        try {
            int _type = KW_OPTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:249:10: ( 'OPTION' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:249:12: 'OPTION'
            {
            match("OPTION"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_OPTION"

    // $ANTLR start "KW_CONCATENATE"
    public final void mKW_CONCATENATE() throws RecognitionException {
        try {
            int _type = KW_CONCATENATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:250:15: ( 'CONCATENATE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:250:17: 'CONCATENATE'
            {
            match("CONCATENATE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_CONCATENATE"

    // $ANTLR start "KW_SHOW_DATABASE"
    public final void mKW_SHOW_DATABASE() throws RecognitionException {
        try {
            int _type = KW_SHOW_DATABASE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:251:17: ( 'SHOW_DATABASE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:251:19: 'SHOW_DATABASE'
            {
            match("SHOW_DATABASE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_SHOW_DATABASE"

    // $ANTLR start "KW_UPDATE"
    public final void mKW_UPDATE() throws RecognitionException {
        try {
            int _type = KW_UPDATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:252:10: ( 'UPDATE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:252:12: 'UPDATE'
            {
            match("UPDATE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_UPDATE"

    // $ANTLR start "KW_RESTRICT"
    public final void mKW_RESTRICT() throws RecognitionException {
        try {
            int _type = KW_RESTRICT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:253:12: ( 'RESTRICT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:253:14: 'RESTRICT'
            {
            match("RESTRICT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_RESTRICT"

    // $ANTLR start "KW_CASCADE"
    public final void mKW_CASCADE() throws RecognitionException {
        try {
            int _type = KW_CASCADE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:254:11: ( 'CASCADE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:254:13: 'CASCADE'
            {
            match("CASCADE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_CASCADE"

    // $ANTLR start "KW_SKEWED"
    public final void mKW_SKEWED() throws RecognitionException {
        try {
            int _type = KW_SKEWED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:255:10: ( 'SKEWED' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:255:12: 'SKEWED'
            {
            match("SKEWED"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_SKEWED"

    // $ANTLR start "KW_ROLLUP"
    public final void mKW_ROLLUP() throws RecognitionException {
        try {
            int _type = KW_ROLLUP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:256:10: ( 'ROLLUP' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:256:12: 'ROLLUP'
            {
            match("ROLLUP"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_ROLLUP"

    // $ANTLR start "KW_CUBE"
    public final void mKW_CUBE() throws RecognitionException {
        try {
            int _type = KW_CUBE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:257:8: ( 'CUBE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:257:10: 'CUBE'
            {
            match("CUBE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_CUBE"

    // $ANTLR start "KW_DIRECTORIES"
    public final void mKW_DIRECTORIES() throws RecognitionException {
        try {
            int _type = KW_DIRECTORIES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:258:15: ( 'DIRECTORIES' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:258:17: 'DIRECTORIES'
            {
            match("DIRECTORIES"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_DIRECTORIES"

    // $ANTLR start "KW_FOR"
    public final void mKW_FOR() throws RecognitionException {
        try {
            int _type = KW_FOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:259:7: ( 'FOR' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:259:9: 'FOR'
            {
            match("FOR"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_FOR"

    // $ANTLR start "KW_WINDOW"
    public final void mKW_WINDOW() throws RecognitionException {
        try {
            int _type = KW_WINDOW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:260:10: ( 'WINDOW' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:260:12: 'WINDOW'
            {
            match("WINDOW"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_WINDOW"

    // $ANTLR start "KW_UNBOUNDED"
    public final void mKW_UNBOUNDED() throws RecognitionException {
        try {
            int _type = KW_UNBOUNDED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:261:13: ( 'UNBOUNDED' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:261:15: 'UNBOUNDED'
            {
            match("UNBOUNDED"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_UNBOUNDED"

    // $ANTLR start "KW_PRECEDING"
    public final void mKW_PRECEDING() throws RecognitionException {
        try {
            int _type = KW_PRECEDING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:262:13: ( 'PRECEDING' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:262:15: 'PRECEDING'
            {
            match("PRECEDING"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_PRECEDING"

    // $ANTLR start "KW_FOLLOWING"
    public final void mKW_FOLLOWING() throws RecognitionException {
        try {
            int _type = KW_FOLLOWING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:263:13: ( 'FOLLOWING' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:263:15: 'FOLLOWING'
            {
            match("FOLLOWING"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_FOLLOWING"

    // $ANTLR start "KW_CURRENT"
    public final void mKW_CURRENT() throws RecognitionException {
        try {
            int _type = KW_CURRENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:264:11: ( 'CURRENT' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:264:13: 'CURRENT'
            {
            match("CURRENT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_CURRENT"

    // $ANTLR start "KW_LESS"
    public final void mKW_LESS() throws RecognitionException {
        try {
            int _type = KW_LESS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:265:8: ( 'LESS' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:265:10: 'LESS'
            {
            match("LESS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_LESS"

    // $ANTLR start "KW_MORE"
    public final void mKW_MORE() throws RecognitionException {
        try {
            int _type = KW_MORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:266:8: ( 'MORE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:266:10: 'MORE'
            {
            match("MORE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_MORE"

    // $ANTLR start "KW_OVER"
    public final void mKW_OVER() throws RecognitionException {
        try {
            int _type = KW_OVER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:267:8: ( 'OVER' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:267:10: 'OVER'
            {
            match("OVER"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_OVER"

    // $ANTLR start "KW_GROUPING"
    public final void mKW_GROUPING() throws RecognitionException {
        try {
            int _type = KW_GROUPING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:268:12: ( 'GROUPING' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:268:14: 'GROUPING'
            {
            match("GROUPING"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_GROUPING"

    // $ANTLR start "KW_SETS"
    public final void mKW_SETS() throws RecognitionException {
        try {
            int _type = KW_SETS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:269:8: ( 'SETS' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:269:10: 'SETS'
            {
            match("SETS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_SETS"

    // $ANTLR start "KW_TRUNCATE"
    public final void mKW_TRUNCATE() throws RecognitionException {
        try {
            int _type = KW_TRUNCATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:270:12: ( 'TRUNCATE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:270:14: 'TRUNCATE'
            {
            match("TRUNCATE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_TRUNCATE"

    // $ANTLR start "KW_NOSCAN"
    public final void mKW_NOSCAN() throws RecognitionException {
        try {
            int _type = KW_NOSCAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:271:10: ( 'NOSCAN' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:271:12: 'NOSCAN'
            {
            match("NOSCAN"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_NOSCAN"

    // $ANTLR start "KW_PARTIALSCAN"
    public final void mKW_PARTIALSCAN() throws RecognitionException {
        try {
            int _type = KW_PARTIALSCAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:272:15: ( 'PARTIALSCAN' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:272:17: 'PARTIALSCAN'
            {
            match("PARTIALSCAN"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_PARTIALSCAN"

    // $ANTLR start "KW_USER"
    public final void mKW_USER() throws RecognitionException {
        try {
            int _type = KW_USER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:273:8: ( 'USER' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:273:10: 'USER'
            {
            match("USER"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_USER"

    // $ANTLR start "KW_ROLE"
    public final void mKW_ROLE() throws RecognitionException {
        try {
            int _type = KW_ROLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:274:8: ( 'ROLE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:274:10: 'ROLE'
            {
            match("ROLE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_ROLE"

    // $ANTLR start "KW_INNER"
    public final void mKW_INNER() throws RecognitionException {
        try {
            int _type = KW_INNER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:275:9: ( 'INNER' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:275:11: 'INNER'
            {
            match("INNER"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_INNER"

    // $ANTLR start "KW_EXCHANGE"
    public final void mKW_EXCHANGE() throws RecognitionException {
        try {
            int _type = KW_EXCHANGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:276:12: ( 'EXCHANGE' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:276:14: 'EXCHANGE'
            {
            match("EXCHANGE"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KW_EXCHANGE"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:281:5: ( '.' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:281:7: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:282:7: ( ':' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:282:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:283:7: ( ',' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:283:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "SEMICOLON"
    public final void mSEMICOLON() throws RecognitionException {
        try {
            int _type = SEMICOLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:284:11: ( ';' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:284:13: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SEMICOLON"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:286:8: ( '(' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:286:10: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:287:8: ( ')' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:287:10: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "LSQUARE"
    public final void mLSQUARE() throws RecognitionException {
        try {
            int _type = LSQUARE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:288:9: ( '[' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:288:11: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LSQUARE"

    // $ANTLR start "RSQUARE"
    public final void mRSQUARE() throws RecognitionException {
        try {
            int _type = RSQUARE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:289:9: ( ']' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:289:11: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RSQUARE"

    // $ANTLR start "LCURLY"
    public final void mLCURLY() throws RecognitionException {
        try {
            int _type = LCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:290:8: ( '{' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:290:10: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LCURLY"

    // $ANTLR start "RCURLY"
    public final void mRCURLY() throws RecognitionException {
        try {
            int _type = RCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:291:8: ( '}' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:291:10: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RCURLY"

    // $ANTLR start "EQUAL"
    public final void mEQUAL() throws RecognitionException {
        try {
            int _type = EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:293:7: ( '=' | '==' )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='=') ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1=='=') ) {
                    alt2=2;
                }
                else {
                    alt2=1;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }
            switch (alt2) {
                case 1 :
                    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:293:9: '='
                    {
                    match('='); 

                    }
                    break;
                case 2 :
                    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:293:15: '=='
                    {
                    match("=="); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EQUAL"

    // $ANTLR start "EQUAL_NS"
    public final void mEQUAL_NS() throws RecognitionException {
        try {
            int _type = EQUAL_NS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:294:10: ( '<=>' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:294:12: '<=>'
            {
            match("<=>"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EQUAL_NS"

    // $ANTLR start "NOTEQUAL"
    public final void mNOTEQUAL() throws RecognitionException {
        try {
            int _type = NOTEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:295:10: ( '<>' | '!=' )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='<') ) {
                alt3=1;
            }
            else if ( (LA3_0=='!') ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }
            switch (alt3) {
                case 1 :
                    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:295:12: '<>'
                    {
                    match("<>"); 



                    }
                    break;
                case 2 :
                    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:295:19: '!='
                    {
                    match("!="); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NOTEQUAL"

    // $ANTLR start "LESSTHANOREQUALTO"
    public final void mLESSTHANOREQUALTO() throws RecognitionException {
        try {
            int _type = LESSTHANOREQUALTO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:296:19: ( '<=' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:296:21: '<='
            {
            match("<="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LESSTHANOREQUALTO"

    // $ANTLR start "LESSTHAN"
    public final void mLESSTHAN() throws RecognitionException {
        try {
            int _type = LESSTHAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:297:10: ( '<' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:297:12: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LESSTHAN"

    // $ANTLR start "GREATERTHANOREQUALTO"
    public final void mGREATERTHANOREQUALTO() throws RecognitionException {
        try {
            int _type = GREATERTHANOREQUALTO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:298:22: ( '>=' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:298:24: '>='
            {
            match(">="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GREATERTHANOREQUALTO"

    // $ANTLR start "GREATERTHAN"
    public final void mGREATERTHAN() throws RecognitionException {
        try {
            int _type = GREATERTHAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:299:13: ( '>' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:299:15: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GREATERTHAN"

    // $ANTLR start "DIVIDE"
    public final void mDIVIDE() throws RecognitionException {
        try {
            int _type = DIVIDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:301:8: ( '/' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:301:10: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIVIDE"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:302:6: ( '+' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:302:8: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:303:7: ( '-' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:303:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "STAR"
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:304:6: ( '*' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:304:8: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STAR"

    // $ANTLR start "MOD"
    public final void mMOD() throws RecognitionException {
        try {
            int _type = MOD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:305:5: ( '%' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:305:7: '%'
            {
            match('%'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MOD"

    // $ANTLR start "DIV"
    public final void mDIV() throws RecognitionException {
        try {
            int _type = DIV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:306:5: ( 'DIV' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:306:7: 'DIV'
            {
            match("DIV"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIV"

    // $ANTLR start "AMPERSAND"
    public final void mAMPERSAND() throws RecognitionException {
        try {
            int _type = AMPERSAND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:308:11: ( '&' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:308:13: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AMPERSAND"

    // $ANTLR start "TILDE"
    public final void mTILDE() throws RecognitionException {
        try {
            int _type = TILDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:309:7: ( '~' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:309:9: '~'
            {
            match('~'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TILDE"

    // $ANTLR start "BITWISEOR"
    public final void mBITWISEOR() throws RecognitionException {
        try {
            int _type = BITWISEOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:310:11: ( '|' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:310:13: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BITWISEOR"

    // $ANTLR start "BITWISEXOR"
    public final void mBITWISEXOR() throws RecognitionException {
        try {
            int _type = BITWISEXOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:311:12: ( '^' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:311:14: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BITWISEXOR"

    // $ANTLR start "QUESTION"
    public final void mQUESTION() throws RecognitionException {
        try {
            int _type = QUESTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:312:10: ( '?' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:312:12: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "QUESTION"

    // $ANTLR start "DOLLAR"
    public final void mDOLLAR() throws RecognitionException {
        try {
            int _type = DOLLAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:313:8: ( '$' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:313:10: '$'
            {
            match('$'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DOLLAR"

    // $ANTLR start "Letter"
    public final void mLetter() throws RecognitionException {
        try {
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:319:5: ( 'a' .. 'z' | 'A' .. 'Z' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Letter"

    // $ANTLR start "HexDigit"
    public final void mHexDigit() throws RecognitionException {
        try {
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:324:5: ( 'a' .. 'f' | 'A' .. 'F' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HexDigit"

    // $ANTLR start "Digit"
    public final void mDigit() throws RecognitionException {
        try {
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:329:5: ( '0' .. '9' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Digit"

    // $ANTLR start "Exponent"
    public final void mExponent() throws RecognitionException {
        try {
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:335:5: ( ( 'e' | 'E' ) ( PLUS | MINUS )? ( Digit )+ )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:336:5: ( 'e' | 'E' ) ( PLUS | MINUS )? ( Digit )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:336:17: ( PLUS | MINUS )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='+'||LA4_0=='-') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:336:33: ( Digit )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0 >= '0' && LA5_0 <= '9')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Exponent"

    // $ANTLR start "RegexComponent"
    public final void mRegexComponent() throws RecognitionException {
        try {
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:341:5: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | PLUS | STAR | QUESTION | MINUS | DOT | LPAREN | RPAREN | LSQUARE | RSQUARE | LCURLY | RCURLY | BITWISEXOR | BITWISEOR | DOLLAR )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:
            {
            if ( input.LA(1)=='$'||(input.LA(1) >= '(' && input.LA(1) <= '+')||(input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= '9')||input.LA(1)=='?'||(input.LA(1) >= 'A' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '_')||(input.LA(1) >= 'a' && input.LA(1) <= '}') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RegexComponent"

    // $ANTLR start "StringLiteral"
    public final void mStringLiteral() throws RecognitionException {
        try {
            int _type = StringLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:348:5: ( ( '\\'' (~ ( '\\'' | '\\\\' ) | ( '\\\\' . ) )* '\\'' | '\\\"' (~ ( '\\\"' | '\\\\' ) | ( '\\\\' . ) )* '\\\"' )+ )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:349:5: ( '\\'' (~ ( '\\'' | '\\\\' ) | ( '\\\\' . ) )* '\\'' | '\\\"' (~ ( '\\\"' | '\\\\' ) | ( '\\\\' . ) )* '\\\"' )+
            {
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:349:5: ( '\\'' (~ ( '\\'' | '\\\\' ) | ( '\\\\' . ) )* '\\'' | '\\\"' (~ ( '\\\"' | '\\\\' ) | ( '\\\\' . ) )* '\\\"' )+
            int cnt8=0;
            loop8:
            do {
                int alt8=3;
                int LA8_0 = input.LA(1);

                if ( (LA8_0=='\'') ) {
                    alt8=1;
                }
                else if ( (LA8_0=='\"') ) {
                    alt8=2;
                }


                switch (alt8) {
            	case 1 :
            	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:349:7: '\\'' (~ ( '\\'' | '\\\\' ) | ( '\\\\' . ) )* '\\''
            	    {
            	    match('\''); 

            	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:349:12: (~ ( '\\'' | '\\\\' ) | ( '\\\\' . ) )*
            	    loop6:
            	    do {
            	        int alt6=3;
            	        int LA6_0 = input.LA(1);

            	        if ( ((LA6_0 >= '\u0000' && LA6_0 <= '&')||(LA6_0 >= '(' && LA6_0 <= '[')||(LA6_0 >= ']' && LA6_0 <= '\uFFFF')) ) {
            	            alt6=1;
            	        }
            	        else if ( (LA6_0=='\\') ) {
            	            alt6=2;
            	        }


            	        switch (alt6) {
            	    	case 1 :
            	    	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:349:14: ~ ( '\\'' | '\\\\' )
            	    	    {
            	    	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
            	    	        input.consume();
            	    	    }
            	    	    else {
            	    	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	    	        recover(mse);
            	    	        throw mse;
            	    	    }


            	    	    }
            	    	    break;
            	    	case 2 :
            	    	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:349:29: ( '\\\\' . )
            	    	    {
            	    	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:349:29: ( '\\\\' . )
            	    	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:349:30: '\\\\' .
            	    	    {
            	    	    match('\\'); 

            	    	    matchAny(); 

            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop6;
            	        }
            	    } while (true);


            	    match('\''); 

            	    }
            	    break;
            	case 2 :
            	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:350:7: '\\\"' (~ ( '\\\"' | '\\\\' ) | ( '\\\\' . ) )* '\\\"'
            	    {
            	    match('\"'); 

            	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:350:12: (~ ( '\\\"' | '\\\\' ) | ( '\\\\' . ) )*
            	    loop7:
            	    do {
            	        int alt7=3;
            	        int LA7_0 = input.LA(1);

            	        if ( ((LA7_0 >= '\u0000' && LA7_0 <= '!')||(LA7_0 >= '#' && LA7_0 <= '[')||(LA7_0 >= ']' && LA7_0 <= '\uFFFF')) ) {
            	            alt7=1;
            	        }
            	        else if ( (LA7_0=='\\') ) {
            	            alt7=2;
            	        }


            	        switch (alt7) {
            	    	case 1 :
            	    	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:350:14: ~ ( '\\\"' | '\\\\' )
            	    	    {
            	    	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
            	    	        input.consume();
            	    	    }
            	    	    else {
            	    	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	    	        recover(mse);
            	    	        throw mse;
            	    	    }


            	    	    }
            	    	    break;
            	    	case 2 :
            	    	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:350:29: ( '\\\\' . )
            	    	    {
            	    	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:350:29: ( '\\\\' . )
            	    	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:350:30: '\\\\' .
            	    	    {
            	    	    match('\\'); 

            	    	    matchAny(); 

            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop7;
            	        }
            	    } while (true);


            	    match('\"'); 

            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "StringLiteral"

    // $ANTLR start "CharSetLiteral"
    public final void mCharSetLiteral() throws RecognitionException {
        try {
            int _type = CharSetLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:355:5: ( StringLiteral | '0' 'X' ( HexDigit | Digit )+ )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='\"'||LA10_0=='\'') ) {
                alt10=1;
            }
            else if ( (LA10_0=='0') ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }
            switch (alt10) {
                case 1 :
                    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:356:5: StringLiteral
                    {
                    mStringLiteral(); 


                    }
                    break;
                case 2 :
                    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:357:7: '0' 'X' ( HexDigit | Digit )+
                    {
                    match('0'); 

                    match('X'); 

                    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:357:15: ( HexDigit | Digit )+
                    int cnt9=0;
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( ((LA9_0 >= '0' && LA9_0 <= '9')||(LA9_0 >= 'A' && LA9_0 <= 'F')||(LA9_0 >= 'a' && LA9_0 <= 'f')) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt9 >= 1 ) break loop9;
                                EarlyExitException eee =
                                    new EarlyExitException(9, input);
                                throw eee;
                        }
                        cnt9++;
                    } while (true);


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CharSetLiteral"

    // $ANTLR start "BigintLiteral"
    public final void mBigintLiteral() throws RecognitionException {
        try {
            int _type = BigintLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:361:5: ( ( Digit )+ 'L' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:362:5: ( Digit )+ 'L'
            {
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:362:5: ( Digit )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0 >= '0' && LA11_0 <= '9')) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);


            match('L'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BigintLiteral"

    // $ANTLR start "SmallintLiteral"
    public final void mSmallintLiteral() throws RecognitionException {
        try {
            int _type = SmallintLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:366:5: ( ( Digit )+ 'S' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:367:5: ( Digit )+ 'S'
            {
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:367:5: ( Digit )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0 >= '0' && LA12_0 <= '9')) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);


            match('S'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SmallintLiteral"

    // $ANTLR start "TinyintLiteral"
    public final void mTinyintLiteral() throws RecognitionException {
        try {
            int _type = TinyintLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:371:5: ( ( Digit )+ 'Y' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:372:5: ( Digit )+ 'Y'
            {
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:372:5: ( Digit )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0 >= '0' && LA13_0 <= '9')) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);


            match('Y'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TinyintLiteral"

    // $ANTLR start "DecimalLiteral"
    public final void mDecimalLiteral() throws RecognitionException {
        try {
            int _type = DecimalLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:376:5: ( Number 'B' 'D' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:377:5: Number 'B' 'D'
            {
            mNumber(); 


            match('B'); 

            match('D'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DecimalLiteral"

    // $ANTLR start "ByteLengthLiteral"
    public final void mByteLengthLiteral() throws RecognitionException {
        try {
            int _type = ByteLengthLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:381:5: ( ( Digit )+ ( 'b' | 'B' | 'k' | 'K' | 'm' | 'M' | 'g' | 'G' ) )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:382:5: ( Digit )+ ( 'b' | 'B' | 'k' | 'K' | 'm' | 'M' | 'g' | 'G' )
            {
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:382:5: ( Digit )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0 >= '0' && LA14_0 <= '9')) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt14 >= 1 ) break loop14;
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        throw eee;
                }
                cnt14++;
            } while (true);


            if ( input.LA(1)=='B'||input.LA(1)=='G'||input.LA(1)=='K'||input.LA(1)=='M'||input.LA(1)=='b'||input.LA(1)=='g'||input.LA(1)=='k'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ByteLengthLiteral"

    // $ANTLR start "Number"
    public final void mNumber() throws RecognitionException {
        try {
            int _type = Number;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:386:5: ( ( Digit )+ ( DOT ( Digit )* ( Exponent )? | Exponent )? )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:387:5: ( Digit )+ ( DOT ( Digit )* ( Exponent )? | Exponent )?
            {
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:387:5: ( Digit )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0 >= '0' && LA15_0 <= '9')) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt15 >= 1 ) break loop15;
                        EarlyExitException eee =
                            new EarlyExitException(15, input);
                        throw eee;
                }
                cnt15++;
            } while (true);


            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:387:14: ( DOT ( Digit )* ( Exponent )? | Exponent )?
            int alt18=3;
            int LA18_0 = input.LA(1);

            if ( (LA18_0=='.') ) {
                alt18=1;
            }
            else if ( (LA18_0=='E'||LA18_0=='e') ) {
                alt18=2;
            }
            switch (alt18) {
                case 1 :
                    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:387:16: DOT ( Digit )* ( Exponent )?
                    {
                    mDOT(); 


                    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:387:20: ( Digit )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( ((LA16_0 >= '0' && LA16_0 <= '9')) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);


                    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:387:29: ( Exponent )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0=='E'||LA17_0=='e') ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:387:30: Exponent
                            {
                            mExponent(); 


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:387:43: Exponent
                    {
                    mExponent(); 


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Number"

    // $ANTLR start "TimeUnit"
    public final void mTimeUnit() throws RecognitionException {
        try {
            int _type = TimeUnit;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:391:5: ( ( Digit )+ ( DOT ( Digit )* )? '(' ( 'd' | 'h' | 'm' | 's' | 'D' | 'H' | 'M' | 'S' ) ')' )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:392:5: ( Digit )+ ( DOT ( Digit )* )? '(' ( 'd' | 'h' | 'm' | 's' | 'D' | 'H' | 'M' | 'S' ) ')'
            {
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:392:5: ( Digit )+
            int cnt19=0;
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0 >= '0' && LA19_0 <= '9')) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt19 >= 1 ) break loop19;
                        EarlyExitException eee =
                            new EarlyExitException(19, input);
                        throw eee;
                }
                cnt19++;
            } while (true);


            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:392:14: ( DOT ( Digit )* )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0=='.') ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:392:15: DOT ( Digit )*
                    {
                    mDOT(); 


                    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:392:19: ( Digit )*
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( ((LA20_0 >= '0' && LA20_0 <= '9')) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop20;
                        }
                    } while (true);


                    }
                    break;

            }


            match('('); 

            if ( input.LA(1)=='D'||input.LA(1)=='H'||input.LA(1)=='M'||input.LA(1)=='S'||input.LA(1)=='d'||input.LA(1)=='h'||input.LA(1)=='m'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TimeUnit"

    // $ANTLR start "Identifier"
    public final void mIdentifier() throws RecognitionException {
        try {
            int _type = Identifier;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:396:5: ( ( Letter | Digit ) ( Letter | Digit | '_' )* | '`' ( RegexComponent )+ '`' )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( ((LA24_0 >= '0' && LA24_0 <= '9')||(LA24_0 >= 'A' && LA24_0 <= 'Z')||(LA24_0 >= 'a' && LA24_0 <= 'z')) ) {
                alt24=1;
            }
            else if ( (LA24_0=='`') ) {
                alt24=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;

            }
            switch (alt24) {
                case 1 :
                    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:397:5: ( Letter | Digit ) ( Letter | Digit | '_' )*
                    {
                    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:397:22: ( Letter | Digit | '_' )*
                    loop22:
                    do {
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( ((LA22_0 >= '0' && LA22_0 <= '9')||(LA22_0 >= 'A' && LA22_0 <= 'Z')||LA22_0=='_'||(LA22_0 >= 'a' && LA22_0 <= 'z')) ) {
                            alt22=1;
                        }


                        switch (alt22) {
                    	case 1 :
                    	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop22;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:398:7: '`' ( RegexComponent )+ '`'
                    {
                    match('`'); 

                    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:398:11: ( RegexComponent )+
                    int cnt23=0;
                    loop23:
                    do {
                        int alt23=2;
                        int LA23_0 = input.LA(1);

                        if ( (LA23_0=='$'||(LA23_0 >= '(' && LA23_0 <= '+')||(LA23_0 >= '-' && LA23_0 <= '.')||(LA23_0 >= '0' && LA23_0 <= '9')||LA23_0=='?'||(LA23_0 >= 'A' && LA23_0 <= '[')||(LA23_0 >= ']' && LA23_0 <= '_')||(LA23_0 >= 'a' && LA23_0 <= '}')) ) {
                            alt23=1;
                        }


                        switch (alt23) {
                    	case 1 :
                    	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:
                    	    {
                    	    if ( input.LA(1)=='$'||(input.LA(1) >= '(' && input.LA(1) <= '+')||(input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= '9')||input.LA(1)=='?'||(input.LA(1) >= 'A' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '_')||(input.LA(1) >= 'a' && input.LA(1) <= '}') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt23 >= 1 ) break loop23;
                                EarlyExitException eee =
                                    new EarlyExitException(23, input);
                                throw eee;
                        }
                        cnt23++;
                    } while (true);


                    match('`'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Identifier"

    // $ANTLR start "CharSetName"
    public final void mCharSetName() throws RecognitionException {
        try {
            int _type = CharSetName;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:402:5: ( '_' ( Letter | Digit | '_' | '-' | '.' | ':' )+ )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:403:5: '_' ( Letter | Digit | '_' | '-' | '.' | ':' )+
            {
            match('_'); 

            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:403:9: ( Letter | Digit | '_' | '-' | '.' | ':' )+
            int cnt25=0;
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( ((LA25_0 >= '-' && LA25_0 <= '.')||(LA25_0 >= '0' && LA25_0 <= ':')||(LA25_0 >= 'A' && LA25_0 <= 'Z')||LA25_0=='_'||(LA25_0 >= 'a' && LA25_0 <= 'z')) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:
            	    {
            	    if ( (input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= ':')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt25 >= 1 ) break loop25;
                        EarlyExitException eee =
                            new EarlyExitException(25, input);
                        throw eee;
                }
                cnt25++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CharSetName"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:406:5: ( ( ' ' | '\\r' | '\\t' | '\\n' ) )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:406:8: ( ' ' | '\\r' | '\\t' | '\\n' )
            {
            if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:410:3: ( '--' (~ ( '\\n' | '\\r' ) )* )
            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:410:5: '--' (~ ( '\\n' | '\\r' ) )*
            {
            match("--"); 



            // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:410:10: (~ ( '\\n' | '\\r' ) )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0 >= '\u0000' && LA26_0 <= '\t')||(LA26_0 >= '\u000B' && LA26_0 <= '\f')||(LA26_0 >= '\u000E' && LA26_0 <= '\uFFFF')) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);


             _channel=HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMENT"

    public void mTokens() throws RecognitionException {
        // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:8: ( KW_TRUE | KW_FALSE | KW_ALL | KW_AND | KW_OR | KW_NOT | KW_LIKE | KW_IF | KW_EXISTS | KW_CONSTANT | KW_INTERVAL | KW_INCRE | KW_ASC | KW_DESC | KW_ORDER | KW_GROUP | KW_BY | KW_HAVING | KW_WHERE | KW_FROM | KW_AS | KW_SELECT | KW_DISTINCT | KW_INSERT | KW_OVERWRITE | KW_OUTER | KW_UNIQUEJOIN | KW_PRESERVE | KW_JOIN | KW_LEFT | KW_RIGHT | KW_FULL | KW_ON | KW_PARTITION | KW_PARTITIONS | KW_TABLE | KW_TABLES | KW_COLUMNS | KW_INDEX | KW_INDEXES | KW_REBUILD | KW_FUNCTIONS | KW_SHOW | KW_MSCK | KW_REPAIR | KW_DIRECTORY | KW_LOCAL | KW_TRANSFORM | KW_USING | KW_CLUSTER | KW_DISTRIBUTE | KW_SORT | KW_UNION | KW_LOAD | KW_EXPORT | KW_IMPORT | KW_DATA | KW_INPATH | KW_IS | KW_NULL | KW_CREATE | KW_EXTERNAL | KW_ALTER | KW_CHANGE | KW_COLUMN | KW_FIRST | KW_AFTER | KW_DESCRIBE | KW_DROP | KW_RENAME | KW_IGNORE | KW_PROTECTION | KW_TO | KW_COMMENT | KW_BOOLEAN | KW_TINYINT | KW_SMALLINT | KW_INT | KW_BIGINT | KW_FLOAT | KW_DOUBLE | KW_DATE | KW_DATETIME | KW_TIMESTAMP | KW_DECIMAL | KW_STRING | KW_VARCHAR | KW_ARRAY | KW_STRUCT | KW_MAP | KW_UNIONTYPE | KW_REDUCE | KW_PARTITIONED | KW_CLUSTERED | KW_SORTED | KW_INTO | KW_BUCKETS | KW_ROW | KW_ROWS | KW_FORMAT | KW_DELIMITED | KW_FIELDS | KW_TERMINATED | KW_ESCAPED | KW_COLLECTION | KW_ITEMS | KW_KEYS | KW_KEY_TYPE | KW_LINES | KW_STORED | KW_FILEFORMAT | KW_SEQUENCEFILE | KW_TEXTFILE | KW_RCFILE | KW_ORCFILE | KW_INPUTFORMAT | KW_OUTPUTFORMAT | KW_INPUTDRIVER | KW_OUTPUTDRIVER | KW_OFFLINE | KW_ENABLE | KW_DISABLE | KW_READONLY | KW_NO_DROP | KW_LOCATION | KW_TABLESAMPLE | KW_BUCKET | KW_OUT | KW_OF | KW_PERCENT | KW_CAST | KW_ADD | KW_REPLACE | KW_RLIKE | KW_REGEXP | KW_TEMPORARY | KW_FUNCTION | KW_MACRO | KW_EXPLAIN | KW_EXTENDED | KW_FORMATTED | KW_PRETTY | KW_DEPENDENCY | KW_LOGICAL | KW_SERDE | KW_WITH | KW_DEFERRED | KW_SERDEPROPERTIES | KW_DBPROPERTIES | KW_LIMIT | KW_SET | KW_UNSET | KW_TBLPROPERTIES | KW_IDXPROPERTIES | KW_VALUE_TYPE | KW_ELEM_TYPE | KW_CASE | KW_WHEN | KW_THEN | KW_ELSE | KW_END | KW_MAPJOIN | KW_STREAMTABLE | KW_HOLD_DDLTIME | KW_CLUSTERSTATUS | KW_UTC | KW_UTCTIMESTAMP | KW_LONG | KW_DELETE | KW_PLUS | KW_MINUS | KW_FETCH | KW_INTERSECT | KW_VIEW | KW_IN | KW_DATABASE | KW_DATABASES | KW_MATERIALIZED | KW_SCHEMA | KW_SCHEMAS | KW_GRANT | KW_REVOKE | KW_SSL | KW_UNDO | KW_LOCK | KW_LOCKS | KW_UNLOCK | KW_SHARED | KW_EXCLUSIVE | KW_PROCEDURE | KW_UNSIGNED | KW_WHILE | KW_READ | KW_READS | KW_PURGE | KW_RANGE | KW_ANALYZE | KW_BEFORE | KW_BETWEEN | KW_BOTH | KW_BINARY | KW_CROSS | KW_CONTINUE | KW_CURSOR | KW_TRIGGER | KW_RECORDREADER | KW_RECORDWRITER | KW_SEMI | KW_LATERAL | KW_TOUCH | KW_ARCHIVE | KW_UNARCHIVE | KW_COMPUTE | KW_STATISTICS | KW_USE | KW_OPTION | KW_CONCATENATE | KW_SHOW_DATABASE | KW_UPDATE | KW_RESTRICT | KW_CASCADE | KW_SKEWED | KW_ROLLUP | KW_CUBE | KW_DIRECTORIES | KW_FOR | KW_WINDOW | KW_UNBOUNDED | KW_PRECEDING | KW_FOLLOWING | KW_CURRENT | KW_LESS | KW_MORE | KW_OVER | KW_GROUPING | KW_SETS | KW_TRUNCATE | KW_NOSCAN | KW_PARTIALSCAN | KW_USER | KW_ROLE | KW_INNER | KW_EXCHANGE | DOT | COLON | COMMA | SEMICOLON | LPAREN | RPAREN | LSQUARE | RSQUARE | LCURLY | RCURLY | EQUAL | EQUAL_NS | NOTEQUAL | LESSTHANOREQUALTO | LESSTHAN | GREATERTHANOREQUALTO | GREATERTHAN | DIVIDE | PLUS | MINUS | STAR | MOD | DIV | AMPERSAND | TILDE | BITWISEOR | BITWISEXOR | QUESTION | DOLLAR | StringLiteral | CharSetLiteral | BigintLiteral | SmallintLiteral | TinyintLiteral | DecimalLiteral | ByteLengthLiteral | Number | TimeUnit | Identifier | CharSetName | WS | COMMENT )
        int alt27=285;
        alt27 = dfa27.predict(input);
        switch (alt27) {
            case 1 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:10: KW_TRUE
                {
                mKW_TRUE(); 


                }
                break;
            case 2 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:18: KW_FALSE
                {
                mKW_FALSE(); 


                }
                break;
            case 3 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:27: KW_ALL
                {
                mKW_ALL(); 


                }
                break;
            case 4 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:34: KW_AND
                {
                mKW_AND(); 


                }
                break;
            case 5 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:41: KW_OR
                {
                mKW_OR(); 


                }
                break;
            case 6 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:47: KW_NOT
                {
                mKW_NOT(); 


                }
                break;
            case 7 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:54: KW_LIKE
                {
                mKW_LIKE(); 


                }
                break;
            case 8 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:62: KW_IF
                {
                mKW_IF(); 


                }
                break;
            case 9 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:68: KW_EXISTS
                {
                mKW_EXISTS(); 


                }
                break;
            case 10 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:78: KW_CONSTANT
                {
                mKW_CONSTANT(); 


                }
                break;
            case 11 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:90: KW_INTERVAL
                {
                mKW_INTERVAL(); 


                }
                break;
            case 12 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:102: KW_INCRE
                {
                mKW_INCRE(); 


                }
                break;
            case 13 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:111: KW_ASC
                {
                mKW_ASC(); 


                }
                break;
            case 14 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:118: KW_DESC
                {
                mKW_DESC(); 


                }
                break;
            case 15 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:126: KW_ORDER
                {
                mKW_ORDER(); 


                }
                break;
            case 16 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:135: KW_GROUP
                {
                mKW_GROUP(); 


                }
                break;
            case 17 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:144: KW_BY
                {
                mKW_BY(); 


                }
                break;
            case 18 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:150: KW_HAVING
                {
                mKW_HAVING(); 


                }
                break;
            case 19 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:160: KW_WHERE
                {
                mKW_WHERE(); 


                }
                break;
            case 20 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:169: KW_FROM
                {
                mKW_FROM(); 


                }
                break;
            case 21 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:177: KW_AS
                {
                mKW_AS(); 


                }
                break;
            case 22 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:183: KW_SELECT
                {
                mKW_SELECT(); 


                }
                break;
            case 23 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:193: KW_DISTINCT
                {
                mKW_DISTINCT(); 


                }
                break;
            case 24 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:205: KW_INSERT
                {
                mKW_INSERT(); 


                }
                break;
            case 25 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:215: KW_OVERWRITE
                {
                mKW_OVERWRITE(); 


                }
                break;
            case 26 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:228: KW_OUTER
                {
                mKW_OUTER(); 


                }
                break;
            case 27 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:237: KW_UNIQUEJOIN
                {
                mKW_UNIQUEJOIN(); 


                }
                break;
            case 28 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:251: KW_PRESERVE
                {
                mKW_PRESERVE(); 


                }
                break;
            case 29 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:263: KW_JOIN
                {
                mKW_JOIN(); 


                }
                break;
            case 30 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:271: KW_LEFT
                {
                mKW_LEFT(); 


                }
                break;
            case 31 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:279: KW_RIGHT
                {
                mKW_RIGHT(); 


                }
                break;
            case 32 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:288: KW_FULL
                {
                mKW_FULL(); 


                }
                break;
            case 33 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:296: KW_ON
                {
                mKW_ON(); 


                }
                break;
            case 34 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:302: KW_PARTITION
                {
                mKW_PARTITION(); 


                }
                break;
            case 35 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:315: KW_PARTITIONS
                {
                mKW_PARTITIONS(); 


                }
                break;
            case 36 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:329: KW_TABLE
                {
                mKW_TABLE(); 


                }
                break;
            case 37 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:338: KW_TABLES
                {
                mKW_TABLES(); 


                }
                break;
            case 38 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:348: KW_COLUMNS
                {
                mKW_COLUMNS(); 


                }
                break;
            case 39 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:359: KW_INDEX
                {
                mKW_INDEX(); 


                }
                break;
            case 40 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:368: KW_INDEXES
                {
                mKW_INDEXES(); 


                }
                break;
            case 41 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:379: KW_REBUILD
                {
                mKW_REBUILD(); 


                }
                break;
            case 42 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:390: KW_FUNCTIONS
                {
                mKW_FUNCTIONS(); 


                }
                break;
            case 43 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:403: KW_SHOW
                {
                mKW_SHOW(); 


                }
                break;
            case 44 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:411: KW_MSCK
                {
                mKW_MSCK(); 


                }
                break;
            case 45 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:419: KW_REPAIR
                {
                mKW_REPAIR(); 


                }
                break;
            case 46 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:429: KW_DIRECTORY
                {
                mKW_DIRECTORY(); 


                }
                break;
            case 47 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:442: KW_LOCAL
                {
                mKW_LOCAL(); 


                }
                break;
            case 48 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:451: KW_TRANSFORM
                {
                mKW_TRANSFORM(); 


                }
                break;
            case 49 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:464: KW_USING
                {
                mKW_USING(); 


                }
                break;
            case 50 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:473: KW_CLUSTER
                {
                mKW_CLUSTER(); 


                }
                break;
            case 51 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:484: KW_DISTRIBUTE
                {
                mKW_DISTRIBUTE(); 


                }
                break;
            case 52 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:498: KW_SORT
                {
                mKW_SORT(); 


                }
                break;
            case 53 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:506: KW_UNION
                {
                mKW_UNION(); 


                }
                break;
            case 54 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:515: KW_LOAD
                {
                mKW_LOAD(); 


                }
                break;
            case 55 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:523: KW_EXPORT
                {
                mKW_EXPORT(); 


                }
                break;
            case 56 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:533: KW_IMPORT
                {
                mKW_IMPORT(); 


                }
                break;
            case 57 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:543: KW_DATA
                {
                mKW_DATA(); 


                }
                break;
            case 58 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:551: KW_INPATH
                {
                mKW_INPATH(); 


                }
                break;
            case 59 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:561: KW_IS
                {
                mKW_IS(); 


                }
                break;
            case 60 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:567: KW_NULL
                {
                mKW_NULL(); 


                }
                break;
            case 61 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:575: KW_CREATE
                {
                mKW_CREATE(); 


                }
                break;
            case 62 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:585: KW_EXTERNAL
                {
                mKW_EXTERNAL(); 


                }
                break;
            case 63 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:597: KW_ALTER
                {
                mKW_ALTER(); 


                }
                break;
            case 64 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:606: KW_CHANGE
                {
                mKW_CHANGE(); 


                }
                break;
            case 65 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:616: KW_COLUMN
                {
                mKW_COLUMN(); 


                }
                break;
            case 66 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:626: KW_FIRST
                {
                mKW_FIRST(); 


                }
                break;
            case 67 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:635: KW_AFTER
                {
                mKW_AFTER(); 


                }
                break;
            case 68 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:644: KW_DESCRIBE
                {
                mKW_DESCRIBE(); 


                }
                break;
            case 69 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:656: KW_DROP
                {
                mKW_DROP(); 


                }
                break;
            case 70 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:664: KW_RENAME
                {
                mKW_RENAME(); 


                }
                break;
            case 71 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:674: KW_IGNORE
                {
                mKW_IGNORE(); 


                }
                break;
            case 72 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:684: KW_PROTECTION
                {
                mKW_PROTECTION(); 


                }
                break;
            case 73 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:698: KW_TO
                {
                mKW_TO(); 


                }
                break;
            case 74 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:704: KW_COMMENT
                {
                mKW_COMMENT(); 


                }
                break;
            case 75 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:715: KW_BOOLEAN
                {
                mKW_BOOLEAN(); 


                }
                break;
            case 76 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:726: KW_TINYINT
                {
                mKW_TINYINT(); 


                }
                break;
            case 77 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:737: KW_SMALLINT
                {
                mKW_SMALLINT(); 


                }
                break;
            case 78 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:749: KW_INT
                {
                mKW_INT(); 


                }
                break;
            case 79 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:756: KW_BIGINT
                {
                mKW_BIGINT(); 


                }
                break;
            case 80 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:766: KW_FLOAT
                {
                mKW_FLOAT(); 


                }
                break;
            case 81 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:775: KW_DOUBLE
                {
                mKW_DOUBLE(); 


                }
                break;
            case 82 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:785: KW_DATE
                {
                mKW_DATE(); 


                }
                break;
            case 83 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:793: KW_DATETIME
                {
                mKW_DATETIME(); 


                }
                break;
            case 84 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:805: KW_TIMESTAMP
                {
                mKW_TIMESTAMP(); 


                }
                break;
            case 85 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:818: KW_DECIMAL
                {
                mKW_DECIMAL(); 


                }
                break;
            case 86 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:829: KW_STRING
                {
                mKW_STRING(); 


                }
                break;
            case 87 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:839: KW_VARCHAR
                {
                mKW_VARCHAR(); 


                }
                break;
            case 88 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:850: KW_ARRAY
                {
                mKW_ARRAY(); 


                }
                break;
            case 89 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:859: KW_STRUCT
                {
                mKW_STRUCT(); 


                }
                break;
            case 90 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:869: KW_MAP
                {
                mKW_MAP(); 


                }
                break;
            case 91 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:876: KW_UNIONTYPE
                {
                mKW_UNIONTYPE(); 


                }
                break;
            case 92 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:889: KW_REDUCE
                {
                mKW_REDUCE(); 


                }
                break;
            case 93 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:899: KW_PARTITIONED
                {
                mKW_PARTITIONED(); 


                }
                break;
            case 94 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:914: KW_CLUSTERED
                {
                mKW_CLUSTERED(); 


                }
                break;
            case 95 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:927: KW_SORTED
                {
                mKW_SORTED(); 


                }
                break;
            case 96 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:937: KW_INTO
                {
                mKW_INTO(); 


                }
                break;
            case 97 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:945: KW_BUCKETS
                {
                mKW_BUCKETS(); 


                }
                break;
            case 98 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:956: KW_ROW
                {
                mKW_ROW(); 


                }
                break;
            case 99 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:963: KW_ROWS
                {
                mKW_ROWS(); 


                }
                break;
            case 100 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:971: KW_FORMAT
                {
                mKW_FORMAT(); 


                }
                break;
            case 101 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:981: KW_DELIMITED
                {
                mKW_DELIMITED(); 


                }
                break;
            case 102 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:994: KW_FIELDS
                {
                mKW_FIELDS(); 


                }
                break;
            case 103 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1004: KW_TERMINATED
                {
                mKW_TERMINATED(); 


                }
                break;
            case 104 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1018: KW_ESCAPED
                {
                mKW_ESCAPED(); 


                }
                break;
            case 105 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1029: KW_COLLECTION
                {
                mKW_COLLECTION(); 


                }
                break;
            case 106 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1043: KW_ITEMS
                {
                mKW_ITEMS(); 


                }
                break;
            case 107 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1052: KW_KEYS
                {
                mKW_KEYS(); 


                }
                break;
            case 108 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1060: KW_KEY_TYPE
                {
                mKW_KEY_TYPE(); 


                }
                break;
            case 109 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1072: KW_LINES
                {
                mKW_LINES(); 


                }
                break;
            case 110 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1081: KW_STORED
                {
                mKW_STORED(); 


                }
                break;
            case 111 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1091: KW_FILEFORMAT
                {
                mKW_FILEFORMAT(); 


                }
                break;
            case 112 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1105: KW_SEQUENCEFILE
                {
                mKW_SEQUENCEFILE(); 


                }
                break;
            case 113 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1121: KW_TEXTFILE
                {
                mKW_TEXTFILE(); 


                }
                break;
            case 114 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1133: KW_RCFILE
                {
                mKW_RCFILE(); 


                }
                break;
            case 115 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1143: KW_ORCFILE
                {
                mKW_ORCFILE(); 


                }
                break;
            case 116 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1154: KW_INPUTFORMAT
                {
                mKW_INPUTFORMAT(); 


                }
                break;
            case 117 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1169: KW_OUTPUTFORMAT
                {
                mKW_OUTPUTFORMAT(); 


                }
                break;
            case 118 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1185: KW_INPUTDRIVER
                {
                mKW_INPUTDRIVER(); 


                }
                break;
            case 119 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1200: KW_OUTPUTDRIVER
                {
                mKW_OUTPUTDRIVER(); 


                }
                break;
            case 120 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1216: KW_OFFLINE
                {
                mKW_OFFLINE(); 


                }
                break;
            case 121 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1227: KW_ENABLE
                {
                mKW_ENABLE(); 


                }
                break;
            case 122 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1237: KW_DISABLE
                {
                mKW_DISABLE(); 


                }
                break;
            case 123 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1248: KW_READONLY
                {
                mKW_READONLY(); 


                }
                break;
            case 124 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1260: KW_NO_DROP
                {
                mKW_NO_DROP(); 


                }
                break;
            case 125 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1271: KW_LOCATION
                {
                mKW_LOCATION(); 


                }
                break;
            case 126 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1283: KW_TABLESAMPLE
                {
                mKW_TABLESAMPLE(); 


                }
                break;
            case 127 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1298: KW_BUCKET
                {
                mKW_BUCKET(); 


                }
                break;
            case 128 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1308: KW_OUT
                {
                mKW_OUT(); 


                }
                break;
            case 129 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1315: KW_OF
                {
                mKW_OF(); 


                }
                break;
            case 130 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1321: KW_PERCENT
                {
                mKW_PERCENT(); 


                }
                break;
            case 131 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1332: KW_CAST
                {
                mKW_CAST(); 


                }
                break;
            case 132 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1340: KW_ADD
                {
                mKW_ADD(); 


                }
                break;
            case 133 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1347: KW_REPLACE
                {
                mKW_REPLACE(); 


                }
                break;
            case 134 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1358: KW_RLIKE
                {
                mKW_RLIKE(); 


                }
                break;
            case 135 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1367: KW_REGEXP
                {
                mKW_REGEXP(); 


                }
                break;
            case 136 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1377: KW_TEMPORARY
                {
                mKW_TEMPORARY(); 


                }
                break;
            case 137 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1390: KW_FUNCTION
                {
                mKW_FUNCTION(); 


                }
                break;
            case 138 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1402: KW_MACRO
                {
                mKW_MACRO(); 


                }
                break;
            case 139 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1411: KW_EXPLAIN
                {
                mKW_EXPLAIN(); 


                }
                break;
            case 140 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1422: KW_EXTENDED
                {
                mKW_EXTENDED(); 


                }
                break;
            case 141 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1434: KW_FORMATTED
                {
                mKW_FORMATTED(); 


                }
                break;
            case 142 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1447: KW_PRETTY
                {
                mKW_PRETTY(); 


                }
                break;
            case 143 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1457: KW_DEPENDENCY
                {
                mKW_DEPENDENCY(); 


                }
                break;
            case 144 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1471: KW_LOGICAL
                {
                mKW_LOGICAL(); 


                }
                break;
            case 145 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1482: KW_SERDE
                {
                mKW_SERDE(); 


                }
                break;
            case 146 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1491: KW_WITH
                {
                mKW_WITH(); 


                }
                break;
            case 147 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1499: KW_DEFERRED
                {
                mKW_DEFERRED(); 


                }
                break;
            case 148 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1511: KW_SERDEPROPERTIES
                {
                mKW_SERDEPROPERTIES(); 


                }
                break;
            case 149 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1530: KW_DBPROPERTIES
                {
                mKW_DBPROPERTIES(); 


                }
                break;
            case 150 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1546: KW_LIMIT
                {
                mKW_LIMIT(); 


                }
                break;
            case 151 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1555: KW_SET
                {
                mKW_SET(); 


                }
                break;
            case 152 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1562: KW_UNSET
                {
                mKW_UNSET(); 


                }
                break;
            case 153 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1571: KW_TBLPROPERTIES
                {
                mKW_TBLPROPERTIES(); 


                }
                break;
            case 154 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1588: KW_IDXPROPERTIES
                {
                mKW_IDXPROPERTIES(); 


                }
                break;
            case 155 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1605: KW_VALUE_TYPE
                {
                mKW_VALUE_TYPE(); 


                }
                break;
            case 156 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1619: KW_ELEM_TYPE
                {
                mKW_ELEM_TYPE(); 


                }
                break;
            case 157 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1632: KW_CASE
                {
                mKW_CASE(); 


                }
                break;
            case 158 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1640: KW_WHEN
                {
                mKW_WHEN(); 


                }
                break;
            case 159 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1648: KW_THEN
                {
                mKW_THEN(); 


                }
                break;
            case 160 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1656: KW_ELSE
                {
                mKW_ELSE(); 


                }
                break;
            case 161 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1664: KW_END
                {
                mKW_END(); 


                }
                break;
            case 162 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1671: KW_MAPJOIN
                {
                mKW_MAPJOIN(); 


                }
                break;
            case 163 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1682: KW_STREAMTABLE
                {
                mKW_STREAMTABLE(); 


                }
                break;
            case 164 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1697: KW_HOLD_DDLTIME
                {
                mKW_HOLD_DDLTIME(); 


                }
                break;
            case 165 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1713: KW_CLUSTERSTATUS
                {
                mKW_CLUSTERSTATUS(); 


                }
                break;
            case 166 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1730: KW_UTC
                {
                mKW_UTC(); 


                }
                break;
            case 167 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1737: KW_UTCTIMESTAMP
                {
                mKW_UTCTIMESTAMP(); 


                }
                break;
            case 168 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1753: KW_LONG
                {
                mKW_LONG(); 


                }
                break;
            case 169 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1761: KW_DELETE
                {
                mKW_DELETE(); 


                }
                break;
            case 170 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1771: KW_PLUS
                {
                mKW_PLUS(); 


                }
                break;
            case 171 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1779: KW_MINUS
                {
                mKW_MINUS(); 


                }
                break;
            case 172 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1788: KW_FETCH
                {
                mKW_FETCH(); 


                }
                break;
            case 173 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1797: KW_INTERSECT
                {
                mKW_INTERSECT(); 


                }
                break;
            case 174 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1810: KW_VIEW
                {
                mKW_VIEW(); 


                }
                break;
            case 175 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1818: KW_IN
                {
                mKW_IN(); 


                }
                break;
            case 176 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1824: KW_DATABASE
                {
                mKW_DATABASE(); 


                }
                break;
            case 177 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1836: KW_DATABASES
                {
                mKW_DATABASES(); 


                }
                break;
            case 178 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1849: KW_MATERIALIZED
                {
                mKW_MATERIALIZED(); 


                }
                break;
            case 179 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1865: KW_SCHEMA
                {
                mKW_SCHEMA(); 


                }
                break;
            case 180 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1875: KW_SCHEMAS
                {
                mKW_SCHEMAS(); 


                }
                break;
            case 181 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1886: KW_GRANT
                {
                mKW_GRANT(); 


                }
                break;
            case 182 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1895: KW_REVOKE
                {
                mKW_REVOKE(); 


                }
                break;
            case 183 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1905: KW_SSL
                {
                mKW_SSL(); 


                }
                break;
            case 184 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1912: KW_UNDO
                {
                mKW_UNDO(); 


                }
                break;
            case 185 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1920: KW_LOCK
                {
                mKW_LOCK(); 


                }
                break;
            case 186 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1928: KW_LOCKS
                {
                mKW_LOCKS(); 


                }
                break;
            case 187 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1937: KW_UNLOCK
                {
                mKW_UNLOCK(); 


                }
                break;
            case 188 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1947: KW_SHARED
                {
                mKW_SHARED(); 


                }
                break;
            case 189 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1957: KW_EXCLUSIVE
                {
                mKW_EXCLUSIVE(); 


                }
                break;
            case 190 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1970: KW_PROCEDURE
                {
                mKW_PROCEDURE(); 


                }
                break;
            case 191 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1983: KW_UNSIGNED
                {
                mKW_UNSIGNED(); 


                }
                break;
            case 192 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:1995: KW_WHILE
                {
                mKW_WHILE(); 


                }
                break;
            case 193 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2004: KW_READ
                {
                mKW_READ(); 


                }
                break;
            case 194 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2012: KW_READS
                {
                mKW_READS(); 


                }
                break;
            case 195 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2021: KW_PURGE
                {
                mKW_PURGE(); 


                }
                break;
            case 196 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2030: KW_RANGE
                {
                mKW_RANGE(); 


                }
                break;
            case 197 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2039: KW_ANALYZE
                {
                mKW_ANALYZE(); 


                }
                break;
            case 198 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2050: KW_BEFORE
                {
                mKW_BEFORE(); 


                }
                break;
            case 199 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2060: KW_BETWEEN
                {
                mKW_BETWEEN(); 


                }
                break;
            case 200 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2071: KW_BOTH
                {
                mKW_BOTH(); 


                }
                break;
            case 201 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2079: KW_BINARY
                {
                mKW_BINARY(); 


                }
                break;
            case 202 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2089: KW_CROSS
                {
                mKW_CROSS(); 


                }
                break;
            case 203 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2098: KW_CONTINUE
                {
                mKW_CONTINUE(); 


                }
                break;
            case 204 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2110: KW_CURSOR
                {
                mKW_CURSOR(); 


                }
                break;
            case 205 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2120: KW_TRIGGER
                {
                mKW_TRIGGER(); 


                }
                break;
            case 206 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2131: KW_RECORDREADER
                {
                mKW_RECORDREADER(); 


                }
                break;
            case 207 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2147: KW_RECORDWRITER
                {
                mKW_RECORDWRITER(); 


                }
                break;
            case 208 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2163: KW_SEMI
                {
                mKW_SEMI(); 


                }
                break;
            case 209 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2171: KW_LATERAL
                {
                mKW_LATERAL(); 


                }
                break;
            case 210 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2182: KW_TOUCH
                {
                mKW_TOUCH(); 


                }
                break;
            case 211 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2191: KW_ARCHIVE
                {
                mKW_ARCHIVE(); 


                }
                break;
            case 212 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2202: KW_UNARCHIVE
                {
                mKW_UNARCHIVE(); 


                }
                break;
            case 213 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2215: KW_COMPUTE
                {
                mKW_COMPUTE(); 


                }
                break;
            case 214 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2226: KW_STATISTICS
                {
                mKW_STATISTICS(); 


                }
                break;
            case 215 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2240: KW_USE
                {
                mKW_USE(); 


                }
                break;
            case 216 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2247: KW_OPTION
                {
                mKW_OPTION(); 


                }
                break;
            case 217 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2257: KW_CONCATENATE
                {
                mKW_CONCATENATE(); 


                }
                break;
            case 218 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2272: KW_SHOW_DATABASE
                {
                mKW_SHOW_DATABASE(); 


                }
                break;
            case 219 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2289: KW_UPDATE
                {
                mKW_UPDATE(); 


                }
                break;
            case 220 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2299: KW_RESTRICT
                {
                mKW_RESTRICT(); 


                }
                break;
            case 221 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2311: KW_CASCADE
                {
                mKW_CASCADE(); 


                }
                break;
            case 222 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2322: KW_SKEWED
                {
                mKW_SKEWED(); 


                }
                break;
            case 223 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2332: KW_ROLLUP
                {
                mKW_ROLLUP(); 


                }
                break;
            case 224 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2342: KW_CUBE
                {
                mKW_CUBE(); 


                }
                break;
            case 225 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2350: KW_DIRECTORIES
                {
                mKW_DIRECTORIES(); 


                }
                break;
            case 226 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2365: KW_FOR
                {
                mKW_FOR(); 


                }
                break;
            case 227 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2372: KW_WINDOW
                {
                mKW_WINDOW(); 


                }
                break;
            case 228 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2382: KW_UNBOUNDED
                {
                mKW_UNBOUNDED(); 


                }
                break;
            case 229 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2395: KW_PRECEDING
                {
                mKW_PRECEDING(); 


                }
                break;
            case 230 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2408: KW_FOLLOWING
                {
                mKW_FOLLOWING(); 


                }
                break;
            case 231 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2421: KW_CURRENT
                {
                mKW_CURRENT(); 


                }
                break;
            case 232 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2432: KW_LESS
                {
                mKW_LESS(); 


                }
                break;
            case 233 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2440: KW_MORE
                {
                mKW_MORE(); 


                }
                break;
            case 234 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2448: KW_OVER
                {
                mKW_OVER(); 


                }
                break;
            case 235 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2456: KW_GROUPING
                {
                mKW_GROUPING(); 


                }
                break;
            case 236 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2468: KW_SETS
                {
                mKW_SETS(); 


                }
                break;
            case 237 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2476: KW_TRUNCATE
                {
                mKW_TRUNCATE(); 


                }
                break;
            case 238 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2488: KW_NOSCAN
                {
                mKW_NOSCAN(); 


                }
                break;
            case 239 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2498: KW_PARTIALSCAN
                {
                mKW_PARTIALSCAN(); 


                }
                break;
            case 240 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2513: KW_USER
                {
                mKW_USER(); 


                }
                break;
            case 241 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2521: KW_ROLE
                {
                mKW_ROLE(); 


                }
                break;
            case 242 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2529: KW_INNER
                {
                mKW_INNER(); 


                }
                break;
            case 243 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2538: KW_EXCHANGE
                {
                mKW_EXCHANGE(); 


                }
                break;
            case 244 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2550: DOT
                {
                mDOT(); 


                }
                break;
            case 245 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2554: COLON
                {
                mCOLON(); 


                }
                break;
            case 246 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2560: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 247 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2566: SEMICOLON
                {
                mSEMICOLON(); 


                }
                break;
            case 248 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2576: LPAREN
                {
                mLPAREN(); 


                }
                break;
            case 249 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2583: RPAREN
                {
                mRPAREN(); 


                }
                break;
            case 250 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2590: LSQUARE
                {
                mLSQUARE(); 


                }
                break;
            case 251 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2598: RSQUARE
                {
                mRSQUARE(); 


                }
                break;
            case 252 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2606: LCURLY
                {
                mLCURLY(); 


                }
                break;
            case 253 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2613: RCURLY
                {
                mRCURLY(); 


                }
                break;
            case 254 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2620: EQUAL
                {
                mEQUAL(); 


                }
                break;
            case 255 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2626: EQUAL_NS
                {
                mEQUAL_NS(); 


                }
                break;
            case 256 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2635: NOTEQUAL
                {
                mNOTEQUAL(); 


                }
                break;
            case 257 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2644: LESSTHANOREQUALTO
                {
                mLESSTHANOREQUALTO(); 


                }
                break;
            case 258 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2662: LESSTHAN
                {
                mLESSTHAN(); 


                }
                break;
            case 259 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2671: GREATERTHANOREQUALTO
                {
                mGREATERTHANOREQUALTO(); 


                }
                break;
            case 260 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2692: GREATERTHAN
                {
                mGREATERTHAN(); 


                }
                break;
            case 261 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2704: DIVIDE
                {
                mDIVIDE(); 


                }
                break;
            case 262 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2711: PLUS
                {
                mPLUS(); 


                }
                break;
            case 263 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2716: MINUS
                {
                mMINUS(); 


                }
                break;
            case 264 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2722: STAR
                {
                mSTAR(); 


                }
                break;
            case 265 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2727: MOD
                {
                mMOD(); 


                }
                break;
            case 266 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2731: DIV
                {
                mDIV(); 


                }
                break;
            case 267 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2735: AMPERSAND
                {
                mAMPERSAND(); 


                }
                break;
            case 268 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2745: TILDE
                {
                mTILDE(); 


                }
                break;
            case 269 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2751: BITWISEOR
                {
                mBITWISEOR(); 


                }
                break;
            case 270 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2761: BITWISEXOR
                {
                mBITWISEXOR(); 


                }
                break;
            case 271 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2772: QUESTION
                {
                mQUESTION(); 


                }
                break;
            case 272 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2781: DOLLAR
                {
                mDOLLAR(); 


                }
                break;
            case 273 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2788: StringLiteral
                {
                mStringLiteral(); 


                }
                break;
            case 274 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2802: CharSetLiteral
                {
                mCharSetLiteral(); 


                }
                break;
            case 275 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2817: BigintLiteral
                {
                mBigintLiteral(); 


                }
                break;
            case 276 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2831: SmallintLiteral
                {
                mSmallintLiteral(); 


                }
                break;
            case 277 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2847: TinyintLiteral
                {
                mTinyintLiteral(); 


                }
                break;
            case 278 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2862: DecimalLiteral
                {
                mDecimalLiteral(); 


                }
                break;
            case 279 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2877: ByteLengthLiteral
                {
                mByteLengthLiteral(); 


                }
                break;
            case 280 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2895: Number
                {
                mNumber(); 


                }
                break;
            case 281 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2902: TimeUnit
                {
                mTimeUnit(); 


                }
                break;
            case 282 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2911: Identifier
                {
                mIdentifier(); 


                }
                break;
            case 283 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2922: CharSetName
                {
                mCharSetName(); 


                }
                break;
            case 284 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2934: WS
                {
                mWS(); 


                }
                break;
            case 285 :
                // /home/kangyanli/hive-0.12.0/src/ql/src/java/org/apache/hadoop/hive/ql/parse/HiveLexer.g:1:2937: COMMENT
                {
                mCOMMENT(); 


                }
                break;

        }

    }


    protected DFA27 dfa27 = new DFA27(this);
    static final String DFA27_eotS =
        "\1\uffff\5\64\1\124\21\64\1\u009c\13\uffff\1\u009e\1\u00a0\2\uffff"+
        "\1\u00a2\11\uffff\2\u00b0\3\uffff\2\64\1\u00b9\15\64\1\u00d1\3\64"+
        "\1\u00d8\2\64\1\u00db\1\u00dd\3\64\2\uffff\4\64\1\u00ed\1\u00f4"+
        "\1\64\1\u00f6\24\64\1\u011a\47\64\4\uffff\1\u0161\7\uffff\1\u0163"+
        "\2\uffff\1\u0163\1\64\1\u0166\1\u00b0\1\u0167\1\u0168\1\u00b0\1"+
        "\64\1\uffff\2\u016f\1\uffff\5\64\1\uffff\17\64\1\u0186\2\64\1\u0189"+
        "\1\64\1\u018b\1\64\1\u018d\1\uffff\3\64\1\u0191\1\64\1\u0193\1\uffff"+
        "\1\64\1\u0197\1\uffff\1\64\1\uffff\1\64\1\124\15\64\1\uffff\1\u01aa"+
        "\5\64\1\uffff\1\64\1\uffff\11\64\1\u01bd\22\64\1\u01d9\6\64\1\uffff"+
        "\20\64\1\u01f3\11\64\1\u01ff\10\64\1\u020b\1\u020d\22\64\1\u0225"+
        "\5\64\1\u022d\7\64\5\uffff\1\u0235\3\uffff\1\u00b0\3\uffff\1\u00b0"+
        "\1\u016b\1\uffff\1\u023a\13\64\1\u0246\1\64\1\u0248\1\u0249\6\64"+
        "\1\uffff\2\64\1\uffff\1\64\1\uffff\1\64\1\uffff\3\64\1\uffff\1\64"+
        "\1\uffff\1\u0259\2\64\1\uffff\4\64\1\u0260\1\u0261\2\64\1\u0264"+
        "\1\u0265\1\64\1\u0269\1\u026a\1\64\1\u026c\2\64\1\u026f\1\uffff"+
        "\22\64\1\uffff\1\u0283\13\64\1\u028f\1\u0290\3\64\1\u0294\1\u0296"+
        "\10\64\1\uffff\1\u02a1\1\u02a3\1\u02a4\5\64\1\u02aa\10\64\1\u02b3"+
        "\1\64\1\u02b5\4\64\1\u02ba\1\uffff\1\u02bb\1\u02bd\1\64\1\u02c0"+
        "\7\64\1\uffff\5\64\1\u02cd\4\64\1\u02d2\1\uffff\1\64\1\uffff\10"+
        "\64\1\u02dc\1\64\1\u02de\6\64\1\u02e7\4\64\1\u02ec\1\uffff\1\64"+
        "\1\u02ee\3\64\1\u02f2\1\64\1\uffff\3\64\1\u02f7\1\64\1\u02f9\1\u02fa"+
        "\2\uffff\2\u00b0\1\64\1\uffff\3\64\1\u02ff\1\u0300\6\64\1\uffff"+
        "\1\u0307\2\uffff\1\64\1\u0309\2\64\1\u030c\2\64\1\u030f\1\u0310"+
        "\1\64\1\u0312\1\u0313\1\64\1\u0315\1\64\1\uffff\1\u0317\5\64\2\uffff"+
        "\1\u031d\1\u031e\2\uffff\1\u031f\1\64\1\u0321\2\uffff\1\64\1\uffff"+
        "\2\64\1\uffff\2\64\1\u0329\2\64\1\u032d\2\64\1\u0330\12\64\1\uffff"+
        "\11\64\1\u0344\1\64\2\uffff\3\64\1\uffff\1\64\1\uffff\12\64\1\uffff"+
        "\1\64\2\uffff\2\64\1\u0358\1\u0359\1\64\1\uffff\7\64\1\u0362\1\uffff"+
        "\1\u0363\1\uffff\3\64\1\u0368\2\uffff\1\64\1\uffff\2\64\1\uffff"+
        "\11\64\1\u0376\1\u0377\1\64\1\uffff\3\64\1\u037c\1\uffff\11\64\1"+
        "\uffff\1\u0387\1\uffff\1\u0388\6\64\1\u038f\1\uffff\4\64\1\uffff"+
        "\1\64\1\uffff\1\64\1\u0396\1\u0397\1\uffff\1\64\1\u0399\1\64\1\u039b"+
        "\1\uffff\1\64\2\uffff\3\64\1\u03a1\2\uffff\6\64\1\uffff\1\64\1\uffff"+
        "\1\u03a9\1\64\1\uffff\1\u03ac\1\64\2\uffff\1\64\2\uffff\1\64\1\uffff"+
        "\1\64\1\uffff\2\64\1\u03b4\1\64\1\u03b6\3\uffff\1\64\1\uffff\5\64"+
        "\1\u03bd\1\64\1\uffff\1\u03bf\2\64\1\uffff\1\u03c2\1\u03c3\1\uffff"+
        "\1\64\1\u03c5\1\u03c6\6\64\1\u03cd\3\64\1\u03d2\4\64\1\u03d7\1\uffff"+
        "\1\u03d8\1\64\1\u03da\4\64\1\u03df\10\64\1\u03e8\2\64\2\uffff\1"+
        "\64\1\u03ec\1\u03ed\1\u03ef\1\u03f0\1\64\1\u03f2\1\64\2\uffff\1"+
        "\u03f4\1\u03f5\2\64\1\uffff\1\64\1\u03f9\1\u03fa\1\64\1\u03fc\1"+
        "\u03fd\1\64\1\u03ff\1\64\1\u0402\1\u0403\2\64\2\uffff\1\64\1\u0407"+
        "\2\64\1\uffff\1\64\1\u040b\1\64\1\u040d\6\64\2\uffff\1\64\1\u0415"+
        "\1\64\1\u0417\1\u0418\1\64\1\uffff\1\u041a\1\u041b\2\64\1\u041f"+
        "\1\u0420\2\uffff\1\64\1\uffff\1\64\1\uffff\3\64\1\u0426\1\64\1\uffff"+
        "\1\u0428\6\64\1\uffff\2\64\1\uffff\1\64\1\u0432\1\u0433\3\64\1\u0437"+
        "\1\uffff\1\u0438\1\uffff\1\64\1\u043a\1\u043b\3\64\1\uffff\1\u043f"+
        "\1\uffff\2\64\2\uffff\1\64\2\uffff\1\u0443\4\64\1\u0448\1\uffff"+
        "\3\64\1\u044c\1\uffff\1\64\1\u044e\1\u044f\1\u0452\2\uffff\1\u0453"+
        "\1\uffff\1\u0454\1\64\1\u0456\1\64\1\uffff\4\64\1\u045c\3\64\1\uffff"+
        "\2\64\1\u0462\2\uffff\1\u0463\2\uffff\1\u0464\1\uffff\1\64\2\uffff"+
        "\3\64\2\uffff\1\64\2\uffff\1\64\1\uffff\1\64\1\u046c\2\uffff\3\64"+
        "\1\uffff\3\64\1\uffff\1\64\1\uffff\5\64\1\u0479\1\u047a\1\uffff"+
        "\1\u047b\2\uffff\1\64\2\uffff\3\64\2\uffff\1\u0480\1\64\1\u0482"+
        "\1\u0483\1\64\1\uffff\1\64\1\uffff\2\64\1\u0488\2\64\1\u048c\3\64"+
        "\2\uffff\3\64\2\uffff\1\u0493\2\uffff\1\u0494\2\64\1\uffff\3\64"+
        "\1\uffff\1\u049a\1\u049b\1\64\1\u049d\1\uffff\1\u049e\1\u049f\1"+
        "\64\1\uffff\1\64\2\uffff\2\64\3\uffff\1\u04a4\1\uffff\2\64\1\u04a7"+
        "\1\u04a8\1\64\1\uffff\1\64\1\u04ad\1\u04ae\1\64\1\u04b0\3\uffff"+
        "\4\64\1\u04b5\2\64\1\uffff\2\64\1\u04ba\3\64\1\u04be\5\64\3\uffff"+
        "\1\u04c4\2\64\1\u04c7\1\uffff\1\64\2\uffff\1\u04c9\1\64\1\u04cb"+
        "\1\64\1\uffff\1\u04cd\1\64\1\u04cf\1\uffff\1\64\1\u04d1\1\u04d2"+
        "\1\u04d3\2\64\2\uffff\1\u04d6\4\64\2\uffff\1\u04db\3\uffff\2\64"+
        "\1\u04de\1\64\1\uffff\1\u04e0\1\64\2\uffff\1\64\1\u04e3\1\64\1\u04e5"+
        "\2\uffff\1\64\1\uffff\4\64\1\uffff\3\64\1\u04ee\1\uffff\1\u04ef"+
        "\1\u04f0\1\64\1\uffff\1\u04f2\1\64\1\u04f4\1\u04f7\1\64\1\uffff"+
        "\2\64\1\uffff\1\64\1\uffff\1\64\1\uffff\1\u04fd\1\uffff\1\64\1\uffff"+
        "\1\u04ff\3\uffff\2\64\1\uffff\4\64\1\uffff\1\64\1\u0507\1\uffff"+
        "\1\64\1\uffff\1\u0509\1\u050a\1\uffff\1\64\1\uffff\6\64\1\u0512"+
        "\1\u0513\3\uffff\1\64\1\uffff\1\u0515\1\uffff\1\u0516\1\64\1\uffff"+
        "\4\64\1\u051c\1\uffff\1\64\1\uffff\2\64\1\u0520\1\u0521\1\u0522"+
        "\1\64\1\u0524\1\uffff\1\64\2\uffff\1\u0526\5\64\1\u052c\2\uffff"+
        "\1\64\2\uffff\1\u052e\1\u052f\3\64\1\uffff\1\64\1\u0534\1\u0535"+
        "\3\uffff\1\64\1\uffff\1\64\1\uffff\1\u0538\1\u0539\1\u053a\2\64"+
        "\1\uffff\1\u053d\2\uffff\1\u053e\1\u053f\1\u0540\1\u0541\2\uffff"+
        "\1\u0542\1\u0543\3\uffff\1\64\1\u0545\7\uffff\1\64\1\uffff\1\u0547"+
        "\1\uffff";
    static final String DFA27_eofS =
        "\u0548\uffff";
    static final String DFA27_minS =
        "\1\11\2\101\1\104\1\106\1\117\1\75\1\101\1\104\1\114\2\101\1\122"+
        "\1\105\1\101\1\110\1\103\1\116\1\101\1\117\3\101\2\105\13\uffff"+
        "\2\75\2\uffff\1\55\7\uffff\2\0\2\50\3\uffff\1\101\1\102\1\60\2\115"+
        "\1\114\1\105\1\114\1\117\1\114\1\105\1\117\1\114\1\124\1\114\1\101"+
        "\1\60\1\124\1\103\1\104\1\60\1\105\1\124\2\60\1\124\1\123\1\114"+
        "\2\uffff\1\113\1\106\1\101\1\124\2\60\1\120\1\60\1\116\1\105\1\130"+
        "\2\103\1\101\1\123\1\114\1\125\1\105\1\101\1\123\1\102\1\103\1\122"+
        "\1\124\1\117\1\125\1\120\1\101\1\60\1\117\1\107\1\103\1\106\1\126"+
        "\1\114\1\105\1\116\1\114\1\101\1\122\2\101\1\110\1\114\1\105\1\101"+
        "\1\105\1\103\1\104\1\105\2\122\1\125\1\122\1\111\1\107\1\101\1\114"+
        "\1\106\1\111\1\116\2\103\1\116\2\122\1\105\1\131\4\uffff\1\76\5"+
        "\uffff\2\0\1\42\2\0\1\42\2\60\1\50\2\60\1\50\1\53\1\uffff\2\60\1"+
        "\uffff\1\105\1\116\1\107\1\114\1\103\1\uffff\1\131\1\105\1\115\1"+
        "\124\2\120\1\116\1\123\1\115\1\114\1\103\1\123\1\114\1\105\1\101"+
        "\1\60\1\114\1\103\1\60\1\105\1\60\1\114\1\60\1\uffff\1\105\1\101"+
        "\1\110\1\60\1\105\1\60\1\uffff\1\122\1\60\1\uffff\1\114\1\uffff"+
        "\1\111\1\60\1\104\1\103\1\114\2\105\1\111\1\124\1\123\1\101\1\104"+
        "\1\111\1\107\1\105\1\uffff\1\60\1\122\2\105\1\101\1\105\1\uffff"+
        "\1\117\1\uffff\1\117\1\115\1\120\1\123\1\114\1\105\1\110\1\101\1"+
        "\102\1\60\1\105\1\103\1\114\1\115\1\123\1\101\1\123\1\116\1\103"+
        "\1\122\1\105\1\103\1\111\3\105\1\101\1\105\1\60\1\101\1\120\1\102"+
        "\1\122\1\125\1\116\1\uffff\1\114\1\110\1\111\1\101\1\113\1\117\1"+
        "\127\1\111\1\104\1\116\1\114\1\110\1\104\1\105\1\125\1\104\1\60"+
        "\1\111\1\127\1\122\1\124\1\114\1\105\1\122\1\124\1\105\1\60\1\127"+
        "\1\117\1\105\2\117\1\122\1\117\1\116\2\60\1\101\2\103\1\124\1\103"+
        "\1\123\1\107\1\116\1\110\1\125\2\101\1\125\1\104\1\105\2\117\1\124"+
        "\1\60\1\105\1\111\1\113\1\107\1\113\1\60\1\122\1\105\1\125\1\105"+
        "\1\103\1\127\1\123\2\uffff\1\0\1\uffff\1\0\1\60\3\uffff\1\50\1\53"+
        "\1\uffff\3\60\1\uffff\1\60\1\103\1\123\1\107\1\105\1\110\1\111\1"+
        "\123\1\111\1\106\1\117\1\122\1\60\1\105\2\60\2\124\1\104\1\106\1"+
        "\124\1\101\1\uffff\1\117\1\110\1\uffff\1\122\1\uffff\1\131\1\uffff"+
        "\1\122\1\131\1\111\1\uffff\1\122\1\uffff\1\60\1\122\1\125\1\uffff"+
        "\1\111\1\117\1\122\1\101\2\60\1\123\1\124\2\60\1\114\2\60\1\103"+
        "\1\60\2\122\1\60\1\uffff\1\105\1\122\1\130\2\124\3\122\1\123\1\122"+
        "\1\124\1\122\1\101\1\116\1\125\1\101\1\120\1\114\1\uffff\1\60\1"+
        "\124\1\111\1\101\1\115\2\105\1\125\2\124\1\123\1\107\2\60\1\101"+
        "\1\117\1\105\2\60\2\115\1\124\1\116\1\122\1\111\1\102\1\103\1\uffff"+
        "\3\60\1\114\1\117\1\120\1\124\1\105\1\60\1\116\1\122\1\105\1\122"+
        "\1\105\1\116\1\137\1\105\1\60\1\105\1\60\1\117\1\103\2\105\1\60"+
        "\1\uffff\2\60\1\105\1\60\1\114\1\116\1\103\1\101\1\105\1\111\1\115"+
        "\1\uffff\1\105\1\125\1\116\1\124\1\107\1\60\2\103\1\125\1\107\1"+
        "\60\1\uffff\1\124\1\uffff\1\124\1\105\1\124\3\105\1\111\1\105\1"+
        "\60\1\105\1\60\1\124\2\111\1\101\1\115\1\103\1\60\1\130\1\113\2"+
        "\122\1\60\1\uffff\1\125\1\60\1\114\2\105\1\60\1\117\1\uffff\1\117"+
        "\1\122\1\123\1\60\1\110\2\60\1\uffff\3\60\1\104\1\uffff\1\101\1"+
        "\106\1\105\2\60\1\116\1\124\1\116\1\111\1\122\1\117\1\uffff\1\60"+
        "\2\uffff\1\111\1\60\1\123\1\117\1\60\1\124\1\127\2\60\1\132\2\60"+
        "\1\126\1\60\1\122\1\uffff\1\60\1\124\2\116\1\117\1\116\2\uffff\2"+
        "\60\2\uffff\1\60\1\111\1\60\2\uffff\1\101\1\uffff\1\101\1\123\1"+
        "\uffff\1\115\1\124\1\60\1\110\1\104\1\60\1\124\1\105\1\60\1\117"+
        "\1\123\1\124\1\111\1\116\1\104\1\123\1\116\2\105\1\uffff\1\101\1"+
        "\116\1\124\1\116\1\103\1\116\1\124\2\105\1\60\1\105\2\uffff\1\104"+
        "\1\122\1\116\1\uffff\1\111\1\uffff\1\101\1\111\1\105\1\104\1\122"+
        "\1\116\1\111\1\114\1\124\1\101\1\uffff\1\111\2\uffff\1\105\1\120"+
        "\2\60\1\101\1\uffff\1\124\1\131\1\124\2\105\1\107\1\104\1\60\1\uffff"+
        "\1\60\1\uffff\1\127\1\124\1\116\1\60\2\uffff\1\104\1\uffff\2\104"+
        "\1\uffff\1\111\1\107\1\124\1\115\1\104\1\123\1\101\1\104\1\105\2"+
        "\60\1\116\1\uffff\1\113\1\110\1\116\1\60\1\uffff\1\115\1\105\1\122"+
        "\1\131\1\104\1\103\1\104\1\101\1\116\1\uffff\1\60\1\uffff\1\60\1"+
        "\114\1\122\1\103\2\105\1\116\1\60\1\uffff\1\120\1\105\1\104\1\111"+
        "\1\uffff\1\120\1\uffff\1\105\2\60\1\uffff\1\111\1\60\1\111\1\60"+
        "\1\uffff\1\101\2\uffff\1\124\1\117\1\122\1\60\2\uffff\1\124\2\101"+
        "\1\114\1\101\1\120\1\uffff\1\117\1\uffff\1\60\1\122\1\uffff\1\60"+
        "\1\111\2\uffff\1\105\2\uffff\1\105\1\uffff\1\111\1\uffff\1\104\1"+
        "\105\1\60\1\120\1\60\3\uffff\1\117\1\uffff\2\114\1\101\2\105\1\60"+
        "\1\123\1\uffff\1\60\1\117\1\122\1\uffff\2\60\1\uffff\1\120\2\60"+
        "\1\116\1\101\1\105\1\111\1\107\1\104\1\60\1\116\1\125\1\105\1\60"+
        "\2\124\1\105\1\122\1\60\1\uffff\1\60\1\105\1\60\1\124\1\102\1\114"+
        "\1\124\1\60\2\105\1\103\1\102\1\105\1\117\1\123\1\115\1\60\1\105"+
        "\1\116\2\uffff\1\116\4\60\1\116\1\60\1\104\2\uffff\2\60\1\103\1"+
        "\122\1\uffff\1\101\2\60\1\116\2\60\1\124\1\60\1\124\2\60\1\112\1"+
        "\131\2\uffff\1\105\1\60\1\111\1\104\1\uffff\1\105\1\60\1\126\1\60"+
        "\1\111\1\124\1\125\1\111\1\114\1\124\2\uffff\1\104\1\60\1\105\2"+
        "\60\1\114\1\uffff\2\60\1\122\1\103\2\60\2\uffff\1\116\1\uffff\1"+
        "\101\1\uffff\1\122\1\105\1\122\1\60\1\115\1\uffff\1\60\1\115\1\124"+
        "\1\105\1\122\1\105\1\116\1\uffff\1\115\1\105\1\uffff\1\116\2\60"+
        "\1\124\1\117\1\122\1\60\1\uffff\1\60\1\uffff\1\116\2\60\1\114\1"+
        "\103\1\116\1\uffff\1\60\1\uffff\1\122\1\111\2\uffff\1\105\2\uffff"+
        "\1\60\1\114\1\104\1\126\1\105\1\60\1\uffff\1\124\1\105\1\116\1\60"+
        "\1\uffff\1\111\3\60\2\uffff\1\60\1\uffff\1\60\1\105\1\60\1\105\1"+
        "\uffff\1\116\1\104\1\124\1\125\1\60\1\122\2\105\1\uffff\1\122\1"+
        "\107\1\60\2\uffff\1\60\2\uffff\1\60\1\uffff\1\114\2\uffff\1\105"+
        "\1\117\1\124\2\uffff\1\124\2\uffff\1\101\1\uffff\1\111\1\60\2\uffff"+
        "\1\117\1\120\1\104\1\uffff\1\126\1\105\1\123\1\uffff\1\105\1\uffff"+
        "\1\116\1\111\1\122\1\117\1\123\2\60\1\uffff\1\60\2\uffff\1\131\2"+
        "\uffff\1\105\1\122\1\124\2\uffff\1\60\1\114\2\60\1\115\1\uffff\1"+
        "\120\1\uffff\1\120\1\105\1\60\1\131\1\122\1\60\1\101\1\104\1\107"+
        "\2\uffff\1\105\1\122\1\111\2\uffff\1\60\2\uffff\1\60\2\124\1\uffff"+
        "\1\115\1\126\1\122\1\uffff\2\60\1\105\1\60\1\uffff\2\60\1\101\1"+
        "\uffff\1\117\2\uffff\1\104\1\124\3\uffff\1\60\1\uffff\1\104\1\103"+
        "\2\60\1\124\1\uffff\1\111\2\60\1\124\1\60\3\uffff\1\124\1\106\1"+
        "\120\1\101\1\60\1\102\1\103\1\uffff\1\111\1\105\1\60\1\105\1\104"+
        "\1\124\1\60\1\107\1\117\1\105\1\116\1\103\3\uffff\1\60\1\101\1\111"+
        "\1\60\1\uffff\1\111\2\uffff\1\60\1\114\1\60\1\104\1\uffff\1\60\1"+
        "\124\1\60\1\uffff\1\124\3\60\1\115\1\126\2\uffff\1\60\2\101\1\105"+
        "\1\124\2\uffff\1\60\3\uffff\1\124\1\116\1\60\1\101\1\uffff\1\60"+
        "\1\131\2\uffff\1\105\1\60\1\105\1\60\2\uffff\1\111\1\uffff\2\111"+
        "\1\105\1\102\1\uffff\1\114\1\123\1\116\1\60\1\uffff\2\60\1\101\1"+
        "\uffff\1\60\1\116\2\60\1\101\1\uffff\1\104\1\124\1\uffff\1\132\1"+
        "\uffff\1\105\1\uffff\1\60\1\uffff\1\111\1\uffff\1\60\3\uffff\1\101"+
        "\1\105\1\uffff\1\114\1\124\1\122\1\111\1\uffff\1\105\1\60\1\uffff"+
        "\1\124\1\uffff\2\60\1\uffff\1\123\1\uffff\1\105\1\115\1\114\1\122"+
        "\1\101\1\105\2\60\3\uffff\1\115\1\uffff\1\60\1\uffff\1\60\1\104"+
        "\1\uffff\1\116\3\105\1\60\1\uffff\1\105\1\uffff\1\124\1\122\3\60"+
        "\1\105\1\60\1\uffff\1\125\2\uffff\1\60\1\123\2\105\1\124\1\123\1"+
        "\60\2\uffff\1\120\2\uffff\2\60\2\122\1\104\1\uffff\1\123\2\60\3"+
        "\uffff\1\123\1\uffff\1\123\1\uffff\3\60\1\111\1\105\1\uffff\1\60"+
        "\2\uffff\4\60\2\uffff\2\60\3\uffff\1\105\1\60\7\uffff\1\123\1\uffff"+
        "\1\60\1\uffff";
    static final String DFA27_maxS =
        "\1\176\1\122\1\125\1\123\1\126\1\125\1\75\1\117\1\124\1\130\1\125"+
        "\2\122\1\131\1\117\1\111\2\124\1\125\2\117\1\123\1\111\1\105\1\126"+
        "\13\uffff\1\76\1\75\2\uffff\1\55\7\uffff\2\uffff\2\172\3\uffff\1"+
        "\125\1\102\1\172\1\116\1\130\1\114\1\105\1\114\1\117\1\116\1\122"+
        "\1\117\1\122\2\124\1\104\1\172\1\124\1\122\1\104\1\172\1\105\1\124"+
        "\2\172\1\124\1\137\1\114\2\uffff\1\116\1\123\1\116\1\124\2\172\1"+
        "\120\1\172\1\116\1\105\1\130\1\124\1\103\1\104\1\123\1\116\1\125"+
        "\1\117\1\101\1\123\1\122\1\123\1\126\1\124\1\117\1\125\1\120\1\117"+
        "\1\172\1\124\1\116\1\103\1\124\1\126\1\114\1\111\2\124\1\117\1\122"+
        "\1\101\1\122\1\110\1\114\1\105\1\123\1\111\1\103\1\104\1\117\2\122"+
        "\1\125\1\122\1\111\1\107\1\126\1\127\1\106\1\111\1\116\1\103\1\124"+
        "\1\116\2\122\1\105\1\131\4\uffff\1\76\5\uffff\2\uffff\1\47\2\uffff"+
        "\1\47\1\146\4\172\1\145\1\71\1\uffff\2\172\1\uffff\2\116\1\107\1"+
        "\114\1\103\1\uffff\1\131\1\105\1\115\1\124\2\120\1\116\1\123\1\115"+
        "\1\114\1\103\1\123\1\114\1\105\1\101\1\172\1\114\1\103\1\172\1\105"+
        "\1\172\1\114\1\172\1\uffff\1\105\1\101\1\110\1\172\1\105\1\172\1"+
        "\uffff\1\122\1\172\1\uffff\1\114\1\uffff\1\111\1\172\1\104\1\103"+
        "\1\114\2\105\1\111\1\124\1\123\1\113\1\104\1\111\1\107\1\105\1\uffff"+
        "\1\172\1\122\2\105\1\125\1\105\1\uffff\1\117\1\uffff\1\117\1\115"+
        "\1\120\1\123\1\117\1\105\1\114\1\101\1\102\1\172\1\105\1\124\1\125"+
        "\1\120\1\123\1\101\1\123\1\116\1\124\1\123\1\105\1\103\2\111\2\105"+
        "\1\124\1\105\1\172\1\105\1\120\1\102\1\122\1\125\1\116\1\uffff\1"+
        "\114\1\110\1\111\1\101\1\113\1\117\1\127\1\111\1\104\1\122\1\114"+
        "\1\110\1\104\1\105\1\125\1\104\1\172\1\111\1\127\1\122\1\124\1\114"+
        "\1\125\1\122\1\124\1\105\1\172\1\127\1\121\1\111\2\117\1\122\1\117"+
        "\1\116\2\172\1\101\3\124\1\103\1\123\1\107\1\116\1\110\1\125\1\114"+
        "\1\101\1\125\1\104\1\105\2\117\1\124\1\172\1\114\1\111\1\113\1\107"+
        "\1\113\1\172\1\122\1\105\1\125\1\105\1\103\1\127\1\123\2\uffff\1"+
        "\uffff\1\uffff\1\uffff\1\172\3\uffff\1\145\1\71\1\uffff\1\71\2\172"+
        "\1\uffff\1\172\1\103\1\123\1\107\1\105\1\110\1\111\1\123\1\111\1"+
        "\106\1\117\1\122\1\172\1\105\2\172\2\124\1\104\1\106\1\124\1\101"+
        "\1\uffff\1\117\1\110\1\uffff\1\122\1\uffff\1\131\1\uffff\1\122\1"+
        "\131\1\111\1\uffff\1\122\1\uffff\1\172\1\122\1\125\1\uffff\1\111"+
        "\1\117\1\122\1\101\2\172\1\123\1\124\2\172\1\124\2\172\1\103\1\172"+
        "\2\122\1\172\1\uffff\1\105\1\122\1\130\2\124\3\122\1\123\1\122\1"+
        "\124\1\122\1\101\1\122\1\125\1\101\1\120\1\114\1\uffff\1\172\1\124"+
        "\1\111\1\101\1\115\2\105\1\125\2\124\1\123\1\107\2\172\1\101\1\117"+
        "\1\105\2\172\2\115\1\124\1\116\2\122\1\102\1\103\1\uffff\3\172\1"+
        "\114\1\117\1\120\1\124\1\105\1\172\1\116\1\122\1\105\1\122\1\105"+
        "\1\116\1\137\1\105\1\172\1\105\1\172\1\117\1\103\2\105\1\172\1\uffff"+
        "\2\172\1\105\1\172\1\114\1\116\1\103\1\101\1\105\1\111\1\115\1\uffff"+
        "\1\105\1\125\1\116\1\124\1\107\1\172\2\103\1\125\1\107\1\172\1\uffff"+
        "\1\124\1\uffff\1\124\1\105\1\124\3\105\1\111\1\105\1\172\1\105\1"+
        "\172\1\124\2\111\1\101\1\115\1\103\1\172\1\130\1\113\2\122\1\172"+
        "\1\uffff\1\125\1\172\1\114\2\105\1\172\1\117\1\uffff\1\117\1\122"+
        "\1\123\1\172\1\110\2\172\1\uffff\1\71\2\102\1\104\1\uffff\1\101"+
        "\1\106\1\105\2\172\1\116\1\124\1\116\1\111\1\122\1\117\1\uffff\1"+
        "\172\2\uffff\1\111\1\172\1\123\1\117\1\172\1\124\1\127\2\172\1\132"+
        "\2\172\1\126\1\172\1\122\1\uffff\1\172\1\124\2\116\1\117\1\116\2"+
        "\uffff\2\172\2\uffff\1\172\1\111\1\172\2\uffff\1\101\1\uffff\1\101"+
        "\1\126\1\uffff\1\115\1\124\1\172\1\110\1\106\1\172\1\124\1\105\1"+
        "\172\1\117\1\123\1\124\1\111\1\116\1\104\1\123\1\116\2\105\1\uffff"+
        "\1\101\1\116\1\124\1\116\1\103\1\116\1\124\2\105\1\172\1\105\2\uffff"+
        "\1\104\1\122\1\116\1\uffff\1\111\1\uffff\1\101\1\111\1\105\1\104"+
        "\1\122\1\116\1\111\1\114\1\124\1\101\1\uffff\1\111\2\uffff\1\105"+
        "\1\120\2\172\1\101\1\uffff\1\124\1\131\1\124\2\105\1\107\1\104\1"+
        "\172\1\uffff\1\172\1\uffff\1\127\1\124\1\116\1\172\2\uffff\1\104"+
        "\1\uffff\2\104\1\uffff\1\111\1\107\1\124\1\115\1\104\1\123\1\101"+
        "\1\104\1\105\2\172\1\116\1\uffff\1\113\1\110\1\116\1\172\1\uffff"+
        "\1\115\1\105\1\122\1\131\1\104\1\103\1\104\1\124\1\116\1\uffff\1"+
        "\172\1\uffff\1\172\1\114\1\122\1\103\2\105\1\116\1\172\1\uffff\1"+
        "\120\1\105\1\104\1\111\1\uffff\1\120\1\uffff\1\105\2\172\1\uffff"+
        "\1\111\1\172\1\111\1\172\1\uffff\1\101\2\uffff\1\124\1\117\1\122"+
        "\1\172\2\uffff\1\124\2\101\1\114\1\101\1\120\1\uffff\1\117\1\uffff"+
        "\1\172\1\122\1\uffff\1\172\1\111\2\uffff\1\105\2\uffff\1\105\1\uffff"+
        "\1\111\1\uffff\1\106\1\105\1\172\1\120\1\172\3\uffff\1\117\1\uffff"+
        "\2\114\1\101\2\105\1\172\1\123\1\uffff\1\172\1\117\1\122\1\uffff"+
        "\2\172\1\uffff\1\120\2\172\1\116\1\101\1\105\1\111\1\107\1\104\1"+
        "\172\1\116\1\125\1\105\1\172\2\124\1\105\1\122\1\172\1\uffff\1\172"+
        "\1\105\1\172\1\124\1\102\1\114\1\124\1\172\2\105\1\103\1\102\1\105"+
        "\1\117\1\123\1\115\1\172\1\105\1\116\2\uffff\1\116\4\172\1\116\1"+
        "\172\1\104\2\uffff\2\172\1\103\1\122\1\uffff\1\101\2\172\1\116\2"+
        "\172\1\124\1\172\1\124\2\172\1\112\1\131\2\uffff\1\105\1\172\1\111"+
        "\1\104\1\uffff\1\105\1\172\1\126\1\172\1\111\1\124\1\125\1\111\1"+
        "\114\1\124\2\uffff\1\104\1\172\1\105\2\172\1\114\1\uffff\2\172\1"+
        "\127\1\103\2\172\2\uffff\1\116\1\uffff\1\101\1\uffff\1\122\1\105"+
        "\1\122\1\172\1\115\1\uffff\1\172\1\115\1\124\1\105\1\122\1\105\1"+
        "\116\1\uffff\1\115\1\105\1\uffff\1\116\2\172\1\124\1\117\1\122\1"+
        "\172\1\uffff\1\172\1\uffff\1\116\2\172\1\114\1\103\1\116\1\uffff"+
        "\1\172\1\uffff\1\122\1\111\2\uffff\1\105\2\uffff\1\172\1\114\1\104"+
        "\1\126\1\105\1\172\1\uffff\1\124\1\105\1\116\1\172\1\uffff\1\111"+
        "\3\172\2\uffff\1\172\1\uffff\1\172\1\105\1\172\1\105\1\uffff\1\116"+
        "\1\104\1\124\1\125\1\172\1\122\2\105\1\uffff\1\122\1\107\1\172\2"+
        "\uffff\1\172\2\uffff\1\172\1\uffff\1\114\2\uffff\1\105\1\117\1\124"+
        "\2\uffff\1\124\2\uffff\1\101\1\uffff\1\111\1\172\2\uffff\1\117\1"+
        "\120\1\104\1\uffff\1\126\1\105\1\123\1\uffff\1\105\1\uffff\1\116"+
        "\1\111\1\122\1\117\1\123\2\172\1\uffff\1\172\2\uffff\1\131\2\uffff"+
        "\1\105\1\122\1\124\2\uffff\1\172\1\114\2\172\1\115\1\uffff\1\120"+
        "\1\uffff\1\120\1\105\1\172\1\131\1\122\1\172\1\101\1\104\1\107\2"+
        "\uffff\1\105\1\122\1\111\2\uffff\1\172\2\uffff\1\172\2\124\1\uffff"+
        "\1\115\1\126\1\122\1\uffff\2\172\1\105\1\172\1\uffff\2\172\1\101"+
        "\1\uffff\1\117\2\uffff\1\104\1\124\3\uffff\1\172\1\uffff\1\104\1"+
        "\103\2\172\1\124\1\uffff\1\131\2\172\1\124\1\172\3\uffff\1\124\1"+
        "\106\1\120\1\101\1\172\1\102\1\103\1\uffff\1\111\1\105\1\172\1\105"+
        "\1\104\1\124\1\172\1\107\1\117\1\105\1\116\1\103\3\uffff\1\172\1"+
        "\101\1\111\1\172\1\uffff\1\111\2\uffff\1\172\1\114\1\172\1\104\1"+
        "\uffff\1\172\1\124\1\172\1\uffff\1\124\3\172\1\115\1\126\2\uffff"+
        "\1\172\2\101\1\105\1\124\2\uffff\1\172\3\uffff\1\124\1\116\1\172"+
        "\1\101\1\uffff\1\172\1\131\2\uffff\1\105\1\172\1\105\1\172\2\uffff"+
        "\1\111\1\uffff\2\111\1\105\1\102\1\uffff\1\114\1\123\1\116\1\172"+
        "\1\uffff\2\172\1\101\1\uffff\1\172\1\116\2\172\1\101\1\uffff\1\104"+
        "\1\124\1\uffff\1\132\1\uffff\1\105\1\uffff\1\172\1\uffff\1\111\1"+
        "\uffff\1\172\3\uffff\1\101\1\105\1\uffff\1\114\1\124\1\122\1\111"+
        "\1\uffff\1\105\1\172\1\uffff\1\124\1\uffff\2\172\1\uffff\1\123\1"+
        "\uffff\1\105\1\115\1\114\1\122\1\101\1\105\2\172\3\uffff\1\115\1"+
        "\uffff\1\172\1\uffff\1\172\1\104\1\uffff\1\116\3\105\1\172\1\uffff"+
        "\1\105\1\uffff\1\124\1\122\3\172\1\105\1\172\1\uffff\1\125\2\uffff"+
        "\1\172\1\123\2\105\1\124\1\123\1\172\2\uffff\1\120\2\uffff\2\172"+
        "\2\122\1\104\1\uffff\1\123\2\172\3\uffff\1\123\1\uffff\1\123\1\uffff"+
        "\3\172\1\111\1\105\1\uffff\1\172\2\uffff\4\172\2\uffff\2\172\3\uffff"+
        "\1\105\1\172\7\uffff\1\123\1\uffff\1\172\1\uffff";
    static final String DFA27_acceptS =
        "\31\uffff\1\u00f4\1\u00f5\1\u00f6\1\u00f7\1\u00f8\1\u00f9\1\u00fa"+
        "\1\u00fb\1\u00fc\1\u00fd\1\u00fe\2\uffff\1\u0105\1\u0106\1\uffff"+
        "\1\u0108\1\u0109\1\u010b\1\u010c\1\u010d\1\u010e\1\u010f\4\uffff"+
        "\1\u011a\1\u011b\1\u011c\34\uffff\1\u0100\1\6\104\uffff\1\154\1"+
        "\u009b\1\u009c\1\u0110\1\uffff\1\u0102\1\u0103\1\u0104\1\u011d\1"+
        "\u0107\15\uffff\1\u0118\2\uffff\1\u0119\5\uffff\1\111\27\uffff\1"+
        "\25\6\uffff\1\5\2\uffff\1\41\1\uffff\1\u0081\17\uffff\1\10\6\uffff"+
        "\1\u00af\1\uffff\1\73\43\uffff\1\21\105\uffff\1\u00ff\1\u0101\1"+
        "\uffff\1\u0111\2\uffff\1\u0113\1\u0114\1\u0115\2\uffff\1\u0116\3"+
        "\uffff\1\u0117\26\uffff\1\u00e2\2\uffff\1\3\1\uffff\1\4\1\uffff"+
        "\1\15\3\uffff\1\u0084\1\uffff\1\163\3\uffff\1\u0080\22\uffff\1\116"+
        "\22\uffff\1\u00a1\33\uffff\1\u010a\31\uffff\1\u0097\13\uffff\1\u00b7"+
        "\13\uffff\1\u00d7\1\uffff\1\u00a6\27\uffff\1\142\7\uffff\1\132\7"+
        "\uffff\1\u0112\4\uffff\1\1\13\uffff\1\u009f\1\uffff\1\24\1\40\17"+
        "\uffff\1\u00ea\6\uffff\1\74\1\7\2\uffff\1\36\1\u00e8\3\uffff\1\u00b9"+
        "\1\66\1\uffff\1\u00a8\2\uffff\1\140\23\uffff\1\u00a0\13\uffff\1"+
        "\u0083\1\u009d\3\uffff\1\u00e0\1\uffff\1\16\12\uffff\1\71\1\uffff"+
        "\1\122\1\105\5\uffff\1\u00c8\10\uffff\1\u009e\1\uffff\1\u0092\4"+
        "\uffff\1\u00ec\1\u00d0\1\uffff\1\53\2\uffff\1\64\14\uffff\1\u00b8"+
        "\4\uffff\1\u00f0\11\uffff\1\u00aa\1\uffff\1\35\10\uffff\1\u00c1"+
        "\4\uffff\1\143\1\uffff\1\u00f1\3\uffff\1\54\4\uffff\1\u00e9\1\uffff"+
        "\1\u00ae\1\153\4\uffff\1\44\1\u00d2\6\uffff\1\2\1\uffff\1\102\2"+
        "\uffff\1\120\2\uffff\1\u00ac\1\77\1\uffff\1\103\1\130\1\uffff\1"+
        "\17\1\uffff\1\32\5\uffff\1\155\1\u0096\1\57\1\uffff\1\u00ba\7\uffff"+
        "\1\47\3\uffff\1\u00f2\2\uffff\1\152\23\uffff\1\u00ca\23\uffff\1"+
        "\20\1\u00b5\10\uffff\1\23\1\u00c0\4\uffff\1\u0091\15\uffff\1\65"+
        "\1\u0098\4\uffff\1\61\12\uffff\1\u00c3\1\37\6\uffff\1\u00c2\6\uffff"+
        "\1\u0086\1\u00c4\1\uffff\1\u008a\1\uffff\1\u00ab\5\uffff\1\45\7"+
        "\uffff\1\146\2\uffff\1\144\7\uffff\1\u00d8\1\uffff\1\u00ee\6\uffff"+
        "\1\30\1\uffff\1\72\2\uffff\1\70\1\107\1\uffff\1\11\1\67\6\uffff"+
        "\1\171\4\uffff\1\101\4\uffff\1\75\1\100\1\uffff\1\u00cc\4\uffff"+
        "\1\u00a9\10\uffff\1\121\3\uffff\1\117\1\u00c9\1\uffff\1\177\1\u00c6"+
        "\1\uffff\1\22\1\uffff\1\u00e3\1\26\3\uffff\1\u00bc\1\137\1\uffff"+
        "\1\126\1\131\1\uffff\1\156\2\uffff\1\u00b3\1\u00de\3\uffff\1\u00bb"+
        "\3\uffff\1\u00db\1\uffff\1\u008e\7\uffff\1\55\1\uffff\1\106\1\134"+
        "\1\uffff\1\u0087\1\u00b6\3\uffff\1\u00df\1\162\5\uffff\1\u00cd\1"+
        "\uffff\1\114\11\uffff\1\u00c5\1\u00d3\3\uffff\1\170\1\174\1\uffff"+
        "\1\u0090\1\u00d1\3\uffff\1\50\3\uffff\1\u008b\4\uffff\1\150\3\uffff"+
        "\1\46\1\uffff\1\112\1\u00d5\2\uffff\1\62\1\u00dd\1\u00e7\1\uffff"+
        "\1\125\5\uffff\1\172\5\uffff\1\113\1\141\1\u00c7\7\uffff\1\u00b4"+
        "\14\uffff\1\u0082\1\51\1\u0085\4\uffff\1\u00a2\1\uffff\1\127\1\u00ed"+
        "\4\uffff\1\161\3\uffff\1\u0089\6\uffff\1\175\1\13\5\uffff\1\76\1"+
        "\u008c\1\uffff\1\u00f3\1\12\1\u00cb\4\uffff\1\104\2\uffff\1\u0093"+
        "\1\27\4\uffff\1\u00b0\1\123\1\uffff\1\u00eb\4\uffff\1\115\4\uffff"+
        "\1\u00bf\3\uffff\1\34\5\uffff\1\173\2\uffff\1\u00dc\1\uffff\1\60"+
        "\1\uffff\1\124\1\uffff\1\u0088\1\uffff\1\52\1\uffff\1\u008d\1\u00e6"+
        "\1\31\2\uffff\1\u00ad\4\uffff\1\u00bd\2\uffff\1\136\1\uffff\1\145"+
        "\2\uffff\1\56\1\uffff\1\u00b1\10\uffff\1\133\1\u00d4\1\u00e4\1\uffff"+
        "\1\u00e5\1\uffff\1\u00be\2\uffff\1\42\5\uffff\1\147\1\uffff\1\157"+
        "\7\uffff\1\151\1\uffff\1\u008f\1\63\7\uffff\1\u00d6\1\33\1\uffff"+
        "\1\110\1\43\5\uffff\1\176\3\uffff\1\14\1\164\1\166\1\uffff\1\u00d9"+
        "\1\uffff\1\u00e1\5\uffff\1\u00a3\1\uffff\1\135\1\u00ef\4\uffff\1"+
        "\165\1\167\2\uffff\1\u0095\1\u00a4\1\160\2\uffff\1\u00a7\1\u00ce"+
        "\1\u00cf\1\u00b2\1\u0099\1\u009a\1\u00a5\1\uffff\1\u00da\1\uffff"+
        "\1\u0094";
    static final String DFA27_specialS =
        "\60\uffff\1\2\1\3\161\uffff\1\4\1\6\1\uffff\1\1\1\7\u00ba\uffff"+
        "\1\5\1\uffff\1\0\u03e3\uffff}>";
    static final String[] DFA27_transitionS = {
            "\2\66\2\uffff\1\66\22\uffff\1\66\1\6\1\61\1\uffff\1\30\1\52"+
            "\1\53\1\60\1\35\1\36\1\51\1\47\1\33\1\50\1\31\1\46\1\62\11\63"+
            "\1\32\1\34\1\44\1\43\1\45\1\57\1\uffff\1\3\1\15\1\12\1\13\1"+
            "\11\1\2\1\14\1\16\1\10\1\23\1\27\1\7\1\25\1\5\1\4\1\22\1\64"+
            "\1\24\1\20\1\1\1\21\1\26\1\17\3\64\1\37\1\uffff\1\40\1\56\1"+
            "\65\33\64\1\41\1\55\1\42\1\54",
            "\1\70\1\74\2\uffff\1\73\2\uffff\1\75\1\72\5\uffff\1\71\2\uffff"+
            "\1\67",
            "\1\76\3\uffff\1\104\3\uffff\1\101\2\uffff\1\102\2\uffff\1\103"+
            "\2\uffff\1\77\2\uffff\1\100",
            "\1\112\1\uffff\1\110\5\uffff\1\105\1\uffff\1\106\3\uffff\1"+
            "\111\1\107",
            "\1\117\7\uffff\1\116\1\uffff\1\120\1\uffff\1\113\2\uffff\1"+
            "\115\1\114",
            "\1\121\5\uffff\1\122",
            "\1\123",
            "\1\130\3\uffff\1\126\3\uffff\1\125\5\uffff\1\127",
            "\1\137\1\uffff\1\131\1\135\5\uffff\1\133\1\132\4\uffff\1\134"+
            "\1\136",
            "\1\143\1\uffff\1\142\4\uffff\1\141\4\uffff\1\140",
            "\1\150\6\uffff\1\147\3\uffff\1\145\2\uffff\1\144\2\uffff\1"+
            "\146\2\uffff\1\151",
            "\1\154\1\157\2\uffff\1\152\3\uffff\1\153\5\uffff\1\156\2\uffff"+
            "\1\155",
            "\1\160",
            "\1\165\3\uffff\1\163\5\uffff\1\162\5\uffff\1\164\3\uffff\1"+
            "\161",
            "\1\166\15\uffff\1\167",
            "\1\170\1\171",
            "\1\177\1\uffff\1\172\2\uffff\1\173\2\uffff\1\u0081\1\uffff"+
            "\1\175\1\uffff\1\174\3\uffff\1\u0080\1\176",
            "\1\u0082\1\uffff\1\u0085\2\uffff\1\u0083\1\u0084",
            "\1\u0087\3\uffff\1\u0088\6\uffff\1\u0089\5\uffff\1\u0086\2"+
            "\uffff\1\u008a",
            "\1\u008b",
            "\1\u0091\1\uffff\1\u008f\1\uffff\1\u008d\3\uffff\1\u008c\2"+
            "\uffff\1\u0090\2\uffff\1\u008e",
            "\1\u0093\7\uffff\1\u0094\5\uffff\1\u0095\3\uffff\1\u0092",
            "\1\u0096\7\uffff\1\u0097",
            "\1\u0098",
            "\1\u009b\5\uffff\1\u0099\12\uffff\1\u009a",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u009d\1\123",
            "\1\u009f",
            "",
            "",
            "\1\u00a1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\47\u00a3\1\u00a5\64\u00a3\1\u00a4\uffa3\u00a3",
            "\42\u00a6\1\u00a8\71\u00a6\1\u00a7\uffa3\u00a6",
            "\1\u00b3\5\uffff\1\u00ae\1\uffff\12\u00ab\7\uffff\1\64\1\u00b1"+
            "\2\64\1\u00af\1\64\1\u00b2\3\64\1\u00b2\1\u00aa\1\u00b2\5\64"+
            "\1\u00ac\4\64\1\u00a9\1\u00ad\1\64\4\uffff\1\64\1\uffff\1\64"+
            "\1\u00b2\2\64\1\u00af\1\64\1\u00b2\3\64\1\u00b2\1\64\1\u00b2"+
            "\15\64",
            "\1\u00b3\5\uffff\1\u00ae\1\uffff\12\u00ab\7\uffff\1\64\1\u00b1"+
            "\2\64\1\u00af\1\64\1\u00b2\3\64\1\u00b2\1\u00aa\1\u00b2\5\64"+
            "\1\u00ac\5\64\1\u00ad\1\64\4\uffff\1\64\1\uffff\1\64\1\u00b2"+
            "\2\64\1\u00af\1\64\1\u00b2\3\64\1\u00b2\1\64\1\u00b2\15\64",
            "",
            "",
            "",
            "\1\u00b5\7\uffff\1\u00b6\13\uffff\1\u00b4",
            "\1\u00b7",
            "\12\64\7\uffff\24\64\1\u00b8\5\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u00bb\1\u00ba",
            "\1\u00be\4\uffff\1\u00bc\5\uffff\1\u00bd",
            "\1\u00bf",
            "\1\u00c0",
            "\1\u00c1",
            "\1\u00c2",
            "\1\u00c3\1\uffff\1\u00c4",
            "\1\u00c6\6\uffff\1\u00c7\5\uffff\1\u00c5",
            "\1\u00c8",
            "\1\u00ca\5\uffff\1\u00c9",
            "\1\u00cb",
            "\1\u00cc\7\uffff\1\u00cd",
            "\1\u00cf\2\uffff\1\u00ce",
            "\12\64\7\uffff\2\64\1\u00d0\27\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u00d2",
            "\1\u00d4\16\uffff\1\u00d3",
            "\1\u00d5",
            "\12\64\7\uffff\2\64\1\u00d7\1\u00d6\26\64\4\uffff\1\64\1\uffff"+
            "\32\64",
            "\1\u00d9",
            "\1\u00da",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\5\64\1\u00dc\24\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u00de",
            "\1\u00e1\1\u00df\12\uffff\1\u00e0",
            "\1\u00e2",
            "",
            "",
            "\1\u00e3\1\uffff\1\u00e5\1\u00e4",
            "\1\u00e6\14\uffff\1\u00e7",
            "\1\u00e9\1\uffff\1\u00e8\3\uffff\1\u00ea\6\uffff\1\u00eb",
            "\1\u00ec",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\2\64\1\u00ef\1\u00f1\11\64\1\u00f3\1\64\1\u00f2"+
            "\2\64\1\u00f0\1\u00ee\6\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u00f5",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u00f7",
            "\1\u00f8",
            "\1\u00f9",
            "\1\u00fd\5\uffff\1\u00fa\6\uffff\1\u00fb\3\uffff\1\u00fc",
            "\1\u00fe",
            "\1\u00ff\2\uffff\1\u0100",
            "\1\u0101",
            "\1\u0103\1\u0104\1\u0102",
            "\1\u0105",
            "\1\u0106\11\uffff\1\u0107",
            "\1\u0108",
            "\1\u0109",
            "\1\u010b\17\uffff\1\u010a",
            "\1\u010d\2\uffff\1\u0110\5\uffff\1\u010e\3\uffff\1\u010f\2"+
            "\uffff\1\u010c",
            "\1\u0112\1\u0111\2\uffff\1\u0113",
            "\1\u0114",
            "\1\u0115",
            "\1\u0116",
            "\1\u0117",
            "\1\u0119\15\uffff\1\u0118",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u011b\4\uffff\1\u011c",
            "\1\u011d\6\uffff\1\u011e",
            "\1\u011f",
            "\1\u0120\15\uffff\1\u0121",
            "\1\u0122",
            "\1\u0123",
            "\1\u0124\3\uffff\1\u0125",
            "\1\u0127\5\uffff\1\u0126",
            "\1\u0128\1\u012c\3\uffff\1\u0129\1\u012a\1\uffff\1\u012b",
            "\1\u012e\15\uffff\1\u012d",
            "\1\u012f",
            "\1\u0130",
            "\1\u0133\15\uffff\1\u0132\2\uffff\1\u0131",
            "\1\u0134",
            "\1\u0135",
            "\1\u0136",
            "\1\u013b\1\u013c\1\uffff\1\u0139\4\uffff\1\u0137\2\uffff\1"+
            "\u013a\6\uffff\1\u0138",
            "\1\u013e\3\uffff\1\u013d",
            "\1\u013f",
            "\1\u0140",
            "\1\u0141\11\uffff\1\u0142",
            "\1\u0143",
            "\1\u0144",
            "\1\u0145",
            "\1\u0146",
            "\1\u0147",
            "\1\u0148",
            "\1\u014d\1\u0149\1\u0150\1\u014c\2\uffff\1\u014e\6\uffff\1"+
            "\u014b\1\uffff\1\u014a\2\uffff\1\u0151\2\uffff\1\u014f",
            "\1\u0153\12\uffff\1\u0152",
            "\1\u0154",
            "\1\u0155",
            "\1\u0156",
            "\1\u0157",
            "\1\u0159\14\uffff\1\u0158\3\uffff\1\u015a",
            "\1\u015b",
            "\1\u015c",
            "\1\u015d",
            "\1\u015e",
            "\1\u015f",
            "",
            "",
            "",
            "",
            "\1\u0160",
            "",
            "",
            "",
            "",
            "",
            "\47\u00a3\1\u00a5\64\u00a3\1\u00a4\uffa3\u00a3",
            "\0\u0162",
            "\1\61\4\uffff\1\60",
            "\42\u00a6\1\u00a8\71\u00a6\1\u00a7\uffa3\u00a6",
            "\0\u0164",
            "\1\61\4\uffff\1\60",
            "\12\u0165\7\uffff\6\u0165\32\uffff\6\u0165",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u00b3\5\uffff\1\u00ae\1\uffff\12\u00ab\7\uffff\1\64\1\u00b1"+
            "\2\64\1\u00af\1\64\1\u00b2\3\64\1\u00b2\1\u00aa\1\u00b2\5\64"+
            "\1\u00ac\5\64\1\u00ad\1\64\4\uffff\1\64\1\uffff\1\64\1\u00b2"+
            "\2\64\1\u00af\1\64\1\u00b2\3\64\1\u00b2\1\64\1\u00b2\15\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u00b3\7\uffff\12\u0169\10\uffff\1\u016b\2\uffff\1\u016a"+
            "\37\uffff\1\u016a",
            "\1\u016c\1\uffff\1\u016c\2\uffff\12\u016d",
            "",
            "\12\64\7\uffff\3\64\1\u016e\26\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u0170\10\uffff\1\u0171",
            "\1\u0172",
            "\1\u0173",
            "\1\u0174",
            "\1\u0175",
            "",
            "\1\u0176",
            "\1\u0177",
            "\1\u0178",
            "\1\u0179",
            "\1\u017a",
            "\1\u017b",
            "\1\u017c",
            "\1\u017d",
            "\1\u017e",
            "\1\u017f",
            "\1\u0180",
            "\1\u0181",
            "\1\u0182",
            "\1\u0183",
            "\1\u0184",
            "\12\64\7\uffff\14\64\1\u0185\15\64\4\uffff\1\64\1\uffff\32"+
            "\64",
            "\1\u0187",
            "\1\u0188",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u018a",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u018c",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u018e",
            "\1\u018f",
            "\1\u0190",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0192",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u0194",
            "\12\64\7\uffff\4\64\1\u0195\12\64\1\u0196\12\64\4\uffff\1\64"+
            "\1\uffff\32\64",
            "",
            "\1\u0198",
            "",
            "\1\u0199",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u019a",
            "\1\u019b",
            "\1\u019c",
            "\1\u019d",
            "\1\u019e",
            "\1\u019f",
            "\1\u01a0",
            "\1\u01a1",
            "\1\u01a2\11\uffff\1\u01a3",
            "\1\u01a4",
            "\1\u01a5",
            "\1\u01a6",
            "\1\u01a7",
            "",
            "\12\64\7\uffff\4\64\1\u01a8\11\64\1\u01a9\13\64\4\uffff\1\64"+
            "\1\uffff\32\64",
            "\1\u01ab",
            "\1\u01ac",
            "\1\u01ad",
            "\1\u01ae\23\uffff\1\u01af",
            "\1\u01b0",
            "",
            "\1\u01b1",
            "",
            "\1\u01b2",
            "\1\u01b3",
            "\1\u01b4",
            "\1\u01b5",
            "\1\u01b7\2\uffff\1\u01b6",
            "\1\u01b8",
            "\1\u01ba\3\uffff\1\u01b9",
            "\1\u01bb",
            "\1\u01bc",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u01be",
            "\1\u01c1\17\uffff\1\u01bf\1\u01c0",
            "\1\u01c3\10\uffff\1\u01c2",
            "\1\u01c4\2\uffff\1\u01c5",
            "\1\u01c6",
            "\1\u01c7",
            "\1\u01c8",
            "\1\u01c9",
            "\1\u01cc\1\uffff\1\u01cb\16\uffff\1\u01ca",
            "\1\u01ce\1\u01cd",
            "\1\u01cf",
            "\1\u01d0",
            "\1\u01d1",
            "\1\u01d3\3\uffff\1\u01d2",
            "\1\u01d4",
            "\1\u01d5",
            "\1\u01d7\22\uffff\1\u01d6",
            "\1\u01d8",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u01da\3\uffff\1\u01db",
            "\1\u01dc",
            "\1\u01dd",
            "\1\u01de",
            "\1\u01df",
            "\1\u01e0",
            "",
            "\1\u01e1",
            "\1\u01e2",
            "\1\u01e3",
            "\1\u01e4",
            "\1\u01e5",
            "\1\u01e6",
            "\1\u01e7",
            "\1\u01e8",
            "\1\u01e9",
            "\1\u01eb\3\uffff\1\u01ea",
            "\1\u01ec",
            "\1\u01ed",
            "\1\u01ee",
            "\1\u01ef",
            "\1\u01f0",
            "\1\u01f1",
            "\12\64\7\uffff\22\64\1\u01f2\7\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u01f4",
            "\1\u01f5",
            "\1\u01f6",
            "\1\u01f7",
            "\1\u01f8",
            "\1\u01fb\3\uffff\1\u01f9\13\uffff\1\u01fa",
            "\1\u01fc",
            "\1\u01fd",
            "\1\u01fe",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0200",
            "\1\u0202\1\uffff\1\u0201",
            "\1\u0203\3\uffff\1\u0204",
            "\1\u0205",
            "\1\u0206",
            "\1\u0207",
            "\1\u0208",
            "\1\u0209",
            "\12\64\7\uffff\21\64\1\u020a\10\64\4\uffff\1\64\1\uffff\32"+
            "\64",
            "\12\64\7\uffff\32\64\4\uffff\1\u020c\1\uffff\32\64",
            "\1\u020e",
            "\1\u0211\17\uffff\1\u020f\1\u0210",
            "\1\u0213\20\uffff\1\u0212",
            "\1\u0214",
            "\1\u0215",
            "\1\u0216",
            "\1\u0217",
            "\1\u0218",
            "\1\u0219",
            "\1\u021a",
            "\1\u021b\12\uffff\1\u021c",
            "\1\u021d",
            "\1\u021e",
            "\1\u021f",
            "\1\u0220",
            "\1\u0221",
            "\1\u0222",
            "\1\u0223",
            "\12\64\7\uffff\22\64\1\u0224\7\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0227\6\uffff\1\u0226",
            "\1\u0228",
            "\1\u0229",
            "\1\u022a",
            "\1\u022b",
            "\12\64\7\uffff\11\64\1\u022c\20\64\4\uffff\1\64\1\uffff\32"+
            "\64",
            "\1\u022e",
            "\1\u022f",
            "\1\u0230",
            "\1\u0231",
            "\1\u0232",
            "\1\u0233",
            "\1\u0234",
            "",
            "",
            "\47\u00a3\1\u00a5\64\u00a3\1\u00a4\uffa3\u00a3",
            "",
            "\42\u00a6\1\u00a8\71\u00a6\1\u00a7\uffa3\u00a6",
            "\12\u0165\7\uffff\6\u0165\24\64\4\uffff\1\64\1\uffff\6\u0165"+
            "\24\64",
            "",
            "",
            "",
            "\1\u00b3\7\uffff\12\u0169\10\uffff\1\u016b\2\uffff\1\u016a"+
            "\37\uffff\1\u016a",
            "\1\u0236\1\uffff\1\u0236\2\uffff\12\u0237",
            "",
            "\12\u0238",
            "\12\u016d\7\uffff\1\64\1\u0239\30\64\4\uffff\1\64\1\uffff\32"+
            "\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u023b",
            "\1\u023c",
            "\1\u023d",
            "\1\u023e",
            "\1\u023f",
            "\1\u0240",
            "\1\u0241",
            "\1\u0242",
            "\1\u0243",
            "\1\u0244",
            "\1\u0245",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0247",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u024a",
            "\1\u024b",
            "\1\u024c",
            "\1\u024d",
            "\1\u024e",
            "\1\u024f",
            "",
            "\1\u0250",
            "\1\u0251",
            "",
            "\1\u0252",
            "",
            "\1\u0253",
            "",
            "\1\u0254",
            "\1\u0255",
            "\1\u0256",
            "",
            "\1\u0257",
            "",
            "\12\64\7\uffff\26\64\1\u0258\3\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u025a",
            "\1\u025b",
            "",
            "\1\u025c",
            "\1\u025d",
            "\1\u025e",
            "\1\u025f",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0262",
            "\1\u0263",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0266\7\uffff\1\u0267",
            "\12\64\7\uffff\22\64\1\u0268\7\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u026b",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u026d",
            "\1\u026e",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u0270",
            "\1\u0271",
            "\1\u0272",
            "\1\u0273",
            "\1\u0274",
            "\1\u0275",
            "\1\u0276",
            "\1\u0277",
            "\1\u0278",
            "\1\u0279",
            "\1\u027a",
            "\1\u027b",
            "\1\u027c",
            "\1\u027e\3\uffff\1\u027d",
            "\1\u027f",
            "\1\u0280",
            "\1\u0281",
            "\1\u0282",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0284",
            "\1\u0285",
            "\1\u0286",
            "\1\u0287",
            "\1\u0288",
            "\1\u0289",
            "\1\u028a",
            "\1\u028b",
            "\1\u028c",
            "\1\u028d",
            "\1\u028e",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0291",
            "\1\u0292",
            "\1\u0293",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\21\64\1\u0295\10\64\4\uffff\1\64\1\uffff\32"+
            "\64",
            "\1\u0297",
            "\1\u0298",
            "\1\u0299",
            "\1\u029a",
            "\1\u029b",
            "\1\u029c\10\uffff\1\u029d",
            "\1\u029e",
            "\1\u029f",
            "",
            "\12\64\7\uffff\1\64\1\u02a0\30\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\23\64\1\u02a2\6\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u02a5",
            "\1\u02a6",
            "\1\u02a7",
            "\1\u02a8",
            "\1\u02a9",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u02ab",
            "\1\u02ac",
            "\1\u02ad",
            "\1\u02ae",
            "\1\u02af",
            "\1\u02b0",
            "\1\u02b1",
            "\1\u02b2",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u02b4",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u02b6",
            "\1\u02b7",
            "\1\u02b8",
            "\1\u02b9",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\u02bc\1\uffff\32\64",
            "\1\u02be",
            "\12\64\7\uffff\4\64\1\u02bf\25\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u02c1",
            "\1\u02c2",
            "\1\u02c3",
            "\1\u02c4",
            "\1\u02c5",
            "\1\u02c6",
            "\1\u02c7",
            "",
            "\1\u02c8",
            "\1\u02c9",
            "\1\u02ca",
            "\1\u02cb",
            "\1\u02cc",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u02ce",
            "\1\u02cf",
            "\1\u02d0",
            "\1\u02d1",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u02d3",
            "",
            "\1\u02d4",
            "\1\u02d5",
            "\1\u02d6",
            "\1\u02d7",
            "\1\u02d8",
            "\1\u02d9",
            "\1\u02da",
            "\1\u02db",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u02dd",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u02df",
            "\1\u02e0",
            "\1\u02e1",
            "\1\u02e2",
            "\1\u02e3",
            "\1\u02e4",
            "\12\64\7\uffff\16\64\1\u02e5\3\64\1\u02e6\7\64\4\uffff\1\64"+
            "\1\uffff\32\64",
            "\1\u02e8",
            "\1\u02e9",
            "\1\u02ea",
            "\1\u02eb",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u02ed",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u02ef",
            "\1\u02f0",
            "\1\u02f1",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u02f3",
            "",
            "\1\u02f4",
            "\1\u02f5",
            "\1\u02f6",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u02f8",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\12\u0237",
            "\12\u0237\10\uffff\1\u016b",
            "\12\u0238\10\uffff\1\u016b",
            "\1\u016e",
            "",
            "\1\u02fb",
            "\1\u02fc",
            "\1\u02fd",
            "\12\64\7\uffff\22\64\1\u02fe\7\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0301",
            "\1\u0302",
            "\1\u0303",
            "\1\u0304",
            "\1\u0305",
            "\1\u0306",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "",
            "\1\u0308",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u030a",
            "\1\u030b",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u030d",
            "\1\u030e",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0311",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0314",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0316",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0318",
            "\1\u0319",
            "\1\u031a",
            "\1\u031b",
            "\1\u031c",
            "",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0320",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "",
            "\1\u0322",
            "",
            "\1\u0323",
            "\1\u0325\2\uffff\1\u0324",
            "",
            "\1\u0326",
            "\1\u0327",
            "\12\64\7\uffff\4\64\1\u0328\25\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u032a",
            "\1\u032c\1\uffff\1\u032b",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u032e",
            "\1\u032f",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0331",
            "\1\u0332",
            "\1\u0333",
            "\1\u0334",
            "\1\u0335",
            "\1\u0336",
            "\1\u0337",
            "\1\u0338",
            "\1\u0339",
            "\1\u033a",
            "",
            "\1\u033b",
            "\1\u033c",
            "\1\u033d",
            "\1\u033e",
            "\1\u033f",
            "\1\u0340",
            "\1\u0341",
            "\1\u0342",
            "\1\u0343",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0345",
            "",
            "",
            "\1\u0346",
            "\1\u0347",
            "\1\u0348",
            "",
            "\1\u0349",
            "",
            "\1\u034a",
            "\1\u034b",
            "\1\u034c",
            "\1\u034d",
            "\1\u034e",
            "\1\u034f",
            "\1\u0350",
            "\1\u0351",
            "\1\u0352",
            "\1\u0353",
            "",
            "\1\u0354",
            "",
            "",
            "\1\u0355",
            "\1\u0356",
            "\12\64\7\uffff\10\64\1\u0357\21\64\4\uffff\1\64\1\uffff\32"+
            "\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u035a",
            "",
            "\1\u035b",
            "\1\u035c",
            "\1\u035d",
            "\1\u035e",
            "\1\u035f",
            "\1\u0360",
            "\1\u0361",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u0364",
            "\1\u0365",
            "\1\u0366",
            "\12\64\7\uffff\17\64\1\u0367\12\64\4\uffff\1\64\1\uffff\32"+
            "\64",
            "",
            "",
            "\1\u0369",
            "",
            "\1\u036a",
            "\1\u036b",
            "",
            "\1\u036c",
            "\1\u036d",
            "\1\u036e",
            "\1\u036f",
            "\1\u0370",
            "\1\u0371",
            "\1\u0372",
            "\1\u0373",
            "\1\u0374",
            "\12\64\7\uffff\23\64\1\u0375\6\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0378",
            "",
            "\1\u0379",
            "\1\u037a",
            "\1\u037b",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u037d",
            "\1\u037e",
            "\1\u037f",
            "\1\u0380",
            "\1\u0381",
            "\1\u0382",
            "\1\u0383",
            "\1\u0385\22\uffff\1\u0384",
            "\1\u0386",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0389",
            "\1\u038a",
            "\1\u038b",
            "\1\u038c",
            "\1\u038d",
            "\1\u038e",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u0390",
            "\1\u0391",
            "\1\u0392",
            "\1\u0393",
            "",
            "\1\u0394",
            "",
            "\1\u0395",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u0398",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u039a",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u039c",
            "",
            "",
            "\1\u039d",
            "\1\u039e",
            "\1\u039f",
            "\12\64\7\uffff\1\u03a0\31\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "",
            "\1\u03a2",
            "\1\u03a3",
            "\1\u03a4",
            "\1\u03a5",
            "\1\u03a6",
            "\1\u03a7",
            "",
            "\1\u03a8",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u03aa",
            "",
            "\12\64\7\uffff\23\64\1\u03ab\6\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u03ad",
            "",
            "",
            "\1\u03ae",
            "",
            "",
            "\1\u03af",
            "",
            "\1\u03b0",
            "",
            "\1\u03b2\1\uffff\1\u03b1",
            "\1\u03b3",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u03b5",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "",
            "",
            "\1\u03b7",
            "",
            "\1\u03b8",
            "\1\u03b9",
            "\1\u03ba",
            "\1\u03bb",
            "\1\u03bc",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u03be",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u03c0",
            "\1\u03c1",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u03c4",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u03c7",
            "\1\u03c8",
            "\1\u03c9",
            "\1\u03ca",
            "\1\u03cb",
            "\1\u03cc",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u03ce",
            "\1\u03cf",
            "\1\u03d0",
            "\12\64\7\uffff\22\64\1\u03d1\7\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u03d3",
            "\1\u03d4",
            "\1\u03d5",
            "\1\u03d6",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u03d9",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u03db",
            "\1\u03dc",
            "\1\u03dd",
            "\1\u03de",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u03e0",
            "\1\u03e1",
            "\1\u03e2",
            "\1\u03e3",
            "\1\u03e4",
            "\1\u03e5",
            "\1\u03e6",
            "\1\u03e7",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u03e9",
            "\1\u03ea",
            "",
            "",
            "\1\u03eb",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\22\64\1\u03ee\7\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u03f1",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u03f3",
            "",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u03f6",
            "\1\u03f7",
            "",
            "\1\u03f8",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u03fb",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u03fe",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0400",
            "\12\64\7\uffff\22\64\1\u0401\7\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0404",
            "\1\u0405",
            "",
            "",
            "\1\u0406",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0408",
            "\1\u0409",
            "",
            "\1\u040a",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u040c",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u040e",
            "\1\u040f",
            "\1\u0410",
            "\1\u0411",
            "\1\u0412",
            "\1\u0413",
            "",
            "",
            "\1\u0414",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0416",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0419",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u041c\4\uffff\1\u041d",
            "\1\u041e",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "",
            "\1\u0421",
            "",
            "\1\u0422",
            "",
            "\1\u0423",
            "\1\u0424",
            "\1\u0425",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0427",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0429",
            "\1\u042a",
            "\1\u042b",
            "\1\u042c",
            "\1\u042d",
            "\1\u042e",
            "",
            "\1\u042f",
            "\1\u0430",
            "",
            "\1\u0431",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0434",
            "\1\u0435",
            "\1\u0436",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u0439",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u043c",
            "\1\u043d",
            "\1\u043e",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u0440",
            "\1\u0441",
            "",
            "",
            "\1\u0442",
            "",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0444",
            "\1\u0445",
            "\1\u0446",
            "\1\u0447",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u0449",
            "\1\u044a",
            "\1\u044b",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u044d",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\4\64\1\u0450\15\64\1\u0451\7\64\4\uffff\1\64"+
            "\1\uffff\32\64",
            "",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0455",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0457",
            "",
            "\1\u0458",
            "\1\u0459",
            "\1\u045a",
            "\1\u045b",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u045d",
            "\1\u045e",
            "\1\u045f",
            "",
            "\1\u0460",
            "\1\u0461",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u0465",
            "",
            "",
            "\1\u0466",
            "\1\u0467",
            "\1\u0468",
            "",
            "",
            "\1\u0469",
            "",
            "",
            "\1\u046a",
            "",
            "\1\u046b",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "",
            "\1\u046d",
            "\1\u046e",
            "\1\u046f",
            "",
            "\1\u0470",
            "\1\u0471",
            "\1\u0472",
            "",
            "\1\u0473",
            "",
            "\1\u0474",
            "\1\u0475",
            "\1\u0476",
            "\1\u0477",
            "\1\u0478",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "",
            "\1\u047c",
            "",
            "",
            "\1\u047d",
            "\1\u047e",
            "\1\u047f",
            "",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0481",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0484",
            "",
            "\1\u0485",
            "",
            "\1\u0486",
            "\1\u0487",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0489",
            "\1\u048a",
            "\12\64\7\uffff\22\64\1\u048b\7\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u048d",
            "\1\u048e",
            "\1\u048f",
            "",
            "",
            "\1\u0490",
            "\1\u0491",
            "\1\u0492",
            "",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0495",
            "\1\u0496",
            "",
            "\1\u0497",
            "\1\u0498",
            "\1\u0499",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u049c",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u04a0",
            "",
            "\1\u04a1",
            "",
            "",
            "\1\u04a2",
            "\1\u04a3",
            "",
            "",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u04a5",
            "\1\u04a6",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u04a9",
            "",
            "\1\u04ab\17\uffff\1\u04aa",
            "\12\64\7\uffff\22\64\1\u04ac\7\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u04af",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "",
            "",
            "\1\u04b1",
            "\1\u04b2",
            "\1\u04b3",
            "\1\u04b4",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u04b6",
            "\1\u04b7",
            "",
            "\1\u04b8",
            "\1\u04b9",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u04bb",
            "\1\u04bc",
            "\1\u04bd",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u04bf",
            "\1\u04c0",
            "\1\u04c1",
            "\1\u04c2",
            "\1\u04c3",
            "",
            "",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u04c5",
            "\1\u04c6",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u04c8",
            "",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u04ca",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u04cc",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u04ce",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u04d0",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u04d4",
            "\1\u04d5",
            "",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u04d7",
            "\1\u04d8",
            "\1\u04d9",
            "\1\u04da",
            "",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "",
            "",
            "\1\u04dc",
            "\1\u04dd",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u04df",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u04e1",
            "",
            "",
            "\1\u04e2",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u04e4",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "",
            "\1\u04e6",
            "",
            "\1\u04e7",
            "\1\u04e8",
            "\1\u04e9",
            "\1\u04ea",
            "",
            "\1\u04eb",
            "\1\u04ec",
            "\1\u04ed",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u04f1",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u04f3",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\4\64\1\u04f6\15\64\1\u04f5\7\64\4\uffff\1\64"+
            "\1\uffff\32\64",
            "\1\u04f8",
            "",
            "\1\u04f9",
            "\1\u04fa",
            "",
            "\1\u04fb",
            "",
            "\1\u04fc",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u04fe",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "",
            "",
            "\1\u0500",
            "\1\u0501",
            "",
            "\1\u0502",
            "\1\u0503",
            "\1\u0504",
            "\1\u0505",
            "",
            "\1\u0506",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u0508",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u050b",
            "",
            "\1\u050c",
            "\1\u050d",
            "\1\u050e",
            "\1\u050f",
            "\1\u0510",
            "\1\u0511",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "",
            "",
            "\1\u0514",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0517",
            "",
            "\1\u0518",
            "\1\u0519",
            "\1\u051a",
            "\1\u051b",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u051d",
            "",
            "\1\u051e",
            "\1\u051f",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0523",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "\1\u0525",
            "",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0527",
            "\1\u0528",
            "\1\u0529",
            "\1\u052a",
            "\1\u052b",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "",
            "\1\u052d",
            "",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u0530",
            "\1\u0531",
            "\1\u0532",
            "",
            "\1\u0533",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "",
            "",
            "\1\u0536",
            "",
            "\1\u0537",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\1\u053b",
            "\1\u053c",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "",
            "",
            "\1\u0544",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u0546",
            "",
            "\12\64\7\uffff\32\64\4\uffff\1\64\1\uffff\32\64",
            ""
    };

    static final short[] DFA27_eot = DFA.unpackEncodedString(DFA27_eotS);
    static final short[] DFA27_eof = DFA.unpackEncodedString(DFA27_eofS);
    static final char[] DFA27_min = DFA.unpackEncodedStringToUnsignedChars(DFA27_minS);
    static final char[] DFA27_max = DFA.unpackEncodedStringToUnsignedChars(DFA27_maxS);
    static final short[] DFA27_accept = DFA.unpackEncodedString(DFA27_acceptS);
    static final short[] DFA27_special = DFA.unpackEncodedString(DFA27_specialS);
    static final short[][] DFA27_transition;

    static {
        int numStates = DFA27_transitionS.length;
        DFA27_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA27_transition[i] = DFA.unpackEncodedString(DFA27_transitionS[i]);
        }
    }

    class DFA27 extends DFA {

        public DFA27(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 27;
            this.eot = DFA27_eot;
            this.eof = DFA27_eof;
            this.min = DFA27_min;
            this.max = DFA27_max;
            this.accept = DFA27_accept;
            this.special = DFA27_special;
            this.transition = DFA27_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( KW_TRUE | KW_FALSE | KW_ALL | KW_AND | KW_OR | KW_NOT | KW_LIKE | KW_IF | KW_EXISTS | KW_CONSTANT | KW_INTERVAL | KW_INCRE | KW_ASC | KW_DESC | KW_ORDER | KW_GROUP | KW_BY | KW_HAVING | KW_WHERE | KW_FROM | KW_AS | KW_SELECT | KW_DISTINCT | KW_INSERT | KW_OVERWRITE | KW_OUTER | KW_UNIQUEJOIN | KW_PRESERVE | KW_JOIN | KW_LEFT | KW_RIGHT | KW_FULL | KW_ON | KW_PARTITION | KW_PARTITIONS | KW_TABLE | KW_TABLES | KW_COLUMNS | KW_INDEX | KW_INDEXES | KW_REBUILD | KW_FUNCTIONS | KW_SHOW | KW_MSCK | KW_REPAIR | KW_DIRECTORY | KW_LOCAL | KW_TRANSFORM | KW_USING | KW_CLUSTER | KW_DISTRIBUTE | KW_SORT | KW_UNION | KW_LOAD | KW_EXPORT | KW_IMPORT | KW_DATA | KW_INPATH | KW_IS | KW_NULL | KW_CREATE | KW_EXTERNAL | KW_ALTER | KW_CHANGE | KW_COLUMN | KW_FIRST | KW_AFTER | KW_DESCRIBE | KW_DROP | KW_RENAME | KW_IGNORE | KW_PROTECTION | KW_TO | KW_COMMENT | KW_BOOLEAN | KW_TINYINT | KW_SMALLINT | KW_INT | KW_BIGINT | KW_FLOAT | KW_DOUBLE | KW_DATE | KW_DATETIME | KW_TIMESTAMP | KW_DECIMAL | KW_STRING | KW_VARCHAR | KW_ARRAY | KW_STRUCT | KW_MAP | KW_UNIONTYPE | KW_REDUCE | KW_PARTITIONED | KW_CLUSTERED | KW_SORTED | KW_INTO | KW_BUCKETS | KW_ROW | KW_ROWS | KW_FORMAT | KW_DELIMITED | KW_FIELDS | KW_TERMINATED | KW_ESCAPED | KW_COLLECTION | KW_ITEMS | KW_KEYS | KW_KEY_TYPE | KW_LINES | KW_STORED | KW_FILEFORMAT | KW_SEQUENCEFILE | KW_TEXTFILE | KW_RCFILE | KW_ORCFILE | KW_INPUTFORMAT | KW_OUTPUTFORMAT | KW_INPUTDRIVER | KW_OUTPUTDRIVER | KW_OFFLINE | KW_ENABLE | KW_DISABLE | KW_READONLY | KW_NO_DROP | KW_LOCATION | KW_TABLESAMPLE | KW_BUCKET | KW_OUT | KW_OF | KW_PERCENT | KW_CAST | KW_ADD | KW_REPLACE | KW_RLIKE | KW_REGEXP | KW_TEMPORARY | KW_FUNCTION | KW_MACRO | KW_EXPLAIN | KW_EXTENDED | KW_FORMATTED | KW_PRETTY | KW_DEPENDENCY | KW_LOGICAL | KW_SERDE | KW_WITH | KW_DEFERRED | KW_SERDEPROPERTIES | KW_DBPROPERTIES | KW_LIMIT | KW_SET | KW_UNSET | KW_TBLPROPERTIES | KW_IDXPROPERTIES | KW_VALUE_TYPE | KW_ELEM_TYPE | KW_CASE | KW_WHEN | KW_THEN | KW_ELSE | KW_END | KW_MAPJOIN | KW_STREAMTABLE | KW_HOLD_DDLTIME | KW_CLUSTERSTATUS | KW_UTC | KW_UTCTIMESTAMP | KW_LONG | KW_DELETE | KW_PLUS | KW_MINUS | KW_FETCH | KW_INTERSECT | KW_VIEW | KW_IN | KW_DATABASE | KW_DATABASES | KW_MATERIALIZED | KW_SCHEMA | KW_SCHEMAS | KW_GRANT | KW_REVOKE | KW_SSL | KW_UNDO | KW_LOCK | KW_LOCKS | KW_UNLOCK | KW_SHARED | KW_EXCLUSIVE | KW_PROCEDURE | KW_UNSIGNED | KW_WHILE | KW_READ | KW_READS | KW_PURGE | KW_RANGE | KW_ANALYZE | KW_BEFORE | KW_BETWEEN | KW_BOTH | KW_BINARY | KW_CROSS | KW_CONTINUE | KW_CURSOR | KW_TRIGGER | KW_RECORDREADER | KW_RECORDWRITER | KW_SEMI | KW_LATERAL | KW_TOUCH | KW_ARCHIVE | KW_UNARCHIVE | KW_COMPUTE | KW_STATISTICS | KW_USE | KW_OPTION | KW_CONCATENATE | KW_SHOW_DATABASE | KW_UPDATE | KW_RESTRICT | KW_CASCADE | KW_SKEWED | KW_ROLLUP | KW_CUBE | KW_DIRECTORIES | KW_FOR | KW_WINDOW | KW_UNBOUNDED | KW_PRECEDING | KW_FOLLOWING | KW_CURRENT | KW_LESS | KW_MORE | KW_OVER | KW_GROUPING | KW_SETS | KW_TRUNCATE | KW_NOSCAN | KW_PARTIALSCAN | KW_USER | KW_ROLE | KW_INNER | KW_EXCHANGE | DOT | COLON | COMMA | SEMICOLON | LPAREN | RPAREN | LSQUARE | RSQUARE | LCURLY | RCURLY | EQUAL | EQUAL_NS | NOTEQUAL | LESSTHANOREQUALTO | LESSTHAN | GREATERTHANOREQUALTO | GREATERTHAN | DIVIDE | PLUS | MINUS | STAR | MOD | DIV | AMPERSAND | TILDE | BITWISEOR | BITWISEXOR | QUESTION | DOLLAR | StringLiteral | CharSetLiteral | BigintLiteral | SmallintLiteral | TinyintLiteral | DecimalLiteral | ByteLengthLiteral | Number | TimeUnit | Identifier | CharSetName | WS | COMMENT );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA27_356 = input.LA(1);

                        s = -1;
                        if ( (LA27_356=='\"') ) {s = 168;}

                        else if ( ((LA27_356 >= '\u0000' && LA27_356 <= '!')||(LA27_356 >= '#' && LA27_356 <= '[')||(LA27_356 >= ']' && LA27_356 <= '\uFFFF')) ) {s = 166;}

                        else if ( (LA27_356=='\\') ) {s = 167;}

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA27_166 = input.LA(1);

                        s = -1;
                        if ( (LA27_166=='\"') ) {s = 168;}

                        else if ( ((LA27_166 >= '\u0000' && LA27_166 <= '!')||(LA27_166 >= '#' && LA27_166 <= '[')||(LA27_166 >= ']' && LA27_166 <= '\uFFFF')) ) {s = 166;}

                        else if ( (LA27_166=='\\') ) {s = 167;}

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA27_48 = input.LA(1);

                        s = -1;
                        if ( ((LA27_48 >= '\u0000' && LA27_48 <= '&')||(LA27_48 >= '(' && LA27_48 <= '[')||(LA27_48 >= ']' && LA27_48 <= '\uFFFF')) ) {s = 163;}

                        else if ( (LA27_48=='\\') ) {s = 164;}

                        else if ( (LA27_48=='\'') ) {s = 165;}

                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA27_49 = input.LA(1);

                        s = -1;
                        if ( ((LA27_49 >= '\u0000' && LA27_49 <= '!')||(LA27_49 >= '#' && LA27_49 <= '[')||(LA27_49 >= ']' && LA27_49 <= '\uFFFF')) ) {s = 166;}

                        else if ( (LA27_49=='\\') ) {s = 167;}

                        else if ( (LA27_49=='\"') ) {s = 168;}

                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA27_163 = input.LA(1);

                        s = -1;
                        if ( (LA27_163=='\'') ) {s = 165;}

                        else if ( ((LA27_163 >= '\u0000' && LA27_163 <= '&')||(LA27_163 >= '(' && LA27_163 <= '[')||(LA27_163 >= ']' && LA27_163 <= '\uFFFF')) ) {s = 163;}

                        else if ( (LA27_163=='\\') ) {s = 164;}

                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA27_354 = input.LA(1);

                        s = -1;
                        if ( (LA27_354=='\'') ) {s = 165;}

                        else if ( ((LA27_354 >= '\u0000' && LA27_354 <= '&')||(LA27_354 >= '(' && LA27_354 <= '[')||(LA27_354 >= ']' && LA27_354 <= '\uFFFF')) ) {s = 163;}

                        else if ( (LA27_354=='\\') ) {s = 164;}

                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA27_164 = input.LA(1);

                        s = -1;
                        if ( ((LA27_164 >= '\u0000' && LA27_164 <= '\uFFFF')) ) {s = 354;}

                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA27_167 = input.LA(1);

                        s = -1;
                        if ( ((LA27_167 >= '\u0000' && LA27_167 <= '\uFFFF')) ) {s = 356;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 27, _s, input);
            error(nvae);
            throw nvae;
        }

    }
 

}