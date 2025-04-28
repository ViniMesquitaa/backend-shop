package com.shop.cleaning.dev.controllers;

import com.shop.cleaning.dev.dtos.responseDtos.DtoItemCartResponse;
import com.shop.cleaning.dev.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("items/{clientId}")
    public ResponseEntity<List<DtoItemCartResponse>> getCartItems(@PathVariable UUID clientId) {
        List<DtoItemCartResponse> cartItems = cartService.getProductsCartByClient(clientId);
        return ResponseEntity.ok(cartItems);
    }

}
