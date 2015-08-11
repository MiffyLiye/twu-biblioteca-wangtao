package com.twu.biblioteca.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public abstract class BaseController implements IHaveSession {
    protected String NewLine;
    protected List<String> path;
    protected List<String> requestedPath;
    protected Scanner scanner;
    protected Map<String, Object> session;

    protected abstract void initRequestedPath();

    public BaseController() {
        this.NewLine = System.getProperty("line.separator");

        path = new LinkedList<String>();
        requestedPath = new LinkedList<String>();
        initRequestedPath();

        scanner = new Scanner(System.in);
    }

    @Override
    public Map<String, Object> getSession() {
        return session;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public List<String> getRequestedPath() {
        return requestedPath;
    }

    public void setRequestedPath(List<String> requestedPath) {
        this.requestedPath = requestedPath;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean pathMatched() {
        for (int i = 0; i < requestedPath.size(); i++) {
            if (path.size() > i && path.get(i).equals(requestedPath.get(i))) {
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
