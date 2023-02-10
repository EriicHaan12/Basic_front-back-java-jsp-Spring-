package weberichan;

public class Circle extends Shape {

	Circle(String name, String color, Point p, int radius) {
		super(name, color, p);
	this.radius=radius;
	}

	private int radius;
	
	
	
	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
//	
	@Override
	public void draw() {
		
	System.out.println(this.toString()+"인 원을 그립니다");
	}

	@Override
	public String toString() {
		return  super.toString() + "radius=" + this.radius;
	}
	
	
	
	
}

