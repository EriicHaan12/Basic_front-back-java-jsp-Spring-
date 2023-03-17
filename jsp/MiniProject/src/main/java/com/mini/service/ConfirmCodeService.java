package com.mini.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.google.gson.JsonObject;
import com.mini.controller.MemberFactory;

public class ConfirmCodeService implements MemberService {

	@Override
	public MemberFactory execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String uic = req.getParameter("uic");
		System.out.println(uic);
		
		// Session에 바인딩 해둔 코드를 꺼내와야함 (보낸 코드가 일치하는지 확인 하기 위해)
		HttpSession ses = req.getSession();
		// Object를 반환하기 떄문에 비교를 위해 String으로  형변환 시켜줘야함
		String confrimCode =  (String)ses.getAttribute("confirmCode");
		
		
		resp.setContentType("application/json; charset=utf-8"); // json 형식으로 응답
		PrintWriter out = resp.getWriter();
		
		JSONObject json = new JSONObject();
		
		
		if(uic.equals(confrimCode)) {
			//이메일 인증 성공
			
			json.put("status","success");
			
		}else {
			// 이메일 인증 실패 
			json.put("status","fail");
		}
		out.print(json.toString());
		out.close();
		
		return null;
	}

}
