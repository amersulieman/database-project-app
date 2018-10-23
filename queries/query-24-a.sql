/*24. 1) Find out the job distribution among business sectors; 2) find out the biggest sector 
in terms of number of employees and the total amount of salaries and wages paid to employees. (Two queries) */

/*Biggest Sector */
WITH job_distribution as (
    SELECT sec_id, COUNT(pos_code) as num_positions
    FROM comp_sectors NATURAL JOIN (SELECT pos_code, comp_id FROM job_position)
    GROUP By sec_id
)

SELECT sec_id, num_positions as num_employees
FROM job_distribution
WHERE num_positions = (SELECT MAX(num_positions) FROM job_distribution);
