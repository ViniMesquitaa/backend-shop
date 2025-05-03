package com.shop.cleaning.dev.services;

import com.shop.cleaning.dev.dtos.requestDtos.ProductUpdateRequestDTO;
import com.shop.cleaning.dev.dtos.responseDtos.ProductResponseDTO;
import com.shop.cleaning.dev.dtos.requestDtos.ProductRequestDTO;
import com.shop.cleaning.dev.entities.Product;
import com.shop.cleaning.dev.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //create new product
    @Transactional
    public UUID addProduct(ProductRequestDTO productRequestDTO) {
        Product product = new Product(UUID.randomUUID(), productRequestDTO.img(), productRequestDTO.name(), productRequestDTO.description(), productRequestDTO.price(), productRequestDTO.quantityStock(), productRequestDTO.active(), Instant.now(), null);
        var savedProduct = productRepository.save(product);
        return savedProduct.getId();
    }

    //get product by id
    public ProductResponseDTO getProductById(UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product Not Found"));
        return new ProductResponseDTO(product.getId(), product.getImg(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantityStock(), product.getAtivo());
    }

    //get product, but just the actives products
    public List<ProductResponseDTO> getAllProductsActive() {
        return productRepository.findAllByAtivoTrue().stream().map(product -> new ProductResponseDTO(product.getId(), product.getImg(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantityStock(), product.getAtivo())).collect(Collectors.toList());
    }

    //get all products
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream().map(product -> new ProductResponseDTO(product.getId(), product.getImg(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantityStock(), product.getAtivo())).collect(Collectors.toList());
    }

    //delete products by id
    @Transactional
    public void deleteProductById(UUID productId) {
        if (!productRepository.existsById(productId)) {
            throw new RuntimeException("Product Not Found");
        }
        productRepository.deleteById(productId);
    }

    //inactive products by id
    @Transactional
    public void inactivateProduct(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));

        if (!product.getAtivo()) {
            throw new RuntimeException("Produto ja esta inativado");
        }

        product.setAtivo(false);
        productRepository.save(product);
    }

    //active products by id
    @Transactional
    public void activateProduct(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));


        if (product.getAtivo()) {
            throw new RuntimeException("Produto ja esta ativado");
        }

        product.setAtivo(true);
        productRepository.save(product);
    }

    //update infos product
    @Transactional
    public ProductResponseDTO updateInfoProduct(UUID id, ProductUpdateRequestDTO productUpdateDtoRequest) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found"));
        product.setName(productUpdateDtoRequest.name());
        product.setDescription(productUpdateDtoRequest.description());
        product.setPrice(productUpdateDtoRequest.price());
        product.setQuantityStock(productUpdateDtoRequest.quantityStock());
        product.setAtivo(productUpdateDtoRequest.ativo());

        Product updatedProduct = productRepository.save(product);

        return new ProductResponseDTO(updatedProduct.getId(), updatedProduct.getImg(), updatedProduct.getName(), updatedProduct.getDescription(), updatedProduct.getPrice(), updatedProduct.getQuantityStock(), updatedProduct.getAtivo());

    }


}
