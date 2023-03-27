use hjw;
select* from hjw.member;
commit;
drop table board;

-- member 테이블 생성
select * from member;

CREATE TABLE `member` (
  `userId` varchar(8) NOT NULL,
  `userPwd` varchar(200) NOT NULL,
  `userEmail` varchar(50) NOT NULL,
  `userMobile` varchar(13) DEFAULT NULL,
  `userGender` varchar(1) NOT NULL,
  `hobbies` varchar(100) DEFAULT NULL,
  `job` varchar(20) DEFAULT NULL,
  `userImg` varchar(100) DEFAULT 'uploadMember/noimage.png',
  `memo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 패스워드 암호화
-- md5라는 알고리즘으로 암호화
select md5('1234'); 
-- sha1라는 알고리즘으로 암호화
select sha1('1234');
-- 암호화(암호화()) 섞는것이 가능하다
select sha1(md5('1234'));

-- 회원 가입
-- 회원가입시 이미지가 없을때는 default 값이 들어가도록 해주는 sql문
-- 이미지가 들어올 컬럼을 넣어주지 않으면 된다, 이미 default로 noimage를 넣어줬기 때문
insert into 
member(userId,userPwd,userEmail,userMobile,userGender,hobbies,job,userImg,memo) 
values('abc',sha1(md5('1234')),'aa@na.com','010-1111-2222','M','','학생','');

select* from member;
-- 이미지가 있을 때
insert into 
member 
values(?,sha1(md5(?)),?,?,?,?,?,?,?);

-- 중복된 유저 아이디 검사
select count(*) as userCnt from member where userId=?;


-- 로그인 처리
select* from member where userId = ? and userPwd=sha1(md5(?)); 
select* from member where userId = 'aa112' and userPwd=sha1(md5('1234')); 


-- 회원 포인트 적립을 위한 테이블 생성
CREATE TABLE `memberpoint` (
  `who` varchar(8) NOT NULL,
  `when` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `why` varchar(50) DEFAULT NULL,
  `howMuch` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- 포인트 적립 사유 테이블
CREATE TABLE `pointpolicy` (
  `why` varchar(50) NOT NULL,
  `howMuch` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 참조 관계 만들기
ALTER TABLE `hjw`.`memberpoint` 
ADD CONSTRAINT `who_fk`
  FOREIGN KEY (`who`)
  REFERENCES `hjw`.`member` (`userId`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;


alter table memberpoint
add constraint memberpoint_why_fk foreign key(why) references pointpolicy(why);

-- alter table memberpoint
-- add constraint memberpoint_howmuch_fk foreign key(howmuch) references pointpolicy(howmuch);

select * from member;
select * from hjw.pointpolicy;
-- 맴버 포인트 조회
select * from memberpoint;
select * from pointpolicy;
insert into pointpolicy values('게시물 신고','-10');

insert into memberpoint(who,why,howmuch) values('aa112','로그인','10');

select *from hjw.member;
select *from hjw.memberpoint;

-- 유저에게 포인트를 부여하는 쿼리문
insert into memberpoint(who,why,howmuch) 
values(?,'회원가입',(select howmuch from pointpolicy where why ='회원가입'));

-- 로그인 기록 관리 테이블 생성
CREATE TABLE `latestloginlog` (
  `who` varchar(8) NOT NULL,
  `latestLoginDate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`who`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- member, lastestloginlog 관계 지정
alter table latestloginlog
add constraint member_userIlatestloginlogd_fk foreign key (who) references member(userId);


-- 처음 로그인한 것이 아니고, 로그인 하고 날짜가 바뀌었으면 0 보다 크게 설정 
-- 처음 로그인한것이 아니고, 날짜가 바뀐 것이 아니면 0;

select* from latestloginlog;

-- 처음 로그인한 사람의 로그인 기록을 남기는 쿼리문
insert into latestloginlog(who) values(?);

-- 기존 로그인한 기족이 있는 사람 update 하는 쿼리문
update latestloginlog set latestLoginDate =now() where who = ?;
 


-- 최근 로그인 한 것이 날짜가 바뀌었는가?

select * from hjw.latestloginlog;
-- 회원 가입 후 처음 로그인 하면 null 
-- 과거 로그인 했던 기록이 언제 였는지(없다면 -1)
select ifnull(a.diff,-1)as datediff from(select datediff(now(), (select latestlogindate from latestloginlog where who='hanoo2')) as diff)a ;

select datediff(now(), (select latestlogindate from latestloginlog where who='hanoo2')) as diff;
-- select TIMESTAMPDIFF(day, now(), (select latestlogindate from latestloginlog where who='aa112')  ) as diff;



-- 유저 아이디로 회원 정보 불러오기

select * from board;

-- 유저 한명의 포인트 내역 가져오기
select * from memberpoint where who = 'hanoo2' order by no desc;


-- 회원관리 끝---------------------------------------------------------

-- 답글형(계층형) 게시판 구현
-- 게시판 테이블 생성
use hjw;


CREATE TABLE `hjw`.`board` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `writer` VARCHAR(8) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `postDate` DATETIME NULL DEFAULT now(),
  `content` VARCHAR(500) NOT NULL,
  `imgFile` VARCHAR(50) NULL,
  `readcount` INT NOT NULL DEFAULT 0,
  `likecount` INT NOT NULL DEFAULT 0,
  `ref` INT NOT NULL DEFAULT 0,   -- 부모글을 참조
  `step` INT NOT NULL DEFAULT 0,  -- 답글의 깊이
  `reforder` INT NOT NULL DEFAULT 0, -- 부모글과 답글을 보여주는 순서
  PRIMARY KEY (`no`));

