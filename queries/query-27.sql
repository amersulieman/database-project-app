with jobless_people AS(
    /*jobless people who never worked*/
    (select per_id
     from person)
     minus
     (select per_id
      from works
      where end_date is null)
    ),
leaf_nodes As(
        (select cate_code
         from job_category)
        minus
        (select parent_cate
        from job_category)),
cate_open_positions As(  
    select cate_code,pos_code
         from (
                (select pos_code
                from job_position)
                minus
                  (select pos_code
                    from works
                    where end_date IS null)
                )natural join job_position natural join leaf_nodes),
open_positions_requirements As(
      select pos_code,ks_code
      from cate_open_positions natural join requirements),

jobless_people_skills As (
        select *
        from jobless_people natural join has_skill),
    
pos_qualified_people_count As(
        select distinct per_id,pos_code, count(ks_code) As counting
        from jobless_people_skills natural join open_positions_requirements
        group by per_id,pos_code),
number_of_req_of_each_position AS(
        select pos_code,count(ks_code) As counting
        from open_positions_requirements 
        group by pos_code),
num_spots_per_category AS(
        select cate_code,count(pos_code) as spots
        from cate_open_positions
        group by cate_code),
num_qualified_per_cate AS(        
        select cate_code,count(per_id) as counts
        from(
                select *
                from(
                    select distinct per_id,T.pos_code
                    from pos_qualified_people_count T,number_of_req_of_each_position S
                    where T.pos_code = S.pos_code and T.counting = S.counting)natural join cate_open_positions
            )
            group by cate_code
    ),
category_ As(
    select cate_code
        from num_spots_per_category natural join num_qualified_per_cate
        where spots - counts =(select max(spots-counts)
                              from num_spots_per_category natural join num_qualified_per_cate)),
skills_needed As(/**Skills needed to be taught to get people jobs */
    select distinct ks_code
    from category_ natural join cate_open_positions natural join open_positions_requirements)
/*All courses that teach these skills*/
select distinct c_code
from teaches natural join skills_needed;
        
    
    