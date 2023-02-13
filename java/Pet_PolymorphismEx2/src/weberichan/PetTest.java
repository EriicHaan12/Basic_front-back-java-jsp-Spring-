package weberichan;

// pet 패키기에 있는 모든 class를 import 하고 싶다면?
import pet.*;

public class PetTest {
	public static void main(String[] args) {
		//1. 메인 메소드에 새로운 Dog class의 객체 생성
		Dog dog1 = new Dog("장미","진돗개",3);
		
		
		//2.toString으로 출력
		System.out.println(dog1.toString());
		
		//3.doCry() 호출
		dog1.doCry();
		
		//Cat도 같은 방법으로 생성
		
		Cat cat1 = new Cat("나비","샴고양이",2);
		System.out.println(cat1.toString());
		cat1.doCry();
		
		
		System.out.println("===========================================");
		// 다형성 테스트
		//새로 Pet 클래스에 있는 멤버 속성을 이용해서 객체 생성
		Pet 우리집애완동물 = new Dog("두께","골든리트리버",2);// 업 캐스팅
	
		
		//우리집애완동물은 Pet type이지만, 묵시적으로 Dog 타입으로 다운 캐스팅 되어,
		//사실상 Dog.doCry()가 호출되는거나 다름없어진다. 
		// 1다향성
		우리집애완동물.doCry();  // 자식이 부모이름으로 된 걸 쓰는 것. 
		
		
		Pet 우리집애완동물2 = new Cat("냥이","페르시안",2);// 업캐스팅 
		우리집애완동물2.doCry();
		
		PetDoctor 두리틀 =new PetDoctor("두리틀",40);
		두리틀.doClinic(우리집애완동물2);
		
		두리틀.doClinic(우리집애완동물);
		
	//	Pet 우리집애완동물전용의사 = 두리틀; Type에러 . Pet 과 PetDoctor 는 상속관계가 아니므로 업캐스팅이 불가함.
	 
		Dog dog2 = (Dog)우리집애완동물; //다운캐스팅 Pet에 있는걸 자식클래스인 Dog가 다운캐스팅으로 사용가능
	 
		
	}

}
