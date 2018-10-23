/*10. Suppose the skill gap of a worker and the requirement of a desired job position can be covered by one course. 
Find the “quickest” solution for this worker. Show the course, section information and the completion date. */

/*Temp table that gets the missing skills for specifc position and person id*/
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
    )),
/*another temp table that gets the course(s) that are active and teach all missing skills */
right_course as( SELECT c_code,title
                FROM missing NATURAL JOIN teaches NATURAL JOIN course
                where status ='active'
                GROUP BY c_code,title
                HAVING COUNT(ks_code) = (select count(ks_code)
                                        from missing))
/*return the course with the earliest complete date for quickest solution*/                                    
select c_code,title,sec_no,complete_date,offered_by,format,price
from section natural join right_course 
where complete_date = (select min(complete_date)
                        from right_course natural join section);
                        







