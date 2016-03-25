set hive.inc.test.findNewFiles=true;
set hive.inc.test.putNewFiles=true;
set hive.inc.test.runinc=true;
set hive.inc.test.runoriginal=false;
set mapred.min.split.size=536870912;
set hive.exec.reducers.bytes.per.reducer=1225000000;
set hive.auto.convert.join=false;
set hive.inctimes=10;
--set hive.inc.split.gby=false;
set hive.inc.costmodel.gby=true;
--set hive.inc.test.costmodel.split=true;
set hive.inc.file.sourcedir=/home/kangyanli/tpch_2_15.0/dbgen/20G/;
set hive.inc.file.sinkdir=/home/kangyanli/tpch/20G/;
--Q1
SELECT
  L_RETURNFLAG, L_LINESTATUS, SUM(L_QUANTITY), SUM(L_EXTENDEDPRICE), SUM(L_EXTENDEDPRICE*(1-L_DISCOUNT)), SUM(L_EXTENDEDPRICE*(1-L_DISCOUNT)*(1+L_TAX)), AVG(L_QUANTITY), AVG(L_EXTENDEDPRICE), AVG(L_DISCOUNT), COUNT(1)
FROM
  lineitem/*+INCREMENTAL(after 2014/3/4,12:5:20 INTERVAL 3/second)*/
WHERE
  L_SHIPDATE<='1998-09-02'
GROUP BY L_RETURNFLAG, L_LINESTATUS
ORDER BY L_RETURNFLAG, L_LINESTATUS;

--Q2
select
  s.s_acctbal, s.s_name, n.n_name, p.p_partkey, ps.ps_supplycost, p.p_mfgr, s.s_address, s.s_phone, s.s_comment
from
  nation n join region r
  on
    n.n_regionkey = r.r_regionkey and r.r_name = 'EUROPE'
  join supplier s
  on
    s.s_nationkey = n.n_nationkey
  join partsupp/*+INCREMENTAL(after 2014/3/4,12:5:20 INTERVAL 3/second)*/  ps
  on
    s.s_suppkey = ps.ps_suppkey
  join part p
  on
    p.p_partkey = ps.ps_partkey and p.p_size = 15 and p.p_type like '%BRASS'
  join
  (
   select
      min(ps_supplycost) as ps_min_supplycost
   from
     nation n join region r
   on
     n.n_regionkey = r.r_regionkey and r.r_name = 'EUROPE'
   join supplier s
   on
     s.s_nationkey = n.n_nationkey
   join partsupp/*+INCREMENTAL(after 2014/3/4,12:5:20 INTERVAL 3/second)*/  ps
   on
     s.s_suppkey = ps.ps_suppkey
   join part p
   on
     p.p_partkey = ps.ps_partkey and p.p_size = 15 and p.p_type like '%BRASS'
  )t
  on
    ps.ps_supplycost=t.ps_min_supplycost
  order by s_acctbal desc, n_name, s_name, p_partkey
  limit 100;

--Q3
select 
  l_orderkey, sum(l_extendedprice*(1-l_discount)) as revenue, o_orderdate, o_shippriority 
from 
  customer c join orders o 
    on c.c_mktsegment = 'BUILDING' and c.c_custkey = o.o_custkey 
  join lineitem/*+INCREMENTAL(after 2014/3/4,12:5:20 INTERVAL 3/second)*/ l 
    on l.l_orderkey = o.o_orderkey
where 
  o_orderdate < '1995-03-15' and l_shipdate > '1995-03-15' 
group by l_orderkey, o_orderdate, o_shippriority 
order by revenue desc, o_orderdate 
limit 10;

--Q4
select o_orderpriority, count(1) as order_count 
from 
  orders o join 
  (
  select
     distinct l_orderkey as o_orderkey
   from
     lineitem /*+INCREMENTAL(after 2014/3/4,12:5:20 INTERVAL 3/second)*/
   where
     l_commitdate < l_receiptdate) t 
  on 
o.o_orderkey = t.o_orderkey and o.o_orderdate >= '1993-07-01' and o.o_orderdate < '1993-10-01' 
group by o_orderpriority 
order by o_orderpriority;

--Q5
select
  n_name, sum(l_extendedprice * (1 - l_discount)) as revenue
from
  nation n join region r
    on n.n_regionkey = r.r_regionkey and r.r_name = 'ASIA'
  join supplier s
    on s.s_nationkey = n.n_nationkey
  join lineitem/*+INCREMENTAL(after 2014/3/4,12:5:20 INTERVAL 3/second)*/ l
    on l.l_suppkey = s.s_suppkey
  join orders o
    on l.l_orderkey = o.o_orderkey and o.o_orderdate >= '1994-01-01'
              and o.o_orderdate < '1995-01-01'
  join customer c
    on c.c_nationkey = s.s_nationkey and c.c_custkey = o.o_custkey
