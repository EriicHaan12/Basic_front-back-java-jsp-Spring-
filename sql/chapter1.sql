-- SELECT문으로 특정 데이터 추출하기
-- select문의 기본 문법
-- select *  (모두 조회하기) from 조회할 테이블 명
-- select 조회할 컬럼명1, 조회할 컬럼명2, 조회할 컬럼명 3, ...
-- 이후 from 테이블 명;(조회할 테이블명을 지정해주면 된다) 

--countries 테이블의 모든 컬럼 조회
select * from countries;

--부서 테이블의 모든 컬럼 조회
select *from departments;

-- 사원 테이블 의 모든 컬럼 조회
select * from employees;

--사원 테이블에서 사원 이름만 조회 하기
select first_name from employees;


--부서 테이블에서 부서명만 조회하자.
select department_name from departments;

-- 지역테이블에서 도로명 주소만 조회하자
select street_address from locations;

--사원 테이블에서 사원명과 급여를 조회하자
select first_name,salary from employees;

-- 사원 테이블에서 사번, 이름 , 입사일을 조회하기

select employee_id, first_name, hire_date from employees;

--부서 테이블에서 부서명, 부서번호를 조회하자
select department_name, department_id from departments;

--컬럼명에 별칭을 지을 수 있다.
--1) 컬럼명에 별칭을 지을려면 컬럼을 기술한 뒤 컬럼명 뒤에 as라는 키워드를 쓴다.
select first_name as name from employees;

select employee_id as 사번 , first_name as 이름 from employees;

-- 2)as라는 키워드를 생략해도 된다.
select first_name 이름 from employees;

-- 3)별칭에 공백이나 특수문자를 포함하는 경우에는 별칭을 "별칭"(큰따옴표)로 묶는다.
select first_name "비밀 임★" from employees;

--4)dlstinct 키워드는 중복된 데이터를 한번씩만 출력하게 한다.
select job_id from employees;

select DISTINCT job_id from employees; --job_id 의 중복된 행을 한번씩만 출력

-- 한 컬럼에 중복된것이 있고 나머지 컬럼에 중복된 것이 없다면, 그런데 그 테이블을
-- distinct를 써서 같이 조회 하게 된다면, 제대로된 데이터가 안나올 확률이 높다. 
SELECT DISTINCT job_id, first_name from employees;


-- 5)where 절을 사용하여 행 검색 할 떄 조건을 부여 할 수 있다.
select * -- select 절
from employees-- from 절
where employee_id>=100; --where 절

-- 해석) 사원 테이블에 모든 테이블을 찍되, 사원 아이디가 100번 이상인 사람들만 조회

--1.where절에 조건식을 사용할 수 있다.
--2. 조건식에 사용되는 연산자(<,>,>=,<=,!=(not equal),=(equal) 등이 있다) 

--사원 테이블에서 이름이 Adam 인 사원의 사번, 이름, 입사일을 조회해보자
select employee_id, first_name, hire_date from employees where first_name='Adam';
--sql에서 문자열이나 날짜를 표현할 떄는 ''(작은 따옴표)를 이용한다.
--문자열은 참고로 대소문자를 구문한다, 나머지 sql문들을 대소문자 구분없이 쓸 수 있다.


--사원 테이블에서 급여가 5000이상인 사원들의 사번, 이름 , 급여를 조회하자.
select employee_id, first_name, salary
from employees
where salary >= 5000;


--지역 테이블에서 지역 번호가 1800번 이하인 지역의 모든 컬럼을 조회해보자
select *
from locations
where location_id<=1800;


--사원 테이블에서 입사일이 2002년 이전에 입사한 사원들의 사번, 이름, 급여, 입사일을 출력하자
select employee_id, first_name, salary, hire_date
from employees
where hire_date<'02/01/01';
-- 날짜 데이터는 연산될때 알아서 timestamp가 되어 계산된다.

