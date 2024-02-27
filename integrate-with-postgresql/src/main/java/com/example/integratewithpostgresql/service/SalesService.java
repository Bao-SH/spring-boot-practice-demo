package com.example.integratewithpostgresql.service;

import com.example.integratewithpostgresql.domain.Sales;
import com.example.integratewithpostgresql.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final SalesRepository salesRepository;

    @Cacheable("salesCache")
    public Sales findById(UUID id) {
        return salesRepository.findById(id).orElse(null);
    }
}
