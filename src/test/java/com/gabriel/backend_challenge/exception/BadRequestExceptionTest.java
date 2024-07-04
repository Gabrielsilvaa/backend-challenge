package com.gabriel.backend_challenge.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BadRequestExceptionTest {

    @Test
    @DisplayName("Should return the correct message when BadRequestException is thrown")
    public void shouldReturnCorrectMessageWhenBadRequestExceptionIsThrown() {
        String expectedMessage = "Test exception message";
        BadRequestException exception = new BadRequestException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage());
    }
}