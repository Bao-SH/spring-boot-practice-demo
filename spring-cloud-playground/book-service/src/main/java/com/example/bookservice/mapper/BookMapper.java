package com.example.bookservice.mapper;

import com.example.bookservice.entity.BookEntity;
import com.example.bookservice.request.CreateBookRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "userId", ignore = true)
    BookEntity toEntity (CreateBookRequest createBookRequest);
}
