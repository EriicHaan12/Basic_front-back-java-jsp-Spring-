	package weberichan;
	
	class ScoreV2 {
	
		public static void main(String[] args) {
			Student s1 = new Student("9900001", "홍길동",90, 30, 100);
			Student s2 = new Student("9900002", "둘리",15, 95, 80);
			
			System.out.println(s1.toString());
			System.out.println(s2.toString());
			System.out.println(s1.getGrade());
			
			s1.setKor(120); // 조건에 맞지 않아 고쳐지지 않음
			s1.setKor(0);
			System.out.println(s1.toString());// set으로 바꾸면 한꺼번에 정보가 다 수정된다.
			
		}
	
	}
