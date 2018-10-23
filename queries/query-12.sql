/*query 12. If query #9 returns nothing, then find the course sets that their combination covers all the missing 
knowledge/ skills for a person to pursue a pos_code. The considered course sets will not include more than three courses. 
If multiple course sets are found, list the course sets (with their course IDs) in the order of the ascending order of 
the course sets’ total costs. */

/*Course set= {course1, course2, course 3} */

with ta as (
    SELECT *
    FROM teaches NATURAL JOIN (
        (
            /*required skills*/
            SELECT ks_code
            FROM requirements
            WHERE pos_code='721'
        )
        minus
        (
            /*has_skills*/
            SELECT ks_code
            FROM has_skill
            WHERE per_id = 924
        ))
)

Select *
FROM ta ta1, ta ta2
WHERE ta1.ks_code != ta2.ks_code;
