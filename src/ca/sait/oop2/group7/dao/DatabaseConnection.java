package ca.sait.oop2.group7.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	// TODO: change these fields to url, user and pass when DB is ready
	private static final String URL = "jdbc:mariadb://localhost:3306/testdb";
	private static final String USER = "libraryuser";
	private static final String PASSWORD = "yournewpassword";
			
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
