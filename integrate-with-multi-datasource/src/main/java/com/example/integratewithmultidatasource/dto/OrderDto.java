package com.example.integratewithmultidatasource.dto;

import jakarta.validation.constraints.NotNull;

public record OrderDto(
    @NotNull String name) {
}
