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
	<c:set var="now" value="<%=new java.util.Date()%>" />
	<div>${now }</div>
	<div>
		<fmt:formatDate value="${now }" />
	</div>
<div>
	time:<fmt:formatDate value="${now }" type="time"/>
</div>
<div>
	date:<fmt:formatDate value="${now }" type="date"/>
</div>
<div>
	both:<fmt:formatDate value="${now }" type="both"/>
</div>
<div>
	time:<fmt:formatDate value="${now }" type="time" timeStyle="medium"/>
</div>
<div>
	date:<fmt:formatDate value="${now }" type="date" timeStyle="long"/>
</div>
<div>
	pattern:<fmt:formatDate value="${now }" pattern="yyyy-MM-dd hh:mm:ss"/>
</div>



</body>
</html>