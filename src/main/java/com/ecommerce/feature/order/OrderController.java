package com.ecommerce.feature.order;

import com.ecommerce.feature.order.dto.CreateOrderRequest;
import com.ecommerce.feature.order.dto.DeleteOrderRequest;
import com.ecommerce.feature.order.dto.OrderResponse;
import com.ecommerce.feature.order.dto.SetPaymentStatusRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    OrderResponse createNew(@Valid @RequestBody CreateOrderRequest request) {
        return orderService.createNew(request);
    }

    @GetMapping
    Page<OrderResponse> findAll(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "15") int pageSize
    ) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        return orderService.findAll(pageRequest);
    }

    @GetMapping("/{id}")
    OrderResponse findById(@PathVariable UUID id) {
        return orderService.findById(id);
    }

    @PatchMapping("/{id}/soft-delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void softDeleteById(
            @PathVariable UUID id,
            @Valid @RequestBody DeleteOrderRequest request) {
        orderService.softDeleteById(id, request);
    }

    @DeleteMapping("/{id}/hard-delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void hardDeleteById(@PathVariable UUID id) {
        orderService.hardDeleteById(id);
    }

    @PatchMapping("/{id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void setPaymentStatusById(
            @PathVariable UUID id,
            @Valid @RequestBody SetPaymentStatusRequest request) {
        orderService.setPaymentStatus(id, request);
    }
}

