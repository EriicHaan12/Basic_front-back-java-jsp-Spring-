package weberichan;

import java.util.Iterator;
import java.util.Stack;

public class stackTest {

	//Java에서의 Collection은 모두 가변성을 가지고 있다.
	//즉, 객체를 생성할 때 객체의 size(length)를 지정해줄 필요가 없다, 넣는대로 늘어난다
	public static void main(String[] args) {
		Stack<String> stack = new Stack<>();
		stack.push("상진상");
		stack.push("에릭한");
		stack.push("키양");
//		while(!stack.isEmpty()) {
//			System.out.println(stack.pop());
//
//		}
		System.out.println(stack.size());
		System.out.println(stack.peek());
		System.out.println(stack.peek());
		System.out.println(stack.peek());
		System.out.println("==========================");
	
		// Iterator로 반복시키면 Stack을 쓰더라도 넣은 데이터 순서대로 뽑아 낼 수 있다.
for (Iterator iterator = stack.iterator(); iterator.hasNext();) {
	String string = (String) iterator.next();
	System.out.println(string);
}	
	}

}
