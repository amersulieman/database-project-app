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
        select (current_pay - old_income) as pay_difference,count (per_id) as pay
        from sum_current natural join old_jobs
        group by current_pay - old_income),      
/*If the difference between current and last salary is negative , then they had a pay decrease*/
pay_decrease As(
    select sum(pay) as pay_decrease
    from income_difference
    where pay_difference <0),
pay_increase AS(    
    select sum(pay) as pay_increase
    from income_difference
    where pay_difference >0)
select pay_decrease, pay_increase 
from pay_decrease,pay_increase;

