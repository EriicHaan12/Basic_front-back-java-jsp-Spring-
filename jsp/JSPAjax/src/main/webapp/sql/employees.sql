-- 모든 직원 정보를 출력하는 쿼리문
select* from employees;
commit;

--모든 job 정보를 출력하는 쿼리문
select* from jobs;

select min_salary, max_salary
from jobs
where job_id = 'IT_PROG';


-- 모든 부서 정보를 출력하는 쿼리문 
select * from departments order by department_id asc;

-- 모든 직원 정보 + 직원이 근무하는 부서명 까지 출력
select e.*,d.department_name
from employees e inner join departments d
on e.department_id = d.department_id;

-- 사원을 저장 하는 쿼리문

-- 1. 사번은 1씩 증가한 값을 넣어야 하기 때문에 다음 저장될 사원의 사번을 얻어온다.
select max(employees_id)+1 from employees;

-- 2. 1번 과정에서 얻어온 사번과 함께 유저가 입력한 데이터를 insert
insert into employees
values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);

-- 저장 프로시저 생성
-- PROC_INSERTFRIEND 참조

-- 이 후 만들어진 저장 프로시져 호출 하는 방법(오라클에서 호출 하는 방법)
exec proc_insertfriend(10,'프리져','010-0101-0111','잘되가니?');

-- 서버에서 출력되는 메세지를 보겠다는 명령어
set serveroutput on;

-- 기존에 했던 방식이 아닌 friendno를 직접 넣어주지 않고 프로시저에 작성한 실행문으로 자동 생성(최대값+1)해서 넣는 방식으로 데이터 insert
exec proc_insertfriend('고둘기','010-0101-0123','잘되겠지?');



exec proc_saveemp('hong','chalchal','aa@na.com','010-1478-5548','21/06/23',
'IT_PROG',9000,0.4,100, );


