package weberichan;

public class Student implements Comparable<Student> {
	private String stuName;
	private String stuNo;
	private int score;
	
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuNo() {
		return stuNo;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	Student(String stuName, String stuNo, int score) {
		super();
		this.stuName = stuName;
		this.stuNo = stuNo;
		this.score = score;
	}
	@Override
	public String toString() {
		return "Student [stuName=" + stuName + ", stuNo=" + stuNo + ", score=" + score + "]";
	}
	// java.lang.Comparable을 상속받아 구현할 때는 아래의 메서드를 오버라이딩한다.
	@Override
	public int compareTo(Student o) {
			int result = 0 ; 
			if(this.score < o.score) {
				result =-1;
			}else if(this.score> o.score) {
				result = 1;
			}
		
		return result;
//		result result*-1; 오름차순 내림차순 설정
	}
	
	//java.util.Comparator 를 상속받아 구현할 때 는 아래의 메서드를 오버라이딩
	// 사용방법이 약간 다른다.
//	@Override
//	public int compare(Student o1, Student o2) {
//		
//		if(o1.score<o2.score) {
//			return -1;	
//		}else if(o1.score==o2.score){
//			return 0;
//		} else {
//			return 1;	
//		}
	
	
	
}

