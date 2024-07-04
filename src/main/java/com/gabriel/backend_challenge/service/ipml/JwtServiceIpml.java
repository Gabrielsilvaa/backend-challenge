package com.gabriel.backend_challenge.service.ipml;

import com.gabriel.backend_challenge.handlers.Context;
import com.gabriel.backend_challenge.handlers.TokenValidationHandler;
import com.gabriel.backend_challenge.handlers.validation.TokenClaimValidationHandler;
import com.gabriel.backend_challenge.handlers.validation.TokenStructureValidationHandler;
import com.gabriel.backend_challenge.service.JwtService;
import org.springframework.stereotype.Component;


@Component
public class JwtServiceIpml implements JwtService {

    @Override
    public boolean validateToken(String token) {
        try {
            TokenValidationHandler structureHandler = new TokenStructureValidationHandler();
            TokenValidationHandler objectStructureHandler = new TokenClaimValidationHandler();
            structureHandler.setNext(objectStructureHandler);
            structureHandler.handle(new Context(token));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

