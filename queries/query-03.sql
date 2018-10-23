/*companies' labor costs*/
select comp_name, sum(salary) as salary
from (
    /*Company Labor Costs*/
        (
        /*current Wage positions*/
        SELECT comp_name, pay_rate*1920 as salary
        FROM (company NATURAL JOIN job_position) NATURAL JOIN works
        WHERE pay_type='wage' AND end_date IS NULL
        ) 
        UNION 
        (
        /*current salary positions*/
        SELECT comp_name, pay_rate as salary
        FROM (company NATURAL JOIN job_position) NATURAL JOIN works
        WHERE pay_type='salary' AND end_date IS NULL
        )
)
group by comp_name
order by salary desc;