group by n_name
order by revenue desc;

--Q6
select 
  sum(l_extendedprice*l_discount) as revenue
from 
  lineitem/*+INCREMENTAL(after 2014/3/4,12:5:20 INTERVAL 3/second)*/
where 
  l_shipdate >= '1994-01-01'
  and l_shipdate < '1995-01-01'
  and l_discount >= 0.05 and l_discount <= 0.07
  and l_quantity < 24;
  

--Q7
drop table if exists q7_volume_shipping_tmp;
create table q7_volume_shipping_tmp(supp_nation string, cust_nation string, s_nationkey int, c_nationkey int);

insert overwrite table q7_volume_shipping_tmp
select 
  * 
from
  (
    select 
      n1.n_name as supp_nation, n2.n_name as cust_nation, n1.n_nationkey as s_nationkey,      
      n2.n_nationkey as c_nationkey
from 
  nation n1 join nation n2 
  on 
    n1.n_name = 'FRANCE' and n2.n_name = 'GERMANY'
    UNION ALL
select 
  n1.n_name as supp_nation, n2.n_name as cust_nation, n1.n_nationkey as s_nationkey, 
  n2.n_nationkey as c_nationkey
from 
  nation n1 join nation n2 
  on 
    n2.n_name = 'FRANCE' and n1.n_name = 'GERMANY'
) a;

select
  supp_nation, cust_nation, l_year, sum(volume) as revenue
from
  (
    select
      supp_nation, cust_nation, year(l_shipdate) as l_year,
      l_extendedprice * (1 - l_discount) as volume
    from
      orders o join lineitem/*+INCREMENTAL(after 2014/3/4,12:5:20 INTERVAL 3/second)*/ l
        on o.o_orderkey = l.l_orderkey and l.l_shipdate >= '1995-01-01'
                 and l.l_shipdate <= '1996-12-31'
      join customer c
        on c.c_custkey = o.o_custkey
      join supplier s
        on s.s_suppkey = l.l_suppkey
      join q7_volume_shipping_tmp t
        on c.c_nationkey = t.c_nationkey and s.s_nationkey = t.s_nationkey
   ) shipping
group by supp_nation, cust_nation, l_year
order by supp_nation, cust_nation, l_year;

--Q8
select
  o_year, sum(case when nation = 'BRAZIL' then volume else 0.0 end) / sum(volume) as mkt_share
from
  (
select
  year(o_orderdate) as o_year, l_extendedprice * (1-l_discount) as volume,
  n2.n_name as nation
    from
    nation n1 join region r
      on n1.n_regionkey = r.r_regionkey and r.r_name = 'AMERICA'
    join customer c
      on c.c_nationkey = n1.n_nationkey
    join orders o
      on c.c_custkey = o.o_custkey
    join lineitem/*+INCREMENTAL(after 2014/3/4,12:5:20 INTERVAL 3/second)*/ l
      on l.l_orderkey = o.o_orderkey and o.o_orderdate >= '1995-01-01'
         and o.o_orderdate < '1996-12-31'
    join part p
      on p.p_partkey = l.l_partkey and p.p_type = 'ECONOMY ANODIZED STEEL'
    join supplier s
      on s.s_suppkey = l.l_suppkey
    join  nation n2
      on s.s_nationkey = n2.n_nationkey
  ) all_nation
group by o_year
order by o_year;

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
      join lineitem/*+INCREMENTAL(after 2014/3/4,12:5:20 INTERVAL 3/second)*/ l 
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

--Q10
select 
  c_custkey, c_name, sum(l_extendedprice * (1 - l_discount)) as revenue, 
  c_acctbal, n_name, c_address, c_phone, c_comment
from
  customer c join orders o 
  on 
    c.c_custkey = o.o_custkey and o.o_orderdate >= '1993-10-01' and o.o_orderdate < '1994-01-01'
  join nation n 
  on 
    c.c_nationkey = n.n_nationkey
  join lineitem/*+INCREMENTAL(2014/3/4,12:5:20-2014/8/4,12:5:20)*/ l 
  on 
    l.l_orderkey = o.o_orderkey and l.l_returnflag = 'R'
group by c_custkey, c_name, c_acctbal, c_phone, n_name, c_address, c_comment 
order by revenue desc 
limit 20;
  
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
  join partsupp/*+INCREMENTAL(2014/3/4,12:5:20-2014/8/4,12:5:20)*/ ps
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
    join partsupp/*+INCREMENTAL(2014/3/4,12:5:20-2014/8/4,12:5:20)*/ ps
    on
      ps.ps_suppkey = s.s_suppkey
  )m
  where
    t.part_value > m.total_value*0.00002
order by part_value desc;

