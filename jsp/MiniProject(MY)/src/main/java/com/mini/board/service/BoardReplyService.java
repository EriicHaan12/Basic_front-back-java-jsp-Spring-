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
import com.mini.error.CommonException;
import com.mini.vodto.BoardVo;

public class BoardReplyService implements BoardService {

	@Override
	public BoardFactory action(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardFactory bf = BoardFactory.getInstance();

		req.setCharacterEncoding("utf-8");
		
		String writer = req.getParameter("writer");
		String title = req.getParameter("title");
		String content = req.getParameter("content");

		int pNo = Integer.parseInt(req.getParameter("pNo"));
		int pRef = Integer.parseInt(req.getParameter("pRef"));
		int pStep = Integer.parseInt(req.getParameter("pStep"));
		int pRefOrder = Integer.parseInt(req.getParameter("pRefOrder"));

		content = content.replace("\n", "<br>");

		BoardVo reply = new BoardVo(0, writer, title, null, content, null, 0, 0, pRef, pStep, pRefOrder);
		System.out.println(reply.toString());

		BoardDAO dao = BoardDAOImpl.getInstance();

		try {
			if (dao.transactionProcessForReplyPost(reply) == 1) {
				// 업데이트 이후 리스트 페이지로 그냥 돌아가면 되기 때문에, 바인딩 해줄 것이 없다.
				bf.setRedirect(false);
				bf.setWhereIsgo("listAll.bo");
			} else {// result = -1
				bf.setWhereIsgo("viewBoard.bo?no=" + pNo + "&status=fail");
			}
		} catch (NamingException | SQLException e) {
			if (e instanceof NamingException) {
				
				CommonException ce = new CommonException(e.getMessage(), 99);
				// throw ce; 강제로 예외를 발생 시킴
				ce.setErrorMsg(e.getMessage());
				ce.setStackTrace(e.getStackTrace());

				req.setAttribute("error", ce); // 에러 정보를 가진 CommonException 객체를 바인딩

				bf.setRedirect(false);
				// req.getRequestDispatcher("../error.jsp").forward(req, resp);// 에러가 뜬 객체를 확인하고
				// 에러페이지로 이동시키기
				// 페이지를 이동시키기(fowarding 시켜주기 ) 떄문에 return을 해줄 필요가 없다
				bf.setWhereIsgo("../error.jsp");

			} else if (e instanceof SQLException) {
				e.printStackTrace();
				bf.setRedirect(true);
				bf.setWhereIsgo("viewBoard.bo?no=" + pNo + "&status=fail"); //

				return bf;
			}

		}

		return bf;
	}

}
