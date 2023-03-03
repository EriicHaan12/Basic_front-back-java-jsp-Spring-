package com.jspbasic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionLogin.do")
public class SessionLoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//아이디 : abcd123
		//비밀번호 :555566 
		//이라고 할 때, session을 이용한 로그인 처리와 로그아웃 처리를 해보자
	String userId= 	req.getParameter("userId");
	String userPwd = req.getParameter("userPwd");
	
	if(userId.equals("abcd123") &&userPwd.equals("555566")) {// 로그인 성공
		//세션 객체에 로그인 정보를 남겨줘야함. -> 로그인 정보를 세션 객체에 바인딩 하면 된다.
			HttpSession ses= req.getSession(); // request로 부터 세션 객체를 얻어옴
			
			System.out.println("세션 id :" + ses.getId());
			
			ses.setAttribute("loginMember",userId ); // 바인딩
			resp.sendRedirect("mainTest.jsp?status=loginSuccess"); // mainTest.jsp로 보내버리기
	}
	
	}

}
