package com.gabriel.backend_challenge.handlers.validation;

import com.gabriel.backend_challenge.handlers.TokenValidationHandler;
import com.gabriel.backend_challenge.entity.dto.UserDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;
import java.util.stream.Collectors;

public class TokenObjectStructureValidationHandler implements TokenValidationHandler {
    private TokenValidationHandler next;

    @Override
    public void setNext(TokenValidationHandler next) {
        this.next = next;
    }

    @Override
    public void handle(String token) {
        if (next != null) {
            next.handle(token);
        }
    }

    protected void validateFields(UserDto userDto) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        if (!violations.isEmpty()) {
            String errorMessage = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            throw new IllegalArgumentException(errorMessage);
        }
        isValidSeed(userDto.getSeed());
    }

    protected void isValidSeed(int number) {
        if (number <= 1) {
            throw new IllegalArgumentException("Error: Seed must be greater than 1");
        }
        if (number % 2 == 0) {
            throw new IllegalArgumentException("Error: Seed not allowed");
        }
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                throw new IllegalArgumentException("Error: Seed not allowed");
            }
        }
    }
}