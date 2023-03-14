package com.mini.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {
	public static Connection dbConnect() throws NamingException, SQLException {
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env"); 
		//디렉토리 서비스에 의해 context.xml파일의 객체를 얻어와 (JNDI에 의해)
		DataSource ds = (DataSource) envContext.lookup("jdbc/mySQL");  // 이부분도 context와 동일하게 바꿔준다.
		// 아래 이름의 태그를 찾아 그 부분으로 부터 Connection 객체를 얻어온다는 의미
		Connection conn = ds.getConnection();
		System.out.println(conn.toString());
		return conn;
	}
	
	public static void dbClose(ResultSet rs, Statement pstmt, Connection con) throws SQLException {
		rs.close();
		pstmt.close();
		con.close();
	}
	public static void dbClose(Statement pstmt, Connection con) throws SQLException {
	
		pstmt.close();
		con.close();
	}
}
