package com.mini.board.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mini.board.controller.BoardFactory;
import com.mini.board.dao.BoardDAO;
import com.mini.board.dao.BoardDAOImpl;
import com.mini.etc.IpCheck;
import com.mini.vodto.BoardVo;
import com.mini.vodto.ReadCountProcess;

public class BoardViewService implements BoardService {

	@Override
	public BoardFactory action(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		BoardFactory bf = BoardFactory.getInstance();
		System.out.println(bf.toString());

		if (req.getParameter("no") == null || req.getParameter("no").equals("")) {
			// 쿼리스트링에 no라는 파라메터가 존재하지 않거나(null) no라는 파라메터의 값이 비어있다.(?no=)
			bf.setRedirect(true);
			bf.setWhereIsgo("listAll.bo");
			return bf;
		}
		
		System.out.println(req.getParameter("no"));
		int boardNo = Integer.parseInt(req.getParameter("no"));
		System.out.println(boardNo + "번글을 조회 하자");

	

		// ip주소 얻어오기 위한 메서드 호출
		String ipAddr = IpCheck.getIpAddr();

		ReadCountProcess rcp = new ReadCountProcess(0, ipAddr, boardNo, null);
		BoardDAO dao = BoardDAOImpl.getInstance();
		System.out.println(rcp.toString());

		try {
			if (dao.transactionProcessForReadCount(rcp) == 1) {
				// 트랜잭션 처리
				BoardVo board = dao.selectBoardByNo(boardNo);
				
				//포워딩
				req.setAttribute("board", board);
				bf.setRedirect(false);
				bf.setWhereIsgo("viewBoard.jsp");
			}
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 1) ip 체크 후 동일한 ip주소가 같은 게시물을 24시간 내에 읽으면 조회수 증가 시키지 않도록

		// 1) 조회수 증가

		// 1) 글 읽어보기

		// 2)1번이 성공 했다면, ipcheck 해서 동일 ip이면 24시간에 조회수 1씩 증가

		return bf;
	}

}
