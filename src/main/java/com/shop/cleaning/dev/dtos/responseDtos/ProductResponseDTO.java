package com.shop.cleaning.dev.dtos.responseDtos;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDTO(UUID id, String img, String name, String description, BigDecimal price, Integer quantityStock, boolean active) {

    public ProductResponseDTO(UUID id, String img, String name, String description, BigDecimal price, Integer quantityStock, boolean active) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityStock = quantityStock;
        this.active = active;
    }


}
