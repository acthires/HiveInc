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

--Q11
select
  ps_partkey, part_value
from
  (select
    ps_partkey, sum(ps_supplycost * ps_availqty) as part_value
  from
    nation n join supplier s
  on
    s.s_nationkey = n.n_nationkey and n.n_name = 'GERMANY'
  join partsupp/*+INCREMENTAL(2014/3/4,12:5:20-2014/8/4,12:5:20)*/ ps
  on
    ps.ps_suppkey = s.s_suppkey
  group by ps_partkey
  )t
  join
  (
    select
      sum(ps_supplycost * ps_availqty) as total_value
    from
      nation n join supplier s
    on
      s.s_nationkey = n.n_nationkey and n.n_name = 'GERMANY'
    join partsupp/*+INCREMENTAL(2014/3/4,12:5:20-2014/8/4,12:5:20)*/ ps
    on
      ps.ps_suppkey = s.s_suppkey
  )m
  where
    t.part_value > m.total_value*0.00002
order by part_value desc;

