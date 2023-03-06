<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String[] heros = { "캡틴 아메리카", "아이언맨", "스파이더맨" };

	pageContext.setAttribute("heros", heros);
	%>

	<ul>
		<c:forEach var ="hero" items=" ${heros}">

			<li>${hero}</li>

		</c:forEach>
	</ul>
</body>
</html>