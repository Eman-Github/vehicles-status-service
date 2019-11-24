package com.microservices.vehicles.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.microservices.vehicles.model.Customer;
import com.microservices.vehicles.model.Vehicle;
import com.microservices.vehicles.model.VehiclesStatusEnum;
import com.microservices.vehicles.repository.VehicleRepository;

public class VehicleServiceTest {

	private VehicleService vehicleService;
	private VehicleRepository vehicleRepository;
	
	@BeforeEach
	  public void beforeEach() {
		this.vehicleRepository = mock(VehicleRepository.class);
	    this.vehicleService = new VehicleService(vehicleRepository);
	}
	
	@Test
	@DisplayName("GIVEN "
	      + " WHEN a user is trying to get all vehicles in the system"
	      + " THEN List of Vehicles is returned successfully")
	  public void test_getAllVehicles_Exist() throws Exception {
		
		List<Vehicle> vehiclesList = new ArrayList<>();
		Vehicle vehicle1Cust1 = new Vehicle();
		vehicle1Cust1.setVehicleId("YS2R4X20005399401");
		vehicle1Cust1.setRegNumber("ABC123");
		vehicle1Cust1.setStatus(VehiclesStatusEnum.CONNECTED.getStatus());
		vehiclesList.add(vehicle1Cust1);
		
		when(this.vehicleRepository.getAllVehicles()).thenReturn(vehiclesList);
		assertEquals(1, vehicleService.getAllVehicles().size() );
	}
	
	@Test
	@DisplayName("GIVEN "
	      + " WHEN a user is trying to get all vehicles in the system , but non exists"
	      + " THEN List of empty Vehicles is returned")
	  public void test_getAllVehicles_NotExist() throws Exception {
		
		List<Vehicle> emplyVehiclesList = null;
		when(this.vehicleRepository.getAllVehicles()).thenReturn(emplyVehiclesList);
		
		assertNull( vehicleService.getAllVehicles() );
	}
	@Test
	@DisplayName("GIVEN "
	      + " WHEN a user is trying to get vehicles with specific status in the system"
	      + " THEN List of Vehicles is returned successfully")
	  public void test_getVehiclesByStatus_Exist() throws Exception {
		
		List<Vehicle> vehiclesList = new ArrayList<>();
		Vehicle vehicle2Cust1 = new Vehicle();
		vehicle2Cust1.setVehicleId("VLUR4X20009093588");
		vehicle2Cust1.setRegNumber("DEF456");
		vehicle2Cust1.setStatus(VehiclesStatusEnum.DISCONNECTED.getStatus());
		vehiclesList.add(vehicle2Cust1);
		
		Vehicle vehicle2Cust2 = new Vehicle();
		vehicle2Cust2.setVehicleId("YS2R4X20005387949");
		vehicle2Cust2.setRegNumber("MNO345");
		vehicle2Cust2.setStatus(VehiclesStatusEnum.DISCONNECTED.getStatus());
		vehiclesList.add(vehicle2Cust2);
		
		Vehicle vehicle2Cust3 = new Vehicle();
		vehicle2Cust3.setVehicleId("YS2R4X20005387949");
		vehicle2Cust3.setRegNumber("MNO345");
		vehicle2Cust3.setStatus(VehiclesStatusEnum.DISCONNECTED.getStatus());
		vehiclesList.add(vehicle2Cust3);
		
		when(this.vehicleRepository.getVehiclesByStatus("disconnected")).thenReturn(vehiclesList);
		
		assertEquals(vehiclesList.size(),vehicleService.getVehiclesByStatus("disconnected").size() );
	}
	
	@Test
	@DisplayName("GIVEN "
	      + " WHEN a user is trying to get vehicles with specific status in the system , but not exists"
	      + " THEN List of empty Vehicles is returned.")
	  public void test_getVehiclesByStatus_NotExist() throws Exception {
		
		List<Vehicle> emplyVehiclesList = null;
		when(this.vehicleRepository.getVehiclesByStatus("connected")).thenReturn(emplyVehiclesList);
		
		assertNull( vehicleService.getVehiclesByStatus("connected") );
	}
	
	@Test
	@DisplayName("GIVEN "
	      + " WHEN a user is trying to get vehicles for specific customer in the system"
	      + "  THEN List of Vehicles for that cstomer is returned")
	  public void test_getVehiclesByCustomer_Exist() throws Exception {
		
		Customer customer1 = new Customer();
		customer1.setCustomerId(1);
		customer1.setCustomerName("Kalles Grustransporter AB");
		customer1.setCustomerAddress("Cementvägen 8, 111 11 Södertälje ");
		
		List<Vehicle> vehiclesList = new ArrayList<>();
		Vehicle vehicle1Cust1 = new Vehicle();
		vehicle1Cust1.setVehicleId("YS2R4X20005399401");
		vehicle1Cust1.setRegNumber("ABC123");
		vehicle1Cust1.setStatus(VehiclesStatusEnum.CONNECTED.getStatus());
		vehicle1Cust1.setCustomer(customer1);
		vehiclesList.add(vehicle1Cust1);
		
		Vehicle vehicle2Cust1 = new Vehicle();
		vehicle2Cust1.setVehicleId("VLUR4X20009093588");
		vehicle2Cust1.setRegNumber("DEF456");
		vehicle2Cust1.setStatus(VehiclesStatusEnum.DISCONNECTED.getStatus());
		vehicle1Cust1.setCustomer(customer1);
		vehiclesList.add(vehicle2Cust1);
		
		Vehicle vehicle3Cust1 = new Vehicle();
		vehicle3Cust1.setVehicleId("VLUR4X20009048066");
		vehicle3Cust1.setRegNumber("GHI789");
		vehicle3Cust1.setStatus(VehiclesStatusEnum.CONNECTED.getStatus());
		vehicle1Cust1.setCustomer(customer1);
		vehiclesList.add(vehicle3Cust1);
	
		when(this.vehicleRepository.getVehiclesByCustomerId(1)).thenReturn(vehiclesList);
		
		assertEquals(vehiclesList.size(),vehicleService.getVehiclesByCustomerId(1).size());
	}
	
	@Test
	@DisplayName("GIVEN "
	      + " WHEN a user is trying to get vehicles for specific customer in the system , but not exists"
	      + " THEN ResourceNotFoundException is thrown.")
	  public void test_getVehiclesByCustomer_NotExist() throws Exception {
		
		List<Vehicle> emplyVehiclesList = null;
		when(this.vehicleRepository.getVehiclesByCustomerId(5555)).thenReturn(emplyVehiclesList);
		
		assertNull( vehicleService.getVehiclesByCustomerId(5555) );
	}
	
}
