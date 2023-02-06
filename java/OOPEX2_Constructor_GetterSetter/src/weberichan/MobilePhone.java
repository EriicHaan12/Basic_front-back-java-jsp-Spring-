package weberichan;

public class MobilePhone {
	//속성 (멤버 변수/속성(은) 는 자동 초기화 된다.)
  private String brandName; // 초기값을 주지 않아도 에러가 나지 않는다.
  private String modelName;
  private int mainMemory;

	//생성자
	// 생성자도 오버로딩으로 만들 수 있다.
	public MobilePhone() {
	System.out.println("폰이 개통 되었습니다.");
	}
	
	
	public MobilePhone(String brandName, int mainMemory) {
		this();
		this.brandName = brandName;
		this.mainMemory =mainMemory;
//		this(brandName, null, mainMemory); //생성자 재활용
	}
	
	
	//생성자 오버로딩
	public MobilePhone(String brandName, String modelName, int mainMemory) {
	
		//생성자에서 다른 생성자를 호출 할 때는반드시 생성자으 ㅣ첫 문장에 써야 한다.
		this();// 현재 객체의 생성자 호출. 여기에서는 매개변수가 없는 기본생성자를 호출함
		
		//속성에 초기값 할당(넘겨받은 지역변수의 값을 멤버 변수에 할당)
		//지역변수와 멤버 변수의 이름이 같기 때문에 혼동되는 것을 방지하기 위해
		//멤버 변수에 현재객체라는 의미를 가지고 있는 키워드 this를 사용한다.
	this.brandName = brandName; //이 객체의 멤버 변수 = 넘겨받은 지역 변수 로 할당	
	this.modelName= modelName;
	this.mainMemory=mainMemory;
	}
	
	//메서드
	
	//getter 메서드 만들기
	String getBrandName() {
		return this.brandName;
	}
	
	String getModelName() {
		return this.modelName;
	}
	String getMainMemory() {
		return this.mainMemory+"GB";
	}
	
	//setter
	void setBrandName(String brandName) {
		this.brandName= brandName;
	}
	void setModelName(String modelName) {
		this.modelName=modelName;
	}
	boolean setMainMemory(int mainMemory) {
		//메인 메모리의 용량이 1TB까지만 허용한다고 가정하고 만든다면,
		//setter를 이런식으로 설정하는 것이 가능하다.
		boolean  result = false;
		if(mainMemory<=1024) {
			this.mainMemory= mainMemory;		
			result = true;
		}else {
			result = false;
		}
		return result ;
		
	}

	
	
	public String toString() {
		return"["+ this.getClass().getName()+this.hashCode()+"] :"+ this.brandName +
", modelName : "+this.modelName +
", mainMemory : "+this.mainMemory;				
//getClass() 현재 클래스 자체가 반환, getName() 현재 객체의 이름 반환
		}
	
	
}
