package com.microservices.vehicles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservices.vehicles.model.Vehicle;
import com.microservices.vehicles.repository.VehicleRepository;

@Service
public class VehicleService {

	VehicleRepository vehicleRepository; 
	
	@Autowired
	public VehicleService(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}
	  /**
	   * Get List of Vehicles and their owner customers
	   */
	  public List<Vehicle> getAllVehicles() {
	    return this.vehicleRepository.getAllVehicles();
	  }
	  
	  public List<Vehicle> getVehiclesByStatus(String statusName){
		  List<Vehicle> vehicles = this.vehicleRepository.getVehiclesByStatus(statusName);
		  return vehicles;
	  }
	  public List<Vehicle> getVehiclesByCustomerId(Integer customerId){
		  List<Vehicle> vehicles = this.vehicleRepository.getVehiclesByCustomerId(customerId);
		  return vehicles;
	  }
	  
}
