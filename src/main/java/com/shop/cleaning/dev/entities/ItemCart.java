package com.shop.cleaning.dev.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter

@Entity
@Table(name = "itens_carrinho")
public class ItemCart {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private Integer itemQuantity;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cartShop;


    @ManyToOne
    @JoinColumn(name = "requested_id")
    private Requested requested;


    private BigDecimal precoUnitario;

    private BigDecimal totalItem;

    private boolean ativo;

    public boolean getAtivo() {
        return ativo;
    }

    public ItemCart() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Cart getCartShop() {
        return cartShop;
    }

    public void setCartShop(Cart cartShop) {
        this.cartShop = cartShop;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public BigDecimal getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(BigDecimal totalItem) {
        this.totalItem = totalItem;
    }
}
