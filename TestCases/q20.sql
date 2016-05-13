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

--Q20
select 
  s_name, s_address
from 
  supplier s join nation n
  on
    s.s_nationkey = n.n_nationkey
    and n.n_name = 'CANADA'
  join 
	(select
      		ps_suppkey
    	from 
		(select ps_suppkey, ps_availqty, sum_quantity
      		from
        		partsupp ps 
	  		join
			 	(select distinct p_partkey
        			from
          				part
        			where
          				p_name like 'forest%'
				) t1
      			on
        			ps.ps_partkey = t1.p_partkey
      			join
				(select
          				l_partkey, l_suppkey, 0.5 * sum(l_quantity) as sum_quantity
        			from
        				lineitem/*+INCREMENTAL(2014/3/4,12:5:20-2014/8/4,12:5:20)*/
        			where
          				l_shipdate >= '1994-01-01'
          				and l_shipdate < '1995-01-01'
        			group by 
					l_partkey, l_suppkey
				) t2
      			on
        			ps.ps_partkey = t2.l_partkey and ps.ps_suppkey = t2.l_suppkey) psss
    		where
    			ps_availqty > sum_quantity
    		group by 
			ps_suppkey
	) t4
  on 
	s.s_suppkey = t4.ps_suppkey
  order by s_name;

