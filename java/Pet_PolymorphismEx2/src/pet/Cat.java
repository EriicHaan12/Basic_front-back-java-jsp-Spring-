package pet;


//1. 다른 패키지에 있는 부모 클래스를 가져오기 위한 부모클래스의 패키지 import 

import weberichan.Pet;



//2. 상속 관계 만들어주기 --원래는 extends + 부모 클래스를 적으면 되지만,
// pakage가 다른 경우 접근 방법이 달라진다.

//3. 부모 pet 클래스에서 만들어준  추상 메소드 속성을 이용하기 위해Cat 클래스를 추상 클래스로 만들어주기
// 클래스 내부에 추상 메소드를 써주기 위해서

// 추상 클래스는 내부에 일반 메소드도 가질 수 있고, 추상 메소드도 가질 수 있음

public class Cat extends Pet {

	public Cat(String name, String type, int age) {
		super(name, type, age);
		// TODO Auto-generated constructor stub
	}
	
	
	//3. 현재 따로 pet이라는 종의 메소드를 구현하기 힘드니 추상 메소드로 만들어주기...
	// 이렇게 되면 pet 의 하위 클래스에서 메소드를 따로 구현해주겠다는 뜻임.
	// doCry 추상 메서드 만들어주기
	@Override
	public void doCry() {
	
		System.out.println("야옹야옹");
	}


	//4.toString을 따로 만들어 부모클래스의 toString 속성 가져와서 쓰기
	public String toString() {
		return "[Cat]"+super.toString();
	}
	
}
