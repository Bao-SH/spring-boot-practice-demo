package com.example.bookservice.request;


import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class UpdateBookRequest {
    private UUID id;
    private String userId;
}
