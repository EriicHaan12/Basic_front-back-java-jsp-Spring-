package com.mini.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.mini.service.MemberService;

public class RegisterMemberService implements MemberService {
	// 파일 업로드를 위한 세팅
	// (하나의 파일 블럭에 들어오는 버퍼 사이즈)5MB
	private static final int MEMORY_TRESHOLD = -1024 * 1024 * 5;
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; // 최대 파일 업로드 크기 (10MB)
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 15;// 최대 request 버퍼 크기

	@Override
	public MemberFactory execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String upload = "\\uploadMember"; // 업로드할 경로
		ServletContext context = req.getServletContext(); // 현재 request에 대응하는 서블릿 객체를 얻어오는 코드
		System.out.println("현재 서블릿 객체 : "+ context);
//		req.getRealPath(upload);
		String realPath = context.getRealPath(upload); // 실제 주소를 받아옴
		// 파일이 업로드될 물리적 경로
		System.out.println("파일이 서버에 저장될 실제 경로 : " + realPath);

		// 인코딩 설정
		String encoding = "utf-8"; // 파일 이름, 텍스트 데이터 인코딩 해주기 위해서...
		// 파일 객체 생성 (파일 저장용)
		File saveFileDir = new File(realPath);
		// 파일 저장하기

		String userId = "";
		String userPwd = "";
		String userName = "";
		String userEmail = "";
		String userMobile = "";
		String userGender = "";
		String hobbies = "";
		String job = "";
		String userImg = "";
		String memo = "";

		// commons에 있는 거(파일이 저장될 공간의 경로, 사이즈 등의 정보를 가지고 있는 객체)
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(saveFileDir);
		// 파일 저장공간을 할당 해준다.(setSizeThreshold 부터 전부 파일 업로드에 대한 환경설정)
		factory.setSizeThreshold(MEMORY_TRESHOLD);// 저장될 파일의 사이즈(규격) 설정

		// 실제 request로 넘겨저온 매개변수를 통해 파일을 upload 시켜줄 객체
		ServletFileUpload sfu = new ServletFileUpload(factory);//

		sfu.setFileSizeMax(MAX_FILE_SIZE);
		sfu.setSizeMax(MAX_REQUEST_SIZE);

		// 중요) 파일 업로드 시 함께 넘겨져온 다른 텍스트 데이터를 아래와 같이
		// req.getParameter로 데이터를 얻어올 수 있는지 실험
		userId = req.getParameter(userId);
		System.out.println(userId);
		// 결과) 이렇게 작성해도 userId 값은 들어오지 않고 null이 뜬다
		// 사용할 수 없다.

		// FileItem 의 특징
		// 1) name 속성의 값이 이미지는 null이 아니라 파일 이름이다.
		// (이미지가 아닌 다른 데이터는 name 속성의 값이 null이다)
		// 2)이미지 파일의 isFormField 속성의 값은 false(이진 파일이며, 폼 데이터가 아님)이고,
		// 이미지 파일이 아닌데이터는 isFormField 속성이 true이다.
		// FieldName 속성의 값은 데이터가 넘겨져온 매개변수 이름이다.

		try {
			List<FileItem> lst = sfu.parseRequest(req);
			for (FileItem fi : lst) {
			//	System.out.println(fi);

				// 위의 FileItem의 특징을 이용해 이미지가 아닐때는 true를 뽑아주는 식 만들기
				if (fi.isFormField()) {// 이미지가 아닌 데이터를 뽑기 (true)
					// 이미지가 아닐 경우 넘겨 받은 데이터
					if (fi.getFieldName().equals("userId")) {
						userId = fi.getString(encoding);
						
					} else if (fi.getFieldName().equals("pwd")) {
						userPwd = fi.getString(encoding);
						
					} else if (fi.getFieldName().equals("email")) {
						userEmail = fi.getString(encoding);
						
					} else if (fi.getFieldName().equals("mobile")) {
						userMobile = fi.getString(encoding);
						
					} else if (fi.getFieldName().equals("gender")) {
						userGender = fi.getString(encoding);
						
					} else if (fi.getFieldName().equals("hobby")) {
						hobbies = fi.getString(encoding);
						
					} else if (fi.getFieldName().equals("job")) {
						job = fi.getString(encoding);
						
					} else if (fi.getFieldName().equals("memo")) {
						memo = fi.getString(encoding);
					}

				} else {// isFormField()가 false일 경우 즉, image 파일 인 경우
					saveUserImg(fi, userId, realPath);
				}

			}
			System.out.println(userId);

		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		return null;
	}

