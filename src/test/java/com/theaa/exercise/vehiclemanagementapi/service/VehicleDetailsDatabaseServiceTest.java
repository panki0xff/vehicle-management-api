package com.theaa.exercise.vehiclemanagementapi.service;

import com.theaa.exercise.vehiclemanagementapi.exception.VrnAlreadyExistException;
import com.theaa.exercise.vehiclemanagementapi.exception.VrnUnmatchException;
import com.theaa.exercise.vehiclemanagementapi.repository.VehicleRepository;
import com.theaa.exercise.vehiclemanagementapi.repository.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VehicleDetailsDatabaseServiceTest {
    @InjectMocks
    private VehicleDetailsDatabaseService vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setVrn("TTT123");
        vehicle.setMake("Toyota");
        vehicle.setModel("Prius");
        vehicle.setYear(2000);
        vehicle.setFuelType("Petrol");

        when(vehicleRepository.findByVrn("TTT123")).thenReturn(new ArrayList<>());
        when(vehicleRepository.saveAndFlush(vehicle)).then(invocationOnMock -> {
            Vehicle v = invocationOnMock.getArgument(0);
            v.setId(1234L);
            return v;
        });

        try {
            Vehicle savedVehicle = vehicleService.addVehicle(vehicle);
            verify(vehicleRepository, times(1)).findByVrn("TTT123");
            verify(vehicleRepository, times(1)).saveAndFlush(vehicle);
            assertEquals(1234L, savedVehicle.getId());
            assertEquals(vehicle.getVrn(), savedVehicle.getVrn());
            assertEquals(vehicle.getMake(), savedVehicle.getMake());
            assertEquals(vehicle.getModel(), savedVehicle.getModel());
            assertEquals(vehicle.getYear(), savedVehicle.getYear());
            assertEquals(vehicle.getFuelType(), savedVehicle.getFuelType());
        } catch (VrnAlreadyExistException e) {
            fail();
        }
    }

    @Test
    void testAddVehicleFail() {
        Vehicle vehicle = new Vehicle();
        vehicle.setVrn("TTT123");
        vehicle.setMake("Toyota");
        vehicle.setModel("Prius");
        vehicle.setYear(2000);
        vehicle.setFuelType("Petrol");

        when(vehicleRepository.findByVrn("TTT123")).thenReturn(List.of(new Vehicle()));
        assertThrows(VrnAlreadyExistException.class, () -> {
            vehicleService.addVehicle(vehicle);
        });
        verify(vehicleRepository, times(1)).findByVrn("TTT123");
        verify(vehicleRepository, never()).saveAndFlush(vehicle);
    }

    @Test
    void updateVehicleByVrn() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1234L);
        vehicle.setVrn("TTT123");
        vehicle.setMake("Toyota");
        vehicle.setModel("Prius");
        vehicle.setYear(2000);
        vehicle.setFuelType("Petrol");

        when(vehicleRepository.findByVrn("TTT123")).thenReturn(List.of(vehicle));
        when(vehicleRepository.saveAndFlush(vehicle)).then(invocationOnMock -> {
            Vehicle v = invocationOnMock.getArgument(0);
            Vehicle result = new Vehicle();
            BeanUtils.copyProperties(vehicle, result);
            BeanUtils.copyProperties(v, result, "id");
            return result;
        });

        try {
            Vehicle vehicle2 = new Vehicle();
            vehicle2.setVrn("TTT123");
            vehicle2.setMake("Toyota");
            vehicle2.setModel("Prius");
            vehicle2.setYear(2011);
            vehicle2.setFuelType("Hybrid");

            Vehicle savedVehicle = vehicleService.updateVehicleByVrn(vehicle2.getVrn(), vehicle2);
            verify(vehicleRepository, times(1)).findByVrn("TTT123");
            verify(vehicleRepository, times(1)).saveAndFlush(vehicle);
            assertEquals(1234L, vehicle.getId());
            assertEquals(savedVehicle.getVrn(), vehicle2.getVrn());
            assertEquals(savedVehicle.getMake(), vehicle2.getMake());
            assertEquals(savedVehicle.getModel(), vehicle2.getModel());
            assertEquals(savedVehicle.getYear(), vehicle2.getYear());
            assertEquals(savedVehicle.getFuelType(), vehicle2.getFuelType());
        } catch (VrnUnmatchException e) {
            fail();
        }
    }

    @Test
    void updateVehicleByVrnFail() {
        Vehicle vehicle = new Vehicle();
        vehicle.setVrn("TTT123");
        vehicle.setMake("Toyota");
        vehicle.setModel("Prius");
        vehicle.setYear(2000);
        vehicle.setFuelType("Petrol");

        when(vehicleRepository.findByVrn("TTT123")).thenReturn(new ArrayList<>());
        assertThrows(VrnUnmatchException.class, () -> {
            vehicleService.updateVehicleByVrn(vehicle.getVrn(), vehicle);
        });
        verify(vehicleRepository, times(1)).findByVrn("TTT123");
        verify(vehicleRepository, never()).saveAndFlush(vehicle);
    }

    @Test
    void searchVehiclesByVrns() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1234L);
        vehicle.setVrn("TTT123");
        vehicle.setMake("Toyota");
        vehicle.setModel("Prius");
        vehicle.setYear(2000);
        vehicle.setFuelType("Petrol");

        when(vehicleRepository.findByVrnIn(List.of("TTT123"))).thenReturn(List.of(vehicle));
        List<Vehicle> vehicleList = vehicleService.searchVehiclesByVrns(List.of("TTT123"));
        verify(vehicleRepository, times(1)).findByVrnIn(List.of("TTT123"));
        assertEquals(1, vehicleList.size());
        Vehicle checkVehicle = vehicleList.get(0);
        assertEquals(vehicle.getId(), checkVehicle.getId());
        assertEquals(vehicle.getVrn(), checkVehicle.getVrn());
        assertEquals(vehicle.getMake(), checkVehicle.getMake());
        assertEquals(vehicle.getModel(), checkVehicle.getModel());
        assertEquals(vehicle.getYear(), checkVehicle.getYear());
        assertEquals(vehicle.getFuelType(), checkVehicle.getFuelType());
    }

    @Test
    void searchVehiclesByVrnsMulti() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1234L);
        vehicle.setVrn("TTT123");
        vehicle.setMake("Toyota");
        vehicle.setModel("Prius");
        vehicle.setYear(2000);
        vehicle.setFuelType("Petrol");

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setId(2345L);
        vehicle2.setVrn("GGG444");
        vehicle2.setMake("Toyota");
        vehicle2.setModel("Prius");
        vehicle2.setYear(2010);
        vehicle2.setFuelType("Petrol");

        when(vehicleRepository.findByVrnIn(List.of("TTT123", "GGG444"))).thenReturn(List.of(vehicle, vehicle2));
        List<Vehicle> vehicleList = vehicleService.searchVehiclesByVrns(List.of("TTT123", "GGG444"));
        verify(vehicleRepository, times(1)).findByVrnIn(List.of("TTT123", "GGG444"));
        assertEquals(2, vehicleList.size());
        Vehicle checkVehicle = vehicleList.get(0);
        assertEquals(vehicle.getId(), checkVehicle.getId());
        assertEquals(vehicle.getVrn(), checkVehicle.getVrn());
        assertEquals(vehicle.getMake(), checkVehicle.getMake());
        assertEquals(vehicle.getModel(), checkVehicle.getModel());
        assertEquals(vehicle.getYear(), checkVehicle.getYear());
        assertEquals(vehicle.getFuelType(), checkVehicle.getFuelType());
    }

    @Test
    void searchVehiclesByVrnsEmpty() {

        when(vehicleRepository.findByVrnIn(List.of("TTT123", "GGG444"))).thenReturn(new ArrayList<>());
        List<Vehicle> vehicleList = vehicleService.searchVehiclesByVrns(List.of("TTT123", "GGG444"));
        verify(vehicleRepository, times(1)).findByVrnIn(List.of("TTT123", "GGG444"));
        assertEquals(0, vehicleList.size());
    }
}