package com.jspbasic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/readCook.do")
public class ReadCookieServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("text/html; charset = utf-8");
		PrintWriter out =	resp.getWriter();
		Cookie[] cooks=	req.getCookies();
	
		// 이름을 알고 있는 쿠키의 값만 출력
		// 그 이름의 쿠키가 있다면, 그 쿠키의 값을 뽑아오는 과정
	for (Cookie c : cooks) {
		if(c.getName().equals("myCook")) {
			c.getValue();
		out.print(c.toString());
		}
	}
	
	out.print("<hr/>");
	// 모든 쿠키 다 출력
	for (Cookie c : cooks) {
		out.print(c.getName()+ " : "+ c.getValue()+"</br>");
		
		
	}
	
	// 이름이 myCook 인 쿠키 삭제
	for (Cookie c : cooks) {
		out.print(c.getName()+ " : "+ c.getValue()+"</br>");
		if(c.getName().equals("myCook")) { // myCook이라는 쿠키 찾아서 
			c.setMaxAge(0); 	// 만료일의 값을 0으로 설정
		resp.addCookie(c);
		}
		
	}
	}

}
