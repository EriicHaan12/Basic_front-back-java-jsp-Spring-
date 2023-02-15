package weberichan;

public class ThrowTest {

	public static void main(String[] args) {
		Student s = null;
		//에러가 나서 이후 s 객체를 생성하더라도 만들어지면 안됨.
		// 예외 처리를 해줘야함...
		//예외처리는 메인 함수에서도 할 수 있다.
		
		//생성자에서 발생된 에러에 대한 예외처리를 여기서 해준다.
		try {
			s = new Student("98001", "둘리", -12, 45, 100);
		} catch (IllegalArgumentException e) {
			System.out.println("국어점수를 잘못 입력하였습니다.");
		}
		
		// 잘못된 점수가 입력되었을 때는 객체가 만들어지면 안된다는 것을 테스트 
		System.out.println(s.hashCode());
		// 예외처리 후 catch에 걸려서 hashCode가 안찍힌다 , 즉 객체가 생성이 안된다.
	}

}
