package com.dbtproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kavya Kommineni
 * This class is used to connect to SQL DB using JDBC Connection
 *
 */
public class DBTProject {

	static final String DB_URL = "jdbc:sqlserver://localhost:1410;" +
			"databaseName=Online Shopping;integratedSecurity=true;";
	
	public static void main(String[] args) {
		
		DBTProject d = new DBTProject();
		List<String> list = d.dbconnection();
		for(String s : list)
		{
			System.out.println(s);
		}
	}

	public  List<String> dbconnection() {
		Connection conn = null;
		Statement stmt = null;
		String id, name, address;
		List<String> list = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL);

			System.out.println("Connected to database");
			
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM CUSTOMER";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("Data in Customer table before inserting");
			while (rs.next()) {
				id = rs.getString("Customer_Id");
				name = rs.getString("Customer_Name");
				address = rs.getString("Customer_Address");

				System.out.print("Customer Id: " + id);
				System.out.print(", Customer Name: " + name);
				System.out.print(", Customer Address: " + address);
				System.out.println();
			}
			rs.close();
			
			System.out.println("Inserting a row into Customer table..");
			sql = "INSERT INTO CUSTOMER VALUES('Deepu','New York,US','DeepuK')";
			stmt.executeUpdate(sql);

			sql = "SELECT * FROM CUSTOMER";
			rs = stmt.executeQuery(sql);
			System.out.println("Data in Customer table after inserting");
			while (rs.next()) {
				id = rs.getString("Customer_Id");
				name = rs.getString("Customer_Name");
				address = rs.getString("Customer_Address");

				System.out.print("Customer Id: " + id);
				System.out.print(", Customer Name: " + name);
				System.out.print(", Customer Address: " + address);
				System.out.println();
			}
			rs.close();
			
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		return list;
	}
}
