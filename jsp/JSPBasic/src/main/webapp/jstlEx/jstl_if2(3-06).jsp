<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- else 문이 없다보니 조건을 하나하나 만들어줘야한다. -->
<div>
<c:if test = "${param.favSeason=='봄'}"> 
<div style="color : red;"> 봄을 좋아하네요  </div>
</c:if>

<c:if test = "${param.favSeason=='여름'}"> 
<div style="color : blue;"> 여름을 좋아하네요  </div>
</c:if>

<c:if test = "${param.favSeason=='가을'}"> 
<div style="color : brown;"> 가을을 좋아하네요  </div>
</c:if>

<c:if test = "${param.favSeason=='겨울'}"> 
<div style="color : gray;"> 겨울을 좋아하네요  </div>
</c:if>

</div>
</body>
</html>