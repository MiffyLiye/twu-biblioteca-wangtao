package com.twu.biblioteca.controller;

public class WelcomeController extends BaseController {
    private String welcome_message;

    @Override
    protected void init_requested_path() {
        requested_path.add("welcome");
    }

    public WelcomeController() {
        super();
        welcome_message = "Welcome" + NewLine
                + "Biblioteca is ready" + NewLine;
    }

    @Override
    public boolean matchPath() {
        return path.size() == 1 && path.get(0).equals(requested_path.get(0));
    }

    @Override
    public void runView() {
        System.out.print(welcome_message);
        path.add("main menu");
    }
}
