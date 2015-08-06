package com.twu.biblioteca.controller;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class MainMenuControllerTest {
    private MainMenuController mainMenuController;
    private List<String> path;

    @Before
    public void SetUp() {
        mainMenuController = new MainMenuController();
        path = new LinkedList<String>();
    }

    @Test
    public void should_match_path_main_menu() throws Exception {
        path.add("welcome");
        path.add("main menu");
        mainMenuController.setPath(path);
        assertEquals(true, mainMenuController.pathMatched());
    }

    @Test
    public void should_not_match_path_otherwise() {
        path.add("welcome");
        path.add("query books");
        mainMenuController.setPath(path);
        assertEquals(false, mainMenuController.pathMatched());
    }
}