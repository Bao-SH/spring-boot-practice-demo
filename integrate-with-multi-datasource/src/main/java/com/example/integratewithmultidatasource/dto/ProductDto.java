package com.example.integratewithmultidatasource.dto;

import jakarta.validation.constraints.NotNull;

public record ProductDto(
    @NotNull String name,
    @NotNull Double price) {
}
