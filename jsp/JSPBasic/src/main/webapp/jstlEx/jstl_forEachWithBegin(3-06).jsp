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
<!-- step 이 생략되면 1씩 증감으로 된다 -->
<c:forEach var="i" begin="1" items="9" >
	<div>${i }번째 div</div>
	
	<c:forEach var="dan" begin="1" end="9">
	
	<c:forEach  var="i" begin="1" end="9"> 
		${dan}* ${i } =${dan*i} &nbsp;&nbsp;&nbsp; 
	</c:forEach>
	</c:forEach>

</c:forEach>
</body>
</html>