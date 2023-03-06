<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="now" value ="<%= new java.util.Date() %>"/>
<div> 톰캣 서버의 기본 로케일 : <%=response.getLocale() %></div>
<div> 통화 : <fmt:formatNumber value="123456.78" type="currency" /> </div>
<div> 현재날짜시간 : <fmt:formatDate value="${now }" type="both" /> </div>





</body>
</html>