<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>게시판 상세 조회 페이지</title>
    <script>
      // script 에서도 EL표현식을 쓸 수 있다.
      // 다만 쓰기 위해서는 '' 로 표현식을 감싸줘야 문자열로 인식이 된다.
      // 감싸주지 않으면 하나의 객체로 인식되어 출력되지 않는다.
      function goReply() {
        console.log("${sessionScope.loginMember.memo}");
        //로그인이 되어 있다면 아래의 코드가 콘솔에 출력됨.

        // 로그인이 되어 있는지 확인
        let loginId = "${sessionScope.loginMember.userId}";
        if (loginId != "") {
          // 로그인 함
          let url = "replyBoard.jsp?pNo=${requestScope.board.no}&pRef=${requestScope.board.ref}";
          url += "&pStep=${requestScope.board.step}&pRefOrder=${requestScope.board.refOrder}";
          location.href = url;
        } else {
          // 로그인 하지 않음
          alert("로그인 이 후 작성하실 수 있습니다.");
          location.href = "../member/login.jsp";
        }
      }
    </script>
  </head>
  <body>
    <c:set var="contextPath" value="<%=request.getContextPath()%>" />
    <jsp:include page="../header.jsp"></jsp:include>
    <div class="container">
      <h4 style="margin-top: 5px">게시판 상세 조회 페이지</h4>
      <div class="mb-3 mt-3"><label for="userId">번호 : </label> <input type="text" class="form-control" id="boardNo" value="${requestScope.board.no }" readonly /></div>

      <div class="mb-3 mt-3"><label for="userId">작성자 : </label> <input type="text" class="form-control" id="writer" value="${requestScope.board.writer }" readonly /></div>
      <div class="mb-3 mt-3"><label for="title">작성일 : </label> <input type="text" class="form-control" id="title" value="${requestScope.board.postDate }" readonly /></div>

      <div class="mb-3 mt-3"><label for="title">제 목 : </label> <input type="text" class="form-control" id="title" value="${requestScope.board.title }" readonly /></div>

      <div class="mb-3 mt-3"><label for="title">조회수 : </label> <input type="text" class="form-control" id="title" value="${requestScope.board.readCount }" readonly /></div>
      <div class="mb-3 mt-3"><label for="title">추천수 : </label> <input type="text" class="form-control" id="title" value="${requestScope.board.likeCount }" readonly /></div>

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
        <!-- 	<c:if test="${sessionScope.loginMember!=null }"> -->
        <button type="button" class="btn btn-success" onclick="">수정</button>
        <button type="button" class="btn btn-success">삭제</button>
        <button type="button" class="btn btn-info" onclick="goReply();">답글 달기</button>

        <!-- 	</c:if> -->
        <button type="button" class="btn btn-warning" onclick="location.href='listAll.bo';">목록으로</button>
      </div>
    </div>
    <jsp:include page="../footer(3-02).jsp"></jsp:include>
  </body>
</html>
