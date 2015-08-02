package com.twu.biblioteca;

import java.util.Scanner;

public class Driver {
    private WelcomeMessage welcomeMessage;
    private Scanner scanner = new Scanner(System.in);

    public Driver() {}

    public Driver(WelcomeMessage welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        System.out.print(welcomeMessage.get());
        String cmd = scanner.nextLine();
        if (cmd.toLowerCase().equals("q")) {
            return;
        }
        else if (cmd.toLowerCase().equals("1")) {

        }
    }
}
