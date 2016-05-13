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
      join lineitem l 
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

