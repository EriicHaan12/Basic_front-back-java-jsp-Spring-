<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>elScope.jsp</h1>

<!-- request 영역, 범위(scope)에 있는 데이터를 꺼내올 때-->
	<div> num1 : ${requestScope.num1 }</div>
	<div> num1 : ${requestScope.num2 }</div>
	<!-- session 영역에 있는 데이터를 꺼내올 때-->
	<div> result : ${sessionScope.result }</div>
	
	<div>member : ${requestScope.member }</div>
	
</body>
</html>