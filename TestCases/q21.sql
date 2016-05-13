--Q21
select
  s_name, count(1) as numwait
from
  nation n join supplier s
    on s.s_nationkey = n.n_nationkey
       and n.n_name = 'SAUDI ARABIA'
  join lineitem l
    on s.s_suppkey = l.l_suppkey
  join orders o
    on o.o_orderkey = l.l_orderkey and o.o_orderstatus = 'F'
  join
	(select
  		l_orderkey, count(distinct l_suppkey) as count_suppkey, max(l_suppkey) as max_suppkey
     	from
  		lineitem
     	group by 
		l_orderkey
	) t1
    on l.l_orderkey = t1.l_orderkey
  right outer join 
	(select
  		l_orderkey, count(distinct l_suppkey) as count_suppkey, max(l_suppkey) as max_suppkey
	from
  		lineitem
	where
  		l_receiptdate > l_commitdate
	group by 
		l_orderkey
	) t2
    on l.l_orderkey = t2.l_orderkey
where
  l.l_receiptdate > l.l_commitdate
  and ((t1.count_suppkey > 1) or ((t1.count_suppkey=1) and (l_suppkey <> t1.max_suppkey)))
  and ((t2.count_suppkey is null) or ((t2.count_suppkey=1) and (l_suppkey = t2.max_suppkey)))
group by s_name
order by numwait desc, s_name
limit 100;

