package com.mini.voDto;

public class MemberDTO {
	// VO로 만들려고 했지만
	//사실상 DTO -- setter 해줄 필요 x / readOnly라
	//
	
	private String userId;
	private String userPwd;
	private String userEmail;
	private String userMobile;
	private String userGender;
	private String hobbies;
	private String job;
	private String userImg;
	private String memo;
	
	
	public MemberDTO(String userId, String userPwd, String userEmail, String userMobile,
			String userGender, String hobbies, String job, String userImg, String memo) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userEmail = userEmail;
		this.userMobile = userMobile;
		this.userGender = userGender;
		this.hobbies = hobbies;
		this.job = job;
		this.userImg = userImg;
		this.memo = memo;
	}

	
	
	public String getUserId() {
		return userId;
	}


	public String getUserPwd() {
		return userPwd;
	}

	public String getUserEmail() {
		return userEmail;
	}


	public String getUserMobile() {
		return userMobile;
	}


	public String getUserGender() {
		return userGender;
	}


	public String getHobbies() {
		return hobbies;
	}


	public String getJob() {
		return job;
	}


	public String getUserImg() {
		return userImg;
	}


	public String getMemo() {
		return memo;
	}

	@Override
	public String toString() {
		return "MemberDTO [userId=" + userId + ", userPwd=" + userPwd + ", userName=" +  ", userEmail="
				+ userEmail + ", userMobile=" + userMobile + ", userGender=" + userGender + ", hobbies=" + hobbies
				+ ", job=" + job + ", userImg=" + userImg + ", memo=" + memo + "]";
	}
	
	
}
