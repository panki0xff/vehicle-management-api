package com.theaa.exercise.vehiclemanagementapi.service;

import com.theaa.exercise.vehiclemanagementapi.repository.model.Vehicle;

import java.util.List;

public interface VehicleDetailsService {
    Vehicle addVehicle(Vehicle vehicle);

    Vehicle updateVehicleByVrn(String vrn, Vehicle updatedVehicle);

    List<Vehicle> searchVehiclesByVrns(List<String> vrns);
}
