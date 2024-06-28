package com.gabriel.backend_challenge.service;

import com.gabriel.backend_challenge.service.ipml.JwtServiceIpml;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtServiceIpmlTest {

    @InjectMocks
    private JwtServiceIpml jwtServiceIpml;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("decodeJwt should return true when given a valid token")
    void decodeJwtShouldReturnTrueWhenGivenValidToken() {
        String validToken = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
        boolean result = jwtServiceIpml.decodeJwt(validToken);
        assertTrue(result);
    }

    @Test
    @DisplayName("decodeJwt should return false when given an invalid token")
    void decodeJwtShouldReturnFalseWhenGivenInvalidToken() {
        String invalidToken = "invalid_token";
        boolean result = jwtServiceIpml.decodeJwt(invalidToken);
        assertFalse(result);
    }

    @Test
    @DisplayName("decodeJwt should return false when given a null token")
    void decodeJwtShouldReturnFalseWhenGivenNullToken() {
        String nullToken = null;
        boolean result = jwtServiceIpml.decodeJwt(nullToken);
        assertFalse(result);
    }

    @Test
    @DisplayName("decodeJwt should return false when given an empty token")
    void decodeJwtShouldReturnFalseWhenGivenEmptyToken() {
        String emptyToken = "";
        boolean result = jwtServiceIpml.decodeJwt(emptyToken);
        assertFalse(result);
    }
}