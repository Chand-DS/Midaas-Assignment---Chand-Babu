package com.example.MoonWalkRestro.strategy;

import com.example.MoonWalkRestro.entity.CustomerOrder;

public interface EstimationStrategy {

    int estimatePreparationTime(CustomerOrder order);

    String getStrategyName();
}
