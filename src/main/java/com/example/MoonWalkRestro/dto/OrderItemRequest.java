package com.example.MoonWalkRestro.dto;

import lombok.Data;

@Data
public class OrderItemRequest {

    private Long dishId;

    private Integer quantity;
}
