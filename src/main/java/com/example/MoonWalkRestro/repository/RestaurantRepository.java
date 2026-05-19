package com.example.MoonWalkRestro.repository;


import com.example.MoonWalkRestro.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
