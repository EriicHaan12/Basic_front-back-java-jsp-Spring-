package com.mini.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mini.service.MemberService;

public class LoginMemberService implements MemberService {

	

	@Override
	public MemberFactory execute(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		System.out.println("로그인 해볼래?");
		return null;
	}

	

}
