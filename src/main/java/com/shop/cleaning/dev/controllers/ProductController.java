package com.shop.cleaning.dev.controllers;

import com.shop.cleaning.dev.dtos.requestDtos.ProductUpdateRequestDTO;
import com.shop.cleaning.dev.dtos.responseDtos.ProductResponseDTO;
import com.shop.cleaning.dev.dtos.requestDtos.ProductRequestDTO;
import com.shop.cleaning.dev.services.ItemCartService;
import com.shop.cleaning.dev.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
     final ItemCartService itemCartService;
     final ProductService productService;

    public ProductController(ItemCartService itemCartService, ProductService productService) {
        this.itemCartService = itemCartService;
        this.productService = productService;
    }

    //create new product
    @PostMapping("/register")
    public ResponseEntity<ProductRequestDTO> createProduct(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        var productId = productService.addProduct(productRequestDTO);
        return ResponseEntity.created(URI.create("/product/" + productId.toString())).build();
    }

    //get product by id
    @GetMapping( "/{productId}")
    public ResponseEntity<ProductResponseDTO> getProductsById(@PathVariable UUID productId) {
        ProductResponseDTO productResponse = productService.getProductById(productId);
        return ResponseEntity.ok(productResponse);
    }

    //get product, but only active products
    @GetMapping("/active")
    public ResponseEntity<List<ProductResponseDTO>> getActiveProducts() {
        List<ProductResponseDTO> products = productService.getAllProductsActive();
        return ResponseEntity.ok(products);
    }

    //get all products
    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/del/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable @Valid UUID productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/active/{id}")
    public ResponseEntity<Void> activateProduct(@PathVariable UUID id) {
        productService.activateProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/inactive/{id}")
    public ResponseEntity<Void> inactivateProduct(@PathVariable UUID id) {
        productService.inactivateProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/edit/{idProduct}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable UUID idProduct, @Valid @RequestBody ProductUpdateRequestDTO updateProduct){
        ProductResponseDTO updateDtoProduct = productService.updateInfoProduct(idProduct, updateProduct);
        return ResponseEntity.ok(updateDtoProduct);
    }


}
