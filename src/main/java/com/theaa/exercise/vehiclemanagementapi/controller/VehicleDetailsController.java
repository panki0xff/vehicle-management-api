package com.theaa.exercise.vehiclemanagementapi.controller;

import com.theaa.exercise.vehiclemanagementapi.repository.model.Vehicle;
import com.theaa.exercise.vehiclemanagementapi.service.VehicleDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Vehicle Management", description = "Endpoints to manage vehicle details")
@RequestMapping("/vehicle")
public class VehicleDetailsController {
    @Autowired
    private VehicleDetailsService vehicleDetailsService;

    @Operation(summary = "Add vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vehicle.class))),
            @ApiResponse(responseCode = "400", description = "Request Fail")
    })
    @PostMapping("/add")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        try {
            Vehicle addedVehicle = vehicleDetailsService.addVehicle(vehicle);
            return ResponseEntity.ok(addedVehicle);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Update an existing vehicle by VRN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle details updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vehicle.class))),
            @ApiResponse(responseCode = "404", description = "Vehicle not found with the provided VRN")
    })
    @PutMapping("/update/{vrn}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable String vrn, @RequestBody Vehicle vehicle) {
        Vehicle updatedVehicle = vehicleDetailsService.updateVehicleByVrn(vrn, vehicle);
        if (updatedVehicle != null) {
            return ResponseEntity.ok(updatedVehicle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Search for vehicles by VRNs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of vehicles found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "No vehicles found with given VRNs")
    })
    @GetMapping("/search")
    public ResponseEntity<List<Vehicle>> searchVehiclesByVrns(@RequestParam List<String> vrns) {
        List<Vehicle> foundVehicles = vehicleDetailsService.searchVehiclesByVrns(vrns);
        if(!CollectionUtils.isEmpty(foundVehicles)){
            return ResponseEntity.ok(foundVehicles);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}