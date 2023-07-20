package com.connection.mysql;

import java.sql.Connection;

import com.Bean.model.RegisterationFormBean;

public interface Connectionn{
	
	public Connection getConnection();
	 public  void generate_Pdf();
	 public void update(RegisterationFormBean scBean);
	

}
