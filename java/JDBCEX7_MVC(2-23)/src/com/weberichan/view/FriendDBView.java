package com.weberichan.view;

import java.sql.SQLException;
import java.util.Scanner;

import com.weberichan.controller.Action;
import com.weberichan.controller.service.FriendManagementService;

public class FriendDBView {

		
		private static void outputMenu() {
			System.out.println();
			System.out.println("=========================================");
			System.out.println("==              친구 관리V1             ==");
			System.out.println("=========================================");
			System.out.println("==1. 친구 조회(전체 조회)               ==");
			System.out.println("==2. 친구 입력                          ==");
			System.out.println("==3. 친구 조회(이름으로 조회)           ==");
			System.out.println("==4. 친구 수정(이름 수정)               ==");
			System.out.println("==5. 친구 수정(전화 번호 수정)          ==");
			System.out.println("==6. 친구 수정(주소 수정)              ==");
			System.out.println("==7. 친구 삭제                         ==");
			System.out.println("==9. 종료                              ==");
			System.out.println("=========================================");
			System.out.println("메뉴 번호 입력 >>>");
	}
		public static void main(String[] args) throws ClassNotFoundException, SQLException {
			while (true) {
	
				outputMenu();
				Scanner sc = new Scanner(System.in);
				int menu = sc.nextInt();
		FriendManagementService fms = Action.getInstance().getService(menu);
			
		if(fms != null) {
		
			try {
			fms.toDo();
			}catch(ClassNotFoundException e) {
				System.out.println("드라이버가 없습니다.");
			}catch(SQLException e) {
				System.out.println("SQL Exception");
				e.printStackTrace();
			}
			
		}
		
			//같은 해시코드가 무한히 찍히면 singleTon 생성에 성공한것
//			for(int i = 0; i<5; i ++) {
//				System.out.println(Action.getInstance().hashCode());		
//			}
		
				
//			switch (menu) {
//			case 1:
//				outputEntireFrieds();
//				break;
//
//			case 9 :
//				System.exit(0);
//				}
//			}
			
			
			
			
		}
//		private static void outputEntireFrieds() {
//			
//			
//		}

}
}
