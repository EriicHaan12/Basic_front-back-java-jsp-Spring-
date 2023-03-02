<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

function getParameter(paraName) {
    // 쿼리스트링에서 넘겨 받은 paramName 을 찾아 그 변수의 값을 retrun
    // 만약 쿼리스트링에 paramName 이 없다면 null 을 return
    let url = location.href;
    let returnVal = null;
    if (url.indexOf("?") != -1) {
      // 쿼리스트링이 있을때
      let queryString = url.split("?")[1];
      let tmpAr = queryString.split("&");
      for (let item of tmpAr) {
        if (item.split("=")[0] == paraName) {
          returnVal = item.split("=")[1];
          console.log(returnVal);
          break; // 해당 반복문 블럭을 빠져나감
        }
      }
    }
    return returnVal;
  }
  window.onload= function(){
	 let status =  getParameter("status");
  if(status === "loginSuccess"){
	  alert("로그인 성공!")
  	}
  }

</script>
</head>
<body>
	<h1>mainTest</h1>
	<a href="loginTest1(3-02).jsp">로그인 하러 가기</a>
</body>
</html>