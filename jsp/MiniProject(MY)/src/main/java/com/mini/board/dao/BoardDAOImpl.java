package com.mini.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.mini.member.dao.DBConnection;
import com.mini.member.dao.MemberDAOImpl;
import com.mini.vodto.BoardVo;
import com.mini.vodto.ReadCountProcess;

public class BoardDAOImpl implements BoardDAO {

	private static BoardDAOImpl instance = null;

	private BoardDAOImpl() {
	}

	public static BoardDAOImpl getInstance() {
		if (instance == null) {
			instance = new BoardDAOImpl();
		}
		return instance;
	}

	@Override
	public List<BoardVo> selectEntireBoard() throws NamingException, SQLException {

		List<BoardVo> lst = new ArrayList<>();

		Connection con = DBConnection.dbConnect();

		if (con != null) {
			String q = "select * from board order by no desc";
			PreparedStatement pstmt = con.prepareStatement(q);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				lst.add(new BoardVo(rs.getInt("no"), rs.getString("writer"), rs.getString("title"),
						rs.getTimestamp("postDate"), rs.getString("content"), rs.getString("imgFile"),
						rs.getInt("readCount"), rs.getInt("likeCount"), rs.getInt("ref"), rs.getInt("step"),
						rs.getInt("refOrder")));
			}
			DBConnection.dbClose(rs, pstmt, con);
		}

