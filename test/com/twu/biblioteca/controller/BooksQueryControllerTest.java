package com.twu.biblioteca.controller;

import com.twu.biblioteca.controller.book.BooksQueryController;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class BooksQueryControllerTest {
    private BooksQueryController booksQueryController;
    private List<String> path;

    @Before
    public void setUp() throws Exception {
        booksQueryController = new BooksQueryController();
        path = new LinkedList<String>();
    }

    @Test
    public void should_match_path_welcome_list_books() throws Exception {
        path.add("welcome");
        path.add("query books");
        booksQueryController.setPath(path);
        assertEquals(true, booksQueryController.pathMatched());
    }

    @Test
    public void should_match_path_welcome_list_books_book_details() throws Exception {
        path.add("welcome");
        path.add("query books");
        path.add("book details");
        booksQueryController.setPath(path);
        assertEquals(true, booksQueryController.pathMatched());
    }

    @Test
    public void should_not_match_welcome_checkout_books() {
        path.add("checkout books");
        booksQueryController.setPath(path);
        assertEquals(false, booksQueryController.pathMatched());
    }
}