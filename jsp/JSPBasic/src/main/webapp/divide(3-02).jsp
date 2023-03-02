<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page errorPage="commonError/error.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일부러 예외를 발생 시키는 페이지</title>
</head>
<body>

<!-- 그냥 수학 연산 수식만 써도 작동이 된다. -->
<%= 2/0 %> 


</body>
</html>