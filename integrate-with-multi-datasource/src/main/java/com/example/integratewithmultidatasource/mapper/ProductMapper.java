package com.example.integratewithmultidatasource.mapper;

import com.example.integratewithmultidatasource.dto.ProductDto;
import com.example.integratewithmultidatasource.dto.UserDto;
import com.example.integratewithmultidatasource.entity.product.ProductEntity;
import com.example.integratewithmultidatasource.entity.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", ignore = true)
    ProductEntity toProductEntity(ProductDto productDto);
}
