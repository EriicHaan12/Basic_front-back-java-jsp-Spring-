--dual 테이블
--여러 목적을 위해 만들어둔 가상의 테이블
select 5+3 from dual;


--1)lower() : 소문자로 변환
select 'DataBase', lower('Database') from dual;

--이름이 lex인 사원의 모든 정보 출력
select *from employees where lower (first_name)= 'lex';


--2)upper() : 대문자로 변환

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
 --3) 첫글자만 대문자로 나머지는 소문자로 변환하는 initcap 함수
 
 select 'database', initcap('database') from dual;
 --이름이 Lex인 사원의 모든 정보 출력
 select *from employees where first_name= initcap('LEX');
 
 
 --4)문자를 연결하는 concat 함수
 --  참고로 두개의 문자열만 연결 시켜주고 3개 이상은 연결이 안된다.
 --숫자도 문자열로 바뀐 뒤 합쳐진다.
 select concat('data','base') from dual;
 
 --연산자 || 를 쓰게 되면 여러개의 문자열을 하나로 합칠 수 있다.
 --참고로 숫자도 같이 합칠 수 있다.
 select 'data' ||'base' || 'oracle' from dual;
 
 --모든 사원의 이름과 성을 합하여 이름, 성으로 출력하시고, 컬럼명을 fullName으로 하세요
 select first_name || ','|| last_name as fullName from employees;
 
 --5)문자의 길이를 구하는 length 함수
 select length('database') from dual;
select length('데이터베이스') from dual;

--이름이 6글자 이하인 사원들의 이름을 소문자로 출력 하는 쿼리문
select lower(first_name)
from employees
where length(first_name)<=6;
 
 
--6)문자열의 일부를 추출하는 substr 함수
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



--7)특정 문자의 위치를 구하는 instr()
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



--8) trim : 특정문자를 잘라주는 함수
--JS에선 앞뒤 공백을 자르는 함수다
select trim('a' from 'aaaaDataBaseaaaa') from dual;

select trim(' ' from '    DataBase    ' )from dual;--공백도 제거 가능


---------------------------------------------------------------------


--숫자 데이터 처리 함수

--1) 절대값을 구하는 abs()
--음수값을 정수로 바꾸는 함수 
select abs(-15) from dual;

--2) 소수점 아래를 버리는 floor()

select floor(3.141592) from dual;


--3) 특정 자리수에서 반올림 하는 round()
--round(대상, 표시할 자리수)
select round(3.141592, 2) from dual; --셋째자리에서 반올림하여 소수점 둘째자리까지만 표시

select round(3.141592, 4) from dual;

select round(314.1592, -2) from dual; --표시할 자릿수가 음수가 되면 소숫점 기준 정수부분으로 반올림 됨.

--4) 특정 자릿수에서 잘라내는 trunc()
select trunc(3.141592, 4) from dual;
select trunc(314.1592, -2) from dual; --표시할 자릿수가 음수가 되면 소숫점 기준 정수부분에서 잘림

--5)나머지 값을 반환하는 mod()
select mod(34,3) from dual;




--사원들의 연봉을 구하려고 한다. 연봉 = (월급*12)+ (월급*12*커미션) 을 구해,
-- 수수점 이하 2자리까지만 출력되도록 하세요. 연봉은 컬럼의 컬럼명은 annual salary라고 출력하세요.

select employee_id, first_name, round((salary*12)+(salary*12*commission_pct),2) as "annual salary" 
from employees;



--------------------------------------------------
--날짜 관련 함수 : 데이터 타입이 DATE인 데이터를 대상으로 하는 함수
-- 날짜 타입도 연산이 가능하다.
--1) 현재 날짜를 반환하는 sysdate

select sysdate from dual;

select sysdate +1 내일 from dual; --일수에 +1이 된다.

--사원들이 입사일로부터 현재까지 입사한지 몇일 지났는지 출력
select first_name, floor( sysdate- hire_date)||'일 지났다' "근무일수"
from employees;


--2) 두 날짜 사이 간격을 계산하는 months_between 함수 (간격 기준은 개월수 이다)
select first_name, hire_date,  floor( months_between(   sysdate, hire_date)) 
from employees;

--3) 개월 수 를 더하는 add_months 함수
select first_name, hire_date, add_months(hire_date,3)
from employees;

--4) 해당 요일에 가장 가까운 날짜를 반환하는 next_day 함수
select sysdate,next_day(sysdate,'금요일') from dual;

--5)해당 달의 마지막 날짜를 반환하는 last_day 함수
select sysdate, last_day(sysdate) from dual;


--6) 특정 기준으로 반올림하는 round함수 (날짜도 반올림이 된다)
--일, 월,년 반올림 가능
select sysdate, round(sysdate,'month') from dual;

--7) 특정 기준으로 버리는 trunc 함수
select sysdate, trunc(sysdate,'month') from dual;


----------------------------------------------------------------------
--변환 함수 : 데이터 타입을 변환 시키고자 할 때 사용

-- to_char() : 문자형으로 변환
-- to_char(날짜데이터, '출력형식') : 날짜를 문자열로 변환

