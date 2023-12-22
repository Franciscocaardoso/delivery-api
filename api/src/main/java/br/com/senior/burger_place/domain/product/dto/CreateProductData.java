package br.com.senior.burger_place.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateProductData(
        @NotBlank
        String name,
        @NotNull
        @Positive
        Double price,
        String description
) {
}