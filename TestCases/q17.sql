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

--Q17
select
  sum(l_extendedprice) / 7.0 as avg_yearly
from
  part p join lineitem/*+INCREMENTAL(2014/3/4,12:5:20-2014/8/4,12:5:20)*/ l
    on
      p.p_partkey = l.l_partkey
      and p.p_brand = 'Brand#23'
      and p.p_container = 'MED BOX'
  join 
	(select 
  		l_partkey as t_partkey, 0.2 * avg(l_quantity) as t_avg_quantity
	from 
  		lineitem/*+INCREMENTAL(2014/3/4,12:5:20-2014/8/4,12:5:20)*/
	group by 
		l_partkey
	)t
    on l.l_partkey = t.t_partkey
where l_quantity < t_avg_quantity;

