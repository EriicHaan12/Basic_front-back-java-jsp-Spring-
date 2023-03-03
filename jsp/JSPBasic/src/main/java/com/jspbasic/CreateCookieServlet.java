package com.jspbasic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/createCookie.do")
public class CreateCookieServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		Cookie c = new Cookie("myCook" ,"erh" ); // 쿠키 객체 및 쿠키 생성
		c.setMaxAge(60*60*24);//하루 동안 쿠키 저장 
		//쿠키 만료일 설정 초단위 정수 값만 저장, 0으로 넣으면 쿠키가 삭제된다.
		resp.addCookie(c); // 만들어진 쿠키 객체를 response 객체에 담아 클라이언트로 전송(쿠키 저장)
		
		//현재의 세션 Id값을 session이라는 이름의 쿠키로 저장
	Cookie sesID = new Cookie("session", req.getSession().getId());
	sesID.setMaxAge(60*60*24*7);  // 만료일 설정 (7일)
	resp.addCookie(sesID);
	}

}
