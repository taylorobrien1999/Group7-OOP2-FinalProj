package ca.sait.oop2.group7.model;

public interface Borrowable {
	void borrow(Member member);
	void returnItem();
	boolean isAvailable();
}
