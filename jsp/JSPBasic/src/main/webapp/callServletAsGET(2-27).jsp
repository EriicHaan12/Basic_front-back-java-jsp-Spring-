<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GET방식으로 Servlet 호출하기</title>
</head>
<body>
<form action="HelloServlet_GET" method="get">
<div> 국어 : <input type="text" name="kor"></div>
<div> 영어 : <input type="text" name="eng"></div>
<div> 수학 : <input type="text" name="math"></div>	
<input type="submit" value="전송">
</form>
</body>
</html>