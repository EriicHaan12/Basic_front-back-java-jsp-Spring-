package weberichan;

public class Point {
	private int x;
	private int y;

	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	Point(int x, int y) {
		super(); // 부모의 기본 생성자 호출
		this.x = x;
		this.y = y;
	}
	
	//오버로딩
	Point(Point p){
		this.x=p.getX();  
		this.y=p.getY();
		// x,y는 private 한 값이기 때문에 직접 접근이 불가하므로 getter 로 넘겨받은 값을 넘겨 받는다.
		
	}
	//객체를 만들면서 멤버를 초기화 하는 것이 생성자의 가장 큰 역할이다...
	
	//어노테이션
		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}

}
