package com.mini.member.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mini.etc.PagingInfo;
import com.mini.member.controller.MemberFactory;
import com.mini.member.dao.MemberDAO;
import com.mini.member.dao.MemberDAOImpl;
import com.mini.vodto.MemberPointVo;

public class GetMemberPointService implements MemberService {

	@Override
	public MemberFactory execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("application/json; chartset=utf-8");
		resp.setCharacterEncoding("utf-8");
		
		PrintWriter out = resp.getWriter();

		String userId = req.getParameter("userId");
		int pageNo =-1;
		
		if( req.getParameter("pageNo")==null || req.getParameter("pageNo").equals("")){
			pageNo= 1;
		}else {
			pageNo = Integer.parseInt( req.getParameter("pageNo"));
		}
		
		
		System.out.println("페이지 번호 : " + pageNo);
		
		MemberDAO mdao = MemberDAOImpl.getinstance();
		
		
		JSONObject json = new JSONObject();
		
		try {
			
			
		PagingInfo pi= getPagingInfo(pageNo, mdao, userId);
		
		
		
			List<MemberPointVo> mpv = mdao.getMemberPoint(userId, pi);
		
		
			
			JSONArray memberPoints = new JSONArray();
			for(MemberPointVo pt : mpv) {
					JSONObject memberPoint = new JSONObject();
					memberPoint.put("who", pt.getWho());
					Timestamp when = pt.getWhen();
					memberPoint.put("when", pt.getWhen().toString());
					memberPoint.put("why", pt.getWhy());
					memberPoint.put("howmuch", pt.getHowmuch());
					
					memberPoints.add(memberPoint);
			}
			json.put("memberPoints",memberPoints);
			
			json.put("startNumOfCurrentPagingBlock",pi.getStartNumOfCurrentPagingBlock()+"");
			json.put("endNumOfCurrentPagingBlock", pi.getendNumOfCurrentPagingBlock()+"");
		
			json.put("status", "success");
			
		} catch (NamingException | SQLException e) {

			e.printStackTrace();
			json.put("status","fail");
			json.put("errorMsg", e.getMessage());
			
			
			
		} // 포인트 내역 가져오기

		System.out.println(json.toJSONString());
		
		out.print(json.toJSONString());
		out.close();
		return null;
	}

	private PagingInfo getPagingInfo(int pageNo, MemberDAO mdao, String userId) 
			throws NamingException, SQLException {
		
		PagingInfo pi = new PagingInfo();
		
		pi.setViewPostCntPerPage(5); // 하나의 페이지에 보여줄 데이터 갯수, default값으로 5개
		
		pi.setTotalPostCnt(mdao.getTotalPointCnt(userId)); //전체 페이지수 계산
		
		pi.setTotalPageCnt(pi.getTotalPostCnt(), pi.getViewPostCntPerPage()); //총페이지수
		
		pi.setStartRowIndex(pageNo);   //시작 페이지 rowIndex 세팅
		
		
		pi.setPageBlockOfCurrentPage(pageNo); //페이징 블럭
		pi.setStartNumOfCurrentPagingBlock(pi.getPageBlockOfCurrentPage());// 페이징 블럭 시작번호
		pi.setEndNumOfCurrentPagingBlock(pi.getStartNumOfCurrentPagingBlock());// 페이징 블럭 끝번호
		
		
		return pi;
	}

}
