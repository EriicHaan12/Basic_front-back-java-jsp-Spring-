<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
<%--<%request.getParameter("nickName")%>이 좋아하는 계절은  --%>   

    ${param.nickName }이 좋아하는 계절은

<!--내장 객체로 표현했을 때 <%=request.getParameter("favSeason") %>  --> 

<!--EL 태그로도 입력 받은 parameter을 받아 출력 할 수 있다.-->
${param.favSeason }

입니다.
</div>
</body>
</html>