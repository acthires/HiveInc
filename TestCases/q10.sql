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

--Q10
select 
  c_custkey, c_name, sum(l_extendedprice * (1 - l_discount)) as revenue, 
  c_acctbal, n_name, c_address, c_phone, c_comment
from
  customer c join orders o 
  on 
    c.c_custkey = o.o_custkey and o.o_orderdate >= '1993-10-01' and o.o_orderdate < '1994-01-01'
  join nation n 
  on 
    c.c_nationkey = n.n_nationkey
  join lineitem/*+INCREMENTAL(2014/3/4,12:5:20-2014/8/4,12:5:20)*/ l 
  on 
    l.l_orderkey = o.o_orderkey and l.l_returnflag = 'R'
group by c_custkey, c_name, c_acctbal, c_phone, n_name, c_address, c_comment 
order by revenue desc 
limit 20;
  
