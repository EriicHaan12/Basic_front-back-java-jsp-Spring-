<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<script type="text/javascript">






</script>
</head>
<body>

	<div class="container">
	<h1>EMP CRUD TABLE SCOTT</h1>
	  <div class="row style="padding: 10px; color: #333;" >
    <div class="col-sm-6">
      <p id ="dataCount">데이터 갯수 들어올 자리</p>
    </div>
    <div class="col-sm-6 ">
      <p id = "resultData">출력 날짜 들어올 자리</p>
    </div>
  </div>
  
  <!-- 사원 정보 출력할 output 들어올 곳 -->
  <div class="empInfo"></div>
  
  
  <!-- 모달창 클릭 버튼 -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">
  Open modal
</button>

<!-- The Modal -->
<div class="modal" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Modal Heading</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        Modal body..
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>
  
  
  
  
  
</div>
	
	



</body>
</html>