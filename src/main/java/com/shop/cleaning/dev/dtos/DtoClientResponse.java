package com.shop.cleaning.dev.dtos;

import com.shop.cleaning.dev.entities.Address;

import java.time.Instant;
import java.util.UUID;

public record DtoClientResponse(UUID id,
                                String name,
                                Address address,
                                Instant timeStamp,
                                Instant updateTimeStamp) {
}
