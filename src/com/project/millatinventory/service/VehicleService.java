package com.project.millatinventory.service;

import java.util.List;

import com.project.millatinventory.model.Moter;
import com.project.millatinventory.model.Vehicle;
import com.project.millatinventory.model.Vendors;

public interface VehicleService {

	public void saveVehicle(Vehicle vehicle);
	public List<Vehicle> getVehicle();
	public void updateVehicle(Vehicle vehicle);
	public int deleteVehicleById(int vehicleId);
	public Vehicle getVehicleById(int vehicleId);
	Vehicle getVehicleCriteria(Vehicle vehicle);
	List<Vehicle> getVehiclesByVehicleType(int vehicleType, Integer vendorId);
	List<Moter> getVehicleTypeByVendor(Vendors vendors);
	List<String> getExcavatorTypeByVendorAndVehicleType(int vendorId, int vehicleType);
}
