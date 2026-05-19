package com.example.MoonWalkRestro.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer preparationTimeInMinutes;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private KitchenResource requiredResource;
}