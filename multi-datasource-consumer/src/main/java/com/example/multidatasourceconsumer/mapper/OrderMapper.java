package com.example.multidatasourceconsumer.mapper;

import com.example.multidatasourceconsumer.dto.OrderDto;
import com.example.multidatasourceconsumer.entity.order.OrderEntity;
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