-- member, board 테이블 관계 설정

alter table board 
add constraint board_writer_fk 
foreign key(writer)
references member(userId)
on delete cascade;


select * from board;

commit;
-- 게시판 테스트 글 생성
insert into board(writer,title,content, imgFile) values('hanoo2','첫글 테스트','ㅎㅎㅇ','');

-- 게시판글 ref 달아준뒤 생성
-- 게시판 부모글 테스트 생성
-- ref 컬럼은 부모글을 참조하는 컬럼이다. 답글이 아닌 부모글을 쓸 때는 자기 자신의 글 번호(no)와 같은 값을 가지도록 해야한다.
select max(no)+1 from board;

insert into board(writer,title,content, imgFile,ref) values('aa112','ref 테스트','ㅎㅎㅇ','',(select max(no)+1 from board));
insert into board(writer,title,content, imgFile,ref) values('aa112','ref 테스트','ㅎㅎㅇ','',2);

-- 게시판 전체 글 목록을 보여주는 쿼리문
select * from board order by no desc;

select max(no) +1 as nextRef from board;

-- 현재의 board테이블의 auto increment 값을 참조 하는 쿼리문
select auto_increment 
from information_schema.tables 
where table_schema ='hjw' and table_name ='board';

-- ? 번 글을 조회하는 쿼리문
select * from board where no=?;


select * from board ;

-- ? 번글의 조회수를 증가하는 쿼리문
update board set readcount = readcount+1 where no=? ;


-- 게시물 상세 조회 --------------------------
-- 조회수 처리를 위한 테이블 생성
CREATE TABLE `hjw`.`readcountprocess` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `ipAddr` VARCHAR(15) NOT NULL,
  `boardNo` INT NOT NULL,
  `readTime` DATETIME NOT NULL DEFAULT now(),
  PRIMARY KEY (`no`),
  INDEX `readcount_boardNo_fk_idx` (`boardNo` ASC) VISIBLE,
  CONSTRAINT `readcount_boardNo_fk`
    FOREIGN KEY (`boardNo`)
    REFERENCES `hjw`.`board` (`no`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- ?번 ip 주소가 ?번 글을 읽은 적이 있냐?()

select * from readcountprocess where ipAddr =? and boardNo=?;

-- ?번 ip 주소가 ? 번 글을 읽은 시간이 몇시간전?
-- 만약 읽은 적이 없다면 -1 반환 하도록
-- 24시간 이내이면 0
-- 24시간 이후이면 1
 use hjw;

-- select timediff(now(),(select readtime from readcountprocess where ipAddr ='211.197.18.247' and boardNo=1))>'24:00:00';

 select ifNull(TIMESTAMPDIFF(hour,(select readtime 
 from readcountprocess where ipAddr ='211.197.18.247' and boardNo=1), now())>24, -1)as diff ;

-- ip주소,ㅡ 글번호, 읽은 시간 insert
insert into readcountprocess(ipAddr,boardNo) values(?,?);


-- ip주소, 글번호의 데이터를 읽어 readTime을 현재시간으로 업데이트
update readcountprocess set readTime = now()
where ipAddr= ? and boardNo=?;

-- -------------------------------------
-- 게시판 답글 달기
-- 모든 게시물 출력하는 쿼리문
-- 기존
select* from board order by no desc; 
-- 답글 기능 추가 이후
select* from board order by ref desc, reforder asc;


-- 답글이 달리게 되는 알고리즘			
-- 1) 기존 board 테이블이 전체 행에 대해 아래의 작업을 수행한다.			
-- ㅡ> pRef== ref and pRefOrder< refOrder 최상위 부모글인 row를 찾는다.			

-- 부모글이 답글로 작성되었는지 아닌지 판단하여 업데이트 (답글이 끼어들기할 공간을 먼저 만들어주기 위해 만든 쿼리문)
update board set reforder = reforder+1 where ref =? and reforder>?;

