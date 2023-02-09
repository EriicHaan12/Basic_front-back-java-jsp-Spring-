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
 
 
 
 -- SF20210087
insert into rent values(3,"SF20210087","SF",sysdate()-63,"N",null,"Y",sysdate()-60,null);
 
 select * from member where user_num=3;
 select * from video;
 
 update video set release_date= "2012-07-19"  where video_title="다크나이트 라이즈";


 commit;
 -- 상황)
 
 -- 영화 인기도 (대여횟수)가 딱 가운데 순위인 영화의 감독 작품중에 가장 잘나가는 영화는?
 
 -- 즉, 비디오 대여 기록을 대여 횟수 기준으로 정렬했을 때 순위가 가운데인 영화를 먼저 찾고
 -- 그 영화의 감독을 알아낸다.
 -- 그 영화 감독의 작품(비디오) 목록을 다시 불러낸 뒤,
 
 
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
-- 오늘 할일 
-- 신간 대여일 / 연체일 계산 하는 쿼리문 만들기

-- 빌린 비디오

-- 빌린 비디오가 신간인지 아닌지 확인
-- 빌려간 비디오가 개봉일이 30일 이내이면 대여일을 2일로 설정(신간), 30일이 지나면 대여일을 3일로설정(일반 비디오) 
-- 특정 비디오를 선택해서 신간인지 아닌지 확인.

-- video table의 release_date 개봉일 
-- rent table 의 rentdate  대여일
-- rent table 의 return_due_date 반납 예정일




select * from video;

-- 비디오 코드
set @inputVideoCode = "AC20010001";
-- 입력한 비디오의 장르
set @inputVideoGenre = (select genre_code from video where VIDEO_CODE = @inputVideoCode);
-- 입력한 비디오코드의 비디오 개봉일 출력
set @showDateVideo = (select RELEASE_DATE 날짜 from video where VIDEO_CODE = @inputVideoCode);
select @showDateVideo;
-- 빌린 날짜
set @rentDate = "2023-02-08";
-- 빌린 날짜에서 개봉일 뺀 날짜 계산
set @diffDateVideo = TIMESTAMPDIFF(day,@showDateVideo,date(@rentDate));

select @diffDateVideo ,case when @diffDateVideo>30 then  date_add( @rentDate, interval 3 day)
							when @diffDateVideo<=30 then date_add( @rentDate, interval 2 day)
						end as 예상반납일;

set @returnDueDate = (select 

select * from rent;
-- 대여 기록 강마루 한테 10개 넣기

insert into rent(user_num, video_code, genre_code, rentdate, isreturn, check_late, return_due_date)
 values(11,@inputVideoCode,@inputVideoGenre,@rentDate,"N","N",sysdate()+3);


-- 배기양,이지금, 유모레, 이민하, 이구름, 고래, 김희망 한테 각각 3개씩 넣기




 
 SELECT TIMESTAMPDIFF(day,'2023-02-01','2023-03-01');
 
-- 대여기간 = 신간 2일, 나머지 3일 / 신간 기준(그 달에 개봉한 영화)


 -- set @rentdate = (select )
 
 
