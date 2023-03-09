package com.jspajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.erichan.dao.EmployeesDAO;
import com.erichan.dao.EmployeesDAOImpl;
import com.erichan.vo.Employees;
import com.jspajax.etc.OutputJSONForError;

@WebServlet("/saveEmp.do")
public class SaveEmployeeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("application/json; charset=utf-8"); // json 형식으로 응답 받기
		PrintWriter out = resp.getWriter();
		
		//request로 부터 넘어온 데이터를 데이터 타입 변경하여 얻어옴 (내가 얻어올 DTO 타입으로...)
		String fristName = req.getParameter("FIRST_NAME");
		String lastName = req.getParameter("LAST_NAME");
		String email = req.getParameter("EMAIL");
		String phoneNumber = req.getParameter("PHONE_NUMBER");

		String strHireDate = req.getParameter("HIRE_DATE");
		Date hireDate = Date.valueOf(strHireDate);

		String jobId = req.getParameter("JOB_ID");

		float salary = Float.parseFloat(req.getParameter("SALARY"));

		float cpmmissionPct = Float.parseFloat(req.getParameter("COMMISSION_PCT"));
		int managerId = Integer.parseInt(req.getParameter("MANAGER_ID"));
		int departmentId = Integer.parseInt(req.getParameter("DEPARTMENT_ID"));

		// 저장 할 데이터를 DTO 객체로 만들어 DAO 단으로 전송
		// 사번과 부서명 insert 할 데이터가 아니므로 초기값(0,null)으로 넣어준다.
		Employees emp = new Employees(0, fristName, lastName, email, phoneNumber, hireDate, jobId, salary,
				cpmmissionPct, managerId, departmentId, null);
		System.out.println(emp.toString());
		
		//저장 프로시저(Stored Procedure)를 사용하지 않는다면,,
		//-- 1. 사번은 1씩 증가한 값을 넣어야 하기 때문에 다음 저장될 사원의 사번을 얻어온다.
		//-- 2. 1번 과정에서 얻어온 사번과 함께 유저가 입력한 데이터를 insert
		//위 두가지 과정으로 사원을 저장해야 하므로 dao단을 2번 다녀와야 한다(한번은 select (정보를 얻기위해) 또 한번은 insert하기 위해)
		//ex) 자바 기본 때 배운 Friend 예제 참조
		
		
		//하지만, 저장 프로시저를 사용 하면 dao단을 1번만 호출하면 된다.(필요한 비지니스 로직이 DB에 저장된 개념)
		// why? 비지니스 로직이 저장 프로시저에 있기 때문에 필요한 기능을 db에 저장 시켜 끌어 바로 끌어 쓸 수 있게 된다.
		
		EmployeesDAO dao=  EmployeesDAOImpl.getInstance();
	 try {
		 // error 메세지가 String 타입이기 때문에 String으로 만들어준다
		String result = dao.insertEmp(emp);
		if(result.equals("success")) {
			JSONObject json  = new JSONObject();
			json.put("status", "success");
			String outputDate = new java.util.Date(System.currentTimeMillis()).toLocaleString();
			json.put("outputDate", outputDate);
				
			out.print(json.toJSONString());
		}else if(result.equals("error")) {
			JSONObject json  = new JSONObject();
			json.put("status", "fail");
			String outputDate = new java.util.Date(System.currentTimeMillis()).toLocaleString();
			json.put("outputDate", outputDate);
//			out.print(OutputJSONForError.outputJson());
		}
		
	} catch (NamingException | SQLException e) {
		
	OutputJSONForError.outputJson(e);
	}	
	}

}
