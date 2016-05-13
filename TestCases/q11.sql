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
  join partsupp ps
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
    join partsupp ps
    on
      ps.ps_suppkey = s.s_suppkey
  )m
  where
    t.part_value > m.total_value*0.00002
order by part_value desc;

