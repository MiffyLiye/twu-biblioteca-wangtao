package com.twu.biblioteca;


public class WelcomeMessage {
    private String NewLine;

    public WelcomeMessage() {
        NewLine = System.getProperty("line.separator");
    }

    public String get() {
        StringBuilder message = new StringBuilder();
        message.append("Welcome" + NewLine);
        message.append("Biblioteca is ready" + NewLine);
        return message.toString();
    }
}
