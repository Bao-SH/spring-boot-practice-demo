package com.example.multidatasourceconsumer.dto;

import jakarta.validation.constraints.NotNull;

public record OrderDto(
    @NotNull String name) {
}
