package weberichan;

//1. 가장 상위 클래스에 필요한 모든@@은 @@을 가진다를 생각하면서 변수 만들어보기

// abstract 추상 클래스로 만들려면 public이나 final 로 써야 한다.
public abstract class Pet {
	private String name;
	private String type;
	private int age;
	
	
	//2. 생성자 생성
	// protected : 상속 관계 속한 클래스만 호출 가능하게 하는 제약 조건
	// public으로 바꿔주자
	public Pet(String name, String type, int age) {
		super();
		this.name = name;
		this.type = type;
		this.age = age;
	}

	//3. getter , setter 에 뭐가 필요한지 어떤건 호출만하고 수정은 하지 않을지 생각하면서getter,setter  만들기

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getType() {
		return type;
	}
	

	//4. 추상 메소드 지정해주기 
	// -- 지정 기준: 상속관계에 속한 클래스들이 모두 쓸만하지만 어떻게 쓰는진 정해지지 않았을 때...
	public abstract void doCry();

	
	//5.toString()  출력 메소드 생성
	@Override
	public String toString() {
		return "Pet [name=" + name + ", type=" + type + ", age=" + age + "]";
	}
	
	
}
