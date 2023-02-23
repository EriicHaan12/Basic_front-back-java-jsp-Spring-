package com.weberichan.vo;

public class Friend {
	private int friendNo;
	private String friendName;
	private String mobile;
	private String addr;
	
	public Friend(int friendNo, String friendName, String mobile, String addr) {
		super();
		this.friendNo = friendNo;
		this.friendName = friendName;
		this.mobile = mobile;
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "Friend [friendNo=" + friendNo + ", friendName=" + friendName + ", mobile=" + mobile + ", addr=" + addr
				+ "]";
	}
	
}
