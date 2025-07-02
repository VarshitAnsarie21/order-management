package com.ordermanagementsystem.repository;

import com.ordermanagementsystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);

    @Query("SELECT o.customer.id, COUNT(o) FROM Order o GROUP BY o.customer.id")
    List<Object[]> countOrdersByCustomer();

    @Query("SELECT o.customer, COUNT(o) as cnt FROM Order o GROUP BY o.customer ORDER BY cnt DESC")
    List<Object[]> findTopCustomers(org.springframework.data.domain.Pageable pageable);
}