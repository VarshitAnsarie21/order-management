package com.ordermanagementsystem.controller;

import com.ordermanagementsystem.dto.OrderRequestDTO;
import com.ordermanagementsystem.entity.Order;
import com.ordermanagementsystem.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order placeOrder(@Valid @RequestBody OrderRequestDTO orderRequest) {
        return orderService.placeOrder(orderRequest);
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getOrdersByCustomer(@PathVariable Long customerId) {
        return orderService.getOrdersByCustomer(customerId);
    }
}