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
	}

}
