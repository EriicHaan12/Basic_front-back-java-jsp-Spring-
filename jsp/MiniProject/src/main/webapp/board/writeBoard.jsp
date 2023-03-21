<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<style type="text/css">
#imgPreview {
	width: 70px;
}

.btns {
	float: right;
	border-radius: 25px
}
</style>
<script type="text/javascript">
	
	$(function(){
		$("#imgFile").change(function(e) {
			if (validImg()) { // 이곳이 true로 통과 되었다는 것은 이미지 파일인 경우에만 이라는 뜻
				// memberImg 를 multiple 속성을 부여해주면 다중 파일을 업로드 시킬 수 있다.
				let file = e.target.files[0];

				let reader = new FileReader();//FileReader  객체 생성,  비동기 식으로 파일을 읽어주기

				//reader가 다읽었을 때 호출 되는 함수 즉,
				//// 파일을 다 읽었을 때 호출되는 콜백 함수

				reader.onload = function(evt) {

					$("#imgPreview").attr("src", evt.target.result);//파일의 실제 위치를 src 속성에 부여해서
					// 유저가 업로드 할 파일을 페이지에 올리면 preview 띄워주기

				};
				reader.readAsDataURL(file);

			}
		});
	});
	

	function validImg() {
		//파일올릴때 이미지 파일인지 유효성 검사
		let isValid = false;
		let fileName = $("#imgFile").val();
		
		let ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		let imgArr = [ "jpeg", "jpg", "png", "jpeg", "jfif" ];
		
		$.each(imgArr,function(i,elt){
			if(ext==elt){
				isValid= true;
			}
		});
		
		if(fileName!=''&&!isValid){
			alert('이미지파일이 아닙니다.');
			$("#imgFile").val('');
		}else if(fileName==''){ // 아무것도 올리지 않았을 땐 그냥 true
			isValid =true;
		}
		return isValid;
	}

	
</script>

<title>게시판 글 쓰기</title>
</head>
<body>
	<c:if test="${sessionScope.loginMember==null }">
		<c:redirect url="../member/login.jsp"></c:redirect>
	</c:if>
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container">
		<h4 style="margin-top: 5px;">게시판 글쓰기 페이지</h4>
		<form method="post" action="write.bo" enctype="multipart/form-data">

			<div class="mb-3 mt-3">
				<label for="userId">작성자 : </label> <input type="text"
					class="form-control" id="writer" name="writer"
					value="${sessionScope.loginMember.userId }" readonly />
			</div>
			<div class="mb-3 mt-3">
				<label for="title">제 목 : </label> <input type="text"
					class="form-control" id="title" name="title" />
			</div>
			<div class="form-check">
				<label for="content">본 문 :</label>
				<textarea class="form-control" rows="20" id="content" name="content"></textarea>
			</div>

			<div class="form-check">
				<label class="form-check-label" for="">이미지 : </label> <input
					type="file" id="imgFile" name="imgFile" />
				<div>
					<img id="imgPreview" />
				</div>
			</div>
			<div class="btns">
				<button type="button" class="btn btn-success"
					onclick="location.href='listAll.bo';">취소</button>
				<button type="submit" class="btn btn-success">저장</button>
			</div>
		</form>
	</div>

	<jsp:include page="../footer(3-02).jsp"></jsp:include>
</body>
</html>