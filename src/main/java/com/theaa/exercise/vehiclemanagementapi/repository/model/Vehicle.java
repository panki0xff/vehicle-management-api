package com.theaa.exercise.vehiclemanagementapi.repository.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vrn;
    private String make;
    private String model;
    @Column(name = "mfg_year")
    private int year;
    private String fuelType;
}
