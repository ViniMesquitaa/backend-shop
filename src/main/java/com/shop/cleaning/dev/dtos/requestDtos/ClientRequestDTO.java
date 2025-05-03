package com.shop.cleaning.dev.dtos.requestDtos;

import com.shop.cleaning.dev.entities.utilClass.Address;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ClientRequestDTO(
                                @Id
                                UUID id,
                                @NotBlank
                                String name,
                                Address address


) {
}
