package weberichan;

public class Student {
	//1.멤버 변수 
	private String stuNo ;
	private String stuName;

	private int kor;
	private int eng;
	private int math;
	
	private int tot;
	private float avg;
	private char grade;
	
//	private static int totalEvery; //전체 총점
//	private static int avgEvery; //전체 평균
	
	//2.생성자
	//입력 받을 매개변수 기입
	public Student(String stuNo, String stuName,int kor,int eng ,int math) {
	this.stuNo= stuNo;
	this.stuName=stuName;
	this.kor= kor;
	this.eng= eng;
	this.math=math;
	
	this.tot = this.kor+this.eng+this.math;
	this.avg=	(float)this.tot/3f;
	
	
	setTot();
	setAvg();
	setGrade();
	
	}	

	//3.메서드 생성
//	(맴버, 생성,변수, getter,setterㅁ, 출력장치)= 이걸 종합해서 'bean'이라 부르기도 한다.
		//getter생성
	//source -> generate getter,setter 에서 쉽게 만들 수도 있다.
	
	//생성자를 이용한 깊은 복사
	
	//객체 = 사용자 정의 데이터 타입 과 같은 의미 이기 때문에,
	//데이터 타입으로도 지정될 수 있다.
	
	//즉 이전에 Class로써 만들어준 객체 Student를 데이터 타입 속성으로써 사용 할 수 있기 때문에,
	// 데이터 타입 속성으로써 사용 될 수 있다.
	
	public Student(Student originStudent) {
		this.stuNo= originStudent.stuNo;
		this.stuName=originStudent.stuName;
		this.kor= originStudent.kor;
		this.eng=originStudent.eng;
		this.math=originStudent.math;
		
//		this.tot= originStudent.tot;
//		this.avg=originStudent.avg;
//		this.grade=originStudent.grade;
		
		setTot();
		setAvg();
		setGrade();
		
	}
	

	public String getStuNo() {
		return stuNo;
	}

	public String getStuName() {
		return stuName;
	}

	public int getKor() {
		return kor;
	}

	public int getEng() {
		return eng;
	}

	public int getMath() {
		return math;
	}

	public int getTot() {
		return tot;
	}

	public float getAvg() {
		return avg;
	}

	public char getGrade() {
		return grade;
	}
	
	//현재로써는 기능 구현 불가능 
	
//	public static int getTotEvery() {
//		return totalEvery;
//	//return Student.totalEvery;
//	}
//	public static int getAvgEvery() {
//		return avgEvery;
//	//return Student.avgEvery;// 같은 클래스라 생략 가능
//	}
	
	
	//setter 생성
	//객체가 만들어 진 뒤 setter가 생성 되기 때문에
	// setter를 지정 해줄 때는 pk가 무엇인지, 수정되면 안되는 것들이 어떤것들이 있는지 파악하고
	//setter를 만들어 주도록 하자.
	//setter를 만들지 않고 getter만 만들었을 때는 read only 라는 것을 내포 하고 있다.
	
	public void setStuName(String stuName) {
		this.stuName=stuName;
	}
	
	public void setKor(int kor) {
		if(kor>=0&&kor<=100) {
			this.kor=kor;
			setTot();//  변경된 점수 때문에 총점을 다시 구해줘야 한다.
			setAvg();// 마찬가지로 다시 구해줘야함..
			setGrade();
		}
	}
	public void setEng(int eng) {
		if(eng>=0&&eng<=100) {
			this.eng=eng;
			setTot();
			setAvg();
			setGrade();
		}
	}
	public void setMath(int math) {
		if(math>=0&&math<=100) {
			this.math=math;
			setTot();
			setAvg();
			setGrade();
		}
	}
	
	//전체총점, 전체 평균은 지금으로써는 구할 수 없다.
//	public static void setTotEvery() {
//		
//	
//	}
	//public static void setAvgEvery(){
//	}
//	

	
	
	public void setTot() {
		//유효성을 검사 해줘야 하기 때문에 int tot 받으면 안된다...
		//
		this.tot = this.kor+this.eng+this.math;
	}
	
	public void setAvg() {
		this.avg = (float)this.tot/3;
	}
	
	
	public void setGrade() {
		switch ((int)Math.floor(this.avg/10d)) { 

		case 10:
		case 9:	
			this.grade ='A';
			break;
		case 8 :
			this.grade = 'B';
		break;
		case 7 :
			this.grade = 'C';
		break;
		case 6 :
			this.grade  = 'D';
		break;
		
		default:
			this.grade  = 'F';
			break;
			}
		
	}
	// 4.메서드 생성
	public String toString() {
		return  "학번 : "+ this.stuNo + ", " + 
				"이름 : "+ this.stuName+", "+
				"국어 : "+ this.kor+ ", "+
				"영어 : "+ this.eng+ ", "+
				"수학 : "+ this.math+ ", "+
				"총점 : "+ this.tot+ ", "+
				"평균 : "+ this.avg+ ", "+
				"학점 : "+ this.grade;		
	}
	
	
	
	
}

