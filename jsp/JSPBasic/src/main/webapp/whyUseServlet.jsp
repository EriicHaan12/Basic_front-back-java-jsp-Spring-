<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>왜 Servlet을 사용하는가? here</title>
<style type="text/css">

h1{
background-color: blue;
}
</style>
<script type="text/javascript">

function hello(){
	alert("!");
}
</script>
</head>
<body>
<h1 onclick="hello();">왜 Servlet을 사용하는가</h1>


<!-- 변수에 임의의 값을 저장 한 후, 그 값이 홀수이면
파란색 글씨로 "홀수 입니다"라고 출력하고, 짝수이면 빨간색 글씨로 "짝수 입니다"라고 출력해 보자
 -->

<% 
// 자바 문법을 쓸 수 있는 곳...
int num =3;
out.print(num); // System.out 은 console 창에 띄우는 용/ out.print는 웹에 띄우는 용

if(num %2 == 0){  //짝수
	%>
	<div style="color:red">짝수입니다.</div>
	<%
} else{
	%> 
	<div style="color:blue">홀수 입니다.</div>
<%
}


%>  

</body>
</html>