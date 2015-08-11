package com.twu.biblioteca;

import com.twu.biblioteca.controller.*;
import com.twu.biblioteca.controller.book.*;
import com.twu.biblioteca.controller.movie.*;
import com.twu.biblioteca.service.*;

import java.util.*;

public class Driver implements IHaveSession {
    public Driver(BookService bookService, MovieService movieService, UserService userService) {
        this();

        controllers.add(new WelcomeController());
        controllers.add(new MainMenuController());
        controllers.add(new BooksQueryController(bookService));
        controllers.add(new BooksCheckoutController(bookService));
        controllers.add(new BooksReturnController(bookService));
        controllers.add(new MoviesQueryController(movieService));
        controllers.add(new MoviesCheckoutController(movieService));
        controllers.add(new MoviesReturnController(movieService));
        controllers.add(new UserController(userService));

        for (BaseController controller : controllers) {
            controller.setPath(path);
            controller.setScanner(scanner);
            controller.setSession(session);
        }
    }

    @Override
    public Map<String, Object> getSession() {
        return session;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
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
                boolean matched = false;
                for (BaseController controller : controllers) {
                    if (controller.pathMatched()) {
                        controller.runView();
                        matched = true;
                        break;
                    }
                }
                if (!matched) {
                    System.out.println("Something happened.");
                    break;
                }
            }
        }
    }

    private List<BaseController> controllers;
    private Scanner scanner;
    private Map<String, Object> session;

    private List<String> path;

    private Driver() {
        path = new LinkedList<String>();
        scanner = new Scanner(System.in);
        controllers = new LinkedList<BaseController>();
        session = new TreeMap<String, Object>();
    }
}

