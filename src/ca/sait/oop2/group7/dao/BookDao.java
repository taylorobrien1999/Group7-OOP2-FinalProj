package ca.sait.oop2.group7.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import ca.sait.oop2.group7.model.Book;

public class BookDao {
	
	public void insert (Book book) {
		// jdbc INSERT into books table
		
		String sql = "INSERT INTO books (title, author, isbn, available) VALUES (?,?,?,?)";
		
		try (Connection conn = DatabaseConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql)) {
			
				stmt.setString(1, book.getTitle());
				stmt.setString(2, book.getAuthor());
				stmt.setString(3, book.getIsbn());
				stmt.setBoolean(4, book.isAvailable());
				
				
				stmt.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
			
		}
	}
	
	public List<Book> findAll() {
		// SELECT * FROM books
		return null;
	}
}
