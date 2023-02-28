package com.jspbasic;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspbasic.vo.PracticeVo;
@WebServlet("/prctice.do")
public class ProductInputDataServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String prodName = req.getParameter("prodName");
		int amount = Integer.parseInt(req.getParameter("amount"));
		int price = Integer.parseInt(req.getParameter("price"));
		String color = req.getParameter("color");
	//바인딩
		PracticeVo pv = new PracticeVo(prodName, amount, price, color);
		
		req.setAttribute("pv", pv);
		
		RequestDispatcher rd =req.getRequestDispatcher("practiceOutputProduct.jsp");
		rd.forward(req, resp);// 전송
	}

}
