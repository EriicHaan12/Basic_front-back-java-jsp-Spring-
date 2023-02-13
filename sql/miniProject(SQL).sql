use gottclass6;


 
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
  
  -- ---------------------------------------------
 select * from rent where USER_NUM=3;
 
 -- 시리즈물 검색
--  select * from video where VIDEO_TITLE like "다크나이트%";
 
 
 
insert into rent values(3,"SF20210087","SF",sysdate()-63,"N",null,"Y",sysdate()-60,null);
 
 select * from member where user_num=3;
 select * from video;
 
 update video set release_date= "2012-07-19"  where video_title="다크나이트 라이즈";


 
 -- 블랙리스트 조회
 select * from member where OVERDUE>=100;
 update member set chk_black="Y" where user_name="홍찰찰";
 commit;
 
 select * from rent;
 
 
 -- 인기 목록 조회
 -- 즉, 대여기록중 가장 많이 빌려간 비디오 top5
 
 select * from genre;
 select * from video;
 insert into video values("DR20190808","Drama","교섭",12,"2023-01-18","(주)영화사수박","신범수",0); 

commit;



select * from video;

-- 비디오 코드
set @inputVideoCode = "AC20030135";
-- 입력한 비디오의 장르
set @inputVideoGenre = (select genre_code from video where VIDEO_CODE = @inputVideoCode);
-- 입력한 비디오코드의 비디오 개봉일 출력
set @showDateVideo = (select RELEASE_DATE 날짜 from video where VIDEO_CODE = @inputVideoCode);
select @showDateVideo;
-- 빌린 날짜
set @rentDate = "2006-09-10";
-- 반납 날짜
set @returnDate ="2006-09-12"; -- 반납 한 경우
set @returnDate=null;  -- 아직 반납 하지 않은 경우
-- 빌린 날짜에서 개봉일 뺀 날짜 계산
set @diffDateVideo = TIMESTAMPDIFF(day,@showDateVideo,date(@rentDate));
select @diffDateVideo;

set @returnDueDate =  case when @diffDateVideo>30 then  date_add( @rentDate, interval 3 day)
							when @diffDateVideo<=30 then date_add( @rentDate, interval 2 day)
						end;
select @returnDueDate;

-- 반납 여부 확인
set @isReturn = case  when @returnDate is null then  "N"
						when @returnDate is not null then "Y"
					end;
select @isReturn;
-- 제 때 반납한지 확인
set@chkLate =  case when  TIMESTAMPDIFF(day ,@rentDate,@returnDueDate) <TIMESTAMPDIFF(day ,@rentDate,@returnDate)  
					then "Y"
                     when TIMESTAMPDIFF(day ,@rentDate,@returnDueDate) >=TIMESTAMPDIFF(day ,@rentDate,@returnDate)  
					then "N"
                    end;
select @chkLate;
-- 장르에 따른 연체료 
set @genreFee = (select late_fee from genre where  GENRE_CODE=@inputVideoGenre);
-- 신간 요금 검색
set @genreFee = case when @diffDateVideo >30 then @genreFee
					when @diffDateVideo <=30 then @genreFee+200
                    end;

select @genreFee;

-- 연체료 계산
-- 반납일 - 반납 예정일 <=0 는 0  반납일- 반납 예정일 >0  는 반납일-반납 예정일 * 장르 연체료
set @addLateFee = case when  @chkLate='N'   
								then null 
						when @chkLate = 'Y'
								then TIMESTAMPDIFF(day,@returnDueDate,@returnDate) *@genreFee
					end;
                    select TIMESTAMPDIFF(day, @returnDate,@returnDueDate);

select @addLateFee; 
select *from genre;


-- 대여 기록 유모레, 한테 10개 넣기

insert into rent(user_num, video_code, genre_code, rentdate, isreturn,add_late_fee, check_late, return_due_date,return_date)
 values(16,@inputVideoCode,@inputVideoGenre,@rentDate, @isReturn,@addLateFee,@chkLate,@returnDueDate,@returnDate);


select * from rent where USER_NUM= 16;





update rent set return_date ="2023-02-12" where NUM = 79;
commit;
-- 배기양,이지금, 유모레, 이민하, 이구름, 고래, 김희망 한테 각각 3개씩 넣기



-- 대여 횟수가 많은 top5 비디오;
 select * from video order by total_view desc limit 5;

select * from genre;

 select * from rent;
 
   set @selectYear = "2022";
   set @selectMonth = "2";
 
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
 
 
use gottclass6;

select * from member;

select *from age_group_rent;

select * from removeUser;


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
			when TIMESTAMPDIFF(year,birthday, '2023-01-01')>=0 and TIMESTAMPDIFF(year,birthday, '2023-01-01')<20 then'10대'
			when TIMESTAMPDIFF(year,birthday, '2023-01-01')>=20 and TIMESTAMPDIFF(year,birthday, '2023-01-01')<30 then'20대'
			when TIMESTAMPDIFF(year,birthday, '2023-01-01')>=30 and TIMESTAMPDIFF(year,birthday, '2023-01-01')<40 then '30대'
			when TIMESTAMPDIFF(year,birthday, '2023-01-01')>=40 and TIMESTAMPDIFF(year,birthday, '2023-01-01')<50 then'40대'
			when TIMESTAMPDIFF(year,birthday, '2023-01-01')>=50 then '노년층'
			end as '연령' ,genre_code

from checkAge;


select * from classifyAge;
select * from classifyAge where 연령 ="10대";
select * from classifyAge where 연령 ="20대";
select * from classifyAge where 연령 ="30대";
select * from classifyAge where 연령 ="40대";
select * from classifyAge where 연령 ="노년층";

-- 특정 연령층의 선호 장르 top3
select 연령, count(연령) as 대여횟수, genre_code as '20대 선호장르 top 3' from classifyAge group by genre_code having 연령 = "20대" order by 대여횟수 desc limit 3 ;

-- 회원별 추천 장르 
-- test는 15번 회원으로 
select * from rent;
select * from member;
commit;



select * from video;

-- 장르별 인기비디오 top 5
create or replace view rentVideoAsGenre
as
select  v.video_title, g.genre_code
from video v, genre g, rent r
where r.video_code = v.video_code and g.GENRE_CODE = r.GENRE_CODE;

select * from rentVideoAsGenre;