select sysdate, length(to_char(sysdate, 'yyyy-mm-dd'))  from dual;
-- 참고로 오라클에서 왼쪽으로 자동 정렬 되어있을 때 그 데이터는 문자이고,
-- 오른쪽 자동 정렬 되어 있을 때 그 데이터는 숫자이다
select sysdate, to_char(sysdate, 'yyyy-mm-dd dy')  from dual; --dy한 부분은 요일로 나온다.

select sysdate, to_char(sysdate, 'yyyy-mm-dd am HH24:mi:ss')  from dual;
-- 시간 분 초 까지 찍는 법

select to_char(123456, '999999') from dual; -- 여기서 찍은 9는 자릿수를 나타내며, 자릿수가 맞지 않아도 채우지 않는다.
--대신 바꿀 문자열 보다 9보다 많을 경우 제대로 나오지 않는다.

select to_char(123456, '00000000') from dual;
--0 같은 경우에는 부족한 자릿수를 0으로 채워준다.

select first_name, salary,  to_char(salary , 'L999,999') from employees;
--앞에 L을 붙여주면 돈 단위하나 붙고,  (,) 콤마를 써주면 돈단위 표시가 된다.

--2) to_date(): 날짜형으로 변환

select first_name, hire_date 
from employees
where hire_date ='06/01/03';

select first_name, hire_date 
from employees
where hire_date = to_date(20060103,'yymmdd');


--3)to_number() : 숫자형으로 변환

select '10,000'+ '20,000' from dual;

select to_number('10,000','99,999') + to_number('20,000','99,999') from dual;
-------------------------------------------------------------------

--기타 함수
-- (중요) 1) 
--nvl(a,b) : 첫번째 인자(a)로 받은 값이  null이면 두번째 인자(b) 값으로 변경


--사원들의 연봉을 구하려고 한다. 연봉 = (월급*12)+ (월급*12*커미션) 을 구해,
-- 수수점 이하 2자리까지만 출력되도록 하세요. 연봉은 컬럼의 컬럼명은 annual salary라고 출력하세요.

--사원들의 연봉을 구하려고 한다. 연봉 = (월급*12)+ (월급*12*커미션) 을 구해,
-- 수수점 이하 2자리까지만 출력되도록 하세요. 연봉은 컬럼의 컬럼명은 annual salary라고 출력하세요.
select employee_id, first_name, round((salary*12)+(salary*12*commission_pct),2) as "annual salary" 
from employees;


select employee_id, first_name, to_char(   round((salary*12)+(salary*12* nvl(commission_pct,0)),2) ,'L999,999' ) as "annual salary" 
from employees;


--2) decode 함수 : 프로그래밍 언어의 switch ~ case 문과 같은 역할

select first_name, department_id, decode(department_id, 90, 'Executive',
                                                        60, 'IT',    
                                                        100,'Finance'
)from employees;


-- 3) case 함수 : if else if 와 비슷한 역할

select first_name, department_id, case when department_id = 90 then 'Executive'
                                        when department_id = 60 then 'IT'    
                                        when department_id = 100 then 'Finance'
                                        end 
from employees;


------------------------------------------------------------------

--그룹 함수

--1) sum() : 합계 구하는 함수
select  sum(salary) from employees;
--sum()은 단일행 함수이기 때문에, 여러행이 한꺼번에 나오는 컬럼과 함께 사용 할 수 없다.

--2) avg() : 평균 구하는 함수

select to_char( round(avg(salary),2),'L9,999.99') from employees;

--3) max() , min() : 최대값, 최소값 구하는 함수

select max(salary), min(salary) from employees;

--4) count() : 행의 갯수를 세어주는 함수

select count(commission_pct) as "커미션을 받는 직원수"  from employees;

select count(*) as "전체 직원수" from employees;


--5) stddev() : 표준편차
--표준편차는 각 측정값과 평균의 차이를 측정하여 해당 자료의 산포도를 나타내는 값입니다.
--- 분산은 수치가 너무 크기 때문에 제곱근으로 적당하게 줄여 사용하는 데 이를 표준편차라 한다. (즉, 표준편차2=분산)
select stddev(salary) from employees;

--6) variance() : 분산
--- 편차의 제곱의 평균값. 변량들이 퍼져있는 정도를 의미한다.
select variance(salary) from employees;


--group by 절 : 그룹화를 시킬 때 사용하는 절
-- group by 컬럼 


--소속 부서별 급여 총액과 급여 평균을 구해보자
select department_id, sum(salary), avg(salary) from employees
group by department_id;

--직무별 급여 총액과 급여 평균 구해보자
select job_id, sum(salary) 합계, avg(salary) from employees
group by job_id
order by 합계 desc;


-- having 절 : 그룹화를 시킨 컬럼에 조건을 부여 할 때 쓰는 절
-- 부서별 평균 급여가 5000이상인 부서의 부서번호와 부서별 평균 급여를 출력하세요.
select department_id , avg(salary)
from employees
group by department_id
having avg(salary)>=5000;

--그룹화를 시킨 컬럼은 where절로 조건 제한을 하지 못한다.


--직급별 급여 최대값과 급여 최솟값을 구하되, 최대 급여가 7000이상인 부서만 출력하세요.

select job_id, max(salary), min(salary)
from employees
group by job_id
having max(salary)>=7000
order by max(salary) desc;



