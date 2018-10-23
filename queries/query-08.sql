/*8. Given a person’s identifier, list a person’s missing 
knowledge/skills for a specific pos_code in a readable format.*/


SELECT ks_code, title
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
    )NATURAL JOIN knowledge_skills;