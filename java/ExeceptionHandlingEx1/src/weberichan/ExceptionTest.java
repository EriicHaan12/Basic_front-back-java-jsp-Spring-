package weberichan;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수를 입력하세요 >>");
		int result = 0;
		try {
			int num = sc.nextInt();// 정수를 입력하지 않으면 InputMismatcException 예외 발생

			result = 3/num;
			// result를 파일로 저장하는 코드
			System.out.println("결과는 : " + result + "입니다");
		} catch (InputMismatchException e) {
//			e.printStackTrace(); 
			
			// catch에서 예외가 발생할 경우 e객체에 예외를 받아와서
			//출력시키는 용도, 그렇게 되면 쌓인 예외 정보들이 한꺼번에 출력된다.
			System.out.println("정수를 입력 해주세요");
		}catch (ArithmeticException e) {
//			e.printStackTrace(); 
			System.out.println("옳바른 계산이 아닙니다, 0을 입력하지 말아주세요...");
		}catch(Exception e) { //Exception은 예외처리의 최상위 객체,
			// 예외처리를 하지 못한 다른 예외가 발생되서 처리해야될 때
			// 주의사항으로는 Exception은 예외처리 할 때, 가장 마지막에 써줘야함
			// 그렇지 않으면 Exception 이후에 쓴 예외처리는 Unreachable 에러가 난다.
			System.out.println("알 수 없는 예외 발생");
		}finally {// try블럭에서 예외가 발생 하든 안하든 마지막에 수행될 블럭
			System.out.println("프로그램 끝");
		}
		
		
	}
}
