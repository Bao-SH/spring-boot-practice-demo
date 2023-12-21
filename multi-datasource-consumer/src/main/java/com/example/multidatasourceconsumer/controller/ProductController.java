package com.example.multidatasourceconsumer.controller;

import com.example.multidatasourceconsumer.dto.ProductDto;
import com.example.multidatasourceconsumer.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/product")
    public UUID createHello(@RequestBody ProductDto productDto) {
        return productService.create(productDto);
    }
}
