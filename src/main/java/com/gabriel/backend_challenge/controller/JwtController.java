package com.gabriel.backend_challenge.controller;


import com.gabriel.backend_challenge.exception.BadRequestException;
import com.gabriel.backend_challenge.service.ipml.JwtServiceIpml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class JwtController {

    private final JwtServiceIpml jwtServiceIpml = new JwtServiceIpml();

    private static final String BEAR = "Bearer ";
    private static final String AUTH = "Authorization";
    private static final Integer BEGININDEX = 7;

    @PostMapping("/v1/decode")
    public ResponseEntity<Boolean> decodeJwtToken(@RequestBody String token) {
        if (token == null) {
            log.error("Token cannot be null");
            throw new BadRequestException("Token cannot be null");
        }
        Boolean result = jwtServiceIpml.validateToken(token);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/v2/decode")
    public ResponseEntity<Boolean> decodeJwtTokenv2(@RequestHeader(AUTH) String bearerToken) {
        if (bearerToken == null || !bearerToken.startsWith(BEAR)) {
            log.error("Invalid or null bearer token");
            throw new BadRequestException("Token cannot be null");
        }
        String token = bearerToken.substring(BEGININDEX);
        Boolean result = jwtServiceIpml.validateToken(token);
        return ResponseEntity.ok(result);
    }
}
