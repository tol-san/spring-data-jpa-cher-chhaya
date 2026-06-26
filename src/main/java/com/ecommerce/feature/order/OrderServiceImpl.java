package com.ecommerce.feature.order;

import com.ecommerce.feature.order.dto.CreateOrderRequest;
import com.ecommerce.feature.order.dto.DeleteOrderRequest;
import com.ecommerce.feature.order.dto.OrderResponse;
import com.ecommerce.feature.order.dto.SetPaymentStatusRequest;
import com.ecommerce.feature.product.Product;
import com.ecommerce.feature.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    @Override
    public OrderResponse createNew(CreateOrderRequest createOrderRequest) {

        List<OrderLine> orderLines = new ArrayList<>();
        Order order = orderMapper.toEntity(createOrderRequest);

        boolean isValidOrder = createOrderRequest.orderLineList().stream()
                .allMatch(orderLineDto -> {
                    Optional<Product> productOptional = productRepository.findByCode(orderLineDto.code());



                    if (productOptional.isPresent()) {
                        OrderLine orderLine = new OrderLine();
                        orderLine.setProduct(productOptional.get());
                        orderLine.setQuantity(orderLineDto.quantity());
                        orderLine.setUnitPrice(orderLineDto.unitPrice());
                        orderLine.setIsDeleted(false);
                        orderLine.setOrder(order);
                        orderLines.add(orderLine);

                        return true;
                    }
                    return false;
                });
        if (!isValidOrder) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid order line.");
        }

        order.setCustomerId("Random CDI0009");
        order.setAddress(createOrderRequest.address());
        order.setIsDeleted(false);
        order.setStatus(false);
        order.setDiscount(order.getDiscount());
        order.setRemark(order.getRemark());
        order.setOrderedAt(LocalDateTime.now());
        order.setOrderLines(orderLines);

        var newOrder = orderRepository.save(order);

        return orderMapper.toDto(newOrder);
    }

    @Override
    public Page<OrderResponse> findAll(PageRequest pageRequest) {
        return orderRepository.findAll(pageRequest)
                .map(orderMapper::toDto);
    }

    @Override
    public OrderResponse findById(UUID id) {
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Order not found"
        ));
        return orderMapper.toDto(order);
    }

    @Override
    public void softDeleteById(UUID id, DeleteOrderRequest request) {
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order not found."
                ));
        order.setIsDeleted(request.isDeleted());
        orderRepository.save(order);
    }

    @Override
    public void hardDeleteById(UUID id) {
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order not found."
                ));
        orderRepository.deleteById(order.getId());
    }

    @Override
    public void setPaymentStatus(UUID id, SetPaymentStatusRequest request) {
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order not found."
                ));
       order.setStatus(request.status());
       orderRepository.save(order);
    }
}
