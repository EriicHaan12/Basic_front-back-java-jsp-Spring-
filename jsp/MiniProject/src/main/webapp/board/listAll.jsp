<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

<title>Insert title here</title>
<style type="text/css">
.board {
	margin-top: 15px;
	margin-bottom: 15px;
}

.writeBtn {
	float: right;
	margin-right: 10px;
}

.paging {
	clear: both;
}
</style>

<script type="text/javascript">

function goBoardDetail(no){
	location.href='viewBoard.bo?no='+no;
}

</script>

</head>
<body>
	<c:set var="contextPath" value="<%=request.getContextPath()%>" />
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container">

		<h4 style="margin-top: 5px;">게시판 글 목록 페이지</h4>
		<div class="board">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성 날짜</th>
						<th>조회수</th>
						<th>추천수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="board" items="${requestScope.boardList }">
						<tr onclick="goBoardDetail(${board.no });">
							<td>${board.no }</td>
							<td>${board.title }</td>
							<td>${board.writer }</td>
							<td>${board.postDate }</td>
							<td>${board.readCount }</td>
							<td>${board.likeCount }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<div class="btns">
	<!-- 출력 날짜형식을 바꾸고 싶다면...  fmt:formatDate를 쓰자 -->
			 <button type="button" class="btn btn-secondary writeBtn" onclick="location.href='writeBoard.jsp';">글쓰기</button> 

			</div>
			<div class="paging"></div>
		</div>
	</div>

	<jsp:include page="../footer(3-02).jsp"></jsp:include>
</body>
</html>