package com.weberichan.controller.service;

import java.sql.SQLException;
import java.util.List;

import com.weberichan.dao.FriendsMgmDAO;
import com.weberichan.dao.FriendsMgmDAOImpl;
import com.weberichan.dto.ModifyFriendNameDTO;
import com.weberichan.view.FriendDBInsert;
import com.weberichan.vo.Friend;

public class ModifyFriendName implements FriendManagementService {

	@Override
	public void toDo() throws ClassNotFoundException, SQLException {
		FriendsMgmDAO dao = FriendsMgmDAOImpl.getInstance();
		List<Friend> lst = dao.SelectAllFriends(); // 전체 친구 목록 가져오기
		
		for(Friend f : lst) {
			System.out.println(f.toString());
		}
		// 수정될 친구 데이터를 dto 객체에 담아, service단으로 전송
	ModifyFriendNameDTO dto = FriendDBInsert.getModifyNameData(); //DB에서 Service 단으로 데이터 전송
		
	int result = dao.updateFriendName(dto);
	if(result ==1) {
		System.out.println("친구 수정 완료");
	}
	}
	
}
