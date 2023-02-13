package weberichan;

import java.util.ArrayList;


public class Department {
	private int deptNo;
	private String deptName;

	//ArrayList<E> 에서 <E> 'E' 의 의미는 element로써 Array에 E 만 담을 수 있는 배열이라고 지정해주는 것과 같다.
	private ArrayList<Employee> empList; //Employee 타입만 들어갈 수 있는 가변길이 배열(컬렉션)

	
	
	
	Department(int deptNo, String deptName) {
		super();
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.empList = new ArrayList<Employee>(); // 이미 변수 생성 때<Employee>를 선언해놔서 안써도 상관 없다.
	}

	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public int getDeptNo() {
		return deptNo;
	}
	public void addEmployee(Employee e) {
		this.empList.add(e); // 배열 끝에 새로운 사원 추가
	}
	public void outputEntireEmployees() {
		for (Employee e : this.empList) {
			System.out.println(e.toString());
		} 
			
		
	}

}

//interface는 오로지 상수와 추상 메서드 밖에 가질 수 없다.
// 즉,  interface 내에서는 추상화 정도가 너무 높기 때문에 객체를 구체적으로 표현 할 방법이 없다.
// 추상 메서드만 가지고있다.

// 데이터를 뫃아놓고 관리할 수 있는 클래스들을 컬렉션이라고 부름.