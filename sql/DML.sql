create table board(
no number(10),
writer varchar2(12),
title varchar2(40),
constraint board_no_pk primary key(no),
constraint board_writer_fk foreign key(writer) references member(userId) on delete cascade);--cascade on delete cascade 회원 탈퇴시 글까지 같이 삭제

select *from member;


insert into board values(1,'abcd1122','1등');
insert into board values(2,'avcd1122','2등');
insert into board values(3,'abcd1122','3번글');

select * from board order by no DESC;-- 최신글 먼저 출력

delete from member where userid='abcd1122';-- 이렇게 유저가 (회원탈퇴)삭제되면 
--걸어둔 cascade 속성에 의해 유저가 쓴 글 또한 같이 삭제 된다.

drop table board;
drop table member;


create table member(
userid varchar2(10),
pwd varchar2(5) constraint member_pwd_nn not null,
CONSTRAINT member_userid_pk primary key(userid));

insert into member values('다람쥐','1234');
insert into member values('홍찰찰','1234');
select * from member;


create table board(
no number(10),
writer varchar2(12),
title varchar2(40),
constraint board_no_pk primary key(no),
constraint board_writer_fk foreign key(writer) references member(userId) on delete set null); --cascade on delete set null 회원 탈퇴시 글이 null로 남아 있음.

insert into board values(1,'다람쥐','1등');
insert into board values(2,'홍찰찰','2등');
insert into board values(3,'다람쥐','3번글');

select * from board;
delete from member where userid='다람쥐';
select * from board; -- 이렇게 유저를 삭제하게 되면 게시만에 번호와 글은 남되, 유저만 null로 처리 된다.
