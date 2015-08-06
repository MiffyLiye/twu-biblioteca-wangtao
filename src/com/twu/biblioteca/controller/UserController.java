package com.twu.biblioteca.controller;

import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.service.UserService;

import java.util.Map;

public class UserController extends BaseController {
    private UserService userService;

    public UserController() {}

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void initRequestedPath() {
        requestedPath.add("welcome");
        requestedPath.add("user center");
    }

    @Override
    public void runView() {
        if (path.size() == 2) {
            userCenterView();
        }
        else if (path.size() == 3 && path.get(2).equals("user login")) {
            userLoginView();
        }
        else if (path.size() == 3 && path.get(2).equals("user logout")) {
            userLogoutView();
        }
        else if (path.size() == 3 && path.get(2).equals("user information")) {
            userInformationView();
        }
    }

    private void userCenterView() {
        System.out.print("User Center" + NewLine);
        System.out.print("Press B to go back." + NewLine
                + "Press 1 to login. Press 2 to logout. Press 3 to see user information" + NewLine);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.toLowerCase().equals("b")) {
                path.remove(path.size() - 1);
                break;
            } else if (cmd.toLowerCase().equals("1")) {
                path.add("user login");
                break;
            } else if (cmd.toLowerCase().equals("2")) {
                path.add("user logout");
                break;
            } else if (cmd.toLowerCase().equals("3")) {
                path.add("user information");
                break;
            } else {
                System.out.print("Select a valid option!" + NewLine);
            }
        }
    }

    private void userLoginView() {
        System.out.print("User Login" + NewLine);
        System.out.print("Input Library Number:" + NewLine);
        String number = scanner.nextLine();
        System.out.print("Input Password:" + NewLine);
        String password = scanner.nextLine();

        if (userService.authUser(number, password)) {
            session.put("user library number", number);
            System.out.print("Logged in. Press Enter to go back." + NewLine);
        }
        else {
            System.out.print("Invalid library number or password. Press Enter to go back." + NewLine);
        }

        scanner.nextLine();
        path.remove(path.size() - 1);
    }

    private void userLogoutView() {
        if (session.containsKey("user library number")) {
            session.remove("user library number");
            System.out.print("Logged out. Press Enter to go back." + NewLine);
        }
        else {
            System.out.print("Not logged in. Press Enter to go back." + NewLine);
        }
        scanner.nextLine();
        path.remove(path.size() - 1);
    }

    private void userInformationView() {
        String number = (String) session.get("user library number");
        if (number != null) {
            User user = userService.findByLibriaryNumber(number);
            System.out.print("ID: " + user.getId().toString() + NewLine
                    + "Name: " + user.getName() + NewLine
                    + "Library Number: " + user.getLibrary_number() + NewLine
                    + "Email: " + user.getEmail() + NewLine
                    + "Phone: " + user.getPhone() + NewLine);
        }
        else {
            System.out.print("Not logged in.");
        }
        System.out.print("Press Enter to go back.");
        scanner.nextLine();
        path.remove(path.size() - 1);
    }
}
