package ca.sait.oop2.group7.service;

import java.time.LocalDate;
import java.util.List;

import ca.sait.oop2.group7.dao.BookDao;
import ca.sait.oop2.group7.dao.LoanDao;
import ca.sait.oop2.group7.dao.MemberDao;
import ca.sait.oop2.group7.model.Book;
import ca.sait.oop2.group7.model.Member;
import ca.sait.oop2.group7.exceptions.LibraryException;

public class LibraryService {
	
	private MemberDao memberDao;
	private BookDao bookDao;
	private LoanDao loanDao;
	
	public LibraryService() {
		this.memberDao = new MemberDao();
		this.bookDao = new BookDao();
		this.loanDao = new LoanDao();
	}
	
	
	// MEMBERS
	public void addMember(Member member) {
		memberDao.insert(member);
	}
	
	public List<Member> getAllMembers() {
		return memberDao.findAll();
	}
	
	public Member getMemberById(int id) {
		return memberDao.findById(id);
	}
	
	// BOOKS
	public void addBook(Book book) {
		bookDao.insert(book);
	}
	
	public List<Book> getAllBooks() {
		return bookDao.findAll();
	}
	
	public Book getBookById(int id) {
		return bookDao.findById(id);
	}
	
	
	// LOANS 
	public int borrowBook(int memberId, int bookId) throws LibraryException {
		Member m = memberDao.findById(memberId);
        if (m == null) {
            throw new LibraryException("Member not found: " + memberId);
        }

        Book b = bookDao.findById(bookId);
        if (b == null) {
            throw new LibraryException("Book not found: " + bookId);
        }

        Integer activeLoanId = loanDao.findActiveLoanIdForBook(bookId);
        if (activeLoanId != null) {
            throw new LibraryException("Book is already borrowed (loanId=" + activeLoanId + ")");
        }

        LocalDate loanDate = LocalDate.now();
        LocalDate dueDate = loanDate.plusDays(14);

        int loanId = loanDao.insertLoan(memberId, bookId, loanDate, dueDate);
        if (loanId == -1) {
            throw new LibraryException("Could not create loan.");
        }

        return loanId;
    }
	
	public void returnBook(int loanId) throws LibraryException {
		boolean ok = loanDao.markReturned(loanId, LocalDate.now());
		if (!ok) {
			throw new LibraryException("Loan not found or already returned: " + loanId);
		}
	}
	
	// display loans quickly
	public List<String> getAllLoansSimple() {
		return loanDao.findAllLoanRows();
	}
	
	// Update and delete requirement for C.R.U.D implementation
	
	public void updateMemberEmail(int memberId, String email) throws LibraryException {
		boolean ok = memberDao.updateEmail(memberId, email);
		if (!ok) throw new LibraryException("Member not found: " + memberId);
	}
	
	public void deleteMember(int memberId) throws LibraryException {

	    Member m = memberDao.findById(memberId);
	    if (m == null) {
	        throw new LibraryException("Member not found: " + memberId);
	    }

	 // If they still have a book out right now, it doesnt allow delete
	    if (loanDao.memberHasActiveLoans(memberId)) {
	        throw new LibraryException("Cannot delete member " + memberId + " because they have an active loan.");
	    }

	    boolean ok = memberDao.deleteById(memberId);
	    if (!ok) {
	        throw new LibraryException("Member not found: " + memberId);
	    }
	}


	public void deleteBook(int bookId) throws LibraryException {
	    boolean ok = bookDao.deleteById(bookId);
	    if (!ok) throw new LibraryException("Book not found: " + bookId);
	}


	public void updateBook(Book book) throws LibraryException {
	    boolean ok = bookDao.update(book);
	    if (!ok) throw new LibraryException("Book not found: " + book.getId());
	}
	
}
