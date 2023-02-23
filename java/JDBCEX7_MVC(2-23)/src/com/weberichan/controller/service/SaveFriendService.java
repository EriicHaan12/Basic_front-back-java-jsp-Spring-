package com.weberichan.controller.service;

import java.sql.SQLException;

import com.weberichan.dao.FriendsMgmDAO;
import com.weberichan.dao.FriendsMgmDAOImpl;
import com.weberichan.dto.FriendDTO;
import com.weberichan.view.FriendDBInsert;

public class SaveFriendService implements FriendManagementService {

	@Override
	public void toDo() throws ClassNotFoundException, SQLException {

		System.out.println("친구 입력하러 가자!!");
	
		FriendsMgmDAO dao = FriendsMgmDAOImpl.getInstance();
		// 입력될 친구 데이터 가져온 부분...
		FriendDTO friend =FriendDBInsert.getFriendData();
//		System.out.println(friend.toString());
	
	// 다음에 저장될 친구 번호를 거져와야함
	int friendNo = dao.getNextFrinedNo();
	
	System.out.println(friendNo);
	//친구 저장
	if(dao.insertFriend(friendNo, friend)== 1) {
		System.out.println("친구 저장 완료");
	};
	}

}
