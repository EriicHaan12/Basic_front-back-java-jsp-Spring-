<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>session 객체를 통한 로그인</title>
</head>
<body>
	<form action="practiceLogin&Logout.do" method="post">
		<div>
			아이디 : <input type="text" name="userId">
		</div>
		<div>
			패스워드 : <input type="password" name="userPwd">
		</div>
		<div>
			<input type="reset" value="취소">
		</div>
		<div>
			<input type="submit" value="로그인">
		</div>
	</form>

</body>
</html>