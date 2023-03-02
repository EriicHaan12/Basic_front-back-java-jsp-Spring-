package com.jspbasic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//3-02
@WebServlet("/getParamNames.do")
public class GetParameterNamesServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		Enumeration<String> params = req.getParameterNames();// enumeration으로 반환

		Map<String, List<String>> map = new HashMap<>();
		while (params.hasMoreElements()) { // 다음 요소가 있을 동안 반복
			String paramName = (String) params.nextElement();// 넘겨진 파라메터 이름 얻기

			String value = req.getParameter(paramName); // 그 파라메터에 대한 value 얻기

			System.out.println(paramName + ":" + value);
			List<String> values = new ArrayList<>();
			values.add(value);
			map.put(paramName, values);
		}
			String param1 = "";
			if( map.containsKey("userId")) {
				List<String> value = map.get("userId");
				param1 = value.get(0);
			}
			System.out.println(param1);
		}

}
