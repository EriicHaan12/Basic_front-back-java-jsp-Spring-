package com.mini.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.mini.voDto.LoginDTO;
import com.mini.voDto.MemberDTO;

public interface MemberDAO {
	//회원 가입 
	int insertMember(MemberDTO dto) throws NamingException, SQLException;
	
	//아이디 중복 검사(아이디를 찾아서 count를 세주고 중복된 아이디의 갯수를 반환 하게 하도록)
	int selectByUserId(String userId)throws NamingException, SQLException;
	
	//로그인
	MemberDTO loginUser(LoginDTO dto)throws NamingException, SQLException;
	
	
	// 포인트 점수 부여
	// 커넥션을 따로 열고 닫으면 insert를 할 때랑 다른 객체로 인식 되기 때문에 insert 때 얼어준 connection을 같이 받아준다.
	int addPoint(String userId, String why, Connection con )throws NamingException, SQLException;
	
}
