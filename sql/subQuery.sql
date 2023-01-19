--서브 쿼리
--100번 사원이 소속되어 있는 부서의 부서명을 알아보자
--1. 단일행 서브 쿼리
--1)  100번 사원이 소속되어있는 부서의 부서번호를 알아내야 한다.

select department_id from employees
where employee_id = 100;

--2) 1번에서 알아낸 부서 번호(90번)의 부서 이름을 알아낸다.

select department_name from departments
where department_id =90;


--3) 1,2번 문장을 하나의 서브쿼리로 합치면 
--서브쿼리는 먼저 수행되어야하는 쿼리를 괄호로 감싸주고 최종적으로 결과값을 알아야 될 쿼리를 앞에다 써준다.
--형태)  메인쿼리 = (서브쿼리)
select department_name from departments
where department_id =
(select department_id from employees
where employee_id = 100);

-- Executive 부서의 국가코드, 주 , 시, 도로명 주소를 출력해보자.
select COUNTRY_ID , STATE_PROVINCE, city, STREET_ADDRESS
from LOCATIONS
where LOCATION_ID =(select LOCATION_ID from departments
where department_name='Executive');

--괄호 앞에 오는 where문의 조건이랑 괄호 첫번째오는 찾을값은 항상 같다.

--단일 행 서브 쿼리(single row subquey) : 내부 select 문장으로 부터 하나의 행만을 반환 받으며, 
-- 연산자로 =,>,<, >=, <= , !=, = 를 사용할 수 있다.

--예시) 'diana'와 같은 부서에 다니는 동료들의 모든 정보를 출력하세요.

select *
from employees
where department_id= (select department_id from employees WHERE
first_name ='Diana');


--예시 2) 사원들의 평균 급여보다 더 많은 급여를 받는 사원의 사번, 이름, 급여를 출력하세요.

select employee_id, first_name, salary
from employees
where salary > (select avg(salary) from employees)
order by salary DESC;

------------------------------------------------------
--2 . 다중 행 서브 쿼리(multiple row subquery) : 서브쿼리에서 반환되는 행의 갯수가 2개 이상 일 떄 사용하는 서브쿼리

-- 연산자로는 아래의 연산자를 사용한다.

-- in : 메인 쿼리의 비교조건이 서브쿼리의 결과 중에서 하나라도 일치하면 참 (~중에서 라고 해석하면 편함 )
-- all : 메인 쿼리의 비교조건이 서브쿼리의 결과와 모두 일치하면 참 (모든것의 합집합)
-- any : 메인 쿼리의 비교조건이 서브쿼리의 결과와 하나이상 일치하면 참 (모든 것의 교집합, and 라고 생각하면 편함 즉, 비교할 쿼리행의 모든 조건과 일치해야 참)


-- in과 all가 헷갈린다면 in은 쓰는 의미 자체로 같다(=)라고 해석하면 되어 같다를 대신해서 쓰지만, any는 비교연산자와 함께 사용된다.

--예시) 급여를 7000이상 받는 사원이 소속된 부서와 동일한 부서에서 근무하는 사원들의 정보 출력
--in 연산자 사용 예시
--1)   급여를 7000이상 받는 사원이 소속된 부서
select *from employees
where department_id  --여기까지는 사원들의 모든 부서 번호
 in( select distinct  department_id from employees
where salary >= 7000);-- 여기는 급여가 20000이상인 사람들의 부서번호

-- 즉 조건이 모두 일치하는게 아니라 하나만 일치 하는 것이기 때문에 in을 써줘야한다.


--all 연산자 사용 예시

-- 예시) 부서번호가 30번에 소속된 사원중에서 급여를 가장 많이 받는 사원보다 더 많이 받는 사원의 이름, 급여를 출력해보자.

select first_name, salary
from employees
where salary  > all(select salary from employees where department_id=30 ); -- 최대값이라고 해서 max를 쓰면 단일행이 되기때문에 그룹에 쓸 수 없게 된다.
--이때는 30번 부서의 사원들 전원의 급여를 뽑아 all을 써줌으로서 30번 부서사람들의 
--급여를 하나하나 비교해서 그 사람들 중 가장 급여를 많이 받는 사람보다 더 많이 받는 사람의 급여를 출력한다.


--참고로 db에 가지고 있는 데이터가 많아지게 되면 연산속도가 늦어지기 때문에 그룹함수를 잘 쓰지 않는다.


--예시2) 부서번호가 30번에 소속된 사원중에서 급여를 적게 많이 받는 사원보다 더 많이 받는 사원의 이름, 급여를 출력해보자.

select first_name, salary
from employees
where salary  > any
(select salary from employees where department_id=30 )
order by salary asc;


--서브쿼리를 이용하여 테이블 복사하기 (데이터만 복사, pk같은 제약조건은 복사 되지 않는다.)

create table copyEmp 
as select* from employees;


create table copyEmp2
as select employee_id, first_name, salary 
from employees
where department_id =30;


create table copyEmp3 
as select* from employees
where 1 =0;
--where 조건절을 false 값을 줘서 table을 copy하게 되면 table의 구조만 복사되게 된다.


-- table 삭제 하는 방법
drop table copyemp;
drop table copyem2;
drop table copyemp3;


----------------------------------------------------------------






--사원들이 소속되어 있는 부서의 부서명을 알아보자. 사번, 이름, 소속부서명을 출력해보자.

select employee_id, first_name, department_name
from departments
where department_id = (select department_id from employees);
-- 위 문장은 에러가 뜬다. 서브쿼리는 메인쿼리문 테이블에 있는 컬럼 정보 밖에 얻지 못한다.
-- 위의 문제를 해결하기 위해서는 join으로 해결해야한다.

--사원들이 소속되어 있는 부서의 부서명을 알아보자. 사번, 이름, 소속부서명을 출력해보자(join)
select employee_id, first_name, department_name
from employees, departments
where employees.department_id = departments.department_id;





-------------------------
