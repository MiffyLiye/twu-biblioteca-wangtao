package com.twu.biblioteca.controller.book;

import com.twu.biblioteca.controller.BaseController;
import com.twu.biblioteca.service.BookService;

import java.util.List;
import java.util.Scanner;

public class BooksCheckoutController extends BaseController {
    private BookService bookService;

    @Override
    protected void init_requested_path() {
        requested_path.add("welcome");
        requested_path.add("checkout books");
    }

    public BooksCheckoutController() {
        super();
    }

    public BooksCheckoutController(BookService bookService) {
        this();
        this.bookService = bookService;
    }

    @Override
    public void runView() {
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
}
