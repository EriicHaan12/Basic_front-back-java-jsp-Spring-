----------------view
--- 데이터를 조회하기 위함 객체
select *from emp_details_view
where department_id = 90 order by salary DESC;


SELECT
  e.employee_id,
  e.job_id,
  e.manager_id,
  e.department_id,
  d.location_id,
  l.country_id,
  e.first_name,
  e.last_name,
  e.salary,
  e.commission_pct,
  d.department_name,
  j.job_title,
  l.city,
  l.state_province,
  c.country_name,
  r.region_name
FROM
  employees e,
  departments d,
  jobs j,
  locations l,
  countries c,
  regions r
WHERE e.department_id = d.department_id
  AND d.location_id = l.location_id
  AND l.country_id = c.country_id
  AND c.region_id = r.region_id
  AND j.job_id = e.job_id;
  
  
  ---------
  -- view 생성
  
  --부서번호가 30번으로 소속된 모든 직원의 정보를 담고 있는 view생성
  create or replace view view_emp30
  as 
  select *from employees where department_id =30;
  
  select* from view_emp30;
  
  insert into view_emp30 values(
  300,'길동' ,'고','kildong','555.555.5555',sysdate,'PU_CLECK',3000,null,100,30
  );
  
  delete from view_emp30 where employee_id=300;
  
  --------------------------------
  --with check option으로 뷰 생성하기
  
  create or replace view view_emp90
  as
  select employee_id, first_name,last_name,email,job_id, hire_date, department_id
  from employees
  where department_id = 90
  with check option;
  -- 제약조건 not null 떄문에, last_name,email,job_id 을 넣어줘야함
  select *from view_emp90;
  
  insert into view_emp90 values(500,'길동','고','a@a.com','AD_VP',sysdate,90);
  
  --view에 해당컬럼이 없다는 에러기 뜬다. with check option 떄문에
  update view_emp90 set salary = 9000 where employee_id =500;
  --실제로는 salary데이터가 존재하지만, 
  --view로 만든 테이블상에는  salary컬럼이 존재하지 않기 떄문에   witch ckeck option으로 인해 수정 권한을 가질 수 없게 된다.
  
  --view 테이블 상에 존재하는 컬럼에 대해서는 수정이 가능하다.
  update view_emp90 set hire_date ='05/12/31' where employee_id=500;
  
  
  
   create or replace view view_emp90
  as
  select employee_id, first_name,last_name,email,job_id, hire_date, department_id
  from employees
  where department_id = 90
  with read only;
  
  select* from view_emp90;
  
  
  --ORA-42399: cannot perform a DML operation on a read-only view
  delete from view_emp90 
  where employee_id =500;
  --read only로 인해 읽기 권한 밖에 없다.
  
  
  select * from employees;
 
 
 delete from employees where employee_id = 500; 
 ----------------------------------------------------
 
 --force 옵션으로 view 생성
 
 drop table board;
 drop table member;
 drop table emp01;
 drop table dept01;
 
 
 
 create or replace force  view view_member
 as
 select * from member;
 
 -- 테이블이 없는데 강제적으로 만들었기 때문에, 생성은 되긴한데 에러가 뜬다.
 select * from view_member;
 
 
 create table member(
 userId varchar2(10)
 );
 -- 강제적으로 view를 만들고 이후 테이블을 만들게 되면
 -- 정상적으로 view가 보여지게 된다.
 
  select * from view_member;
 
  
  ---------------------------------------------------
  --만약 view에 컬럼 별칭을 붙여 생성하게 된다면, 해당 뷰에 대해서는 컬럼 별칭으로 조건절을 만들어야 한다.
  create or replace view  view_emp(사번, 이름, 급여, 부서번호)
  as
  select employee_id, first_name,salary, department_id
  FROM employees;
  
  
  --지정해준대로 where 절을 써야 된다.
  select * from view_emp where 부서번호 =60;
  
  
  ------------------------------------------------------------------
 --그룹함수를 이용해서 view를 생성할 때에는 반드시 그룹함수를 지정한 컬럼은 컬럼 별칭을 주어야 한다.
 
  create or replace view view_sal
  as
  select department_id, sum(salary)급여총액 ,avg(salary) 급여평균
  from employees
  group by department_id;
  
  select * from view_sal;
  
  --그룹 함수를 이용한 view는 DML(insert/ update, delete) 사용 불가
  delete from view_sal where department_id = 30;
  
  
  --------------------------------------------
  --조인을 통해 여러개의 테이블 결과를 뷰로 생성한 경우
  --insert 작업은 뷰를 통해서 할 수 없다...
  --나머지 DML은 제약조건만 잘 지킨다면 가능하다.
  create or replace view view_emp_dept
  as
  select e.employee_id, e.first_name, e.department_id,d.department_name
  from employees e inner join departments d
  on e.department_id=d.department_id;
  
  select* from view_emp_dept;
  
  update view_emp_dept set first_name ='제니퍼' where employee_id =200;
  
  
  delete from view_emp_dept where employee_id =200;
  
  -------------------------------------
  
  --뷰 삭제
  
  drop view view_emp;
  drop view view_emp_dept;
  drop view view_emp30;
  drop view view_emp90;
  drop view view_member;
  drop view view_sal;
  
  
  --------------------------------
  --view , top-N 쿼리 만들기
  --급여가 높은순으로 5명만 출력해보자.

