package com.ordermanagementsystem.controller;

import com.ordermanagementsystem.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final OrderService orderService;

    public ReportController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/total-orders")
    public List<Map<String, Object>> getTotalOrdersByCustomer() {
        List<Object[]> results = orderService.getTotalOrdersByCustomer();
        List<Map<String, Object>> response = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("customerId", row[0]);
            map.put("totalOrders", row[1]);
            response.add(map);
        }
        return response;
    }

    @GetMapping("/top-customers")
    public List<Map<String, Object>> getTopCustomers() {
        List<Object[]> results = orderService.getTopCustomers();
        List<Map<String, Object>> response = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("customer", row[0]);
            map.put("totalOrders", row[1]);
            response.add(map);
        }
        return response;
    }
}