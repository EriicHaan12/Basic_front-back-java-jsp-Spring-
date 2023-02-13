package weberichan;

public class Rectangle extends Shape {


private int width;
private int height;


Rectangle(String name, String color, Point p, int width, int height) {
	super(name, color, p);
	this.width=width;
	this.height=height;

}


public int getWidth() {
	return width;
}



public void setWidth(int width) {
	this.width = width;
}



public int getHeight() {
	return height;
}



public void setHeight(int height) {
	this.height = height;
}



@Override
public void draw() {
	System.out.println(this.toString()+ "인 사각형을 그립니다.");
	

}


@Override
public String toString() {
	return super.toString() + 
			"width=" + this.width + ", height=" +this. height;
}




}
