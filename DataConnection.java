package com.ait.inventoryManagement;

import java.sql.*;

public class DataConnection {

	
	
	private static final String url="jdbc:mysql://localhost:3306/inventory_db";
	private static final String username="root";
	private static final String password="Samba@300";
	
	
	private DataConnection() {}
		public static Connection getConnection() throws SQLException {
			return DriverManager.getConnection(url, username, password);
		}
	
	
	
	
	
	
	
	
//	public static Connection getConnection( String url ,String username, String password) throws SQLException {
//		Connection con= null;
//		
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection conn;
//			conn=DriverManager.getConnection(url, username, password);
//			System.out.println(conn);
//			if(conn !=null) {
//				System.out.println("Connection is done...!!");
//			}
//		} catch (ClassNotFoundException e) {
//			
//			e.printStackTrace();
//		}
//		return con;
//	}

	
	
}
