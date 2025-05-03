package com.shop.cleaning.dev.controllers;

import com.shop.cleaning.dev.dtos.requestDtos.CreateItemCartDTO;
import com.shop.cleaning.dev.services.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<CreateItemCartDTO> createItemCart(@RequestBody CreateItemCartDTO createItemCartDTO ) {
        cartService.addProductToCart(createItemCartDTO);
        return ResponseEntity.ok().body(createItemCartDTO);
    }
}
