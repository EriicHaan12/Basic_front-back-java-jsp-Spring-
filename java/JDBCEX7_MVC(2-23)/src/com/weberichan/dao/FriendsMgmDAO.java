package com.weberichan.dao;

import java.sql.SQLException;
import java.util.List;

import com.weberichan.dto.FriendDTO;
import com.weberichan.vo.Friend;

public interface FriendsMgmDAO {
	// 전체 친구 목록 조회
	List<Friend> SelectAllFriends() throws ClassNotFoundException, SQLException;
	
	//친구 저장
     int insertFriend(int friendNo, FriendDTO dto)throws ClassNotFoundException, SQLException;
	
	// 친구 번호 중 가장 높은 번호를 가져옴
     int getNextFrinedNo()throws ClassNotFoundException, SQLException;
	
}
