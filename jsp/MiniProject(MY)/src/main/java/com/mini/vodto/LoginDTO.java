package com.mini.vodto;

public class LoginDTO {
	
	private String userId;
	private String pwd;
	
	public String getUserId() {
		return userId;
	}
	public String getPwd() {
		return pwd;
	}

	

	public LoginDTO(String userId, String pwd) {
		super();
		this.userId = userId;
		this.pwd = pwd;
	}
	
	@Override
	public String toString() {
		return "LoginDTO [userId=" + userId + ", pwd=" + pwd + "]";
	}
	
	
}
