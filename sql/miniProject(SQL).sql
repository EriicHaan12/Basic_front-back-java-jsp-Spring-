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
set @inputVideoCode = "MU20226666";
-- 입력한 비디오의 장르
set @inputVideoGenre = (select genre_code from video where VIDEO_CODE = @inputVideoCode);
-- 입력한 비디오코드의 비디오 개봉일 출력
set @showDateVideo = (select RELEASE_DATE 날짜 from video where VIDEO_CODE = @inputVideoCode);
select @showDateVideo;
-- 빌린 날짜
set @rentDate = "2022-09-11";
-- 반납 날짜
set @returnDate ="2022-09-15"; -- 반납 한 경우
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
set @addLateFee = case when  TIMESTAMPDIFF(day,@returnDueDate,@returnDate)<=0   
								then null 
						when TIMESTAMPDIFF(day,@returnDueDate,@returnDate)>0   
								then TIMESTAMPDIFF(day,@returnDueDate,@returnDate) *@genreFee
					end;
                    select TIMESTAMPDIFF(day, @returnDate,@returnDueDate);

select @addLateFee; 
select *from genre;


-- 대여 기록 유모레, 한테 10개 넣기

insert into rent(user_num, video_code, genre_code, rentdate, isreturn,add_late_fee, check_late, return_due_date,return_date)
 values(15,@inputVideoCode,@inputVideoGenre,@rentDate, @isReturn,@addLateFee,@chkLate,@returnDueDate,@returnDate);

select * from rent where USER_NUM= 15;


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
 
 select r.add_late_fee ,g.rental_fee
 from rent r inner join gerne g
 on  g.genre_CODE= r.genre_code
 where r.return_date between "2020-02-01" and "2023-02-01";
  
 
select TIMESTAMPDIFF(day,"2022-06-16","2022-06-26") from dual;

-- 연도별 연체료 총합
set @totalSumLateFee =(select sum(add_late_fee) from rent  where return_date  like "2022%");
-- 연도별 대여료 총합
set @totalSumRentalFee = (select sum(g.rental_fee) 
						from rent r inner join genre g 
                        on g.GENRE_CODE= r.GENRE_CODE
                        having r.RETURN_DATE like "2022%") ;

select  @totalAddLateFee;