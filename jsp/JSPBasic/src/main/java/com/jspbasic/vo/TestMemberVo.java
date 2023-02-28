package com.jspbasic.vo;

public class TestMemberVo {
	private String userId;
	private String pwd;
	private String email;
	private String mobile;
	private String gender;
	private int age;
	private String hobbies;
	private String job;
	private String memo;
	
	public TestMemberVo(String userId, String pwd, String email, String mobile, String gender, int age, String hobbies,
			String job, String memo) {
		super();
		this.userId = userId;
		this.pwd = pwd;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.age = age;
		this.hobbies = hobbies;
		this.job = job;
		this.memo = memo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "TestMemberVo [userId=" + userId + ", pwd=" + pwd + ", email=" + email + ", mobile=" + mobile
				+ ", gender=" + gender + ", age=" + age + ", hobbies=" + hobbies + ", job=" + job + ", memo=" + memo
				+ "]";
	}
	
	
}
