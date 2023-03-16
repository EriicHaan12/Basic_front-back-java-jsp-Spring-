package com.mini.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.mini.voDto.LoginDTO;
import com.mini.voDto.MemberDTO;

public class MemberDAOImpl implements MemberDAO{

	private static MemberDAOImpl instance = null;
	private MemberDAOImpl(){}
	public static MemberDAOImpl getinstance() {
	
		if(instance ==null) {
			instance = new MemberDAOImpl();
		}
		return instance;
	}
	
	@Override
	public int insertMember(MemberDTO dto) throws NamingException, SQLException {
	
		int result = 0;
		Connection con = DBConnection.dbConnect();
		
		if(con!=null) {
			
			//파일이 있을 때, 없을 때 쿼리문을 구분해주기 위해
			String sql = "";
			// 이것도 쿼리문의 컬럼 갯수가 다르기 때문에 초기 값을 준다.
			PreparedStatement pstmt = null;
			
			if(!dto.getUserImg().equals("")) {// 업로드할 파일이 있을 때
				
			sql=" insert into member values(?,sha1(md5(?)),?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);	
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2,dto.getUserPwd());
			pstmt.setString(3,dto.getUserEmail());
			pstmt.setString(4,dto.getUserMobile());
			pstmt.setString(5,dto.getUserGender());
			pstmt.setString(6,dto.getHobbies());
			pstmt.setString(7,dto.getJob());
			pstmt.setString(8,dto.getUserImg());
			pstmt.setString(9,dto.getMemo());
			
			
			}else { // 업로드 할 파일이 없을 때
				sql=" insert into "
						+ "member(userId,userPwd,userEmail,userMobile,userGender,hobbies,job,memo) "
						+ "values(?,sha1(md5(?)),?,?,?,?,?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, dto.getUserId());
				pstmt.setString(2,dto.getUserPwd());
				pstmt.setString(3,dto.getUserEmail());
				pstmt.setString(4,dto.getUserMobile());
				pstmt.setString(5,dto.getUserGender());
				pstmt.setString(6,dto.getHobbies());
				pstmt.setString(7,dto.getJob());
				pstmt.setString(8,dto.getMemo());
			}
			
			result = pstmt.executeUpdate(); // int타입으로 반환 executeUpdate
			DBConnection.dbClose(pstmt, con);
		}
		
		return result;
	}
	@Override
	public int selectByUserId(String userId) throws NamingException, SQLException {
		int result =0;
		Connection con = DBConnection.dbConnect();
		if(con != null) {
			String query = "select count(*) as userCnt from member where userId=?";
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,userId);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt("userCnt");
				
			}
			DBConnection.dbClose(pstmt, con);
		}
		
		return result;
	}
	@Override
	public MemberDTO loginUser(LoginDTO dto) throws NamingException, SQLException {

		MemberDTO member = null;
		
		Connection con = DBConnection.dbConnect();
		
		if(con != null) {
			String query = "select * from member where userId = ? and userPwd = sha1(md5(?))";
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,dto.getUserId());
			pstmt.setString(2,dto.getPwd());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
			member = new MemberDTO(rs.getString("userId"), 
									rs.getString("userPwd"),
									rs.getString("userEmail"),
									rs.getString("userMobile"),
									rs.getString("userGender"),
									rs.getString("hobbies"),
									rs.getString("job"),
									rs.getString("userImg"),
									rs.getString("memo"));
				
			}
			DBConnection.dbClose(rs, pstmt, con);
		}
		
		return member;
	}
}
