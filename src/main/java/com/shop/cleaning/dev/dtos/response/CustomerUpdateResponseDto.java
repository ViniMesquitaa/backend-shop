package com.shop.cleaning.dev.dtos.response;

import com.shop.cleaning.dev.entities.utilClass.Address;

import java.util.UUID;

public record CustomerUpdateResponseDto(UUID id, String fullName, String numberPhone, Address address) {
}
