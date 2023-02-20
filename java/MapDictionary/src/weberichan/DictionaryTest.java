package weberichan;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.List;

public class DictionaryTest {
	Map<String,List<String>> engWord = new HashMap<>();
	
	public static void main(String[] args) {
		DictionaryTest mt = new DictionaryTest();
	
	//단어 등록
	
	
	while(true) {
		System.out.println("1.단어 등록 2. 뜻 등록 3. 단어로 뜻 검색 4. 뜻-> 단어 검색  "
				 +"5. 삭제 + 6.전체 단어 출력 + 7.끝");
		
		Scanner sc = new Scanner(System.in);
		int menu = sc.nextInt();
		switch (menu) {
		case 1:
			mt.registerWord();
			break;
		case 2:
			mt.addMean();
			break;
		case 3:
			mt.findToMeanWithWord();
			break;
		case 4:
			mt.findToWordWithMean();
			break;
		case 6:
			mt.outputEngWord();
			break;
		case 7:
			System.exit(0);
			break;	
			
		default:
			break;
			}
		}
	
	}
	private void findToWordWithMean() {
	    System.out.print("뜻 입력>>");
	    Scanner sc = new Scanner(System.in);
	    String mean = sc.nextLine();
	    
	    List<String> word = new ArrayList<String>();
	    
	    for (String words : engWord.keySet() ) {
	        ArrayList<String> means = (ArrayList<String>) engWord.get(words);
	        if(means.contains(mean)) {
	            word.add(words);
	        }
	    }
	    if(word.isEmpty()) {
	        System.out.print("해당 뜻의 단어가 존재하지 않습니다.");
	    } else {
	        System.out.print(word);
	    }
	}
	
//	private void findToWordWithMean() {
//		//value 값들만 불러오기
////		System.out.print(engWord.values());
//		System.out.print("뜻 입력>>");
//		Scanner sc = new Scanner(System.in);
//		String mean = sc.nextLine();
//		List<String> word = null;
//	
////		for(List<String>means : engWord.values()) {
////			
////			if(this.engWord.containsValue(mean)) {		
////			}	
////		}
//		for (String words : engWord.keySet() ) {
//		
//			ArrayList<String> means = (ArrayList<String>) engWord.get(words);
//	
//		
//			if(means.contains(mean)) {
//				word = this.engWord.get(word);
//				System.out.print(word);
//			}else {
//				System.out.print("해당 뜻의 단어가 존재 하지 않습니다.");
//				}
//			}	
//		}
			
//			System.out.print(word);
	
	
	private void findToMeanWithWord() {
		System.out.print("단어 입력>>");
		Scanner sc = new Scanner(System.in);
		String word =sc.nextLine();
		
		for ( String words : engWord.keySet()) {
			List<String> mean =(List<String>)engWord.get(words);
		
			if(this.engWord.containsValue(mean)) {
				System.out.print(this.engWord.get(words)); 
				//왜 null이 뜨지..?
			}else {
				System.out.print("찾는 단어의 뜻은 단어장에 없습니다."); 
			}
		}
		
	}
	
	
	
	private void addMean() {
		System.out.print("단어를 입력>>");
		Scanner sc = new Scanner(System.in);
		String word =sc.nextLine();
		//key로써 value에 접근하는게 map의 방식
	if(	this.engWord.containsKey(word)) {
		List<String> means = this.engWord.get(word);
		
		System.out.print("해당 단어의 뜻 입력>>");
		String mean = sc.nextLine();
		means.add(mean);
		
		}else {
			System.out.println("해당하는 단어가 단어장에 없습니다!");
		}
	}
	
	private void registerWord() {
		System.out.print("단어를 입력>>");
		Scanner sc = new Scanner(System.in);
		String word =sc.nextLine();
	
	System.out.print("해당 단어의 뜻 입력>>");
	String mean = sc.nextLine();
	List<String> means = new ArrayList<>();
	means.add(mean);
	
	

    this.engWord.put(word, means);
	}
	
	
	private void outputEngWord() {
	Set<String>words =	this.engWord.keySet();
	
	for(String word : words) {
		System.out.println("\t 단어 : "+ word);
		List<String>means = this.engWord.get(word);
		for(String mean : means) {
		System.out.print("\t 뜻 : " + mean + "\t");
			}
		}
	}
	

	
	
}

	
//	 	 static List word1 = new ArrayList<Object>();
//		 static List word2 = new ArrayList<Object>();
//		 static List word3 = new ArrayList<Object>();
//		 static List word4 = new ArrayList<Object>();
//		 
//	public static void main(String[] args) {
//		//놀이
//	word1.add("play");
//	word1.add("game");
//		//담배
//	word2.add("cigarette");
//	word2.add("tobacoo");
//		//음료
//	word3.add("beverage");
//	word3.add("drink");
//	//라이터
//	word4.add("lighter");
//		
//System.out.println(word1);
//
// engWord.put("놀이", word1);
// engWord.put("담배", word2);
// engWord.put("음료", word3);
// engWord.put("라이터", word4);
//
//
// 
//		//	Map <String, List<String>> / Map<String,set<String>>
//			
//			// map.put("단어", 뜻1); 이런식으로 집어넣고
//			// 뜻1에는 List 클래스에서 여러개의 뜻을 집어 넣을 수 있도록 한다.
//			//뜻 1 객체에는 여러개의 String 타입의 의미를 넣어줄 수 있도록 만들어 주자
//		
//		
//		for(String key : engWord.keySet()) {
//			Object value = (Object)engWord.get(key);
//			System.out.println(key + " : "+ value);
//		}
//		
//	
	
	


