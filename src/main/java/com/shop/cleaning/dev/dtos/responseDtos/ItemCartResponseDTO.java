package com.shop.cleaning.dev.dtos.responseDtos;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemCartResponseDTO(UUID id, ProductResponseDTO productResponse, Integer itemQuantity, BigDecimal unitPrice, BigDecimal totalItem, boolean active) {


}
