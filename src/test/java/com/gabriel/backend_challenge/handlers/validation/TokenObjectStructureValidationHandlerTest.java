package com.gabriel.backend_challenge.handlers.validation;

import com.gabriel.backend_challenge.entity.dto.UserDto;
import com.gabriel.backend_challenge.entity.enuns.RoleEnum;
import com.gabriel.backend_challenge.handlers.TokenValidationHandler;
import com.gabriel.backend_challenge.handlers.validation.TokenObjectStructureValidationHandler;
import com.gabriel.backend_challenge.testUtil.TestUtil;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class TokenObjectStructureValidationHandlerTest {

    @InjectMocks
    private TokenObjectStructureValidationHandler tokenObjectStructureValidationHandler;

    @Mock
    private TokenValidationHandler nextHandler;

    RoleEnum randomRole = TestUtil.generateRandomRole();
    Integer randomPrime = TestUtil.generateRandomPrime(2, 100);
    String radomName = TestUtil.generateRandomName();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("handle should pass the token to the next handler when given a valid token")
    void handleShouldPassTokenToNextHandlerWhenGivenValidToken() {
        String validToken = "your_valid_token_here";
        tokenObjectStructureValidationHandler.handle(validToken);
        verify(nextHandler).handle(validToken);
    }

    @Test
    @DisplayName("validateFields should throw IllegalArgumentException when given a UserDto with invalid fields")
    void validateFieldsShouldThrowIllegalArgumentExceptionWhenGivenUserDtoWithInvalidFields() {
        UserDto userDto = new UserDto(); // Set invalid fields here
        assertThrows(IllegalArgumentException.class, () -> tokenObjectStructureValidationHandler.validateFields(userDto));
    }

    @Test
    @DisplayName("validateFields should not throw any exception when given a UserDto with valid fields")
    void validateFieldsShouldNotThrowAnyExceptionWhenGivenUserDtoWithValidFields() {
        UserDto userDto = new UserDto();
        userDto.setRole(randomRole);
        userDto.setSeed(randomPrime);
        userDto.setName(radomName);
        assertDoesNotThrow(() -> tokenObjectStructureValidationHandler.validateFields(userDto));
    }

    @Test
    @DisplayName("isValidSeed should throw IllegalArgumentException when given an invalid number")
    void isValidSeedShouldThrowIllegalArgumentExceptionWhenGivenInvalidNumber() {
        int invalidNumber = 0; // Set invalid number here
        assertThrows(IllegalArgumentException.class, () -> tokenObjectStructureValidationHandler.isValidSeed(invalidNumber));
    }

    @Test
    @DisplayName("isValidSeed should not throw any exception when given a valid number")
    void isValidSeedShouldNotThrowAnyExceptionWhenGivenValidNumber() {
        int validNumber = 3; // Set valid number here
        assertDoesNotThrow(() -> tokenObjectStructureValidationHandler.isValidSeed(validNumber));
    }
}