package com.erichan.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.erichan.vo.DepartmentVo;
import com.erichan.vo.Employees;
import com.erichan.vo.JobsVo;

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

	@Override
	public List<JobsVo> selectAlljobs() throws NamingException, SQLException {
System.out.println(getClass().getName()+ "VO 단");
		
		List<JobsVo> lst = new ArrayList<>();
		
		Connection con = DBConnection.dbConnect();
		if(con !=null) {
			String query ="select * from jobs";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lst.add(new JobsVo(rs.getString("JOB_ID"),
							rs.getString("JOB_TITLE"),
							rs.getInt("MIN_SALARY"),
							rs.getInt("MAX_SALARY")));  
			}
			
			DBConnection.dbClose(rs, pstmt, con);
		}
		
		return lst;
	}

	@Override
	public List<DepartmentVo> selectAllDept() throws NamingException, SQLException {
			System.out.println(getClass().getName()+ "DAO 단");
		
		List<DepartmentVo> lst = new ArrayList<>();
		
		Connection con = DBConnection.dbConnect();
		if(con !=null) {
			String query ="select * from departments";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lst.add(new DepartmentVo(rs.getInt("DEPARTMENT_ID"),
						rs.getString("DEPARTMENT_NAME"),
						rs.getInt("MANAGER_ID"),
						rs.getInt("LOCATION_ID")
								));  
				}
			
			DBConnection.dbClose(rs, pstmt, con);
		}
		
		return lst;
	}

	@Override
	public String insertEmp(Employees empDto) throws NamingException, SQLException {
		String result =null;
		
		Connection con = DBConnection.dbConnect();
		if(con!=null) {
			
			// 메세지를 담아줄  String 타입 쿼리문 생성 // pstmt 생성할 때랑 비슷...
			String query = "{call PROC_SAVEEMP(?,?,?,?,?,?,?,?,?,?,?)}";
			//저장 프로시저 함수를 호출, query 컴파일
		CallableStatement cstmt = con.prepareCall(query);
		// 매개변수로 준 ? 세팅
		cstmt.setString(1, empDto.getFirst_name());
		cstmt.setString(2, empDto.getLast_name());
		cstmt.setString(3, empDto.getEmail());
		cstmt.setString(4, empDto.getPhone_number());
		cstmt.setDate(5, empDto.getHire_date());
		cstmt.setString(6, empDto.getJob_id());
		cstmt.setFloat(7, empDto.getSalary());
		cstmt.setFloat(8, empDto.getCommission_pct());
		cstmt.setInt(9, empDto.getManager_id());
		cstmt.setInt(10, empDto.getDepartment_id());
		// 마지막 매개 변수는 OUT 매개변수 이다. out 매개변수는 아래와 같이 작성한다.
		//out 타입을 매개변수로 주기 위해서는 int 타입을 넣어야 하는데 
		// java.sql.Types. 에서는 DB에서 쓰이는 모든 타입을 int로 반환 시킬수 있다.
		// out 매개변수라고 등록 시키기
		cstmt.registerOutParameter(11, java.sql.Types.VARCHAR);
		
		// 실행
		cstmt.executeUpdate();
		
		// out 매개변수에서 반환되는 값을 가져오기
		result = cstmt.getString(11);
		
		System.out.println(result);
		
		DBConnection.dbClose(cstmt, con);
		}
		
		return result;
	}
}
