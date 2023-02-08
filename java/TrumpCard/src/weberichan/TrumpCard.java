package weberichan;

import java.util.Scanner;


/**
 * @Class Name : TrumpCard,
 * @Author : goott6,
 * @Date : 2023. 2. 8.,
 * @Pakages : weberichan,
 * @Description :포커 카드 각각에 대한 객체
 */
public class TrumpCard {

	public static void main(String[] args) {

		Dealer 주윤발 = new Dealer();
		System.out.println(주윤발.displayCard());
		
		주윤발.shuffleCard();
		System.out.println(주윤발.displayCard());
		
		
//		for (int i = 0; i < Dealer.CARD_NUM; i++) {
//			Card c = 주윤발.pickCard();
//			System.out.println((i+1)+"번째 뽑힌 카드 :" + c.toString());
//	Card c = 주윤발.pickCard();
//		
//		System.out.println(c.toString());
		
		for (int i = 0; i < Dealer.CARD_NUM; i++) {
			주윤발.pickCardAndRemoveArray();
		}

	
		System.out.println(주윤발.displayCard());
		
		
		}
		
		//cardDeck.length-1 짜리 배열을 만들고 뽑힌 카드는 복사
		//0~ index-1 까지 반복해서 복사한 뒤 넣어주고
		// index+1~cardDeck.length 까지 복사해서 넣어주면 된다.
		//넣어준 배열을 반환 
		//새로 만들 배열의 length을 기존 배열length에서 빼준다.
		
		
		
		
	}

	

	

		
//		private static void outputGameMenu(){
//			System.out.println("=========================================");
//			System.out.println("==              포커 게임              ==");
//			System.out.println("=========================================");
//			System.out.println("==1. 딜러 생성(번호 생성)              ==");
//			System.out.println("==2. 딜러 이름 생성                    ==");
//			System.out.println("==3. 전체카드 보여주기                 ==");
//			System.out.println("==4. 카드 섞기                         ==");
//			System.out.println("==5. 한장 뽑기			               ==");
//			System.out.println("==6. 종료                              ==");
//			System.out.println("=========================================");
//			System.out.println("메뉴 번호 입력 >>>");
//		}
//
//		private Dealer createDealer() {
//			Scanner noDeal = new Scanner(System.in);
//			Scanner nameDeal = new Scanner(System.in);
//			
//			System.out.println("딜러 번호 입력>>>");
//			int DealerNo = noDeal.nextInt();
//			
//			System.out.println("딜러 이름 입력>>>");
//			String DealerName = nameDeal.nextLine();
//			
//			Dealer d1 = new Dealer(DealerNo, DealerName);
//			System.out.println(d1.toString());
//			return d1;
//		}
//		
//		public static void main(String[] args) {
//			
//			TrumpCard TC = new TrumpCard();
//			
//			while (true) {
//
//				outputGameMenu();
//				Scanner menuTc = new Scanner(System.in);
//				Dealer dealer =null;
//				int menu = menuTc.nextInt();
//
//				switch (menu) {
//
//				case 1:
//					dealer = TC.createDealer();
//					break;
//				case 2:
//			
//					break;
//				case 3:
//					
//					break;
//				case 4:
//				
//					break;
//				case 5:
//				
//					break;
//				case 6:
//					System.exit(menu);//  프로그램 종료
//					break;
//
//				default:
//					break;
//				}
//
//			}
//		
		
		
	// 메인 함수 끝
	


