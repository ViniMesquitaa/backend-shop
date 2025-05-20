package com.shop.cleaning.dev.controller;

import com.shop.cleaning.dev.dtos.request.ProductRequestDto;
import com.shop.cleaning.dev.dtos.request.ProductUpdateRequestDto;
import com.shop.cleaning.dev.dtos.response.ProductResponseDto;
import com.shop.cleaning.dev.services.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("product")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto savedProduct = productService.addProduct(productRequestDto);
        URI location = URI.create("/product/" + savedProduct.id());
        return ResponseEntity.created(location).body(savedProduct);
    }

    @GetMapping
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @GetMapping("/active")
    public ResponseEntity<List<ProductResponseDto>> getActiveProducts() {
        List<ProductResponseDto> products = productService.getAllProductsActive();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<ProductResponseDto>> getInactiveProducts() {
        List<ProductResponseDto> products = productService.getAllProductsInactive();
        return ResponseEntity.ok(products);
    }



    @PutMapping("/activate/{id}")
    public ResponseEntity<Void> activateProduct(@PathVariable String id) {
        productService.activateProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/inactivate/{id}")
    public ResponseEntity<Void> inactivateProduct(@PathVariable String id) {
        productService.inactivateProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ProductResponseDto> editProduct(@PathVariable String id, @RequestBody ProductUpdateRequestDto productUpdateRequestDto) {
        ProductResponseDto updateProductResponseDto = productService.updateInfoProduct(id, productUpdateRequestDto);
        return ResponseEntity.ok(updateProductResponseDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

}
