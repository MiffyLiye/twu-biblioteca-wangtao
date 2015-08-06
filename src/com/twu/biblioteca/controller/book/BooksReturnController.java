package com.twu.biblioteca.controller.book;

import com.twu.biblioteca.controller.BaseController;
import com.twu.biblioteca.service.BookService;

import java.util.List;
import java.util.Scanner;

public class BooksReturnController extends BaseController {
    private BookService bookService;

    @Override
    protected void init_requested_path() {
        requested_path.add("welcome");
        requested_path.add("return books");
    }

    public BooksReturnController() {
        super();
    }

    public BooksReturnController(BookService bookService) {
        this();
        this.bookService = bookService;
    }

    @Override
    public void runView() {
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
