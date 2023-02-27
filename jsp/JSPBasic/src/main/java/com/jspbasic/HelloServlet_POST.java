package com.jspbasic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloServlet_POST") // 매핑 (mapping 주소)
public class HelloServlet_POST extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// GET방식으로 넘겨받는게 없으면 없어도 상관 없다.

//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
////		super.doGet(req, resp);
//		System.out.println("GET 방식으로 요청됨");
//		
//	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		super.doPost(req, resp);
		System.out.println("POST 방식으로 요청됨");

		req.setCharacterEncoding("utf-8"); // request 객체 인코딩 설정
		
		String userId = req.getParameter("userId");
		String userPwd = req.getParameter("userPwd");

		resp.setContentType("text/html; charset = utf-8"); //response 객체 인코딩 설정
		PrintWriter writer = resp.getWriter();

		writer.print("<!DOCUTYPE html>");
		writer.print("<html>");
		writer.print("<head>");
		writer.print("<title>");
		writer.print("POST방식 연습");
		writer.print("</title>");
		writer.print("</head>");
		writer.print("<body>");
		writer.print("<div>입력하신 아이디 : " + userId + ", 비밀번호 : " + userPwd + "</div>");
		writer.print("</body>");
		writer.print("</html>");

		writer.flush();
		writer.close();
	}

}
