package weberichan;

public  class Permanent extends Employee {

	Permanent(String empNo, String ename, int deptNo, int salary) {
		super(empNo, ename, deptNo, salary);
	}

	@Override
	public int paidSalary() {
		
		return super.salary;
	}

	@Override
	public String toString() {
		return "Permanent [toString()=" + super.toString() + "]";
	}
	

}
