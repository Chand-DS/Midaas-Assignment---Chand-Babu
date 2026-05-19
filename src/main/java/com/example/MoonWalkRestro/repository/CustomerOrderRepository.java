package com.example.MoonWalkRestro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MoonWalkRestro.entity.CustomerOrder;
import com.example.MoonWalkRestro.enums.OrderStatus;

import java.util.List;

public interface CustomerOrderRepository
        extends JpaRepository<CustomerOrder, Long> {

    List<CustomerOrder> findByStatus(OrderStatus status);

    List<CustomerOrder> findByStatusOrderByCreatedAtAsc(
            OrderStatus status
    );

    long countByStatus(OrderStatus status);
}