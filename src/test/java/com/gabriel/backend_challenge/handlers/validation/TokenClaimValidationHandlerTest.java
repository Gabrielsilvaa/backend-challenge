package com.gabriel.backend_challenge.handlers.validation;

import com.gabriel.backend_challenge.entity.dto.UserDto;
import com.gabriel.backend_challenge.entity.enuns.RoleEnum;
import com.gabriel.backend_challenge.handlers.Context;
import com.gabriel.backend_challenge.handlers.TokenValidationHandler;
import com.gabriel.backend_challenge.testUtil.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class TokenClaimValidationHandlerTest {

    @InjectMocks
    private TokenClaimValidationHandler tokenClaimValidationHandler;

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
    @DisplayName("validateFields should throw IllegalArgumentException when given a UserDto with invalid fields")
    void validateFieldsShouldThrowIllegalArgumentExceptionWhenGivenUserDtoWithInvalidFields() {
        UserDto userDto = new UserDto(); // Set invalid fields here
        assertThrows(IllegalArgumentException.class, () -> tokenClaimValidationHandler.validateFields(userDto));
    }

    @Test
    @DisplayName("validateFields should not throw any exception when given a UserDto with valid fields")
    void validateFieldsShouldNotThrowAnyExceptionWhenGivenUserDtoWithValidFields() {
        UserDto userDto = new UserDto();
        userDto.setRole(randomRole);
        userDto.setSeed(randomPrime);
        userDto.setName(radomName);
        assertDoesNotThrow(() -> tokenClaimValidationHandler.validateFields(userDto));
    }

    @Test
    @DisplayName("isValidSeed should throw IllegalArgumentException when given an invalid number")
    void isValidSeedShouldThrowIllegalArgumentExceptionWhenGivenInvalidNumber() {
        int invalidNumber = 0; // Set invalid number here
        assertThrows(IllegalArgumentException.class, () -> tokenClaimValidationHandler.isValidSeed(invalidNumber));
    }

    @Test
    @DisplayName("isValidSeed should not throw any exception when given a valid number")
    void isValidSeedShouldNotThrowAnyExceptionWhenGivenValidNumber() {
        int validNumber = 3; // Set valid number here
        assertDoesNotThrow(() -> tokenClaimValidationHandler.isValidSeed(validNumber));
    }
}