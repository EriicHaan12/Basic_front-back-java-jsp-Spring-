<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삼각형 뚝딱뚝딱</title>

<script type="text/javascript">

function validation(){
	let base= document.getElementsById("base").value;
	let height= document.getElementsById("height").value;
	let isValid = false;
	if(base<0 ||height <0){
		alert("밑변 또는 높이가 잘못 입력 되었습니다, 다시 입력해주세요");
		
	}else{
		isValid = true;
	}
	return isValid;
}

</script>

</head>
<body>
<form action="TriangleServlet" method="get">
<div> 밑변 : <input type="text" name="base" id='base'>cm</div>
<div> 높이 : <input type="text" name="height" id = 'height"'>cm</div>	
<input type="submit" value="전송" onclick="return validation();">
</form>


</body>
</html>