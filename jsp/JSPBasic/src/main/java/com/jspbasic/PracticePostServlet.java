package com.jspbasic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PracticePostServlet")
public class PracticePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public PracticePostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html; charset = utf-8");
		PrintWriter writer = response.getWriter();
		
		String userName = request.getParameter("userName");
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		
	
		writer.print("<!DOCUTYPE html>"
				+ "<head><title>"
				+ "POST 연습문제</title></head>"
				+ "<body>"
				+ "<div id='name' class =output>유저 이름 :"+userName + "</div>"
				+ "<div id='id' class =output>유저 아이디 :"+userId + "</div>"
				+ "<div id = 'pwd' class =output>유저 비밀번호 :"+userPwd + "</div>"
				+ "</body>"
				+ "</html>"
				+ "<style>"
				+ ".output:{"
				+ " font-size: 25px;"
				+ " font-weight: bold; }"
				+ "#name {color : green;}"
				+ "#id {color : gray}"
				+ "#pwd{color : brown}"
				+ "</style>");
		writer.flush();
		writer.close();
	
	}

}
