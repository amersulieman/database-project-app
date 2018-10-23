/*People with their salaries who held jobs before and stopped working there*/
with old_jobs AS(
                select per_id,pos_code,pay_rate as old_income
                from works S natural join job_position natural join company
                where sec_id= '541511-04' and  end_date= (select max(end_date)
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
                group by per_id)
/*Gets the difference between their current income and previous income*/
select avg(current_pay - old_income) as verage_of_change
        from sum_current natural join old_jobs;

        
        

