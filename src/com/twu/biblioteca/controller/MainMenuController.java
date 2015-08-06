package com.twu.biblioteca.controller;

public class MainMenuController extends BaseController {

    @Override
    protected void init_requested_path() {
        requested_path.add("welcome");
        requested_path.add("main menu");
    }

    public MainMenuController() {
        super();
    }

    @Override
    public void runView() {
        System.out.print("Press Q to quit." + NewLine
                + "Press 1 to list books. Press 2 to checkout books. Press 3 to return books." + NewLine
                + "Press 4 to list movies. Press 5 to checkout movies. Press 6 to return movies." + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("q")) {
                path.remove(1);
                path.remove(0);
                break;
            }
            else if (cmd.toLowerCase().equals("1")) {
                path.set(1, "query books");
                break;
            }
            else if (cmd.toLowerCase().equals("2")) {
                path.set(1, "checkout books");
                break;
            }
            else if (cmd.toLowerCase().equals("3")) {
                path.set(1, "return books");
                break;
            }
            else if (cmd.toLowerCase().equals("4")) {
                path.set(1, "query movies");
                break;
            }
            else if (cmd.toLowerCase().equals("5")) {
                path.set(1, "checkout movies");
                break;
            }
            else if (cmd.toLowerCase().equals("6")) {
                path.set(1, "return movies");
                break;
            }
            else {
                System.out.print("Select a valid option!" + NewLine);
            }
        }
    }

}
