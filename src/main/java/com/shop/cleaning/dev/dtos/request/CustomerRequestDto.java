package com.shop.cleaning.dev.dtos.request;

import com.shop.cleaning.dev.entities.utilClass.Address;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.Instant;
import java.util.UUID;

public record CustomerRequestDto(
        @Id
        UUID id,
        @NotBlank
        String fullName,
        @NotBlank
        @Column(unique = true)
        String phoneNumber,
        @Valid
        Address address,
        @NotBlank
        @Column(unique = true)
        String userName,
        @NotBlank
        String password,
        String token) {
}
