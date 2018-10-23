/*13. Given a person’s identifier, list all the job categories that a person is qualified for. */

SELECT cate_code
FROM (
    SELECT cate_code, COUNT(skill_code) as reqs
    FROM core_skills
    GROUP BY cate_code
    )
    NATURAL JOIN
    (
    SELECT cate_code, COUNT(skill_code) as has
    FROM (SELECT DISTINCT cate_code, skill_code
        FROM has_skill NATURAL JOIN skills_category NATURAL JOIN core_skills
        WHERE per_id=123)
    GROUP BY cate_code
    )
WHERE reqs=has