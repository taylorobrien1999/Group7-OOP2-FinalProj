package ca.sait.oop2.group7.ui;

import java.util.List;
import java.util.Scanner;

import ca.sait.oop2.group7.model.Member;
import ca.sait.oop2.group7.model.Book;
import ca.sait.oop2.group7.exceptions.LibraryException;
import ca.sait.oop2.group7.service.LibraryService;

public class LibraryApp {

	public static void main(String[] args) {
		System.out.println("Library Management System starting...");
		
		LibraryService service = new LibraryService();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            System.out.println("\n==== Library Menu ====");
            System.out.println("1. Add Member");
            System.out.println("2. List Members");
            System.out.println("3. Add Book");
            System.out.println("4. List Books");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. List Loans");
            System.out.println("8. Update Member Email");
            System.out.println("9. Delete Member");
            System.out.println("10. Update Book");
            System.out.println("11. Delete Book");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
            	case "1":
                System.out.print("Member name: ");
                String name = scanner.nextLine();

                System.out.print("Email: ");
                String email = scanner.nextLine();

                Member m = new Member(0, name, email);
                service.addMember(m);
                System.out.println("Member added.");
                break;
	            
            	case "2":
            		List<Member> all = service.getAllMembers();
                    System.out.println("\n=== Members ===");
                    if (all.isEmpty()) {
                        System.out.println("No members found.");
                    } else {
                        for (Member mem : all) {
                            System.out.println(mem);
                        }
                    }
                    break;
                    
                    
            	case "3":
            	    System.out.print("Title: ");
            	    String title = scanner.nextLine();

            	    System.out.print("Author: ");
            	    String author = scanner.nextLine();

            	    System.out.print("ISBN (optional): ");
            	    String isbn = scanner.nextLine();

            	    System.out.print("Publish year: ");
            	    int year = Integer.parseInt(scanner.nextLine());

            	    Book b = new Book(0, title, year, author, isbn);
            	    service.addBook(b);
            	    System.out.println("Book added.");
            	    break;

            	case "4":
            	    List<Book> books = service.getAllBooks();
            	    System.out.println("\n=== Books ===");
            	    if (books.isEmpty()) {
            	        System.out.println("No books found.");
            	    } else {
            	        for (Book book : books) {
            	            System.out.println(book.getId() + " : " + book.getTitle() + " by " + book.getAuthor()
            	                    + " (" + book.getPublishYear() + ") isbn=" + book.getIsbn());
            	        }
            	    }
            	    break;

            	case "5":
            	    System.out.print("Member id: ");
            	    int memberId = Integer.parseInt(scanner.nextLine());

            	    System.out.print("Book id: ");
            	    int bookId = Integer.parseInt(scanner.nextLine());

            	    try {
            	        int loanId = service.borrowBook(memberId, bookId);
            	        System.out.println("Borrowed! Loan id = " + loanId);
            	    } catch (LibraryException e) {
            	        System.out.println("Borrow failed: " + e.getMessage());
            	    }
            	    break;

            	case "6":
            	    System.out.print("Loan Id: ");
            	    int loanIdToReturn = Integer.parseInt(scanner.nextLine());

            	    try {
            	        service.returnBook(loanIdToReturn);
            	        System.out.println("Returned.");
            	    } catch (LibraryException e) {
            	        System.out.println("Return failed: " + e.getMessage());
            	    }
            	    break;

            	case "7":
            	    List<String> loans = service.getAllLoansSimple();
            	    System.out.println("\n=== Loans ===");
            	    if (loans.isEmpty()) {
            	        System.out.println("No loans found.");
            	    } else {
            	        for (String row : loans) {
            	            System.out.println(row);
            	        }
            	    }
            	    break; 
            	    
            	case "8":
            	    System.out.print("Member id: ");
            	    int mid = Integer.parseInt(scanner.nextLine());
            	    System.out.print("New email: ");
            	    String newEmail = scanner.nextLine();
            	    try {
            	        service.updateMemberEmail(mid, newEmail);
            	        System.out.println("Member updated.");
            	    } catch (LibraryException e) {
            	        System.out.println("Update failed: " + e.getMessage());
            	    }
            	    break;

            	case "9":
            	    System.out.print("Member id: ");
            	    int midDel = Integer.parseInt(scanner.nextLine());
            	    try {
            	        service.deleteMember(midDel);
            	        System.out.println("Member deleted.");
            	    } catch (LibraryException e) {
            	        System.out.println("Delete failed: " + e.getMessage());
            	    }
            	    break;

            	case "10":
            	    System.out.print("Book id: ");
            	    int bid = Integer.parseInt(scanner.nextLine());
            	    System.out.print("Title: ");
            	    String nt = scanner.nextLine();
            	    System.out.print("Author: ");
            	    String na = scanner.nextLine();
            	    System.out.print("ISBN: ");
            	    String ni = scanner.nextLine();
            	    System.out.print("Publish year: ");
            	    int ny = Integer.parseInt(scanner.nextLine());

            	    try {
            	        service.updateBook(new Book(bid, nt, ny, na, ni));
            	        System.out.println("Book updated.");
            	    } catch (LibraryException e) {
            	        System.out.println("Update failed: " + e.getMessage());
            	    }
            	    break;

            	case "11":
            	    System.out.print("Book id: ");
            	    int bidDel = Integer.parseInt(scanner.nextLine());
            	    try {
            	        service.deleteBook(bidDel);
            	        System.out.println("Book deleted.");
            	    } catch (LibraryException e) {
            	        System.out.println("Delete failed: " + e.getMessage());
            	    }
            	    break;
                    
            	case "0":
            		running = false;
            		break;
            		
            	
            	default:
            		System.out.println("Invalid option.");
            }
        }
        

        scanner.close();
        System.out.println("Exiting...");
	}
}


