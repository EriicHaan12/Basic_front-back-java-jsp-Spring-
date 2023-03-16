package com.mini.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.JsonObject;
import com.mini.controller.MemberFactory;
import com.mini.dao.MemberDAOImpl;
import com.mini.error.CommonException;

public class DuplicateUserIdService implements MemberService {

	@Override
	public MemberFactory execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		MemberFactory mf = MemberFactory.getInstance();
		// 받은 데이터를 json으로 바꾸고 응답하기
		resp.setContentType("application/json; charset=utf-8"); // json 형식으로 응답
		PrintWriter out = resp.getWriter();

		String userId = req.getParameter("userId");

		System.out.println("서비스 단 : " + userId + "값이 중복되는지 검사하자");

		JSONObject json = new JSONObject();

		try {
			int result = MemberDAOImpl.getinstance().selectByUserId(userId);

			System.out.println("json으로 반환 시킬 result 값 : " + result);

			// 일단 통신이 성공한지 실패한지 확인
			json.put("status", "success");
			if (result == 0) {// 중복된 아이디가 없다
				json.put("duplicate", "no");
			} else {// 중복된 아이디가 있다
				json.put("duplicate", "yes");
			}

		} catch (NamingException | SQLException e) {

			if (e instanceof NamingException) {
				// NamingException은 개발자 실수 이기 때문에 개발자만 보도록 공통 error.jsp 에러페이지를 만들었고
				// 에러 정보를 error.jsp로 바인딩하여 error.jsp페이지에서 에러정보를 출력하였다.
				// forward
				// 프로그래머가 코드를 잘못 입력 하였을 경우
				CommonException ce = new CommonException(e.getMessage(), 99);
				// throw ce; 강제로 예외를 발생 시킴
				ce.setErrorMsg(e.getMessage());
				ce.setStackTrace(e.getStackTrace());

				req.setAttribute("error", ce); // 에러 정보를 가진 CommonException 객체를 바인딩

				req.getRequestDispatcher("../error.jsp").forward(req, resp);// 에러가 뜬 객체를 확인하고 에러페이지로 이동시키기

				// 페이지를 이동시키기(fowarding 시켜주기 ) 떄문에 return을 해줄 필요가 없다

			} else {
				json.put("status", "fail");

			}

		}
		out.print(json.toJSONString());
		out.close();
		
		// redirect 할 것이 없기 때문에 이렇게 설정 해주면 된다. 이렇게 해주지 않으면 java.lang.NullPointerException 
		mf.setRedirect(false);
		return mf;
	}

}
