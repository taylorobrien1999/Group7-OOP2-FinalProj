package ca.sait.oop2.group7.service;

import java.util.List;
import ca.sait.oop2.group7.model.Book;
import ca.sait.oop2.group7.model.Member;
import ca.sait.oop2.group7.model.Loan;
import ca.sait.oop2.group7.exceptions.LibraryException;

public class LibraryService {
	
	// to do: add MemberDao, BookDao, LoanDao here
	
	public LibraryService() {
		//to do
	}
	
	public void addMember(Member member) {
		//to do 
	}
	
	public List<Member> getAllMembers() {
		// todo
		return null;
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
