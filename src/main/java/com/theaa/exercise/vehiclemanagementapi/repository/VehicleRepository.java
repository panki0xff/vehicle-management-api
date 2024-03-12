package com.theaa.exercise.vehiclemanagementapi.repository;

import com.theaa.exercise.vehiclemanagementapi.repository.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByVrn(String vrn);
}
