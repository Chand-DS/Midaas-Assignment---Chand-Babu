package com.example.MoonWalkRestro.controller;

import com.example.MoonWalkRestro.entity.ExecutionLog;
import com.example.MoonWalkRestro.repository.ExecutionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class ExecutionLogController {

    private final ExecutionLogRepository executionLogRepository;

    @GetMapping
    public List<ExecutionLog> getAllLogs() {

        return executionLogRepository.findAll();
    }
}
