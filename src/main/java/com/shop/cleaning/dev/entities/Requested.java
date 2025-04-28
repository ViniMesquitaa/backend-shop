package com.shop.cleaning.dev.entities;

import com.shop.cleaning.dev.entities.enuns.StatusRequestedEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Table(name = "pedido")
@Entity
public class Requested {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @CreationTimestamp
    private Instant createTimeStamp;
    private BigDecimal totalPrice;
    private StatusRequestedEnum status;

    @OneToMany(mappedBy = "requested", cascade = CascadeType.ALL)
    public List<ItemCart> items = new ArrayList<>();
}
