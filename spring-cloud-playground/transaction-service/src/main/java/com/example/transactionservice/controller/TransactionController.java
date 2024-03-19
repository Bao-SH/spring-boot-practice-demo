package com.example.transactionservice.controller;

import com.example.transactionservice.request.TransactionRequest;
import com.example.transactionservice.response.BookResponse;
import com.example.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    //假如我是管理员，然后我要将某一用户借某本书
    //入参：书的id和用户名
    //影响：书的status和user
    @PostMapping()
    public BookResponse borrow(@RequestBody TransactionRequest transactionRequest) {
        if ("BORROW".equalsIgnoreCase(transactionRequest.getType())) {
            return transactionService.updateBookInfo(transactionRequest);
        }
        return null;
    }
}
