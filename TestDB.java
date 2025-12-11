package Test;

import java.sql.*;

public class TestDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String url = "jdbc:mariadb://localhost:3306/testdb";
			String user = "root";
			String pass = "yournewpassword";

			Connection conn = DriverManager.getConnection(url, user, pass);
			System.out.println("Connected!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
