
public class ScoreV1 {
public static void main(String[]args) {
	String name = "둘리";
	int kor = 98, eng = 34, math = 100;
	int tot = kor+eng+math;
	float avg = tot/3f;
	
	char grade =' ';
	switch ((int)Math.floor(avg/10d)) { 
	// 연산될 때 데이터타입은 더 큰 쪽을 따라 가기 때문에 일일히 
	// 데이터 타입을 바꿔줘야만 한다.
	//Java는  Javascript와는 다르게 타입을 엄격하게 지켜야한다.
	//즉, 연산할 시 기대 결과값에 맞게 끔 프로그래머가 기대 데이터 타입을 지정해줘야만
	// 원하는 데이터 타입의 결과 값을 얻을 수 있다.

	case 10:
	case 9:	
		grade ='A';
		break;
	case 8 :
		grade = 'B';
	break;
	case 7 :
		grade = 'C';
	break;
	case 6 :
		grade = 'D';
	break;
	
	default:
		grade = 'F';
		break;
	}
	System.out.println("성적표");
	System.out.println("이름\t 국어\t 영어\t 수학\t 총점\t 평균\t 학점\t");
	System.out.println("==================================================");
	System.out.println(name + "\t"+ kor + "\t" +eng+ "\t" + math + "\t" +tot + "\t" + avg + "\t" +  grade);
}// main 메서드의 끝



}
