package com.mini.board.controller;

import com.mini.board.service.BoardListService;
import com.mini.board.service.BoardService;
import com.mini.board.service.BoardViewService;
import com.mini.board.service.BoardWriteService;

public class BoardFactory {

	private static BoardFactory instance = null;

	private boolean isRedirect;// 리다이렉트 인지 아닌지
	private String whereIsgo; // 어느 페이지로 갈지

	private BoardFactory() {
	}

	public static BoardFactory getInstance() {
		if (instance == null) {
			instance = new BoardFactory();
		}
		return instance;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	public String getWhereIsgo() {
		return whereIsgo;
	}

	public void setWhereIsgo(String whereIsgo) {
		this.whereIsgo = whereIsgo;
	}

	public BoardService getService(String command) {
		BoardService service = null;
		if (command.equals("/board/listAll.bo")) {
			service = new BoardListService();
		} else if (command.equals("/board/write.bo")) {
			service = new BoardWriteService();
		} else if (command.equals("/board/viewBoard.bo")) {
			service = new BoardViewService();
		}
		return service;
	}

	@Override
	public String toString() {
		return "BoardFactory [isRedirect=" + isRedirect + ", whereIsgo=" + whereIsgo + "]";
	}

}
