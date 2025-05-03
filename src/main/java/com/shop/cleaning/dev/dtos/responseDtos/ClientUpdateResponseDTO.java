package com.shop.cleaning.dev.dtos.responseDtos;

import com.shop.cleaning.dev.entities.utilClass.Address;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ClientUpdateDtoResponse(
        @Id
        UUID id,
        @NotBlank
        String name,
        @NotBlank
        Address address) {
}
