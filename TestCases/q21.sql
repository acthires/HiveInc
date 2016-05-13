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

--Q21
select
  s_name, count(1) as numwait
from
  nation n join supplier s
    on s.s_nationkey = n.n_nationkey
       and n.n_name = 'SAUDI ARABIA'
  join lineitem/*+INCREMENTAL(constant 10 day INTERVAL 3/second)*/ l
    on s.s_suppkey = l.l_suppkey
  join orders o
    on o.o_orderkey = l.l_orderkey and o.o_orderstatus = 'F'
  join
	(select
  		l_orderkey, count(distinct l_suppkey) as count_suppkey, max(l_suppkey) as max_suppkey
     	from
  		lineitem/*+INCREMENTAL(2014/3/4,12:5:20-2014/8/4,12:5:20)*/
     	group by 
		l_orderkey
	) t1
    on l.l_orderkey = t1.l_orderkey
  right outer join 
	(select
  		l_orderkey, count(distinct l_suppkey) as count_suppkey, max(l_suppkey) as max_suppkey
	from
  		lineitem/*+INCREMENTAL(2014/3/4,12:5:20-2014/8/4,12:5:20)*/
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

