package com.gabriel.backend_challenge.controller;

import com.gabriel.backend_challenge.service.ipml.JwtServiceIpml;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
class JwtControllerTest {

    @InjectMocks
    JwtController jwtController;

    @MockBean
    JwtServiceIpml jwtServiceIpml;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    @DisplayName("Should decode JWT token successfully")
//    void shouldDecodeJwtToken() {
//        String token = "testToken";
//        when(jwtServiceIpml.decodeJwt(token)).thenReturn(true);
//        Boolean result = jwtController.decodeJwtToken(token);
//        assertTrue(result);
//        verify(jwtServiceIpml, times(1)).decodeJwt(token);
//    }

    @Test
    @DisplayName("Should not decode JWT token when token is null")
    void shouldNotDecodeJwtTokenWhenTokenIsNull() {
        String token = null;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            jwtController.decodeJwtToken(token);
        });

        String expectedMessage = "Token cannot be null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

//    @Test
//    @DisplayName("Should decode JWT token v2 successfully")
//    void shouldDecodeJwtTokenv2() {
//        String bearerToken = "Bearer testToken";
//        String token = "testToken";
//        when(jwtServiceIpml.decodeJwt(token)).thenReturn(true);
//        Boolean result = jwtController.decodeJwtTokenv2(bearerToken);
//        assertTrue(result);
//        verify(jwtServiceIpml, times(1)).decodeJwt(token);
//    }

    @Test
    @DisplayName("Should not decode JWT token v2 when bearer token is invalid")
    void shouldNotDecodeJwtTokenv2WhenBearerTokenIsInvalid() {
        String bearerToken = "Invalid testToken";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            jwtController.decodeJwtTokenv2(bearerToken);
        });

        String expectedMessage = "Invalid or null bearer token";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}