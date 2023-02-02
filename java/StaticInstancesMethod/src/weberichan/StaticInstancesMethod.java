package weberichan;

import java.util.Scanner;

//import java.lang.Math;
//java.lang은 기본 패키지 이기 때문에 안써도 에러가 나지 않는다.
//(기본 패키지는 import 쓰지 않아도 에러가 나지 않는다.)

public class StaticInstancesMethod {
	//Static과 non-Static 메서드의 메서드 호출 방법
	public static void main(String[] args) {
		// 1) static 키워드가 있는 멤버의 호출 방법 : 클래스명.맴버명()으로 호출
		System.out.println(	Math.abs(-3)); // 절대값 뽑아내는 Math 클래스의 함수, static 함수

		// 2) non-static(instance) : static 키워드가 없는 멤버
		// non-static은 기본적으로 메모리가 올라가지 않기 때문에,
		// 새로 객체를 생성해서 만들 데이터를 참조 시켜줘야한다.
		
		// 그 맴버를 가지고 있는 클래스로 부터 객체를 만들고, 객체명.멤버명()으로 호출
		Scanner sc = new Scanner(System.in); // 객체 생성. 객체명 : sc
		int num1 = 	sc.nextInt();
		System.out.println(num1);
		sc.close();
		sc = null;
		
		System.gc(); // 가비지컬렉터 호출, 안쓰는 메모리를 수거함
		
		System.out.print("num>>>");
		int num = sc.nextInt();
		System.out.println(num);
	}

}
//기본적으로 static이 쓰기는 편하지만
//메모리를 좀 더 효율적으로 관리 하기 위해서는 non-static을 쓰는게 좋다.
// 왜냐하면 non-static은 기본적으로 차지하는 메모리가 없기 떄문에, 원할 떄마다 메모리를
// 늘리거나 줄이기에 좋기 때문