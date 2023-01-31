
public class DataTypeCasting {

	public static void main(String[]args) {
		
		byte b =123;
		System.out.println(b);

		//묵시적 형변환(casting) : 프로그래머가 형 변환 연산자를 쓰지 않더라도 자동으로 되는 형변환.
		// 보통. 작인 타입에서 큰 타입으로 변할 때 묵시적 형 변환이 일어난다.
		
		int i = b; // 인트 타입이 byte 보다 크기 때문에 묵시적 형변환이 된다.
		
		
		
		//명시적 형변환 :  프로그래머가 형 변환을 하라고 명시해 놓는 것.(큰타입 -> 작은 타입으로 변환)
		// 앞에 (형변환 할 데이터 타입)캐스팅 할 변수
		double pi = 3.141592;
		
	//	float fPi = pi; 에러) float 가 double 보다 작기 때문에 묵시적 형변환이 안된다.
	// 쉽게 이해하자면 작은 박스를 큰박스 안에 넣는 건 쉽지만, 작은 박스 안에 큰 박스를 넣는 건 불가능 하기 때문에
		
		
		float fPi = (float)pi;
		System.out.println(fPi);
		
		
		
		// 만약 큰 데이터 타입-> 작은 데이터 타입으로 명시적 형변환을 하는데,
		// 작은 데이터 타입이 형용 할 수 없는 법위의 데이터 이면, 그 데이터 타입 만큼 잘려 나가고 저장된다.
		
		int i2 = 2147483647; // 32767 int값도 되지만 short 타입의 범위에 있다.
		short s = (short)i2; // 명시적 형변환 해도 underflow가 발생 하지 않는다.
		System.out.println(s);
		
	//그래서 만약 명시적 형변환을 쓰ㅔ 될 경우 형변환 되는 변수와 형변환 하려고 하는 변수가 같은 범위에 있는 것이 좋다.

	
		// 문자는 내부적으로 아스키 코드 값이 있기 때문에 아래와 같은 코딩이 가능
	char c = 'a';
	System.out.println(c); // 'a'
	
	System.out.println((int)c); // 뜬금없이 97이 뜨는데 
	//문자는 내부적으로 아스키 코드값을 가지고 있고, int 타입으로 바꿔줄 시 아스키 코드 값으로 변환된다.
	
	System.out.println((char)98);// 'b' 반대 되는 상황에서도 가능하다.
	
	// boolean 은 논리형이고, float 는 4byte 실수 이지만, 아예 다른 타입이기 때문에 형 변환이 불가하다.
	//boolean bool = true;
	//float f2= bool;
	}
	
}
