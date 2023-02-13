package weberichan;

public class SingleTon {

	private static SingleTon instance; 
	// 각 객체마다 들어가는 값이 달라야 되기 때문에 static(정적)으로 만들어야된다.
	
	private SingleTon(){ }// private한 생성자를 만들어야 하므로 기본 생성자를 생략 하면 안된다.
	
	//private 으로 만들었기 때문에 따로 getter 역할을 할 수 있는 메서드를 만들어준다.
	public static SingleTon getInstance() {
		if(instance == null) { // 객체가 없다면?
			instance = new SingleTon(); // 없다면 새로 만들어 주기
		}
		return instance; // 있다면 그대로 있는 객체를 반환.
	}
	
}