	private void saveUserImg(FileItem fi, String userId, String realPath) {
		long tmpFileSize = fi.getSize();// 이미지 파일 사이즈 가져오기
		String tmpFileName = fi.getName();
		// 이미지 파일 이름 가져오기(user가 업로드한 파일명,확장자 포함)

		// 실제 저장될 파일명
		String newFileName = "";

		if (tmpFileSize > 0) { // 파일이 들어왔을 때
			// 파일 이름 처리를 어떻게 할 것인지(같은 이름의 파일이 있으면 overwirte 되기 때문..)
			// 처리방법

			// 1)DB에 저장될 때 중복되지 않을 이름으로 저장하기
			// ex) 파일명을 "userId_(업로드한 날짜 및 시간)|| (유니크값).확장자"
			// newFileName= makeNewUniqueFileName(userId, tmpFileName);

			// refactor-> extract Method 를 하면 쉽게 함수화 시킬 수 있다.

			// 2) 파일을 저장하기 전에 같은 이름의 파일이 존재하는지 유효성 검사를 하여
			// 같은 이름의 파일이 존재한다면
			// ex) 올릴 파일명이 "둘리"라고 했을 때 "둘리(번호).확장자"
			int cnt = 0;
			while (duplicateFileName(tmpFileName, realPath)) {
				cnt++;
				tmpFileName = makeNewFileNameWithNumbering(tmpFileName, cnt);
			}
			newFileName = tmpFileName;
			System.out.println(newFileName);
		}
		
	}

	private String makeNewFileNameWithNumbering(String tmpFileName, int cnt) {
		// tmpFileName이 업로드 될 파일 경로에 같은 파일이 있는지 검사
		// 같은 것이 있다면 몇개나 있는지 확인 한 뒤, 시간 순으로 파일명+숫자를 증감.확장자 를 해준다.
		// ex)"파일명(번호).확장자"
		String newFileName = "";
		String ext = tmpFileName.substring(tmpFileName.lastIndexOf(".") + 1);// 확장자명
		String oldFilewNameWithoutExt = tmpFileName.substring(0,tmpFileName.lastIndexOf("."));// 파일 이름만
		// 확장자가 없는 고유 파일명

		int openPos = oldFilewNameWithoutExt.indexOf("(");// 괄호가 없으면 -1 ,있으면 위치index찍어줌
		
		if (openPos == -1) {// 괄호가 없을 때 -> 처음 중복 됬을 때
			newFileName = oldFilewNameWithoutExt + "(" + cnt + ")." + ext;
		} else { // 이후 중복 됬을 때 -> 괄호가 있을 때 ex)김태희(1).jpg 가 되었을 때
			newFileName = oldFilewNameWithoutExt.substring(0, openPos) + "(" + cnt + ")." + ext;
		}
		// while문으로 다시 중복이 있는지 확인해주기 위해서
		return newFileName;
	}

//tmpFileName의 파일이 realPath 존재한다면 true, 아니면 false 반환
	private boolean duplicateFileName(String tmpFileName, String realPath) {
		boolean result = false;

		// 파일 경로
		File tmpFileNamePath = new File(realPath);
		File[] files = tmpFileNamePath.listFiles();
		for (File f : files) {
			if (f.getName().equals(tmpFileName)) {// true 파일명이 중복되는 것이 있다
				result = true;
			}
		}
		// 중복이 없다면 false
		return result;
	}

	private String makeNewUniqueFileName(String userId, String tmpFileName) {
		String newFileName;
		String ext = tmpFileName.substring(tmpFileName.lastIndexOf(".") + 1);// 확장자명 얻어오기

		String uuid = UUID.randomUUID().toString(); // 16진수 128bit 랜덤한 유니크값 생성
		newFileName = userId + "_" + uuid + "." + ext;
		System.out.println(newFileName);// unique 값으로 저장될 새로운 파일명
		return newFileName;
	}

}
