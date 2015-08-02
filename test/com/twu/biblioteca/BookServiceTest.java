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

    @Before
    public void SetUp() {
        bookService = new BookService();
        List<Book> books = new LinkedList<Book>();
        books.add(new Book(1, "The Story of Tao Part One", "Wang Tao", 2015));
        books.add(new Book(2, "The Story of Tao Part Two", "Wang Tao", 2015));
        bookService.setBooks(books);
    }

    @Test
    public void should_list_all_books() throws Exception {
        String summaryList = "ID: 1\tTitle: The Story of Tao Part One\r\n"
                + "ID: 2\tTitle: The Story of Tao Part Two\r\n";
        assertEquals(summaryList, bookService.getSummaryList());
    }

    @Test
    public void should_list_book_details_by_id() {
        String details = "ID: 1\r\n"
                + "Title: The Story of Tao Part One\r\n"
                + "Author: Wang Tao\r\n"
                + "Published in 2015\r\n";
        assertEquals(details, bookService.getBookDetailsById(1));
    }

    @Test
    public void should_show_book_not_found_if_cannot_find_book_by_id() {
        assertEquals("Book not found.\r\n", bookService.getBookDetailsById(0));
    }
}