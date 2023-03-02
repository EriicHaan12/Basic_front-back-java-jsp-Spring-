<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import= "java.util.Calendar" %>
    <%@ page import = "java.text.SimpleDateFormat" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page 지시자</title>
</head>
<body>
<!-- 현재 날짜 출력하기 -->
<!--  jsp 내부에서는 import 를 따로 받아오는 것이 불가능 하기 떄문에 page 지시자를 써야만 import 가 가능하다 -->
<% Calendar cal = Calendar.getInstance(); 
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 mm월 dd일");
out.print(sdf.format(cal.getTime()));
%>
</body>
</html>