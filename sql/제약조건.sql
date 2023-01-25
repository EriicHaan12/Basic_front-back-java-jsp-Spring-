--------------------------------------------
--제약조건을 컬럼단위로 기술 하는 방법

--1)not null 제약 조건
---------------------------------------

create table emp01(
empno number(4),
ename varchar2(10));

insert into emp01 values(null, '둘리');

select * from emp01;
--이렇게 되면 primary key 가 null 이여도 상관 없다는 것이기 떄문에 잘못된 데이터이다.

drop table emp01;

create table emp01(
empno number(4) not null, -- null 값은 못들어가도록 제약 조건 생성
ename varchar2(10) not null);

insert into emp01 values(null, '둘리');
-- 제약조건 not null 을 설정해 줬기 때문에 발생하는 메세지. (프로그래머가 설정해준 제약조건이기 때문에 에러 취급하진 말자..!)

--------------------------------------------------

--2)unique 제약조건

create table emp01
(empno number(4) not null,
ename varchar2(10)unique);

--unique 제약조건에 위배하면 아래와 같은 에러가 발생 한다.
-- ORA-00001: unique constraint (HR.SYS_C007018) violated
insert into emp01 values(1000, '둘리');
--두번 실행시 오류, unique 값이 아니게 되기 때문에...
insert into emp01 values(1000, '둘리2');
--empno는 not null 만 걸어뒀기 때문에, 중복값이 들어가도 에러가 뜨지 않는다.

--unique 제약조건을 건 ename 컬럼은 null을 허용 할 수 있다.
insert into emp01 values(1000, null);
--unique 제약 조건은 null 값을 허용한다. 즉, 중복값만 아니면 된다는 뜻.

drop table emp01;
--------------------------------------------------
--3)primary key 제약 조건 : not null + unique

-------------------------------------------
create table emp01
(empno number(4) primary key,
ename varchar2(10)not null);

insert into emp01 values(null, '둘리');
-- null을 저장 할 수 없다.

insert into emp01 values(1000, '둘리');

--ORA-00001: unique constraint (HR.SYS_C007022) violated
insert into emp01 values(1000, '둘리');
-- 중복된 값을 허용 하지 않는다.


insert into emp01 values(1001, '둘리3');

insert into emp01 values(1002, '둘리4');
select * from emp01;

alter table emp01
add(sal number(6));

--primary key : null이 아니고, unique 한 값이기 때문(즉, 이 컬럼에 존재하는 고유한 값이기 때문)에 이 컬럼을 통해 검색, 수정, 삭제를 하는 것이 좋다.
--수정과 삭제 작업을 할 떄는 where 문의 조건식을 primary key 컬럼을 통해 만드는 것이 좋으며,

-- 데이터 검색 작업을 할 때에도 primary key를 통해서 검색하는 것이 indexing(정렬) 되어 있어 성능이 향상 된다.(데이터에 접근되는 고유값이기 때문에 찾기도 좋고 속도도 빨라진다.)

---1000번 사원의 급여를 5000으로 수정하자.
update emp01 set sal = 5000;
-- 이렇게 적으면 sal컬럼 모든 데이터가 5000으로 수정된다.

-- 마지막에 실행한 데이터 되돌리기
rollback; 

update emp01 set sal = 5000 where empno =1000;
select * from emp01;

--저장 시키기
commit;

--이름이 둘리4인 양반들 지우기
delete from emp01;
-- 다 지워진다.

rollback; 
--접근성이 좋지 않기 떄문에 사번으로 지울 사람을 찾아주자.
-- 사번이 1001인 양반 지우기
delete from emp01 where empno=1001;
select * from emp01;
drop table emp01;

---------------------------------
--4)foreign key 제약 조건 : 참조되는 테이블의 컬럼에 해당하는 값이 존재해야 한다.

--employees 테이블에 데이터를 넣어보자
--sysdate : 오늘날짜
-- 데이터를 넣을 때 입력할 데이터 타입이 무엇인지 확인해야한다. nullable 즉 null값이 yes인지 no인지, 
--어떤 데이터 타입으로 넣어야 할지, 
--foreign key 제약 조건이 걸려 있으면 그 테이블의 컬럼에 해당하는 데이터를 넣어줘야만 한다.


--foreign key 제약 조건을 위배 하게 되면...
--1) 부서번호가 부서 테이블에 존재 하지 않는 값을 넣으려 할때, (즉, 있지도 않은 데이터를 입력 할때)
--ORA-02291: integrity constraint (HR.EMP_DEPT_FK) violated - parent key not found (부모키(부서테이블의 부서번호)가 없다.)
insert into employees values(
207,'Eric','Han', 'ERICHAN',null,sysdate,'IT_PROG',5000,null, 115,300
);

--정상적으로 입력할 시
insert into employees values(
207,'Eric','Han', 'ERICHAN',null,sysdate,'IT_PROG',5000,null, 115,60
);
--2)데이터가 있는 컬럼을 삭제 하려 할 때

--사원이 있는 부서를 삭제 하려 할 때,
--ORA-02292: integrity constraint (HR.EMP_DEPT_FK) violated - child record found (자식키가 발견되었다.)
-- 소속 번호가 40번인 사원들이 존재, 삭제 할 컬럼 안에 데이터가 존재하기 떄문에 삭제 되지 않는다.
delete from departments where department_id=40;

