package com.mini.board.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mini.board.controller.BoardFactory;
import com.mini.board.dao.BoardDAO;
import com.mini.board.dao.BoardDAOImpl;
import com.mini.error.CommonException;
import com.mini.vodto.BoardVo;

public class BoardTopListService implements BoardService {

	@Override
	public BoardFactory action(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		BoardFactory bf = BoardFactory.getInstance();
		
		BoardDAO dao = BoardDAOImpl.getInstance();
		
		
		try {
			List<BoardVo> lst = dao.selectTopListBoard();
			req.setAttribute("boardList", lst);
			
			bf.setRedirect(false);
			bf.setWhereIsgo("index.jsp");
			
		} catch (NamingException | SQLException e) {
			if(e instanceof NamingException) {
				//NamingException은 개발자 실수 이기 때문에 개발자만 보도록 공통 error.jsp 에러페이지를 만들었고
				//에러 정보를 error.jsp로 바인딩하여 error.jsp페이지에서 에러정보를 출력하였다.
				//forward 
				// 프로그래머가 코드를 잘못 입력 하였을 경우
				CommonException ce =new CommonException(e.getMessage(), 99);
					//throw ce; 강제로 예외를 발생 시킴
					ce.setErrorMsg(e.getMessage());
					ce.setStackTrace(e.getStackTrace());
					
					req.setAttribute("error",ce); // 에러 정보를 가진 CommonException 객체를 바인딩
					
				//	req.getRequestDispatcher("../error.jsp").forward(req, resp);//에러가 뜬 객체를 확인하고 에러페이지로 이동시키기
					//페이지를 이동시키기(fowarding 시켜주기 ) 떄문에 return을 해줄 필요가 없다
					
					// 멤버 페이지 때와는 다르게 서블릿에서
					//RequestDispatcher rd=req.getRequestDispatcher(bf.getWhereIsgo()); 라고 묶어 줬기 떄문에
					bf.setRedirect(false);
					bf.setWhereIsgo("../error.jsp");
				
			}else if(e instanceof SQLException) {
				// 유저가 회원정보 입력 때 잘못 입력 하였을 경우 나는 에러
				//SQL Exception 은 대부분 실제 유저의 입력 오류로 인한 예외
				bf.setRedirect(true);// 에러가 떴을 때 에러가 떴다는 상태를 쿼리스트링으로 보내주기 위해
				bf.setWhereIsgo("../register.jsp?status=fail"); // 다시 회원가입하라고 register.jsp 페이지로 이동시켜주기
				//(대신쿼리 스트링을 status=fail을 달아서 보내준다, 이후 회원가입이 실패했다는 사실을 유저에게 알리기 위해)
				return bf;
			}
		}
		return bf;
	}

}
