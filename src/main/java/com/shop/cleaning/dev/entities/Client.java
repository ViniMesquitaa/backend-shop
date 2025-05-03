package com.shop.cleaning.dev.entities;

import com.shop.cleaning.dev.entities.utilClass.Address;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Setter
@Getter
@Entity
@Table(name = "Cliente")
public class Client {
    @Id
    private UUID id;

    private String name;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Cart> cart;

    @CreationTimestamp
    private Instant creationTimesStamp;
    @UpdateTimestamp
    private Instant updateTimesStamp;


    public Client(UUID id, String name, Address address, Instant creationTimesStamp, Instant updateTimesStamp) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.creationTimesStamp = Instant.now();
        this.updateTimesStamp = Instant.now();

    }

    public Client() {

    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getCreationTimesStamp() {
        return creationTimesStamp;
    }

    public void setCreationTimesStamp(Instant creationTimesStamp) {
        this.creationTimesStamp = creationTimesStamp;
    }

    public Instant getUpdateTimesStamp() {
        return updateTimesStamp;
    }

    public void setUpdateTimesStamp(Instant updateTimesStamp) {
        this.updateTimesStamp = updateTimesStamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }
}
