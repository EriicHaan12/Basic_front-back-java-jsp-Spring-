package com.jspajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

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

@WebServlet("/getEmployee.do")
public class GetEmployeeServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		int empNo = Integer.parseInt(req.getParameter("empNo"));
		resp.setContentType("application/json; charset=utf-8"); // json 형식으로 응답 받기
		PrintWriter out = resp.getWriter();

		EmployeesDAO dao = EmployeesDAOImpl.getInstance();

		try {
			Employees e = dao.selectEmployeeByEmpNo(empNo);
		
			if (e != null) {

				JSONObject json = new JSONObject();
				json.put("status", "success");
				String outputDate = new java.util.Date(System.currentTimeMillis()).toLocaleString();
				json.put("outputDate", outputDate);
				
				//전체 사원이 아닌 사원 한명 데이터만 출력
				

				// 각각의 데이터를 한 객체에다 넣어주기 위한 객체
				JSONObject employee = new JSONObject();
				employee.put("EMPLOYEE_ID", e.getEmployee_id() + "");
				// int 타입은 문자열로 바꿔줘야 되기 때문에 ""를 더해줘서 자연스레 문자열로 반환되도록 한다
				employee.put("FIRST_NAME", e.getFirst_name());
				employee.put("LAST_NAME", e.getLast_name());
				employee.put("EMAIL", e.getEmail());
				employee.put("PHONE_NUMBER", e.getPhone_number() + "");

				Date tempDate = e.getHire_date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				employee.put("HIRE_DATE", sdf.format(tempDate));

//				employee.put("HIRE_DATE", e.getHire_date().toLocaleString()); 
				// Date 타입은 이렇게 반환 // 원래라면 Calendar 클래스를 이용해야함
				employee.put("JOB_ID", e.getJob_id());
				employee.put("SALARY", e.getSalary() + "");
				employee.put("COMMISSION_PCT", e.getCommission_pct() + "");
				employee.put("MANAGER_ID", e.getManager_id() + "");
				employee.put("DEPARTMENT_ID", e.getDepartment_id() + "");
				employee.put("DEPARTMENT_NAME", e.getDepartement_name() + "");

				json.put("employee", employee);

				out.print(json.toJSONString());
			}

		} catch (NamingException | SQLException e) {
		
			out.print(OutputJSONForError.outputJson(e))	;
		}
		out.close();
	}
	

}
