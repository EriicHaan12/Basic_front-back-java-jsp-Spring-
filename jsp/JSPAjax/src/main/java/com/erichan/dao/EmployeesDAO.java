package com.erichan.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.erichan.vo.Employees;

public interface EmployeesDAO   {
//모든 직원 정보를 얻어오는 메서드
	public abstract List<Employees> selectAllEmp() throws NamingException, SQLException;
}
