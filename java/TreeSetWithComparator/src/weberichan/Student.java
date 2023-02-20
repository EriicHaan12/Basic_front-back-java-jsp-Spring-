package weberichan;

import java.util.Comparator;
import java.util.Objects;

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
	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int hashCode() {
		return this.stuNo.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
	
		if(obj instanceof Student) {
			Student tmp = (Student)obj;
			if(this.stuNo.equals(tmp.stuNo)) {
				result = true;
			}
		}
		return result;
		
	}

	
	
}

