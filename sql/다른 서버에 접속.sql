------------- 상대방이 열어둔 로컬 서버에 상대방의 ip 포트번호로 접속 할 시
select * from test;


-- 열어둔 서버에 접속하게 되면 다른 유저가 만든 테이블을 볼 수 있다.
--하지만 작성한 데이터도 커밋을 날리기 전까지는 적용 되지 않는다.

insert into test values(
'코드초보','한정우'
);

--커밋 전까지는 업데이트 내용이 보이지 않는다.
commit;

-- 하지만 DDL문을 사용 하게 되면 commit 을 쓸 필요없이 바로 update 된다.
-- alter table test add(num number(3))

--이렇게 작성하게 되면 rollback 해도 바뀌지 않는다.


--rollback
insert into test values('호호하하','hjw',10,20);

rollback;
select * from test;

--update 
update test set name = '홍찰찰' where num =20 and numnumnum = 20;

savepoint after_update;
commit;

delete from test where num =20;

--협업 하게 될 때 commit , rollback을 주의해서 써야된다.
-- 

