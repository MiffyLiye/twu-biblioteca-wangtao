package com.twu.biblioteca;

public class BibliotecaApp {
    private static WelcomeMessage welcomeMessage = new WelcomeMessage();
    private static Driver driver = new Driver(welcomeMessage);

    public static void main(String[] args) {
        driver.run();
    }
}
