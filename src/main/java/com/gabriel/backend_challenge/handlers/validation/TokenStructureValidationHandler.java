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

    private TokenClaimValidationHandler tokenClaimValidationHandler = new TokenClaimValidationHandler();

    @Override
    public void setNext(TokenValidationHandler next) {
        this.next = next;
    }

    @Override
    public void handle(String token) throws RuntimeException {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            isTokenStructureValid(token);
            UserDto userDto = decodeToken(decodedJWT);

            tokenClaimValidationHandler.validateFields(userDto);

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

}