package weberichan;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueTest {

	public static void main(String[] args) {

		// 인터페이스라 직접 객체 생성이 안된다.
		
//			Queue<String> queue = new Queue<>();
		Queue<String> queue = new ArrayDeque<>();
		
		queue.add("kee");
		queue.add("eric");
		queue.add("jhin");
		// 들어간 순서대로 나온다.
		
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
	}
}