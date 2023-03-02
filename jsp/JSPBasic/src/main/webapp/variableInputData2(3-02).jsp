<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
        <h1>회원 가입</h1>
    </div>
    <div class="container">
        <form method="post" action="getParamNames.do">
            <div class="mb-3 mt-3">
                <label for="userId">아이디:</label>
                <input type="text" class="form-control" id="userId" placeholder="Enter your name" name="userId" />
            </div>

            <div class="mb-3">
                <label for="pwd">Password:</label>
                <input type="password" class="form-control" id="pwd1" placeholder="Enter password" name="pwd" />
            </div>

            <div class="mb-3">
                <label for="pwd2">Password Confirm:</label>
                <input type="password" class="form-control" id="pwd2" placeholder="Enter password" />
            </div>

            <div class="mb-3 mt-3">
                <label for="email">이메일:</label>
                <input type="text" class="form-control" id="email" placeholder="Enter your 2매일" name="email" />
            </div>

            <div class="mb-3 mt-3">
                <label for="mobile">휴대전화:</label>
                <input type="text" class="form-control" id="mobile" placeholder="Enter your mobile Number"
                    name="mobile" />
            </div>

            <fieldset>
                <legend>성별</legend>
                <div class="form-check">
                    <input type="radio" class="form-check-input" id="radio1" name="gender" value="female" />
                    <label class="form-check-label" for="radio1">여성</label>
                </div>
                <div class="form-check">
                    <input type="radio" class="form-check-input" id="radio2" name="gender" value="male" />
                    <label class="form-check-label" for="radio2">남성</label>
                </div>
            </fieldset>
               <fieldset>
                  <legend>나이</legend>
                  <div class = "form-check">
                  <label class = "form-check-label" for="age">나이</label>
                  <input type="number" class = "form-control" id="age" name ="age">
                  </div>
                  </fieldset>
            

            <fieldset>
                <legend>취미</legend>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="check1" name="hobby" value="study" />
                    <label class="form-check-label">공부</label>
                </div>

                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="check2" name="hobby" value="music" />
                    <label class="form-check-label">음악감상</label>
                </div>

                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="check3" name="hobby" value="travel" />
                    <label class="form-check-label">여행</label>
                </div>
            </fieldset>

            <label for="sel1" class="form-label">직업 (select one):</label>
            <select class="form-select" id="sel1" name="job">
                <option>--직업을 선택하세요--</option>
                <option value="student">학생</option>
                <option value="creator">크리에이터</option>
                <option value="buildingOwner">건물주</option>
                <option value="officer">공무원</option>
            </select>

            <label for="comment">MEMO :</label>
            <textarea class="form-control" rows="5" id="memo" name="memo"></textarea>

            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="agree" name="agree" value="Y" />
                <label class="form-check-label">가입조항에 동의 합니다.</label>
            </div>

            <div style="margin-top: 20px; text-align: center;">
                <button type="submit" class="btn btn-success" onclick="return registerValid();">회원가입</button>
                <button type="reset" class="btn btn-warning">취소</button>
            </div>
        </form>

        <script>

        </script>
    </div>
</body>
</html>