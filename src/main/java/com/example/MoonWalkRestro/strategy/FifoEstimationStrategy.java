package com.example.MoonWalkRestro.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.example.MoonWalkRestro.entity.CustomerOrder;
import com.example.MoonWalkRestro.entity.OrderItem;
import com.example.MoonWalkRestro.enums.OrderStatus;
import com.example.MoonWalkRestro.repository.CustomerOrderRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FifoEstimationStrategy implements EstimationStrategy {

    private final CustomerOrderRepository orderRepository;

    @Override
    public int estimatePreparationTime(CustomerOrder order) {

        int backlogTime = calculateBacklogTime();

        int currentOrderTime = 0;

        for (OrderItem item : order.getItems()) {

            currentOrderTime +=
                    item.getDish().getPreparationTimeInMinutes()
                            * item.getQuantity();
        }

        return backlogTime + currentOrderTime;
    }

    private int calculateBacklogTime() {

        List<CustomerOrder> activeOrders =
                orderRepository.findByStatus(OrderStatus.PLACED);

        int total = 0;

        for (CustomerOrder order : activeOrders) {

            for (OrderItem item : order.getItems()) {

                total += item.getDish()
                        .getPreparationTimeInMinutes()
                        * item.getQuantity();
            }
        }

        return total;
    }

    @Override
    public String getStrategyName() {
        return "FIFO_BACKLOG_STRATEGY";
    }
}