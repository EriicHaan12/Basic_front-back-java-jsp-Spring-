package weberichan;

public class OOPEX2_Constructor {

	public static void main(String[] args) {

		MobilePhone phone =new MobilePhone(); // 객체 생성
	System.out.println(phone.hashCode());
	System.out.println(phone.toString());
	
	MobilePhone galaxy = new MobilePhone("삼성", "갤럭시s22", 256);
	
	System.out.println(galaxy.toString());
	
//	MobilePhone hwa = new MobliePhone("화웨이", 128);
	
	MobilePhone hwa =new MobilePhone("화웨이",null,0); //이렇게 호출 하면 생성자의 오버로딩 갯수가 줄어든다.
	System.out.println(hwa.toString());
	

	
	
	MobilePhone phone2 = new MobilePhone(null,"아이폰", 512);
	System.out.println(phone2.toString());

//	phone2.brandName = "애플";// 좋은 코드는 아니다.
	// 이 코드의 역할: 외부(다른 클래스)에서 멤버 변수의 값을 변경하려고 할 때(SETTER)
	
//	System.out.println(galaxy.brandName);
	//이 코드의 역할 : 외부(다른 클래스)에서 멤버 변수의 값을 얻어오려고 할 때(GETTER)
	
	
	//getter 쓰기
	System.out.println(galaxy.getBrandName());
	
	System.out.println(hwa.getModelName());
	
	System.out.println(phone2.getMainMemory());
	
	
	//setter 쓰기
	phone2.setBrandName("애플");
	//setter로 바꾼 값 출력
	System.out.println(phone2.toString());
	
if(	hwa.setMainMemory(1024)) {
	System.out.println("메모리 업그레이드 성공!");
}else {
	System.out.println("메모리 업그레이드 실패!");
}
System.out.println(hwa.toString());
	
	
	}
	
	

}
