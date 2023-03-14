package com.jspajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.erichan.dao.EmployeesDAO;
import com.erichan.dao.EmployeesDAOImpl;
import com.erichan.vo.Employees;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jspajax.etc.OutputJSONForError;

@WebServlet("/getAllemployees.do")
public class GetAllEmployeesServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//응답받을 데이터 타입
		resp.setContentType("application/json; charset=utf-8"); // json 형식으로 응답 받기
		 PrintWriter out= resp.getWriter();
		 
		  String searchName =  req.getParameter("searchName");
		String sortBy = req.getParameter("orderBy");
		
		  System.out.println(sortBy);
		 System.out.println(searchName);
		 
		EmployeesDAO dao = EmployeesDAOImpl.getInstance();
		try {
		
			
		List<Employees> lst = dao.selectAllEmp(searchName, sortBy);
		//Gson 라이브러리를 이용한 json 변환하기.
//	String outputJson=	toJsonWithGson(lst);
		
	
	//json- simple 라이브러리를 이용해서 받아온 resp 데이터를 json으로 변환하기
	String outputJson=	toJsonWihtJsonSimple(lst);
		
		
//		System.out.println(json2);
		
//		for (Employees e : lst) {
//			System.out.println(e.toString());
//		}

		out.print(outputJson);
		
		
		} catch (NamingException | SQLException e) {
//			//NamingException : DB연결에 문제 날 경우, context 객체나 설정 오류 일때 에러가 남
//			//SQLException : NamingException 보다 범위가 더 넓은 에러로 DB 문제일 수도 있고 SQL 문제일 수도 있다.
//			
//			// 데이터를 넘길 때 에러가 날경우 출력될 json 만들기
//			JSONObject json  = new JSONObject();
//			json.put("status", "fail");
//			json.put("errorMsg", e.getMessage());
//			String outputDate = new java.util.Date(System.currentTimeMillis()).toLocaleString(); 
			
			// 만든 클래스의 메서드를 출력
			out.print(OutputJSONForError.outputJson(e));
			
			
		}
		out.close();
	}

	private String toJsonWihtJsonSimple(List<Employees> lst) {
		//Json-simple  라이브러리를 이용한 json으로 데이터 변환
		//JSONObject : (Json-simple에서) 객체를 의미한다 
		//JSONArray : (Json-simple에서)배열을 의미
		
		JSONObject json = new JSONObject();
		//json-simple을 이용한 json 내부 디자인 꾸미기
		json.put("status", "success");
		String outputDate = new java.util.Date(System.currentTimeMillis()).toLocaleString(); // 현재시간을 출력해 문자열로 만들기
		//현재 시간 넣기
		json.put("output",outputDate);
		// 데이터 갯수 넣기
		json.put("count", lst.size()+"");
		//Object이기 때문에 String으로 변환 후 반환
		
		//받은 데이터를 넣기 위한 빈배열 만들기
		JSONArray employees =new JSONArray();
		
		// 데이터가 하나라도 있을 때 반복문을 돌리도록 설정
		if(lst.size()>0) {
			
			for (Employees e : lst) {
				// 각각의 데이터를 한 객체에다 넣어주기 위한 객체
				JSONObject employee = new JSONObject();
				
				employee.put("EMPLOYEE_ID", e.getEmployee_id()+""); 
				// int 타입은 문자열로 바꿔줘야 되기 때문에 ""를 더해줘서 자연스레 문자열로 반환되도록 한다
				employee.put("FIRST_NAME", e.getFirst_name());
				employee.put("LAST_NAME", e.getLast_name());
				employee.put("EMAIL", e.getEmail());
				employee.put("PHONE_NUMBER", e.getPhone_number()+"");
				
				Date tempDate = e.getHire_date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
				employee.put("HIRE_DATE", sdf.format(tempDate)); 
				
//				employee.put("HIRE_DATE", e.getHire_date().toLocaleString()); 
				//Date 타입은 이렇게 반환 // 원래라면 Calendar 클래스를 이용해야함
				employee.put("JOB_ID", e.getJob_id());
				employee.put("SALARY", e.getSalary()+"");
				employee.put("COMMISSION_PCT", e.getCommission_pct()+"");
				employee.put("MANAGER_ID", e.getManager_id()+"");
				employee.put("DEPARTMENT_ID", e.getDepartment_id()+"");
				employee.put("DEPARTMENT_NAME", e.getDepartement_name()+"");
			
				//employees 라는 데이터를 담는 전체 배열에다가 각 데이터 employee 객체 데이터를 넣어주기
				employees.add(employee);
			}
		}
		
		
		
		// 마지막 전체 json 안에 만든 employees 배열 넣어주기
		json.put("employees", employees);
		
		return json.toJSONString();
	}

	private String toJsonWithGson(List<Employees> lst) {
		//Gson 라이브러리를 이용한 json으로 데이터 변환
		Gson gson = new Gson();
		Type type = new TypeToken<List<Employees>>(){}.getType();
		String outputJson = gson.toJson(lst, type); //받아온 json 타입 문자열
		return outputJson;
	}

}
