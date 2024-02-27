package com.example.integratewithpostgresql.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "sales")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Sales implements Serializable {
    @Id
    UUID id;
    String name;
    String price;
}
