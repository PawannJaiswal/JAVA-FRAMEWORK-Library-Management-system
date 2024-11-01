package com.library.app;

import com.library.dao.LibraryBookDAO;
import com.library.dao.MemberDAO;
import com.library.model.LibraryBook;
import com.library.model.Member;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    private static Scanner scanner = new Scanner(System.in);
    private static LibraryBookDAO bookDAO = new LibraryBookDAO();
    private static MemberDAO memberDAO = new MemberDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Library Management Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. View Book");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. View All Books");
            System.out.println("6. Add Member");
            System.out.println("7. View Member");
            System.out.println("8. Update Member");
            System.out.println("9. Delete Member");
            System.out.println("10. View All Members");
            System.out.println("11. Exit");

            int choice = getIntInput("Choose an option: ");
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBook();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    viewAllBooks();
                    break;
                case 6:
                    addMember();
                    break;
                case 7:
                    viewMember();
                    break;
                case 8:
                    updateMember();
                    break;
                case 9:
                    deleteMember();
                    break;
                case 10:
                    viewAllMembers();
                    break;
                case 11:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Book Methods
    private static void addBook() {
        System.out.println("Enter Title:");
        String title = scanner.nextLine();

        System.out.println("Enter Author:");
        String author = scanner.nextLine();

        System.out.println("Enter Genre:");
        String genre = scanner.nextLine();

        int year = getIntInput("Enter Year:");

        LibraryBook book = new LibraryBook();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setYear(year);

        bookDAO.addBook(book);
        System.out.println("Book added successfully!");
    }

    private static void viewBook() {
        int id = getIntInput("Enter Book ID to view: ");
        LibraryBook book = bookDAO.getBook(id);

        if (book != null) {
            System.out.println("Book ID: " + book.getId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Genre: " + book.getGenre());
            System.out.println("Year: " + book.getYear());
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void updateBook() {
        int id = getIntInput("Enter Book ID to update: ");
        LibraryBook book = bookDAO.getBook(id);

        if (book != null) {
            System.out.println("Enter new Title (leave empty to keep current):");
            String title = scanner.nextLine();
            if (!title.isEmpty()) {
                book.setTitle(title);
            }

            System.out.println("Enter new Author (leave empty to keep current):");
            String author = scanner.nextLine();
            if (!author.isEmpty()) {
                book.setAuthor(author);
            }

            System.out.println("Enter new Genre (leave empty to keep current):");
            String genre = scanner.nextLine();
            if (!genre.isEmpty()) {
                book.setGenre(genre);
            }

            int year = getIntInput("Enter new Year (leave empty to keep current):");
            if (year > 0) {
                book.setYear(year);
            } else {
                System.out.println("Year must be positive.");
                return;
            }

            bookDAO.updateBook(book);
            System.out.println("Book updated successfully!");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void deleteBook() {
        int id = getIntInput("Enter Book ID to delete: ");
        LibraryBook book = bookDAO.getBook(id);

        if (book != null) {
            bookDAO.deleteBook(id);
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void viewAllBooks() {
        List<LibraryBook> books = bookDAO.getAllBooks();

        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (LibraryBook book : books) {
                System.out.println("Book ID: " + book.getId());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Genre: " + book.getGenre());
                System.out.println("Year: " + book.getYear());
                System.out.println("----------------------------");
            }
        }
    }

    // Member Methods
    private static void addMember() {
        System.out.println("Enter Name:");
        String name = scanner.nextLine();

        System.out.println("Enter Email:");
        String email = scanner.nextLine();

        System.out.println("Enter Phone:");
        String phone = scanner.nextLine();

        System.out.println("Enter Membership Date (yyyy-MM-dd):");
        String dateStr = scanner.nextLine();

        try {
            java.util.Date membershipDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            Member member = new Member();
            member.setName(name);
            member.setEmail(email);
            member.setPhone(phone);
            member.setMembershipDate(membershipDate);

            memberDAO.addMember(member);
            System.out.println("Member added successfully!");
        } catch (Exception e) {
            System.out.println("Error parsing date.");
        }
    }

    private static void viewMember() {
        int id = getIntInput("Enter Member ID to view: ");
        Member member = memberDAO.getMemberById(id);

        if (member != null) {
            System.out.println("Member ID: " + member.getId());
            System.out.println("Name: " + member.getName());
            System.out.println("Email: " + member.getEmail());
            System.out.println("Phone: " + member.getPhone());
            System.out.println("Membership Date: " + member.getMembershipDate());
        } else {
            System.out.println("Member not found.");
        }
    }

    private static void updateMember() {
        int id = getIntInput("Enter Member ID to update: ");
        Member member = memberDAO.getMemberById(id);

        if (member != null) {
            System.out.println("Enter new Name (leave empty to keep current):");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                member.setName(name);
            }

            System.out.println("Enter new Email (leave empty to keep current):");
            String email = scanner.nextLine();
            if (!email.isEmpty()) {
                member.setEmail(email);
            }

            System.out.println("Enter new Phone (leave empty to keep current):");
            String phone = scanner.nextLine();
            if (!phone.isEmpty()) {
                member.setPhone(phone);
            }

            System.out.println("Enter new Membership Date (yyyy-MM-dd, leave empty to keep current):");
            String dateStr = scanner.nextLine();
            if (!dateStr.isEmpty()) {
                try {
                    java.util.Date membershipDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                    member.setMembershipDate(membershipDate);
                } catch (Exception e) {
                    System.out.println("Error parsing date.");
                    return;
                }
            }

            memberDAO.updateMember(member);
            System.out.println("Member updated successfully!");
        } else {
            System.out.println("Member not found.");
        }
    }

    private static void deleteMember() {
        int id = getIntInput("Enter Member ID to delete: ");
        Member member = memberDAO.getMemberById(id);

        if (member != null) {
            memberDAO.deleteMember(id);
            System.out.println("Member deleted successfully!");
        } else {
            System.out.println("Member not found.");
        }
    }

    private static void viewAllMembers() {
        List<Member> members = memberDAO.getAllMembers();

        if (members.isEmpty()) {
            System.out.println("No members found.");
        } else {
            for (Member member : members) {
                System.out.println("Member ID: " + member.getId());
                System.out.println("Name: " + member.getName());
                System.out.println("Email: " + member.getEmail());
                System.out.println("Phone: " + member.getPhone());
                System.out.println("Membership Date: " + member.getMembershipDate());
                System.out.println("----------------------------");
            }
        }
    }

    private static int getIntInput(String prompt) {
        int input = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt);
            try {
                input = scanner.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear invalid input
            }
        }
        scanner.nextLine(); // Clear the newline character
        return input;
    }
}
