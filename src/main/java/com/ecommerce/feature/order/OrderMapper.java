package com.ecommerce.feature.order;

import com.ecommerce.feature.order.dto.CreateOrderRequest;
import com.ecommerce.feature.order.dto.OrderResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse toDto(Order order);
    Order toEntity(CreateOrderRequest request);
}
