package com.weberichan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.weberichan.dto.FriendDTO;
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
}
