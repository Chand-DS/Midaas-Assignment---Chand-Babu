package com.example.MoonWalkRestro.service;

public interface ExecutionLogService {

    void logExecution(Long orderId,
                      Integer estimatedTime,
                      Integer elapsedTime,
                      String status,
                      String algorithm,
                      String remarks);
}