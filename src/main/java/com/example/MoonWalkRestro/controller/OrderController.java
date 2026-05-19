package com.example.MoonWalkRestro.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import com.example.MoonWalkRestro.dto.CreateOrderRequest;
import com.example.MoonWalkRestro.dto.OrderResponse;
import com.example.MoonWalkRestro.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "Order APIs", description = "Operations related to restaurant orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Place a new order")
    public OrderResponse placeOrder(
            @RequestBody CreateOrderRequest request) {

        return orderService.placeOrder(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order details by ID")
    public OrderResponse getOrder(@PathVariable Long id) {

        return orderService.getOrder(id);
    }
}