--1) 급여 내림차순으로 정렬 하여 출력
select employee_id, first_name, salary
from employees
order by salary desc;

--2) 1번의 쿼리문에 rownum이라는 컬럼을 이용해서 출력
--rownum컬럼 : insert문에 의해 입력된 순서에 따라 1씩 증가되는 내부의 값

-- 쉽게 이해 하자면 JSP에서 증감문을 만들기 위해 num을 만들고 num++ 해줘서 순서를 만들어 준 것과 비슷...

select rownum, e.* 
from employees e;


select rownum, employee_id,first_name, salary
from employees
order by salary desc;

--쿼리문 처리 순서 : where, from > select 즉, 모든 조건절은 다 처리하고 마지막에 select를 처리함.

--3) 2번 쿼리문으로 뷰 생성하기(rownum 빼고)

create or replace view view_sal_top5
as
select employee_id,first_name, salary
from employees
order by salary desc;

--4) 3번의 뷰에 rownum을 붙여 출력하고 5명만 출력 할 수 있다.
--뷰에 담긴 순서= rownum 
select rownum, e.* from view_sal_top5 e where rownum<6;


--위의 문제를 서브쿼리로도 풀 수 있다.
 select rownum, e.* from ( select employee_id,first_name, salary
from employees
order by salary desc) e
  where rownum <6;
  
  --ex) 입사일이 늦은 순 7명 출력
  create or replace view view_hiredate
  as
  select first_name, hire_date
  from employees
  order by hire_date desc;
  
  select rownum , h.*
  from view_hiredate h
  where rownum <8;
  
  --개인적으로는 일반 쿼리를 민들고 서브쿼리를 이용해서 조건절만 바꾸는게 제일 만들기 쉬운 것 같다.
  
  select first_name, hire_date
  from employees
  order by hire_date desc;
  
  select rownum, e.*
  from (  select first_name, hire_date
  from employees
  order by hire_date desc) e
  where rownum <8;
  
  commit;
  -------------------------------
  insert into member values('abcd');
  select * from member;
  
  savepoint after_insert; --세이프포인트 지정
  
  update member set userid='bbbb'where userid ='abcd';
  
  rollback to after_insert;-- 이렇게 롤백 시점을 정해둔 savepoint 로 지정해준다면
  -- 그 시점 까지만 rollback 된다.
  
  select * from member; 
  
  rollback;
  select * from member; 
  --그냥 rollback 작업이 수행되어 savepoint를 정해주더라도 그 이전까지 rollback 된다.
  
  
  
  
  ---------------------------
  --다음의 쿼리문이 순서대로 실행 되었다면, db에 영구 반영되는 문장은?
  --1.insert문-- 
  --2.savepoint a 수행;--
  --3. delete 문
  --4. savepoint b 수행;  
  --5. update문 수행  
  --6. rollback to a 수행;
  --7.insert문 수행 --
  --8.savepoint c 수행;
  --9.delete문 수행;
  --10.commit ;
  --1, 7, 9 번이 db에 남게 되고 수행된다.