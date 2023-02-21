package weberichan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RaceHorse {

	static int strRank = 1;

	public static void main(String[] args) {
		List<Horse> list = new ArrayList<>();

		list.add(new Horse("0번마"));
		list.add(new Horse("1번마"));
		list.add(new Horse("2번마"));
		list.add(new Horse("3번마"));
		list.add(new Horse("4번마"));
		list.add(new Horse("5번마"));
		list.add(new Horse("6번마"));
		list.add(new Horse("7번마"));
		list.add(new Horse("8번마"));
		list.add(new Horse("9번마"));

		for (Horse horse : list) {
			horse.start();
		}

		for (Horse hs : list) {
			try {
				hs.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Collections.sort(list);
		System.out.println("경기끝 ....");
		System.out.println("======================================================");
		System.out.println();
		System.out.println(" 경기 결과 ");

		for (Horse horse : list) {
			System.out.println(horse.getName1() + " " + horse.getRank() + "등");
		}
	}
}