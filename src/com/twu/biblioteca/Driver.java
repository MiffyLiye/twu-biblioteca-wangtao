package com.twu.biblioteca;

import com.twu.biblioteca.controller.BaseController;
import com.twu.biblioteca.controller.WelcomeController;
import com.twu.biblioteca.controller.book.BooksCheckoutController;
import com.twu.biblioteca.controller.book.BooksQueryController;
import com.twu.biblioteca.controller.book.BooksReturnController;
import com.twu.biblioteca.controller.MainMenuController;
import com.twu.biblioteca.controller.movie.MoviesReturnController;
import com.twu.biblioteca.controller.movie.MoviesCheckoutController;
import com.twu.biblioteca.controller.movie.MoviesQueryController;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.MovieService;

import java.util.*;

public class Driver {
    public Driver(BookService bookService, MovieService movieService) {
        this();

        controllers.add(new WelcomeController());
        controllers.add(new MainMenuController());
        controllers.add(new BooksQueryController(bookService));
        controllers.add(new BooksCheckoutController(bookService));
        controllers.add(new BooksReturnController(bookService));
        controllers.add(new MoviesQueryController(movieService));
        controllers.add(new MoviesCheckoutController(movieService));
        controllers.add(new MoviesReturnController(movieService));

        for (BaseController controller : controllers) {
            controller.setPath(path);
            controller.setScanner(scanner);
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;

        for (BaseController controller : controllers) {
            controller.setScanner(scanner);
        }
    }

    public void run() {
        path.add("welcome");
        while (true) {
            if (path.size() == 0) {
                break;
            }
            else {
                for (BaseController controller : controllers) {
                    if (controller.matchPath()) {
                        controller.runView();
                        break;
                    }
                }
            }
        }
    }

    private List<BaseController> controllers;
    private Scanner scanner;

    private List<String> path;

    private Driver() {
        path = new LinkedList<String>();
        scanner = new Scanner(System.in);
        controllers = new LinkedList<BaseController>();
    }
}

