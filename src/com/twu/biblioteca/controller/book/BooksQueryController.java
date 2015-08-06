package com.twu.biblioteca.controller.book;

import com.twu.biblioteca.controller.BaseController;
import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.service.BookService;

import java.util.Collections;
import java.util.List;

public class BooksQueryController extends BaseController {
    public BooksQueryController() {
        super();
    }

    public BooksQueryController(BookService bookService) {
        this();
        this.bookService = bookService;
    }

    @Override
    public void runView() {
        if (path.size() == 2) {
            booksListView();
        }
        else if (path.size() >= 3 && path.get(2).equals("book details")) {
            bookDetailsView();
        }
    }

    private BookService bookService;
    private Integer book_details_id;

    @Override
    protected void initRequestedPath() {
        requestedPath.add("welcome");
        requestedPath.add("query books");
    }

    private void booksListView() {
        List<Book> bookList = bookService.getAvailableItems();
        Collections.sort(bookList);
        for (Book book : bookList) {
            System.out.print("ID: " + book.getId().toString() + "\t" + "Title: " + book.getTitle() + NewLine);
        }
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
        Book book = bookService.findAvailableItemById(book_details_id);
        System.out.print("ID: " + book.getId().toString()
                + NewLine + "Title: " + book.getTitle() + NewLine
                + "Author: " + book.getAuthor() + NewLine
                + "Published in " + book.getYearPublished().toString() + NewLine);
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
