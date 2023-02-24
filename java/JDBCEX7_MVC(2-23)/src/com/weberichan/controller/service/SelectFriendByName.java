package com.weberichan.controller.service;

import java.sql.SQLException;
import java.util.List;

import com.weberichan.dao.FriendsMgmDAO;
import com.weberichan.dao.FriendsMgmDAOImpl;
import com.weberichan.dto.SelectFriendByNameDTO;
import com.weberichan.view.FriendDBInsert;
import com.weberichan.vo.Friend;

public class SelectFriendByName implements FriendManagementService {

	@Override
	public void toDo() throws ClassNotFoundException, SQLException {

		FriendsMgmDAO dao = FriendsMgmDAOImpl.getInstance();
		List<Friend> lst = dao.SelectAllFriends();
		
		for(Friend f : lst) {
			System.out.println(f.toString());
		}
		
		SelectFriendByNameDTO dto = FriendDBInsert.getSelectFriendbyNameData();
		List<Friend>result = dao.selectFriendsByName(dto);
		System.out.println(dto);
	
	}
	
}
