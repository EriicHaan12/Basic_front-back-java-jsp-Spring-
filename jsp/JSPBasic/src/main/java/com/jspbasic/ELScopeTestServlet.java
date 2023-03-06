package com.jspbasic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspbasic.vo.TestMemberVo;

@WebServlet("/elScope.do")
public class ELScopeTestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num1 = 5, num2 = 3;
		int result = num1 - num2;
		
		
		TestMemberVo member = new TestMemberVo("abc", "1234", "010-1234-5678","male", null, 20, null, null, null);
		
		req.setAttribute("num1", num1);
		req.setAttribute("num2", num2);
		req.setAttribute("member", member);
		//session 저장
		HttpSession ses = req.getSession();
		ses.setAttribute("result",result);
		
		//포워드 시키기
		//응답을 미루기, elScope.jsp 로 응답하도록 만드는 작업
		req.getRequestDispatcher("elScope(3-06).jsp").forward(req, resp);;
	}

}
