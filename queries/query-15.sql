/*Query 15*/

/*Using division getting all the reuiremnets of a position
divided by the skills a person have. If everything gets matched up, 
Then that person is qualified*/
with qualified_per_id as(select per_id
from person  T
      where not exists ((select ks_code
       from requirements 
        where pos_code='721')
    minus
      (select ks_code
       from has_skill S
       where T.per_id=S.per_id)
       ))/*End of table that get per_id of qualified person*/

/*Natural join with person to get the email and name*/       
select first_name,last_name,email
    from qualified_per_id natural join person;
        
      