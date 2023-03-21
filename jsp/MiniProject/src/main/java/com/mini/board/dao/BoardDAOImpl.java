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
			
			while(rs.next()) {
				nextRef= rs.getInt("nextRef");
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

}
