package com.mini.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mini.controller.MemberFactory;

public class LogoutMemberService implements MemberService {

	@Override
	public MemberFactory execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
		//로그아웃 
		//로그아웃을 요청한 클라이언트의 세션을 얻어온다.
		HttpSession ses=req.getSession();
		ses.removeAttribute("loginMember");
		ses.invalidate();// 세션 만료 시키기 -> 새로운 세션이 생성된다.
		
		MemberFactory mf =MemberFactory.getInstance();
		mf.setRedirect(true);
		mf.setWhereIsgo("../index.jsp");
		
		return mf;
	}

}
