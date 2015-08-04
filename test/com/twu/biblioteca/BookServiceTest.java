package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MiffyLiye on 02/08/2015.
 */
public class BookServiceTest {
    private BookService bookService;
    private String NewLine;

    @Before
    public void SetUp() {
        NewLine = System.getProperty("line.separator");

        bookService = new BookService();
        List<Book> books = new LinkedList<Book>();
        books.add(new Book(1, "The Story of Tao Part One", "Wang Tao", 2015));
        books.add(new Book(2, "The Story of Tao Part Two", "Wang Tao", 2015));
        bookService.setAvailableBooks(books);
    }

    @Test
    public void should_list_all_books() throws Exception {
        String summaryList = "ID: 1\tTitle: The Story of Tao Part One" + NewLine
                + "ID: 2\tTitle: The Story of Tao Part Two" + NewLine;
        assertEquals(summaryList, bookService.getSummaryList());
    }

    @Test
    public void should_list_book_details_by_id() {
        String details = "ID: 1" + NewLine
                + "Title: The Story of Tao Part One" + NewLine
                + "Author: Wang Tao" + NewLine
                + "Published in 2015" + NewLine;
        assertEquals(details, bookService.getBookDetailsById(1));
    }

    @Test
    public void should_show_book_not_found_if_cannot_find_book_by_id() {
        assertEquals("Book not found." + NewLine, bookService.getBookDetailsById(0));
    }

    @Test
    public void should_successful_checkout_book_if_it_is_available() {
        boolean actual = bookService.checkout(1);
        assertEquals(true, actual);
    }

    @Test
    public void should_not_list_checkouted_book_on_books_list() {
        bookService.checkout(1);
        assertEquals("ID: 2\tTitle: The Story of Tao Part Two" + NewLine, bookService.getSummaryList());
    }

    @Test
    public void should_unsuccessful_checkout_book_if_it_is_unavailable() {
        bookService.checkout(1);
        assertEquals(false, bookService.checkout(1));

        assertEquals(false, bookService.checkout(-1));
    }

    @Test
    public void should_successful_return_book_if_it_is_checkouted() {
        bookService.checkout(1);
        assertTrue(bookService.checkin(1));

        String summaryList = "ID: 1\tTitle: The Story of Tao Part One" + NewLine
                + "ID: 2\tTitle: The Story of Tao Part Two" + NewLine;
        assertEquals(summaryList, bookService.getSummaryList());
    }

    @Test
    public void should_unsuccessful_return_book_if_it_is_not_checkouted() {
        assertFalse(bookService.checkin(1));
        assertFalse(bookService.checkin(-1));
    }

}