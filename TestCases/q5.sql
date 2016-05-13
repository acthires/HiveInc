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

--Q5
select
  n_name, sum(l_extendedprice * (1 - l_discount)) as revenue
from
  nation n join region r
    on n.n_regionkey = r.r_regionkey and r.r_name = 'ASIA'
  join supplier s
    on s.s_nationkey = n.n_nationkey
  join lineitem/*+INCREMENTAL(after 2014/3/4,12:5:20 INTERVAL 3/second)*/ l
    on l.l_suppkey = s.s_suppkey
  join orders o
    on l.l_orderkey = o.o_orderkey and o.o_orderdate >= '1994-01-01'
              and o.o_orderdate < '1995-01-01'
  join customer c
    on c.c_nationkey = s.s_nationkey and c.c_custkey = o.o_custkey
group by n_name
order by revenue desc;

