package com.microservices.vehicles.api;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.microservices.vehicles.repository.VehicleRepository;
import com.microservices.vehicles.schedule.ChangeVehicleStatusSchedule;
import com.microservices.vehicles.service.VehicleService;

@Retention(RUNTIME)
@Target(TYPE)

@Import({
	  
	VehicleService.class,
	VehicleRepository.class,
	ChangeVehicleStatusSchedule.class
	})
public @interface ImportApiDependencies {

}