--3. 조건연산자를 연결할 떄 논리연산자(and, or, not)를 사용할 수 있다.
--사번이 130번보다 작거나 급여가 5000보다 큰 사원들의 사번, 급여를 출력하자.

select employee_id, salary
from employees
where employee_id <130 or salary >5000;


--급여가 5000이상이고 부서번호가 30번보다 작은 사원들의 사번,급여, 부서번호를 출력해보자

select employee_id, salary, department_id
from employees
where salary>=5000 and department_id<30;


--부서번호가 100번이 아닌 모든 사원들의 모든 컬럼을 조회하자 (not)
-- not iqual != 대신 <> , ^= 로 대체해서 쓸 수 있다.
select *
from employees
where department_id != 100;


--4. between A and B 연산자 : A이상이고, B이하 라는 의미를 가짐
-- 급여가 5000이상이고 7000이하인 사원들의 이름, 급여를 출력해보자

select first_name, salary
from employees
where salary>=5000 and salary<=7000;


select first_name, salary
from employees
where salary BETWEEN 5000 and 7000;

--입사년도가 2003년~2005년 인 사람들의 모든 정보를 조회하자
SELECT *
from employees
where hire_date BETWEEN '03/01/01' and '05/12/31';

-- 5.In(A, B, C ...) : A 또는 B 또는 C ...
-- 부서번호가 10번 또는 50번 또는 100번인  사원들의 모든 정보를 출력하자.
select *
from employees
where department_id =10 or department_id =50 or department_id=100;


select *
from employees
where department_id in(10,50,100);

--6) 패턴을 이용하여 검색하는 like 연산자

--1. 컬럼명 like 패턴
--2. 패턴은 아래의 2가지를 이용할 수 있다.

-- '%'(나머지 연산자) : 문자가 없거나 하나이상의 문자가 어떤 값이 오든 상관 없다.
-- '_' (언더바): 하나의 문자가 어떤 값이 오든 상관 없다. 

--이름이 s로 시작하는 모든 사원들의 정보를 출력하자

select *
from employees
WHERE first_name LIKE'S%';
--첫글자가 S이며 나머지는 어떤 글자가 오든 상관없다(%)


--이름이 n으로 끝나는 모든 사원들의 정보 출력
select *
from employees
where first_name like'%n';

--직무가 AN으로 끝나는 모든 사원들의 정보를 출력하자
select *
from employees
where job_id like'%AN';

--이름의 끝에서 2번째 글자가 a인 사원들의 모든 정보를 출력해보자
select *
from employees
where first_name like '%a_';


--이름의 3번째 글자가 r인 사원들의 모든 정보를 출력
select *
from employees
where first_name like '__r%';

--직무에 _가 포함된 사원들의 모든 정보 출력
select *
from employees
where job_id like '%_%';

-- 7)null을 위한 연산자
--커미션을 받는 모든 사원들의 모든 정보를 출력

select *
from employees
WHERE commission_pct !=null; -- 이렇게 쓰면 안된다.


--is null; (null과 같다), is not null(null과 같지 않다.)

select *
from employees
WHERE commission_pct is not null;

-- 8) 정렬을 하기 위해서는 order by 절을 사용한다.

--order by 컬럼명[정렬기준];
--정렬기준은 오름차순(ASC), 내림차순(DESC)를 사용하되, 오름차순일 경우 ASC키워드 생략 가능하다.

--모든 사원들의 정보를 출력하되, 급여 내림차순으로 정렬하여 출력하자.
select *
from employees
ORDER by salary DESC;

--정렬은,(콤마) 로 구분하여 여러개를 사용 할 수 있다.
--부서번호가 10번인 사원들의 모든 정보를 출력하되, 급여 오름차순으로 정렬하고, 급여가 같을 경우 이름 내림차순으로 정렬하자.

select *
from employees
WHERE department_id =30
ORDER BY salary, first_name DESC;



 