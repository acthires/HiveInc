set mapred.min.split.size=536870912;
set hive.exec.reducers.bytes.per.reducer=1225000000;
set hive.auto.convert.join=false;

--Q3
select 
  l_orderkey, sum(l_extendedprice*(1-l_discount)) as revenue, o_orderdate, o_shippriority 
from 
  customer c join orders o 
    on c.c_mktsegment = 'BUILDING' and c.c_custkey = o.o_custkey 
  join lineitem l 
    on l.l_orderkey = o.o_orderkey
where 
  o_orderdate < '1995-03-15' and l_shipdate > '1995-03-15' 
group by l_orderkey, o_orderdate, o_shippriority 
order by revenue desc, o_orderdate 
limit 10;

