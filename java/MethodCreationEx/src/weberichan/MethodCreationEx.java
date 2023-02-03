package weberichan;

//다양하게 메서드를 만드는 방법과 호출하는 방법을 연습해보자
public class MethodCreationEx {

	// static 메서드 만들기
	public static int add(int a , int b) {
		int result = a+b;

		return result;
	}
	// 메서드 오버로딩으로 만드는 방법
	public static int add (int num1, int num2, int num3) {
		return num1+num2+num3;
	}
	
	public void add(int n, float m) {
		
	}
	
	
	
	public static void main(String[] args) {
	int result = MethodCreationEx.add(3,5);
	
	System.out.println(add(300,500)); // 같은 클래스에 있으면 static 맴버를 호출할 때 클래스 명을 생략 해도 된다.
	// 호출하는 곳과 호출 되는 곳(클래스)가 같다면 메서드 호출 시 클래스명 생략 가능
	
	MethodCreationEx mce = new MethodCreationEx();
	
	float fResult =  mce.myFloatAdd(3.14f,5.65f); 
	
	
	//이름이 outputNTimes 이고, 매개변수를 String 타입 변수 하나와 n(정수)을 받아
	//그 문자열을 화면에 n번 반복 하여 출력하는 메서드(인스턴스 메서드)를 만드시고 호출하세요.
	
	mce.outputNTimes("안녕하세요",5);
	
	}
	public void outputNTimes(String str, int n) {
		for(int i = 0;i<n;i++) {
		System.out.println(str);
		}
	}


	// non -static 메서드 만들기
	public float myFloatAdd(float a,float b ) {
		
		float result = 0f;
		result =a+b;
		return result ;
		
	}
	
}
