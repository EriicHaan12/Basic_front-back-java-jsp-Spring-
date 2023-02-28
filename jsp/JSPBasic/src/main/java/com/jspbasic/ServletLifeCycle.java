package com.jspbasic;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/lifecycle")
public class ServletLifeCycle extends HttpServlet {

	int initCount = 1;
	int doGetCount = 1;
	int destroyCount = 1;
	@Override
	public void init(ServletConfig config) throws ServletException {

		//현재 서블릿이 실행되기 전에 호출되는 메서드이다.
		System.out.println("init 메서드 첫 요청에서만 호출 됨"+ this.initCount++);
		
	}
	@Override
	public void destroy() {
		//톰캣이 종료될 때만 호출되는 메서드.
		System.out.println("destory 메서드 : 톰캣이 종료될 때만 호출 됨"+ this.destroyCount++);
		
	
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
	//get 방식으로 요청 될 때마다 호출 되는 메서드
		System.out.println("doGet 메서드 : GET 방식으로 요청 될 때 만 호출 됨"+ this.doGetCount++);
	
	}

	
	
}
