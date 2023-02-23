package com.weberichan.controller;

import com.weberichan.controller.service.FriendManagementService;
import com.weberichan.controller.service.OutputEntireFriendsService;
import com.weberichan.controller.service.SaveFriendService;

public class Action {
	
	private static Action instance = null;
	//private 한 생성자를 안만들어주면 웹에서 하나의 데이터를 생성 할 때마다 객체가 하나씩 생성되므로 singleTon을 제대로 활용하기 위해서는
	//  private한 생성자를 만들어줘야함
	private Action() {}
	public static Action getInstance() {
		if(instance == null) {
			instance = new Action();
		}
		return instance;
	}
	
	public FriendManagementService getService(int menu) {
		FriendManagementService result = null;
		switch (menu) {
		case 1:
		result =new	OutputEntireFriendsService();
		break;

		case 2 : 
			result = new SaveFriendService();
			break;
		
		case 9 :
		System.exit(0);
			}
		return result;
	}
}
