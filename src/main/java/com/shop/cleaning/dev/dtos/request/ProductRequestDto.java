package com.shop.cleaning.dev.dtos.request;

import java.math.BigDecimal;

public record ProductRequestDto(String id, String img,String name,  String category,  String description, Double price, Integer stock, Boolean active) {
}
