package com.shop.cleaning.dev.services;

import com.shop.cleaning.dev.dtos.responseDtos.ItemCartResponseDTO;
import com.shop.cleaning.dev.dtos.responseDtos.ProductResponseDTO;
import com.shop.cleaning.dev.repositories.ItemCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemCartService {
    private final ItemCartRepository itemCartRepository;

    @Autowired
    public ItemCartService(ItemCartRepository itemCartRepository) {
        this.itemCartRepository = itemCartRepository;
    }


    //visualizar os itens do carrinho por usuario individual
    public List<ItemCartResponseDTO> getProductsCartByClient(UUID clientId) {
        return itemCartRepository.findByClientId(clientId).stream()
                .map(itemCart -> new ItemCartResponseDTO(
                        itemCart.getId(),
                        new ProductResponseDTO(
                                itemCart.getProduct().getId(),
                                itemCart.getProduct().getImg(),
                                itemCart.getProduct().getName(),
                                itemCart.getProduct().getDescription(),
                                itemCart.getProduct().getPrice(),
                                itemCart.getProduct().getQuantityStock(),
                                itemCart.getProduct().getAtivo()
                        ),
                        itemCart.getItemQuantity(),
                        itemCart.getPrecoUnitario(),
                        itemCart.getTotalItem(),
                        itemCart.getAtivo()
                ))
                .collect(Collectors.toList());
    }
}
