package weberichan;

public abstract class Shape {

	private String name;
	private String color;
	
	//new를 하면 안됨! 하게되면 현객체가 없어질때, 종료될 때 같이 없어짐.
	private Point p;

	
	
	
	//오버 로딩한 생성자 
	//매개변수에 Point 까지 넣은 이유가 다른 클래서에서 Point 값을 넘겨받아서 쓰기 위해
	Shape(String name, String color, Point p) {
		super();
		this.name = name;
		this.color = color;
		this.p = p;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Point getP() {
		return p;
	}

	public void setP(Point p) {
		this.p = p;
	}
	
//	 public void draw();
	//	{  // 상위 클래스인 Shape에서는 draw 메서드를 구현 하지 못한다. 
	// 어떤 도형을 어떻게 그려야 할지 모르기 때문..!
	// 그래서 추상 메서드로 만들어 줘야한다...!
	//추상 메서드는 쉽게 풀어 설명하면... 
	// 기능이 있긴 있어야 하지만, 뭘 위해 어떻게 써야 할지는 아직 모를 때 미리 만들어주는 메서드
	
		//중괄호가 바디(메서드 몸체)
//		}

	//추상 메서드
	//추상 클래스가 되는 조건
	//1) class 키워드 앞에 abstract 키워드를 붙인다.(추상 메서드가 없어도 추상 클래스로 만들어짐)
	//2) 클래스의 맴버 메서드 중 하나라도 추상 메서드가 있다면 자동으로 추상 클래스가 되어야한다.

	
		public abstract void draw();
	
	// 추상 클래스 특징
	// 추상 클래스는 객체로 만들수 없다.
	// 추상 메서드는 바디가 없다...
	//하지만 언젠가는 구현 되어야함.
	
	//추상 메서드를 다른 클래스에서 구현하기 위해서는 추상 클래스에 대해 OVERRIDING 을 해줘야 한다.
	// 설계자가 메서드를 규칙적으로 만들기 위한 일종의 룰을 지정해준다고 이해함...
	
	// 추상 메서드가 하나라도 존재하는 특정 클래스는 추상 클래스로 지정해줘야만 한다.
	// 추상 클래스 지정 방법 : 클래스 명 앞 에 abstract 을 붙여준다.
	
	//즉 추상 메서드를 쓸려면 추상 클래스로 만들어줘야 한다.
	
	
	// 아래의 메서드는 오버로딩이다. 부모가 물려준 toString()을 오버로딩 한 것.
		// 그래서 에러가 없다.
	//부모인 Object에게 상속 받은 toString()
	public String toString(int a) {
		return "";
	}
	// 오버 로딩
	public String toString(String a) {
		return a;
	}

	// 오버 라이딩
	@Override// 아래의 메서드가 오버라이딩 됨을 컴파일러에게 명시
	public String toString() {
		return "Shape [name ="+ name+", color = "+color+", p= "+p+"]" ;
	}
	
	
}
