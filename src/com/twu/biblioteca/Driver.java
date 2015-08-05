package com.twu.biblioteca;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public Driver(WelcomeMessage welcomeMessage, BookService bookService) {
        this(welcomeMessage);
        this.bookService = bookService;
    }

    public Driver(WelcomeMessage welcomeMessage, BookService bookService, MovieService movieService) {
        this(welcomeMessage, bookService);
        this.movieService = movieService;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        path.add("welcome");
        while (true) {
            if (path.size() == 0) {
                break;
            }
            else if (path.size() ==1 && path.get(0).equals("welcome")) {
                welcomeView();
            }
            else if (path.get(1).equals("list books")) {
                BooksController();
            }
            else if (path.get(1).equals("checkout books")) {
                BooksCheckoutController();
            }
            else if (path.get(1).equals("return books")) {
                BooksReturnController();
            }
            else if (path.get(1).equals("list movies")) {
                MoviesController();
            }
            else if (path.get(1).equals("checkout movies")) {
                MoviesCheckoutController();
            }
            else if (path.get(1).equals("return movies")) {
                MoviesReturnController();
            }
        }
    }

    private WelcomeMessage welcomeMessage;
    private BookService bookService;
    private MovieService movieService;
    private Scanner scanner;
    private String NewLine;

    private List<String> path;
    private Integer book_details_id;
    private Integer movie_details_id;

    private Driver() {
        NewLine = System.getProperty("line.separator");
        path = new LinkedList<String>();
        scanner = new Scanner(System.in);
    }

    private Driver(WelcomeMessage welcomeMessage) {
        this();
        this.welcomeMessage = welcomeMessage;
    }

    private void welcomeView() {
        System.out.print(welcomeMessage.get());
        System.out.print("Press Q to quit." + NewLine
                + "Press 1 to list books. Press 2 to checkout books. Press 3 to return books." + NewLine
                + "Press 4 to list movies. Press 5 to checkout movies. Press 6 to return movies." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("q")) {
                path.remove(0);
                break;
            }
            else if (cmd.toLowerCase().equals("1")) {
                path.add("list books");
                break;
            }
            else if (cmd.toLowerCase().equals("2")) {
                path.add("checkout books");
                break;
            }
            else if (cmd.toLowerCase().equals("3")) {
                path.add("return books");
                break;
            }
            else if (cmd.toLowerCase().equals("4")) {
                path.add("list movies");
                break;
            }
            else if (cmd.toLowerCase().equals("5")) {
                path.add("checkout movies");
                break;
            }
            else if (cmd.toLowerCase().equals("6")) {
                path.add("return movies");
                break;
            }
            else {
                System.out.print("Select a valid option!" + NewLine);
            }
        }

    }

    private void BooksController() {
        if (path.size() == 2) {
            booksListView();
        }
        else if (path.size() >= 3 && path.get(2).equals("book details")) {
            bookDetailsView();
        }
    }

    private void booksListView() {
        System.out.print(bookService.getSummaryList());
        System.out.print("Press B to go back. Input book ID to see details." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                try {
                    book_details_id = Integer.parseInt(cmd);
                    path.add("book details");
                    break;
                }
                catch (NumberFormatException ex) {
                    System.out.print("Select a valid option!" + NewLine);
                }
            }
        }
    }

    private void bookDetailsView() {
        System.out.print(bookService.getBookDetailsById(book_details_id));
        System.out.print("Press B to go back." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                System.out.print("Select a valid option!" + NewLine);
            }
        }
    }

    private void BooksCheckoutController() {
        if (path.size() == 2) {
            bookCheckoutView();
        }
        else if (path.size() == 3 && path.get(2).equals("successful")) {
            bookCheckoutSuccessfulView();
        }
        else if (path.size() == 3 && path.get(2).equals("unsuccessful")) {
            bookCheckoutUnsuccessfulView();
        }
    }

    private void bookCheckoutView() {
        System.out.print("Checkout Books" + NewLine);
        System.out.print("Press B to go back. Input the book ID to checkout." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                try {
                    Integer id = Integer.parseInt(cmd);
                    if (bookService.checkout(id)) {
                        path.add("successful");
                    }
                    else {
                        path.add("unsuccessful");
                    }
                    break;
                }
                catch (NumberFormatException ex) {
                    System.out.print("Select a valid option!" + NewLine);
                }
            }
        }
    }

    private void bookCheckoutSuccessfulView() {
        System.out.print("Thank you! Enjoy the book." + NewLine);
        System.out.print("Press B to go back." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                System.out.print("Select a valid option!" + NewLine);
            }
        }
    }

    private void bookCheckoutUnsuccessfulView() {
        System.out.print("That book is not available." + NewLine);
        System.out.print("Press B to go back." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                System.out.print("Select a valid option!" + NewLine);
            }
        }
    }

    private void BooksReturnController() {
        if (path.size() == 2) {
            bookReturnView();
        }
        else if (path.size() == 3 && path.get(2).equals("successful")) {
            bookReturnSuccessfulView();
        }
        else if (path.size() == 3 && path.get(2).equals("unsuccessful")) {
            bookReturnUnsuccessfulView();
        }
    }

    private void bookReturnView() {
        System.out.print("Return Books" + NewLine);
        System.out.print("Press B to go back. Input the book ID to return." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                try {
                    Integer id = Integer.parseInt(cmd);
                    if (bookService.checkin(id)) {
                        path.add("successful");
                    }
                    else {
                        path.add("unsuccessful");
                    }
                    break;
                }
                catch (NumberFormatException ex) {
                    System.out.print("Select a valid option!" + NewLine);
                }
            }
        }
    }

    private void bookReturnSuccessfulView() {
        System.out.print("Thank you for returning the book." + NewLine);
        System.out.print("Press B to go back." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                System.out.print("Select a valid option!" + NewLine);
            }
        }
    }

    private void bookReturnUnsuccessfulView() {
        System.out.print("That is not a valid book to return." + NewLine);
        System.out.print("Press B to go back." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                System.out.print("Select a valid option!" + NewLine);
            }
        }
    }

    private void MoviesController() {
        if (path.size() == 2) {
            moviesListView();
        }
        else if (path.size() >= 3 && path.get(2).equals("movie details")) {
            movieDetailsView();
        }
    }

    private void moviesListView() {
        System.out.print(movieService.getSummaryList());
        System.out.print("Press B to go back. Input movie ID to see details." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                try {
                    movie_details_id = Integer.parseInt(cmd);
                    path.add("movie details");
                    break;
                }
                catch (NumberFormatException ex) {
                    System.out.print("Select a valid option!" + NewLine);
                }
            }
        }
    }

    private void movieDetailsView() {
        System.out.print(movieService.getMovieDetailsById(movie_details_id));
        System.out.print("Press B to go back." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                System.out.print("Select a valid option!" + NewLine);
            }
        }
    }

    private void MoviesCheckoutController() {
        if (path.size() == 2) {
            movieCheckoutView();
        }
        else if (path.size() == 3 && path.get(2).equals("successful")) {
            movieCheckoutSuccessfulView();
        }
        else if (path.size() == 3 && path.get(2).equals("unsuccessful")) {
            movieCheckoutUnsuccessfulView();
        }
    }

    private void movieCheckoutView() {
        System.out.print("Checkout Movies" + NewLine);
        System.out.print("Press B to go back. Input the movie ID to checkout." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                try {
                    Integer id = Integer.parseInt(cmd);
                    if (movieService.checkout(id)) {
                        path.add("successful");
                    }
                    else {
                        path.add("unsuccessful");
                    }
                    break;
                }
                catch (NumberFormatException ex) {
                    System.out.print("Select a valid option!" + NewLine);
                }
            }
        }
    }

    private void movieCheckoutSuccessfulView() {
        System.out.print("Thank you! Enjoy the movie." + NewLine);
        System.out.print("Press B to go back." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                System.out.print("Select a valid option!" + NewLine);
            }
        }
    }

    private void movieCheckoutUnsuccessfulView() {
        System.out.print("That movie is not available." + NewLine);
        System.out.print("Press B to go back." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                System.out.print("Select a valid option!" + NewLine);
            }
        }
    }

    private void MoviesReturnController() {
        if (path.size() == 2) {
            movieReturnView();
        }
        else if (path.size() == 3 && path.get(2).equals("successful")) {
            movieReturnSuccessfulView();
        }
        else if (path.size() == 3 && path.get(2).equals("unsuccessful")) {
            movieReturnUnsuccessfulView();
        }
    }

    private void movieReturnView() {
        System.out.print("Return Movies" + NewLine);
        System.out.print("Press B to go back. Input the movie ID to return." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                try {
                    Integer id = Integer.parseInt(cmd);
                    if (movieService.checkin(id)) {
                        path.add("successful");
                    }
                    else {
                        path.add("unsuccessful");
                    }
                    break;
                }
                catch (NumberFormatException ex) {
                    System.out.print("Select a valid option!" + NewLine);
                }
            }
        }
    }

    private void movieReturnSuccessfulView() {
        System.out.print("Thank you for returning the movie." + NewLine);
        System.out.print("Press B to go back." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                System.out.print("Select a valid option!" + NewLine);
            }
        }
    }

    private void movieReturnUnsuccessfulView() {
        System.out.print("That is not a valid movie to return." + NewLine);
        System.out.print("Press B to go back." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            }
            else {
                System.out.print("Select a valid option!" + NewLine);
            }
        }
    }
}
