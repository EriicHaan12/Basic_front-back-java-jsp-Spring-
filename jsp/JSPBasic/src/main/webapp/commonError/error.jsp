<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<!-- 이렇게 써줘야 에러를 처리하는 페이지다 라고 인식이 됨 -->
<!--이 후 exception(예외 처리 내장 객체)를 사용하여 예외처리가 가능해진다 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러처리 페이지(공통 예외 처리 할 때 사용)</title>
</head>
<body>
	<h1>에러 페이지</h1>
	<div>에러가 떴습니다.. 에러가 지속될 경우 우우우...</div>
	<h4>
		<%=exception.getMessage()%></h4>
	<div>
		<%=exception.getStackTrace()%>
	</div>

</body>
</html>