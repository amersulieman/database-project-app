/*query 06*/

(SELECT ks_code 
 FROM works NATURAL JOIN requirements 
 WHERE per_id=131 AND end_date IS NULL
 )
MINUS
/*Person's skills, by person identifier*/
(SELECT ks_code
 FROM has_skill 
 WHERE per_id=131/*Where 123 is the identifier of the person.*/
);
