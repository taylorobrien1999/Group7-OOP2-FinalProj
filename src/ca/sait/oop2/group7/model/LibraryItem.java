package ca.sait.oop2.group7.model;

public abstract class LibraryItem implements Borrowable {
	private int id;
	private String title;
	private int publishYear;
	private boolean available = true;
	
	public LibraryItem(int id, String title, int publishYear) {
		this.id = id;
		this.title = title;
		this.publishYear = publishYear;
	}
	
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	
	public int getPublishYear() {
		return publishYear;
	}
	
	@Override
	public boolean isAvailable() {
		return available;
	}
	
	protected void setAvailable(boolean available) {
		this.available = available;
	}
	
	public abstract String getItemType();
	
	@Override
	public String toString() {
		return "[" + getItemType() + "]" + title + "(" + publishYear + ")";
	}
}
