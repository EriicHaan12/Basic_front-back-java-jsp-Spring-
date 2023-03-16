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


select * from hjw.pointpolicy;
select * from memberpoint;
select * from pointpolicy;
insert into pointpolicy values('게시판답글쓰기','1');

insert into memberpoint(who,why,howmuch) values('aa112','로그인','10');