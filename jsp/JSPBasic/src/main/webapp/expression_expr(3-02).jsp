<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현식</title>
</head>
<body>
<%!
String str = "안녕하세요"; // 멤버 변수로 선언됨.

public int abs(int n){ // 멤버 메서드가 됨.
	if(n <0){
		n = -n;
	}
	return n ;
}

%>

<div><%=str%></div>
<h4><%=abs(-5) %></h4>

</body>
</html>