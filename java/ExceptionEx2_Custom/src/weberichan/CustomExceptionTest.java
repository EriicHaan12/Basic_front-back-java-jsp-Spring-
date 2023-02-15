package weberichan;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomExceptionTest {

		public static void main(String[] args) {
			
		
			Integer num = null; // Integer 타입은 객체이기 때문에 정수, null 값 모두 가질 수 있다.
		while(true) {
			
			try {
				Scanner sc = new Scanner(System.in);
				// 스캐너를 지역변수로 둬야 양수를 입력하지 않아도, 
				// 스캐너가 새로 생성되어 무한히 반복하지 않는다.
				System.out.print("양수를 입력하세요 >>");	
				num =	sc.nextInt();
				if(num >0) {
					break;
				}else {
				throw new NotPositiveInteger("양수가 아닙니다."); // 예외 객체를 만들어 던짐
				//사용자 정의 예외, 고의 예외
				}
			} catch (InputMismatchException e) {
				System.out.print("숫자를 입력하세요");
			}catch (NotPositiveInteger e) {
				System.out.print("입력한 숫자 "+ num + "은 음수 입니다. ");	
				System.out.println(e.getMessage());
			}
			System.out.println(num);
		}
				
			
		
//			try {
//				num =	sc.nextInt();
//				if(num>=0) {
//					System.out.println(num + "은 양수 입니다, 정상 종료 되었습니다.");
//					System.exit(0);
//				} else if(num<0){
//				Exception e = new Exception("양수가 아님");
//				throw e;
//				}
//			} catch (Exception e) {
//	    System.out.println(num + "은 양수가 아닙니다, 다시 입력해주세요");
//			}
	}

}
