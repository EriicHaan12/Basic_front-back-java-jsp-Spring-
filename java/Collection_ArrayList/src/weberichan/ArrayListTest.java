	package weberichan;
	
	import java.util.ArrayList;
	import java.util.Collection;
	import java.util.Iterator;
	import java.util.List;
	import java.util.Stack;
	
	public class ArrayListTest {
	
		public static void main(String[] args) {
			List<Integer> list= new ArrayList<>();
			
			// 데이터 추가
			list.add(10);
			
			list.add(30);
			
			list.add(1,20); // 10과 30사이에 20을 추가하려면 ,
			// ArrayList에서도 가능하지만 LinkedList가 이기능을 쓰기 더 용이하다.
			
			int a = list.get(2);
			Collection<Integer> c = new Stack(); 
			// Stack 컬랙션으로 지정해줘도 Integer 타입만 맞춰주면 데이터를 넣어줄 수 있다.
			c.add(100);
			c.add(200);
			
			
			System.out.println("-=====================스택 출력 =====================");
			
			list.addAll(c);
			
			Stack tmp = (Stack)c;
		
		System.out.println(	(Integer)tmp.pop());
		System.out.println(	(Integer)tmp.pop());		
			
			
			
			System.out.println("================================");
		System.out.println(list.indexOf(30)+ "번째 있음");	
			
			//리스트에 있는 모든 자료 출력
			for(Integer i : list ) {
				System.out.print(i+"\t");
			}
			 System.out.println();
			// 리스트를 반복
			
			// Iterator(반복자) 객체를 이용해 반복하기
			 // Iterator를 사용하는 이유: 컬렉션의 종류에 상관없이 데이터를 탐색하는 방법을
			 // 유지하고 싶을 때 사용
			 
	 		Iterator<Integer> iter = list.iterator();
	 	
	 		for(Iterator iterator = list.iterator(); iterator.hasNext();) {
	 			System.out.println(iterator.next());
	 		}
	 		// 많은 데이터가 들어간 객체를 출력할 때
	 		// 일반 for문을 쓰는것보다 Iterator 반복문을 사용해 출력 하는 것이 속도가 극명하게 빠르다.
	 		// for문은 다음 출력할 데이터를 찾아 출력하는데 시간이 걸리는 반면,
	 		// iterator은 시간이 거의 걸리지 않는다.(hasing된 요소의 주소를 바로 찾아간다)
	 		
	 		
		}
	
	}
	
	//Arraylist : 맨 첫번째 부터 나옴
	
	//stack : 맨뒤에꺼 부터 나옴
