package com.example.transactionservice.service;

import com.example.transactionservice.request.TransactionRequest;
import com.example.transactionservice.request.UpdateBookRequest;
import com.example.transactionservice.response.BookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final WebClient.Builder builder;

    public BookResponse updateBookInfo(TransactionRequest transactionRequest) {
        return builder.build().post()
            .uri("http://book-service/books/update")
            .bodyValue(UpdateBookRequest.builder().id(transactionRequest.getBookId())
                .userId(transactionRequest.getUserId().toString())
                .build())
            .retrieve()
            .bodyToMono(BookResponse.class)
            .block();
    }
}
