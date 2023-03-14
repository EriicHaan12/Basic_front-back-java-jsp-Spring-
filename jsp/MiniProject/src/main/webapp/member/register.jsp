<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 페이지</title>


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

<script>
        // 회원 가입 버튼을 아래의 조건에 따라 유효성 검사를 하고,
        // 유효하면 1-1_sub.html 페이지에 데이터를 전송하자
        // 아이디 : 4자 이상 8자 이하 필수(소문자로 저장)
        // 비밀번호 : 4자 이상 12자 이하 필수(비밀번호 확인과 동일할것)
        // 이름 : 필수 항목
        // 이메일 : 이메일 주소 형식인지 아닌지 검사
        // 휴대전화 : 전화번호 형식인지 아닌지 검사(정규식)
        // 성별 : 남성 또는 여성 중에 하나가 반드시 체크 되어 있어야 한다.
        // 취미 : 여러개를 선택할 수 있다.
        // 직업 : 반드시 하나를 선택 하도록 한다
        // 메모 : 가입인사. 안써도 됨.
        // 가입 조항 동의서에 반드시 체크가 되어 있을 것.

        function validPwd(pwd1, pwd2) {
            // 비밀번호 : 4자 이상 12자 이하 필수(비밀번호 확인과 동일할것)
            let isValid = false;

            if (pwd1 == "") {
                outputError("비밀번호는 필수 입니다", $("#pwd1"));
            } else if (pwd1.length < 4 || pwd1.length > 13) {
                outputError("비밀번호는  4자 이상 12자 이하로 입력하세요", $("#pwd1"));
            } else if (pwd1 != pwd2) {
                outputError("비밀번호가 서로 맞지 않습니다", $("#pwd2"));
            } else {
                isValid = true;
            }

            return isValid;
        }

        function registerValid() {
            // 폼태그 안에 있는 모든 입력 데이터를 회원가입 조건이 맞는지를 비교해서
            // 모든 조건에 다 부합이 되면 true 반환
            // 하나라도 조건에 만족하지 않는 데이터가 있다면 false 반환

            let idCheck = validUserId($("#userId").val());

            let pwdCheck = validPwd($("#pwd1").val(), $("#pwd2").val());

            let emailCheck = validEmail($("#email").val());

            let genderChk = validGender();



            let hobbies = getHobby(); // 문자열
            console.log(hobbies);

            let job = getJob();


            let isAgree = false;
            if (document.getElementById("agree").checked) {  // 가입 동의에 체크가 되어 있다면...
                isAgree = true;
            } else {
                outputError("가입 조항에 동의해주세요", $("#agree"));
            }


            let valid = false;
            if (idCheck && pwdCheck && emailCheck && genderChk && job && isAgree) {
                // 아이디 체크, 패스워드, 이메일, 성별, 직업, 가입조항의  모든 입력 데이터가 유효할 때만 submit이 되도록...
                valid = true;
            }

            return valid;

        }

        function getJob() {
            let isValid = false;
            // selectedIndex : 유저가 select 태그의 옵션중에서 선택한 옵션의 index번호
            console.log(document.getElementById("sel1").selectedIndex);
            if (document.getElementById("sel1").selectedIndex == 0) {
                outputError("직업을 선택하세요", $("#sel1"));
            } else {
                isValid = true;
            }

            return isValid;
        }

        function getHobby() {
            let hobbies = "";
            let hobbyArr = document.getElementsByName("hobby");
            for (let i = 0; i < hobbyArr.length; i++) {
                if (hobbyArr[i].checked == true) {
                    hobbies += hobbyArr[i].value + ",";
                }
            }
            console.log(hobbies);

            return hobbies.substring(0, hobbies.length - 1);
        }

        function validGender() {
            // 성별 : 남성 또는 여성 중에 하나가 반드시 체크 되어 있어야 한다.
            let isValid = false;  // 성별 유효성 검사에 통
            let radio1 = false;
            let radio2 = false;

            if (document.getElementsByName("gender")[0].checked) {
                radio1 = true;
            }

            if (document.getElementsByName("gender")[1].checked) {
                radio2 = true;
            }

            if (radio1 == false && radio2 == false) { // 아무곳도 체크가 안되었다면
                outputError("성별을 선택하세요", $("#radio2"));
            } else {
                isValid = true;
            }

            return isValid;
        }

        function validEmail(email) {
            // 이메일 : 이메일 주소 형식인지 아닌지 검사
            let pattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
            let isValid = false;
            if (!email.match(pattern)) {  // email이 pattern과 일치 하지 않느냐?
                outputError("이메일 주소형식이 아니에요", $("#email"));
            } else {
                isValid = true;
            }

            return isValid;
        }


        function outputError(errorMsg, tagObj) {
            // errorMsg를 tagObj객체 다음 요소에 삽입시켜 출력한다.
            let err = `<div class='errMsg'>${errorMsg}</div>`;
            $(err).insertAfter($(tagObj));
            $(tagObj).focus();
            $(".errMsg").hide(3000);
        }

        function validUserId(userId) {
            //4자 이상 8자 이하 필수 

            // 아이디가 3자 이하이거나, 9자 이상이거나, 아무것도 입력하지 않았을때 => 에러메지지 출력, 다시 입력


            let idCheck = false;
            if (userId == "" || userId.length < 1) { // 아이디가 입력되지 않았다
                outputError("아이디는 필수로 입력하세요!", $("#userId"));
            } else if (userId.length <= 3 || userId.length >= 9) {
                outputError("4자 이상 8자 이하로 입력하세요!", $("#userId"));
            } else {
                idCheck = true;
                // $("#userId").attr("readonly", "true"); // 요렇게 처리 하거나
                // 체크 이미지를 추가하거나 input 태그에 배경색을 칠하거나....
            }

            // 아이디가 4자이상 8자이하이고, 0-9숫자A-Za-z 대소문자가 반드시 포함되어야 한다. 
            // 이런 복잡한 문제는 if로 해결하지 말고, 정규식을 응용하자.
            // let pattern = /^[A-Za-z0-9]{4,8}$/;
            // if (pattern.test(userId)) {
            //     // 아이디가 유효하다
            //     console.log("유효");
            // } else {
            //     // 아이디가 유효하지 않다.
            //     console.log("무효");
            // }


            return idCheck;
        }

    </script>


