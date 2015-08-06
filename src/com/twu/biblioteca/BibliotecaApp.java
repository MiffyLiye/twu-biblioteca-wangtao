package com.twu.biblioteca;

import com.twu.biblioteca.controller.MainMenuController;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.MovieService;

public class BibliotecaApp {
    public static void main(String[] args) {
        BookService bookService = new BookService();
        MovieService movieService = new MovieService();
        Driver driver = new Driver(bookService, movieService);
        driver.run();
    }
}
