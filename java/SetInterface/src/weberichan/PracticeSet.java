package weberichan;

import java.util.HashSet;
import java.util.Set;

public class PracticeSet {
	public static void main(String[] args) {

		
		//set은 자료구조에서 데이터들을 비교할 때 hashCode와 데이터 값 둘다 본다.
		
		Korean k1 = new Korean("990101-1234567", "홍찰찰");
		Korean k2 = new Korean("990101-1234567", "홍찰찰");
		Korean k3 = new Korean("990101-1000001", "홍찰찰");
		System.out.println("k1의 해쉬코드 : " + k1.hashCode());
		System.out.println("===========");
		System.out.println("k2의 해쉬코드 : " + k2.hashCode());
		System.out.println("===========");
		System.out.println("k3의 해쉬코드 : " + k3.hashCode());

		Set<Korean> set = new HashSet<>();
		set.add(k1);
		set.add(k2);
		set.add(k3);
		set.add(new Korean("990101-1234568", "홍찰찰")); // 같은 주소라 같은 데이터라 생각되서 중복된 것은 데이터로 남겨지지 않는다.

		// 같은 Korean set 에 저장된다.(논리적인 오류가 생긴다)
		// k1 과 k2의 주소가 다르므로, 다른 객체라 생각해서 저장한 것.
		for (Korean ko : set) {
			System.out.println(ko.toString() + ko.hashCode());
			// 주소 값이 다르게 들어가진다.
		}
		// 이 문제의 본질 : 주민번호와 이름이 같으면 같은 객체로 인식시켜야 함.
		// -> 주민번호와 이름이 같은 객체면 중복 저장이 안도도록

		// equals()를 오버라이딩
		System.out.println(k1.equals(k2)); // 문자열이 아닐때는 안에 있는 데이터값으로 비교하는 게 아닌
		// 주소 값으로 비교하게 된다.
		System.out.println(k2.equals(k3));
		
		// hashCode()를 오버라이딩 하는 법
	
	}	
	
}
