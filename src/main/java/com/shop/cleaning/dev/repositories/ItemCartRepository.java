package com.shop.cleaning.dev.repositories;

import com.shop.cleaning.dev.entities.Cart;
import com.shop.cleaning.dev.entities.ItemCart;
import com.shop.cleaning.dev.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemCartRepository extends JpaRepository<ItemCart, UUID> {
    List<ItemCart> findByClientId(UUID clientId);
    ItemCart findByProductAndCartShop(Product product, Cart cart);
}
