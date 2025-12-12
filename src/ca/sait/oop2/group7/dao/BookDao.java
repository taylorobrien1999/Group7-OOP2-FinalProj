package ca.sait.oop2.group7.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ca.sait.oop2.group7.model.Book;

public class BookDao {

    public void insert(Book book) {
        String sql = "INSERT INTO books (title, author, isbn, publish_year) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getIsbn());
            stmt.setInt(4, book.getPublishYear());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();

        String sql = "SELECT id, title, author, isbn, publish_year FROM books";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String isbn = rs.getString("isbn");
                int year = rs.getInt("publish_year");

                books.add(new Book(id, title, year, author, isbn));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }
    
    public Book findById(int bookId) {
        String sql = "SELECT id, title, author, isbn, publish_year FROM books WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    String isbn = rs.getString("isbn");
                    int year = rs.getInt("publish_year");
                    return new Book(id, title, year, author, isbn);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error finding book by id:");
            e.printStackTrace();
        }

        return null;
    }
    
    public boolean update(Book book) {
        String sql = "UPDATE books SET title=?, author=?, isbn=?, publish_year=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getIsbn());
            stmt.setInt(4, book.getPublishYear());
            stmt.setInt(5, book.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating book:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteById(int bookId) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting book:");
            e.printStackTrace();
            return false;
        }
    }
    
}
