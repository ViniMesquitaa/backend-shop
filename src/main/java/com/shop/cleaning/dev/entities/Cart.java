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

    @Column(name = "total", nullable = false)
    private BigDecimal total = BigDecimal.ZERO;







    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<ItemCart> getItemsCart() {
        return itemsCart;
    }

    public void setItemsCart(List<ItemCart> itemsCart) {
        this.itemsCart = itemsCart;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
