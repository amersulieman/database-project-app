/*23. Find out the total amount of salaries and wages paid to employees
Of the largest company.*/

WITH pay_totals as (
    SELECT comp_id, sal_total+wage_total as total
    FROM (
        SELECT comp_id, SUM (pay_rate) as sal_total
        FROM job_position NATURAL JOIN works
        WHERE pay_type='salary' AND end_date IS NULL
        GROUP BY comp_id 
        )
        NATURAL JOIN
        (
        SELECT comp_id, SUM (pay_rate*1920) as wage_total
        FROM job_position NATURAL JOIN works
        WHERE pay_type='wage' AND end_date IS NULL
        GROUP BY comp_id 
        )
)
SELECT *
FROM pay_totals
WHERE total = (SELECT MAX(total) FROM pay_totals);