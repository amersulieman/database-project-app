SELECT DISTINCT first_name, last_name
FROM  person NATURAL JOIN works NATURAL JOIN job_position 
WHERE comp_id='1943' AND end_date IS NULL;