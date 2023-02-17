package weberichan;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class StuClass {
		private int classNo;
		private Set<Student> stuSet;
		
		
	public StuClass(int classNo) {
			super();
			this.classNo = classNo;
			this.stuSet = new TreeSet<>();
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
