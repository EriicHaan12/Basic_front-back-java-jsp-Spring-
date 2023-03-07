package com.erichan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.erichan.vo.Employees;

public class EmployeesDAOImpl implements EmployeesDAO {
//싱글톤 
	private static EmployeesDAOImpl instance;
	
	private EmployeesDAOImpl() {};
	
		public static EmployeesDAOImpl getInstance() {
			if(instance==null) {
				instance =new EmployeesDAOImpl();
			
			}
			return instance;
		}
		
	
	
	
	@Override
	public List<Employees> selectAllEmp() throws NamingException, SQLException {
		System.out.println(getClass().getName()+ "DAO 단");
		
		List<Employees> lst = new ArrayList<>();
		
		Connection con = DBConnection.dbConnect();
		if(con !=null) {
			String query ="select e.*, d.department_name "
					+ "from employees e inner join departments d "
					+ "on e.department_id = d.department_id";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lst.add(new Employees(rs.getInt("EMPLOYEE_ID"), 
								rs.getString("FIRST_NAME"), 
								rs.getString("LAST_NAME"),
								rs.getString("EMAIL"),
								rs.getString("PHONE_NUMBER"),
								rs.getDate("HIRE_DATE"),
								rs.getString("JOB_ID"),
								rs.getFloat("SALARY"),
								rs.getFloat("COMMISSION_PCT"),
								rs.getInt("MANAGER_ID"),
								rs.getInt("DEPARTMENT_ID"),
								rs.getString("DEPARTMENT_NAME")));  
			}
			
			DBConnection.dbClose(rs, pstmt, con);
		}
		
		return lst;
	}

}
