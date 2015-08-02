package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class WelcomeMessageTest {
    private WelcomeMessage welcomeMessage;

    @Before
    public void SetUp() {
        welcomeMessage = new WelcomeMessage();
    }

    @Test
    public void welcome_message_should_not_be_null() throws Exception {
        assertNotNull(welcomeMessage);
        assertNotNull(welcomeMessage.get());
    }

    @Test
    public void welcome_message_should_contain_welcome() {
        assertTrue(welcomeMessage.get().toLowerCase().contains("welcome"));
    }

    @Test
    public void welcome_message_should_show_program_is_ready() {
        assertTrue(welcomeMessage.get().toLowerCase().contains("ready"));
    }
}