--Q12
select 
  l_shipmode,
  sum(case
    when o_orderpriority ='1-URGENT'
         or o_orderpriority ='2-HIGH'
    then 1
    else 0
end
  ) as high_line_count,
  sum(case
    when o_orderpriority <> '1-URGENT'
         and o_orderpriority <> '2-HIGH'
    then 1
    else 0
end
  ) as low_line_count
from
  orders o join lineitem/*+INCREMENTAL(2014/3/4,12:5:20-2014/8/4,12:5:20)*/ l 
  on 
    o.o_orderkey = l.l_orderkey and l.l_commitdate < l.l_receiptdate
and l.l_shipdate < l.l_commitdate and l.l_receiptdate >= '1994-01-01' 
and l.l_receiptdate < '1995-01-01'
where 
  l.l_shipmode = 'MAIL' or l.l_shipmode = 'SHIP'
group by l_shipmode
order by l_shipmode;

--Q13
select 
  c_count, count(1) as custdist
from 
  (select 
     c_custkey, count(o_orderkey) as c_count
   from 
     customer c left outer join orders /*+INCREMENTAL(2014/3/4,12:5:20-2014/8/4,12:5:20)*/ o 
     on 
       c.c_custkey = o.o_custkey and not o.o_comment like '%special%requests%'
   group by c_custkey
   ) c_orders
group by c_count
order by custdist desc, c_count desc;

--Q14
select 
  100.00 * sum(case
               when p_type like 'PROMO%'
               then l_extendedprice*(1-l_discount)
               else 0.0
               end
  ) / sum(l_extendedprice * (1 - l_discount)) as promo_revenue
from 
  part p join lineitem/*+INCREMENTAL(2014/3/4,12:5:20-2014/8/4,12:5:20)*/ l 
  on 
    l.l_partkey = p.p_partkey and l.l_shipdate >= '1995-09-01' and l.l_shipdate < '1995-10-01';

--Q15
--create table revenue(supplier_no int, total_revenue double);
--insert overwrite table revenue
--select
--  l_suppkey as supplier_no, sum(l_extendedprice * (1 - l_discount)) as total_revenue
--from
--  lineitem
--where
--  l_shipdate >= '1996-01-01' and l_shipdate < '1996-04-01'
--group by l_suppkey;
--
--select
--  s_suppkey, s_name, s_address, s_phone, total_revenue, max_revenue
--from supplier s join
--  revenue r
--on
--  s.s_suppkey = r.supplier_no
--join
--  (
--  select max(total_revenue) as max_revenue
--  from
--  revenue
--  )m
--on
--  r.total_revenue = m.max_revenue
--order by s_suppkey;

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

--Q18
select 
  c_name,c_custkey,o_orderkey,o_orderdate,o_totalprice,sum(l_quantity)
from 
  customer c 
  join 
  orders o 
  on 
    c.c_custkey = o.o_custkey
  join (
    select
      l_orderkey, sum(l_quantity) as t_sum_quantity
   from
     lineitem/*+INCREMENTAL(2014/3/4,12:5:20-2014/8/4,12:5:20)*/
   group by l_orderkey) t 
  on 
    o.o_orderkey = t.l_orderkey and t.t_sum_quantity > 300
  join 
    lineitem /*+INCREMENTAL(constant 10 day INTERVAL 3/second)*/ l 
  on 
    o.o_orderkey = l.l_orderkey
group by c_name,c_custkey,o_orderkey,o_orderdate,o_totalprice
order by o_totalprice desc,o_orderdate
limit 100;

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

--Q22
select
  cntrycode, count(1) as numcust, sum(c_acctbal) as totacctbal
from
  orders/*+INCREMENTAL(2014/3/4,12:5:20-2014/8/4,12:5:20)*/ ot
  right outer join
  (select
    c_acctbal, c_custkey, substr(c_phone, 1, 2) as cntrycode
  from
    customer
  where
    substr(c_phone, 1, 2) = '13' or
    substr(c_phone, 1, 2) = '31' or
    substr(c_phone, 1, 2) = '23' or
    substr(c_phone, 1, 2) = '29' or
    substr(c_phone, 1, 2) = '30' or
    substr(c_phone, 1, 2) = '18' or
    substr(c_phone, 1, 2) = '17'
  )ct
  on
    ct.c_custkey = ot.o_custkey and ot.o_custkey is null
  join
  (select
       avg(c_acctbal) as avg_acctbal
   from
     customer
    where
      (substr(c_phone, 1, 2) = '13' or
      substr(c_phone, 1, 2) = '31' or
      substr(c_phone, 1, 2) = '23' or
      substr(c_phone, 1, 2) = '29' or
      substr(c_phone, 1, 2) = '30' or
      substr(c_phone, 1, 2) = '18' or
      substr(c_phone, 1, 2) = '17')
      and c_acctbal > 0.00
  ) ct1
  where
   ct.c_acctbal > ct1.avg_acctbal
group by cntrycode
order by cntrycode;

