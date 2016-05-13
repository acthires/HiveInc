--Q17
select
  sum(l_extendedprice) / 7.0 as avg_yearly
from
  part p join lineitem l
    on
      p.p_partkey = l.l_partkey
      and p.p_brand = 'Brand#23'
      and p.p_container = 'MED BOX'
  join 
	(select 
  		l_partkey as t_partkey, 0.2 * avg(l_quantity) as t_avg_quantity
	from 
  		lineitem
	group by 
		l_partkey
	)t
    on l.l_partkey = t.t_partkey
where l_quantity < t_avg_quantity;

