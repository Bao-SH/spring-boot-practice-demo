package com.example.userservice.mapper;

import com.example.userservice.entity.UserEntity;
import com.example.userservice.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    UserEntity toEntity (UserRequest userRequest);
}
