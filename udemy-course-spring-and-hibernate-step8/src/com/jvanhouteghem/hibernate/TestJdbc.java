package com.jvanhouteghem.hibernate;


import java.sql.Connection;
import java.sql.DriverManager;

// Example without Hibernate configuration file to test connection
public class TestJdbc {

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/hibernatemkyong?useSSL=false";
		String user = "root";
		String pass = "";
		
		try {
			System.out.println("Connecting to database " + jdbcUrl);
			
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			
			System.out.println("Connection successfull !!!");
			
		} catch (Exception exc){
			exc.printStackTrace();
		}
		

	}

}
