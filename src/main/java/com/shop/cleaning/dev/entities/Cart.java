package com.shop.cleaning.dev.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

@Table(name = "Carrinho")
@Entity
public class Cart {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "cartShop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCart> itemsCart;

    private BigDecimal total;

}
