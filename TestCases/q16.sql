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

--Q16
select
	p_brand, p_type, p_size, count(distinct ps_suppkey) as supplier_cnt
from 
	(select 
		* 
	from
		(select
			p_brand, p_type, p_size, ps_suppkey
     		from
       			partsupp/*+INCREMENTAL(constant 10 day INTERVAL 3/second)*/ ps join part p
     		on
       			p.p_partkey = ps.ps_partkey and p.p_brand <> 'Brand#45'
       			and not p.p_type like 'MEDIUM POLISHED%'
       			join supplier s
     		on
      			ps.ps_suppkey = s.s_suppkey
        	where
			not s_comment like '%Customer%Complaints%'
   		)q 
   	where
   		p_size = 49 or p_size = 14 or p_size = 23 or
        	p_size = 45 or p_size = 19 or p_size = 3 or
        	p_size = 36 or p_size = 9
	) q16_all
group by
	p_brand, p_type, p_size
order by 
	supplier_cnt desc, p_brand, p_type, p_size;

