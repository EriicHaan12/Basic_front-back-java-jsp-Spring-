package weberichan;

public class ThreadTest {

	public static void main(String[] args) {
		
		//스레드 객체 생성법
		//1)Thread 클래스를 상속 받아 수현한 클래스
		ThreadEx t1 = new ThreadEx();
		//2) Runnable 인터페이스를 구현한 클래스
		//Runnable 객체를 만들고 Thread 객체에다 Runnable 객체를 넣어준다.
		Runnable r = new ThreadRunnable();
		Thread t2 = new Thread(r);
		
		t1.setPriority(Thread.MAX_PRIORITY); // 메인은 속도를 좀더 낮추고
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		
		// 스레드를 실행 시키는 방법
		t1.start(); // 스레드가 시작되며 스레드가 실행 가능한 상태로 놓이게 된다.(Runnable 상태가 된다)
		t2.start();
		
		for(int i = 0 ; i<10; i++) {
			System.out.print(Thread.currentThread().getName()+ ", i : "+ i +"\n");
		}
	}
	
}

class ThreadEx extends Thread{ // 부모가 Thread 이므로 ThreadEx도 쓰레드 객체가 된다.
// 스레드가 생성되어 실행될 때 자동으로 호출되는 일종의 콜백 함수
	@Override
	public void run() {
		for(int i = 0 ; i<10; i++) {
			System.out.print(getName()+ ", i : "+ i +"\n");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
	
}

class ThreadRunnable implements Runnable { // 부모가 Ruunable 이므로 ThreadRunnable도 Runnable 객체

	@Override
	public void run() {
		for(int i =0 ; i<10; i++) {
			System.out.println(Thread.currentThread().getName()+ ", i : "+ i);
		
		
		}
	}
	
}