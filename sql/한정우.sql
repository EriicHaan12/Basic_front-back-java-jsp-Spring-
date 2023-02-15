use gottclass6;

-- 기본 테이블 CRUD--------------------------------

-- member 테이블 crud

-- create (회원 등록)
 insert into member (USER_NAME,PHONE_NUM,BIRTHDAY,GENDER,OVERDUE,ADDR) 
values("홍찰찰","010-3383-6914","2011-08-10","male","120","서울시 금천구 가산동");

-- read (회원 정보 읽기)
select * from member where USER_NAME="한찰찰";

-- update (회원 수정)
 update member set overdue = 0 where user_name ="한찰찰";

-- delete(회원 삭제) 
-- hard delete
delete from member where user_num=40;
 
 -- soft delete 
 update member set deleted_at = sysdate() where user_name="한찰찰";
 
 
 -- member 테이블 crud
 
-- create (회원 등록)
 insert into member (USER_NAME,PHONE_NUM,BIRTHDAY,GENDER,OVERDUE,ADDR) 
values("홍찰찰","010-3383-6914","2011-08-10","male","120","서울시 금천구 가산동");

-- read (회원 정보 읽기)
select * from member where USER_NAME="한찰찰";

-- update (회원 수정)
 update member set overdue = 0 where user_name ="한찰찰";

-- delete(회원 삭제) 
-- hard delete
delete from member where user_num=40;
 
 -- soft delete 
 update member set deleted_at = sysdate() where user_name="한찰찰";
 
 
 -- genre CRUD
 
 -- create
  insert into genre (genre_code,rental_fee,late_fee,genre_name,lend_time) 
 values("RomanThriller",2500,300,"로맨스릴러",3);
 
 
 -- read
 select * from genre;
 select * from genre
 where GENRE_NAME="로맨스릴러";
 select * from genre where LEND_TIME =3;
 
 -- update
  update genre set lend_time =10 where genre_name="로맨스릴러";
 
 -- delete
 -- hard delete
 delete from genre where GENRE_NAME="로맨스릴러";
 
 
 -- trigger 활용
 
--  DELIMITER $$

-- create trigger TRG_ADD_TOTAL_VIEW
-- after insert 
-- on rent
-- for each row
-- begin
-- update video set total_view = total_view+1
-- where video_code = new.video_code;

-- end$$
-- DELIMITER ;
 
 -- ------------------------- rent table 변수 이용해서 다시 데이터 입력 해보기
 
 -- 비디오id로 반납날짜 계산 = if(오늘날짜- 비디오 개봉일<=30){
-- 						     	신간비디오 이므로 반납 예정일이 대여일+2}
-- 						if(오늘날짜- 비디오 개봉일>30){
--                        일반 비디오 이므로 반납예정일은 대여일+3  }


-- 넣을 비디오 코드
set @inputVideoCode = "TH20183782";
-- 입력한 비디오의 장르
set @inputVideoGenre = (select genre_code from video where VIDEO_CODE = @inputVideoCode);
-- 입력한 비디오코드의 비디오 개봉일 출력
set @showDateVideo = (select RELEASE_DATE 날짜 from video where VIDEO_CODE = @inputVideoCode);
select @showDateVideo;
-- 빌린 날짜
set @rentDate = "2019-06-11";
-- 반납 날짜
set @returnDate ="2019-06-12"; -- 반납 한 경우
set @returnDate=null;  -- 아직 반납 하지 않은 경우
-- 빌린 날짜에서 개봉일 뺀 날짜 계산
set @diffDateVideo = TIMESTAMPDIFF(day,@showDateVideo,date(@rentDate));
select @diffDateVideo;

set @returnDueDate =  case 
							when @diffDateVideo>30 then  date_add( @rentDate, interval 3 day)
							when @diffDateVideo<=30 then date_add( @rentDate, interval 2 day)
						end;
select @returnDueDate;

-- 반납 여부 확인
set @isReturn = case  when @returnDate is null then  "N"
						when @returnDate is not null then "Y"
					end;
select @isReturn;

set@chkLate = 
			case when TIMESTAMPDIFF(day,@rentDate,@returnDueDate) <TIMESTAMPDIFF(day,@rentDate,@returnDate)  
											then "Y"
				when TIMESTAMPDIFF(day,@rentDate,@returnDueDate)>=TIMESTAMPDIFF(day,@rentDate,@returnDate)  
											then "N"
				end;
                    
  
