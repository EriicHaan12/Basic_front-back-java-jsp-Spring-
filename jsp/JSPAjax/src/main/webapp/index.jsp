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
	let inputSal=0;
	let inputManagerId="";
	let inputDeptId ="";
	
	let allEmpData =null;
	let allDeptData=null;
	//manager 및 department select할 때 insert 하기 위한 전역변수
	
	$(document).ready(function() {
						getAllEmployees();
//직업을 입력받기 위해 필요한 db의 jobs 테이블
						getJobsData();
						
						getDeptInfo();

	$(".writeIcon").click(function() {

	//사원 입력시 필요한 부가 정보를 바인딩
		makeJobSelection();
						
		makeManagerSelection();
		
		makeDeptSelection();	
	$("#writeModal").show(500);
						});

	//직급 정보 입력시
	$("#writeJobID").change(function() {
	if ($(this).val() != '') {

				// alert($(this).attr('id'));
				//몇번째 select 박스를 선택한지 숫자가 나오는 코드
		let index = document.getElementById("writeJobID").selectedIndex;
			//options 속성 : select 태그 안에 있는 option 태그들 (배열 처럼 운용)
				//selectedIndex 속성 : 유저가 선택한 option 태그 번째
			//return 이 안되므로 전역변수에 값을 넘겨준다.
	inputJobId = document.getElementById("writeJobID").options[index].getAttribute("id")
		let minSal = 0;
		let maxSal = 0;
		$.each(jobs.JOBS,function(i, e) {
			if (inputJobId == e.JOB_ID) {
					minSal = e.MIN_SALARY;
					maxSal = e.MAX_SALARY;
				}});
			console.log(minSal, maxSal);
	makeSalaryInput(minSal, maxSal);

			}

		});
	
	$("#writeDepartment").change(function(){
		if ($(this).val() != '') {

	let index = document.getElementById("writeDepartment").selectedIndex;
		
	inputDeptId = document.getElementById("writeDepartment").options[index].getAttribute("id");
		}
	});
	
	 $("#writeManger").change(function(){
         if($(this).val() != '') {
            let index = document.getElementById("writeManger").selectedIndex;
            inputManagerId = document.getElementById("writeManger").options[index].getAttribute("id");
         }
      });
	
	
	//사원 저장 버튼클릭시
	$(".writeBtn").click(function(){ 
		//1. input 태그들로부터 유저가 입력한 값을 가져오기
		let	writeFristName = $("#writeFristName").val();
		let writeLastName= $("#writeLastName").val();
		let writeEmail= $("#writeEmail").val();
		let writePhoneNumber =$("#writePhoneNumber").val();
		let writeHireDate=$("#writeHireDate").val();
		let writeJobId=inputJobId;
		let writeSal =Number($("#writeSalary").html()); 
			//inputSal;
		let writeComm= $("#writeComm").val();
		let writeManagerId = inputManagerId;  //
		let writeDeptId = inputDeptId;  //
		
		//2. 유효성 검사하기
		let isValid=false;
		if(writeSal == 0){
			
			alert("급여를 선택하지 않았습니다")}
		else{
				isValid= true;
			}
			
		
		
		//3. 유효성 검사에 통과하면 ajax로 데이터를 서블릿으로 보내기
		let emp = {
				"FIRST_NAME" : writeFristName,
				"LAST_NAME" :writeLastName,
				"EMAIL" : writeEmail,
				"PHONE_NUMBER" : writePhoneNumber,
				"HIRE_DATE" : writeHireDate,
				"PHONE_NUMBER" :writePhoneNumber,
				"JOB_ID" :writeJobId,
				"SALARY" : writeSal,
				"COMMISSION_PCT" : writeComm,
				"MANAGER_ID" : writeManagerId,
				"DEPARTMENT_ID": writeDeptId
			};
		
		saveEmp(emp)
	});
	
	function saveEmp(emp){
		console.log(emp);
		};
		
	

	$(".writecloseBtn").click(function() {
			$("#writeModal").hide();})
		});

	//직급정보 select태그 박스에 동적 바인딩
	//jobs정보를 불러오기 위한 select 박스의 옵션 만드는 함수
	
	function makeJobSelection() {
		let output = "<option value=''>---직급을 선택하세요 ---</option>";
		$.each(jobs.JOBS, function(i, item) {
			output += "<option id="+item.JOB_ID+ ">" + item.JOB_TITLE
					+ "</option>"
		});
		$("#writeJobID").html(output);
	}

	function changeSal(sal){
		
		//	inputSal = sal;
		//$("#writeSalary").html(inputSal);
		$("#writeSalary").html(sal); // span 태그에 넣어줌
	//	inputSal = $("#writeSalary").html();
	}
	$("#writeManager").change(function(){
		if ($(this).val() != '') {
	let index = document.getElementById("writeJobID").selectedIndex;	
	inputJobId = document.getElementById("writeJobID").options[index].getAttribute("id")
		}	
	});
	
	
	


	//급여정보를 동적으로 태그에 바인딩
	function makeSalaryInput(minSal, maxSal) {
		// select 박스 선택 후 나오는 salary range 만들기
			let output="<input type='range' class='form-range' min='" + minSal+"' max='"
			+ maxSal + "' id ='wirteSal' onchange='changeSal(this.value);' step='100'>";
			$("#salInput").html(output);
			$("#salInput").show();
	
	}
	
	
	
	

	// 모든 직원 데이터를 얻어오는 함수
	function getAllEmployees() {
		$.ajax({
			url : "getAllemployees.do", // 데이터가 송수신될 서버의 주소 // 서블릿 파일의 매핑 주소를 쓰면 된다.
			type : "GET", // 통신 방식 (GET, POST, PUT, DELETE)
			dataType : "json", // 수신받을 데이터 타입(MIME TYPE)
			success : function(data) { // 통신이 성공하면 수행할 함수(콜백 함수)
				console.log(data);
				if (data.status == "fail") {
					alert("데이터를 불러오지 못했습니다.")
				}

				else if (data.status == "success") {
					console.log(data);
					allEmpData =data;
					outputEntireEmployees(data);
				}
			}
		});
	}

	//모든 부서 데이터를 얻어오는 함수
	function getDeptInfo(){
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
				 allDeptData=data;
			}

		},
		error : function() {

		},
		complete : function() {

		}
	});
	
	}
	function makeDeptSelection(){
		let output = "<option value=''>---부서를 선택하세요 ---</option>";
			/////////////////////////////////////////////////////////////////////////////////
			$.each(allDeptData.depts, function(i, e) {
				output += "<option id='"+ e.DEPARTMENT_ID + "'>"+e.DEPARTMENT_NAME + "</option>";
				
			});
			$("#writeDepartment").html(output);
	}
	
	//매니저 정보 select 태그 박스에 동적 바인딩
	function makeManagerSelection(){
		let output = "<option value=''>---매니저를 선택하세요 ---</option>"
	
			$.each(allEmpData.employees, function(i, e) {
				output += "<option id='"+ e.EMPLOYEE_ID + "'>"+ e.FIRST_NAME + "," + e.LAST_NAME+"</option>"
				
			});
			$("#writeManger").html(output);
	
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

	function outputEntireEmployees(json) {
		let output = "<div class ='row' >"
		$("#outputCnt").html("데이터 : " + json.count + "개");
		$("#outputDate").html("출력 일시 : " + json.output);

		output = "<table class='table table-striped empInfo'>"
		output += "<thead><tr><th>사번</th><th>이름</th><th>이메일</th><th>전화번호</th><th>입사일</th><th>직급</th>"
				+ "<th>급여</th><th>커미션(%)</th><th>직속상관이름</th><th>소속부서명</th>"
				+ "</tr></thead><tbody>"

		$.each(json.employees,
				function(i, item) {
					output += "<tr class='emp'>"
					output += "<td>" + item.EMPLOYEE_ID + "</td>";
					output += "<td>" + item.FIRST_NAME + "," + item.LAST_NAME
							+ "</td>";
					output += "<td>" + item.EMAIL + "</td>";
					output += "<td>" + item.PHONE_NUMBER + "</td>";
					output += "<td>" + item.HIRE_DATE + "</td>";
					output += "<td>" + item.JOB_ID + "</td>";
					output += "<td>" + item.SALARY + "</td>";

					if (item.COMMISSION_PCT == "0.0") {
						output += "<td></td>";
					} else {
						output += "<td>" + item.COMMISSION_PCT * 100 + "</td>";
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
					output += "</tr>"

				});
		output += "</tbody></table>"
		$(".empInfo").html(output);
		console.log(json)
	}
</script>
<style type="text/css">
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
</style>
</head>
<body>
	<div class="container">
		<h1>Employees CRUD With Ajax</h1>

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
	<!-- wrtier 이미지클릭했을 때만 나타나는 모달창 -->
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
						<div id="salInput" style="display:none;"></div>
					</div>
					<div class="mb-3 mt-3">
						<label for="writeComm" class="form-label">Commission : </label>
						<input type="number" class="form-control" id="writeComm" min="0.01", max="1.00" step="0.01"/>
					</div>
					<div class="mb-3 mt-3">
						<label for="writeManger" class="form-label">Manager:</label> <select
							id="writeManger">
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




</body>
</html>