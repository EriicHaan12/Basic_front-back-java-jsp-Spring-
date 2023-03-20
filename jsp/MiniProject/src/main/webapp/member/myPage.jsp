<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script type="text/javascript">
	function showBasic() {
		$(".card").show();
		//$(".memberPoint").hide();
	}

	function showPoint() {
		$(".card").hide();
		$(".memberPoint").show();
	}
</script>
<style type="text/css">
.memberPoint {
	display: none;
}

.profileImg {
	width: 100px;
}
</style>
</head>
<body>
	<c:set var="contextPath" value="<%=request.getContextPath()%>" />
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container">

		<ul class="nav">
			<li class="nav-item"><a class="nav-link active" href="#"
				onclick="showBasic();">기본정보</a>
				<div class="card" style="width: 400px; margin: 20px auto;">
					<div class="profileImg">
						<img class="card-img-top"
							src="${contextPath }/${memberInfo.userImg }"
							alt="${memberInfo.userId }">
					</div>

					<div class="card-body">
						<h4 class="card-title">${memberInfo.userId }</h4>
						<p class="card-text">
						<div class="userEmail">${memberInfo.userEmail }</div>
						<div class="userMobile">${memberInfo.userMobile }</div>
						<div class="userGender">${memberInfo.userGender}</div>
						<div class="userHobbies">${memberInfo.hobbies }</div>
						<div class="job">${memberInfo.job }</div>
						<div class="memo">${memberInfo.memo }</div>
						</p>
						<a href="#" class="btn btn-primary">See Profile</a>
					</div>
				</div></li>

			<li class="nav-item"><a class="nav-link memberPoint" href="#"
				onclick="showPoint();">포인트적립내역</a>
				<div class="memberPoint"></div>

				<table class="table table-striped">
					<thead>
						<tr>
							<th>적립 일시</th>
							<th>적립 사유</th>
							<th>적립금</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="mp" items="${memberPoint }">
						<!-- 유저가 보유한 총포인트를 계산 하기 위한 변수 -->
							<c:set var= "totalPoint" value="${totalPoint + mp.howmuch}"/>
							<tr>
								<td>${mp.when }</td>
								<td>${mp.why }</td>
								<td>${mp.howmuch }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<div class="container-fluid">
					<div class="row">
						<div class="col-3">
							<p>현재 보유 포인트</p>
						</div>
						<div class="col-9">
							<p>
							${totalPoint}
							   </p>
							   
						</div>
					</div>
				</div></li>
			<!-- 	<li class="nav-item"><a class="nav-link" href="#">내가 쓴 글</a></li>
			<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a>
			</li>
			 -->
		</ul>
	</div>
	<jsp:include page="../footer(3-02).jsp"></jsp:include>
</body>
</html>