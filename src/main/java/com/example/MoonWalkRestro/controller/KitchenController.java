package com.example.MoonWalkRestro.controller;

import com.example.MoonWalkRestro.entity.CustomerOrder;
import com.example.MoonWalkRestro.enums.OrderStatus;
import com.example.MoonWalkRestro.repository.CustomerOrderRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kitchen")
@RequiredArgsConstructor
@Tag(name = "Kitchen APIs", description = "Kitchen monitoring APIs")
public class KitchenController {

    private final CustomerOrderRepository orderRepository;

    @GetMapping("/active-orders")
    @Operation(summary = "Get all active PREPARING orders")
    public List<CustomerOrder> getActiveOrders() {

        return orderRepository.findByStatus(
                OrderStatus.PREPARING
        );
    }

    @GetMapping("/pending-orders")
    @Operation(summary = "Get all active Pending orders")
    public List<CustomerOrder> getPendingOrders() {

        return orderRepository.findByStatus(
                OrderStatus.PLACED
        );
    }

    @GetMapping("/ready-orders")
    public List<CustomerOrder> getReadyOrders() {

        return orderRepository.findByStatus(
                OrderStatus.READY
        );
    }
}
