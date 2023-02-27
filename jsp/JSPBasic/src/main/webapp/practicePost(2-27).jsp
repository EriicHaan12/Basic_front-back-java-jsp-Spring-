<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>POST 연습문제</title>
<script type="text/javascript">

function chkValidation(){
	
	let name = document.getElementById("userName").value;
	let userId = document.getElementById("userId").value;
	let pwd = document.getElementById("userPwd").value;

	let namePattern = /^[가-힣]+$/;
	// 이름 : 한글, 띄워쓰기 불가
	let idPattern = /^(?=.*[a-z0-9])[a-z0-9]{3,16}$/;
		//아이디 :  3자 이상 16자 이하, 영어 또는 숫자로 구성
		// 대소문자 상관없이 toLowerCase()로 변환시켜 데이터베이스에 삽입하기 때문에 대문자 비교는 없다
	let pwdPattern = /^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9!@#$%^&*()._-]{6,16}$/;
	//- 6자 이상 16자 이하, 영어와 숫자의 조합으로 구성
	// . _ -를 포함한 특수문자도 같이 허용해준다.
	
	
		 let isValid = false;

		  if(namePattern.test(name)){
		  isValid = true;
		  	}else{
			  alert("유효한 이름이 아닙니다. 다시 입력해주세요.");
			  
		  }
		  if(idPattern.test(userId)){
			  isValid = true;
			  }else{
		    alert("유효한 아이디가 아닙니다. 다시 입력해주세요.");
		  }
		  if(pwdPattern.test(pwd)){
			  isValid = true;
		  }else{
		    alert("유효한 비밀번호가 아닙니다. 다시 입력해주세요.");
		  }
		  return isValid;
		}

</script>
</head>
<body>
	<form action="PracticePostServlet" method="post">

		<div>
			이름: <input type="text" name="userName">
		</div>
		<div>
			아이디 : <input type="text" name="userId">
		</div>
		<div>
			패스워드 : <input type="password" name="userPwd">
		</div>

		<input type="submit" value="전송" onclick="return chkValidation();" />
	</form>
</body>
</html>