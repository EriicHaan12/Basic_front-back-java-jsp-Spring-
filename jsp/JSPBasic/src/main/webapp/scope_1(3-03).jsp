<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>scope_1Page</h1>
	<%
	//각 내장 객체의 영역에 정보 바인딩
	pageContext.setAttribute("name", "page data");
	request.setAttribute("name", "request data");
	session.setAttribute("name", "session data");
	application.setAttribute("name", "application data");
	%>

	<%
	//System.out.println("scope_1page.jsp=====================");
	// 각 내장 객체의 영역에 바인딩 된 정보 확인
	out.println("pageContext : " + pageContext.getAttribute("name") + "</br>");
	out.println("request : " + request.getAttribute("name") + "</br>");
	out.println("session : " + session.getAttribute("name") + "</br>");
	out.println("application : " + application.getAttribute("name") + "</br>");

	request.getRequestDispatcher("scope_2Page(3-03).jsp").forward(request, response); // 데이터 포워딩
	%>

</body>
</html>