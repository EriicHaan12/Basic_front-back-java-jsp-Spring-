package com.mini.board.service;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.mini.board.controller.BoardFactory;
import com.mini.board.dao.BoardDAO;
import com.mini.board.dao.BoardDAOImpl;
import com.mini.etc.FileNameProcessiong;
import com.mini.member.dao.MemberDAO;
import com.mini.vodto.BoardVo;
import com.mini.vodto.MemberDTO;

public class BoardWriteService implements BoardService {

	private static final int MEMORY_TRESHOLD = -1024 * 1024 * 5;
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; // 최대 파일 업로드 크기 (10MB)
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 15;// 최대 request 버퍼 크기

	@Override
	public BoardFactory action(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		BoardFactory bf = BoardFactory.getInstance();

		try {
			BoardVo board = getData(req); // 넘겨저온 텍스트 데이터와 파일을 분리하여 저장

			BoardDAO dao = BoardDAOImpl.getInstance();

			if (dao.insertBoard(board) == 1) {
				bf.setRedirect(true);
				bf.setWhereIsgo("listAll.bo");
			} else {
				bf.setRedirect(true);// 글 전체를 가져온 후 다시 글전체 페이지로
				bf.setWhereIsgo("listAll.bo?status=fail");// 글 등록 실패시
			}

		} catch (FileUploadException | NamingException | SQLException e) {

			e.printStackTrace();
		}

		return bf;
	}

	private BoardVo getData(HttpServletRequest req)
			throws FileUploadException, UnsupportedEncodingException, NamingException, SQLException {

		String upload = "\\board\\imgs"; // 업로드할 경로
		ServletContext context = req.getServletContext(); // 현재 request에 대응하는 서블릿 객체를 얻어오는 코드

		System.out.println("현재 서블릿 객체 : " + context);
//		req.getRealPath(upload);
		String realPath = context.getRealPath(upload); // 실제 주소를 받아옴
		System.out.println("파일이 서버에 저장될 실제 경로 : " + realPath);
		
		File saveFileDir = new File(realPath);
		// 인코딩 설정
		String encoding = "utf-8"; // 파일 이름, 텍스트 데이터 인코딩 해주기 위해서...

		String writer = "";
		String title = "";
		String content = "";
		String imgFile = "";

		DiskFileItemFactory factory = new DiskFileItemFactory();

		
		factory.setRepository(saveFileDir);
		factory.setSizeThreshold(MEMORY_TRESHOLD);// 저장될 파일의 사이즈(규격) 설정

		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setFileSizeMax(MAX_FILE_SIZE);
		sfu.setSizeMax(MAX_REQUEST_SIZE);

		List<FileItem> lst = sfu.parseRequest(req);

		for (FileItem fi : lst) {
			if (fi.isFormField()) { // 파일이 아닐 떄

				if (fi.getFieldName().equals("writer")) {

					// 세션에서 얻어오는 방법
					// HttpSession ses = req.getSession();
					// MemberDTO loginMember = (MemberDTO) ses.getAttribute("loginMember");
					// writer = loginMember.getUserId();

					// req 에서 얻어오는 방법
					writer = fi.getString(encoding);
				} else if (fi.getFieldName().equals("title")) {
					title = fi.getString(encoding);

				} else if (fi.getFieldName().equals("content")) {
					content = fi.getString(encoding);
				}
			} else { // 파일이 있다면...
				imgFile = FileNameProcessiong.getNewFileName(fi, realPath); // 중복되지 않는 새로운 파일 이름 얻기
				File uploadFilePath = new File(realPath + File.separator + imgFile);
				try {
					fi.write(uploadFilePath);// 실제 저장
				} catch (Exception e) {
					// 유저가 업로드한 파일이 저장이 안되었을 때 발생하는 에러
					imgFile = ""; // 만약 이렇게 될 경우에는 회원가입은 시켜주되, 이미지만 업로드가 되지 않았기 때문에,
					// 이미지를 default로 만들어준다.
				}

			}

		}
		String dbUserImg = "";

		// 2) 실제 저장
		// DB에 insert 하기 전에 업로드 된 파일이 있는지 체크
		if (!imgFile.equals("")) {// 업로드된 이미지가 있다면
			dbUserImg = "board/imgs/" + imgFile; // DB에 경로까지 포함해서 insert 한다.
		}

		int ref = BoardDAOImpl.getInstance().getNextRef();
		
		 content =  content.replace("\n", "<br>"); // 저장하고 다시 출력할 때 줄바꿈 하기 위해서
		
		
		// DAO으로 전송하기 위해
		BoardVo board = new BoardVo(0, writer, title, null, content, dbUserImg, 0, 0, ref, 0, 0);

		System.out.println(board.toString());

		return board;
	}

}
