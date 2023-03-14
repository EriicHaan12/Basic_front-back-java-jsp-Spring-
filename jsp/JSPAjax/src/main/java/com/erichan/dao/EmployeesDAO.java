package com.erichan.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.erichan.vo.DepartmentVo;
import com.erichan.vo.Employees;
import com.erichan.vo.JobsVo;

public interface EmployeesDAO   {
//모든 직원 정보를 얻어오는 메서드
	public abstract List<Employees> selectAllEmp(String searchName, String sortBy) throws NamingException, SQLException;

	//모든 jobs 정보를 얻어오는 메서드
	//jobs는 dto를 해줄 필요가 없으니 Vo로 칭해주자
	public abstract List<JobsVo> selectAlljobs() throws NamingException, SQLException;

	
	//모든 departments 정보를 얻어오는 메서드
	List<DepartmentVo> selectAllDept() throws NamingException, SQLException;

	
	// 사원을 저장하는 메서드
	
	// 저장 프로시져에서 예외가 날 경우 'error'라는 문자열을 반환 시켜주기 위한 메서드
	String insertEmp(Employees empDto)throws NamingException, SQLException;
	
	
	//사원을 삭제하는 메서드(사번, 현재날짜)
	int deleteEmp(int empNo, Date quitDate) throws NamingException, SQLException;

//수정할 때 사번으로 해당 사번의 사원 정보를 얻어오는 메서드
Employees selectEmployeeByEmpNo(int empNo)  throws NamingException, SQLException;


// 사원 정보를 수정하는 메서드
int updateEmployee(Employees dto)throws NamingException, SQLException;


}




