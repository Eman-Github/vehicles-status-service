package com.microservices.vehicles.api;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.microservices.vehicles.exception.BadRequestException;
import com.microservices.vehicles.exception.ResourceNotFoundException;
import com.microservices.vehicles.model.Customer;
import com.microservices.vehicles.model.Vehicle;
import com.microservices.vehicles.model.VehiclesStatusEnum;
import com.microservices.vehicles.service.VehicleService;

public class VehicleStatusControllerTest {

	private VehicleService vehicleService;
	private VehicleStatusController vehicleStatusController;
	
	@BeforeEach
	  public void beforeEach() {
	    this.vehicleService = mock(VehicleService.class);
	    this.vehicleStatusController = new VehicleStatusController(vehicleService);
	}
	
	@Test
	@DisplayName("GIVEN "
	      + " WHEN a user is trying to get all vehicles in the system"
	      + " THEN Response return successfully with code 200")
	  public void test_getAllVehicles_Exist() throws Exception {
		
		List<Vehicle> vehiclesList = new ArrayList<>();
		Vehicle vehicle1Cust1 = new Vehicle();
		vehicle1Cust1.setVehicleId("YS2R4X20005399401");
		vehicle1Cust1.setRegNumber("ABC123");
		vehicle1Cust1.setStatus(VehiclesStatusEnum.CONNECTED.getStatus());
		vehiclesList.add(vehicle1Cust1);
		
		when(this.vehicleService.getAllVehicles()).thenReturn(vehiclesList);
		ResponseEntity<List<Vehicle>> responseEntity = this.vehicleStatusController.getAllVehicles();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode() );
	}
	
	@Test
	@DisplayName("GIVEN "
	      + " WHEN a user is trying to get all vehicles in the system , but non exists"
	      + " THEN ResourceNotFoundException is thrown.")
	  public void test_getAllVehicles_NotExist() throws Exception {
		
		List<Vehicle> emplyVehiclesList = null;
		when(this.vehicleService.getAllVehicles()).thenReturn(emplyVehiclesList);
		
		assertThrows(ResourceNotFoundException.class, () -> {
		       this.vehicleStatusController.getAllVehicles();
		    });
	}
	@Test
	@DisplayName("GIVEN "
	      + " WHEN a user is trying to get vehicles with specific status in the system"
	      + " THEN Response return successfully with code 200")
	  public void test_getVehiclesByStatus_Exist() throws Exception {
		
		List<Vehicle> vehiclesList = new ArrayList<>();
		Vehicle vehicle1Cust1 = new Vehicle();
		vehicle1Cust1.setVehicleId("YS2R4X20005399401");
		vehicle1Cust1.setRegNumber("ABC123");
		vehicle1Cust1.setStatus(VehiclesStatusEnum.CONNECTED.getStatus());
		vehiclesList.add(vehicle1Cust1);
		
		when(this.vehicleService.getVehiclesByStatus("connected")).thenReturn(vehiclesList);
		ResponseEntity<List<Vehicle>> responseEntity = this.vehicleStatusController.getVehiclesByStatus("connected");

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode() );
	}
	
	@Test
	@DisplayName("GIVEN "
	      + " WHEN a user is trying to get vehicles with specific status in the system , but not exists"
	      + " THEN ResourceNotFoundException is thrown.")
	  public void test_getVehiclesByStatus_NotExist() throws Exception {
		
		List<Vehicle> emplyVehiclesList = null;
		when(this.vehicleService.getVehiclesByStatus("connected")).thenReturn(emplyVehiclesList);
		
		assertThrows(ResourceNotFoundException.class, () -> {
		       this.vehicleStatusController.getVehiclesByStatus("connected");
		    });
	}
	
	@Test
	@DisplayName("GIVEN "
	      + " WHEN a user is trying to get vehicles with specific status in the system and not provided the status"
	      + " THEN BadRequestException is thrown.")
	  public void test_getVehiclesByStatus_WithNullStatus() throws Exception {
		
		assertThrows(BadRequestException.class, () -> {
		       this.vehicleStatusController.getVehiclesByStatus(null);
		    });
	}
	
	@Test
	@DisplayName("GIVEN "
	      + " WHEN a user is trying to get vehicles for specific customer in the system"
	      + " THEN Response return successfully with code 200")
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
		
		when(this.vehicleService.getVehiclesByCustomerId(1)).thenReturn(vehiclesList);
		ResponseEntity<List<Vehicle>> responseEntity = this.vehicleStatusController.getVehiclesByCustomerId(1);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode() );
	}
	
	@Test
	@DisplayName("GIVEN "
	      + " WHEN a user is trying to get vehicles for specific customer in the system , but not exists"
	      + " THEN ResourceNotFoundException is thrown.")
	  public void test_getVehiclesByCustomer_NotExist() throws Exception {
		
		List<Vehicle> emplyVehiclesList = null;
		when(this.vehicleService.getVehiclesByCustomerId(5555)).thenReturn(emplyVehiclesList);
		
		assertThrows(ResourceNotFoundException.class, () -> {
		       this.vehicleStatusController.getVehiclesByCustomerId(5555);
		    });
	}
	
	@Test
	@DisplayName("GIVEN "
	      + " WHEN a user is trying to get vehicles for specific customer in the system and not provided customer id"
	      + " THEN BadRequestException is thrown.")
	  public void test_getVehiclesByCustomer_WithNullCustomerId() throws Exception {
		
		assertThrows(BadRequestException.class, () -> {
		       this.vehicleStatusController.getVehiclesByCustomerId(null);
		    });
	}
}
