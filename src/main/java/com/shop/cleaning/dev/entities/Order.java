package com.shop.cleaning.dev.entities;

import com.shop.cleaning.dev.entities.enuns.StatusRequestedEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private StatusRequestedEnum status;
    @CreationTimestamp
    private Instant orderCreatedDate;

    private BigDecimal total;

    public Order() {}
}
