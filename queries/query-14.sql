/*14. Given a person’s identifier, find the job position with the highest 
pay rate for this person according to his/her skill possession. */
WITH qualified_positions as (
    SELECT pos_code
    FROM 
        (
            /*requirements of all job positions*/
            SELECT pos_code, count(ks_code) as reqs
            FROM requirements
            GROUP BY pos_code
        )
        NATURAL JOIN
        (
            /*the skills a person have that are requirements of a job position*/
            SELECT pos_code, count(ks_code) as has
            FROM has_skill NATURAL JOIN requirements
            WHERE per_id=123
            GROUP BY pos_code
        )
    WHERE reqs = has
),
converted_salaries as (
    SELECT pos_code, pay_rate
    FROM qualified_positions NATURAL JOIN job_position
    WHERE pay_type='salary'
    UNION
    SELECT pos_code, pay_rate*1920
    FROM qualified_positions NATURAL JOIN job_position
    WHERE pay_type='wage'
)
SELECT *
FROM converted_salaries
WHERE pay_rate = (SELECT MAX(pay_rate) FROM converted_salaries);