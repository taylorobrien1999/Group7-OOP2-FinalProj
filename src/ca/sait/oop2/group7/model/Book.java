package ca.sait.oop2.group7.model;

public class Book extends LibraryItem {
	private String author;
	private String isbn;
	
	public Book (int id, String title, int publishYear, String author, String isbn) {
		super(id, title, publishYear);
		this.author = author;
		this.isbn = isbn;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	@Override
	public String getItemType() {
		return "Book";
	}
	
	@Override
	public void borrow(Member member) {
		// ** NEEDS MORE LOGIC ADDED **
		setAvailable(false);
	}
	
	@Override
	public void returnItem() {
		setAvailable(true);
	}

}
