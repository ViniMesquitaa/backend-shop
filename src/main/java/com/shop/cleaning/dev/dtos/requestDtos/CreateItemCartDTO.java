package com.shop.cleaning.dev.dtos.requestDtos;

import com.shop.cleaning.dev.entities.Client;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateItemCartDTO(UUID idClient, UUID idProduct, UUID idCart, Integer quantity, BigDecimal total) {
}
