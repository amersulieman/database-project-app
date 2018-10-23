/*People with their salaries who held jobs before and stopped working there*/
with old_jobs AS(
                select per_id,pos_code,pay_rate as old_income
                from works S natural join job_position
                where end_date= (select max(end_date)
                                 from works T
                                 where S.per_id = T.per_id)
                ),
/*Id's of those people who held those jobs before*/
people as (
                select per_id
                from old_jobs
              ),
/*The salary sum of the current jobs each person hold*/
sum_current As(
                select per_id,sum(pay_rate)AS current_pay/*as current_income*/
                from works natural join people natural join job_position
                where end_date is null
                group by per_id),
/*Gets the difference between their current income and previous income*/
income_difference As(
        select (current_pay - old_income) as pay_difference,count (per_id) as pay_increase
        from sum_current natural join old_jobs
        group by current_pay - old_income)
/*If the difference between current and last salry is positive , then they had a pay increase*/
select sum(pay_increase) as num_increased
from income_difference
where pay_difference >0;