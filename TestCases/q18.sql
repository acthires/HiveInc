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

--Q18
select 
  c_name,c_custkey,o_orderkey,o_orderdate,o_totalprice,sum(l_quantity)
from 
  customer c 
  join 
  orders o 
  on 
    c.c_custkey = o.o_custkey
  join (
    select
      l_orderkey, sum(l_quantity) as t_sum_quantity
   from
     lineitem/*+INCREMENTAL(2014/3/4,12:5:20-2014/8/4,12:5:20)*/
   group by l_orderkey) t 
  on 
    o.o_orderkey = t.l_orderkey and t.t_sum_quantity > 300
  join 
    lineitem /*+INCREMENTAL(constant 10 day INTERVAL 3/second)*/ l 
  on 
    o.o_orderkey = l.l_orderkey
group by c_name,c_custkey,o_orderkey,o_orderdate,o_totalprice
order by o_totalprice desc,o_orderdate
limit 100;

