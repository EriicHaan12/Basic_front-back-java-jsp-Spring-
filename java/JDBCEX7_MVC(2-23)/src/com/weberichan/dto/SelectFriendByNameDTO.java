package com.weberichan.dto;

public class SelectFriendByNameDTO {
 private String friendName;

 
public String getFriendName() {
	return friendName;
}


@Override
public String toString() {
	return "SelectFriendByNameDTO [friendName=" + friendName + "]";
}


public SelectFriendByNameDTO(String friendName) {
	super();
	this.friendName = friendName;
}
 
}
