package com.mini.board.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mini.board.controller.BoardFactory;

public class BoardViewService implements BoardService {

	@Override
	public BoardFactory action(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		int no = Integer.parseInt(req.getParameter("no"));
		
		//  시작전에 글작성  예외처리 먼저 끝내야함 
		
		//1) 글 읽어보기
		//2) 1번이 성공 했다면 조회수 1증가
		//3) ipcheck 해서 동일 ip이면 24시간에 조회수 1씩 증가
		
		
		return null;
	}

}
