package com.microservices.vehicles.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.microservices.vehicles.model.Vehicle;
import com.microservices.vehicles.model.VehiclesStatusEnum;
import com.microservices.vehicles.service.VehicleService;

public class ChangeVehicleStatusSchedule {

	VehicleService vehicleService ;
	List<Vehicle> vehicleList ;
	
	@Autowired
	ChangeVehicleStatusSchedule(VehicleService vehicleService){
		this.vehicleService = vehicleService;
	}
	@Scheduled(fixedDelay = 60000,initialDelay = 60000)
	private void changeVehiclesStatus() {
		int connectionHeath;
		
		vehicleList = this.vehicleService.getAllVehicles();
		
		for(Vehicle vehicle: vehicleList) {
			
			connectionHeath = getRandomNumberInRange(0, 1);
			System.out.println(connectionHeath);
			
			if(connectionHeath ==0) {
				vehicle.setStatus(VehiclesStatusEnum.DISCONNECTED.getStatus());
			}else if (connectionHeath ==1) {
				vehicle.setStatus(VehiclesStatusEnum.CONNECTED.getStatus());
			}
		}
	}
	
	private int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		return (int)(Math.random() * ((max - min) + 1)) + min;
	}
}
