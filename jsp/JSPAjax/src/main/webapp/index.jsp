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

<!-- 부트스트랩, jquery 가져오기 -->
<title>Insert title here</title>
<script type="text/javascript">
	let jobs = null; //전역변수로 만들어서 함수가 끝나도 저장되도록
	//insert할 때 넣을 데이터 용 전역변수
	let inputJobId = "";
	let inputSal = 0;
	let inputManagerId = "";
	let inputDeptId = "";
	let searchName = "";
	let sortBy="empNo";
	let allEmpData = null;
	let allDeptData = null;
	//manager 및 department select할 때 insert 하기 위한 전역변수

	$(document)
			.ready(
					function() {
						getAllEmployees();
						//직업을 입력받기 위해 필요한 db의 jobs 테이블
						getJobsData();

						getDeptInfo();
						
						
						// 정렬 기준 선택
						
	

						$(".writeIcon").click(function() {

							//사원 입력시 필요한 부가 정보를 바인딩
							//		makeJobSelection()
							// 사원 수정 할 때도 쓰기 위해 수정
							$("#writeJobID").html(makeJobSelection());

							$("#writeManager").html(makeManagerSelection());

							$("#writeDepartment").html(makeDeptSelection());

							$("#writeModal").show(500);

						});
						// 사원 이름으로 검색시 
						//or keyup
						$(".searchName").change(function() {
							 searchName = $(this).val();
							console.log(searchName);
							if (searchName.length > 1) { // 두자 이상일 때 검색 시작하도록 

								getAllEmployees(searchName);
							}
							
						});
						
						
						// 정렬 기준 선택
						$(".orderMethod").change(function(){
						if(	$("input[name=orderMethod]:checked")){
							sortBy =$("input[name=orderMethod]:checked").val()
					
							getAllEmployees(searchName,sortBy);
							}
							console.log(sortBy)
						})
						
						});
						
						// 클릭시 모달창 닫기

						$(".modifycloseBtn").click(function() {
							$("#modifyModal").hide();

						});

						$(".remCloseBtn").click(function() {
							$("#removeModal").hide();
						})

						$(".remBtn").click(function() {
							let empNo = $("#remEmpNo").html();
							$.ajax({
								url : "remEmp.do",
								type : "get",
								// 삭제할 때는 사원 번호만 필요하기 때문에
								data : {
									"empNo" : empNo
								},
								dataType : "json",
								success : function(data) {
									console.log(data);
									if (data.status == "success") {
										alert("!");
										getAllEmployees(searchName,sortBy); // 데이터 갱신 시켜주기 위해서
										$("#removeModal").hide();

									} else if (data.status == "fail") {
										alert("삭제에 실패 했습니다.");
									}
								}
							});

						});

						//직급 정보 입력시
						$("#writeJobID")
								.change(
										function() {
											if ($(this).val() != '') {

												// alert($(this).attr('id'));
												//몇번째 select 박스를 선택한지 숫자가 나오는 코드
												let index = document
														.getElementById("writeJobID").selectedIndex;
												//options 속성 : select 태그 안에 있는 option 태그들 (배열 처럼 운용)
												//selectedIndex 속성 : 유저가 선택한 option 태그 번째
												//return 이 안되므로 전역변수에 값을 넘겨준다.
												inputJobId = document
														.getElementById("writeJobID").options[index]
														.getAttribute("id")
												//--
												let salInfo = getSalInfo();

												let output = makeSalaryInput(
														salInfo, null);
												$("#salInput").html(output);
												$("#salInput").show(output);
											}

										});

						function getSalInfo() {
							let minSal = 0;
							let maxSal = 0;
							$.each(jobs.JOBS, function(i, e) {
								if (inputJobId == e.JOB_ID) {
									minSal = e.MIN_SALARY;
									maxSal = e.MAX_SALARY;
								}
							});
							console.log(minSal, maxSal);
							return {
								"mindSal" : minSal,
								"maxSal" : maxSal
							};

						}
						// 사원 수정 정보를 서블릿으로 요청 하는 함수
						function modifyEmployee(modiEmp) {
							$.ajax({
								url : "modifyEmp.do",
								type : "get",
								// 삭제할 때는 사원 번호만 필요하기 때문에
								data : modiEmp,
								dataType : "json",
								success : function(data) {
									console.log(data);
									if (data.status == "success") {
										getAllEmployees(searchName,sortBy);
										$("#modifyModal").hide();

									} else if (data.status == "fail") {
										alert("수정에 실패 했습니다.");
									}
								}
							});

						}

						$("#writeDepartment")
								.change(
										function() {
											if ($(this).val() != '') {

												let index = document
														.getElementById("writeDepartment").selectedIndex;

												inputDeptId = document
														.getElementById("writeDepartment").options[index]
														.getAttribute("id");
											}
										});

						$("#writeManager")
								.change(
										function() {
											if ($(this).val() != '') {
												let index = document
														.getElementById("writeManager").selectedIndex;
												inputManagerId = document
														.getElementById("writeManager").options[index]
														.getAttribute("id");
											}
										});

						//사원 수정 버튼 클릭시
						$(".modifyBtn").click(
								function() {

									let modifyFristName = $("#modifyFristName")
											.val();
									let modifyLastName = $("#modifyLastName")
											.val();
									let modifyEmail = $("#modifyEmail").val();
									let modifyPhoneNumber = $(
											"#modifyPhoneNumber").val();
									let modifyHireDate = $("#modifyHireDate")
											.val();
									let modifyJobId = $("#modifyJobID").val();

									let modifySal = Number($("#modifySal")
											.val());

									let modifyComm = $("#modifyComm").val();

									let modifyManagerId = $("#modifyManger")
											.val();

									let modifyDeptId = $("#modifyDepartment")
											.val();

									let empNo = $("#modifyEmpNo").val(); // 수정할 직원의 사번

									let modiEmp = {
										"EMPLOYEE_ID" : empNo,
										"FIRST_NAME" : modifyFristName,
										"LAST_NAME" : modifyLastName,
										"EMAIL" : modifyEmail,
										"PHONE_NUMBER" : modifyPhoneNumber,
										"HIRE_DATE" : modifyHireDate,
										"JOB_ID" : modifyJobId,
										"SALARY" : modifySal,
										"COMMISSION_PCT" : modifyComm,
										"MANAGER_ID" : modifyManagerId,
										"DEPARTMENT_ID" : modifyDeptId
									};
									console.log(modiEmp);
									modifyEmployee(modiEmp);

								});

						//사원 저장 버튼클릭시
						$(".writeBtn").click(
								function() {
									//1. input 태그들로부터 유저가 입력한 값을 가져오기
									let writeFristName = $("#writeFristName")
											.val();
									let writeLastName = $("#writeLastName")
											.val();
									let writeEmail = $("#writeEmail").val();
									let writePhoneNumber = $(
											"#writePhoneNumber").val();
									let writeHireDate = $("#writeHireDate")
											.val();
									let writeJobId = inputJobId;
									let writeSal = Number($("#writeSalary")
											.html());
									//inputSal;
									let writeComm = $("#writeComm").val();
									let writeManagerId = inputManagerId; //
									let writeDeptId = inputDeptId; //

									//2. 유효성 검사하기
									let isValid = false;
									if (writeSal == 0) {

										alert("급여를 선택하지 않았습니다")
									} else {
										isValid = true;
									}

									//3. 유효성 검사에 통과하면 ajax로 데이터를 서블릿으로 보내기
									let emp = {
										"FIRST_NAME" : writeFristName,
										"LAST_NAME" : writeLastName,
										"EMAIL" : writeEmail,
										"PHONE_NUMBER" : writePhoneNumber,
										"HIRE_DATE" : writeHireDate,
										"JOB_ID" : writeJobId,
										"SALARY" : writeSal,
										"COMMISSION_PCT" : writeComm,
										"MANAGER_ID" : writeManagerId,
										"DEPARTMENT_ID" : writeDeptId
									};

									saveEmp(emp)
								});

						//입력한 데이터들 저장하는 메소드
						function saveEmp(emp) {
							console.log(emp); // 그냥 JSON은 객체로 인식
							console.log(JSON.stringify(emp)); // JSON을 stringify 메서드로 바꿔 문자열로 인식되게 끔 함.

							$.ajax({
								url : "saveEmp.do",
								type : "GET",
								data : emp, //서블릿에 전송할 데이터
								dataType : "json",
								success : function(data) {
									if (data.status == "success") {

										$("#writeModal").hide();
										writeModalInit();
										getAllEmployees(searchName,sortBy);

									} else if (data.status == "fail") {
										alert("저장에 실패 했습니다.");
									}
								}
							});

						}
						;
						// 저장됬을 때 모달 창의 입력칸 초기화
						function writeModalInit() {

							$("#writeFristName").val('');
							$("#writeLastName").val('');
							$("#writeEmail").val('');
							$("#writePhoneNumber").val('');
							$("#writeHireDate").val('');
							$("#writeComm").val('');
						}
						
						$(".writecloseBtn").click(function() {
							$("#writeModal").hide();
						});
				

	//직급정보 select태그 박스에 동적 바인딩
	//jobs정보를 불러오기 위한 select 박스의 옵션 만드는 함수

	function makeJobSelection() {
		let output = "<option value=''>---직급을 선택하세요 ---</option>";
		$.each(jobs.JOBS, function(i, item) {
			// 이 때 따옴표 써야되는지..?
			output += "<option id="+item.JOB_ID+ " value ="+item.JOB_ID+" >"
					+ item.JOB_TITLE + "</option>"
		});
		return output;
	}
	// 급여값 선택할 때
	function changeSal(sal) {

		//	inputSal = sal;
		//$("#writeSalary").html(inputSal);
		$("#writeSalary").html(sal); // span 태그에 넣어줌
		//	inputSal = $("#writeSalary").html();
	}
	$("#writeManager")
			.change(
					function() {
						if ($(this).val() != '') {
							let index = document.getElementById("writeJobID").selectedIndex;
							inputJobId = document.getElementById("writeJobID").options[index]
									.getAttribute("id")
						}
					});

	//급여정보를 동적으로 태그에 바인딩
	function makeSalaryInput(salInfo, mode) {
		// select 박스 선택 후 나오는 salary range 만들기
		let output = "";
		if (mode == null) { // 사원 입력 급여 정보
			output = "<input type='range' class='form-range' min='"
					+ salInfo.minSal
					+ "' max='"
					+ salInfo.maxSal
					+ "' id ='wirteSal' onchange='changeSal(this.value);' step='100'>";
		} else if (mode = "modify") { // 사원 수정 급여 정보
			output = "<input type='range' class='form-range' min='"
					+ salInfo.minSal
					+ "' max='"
					+ salInfo.maxSal
					+ "' id ='modifySal' onchange='modiChaneSal(this.value);' step='100'>";
		}

		return output;
	}

	function modiChaneSal(sal) {
		$("#modifySalary").html(sal);
	}
	
	


	// 모든 직원 데이터를 얻어오는 함수
	function getAllEmployees(searchName, sortBy) {
	
		let url = "getAllemployees.do";
		url+="?";
		
		if (searchName != null) {
			url += "searchName="+searchName +"&";	
		}
		if(sortBy !=null){
			
			url+="orderBy="+sortBy;
		
		}
		console.log(url);
		
		$.ajax({
			url : url, // 데이터가 송수신될 서버의 주소 // 서블릿 파일의 매핑 주소를 쓰면 된다.
			type : "GET", // 통신 방식 (GET, POST, PUT, DELETE)
			dataType : "json", // 수신받을 데이터 타입(MIME TYPE)
			success : function(data) { // 통신이 성공하면 수행할 함수(콜백 함수)
				console.log(data);
				if (data.status == "fail") {
					alert("데이터를 불러오지 못했습니다.")
				}

				else if (data.status == "success") {
					console.log(data);
					allEmpData = data;
					outputEntireEmployees(data, searchName);
				}
			}
		});
	}

	//모든 부서 데이터를 얻어오는 함수
	function getDeptInfo() {
		$.ajax({
			url : "getAllDept.do", // 데이터가 송수신될 서버의 주소 // 서블릿 파일의 매핑 주소를 쓰면 된다.
			type : "GET", // 통신 방식 (GET, POST, PUT, DELETE)
			dataType : "json", // 수신받을 데이터 타입(MIME TYPE)
			success : function(data) { // 통신이 성공하면 수행할 함수(콜백 함수)
				console.log(data);
				if (data.status == "fail") {
					alert("데이터를 불러오지 못했습니다.")
				}

				else if (data.status == "success") {
					console.log(data)
					allDeptData = data;
				}

			},
			error : function() {

			},
			complete : function() {

			}
		});

	}

	function makeDeptSelection() {
		let output = "<option value=''>---부서를 선택하세요 ---</option>";
		/////////////////////////////////////////////////////////////////////////////////
		$
				.each(
						allDeptData.depts,
						function(i, e) {
							output += "<option id='"+ e.DEPARTMENT_ID +"'value='"+e.DEPARTMENT_ID+"'>"

									+ e.DEPARTMENT_NAME + "</option>";

						});
		return output;
	}

	//매니저 정보 select 태그 박스에 동적 바인딩
	function makeManagerSelection() {
		let output = "<option value=' '>---매니저를 선택하세요 ---</option>";

		$
				.each(
						allEmpData.employees,
						function(i, e) {
							output += "<option id='"+ e.EMPLOYEE_ID + "' value='" + e.EMPLOYEE_ID + "' >"
									+ e.FIRST_NAME
									+ ","
									+ e.LAST_NAME
									+ "</option>";

						});
		return output;

	}

	function getJobsData() {
		$.ajax({
			url : "getJobsData.do", // 데이터가 송수신될 서버의 주소 // 서블릿 파일의 매핑 주소를 쓰면 된다.
			type : "POST", // 통신 방식 (GET, POST, PUT, DELETE)
			dataType : "json", // 수신받을 데이터 타입(MIME TYPE)
			success : function(data) { // 통신이 성공하면 수행할 함수(콜백 함수)
				console.log(data);
				jobs = data;
			},
			error : function() {

			},
			complete : function() {

			}
		});
	}
	// 모든 직원 데이터 파싱하여 출력하는 함수
	function outputEntireEmployees(json, sn) {
		let output = "<div class ='row' >"
		$("#outputCnt").html("데이터 : " + json.count + "개");
		$("#outputDate").html("출력 일시 : " + json.output);

		output = "<table class='table table-striped empInfo'>"
		output += "<thead><tr><th>사번</th><th>이름</th><th>이메일</th><th>전화번호</th><th>입사일</th><th>직급</th>"
				+ "<th>급여</th><th>커미션(%)</th><th>직속상관이름</th><th>소속부서명</th><th></th><th></th>"
				+ "</tr></thead><tbody>";

		$
				.each(
						json.employees,
						function(i, item) {
							output += "<tr class='emp'>"
							output += "<td>" + item.EMPLOYEE_ID + "</td>";

							if (sn == null) {
								output += "<td>" + item.FIRST_NAME + ","
										+ item.LAST_NAME + "</td>";
							} else if (sn != null) {
								let fullName = item.FIRST_NAME + ", "
										+ item.LAST_NAME;
								let preFullName = fullName.split(sn)[0];
								let postFullName = fullName.split(sn)[1];

								output += "<td>" + preFullName + "<mark>" + sn
										+ "</mark>" + postFullName + "</td>";
							}

							output += "<td>" + item.EMAIL + "</td>";
							output += "<td>" + item.PHONE_NUMBER + "</td>";
							output += "<td>" + item.HIRE_DATE + "</td>";
							output += "<td>" + item.JOB_ID + "</td>";
							output += "<td>" + item.SALARY + "</td>";

							if (item.COMMISSION_PCT == "0.0") {
								output += "<td></td>";
							} else {
								output += "<td>" + item.COMMISSION_PCT * 100
										+ "</td>";
							}
							let managerId = item.MANAGER_ID;
							let managerName = "";

							$.each(json.employees, function(i, item) {
								if (managerId == item.EMPLOYEE_ID) { // 직속상관의 번호로 그사람의 이름을 찾았을 때
									managerName = item.FIRST_NAME + ","
											+ item.LAST_NAME;
									output += "<td>" + managerName + "</td>";
								}
							})

							//	output+="<td>"+item.DEPARTMENT_ID+"</td>";
							output += "<td>" + item.DEPARTMENT_NAME + "</td>";
							output += "<td> <img src='images/modify.png' class='maintainIcon' onclick='modiModalShow("
									+ item.EMPLOYEE_ID + ");'/></td>";
							output += "<td><img src='images/remove.png' class='maintainIcon'onclick='remModalShow("
									+ item.EMPLOYEE_ID + ");' /></td>";
							output += "</tr>";

						});
		output += "</tbody></table>";
		$(".empInfo").html(output);
		console.log(json);
	}

	// 사원 수정 모달창 띄우기
	function modiModalShow(empNo) {
		//alert(empNo+ "번 사원 수정");

		$.ajax({
			url : "getEmployee.do", // 데이터가 송수신될 서버의 주소 // 서블릿 파일의 매핑 주소를 쓰면 된다.
			type : "POST", // 통신 방식 (GET, POST, PUT, DELETE)
			// 보낼 데이터
			data : {
				"empNo" : empNo
			},
			dataType : "json", // 수신받을 데이터 타입(MIME TYPE)
			success : function(data) { // 통신이 성공하면 수행할 함수(콜백 함수)
				console.log(data);
				if (data.status == "fail") {
					alert("데이터를 불러오지 못해ㅆ습니다")
				} else if (data.status == "success") {

					bindingDataModifyModal(data);
				}
			}
		});

		$("#modifyModal").show(500);
		//수정할 때 편의를 위해서 사원 정보 먼저 모달창 입력칸에 바인딩 시켜주기
		$("#modifyEmpNo").val(empNo);
	}

	// 사원 수정 모달창에 데이터 바인딩

	function bindingDataModifyModal(data) {
		$("#modifyFristName").val(data.employee.FIRST_NAME);
		$("#modifyLastName").val(data.employee.LAST_NAME);
		$("#modifyEmail").val(data.employee.EMAIL);
		$("#modifyPhoneNumber").val(data.employee.PHONE_NUMBER);

		$("#modifyHireDate").val(data.employee.HIRE_DATE.split(" ")[0]);

		// jodId 바인딩 시켜주기

		$("#modifyJobID").html(makeJobSelection());
		$("#modifyJobID").val(data.employee.JOB_ID);

		let minSal = 0;
		let maxSal = 0;
		// 선택한 직급의 최소, 최대 급여를 얻어옴
		$.each(jobs.JOBS, function(i, e) {
			if (data.employee.JOB_ID == e.JOB_ID) {
				minSal = e.MIN_SALARY;
				maxSal = e.MAX_SALARY;
			}
		});
		let salInfo = {
			"minSal" : minSal,
			"maxSal" : maxSal
		};

		$("#salmodifyInput").html(makeSalaryInput(salInfo, "modify"));

		$("#salmodifyInput").show();

		$("#modifyComm").val(data.employee.COMMISSION_PCT);

		$("#modifyManger").html(makeManagerSelection());
		if (data.employee.MANAGER_ID != "0") {

			$("#modifyManger").val(data.employee.MANAGER_ID);
		}
		$("#modifyDepartment").html(makeDeptSelection());

		$("#modifyDepartment").val(data.employee.DEPARTMENT_ID);
		//makeDeptSelection

	}

	function remModalShow(empNo) {
		//alert(empNo+ "번 사원 수정");
		$("#removeModal").show(500);
		$("#remEmpNo").html(empNo);
	};
	
