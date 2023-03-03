<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>웹 어플리케이션의 컨텍스트 패스 : <%=application.getContextPath() %></div>
	<div>웹 어플리케이션의 실제 저장 경로 : <%= application.getRealPath(request.getRequestURI()) %> </div>
	
</body>
</html>