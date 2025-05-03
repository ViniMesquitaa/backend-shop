package com.shop.cleaning.dev.dtos.responseDtos;

import java.math.BigDecimal;
import java.util.UUID;

public record DtoItemCartResponse(UUID id, DtoProductResponse productResponse, Integer itemQuantity, BigDecimal unitPrice, BigDecimal totalItem, boolean active) {


}
