package com.mini.dao;

import java.sql.SQLException;

import javax.naming.NamingException;

import com.mini.voDto.MemberDTO;

public interface MemberDAO {
	//회원 가입 
	int insertMember(MemberDTO dto) throws NamingException, SQLException;
}
