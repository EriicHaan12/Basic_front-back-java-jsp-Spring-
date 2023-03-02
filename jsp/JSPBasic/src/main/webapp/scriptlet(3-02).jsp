<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크립트릿</title>
</head>
<body>
<%
// 스크립트 릿에서 선언된 변수는_jspservice() -> 자동으로 만들어지는 서블릿의 메서드

// 의 지역변수로 만들어진다.

int num1 =3, num2 = 5;
int result = num1 + num2;
out.print("<div>" + num1 + " + " + num2 + "=" + result + "</div>");
%>
</body>
</html>