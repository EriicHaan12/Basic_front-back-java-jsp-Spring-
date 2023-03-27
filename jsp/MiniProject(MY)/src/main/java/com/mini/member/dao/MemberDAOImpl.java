package com.mini.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.mini.etc.PagingInfo;
import com.mini.vodto.LoginDTO;
import com.mini.vodto.MemberDTO;
import com.mini.vodto.MemberPointVo;

public class MemberDAOImpl implements MemberDAO {

	private static MemberDAOImpl instance = null;

	private MemberDAOImpl() {
	}

	public static MemberDAOImpl getinstance() {

		if (instance == null) {
			instance = new MemberDAOImpl();
		}
		return instance;
	}

	@Override
	public int insertMember(MemberDTO dto) throws NamingException, SQLException {

		int result = 0;
		Connection con = DBConnection.dbConnect();

		if (con != null) {

			// 파일이 있을 때, 없을 때 쿼리문을 구분해주기 위해
			String sql = "";
			// 이것도 쿼리문의 컬럼 갯수가 다르기 때문에 초기 값을 준다.
			PreparedStatement pstmt = null;

			if (!dto.getUserImg().equals("")) {// 업로드할 파일이 있을 때

				sql = " insert into member values(?,sha1(md5(?)),?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getUserId());
				pstmt.setString(2, dto.getUserPwd());
				pstmt.setString(3, dto.getUserEmail());
				pstmt.setString(4, dto.getUserMobile());
				pstmt.setString(5, dto.getUserGender());
				pstmt.setString(6, dto.getHobbies());
				pstmt.setString(7, dto.getJob());
				pstmt.setString(8, dto.getUserImg());
				pstmt.setString(9, dto.getMemo());

			} else { // 업로드 할 파일이 없을 때
				sql = " insert into " + "member(userId,userPwd,userEmail,userMobile,userGender,hobbies,job,memo) "
						+ "values(?,sha1(md5(?)),?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getUserId());
				pstmt.setString(2, dto.getUserPwd());
				pstmt.setString(3, dto.getUserEmail());
				pstmt.setString(4, dto.getUserMobile());
				pstmt.setString(5, dto.getUserGender());
				pstmt.setString(6, dto.getHobbies());
				pstmt.setString(7, dto.getJob());
				pstmt.setString(8, dto.getMemo());
			}

			// 회원가입과 포인트 부여가 트랜잭션 처리가 되어야함

			// 트랜잭션 밑작업
			con.setAutoCommit(false); // 자동으로 commit 되는것을 막아야한다. commit 되는것을 되돌리는 방법이 없기 때문

			result = pstmt.executeUpdate(); // int타입으로 반환 executeUpdate

			if (result == 1) {
				// 회원가입한 유저에게 포인트 부여
				int result2 = addPoint(dto.getUserId(), "회원가입", con);

				if (result2 == 1) {
					con.commit(); // 이 떄 커밋 시켜주기
				} else {
					con.rollback(); // insert는 잘됬지만 포인트 부여쪽에서 error가 났다면 rollback
				}

			}

			// 트랜잭션이 끝났기 때문에 다시 setAutoCommit을 다시 default로 돌려준다.
			con.setAutoCommit(true);
			DBConnection.dbClose(pstmt, con);
		}

		return result;
	}

	@Override
	public int selectByUserId(String userId) throws NamingException, SQLException {
		int result = 0;
		Connection con = DBConnection.dbConnect();
		if (con != null) {
			String query = "select count(*) as userCnt from member where userId=?";
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("userCnt");

			}
			DBConnection.dbClose(pstmt, con);
		}

