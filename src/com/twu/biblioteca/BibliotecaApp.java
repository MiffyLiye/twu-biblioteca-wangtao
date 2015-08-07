package com.twu.biblioteca;

import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.MovieService;
import com.twu.biblioteca.service.UserService;

public class BibliotecaApp {
    public static void main(String[] args) {
        BookService bookService = new BookService();
        MovieService movieService = new MovieService();
        UserService userService = new UserService();
        Driver driver = new Driver(bookService, movieService, userService);
        driver.run();
    }
}
