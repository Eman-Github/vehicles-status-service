package com.microservices.vehicles.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.microservices.vehicles.model.Customer;
import com.microservices.vehicles.model.Vehicle;
import com.microservices.vehicles.model.VehiclesStatusEnum;

@Repository
public class VehicleRepository {
	
	List<Vehicle> vehicleList = new ArrayList<Vehicle>();
	public VehicleRepository() {
			initiateData();
	}
	
	private void initiateData() {
		
		Customer customer1 = new Customer();
		customer1.setCustomerId(1);
		customer1.setCustomerName("Kalles Grustransporter AB");
		customer1.setCustomerAddress("Cementvägen 8, 111 11 Södertälje ");
		
		Vehicle vehicle1Cust1 = new Vehicle();
		vehicle1Cust1.setVehicleId("YS2R4X20005399401");
		vehicle1Cust1.setRegNumber("ABC123");
		vehicle1Cust1.setStatus(VehiclesStatusEnum.CONNECTED.getStatus());
		vehicle1Cust1.setCustomer(customer1);
		vehicleList.add(vehicle1Cust1);
		
		Vehicle vehicle2Cust1 = new Vehicle();
		vehicle2Cust1.setVehicleId("VLUR4X20009093588");
		vehicle2Cust1.setRegNumber("DEF456");
		vehicle2Cust1.setStatus(VehiclesStatusEnum.DISCONNECTED.getStatus());
		vehicle2Cust1.setCustomer(customer1);
		vehicleList.add(vehicle2Cust1);
		
		Vehicle vehicle3Cust1 = new Vehicle();
		vehicle3Cust1.setVehicleId("VLUR4X20009048066");
		vehicle3Cust1.setRegNumber("GHI789");
		vehicle3Cust1.setStatus(VehiclesStatusEnum.CONNECTED.getStatus());
		vehicle3Cust1.setCustomer(customer1);
		vehicleList.add(vehicle3Cust1);
	
		//======================================================================
		
		Customer customer2 = new Customer();
		customer2.setCustomerId(2);
		customer2.setCustomerName("Johans Bulk AB");
		customer2.setCustomerAddress("Balkvägen 12, 222 22 Stockholm");
		
		Vehicle vehicle1Cust2 = new Vehicle();
		vehicle1Cust2.setVehicleId("YS2R4X20005388011");
		vehicle1Cust2.setRegNumber("JKL012");
		vehicle1Cust2.setStatus(VehiclesStatusEnum.CONNECTED.getStatus());
		vehicle1Cust2.setCustomer(customer2);
		vehicleList.add(vehicle1Cust2);
		
		Vehicle vehicle2Cust2 = new Vehicle();
		vehicle2Cust2.setVehicleId("YS2R4X20005387949");
		vehicle2Cust2.setRegNumber("MNO345");
		vehicle2Cust2.setStatus(VehiclesStatusEnum.DISCONNECTED.getStatus());
		vehicle2Cust2.setCustomer(customer2);
		vehicleList.add(vehicle2Cust2);
		
		//======================================================================
		
		Customer customer3 = new Customer();
		customer3.setCustomerId(3);
		customer3.setCustomerName("Johans Bulk AB");
		customer3.setCustomerAddress("Balkvägen 12, 222 22 Stockholm");
		
		Vehicle vehicle1Cust3 = new Vehicle();
		vehicle1Cust3.setVehicleId("YS2R4X20005388011");
		vehicle1Cust3.setRegNumber("JKL012");
		vehicle1Cust3.setStatus(VehiclesStatusEnum.CONNECTED.getStatus());
		vehicle1Cust3.setCustomer(customer3);
		vehicleList.add(vehicle1Cust3);
		
		Vehicle vehicle2Cust3 = new Vehicle();
		vehicle2Cust3.setVehicleId("YS2R4X20005387949");
		vehicle2Cust3.setRegNumber("MNO345");
		vehicle2Cust3.setStatus(VehiclesStatusEnum.DISCONNECTED.getStatus());
		vehicle2Cust3.setCustomer(customer3);
		vehicleList.add(vehicle2Cust3);
	}
	
	public List<Vehicle> getAllVehicles(){
		 return vehicleList;
	}
	
	public List<Vehicle> getVehiclesByStatus(String statusName){
		List<Vehicle> vehiclesStatusList = new ArrayList<Vehicle>();
		
		for(Vehicle vehicle :vehicleList) {
			if(vehicle.getStatus() != null && vehicle.getStatus().equalsIgnoreCase(statusName)) {
				vehiclesStatusList.add(vehicle);
			}
		}
		 return vehiclesStatusList;
	}
	
	public List<Vehicle> getVehiclesByCustomerId(Integer customerId){
		List<Vehicle> customerVehiclesList = new ArrayList<Vehicle>();
		
		for(Vehicle vehicle :vehicleList) {
			if(vehicle.getCustomer() != null && vehicle.getCustomer().getCustomerId() == customerId ) {
				customerVehiclesList.add(vehicle);
			}
		}
		 return customerVehiclesList;
	}

}
