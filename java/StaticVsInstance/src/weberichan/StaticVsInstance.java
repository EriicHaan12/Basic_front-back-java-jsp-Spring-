package weberichan;

public class StaticVsInstance {
	static int SInt; //static 멤버 변수 //같은 클래스라 생략 가능
	int iInt; // instance 멤버 변수
	
	
	{
	//instance 멤버들을 초기화 할 수 있는 블럭
		iInt = 5;
		
	}
	static {
		//static 멤버들을 초기화 할 수 있는 블럭
		SInt = 100;
	}
	

	int getIint() {
		return this.iInt;
	}
	
	
	public void acc() {
		StaticVsInstance.SInt++;
		this.iInt++;
		
		System.out.println("no : " + this.hashCode()+", sInt : " + 
		StaticVsInstance.SInt+ ", iInt : " + this.iInt);
		
	}
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			StaticVsInstance svi = new StaticVsInstance();
			svi.acc();
			
		}
	}
	
}
