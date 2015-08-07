package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.User;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;

public class UserService {
    private List<User> users;

    public UserService() {
        this.users = new LinkedList<User>();
        users.add(new User(1, "Wang Tao", "000-0001", "password", "wangtao@example.com", "+861023456789"));
        users.add(new User(2, "Miffy Liye", "000-0002", "password", "miffyliye@example.com", "+861098765432"));

    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User findByLibriaryNumber(String number) {
        for (User user : users) {
            if (user.getLibrary_number().equals(number)) {
                return user;
            }
        }
        return null;
    }

    public boolean authUser(String library_number, String password) {
        User user = findByLibriaryNumber(library_number);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        else {
            return false;
        }
    }
}
