package com.jspbasic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
// 매핑주소(현재 서블릿이 실행되는 요청 이름)
public class HelloServlet_GET extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet_GET() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    /**
     * 
     * 클라이언트에서 Get방식으로 요청하면 호출되는 메서드
     *GET 방식으로 요청하는 방법
     *- url 주소표지술에 현재 페이지를 기술하고 엔터를 쳤을 경우 
     *- a 태그가 클릭되어 현재 페이지가 로딩된 경우
     *- form태그에서 method="GET" 으로 요청된 경우
     *- js에서 lociton.href='현재페이지'로 이동된 경우
     *- js에서 ajax를 통해 get 방식으로 데이터를 전송했을 경우  
     */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("HelloServlet_GET");
		System.out.println("GET 방식으로 요청됨");
		
		//GET 방식으로 요청하면서 전달한 파라메터를 얻어옴
		int kor = Integer.parseInt(request.getParameter("kor")); 
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		System.out.println(kor + "," + eng + ","+ math);
		
		
		int tot = kor+ eng+ math;
		float avg = tot / 3f;
		System.out.println(tot + "," + avg);
		
		
		//국어, 영어, 수학 , 총점, 평균을 유저가 확인 할 수 있는 웹 페이지로 출력
		//응답할 문서의 종류와 인코딩 방식을 설정
		response.setContentType("text/html; charset = utf-8"); // 미리 response 로 응답 받을 객체의 타입을 지정해주는 법
		
		
	PrintWriter out = response.getWriter(); //출력할 수 있는 객체
	out.print("<!DOCTYPE html>"
			+ "<html >"
			+ "<head>"
			+ "<meta charset='UTF-8'>"
			+ "<title>성적표</title>"
			+ "</head>"
			+ "<body>"
			+ "<div>국어 :" + kor 
			+ "</div>"
			+ "<div>영어 :" + eng 
			+ "</div>"
			+ "<div>수학 :" + math 
			+ "</div>"
			+ "<div>총점 :" + tot 
			+ "</div>"
			+ "<div>평균 :" + avg 
			+ "</div>"
			+"</body></html>");
	
		out.flush();
		out.close();
	}

	/**
	 * 클라이언트가 post 방식으로 요청했을 경우 실행되는 메서드
	 * form태그에서 method="GET" 으로 요청된 경우 
	 * js에서 ajax를 통해 get 방식으로 데이터를 전송했을 경우
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("HelloServlet_POST");
		System.out.println("POST 방식으로 요청됨");
	}

}
