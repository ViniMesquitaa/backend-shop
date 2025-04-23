package com.shop.cleaning.dev.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "produtos")
public class Product {

    @Id
    @GeneratedValue
    private UUID id;

    private String img;
    private String name;
    private String description;
    private BigDecimal price;

    private boolean ativo;

    private Integer quantityStock;

}
