package com.mini.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mini.controller.MemberFactory;
import com.mini.dao.MemberDAOImpl;
import com.mini.error.CommonException;
import com.mini.voDto.LoginDTO;
import com.mini.voDto.MemberDTO;


public class LoginMemberService implements MemberService {

	@Override
	public MemberFactory execute(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		MemberFactory mf = MemberFactory.getInstance();
		
		String userId = req.getParameter("userId");
		String pwd = req.getParameter("pwd");
		
		LoginDTO dto = new LoginDTO(userId,pwd);
		
		System.out.println(dto.toString());
		
	//강사님께 여쭤보기
		try {
			MemberDTO loginMember=  MemberDAOImpl.getinstance().loginWithTransaction(dto);
			 if(loginMember != null) { // 로그인 성공
				 System.out.println("로그인 성공!");
				 
				 //로그인한 유저의 정보를 세션객체에 바인딩
				 HttpSession ses =  req.getSession();
				 
				 ses.setAttribute("loginMember",loginMember); 
				 
				 System.out.println("세션으로 넘겨받은 ses : " + ses.toString());
				 req.getRequestDispatcher("../index.jsp").forward(req, resp); // 바인딩 된 객체 페이지 이동(포워딩)
				// 성공했을 때는 객체를 데이터를 넘겨주고
				 
			 }else {
				 //실패했을 때는 쿼리스트링만
					mf.setRedirect(true);// 
					mf.setWhereIsgo("../index.jsp?status=fail"); // 메인 페이지로 이동 시켜주기
			 }
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
					req.getRequestDispatcher("../error.jsp").forward(req, resp);//에러가 뜬 객체를 확인하고 에러페이지로 이동시키기
					
					//페이지를 이동시키기(fowarding 시켜주기 ) 떄문에 return을 해줄 필요가 없다
					
			}else if(e instanceof SQLException) {
				// 유저가 회원정보 입력 때 잘못 입력 하였을 경우 나는 에러
				//SQL Exception 은 대부분 실제 유저의 입력 오류로 인한 예외
				
				mf.setRedirect(true);// 에러가 떴을 때 에러가 떴다는 상태를 쿼리스트링으로 보내주기 위해
				mf.setWhereIsgo("login.jsp?status=fail");
				//(대신쿼리 스트링을 status=fail을 달아서 보내준다, 이후 회원가입이 실패했다는 사실을 유저에게 알리기 위해)
			}
		}
		return mf;
	}
}
