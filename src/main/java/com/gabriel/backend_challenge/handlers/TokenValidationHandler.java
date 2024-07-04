package com.gabriel.backend_challenge.handlers;

public interface TokenValidationHandler {
    void setNext(TokenValidationHandler next);
    void handle(Context context) throws RuntimeException;
}