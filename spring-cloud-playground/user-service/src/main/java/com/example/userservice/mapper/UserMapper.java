package com.example.userservice.mapper;

import com.example.userservice.dto.User;
import com.example.userservice.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User toUser (UserEntity userEntity);
}
