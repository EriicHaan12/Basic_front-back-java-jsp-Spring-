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
	String hobbies = "낮잠, 음악감상, 영화 감상, 맛집 투어, 공부";
	pageContext.setAttribute("hobbies", hobbies);
	
%>
<ul>
<c:forTokens   var="hobby" items="${hobbies }" delims=",">

<li>${hobby }</li>
</c:forTokens>

</ul>
<c:redirect url="../mainTest.jsp"></c:redirect>
</body>
</html>