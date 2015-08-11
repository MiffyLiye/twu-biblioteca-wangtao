package com.twu.biblioteca.controller;

import java.util.Map;

public interface IHaveSession {
    Map<String, Object> getSession();
    void setSession(Map<String, Object> session);
}
