package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Book;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {
    private BookService bookService;
    private String library_number;

    @Before
    public void SetUp() {
        library_number = "000-0001";

        bookService = new BookService();
        List<Book> available_books = new LinkedList<Book>();
        available_books.add(new Book(1, "The Story of Tao Part One", "Wang Tao", 2015));
        available_books.add(new Book(2, "The Story of Tao Part Two", "Wang Tao", 2015));
        bookService.setAvailableItems(available_books);
        List<Book> checkouted_books = new LinkedList<Book>();
        checkouted_books.add(new Book(3, "The Story of Tao Part Three", "Wang Tao", 2015));
        bookService.setCheckoutedItems(checkouted_books);
    }

    @Test
    public void should_get_all_available_books() throws Exception {
        assertEquals(2, bookService.getAvailableItems().size());
    }

    @Test
    public void should_get_available_book_by_id() {
        assertNotNull(bookService.findAvailableItemById(1));
    }

    @Test
    public void should_not_found_if_cannot_find_book_by_id() {
        assertNull(bookService.findAvailableItemById(0));
    }

    @Test
    public void should_successful_checkout_book_if_it_is_available() {
        assertTrue(bookService.checkout(1, this.library_number));
    }

    @Test
    public void should_not_list_checkouted_book_on_books_list() {
        bookService.checkout(1, this.library_number);
        assertEquals(1, bookService.getAvailableItems().size());
    }

    @Test
    public void should_unsuccessful_checkout_book_if_it_is_unavailable() {
        bookService.checkout(1, this.library_number);
        assertEquals(false, bookService.checkout(1, this.library_number));

        assertEquals(false, bookService.checkout(-1, this.library_number));
    }

    @Test
    public void should_successful_return_book_if_it_is_checkouted() {
        bookService.checkout(1, this.library_number);
        assertEquals(true, bookService.checkin(1));

        assertEquals(2, bookService.getAvailableItems().size());
    }

    @Test
    public void should_unsuccessful_return_book_if_it_is_not_checkouted() {
        assertFalse(bookService.checkin(1));
        assertFalse(bookService.checkin(-1));
    }

}