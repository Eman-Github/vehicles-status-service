package com.microservices.vehicles.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.microservices.vehicles.exception.BadRequestException;
import com.microservices.vehicles.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microservices.vehicles.model.Vehicle;
import com.microservices.vehicles.service.VehicleService;

@RestController
@RequestMapping(value="/vehicles")
public class VehicleStatusController {
	
	VehicleService vehicleService;
	
	@Autowired
	public VehicleStatusController(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}
	//@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = "/")
	public ResponseEntity<List<Vehicle>> getAllVehicles(){
		
		List<Vehicle> vehicles  = this.vehicleService.getAllVehicles();
		if(!CollectionUtils.isEmpty(vehicles)) {
			
			return ResponseEntity.ok(vehicles);
		}else {
			throw new ResourceNotFoundException("There is no Vehicles exists in the system");
		}
		
	}
	
	//@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = "/status/{statusName}")
	public ResponseEntity<List<Vehicle>> getVehiclesByStatus(@PathVariable String statusName) throws BadRequestException{
		
		if(statusName != null) {
			List<Vehicle> vehicles  = this.vehicleService.getVehiclesByStatus(statusName);
			if(!CollectionUtils.isEmpty(vehicles)) {
				
				return ResponseEntity.ok(vehicles);
			}else {
				
				throw new ResourceNotFoundException("There is no Vehicles exists in the system with Status " + statusName );
			}	
		}else {
			throw new BadRequestException("Bad Request , No Status provided ");
		}
	}
	
	//@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = "/customer/{customerId}")
	public ResponseEntity<List<Vehicle>> getVehiclesByCustomerId(@PathVariable Integer customerId) throws BadRequestException{
		
		if(customerId != null) {
			List<Vehicle> vehicles  = this.vehicleService.getVehiclesByCustomerId(customerId);
			if(!CollectionUtils.isEmpty(vehicles)) {
				
				return ResponseEntity.ok(vehicles);
			}else {
				throw new ResourceNotFoundException("There is no Vehicles exists in the system for this customer id = " + customerId);
			}
		}else {
			throw new BadRequestException("Bad Request , No Customer ID provided ");
		}
	}
}
