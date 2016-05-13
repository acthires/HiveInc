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

--Q9
select
  nation, o_year, sum(amount) as sum_profit
from
  (
select
  n_name as nation, year(o_orderdate) as o_year,
  l_extendedprice * (1 - l_discount) -  ps_supplycost * l_quantity as amount
    from
      nation n join supplier s on n.n_nationkey = s.s_nationkey
      join lineitem/*+INCREMENTAL(after 2014/3/4,12:5:20 INTERVAL 3/second)*/ l 
	on s.s_suppkey = l.l_suppkey
      join partsupp ps
        on ps.ps_suppkey = l.l_suppkey and ps.ps_partkey = l.l_partkey
      join part p
         on p.p_name like '%green%' and p.p_partkey = l.l_partkey
      join orders o
        on o.o_orderkey = l.l_orderkey
  )profit
group by nation, o_year
order by nation, o_year desc;

