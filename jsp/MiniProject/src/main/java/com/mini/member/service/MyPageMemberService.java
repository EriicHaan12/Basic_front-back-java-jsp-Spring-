package com.mini.member.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mini.member.controller.MemberFactory;
import com.mini.member.dao.MemberDAO;
import com.mini.member.dao.MemberDAOImpl;
import com.mini.vodto.MemberDTO;
import com.mini.vodto.MemberPointVo;

public class MyPageMemberService implements MemberService {

	@Override
	public MemberFactory execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		//유저 아이디
		// 파라메터에서 넘겨받기
		//String userId =req.getParameter("userId");
		
		// Session으로 넘겨받기
		HttpSession ses = req.getSession();
		MemberDTO loginMember = (MemberDTO)ses.getAttribute("loginMember");
		
	
		if(loginMember != null) {
			String userId = loginMember.getUserId();	
		
			MemberDAO dao=  MemberDAOImpl.getinstance();
			
			try {
				MemberDTO memberInfo=dao.getMemberInfo(userId);
				//회원 정보를 request에 바인딩 시켜주기
				
				System.out.println("memberInfo : " + memberInfo.toString());
				
				List<MemberPointVo> mpv = dao.getMemberPoint(userId);
				
				
				req.setAttribute("memberInfo", memberInfo);
				req.setAttribute("memberPoint", mpv);
				// 페이지 이동
				req.getRequestDispatcher("myPage.jsp").forward(req, resp);
				
			} catch (NamingException | SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
		
		return null;
	}

}
