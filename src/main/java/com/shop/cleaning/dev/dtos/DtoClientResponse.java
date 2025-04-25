package com.shop.cleaning.dev.dtos;

import com.shop.cleaning.dev.entities.Address;

import java.util.UUID;

public record DtoClient(UUID id, String name, Address address) {
}
