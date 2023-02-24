package com.weberichan.dto;

public class ModifyMobileDTO {
	private int friendNo;
	private String mobile;
	
	public int getFriendNo() {
		return friendNo;
	}
	public String getMobile() {
		return mobile;
	}
	@Override
	public String toString() {
		return "ModifyFriendMobileDTO [friendNo=" + friendNo + ", mobile=" + mobile + "]";
	}
	public ModifyMobileDTO(int friendNo, String mobile) {
		super();
		this.friendNo = friendNo;
		this.mobile = mobile;
	}
	
}
