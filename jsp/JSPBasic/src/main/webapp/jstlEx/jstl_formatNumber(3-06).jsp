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
<div>
<fmt:formatNumber value="12345678.89"/>
</div>

<div>
<fmt:formatNumber value="0.574" type="percent"/>
</div>


<div>
<fmt:formatNumber value="123456" type="currency"/>
</div>

<div>
<fmt:formatNumber value="123456" type="currency" currencySymbol="$"/>
</div>

<div>
<fmt:formatNumber value="1234567.8" pattern ="$#,###.#" />
</div>
</body>
</html>