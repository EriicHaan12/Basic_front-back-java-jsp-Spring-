import java.util.Arrays;

public class ArrayEx1 {

	public static void main(String[] args) {
		
		
		int a ; 
		//System.out.println(a); // 지역변수는 초기화를 하지 않으면 사용할 수 없다.
		
		int[] scores = new int[3]; // 객체는 자동 초기화 된다.[0,0,0]
		System.out.println(scores.hashCode());	// 빈배열을 println를 통해 콘솔창의 띄우면
		// 그 객체의 주소? 값이 뜬다.
		//Int타입의 기본 값은 0 이다
		
		String[] names = new String[5];
		System.out.println(names);	
		//String 타입의 기본값은 null
		
		//Boolean 기본 값 : false
		//Float 기본 값  : 0.0F
		//Double 기본 값 : 0.0D
		
		boolean isOk[]= new boolean[2];
		System.out.println(isOk); // isOk[false, false]
		
		
		//배열의 요소에 값 할당 (초기화 시키기)
		scores[0]=45;
		scores[1]=23;
		scores[2]=100;
		
		//scores[3]=45; 런타임에러 발생(runtime exception) : 배열의 범위에서 벗어남.
		//코드를 칠 때는 에러 코드가 발생 하지 않지만 실행 시키면 에러가 뜬다.
		System.out.println(scores[2]);
		//
		
		
		//기존의 scores 배열이 참조하던 배열에서 다른 배열로 참조됨.
		//new 연산자 : 새롭게 메모리를 할당하고, 객체를 만드는 연산자.
 		scores = new int[4];  
		System.out.println(scores.hashCode());
		// 크기가 정적이라 이렇게 해도 배열의 크기가 늘어나지 않는다. 
		// 대신 이렇게 쓰게 되면 기존 배열에 덮어 씌여 진다.
		
		//그렇다고 혹시 이렇게 만들어보면?
//		scores = new char[3]; 
		// 마찬가지로 에러 : 맨 위에서 scores 를 int로 쓰겠다고 
		//선언 해놨기 때문에 에러가 뜬다.
		System.out.println(scores[2]);//배열이 초기화 된 모습을 볼 수 있다.
	
		//배열의 각 요소에 값을 할당 할 때 아래와 같은 방법을 쓰면 편리하다.
		int[] scores2 = {100,20,30,55,23};
		System.out.println(scores2.hashCode());
		
		String[] heros = {"아이언맨","스파이더맨","헐크"};
		//자바에서 중괄호{} 는 객체를 의미 하지 않는다.
		
		//배열의 각요소를 화면에 출력하는 방법
		for (int i = 0; i < heros.length; i++) {
			System.out.println(heros[i]);
		}
		//JS의 for of 와 같은 문법
		for (String hero : heros) {
			System.out.println(hero);
		}
		
		//배열의 각 요소를 간단하게 출력 하는 방법 : Arrays.toString(배열명);
		System.out.println(Arrays.toString(scores2));


	}


	
}
