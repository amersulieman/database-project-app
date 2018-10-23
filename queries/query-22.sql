/*22. Find all the unemployed people who once held a job position of the given pos_code. */

(
    SELECT distinct per_id
    FROM works
    WHERE end_date IS NOT NULL AND pos_code='931'
)
MINUS
(
    SELECT distinct per_id
    FROM WORKS
    WHERE end_date IS NULL
);