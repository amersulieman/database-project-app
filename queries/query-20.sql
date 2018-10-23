/*20. Given a position code and its corresponding missing-k list specified in Question 19.
Find every skill that is needed by at least one person in the given missing-k list. List
each skill code and the number of people who need it in the descending order of the people counts.*/

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
        group by per_id),
 missing_k AS(
        select per_id,missing
        from missing_skills
        where missing <=5/*Kth number*/
            )
select ks_code,count(per_id) AS people
   from (
        /*missing_k table cartesion with requirements*/
          (select per_id,ks_code
          from missing_k,requirements 
          where pos_code ='321')
        minus 
          (select per_id,ks_code
          from has_skill)
        )
 group by ks_code
 order by people desc;
    
