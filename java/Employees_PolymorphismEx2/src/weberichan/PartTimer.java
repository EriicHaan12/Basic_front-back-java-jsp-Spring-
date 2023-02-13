package weberichan;

public class PartTimer extends Employee {

	private int workTime; //일한 시간
	private int payPerHour; // 시급 
	
	
	PartTimer(String empNo, String ename, int deptNo, int workTime, int payPerHour) {
		super(empNo, ename, deptNo,  0);
		this.workTime = workTime;
		this.payPerHour= payPerHour;
		this.paidSalary();
	}


	public int getWorkTime() {
		return workTime;
	}


	public void setWorkTime(int workTime) {
		this.workTime = workTime;
	}


	public int getPayPerHour() {
		return payPerHour;
	}


	public void setPayPerHour(int payPerHour) {
		this.payPerHour = payPerHour;
	}
	
	@Override
	public int paidSalary() {
		super.salary= this.workTime*this.payPerHour;
		return super.salary;
	}


	@Override
	public String toString() {
		return "PartTimer [workTime=" + workTime + ", payPerHour=" + payPerHour + ", toString()=" + super.toString()
				+ "salary = "+ super.salary + "]";
	}

}
