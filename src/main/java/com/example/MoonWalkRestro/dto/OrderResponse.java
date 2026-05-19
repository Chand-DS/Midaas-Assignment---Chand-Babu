package com.example.MoonWalkRestro.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderResponse {

    private Long orderId;

    private String status;

    private LocalDateTime estimatedReadyTime;

    private LocalDateTime actualReadyTime;

    private Long countdownInSeconds;
}
