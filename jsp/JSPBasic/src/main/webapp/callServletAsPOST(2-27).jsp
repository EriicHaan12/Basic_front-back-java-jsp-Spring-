<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>POST 방식으로 서블릿을 요청해보자</title>
</head>
<body>
<form action="HelloServlet_POST" method="post">

<div>아이디 : <input type="text" name="userId"></div>
<div>패스워드 : <input type="password" name="userPwd"></div>

<input type="submit" value="전송"/>
</form>
</body>
</html>