package com.jspajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import com.erichan.vo.JobsVo;
import com.google.gson.JsonObject;
import com.jspajax.etc.OutputJSONForError;

@WebServlet("/getJobsData.do")
public class GetJobsDataServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 싱글톤임을 인지해야만 한다...
		EmployeesDAO dao = EmployeesDAOImpl.getInstance();
		resp.setContentType("application/json; charset=utf-8"); // json 형식으로 응답 받기
		 PrintWriter out= resp.getWriter();
		try {
			// 요청 받은 jobs 정보를 List객체에 넣어 주기

			List<JobsVo> lst = dao.selectAlljobs();

//		for(JobsVo j : lst) {
//			System.out.println(j.toString());
//		}
			JSONObject json = new JSONObject();
			json.put("status", "success");
			String outputDate = new java.util.Date(System.currentTimeMillis()).toLocaleString();
			json.put("outputDate", outputDate);
			json.put("count", lst.size() + "");

			JSONArray jobs = new JSONArray();

			if (lst.size() > 0) {
				for (JobsVo j : lst) {
					JSONObject job = new JSONObject();
					job.put("JOB_ID", j.getJob_id());
					job.put("JOB_TITLE", j.getJob_title());
					job.put("MIN_SALARY", j.getMin_salary());
					job.put("MAX_SALARY", j.getMax_salary());
					
					jobs.add(job);
				}

				json.put("JOBS", jobs);
			}
			out.print(json.toJSONString());

		} catch (NamingException | SQLException e) {
			out.print(OutputJSONForError.outputJson(e));
		}
		out.close();
	}

}
