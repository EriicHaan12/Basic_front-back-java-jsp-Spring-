import java.util.Arrays;

public class ArrayEx3_ArrayCopy {

	public static void main(String[]args) {
		//========================================Deep Copy
		char[] chArr = new char[] {
				'a','b','c'
		};
		//배열 선언, 생성,초기화(크기까지) 한번에 
		
		System.out.println("변경 전 원본 배열 : " + Arrays.toString(chArr));
	
	//원본 배열을 복사(deep copy)
	char[]copyChArr= new char [chArr.length];	
	int i =0;
	for(char c : chArr) {
		copyChArr[i++] = c;
	}
	// 원본 배열의 값을 수정(복사본이 원본의 영향을 받지 않는다.)
	chArr[1]='B';
	System.out.println("변경 후 원본 배열 : " +Arrays.toString(chArr));
	System.out.println("변경 후 copyChArr : " +Arrays.toString(copyChArr));
	//why? 원본배열의 주소값과 사본배열의 주소값이 다르다(=서로 다른 객체로 인식됨)
	System.out.println("변경 후 원본 배열 : " +chArr.hashCode());
	System.out.println("변경 후 copyChArr : " +copyChArr.hashCode());
	System.out.println("======================================");
	//==================================
	
	
	//===========================Shallow Copy
	String[] heros = new String[] {
			"아이언맨","헐크","스파이더맨"
	};
	System.out.println("변경 전 원본 배열 : " + Arrays.toString(heros));
	//얕은 복사(Shallow copy)수행
	
	String[] copyedHeros =  heros;
	System.out.println("변경 전 사본 배열 : " + Arrays.toString(copyedHeros));
	
	//원본 배열의 값을 수정
	heros[2]="캡틴아메리카";
	System.out.println("변경 후 원본 배열 : " + Arrays.toString(heros));
	System.out.println("변경 후 원본 배열 : " + Arrays.toString(copyedHeros));
	//why? 원본 배열의 주소값과 사본배열의 주소값이 같다(즉, 같은 객체이다)
	System.out.println("원본 배열의 주소 값 : " + heros.hashCode());
	System.out.println("사본 배열의 주소 값 : " + heros.hashCode());
	//==============================================================
	//String 타입의 특성
	//
//	String str1 = "대한민국";
	String str1= new String("대한민국");
//	String str2 = "대한민국";
	String str2 = new String("대한민국");
	
	//new 연산자를 통한 배열 생성과, 
	//new 연산자 를 쓰지 않고 생성한 배열의 차이 비교
	//new 연산자는 쓰게 되면 생성될 때 새로운 메모리 주소를 가지고 생성되기 때문에 
	// 내용이 같더라도 주소는 틀려지게 된다.
	//
	System.out.println(str1.hashCode());
	System.out.println(str2.hashCode());
	
	if(str1.equals(str2)) { // 같은 데이터 값이 들어와있는지 비교할 때 쓴다.
		System.out.println("같은 값!");
	}else {
		System.out.println("다른 값!");
	}
	
	
	str2+= "짱!"; //문자열이 참조하는 값이 달라지면 주소도 달라진다.
	System.out.println(str1.hashCode());
	System.out.println(str2.hashCode());
	if(str1==str2) { // str1의 주소와 str2의 주소가 같냐? 라는 뜻이 있다.
		System.out.println("같은 주소");
	}else {
		System.out.println("다른 주소");
	}
	
	System.out.println("===================================");
	
	String userid = "abcd1234";
	if(userid .equals("abcd1234")) { // userid가 참조하는 문자열의 값과 equal()안에 넣은 값이 같냐 라는 의미
		System.out.println("같은 값");
	}else {
		System.out.println("다른 값");
	}
	}
}
