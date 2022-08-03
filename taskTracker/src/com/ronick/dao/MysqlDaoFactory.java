package com.ronick.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDaoFactory {
	private String url;
	private String user;
	private String password;
	
	
	private MysqlDaoFactory(String url, String user, String password) {
		super();
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	public static MysqlDaoFactory getInstance( ) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			 
			e.printStackTrace();
		}
		
		return new MysqlDaoFactory("jdbc:mysql://localhost:3306/javaee","root","");
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user,password);
	}
	
	public TaskInterfaceDao getTaskDao() {
		return new TaskDao(this);
	}

}
