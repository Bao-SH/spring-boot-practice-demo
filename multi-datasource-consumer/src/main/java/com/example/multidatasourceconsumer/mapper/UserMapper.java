package com.example.multidatasourceconsumer.mapper;

import com.example.multidatasourceconsumer.dto.UserDto;
import com.example.multidatasourceconsumer.entity.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    UserEntity toUserEntity(UserDto userDto);
}
