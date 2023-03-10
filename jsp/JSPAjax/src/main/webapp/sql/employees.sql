-- 모든 직원 정보를 출력하는 쿼리문
select* from employees where quit_date is not null;
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
'IT_PROG',9000,0.4,100);

----------------------------------------------
-- ?번 사원의 이름을 수정 할 때
update employees set first_name=? where employee_id=?;
--? 번 사원의 성을 수정할 때
update employees set last_name=? where employee_id=?;
-- 이름과 성 수정
update employees set first_name=?, last_name=? where employee_id=?;
--? 번 사원의 이메일 전화 번호 수정
update employees set email=?, phone_number=? where employee_id=?;

-- update문을 수행 할 때, 유저가 어떤 값을 수정 할지 모른다. 그래서 다양한 경우의 쿼리문을 모두 만들 수 없다.

-- 해법
-- 1) 수정할 사원 정보를 데이터 입력 UI에 바인딩 시켜 준 후, 
select * from employees where employee_id = ?;

--2) 모든 컬럼의 값을 update 시켜준다.(이렇게 짜주면 쿼리문이 하나만 나오게 된다.)
update employees set first_name=?,last_name=?, email=?,phone_number=?,hire_date=?,job_id=?,salary=?,
commission_pct=?,manager_id=?,department_id=?
where employee_id=?;

---------------------------
-- 삭제 작업
-- 1) 실제 delete 문을 사용해야 하는 경우
-- 삭제하기 전에 삭제 될 사원을 다른 테이블(ex oldEmp)에 옮겨야 함
-- 이 후 delete 수행
create table oldEmp 
as
select * from employees
-- employees 테이블 껍데기 전체 복사
where 1 =0;
select * from oldEmp;




-- 2) 실제 delete 문을 사용하지 않는 경우
-- 해당 테이블에  삭제여부컬럼(ex. isDelete)을 만든 후 삭제 되는 데이터에 대해 update 수행
-- 데이터 검색시 안보이도록 처리

alter table employees 
add quit_date date  DEFAULT null; 

select * from employees;


-- 넣어줄 쿼리문
update employees set quit_date = ? where employee_id =?;