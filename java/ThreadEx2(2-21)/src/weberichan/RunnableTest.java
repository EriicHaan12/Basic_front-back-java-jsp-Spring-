package weberichan;

import java.util.Iterator;

import javax.swing.JOptionPane;


public class RunnableTest implements Runnable{

	@Override
	public void run() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}



public class ThreadTest {
//유저에게 값을 입력 받는 동안에도 스레드가 돌 수 있도록
	
	public static void main(String[] args) {
		
		
		
		String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
		System.out.println("입력한 값은 : " + input);
	
	}
	Thread t1 = new Thread (new RunnableTest2());

}


