package com.gabriel.backend_challenge.entity.adapter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gabriel.backend_challenge.entity.dto.UserDto;
import com.gabriel.backend_challenge.entity.enuns.RoleEnum;

public class UserAdapter {

    private UserAdapter() {
    }

    public static UserDto decodeToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        UserDto userDto = new UserDto();
        userDto.setRole(RoleEnum.valueOf(decodedJWT.getClaim("Role").asString().toUpperCase()));
        userDto.setSeed(Integer.valueOf(decodedJWT.getClaim("Seed").asString()));
        userDto.setName(decodedJWT.getClaim("Name").asString());
        return userDto;
    }
}
