package weberichan;


class ParentA {
	
	//멤버 변수
	private int a = 100;
	
	public int getA() {
		return this.a;
	}
	
}

//class ParentD{
//	
//}
//자바에서는 다중 상속을 표현하는 문법 자체가 없다.syntax error
//
//class ETC extends ParentA ParentD{
//	
//}


public class InheritanceEx2Test  extends ParentA {
	public static void main(String[] args) {
	
		//ParentA() 객체 생성
	System.out.println(	new	ParentA().getA()); // 자기 자신의 멤버를 사용, 상속으로 만든게 아님

	System.out.println(new Child().getA()); // Child 객체의 멤버는 아니지만 상속을 받은 멤버이기에 사용 가능

	System.out.println(new InheritanceEx2Test().getA());
	
	ParentA objA = new ParentA();
	
	Child objChild = new Child();
	
	if(objChild instanceof Child) {// objChild 가 Child의 객체이냐? 라고 묻는거 
		//만들어준 objChild 객체는 Child와 ParentA 
		System.out.println("네");
	}else {
		System.out.println("아니오");
	}
	
	if(objChild instanceof ParentA) {// objChild 가 Child의 객체이냐? 라고 묻는거 
		//네-  Child의 부모가 ParentA이기 때문 is- a 관계가 성립
		//만들어준 objChild 객체는 Child와 ParentA 
		System.out.println("네");
	}else {
		System.out.println("아니오");
	}
//	if(objChild instanceof ParentD) {
		// objChild 가 ParentD의 객체이냐? (상속관계가 아니기 때문에 비교 자체가 불가)
		
		//만들어준 objChild 객체는 Child와 ParentA 
		
//		
//		System.out.println("네");
//	}else {
//		System.out.println("아니오");
//	}
//	
	
	
	}
}



class Child extends ParentA{
	
} 