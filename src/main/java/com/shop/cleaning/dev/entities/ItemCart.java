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
@Table(name = "itens_carrinho")
public class ItemCart {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer itemQuantity;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cartShop;


    private BigDecimal precoUnitario;

    private BigDecimal totalItem;

    private boolean ativo;

}
