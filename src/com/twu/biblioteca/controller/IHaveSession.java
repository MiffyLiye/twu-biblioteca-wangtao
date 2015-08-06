package com.twu.biblioteca.controller;

import java.util.Map;

public interface IHaveSession {
    public Map<String, Object> getSession();
    public void setSession(Map<String, Object> session);
}
