package com.gabriel.backend_challenge.handlers.validation;

import com.gabriel.backend_challenge.handlers.TokenValidationHandler;
import com.gabriel.backend_challenge.handlers.validation.TokenStructureValidationHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TokenStructureValidationHandlerTest {

    @InjectMocks
    private TokenStructureValidationHandler tokenStructureValidationHandler;

    @Mock
    private TokenValidationHandler nextHandler;

    @Mock
    private Logger log;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("handle should throw RuntimeException when given a token with invalid structure")
    void handleShouldThrowRuntimeExceptionWhenGivenTokenWithInvalidStructure() {
        String invalidStructureToken = "invalid_structure_token";
        assertThrows(RuntimeException.class, () -> tokenStructureValidationHandler.handle(invalidStructureToken));
    }

    @Test
    @DisplayName("handle should throw RuntimeException when given a token with invalid object structure")
    void handleShouldThrowRuntimeExceptionWhenGivenTokenWithInvalidObjectStructure() {
        String invalidObjectStructureToken = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY";
        assertThrows(RuntimeException.class, () -> tokenStructureValidationHandler.handle(invalidObjectStructureToken));
    }



    @Test
    @DisplayName("handle should not throw RuntimeException when given a token with valid object structure")
    void handleShouldNotThrowRuntimeExceptionWhenGivenTokenWithValidObjectStructure() {
        String validObjectStructureToken ="eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
        assertDoesNotThrow(() -> tokenStructureValidationHandler.handle(validObjectStructureToken));
    }

    @Test
    @DisplayName("handle should not throw RuntimeException when given a token with valid structure")
    void handleShouldNotThrowRuntimeExceptionWhenGivenTokenWithValidStructure() {
        String validStructureToken =  "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
        assertDoesNotThrow(() -> tokenStructureValidationHandler.handle(validStructureToken));
    }

    @Test
    @DisplayName("handle should pass the token to the next handler when given a valid token")
    void handleShouldPassTokenToNextHandlerWhenGivenValidToken() {
        String validToken = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
        tokenStructureValidationHandler.handle(validToken);
        verify(nextHandler).handle(validToken);
    }

    @Test
    @DisplayName("handle should not call the next handler when given an invalid token")
    void handleShouldNotCallNextHandlerWhenGivenInvalidToken() {
        String invalidToken = "invalid_token";
        assertThrows(RuntimeException.class, () -> tokenStructureValidationHandler.handle(invalidToken));
        verify(nextHandler, never()).handle(anyString());
    }
}