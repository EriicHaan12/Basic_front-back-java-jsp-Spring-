package com.mini.controller;

import com.mini.service.ConfirmCodeService;
import com.mini.service.DuplicateUserIdService;
import com.mini.service.LoginMemberService;
import com.mini.service.LogoutMemberService;
import com.mini.service.MemberService;
import com.mini.service.MyPageMemberService;
import com.mini.service.RegisterMemberService;
import com.mini.service.SendMailService;

public class MemberFactory {
	private static MemberFactory instance = null;
	private boolean isRedirect;// redirect 할 것인지 말것인지 구분해주기 위한 변수
//default 는 false이다
	private String whereIsgo; // 만약 redirect 한다면 어느 페이지로 이동할 것인지 구분해주기 위한 변수

//싱글톤
	private MemberFactory() {
	}

	public static MemberFactory getInstance() {
		if (instance == null) {
			instance = new MemberFactory();
		}
		return instance;
	}

//
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

	/**
	 * 공통 서블릿에서 command를 받아 필요한 서비스단의 객체를 반환해주는 메서드 command : 공통서블릿단에서 주는 요청한 서비스 객체
	 * 반환값 타입 :
	 */
	public MemberService getService(String command) {

		MemberService service = null;
		if (command.equals("/member/login.mem")) {// 로그인
			service = new LoginMemberService();
		} else if (command.equals("/member/register.mem")) {// 회원가입
			service = new RegisterMemberService();
		} else if (command.equals("/member/duplicateUserId.mem")) {// 회원 아이디 중복 검사
			service = new DuplicateUserIdService();
		} else if (command.equals("/member/logout.mem")) {// 로그아웃
			service = new LogoutMemberService();
		} else if (command.equals("/member/sendMail.mem")) {
			service = new SendMailService(); // 인증코드를 메일로 보내주는 서비스
		} else if (command.equals("/member/confirmCode.mem")) { // 인증코드 확인하는 서비스
			service = new ConfirmCodeService();
		} else if (command.equals("/member/myPage.mem")) { // 마이페이지 이동
			service = new MyPageMemberService();
		}
		return service;
	}

}
