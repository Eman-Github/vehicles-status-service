package com.microservices.vehicles.model;

public enum VehiclesStatusEnum {
	
	CONNECTED("Connected"),
	DISCONNECTED("Disconnected");

	private String status;
	
	VehiclesStatusEnum(String status) {
	    this.setStatus(status);
	  }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
