package com.jspbasic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/sessionLogout.do")
public class SessiongLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SessiongLogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 로그아웃 처리 -> session 객체에 남아 있는 로그인 정보를 지운 후 session 객체 갱신
		HttpSession ses =  request.getSession();
		ses.removeAttribute("loginMember"); // 로그인 할 때 바인딩 된 정보를 삭제
		ses.invalidate(); //session객체를 무효화 시키고, 객체를 언바인딩 시키기 -> 즉 , 갱신 시키기 
		response.sendRedirect("mainTest.jsp"); // mainTest.jsp로 이동
	}



}
