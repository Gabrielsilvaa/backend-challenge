package com.gabriel.backend_challenge.handlers.validation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gabriel.backend_challenge.entity.dto.UserDto;
import com.gabriel.backend_challenge.handlers.Context;
import com.gabriel.backend_challenge.handlers.TokenValidationHandler;
import lombok.extern.slf4j.Slf4j;

import static com.gabriel.backend_challenge.entity.adapter.UserAdapter.decodeToken;

@Slf4j
public class TokenStructureValidationHandler implements TokenValidationHandler {
    private TokenValidationHandler next;

    @Override
    public void setNext(TokenValidationHandler next) {
        this.next = next;
    }

    @Override
    public void handle(Context context) throws RuntimeException {
        try {
            UserDto userDto = decodeToken(isTokenStructureValid(context.getToken()));
            context.setUserDto(userDto);
            if (next != null) {
                next.handle(context);
            }
        } catch (RuntimeException e) {
            log.error("Invalid token structure or object: " + e.getMessage());
            throw e;
        }
    }

    public DecodedJWT isTokenStructureValid(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT;
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

}