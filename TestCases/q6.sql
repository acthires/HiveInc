set hive.inc.test.findNewFiles=true;
set hive.inc.test.putNewFiles=true;
set hive.inc.test.runinc=true;
set hive.inc.test.runoriginal=false;
set mapred.min.split.size=536870912;
set hive.exec.reducers.bytes.per.reducer=1225000000;
set hive.auto.convert.join=false;
set hive.inctimes=10;
set hive.inc.costmodel.gby=true;
set hive.inc.file.sourcedir=/home/kangyanli/tpch_2_15.0/dbgen/20G/;
set hive.inc.file.sinkdir=/home/kangyanli/tpch/20G/;

--Q6
select 
  sum(l_extendedprice*l_discount) as revenue
from 
  lineitem/*+INCREMENTAL(after 2014/3/4,12:5:20 INTERVAL 3/second)*/
where 
  l_shipdate >= '1994-01-01'
  and l_shipdate < '1995-01-01'
  and l_discount >= 0.05 and l_discount <= 0.07
  and l_quantity < 24;
  