-- 장르에 따른 연체료 
set @genreFee = (select late_fee from genre where  GENRE_CODE=@inputVideoGenre);
-- 신간 요금 검색
set @genreFee = case when @diffDateVideo >30 then @genreFee
					when @diffDateVideo <=30 then @genreFee+200
                    end;

select @genreFee;

-- 연체료 계산
-- 반납일 - 반납 예정일 <=0 는 0  반납일- 반납 예정일 >0  는 반납일-반납 예정일 * 장르 연체료
set @addLateFee = case when  TIMESTAMPDIFF(day,@returnDueDate,@returnDate)<=0   
							then null 
						when TIMESTAMPDIFF(day,@returnDueDate,@returnDate)>0   
							then TIMESTAMPDIFF(day,@returnDueDate,@returnDate) *@genreFee
					end;

insert into rent(user_num, video_code, genre_code, rentdate, isreturn,add_late_fee, check_late, return_due_date,return_date)
 values(15,@inputVideoCode,@inputVideoGenre,@rentDate, @isReturn,@addLateFee,@chkLate,@returnDueDate,@returnDate);
 
 
 
 
 -- 대여 횟수가 많은 top5 비디오 및 대여 횟수에 따른 랭크 매기기
 -- 대여 횟수가 많은 top5 비디오;
 select * from video order by total_view desc limit 5;





-- 대여 테이블에서 video 타이틀과 장르 정보만 따로 뽑아 view로 만들기---------------------------------
create or replace view rentVideoAsGenre
as
select  v.video_title, g.genre_code
from video v, genre g, rent r
where r.video_code = v.video_code and g.GENRE_CODE = r.GENRE_CODE ;

select * from rentVideoAsGenre;

-- 비디오 명으로 group by 묶어줘서 비디오별 대여 횟수 조회
create or replace view viewVideoRank
as
select video_title, genre_code, count(video_title) as 대여횟수
from rentVideoAsGenre
group by video_title;

select * from viewVideoRank;

-- 랭킹 매겨주기
select video_title, genre_code, 대여횟수, 
(select count(*)+1 from viewVideoRank where 대여횟수>v.대여횟수) 랭킹
from viewVideoRank v
order by 랭킹 asc;


-- 매출 계산 및 연체 관리---------------
-- 대여 테이블에서 대여기록에 대한 대여비 및 연체료를 확인 할 수 있는 view 만들기
 create or replace view checkSales
 as
 select r.num, 
		r.genre_code,
        g.rental_fee, 
        r.rentdate,
        r.RETURN_DUE_DATE, 
        r.RETURN_DATE,
        r.CHECK_LATE,
        r.ADD_LATE_FEE, 
        g.LATE_FEE
 from rent r , genre g 
 
 where g.genre_CODE= r.genre_code ;
 
 
 select * from checkSales;

 -- 매출 지정 시작 날짜
 set @startChkDate= "2022-02-01";
 -- 매출 지정 마지막 날짜
 
 set @endChkDate = "2023-02-01";

-- 지정 기간 연체료 총합
 set @totalLateFee = (select sum(add_late_fee) from checkSales where return_date between @startChkDate and @endChkDate);
 select  @totalLateFee;

-- 지정 기간 대여료 총합
set @totalRentalFee = (select sum(rental_fee) from checkSales where return_date between @startChkDate and @endChkDate);
 select @totalLateFee;
 
 -- 지정 기간 매출 총합
 set @totalSales = @totalRentalFee+@totalLateFee;
 select @totalSales as 총매출;
 
 
 -- 나이별 선호 장르-----------------
 
 
 create or replace view checkAge
 as
select m.birthday,r.GENRE_CODE, r.video_code
from member m , rent r
where m.user_num = r.user_num ; 

select * from checkAge;

-- 생일 form 세팅
set @birthDay =DATE_FORMAT('1993-08-11', '%Y-%m-%d');

-- 현재 년도- 생일 연도
set @Age=TIMESTAMPDIFF(year,age, '2023-01-01') ;

select @resultYear;

-- rent table에서 연령으로 데이터 뽑기

create or replace view classifyAge
 as
