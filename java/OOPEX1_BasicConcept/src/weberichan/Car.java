package weberichan;

public class Car {
	//속성
	String brandName = "현대";
	//문자열은 값이 같을 경우 저장될 때 주소, id값도 같다
	// 데이터 절약을 위해 같은 주소,id 값을 참조 하기 때문.
	String modelName = "그랜져";
	int price = 45000000;
	String color = "검정색";
	
	//멤버 메서드
	//메서드 생성법
	
	//[접근제한자][static(optional)]반환값의 데이터 타입 | void 메서드명(매개변수1,매개변수2...(없을수도 있다)){

	
	// Car 객체의 속성값을 화면에 출력한다
	
	public void displayCar() {
		System.out.println("브랜드명 : "+ brandName); // 같은 클래스 안이라서 
		System.out.println("모델명 : "+ modelName); // 
		System.out.println("가격 : "+ price);
		System.out.println("색상 : "+ color);
	}
	
	
}