		return result;
	}

	@Override
	public MemberDTO loginUser(LoginDTO dto, Connection con) throws NamingException, SQLException {

		MemberDTO member = null;

		// Connection con = DBConnection.dbConnect();

		if (con != null) {
			String query = "select * from member where userId = ? and userPwd = sha1(md5(?))";
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getPwd());

			ResultSet rs = pstmt.executeQuery();
/////////
			while (rs.next()) {
				member = new MemberDTO(rs.getString("userId"), rs.getString("userPwd"), rs.getString("userEmail"),
						rs.getString("userMobile"), rs.getString("userGender"), rs.getString("hobbies"),
						rs.getString("job"), rs.getString("userImg"), rs.getString("memo"), rs.getString("isAdmin"));

			}
			// DBConnection.dbClose(rs, pstmt, con);
		}
		return member;
	}

	@Override
	public int addPoint(String userId, String why, Connection con) throws NamingException, SQLException {
		int result = 0;

		System.out.println("포인트 부여 하는곳 ");
		// insert와 함께 트랜잭션을 쓰기 위해 따로 Connection을 열어주지 않는다
		// Connection con = DBConnection.dbConnect();
		if (con != null) {
			String query = " insert into memberpoint(who, why, howmuch) "
					+ "values(?, ?, (select howmuch from pointpolicy where why =?))";
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, why);
			pstmt.setString(3, why);

			result = pstmt.executeUpdate();

			System.out.println("포인트 부여 결과 : " + result);

			// 열어주지 않았기 때문에 닫는것도 주석처리
			// DBConnection.dbClose(pstmt, con);
		}
		return result;
	}

	/**
	 * result = -1 (null : 처음 로그인) -> 추후에 insert result = 0 (같은 일자에 로그인한 기록이 있는 유저)
	 * -> 추후에 update result = 1 (최근 로그인 한 날짜가 오늘이 아닌 유저) -> 추후에 update
	 *
	 */
	@Override
	public int whenLatestLoginDate(String userId, Connection con) throws NamingException, SQLException {
		int result = -1;
		if (con != null) {
			String query = "select ifnull(a.diff,-1)as datediff from "
					+ "(select datediff(now(), (select latestlogindate from latestloginlog where who=?)) as diff)a";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();

			System.out.println("ResultSet : " + rs);

			while (rs.next()) {
				int tmp = rs.getInt("datediff");
				if (tmp == 0) {
					result = 0;
				} else if (tmp > 0) {
					result = 1;
				}
			}
			System.out.println("whenLatestLoginDate : " + result);
		}
		return result;
	}

	@Override
	public int writtenLoginDate(int mode, String userId, Connection con) throws NamingException, SQLException {
		int result = 0;
		if (con != null) {
			String query = "";
			PreparedStatement pstmt = null;
			if (mode == -1) {
				query = "insert into latestloginlog(who) values(?)";

			} else {
				query = "update latestloginlog set latestLoginDate =now() where who = ?";
			}
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);

			result = pstmt.executeUpdate();
		}
		return result;
	}

	@Override
	public MemberDTO loginWithTransaction(LoginDTO dto) throws NamingException, SQLException {

		MemberDTO loginUser = null;
		Connection con = DBConnection.dbConnect();

		con.setAutoCommit(false); // 트랜잭션 시작

		if (con != null) {
			MemberDTO loginMember = loginUser(dto, con);
			int addPointResult = -1;
			if (loginMember != null) { // 로그인 성공
				System.out.println("로그인 한 유저 : " + loginMember.toString());
				int loginDate = whenLatestLoginDate(loginMember.getUserId(), con);
				System.out.println("로그인 상태 : " + loginDate);
				if (loginDate > 0 || loginDate == -1) { // 로그인이 처음이거나 로그인 날짜가 오늘이 아닐때
					addPointResult = addPoint(loginMember.getUserId(), "로그인", con); // 포인트 부여
					System.out.println("포인트 부여 : " + addPointResult);
				}
				int writtenLoginDateResult = writtenLoginDate(loginDate, loginMember.getUserId(), con);

				System.out.println("로그인 기록 : " + writtenLoginDateResult);
				if (addPointResult == 1 && writtenLoginDateResult == 1) {
					loginUser = loginMember;
					con.commit();
				} else if (addPointResult == -1 && writtenLoginDateResult == 1) {
					loginUser = loginMember;
					con.commit();
				} else {
					con.rollback();
				}
				// 트랜잭션 처리...
				con.setAutoCommit(true);
				con.close();
			}
		}
		return loginUser;
	}

	@Override
	public MemberDTO getMemberInfo(String userId) throws NamingException, SQLException {
		MemberDTO memberInfo = null;
		String query = "";
		Connection con = DBConnection.dbConnect();
		if (con != null) {
			query = "select * from member where userId = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				memberInfo = new MemberDTO(rs.getString("userId"), rs.getString("userPwd"), rs.getString("userEmail"),
						rs.getString("userMobile"), rs.getString("userGender"), rs.getString("hobbies"),
						rs.getString("job"), rs.getString("userImg"), rs.getString("memo"), rs.getString("isAdmin"));
			}

			DBConnection.dbClose(rs, pstmt, con);

		}

		return memberInfo;
	}

	@Override
	public List<MemberPointVo> getMemberPoint(String userId, PagingInfo pi) throws NamingException, SQLException {
		List<MemberPointVo> lst = new ArrayList<>();
		MemberPointVo mpv = null;
		Connection con = DBConnection.dbConnect();
		if (con != null) {
			String query = "select * from memberpoint where who = ? order by no desc limit ?, ? ";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, pi.getStartRowIndex());
			pstmt.setInt(3, pi.getViewPostCntPerPage());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new MemberPointVo(rs.getInt("no"), rs.getString("who"), rs.getTimestamp("when"),
						rs.getString("why"), rs.getInt("howmuch")));
			}

			DBConnection.dbClose(rs, pstmt, con);

		}
		return lst;
	}

	@Override
	public int getTotalPointCnt(String userId) throws NamingException, SQLException {
		int cnt = -1;
		Connection con = DBConnection.dbConnect();
		if (con != null) {
			String query = "select count(*) as cnt from memberPoint where who= ? ";
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				cnt = rs.getInt("cnt");

			}
			DBConnection.dbClose(pstmt, con);
		}

		return cnt;
	}
}
