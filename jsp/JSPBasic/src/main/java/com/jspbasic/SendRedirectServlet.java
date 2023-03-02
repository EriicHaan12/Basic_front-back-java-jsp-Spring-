package com.jspbasic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sendRedirect.do")
public class SendRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 아이디 : abcd123
		// 비밀번호 : a1b2c3!
		//
		// 아이디와 비밀번호가 일치 하면 mainTest.jsp로 이동 시키자
		response.setContentType("text/html; charset = utf-8");
		PrintWriter out = response.getWriter();

		String userId = request.getParameter("userId");
		String password = request.getParameter("userPwd");
		if (userId.equals("abcd123") && password.equals("a1b2c3!")) {
			
			//아래의 js 코드는 효과가 없다.(responce.sendRedirect 코드가 우선 실행 됨)
//			out.print("<script>");
//			out.print("alert('로그인 성공')");
//			out.print("</script>");

			//response.sendRedirect(String url) : url 페이지로 이동
			//쿼리스트링을 달고 갈 수 있다.
			response.sendRedirect("mainTest.jsp?status=loginSuccess"); // 실행 순서가 java가 더 우선순위에 있어서
			// 위에 작성한 JS 코드들이 실행 되지 않은채 먼저 넘어간다.
		}
	}

}
