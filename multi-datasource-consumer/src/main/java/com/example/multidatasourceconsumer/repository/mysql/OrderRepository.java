package com.example.multidatasourceconsumer.repository.mysql;

import com.example.multidatasourceconsumer.entity.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
}
