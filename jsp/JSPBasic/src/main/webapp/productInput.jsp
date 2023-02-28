<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet 연습문제</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        fieldset {
            padding: 10px;
            background-color: #eeeeee;
            margin-top: 20px;
            margin-bottom: 20px;
        }

        legend {
            background-color: gray;
            color: white;
            padding: 5px 10px;
        }

        .errMsg {
            color: hotpink;
            padding: 10px;
        }
    </style>
</head>
<body>
    <div class="container-fluid p-5 bg-primary text-white text-center">
        <h1>상품 등록</h1>
    </div>
    <div class="container">
        <form method="get" action="prctice.do">
            <div class="mb-3 mt-3">
                <label for="prodName">상품명:</label>
                <input type="text" class="form-control" id="prodName" placeholder="Enter new product" name="prodName" />
            </div>

            <div class="mb-3">
                <label for="number">갯수:</label>
                <input type="number" class="form-control" id="amount"  name="amount" />
            </div>

            <div class="mb-3 mt-3">
                <label for="email">가격 :</label>
                <input type="number" class="form-control" id="price"  name="price" />
            </div>

            <label for="sel1" class="form-label">옵션  (select one):</label>
            <select class="form-select" id="color" name="color">
                <option>--색깔을 선택하세요--</option>
                <option value="red">빨강</option>
                <option value="orange">주황</option>
                <option value="yellow">노랑</option>
                <option value="blue">파랑</option>
            </select>
               <div style="margin-top: 20px; text-align: center;">
                <button type="submit" class="btn btn-success" >회원가입</button>
         </div>
        </form>

    </div>
</body>
</html>