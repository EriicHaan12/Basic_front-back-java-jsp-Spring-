package com.erichan.vo;

public class JobsVo {
	private String job_id;
	private String job_title;
	private int min_salary;
	private int max_salary;

	public JobsVo(String job_id, String job_title, int min_salary, int max_salary) {
		super();
		this.job_id = job_id;
		this.job_title = job_title;
		this.min_salary = min_salary;
		this.max_salary = max_salary;
	}

	// vo단 이라 setter는 필요가 없다.
	public String getJob_id() {
		return job_id;
	}

	public String getJob_title() {
		return job_title;
	}

	public int getMax_salary() {
		return max_salary;
	}

	public int getMin_salary() {
		return min_salary;
	}

	public void setMin_salary(int min_salary) {
		this.min_salary = min_salary;
	}

	@Override
	public String toString() {
		return "JobsVo [job_id=" + job_id + ", job_title=" + job_title + ", min_salary=" + min_salary + ", max_salary="
				+ max_salary + "]";
	}

}
