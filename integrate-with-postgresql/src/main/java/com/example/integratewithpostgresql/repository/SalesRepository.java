package com.example.integratewithpostgresql.repository;

import com.example.integratewithpostgresql.domain.Sales;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SalesRepository extends CrudRepository<Sales, UUID> {
}
