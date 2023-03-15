use hjw;
select* from hjw.member;
commit;
drop table board;

-- member 테이블 생성
select* from member;

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
insert into 
member(userId,userPwd,userEmail,userMobile,userGender,hobbies,job,memo) 
values('abc',sha1(md5('1234')),'aa@na.com','010-1111-2222','M','','학생','');



