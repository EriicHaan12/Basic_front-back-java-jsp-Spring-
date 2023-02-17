package weberichan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortTest  {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		
		list.add("마이콜");
		list.add("둘리");
		list.add("또치");
		list.add("다람쥐");
		System.out.println("==============정렬 전========================");
		for (String string : list) {
			System.out.println(string);
		}
		
		//정렬을 하기 위해 정렬 기준의 객체인 Comparator  구현

//	//비교 할 때 기준점을 새우기 위한 Comparator 클래스 이용
		       // Comparator 객체 생성
	        Comparator<String> comp = new Comparator<String>() {
	            @Override
	            public int compare(String o1, String o2) {
	                return o1.compareTo(o2);
	            }
	        };
	        
	        // 정렬
	        Collections.sort(list, comp);
	        
	        // 출력
	        System.out.println("==============정렬 후========================");
	        for (String string : list) {
	            System.out.println(string);
	        }
	    }
}