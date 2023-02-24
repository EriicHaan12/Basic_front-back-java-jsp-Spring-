package com.weberichan.controller.service;

import java.sql.SQLException;
import java.util.List;

import com.weberichan.dao.FriendsMgmDAO;
import com.weberichan.dao.FriendsMgmDAOImpl;
import com.weberichan.dto.ModifyMobileDTO;
import com.weberichan.view.FriendDBInsert;
import com.weberichan.vo.Friend;

public class ModifyMobile implements FriendManagementService {

	@Override
	public void toDo() throws ClassNotFoundException, SQLException {

		ModifyMobileDTO dto = FriendDBInsert.getModifyMobileData();
		FriendsMgmDAO dao = FriendsMgmDAOImpl.getInstance();
		List<Friend> lst = dao.SelectAllFriends();
		
		for(Friend f : lst) {
			System.out.println(f.toString());
		}
		int result = dao.updateFriendMobile(dto);
		if(result ==1) {
			System.out.println("번호 변경 완료");
		}
		
	}

}
