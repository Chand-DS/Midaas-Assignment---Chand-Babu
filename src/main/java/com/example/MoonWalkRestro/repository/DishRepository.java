package com.example.MoonWalkRestro.repository;


import com.example.MoonWalkRestro.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
}