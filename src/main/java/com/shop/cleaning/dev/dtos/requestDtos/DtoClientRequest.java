package com.shop.cleaning.dev.dtos;

import com.shop.cleaning.dev.entities.Address;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

public record DtoClientRequest(
                                @Id
                                UUID id,
                                @NotBlank
                                String name,
                                Address address


) {
}
