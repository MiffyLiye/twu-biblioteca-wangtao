package com.twu.biblioteca;

import com.twu.biblioteca.entity.*;
import com.twu.biblioteca.service.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class DriverTest {
    private Driver driver;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent;
    private String NewLine;

    private String welcome_message;
    private String main_menu;

    @Before
    public void SetUp() {
        NewLine = System.getProperty("line.separator");

        BookService bookService = new BookService();
        List<Book> available_books = new LinkedList<Book>();
        available_books.add(new Book(1, "The Story of Tao Part One", "Wang Tao", 2015));
        available_books.add(new Book(2, "The Story of Tao Part Two", "Wang Tao", 2015));
        bookService.setAvailableItems(available_books);

        List<Book> checkouted_books = new LinkedList<Book>();
        checkouted_books.add(new Book(3, "The Story of Tao Part Three", "Wang Tao", 2015));
        bookService.setCheckoutedItems(checkouted_books);

        MovieService movieService = new MovieService();
        List<Movie> available_movies = new LinkedList<Movie>();
        available_movies.add(new Movie(1, "The Story of Tao Part One", 2015, "Wang Tao", 10));
        available_movies.add(new Movie(2, "The Story of Tao Part Two", 2015, "Wang Tao", null));
        movieService.setAvailableItems(available_movies);
        List<Movie> checkouted_movies = new LinkedList<Movie>();
        checkouted_movies.add(new Movie(3, "The Story of Tao Part Three", 2015, "Wang Tao", null));
        movieService.setCheckoutedItems(checkouted_movies);

        UserService userService = new UserService();
        List<User> users = new LinkedList<User>();
        users.add(new User(1, "Wang Tao", "000-0001", "password", "wangtao@example.com", "+861023456789"));
        users.add(new User(2, "Miffy Liye", "000-0002", "password", "miffyliye@example.com", "+861098765432"));
        userService.setUsers(users);

        driver = new Driver(bookService, movieService, userService);
        driver.getSession().put("user library number", "000-0001");

        System.setOut(new PrintStream(outContent));

        welcome_message  = "Welcome" + NewLine
                + "Biblioteca is ready" + NewLine;
        main_menu = "Press Q to quit." + NewLine
                + "Press 1 to list books. Press 2 to checkout books. Press 3 to return books." + NewLine
                + "Press 4 to list movies. Press 5 to checkout movies. Press 6 to return movies." + NewLine
                + "Press 7 to go to user center." + NewLine;

    }

    @Test
    public void should_return_welcome_message_by_default() throws Exception {
        byte[] input = ("q" + NewLine).getBytes();
        inContent = new ByteArrayInputStream(input);
        driver.setScanner(new Scanner(inContent));

        driver.run();
        assertEquals(welcome_message + main_menu,
                outContent.toString());
    }

    @Test
    public void should_quit_when_input_cmd_q() {
        byte[] input = ("q" + NewLine).getBytes();
        inContent = new ByteArrayInputStream(input);
        driver.setScanner(new Scanner(inContent));
        driver.run();
        assertEquals(welcome_message + main_menu,
                outContent.toString());
    }

    @Test
    public void should_list_books_summary_when_input_cmd_1_after_welcome_message() {
        byte[] input = ("1" + NewLine
                + "b" + NewLine
                + "q" + NewLine).getBytes();
        inContent = new ByteArrayInputStream(input);
        driver.setScanner(new Scanner(inContent));
        driver.run();
        assertEquals(welcome_message + main_menu
                        + "ID: 1\tTitle: The Story of Tao Part One" + NewLine
                        + "ID: 2\tTitle: The Story of Tao Part Two" + NewLine
                        + "Press B to go back. Input book ID to see details." + NewLine
                        + welcome_message + main_menu,
                        outContent.toString());
    }

    @Test
    public void should_show_invalid_option_if_meet_one_in_welcome_page() {
        byte[] input = ("0"+NewLine
                +"q"+NewLine).getBytes();
        inContent = new ByteArrayInputStream(input);
        driver.setScanner(new Scanner(inContent));
        driver.run();
        assertEquals(welcome_message + main_menu
                        + "Select a valid option!"+NewLine,
                outContent.toString());
    }

    @Test
    public void should_show_invalid_option_if_meet_one_in_list_book_page() {
        byte[] input = ("1"+NewLine
                +"a"+NewLine
                +"b"+NewLine
                +"q"+NewLine).getBytes();
        inContent = new ByteArrayInputStream(input);
        driver.setScanner(new Scanner(inContent));
        driver.run();
        assertEquals(welcome_message + main_menu
                        + "ID: 1\tTitle: The Story of Tao Part One" + NewLine
                        + "ID: 2\tTitle: The Story of Tao Part Two" + NewLine
                        + "Press B to go back. Input book ID to see details." + NewLine
                        + "Select a valid option!" + NewLine
                        + welcome_message + main_menu,
                outContent.toString());
    }

    @Test
    public void test_book_checkout() {
        byte[] input = ("2"+NewLine
                +"1"+NewLine
                +"b"+NewLine
                +"1"+NewLine
                +"b"+NewLine
                +"a"+NewLine
                +"b"+NewLine
                +"q"+NewLine).getBytes();
        inContent = new ByteArrayInputStream(input);
        driver.setScanner(new Scanner(inContent));
        driver.run();
        assertEquals(welcome_message + main_menu
                        + "Checkout Books" + NewLine
                        + "Press B to go back. Input the book ID to checkout." + NewLine
                        + "Thank you! Enjoy the book." + NewLine
                        + "Press B to go back." + NewLine
                        + "Checkout Books" + NewLine
                        + "Press B to go back. Input the book ID to checkout." + NewLine
                        + "That book is not available." + NewLine
                        + "Press B to go back." + NewLine
                        + "Checkout Books" + NewLine
                        + "Press B to go back. Input the book ID to checkout." + NewLine
                        + "Select a valid option!" + NewLine
                        + welcome_message + main_menu,
                outContent.toString());
    }

    @Test
    public void test_book_return() {
        byte[] input = ("3"+NewLine
                +"3"+NewLine
                +"b"+NewLine
                +"3"+NewLine
                +"b"+NewLine
                +"a"+NewLine
                +"b"+NewLine
                +"q"+NewLine).getBytes();
        inContent = new ByteArrayInputStream(input);
        driver.setScanner(new Scanner(inContent));
        driver.run();
        assertEquals(welcome_message + main_menu
                        + "Return Books" + NewLine
                        + "Press B to go back. Input the book ID to return." + NewLine
                        + "Thank you for returning the book." + NewLine
                        + "Press B to go back." + NewLine
                        + "Return Books" + NewLine
                        + "Press B to go back. Input the book ID to return." + NewLine
                        + "That is not a valid book to return." + NewLine
                        + "Press B to go back." + NewLine
                        + "Return Books" + NewLine
                        + "Press B to go back. Input the book ID to return." + NewLine
                        + "Select a valid option!" + NewLine
                        + welcome_message + main_menu,
                outContent.toString());
    }

    @Test
    public void should_show_invalid_option_if_meet_one_in_list_movie_page() {
        byte[] input = ("4"+NewLine+"a"+NewLine+"b"+NewLine+"q"+NewLine).getBytes();
        inContent = new ByteArrayInputStream(input);
        driver.setScanner(new Scanner(inContent));
        driver.run();
        assertEquals(welcome_message + main_menu
                        + "ID: 1\tName: The Story of Tao Part One\tYear: 2015" + NewLine
                        + "ID: 2\tName: The Story of Tao Part Two\tYear: 2015" + NewLine
                        + "Press B to go back. Input movie ID to see details." + NewLine
                        + "Select a valid option!" + NewLine
                        + welcome_message + main_menu,
                outContent.toString());
    }

    @Test
    public void test_movie_checkout() {
        byte[] input = ("5"+NewLine
                +"1"+NewLine
                +"b"+NewLine
                +"1"+NewLine
                +"b"+NewLine
                +"a"+NewLine
                +"b"+NewLine
                +"q"+NewLine).getBytes();
        inContent = new ByteArrayInputStream(input);
        driver.setScanner(new Scanner(inContent));
        driver.run();
        assertEquals(welcome_message + main_menu
                        + "Checkout Movies" + NewLine
                        + "Press B to go back. Input the movie ID to checkout." + NewLine
                        + "Thank you! Enjoy the movie." + NewLine
                        + "Press B to go back." + NewLine
                        + "Checkout Movies" + NewLine
                        + "Press B to go back. Input the movie ID to checkout." + NewLine
                        + "That movie is not available." + NewLine
                        + "Press B to go back." + NewLine
                        + "Checkout Movies" + NewLine
                        + "Press B to go back. Input the movie ID to checkout." + NewLine
                        + "Select a valid option!" + NewLine
                        + welcome_message + main_menu,
                outContent.toString());
    }

    @Test
    public void test_movie_return() {
        byte[] input = ("6"+NewLine
                +"3"+NewLine
                +"b"+NewLine
                +"3"+NewLine
                +"b"+NewLine
                +"a"+NewLine
                +"b"+NewLine
                +"q"+NewLine).getBytes();
        inContent = new ByteArrayInputStream(input);
        driver.setScanner(new Scanner(inContent));
        driver.run();
        assertEquals(welcome_message + main_menu
                        + "Return Movies" + NewLine
                        + "Press B to go back. Input the movie ID to return." + NewLine
                        + "Thank you for returning the movie." + NewLine
                        + "Press B to go back." + NewLine
                        + "Return Movies" + NewLine
                        + "Press B to go back. Input the movie ID to return." + NewLine
                        + "That is not a valid movie to return." + NewLine
                        + "Press B to go back." + NewLine
                        + "Return Movies" + NewLine
                        + "Press B to go back. Input the movie ID to return." + NewLine
                        + "Select a valid option!" + NewLine
                        + welcome_message + main_menu,
                outContent.toString());
    }

    @Test
    public void test_user_successful_login_user_information_logout() {
        byte[] input = ("7"+NewLine
                +"1"+NewLine
                +"000-0001"+NewLine
                +"password"+NewLine
                +NewLine
                +"3"+NewLine
                +NewLine
                +"2"+NewLine
                +NewLine
                +"b"+NewLine
                +"q"+NewLine).getBytes();
        inContent = new ByteArrayInputStream(input);
        driver.setScanner(new Scanner(inContent));
        driver.run();
        assertEquals(welcome_message + main_menu
                        + "User Center" + NewLine
                        + "Press B to go back." + NewLine
                        + "Press 1 to login. Press 2 to logout. Press 3 to see user information" + NewLine
                        + "User Login" + NewLine
                        + "Input Library Number:" + NewLine
                        + "Input Password:" + NewLine
                        + "Logged in. Press Enter to go back." + NewLine
                        + "User Center" + NewLine
                        + "Press B to go back." + NewLine
                        + "Press 1 to login. Press 2 to logout. Press 3 to see user information" + NewLine
                        + "ID: 1" + NewLine
                        + "Name: Wang Tao" + NewLine
                        + "Library Number: 000-0001" + NewLine
                        + "Email: wangtao@example.com" + NewLine
                        + "Phone: +861023456789" + NewLine
                        + "Press Enter to go back.User Center" + NewLine
                        + "Press B to go back." + NewLine
                        + "Press 1 to login. Press 2 to logout. Press 3 to see user information" + NewLine
                        + "Logged out. Press Enter to go back." + NewLine
                        + "User Center" + NewLine
                        + "Press B to go back." + NewLine
                        + "Press 1 to login. Press 2 to logout. Press 3 to see user information" + NewLine
                        + welcome_message + main_menu,
                outContent.toString());
    }

    @Test
    public void test_unsuccessful_login_user_information_logout() {
        driver.getSession().remove("user library number");
        byte[] input = ("7"+NewLine
                +"1"+NewLine
                +"000-0001"+NewLine
                +"pwd"+NewLine
                +NewLine
                +"3"+NewLine
                +NewLine
                +"2"+NewLine
                +NewLine
                +"b"+NewLine
                +"q"+NewLine).getBytes();
        inContent = new ByteArrayInputStream(input);
        driver.setScanner(new Scanner(inContent));
        driver.run();
        assertEquals(welcome_message + main_menu
                        + "User Center" + NewLine
                        + "Press B to go back." + NewLine
                        + "Press 1 to login. Press 2 to logout. Press 3 to see user information" + NewLine
                        + "User Login" + NewLine
                        + "Input Library Number:" + NewLine
                        + "Input Password:" + NewLine
                        + "Invalid library number or password. Press Enter to go back." + NewLine
                        + "User Center" + NewLine
                        + "Press B to go back." + NewLine
                        + "Press 1 to login. Press 2 to logout. Press 3 to see user information" + NewLine
                        + "Not logged in.Press Enter to go back.User Center" + NewLine
                        + "Press B to go back." + NewLine
                        + "Press 1 to login. Press 2 to logout. Press 3 to see user information" + NewLine
                        + "Not logged in. Press Enter to go back." + NewLine
                        + "User Center" + NewLine
                        + "Press B to go back." + NewLine
                        + "Press 1 to login. Press 2 to logout. Press 3 to see user information" + NewLine
                        + welcome_message + main_menu,
                outContent.toString());
    }

    @Test
    public void should_not_checkout_successful_without_login() {
        driver.getSession().remove("user library number");
        byte[] input = ("2"+NewLine
                +"1"+NewLine
                +"b"+NewLine
                +"b"+NewLine
                +"5"+NewLine
                +"1"+NewLine
                +"b"+NewLine
                +"b"+NewLine
                +"q"+NewLine).getBytes();
        inContent = new ByteArrayInputStream(input);
        driver.setScanner(new Scanner(inContent));
        driver.run();
        assertEquals(welcome_message + main_menu
                        + "Checkout Books" + NewLine
                        + "Press B to go back. Input the book ID to checkout." + NewLine
                        + "Please login." + NewLine
                        + "Press B to go back." + NewLine
                        + "Checkout Books" + NewLine
                        + "Press B to go back. Input the book ID to checkout." + NewLine
                        + "Welcome" + NewLine
                        + "Biblioteca is ready" + NewLine
                        + "Press Q to quit." + NewLine
                        + "Press 1 to list books. Press 2 to checkout books. Press 3 to return books." + NewLine
                        + "Press 4 to list movies. Press 5 to checkout movies. Press 6 to return movies." + NewLine
                        + "Press 7 to go to user center." + NewLine
                        + "Checkout Movies" + NewLine
                        + "Press B to go back. Input the movie ID to checkout." + NewLine
                        + "Please login." + NewLine
                        + "Press B to go back." + NewLine
                        + "Checkout Movies" + NewLine
                        + "Press B to go back. Input the movie ID to checkout." + NewLine
                        + welcome_message + main_menu,
                outContent.toString());
    }
}