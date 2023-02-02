import java.util.Arrays;
import java.util.Scanner;
// 이 패키지의 풀 네임

public class ArraySearchEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ar = new int[20];
		for (int i = 0; i < ar.length; i++) {
			ar[i]=(int)(Math.random()*100 +1);
			
		}
		System.out.println(Arrays.toString(ar));
	Scanner sc =new Scanner(System.in);// System.in  = 시스템안의 input 장치 (마우스, 키보드)
	System.out.print("찾을 숫자>>");
	int findNum = sc.nextInt();
	
	int i = 0;
	boolean isFind = false;
	for(int num : ar) {
	
	if(num==findNum){// 찾았다
		isFind = true;
		System.out.println("찾는 값" + findNum +"은"+i+" 번째에 있습니다.");
		}
		i++;
	}
	if(!isFind) {
		System.out.println(findNum + "을 찾을 수 없습니다.");
			}
		}
	}
	


