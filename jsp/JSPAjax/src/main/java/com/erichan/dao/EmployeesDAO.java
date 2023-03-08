package com.erichan.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.erichan.vo.DepartmentVo;
import com.erichan.vo.Employees;
import com.erichan.vo.JobsVo;

public interface EmployeesDAO   {
//모든 직원 정보를 얻어오는 메서드
	public abstract List<Employees> selectAllEmp() throws NamingException, SQLException;

	//모든 jobs 정보를 얻어오는 메서드
	//jobs는 dto를 해줄 필요가 없으니 Vo로 칭해주자
	public abstract List<JobsVo> selectAlljobs() throws NamingException, SQLException;

	
	//모든 departments 정보를 얻어오는 메서드
	List<DepartmentVo> selectAllDept() throws NamingException, SQLException;
	
}

