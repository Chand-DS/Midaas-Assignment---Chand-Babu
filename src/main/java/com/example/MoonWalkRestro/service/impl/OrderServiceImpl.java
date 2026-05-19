package com.example.MoonWalkRestro.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.MoonWalkRestro.dto.CreateOrderRequest;
import com.example.MoonWalkRestro.dto.OrderItemRequest;
import com.example.MoonWalkRestro.dto.OrderResponse;
import com.example.MoonWalkRestro.entity.CustomerOrder;
import com.example.MoonWalkRestro.entity.Dish;
import com.example.MoonWalkRestro.entity.OrderItem;
import com.example.MoonWalkRestro.enums.OrderStatus;
import com.example.MoonWalkRestro.repository.CustomerOrderRepository;
import com.example.MoonWalkRestro.repository.DishRepository;
import com.example.MoonWalkRestro.service.ExecutionLogService;
import com.example.MoonWalkRestro.service.OrderService;
import com.example.MoonWalkRestro.strategy.EstimationStrategy;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CustomerOrderRepository orderRepository;
    private final DishRepository dishRepository;
    private final EstimationStrategy estimationStrategy;
    private final ExecutionLogService executionLogService;

    @Override
    public OrderResponse placeOrder(CreateOrderRequest request) {

        CustomerOrder order = new CustomerOrder();
        order.setCustomerName(request.getCustomerName());
        order.setStatus(OrderStatus.PLACED);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> items = new ArrayList<>();

        for (OrderItemRequest itemRequest : request.getItems()) {

            Dish dish = dishRepository.findById(itemRequest.getDishId())
                    .orElseThrow(() -> new RuntimeException("Dish not found"));

            OrderItem item = OrderItem.builder()
                    .dish(dish)
                    .quantity(itemRequest.getQuantity())
                    .order(order)
                    .build();

            items.add(item);
        }

        order.setItems(items);

        int estimatedMinutes =
                estimationStrategy.estimatePreparationTime(order);

        order.setEstimatedReadyTime(
                LocalDateTime.now().plusMinutes(estimatedMinutes)
        );

        CustomerOrder savedOrder = orderRepository.save(order);

        executionLogService.logExecution(
                savedOrder.getId(),
                estimatedMinutes,
                0,
                savedOrder.getStatus().name(),
                estimationStrategy.getStrategyName(),
                "Order placed successfully"
        );

        return buildResponse(savedOrder);
    }

    @Override
    public OrderResponse getOrder(Long orderId) {

        CustomerOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return buildResponse(order);
    }

    private OrderResponse buildResponse(CustomerOrder order) {

    long countdown = 0;

    if (order.getStatus() != OrderStatus.READY) {

        countdown =
                ChronoUnit.SECONDS.between(
                        LocalDateTime.now(),
                        order.getEstimatedReadyTime()
                );
    }

    return OrderResponse.builder()
            .orderId(order.getId())
            .status(order.getStatus().name())
            .estimatedReadyTime(order.getEstimatedReadyTime())
            .actualReadyTime(order.getActualReadyTime())
            .countdownInSeconds(Math.max(countdown, 0))
            .build();
}
}