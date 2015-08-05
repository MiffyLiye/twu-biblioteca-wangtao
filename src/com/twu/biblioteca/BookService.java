package com.twu.biblioteca;

import java.util.*;

/**
 * Created by MiffyLiye on 02/08/2015.
 */
public class BookService {
    private String NewLine;
    private List<Book> available_books;
    private List<Book> checkouted_books;

    public BookService() {
        NewLine = System.getProperty("line.separator");
        available_books = new LinkedList<Book>();
        available_books.add(new Book(1, "The Story of Tao Part One", "Wang Tao", 2015));
        available_books.add(new Book(2, "The Story of Tao Part Two", "Wang Tao", 2015));
        checkouted_books = new LinkedList<Book>();
        checkouted_books.add(new Book(3, "The Story of Tao Part Three", "Wang Tao", 2015));
    }

    public List<Book> getAvailableBooks() {
        return available_books;
    }

    public void setAvailableBooks(List<Book> books) {
        this.available_books = books;
    }

    public List<Book> getCheckoutedBooks() {
        return checkouted_books;
    }

    public void setCheckoutedBooks(List<Book> books) {
        this.checkouted_books = books;
    }

    public String getSummaryList() {
        StringBuilder message = new StringBuilder();
        for (Book book : available_books) {
            message.append("ID: " + book.getId().toString() + "\t" + "Title: " + book.getTitle() + NewLine);
        }
        return message.toString();
    }

    public String getBookDetailsById(Integer id) {
        StringBuilder message = new StringBuilder();
        Book book = findAvailableBookById(id);
        if (book != null) {
            message.append("ID: " + book.getId().toString()
                    + NewLine + "Title: " + book.getTitle() + NewLine
                    + "Author: " + book.getAuthor() + NewLine
                    + "Published in " + book.getYearPublished().toString() + NewLine);
        }
        else {
            message.append("Book not found." + NewLine);
        }
        return message.toString();
    }

    private Book findAvailableBookById(Integer id) {
        for (Book book : available_books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    private Book findCheckoutedBookById(Integer id) {
        for (Book book : checkouted_books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    public boolean checkout(Integer id) {
        Book book = findAvailableBookById(id);
        if (book == null) {
            return false;
        }
        else {
            Book checkouted = findCheckoutedBookById(id);
            if (checkouted == null) {
                available_books.remove(book);
                checkouted_books.add(book);
                return true;
            }
            else {
                return false;
            }
        }
    }

    public boolean checkin(Integer id) {
        Book book = findCheckoutedBookById(id);
        if (book == null) {
            return false;
        }
        else {
            checkouted_books.remove(book);
            available_books.add(book);
            Collections.sort(available_books);
            return true;
        }
    }
}
