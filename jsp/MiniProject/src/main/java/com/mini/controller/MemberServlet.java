package com.mini.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
//@WebServlet("/register.mem")
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mini.service.MemberService;

@WebServlet("*.mem")// .mem 으로 끝나는 모든 매핑 주소에 대해 현재의 서블릿이 동작한다는 것을 의미.
public class MemberServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}
	
	// 내가 만든 doService 으로 doGet, doPost(즉, GET방식으로 호출하든, POST 방식으로 호출하든 
	// 호출 받은 데이터 모두 doService 메소드로 받기)
private void doService(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
	System.out.println(this.getServletName()+"동작");
	System.out.println("요청한 페이지 :" + req.getRequestURL());
	System.out.println("요청한 URL :" + req.getRequestURI());
	System.out.println("요청한 통신 방식 :" + req.getMethod());
	
	String requestUri = req.getRequestURI();
	String contextPath = req.getContextPath(); // MiniProject
	String command = requestUri.substring(contextPath.length());
	
	System.out.println("요청된 서비스는 : " + command);
	
	MemberFactory mf = MemberFactory.getInstance();
	// command 넘겨 주기
	MemberService service =	mf.getService(command);
	
	if(service!=null) {
		// 받아온 MemberFactory클래스의 mf를 service의 response에 넘겨주기
		mf= service.execute(req, resp);
	}
	if(mf.isRedirect()) { // 
		resp.sendRedirect(mf.getWhereIsgo());// 페이지를 지정해준 whereIsgo로 보내주기
	}

} 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doService(req, resp);
	}

}
