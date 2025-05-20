package com.shop.cleaning.dev.dtos.response;

import java.time.Instant;

public record ProductResponseDto(
        String id,
        String img,
        String name,
        String category,
        String description,
        Double price,
        int stock,
        boolean active,
        Instant createTime,
        Instant updateTime
) {}
