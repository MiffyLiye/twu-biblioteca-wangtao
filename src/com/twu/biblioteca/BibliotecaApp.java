package com.twu.biblioteca;

import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.MovieService;

public class BibliotecaApp {
    private static WelcomeMessage welcomeMessage = new WelcomeMessage();
    private static BookService bookService = new BookService();
    private static MovieService movieService = new MovieService();
    private static Driver driver = new Driver(welcomeMessage, bookService, movieService);

    public static void main(String[] args) {
        driver.run();
    }
}
