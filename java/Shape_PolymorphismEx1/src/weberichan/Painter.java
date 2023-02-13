package weberichan;

//포함관계, 상속 관계가 성립이 안됨
public class Painter {

	
	private String name;

	
	Painter(String name){
		super();
		this.name= name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	//화가가 각각의 원, 삼각형, 사각형을 그린다고 표현할 때, 아래의 코드로 구현하면 
	// 코드의 재사용성이 떨어진다
	
	
//	public void drawTriangle(Triangle t) {
//		System.out.println("이름이 " + t.getName() +"이고, 색상은 "+ t.getColor()+
//				" 이고, 원점 : "+ t.getP().getX()+","+t.getP().getY()+
//				", 밑변 "+ t.getBase()+", 높이 "+ t.getHeight() +"인 삼각형을 그립니다.");
//	}
//	
//	public void drawRectangle(Rectangle r) {
//		System.out.println("이름이 " + r.getName() +"이고, 색상은 "+ r.getColor()+
//				" 이고, 원점 : "+ r.getP().getX()+","+r.getP().getY()+
//				", 가로 "+ r.getWidth()+", 높이 "+ r.getHeight() +"인 직사각형을 그립니다.");
//	}
//	
	
	//논리적으로 도형을 그린다 
	// => 삼각형을 그린다,사각형을 그린다,원을 그린다를
	
	//포함 할 수 있는 포함관계를 성립 시킬 수 있게 된다.
	
	
	//Shape s = c1;
	//Shape s = r1;
	// Shape s = t1;
	
	//다형성 2번째 법칙(? : 이득) : 
	//부모(Shape)이름으로 모든 자식 객체를 매개 변수로 다 받을 수 있다.
	
	public void drawShape(Shape s) {
//		if(s instanceof Circle) {   //Shape에서 업캐스팅 된 Circle 객체가
//			//원을 그린다고 했을 때 ,
//			// Shape Circle 객체가 가 Circle으로 다운 캐스팅(down Casting)이 된다.
//			// 업 캐스팅 된 객체가 자기 자신의 타입으로 되돌아가는 변환/
//			Circle c = (Circle) s;
//			System.out.println(((Circle)s).toString()+"인 원을 그립니다.");
//		
//		if(s instanceof Triangle) {
//			System.out.println(	((Triangle)s).toString()+"인 삼각형을 그립니다." );
//		}
//		
//		if(r instanceof Rectangle) {
//			System.out.println(  ((Rectangle)s).toString()+"인 사각형을 그립니다" );
//		}
		
		
		
		// 다형성 1번째 법칙(? : 이득) : 부모 이름으로 자식이 구현한 멤버를 사용한다.
//		System.out.println(s.toString()+"인 원을 그립니다.");
		
		
		//Shape (부모)에게 있는 멤버 , 즉 자식 멤버 메소드와 부모멤머 메소드가 공통적으로 있는 것만
		//범용성으로 부모 멤버 메소드에서 불러 올 수 있다.
		
		//아래 코드는 자식(Circle)에겐 radius가존재하지만 부모에겐 Radius가 없어서 불러 올 수 없다.
//		System.out.println(s.getRadius());
		
		
		
		s.draw(); // s는 원래 Circle 객체 타입이었다. 
		// 부모(Shape)에게는 draw가 추상 메서드였지만, 자식객체(Circle) 에서는 구현된 메서드 이므로
	// 부모(Shape)의 이름으로 자식이 구현된 draw();를 사용 할 수 있게 되었다.
		}
	}
	