</script>
<style type="text/css">
.maintainIcon {
	width: 15px;
	height: 15px;
	cursor: pointer;
}

.empInfo {
	font-size: 12px;
}

.emp:hover {
	background-color: yellow;
}

.writeIcon {
	width: 70px;
	height: 70px;
	border-radius: 28px;
	background-color: gray;
	position: fixed;
	right: 30px;
	bottom: 30px;
	cursor: pointer;
}

.orderRadio {
	margin-right: 15px;
}
</style>
</head>
<body>
	<div class="container">
		<h1>Employees CRUD With Ajax</h1>
		<!--  사원 검색을 위한 input창 -->
		<div class="searchEmp">
			<input type="text" class="form-control form-control-lg searchName"
				placeholder="input search Employees's Name">
		</div>

	
		<div class="orderMethod">
			 <input type="radio" class="form-check-input orderRadio" 
			 	id="radioEmp" name="orderMethod" value="empNo" checked>사번순
			 <input type="radio" class="form-check-input orderRadio" 
			 	id="radioName" name="orderMethod" value="firstName" >이름순
			 <input type="radio" class="form-check-input orderRadio" 
			 	id="radioHireDate" name="orderMethod" value="hiredate" >입사일순
		</div>
		

		<div class="row" style="padding: 10px; color: #333;">
			<div class="col-sm-3">
				<p id="outputCnt">
					<!-- 데이터 갯수 들어올자리 -->
				</p>
			</div>
			<div class="col-sm-9">
				<p id="outputDate">
					<!-- 날짜 들어올 자리 -->
				</p>
			</div>
			<div class="empInfo"></div>
			<div class="writeIcon">
				<img src="images/writer.png">
			</div>
		</div>

	</div>




	<!-- wrtier 이미지클릭했을 때만 나타나는 모달창  (사원 입력을 위한 모달창)-->
	<div class="modal" id="writeModal" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">사원 입력</h4>
					<button type="button" class="btn-close writecloseBtn"
						data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<div class="mb-3 mt-3">
						<label for="writeFristName" class="form-label">FirstName:</label>
						<input type="text" class="form-control" id="writeFristName">
					</div>
					<div class="mb-3 mt-3">
						<label for="writeLastName" class="form-label">LastName:</label> <input
							type="text" class="form-control" id="writeLastName">
					</div>
					<div class="mb-3 mt-3">
						<label for="writeEmail" class="form-label">Email:</label> <input
							type="text" class="form-control" id="writeEmail">
					</div>
					<div class="mb-3 mt-3">
						<label for="writePhoneNumber" class="form-label">PhoneNumber:</label>
						<input type="text" class="form-control" id="writePhoneNumber">
					</div>

					<div class="mb-3 mt-3">
						<label for="writeHireDate" class="form-label">HireDate:</label> <input
							type="date" class="form-control" id="writeHireDate">
					</div>


					<div class="mb-3 mt-3">
						<label for="wrieJobID" class="form-label">JOB_ID:</label> <select
							id="writeJobID">
						</select>
					</div>
					<div class="mb-3 mt-3">
						<label for="writeSalary" class="form-label">Salary : $<span
							id="writeSalary"></span>
						</label>
						<div id="salInput" style="display: none;"></div>
					</div>
					<div class="mb-3 mt-3">
						<label for="writeComm" class="form-label">Commission : </label> <input
							type="number" class="form-control" id="writeComm" min="0.01"
							, max="1.00" step="0.01" />
					</div>
					<div class="mb-3 mt-3">
						<label for="writeManager" class="form-label">Manager:</label> <select
							id="writeManager">
							<!-- select 로 보여질 때는 이름과 성이 보여지도록, 검사해서 태그를 볼 때는 그 사람의 사번이 나오도록 -->

						</select>
					</div>
					<div class="mb-3 mt-3">
						<label for="writeDepartment" class="form-label">Department:</label>
						<select id="writeDepartment"">
						</select>
					</div>


				</div>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-info writeBtn">Save</button>
					<button type="button" class="btn btn-danger writecloseBtn"
						data-bs-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>



	<!-- ----------------------------->
	<!-- 사원 수정을 위한 모달창-->
	<div class="modal" id="modifyModal" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">사원 수정</h4>
					<button type="button" class="btn-close modifycloseBtn"
						data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->




				<div class="modal-body">

					<div class="mb-3 mt-3">
						<label for="modifyEmpNo" class="form-label">EmployeeID:</label> <input
							type="text" class="form-control" id="modifyEmpNo">
					</div>


					<div class="mb-3 mt-3">
						<label for="modifyFristName" class="form-label">FirstName:</label>
						<input type="text" class="form-control" id="modifyFristName">
					</div>
					<div class="mb-3 mt-3">
						<label for="modifyLastName" class="form-label">LastName:</label> <input
							type="text" class="form-control" id="modifyLastName">
					</div>
					<div class="mb-3 mt-3">
						<label for="modifyEmail" class="form-label">Email:</label> <input
							type="text" class="form-control" id="modifyEmail">
					</div>
					<div class="mb-3 mt-3">
						<label for="modifyPhoneNumber" class="form-label">PhoneNumber:</label>
						<input type="text" class="form-control" id="modifyPhoneNumber">
					</div>

					<div class="mb-3 mt-3">
						<label for="modifyHireDate" class="form-label">HireDate:</label> <input
							type="date" class="form-control" id="modifyHireDate">
					</div>


					<div class="mb-3 mt-3">
						<label for="modifyJobID" class="form-label">JOB_ID:</label> <select
							id="modifyJobID">
						</select>
					</div>
					<div class="mb-3 mt-3">
						<label for="modifySalary" class="form-label">Salary : $<span
							id="modifySalary"></span>
						</label>
						<div id="salmodifyInput" style="display: none;"></div>
					</div>
					<div class="mb-3 mt-3">
						<label for="modifyComm" class="form-label">Commission : </label> <input
							type="number" class="form-control" id="modifyComm" min="0.01"
							, max="1.00" step="0.01" />
					</div>
					<div class="mb-3 mt-3">
						<label for="modifyManger" class="form-label">Manager:</label> <select
							id="modifyManger">
							<!-- select 로 보여질 때는 이름과 성이 보여지도록, 검사해서 태그를 볼 때는 그 사람의 사번이 나오도록 -->

						</select>
					</div>
					<div class="mb-3 mt-3">
						<label for="modifyDepartment" class="form-label">Department:</label>
						<select id="modifyDepartment"">

						</select>
					</div>


				</div>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-info modifyBtn">modify</button>
					<button type="button" class="btn btn-danger modifycloseBtn"
						data-bs-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>

	<!-- ------------------------------------- -->
	<!--  삭제를 위한 모달창 -->

	<!-- The Modal -->
	<div class="modal" id="removeModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">사원 삭제</h4>
					<button type="button" class="btn-close remCloseBtn"
						data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<span id="remEmpNo"></span>번 사원을 정말 삭제 하시겠습니까?
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger remBtn"
						data-bs-dismiss="modal">삭제</button>
					<button type="button" class="btn btn-danger remCloseBtn"
						data-bs-dismiss="modal">취소</button>
				</div>

			</div>
		</div>
	</div>

</body>
</html>