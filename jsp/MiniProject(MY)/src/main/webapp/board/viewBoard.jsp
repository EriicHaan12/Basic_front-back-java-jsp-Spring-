<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세 조회 페이지</title>
</head>
<body>
	<c:set var="contextPath" value="<%=request.getContextPath()%>" />
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container">
		<h4 style="margin-top: 5px;">게시판 상세 조회 페이지</h4>
		<div class="mb-3 mt-3">
			<label for="userId">번호 : </label> <input type="text"
				class="form-control" id="boardNo" value="${requestScope.board.no }"
				readonly />
		</div>

		<div class="mb-3 mt-3">
			<label for="userId">작성자 : </label> <input type="text"
				class="form-control" id="writer"
				value="${requestScope.board.writer }" readonly />
		</div>
		<div class="mb-3 mt-3">
			<label for="title">작성일 : </label> <input type="text"
				class="form-control" id="title"
				value="${requestScope.board.postDate }" readonly />
		</div>

		<div class="mb-3 mt-3">
			<label for="title">제 목 : </label> <input type="text"
				class="form-control" id="title" value="${requestScope.board.title }"
				readonly />
		</div>

		<div class="mb-3 mt-3">
			<label for="title">조회수 : </label> <input type="text"
				class="form-control" id="title"
				value="${requestScope.board.readCount }" readonly />
		</div>
		<div class="mb-3 mt-3">
			<label for="title">추천수 : </label> <input type="text"
				class="form-control" id="title"
				value="${requestScope.board.likeCount }" readonly />
		</div>

		<div class="form-check">
			<label for="content">본 문 :</label>
			<div>${requestScope.board.content }</div>
		</div>

		<div class="form-check">

			<c:if test="${requestScope.board.imgFile!='' }">
				<div>
					<img src="${contextPath }/${requestScope.board.imgFile }" />
				</div>
			</c:if>
		</div>

		<div class="btns">
			<button type="button" class="btn btn-success" onclick="">수정</button>
			<button type="button" class="btn btn-success">삭제</button>
			<button type="button" class="btn btn-info">답글 달기</button>
			<button type="button" class="btn btn-warning"
				onclick="location.href='listAll.bo';">목록으로</button>
		</div>
	</div>
	<jsp:include page="../footer(3-02).jsp"></jsp:include>
</body>
</html>