package com.twu.biblioteca;


public class WelcomeMessage {
    private String newLine = "\r\n";

    public WelcomeMessage() {}

    public String get() {
        String message = "Welcome" + newLine;
        message = message + "Biblioteca is ready" + newLine;
        return message;
    }
}
