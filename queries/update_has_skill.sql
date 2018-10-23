/*update has skill of given person id*/

INSERT INTO has_skill (per_id, ks_code)
/*skills person learned*/
SELECT per_id, ks_code
FROM takes NATURAL JOIN teaches
WHERE per_id=998
MINUS
/*Skills he has*/
SELECT per_id, ks_code
FROM has_skill
WHERE per_id=998;
