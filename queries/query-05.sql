/*5. Given a person’s identifier, list this person’s knowledge/skills in a readable format. */
/*Person's skills, by person identifier*/
SELECT ks_code, title AS skill, lvl AS experience_level
FROM has_skill NATURAL JOIN knowledge_skills
WHERE per_id=123; /*Where 123 is the identifier of the person.*/
