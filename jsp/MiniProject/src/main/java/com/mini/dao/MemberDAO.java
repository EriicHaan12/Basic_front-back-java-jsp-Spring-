package com.mini.dao;

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
	
}
