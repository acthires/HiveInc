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

--Q8
select
  o_year, sum(case when nation = 'BRAZIL' then volume else 0.0 end) / sum(volume) as mkt_share
from
  (
select
  year(o_orderdate) as o_year, l_extendedprice * (1-l_discount) as volume,
  n2.n_name as nation
    from
    nation n1 join region r
      on n1.n_regionkey = r.r_regionkey and r.r_name = 'AMERICA'
    join customer c
      on c.c_nationkey = n1.n_nationkey
    join orders o
      on c.c_custkey = o.o_custkey
    join lineitem/*+INCREMENTAL(after 2014/3/4,12:5:20 INTERVAL 3/second)*/ l
      on l.l_orderkey = o.o_orderkey and o.o_orderdate >= '1995-01-01'
         and o.o_orderdate < '1996-12-31'
    join part p
      on p.p_partkey = l.l_partkey and p.p_type = 'ECONOMY ANODIZED STEEL'
    join supplier s
      on s.s_suppkey = l.l_suppkey
    join  nation n2
      on s.s_nationkey = n2.n_nationkey
  ) all_nation
group by o_year
order by o_year;

