package com.jspbasic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/pageMove.do")
public class PageMovingServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		//서블릿에서 페이지를 이동시키는 방법들
		
		//1) js 의 location.href 를 이용하는 방법
//		out.print("<script>");
//		out.print("location.href ='mainTest.jsp'");
//		out.print("</script>");
		
		//2) response 객체의 sendRedirect()를 이용하는 방법
//		resp.sendRedirect("mainTest.jsp");
		
		
		//1~2번은 쿼리 스트링을 주소 옆에 쓰지 않는 이상 데이터를 보낼 방법이 없다.
		
		//3)RequestDispatcher 객체를 이용하여 이동하는 방법
		// url 주소가 바뀌지 않는다. -> 페이지는 이동되지만 처음 url 주소가 고정되어 있다.
		// 데이터를 바인딩하여 데이터를 보낼 때 주로 사용한다.
//		req.getRequestDispatcher("mainTest.jsp").forward(req, resp);
		
		//4)meta 태그의 <meta http-equiv="refresh" content="시간(초); url= url주소"> 이용하는 방법
//		out.print("<html>");
//		out.print("<head>");
//		out.print("<meta http-equiv='refresh' content='0; url=mainTest.jsp'>");
//		out.print("</head>");
//		out.print("</html>");
//		out.flush();
//		out.close();		
	}

	
}
