package com.example.integratewithmultidatasource.mapper;

import com.example.integratewithmultidatasource.dto.OrderDto;
import com.example.integratewithmultidatasource.entity.order.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    OrderEntity toOrderEntity(OrderDto orderDto);
}
