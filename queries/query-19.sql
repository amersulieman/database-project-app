/*19. For a specified position code and a given small number k, make a “missing-k” list that
lists the people’s IDs and the number of missing skills for the people who miss only up to k
skills in the ascending order of missing skills. */

with missing_k AS(
    select per_id,count (ks_code) as missing
        from(
                (select per_id,ks_code
                 from person,requirements
                 where pos_code ='321')
              minus
                 (select per_id,ks_code
                  from has_skill)
            )
        group by per_id)
select per_id,missing
from missing_k
where missing <=5/*Kth number*/
order by missing asc;
    
