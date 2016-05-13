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

--Q19
select
  sum(l_extendedprice * (1 - l_discount) ) as revenue
from
  lineitem  /*+INCREMENTAL(constant 10 day INTERVAL 3/second)*/ l join part p
  on 
    p.p_partkey = l.l_partkey    
where
  (
    p_brand = 'Brand#12'
	and p_container REGEXP 'SM CASE||SM BOX||SM PACK||SM PKG'
	and l_quantity >= 1 and l_quantity <= 11
	and p_size >= 1 and p_size <= 5
	and l_shipmode REGEXP 'AIR||AIR REG'
	and l_shipinstruct = 'DELIVER IN PERSON'
  ) 
  or 
  (
    p_brand = 'Brand#23'
	and p_container REGEXP 'MED BAG||MED BOX||MED PKG||MED PACK'
	and l_quantity >= 10 and l_quantity <= 20
	and p_size >= 1 and p_size <= 10
	and l_shipmode REGEXP 'AIR||AIR REG'
	and l_shipinstruct = 'DELIVER IN PERSON'
  )
  or
  (
	p_brand = 'Brand#34'
	and p_container REGEXP 'LG CASE||LG BOX||LG PACK||LG PKG'
	and l_quantity >= 20 and l_quantity <= 30
	and p_size >= 1 and p_size <= 15
	and l_shipmode REGEXP 'AIR||AIR REG'
	and l_shipinstruct = 'DELIVER IN PERSON'
  );

