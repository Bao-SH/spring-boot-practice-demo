package com.example.multidatasourceconsumer.service;

import com.example.multidatasourceconsumer.dto.ProductDto;
import com.example.multidatasourceconsumer.mapper.ProductMapper;
import com.example.multidatasourceconsumer.repository.snowflake.SnowflakeRepository;
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
