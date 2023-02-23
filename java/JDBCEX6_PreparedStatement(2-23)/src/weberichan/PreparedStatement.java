package weberichan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PreparedStatement {

	public static void main(String[] args) {

		// 오라클 DB에 접속하기 위해 필요한 정보(아이디, 패스워드, DB서버의 주소)
		String id = "hr";
		String pwd = "1234";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";

		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이버가 있는지
			con = DriverManager.getConnection(url, id, pwd); // 실제 Connection객체를 얻어옴
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다");
		} catch (SQLException e) {
			System.out.println("DB에 연결하지 못했습니다!");
		}

		if (con != null) {
			
		beforeInsert(con);	
			
			
			
		
			System.out.print("조회할 사원 이름>>");
			Scanner sc = new Scanner(System.in);
			String fristName = sc.nextLine();
			//실행할 쿼리문 준비
			// 아래의 쿼리문을 inLine 쿼리문이라고 한다.
			//쿼리문의 매개변수를 아래처럼 작성하면 SQL Injection 공격에 취약하게 된다.
			//때문에 PreparedStatement 클래스를 이용하여 쿼리문을 작성 해야 한다.
//			String query = "select * from employees where first_name ='"+ firstName + "'";
			
			//매개변수가 사용될 자리에 매개변수의 변수명 대신에 ?를 넣는다.
			String query = "select * from employees where first_name =?"; // 매개변수가 하나이기 때문에 ? 한개
			
			java.sql.PreparedStatement pstmt = null;
			
			
	
			
			//ResultSet 객체 : 쿼리문이 실행된 후의 결과 테이블을 담고 있는 객체. 단방향으로만 탐색(읽기) 가능
			ResultSet rs = null;
			try {		
				pstmt = con.prepareStatement(query); // PreparedStatement 객체 생성(sql  문장이 먼저 pre-compiling)
			
			// 매개변수(?)를 변수로 할당
				pstmt.setString(1,fristName);
			
				
				rs = pstmt.executeQuery(); // 실행할 때도 excuteQuery() 호출해야 함.
				while(rs.next()) { // 결과 행(row)가 있을 동안 반복
					System.out.println(rs.getInt("EMPLOYEE_ID") + " " 
							+ rs.getString("FIRST_NAME") + " " 
							+ rs.getString("LAST_NAME") + " " 
							+ rs.getString("EMAIL") + "  "
							+ rs.getString("PHONE_NUMBER") + " "
						    + rs.getDate("HIRE_DATE") + " "
						    + rs.getString("JOB_ID") + " "
						    + rs.getInt("SALARY") + " "
						    + rs.getFloat("COMMISSION_PCT") + " " 
						    + rs.getInt("MANAGER_ID") + " " 
						    + rs.getInt("DEPARTMENT_ID"));
				}
				
				if (rs == null) {
					System.out.println("no data!");
				}
				
				rs.close();
				pstmt.close();
			
				
			
			int deptNo = 290;
			String dName = "개발부";
		    int managerId = 200;
		    int locationId = 1700;
		    
		    // 1) 쿼리문 준비
			String query = "insert into departments values(" + 
			deptNo + ", '" + dName + "', " + managerId + ", " + locationId + ")";
			
//			System.out.println(query);
			
			// 2) Statement 객체
			
			// 3) select문을 제외한 DML문(insert, update, delete) 모두 executeUpdate() 메서드 호출해서 실행
			Statement stmt = null;
			try {
				stmt = con.createStatement();
				// 쿼리문을 실행 하고 영향받은 행의 갯수를 int 로 가져옴
				int result = stmt.executeUpdate(query); 
				
				if (result == 1) {
					System.out.println("저장 완료");
				}
				
				stmt.close();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
	private static void beforeInsert(Connection con) {
	    if (con != null) {
	        int deptNo = 310;
	        String dname = "자재부2";
	        int managerId = 145;
	        int locationId = 2500; // fk제약조건 때문에 같이 넣어줘야함...
	        
	        String query = "insert into departments values(?,?,?,?)";
	        PreparedStatement pstmt = null;
	        
	        try {
	            pstmt = con.prepareStatement(query);
	            pstmt.setInt(1, deptNo);
	            pstmt.setString(2, dname);
	            pstmt.setInt(3, managerId);
	            pstmt.setInt(4, locationId);
	            
	            int result = pstmt.executeUpdate();
	            
	            if (result == 1) {
	                System.out.println("저장 성공");
	            }
	            pstmt.close();
	            con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
