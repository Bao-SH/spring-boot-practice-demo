package com.example.integratewithmysql.mapper;

import com.example.integratewithmysql.dto.UserDto;
import com.example.integratewithmysql.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    UserEntity toUserEntity(UserDto userDto);
}
