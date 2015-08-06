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
        List<Book> available_books = new LinkedList<Book>();
        available_books.add(new Book(1, "The Story of Tao Part One", "Wang Tao", 2015));
        available_books.add(new Book(2, "The Story of Tao Part Two", "Wang Tao", 2015));
        bookService.setAvailableBooks(available_books);
        List<Book> checkouted_books = new LinkedList<Book>();
        checkouted_books.add(new Book(3, "The Story of Tao Part Three", "Wang Tao", 2015));
        bookService.setCheckoutedBooks(checkouted_books);
    }

    @Test
    public void should_get_all_available_books() throws Exception {
        assertEquals(2, bookService.getAvailableBooks().size());
    }

    @Test
    public void should_get_available_book_by_id() {
        assertNotNull(bookService.findAvailableBookById(1));
    }

    @Test
    public void should_not_found_if_cannot_find_book_by_id() {
        assertNull(bookService.findAvailableBookById(0));
    }

    @Test
    public void should_successful_checkout_book_if_it_is_available() {
        boolean actual = bookService.checkout(1);
        assertEquals(true, actual);
    }

    @Test
    public void should_not_list_checkouted_book_on_books_list() {
        bookService.checkout(1);
        assertEquals(1, bookService.getAvailableBooks().size());
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

        assertEquals(2, bookService.getAvailableBooks().size());
    }

    @Test
    public void should_unsuccessful_return_book_if_it_is_not_checkouted() {
        assertFalse(bookService.checkin(1));
        assertFalse(bookService.checkin(-1));
    }

}