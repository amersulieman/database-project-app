/*21. In a local or national crisis, we need to find all the people who once held a job position of the special job category identifier. 
List per_id, name, job position title and the years the person worked (starting year and ending year). */

/*get all the job positions offered by a specific job_category*/
with cate_pos as (select pos_code,pos_title
    from job_position 
    where cate_code ='15-1152'),
/*Gets all the people that worked before and dont work any more for those
positions picked in the previous select*/   
pos_held as(select per_id,first_name,pos_code,pos_title,start_date,end_date
      from cate_pos natural join works natural join person
      where end_date is not null)
select * 
from pos_held;

