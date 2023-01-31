
public class DataTypeAndVariables {
	public static void main(String[] args) {
			//boolean : true || false 를 저장 1byte
		boolean bool = true;
 System.out.println(bool);		
 
 	//char : 문자형. 2byte. 
 //	한 문자를 저장 할 수 있다.
 char character = '\u0041';		// \u0041 : 유니코드 'A'
 System.out.println(character);
 
 	//byte : 정수형. 1byte (-128~127 사이 정수)
 	byte b = 127;
 	System.out.println(b);
// 	b=b+1; // 에러 : b+1 의 값이 byte의 범위를 넘는다.
 	
 	// 0 때문에 항상 최소값이 1만큼 더 크다.
 	
 	System.out.println("Byte타입의 최대값 : " +  Byte.MAX_VALUE); // Byte : byte 타입의 클래스
 	System.out.println("Byte타입의 최소값 : " +  Byte.MIN_VALUE);
	
 	//short : 정수형 . 2byte
 	short sInt = 128;
 	System.out.println(sInt);
 	System.out.println("short 타입의 최대값 : "+ Short.MAX_VALUE); // short : short 타입의 클래스, 최대값: 32767
 	System.out.println("short 타입의 최소값 : "+ Short.MIN_VALUE);//최소값: -32768
	
 	// int : 정수형. 4byte 
 	// 정수형 타입의 기본 타입
 	int i = 35;
 	
 	System.out.println("int 타입의 최대값 : "+ Integer.MAX_VALUE); // Integer : int 타입의 클래스 . 최대값: 2147483647
 	System.out.println("int 타입의 최소값 : "+ Integer.MIN_VALUE); //최소값 : -2147483648
 	
 	// long : 정수형. 8 byte
 	long l = 35l; // ㅣ 또는 L (리터럴 상수)를 붙여 long 타입 임을 명시해야만 한다.
 	// long l = 35L;
 	System.out.println("Long 타입의 최대값 : "+ Long.MIN_VALUE);
 	System.out.println("Long 타입의 최소값 : "+ Long.MIN_VALUE);
 	
 	
 	//float : 실수형. 4byte
 	float f = 3.14f; // f 또는 F 를 붙여 float임을 명시해야만 한다.
 	System.out.println("float 타입의 최대값 : "+ Float.MAX_VALUE);
 	System.out.println("float 타입의 최소값 : "+ Float.MIN_VALUE);
 	
 	//double : 실수형. 8byte
 	double d = 3.14d; // 리터럴 상수를 생략하거나  d 또는 D를 붙여 명시
	System.out.println("double 타입의 최대값 : "+ Double.MAX_VALUE);
	System.out.println("double 타입의 최소값: "+ Double.MIN_VALUE);
	
	
	
	
	//문자열 : 기본 데이터 타입이 아닌 참조타입. String
	String str = "대한민국";
	System.out.println(str);
	System.out.println("str의 길이 : "+str.length()); // string 타입 길이 구하기
	
	
	//Java에서는 Undefined 타입은 없다.
	//null : 기본 데이터 타입은 아님. 무튼 있음.
	String str2 = null;
	System.out.println(str2);
	
	
	
	
	
	
	}
	

}
