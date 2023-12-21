package com.example.multidatasourceconsumer.controller;

import com.example.multidatasourceconsumer.dto.OrderDto;
import com.example.multidatasourceconsumer.mapper.OrderMapper;
import com.example.multidatasourceconsumer.repository.mysql.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;

    @PostMapping("/order")
    public UUID create(@RequestBody OrderDto orderDto) {
        return orderRepository.save(OrderMapper.INSTANCE.toOrderEntity(orderDto)).getId();
    }
}
