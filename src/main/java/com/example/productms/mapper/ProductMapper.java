package com.example.productms.mapper;

import com.example.productms.dao.entity.ProductEntity;
import com.example.productms.dto.ProductRequestDto;
import com.example.productms.dto.ProductResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponseDto toResponseDto(ProductEntity productEntity);
    ProductEntity toEntity(ProductRequestDto productRequestDto);

}
