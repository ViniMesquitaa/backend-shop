package com.shop.cleaning.dev.dtos.response;

import com.shop.cleaning.dev.entities.utilClass.Address;

import java.time.Instant;
import java.util.UUID;

public record CustomerResponseDto(UUID id,
                                  String fullName,
                                  Address address,
                                  Instant createTime,
                                  Instant updateTime) {
}
