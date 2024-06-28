package com.gabriel.backend_challenge.service;

import org.springframework.stereotype.Service;

@Service
public interface JwtService {
    boolean decodeJwt(String token);

}
