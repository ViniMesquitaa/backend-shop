package com.shop.cleaning.dev.dtos.requestDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record ProductRequestDTO(

                                UUID id,
                                String img,
                                @NotBlank
                                String name,
                                @NotNull
                                String description,
                                @Positive
                                BigDecimal price,
                                @Positive
                                Integer quantityStock,
                                boolean active,
                                @CreationTimestamp
                                Instant CreationTimeStamp,
                                @UpdateTimestamp
                                Instant UpdateTimeStamp) {
}
