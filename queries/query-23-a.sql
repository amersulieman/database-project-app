/*23-a. Find out the biggest employer in terms of number of employees.*/

WITH emp_count as (
    SELECT comp_id, count(pos_code) as emp_num
    FROM job_position NATURAL JOIN works
    WHERE end_date IS NULL
    GROUP BY comp_id
)
SELECT comp_id
FROM emp_count
WHERE emp_num = (select max(emp_num) FROM emp_count);