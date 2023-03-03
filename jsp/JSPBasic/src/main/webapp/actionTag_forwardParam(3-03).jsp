<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
request.setCharacterEncoding("utf-8");
%>

<jsp:forward page="actionTag_forwardParam_1(3-03).jsp">
<jsp:param value="hchch" name="userId"/>
<jsp:param value="20" name="age"/>
<jsp:param value="홍찰찰" name="name"/>
</jsp:forward>
</body>
</html>