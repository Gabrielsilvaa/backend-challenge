package com.gabriel.backend_challenge.handlers.validation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gabriel.backend_challenge.entity.dto.UserDto;
import com.gabriel.backend_challenge.handlers.TokenValidationHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import static com.gabriel.backend_challenge.entity.adapter.UserAdapter.decodeToken;

@Slf4j
public class TokenStructureValidationHandler implements TokenValidationHandler {
    private TokenValidationHandler next;

    private TokenObjectStructureValidationHandler tokenObjectStructureValidationHandler = new TokenObjectStructureValidationHandler();

    @Override
    public void setNext(TokenValidationHandler next) {
        this.next = next;
    }

    @Override
    public void handle(String token) throws RuntimeException {
        try {
            isTokenStructureValid(token);
            UserDto userDto = decodeToken(token);
            isTokenObjectStructureValid(token);

            tokenObjectStructureValidationHandler.validateFields(userDto);

            if (next != null) {
                next.handle(token);
            }
        } catch (RuntimeException e) {
            log.error("Invalid token structure or object: " + e.getMessage());
            throw e;
        }
    }

    public void isTokenStructureValid(String token) {
        try {
        JWT.decode(token);
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public void isTokenObjectStructureValid(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        List<String> expectedClaims = Arrays.asList("Role", "Seed", "Name");
        Set<String> remainingClaims = new HashSet<>(claims.keySet());
        remainingClaims.removeAll(expectedClaims);

        if (remainingClaims.size() > 0) {
            throw new RuntimeException("Invalid object structure in token");
        }
    }
}