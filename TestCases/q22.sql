--Q22
select
  cntrycode, count(1) as numcust, sum(c_acctbal) as totacctbal
from
  orders ot
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

