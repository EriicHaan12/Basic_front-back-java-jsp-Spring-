package weberichan;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ThrowsTest {

	//예외를 또 미룰 수 있다.
	//사용자 정의 예외도 throws 를 쓸 수 있다.
	public static void main(String[] args) {
		System.out.print("숫자를 입력하세요 >>");
		int num =0;
		// 아래처럼 이 곳에서 예외처리를 할 수도 있고, 
		try {
			num = getPositiveNumber();
		} catch (InputMismatchException e) {
			System.out.println("숫자가 아닙니다");
		}
		
	System.out.println("num : " + num);

	System.out.print("문자열을 입력 하세요>>");
	
	
	Scanner sc = new Scanner(System.in);
	String str = sc.nextLine();
	int num2 = 0;
	try {
		// int java.lang.Inger.parseInt(String s )Throws NumberFormatException 로 선언되었기 때문에
		// NumberFormatException 예외 처리함
		num2 = Integer.parseInt(str);
	} catch (NumberFormatException e) {
		System.out.println("정수로 바꿀 수 없는 문자열 입니다.");
	}
	System.out.println("바뀐 숫자는 " + num2 + "입니다");
	}

	private static int getPositiveNumber() throws InputMismatchException {
		// throws : 아래에서 발생할 수도 있는 InputMismatchException 을 
		// 이 메서드를 호출한 곳에서 예외 처리 하도록 미룸
		// throws 는 미루고 싶다면 원하는 곳 까지 미룰 수 있다.
		Scanner sc = new Scanner(System.in);
			int tmp = sc.nextInt();
		return tmp;
	}

}
