package com.weberichan.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.weberichan.controller.service.DuplicateMobileService;
import com.weberichan.controller.service.ModifyMobile;
import com.weberichan.controller.service.SelectFriendByName;
import com.weberichan.dto.FriendDTO;
import com.weberichan.dto.ModifyMobileDTO;
import com.weberichan.dto.ModifyFriendNameDTO;
import com.weberichan.dto.SelectFriendByNameDTO;
import com.weberichan.vo.Friend;


public class FriendDBInsert {
	
	
	public static SelectFriendByNameDTO getSelectFriendbyNameData() {
		System.out.println("찾을 친구의 이름>>");
		Scanner scString = new Scanner(System.in);
		String friendName = scString.nextLine();
		
		return new SelectFriendByNameDTO(friendName);
			
	}
	
	
	
	public static ModifyFriendNameDTO getModifyNameData() {
		System.out.println("이름을 수정할 친구 번호>>");
		Scanner scInt = new Scanner(System.in);
		// 입력될 데이터 타입이 2개 이기 때문에 Scanner도 2개 생성 해준다.
		int friendNo = scInt.nextInt();
		Scanner scString = new Scanner(System.in);
	

		
		
		System.out.print("수정될 친구 이름>>");
		String name = scString.nextLine();
		return new ModifyFriendNameDTO(friendNo, name);

	}
	public static ModifyMobileDTO getModifyMobileData() {
		System.out.println("번호를 수정할 친구 번호 입력>>");
		Scanner scInt = new Scanner(System.in);
		int friendNo = scInt.nextInt();
		
		
		
		Scanner scString = new Scanner(System.in);
		System.out.print("변경 할 번호 입력>>");
		String number = scString.nextLine();
		return new ModifyMobileDTO(friendNo, number);
		
	}
	
	
	public static FriendDTO getFriendData() {
		FriendDTO friend = null;
		
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		String name="";
		do {
			System.out.println("친구 이름 >>>");
			name = sc.nextLine();
		} while (name.equals(""));
		
		
		
		String mobile ="";
		boolean result = false;
		do {
			System.out.println("친구 전화번호 >>>");
			mobile = sc.nextLine();
			
			try {
			result = DuplicateMobileService.getInstance().duplicateMoblieService(mobile);
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			} catch (SQLException e) {

				e.printStackTrace();
			} 
			if(result) {
				mobile ="";
			}
		} while (result);
		System.out.println("친구 주소 >>>");
		String addr = sc.nextLine();
		
		friend = new FriendDTO(name, mobile, addr);
		
//		sc.close();
		
		
		return friend;
		
	}
}
