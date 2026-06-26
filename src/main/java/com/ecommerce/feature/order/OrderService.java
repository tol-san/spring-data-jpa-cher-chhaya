package com.ecommerce.feature.order;

import com.ecommerce.feature.order.dto.CreateOrderRequest;
import com.ecommerce.feature.order.dto.DeleteOrderRequest;
import com.ecommerce.feature.order.dto.OrderResponse;
import com.ecommerce.feature.order.dto.SetPaymentStatusRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponse createNew(CreateOrderRequest createOrderRequest);
    Page<OrderResponse> findAll(PageRequest pageRequest);
    OrderResponse findById(UUID id);
    void softDeleteById(UUID id, DeleteOrderRequest request);
    void hardDeleteById(UUID id);
    void setPaymentStatus(UUID id, SetPaymentStatusRequest request);
}
