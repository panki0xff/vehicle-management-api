package com.theaa.exercise.vehiclemanagementapi.repository;

import com.theaa.exercise.vehiclemanagementapi.repository.model.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    public void testSaveAndSearch() {
        // Given
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVrn("ABC123");
        vehicle1.setMake("Toyota");
        vehicle1.setModel("AE86");
        vehicle1.setYear(1986);
        vehicle1.setFuelType("Petrol");
        vehicleRepository.save(vehicle1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setVrn("DEF123");
        vehicle2.setMake("Tesla");
        vehicle2.setModel("Model X");
        vehicle2.setYear(2023);
        vehicle2.setFuelType("Electric");
        vehicleRepository.save(vehicle2);

        List<Vehicle> foundVehicles = vehicleRepository.findByVrn("ABC123");
        assertEquals(1, foundVehicles.size());
        assertEquals(vehicle1, foundVehicles.get(0));

        foundVehicles = vehicleRepository.findByVrn("QQQ123");
        assertEquals(0, foundVehicles.size());
    }
}
