import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.sql.*;
import java.util.Scanner;
public class Admin extends User{
    private UserDataAO userDataAO = new UserDataAO();
    private BookDataAO bookDataAO = new BookDataAO();
    Admin(int userId, String username, String password, String email, String userType) {
        super(userId, username, password, email, userType);
    }

    public void accessLibrarySystem() throws SQLException {
        System.out.println("1.Add book 2.Remove book 3.Manage user 4.Update book 5.View all users: ");

        {
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            switch(option)
            {
                case 1 -> addBook();
                case 2 -> removeBook();
                case 3 -> manageMember();

            }
        }
    }

    public void addBook() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        System.out.println("Enter author: ");
        String author = scanner.nextLine();
        System.out.println("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.println("Enter available copies: ");
        int availableCopies = scanner.nextInt();
        bookDataAO.addBook(title, author, isbn, availableCopies);
    }

    public void removeBook() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book id: ");
        int bookId = scanner.nextInt();
        bookDataAO.deleteBook(bookId);
    }

    public void manageMember() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user id: ");
        int userId = scanner.nextInt();

        User user = userDataAO.getUserById(userId);
        if(user == null)
        {
            System.out.println("couldn't find the user");
        }

        else if (user.getUserType().equalsIgnoreCase("Admin")) {
            System.out.println("Cannot display an admin user!");
        }

        else{
            System.out.println("User id: "+ user.getUserId() +"\nUsername: "+ user.getUsername()+"\nPassword: " + user.getPassword() + "\nEmail: "+ user.getEmail());

            System.out.println("Enter what you want to change 1.Username 2.Password 3.Email: ");
            int option = scanner.nextInt();
            switch (option)
            {
                case 1 -> {
                    System.out.println("Enter new username: ");
                    String newUsername = scanner.next();
                    userDataAO.updateUsername(user.getUserId(),user.getUsername(),newUsername);
                }
                case 2 -> {
                    System.out.println("Enter new password: ");
                    String newPassword = scanner.next();
                    userDataAO.updatePassword(user.getUserId(),user.getPassword(),newPassword);
                }
                case 3 -> {
                    System.out.println("Enter new email: ");
                    String newEmail = scanner.next();
                    userDataAO.updateEmail(user.getUserId(),user.getEmail(),newEmail);
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    public void updateBook() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book id: ");
        int bookId = scanner.nextInt();
        Book book = bookDataAO.getBookById(bookId);
        if(book == null)
        {
            System.out.println("Couldn't find the book provided!");
        }
        else
        {
            System.out.println("Book id: "+book.getBookId()+"\nTitle: "+book.getTitle()+"\nAuthor: "+book.getAuthor()+"ISBN: "+book.getIsbn()+"\nAvailable copies: "+book.getAvailableBooks());
            System.out.println("\n1.Enter what you want to change 1.Title 2.Author 3.ISBN 4.Available copies: ");
            int option = scanner.nextInt();

            switch (option)
            {
                case 1 ->
                {
                    System.out.println("Enter new title: ");
                    String newTitle = scanner.next();
                    bookDataAO.updateBookTitle(book.getBookId(),newTitle);
                }

                case 2 ->
                {
                    System.out.println("Enter new author: ");
                    String newAuthor = scanner.next();
                    bookDataAO.updateBookAuthor(book.getBookId(),newAuthor);
                }
            }
        }
    }

    public void viewAllUsers() {

    }

}
