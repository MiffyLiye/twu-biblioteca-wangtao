package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Book;

import java.util.*;

public class BookService extends ItemService<Book> {

    public BookService() {
        super();
        List<Book> available_books = getAvailableItems();
        available_books.add(new Book(1, "The Story of Tao Part One", "Wang Tao", 2015));
        available_books.add(new Book(2, "The Story of Tao Part Two", "Wang Tao", 2015));
        setAvailableItems(available_books);

        List<Book> checkouted_books = getCheckoutedItems();
        checkouted_books.add(new Book(3, "The Story of Tao Part Three", "Wang Tao", 2015));
        setCheckoutedItems(checkouted_books);
    }
}
