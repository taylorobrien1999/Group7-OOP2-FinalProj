package ca.sait.oop2.group7.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ca.sait.oop2.group7.model.Member;

public class MemberDao {
	
	public void insert (Member member) {
		String sql = "INSERT INTO members (name, email) VALUES (?, ?)";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1,  member.getName());
			stmt.setString(2, member.getEmail());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error inserting member:");
			e.printStackTrace();
		}
	}
	
	public List<Member> findAll() {
		
		List<Member> list = new ArrayList<>();
		
		String sql = "SELECT id, name, email FROM members";
		try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                String email = rs.getString("email");

	                list.add(new Member(id, name, email));
	            }

	        } catch (SQLException e) {
	            System.out.println("Error reading members:");
	            e.printStackTrace();
	        }

	        return list;
	    }
}