# Group7-OOP2-FinalProj


CRUD = DONE

DATABASE CONNECTION = DONE

EXCEPTION HANDLING = DONE

CLI MENU = DONE


// an early first draft of readMe 


Final Project

Group 7:
- Taylor O'Brien [000968002]
- Alexander Pacunayen [x]
- Raphael Carriere [x]
- Alexander Dea [x]


Description

In this project we have created a console-based Library Management System built in Java with a MYSQL Database.
This system supports managing of library members, books, and loans, including borrowing and returning books.
Validation rules such as preventing duplicate active loans, member deletion during an active loan, are in place.

Features

- CRUD operations for Members and Books
- Borrow / Return books
- Database via JDBC
- Input validation and exception handling
- CLI user interface menu

How to Run

1. Create database and tables
2. Update DatabaseConnection login/password
3. Run LibraryApp.java

Database

The following tables are preserved in our database;
members (id, name, email),
books (id, title, author, isbn, publish_year),
and loans (id, member_id, book_id, loan_date, due_date, return_date).


Testing

The application was manually tested individually by each group member. We all tested the CLI menu to ensure proper functionality for features such as application startup/closure, adding, listing, updating, and deleting members and books, as well as borrowing/returning books. We each verified that a book cannot be borrowed if already on a loan, and that no members can be deleted while having an active loan.

Notes


- Members cannot be deleted if they have active loans
- No duplicate loans for the same book
