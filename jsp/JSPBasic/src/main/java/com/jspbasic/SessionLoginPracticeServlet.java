package com.jspbasic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/practiceLogin&Logout.do")
public class SessionLoginPracticeServlet extends HttpServlet {
		

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
	String userId =	req.getParameter("userId");
	String userPwd =req.getParameter("userPwd");
		
	if(userId.equals("aa112")&&userPwd.equals("1122")) {
		

	
	
		HttpSession ses = req.getSession();
		
		ses.setAttribute("loginUser",userId);
		resp.sendRedirect("mainTest2(3-03).jsp?status=successLogin");
	}
	}

	
	
}
