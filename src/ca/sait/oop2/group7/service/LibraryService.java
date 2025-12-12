package ca.sait.oop2.group7.service;

import java.util.List;
import ca.sait.oop2.group7.dao.MemberDao;
import ca.sait.oop2.group7.model.Book;
import ca.sait.oop2.group7.model.Member;
import ca.sait.oop2.group7.model.Loan;
import ca.sait.oop2.group7.exceptions.LibraryException;

public class LibraryService {
	
	private MemberDao memberDao;
	//TODO: later ***
	
	public LibraryService() {
		this.memberDao = new MemberDao();
	}
	
	public void addMember(Member member) {
		memberDao.insert(member);
	}
	
	public List<Member> getAllMembers() {
		return memberDao.findAll();
	}
	
	public void addBook(Book book) {
		//todo -- save book into database
	}
	
	public List<Book> getAllBooks() {
		//todo --  load books from database
		return null;
	}
	
	public Loan borrowBook(int memberId, int bookId) throws LibraryException {
		//todo --use database to check member + book and create a loan
		return null;
	}
	
	public void returnBook(int loanId) throws LibraryException {
		//todo --  use LoanDAO to mark loan as returned
	}

}
