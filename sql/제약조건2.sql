create table member (
id varchar2(20),
password varchar2(40),
regno varchar2(13) CONSTRAINT member_regno_nn not null,
mobile varchar2(13),
address varchar2(100),

CONSTRAINT member_id_pk primary key(id),
CONSTRAINT member_regno_uq unique(regno),
CONSTRAINT member_mobile_uq unique(mobile)
);
drop table member;
drop table book;
drop table bookorder;

create table book(
code number(4),
title varchar2(50) constraint book_title_nn not null,
count number(6),
price number(10),
publish varchar2(50),

constraint book_code_pk primary key(code));


create table bookorder(
no varchar2(10),
id  varchar2(20),
code number(4),
count number(6),
orderDate date,
constraint bookorder_no_pk primary key(no),
constraint bookorder_id_fk foreign key(id) references member(id),
constraint bookorder_code_fk foreign key(code) references book(code));








