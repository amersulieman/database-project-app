/* 16. When a company cannot find any qualified person for a job position, a secondary solution 
is to find a person who is almost qualified to the job position. Make a "missing-one" list 
that lists people who miss only one skill for a specified pos_code.*/

with missing_one AS(
    select per_id,count (ks_code) as missing
       from(
           (select per_id,ks_code
            from person,requirements
             where pos_code ='521')
          minus
            (select per_id,ks_code
             from has_skill)
           )
      group by per_id)
      
select per_id
from missing_one
where missing =1;
    

    
