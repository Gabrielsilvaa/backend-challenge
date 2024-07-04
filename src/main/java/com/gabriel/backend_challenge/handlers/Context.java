package com.gabriel.backend_challenge.handlers;


import com.gabriel.backend_challenge.entity.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Context {

    public Context(String token) {
        this.token = token;
    }

    String token;
    UserDto userDto;

}
