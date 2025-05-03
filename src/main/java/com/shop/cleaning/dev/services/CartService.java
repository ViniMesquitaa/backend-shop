package com.shop.cleaning.dev.services;

import com.shop.cleaning.dev.dtos.requestDtos.CreateItemCartDTO;
import com.shop.cleaning.dev.entities.Cart;
import com.shop.cleaning.dev.entities.Client;
import com.shop.cleaning.dev.entities.ItemCart;
import com.shop.cleaning.dev.entities.Product;
import com.shop.cleaning.dev.repositories.CartRepository;
import com.shop.cleaning.dev.repositories.ClientRepository;
import com.shop.cleaning.dev.repositories.ItemCartRepository;
import com.shop.cleaning.dev.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final ItemCartRepository itemCartRepository;

    public CartService(CartRepository cartRepository, ClientRepository clientRepository, ProductRepository productRepository, ItemCartRepository itemCartRepository) {
        this.cartRepository = cartRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.itemCartRepository = itemCartRepository;
    }

    @Transactional
    public void addProductToCart(CreateItemCartDTO createItemCartDTO) {
        // Verifica se o carrinho existe
        Cart cart = cartRepository.findById(createItemCartDTO.idCart())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Verifica se o cliente existe
        Client client = clientRepository.findById(createItemCartDTO.idClient())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        // Verifica se o produto existe
        Product product = productRepository.findById(createItemCartDTO.idProduct())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Verifica se o produto já existe no carrinho
        ItemCart existingItemCart = itemCartRepository.findByProductAndCartShop(product, cart);

        if (existingItemCart != null) {
            throw new RuntimeException("Product " + product.getName() + " already exists in the cart");
        }

        // Verifica se o produto está disponível no estoque
        if (product.getQuantityStock() == 0) {
            throw new RuntimeException("Product " + product.getName() + " is out of stock");
        }

        // Verifica se a quantidade solicitada é menor ou igual ao estoque
        if (product.getQuantityStock() < createItemCartDTO.quantity()) {
            throw new RuntimeException("Please, make an order for " + product.getName() +
                    " less than or equal to the available quantity of " + product.getQuantityStock());
        }



        // Cria o novo item no carrinho
        ItemCart newItemCart = new ItemCart();
        newItemCart.setProduct(product);
        newItemCart.setCartShop(cart);
        newItemCart.setItemQuantity(createItemCartDTO.quantity());
        newItemCart.setPrecoUnitario(product.getPrice());

        // Salva o item no carrinho
        itemCartRepository.save(newItemCart);

        // Atualiza o estoque do produto
        product.setQuantityStock(product.getQuantityStock() - createItemCartDTO.quantity());
        productRepository.save(product);

        // Atualiza o total do carrinho
        BigDecimal totalPrice = cart.getTotal() != null ? cart.getTotal() : BigDecimal.ZERO;
        totalPrice = totalPrice.add(product.getPrice().multiply(BigDecimal.valueOf(createItemCartDTO.quantity())));

        // Atualiza o total do carrinho
        cart.setTotal(totalPrice);
        cartRepository.save(cart);
    }
}
