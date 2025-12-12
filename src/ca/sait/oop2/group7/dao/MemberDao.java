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
	
	public Member findById(int memberId) {
	    String sql = "SELECT id, name, email FROM members WHERE id = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, memberId);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                String email = rs.getString("email");
	                return new Member(id, name, email);
	            }
	        }

	    } catch (SQLException e) {
	        System.out.println("Error finding member by id:");
	        e.printStackTrace();
	    }

	    return null;
	}
	
	public boolean updateEmail(int memberId, String newEmail) {
	    String sql = "UPDATE members SET email = ? WHERE id = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, newEmail);
	        stmt.setInt(2, memberId);

	        return stmt.executeUpdate() > 0;

	    } catch (SQLException e) {
	        System.out.println("Error updating member:");
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean deleteById(int memberId) {
	    String sql = "DELETE FROM members WHERE id = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, memberId);
	        return stmt.executeUpdate() > 0;

	    } catch (SQLException e) {
	        System.out.println("Error deleting member:");
	        e.printStackTrace();
	        return false;
	    }
	}
	
}