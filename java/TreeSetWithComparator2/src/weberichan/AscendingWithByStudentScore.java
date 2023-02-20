package weberichan;


public class AscendingWithByStudentScore  implements Sortable {

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
