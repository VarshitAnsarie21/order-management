package com.ordermanagementsystem.service;

import com.ordermanagementsystem.dto.OrderProductDTO;
import com.ordermanagementsystem.dto.OrderRequestDTO;
import com.ordermanagementsystem.entity.Customer;
import com.ordermanagementsystem.entity.Order;
import com.ordermanagementsystem.entity.OrderItem;
import com.ordermanagementsystem.entity.Product;
import com.ordermanagementsystem.exception.InsufficientStockException;
import com.ordermanagementsystem.exception.ResourceNotFoundException;
import com.ordermanagementsystem.repository.CustomerRepository;
import com.ordermanagementsystem.repository.OrderRepository;
import com.ordermanagementsystem.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Order placeOrder(OrderRequestDTO orderRequest) {
        Customer customer = customerRepository.findById(orderRequest.getCustomerId())
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (OrderProductDTO dto : orderRequest.getProducts()) {
            Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + dto.getProductId()));

            if (product.getStock() < dto.getQuantity()) {
                throw new InsufficientStockException("Insufficient stock for product: " + product.getName());
            }

            product.setStock(product.getStock() - dto.getQuantity());
            productRepository.save(product);

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(dto.getQuantity());
            item.setPrice(product.getPrice().multiply(BigDecimal.valueOf(dto.getQuantity())));
            orderItems.add(item);

            total = total.add(item.getPrice());
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setTotalAmount(total);
        order.setItems(new ArrayList<>());

        order = orderRepository.save(order);

        for (OrderItem item : orderItems) {
            item.setOrder(order);
        }
        order.getItems().addAll(orderItems);

        orderRepository.save(order);

        return order;
    }

    public List<Order> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public List<Object[]> getTotalOrdersByCustomer() {
        return orderRepository.countOrdersByCustomer();
    }

    public List<Object[]> getTopCustomers() {
        return orderRepository.findTopCustomers(PageRequest.of(0, 5));
    }
}