package com.example.integratewithmultidatasource.mapper;

import com.example.integratewithmultidatasource.dto.UserDto;
import com.example.integratewithmultidatasource.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    UserEntity toUserEntity(UserDto userDto);
}
