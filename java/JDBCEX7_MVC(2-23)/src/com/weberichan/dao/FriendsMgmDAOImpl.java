package com.weberichan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.weberichan.dto.FriendDTO;
import com.weberichan.dto.ModifyFriendNameDTO;
import com.weberichan.dto.ModifyMobileDTO;
import com.weberichan.dto.SelectFriendByNameDTO;
import com.weberichan.vo.Friend;

public class FriendsMgmDAOImpl implements FriendsMgmDAO {

	private static FriendsMgmDAOImpl instance = null;

	private FriendsMgmDAOImpl() {
	};

	public static FriendsMgmDAOImpl getInstance() {
		if (instance == null) {
			instance = new FriendsMgmDAOImpl();
		}
		return instance;
	}
	@Override
	public List<Friend> selectFriendsByName(SelectFriendByNameDTO dto) throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.getConnection();
		System.out.println(con.toString());

		List<Friend> lst = new ArrayList<>();

		if (con != null) {
			String query = "select * from friends where friendName=?";

			PreparedStatement pstmt = null;
			
			pstmt = con.prepareStatement(query);
		System.out.println(dto);
		//dto 는 객체 이기 때문에 객체에서 따로 뽑아내야한다.
			pstmt.setString(1,dto.getFriendName());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new Friend(rs.getInt("FRIENDNO"), rs.getString("FRIENDNAME"), rs.getString("MOBILE"),
						rs.getString("ADDR")));
			}
			for(Friend f : lst) {
			System.out.println(f.toString());
		}
			if(lst.size()==0) {
				System.out.println("그런 친구 없습니다.");
			}
			
			
			DBConnection.close(rs, pstmt, con);
	}
		return  lst;
}
	

	@Override
	public List<Friend> SelectAllFriends() throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.getConnection();
//		System.out.println(con.toString());

		List<Friend> lst = new ArrayList<>();

		if (con != null) {
			String query = "select * from friends";

			PreparedStatement pstmt = null;

			pstmt = con.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new Friend(rs.getInt("FRIENDNO"), rs.getString("FRIENDNAME"), rs.getString("MOBILE"),
						rs.getString("ADDR")));
			}
			DBConnection.close(rs, pstmt, con);
		}
//	for(Friend f : lst) {
//		System.out.println(f.toString());
//	}

		return lst;
	}

	@Override
	public int insertFriend(int friendNo, FriendDTO dto) throws ClassNotFoundException, SQLException {

		Connection con = DBConnection.getConnection();
		String query = "insert into friends(friendNo,friendName,mobile,addr) values(?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, friendNo);
		pstmt.setString(2, dto.getFriendName());
		pstmt.setString(3, dto.getMobile());
		pstmt.setString(4, dto.getAddr());

		int result = pstmt.executeUpdate();
		DBConnection.close(pstmt, con);
		return result;
	}

	@Override
	public int getNextFrinedNo() throws ClassNotFoundException, SQLException {

		Connection con = DBConnection.getConnection();

		int result = 0;
		if (con != null) {
			String query = "select max(friendNo) as MaxNo from friends";

			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt("MaxNo") + 1;
			}
			DBConnection.close(rs, pstmt, con);
		}

		return result;
	}

	@Override
	public int isDuplicateMobile(String mobile) throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.getConnection();
		int result = 0;

		if (con != null) {
			String query = "select count(*) as cnt from friends where mobile =?";

			PreparedStatement pstmt = null;

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mobile);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
			result = rs.getInt("cnt");
			}
			DBConnection.close(rs, pstmt, con);
	}
		return result;
}
// 친구 전화 번호 변경 할 때, 없는 친구 번호를 입력 하게 되면, 에러를 띄워주자...


	@Override
	public int updateFriendName(ModifyFriendNameDTO dto) throws ClassNotFoundException, SQLException {
	
		
		Connection con = DBConnection.getConnection();
		String query = "update friends set friendname=? where friendno=?";
		PreparedStatement pstmt = null;
		
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, dto.getFriendName());
		pstmt.setInt(2, dto.getFriendNo());
		
		int result = pstmt.executeUpdate();
		
		DBConnection.close(pstmt, con);
		return result;
	}

	@Override
	public int updateFriendMobile(ModifyMobileDTO dto) throws ClassNotFoundException, SQLException {
		
		Connection con = DBConnection.getConnection();
		String query = "update friends set mobile=? where friendno=?";
		PreparedStatement pstmt = null;
		
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, dto.getMobile());
		pstmt.setInt(2, dto.getFriendNo());
		
		int result = pstmt.executeUpdate();
		
		DBConnection.close(pstmt, con);
		return result;
	}

	@Override
	public int updateFriendAddr() throws ClassNotFoundException, SQLException {

		return 0;
	}

	@Override
	public int delectFriend() throws ClassNotFoundException, SQLException {

		return 0;
	}
}
