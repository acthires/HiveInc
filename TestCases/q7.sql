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

--Q7
drop table if exists q7_volume_shipping_tmp;
create table q7_volume_shipping_tmp(supp_nation string, cust_nation string, s_nationkey int, c_nationkey int);

insert overwrite table q7_volume_shipping_tmp
select 
  * 
from
  (
    select 
      n1.n_name as supp_nation, n2.n_name as cust_nation, n1.n_nationkey as s_nationkey,      
      n2.n_nationkey as c_nationkey
from 
  nation n1 join nation n2 
  on 
    n1.n_name = 'FRANCE' and n2.n_name = 'GERMANY'
    UNION ALL
select 
  n1.n_name as supp_nation, n2.n_name as cust_nation, n1.n_nationkey as s_nationkey, 
  n2.n_nationkey as c_nationkey
from 
  nation n1 join nation n2 
  on 
    n2.n_name = 'FRANCE' and n1.n_name = 'GERMANY'
) a;

select
  supp_nation, cust_nation, l_year, sum(volume) as revenue
from
  (
    select
      supp_nation, cust_nation, year(l_shipdate) as l_year,
      l_extendedprice * (1 - l_discount) as volume
    from
      orders o join lineitem/*+INCREMENTAL(after 2014/3/4,12:5:20 INTERVAL 3/second)*/ l
        on o.o_orderkey = l.l_orderkey and l.l_shipdate >= '1995-01-01'
                 and l.l_shipdate <= '1996-12-31'
      join customer c
        on c.c_custkey = o.o_custkey
      join supplier s
        on s.s_suppkey = l.l_suppkey
      join q7_volume_shipping_tmp t
        on c.c_nationkey = t.c_nationkey and s.s_nationkey = t.s_nationkey
   ) shipping
group by supp_nation, cust_nation, l_year
order by supp_nation, cust_nation, l_year;

