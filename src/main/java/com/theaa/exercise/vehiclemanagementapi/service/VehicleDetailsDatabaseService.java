package com.theaa.exercise.vehiclemanagementapi.service;

import com.theaa.exercise.vehiclemanagementapi.repository.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleDetailsDatabaseService implements VehicleDetailsService {

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        return null;
    }

    @Override
    public Vehicle updateVehicleByVrn(String vrn, Vehicle updatedVehicle) {
        return null;
    }

    @Override
    public List<Vehicle> searchVehiclesByVrns(List<String> vrns) {
        return null;
    }
}