select  case 
when TIMESTAMPDIFF(year,birthday, '2023-01-01')>0 and TIMESTAMPDIFF(year,birthday, '2023-01-01')<10 then '아동층'
when TIMESTAMPDIFF(year,birthday, '2023-01-01')>=10 and TIMESTAMPDIFF(year,birthday, '2023-01-01')<20 then'10대'
when TIMESTAMPDIFF(year,birthday, '2023-01-01')>=20 and TIMESTAMPDIFF(year,birthday, '2023-01-01')<30 then'20대'
when TIMESTAMPDIFF(year,birthday, '2023-01-01')>=30 and TIMESTAMPDIFF(year,birthday, '2023-01-01')<40 then '30대'
when TIMESTAMPDIFF(year,birthday, '2023-01-01')>=40 and TIMESTAMPDIFF(year,birthday, '2023-01-01')<50 then'40대'
when TIMESTAMPDIFF(year,birthday, '2023-01-01')>=50 then '50대이상'
end as '연령' ,genre_code

from checkAge;



select * from classifyAge;

-- 연령별 대여한 장르 출력
select * from classifyAge;
select * from classifyAge where 연령 ="10대";
select * from classifyAge where 연령 ="20대";
select * from classifyAge where 연령 ="30대";
select * from classifyAge where 연령 ="40대";
select * from classifyAge where 연령 ="노년층";


-- 특정 연령층의 선호 장르 top3
select 연령, count(연령) as 대여횟수, genre_code as '20대 선호장르 top 3' 
from classifyAge 
group by genre_code having 연령 = "20대" order by 대여횟수 desc limit 3 ;



-- 회원별 추천 장르 및 회원별 대여 장르 랭킹 매기기-----------------------------

-- 특정 유저의 번호를 where 절에 넣었을 경우, 그 유저가 여태껏 대여한 비디오를 장르별로 count해서 나열
create or replace view checkOneRent
as
select user_num , GENRE_CODE from rent where user_num = 17;
select * from checkOneRent;

select USER_NUM,  genre_code, count(genre_code) as 횟수
from checkOneRent 
group by genre_code
order by 횟수 desc;


create or replace view OneRentRank
as 
select genre_code as 선호장르, count(genre_code) as 대여횟수
from checkOneRent 
group by genre_code
order by 대여횟수 desc;

select * from OneRentRank ;

-- 회원 별 선호 장르 랭킹 매기기
SELECT 선호장르,대여횟수,( select count(*)+1 from OneRentRank  where 대여횟수 > one.대여횟수 ) as 랭킹 
FROM  
   OneRentRank as one
ORDER BY 
    랭킹 asc;
    
    -- 장르별 인기비디오 top5 및 장르별 랭킹----------------------------------
    create or replace view rentVideoAsGenre
as
select  v.video_title, g.genre_code
from video v, genre g, rent r
where r.video_code = v.video_code and g.GENRE_CODE = r.GENRE_CODE ;

create or replace view oneGenreData
as
select video_title, genre_code
 from rentVideoAsGenre where genre_code = "Romance";

create or replace view genreRank
as
select video_title, genre_code, count(genre_code) as 대여횟수
from oneGenreData
group by video_title;

select video_title, genre_code,대여횟수, 
(select count(*)+1 from genreRank where 대여횟수>G.대여횟수) as 랭킹
from genreRank G
order by 랭킹 asc;


-- 특정 감독 영화의 대여 횟수 및 랭킹 매기기-------------------

-- 감독별 영화 조회

select * from video where DIRECTOR="피터 잭슨";
select * from video where DIRECTOR="미야자키 하야오";
select * from video where DIRECTOR like "고어%";
select * from video where DIRECTOR like "피터%";
select * from video where DIRECTOR like "크리스토퍼%";


select video_title,total_view, director
from video 
where director like "고어%"
order by total_view desc
;

-- 장르별 가장 많이 대여한 사람의 전화번호 조회 -----------------------------

-- 한가지 장르를 가장 많이 빌려간 사람의 전화 번호 조회

create or replace view checkRent
as
select user_num , GENRE_CODE from rent;

-- 장르 입력시, 입력한 장르의 비디오를 가장 많이 대여한 사람 조회
create or replace view topViewVideoMember
as
select distinct
(select distinct user_num from checkRent  where c.user_num= user_num and c.genre_code = genre_code ) user_num,
(select distinct genre_code from checkRent  where c.user_num= user_num and c.genre_code = genre_code ) genre_code,
 (select count(*)+1 from checkRent where c.user_num= user_num and c.genre_code = genre_code) 장르별대여횟수
from checkRent c
where genre_code = "Action"
order by 장르별대여횟수 desc limit 1;

select * from genre;


select t.*,m.USER_NAME, m.phone_num
from topViewVideoMember t inner join member m
on t.user_num = m.USER_NUM;





 
 