package com.example.bookservice.controller;


import com.example.bookservice.entity.BookEntity;
import com.example.bookservice.mapper.BookMapper;
import com.example.bookservice.repository.BookRepository;
import com.example.bookservice.request.CreateBookRequest;
import com.example.bookservice.request.UpdateBookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping("/{id}")
    public BookEntity getBookById(@PathVariable UUID id) {
        Optional<BookEntity> bookEntity = bookRepository.findById(id);
        return bookEntity.orElse(null);
    }

    @PostMapping()
    public UUID createBook(@RequestBody CreateBookRequest createBookRequest) {
        return bookRepository.save(BookMapper.INSTANCE.toEntity(createBookRequest)).getId();
    }

    //todo: should change the endpoint
    @PostMapping("/update")
    public BookEntity updateBookInfo(@RequestBody UpdateBookRequest updateBookRequest) {
        BookEntity bookEntity = bookRepository.findById(updateBookRequest.getId()).orElseThrow();
        bookEntity.setUserId(updateBookRequest.getUserId());
        bookEntity.setStatus("BORROWED");
        return bookRepository.save(bookEntity);
    }
}
