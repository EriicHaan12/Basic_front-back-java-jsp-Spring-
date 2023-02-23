package com.weberichan.dto;

public class FriendDTO {
	private String friendName;
	private String mobile;
	private String addr;



	public FriendDTO(String friendName, String mobile, String addr) {
		super();
		this.friendName = friendName;
		this.mobile = mobile;
		this.addr = addr;
	}
	


	@Override
	public String toString() {
		return "FriendDTO [friendName=" + friendName + ", mobile=" + mobile + ", addr=" + addr + "]";
	}



	public String getFriendName() {
		return friendName;
	}

	public String getMobile() {
		return mobile;
	}

	public String getAddr() {
		return addr;
	}

	
}
