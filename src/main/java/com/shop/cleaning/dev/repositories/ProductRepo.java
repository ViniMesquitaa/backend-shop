package com.shop.cleaning.dev.repositories;

import com.shop.cleaning.dev.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, String> {
    @Query("SELECT MAX(p.id) FROM Product p")
    String findMaxId();

    List<Product> findAllByActiveTrue();
}
