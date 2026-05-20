package com.example.MoonWalkRestro.controller;

import com.example.MoonWalkRestro.entity.ExecutionLog;
import com.example.MoonWalkRestro.repository.ExecutionLogRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
@Tag(name = "Execution Logs", description = "Audit and execution tracking APIs")
public class ExecutionLogController {

    private final ExecutionLogRepository executionLogRepository;

    @GetMapping
    public List<ExecutionLog> getAllLogs() {

        return executionLogRepository.findAll();
    }
}
