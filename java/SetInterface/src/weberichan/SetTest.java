package weberichan;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {

	public static void main(String[] args) {
		Set<Integer> set = new HashSet<>();

		set.add(100);
		set.add(001); // 8진수 때문 8이 들어간다.
		set.add(010);
		set.add(100);
		set.add(2);
		set.add(2);
		set.add(3);
		set.add(6);
		set.add(5);
		// 중복을 허용하지 않아서 동일한 값은 두번 찍히지 않는다.
		for (Integer i : set) {
			System.out.println(i);
		}
		System.out.println("=====================");
		Set<Integer> set2 = new TreeSet<>();
		set2.add(5);
		set2.add(5);
		set2.add(1);
		set2.add(2);
		set2.add(4);
		for (Integer i : set2) {
			System.out.println(i);
		}
	}

}
