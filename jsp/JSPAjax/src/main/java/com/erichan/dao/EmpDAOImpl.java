package com.erichan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.erichan.vo.Emps;

public class EmpDAOImpl implements Emp_ScottDAO {
	//싱글톤
	private static EmpDAOImpl instance;
	private EmpDAOImpl() {};
		
		private static EmpDAOImpl getInstance() {
			if(instance==null) {
				instance = new EmpDAOImpl();
			}
			return instance;
		}
		
		
		@Override
		public List<Emps> selectAllEmp() throws NamingException, SQLException {
		System.out.println(getClass().getName()+ "DAO단");
			// 데이터 담을 객체 생성
			List<Emps> lst = new ArrayList<>();
	
			Connection con = DBConnection.dbConnect();
		
			//DB로 부터 받은 데이터가 있다면
			if(con !=null) {
				dd
				//여기서 부터 다시	
				String query = "select e.empno,e.ename,e.job,e.mgr,e2.ename,e.hiredate,e.sal,e.comm,e.deptno, d.dname\r\n"
						+ "from emp e , emp e2, dept d\r\n"
						+ "where e.empno = e2.empno and d.deptno=e.deptno; ";
			
				PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(); 
			
			// 쿼리문을 실행했을 때 들어온 결과 값이 있다면
			while(rs.next()) {
				//만들어준 객체에 데이터 넣어주기
				lst.add(new Emps(rs.getInt("EMPNO"),
								rs.getString("ENAME"),
								rs.getString("JOB"),
								rs.getInt("MGR"),
								r
						
						))
			}
			
			}
			
			
			return lst;
		}

}
