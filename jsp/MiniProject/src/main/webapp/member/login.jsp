<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container">
		<h1>login.jsp</h1>
		<form action="login.mem">
		<button type="submit" class="btn btn-success">로그인</button>
		</form>
		<jsp:include page="../footer(3-02).jsp"></jsp:include>
	</div>
</body>
</html>