package com.theaa.exercise.vehiclemanagementapi.service;

import com.theaa.exercise.vehiclemanagementapi.exception.VrnAlreadyExistException;
import com.theaa.exercise.vehiclemanagementapi.exception.VrnUnmatchException;
import com.theaa.exercise.vehiclemanagementapi.repository.model.Vehicle;

import java.util.List;

public interface VehicleDetailsService {
    Vehicle addVehicle(Vehicle vehicle) throws VrnAlreadyExistException;

    Vehicle updateVehicleByVrn(String vrn, Vehicle updatedVehicle) throws VrnUnmatchException;

    List<Vehicle> searchVehiclesByVrns(List<String> vrns);
}
