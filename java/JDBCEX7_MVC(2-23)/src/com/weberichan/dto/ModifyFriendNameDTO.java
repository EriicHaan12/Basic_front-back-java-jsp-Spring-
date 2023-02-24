package com.weberichan.dto;

public class ModifyFriendNameDTO {
	private int friendNo;
	private String friendName;
	
	public ModifyFriendNameDTO(int friendNo, String friendName) {
		super();
		this.friendNo = friendNo;
		this.friendName = friendName;
	}

	public int getFriendNo() {
		return friendNo;
	}

	public String getFriendName() {
		return friendName;
	}

	@Override
	public String toString() {
		return "ModifyFriendNameTDTO [friendNo=" + friendNo + ", friendName=" + friendName + "]";
	}
	
	
}
