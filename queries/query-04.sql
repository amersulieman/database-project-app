/*4. Given a person’s identifier, find all the job positions this person is currently holding and worked in the past. */

/*person's positions*/
SELECT pos_code
FROM  works
WHERE per_id=123; /*where 123 equals the given persons identifier*/