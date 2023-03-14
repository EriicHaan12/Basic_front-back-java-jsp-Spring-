use hjw;
select* from hjw.member;

drop table board;

-- member 테이블 생성
select* from member;

CREATE TABLE `member` (
  `userId` varchar(8) NOT NULL,
  `userPwd` varchar(200) NOT NULL,
  `userName` varchar(15) NOT NULL,
  `userEmail` varchar(50) NOT NULL,
  `userMobile` varchar(13) DEFAULT NULL,
  `userGender` varchar(1) NOT NULL,
  `hobbies` varchar(100) DEFAULT NULL,
  `job` varchar(20) DEFAULT NULL,
  `memberImg` varchar(100) DEFAULT 'uploadMember/noimage.png',
  `memo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci