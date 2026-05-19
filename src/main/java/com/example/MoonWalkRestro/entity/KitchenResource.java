package com.example.MoonWalkRestro.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "kitchen_resource")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KitchenResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resourceName;

    private Integer totalUnits;

    private Integer availableUnits;
}