package com.example.MoonWalkRestro.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "execution_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExecutionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private LocalDateTime timestamp;

    private Integer estimatedTimeInMinutes;

    private Integer elapsedTimeInMinutes;

    private String orderStatus;

    private String algorithmUsed;

    private String remarks;
}