select * from employees
where first_name like "%"+"James"+"%";

-- 테이블 생성

create table friends
(
friendNo number(4)primary key,
friendName varchar2(6) not null,
mobile varchar2(13) unique,
addr varchar2(50)
);


