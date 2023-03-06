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
	pageContext.setAttribute("msg", "hello"); //이페이지에서만 살아남는 msg를 바인딩 한 것
%>

<div>${pageScope.msg}</div>
<c:set var = "msg2" value="배고파" /> 

<div><c:out value="오늘뭐먹지"/></div>
<div><c:out value="${msg}"/></div>

<div>${pageScope.msg2}</div>

<div><c:out value="오늘 뭐먹지"/></div>


<c:remove var="msg2"/>

</body>
</html>