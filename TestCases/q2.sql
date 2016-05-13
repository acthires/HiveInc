set mapred.min.split.size=536870912;
set hive.exec.reducers.bytes.per.reducer=1225000000;
set hive.auto.convert.join=false;

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
  join partsupp ps
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
   join partsupp ps
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

