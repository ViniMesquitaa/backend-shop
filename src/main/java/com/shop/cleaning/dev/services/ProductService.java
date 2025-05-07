package com.shop.cleaning.dev.services;

import com.shop.cleaning.dev.dtos.request.ProductRequestDto;
import com.shop.cleaning.dev.dtos.request.ProductUpdateRequestDto;
import com.shop.cleaning.dev.dtos.response.ProductResponseDto;
import com.shop.cleaning.dev.entities.Product;
import com.shop.cleaning.dev.repositories.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public String generateUniqueProductId() {
        List<Product> existingProducts = productRepo.findAll();

        int maxProductNumber = existingProducts.stream().mapToInt(product -> {
            String id = product.getId();
            if (id.startsWith("PROD") && id.length() > 4) {
                try {
                    return Integer.parseInt(id.substring(4));
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
            return 0;
        }).max().orElse(0);

        return "PROD" + (maxProductNumber + 1);
    }

    @Transactional
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        String generatedId = generateUniqueProductId();

        Product product = new Product(generatedId, productRequestDto.img(), productRequestDto.name(), productRequestDto.description(), productRequestDto.price(), productRequestDto.stock(), productRequestDto.active(), Instant.now(), null);

        var productSaved = productRepo.save(product);

        return new ProductResponseDto(productSaved.getId(), productSaved.getImg(), productSaved.getName(), productSaved.getDescription(), productSaved.getPrice(), productSaved.getStock(), productSaved.getActive(), productSaved.getCreateTime(), null);
    }


    public ProductResponseDto getProductById(String id) {
        var product = productRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return new ProductResponseDto(product.getId(), product.getImg(), product.getName(), product.getDescription(), product.getPrice(), product.getStock(), product.getActive(), product.getCreateTime(), null);
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepo.findAll().stream().map(product -> new ProductResponseDto(product.getId(), product.getImg(), product.getName(), product.getDescription(), product.getPrice(), product.getStock(), product.getActive(), product.getCreateTime(), product.getUpdateTime())).collect(Collectors.toList());
    }

    public List<ProductResponseDto> getAllProductsActive() {
        return productRepo.findAllByActiveTrue().stream().map(product -> new ProductResponseDto(product.getId(), product.getImg(), product.getName(), product.getDescription(), product.getPrice(), product.getStock(), product.getActive(), product.getCreateTime(), product.getUpdateTime())).collect(Collectors.toList());
    }




    @Transactional
    public ProductResponseDto updateInfoProduct(String id, ProductUpdateRequestDto productUpdateDtoRequest) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found"));
        product.setImg(productUpdateDtoRequest.img());
        product.setName(productUpdateDtoRequest.name());
        product.setDescription(productUpdateDtoRequest.description());
        product.setPrice(productUpdateDtoRequest.price());
        product.setStock(productUpdateDtoRequest.stock());

        Product updatedProduct = productRepo.save(product);

        return new ProductResponseDto(updatedProduct.getId(), updatedProduct.getImg(), updatedProduct.getName(), updatedProduct.getDescription(), updatedProduct.getPrice(), updatedProduct.getStock(), updatedProduct.getActive(), updatedProduct.getCreateTime(), updatedProduct.getUpdateTime());

    }

    @Transactional
    public void activateProduct(String id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));


        if (product.getActive()) {
            throw new RuntimeException("Produto ja esta ativado");
        }

        product.setActive(true);
        productRepo.save(product);
    }

    @Transactional
    public void inactivateProduct(String id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com id: " + id));

        if (!product.getActive()) {
            throw new EntityNotFoundException("Produto ja esta inativado");
        }

        product.setActive(false);
        productRepo.save(product);
    }

    @Transactional
    public void deleteProductById(String productId) {
        if (!productRepo.existsById(productId)) {
            throw new RuntimeException("Product Not Found");
        }
        productRepo.deleteById(productId);
    }
}
