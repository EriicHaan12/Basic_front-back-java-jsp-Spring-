package weberichan;

public class ThreadTest {

	public static void main(String[] args) {
//		Family 아빠 =new Family("아빠");
//		Family 엄마=new Family("엄마");
//		Family 나 =new Family("나");
		Family fam = new Family("");
		
		
	Thread fTh = new Thread(fam);
	Thread mTh = new Thread(fam);
	Thread nTh = new Thread(fam);
	
	
	mTh.start();
	nTh.start();
	fTh.start();
	}

}
