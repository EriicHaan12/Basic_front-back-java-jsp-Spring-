package weberichan;

import java.util.Comparator;

public class AscendingWithByStudentScore  implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
			int result = 0;
			if(o1.getScore()<o2.getScore()) {
				result = -1;
			} else if(o1.getScore()>o2.getScore()) {
				result =1;
			}
			
		return result;
	}

}
