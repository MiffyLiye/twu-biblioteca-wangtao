package com.twu.biblioteca;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by MiffyLiye on 02/08/2015.
 */
public class BookService {
    private List<Book> books;

    public BookService() {
        books = new LinkedList<Book>();
        books.add(new Book(1, "The Story of Tao Part One", "Wang Tao", 2015));
        books.add(new Book(2, "The Story of Tao Part Two", "Wang Tao", 2015));
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getSummaryList() {
        StringBuilder message = new StringBuilder();
        for (Book book : books) {
            message.append("ID: " + book.getId().toString() + "\t" + "Title: " + book.getTitle() + "\r\n");
        }
        return message.toString();
    }

    public String getBookDetailsById(Integer id) {
        StringBuilder message = new StringBuilder();
        for (Book book : books) {
            if (book.getId() == id) {
                message.append("ID: " + book.getId().toString() + "\r\n"
                    + "Title: " + book.getTitle() + "\r\n"
                    + "Author: " + book.getAuthor() + "\r\n"
                    + "Published in " + book.getYearPublished().toString() + "\r\n");
            }
        }
        return message.toString();
    }
}
