package com.gabriel.backend_challenge.entity.enuns;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RoleEnum {
    ADMIN("Admin"),
    MEMBER("Member"),
    EXTERNAL("External");

    private final String role;

    RoleEnum(String role) {
        this.role = role;
    }

    @JsonValue
    public String getRole() {
        return role;
    }

    @JsonCreator
    public static RoleEnum fromString(String value) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.role.equalsIgnoreCase(value)) {
                return roleEnum;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }
}

