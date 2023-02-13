package weberichan;

import java.util.Calendar;

public class SingleTonTest {

	public static void main(String[] args) {
		//SingleTon s = new SingleTon 생성자가 private 하므로 이런 방식으로 만들 수는 없다.
	
	for (int i = 0; i < 10; i++) {  //5개의 객체 모두 같은 hashcode를 가짐(같은 객체)
		SingleTon s = SingleTon.getInstance();
		System.out.println(s.hashCode());
	}
	
		//java.util.Calendar 클래스 또한 싱글톤으로 구현 되어 있어서 
	//아래와 같이 객체를 얻어 올 수 있다.
	Calendar c = Calendar.getInstance();
	System.out.println(c.toString());
	
	}

}
