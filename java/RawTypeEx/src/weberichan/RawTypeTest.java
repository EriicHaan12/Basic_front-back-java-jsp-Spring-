package weberichan;

import java.util.ArrayList;
import java.util.List;

public class RawTypeTest {

	public static void main(String[] args) {
		ArrayList ar = new ArrayList<>(); // raw타입의 ArraylIST 객체 생성
		
		//raw타입으로 생성된 ArrayList ar에는 모든 데이터타입이 다 저장 될 수 있다.
		//Raw Type?은 넣는 대로 데이터를 다 넣을 수 있기 때문에 쓰긴 편할지 언정, 협업 유지 보수에 
		// 적합하지 않을 수 있다. 
		// --> 데이터 타입의 안정성을 보장 하지 못한다.
		ar.add(10);
		ar.add(3.14f);
		ar.add(3.14159253d);
		ar.add("대한민국");
		ar.add(new Computer());
		
		// 고로 Generic 타입으로 사용 할 것을 권고
		ArrayList<String> ar2= new ArrayList<>(); //new 뒤의 <>안에 데이터 타입은 생략 가능하다.
		//ArrayList<String ar2> = new ArrayList<String>();
		ar2.add("스트링만");
		ar2.add("저장된다 이말이야");
//		ar2.add(new Computer()); // 에러
		
		//Generic 타입은 참조 타입만 사용 가능 (배열타입,열거타입,클래스 타입, 인터페이스 ...)
//		ArrayList<int>ar3 = new ArrayList<>() 에러...
		ArrayList<Integer> ar3 = new ArrayList<>(); // 	ArrayList 객체를 List 컬렉션 속성으로 바꿔준다.
		
	
		
		//위의 형식 보다 아래처럼 인터페이스로 다형성을 구현하여 사용하는 것이 이점이 있다.
		List<String> ar4 = new ArrayList<>();
		
		
	// 인터페이스를 이용하여 다형성을 구현하여 사용하는 것에 대한 이점
		
		// List 인터페이스의 하위 다른 클래스로 변경 해야 할 때 용이. (데이터 타입이 유연해짐)
		// 인터페이스의 특정 구현과 내 코드를 분리하기 위해서.
		
	}

}
