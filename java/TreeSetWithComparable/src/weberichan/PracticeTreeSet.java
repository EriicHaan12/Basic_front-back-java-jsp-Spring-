package weberichan;

import java.util.Set;
import java.util.TreeSet;

public class PracticeTreeSet{

	public static void main(String[] args) {

		StuClass class1 = new StuClass(1);

		//TreeSet 은 자동정렬 되어야 하지만,
		// 이렇게 객체를 넣어줬을 때는, 정렬기준을 정해줘야만 한다.
		//그렇지 않으면  Comparable 클래스에서 캐스팅을 할 수 없다는 
		// ClassCastException 에러가 뜬다.
		//즉, 비교할 기준점을 잡아줘야만 한다.
		
		Student s1 = new Student("230001","둘리", 98);
		Student s2 = new Student("230002","도우너", 34);
		Student s3 = new Student("230003","마이콜", 56);
		Student s4 = new Student("230001","다람쥐", 77);

		
		class1.addStudent(s1);
		class1.addStudent(s2);
		class1.addStudent(s3);
		class1.addStudent(s4);
		//	for(Student stu : set) {
//		
//		System.out.println(stu.toString()+stu.hashCode());
//		}
	
	}


}
