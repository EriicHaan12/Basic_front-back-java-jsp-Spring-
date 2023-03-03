package com.jspbasic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/sessiongLogout2.do")
public class SessionLogoutPracticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public SessionLogoutPracticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	HttpSession ses = request.getSession();
	ses.removeAttribute("loginUser");
	ses.invalidate();
	response.sendRedirect("mainTest.jsp");
		
	}

	

}
