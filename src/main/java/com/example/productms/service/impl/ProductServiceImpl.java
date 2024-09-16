package com.example.productms.service.impl;

import com.example.productms.dao.entity.ProductEntity;
import com.example.productms.dao.repository.ProductRepository;
import com.example.productms.dto.ProductRequestDto;
import com.example.productms.dto.ProductResponseDto;
import com.example.productms.exception.ProductNotFoundException;
import com.example.productms.mapper.ProductMapper;
import com.example.productms.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponseDto> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponseDto)
                .toList();
    }

    @Override
    public ProductResponseDto findById(Long id) {
        ProductEntity productEntity = getProductById(id);
        return productMapper.toResponseDto(productEntity);
    }

    @Override
    public ProductResponseDto save(ProductRequestDto productRequestDto) {
        ProductEntity productEntity = productMapper.toEntity(productRequestDto);
        ProductEntity savedProduct = productRepository.save(productEntity);
        return productMapper.toResponseDto(savedProduct);
    }

    @Override
    public ProductResponseDto update(Long id, ProductRequestDto productRequestDto) {
        ProductEntity existingProduct = getProductById(id);
        ProductEntity productEntity = productMapper.toEntity(productRequestDto);
        productEntity.setId(existingProduct.getId()); // Ensuring the ID remains the same
        ProductEntity updatedProduct = productRepository.save(productEntity);
        return productMapper.toResponseDto(updatedProduct);
    }

    @Override
    public String delete(Long id) {
        ProductEntity productEntity = getProductById(id);
        productRepository.delete(productEntity);
        return "Deleted id: " + id;
    }

    private ProductEntity getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    @Override
    public Integer getProductCountById(Long id) {
        ProductEntity productEntity = getProductById(id);
        return productEntity.getCount();
    }

    @Override
    public Double getProductPrice(Long productId) {
        ProductEntity productEntity = getProductById(productId);
        return productEntity.getPrice();
    }

    @Override
    public void updateProductCount(Long productId, Integer newCount) {
        ProductEntity productEntity = getProductById(productId);
        productEntity.setCount(newCount);
        productRepository.save(productEntity);
    }
}
