package com.mini.etc;

import java.io.File;

import org.apache.commons.fileupload.FileItem;

public class FileNameProcessiong {

	public static String getNewFileName(FileItem fi,  String realPath) {
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
		return newFileName;
	}
	//tmpFileName의 파일이 realPath 존재한다면 true, 아니면 false 반환
		private static boolean duplicateFileName(String tmpFileName, String realPath) {
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
		private static String makeNewFileNameWithNumbering(String tmpFileName, int cnt) {
			// tmpFileName이 업로드 될 파일 경로에 같은 파일이 있는지 검사
			// 같은 것이 있다면 몇개나 있는지 확인 한 뒤, 시간 순으로 파일명+숫자를 증감.확장자 를 해준다.
			// ex)"파일명(번호).확장자"
			String newFileName = "";
			String ext = tmpFileName.substring(tmpFileName.lastIndexOf(".") + 1);// 확장자명
			String oldFilewNameWithoutExt = tmpFileName.substring(0, tmpFileName.lastIndexOf("."));// 파일 이름만
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
	
	
}
