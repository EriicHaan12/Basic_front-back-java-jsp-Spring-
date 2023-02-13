package weberichan;

//Pet들이랑 상속관계가 아니다
public class PetDoctor {

	private String name;
	private int age;
	
	PetDoctor(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

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
	
	//
	public void doClinic(Pet p) {// 제 2 다향성 : 부모이름으로 모든 자식 객체를 매개변수로받을 수 있다.
									// 부모가 가지고 있는 자식들의 속성을 매개변수로 쓸 수 있다.
		System.out.println(p.toString()+"을 치료 합니다!");
		
		
	}

	@Override
	public String toString() {
		return "PetDoctor [name=" + name + ", age=" + age + "]";
	}
	
}
