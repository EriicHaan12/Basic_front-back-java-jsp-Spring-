package weberichan;

//패키지명은 항상 소문자로만 쓴다(암묵적인 룰)

public class OOPEX1_BasicConcept {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//class는 틀만 만들어주고 거기에 맞춰 car라는 Object (그 틀에 대한 규칙을 지켜 만든)
		//클래스 내에 car1,car2 라는 객체를 만들어 준다는 개념..?
		Car car1 = new Car();
		Car car2 = new Car();
		car2.color = "흰색";	//클래스 내에 속성 데이터 수정
		
	
//		System.out.println("granduer1의 모델명 : "+ granduer1.modelName + 
//							"가격 : " + granduer1.price +
//							"색상 : "+ granduer1.color);
//		System.out.println("granduer2의 모델명 : "+ granduer2.modelName + 
//							"가격 : " + granduer2.price+
//							"색상 : "+ granduer2.color);
		
		
		//non-static 으로 만든 
		//Car 객체의 멤버 메서드 displayCar() 호출 하는 방법
			System.out.println("========");
			car1.displayCar();
			System.out.println("========");
			car2.displayCar();
			
			
			
	}

}