		return lst;
	}

	@Override
	public int getNextRef() throws NamingException, SQLException {
		int nextRef = 0;

		Connection con = DBConnection.dbConnect();

		if (con != null) {
			String q = "select max(no) +1 as nextRef from board";
			PreparedStatement pstmt = con.prepareStatement(q);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				nextRef = rs.getInt("nextRef");
			}
			DBConnection.dbClose(rs, pstmt, con);
		}

		return nextRef;
	}

	@Override
	public int insertBoard(BoardVo board) throws NamingException, SQLException {
		// 글 등록 후 addPoint
		// 트랜잭션 처리
		int result = 0;
		Connection con = DBConnection.dbConnect();
		con.setAutoCommit(false); // 트랜잭션 시작

		if (con != null) {
			String q = "insert into board(writer,title,content,imgFile,ref)" + " values(?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getImgFile());
			pstmt.setInt(5, board.getRef());

			// 게시물 등록 및
			int writeResult = pstmt.executeUpdate();

			// 등록시에 글쓴이에게 포인트 부여
			int pointResult = MemberDAOImpl.getinstance().addPoint(board.getWriter(), "게시판글쓰기", con);

			if (writeResult == 1 && pointResult == 1) {
				con.commit();
				result = 1;
			} else {
				con.rollback();
			}

			con.setAutoCommit(true);
			DBConnection.dbClose(pstmt, con);
		}
		return result;
	}

	@Override
	public BoardVo selectBoardByNo(int no) throws NamingException, SQLException {
		BoardVo board = null;

		Connection con = DBConnection.dbConnect();

		if (con != null) {
			String q = "select * from board where no=?";
			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setInt(1, no);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				board = new BoardVo(rs.getInt("no"), rs.getString("writer"), rs.getString("title"),
						rs.getTimestamp("postDate"), rs.getString("content"), rs.getString("imgFile"),
						rs.getInt("readCount"), rs.getInt("likeCount"), rs.getInt("ref"), rs.getInt("step"),
						rs.getInt("refOrder"));
			}
			DBConnection.dbClose(rs, pstmt, con);
		}

		return board;
	}

	@Override
	public int withinOndayOrNot(String ipAddr, int boardNo) throws NamingException, SQLException {
		int result = -1;

		Connection con = DBConnection.dbConnect();
		if (con != null) {
			String q = " select ifNull(TIMESTAMPDIFF(hour, (select readtime "
					+ " from readcountprocess where ipAddr = ? and boardNo= ? ), now()) >24, -1)as diff ";
			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setString(1, ipAddr);
			pstmt.setInt(2, boardNo);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt("diff");
			}
			DBConnection.dbClose(rs, pstmt, con);
		}

		return result;
	}

	// 기존의withinOndayOrNot(String ipAddr, int boardNo) 메소드를 overload한 트랜잭션 처리용 메서드
	public int withinOndayOrNot(String ipAddr, int boardNo, Connection con) throws NamingException, SQLException {
		int result = -1;

		// Connection con = DBConnection.dbConnect();
		if (con != null) {
			String q = " select ifNull(TIMESTAMPDIFF(hour, (select readtime "
					+ " from readcountprocess where ipAddr = ? and boardNo= ? ), now()) >24, -1)as diff ";
			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setString(1, ipAddr);
			pstmt.setInt(2, boardNo);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt("diff");
			}
			// DBConnection.dbClose(rs, pstmt, con);
		}

		return result;
	}

	@Override
	public int updateReadCount(int no) throws NamingException, SQLException {
		int result = 0;

		Connection con = DBConnection.dbConnect();

		if (con != null) {
			String q = "update board set readcount = readcount+1 where no=? ";

			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setInt(1, no);

			result = pstmt.executeUpdate();

			DBConnection.dbClose(pstmt, con);
		}
		return result;
	}

	// 트랜잭션 처리용
	public int updateReadCount(int no, Connection con) throws NamingException, SQLException {
		int result = 0;

		// Connection con = DBConnection.dbConnect();

		if (con != null) {
			String q = "update board set readcount = readcount+1 where no=? ";

			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setInt(1, no);

			result = pstmt.executeUpdate();

			// DBConnection.dbClose(pstmt, con);
		}
		return result;
	}

	@Override
	public int insertReadCount(String ipAddr, int boardNo) throws NamingException, SQLException {

		int result = 0;

		Connection con = DBConnection.dbConnect();

		if (con != null) {
			String q = "insert into readcountprocess(ipAddr,boardNo) values(?, ?)";

			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setString(1, ipAddr);
			pstmt.setInt(2, boardNo);

			result = pstmt.executeUpdate();

			DBConnection.dbClose(pstmt, con);
		}
		return result;
	}

	// 마찬가지로 오버로딩용
	public int insertReadCount(String ipAddr, int boardNo, Connection con) throws NamingException, SQLException {

		int result = 0;

		// Connection con = DBConnection.dbConnect();

		if (con != null) {
			String q = "insert into readcountprocess(ipAddr,boardNo) values(?, ?)";

			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setString(1, ipAddr);
			pstmt.setInt(2, boardNo);

			result = pstmt.executeUpdate();

			// DBConnection.dbClose(pstmt, con);
		}
		return result;
	}

	@Override
	public int updateReadTime(String ipAddr, int boardNo) throws NamingException, SQLException {

		int result = 0;

		Connection con = DBConnection.dbConnect();

		if (con != null) {
			String q = "update readcountprocess set readTime = now()" + " where ipAddr=? and boardNo=?";

			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setString(1, ipAddr);
			pstmt.setInt(2, boardNo);

			result = pstmt.executeUpdate();

			DBConnection.dbClose(pstmt, con);
		}
		return result;
	}

	// 트랜잭션용
	public int updateReadTime(String ipAddr, int boardNo, Connection con) throws NamingException, SQLException {

		int result = 0;

		// Connection con = DBConnection.dbConnect();

		if (con != null) {
			String q = "update readcountprocess set readTime = now()" + " where ipAddr=? and boardNo=?";

			PreparedStatement pstmt = con.prepareStatement(q);

			pstmt.setString(1, ipAddr);
			pstmt.setInt(2, boardNo);

			result = pstmt.executeUpdate();

			// DBConnection.dbClose(pstmt, con);
		}
		return result;
	}

	@Override
	public int transactionProcessForReadCount(ReadCountProcess rcp) throws NamingException, SQLException {
		int result = -1;

		Connection con = DBConnection.dbConnect();
		con.setAutoCommit(false);// 트랜잭션 시작 지점

		if (con != null) {
			// 조회 한지 하루가 지났는지 유효성 검사
			int oneDay = withinOndayOrNot(rcp.getIpAddr(), rcp.getBoardNo(), con);
			if (oneDay == -1) {

				// 두메서드 모두 기존에 있던 메소드를 connection 객체를 가져오기 위해 오버로딩 해서 새로 만들어주었다.
				int insertResult = insertReadCount(rcp.getIpAddr(), rcp.getBoardNo(), con);

				int updateResult = updateReadCount(rcp.getBoardNo(), con);

				// 조회 한지 하루가 지났고,
				if (insertResult == 1 && updateResult == 1) {
					con.commit();
					result =1;
				} else {
					con.rollback();
				}
			} else if (oneDay == 1) {
				int updateResult1 = updateReadTime(rcp.getIpAddr(), rcp.getBoardNo(), con);
				int updateResult2 = updateReadCount(rcp.getBoardNo(), con);

				if (updateResult1 == 1 && updateResult2 == 1) {
					con.commit();
					result =1;
				} else {
					con.rollback();
				}
			}else {
				result =1;
			}

		}

		con.setAutoCommit(true); // 트랜잭션 종료;
		DBConnection.dbClose(con);
		return result;
	}

	@Override
	public BoardVo updateBoardByNo(int no) throws NamingException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
