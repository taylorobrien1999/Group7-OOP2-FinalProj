package ca.sait.oop2.group7.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class LoanDao {

    public Integer findActiveLoanIdForBook(int bookId) {
        String sql = "SELECT id FROM loans WHERE book_id = ? AND return_date IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error checking active loan:");
            e.printStackTrace();
        }

        return null;
    }

    public int insertLoan(int memberId, int bookId, LocalDate loanDate, LocalDate dueDate) {
        String sql = "INSERT INTO loans (member_id, book_id, loan_date, due_date, return_date) VALUES (?, ?, ?, ?, NULL)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, memberId);
            stmt.setInt(2, bookId);
            stmt.setDate(3, Date.valueOf(loanDate));
            stmt.setDate(4, Date.valueOf(dueDate));

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error creating loan:");
            e.printStackTrace();
        }

        return -1;
    }

    public boolean markReturned(int loanId, LocalDate returnDate) {
        String sql = "UPDATE loans SET return_date = ? WHERE id = ? AND return_date IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(returnDate));
            stmt.setInt(2, loanId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error returning loan:");
            e.printStackTrace();
        }

        return false;
    }

    public List<String> findAllLoanRows() {
        List<String> rows = new ArrayList<>();
        String sql = "SELECT id, member_id, book_id, loan_date, due_date, return_date FROM loans ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                rows.add(
                    "Loan #" + rs.getInt("id") +
                    " member=" + rs.getInt("member_id") +
                    " book=" + rs.getInt("book_id") +
                    " loanDate=" + rs.getDate("loan_date") +
                    " due=" + rs.getDate("due_date") +
                    " returned=" + rs.getDate("return_date")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error reading loans:");
            e.printStackTrace();
        }

        return rows;
    }
    
    // crucial feature as a member cannot be deleted 
    // while having loan history
    

    public boolean memberHasAnyLoanHistory(int memberId) {
        String sql = "SELECT COUNT(*) FROM loans WHERE member_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, memberId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error checking member loan history:");
            e.printStackTrace();
            return true;
        }
    }
        
    public boolean memberHasActiveLoans(int memberId) {
    	String sql = "SELECT COUNT(*) FROM loans WHERE member_id = ? AND return_date IS NULL";
 
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, memberId);

                try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next() && rs.getInt(1) > 0;
                }

            } catch (SQLException e) {
                System.out.println("Error checking member active loans:");
                e.printStackTrace();
                return true;
            }
    }
}


