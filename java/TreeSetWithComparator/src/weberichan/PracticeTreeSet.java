package weberichan;

import java.util.Set;
import java.util.TreeSet;

public class PracticeTreeSet {

	public static void main(String[] args) {

		StuClass class1 = new StuClass(1);

		//TreeSet 은 자동정렬 되어야 하지만,
		// 이렇게 객체를 넣어줬을 때는, 정렬기준을 정해줘야만 한다.
		//그렇지 않으면  Comparable 클래스에서 캐스팅을 할 수 없다는 
		// ClassCastException 에러가 뜬다.
		//즉, 비교할 기준점을 잡아줘야만 한다.
		
		Student s1 = new Student("마동석","230002", 98);
		Student s2 = new Student("고라니","230012", 34);
		Student s3 = new Student("너구리","230006", 56);
		Student s4 = new Student("다람쥐","230008", 77);

		
		class1.addStudent(s1);
		class1.addStudent(s2);
		class1.addStudent(s3);
		class1.addStudent(s4);
		
		class1.outputEntireStudent();

	}
}
