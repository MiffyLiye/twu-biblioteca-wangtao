package com.twu.biblioteca;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    private boolean debug = false;
    private WelcomeMessage welcomeMessage;
    private BookService bookService;
    private Scanner scanner;

    private List<String> path;
    private Integer book_details_id;

    public Driver() {
        path = new LinkedList<String>();
        scanner = new Scanner(System.in);
    }

    public Driver(WelcomeMessage welcomeMessage) {
        this();
        this.welcomeMessage = welcomeMessage;
    }

    public Driver(WelcomeMessage welcomeMessage, BookService bookService) {
        this(welcomeMessage);
        this.bookService = bookService;
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
            if (debug) {
                for (String s : path) {
                    System.out.print(s + "/");
                    System.out.println();
                }
            }
            if (path.size() ==1 && path.get(0).equals("welcome")) {
                welcomeView();
            }
            else if (path.size() > 1 && path.get(1).equals("list books")) {
                BooksController();
            }
            else if (path.size() == 0) {
                break;
            }
        }
    }

    private void welcomeView() {
        System.out.print(welcomeMessage.get());
        System.out.print("Press Q to quit. Press 1 to list books.\r\n");
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
            else {
                System.out.print("Select a valid option!\r\n");
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
        System.out.print("Press B to go back. Input book ID to see details.\r\n");
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
                    System.out.print("Select a valid option!\r\n");
                }
            }
        }
    }

    private void bookDetailsView() {
        System.out.print(bookService.getBookDetailsById(book_details_id));
        System.out.print("Press B to go back.\r\n");
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                System.out.print("Select a valid option!\r\n");
            }
        }
    }
}
