package com.theaa.exercise.vehiclemanagementapi.service;

import com.theaa.exercise.vehiclemanagementapi.exception.VrnAlreadyExistException;
import com.theaa.exercise.vehiclemanagementapi.exception.VrnUnmatchException;
import com.theaa.exercise.vehiclemanagementapi.repository.VehicleRepository;
import com.theaa.exercise.vehiclemanagementapi.repository.model.Vehicle;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleDetailsDatabaseService implements VehicleDetailsService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle addVehicle(Vehicle vehicle) throws VrnAlreadyExistException {
        if (!vehicleRepository.findByVrn(vehicle.getVrn()).isEmpty()) {
            throw new VrnAlreadyExistException();
        }
        return vehicleRepository.saveAndFlush(vehicle);
    }

    @Override
    public Vehicle updateVehicleByVrn(String vrn, Vehicle updatedVehicle) throws VrnUnmatchException {
        if (!StringUtils.equals(vrn, updatedVehicle.getVrn())) {
            throw new VrnUnmatchException();
        }
        List<Vehicle> vehicleRecords = vehicleRepository.findByVrn(vrn);
        if (vehicleRecords.size() != 1) {
            throw new VrnUnmatchException();
        }
        Vehicle vehicle = vehicleRecords.get(0);
        BeanUtils.copyProperties(updatedVehicle, vehicle, "id", "vrn");
        return vehicleRepository.saveAndFlush(vehicle);
    }

    @Override
    public List<Vehicle> searchVehiclesByVrns(List<String> vrns) {
        return vehicleRepository.findByVrnIn(vrns);
    }
}
