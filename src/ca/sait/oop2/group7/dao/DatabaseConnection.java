package ca.sait.oop2.group7.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/group7_librarydb";
	private static final String USER = "root";
	private static final String PASSWORD = "Group7OOP!?!?";
			
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

}