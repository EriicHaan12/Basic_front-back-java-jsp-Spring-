package com.jspbasic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ValidationInServlet")
public class ValidationInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ValidationInServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("HelloServlet_GET");
		System.out.println("GET 방식으로 요청됨");

		// 응답할 문서의 종류와 인코딩 방식을 설정
		response.setContentType("text/html; charset = utf-8"); // 미리 response 로 응답 받을 객체의 타입을 지정해주는 법
		PrintWriter out = response.getWriter(); // 출력할 수 있는 객체

		// GET 방식으로 요청하면서 전달한 파라메터를 얻어옴
		int kor = Integer.parseInt(request.getParameter("kor"));
		if (kor < 0 || kor > 100) {

			// 아래 코드는 국어점수를 유효성 검사 하는 부분이다.
			out.print("<script>");
			out.print("alert('국어 점수가 잘못 입력 되었습니다!')");
			out.print("location.href = 'ValidationInServlet.jsp');");
			out.print("</script>");
		}

		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		System.out.println(kor + "," + eng + "," + math);

		int tot = kor + eng + math;
		float avg = tot / 3f;
		System.out.println(tot + "," + avg);

		// 국어, 영어, 수학 , 총점, 평균을 유저가 확인 할 수 있는 웹 페이지로 출력

		out.print("<!DOCTYPE html>" + "<html >" + "<head>" + "<meta charset='UTF-8'>" + "<title>성적표</title>" + "</head>"
				+ "<body>" + "<div>국어 :" + kor + "</div>" + "<div>영어 :" + eng + "</div>" + "<div>수학 :" + math + "</div>"
				+ "<div>총점 :" + tot + "</div>" + "<div>평균 :" + avg + "</div>" + "</body></html>");

		out.flush();
		out.close();
	}

}
