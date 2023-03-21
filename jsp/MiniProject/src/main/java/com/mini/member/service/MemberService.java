package com.mini.member.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mini.member.controller.MemberFactory;

public interface MemberService {

	MemberFactory execute(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException;

}