select * from employees where department_id =40;
--부서번호가 40번인 사원들을 250번으로 이동 시키기
update employees set department_id =250 where department_id=40;

delete from departments where department_id=40;
-----------------부서를 이동시킬때마다, job_history에 찍혀서 예제 실패..!!
rollback;

-- 부모 테이블(참조되는 테이블)인 dept01을 먼저 생성
-- sql에서 말하는 부모,자식 관계는 상속받거나 그런 개념은 아님... 
--단순히 먼저 만들어져 참조될 테이블은 부모 테이블, 이 후 만들어져 다른테이블들을 참조할 테이블은 자식 테이블

create table dept01(
deptno number(2) primary key,
dname varchar2(10) not null);

-- 자식 테이블(특정 테이블을 참조할 테이블)인 emp01을 생성
create table emp01(
empno number(4) primary key,
ename varchar2(10) not null,
deptno number(2) REFERENCES dept01(deptno) --부모테이블을 먼저 만든뒤, foreign key로 지정할 컬럼을 참조 시켜준다.
);

insert into dept01 values(10, '총무부');
insert into dept01 values(20, '개발부');
insert into emp01 values(1000,'둘리',10);


--SQL 오류: ORA-00933: SQL command not properly ended 00933. 00000 -  "SQL command not properly ended"
delete from dept 01 where deptno = 10; -- 에러 (부서 번호가 10번인 사원이 존재함)

select *from emp01;
select *from dept01;

update emp01 set deptno = 20 where empno =1000; -- 10번 부서 사원을 20번으로 이동
delete from dept01 where deptno = 10; -- 10번 부서 삭제 가능하게 됨. (컬럼이 비어있기 때문)

--foreign key 가 걸려있기떄문(참조되는 테이블이 있기 때문)에 삭제 불가.
-- 그러므로 자식 테이블 먼저 삭제 해줘야 한다.
drop table dept01;

--정상 작동
drop table emp01;
drop table dept01;



------------------------------------
--5) check 제약 조건 : 입력되는 값을 체크하여 설정된 값 이외의 값이 들어오면 안되도록 설정.

create table emp01(
empno number(4) primary key,
ename varchar2(10) not null,
gender char(1)check (gender in('M','F')) --성별 컬럼의 값을 check 제약 조건으로 지정
);

insert into emp01 values(1000, '홍길동','m');-- 대문자M이 아니기 떄문에 에러
insert into emp01 values(1000, '홍길동','M');
insert into emp01 values(1001, '홍영희','F');
insert into emp01 values(1001, '홍찰찰','Z');-- check 값 이외의 데이터가 들어갔기 떄문에 에러

drop table emp01;
insert into emp01 values(1001, '홍영희','F');

create table member(
userId varchar2(10) primary key,
pass varchar2(20) not null,
age number(3) check(age between 20 and 150));  --나이 컬럼의 값을 check 제약 조건으로 지정

insert into member values('Eric','1234', 10); --check 값이 true 가 아니기 떄문에 에러
insert into member values('Eric','1234', 160); --check 값이 true 가 아니기 떄문에 에러
insert into member values('Eric','1234', 20);


drop table member;
---------------------------------------------------

--6) default 제약 조건 : 값이 주어지지 않으면 기본값으로 들어가도록 설정함.
--엄밀히 말하면 제약조건에 해당되진 않기 떄문에, 다른 제약조건과 같이 설정 할 수도 있다.
create table dept01(
deptno number(3) primary key,
dname varchar(10),
loc varchar2(16) default '서울');

insert into dept01(deptno, dname) values(
10,'개발부'
);
select *from dept01; -- loc값은 데이터를 넣지도 않았지만 default 제약조건으로 인해 기본값으로 '서울'이 들어가게 된다.

insert into dept01 values(20, '총무부',null); -- 컬럼에 null을 넣겠다고 명시하면 지정해준 default값이 출력되는게 아니라, null값이 들어가게 된다.(주의)
select *from dept01;

----------------------------------
--제약 조건 이름 기술 하기 : 제약조건 이름만 보더라도 어떤 테이블의 어떤 컬럼에 어떤 제약조건이 걸려있는지 쉽게 파악이 되도록 하기 위해 설정.

-- 필수는 아니지만, 나중에 에러가 떴을 때 어떤 에러인지 파악하기 쉽다.

--제약조건 이름의 예
--[테이블명]_[컬럼명]_[제약조건명]

-- 제약 조건 이름을 명시 하는 방법
-- 컬럼명 데이터타입 constraint 제약조건이름  제약조건타입

create table dept01(
deptno number(2) constraint dept01_deptno_pk primary key,
dname varchar2(10) constraint dept01_dname_nn not null,
loc varchar2(20) default 'gyeongju'
);

drop table emp01;

create table emp01(
empno number(4) constraint emp01_empno_pk primary key,
ename varchar2(10) constraint emp01_ename_nn not null,
email varchar2(20) constraint emp01_email_uq unique,
gender varchar2(5) constraint emp01_gender_ck check(gender in('male','female')),
deptno number(2) constraint emp01_deptno_fk references dept01(deptno) 
);

insert into dept01 values(
10, '총무부','부산'
);
