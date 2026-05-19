package com.example.MoonWalkRestro.service;

import com.example.MoonWalkRestro.dto.CreateOrderRequest;
import com.example.MoonWalkRestro.dto.OrderResponse;

public interface OrderService {

    OrderResponse placeOrder(CreateOrderRequest request);

    OrderResponse getOrder(Long orderId);
}
