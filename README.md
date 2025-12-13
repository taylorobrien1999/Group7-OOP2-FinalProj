# Group7-OOP2-FinalProj


Final Project

Group 7:
- Taylor O'Brien [000968002]
- Alexander Pacunayen [000581893]
- Raphael Carriere [x]
- Alexander Dea [000966563]


Description

In this project we have created a console-based Library Management System built in Java with a MySQL Database.
This system supports managing of library members, books, and loans, including borrowing and returning books.
Validation rules such as preventing duplicate active loans, member deletion during an active loan, are in place.

Problem Statement

Libraries and book stores need a reliable service to assist in managing members, books, and loans without losing track of loan records.
When this is handled without validation, issues can occur such as the same book being loaned multiple times, members being deleted while still having an active loan, etc. This system addresses these problems by storing all library data in a connected database, books can only
be borrowed when available, loan records are preserved, and members cannot be removed while still having an active loan.


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
