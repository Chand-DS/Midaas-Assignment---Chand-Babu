package com.example.MoonWalkRestro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MoonWalkRestro.entity.ExecutionLog;

public interface ExecutionLogRepository extends JpaRepository<ExecutionLog, Long> {
}
