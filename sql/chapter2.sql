--dual 테이블
--여러 목적을 위해 만들어둔 가상의 테이블
select 5+3 from dual;


--lower() : 소문자로 변환
select 'DataBase', lower('Database') from dual;

--이름이 lex인 사원의 모든 정보 출력
select *from employees where lower (first_name)= 'lex';


--upper() : 대문자로 변환

select 'DataBase', upper('Database') from dual;
 ----이름이 LEX인 사원의 모든 정보 출력
 select *from employees where upper(first_name)= 'LEX';
    
    
    -- 직급이 'it_prog'인 사원의 모든 정보를 출력
    select *
    from employees
    where lower(job_id) ='it_prog';
 
  select *
    from employees
    where job_id =upper( 'it_prog'); 
 -- 첫글자만 대문자로 나머지는 소문자로 변환하는 initcap 함수
 
 select 'database', initcap('database') from dual;
 --이름이 Lex인 사원의 모든 정보 출력
 select *from employees where first_name= initcap('LEX');
 
 
 --문자를 연결하는 concat 함수
 --  참고로 두개의 문자열만 연결 시켜주고 3개 이상은 연결이 안된다.
 --숫자도 문자열로 바뀐 뒤 합쳐진다.
 select concat('data','base') from dual;
 
 --연산자 || 를 쓰게 되면 여러개의 문자열을 하나로 합칠 수 있다.
 --참고로 숫자도 같이 합칠 수 있다.
 select 'data' ||'base' || 'oracle' from dual;
 
 --모든 사원의 이름과 성을 합하여 이름, 성으로 출력하시고, 컬럼명을 fullName으로 하세요
 select first_name || ','|| last_name as fullName from employees;
 
 --문자의 길이를 구하는 length 함수
 select length('database') from dual;
select length('데이터베이스') from dual;

--이름이 6글자 이하인 사원들의 이름을 소문자로 출력 하는 쿼리문
select lower(first_name)
from employees
where length(first_name)<=6;
 
 
--문자열의 일부를 추출하는 substr 함수
---substr(대상, 시작위치, 추출할 갯수)
--참고로 위치기준은 index가 아니라 length이다.
--날짜도 내부에서 데이터 타입이 문자로 변형 되기 떄문에 날짜도 추출할 수 있다.


-- 마이너스 번쨰는 문자열의 끝부터 시작된다는 의미를 가진다.
-- 추출할 갯수 위치는 마이너스로 쓸 수 없다. 마지막 숫자는 n개의 문자열을 추출한다는 뜻이기 떄문에...
select substr('database',1,3) from dual;

select substr('database',-4,3) from dual;


--입사연도가 2005년인 사원들의 모든 정보 출력

select *
from employees 
where substr(hire_date,1,2) = '05';
-- 이름의 마지막 글자가 el로 끝나는 사원들의 모든 정보를 출력

select *
from employees
where substr(first_name,-2,2)='el';

select *
from employees 
where first_name like'%el';



--특정 문자의 위치를 구하는 instr()
-- instr(대상문자열, 찾을문자열, 찾기 시작할 위치(생략하면 1번째 부터))
--마찬가지로 위치 기준은 length
-- 찾을 문자열이 2개 이상 일 경우 제일 먼저 찾은 것만 추출
select instr('database','a') from dual;

select instr('database','a',3)from dual; --4
-- 추출될 숫자 위치기준은 늘 첫번째 부터 시작


--이름의 3번째 자리가 i인 사원들의 모든 정보를 출력
select*
from employees
where instr(first_name,'i')=3; --instr

select*
from employees
where substr(first_name,3,1)='i'; --substr

select*
from employees
where first_name like '__i%'; --like


