package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        List<User> users = new LinkedList<User>();
        users.add(new User(1, "Wang Tao", "000-0001", "password", "wangtao@example.com", "+861023456789"));
        users.add(new User(2, "Miffy Liye", "000-0002", "password", "miffyliye@example.com", "+861098765432"));

        userService = new UserService();
        userService.setUsers(users);
    }

    @Test
    public void should_find_user_by_library_number() {
        assertEquals("Wang Tao", userService.findByLibriaryNumber("000-0001").getName());
    }

    @Test
    public void should_pass_auth_if_password_is_correct() {
        assertTrue(userService.authUser("000-0001", "password"));
    }

    @Test
    public void should_not_pass_auth_if_library_number_and_password_not_match() {
        assertFalse(userService.authUser("000-0001", "pwd"));
        assertFalse(userService.authUser("000-0000", "password"));
    }
}