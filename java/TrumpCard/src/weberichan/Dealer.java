package weberichan;

import java.util.Arrays;

/**
 * @Class Name : Dealer,
 * @Author : goott6,
 * @Date : 2023. 2. 8.,
 * @Pakages : weberichan,
 * @Description : 딜러
 */
public class Dealer {
	
public final static int CARD_NUM= 52;

private Card[] cardDeck= new Card[CARD_NUM]; //상수는 값을 수정 할 수 없다.
// 변수 및 카드를 넣을 빈 배열 생성

private static int curCardCount=0;


//생성자

//순서대로 넣기
public Dealer() {
	curCardCount=CARD_NUM;
	int cardIdx=0;
	for (int cardType = 1; cardType <= Card.CARDTPYE_MAX; cardType++) {
		for (int cardNum = 1; cardNum <= Card.NUMBER_MAX; cardNum++) {
			this.cardDeck[cardIdx++]=
						new Card(cardType, cardNum); 
			// 이전에 Card클래스에 만들어뒀던  생성자를 활용
		}
	}
	
}



/**
 * @Method Name :shuffleCard,
 * @작성일 : 2023. 2. 8.,
 * @작성자 : goott6,
 * @매개변수 : Dealer,
 * @반환값 : void,
 * @Description : 카드를 섞는다.  
*/
public void shuffleCard() {
	for (int i = 0; i < 1000; i++) {
int index=	(int)(Math.random()*Dealer.curCardCount); 
// int 타입으로 바꾼다는것이 소수점 부분을 잘라내서 정수로 만들어주기 때문에 Math.floor를 써줄 필요 없다.
	Card temp = this.cardDeck[0];
	this.cardDeck[0]=this.cardDeck[index];
	this.cardDeck[index]= temp;
	//새로운 배열(잠시 랜덤한 수를 나열시키기 위한)
	}
}


/**
 * @Method Name :pickCard,
 * @작성일 : 2023. 2. 8.,
 * @작성자 : goott6,
 * @매개변수 : Dealer,
 * @반환값 : Card,
 * @Description : 카드를 한장 뽑아 반환(뽑은 카드는 null로 처리)  
*/
public Card pickCard() {
	
	int index =0; 
	Card returnCard =null;
	do {
		index = (int) (Math.random() * Dealer.CARD_NUM);
		returnCard = this.cardDeck[index];
	} while (returnCard==null);
	
	
	this.cardDeck[index]=null; 
	//가변 배열이라 null값으로 주는 것보다 배열의 길이를 줄이는 것이 낫다.
	
	curCardCount--;
	return returnCard;
}

public Card pickCardAndRemoveArray() {
	int index =0; //뽑은 카드의 index 변수 
	
	Card returnCard =null; // 뽑힌 카드
	
		index = (int)(Math.random() * curCardCount);//
		
		returnCard= this.cardDeck[index];// 뽑은 카드 복사, 다른 곳으로 빼주기 위해서

		
		Card[] newCardDeck= new Card[curCardCount-1];//새로운 빈 배열 생성
		
		for (int from = 0; from < index; from++) {
			newCardDeck[from]=this.cardDeck[from];
		}
		for(int from = index+1; from<curCardCount;from++) {
			newCardDeck[from-1]=this.cardDeck[from];
		}
		System.out.println("뽑은 카드 : " + returnCard.toString()+
				", 전체 카드 갯수 : " +newCardDeck.length +
				", [Cards] "+ Arrays.toString(newCardDeck));
		
		curCardCount--;
		this.cardDeck= newCardDeck;
		
	return returnCard;
}




//getter 이자 출력하는 메소드

/**
 * @Method Name :displayCard,
 * @작성일 : 2023. 2. 8.,
 * @작성자 : goott6,
 * @매개변수 : Dealer,
 * @반환값 : String,
 * @Description :  전체 카드 출력
*/
public String displayCard() {
	return "전체 카드 갯수 : " +this.cardDeck.length +
			", [Cards] "+ Arrays.toString(this.cardDeck);// 배열에 있는 모든 요소 string으로 출력 
}



//private int[][] card= new int[4][13];
private int DealerNum;
private String DealerName;



//반이라는게 학생을 여러개 가질 수 있다...
//private Student[] stuList;// 학생 목록(포함관계)

//딜러 라는 클래스를 만들고
//딜러는 카드를 가지고 있다 로 풀이되는 포함관계 생성

private Card[] cardList;// 카드 목록(포함관계)


//public void outputTotalCard


	


}