-- 답글을 board 테이블에 등록
insert into board(writer, title, content,ref,step,reforder)
values(?,?,?,?,pStep+1,pRefOrder+1);

-- 인기글/ 최신글 몇개 가져와보기-----------
select * from board order by readcount desc limit 3;
select * from board order by postDate desc limit 3; 

select * from member;
-- 내가 쓴글
select * from board where wirter = ?;



-- 답글 수정의 경우(답글 작성자가 로그인 하여 수정 가능, ref,step, reforder가 기존 유지)
-- 답글 삭제의 경우(답글 작성자가 로그인하여 삭제 가능)
-- 답글의 작성자 -> admin
-- 답글의 title -> 삭제된 글입니다
-- 답글의 내용 : x
-- viewBoard.bo 페이지로 링크를 안걸어도 된다.
-- 조회수, 좋아요 수 초기화 되면서 대체(ref,step, reforder은 대체된 글이 유지- 계층형 구조를 무너뜨리지 않게 하기 위해서)



-- 페이지네이션 처리

-- 1) 하나의 페이지에 몇개의 글(row)을 보여줄 것인지 결정;
-- 2) 게시판 글의 총 갯수를 얻어온다.  :20
-- 3) 총 페이지 수 : 게시판 글 수/ 하나의 페이지에 보여줄 글의 수 

-- 20/3 = 6(나누어 떨어지지 않았으므로 +1) => 7페이지 (나머지를 보여줄 페이지 하나가 더 필요하기 때문)

-- 4) n번 페이지에서 출력하게 될 글의 시작 번호

select count(*)as cnt from board;
-- 페이지 예
-- 1페이지(한 페이지에 게시물 3개)
-- limit a , b;
-- a는  order by로 정렬된 테이블의 a번째 index 번호 
-- b는  결과값으로 뿌려줄 row의 갯수결과값으로 뿌려줄 row의 갯수
-- 종합하자면 limit(a번째 부터 a+b번째 까지 row를 뿌리겠다는 뜻)
-- 글의 시작 번호: 전체글의 갯수(23)-(현재 페이지 번호-1)* 한페이지당 보여줄 글 갯수
-- 1페이지: 20-0*3 = 20 번부터 3개 보여주겠다는 뜻

-- 보여주기 시작할 row index 번호 =
-- 한 페이지에서 보여줄 글의 갯수* (현재 페이지-1)
-- 1페이지 3*(1-1)=0 즉 limit 0,3 

select * from board order by ref desc, reforder asc limit 0, 3;


-- 페이징 블럭 만들기(pagination에 페이지 번호를 몇개씩 보여줄 것인가)
-- 쉽게 이해하자면  ui 에 누를 수 있는 페이지 버튼 갯수
-- 1) 1개의 블럭에 몇개 페이지를 둘것인가(pageCntPerBlock) : 3  (페이징 블럭) 
-- 전체 페이징 블럭 갯수 : 전체 페이지 수 / pageCntPerBlock
-- 나누어 떨어지지 않으면 +1 = 7(전체페이지수)/3(pageCntPerBlock) = 
-- 2(2이지만 나누어 떨어지지 않으므로) 최종 결과는 3 

-- 2) 현재 페이지가 속한 페이징 블럭: 현재 페이지 번호 / 페이징 블럭(만약 나누어 떨어지지 않으면 올림 해준다.)
-- ex) 현재 2페이지에 있으면 -> 2/3 (나누어 떨어지지 않으면 올림 해준다.) -> 1번 블럭
-- ex) 현재 6페이지 -> 6/3 -> 2번 블럭

-- 3) 현재 페이징 블럭 시작 번호 : ((현재 페이징 블럭 -1 )* 페이징 블럭)+1
-- ex) 2번 블럭 : ((2-1)*3) +1 = 4

-- 현재 페이징 블럭의 끝번호: (현재 페이징 블럭 시작번호 + pageCntPerBlock)-1
-- 2번 블럭 => (4+3)-1 = 6

-- 게시판 검색 -------------------------------------------------------------------------
select * from pointpolicy;
select * from memberpoint;
update pointpolicy set why ="게시판답글달기" where why = "게시판답글쓰기";
select * from board;
select * from board where title like '%시%' order by ref desc, reforder asc limit 0, 5;


-- 제목으로 검색
select * from board where title like '%?%' order by ref desc, reforder asc limit ?, ?;

-- 작성자 검색
select * from board where wrtier like '%시%' order by ref desc, reforder asc limit ?, ?;

-- 작성자 검색
-- select * from board where content like %?% order by ref desc, reforder asc limit ?, ?;
select count(*) from board where content like "%ㅇㅇ%";

-- 제목 +내용 검색
select * from board where title like "%?%" or content like "%?%" order by ref desc, reforder asc limit ?, ?;

-- 멤버 포인트 개편
select * from memberpoint where who =? order by no desc limit ?, ?;
