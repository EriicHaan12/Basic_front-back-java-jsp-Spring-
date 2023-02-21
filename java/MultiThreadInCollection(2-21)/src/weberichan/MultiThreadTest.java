package weberichan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiThreadTest {

	public static void main(String[] args) {
		Runnable r= new SyncCollection();
		Thread th1 = new Thread(r);
		Thread th2 = new Thread(r);
		th1.start();
		th2.start();
	}
}

class SyncCollection implements Runnable {

	List<Integer> list = new ArrayList<>();

	@Override
	public void run() {
		list.add(10);
		int menu = (int) ((Math.random() * 3) + 1);
		
		switch (menu) {
		case 1:
			list.add(menu);
			break;
		case 2:
			try {
				list.remove(0);
			} catch (IndexOutOfBoundsException e) {
				
				e.printStackTrace();
			}
			break;

		}
	
		
			for(Integer i : this.list) {
				System.out.print(i + "\t");
			}
			System.out.println("\n");
		
	}

}
