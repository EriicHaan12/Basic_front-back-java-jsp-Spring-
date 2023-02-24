package com.weberichan.controller.service;

import java.sql.SQLException;

import com.weberichan.dao.FriendsMgmDAO;
import com.weberichan.dao.FriendsMgmDAOImpl;

public class DuplicateMobileService {
	//이 객체 또한 singleTon으로 만들어 주기
	private static DuplicateMobileService instance = null;
	private DuplicateMobileService() {};
	public static DuplicateMobileService getInstance() {
		if(instance == null) {
			instance = new DuplicateMobileService();
			}
		return instance;
	}
	
	

	/**
	 * @Method Name :duplicateMoblieService,
	 * @작성일 : 2023. 2. 24.,
	 * @작성자 : goott6,
	 * @매개변수 : mobile(전화 번호),
	 * @반환값 : boolean,
	 * @Description : 중복이 되면 true, 중복이 안되면 false 반환
	 */
	public boolean duplicateMoblieService(String mobile) 
			throws ClassNotFoundException, SQLException {
		boolean result = false;
		FriendsMgmDAO dao = FriendsMgmDAOImpl.getInstance();
		if (dao.isDuplicateMobile(mobile) == 1) { // 중복이 있다.
			result = true;
		}
		return false;
	}

}
