/* 9. Given a person’s identifier and a pos_code, list the courses (course id and title) that 
each alone teaches all the missing knowledge/skills for this person to pursue the specific job position. */

    
WITH missing AS (
SELECT ks_code
    FROM
    (
        (
        /*Positions required skills*/
        SELECT ks_code
        FROM requirements
        WHERE pos_code='321'
        ) 
        MINUS
        (
        /*Person's skills, by person identifier*/
        SELECT ks_code
        FROM has_skill
        WHERE per_id=123
        ) 
    ))
SELECT c_code,title
FROM missing NATURAL JOIN teaches NATURAL JOIN course
where status='active'
GROUP BY c_code,title
HAVING COUNT(ks_code) = (select count(ks_code)
                         from missing);


    
    