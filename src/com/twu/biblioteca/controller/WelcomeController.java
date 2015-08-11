package com.twu.biblioteca.controller;

public class WelcomeController extends BaseController {
    public WelcomeController() {
        super();
        welcome_message = "Welcome" + NewLine
                + "Biblioteca is ready" + NewLine;
    }

    @Override
    public boolean pathMatched() {
        return path.size() == 1 && path.get(0).equals(requestedPath.get(0));
    }

    @Override
    public void runView() {
        System.out.print(welcome_message);
        path.add("main menu");
    }

    private String welcome_message;

    @Override
    protected void initRequestedPath() {
        requestedPath.add("welcome");
    }
}
