package com.weberichan.controller.service;

import java.sql.SQLException;
import java.util.List;

import com.weberichan.dao.FriendsMgmDAO;
import com.weberichan.dao.FriendsMgmDAOImpl;
import com.weberichan.vo.Friend;

public class OutputEntireFriendsService implements FriendManagementService {

	@Override
	public void toDo() throws ClassNotFoundException, SQLException {
		System.out.println("전체친구 조회하러 가기!");
		
		
	FriendsMgmDAO fmd = FriendsMgmDAOImpl.getInstance();
	List<Friend> lst = fmd.SelectAllFriends();
	
	for(Friend f : lst ) {
		System.out.println(f.toString());
		}
	}

}
