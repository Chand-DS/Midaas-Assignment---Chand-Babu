package com.example.MoonWalkRestro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MoonWalkRestro.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
