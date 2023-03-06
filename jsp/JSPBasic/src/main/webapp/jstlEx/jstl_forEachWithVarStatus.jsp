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
	<%
	String[] heros = { "캡틴 아메리카", "아이언맨", "스파이더맨" };

	pageContext.setAttribute("heros", heros);
	%>

	<table border="1">
		<tr>
			<th>index</th>
			<th>count</th>
			<th>hero</th>
		</tr>
		<!-- 짝수 번째 tr의 배경을 노란색으로 -->
		<c:forEach var="hero" items="${heros }" varStatus="status">
			<tr>
				<c:choose>
					<c:when test="${status.index%2==0 }">
						<td style="background-color: yellow">${status.index }</td>
					</c:when>
					<c:otherwise>
						<td>${status.index}</td>
					</c:otherwise>
				</c:choose>
				<td>${status.count }</td>
				<td>${hero }</td>
			</tr>
		</c:forEach>

	</table>



	<table border="1">
		<!-- 첫번째 tr에 글자색을 빨간색, 첫번째 tr이 아니면 검정색 -->
		<tr>
			<th>index</th>
			<th>count</th>
			<th>hero</th>
		</tr>
		<c:forEach var="hero" items="${heros }" varStatus="status">
			<c:choose>
				<c:when test="${status.first}">
					<tr style="color: red">


						<c:choose>
							<c:when test="${status.index%2==0 }">
								<td style="background-color: yellow">${status.index }</td>
							</c:when>
							<c:otherwise>
								<td>${status.index}</td>
							</c:otherwise>

						</c:choose>
						<td>${status.count }</td>
						<td>${hero }</td>
					</tr>
				</c:when>
				<c:otherwise>

					<tr>
						<c:choose>
							<c:when test="${status.index%2==0 }">
								<td style="background-color: yellow">${status.index }</td>
							</c:when>
							<c:otherwise>
								<td>${status.index}</td>
							</c:otherwise>
						</c:choose>
						<td>${status.count }</td>
						<td>${hero }</td>
					</tr>

				</c:otherwise>
			</c:choose>
		</c:forEach>

	</table>



</body>
</html>