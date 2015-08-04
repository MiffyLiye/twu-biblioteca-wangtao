package com.twu.biblioteca;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public Driver(WelcomeMessage welcomeMessage, BookService bookService) {
        this(welcomeMessage);
        this.bookService = bookService;
        NewLine = System.getProperty("line.separator");
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        path.add("welcome");
        while (true) {
            if (path.size() == 0) {
                break;
            }
            else if (path.size() ==1 && path.get(0).equals("welcome")) {
                welcomeView();
            }
            else if (path.get(1).equals("list books")) {
                BooksController();
            }
            else if (path.get(1).equals("checkout books")) {
                BooksCheckoutController();
            }
            else if (path.get(1).equals("return books")) {
                BooksReturnController();
            }
        }
    }

    private WelcomeMessage welcomeMessage;
    private BookService bookService;
    private Scanner scanner;
    private String NewLine;

    private List<String> path;
    private Integer book_details_id;

    private Driver() {
        path = new LinkedList<String>();
        scanner = new Scanner(System.in);
    }

    private Driver(WelcomeMessage welcomeMessage) {
        this();
        this.welcomeMessage = welcomeMessage;
    }

    private void welcomeView() {
        System.out.print(welcomeMessage.get());
        System.out.print("Press Q to quit. Press 1 to list books. Press 2 to checkout books. Press 3 to return books." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("q")) {
                path.remove(0);
                break;
            }
            else if (cmd.toLowerCase().equals("1")) {
                path.add("list books");
                break;
            }
            else if (cmd.toLowerCase().equals("2")) {
                path.add("checkout books");
                break;
            }
            else if (cmd.toLowerCase().equals("3")) {
                path.add("return books");
                break;
            }
            else {
                System.out.print("Select a valid option!" + NewLine);
            }
        }

    }

    private void BooksController() {
        if (path.size() == 2) {
            booksListView();
        }
        else if (path.size() >= 3 && path.get(2).equals("book details")) {
            bookDetailsView();
        }
    }

    private void booksListView() {
        System.out.print(bookService.getSummaryList());
        System.out.print("Press B to go back. Input book ID to see details." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                try {
                    book_details_id = Integer.parseInt(cmd);
                    path.add("book details");
                    break;
                }
                catch (NumberFormatException ex) {
                    System.out.print("Select a valid option!" + NewLine);
                }
            }
        }
    }

    private void bookDetailsView() {
        System.out.print(bookService.getBookDetailsById(book_details_id));
        System.out.print("Press B to go back." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                System.out.print("Select a valid option!" + NewLine);
            }
        }
    }

    private void BooksCheckoutController() {
        if (path.size() == 2) {
            bookCheckoutView();
        }
        else if (path.size() == 3 && path.get(2).equals("successful")) {
            bookCheckoutSuccessfulView();
        }
        else if (path.size() == 3 && path.get(2).equals("unsuccessful")) {
            bookCheckoutUnsuccessfulView();
        }
    }

    private void bookCheckoutView() {
        System.out.print("Checkout Books" + NewLine);
        System.out.print("Press B to go back. Input the book ID to checkout." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                try {
                    Integer id = Integer.parseInt(cmd);
                    if (bookService.checkout(id)) {
                        path.add("successful");
                    }
                    else {
                        path.add("unsuccessful");
                    }
                    break;
                }
                catch (NumberFormatException ex) {
                    System.out.print("Select a valid option!" + NewLine);
                }
            }
        }
    }

    private void bookCheckoutSuccessfulView() {
        System.out.print("Thank you! Enjoy the book." + NewLine);
        System.out.print("Press B to go back." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                System.out.print("Select a valid option!" + NewLine);
            }
        }
    }

    private void bookCheckoutUnsuccessfulView() {
        System.out.print("That book is not available." + NewLine);
        System.out.print("Press B to go back." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                System.out.print("Select a valid option!" + NewLine);
            }
        }
    }

    private void BooksReturnController() {
        if (path.size() == 2) {
            bookReturnView();
        }
        else if (path.size() == 3 && path.get(2).equals("successful")) {
            bookReturnSuccessfulView();
        }
        else if (path.size() == 3 && path.get(2).equals("unsuccessful")) {
            bookReturnUnsuccessfulView();
        }
    }

    private void bookReturnView() {
        System.out.print("Return Books" + NewLine);
        System.out.print("Press B to go back. Input the book ID to return." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                try {
                    Integer id = Integer.parseInt(cmd);
                    if (bookService.checkin(id)) {
                        path.add("successful");
                    }
                    else {
                        path.add("unsuccessful");
                    }
                    break;
                }
                catch (NumberFormatException ex) {
                    System.out.print("Select a valid option!" + NewLine);
                }
            }
        }
    }

    private void bookReturnSuccessfulView() {
        System.out.print("Thank you for returning the book." + NewLine);
        System.out.print("Press B to go back." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                System.out.print("Select a valid option!" + NewLine);
            }
        }
    }

    private void bookReturnUnsuccessfulView() {
        System.out.print("That is not a valid book to return." + NewLine);
        System.out.print("Press B to go back." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                System.out.print("Select a valid option!" + NewLine);
            }
        }
    }
}
