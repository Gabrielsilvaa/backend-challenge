package com.gabriel.backend_challenge.controller;


import com.gabriel.backend_challenge.service.ipml.JwtServiceIpml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@RestController
@RequestMapping("/api")
public class JwtController {

    private final JwtServiceIpml jwtServiceIpml = new JwtServiceIpml();

    private static final String BEAR = "Bearer ";
    private static final String AUTH = "Authorization";
    private static final Integer BEGININDEX = 7;

    @PostMapping("/decode/v1")
    public ResponseEntity<Boolean> decodeJwtToken(@RequestBody String token) {
        if (token == null) {
            log.error("Token cannot be null");
            throw new IllegalArgumentException("Token cannot be null");
        }
        Boolean result = jwtServiceIpml.decodeJwt(token);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/decode")
    public ResponseEntity<Boolean> decodeJwtTokenv2(@RequestHeader(AUTH) String bearerToken) {
        if (bearerToken == null || !bearerToken.startsWith(BEAR)) {
            log.error("Invalid or null bearer token");
            throw new IllegalArgumentException("Invalid or null bearer token");
        }
        String token = bearerToken.substring(BEGININDEX);
        Boolean result = jwtServiceIpml.decodeJwt(token);
        return ResponseEntity.ok(result);
    }
}
