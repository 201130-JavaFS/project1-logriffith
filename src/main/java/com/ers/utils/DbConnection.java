package com.ers.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	private static Connection connection;
	
	//constructor is private so the class cannot be instantiated
	public DbConnection() {
		
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DbPropertiesUtil.DRIVER);
		String url = DbPropertiesUtil.URL;
		String username = System.getenv("ERS_Username");
		String password = System.getenv("ERS_Password");
		connection = DriverManager.getConnection(url, username, password);
		return connection;
	}
	
//	public static void main(String[] args) {
//		try(Connection conn = DbConnection.getConnection()){
//			System.out.println("Connection Successful!");
//		} catch(SQLException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}
	
}
