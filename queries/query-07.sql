/*7a*/
/*required skills by job category code ex: '15-1134'*/
Select job_category, title as requires, lvl as with_level
FROM (knowledge_skills NATURAL JOIN requirements ) NATURAL JOIN
    (
    /*positions with specified job category*/
    SELECT pos_code, title as job_category
    FROM job_position NATURAL JOIN job_category
    WHERE cate_code='15-1134'/*where cate_code is = to the given cate_code*/
    );


/*table job_position has the position id we want to pick
    so natural joining with requirements will give us the required skills. 
    then natural joining that with entity set knowledge skills will give the readable 
    format of the requirements of that job position*/
select pos_title,title as requires,lvl as with_level
from job_position natural join requirements natural join knowledge_skills
where pos_code ='521';

