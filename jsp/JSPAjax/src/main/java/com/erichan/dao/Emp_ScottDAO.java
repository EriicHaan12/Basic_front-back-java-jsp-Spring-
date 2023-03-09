package com.erichan.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.erichan.vo.Emps;

public interface Emp_ScottDAO {

	List<Emps> selectAllEmp()throws NamingException, SQLException;
	
	
}
