package com.example.integratewithmultidatasource.repository.mysql;

import com.example.integratewithmultidatasource.entity.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
}
