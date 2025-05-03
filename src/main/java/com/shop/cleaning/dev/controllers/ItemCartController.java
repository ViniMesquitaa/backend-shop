package com.shop.cleaning.dev.controllers;

import com.shop.cleaning.dev.dtos.responseDtos.ItemCartResponseDTO;
import com.shop.cleaning.dev.services.ItemCartService;
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
public class ItemCartController {
    private final ItemCartService itemCartService;

    @Autowired
    public ItemCartController(ItemCartService itemCartService) {
        this.itemCartService = itemCartService;
    }

    @GetMapping("items/{clientId}")
    public ResponseEntity<List<ItemCartResponseDTO>> getCartItems(@PathVariable UUID clientId) {
        List<ItemCartResponseDTO> cartItems = itemCartService.getProductsCartByClient(clientId);
        return ResponseEntity.ok(cartItems);
    }

}
