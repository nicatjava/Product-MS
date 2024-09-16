package com.example.productms.service;

import com.example.productms.dto.ProductRequestDto;
import com.example.productms.dto.ProductResponseDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> findAll();
    ProductResponseDto findById(Long id);
    ProductResponseDto save(ProductRequestDto productRequestDto);
    ProductResponseDto update(Long id, ProductRequestDto productRequestDto);
    String delete(Long id);

    Integer getProductCountById(Long id);
    Double getProductPrice(Long productId);
    void updateProductCount(Long productId, Integer newCount);
}