package weberichan;



public class ShapeTest {
	public static void main(String[] args) {
		
		//Shape 클래스가 추상 클래스이기 때문에 객체를 만드려면 추상메서드를 오버라이딩 해야 만들수 있다.
		//추상 메서드를 overriding 한다는 의미는 객체가 분명해졌다는 뜻
		//   = 명확한(어떤 기능을 가진) 특정한 객체가 되어버린다, 그 객체는 더이상 추상적이지 않게 된다
//		// 위와 같은 논리적 모순이 생기기에 아래와 같이 쓰지 않는다.
		
		
		
//		Shape s = new Shape("도형", "black", new Point(50, 50)) {
//			
//			@Override
//			public void draw() {
//				
//				}	
//		};

		Triangle t1 = new Triangle("삼각형1", "red",new Point(100,100), 5,10);
//		System.out.println(t1.toString());
		
//		t1.draw();
		
		Rectangle r1 = new Rectangle("사각형", "blue", new Point(200,200),100,50);
//	System.out.println(r1.toString());
		//r1.draw();		
				
		Circle c1 = new Circle("원", "black", new Point(300,300), 100);
//		System.out.println(c1.toString());
//		c1.draw();
		
		//다형성
		// c1은 Circle의 객체(하위 객체)이고 sCircle은 Shape의 객체(상위 객체)이다.
		// 아래의 연산이 수행되면, 업캐스팅(upCasting)이 발생하면서 수행된다.
		// 묵시적 형변환
		Shape sCircle = c1;
		// Shape sCircle = (Shape)c1;
		
		Painter 피카소 = new Painter("피카소");
		피카소.drawShape(c1);
		피카소.drawShape(r1);
		피카소.drawShape(t1);
	}
}
