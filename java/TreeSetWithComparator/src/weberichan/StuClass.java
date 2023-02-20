package weberichan;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class StuClass {
		private int classNo;
		private Set<Student> stuSet;
		
		
	public StuClass(int classNo) {
			super();
			this.classNo = classNo;
			//이곳을 DescendingByStudentName()으로 바꾸면 정렬 순서를 다시 바꿀 수 있다.
			this.stuSet = new HashSet<>();		
	}

	public int getClassNo() {
		return classNo;
	}


	public void setClassNo(int classNo) {
		this.classNo = classNo;
	}


	public Set<Student> getStuSet() {
		return stuSet;
	}

	public void setStuSet(Set<Student> stuSet) {
		this.stuSet = stuSet;
	}
	
	public void addStudent(Student s) {
		this.stuSet.add(s);
	}
	public void outputEntireStudent() {
		for (Student student : this.stuSet) {
			System.out.println(student.toString());
		}
	}

	@Override
	public String toString() {
		return "StuClass [classNo=" + classNo + ", stuSet=" + stuSet + "]";
	}
		
}
