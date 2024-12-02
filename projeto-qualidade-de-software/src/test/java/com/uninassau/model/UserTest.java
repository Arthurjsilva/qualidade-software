package com.uninassau.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest {

    @Test
    public void testUserModel() {
        User user = new User();
        user.setId(1L);
        user.setName("Arthur Oliveira");
        user.setEmail("Arthur.Oliveira@example.com");
        user.setPassword("password");

        assertEquals(1L, user.getId());
        assertEquals("Arthur Oliveira", user.getName());
        assertEquals("Arthur.Oliveira@example.com", user.getEmail());
        assertEquals("password", user.getPassword());

        assertNotNull(user.toString());
    }

    @Test
    public void testUserConstructor() {
        User user = new User("John Doe", "john.doe@example.com", "password1");

        assertEquals("Arthur Oliveira", user.getName());
        assertEquals("Arthur.Oliveira@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
    }
}
