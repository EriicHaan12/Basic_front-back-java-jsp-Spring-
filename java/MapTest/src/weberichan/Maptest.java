package weberichan;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Maptest {

	public static void main(String[] args) {

		Map<Integer, String> map = new HashMap<>();
		
		//Map에 데이터 저장
		map.put(1, "꼬씀");		
		map.put(2, "죠릭");
		map.put(3, "라울");
		map.put(4, "마떼오");
		map.put(5, "닉");
		map.put(6, "알란");
	
		// 키가 중복되면 해당 키의 value 가 overwrite 됨
		// 이렇게 넣었을 경우 1번이 뒤에 쓴 중복코드로 덮어씌워지게 된다.
		map.put(1,"진석형");
		// 데이터 탐색
		if(map.containsKey(7)) { // 7 이라는 키가 있으면 7번 키를 가진 값을 출력
			System.out.println(map.get(7));	
		}
		
	
		//Map에 있는 모든 키를 검색 (findAll)
		Set<Integer> keys = map.keySet();
		// 중복된 값이 들어가지 않으므로 
		// collection 중 중복이 허용되지 않는 set으로 받아온다.
		for (Integer i : keys) {
			System.out.print(i+"\t");
		}
		// map에 있는 모든 value 검색
		// value들은 중복된 값이 들어올 수 있기 때문에 collection 중 중복이 허용되는 list type으로 받아온다.
		Collection<String> vList = map.values(); // 다운캐스팅 시켜줘야함
		for (String i : vList) {
			System.out.print(i+"\t");
		}
	}

}
