package com.weberichan.view;

import java.util.Scanner;

import com.weberichan.dto.FriendDTO;

public class FriendDBInsert {
	public static FriendDTO getFriendData() {
		FriendDTO friend = null;
		
		
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("친구 이름 >>>");
		String name = sc.nextLine();
		System.out.println("친구 전화번호 >>>");
		String mobile = sc.nextLine();
		System.out.println("친구 주소 >>>");
		String addr = sc.nextLine();
		
		friend = new FriendDTO(name, mobile, addr);
		return friend;
		
	}
}
