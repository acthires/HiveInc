# HiveInc
======================

HiveInc is implemented on Apache Hive (http://hive.apache.org/) data warehouse software, which facilitates querying and managing large datasets residing in distributed storage. Built on top of Apache Hadoop (http://hadoop.apache.org/) (TM).
HiveInc run HiveQL periodically, which can detect new files for incremental table and get the 


Getting Started
===============
	- Compile: ant
	- Use:  
		○ Write HiveQL with incremental Mark, like :
			§ SELECT  * FROM lineitem/*+INCREMENTAL(after 2014/3/4 INTERVAL 3/M)*/ l JOIN orders o ON (l.l_orderkey=o.o_orderkey) 
		○ Run the HiveQL using following two way：
			§ hive -e "hiveQL";
			§ Write hiveQL in file "example.sql" , run it with command "hive  -f example.sql";

Hive getting started:
- Hive Installation Instructions and a quick tutorial:
  https://cwiki.apache.org/confluence/display/Hive/GettingStarted

- Hive A longer tutorial that covers more features of HiveQL:
  https://cwiki.apache.org/confluence/display/Hive/Tutorial

- The HiveQL Language Manual:
  https://cwiki.apache.org/confluence/display/Hive/LanguageManual


Requirements
============

- Java 1.6

- Hadoop 0.20.x (x >= 1)

Implement Method
====================
	1. Add rules to ParseDriver's input *.g files for "Incremental Mark" Parse; 
	2. IncDriver: run HiveQL periodically
	3. ExtractTmpSemanticAnaylyzer: 1)Identify incremental path identify; 2)Extract non-incremental subAST.
	4. IncSplitSemanticAnalyzer: identify the split/merge position in incremental AST
	5. Modify MapReduceCompiler: store  intermediate output for reuse.
	

Optimization Effect
====================
