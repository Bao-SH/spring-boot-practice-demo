package com.example.integratewithpostgresql.controller;

import com.example.integratewithpostgresql.domain.Sales;
import com.example.integratewithpostgresql.service.SalesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/hello")
@AllArgsConstructor
public class HelloController {

    private final SalesService service;

    @GetMapping("/{sales_id}")
    public Sales getSales(@PathVariable("sales_id") UUID uuid) {
        return service.findById(uuid);
    }
}
