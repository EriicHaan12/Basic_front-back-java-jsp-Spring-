package weberichan;

import java.util.Comparator;

public class DescendingByStudentName implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {

//		if(o1.getStuName()<o2.getStuName()) { //문자열끼리 관계연산자로 비교 할 수 없다.
//			
//		}
		
		return o1.getStuName().compareTo(o2.getStuName())*-1;
	}

}
