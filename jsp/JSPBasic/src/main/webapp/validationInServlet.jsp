<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유효성 검사</title>

<script type="text/javascript">
	function validation() {
		let kor = document.getElementById("kor").value;
		let isValid = false;
		if (kor<0|| kor>100) {
			alert("국어 점수는 유효하지 않습니다!");

		} else {
			isValid = true;
		}
		return isValid;
	}
</script>

</head>
<body>
	<form action="ValidationInServlet" method="get">
		<div>
			국어 : <input type="text" name="kor" id='kor'>
		</div>
		<div>
			영어 : <input type="text" name="eng">
		</div>
		<div>
			수학 : <input type="text" name="math">
		</div>
		<input type="submit" value="전송" onclick="return validation();">
	</form>


</body>
</html>