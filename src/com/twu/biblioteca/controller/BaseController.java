package com.twu.biblioteca.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public abstract class BaseController {
    protected String NewLine;
    protected List<String> path;
    protected List<String> requested_path;
    protected Scanner scanner;

    protected abstract void init_requested_path();

    public BaseController() {
        this.NewLine = System.getProperty("line.separator");

        path = new LinkedList<String>();
        requested_path = new LinkedList<String>();
        init_requested_path();

        scanner = new Scanner(System.in);
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public List<String> getRequested_path() {
        return requested_path;
    }

    public void setRequested_path(List<String> requested_path) {
        this.requested_path = requested_path;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean matchPath() {
        for (int i = 0; i < requested_path.size(); i++) {
            if (path.size() > i && path.get(i).equals(requested_path.get(i))) {
                continue;
            }
            else {
                return false;
            }
        }
        return true;
    }

    public abstract void runView();
}
