package com.mini.board.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.mini.etc.PagingInfo;
import com.mini.vodto.BoardVo;
import com.mini.vodto.ReadCountProcess;

public interface BoardDAO {
	// 게시물 전체 리스트 얻어오는 메서드
	List<BoardVo> selectEntireBoard() throws NamingException, SQLException;
	
	// 게시물 전체 리스트 얻어오는 + 페이징
	List<BoardVo> selectEntireBoard(PagingInfo pi) throws NamingException, SQLException;
	
	
	// 인기 게시물 리스트 얻어오는 메서드
	List<BoardVo> selectTopListBoard()throws NamingException, SQLException;
	
	// 다음 ref 값을 얻어오는 메서드
		int getNextRef()throws NamingException, SQLException;

	// 게시물 insert
	int insertBoard(BoardVo board) throws NamingException, SQLException;

	//게시물 상세 조회
	BoardVo selectBoardByNo(int no)throws NamingException, SQLException;
	
	
	//게시물 상세 조회 페이지 수정
	BoardVo updateBoardByNo(int no)throws NamingException, SQLException;
	
	//동일한 ip주소가 특정한 하나의 게시물을 읽은적이 있는지 확인
	/**
	 * @param ipAddr
	 * @param boardNo
	 * 	 ?번 ip 주소가 ? 번 글을 읽은 시간이 몇시간전?
	 * @return  만약 읽은 적이 없다면 -1 반환 하도록 / 24시간 이내이면 0 /  24시간 이후이면 1
	 * 
	 * @throws NamingException
	 * @throws SQLException
	 */
	int withinOndayOrNot(String ipAddr, int boardNo)throws NamingException, SQLException;
	
	// withinOndayOrNot 이후 조회수 증가 시키기
	int updateReadCount(int no)throws NamingException, SQLException;
	
	// ip주소, 글번호, 읽은 시간 insert 시키기
	int insertReadCount(String ipAddr, int boardNo)throws NamingException, SQLException;
	
	// ip주소, 글번호를 읽어 해당 데이터의 readTime을 현재시간으로 업데이트
	int updateReadTime(String ipAddr, int boardNo)throws NamingException, SQLException;
	
	// 조회수 증가 트랜잭션 작업
	int transactionProcessForReadCount(ReadCountProcess rcp)throws NamingException, SQLException;
	
	// 작성될 게시글이 답글인지 아닌지 판단 후 update 해주기 (답글이 끼어들기 할 공간을 만들어주기) -- 트랜잭션 처리
	int updateReplyPost(BoardVo reply,Connection con)throws NamingException, SQLException;
	
	// 답글을  board테이블에 등록
	int insertReplyPost(BoardVo reply,Connection con)throws NamingException, SQLException;
	
	
	// 답글을 처리 트랜잭션 처리
	int transactionProcessForReplyPost(BoardVo reply)throws NamingException, SQLException;

	//전체 게시판의 글 갯수 얻어오기
	int getTotalPostCnt(String tableName)throws NamingException, SQLException;
	
	
}
