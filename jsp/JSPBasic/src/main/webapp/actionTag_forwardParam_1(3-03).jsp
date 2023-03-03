<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>아이디 : <%=request.getParameter("userId") %></div>
<div>나이 : <%=request.getParameter("age") %></div>
<div>이름 : <%=request.getParameter("name") %></div>

<hr/>
<!-- EL로 다시 코딩해보기 -->

<div>아이디 : ${param.userId } </div>
<div>나이 : ${param.age }</div>
<div>이름 : ${param.name}</div>

</body>
</html>