package com.example.transactionservice.request;

import lombok.Data;

import java.util.UUID;

@Data
public class TransactionRequest {
    private String type;
    private UUID bookId;
    private UUID userId;
}
