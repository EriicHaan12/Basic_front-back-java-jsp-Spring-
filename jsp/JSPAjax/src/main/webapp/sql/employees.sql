-- 모든 직원 정보를 출력하는 쿼리문
select* from employees;
commit;

--모든 job 정보를 출력하는 쿼리문
select* from jobs;

select min_salary, max_salary
from jobs
where job_id = lower('IT_PROG');