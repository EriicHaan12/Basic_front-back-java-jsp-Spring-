package weberichan;

public abstract class Employee {
	private String empNo;
	private String ename;
	private int deptNo;
	protected int salary;
//	private char empType;
	
	Employee(String empNo, String ename, int deptNo, int salary) {
		super();
		this.empNo = empNo;
		this.ename = ename;
		this.deptNo = deptNo;
		this.salary = salary;
	}


	public String getEname() {
		return ename;
	}


	public void setEname(String ename) {
		this.ename = ename;
	}


	public int getDeptNo() {
		return deptNo;
	}


	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}


	public String getEmpNo() {
		return empNo;
	}

	//정규직과 알바직 사원의 급여 지급 방법이 다르므로, 지금 구현 할 수 없다...
	public abstract int paidSalary();


	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", ename=" + ename + ", deptNo=" + deptNo + ", salary=" + salary + "]";
	}
	
}
