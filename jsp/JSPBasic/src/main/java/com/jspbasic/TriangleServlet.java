package com.jspbasic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TriangleServlet")
public class TriangleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public TriangleServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	int base = Integer.parseInt(request.getParameter("base"));
	int height = Integer.parseInt(request.getParameter("height"));		
	
	float area = (base * height )/2f;
	response.setContentType("text/html; charset = utf-8");
	
	PrintWriter output = response.getWriter();
	
	output.print("<!DOCTYPE html>"
			+ "<html >"
			+ "<head>"
			+ "<meta charset='UTF-8'>"
			+ "<title>성적표</title>"
			+ "</head>"
			+ "<body>"
			+ "<div>밑변 :" + base +"cm"
			+ "</div>"
			+ "<div>높이 :" + height+"cm"
			+ "</div>"
			+ "<div>넓이 :" + area +"cm²"
			+ "</div>"
			+"</body></html>");
	output.flush();
	output.close();
	}

	
	
}