</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>

	<div class="container">
		<h1>회원가입</h1>
		<!--register.mem을 맵핑주소로 설정  -->
		<form method="post" action="register.mem"
			enctype="multipart/form-data">
			<div class="mb-3 mt-3">
				<label for="userId">아이디:</label> <input type="text"
					class="form-control" id="userId" placeholder="Enter your name"
					name="userId" />
			</div>

			<div class="mb-3">
				<label for="pwd">Password:</label> <input type="password"
					class="form-control" id="pwd1" placeholder="Enter password"
					name="pwd" />
			</div>

			<div class="mb-3">
				<label for="pwd2">Password Confirm:</label> <input type="password"
					class="form-control" id="pwd2" placeholder="Enter password" />
			</div>

			<div class="mb-3 mt-3">
				<label for="email">이메일:</label> <input type="text"
					class="form-control" id="email" placeholder="Enter your 2매일"
					name="email" />
			</div>

			<div class="mb-3 mt-3">
				<label for="mobile">휴대전화:</label> <input type="text"
					class="form-control" id="mobile"
					placeholder="Enter your mobile Number" name="mobile" />
			</div>

			<fieldset>
				<legend>성별</legend>
				<div class="form-check">
					<input type="radio" class="form-check-input" id="radio1"
						name="gender" value="f" /> <label class="form-check-label"
						for="radio1">여성</label>
				</div>
				<div class="form-check">
					<input type="radio" class="form-check-input" id="radio2"
						name="gender" value="m" /> <label class="form-check-label"
						for="radio2">남성</label>
				</div>
			</fieldset>

			<fieldset>
				<legend>취미</legend>
				<div class="form-check">
					<input class="form-check-input" type="checkbox" id="check1"
						name="hobby" value="study" /> <label class="form-check-label">공부</label>
				</div>

				<div class="form-check">
					<input class="form-check-input" type="checkbox" id="check2"
						name="hobby" value="music" /> <label class="form-check-label">음악감상</label>
				</div>

				<div class="form-check">
					<input class="form-check-input" type="checkbox" id="check3"
						name="hobby" value="travel" /> <label class="form-check-label">여행</label>
				</div>
			</fieldset>

			<label for="sel1" class="form-label">직업 (select one):</label> <select
				class="form-select" id="sel1" name="job">
				<option>--직업을 선택하세요--</option>
				<option value="student">학생</option>
				<option value="creator">크리에이터</option>
				<option value="buildingOwner">건물주</option>
				<option value="officer">공무원</option>
			</select>

			<div class="form-check">
				<label class="form-check-label" for="memberImg">이미지 : </label> <input
					type="file" id="memberImg" name="memberImg" />
			</div>

			<div class="form-check">
				<label for="comment">MEMO :</label>
				<textarea class="form-control" rows="5" id="memo" name="memo"></textarea>
			</div>

			<div class="form-check">
				<input class="form-check-input" type="checkbox" id="agree"
					name="agree" value="Y" /> <label class="form-check-label">가입조항에
					동의 합니다.</label>

			</div>

			<div style="margin-top: 20px; text-align: center;">
				<button type="submit" class="btn btn-success"
					onclick="return registerValid();">회원가입</button>
				<button type="reset" class="btn btn-warning">취소</button>
			</div>
		</form>

	</div>
	<jsp:include page="../footer(3-02).jsp"></jsp:include>

</body>
</html>