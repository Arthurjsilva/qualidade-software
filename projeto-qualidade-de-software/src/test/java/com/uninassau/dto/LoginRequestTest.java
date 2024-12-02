package com.uninassau.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginRequestTest {

    @Test
    public void testLoginRequest() {
        LoginRequest loginRequest = new LoginRequest("arthur.oliveira@example.com", "password");

        assertEquals("arthur.oliveira@example.com", loginRequest.getEmail());
        assertEquals("password", loginRequest.getPassword());

        loginRequest.setPassword("newPassword");

        assertEquals("newPassword", loginRequest.getPassword());
    }
}
