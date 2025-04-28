package com.shop.cleaning.dev.dtos.requestDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductUpdateDtoRequest(@NotBlank String name,
                                      @NotBlank String description,
                                      @NotBlank String img,
                                      @NotNull BigDecimal price,
                                      @NotNull Integer quantityStock,
                                      @NotNull Boolean ativo){
}
