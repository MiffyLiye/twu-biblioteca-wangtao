package com.twu.biblioteca;

public class BibliotecaApp {
    private static WelcomeMessage welcomeMessage = new WelcomeMessage();
    private static BookService bookService = new BookService();
    private static MovieService movieService = new MovieService();
    private static Driver driver = new Driver(welcomeMessage, bookService, movieService);

    public static void main(String[] args) {
        driver.run();
    }
}
