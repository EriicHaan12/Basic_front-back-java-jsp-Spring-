package com.mini.member.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.mini.etc.PagingInfo;
import com.mini.vodto.LoginDTO;
import com.mini.vodto.MemberDTO;
import com.mini.vodto.MemberPointVo;

public interface MemberDAO {
	//회원 가입 
	int insertMember(MemberDTO dto) throws NamingException, SQLException;
	
	//아이디 중복 검사(아이디를 찾아서 count를 세주고 중복된 아이디의 갯수를 반환 하게 하도록)
	int selectByUserId(String userId)throws NamingException, SQLException;
	
	//로그인
	MemberDTO loginUser(LoginDTO dto, Connection con)throws NamingException, SQLException;
	
	// 포인트 점수 부여
	// 커넥션을 따로 열고 닫으면 insert를 할 때랑 다른 객체로 인식 되기 때문에 insert 때 얼어준 connection을 같이 받아준다.
	int addPoint(String userId, String why, Connection con )throws NamingException, SQLException;
	
	
	//가장 최근 로그인한 날짜가 오늘인지?
	// Login 할 때 처리할 커넥션을 같이 유지시키며 트랜잭션 처리를 해준다.
	int whenLatestLoginDate(String userId, Connection con)throws NamingException, SQLException;
	
	// 로그인한 유저의 로그인 기록(insert or update)
	// whenLatestLoginDate에서 의 결과값을 호출mode로 가져와서 쓰기위해 
	int writtenLoginDate(int mode,String userId, Connection con)throws NamingException, SQLException;
	
	//로그인, 포인트 부여를 트랜잭션 처리
	MemberDTO loginWithTransaction(LoginDTO dto )throws NamingException, SQLException;
	
	//유저아이디로 회원 정보 불러오기
	MemberDTO getMemberInfo(String userId)throws NamingException, SQLException;
	
	//한 유저의 포인트 내역 가져오기
	List<MemberPointVo> getMemberPoint(String userId, PagingInfo pi)throws NamingException, SQLException;

	int getTotalPointCnt(String userId)throws NamingException, SQLException;
	
}
