package com.shop.cleaning.dev.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor

@Entity
@Table(name = "produtos")
public class Product {

    @Id
    private UUID id;

    private String img;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean ativo;
    private Integer quantityStock;
    private Instant creationTimesStamp;
    private Instant updateTimesStamp;



    public Product(UUID uuid, String img, String name, String description, BigDecimal price,Integer quantityStock,  boolean active, Instant creationTimesStamp, Instant updateTimesStamp) {
        this.id = uuid;
        this.img = img;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityStock = quantityStock;
        this.ativo = active;
        this.creationTimesStamp = Instant.now();
        this.updateTimesStamp = Instant.now();
    }

    public Product(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getQuantityStock() {
        return quantityStock;
    }

    public void setQuantityStock(Integer quantityStock) {
        this.quantityStock = quantityStock;
    }


}
