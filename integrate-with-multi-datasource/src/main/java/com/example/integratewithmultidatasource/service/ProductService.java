package com.example.integratewithmultidatasource.service;

import com.example.integratewithmultidatasource.dto.ProductDto;
import com.example.integratewithmultidatasource.mapper.ProductMapper;
import com.example.integratewithmultidatasource.repository.snowflake.SnowflakeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final SnowflakeRepository snowflakeRepository;

    public UUID create(ProductDto productDto) {
        return snowflakeRepository.save(ProductMapper.INSTANCE.toProductEntity(productDto)).getId();
    }
}
