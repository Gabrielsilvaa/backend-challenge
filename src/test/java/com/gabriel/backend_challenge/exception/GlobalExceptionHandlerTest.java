package com.gabriel.backend_challenge.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;
    private WebRequest webRequest;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
        webRequest = new ServletWebRequest(new MockHttpServletRequest());
    }

    @Test
    @DisplayName("Should return BAD_REQUEST status when BadRequestException is thrown")
    void shouldReturnBadRequestStatusWhenBadRequestExceptionIsThrown() {
        // Given
        BadRequestException exception = new BadRequestException("Bad request error");

        // When
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleBadRequestException(exception);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Bad request error", responseEntity.getBody());
    }
}