package com.jspajax.etc;

import org.json.simple.JSONObject;
//에러, 예외가 났을 때 처리를 한꺼번에 하기 위해
public class OutputJSONForError {
	//Exception e 는 다형성
	public static String outputJson(Exception e) {
		//NamingException : DB연결에 문제 날 경우, context 객체나 설정 오류 일때 에러가 남
		//SQLException : NamingException 보다 범위가 더 넓은 에러로 DB 문제일 수도 있고 SQL 문제일 수도 있다.
		
		// 데이터를 넘길 때 에러가 날경우 출력될 json 만들기
		JSONObject json  = new JSONObject();
		json.put("status", "fail");
		json.put("errorMsg", e.getMessage());
		String outputDate = new java.util.Date(System.currentTimeMillis()).toLocaleString(); 
	
		return json.toJSONString();
	}
}
