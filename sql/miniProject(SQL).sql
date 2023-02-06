use gottclass6;



select * from member;




select * from rent; 
select * from video;
commit;
insert into member (USER_NAME,PHONE_NUM,BIRTHDAY,GENDER,OVERDUE,ADDR) values("한찰찰","010-3383-6914","2011-08-10","male","120","서울시 금천구 가산동");
select * from member where USER_NAME="한찰찰";


-- MEMBER table에서 회원 탈퇴 부분을 soft delete로 만들기
-- 현재 soft delete 시킬 컬럼이 없기 때문에 팀원과 상의 후 DDL을 이용한 DELETED_AT 컬럼 추가 하기로 결정
-- ALTER TABLE member ADD  deleted_at DATE NULL;


-- member CRUD
-- create (회원 등록)
-- insert into member (USER_NAME,PHONE_NUM,BIRTHDAY,GENDER,OVERDUE,ADDR) values("홍찰찰","010-3383-6914","2011-08-10","male","120","서울시 금천구 가산동");

-- read (회원 정보 읽기)
-- select * from member where USER_NAME="한찰찰";

-- update (회원 수정)
 -- update member set overdue = 0 where user_name ="한찰찰";

-- delete(회원 삭제) 
-- hard delete
-- delete from member where user_num=40;
 
 -- soft delete 
 -- update member set deleted_at = sysdate() where user_name="한찰찰";
 
 -- -------------------------------------------------------------------------
 
 -- video CRUD 
 -- create
-- insert into video(video_code,genre_code, video_title, movie_rated, release_date, production, director) 
-- values("row20131205","Romance","어바웃타임",15,2013-12-05,"워킹 타이틀","리처드 커티스");
-- insert into video(video_code,genre_code, video_title, movie_rated, release_date, production, director)
-- values("WA20230206","War","구트폭파작전",19,2023-02-06,"우리집 스튜디오","에릭한");
 -- read 
-- select * from video where video_title="어바웃타임";

 -- update
 -- update video set video_code = "RO20131205" where video_title="어바웃타임";
 -- delete
 
 -- delete from video where VIDEO_TITLE = "구트폭파작전";
 
 
 -- -------------------------------------------------------------------------
 
 -- genre CRUD
 -- create
  -- insert into genre (genre_code,rental_fee,late_fee,genre_name,lend_time) 
 -- values("RomanThriller",2500,300,"로맨스릴러",3);
 
 
 -- read
 -- select * from genre;
 -- select * from genre
 -- where GENRE_NAME="로맨스릴러";
 -- select * from genre where LEND_TIME =3;
 
 -- update
 -- update genre set lend_time =10 where genre_name="로맨스릴러";
 
 -- delete
 -- hard delete
 -- delete from genre where GENRE_NAME="로맨스릴러";
 
 
 -- ---------------------------------------------------------------
 -- rent CRUD
 -- create
 commit;
 
insert into rent(user_num,video_code,genre_code,rentdate,isreturn,check_late,return_due_date)
 values(38,"RO20131205","Romance",sysdate(),"N","N",sysdate()+3);

-- read  
select * from rent;
-- 특정 인물의 대여 정보 출력
-- select * 
-- from rent r, member m 
-- where m.user_name=
-- (select user_name from member where user_name="홍찰찰") and r.USER_NUM=m.USER_NUM ; 
 
 -- update
 -- 특정 인물이 반납했을 경우 정보 업데이트
 -- update rent set isreturn="Y",return_date=sysdate() where user_num=38;
 
 -- delete
 -- hard delete
  -- delete from rent where user_num =38;
 