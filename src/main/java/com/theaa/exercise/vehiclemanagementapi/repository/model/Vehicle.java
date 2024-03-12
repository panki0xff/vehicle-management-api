package com.theaa.exercise.vehiclemanagementapi.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @JsonIgnore
    private Long id;

    @NotNull
    private String vrn;

    @NotNull
    private String make;

    @NotNull
    private String model;

    @NotNull
    @Column(name = "mfg_year")
    private int year;

    @NotNull
    private String fuelType;
}
