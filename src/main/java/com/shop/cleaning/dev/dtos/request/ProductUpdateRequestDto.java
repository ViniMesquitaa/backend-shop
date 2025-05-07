package com.shop.cleaning.dev.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.antlr.v4.runtime.misc.DoubleKeyMap;

import java.math.BigDecimal;

public record ProductUpdateRequestDto(      @NotBlank String name,
                                            @NotBlank String description,
                                            @NotBlank String img,
                                            @NotNull Double price,
                                            @NotNull Integer stock,
                                            @NotNull Boolean ativo) {
}
