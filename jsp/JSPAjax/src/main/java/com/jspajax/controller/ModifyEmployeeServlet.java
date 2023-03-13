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
@WebServlet("/modifyEmp.do")
public class ModifyEmployeeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		int empNo = Integer.parseInt(req.getParameter("EMPLOYEE_ID"));
		resp.setContentType("application/json; charset=utf-8"); 
		PrintWriter out = resp.getWriter();
		
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

		//업데이트 하기 위한 사번은 입력받아야하기 때문에 empNo를 따로 넣어준다.
		Employees emp = new Employees(empNo, fristName, lastName, email, phoneNumber, hireDate, jobId, salary,
				cpmmissionPct, managerId, departmentId, null);
		System.out.println(emp.toString());
		
	  EmployeesDAO dao = EmployeesDAOImpl.getInstance();
		
	  try {
		if(	dao.updateEmployee(emp)==1) {
			JSONObject json  = new JSONObject();
			json.put("status", "success");
			String outputDate = new java.util.Date(System.currentTimeMillis()).toLocaleString();
			json.put("outputDate", outputDate);
				
			out.print(json.toJSONString());
		}	else {
			JSONObject json  = new JSONObject();
			json.put("status", "fail");
			String outputDate = new java.util.Date(System.currentTimeMillis()).toLocaleString();
			json.put("outputDate", outputDate);
		}	
		
		
	} catch (NamingException | SQLException e) {
		out.print(OutputJSONForError.outputJson(e));
		}
		
	}

}
