create database spring_resource_api;

use spring_resource_api;

select * from job;
select * from temp;

update job j set j.temp_id = 2 where j.id = 2;

select * from temp t inner join job j on t.id = j.temp_id;
select * from job j where '2022-10-22' between j.start_date and '2022-10-31';