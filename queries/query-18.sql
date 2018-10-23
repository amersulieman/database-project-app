/*18. Suppose there is a new position that has nobody qualified. List the persons
who miss the least number of skills that are required by this pos_code and report the “least number”. */

with missing_skills AS(
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
from missing_skills
where missing = (select min (missing)
                    from missing_skills);
    

