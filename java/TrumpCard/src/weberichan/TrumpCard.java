package weberichan;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


/**
 * @Class Name : TrumpCard,
 * @Author : goott6,
 * @Date : 2023. 2. 8.,
 * @Pakages : weberichan,
 * @Description :포커 카드 각각에 대한 객체
 */
//JFrame 상속 받기
public class TrumpCard extends JFrame {
	
	//상속받은 태그로 생성자 생성
	public TrumpCard(String title) {
		super(title);
		
		this.setVisible(true);
//		this.setSize(WIDTH, HEIGHT);
		this.setSize(new Dimension(640, 480));
	
	}
	
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
		
		TrumpCard win = new TrumpCard("포커");
		
		}
		//cardDeck.length-1 짜리 배열을 만들고 뽑힌 카드는 복사
		//0~ index-1 까지 반복해서 복사한 뒤 넣어주고
		// index+1~cardDeck.length 까지 복사해서 넣어주면 된다.
		//넣어준 배열을 반환 
		//새로 만들 배열의 length을 기존 배열length에서 빼준다.

	
	//오버 로딩이 아니라 오버 라이딩..!
	@Override 
	//anotation : 컴파일러에게 아래의 메서드가 오버라이딩
	// 되었음을 알려 주는 것. 
	public void paint(Graphics g) { // 윈도우가 그려질 때 자동으로 호출되는 일종의 콜백 메서드)
		
		super.paint(g);
		
		String imagePath="D:\\lecture\\java\\JS.png";
	
	g.drawOval(100,100, 50, 50);
	
	URL imgUrl = null;
	try {
		imgUrl = new URL("https://i1.sndcdn.com/artworks-2PEHkMSRjhiK0z4y-wWLicA-t500x500.jpg");
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("잘못된 URL 주소입니다");
		System.exit(0);
	}
	BufferedImage img = null;// 출력할 이미지 url 초기화
	
	try {
	img=ImageIO.read(imgUrl);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("이미지를 읽어오지 못했습니다.");
		System.exit(0);
	}
			
			
//	g.drawImage(img, 40, 0, null);// 사진 출력
	
	File fPath = new File(imagePath);
	try {
		g.drawImage(ImageIO.read(fPath), 100, 100, null); //로컬에 저장된 링크로 불러오기
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	}
		
	}
