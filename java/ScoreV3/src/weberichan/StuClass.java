package weberichan;

import java.util.Iterator;

public class StuClass {

	private int no; // 반 번호
	private String className; // 반 이름
	private Student[] stuList;// 학생 목록(포함관계)
	//배열의 선언, 중괄호를 비워두고, new를 쓰지 않으면,
	// 선언만 되고 안에 값이 생성 되진 않는다.
	
	public static final int STUDENT_COUNT  =3; // 학생을 담는 배열의 길이 (상수)
	//상수만큼은 멤버지만 public 하게 만드는 것이 좋다.
	
	private int totalEvery;// 학생 전체 총점
	private float totalAvg;// 학생 전체 평균
	
	//생성자
	public StuClass(int no, String className) {
		this.no = no;
		this.className= className;
		this.stuList=new Student[STUDENT_COUNT];
	}
	
	//새로운 생성자, 오버로딩
	public StuClass (int no, String className, Student[] stuList) {
		this.no=no;
		this.className= className;
		this.stuList= stuList; //?
	}
	
	
	//getter
	public int getNo() {
		return this.no;
	}
	
	public String getClassName() {
		return this.className;
	}

	public Student[] getStuList() {
		return this.stuList;
	}
	
	//setter
	public void setClassName(String classNmae) {
		this.className =classNmae;
	}
	//학생 한명을 받아 stuList 배열의 no번쨰 배열에 추가
	public void addStudent(Student student, int no) {
		this.stuList[no] = student;
	}
	//전체 학생의 목록 출력
	public void outputEntireStudent() {
		ScoreV3 sc = new ScoreV3(); 
		for (int i = 0; i <sc.getCurSavedStudentCnt(); i++) {
			System.out.println(this.stuList[i].toString());
		}
	}
	//
	
	
	public int calcTotEvery() {
		this.totalEvery= 0;
		ScoreV3 sc = new ScoreV3();
		for (int i = 0; i < sc.getCurSavedStudentCnt(); i++) {
			this.totalEvery+= this.stuList[i].getTot();
			
		}
		return this.totalEvery;
	}
	
	public float calcTotalAvg() {
		ScoreV3 sc = new ScoreV3();
	this.totalAvg=(this.totalEvery /3) / (float)(sc.getCurSavedStudentCnt());
	this.totalAvg = Math.round(totalAvg*100)/100;
	return this.totalAvg;
	}
	
	
		
	public String toString() {
		 return "반 번호 : " +this.no +
				 "반 이름 :" + this.className;
	}

	 void findStudentByNo(String stuNo) {
		// stuNo학번을 가진 학생을 this.StuList(배열)에서 검색
		 boolean isFind = false;
		 for(Student s : this.stuList) {
			 if(stuNo.equals(s.getStuNo())) {
				 System.out.println("************* 검색된 학생 정보*********");
				 System.out.println(s.toString());
				 isFind = true;
			 }
		 }
		 if(!isFind) {
			 System.out.println("************* 검색한 학생은 없습니다.*********");
		 }
		
	}

	 void deleteStudentByNo(String stuNo) {
		boolean isFind = false;
		 for (int i = 0; i < this.stuList.length; i++) {
			if(stuNo.equals(this.stuList[i].getStuNo())) {
			System.out.println("******** 해당 학생의 정보가 삭제 되었습니다.*******");
			this.stuList[i]=null;
			isFind = true;
			}	
		}if(!isFind){
			System.out.println("************* 검색한 학생은 없습니다.*********");
		}
		 
		 {
		
		}
		
	}



}
