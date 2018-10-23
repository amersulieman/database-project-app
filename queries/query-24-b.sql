/*24. 1) Find out the job distribution among business sectors; 2) find out the biggest sector 
in terms of number of employees and the total amount of salaries and wages paid to employees. (Two queries) */

/*Biggest Sector */
WITH pay_distribution as (
    SELECT sec_id, sal+wage as total
    FROM
    (SELECT sec_id, SUM(pay_rate) as sal
        FROM comp_sectors NATURAL JOIN job_position
        WHERE pay_type='salary'
        GROUP BY sec_id
    )
    NATURAL JOIN
    (   SELECT sec_id, SUM(pay_rate*1920) as wage
        FROM comp_sectors NATURAL JOIN job_position
        WHERE pay_type='wage'
        GROUP BY sec_id
    )
)

SELECT sec_id, total
FROM pay_distribution
WHERE total = (SELECT MAX(total) FROM pay_distribution);

