<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세션 객체</title>
</head>
<body>
<div>세션 id : <%= session.getId() %></div>
<div>세션 유효 시간 : <%=session.getMaxInactiveInterval() %></div>


</body>
</html>