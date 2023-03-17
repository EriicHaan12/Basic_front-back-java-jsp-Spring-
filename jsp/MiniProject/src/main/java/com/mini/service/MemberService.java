package com.mini.service;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mini.controller.MemberFactory;

public interface MemberService {

	MemberFactory execute(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException, NoSuchProviderException, MessagingException;

}
