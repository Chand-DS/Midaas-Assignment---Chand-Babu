package com.example.MoonWalkRestro.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.MoonWalkRestro.entity.ExecutionLog;
import com.example.MoonWalkRestro.repository.ExecutionLogRepository;
import com.example.MoonWalkRestro.service.ExecutionLogService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExecutionLogServiceImpl implements ExecutionLogService {

    private final ExecutionLogRepository executionLogRepository;

    @Override
    public void logExecution(Long orderId,
                             Integer estimatedTime,
                             Integer elapsedTime,
                             String status,
                             String algorithm,
                             String remarks) {

        ExecutionLog log = ExecutionLog.builder()
                .orderId(orderId)
                .timestamp(LocalDateTime.now())
                .estimatedTimeInMinutes(estimatedTime)
                .elapsedTimeInMinutes(elapsedTime)
                .orderStatus(status)
                .algorithmUsed(algorithm)
                .remarks(remarks)
                .build();

        executionLogRepository.save(log);
    }
}
