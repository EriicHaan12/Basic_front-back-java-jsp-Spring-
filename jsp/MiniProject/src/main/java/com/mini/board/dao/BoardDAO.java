package com.mini.board.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.mini.vodto.BoardVo;

public interface BoardDAO {
	// 게시물 전체 리스트 얻어오는 메서드
	List<BoardVo> selectEntireBoard() throws NamingException, SQLException;

	// 다음 ref 값을 얻어오는 메서드
		int getNextRef()throws NamingException, SQLException;
	

	// 게시물 insert
	int insertBoard(BoardVo board) throws NamingException, SQLException;

}
