package ca.sait.oop2.group7.model;

import java.time.LocalDate;

public class Loan {
	private int loanId;
	private Member member;
	private LibraryItem item;
	private LocalDate loanDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	
	public Loan(int loanId, Member member, LibraryItem item, 
			LocalDate loanDate, LocalDate dueDate) {
		this.loanId = loanId;
		this.member = member;
		this.item = item;
		this.loanDate = loanDate;
		this.dueDate = dueDate;
	}
	
	public int getLoanId() {
		return loanId;
	}
	
	public Member getMember() {
		return member;
	}
	
	public LibraryItem getItem() {
		return item;
	}
	
	public LocalDate getLoanDate() {
		return loanDate;
	}
	
	public LocalDate getDueDate() {
		return dueDate;
	}
	
	public LocalDate getReturnDate() {
		return returnDate;
	}
	
	public void markReturned (LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	
	public boolean isReturned() {
		return returnDate != null;
	}
}
