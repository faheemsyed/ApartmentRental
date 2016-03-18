package database;

import java.sql.*;
import java.util.*;

public class DBConnector {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		getConnection();
	}
	
	public static Connection getConnection() throws Exception{
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/apartments1";
			String username = "root";
			String password = null;
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			//System.out.println("connected");
			return conn;
		} 
		
		catch(Exception e) {
			System.out.println(e);
			}
		
		return null;
	}
	
}
