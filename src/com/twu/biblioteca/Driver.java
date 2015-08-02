package com.twu.biblioteca;

import java.util.Scanner;

public class Driver {
    private WelcomeMessage welcomeMessage;
    private BookService bookService;
    private Scanner scanner;

    public Driver() {
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

    private String mainPage() {
        StringBuilder message = new StringBuilder();
        message.append(welcomeMessage.get());

        message.append("Press Q to quit. Press 1 to list books.\r\n");
        return message.toString();
    }

    private String booksListPage() {
        StringBuilder message = new StringBuilder();
        message.append(bookService.getSummaryList());
        message.append("Press M to go to main menu. Input book ID to see details.\r\n");
        return message.toString();
    }

    private String bookDetailsPage(Integer id) {
        StringBuilder message = new StringBuilder();
        message.append(bookService.getBookDetailsById(id));
        message.append("Press M to go to main menu.\r\n");
        return message.toString();
    }

    public void run() {
        while (true) {
            System.out.print(mainPage());
            String cmd = scanner.nextLine();

            if (cmd.toLowerCase().equals("q")) {
                return;
            }
            else if (cmd.toLowerCase().equals("1")) {
                System.out.print(booksListPage());
                while (true) {
                    cmd = scanner.nextLine();
                    if (cmd.toLowerCase().equals("m")) {
                        break;
                    }
                    else {
                        Integer id = null;
                        try {
                            id = Integer.parseInt(cmd);
                        }
                        catch (NumberFormatException ex) {

                        }
                        if (id != null) {
                            System.out.print(bookDetailsPage(id));
                        }
                    }
                }
            }
        }
    }
}
