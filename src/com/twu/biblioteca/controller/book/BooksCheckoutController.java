package com.twu.biblioteca.controller.book;

import com.twu.biblioteca.controller.BaseController;
import com.twu.biblioteca.service.BookService;

public class BooksCheckoutController extends BaseController {
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

    private BookService bookService;

    @Override
    protected void initRequestedPath() {
        requestedPath.add("welcome");
        requestedPath.add("checkout books");
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
                    String number = (String) session.get("user library number");
                    if (number != null && bookService.checkout(id, number)) {
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
        if (session.get("user library number") == null) {
            System.out.print("Please login." + NewLine);
        }
        else {
            System.out.print("That book is not available." + NewLine);
        }
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
