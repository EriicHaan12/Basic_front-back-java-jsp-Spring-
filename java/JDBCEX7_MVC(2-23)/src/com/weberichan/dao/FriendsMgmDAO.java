package com.weberichan.dao;

import java.sql.SQLException;
import java.util.List;

import com.weberichan.controller.service.SelectFriendByName;
import com.weberichan.dto.FriendDTO;
import com.weberichan.dto.ModifyFriendNameDTO;
import com.weberichan.dto.ModifyMobileDTO;
import com.weberichan.dto.SelectFriendByNameDTO;
import com.weberichan.vo.Friend;

public interface FriendsMgmDAO {
	// 전체 친구 목록 조회
	List<Friend> SelectAllFriends() throws ClassNotFoundException, SQLException;
	
	//친구 저장
     int insertFriend(int friendNo, FriendDTO dto)throws ClassNotFoundException, SQLException;
	
	// 친구 번호 중 가장 높은 번호를 가져옴
     int getNextFrinedNo()throws ClassNotFoundException, SQLException;
	
     //전화번호를 받아 테이블에 받은 전화번호가 중복되는지 검사
    int isDuplicateMobile(String mobile)throws ClassNotFoundException, SQLException;
    
    //친구 조회(이름으로 조회)
	List<Friend> selectFriendsByName(SelectFriendByNameDTO dto) throws ClassNotFoundException, SQLException;
    
    // 친구 수정(이름 수정)
    //전체 친구 목록을 보여주고, 수정할 친구 번호를 입력 받고, 이름 입력 받아 수정
    // 새로 만들기 보단, 있는 기능을 활용
    int updateFriendName(ModifyFriendNameDTO dto)throws ClassNotFoundException, SQLException;

    
    // 친구 수정(전화 번호 수정)
    int updateFriendMobile(ModifyMobileDTO dto)throws ClassNotFoundException, SQLException;

    // 친구 수정(주소 수정)
    int updateFriendAddr()throws ClassNotFoundException, SQLException;
    
    
    //친구 삭제
    int delectFriend()throws ClassNotFoundException, SQLException; 
}
