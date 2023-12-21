package com.example.multidatasourceconsumer.mapper;

import com.example.multidatasourceconsumer.dto.ProductDto;
import com.example.multidatasourceconsumer.entity.product.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", ignore = true)
    ProductEntity toProductEntity(ProductDto productDto);
}
