package com.gabriel.backend_challenge.service.ipml;

import com.gabriel.backend_challenge.handlers.TokenValidationHandler;
import com.gabriel.backend_challenge.handlers.validation.TokenObjectStructureValidationHandler;
import com.gabriel.backend_challenge.handlers.validation.TokenStructureValidationHandler;
import com.gabriel.backend_challenge.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtServiceIpml implements JwtService {



    @Override
    public boolean decodeJwt(String token) {
        try {
            TokenValidationHandler structureHandler = new TokenStructureValidationHandler();
            TokenValidationHandler objectStructureHandler = new TokenObjectStructureValidationHandler();
            structureHandler.setNext(objectStructureHandler);
            structureHandler.handle(token);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

}

