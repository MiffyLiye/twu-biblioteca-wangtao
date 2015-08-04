package com.twu.biblioteca;

import java.util.Date;

public class Book implements Comparable<Book> {
    private Integer id;
    private String title;
    private String author;
    private Integer yearPublished;

    public Book() {}

    public Book(Integer id, String title, String author, Integer yearPublished) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public int compareTo(Book book) {
        return id - book.id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Integer yearPublished) {
        this.yearPublished = yearPublished;
    }
}
