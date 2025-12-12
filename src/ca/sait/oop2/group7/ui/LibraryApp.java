package ca.sait.oop2.group7.ui;

import java.util.List;
import java.util.Scanner;

import ca.sait.oop2.group7.model.Member;
import ca.sait.oop2.group7.service.LibraryService;


public class LibraryApp {

	public static void main(String[] args) {
		System.out.println("Library Management System starting...");
		
		LibraryService service = new LibraryService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            System.out.println("=== Library Menu ===");
            System.out.println("1. Add Member");
            System.out.println("2. List Members");
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
                    System.out.println("=== Members ===");
                    if (all.isEmpty()) {
                        System.out.println("No members found.");
                    } else {
                        for (Member mem : all) {
                            System.out.println(mem);
                        }
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
