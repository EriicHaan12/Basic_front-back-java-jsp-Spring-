<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

<title>Insert title here</title>

<script>

function goBoardDetail(no){
	location.href="viewBoard.bo?no=" + no;
	}
	
function getVpc(){
	let vpc = '${param.viewPost}';
	if(vpc ==''){
		vpc=3;
	}
	$(".viewPostCnt").val(vpc);// select 태그의 value 값을 변경
}
	
	
	$(function(){
		getVpc();
		$(".viewPostCnt").change(function(){
		vpc = $(this).val();
		location.href= "listAll.bo?pageNo=${param.pageNo}&viewPost="+vpc;
		});
	});
	
</script>
<style>
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

.replyImg {
	width: 15px;
}
</style>


</head>
<body>
	<c:set var="contextPath" value="<%=request.getContextPath()%>" />
	<c:if test="${requestScope.boardList ==null}">
		<c:redirect url="listAll.bo"></c:redirect>
	</c:if>

	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container">

		<h4 style="margin-top: 5px;">게시판 글 목록 페이지</h4>

		<div>
			<select class="viewPostCnt" name="viewPostCnt">
				<option value="3" selected>3개씩</option>
				<option value="5">5개씩</option>
				<option value="10">10개씩</option>
				<option value="20">20개씩</option>
			</select>
		</div>

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
						<th>ref</th>
						<th>step</th>
						<th>refOrder</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="board" items="${requestScope.boardList }">
						<tr onclick="goBoardDetail(${board.no });">

							<td>${board.no }</td>
							<td><c:if test="${board.step>0 }">
									<c:forEach var="i" begin="1" end="${board.step }" step="1">
										<img src="${contextPath }/images/reply.png" class="replyImg" />
									</c:forEach>
								</c:if> ${board.title }</td>
							<td>${board.writer }</td>
							<td>${board.postDate }</td>
							<td>${board.readCount }</td>
							<td>${board.likeCount }</td>
							<td>${board.ref }</td>
							<td>${board.step }</td>
							<td>${board.refOrder}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="btns">
				<!-- 출력 날짜형식을 바꾸고 싶다면...  fmt:formatDate를 쓰자 -->
				<button type="button" class="btn btn-secondary writeBtn"
					onclick="location.href='writeBoard.jsp';">글쓰기</button>
			</div>
			${requestScope.pagingInfo }
			<br/>
			<hr>
		requestScope.pagingInfo=  ${requestScope.pagingInfo}
			<div class="paging">
				<ul class="pagination pagination-sm">
					<c:if
						test="${requestScope.pagingInfo.startNumOfCurrentPagingBlock > 1}">
						<li class="page-item"><a class="page-link"
							href="listAll.bo?pageNo=${param.pageNo - 1}&viewPost=${param.viewPost}">Previous</a></li>
					</c:if>
					<c:forEach var="i"
						begin="${requestScope.pagingInfo.startNumOfCurrentPagingBlock }"
						end="${requestScope.pagingInfo.endNumOfCurrentPagingBlock}"
						step="1">
						<c:choose>
							<c:when test="${requestScope.pagingInfo.pageNo ==i }">
								<li class="page-item active"><a class="page-link"
									href="listAll.bo?pageNo=${i}&viewPost=${param.viewPost}">${i}
								</a></li>
							</c:when>

							<c:otherwise>
								<li class="page-item"><a class="page-link"
									href="listAll.bo?pageNo=${i}&viewPost=${param.viewPost}">${i}
								</a></li>
							</c:otherwise>
						</c:choose>

					</c:forEach>
					<c:if test="${param.pageNo < requestScope.pagingInfo.endNumOfCurrentPagingBlock}">
						<li class="page-item"><a class="page-link"
							href="listAll.bo?pageNo=${param.pageNo + 1}&viewPost=${param.viewPost}">Next</a></li>
					</c:if>
					
				</ul>
			</div>
		</div>
	</div>

	<jsp:include page="../footer(3-02).jsp"></jsp:include>
</body>
</html>