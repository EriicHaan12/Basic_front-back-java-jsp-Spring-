select * from employees
where first_name like "%"+"James"+"%";
drop table friends;
-- 테이블 생성

create table friends
(
friendNo number(4)primary key,
friendName varchar2(10) not null,
mobile varchar2(13) unique,
addr varchar2(100));
-- 전체 친구목록을 조회하는 쿼리문
select * from friends;

-- 친구를 저장하는 쿼리문
insert into friends values(1,'홍찰찰','010-1111-222','해피');

insert into friends(friendName,mobile,addr) values(?, ?, ?, ?);

commit;


-- 다음에 저장될 친구 번호를 조회하는 쿼리문
select max(friendNo)AS maxNo from friends;

-- 전화번호가 중복되었는지 검사
select count(*) as cnt from friends where mobile ='010-1111-222';

-- 친구 수정(이름 수정)
update friends set friendname=? where friendno=?;

select * from friends;

-- 친구 조회 (이름으로 조회)
select * from friends where friendname='홍찰찰';

-- 이름으로 친구 검색(like 사용)
Select * from friends where friendName like '%';
