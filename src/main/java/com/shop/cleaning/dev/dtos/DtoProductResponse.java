package com.shop.cleaning.dev.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record DtoProductResponse(UUID id, String img, String name, String description, BigDecimal price, boolean active) {

    public DtoProductResponse(UUID id, String img, String name, String description, BigDecimal price, boolean active) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = active;
    }


}
