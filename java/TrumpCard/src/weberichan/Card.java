package weberichan;

/**
 * @Class Name : Card,
 * @Author : goott6,
 * @Date : 2023. 2. 8.,
 * @Pakages : weberichan,
 * @Description : 카드 클래스 생성 및 타입, 번호 생성 
 */
public class Card {
	// 멤버 변수
	
	//각 카드의 무늬와 숫자는 달라진다. 그래서 instance 맴버 변수로 만든다.
	
	
	
	private int cardType; // 타입을 정수로 준 이유: random으로 뽑아야 하기 때문...
//	private String[] cardType = {"◆","♥","♠","♣"};
	private int number;
//	private String[] cardNum = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	

	// 모든 카드는 width와 heigtht 값이 동일하다. 모든 카드 객체가 공유하는 static 변수로 만든다.
	//지금 당장 쓰진 않지만, 나중에 화면에 동일한 크기의 카드를 띄우기 위한 변수 설정.
	private static int width = 100;
	private static int height= 200;
	
	private static final int HEART =1;
	private static final int SPADE =2;
	private static final int CLOVER =3;
	private static final int DIAMOND =4;
	
	//card이미지는 나중에...
	static final int CARDTPYE_MAX=4; // 카드 종류의 수를 상수로 고정시키기 (default로 줘도 상관 없다)
	 static final int NUMBER_MAX=13;// 카드들의 수를 상수로 고정시키기
	
	
	//생성자
	public Card(int cardType, int number){
		this.cardType= cardType;
		this.number=number;
		
	}
	public String toString() {
		String cardType="";
		String number="";
		switch (this.number) {
		case 1:
			number="A";
			break;
		case 11:
			number="J";
			break;
		case 12:
			number="Q";
			break;
		case 13:
			number="K";
			break;
			
		default:
			number = this.number+ "";//int를 String으로 바꾸는 가장 쉬운 방법...
		}
		
		
		switch (this.cardType) {
		case HEART: 
			cardType="♥";
			
			break;
		case SPADE: 
			cardType="♠";
			
			break;
		case CLOVER: 
			cardType="♣";
			
			break;
		case DIAMOND: 
			cardType="◆"; 
			break;
		default:
			break;
	
		}
		return cardType + number; 
	}
	
	
	
	
	//메서드
	
//	//getter
//	
//	public int getCardType() {
//		return cardType;
//	}
//	
//	public String[] getCardNum() {
//		return cardNum;
//	}
//
//	
//	//setter
//	
//	public String[] setCardType(String[] cardType) {
//		return this.cardType= cardType;
//	}
//	
//	public String[] setCardNum(String[] CardNum) {
//		return this.cardNum=cardNum;
//	}
//	
	
}
