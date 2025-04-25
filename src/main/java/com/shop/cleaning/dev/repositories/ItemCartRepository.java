package com.shop.cleaning.dev.repositories;

import com.shop.cleaning.dev.entities.ItemCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemCartRepository extends JpaRepository<ItemCart, UUID> {
    List<ItemCart> findByClientId(UUID clientId);
}
