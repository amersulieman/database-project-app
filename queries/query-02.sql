/*Query 2*/
SELECT per_id, pay_rate
FROM job_position NATURAL JOIN works
WHERE comp_id='1943' AND pay_type='salary' AND end_date IS NUll
ORDER BY pay_rate DESC;