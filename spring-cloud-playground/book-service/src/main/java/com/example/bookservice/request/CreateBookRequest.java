package com.example.bookservice.request;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateBookRequest {
    private String name;
    private String author;
}
