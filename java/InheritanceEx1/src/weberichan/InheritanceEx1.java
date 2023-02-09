package weberichan;

import java.awt.Frame;
import java.awt.Window;

// doc에서 불러올 태그가 어떤 역할인지, 
// 부모태그가 어떤것인지 파악하고 불러오는것이
// 중요하다.

public class InheritanceEx1 extends Frame{
	
	//extends 확장 하기 위한 명령어
	//상속관계를 만들어주기 위한 단계
	// 이렇게 쓰게 되면 아빠(부모)클래스가 java.awt 라는 뜻
	
	//상속받게 되면 부모  클래스의 
	//멤버변수, 멤버상수, 생성자,멤머 메서드, 생성자도 물려받는다.
	
	public InheritanceEx1(String title) {
	super(title);
		
	}
	//this. = 현재 객체를 가르킴
	//this() 현재 객체가 가르키는 기본 생성자 호출
	
	//super. 부모 객체를 가르킴
	//super() 부모객체가 가르키는 기본 생성자 호출
	
	
	
	public static void main(String[] args) {
		InheritanceEx1 myWindow = new InheritanceEx1("나의 작은 윈도우");
		
		//객체가 잘 만들어진지 확인.
		System.out.println(myWindow.toString());
		
		
		//할아버지 클래스의 속성, 멤버를 불러 올수 있는지 확인
		
		//상속받은 클래스의 속성 및 멤버 등등은 public해서 쓸 수 있는 것이 아니라
		// 상속받았기 때문에 쓸 수 있는거임...
		
		myWindow.setVisible(true);
		
		Window myWin = new Window(myWindow);
//		myWin.  이렇게 쓰게 되면  Frame 의 클래스가 나타나지 않는다.
		//왜냐하면 Frame의 부모클래스인 Window까지만 만들었기 떄문...

		//myWindow. 이렇게 쓰게 되면 Frame 의 클래스까지 나온다.
		
		
		//  부모태그로 갈수록 더욱 추상적인 개념으로 바뀐다...
		// 자식태그로 내려갈수록 더욱 디테일, 구체화 된다.
		
		//즉 부모태그로 올라갈수록, 오버라이딩 할 수 있는 자원,멤버들이 많아진다.
		
		//자식태그는 이미 기능적으로 만들어진것들이 많기 때문에 오버라이딩 할 필요가 거의 없어진다.
		//왜냐면 이미 부모태그의 속성들을 다 물려받았기 때문...
		
		
	}

}
