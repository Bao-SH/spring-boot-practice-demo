package com.example.integratewithpostgresql.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "sales")
@Getter
public class Sales {
    @Id
    UUID id;
    String name;
    String price;
}
