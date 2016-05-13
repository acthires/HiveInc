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
      orders o join lineitem l
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

