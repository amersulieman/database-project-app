/*17. List each of the skill code and the number of people who misses the skill and are
in the missing-one list for a given position code in the ascending order of the people counts. */
 with missing_skills AS(
            select per_id,count (ks_code) as missing
            from(
               (select per_id,ks_code
                from person,requirements
                where pos_code ='521')
              minus
                (select per_id,ks_code
                 from has_skill)
                )
            group by per_id),
   missing_one As(
           select per_id
           from missing_skills
           where missing =1)
                        
select ks_code,count(per_id) AS people
   from (
        /*missing one table cartesion with requirements*/
            (select per_id,ks_code
             from missing_one,requirements 
             where pos_code ='521')
          minus 
             (select per_id,ks_code
              from has_skill)
         )
 group by ks_code
 order by people asc;
    
        
