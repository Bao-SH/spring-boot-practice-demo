package com.example.integratewithmysql.dto;

import jakarta.validation.constraints.NotNull;

public record UserDto(
    @NotNull String name,
    @NotNull String email) {
}
