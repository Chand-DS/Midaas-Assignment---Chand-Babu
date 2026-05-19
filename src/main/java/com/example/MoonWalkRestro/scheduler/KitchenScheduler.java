package com.example.MoonWalkRestro.scheduler;

import com.example.MoonWalkRestro.entity.CustomerOrder;
import com.example.MoonWalkRestro.enums.OrderStatus;
import com.example.MoonWalkRestro.repository.CustomerOrderRepository;
import com.example.MoonWalkRestro.service.ExecutionLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class KitchenScheduler {

    private final CustomerOrderRepository orderRepository;
    private final ExecutionLogService executionLogService;

    /**
     * Runs every 5 seconds
     */
    @Scheduled(fixedDelay = 5000)
    public void processKitchen() {

        log.info("Kitchen scheduler running...");

        movePlacedOrdersToPreparing();

        movePreparingOrdersToReady();
    }

    /**
     * Pick oldest PLACED order
     * and move to PREPARING
     */
    private void movePlacedOrdersToPreparing() {

        List<CustomerOrder> placedOrders =
                orderRepository.findByStatusOrderByCreatedAtAsc(
                        OrderStatus.PLACED
                );

        if (placedOrders.isEmpty()) {
            return;
        }

        CustomerOrder order = placedOrders.get(0);

        order.setStatus(OrderStatus.PREPARING);

        orderRepository.save(order);

        executionLogService.logExecution(
                order.getId(),
                0,
                0,
                OrderStatus.PREPARING.name(),
                "KITCHEN_SCHEDULER",
                "Order moved to PREPARING"
        );

        log.info("Order {} moved to PREPARING", order.getId());
    }

    /**
     * Mark PREPARING orders READY
     * if ETA time has passed
     */
    private void movePreparingOrdersToReady() {

        List<CustomerOrder> preparingOrders =
                orderRepository.findByStatus(
                        OrderStatus.PREPARING
                );

        for (CustomerOrder order : preparingOrders) {

            if (LocalDateTime.now()
                    .isAfter(order.getEstimatedReadyTime())) {

                order.setStatus(OrderStatus.READY);

                order.setActualReadyTime(LocalDateTime.now());

                orderRepository.save(order);

                executionLogService.logExecution(
                        order.getId(),
                        0,
                        0,
                        OrderStatus.READY.name(),
                        "KITCHEN_SCHEDULER",
                        "Order completed successfully"
                );

                log.info("Order {} marked READY", order.getId());
            }
        }
    }
}