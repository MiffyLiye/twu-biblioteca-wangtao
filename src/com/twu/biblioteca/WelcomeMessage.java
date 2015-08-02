package com.twu.biblioteca;


public class WelcomeMessage {
    private String newLine = "\r\n";

    public WelcomeMessage() {}

    public String get() {
        StringBuilder message = new StringBuilder();
        message.append("Welcome" + newLine);
        message.append("Biblioteca is ready" + newLine);
        return message.toString();
    }
}
