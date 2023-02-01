import java.util.Scanner;
public class ArrayEx2 {

	public static void main(String[] args) {
	int[] coinUnit= {500,100,50,10};
	
	//키보드로부터 숫자나 문자를 입력 받울 수 있는 객체 생성(sc)
	Scanner sc = new Scanner(System.in); 
	System.out.print("거스름돈>>>");//2650
		int money = sc.nextInt();
		System.out.println(money);
		
		for (int i = 0; i < coinUnit.length; i++) {
			System.out.println(coinUnit[i]+"원짜리 동전 : " + (money/ coinUnit[i])+"개");
			money %= coinUnit[i];
		}
		
		
		
		
		
/*		
		
		//연습문제 
		//거스름돈을 몇 개의 동전으로 지불 할 수 있는지 구하는 프로그램을 작성하세요.
		int money = 5230;
		int num1 = 0;
		int fiveH = 500;
		int num2 = 0;
		int oneH = 100;
		int ten = 10;
		int num3 = 0;
	for (int i = 0; i < money/fiveH; i++) {
		num1++;
	}
for (int i = 0; i < (money-fiveH*num1)/oneH; i++) {
	num2++;
}
for (int i = 0; i < (money- fiveH*num1-oneH*num2)/ten; i++) {
	num3++;
}
System.out.println("거슬러 줄 돈 : " + money+"원"); 
System.out.println("500원 짜리 동전 : "+ num1 +"개");
System.out.println("100원 짜리 동전 : "+ num2 +"개");
System.out.println("10원 짜리 동전 : "+ num3 + "개");
	//
		
		
		*/
		
	}

}
