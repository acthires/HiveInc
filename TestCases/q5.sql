--Q5
select
  n_name, sum(l_extendedprice * (1 - l_discount)) as revenue
from
  nation n join region r
    on n.n_regionkey = r.r_regionkey and r.r_name = 'ASIA'
  join supplier s
    on s.s_nationkey = n.n_nationkey
  join lineitem l
    on l.l_suppkey = s.s_suppkey
  join orders o
    on l.l_orderkey = o.o_orderkey and o.o_orderdate >= '1994-01-01'
              and o.o_orderdate < '1995-01-01'
  join customer c
    on c.c_nationkey = s.s_nationkey and c.c_custkey = o.o_custkey
group by n_name
order by revenue desc;

