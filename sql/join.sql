-------------
--데이터 정규화 : 관계형 데이터베이스의 설계에서 중복을 최소화하게 데이터를 구조화하는 프로세스를 정규화(Normalization)라고 한다. 
--데이터베이스 정규화의 목표는 이상이 있는 관계를 재구성하여 작고 잘 조직된 관계를 생성하는 것에 있다.

--때에 따라 역정규화 도 시킬 때가 있다.
--보통 자주 써야되거나, 복잡하게 엮인 데이터는 쉽고 빠르게 보기 위해 역정규화 시킬 때도 있다.... (정답은 없음)



--조인 (join)

-- 두개 이상의 테이블에서 결과를 얻기 위한 SQL문장

--사번이 100인 사원의 정보(사번, 이름, 부서번호)와 그가 소속한 부서의 명까지 출력해보자.
--sql 에서는 colum들도 속성 취급한다.

--테이블 별칭을 from 절에 사용하여, 해당 하는 join문장에서는 테이블 별칭을 이용해서 줄여 쓸 수 있다.

select employee_id, first_name,e.department_id, department_name
from employees e, DEPARTMENTS d --컬럼명에 별칭 다는 방법
where e.department_id = d.department_id --join  조건
and employee_id =100;


-- (중요) join 조건은 join문에 사용되는 테이블의 총 갯수 -1개가 되어야 한다.

--CEO가 설날을 맞이하여 모든 직원에게 선물을 택배로 보내려한다.
--모든 직원들이 택배를 무사히 받을 수 있도록 사무실의 주소, 사원 정보를 출력하세요.
--직원들의 사무실 주소, 사원 정보
select e.job_id , e.first_name ||','|| e.last_name,
d.department_name, l.street_address, l.postal_code,l.state_province,l.city,
c.country_name, r.region_name
from EMPLOYEES e, locations l, departments d,COUNTRIES c,regions r
where  e.department_id=d.department_id and 
        d.location_id = l.location_id  and
        l.country_id =c.country_id and
        c.region_id =r.region_id
;

