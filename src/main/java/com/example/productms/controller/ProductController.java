package com.example.productms.controller;

import com.example.productms.dto.ProductRequestDto;
import com.example.productms.dto.ProductResponseDto;
import com.example.productms.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductResponseDto> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto) {
        return productService.save(productRequestDto);
    }

    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequestDto productRequestDto) {
        return productService.update(id, productRequestDto);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return productService.delete(id);
    }

    @GetMapping("/{productId}/count")
    public Integer getProductCount(@PathVariable Long productId) {
        return productService.getProductCountById(productId);
    }

    @GetMapping("/{productId}/price")
    public Double getProductPrice(@PathVariable Long productId) {
        return productService.getProductPrice(productId);
    }

    @PutMapping("/{productId}/count")
    void updateProductStock(@PathVariable("productId") Long productId, @RequestParam Integer newCount){
        productService.updateProductCount(productId, newCount);
    }

}
