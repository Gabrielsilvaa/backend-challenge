package com.gabriel.backend_challenge.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gabriel.backend_challenge.entity.enuns.RoleEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;



@Getter
@Setter
@Slf4j
@RequiredArgsConstructor
public class UserDto {

    @JsonProperty("Role")
    @NotNull(message = "Role cannot be null")
    private RoleEnum role;

    @JsonProperty("Seed")
    @NotNull(message = "Seed cannot be null")
    @Min(value = 0, message = "Seed must be greater than 1")
    private Integer seed;

    @JsonProperty("Name")
    @NotNull(message = "Name cannot be null")
    @Size(max = 256, message = "Name cannot be longer than 256 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name can only contain letters")
    private String name;

}

