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
}
